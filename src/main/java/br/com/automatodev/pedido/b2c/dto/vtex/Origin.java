package br.com.automatodev.pedido.b2c.dto.vtex;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Origin {

    @JsonProperty("Account")
    private String account;

    @JsonProperty("Key")
    private String key;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Origin [account=" + account + ", key=" + key + "]";
    }
}
