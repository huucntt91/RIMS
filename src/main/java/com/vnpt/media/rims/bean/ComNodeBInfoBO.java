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
public class ComNodeBInfoBO {

    private Long id;
    private Long iNodebId;
    private String iNodebCode;
    private String iNodebName;
    private String iBscRncName;
    private String iBscRncMngName;
    private String iMscMss;
    private String iSgsn;
    private String iDcHsdpa42M;
    private String iFilterUser;
    private String iFrequencyBand;
    private String iVendor;
    private Long rNodebId;
    private String rNodebCode;
    private String rNodeBName;
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

    public ComNodeBInfoBO() {
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

    public Long getiNodebId() {
        return iNodebId;
    }

    public void setiNodebId(Long iNodebId) {
        this.iNodebId = iNodebId;
    }

    public String getiNodebCode() {
        return iNodebCode;
    }

    public void setiNodebCode(String iNodebCode) {
        this.iNodebCode = iNodebCode;
    }

    public String getiNodebName() {
        return iNodebName;
    }

    public void setiNodebName(String iNodebName) {
        this.iNodebName = iNodebName;
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

    public Long getrNodebId() {
        return rNodebId;
    }

    public void setrNodebId(Long rNodebId) {
        this.rNodebId = rNodebId;
    }

    public String getrNodebCode() {
        return rNodebCode;
    }

    public void setrNodebCode(String rNodebCode) {
        this.rNodebCode = rNodebCode;
    }

    public String getrNodeBName() {
        return rNodeBName;
    }

    public void setrNodeBName(String rNodeBName) {
        this.rNodeBName = rNodeBName;
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
