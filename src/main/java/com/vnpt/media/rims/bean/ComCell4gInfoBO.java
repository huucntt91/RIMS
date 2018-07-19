/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Cyano
 */
public class ComCell4gInfoBO {

    private long id;
    private long iCellId;
//    private String iCellCode;
    private String iCellName;
    private long iLac;
    private long iCi;

//    private String iDcHsdpa42M;
    private String iFrequencyBand;
    private String iPci;
    private String iTac;
    private String iLcrId;
    private String iBtsNodeBName;
    private String iVendor;
    private long rCellId;
//    private String iCellCode;
    private String rCellName;
    private long rLac;
    private long rCi;

//    private String iDcHsdpa42M;
    private String rFrequencyBand;
    private String rPci;
    private String rTac;
    private String rLcrId;
    private String rVendor;
    private Date compareTime;

    private long type;
    private int status;

    public ComCell4gInfoBO() {
    }

    public String getiVendor() {
        return iVendor;
    }

    public void setiVendor(String iVendor) {
        this.iVendor = iVendor;
    }

    public String getrVendor() {
        return rVendor;
    }

    public void setrVendor(String rVendor) {
        this.rVendor = rVendor;
    }

    public String getiBtsNodeBName() {
        return iBtsNodeBName;
    }

    public void setiBtsNodeBName(String iBtsNodeBName) {
        this.iBtsNodeBName = iBtsNodeBName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getiCellId() {
        return iCellId;
    }

    public void setiCellId(long iCellId) {
        this.iCellId = iCellId;
    }

    public String getiCellName() {
        return iCellName;
    }

    public void setiCellName(String iCellName) {
        this.iCellName = iCellName;
    }

    public long getiLac() {
        return iLac;
    }

    public void setiLac(long iLac) {
        this.iLac = iLac;
    }

    public long getiCi() {
        return iCi;
    }

    public void setiCi(long iCi) {
        this.iCi = iCi;
    }

    public String getiFrequencyBand() {
        return iFrequencyBand;
    }

    public void setiFrequencyBand(String iFrequencyBand) {
        this.iFrequencyBand = iFrequencyBand;
    }

    public String getiPci() {
        return iPci;
    }

    public void setiPci(String iPci) {
        this.iPci = iPci;
    }

    public String getiTac() {
        return iTac;
    }

    public void setiTac(String iTac) {
        this.iTac = iTac;
    }

    public String getiLcrId() {
        return iLcrId;
    }

    public void setiLcrId(String iLcrId) {
        this.iLcrId = iLcrId;
    }

    public long getrCellId() {
        return rCellId;
    }

    public void setrCellId(long rCellId) {
        this.rCellId = rCellId;
    }

    public String getrCellName() {
        return rCellName;
    }

    public void setrCellName(String rCellName) {
        this.rCellName = rCellName;
    }

    public long getrLac() {
        return rLac;
    }

    public void setrLac(long rLac) {
        this.rLac = rLac;
    }

    public long getrCi() {
        return rCi;
    }

    public void setrCi(long rCi) {
        this.rCi = rCi;
    }

    public String getrFrequencyBand() {
        return rFrequencyBand;
    }

    public void setrFrequencyBand(String rFrequencyBand) {
        this.rFrequencyBand = rFrequencyBand;
    }

    public String getrPci() {
        return rPci;
    }

    public void setrPci(String rPci) {
        this.rPci = rPci;
    }

    public String getrTac() {
        return rTac;
    }

    public void setrTac(String rTac) {
        this.rTac = rTac;
    }

    public String getrLcrId() {
        return rLcrId;
    }

    public void setrLcrId(String rLcrId) {
        this.rLcrId = rLcrId;
    }

    public Date getCompareTime() {
        return compareTime;
    }

    public void setCompareTime(Date compareTime) {
        this.compareTime = compareTime;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
