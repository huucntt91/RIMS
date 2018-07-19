/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

/**
 *
 * @author Cyano
 */
public class DongTbiBO {
    private Integer dongTbiId;
    private String tenDongTbi;
    private Integer tvendorId;
    private String tvendorName;
    private String note;

    public DongTbiBO() {
    }

    public String getTvendorName() {
        return tvendorName;
    }

    public void setTvendorName(String tvendorName) {
        this.tvendorName = tvendorName;
    }
    
    

    public Integer getDongTbiId() {
        return dongTbiId;
    }

    public void setDongTbiId(Integer dongTbiId) {
        this.dongTbiId = dongTbiId;
    }

    public String getTenDongTbi() {
        return tenDongTbi;
    }

    public void setTenDongTbi(String tenDongTbi) {
        this.tenDongTbi = tenDongTbi;
    }

    public Integer getTvendorId() {
        return tvendorId;
    }

    public void setTvendorId(Integer tvendorId) {
        this.tvendorId = tvendorId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

   
    
    
}
