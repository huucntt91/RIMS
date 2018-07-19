/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.io.Serializable;

/**
 *
 * @author Cyano
 */
public class Cell3G implements Serializable{
    private String vendor;
    private String type1;
    private String type2;
    private String mBsc;
    private String nodeBname;
    private String cellType;
    private String cellName;
    private Long lac;
    private Long ci;
    private String dlpsc;
    private String freq;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String Vendor) {
        this.vendor = Vendor;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getmBsc() {
        return mBsc;
    }

    public void setmBsc(String mBsc) {
        this.mBsc = mBsc;
    }

    public String getNodeBname() {
        return nodeBname;
    }

    public void setNodeBname(String nodeBname) {
        this.nodeBname = nodeBname;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public Long getLac() {
        return lac;
    }

    public void setLac(Long lac) {
        this.lac = lac;
    }

    public Long getCi() {
        return ci;
    }

    public void setCi(Long ci) {
        this.ci = ci;
    }

    public String getDlpsc() {
        return dlpsc;
    }

    public void setDlpsc(String dlpsc) {
        this.dlpsc = dlpsc;
    }

    

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }
   

    
}
