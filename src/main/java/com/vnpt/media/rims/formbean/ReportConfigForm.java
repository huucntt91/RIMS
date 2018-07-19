/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.util.List;

/**
 *
 * @author VNP
 */
public class ReportConfigForm {

    private String[] column;

    private String[] dataType;
    
    private String[] filterType;

//    private String[] value_;
private List<String> value_;
    public ReportConfigForm() {
    }

    public String[] getColumn() {
        return column;
    }

    public void setColumn(String[] column) {
        this.column = column;
    }

    public String[] getFilterType() {
        return filterType;
    }

    public void setFilterType(String[] filterType) {
        this.filterType = filterType;
    }

    public List<String> getValue_() {
        return value_;
    }

    public void setValue_(List<String> value_) {
        this.value_ = value_;
    }

//    public String[] getValue_() {
//        return value_;
//    }
//
//    public void setValue_(String[] value_) {
//        this.value_ = value_;
//    }

    public String[] getDataType() {
        return dataType;
    }

    public void setDataType(String[] dataType) {
        this.dataType = dataType;
    }

    


}
