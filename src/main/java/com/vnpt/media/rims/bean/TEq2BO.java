/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import java.util.List;

/**
 *
 * @author VNP
 */
public class TEq2BO {
    private Long id;
    private Long eq1Id;
    private String eq1Name;
    private String number;
    private String name;
    private String vendor;
    private String note;
    private List<TEq3BO> tEq3BOList;
    private Long totalColum;
    public Long getId() {
        return id;
    }

    public String getEq1Name() {
        return eq1Name;
    }

    public void setEq1Name(String eq1Name) {
        this.eq1Name = eq1Name;
    }

    public List<TEq3BO> gettEq3BOList() {
        return tEq3BOList;
    }

    public void settEq3BOList(List<TEq3BO> tEq3BOList) {
        this.tEq3BOList = tEq3BOList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEq1Id() {
        return eq1Id;
    }

    public void setEq1Id(Long eq1Id) {
        this.eq1Id = eq1Id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getTotalColum() {
        return totalColum;
    }

    public void setTotalColum(Long totalColum) {
        this.totalColum = totalColum;
    }

   
}
