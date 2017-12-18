/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu.domain;

public class ClientKey {

    private String keyfile;
    private String pubKeyId;

    public String getKeyfile() {
        return keyfile;
    }

    public ClientKey setKeyfile(String keyfile) {

        this.keyfile = keyfile;
        return this;
    }

    public String getPubKeyId() {
        return pubKeyId;
    }

    public ClientKey setPubKeyId(String pubKeyId) {
        this.pubKeyId = pubKeyId;
        return this;
    }
}
