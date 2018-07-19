/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Cyano
 */
public class ActionLogBO implements  Serializable{
    private Integer actionlog_id;
    private Integer user_id;
    private String user_name;
    private String actionlog_name;
    private String ip;
    private Date actionlog_time; 
    private String actionlog_time_value;

    public ActionLogBO() {
    }

    public Integer getActionlog_id() {
        return actionlog_id;
    }

    public void setActionlog_id(Integer actionlog_id) {
        this.actionlog_id = actionlog_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getActionlog_name() {
        return actionlog_name;
    }

    public void setActionlog_name(String actionlog_name) {
        this.actionlog_name = actionlog_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getActionlog_time() {
        return actionlog_time;
    }

    public void setActionlog_time(Date actionlog_time) {
        this.actionlog_time = actionlog_time;
    }

    public String getActionlog_time_value() {
        return actionlog_time_value;
    }

    public void setActionlog_time_value(String actionlog_time_value) {
        this.actionlog_time_value = actionlog_time_value;
    }
    
    
    
    
}
