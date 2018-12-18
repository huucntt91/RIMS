/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author VNP
 */
public class AttributeBO implements Serializable{
    
    
    private Long id;
    private String attrName;
    private String attrCode;
    private String attrTableName;
    private  String aliasExcelAttr;
    private long attrClassId;

    public AttributeBO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    public String getAttrTableName() {
        return attrTableName;
    }

    public void setAttrTableName(String attrTableName) {
        this.attrTableName = attrTableName;
    }

    public String getAliasExcelAttr() {
        return aliasExcelAttr;
    }

    public void setAliasExcelAttr(String aliasExcelAttr) {
        this.aliasExcelAttr = aliasExcelAttr;
    }

    public long getAttrClassId() {
        return attrClassId;
    }

    public void setAttrClassId(long attrClassId) {
        this.attrClassId = attrClassId;
    }
}
