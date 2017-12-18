/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1).txt
 */
package fi.ficora.lippu.repository;

import fi.ficora.lippu.domain.Nonce;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NonceRepository extends CrudRepository<Nonce, String> {
    Nonce findOneNonceByClient(String client);
    List<Nonce> findByExpBetween(LocalDateTime start, LocalDateTime stop);
}
