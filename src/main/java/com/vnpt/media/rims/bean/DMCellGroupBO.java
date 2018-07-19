package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class DMCellGroupBO implements Serializable {

    public String id;
    public String name;
    public String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}   
