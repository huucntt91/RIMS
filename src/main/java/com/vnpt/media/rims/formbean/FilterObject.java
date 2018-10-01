/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;


/**
 *
 * @author Cyano
 */
public class FilterObject {

   
    private String objectFill;
    private String column;
    private String filterType;
    private String value;
    
    
    public FilterObject() {
    }

    public String getObjectFill() {
        return objectFill;
    }

    public void setObjectFill(String objectFill) {
        this.objectFill = objectFill;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
}
