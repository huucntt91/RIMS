/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Cyano
 */
public class PmFmForm {
    private String vnpCode;
    private String nodeType;
    private String frequency;
    private String kpis;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date startTime;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date endTime;
    private String events;

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }
    
    public String getVnpCode() {
        return vnpCode;
    }

    public void setVnpCode(String vnpCode) {
        this.vnpCode = vnpCode;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getKpis() {
        return kpis;
    }

    public void setKpis(String kpis) {
        this.kpis = kpis;
    }
    
    
}
