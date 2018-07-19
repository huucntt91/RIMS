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
public class TEq3BO {

    private Long id;
    private Long eq2Id;
        private String eq2Name;

    private String number;
    private String name;
    private String vendor;
    private String note;
    private String seriaNumber;
    private String partNumber;
    private String maVach;
    private List<TPortBO> tPortBOList;

    public Long getId() {
        return id;
    }

    public String getEq2Name() {
        return eq2Name;
    }

    public void setEq2Name(String eq2Name) {
        this.eq2Name = eq2Name;
    }

    public List<TPortBO> gettPortBOList() {
        return tPortBOList;
    }

    public void settPortBOList(List<TPortBO> tPortBOList) {
        this.tPortBOList = tPortBOList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEq2Id() {
        return eq2Id;
    }

    public void setEq2Id(Long eq2Id) {
        this.eq2Id = eq2Id;
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

    public String getSeriaNumber() {
        return seriaNumber;
    }

    public void setSeriaNumber(String seriaNumber) {
        this.seriaNumber = seriaNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getMaVach() {
        return maVach;
    }

    public void setMaVach(String maVach) {
        this.maVach = maVach;
    }
    
    

}
