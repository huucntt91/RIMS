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
public class ComCell3gInfoBO {

    private long id;
    private long iCellId;
//    private String iCellCode;
    private String iCellName;
    private long iLac;
    private long iCi;

//    private String iDcHsdpa42M;
    private String iTenBangTan;
    private String iFrequencyBand;
    private String iDlPsc;
    private String iCpichPower;
    private String iTotalPower;
    private String iMaxPower;
    private String iVendor;
    private String iBtsNodeBName;
    private long rCellId;
//    private String iCellCode;
    private String rCellName;
    private long rLac;
    private long rCi;

//    private String iDcHsdpa42M;
    private String rFrequencyBand;
    private String rDlPsc;
    private String rCpichPower;
    private String rTotalPower;
    private String rMaxPower;
    private String rVendor;
    private String rNodeB;
    private Date compareTime;

    private long type;
    private int status;

    public ComCell3gInfoBO() {
    }

    public String getiVendor() {
        return iVendor;
    }

    public void setiVendor(String iVendor) {
        this.iVendor = iVendor;
    }

    public String getiBtsNodeBName() {
        return iBtsNodeBName;
    }

    public void setiBtsNodeBName(String iBtsNodeBName) {
        this.iBtsNodeBName = iBtsNodeBName;
    }

   
    public String getrVendor() {
        return rVendor;
    }

    public void setrVendor(String rVendor) {
        this.rVendor = rVendor;
    }

    public String getrNodeB() {
        return rNodeB;
    }

    public void setrNodeB(String rNodeB) {
        this.rNodeB = rNodeB;
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

    public String getiDlPsc() {
        return iDlPsc;
    }

    public void setiDlPsc(String iDlPsc) {
        this.iDlPsc = iDlPsc;
    }

    public String getiCpichPower() {
        return iCpichPower;
    }

    public void setiCpichPower(String iCpichPower) {
        this.iCpichPower = iCpichPower;
    }

    public String getiTotalPower() {
        return iTotalPower;
    }

    public void setiTotalPower(String iTotalPower) {
        this.iTotalPower = iTotalPower;
    }

    public String getiMaxPower() {
        return iMaxPower;
    }

    public void setiMaxPower(String iMaxPower) {
        this.iMaxPower = iMaxPower;
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

    public String getrCpichPower() {
        return rCpichPower;
    }

    public void setrCpichPower(String rCpichPower) {
        this.rCpichPower = rCpichPower;
    }

    public String getrMaxPower() {
        return rMaxPower;
    }

    public String getiTenBangTan() {
        return iTenBangTan;
    }

    public void setiTenBangTan(String iTenBangTan) {
        this.iTenBangTan = iTenBangTan;
    }

    public void setrMaxPower(String rMaxPower) {
        this.rMaxPower = rMaxPower;
    }

    public String getrDlPsc() {
        return rDlPsc;
    }

    public void setrDlPsc(String rDlPsc) {
        this.rDlPsc = rDlPsc;
    }

    public String getrTotalPower() {
        return rTotalPower;
    }

    public void setrTotalPower(String rTotalPower) {
        this.rTotalPower = rTotalPower;
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
