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
public class TConnectionInfoBO{
    private String tConnectionId;
    private String tConnectionGroupId;
    private String tConnectionGroupName;
    private String tConnectionName;
    private String note;

    public String gettConnectionId() {
        return tConnectionId;
    }

    public void settConnectionId(String tConnectionId) {
        this.tConnectionId = tConnectionId;
    }

    public String gettConnectionGroupId() {
        return tConnectionGroupId;
    }

    public void settConnectionGroupId(String tConnectionGroupId) {
        this.tConnectionGroupId = tConnectionGroupId;
    }

    public String gettConnectionGroupName() {
        return tConnectionGroupName;
    }

    public void settConnectionGroupName(String tConnectionGroupName) {
        this.tConnectionGroupName = tConnectionGroupName;
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
