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
public class DsLamBO extends  TNodeBO{
    private String vodLan;
    private String serialNo;
    private String note;

    public DsLamBO() {
    }

    public String getVodLan() {
        return vodLan;
    }

    public void setVodLan(String vodLan) {
        this.vodLan = vodLan;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
