/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.UserBO;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author VNP
 */
public class FilterForm {

    private String id;

    private String column;
    
    private String description;

    private String dataType;

    private String operator;

    private String value_;

    private List<FilterForm> listFilterForm;

    public FilterForm() {
    }
    
    public String listParam() {
        try {
            String list = "";
            for (Field field : UserBO.class.getDeclaredFields()) {
                String value;
                try {
                    value = field.get(this).toString();
                } catch (Exception e) {
                    value = "";
                }
                String column = field.getName();
                list += "," + column + "=" + value;
            }
            return list.substring(1);
        } catch (Exception ex) {
            return "";
        }
    }

    public FilterForm(String id, String column, String dataType, String operator, String value_,String description) {
        this.id = id;
        this.column = column;
        this.dataType = dataType;
        this.operator = operator;
        this.value_ = value_;
        this.description=description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FilterForm> getListFilterForm() {
        return listFilterForm;
    }

    public void setListFilterForm(List<FilterForm> listFilterForm) {
        this.listFilterForm = listFilterForm;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue_() {
        return value_;
    }

    public void setValue_(String value_) {
        this.value_ = value_;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
