/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

/**
 *
 * @author VNP
 */
public class LogicalLinkBO extends NodeBO {

    private String logicalLinkId;
    private String logicalLinkName;
    private String tlinkId;
    private String tlinkName;
    private String tpathId;
    private String tpathName;
    private String note;
    private String bandwidth;
    private String bandwidth_dv;

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getBandwidth_dv() {
        return bandwidth_dv;
    }

    public void setBandwidth_dv(String bandwidth_dv) {
        this.bandwidth_dv = bandwidth_dv;
    }


    public String getLogicalLinkName() {
        return logicalLinkName;
    }

    public void setLogicalLinkName(String logicalLinkName) {
        this.logicalLinkName = logicalLinkName;
    }

    public String getTlinkName() {
        return tlinkName;
    }

    public void setTlinkName(String tlinkName) {
        this.tlinkName = tlinkName;
    }

    public String getTpathName() {
        return tpathName;
    }

    public void setTpathName(String tpathName) {
        this.tpathName = tpathName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLogicalLinkId() {
        return logicalLinkId;
    }

    public void setLogicalLinkId(String logicalLinkId) {
        this.logicalLinkId = logicalLinkId;
    }

    public String getTlinkId() {
        return tlinkId;
    }

    public void setTlinkId(String tlinkId) {
        this.tlinkId = tlinkId;
    }

    public String getTpathId() {
        return tpathId;
    }

    public void setTpathId(String tpathId) {
        this.tpathId = tpathId;
    }
    
    

}
