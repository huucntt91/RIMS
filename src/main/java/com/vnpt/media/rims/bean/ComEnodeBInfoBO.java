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
public class ComEnodeBInfoBO {

    private Long id;
    private Long iEnodebId;
    private String iEnodebCode;
    private String iEnodebName;
    private String iBscRncName;
    private String iBscRncMngName;
    private String iMscMss;
    private String iSgsn;
    private String iDcHsdpa42M;
    private String iFilterUser;
    private String iFrequencyBand;
    private String iVendor;
    private Long reNodebId;
    private String rEnodebCode;
    private String rEnodebName;
    private String rBscRncName;
    private String rBscRncMngName;
    private String rMscMss;
    private String rSgsn;
    private String rDcHsdpa42M;
    private String rFilterUser;
    private String rFrequencyBand;
    private String rVendor;
    private Date compareTime;
    private Long type;

    public ComEnodeBInfoBO() {
    }

    public String getrVendor() {
        return rVendor;
    }

    public void setrVendor(String rVendor) {
        this.rVendor = rVendor;
    }

    public String getiVendor() {
        return iVendor;
    }

    public void setiVendor(String iVendor) {
        this.iVendor = iVendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getiEnodebId() {
        return iEnodebId;
    }

    public void setiEnodebId(Long iEnodebId) {
        this.iEnodebId = iEnodebId;
    }

    public String getiEnodebCode() {
        return iEnodebCode;
    }

    public void setiEnodebCode(String iEnodebCode) {
        this.iEnodebCode = iEnodebCode;
    }

    public String getiEnodebName() {
        return iEnodebName;
    }

    public void setiEnodebName(String iEnodebName) {
        this.iEnodebName = iEnodebName;
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

    public String getiMscMss() {
        return iMscMss;
    }

    public void setiMscMss(String iMscMss) {
        this.iMscMss = iMscMss;
    }

    public String getiSgsn() {
        return iSgsn;
    }

    public void setiSgsn(String iSgsn) {
        this.iSgsn = iSgsn;
    }

    public String getiDcHsdpa42M() {
        return iDcHsdpa42M;
    }

    public void setiDcHsdpa42M(String iDcHsdpa42M) {
        this.iDcHsdpa42M = iDcHsdpa42M;
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

    public Long getReNodebId() {
        return reNodebId;
    }

    public void setReNodebId(Long reNodebId) {
        this.reNodebId = reNodebId;
    }

    public String getrEnodebCode() {
        return rEnodebCode;
    }

    public void setrEnodebCode(String rEnodebCode) {
        this.rEnodebCode = rEnodebCode;
    }

    public String getrEnodebName() {
        return rEnodebName;
    }

    public void setrEnodebName(String rEnodebName) {
        this.rEnodebName = rEnodebName;
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

    public String getrMscMss() {
        return rMscMss;
    }

    public void setrMscMss(String rMscMss) {
        this.rMscMss = rMscMss;
    }

    public String getrSgsn() {
        return rSgsn;
    }

    public void setrSgsn(String rSgsn) {
        this.rSgsn = rSgsn;
    }

    public String getrDcHsdpa42M() {
        return rDcHsdpa42M;
    }

    public void setrDcHsdpa42M(String rDcHsdpa42M) {
        this.rDcHsdpa42M = rDcHsdpa42M;
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

}
