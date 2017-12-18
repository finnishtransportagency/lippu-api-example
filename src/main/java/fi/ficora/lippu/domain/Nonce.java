/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Nonce {
    @Id
    private String nonce;
    private LocalDateTime exp;
    private String client;

    public Nonce() {
    }

    public Nonce(String nonce) {
        this.nonce = nonce;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public LocalDateTime getExp() {
        return exp;
    }

    public void setExp(LocalDateTime exp) {
        this.exp = exp;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
