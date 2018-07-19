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
public class EventBO {
    
    Integer event_define_id;
    String event_define_code;
    String event_define_name;
    String tech_type;
    Integer status;
    Integer object_type_id;

    public EventBO() {
    }
    
    

    public Integer getEvent_define_id() {
        return event_define_id;
    }

    public void setEvent_define_id(Integer event_define_id) {
        this.event_define_id = event_define_id;
    }

    public String getEvent_define_code() {
        return event_define_code;
    }

    public void setEvent_define_code(String event_define_code) {
        this.event_define_code = event_define_code;
    }

    public String getEvent_define_name() {
        return event_define_name;
    }

    public void setEvent_define_name(String event_define_name) {
        this.event_define_name = event_define_name;
    }

    public String getTech_type() {
        return tech_type;
    }

    public void setTech_type(String tech_type) {
        this.tech_type = tech_type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getObject_type_id() {
        return object_type_id;
    }

    public void setObject_type_id(Integer object_type_id) {
        this.object_type_id = object_type_id;
    }
    
    
    
    
}
