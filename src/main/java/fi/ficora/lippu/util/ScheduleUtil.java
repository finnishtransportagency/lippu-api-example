package fi.ficora.lippu.util;

import fi.ficora.lippu.config.Constants;
import fi.ficora.lippu.domain.Nonce;
import fi.ficora.lippu.domain.ReservationItem;
import fi.ficora.lippu.repository.NonceRepository;
import fi.ficora.lippu.repository.ReservationItemRepository;
import fi.ficora.lippu.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Scheduled jobs for the Lippu service.
 *
 * @author markkuko
 */
@Component
public class ScheduleUtil {

    private static final Logger log = LoggerFactory.getLogger(ScheduleUtil.class);
    @Autowired
    private NonceRepository nonceRepository;
    @Autowired
    private ReservationItemRepository reservationItemRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Removes expired nonces from {@link NonceRepository},
     * so the nonce repository does not clutter.
     */
    @Scheduled(cron = "5 */2 * * * *")
    public void removeExpiredNonces() {
        log.info("Starting scheduled job removeExpiredNonces");
        LocalDateTime current = LocalDateTime.now().minusMinutes(Constants
                .SCHEDULED_REMOVE_BUFFER_IN_MINUTES);
        LocalDateTime start = current.minusDays(Constants
                .SCHEDULED_REMOVE_BEFORE_IN_DAYS);
        List<Nonce> nonces = nonceRepository.findByExpBetween(start, current);
        log.debug("Found {} expired nonces", nonces.size());
        nonces.forEach(nonce ->{
            log.info("Removing expired nonce {}, expired at:", nonce.getNonce(), nonce
                    .getExp());
            nonceRepository.delete(nonce.getNonce());
        });
        log.info("Stopping scheduled job removeExpiredNonces");

    }

    /**
     * Removes expired and unconfirmed reservation items and
     * reservations (soft booked) from {@link ReservationItemRepository},
     * so they don't end up taking capacity.
     */
    @Scheduled(cron = "10 */1 * * * *")
    public void removeExpiredReservations() {
        log.info("Starting scheduled job removeExpiredReservations.");
        OffsetDateTime current = OffsetDateTime.now().minusMinutes(Constants
                .SCHEDULED_REMOVE_BUFFER_IN_MINUTES);
        OffsetDateTime start = current.minusDays(Constants
                .SCHEDULED_REMOVE_BEFORE_IN_DAYS);
        List<ReservationItem> items = reservationItemRepository
                .findAllByConfirmedAndReservationValidToBetween(false, start, current);
        log.debug("Found {} expired and unconfirmed reservation items to remove", items
                .size());
        Set<String> reservationCaseIds = new HashSet<>();
        items.forEach(item ->{
            log.info("Removing expired reservation item {}, expired at: {}", item.getId
                    (), item.getReservationValidTo());
            reservationCaseIds.add(item.getCaseId());
            reservationItemRepository.delete(item.getId());
        });
        log.debug("Found {} expired reservations to remove", reservationCaseIds.size());
        reservationCaseIds.forEach(id->{
            log.info("Removing reservation with case id {}", id);
            reservationRepository.delete(id);
        });
        log.info("Stopping scheduled job removeExpiredReservations.");
    }

}
