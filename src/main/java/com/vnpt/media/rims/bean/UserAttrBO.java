package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class UserAttrBO implements Serializable {

    private long userAttrId;
    private long userId;
    private  AttributeBO attr;
    private  AttClassListBO attClass;
    private  ObjectListBO object;
    private  String action;

    public long getUserAttrId() {
        return userAttrId;
    }

    public void setUserAttrId(long userAttrId) {
        this.userAttrId = userAttrId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public AttributeBO getAttr() {
        return attr;
    }

    public void setAttr(AttributeBO attr) {
        this.attr = attr;
    }

    public AttClassListBO getAttClass() {
        return attClass;
    }

    public void setAttClass(AttClassListBO attClass) {
        this.attClass = attClass;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ObjectListBO getObject() {
        return object;
    }

    public void setObject(ObjectListBO object) {
        this.object = object;
    }
}
