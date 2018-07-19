/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Cyano
 */
public class PmFmBO {
    String vnpCode;
    String nodeType;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date createTime;
    List<PmBO> list;
    private String event_define_name;
    private String event_description;

    public PmFmBO() {
    }

    public List<PmBO> getList() {
        return list;
    }

    public void setList(List<PmBO> list) {
        this.list = list;
    }

    public String getEvent_define_name() {
        return event_define_name;
    }

    public void setEvent_define_name(String event_define_name) {
        this.event_define_name = event_define_name;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

   
    
    
}
