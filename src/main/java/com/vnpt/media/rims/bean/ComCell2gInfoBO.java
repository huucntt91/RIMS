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
public class ComCell2gInfoBO {

    private long id;
    private long iCellId;
//    private String iCellCode;
    private String iCellName;
    private long iLac;
    private long iCi;
    private String iBscRncName;
//    private String iDcHsdpa42M;
    private String iFrequencyBand;
    private long iBcch;
    private String iBsic;
    private String iTch;
    private String iTrxConfig;
    private String iBtsNodeBName;
    private String iVendor;
    private String iFileName;
    private long rCellId;
//    private String rCellCode;
    private String rCellName;
    private long rLac;
    private long rCi;
//    private String rBscRncName;
//    private String rDcHsdpa42M;
    private String rFrequencyBand;
    private long rBcch;
    private String rBsic;
    private String rTch;
    private String rTrxConfig;
    private String rVendor;
    private Date compareTime;

    private long type;
    private int status;

    public ComCell2gInfoBO() {
    }

    public String getrVendor() {
        return rVendor;
    }

    public void setrVendor(String rVendor) {
        this.rVendor = rVendor;
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

    public String getiBscRncName() {
        return iBscRncName;
    }

    public void setiBscRncName(String iBscRncName) {
        this.iBscRncName = iBscRncName;
    }

    public String getiFrequencyBand() {
        return iFrequencyBand;
    }

    public void setiFrequencyBand(String iFrequencyBand) {
        this.iFrequencyBand = iFrequencyBand;
    }

    public long getiBcch() {
        return iBcch;
    }

    public void setiBcch(long iBcch) {
        this.iBcch = iBcch;
    }

    public String getiBsic() {
        return iBsic;
    }

    public void setiBsic(String iBsic) {
        this.iBsic = iBsic;
    }

    public String getiTch() {
        return iTch;
    }

    public void setiTch(String iTch) {
        this.iTch = iTch;
    }

    public String getiTrxConfig() {
        return iTrxConfig;
    }

    public void setiTrxConfig(String iTrxConfig) {
        this.iTrxConfig = iTrxConfig;
    }

    public String getiBtsNodeBName() {
        return iBtsNodeBName;
    }

    public void setiBtsNodeBName(String iBtsNodeBName) {
        this.iBtsNodeBName = iBtsNodeBName;
    }

    public String getiVendor() {
        return iVendor;
    }

    public void setiVendor(String iVendor) {
        this.iVendor = iVendor;
    }

    public String getiFileName() {
        return iFileName;
    }

    public void setiFileName(String iFileName) {
        this.iFileName = iFileName;
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

    public long getrBcch() {
        return rBcch;
    }

    public void setrBcch(long rBcch) {
        this.rBcch = rBcch;
    }

    public String getrBsic() {
        return rBsic;
    }

    public void setrBsic(String rBsic) {
        this.rBsic = rBsic;
    }

    public String getrTch() {
        return rTch;
    }

    public void setrTch(String rTch) {
        this.rTch = rTch;
    }

    public String getrTrxConfig() {
        return rTrxConfig;
    }

    public void setrTrxConfig(String rTrxConfig) {
        this.rTrxConfig = rTrxConfig;
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
