/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.io.Serializable;

/**
 *
 * @author VNP
 */
public class OmcCellInfoBO implements Serializable {

    private Long omcCellId;
    private String tenTrenHeThong;
    private Long lac;
    private Long ci;
    private String tenBscRnc;
    private String code;
    private String dcHsdpa42M;
//    private String frequencyBand;
    private Long bangTanId;
    private String bangTanName;

    public OmcCellInfoBO() {
    }

    public Long getOmcCellId() {
        return omcCellId;
    }

    public void setOmcCellId(Long omcCellId) {
        this.omcCellId = omcCellId;
    }

    public String getTenTrenHeThong() {
        return tenTrenHeThong;
    }

    public void setTenTrenHeThong(String tenTrenHeThong) {
        this.tenTrenHeThong = tenTrenHeThong;
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

    public String getTenBscRnc() {
        return tenBscRnc;
    }

    public void setTenBscRnc(String tenBscRnc) {
        this.tenBscRnc = tenBscRnc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDcHsdpa42M() {
        return dcHsdpa42M;
    }

    public void setDcHsdpa42M(String dcHsdpa42M) {
        this.dcHsdpa42M = dcHsdpa42M;
    }

//    public String getFrequencyBand() {
//        return frequencyBand;
//    }
//
//    public void setFrequencyBand(String frequencyBand) {
//        this.frequencyBand = frequencyBand;
//    }
    public Long getBangTanId() {
        return bangTanId;
    }

    public void setBangTanId(Long bangTanId) {
        this.bangTanId = bangTanId;
    }

    public String getBangTanName() {
        return bangTanName;
    }

    public void setBangTanName(String bangTanName) {
        this.bangTanName = bangTanName;
    }

}
