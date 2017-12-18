/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1).txt
 */
package fi.ficora.lippu.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data repository has general lippu service configuration
 * options, like server side encryption key configurations.
 */
@Repository
@ConfigurationProperties("data")
public class DataRepository {

    private String operator;
    private String secret;
    private String privateKey;
    private String publicKey;
    private String authTokenType;
    private List<String> passengerCategories;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public List<String> getPassengerCategories() {
        return passengerCategories;
    }

    public void setPassengerCategories(List<String> passengerCategories) {
        this.passengerCategories = passengerCategories;
    }

    public String getAuthTokenType() {
        return authTokenType;
    }

    public void setAuthTokenType(String authTokenType) {
        this.authTokenType = authTokenType;
    }
}
