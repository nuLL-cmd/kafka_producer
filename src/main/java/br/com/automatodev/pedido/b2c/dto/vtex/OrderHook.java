package br.com.automatodev.pedido.b2c.dto.vtex;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderHook {

    @JsonProperty("hookConfig")
    private String hookConfig;

    @JsonProperty("Domain")
    private String domain;

    @JsonProperty("OrderId")
    private String orderId;

    @JsonProperty("State")
    private String state;

    @JsonProperty("LastState")
    private String lastState;

    @JsonProperty("LastChange")
    private String lastChange;

    @JsonProperty("CurrentChange")
    private String currentChange;

    @JsonProperty("Origin")
    private Origin origin;

    public String getHookConfig() {
        return hookConfig;
    }

    public void setHookConfig(String hookConfig) {
        this.hookConfig = hookConfig;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastState() {
        return lastState;
    }

    public void setLastState(String lastState) {
        this.lastState = lastState;
    }

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }

    public String getCurrentChange() {
        return currentChange;
    }

    public void setCurrentChange(String currentChange) {
        this.currentChange = currentChange;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "OrderHook [hookConfig="
                + hookConfig
                + ", domain="
                + domain
                + ", orderId="
                + orderId
                + ", state="
                + state
                + ", lastState="
                + lastState
                + ", lastChange="
                + lastChange
                + ", currentChange="
                + currentChange
                + ", origin="
                + origin
                + "]";
    }
}
