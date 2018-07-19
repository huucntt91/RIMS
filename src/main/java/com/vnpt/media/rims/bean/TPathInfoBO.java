/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author VNP
 */
public class TPathInfoBO{
    private String tConnectionId;
    private String tPathId;
    private String tPathName;
    private String tConnectionName;
    private String note;

    public String gettConnectionId() {
        return tConnectionId;
    }

    public void settConnectionId(String tConnectionId) {
        this.tConnectionId = tConnectionId;
    }

    public String gettPathId() {
        return tPathId;
    }

    public void settPathId(String tPathId) {
        this.tPathId = tPathId;
    }

    public String gettPathName() {
        return tPathName;
    }

    public void settPathName(String tPathName) {
        this.tPathName = tPathName;
    }

    public String gettConnectionName() {
        return tConnectionName;
    }

    public void settConnectionName(String tConnectionName) {
        this.tConnectionName = tConnectionName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
    
    
}
