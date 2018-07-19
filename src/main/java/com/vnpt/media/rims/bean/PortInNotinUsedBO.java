package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class PortInNotinUsedBO implements Serializable {

    private String id;
    private String code;
    private String name;
    private String cardName;
    private String totalPort;
    private String portInused;
    private String portNotInused;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getTotalPort() {
        return totalPort;
    }

    public void setTotalPort(String totalPort) {
        this.totalPort = totalPort;
    }

    public String getPortInused() {
        return portInused;
    }

    public void setPortInused(String portInused) {
        this.portInused = portInused;
    }

    public String getPortNotInused() {
        return portNotInused;
    }

    public void setPortNotInused(String portNotInused) {
        this.portNotInused = portNotInused;
    }

    
    
    
}
