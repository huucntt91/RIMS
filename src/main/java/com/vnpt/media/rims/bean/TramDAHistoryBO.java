/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;

/**
 *
 * @author tunv1
 */
public class TramDAHistoryBO {

    private String userName;
    private Date createTime;
    private String action;
    private String ipClient;
    private String note;
    public TramDuAnBO getTram() {
        return tram;
    }

    public void setTram(TramDuAnBO tram) {
        this.tram = tram;
    }

    TramDuAnBO tram;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

   
}
