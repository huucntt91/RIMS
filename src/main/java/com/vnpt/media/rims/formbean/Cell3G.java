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
    String cellCode;
    String checkDate;
    String fileName;
    String freqBand;
    String cpichPower;
    String totalPower;
    String maxPower;
    String rac;
    String dlUarfcn;
    String dcSupport;
    String oamIp;
    String serviceIp;

    public String getRac() {
        return rac;
    }

    public void setRac(String rac) {
        this.rac = rac;
    }

    public String getDlUarfcn() {
        return dlUarfcn;
    }

    public void setDlUarfcn(String dlUarfcn) {
        this.dlUarfcn = dlUarfcn;
    }

    public String getDcSupport() {
        return dcSupport;
    }

    public void setDcSupport(String dcSupport) {
        this.dcSupport = dcSupport;
    }

    public String getOamIp() {
        return oamIp;
    }

    public void setOamIp(String oamIp) {
        this.oamIp = oamIp;
    }

    public String getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }
    

    public String getCpichPower() {
        return cpichPower;
    }

    public void setCpichPower(String cpichPower) {
        this.cpichPower = cpichPower;
    }

    public String getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(String totalPower) {
        this.totalPower = totalPower;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }
    

    public String getCellCode() {
        return cellCode;
    }

    public void setCellCode(String cellCode) {
        this.cellCode = cellCode;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFreqBand() {
        return freqBand;
    }

    public void setFreqBand(String freqBand) {
        this.freqBand = freqBand;
    }
    
    

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
