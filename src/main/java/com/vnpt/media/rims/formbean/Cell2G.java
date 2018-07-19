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
public class Cell2G implements Serializable{
    private String Vendor;
    private String type1;
    private String type2;
    private String mBsc;
    private String btsName;
    private String cellName;
    private String freqBand;
    private Long lac;
    private Long ci;
    private String bsic;
    private String bcch;
    private String frequency;
    private String config;

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String Vendor) {
        this.Vendor = Vendor;
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

    public String getBtsName() {
        return btsName;
    }

    public void setBtsName(String btsName) {
        this.btsName = btsName;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getFreqBand() {
        return freqBand;
    }

    public void setFreqBand(String freqBand) {
        this.freqBand = freqBand;
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

    public String getBsic() {
        return bsic;
    }

    public void setBsic(String bsic) {
        this.bsic = bsic;
    }

    public String getBcch() {
        return bcch;
    }

    public void setBcch(String bcch) {
        this.bcch = bcch;
    }


    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    
    
}
