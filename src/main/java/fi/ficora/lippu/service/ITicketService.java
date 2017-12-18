/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu.service;

import fi.ficora.lippu.domain.ReservationItem;

/**
 * Service for generating ticket payloads.
 *
 * @author markkuko
 */
public interface ITicketService {
    /**
     * Generates string representation of the ticket. Currently is only a STUB
     * implementation, NOT used by any validation machines.
     *
     * @return String representation of the ticket
     */
    String generateTicket(ReservationItem item);
}
