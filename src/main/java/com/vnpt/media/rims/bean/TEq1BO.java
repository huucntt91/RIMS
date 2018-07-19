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
public class TEq1BO {

    private Long id;
    private Long tnodeId;
    private String tnodeName;
    private String number;
    private String name;
    private String vendor;
    private String note;
    private List<TEq2BO> tEq2BOList;

    public TEq1BO() {
    }

    public String getTnodeName() {
        return tnodeName;
    }

    public void setTnodeName(String tnodeName) {
        this.tnodeName = tnodeName;
    }

   
    public List<TEq2BO> gettEq2BOList() {
        return tEq2BOList;
    }

    public void settEq2BOList(List<TEq2BO> tEq2BOList) {
        this.tEq2BOList = tEq2BOList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTnodeId() {
        return tnodeId;
    }

    public void setTnodeId(Long tnodeId) {
        this.tnodeId = tnodeId;
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

}
