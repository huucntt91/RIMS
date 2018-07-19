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
public class ComBtsInfoBO {

    private Long id;
    private Long iBtsId;
    private String iBtsName;
//    private String iBtsCode;
    private String iBscRncName;
    private String iBscRncMngName;
//    private String iMscMss;
//    private String iSgsn;
//    private String iDcHsdpa42M;
    private String iFilterUser;
    private String iFrequencyBand;
    private String iVendor;
    private Long rBtsId;
    private String rBtsName;
//    private String rBtsCode;
    private String rBscRncName;
    private String rBscRncMngName;
//    private String rMscMss;
//    private String rSgsn;
//    private String rDcHsdpa42M;
    private String rVendor;
    private String rFilterUser;
    private String rFrequencyBand;
    private Date compareTime;
    private Long type;
    private int status;

    public ComBtsInfoBO() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getiBtsId() {
        return iBtsId;
    }

    public void setiBtsId(Long iBtsId) {
        this.iBtsId = iBtsId;
    }

    public String getiBtsName() {
        return iBtsName;
    }

    public void setiBtsName(String iBtsName) {
        this.iBtsName = iBtsName;
    }

    public String getiBscRncName() {
        return iBscRncName;
    }

    public void setiBscRncName(String iBscRncName) {
        this.iBscRncName = iBscRncName;
    }

    public String getiBscRncMngName() {
        return iBscRncMngName;
    }

    public void setiBscRncMngName(String iBscRncMngName) {
        this.iBscRncMngName = iBscRncMngName;
    }

    public String getiFilterUser() {
        return iFilterUser;
    }

    public void setiFilterUser(String iFilterUser) {
        this.iFilterUser = iFilterUser;
    }

    public String getiFrequencyBand() {
        return iFrequencyBand;
    }

    public void setiFrequencyBand(String iFrequencyBand) {
        this.iFrequencyBand = iFrequencyBand;
    }

    public Long getrBtsId() {
        return rBtsId;
    }

    public void setrBtsId(Long rBtsId) {
        this.rBtsId = rBtsId;
    }

    public String getrBtsName() {
        return rBtsName;
    }

    public void setrBtsName(String rBtsName) {
        this.rBtsName = rBtsName;
    }

    public String getrBscRncName() {
        return rBscRncName;
    }

    public void setrBscRncName(String rBscRncName) {
        this.rBscRncName = rBscRncName;
    }

    public String getrBscRncMngName() {
        return rBscRncMngName;
    }

    public void setrBscRncMngName(String rBscRncMngName) {
        this.rBscRncMngName = rBscRncMngName;
    }

    public String getrFilterUser() {
        return rFilterUser;
    }

    public void setrFilterUser(String rFilterUser) {
        this.rFilterUser = rFilterUser;
    }

    public String getrFrequencyBand() {
        return rFrequencyBand;
    }

    public void setrFrequencyBand(String rFrequencyBand) {
        this.rFrequencyBand = rFrequencyBand;
    }

    public Date getCompareTime() {
        return compareTime;
    }

    public void setCompareTime(Date compareTime) {
        this.compareTime = compareTime;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
