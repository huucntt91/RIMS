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
public class ClassAttributeBO implements Serializable{
    
    
    private Long id;
    private String attrClassName;
    private List<AttributeBO> attrBO;

    public ClassAttributeBO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttrClassName() {
        return attrClassName;
    }

    public void setAttrClassName(String attrClassName) {
        this.attrClassName = attrClassName;
    }

    public List<AttributeBO> getAttrBO() {
        return attrBO;
    }

    public void setAttrBO(List<AttributeBO> attrBO) {
        this.attrBO = attrBO;
    }
    
}
