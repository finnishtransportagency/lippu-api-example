/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Client {


    @Id
    private String account;

    private String name;
    private String contract;

    private String secret;
    private List<ClientKey> keys;


    public String getName() {
        return name;

    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public String getContract() {
        return contract;
    }

    public Client setContract(String contract) {
        this.contract = contract;
        return this;
    }


    public String getSecret() {
        return secret;
    }

    public Client setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<ClientKey> getKeys() {
        return keys;
    }

    public void setKeys(List<ClientKey> keys) {
        this.keys = keys;
    }
}


