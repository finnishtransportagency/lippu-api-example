/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1).txt
 */
package fi.ficora.lippu.repository;

import fi.ficora.lippu.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends CrudRepository<Product, String> {


    List<Product> findByFromLatAndFromLon(Double fromLat, Double fromLon) ;
    List<Product> findByToLatAndToLon(Double toLat, Double toLon);
    List<Product> findByFromLatAndFromLonAndToLatAndToLon(Double fromLat,
                                                          Double fromLon,
                                                          Double toLat,
                                                          Double toLon) ;

    List<Product> findByFromId(String fromId) ;
    List<Product> findByToId(String toId) ;
    List<Product> findByFromIdAndToId(String fromId, String toId) ;

}
