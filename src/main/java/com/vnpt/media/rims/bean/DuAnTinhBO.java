/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import java.util.Date;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author VNP
 */
public class DuAnTinhBO {

    private String duAnId;
    private String maDuAn;
    private String tenDuAn;
    private String tenTinh;
    private String tinhId;
    private String maQuyHoach;
    private String tenQuyHoach;
    private String quyHoachTinhId;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDuAnId() {
        return duAnId;
    }

    public void setDuAnId(String duAnId) {
        this.duAnId = duAnId;
    }

    public String getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(String maDuAn) {
        this.maDuAn = maDuAn;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public String getTinhId() {
        return tinhId;
    }

    public void setTinhId(String tinhId) {
        this.tinhId = tinhId;
    }

    public String getMaQuyHoach() {
        return maQuyHoach;
    }

    public void setMaQuyHoach(String maQuyHoach) {
        this.maQuyHoach = maQuyHoach;
    }

    public String getTenQuyHoach() {
        return tenQuyHoach;
    }

    public void setTenQuyHoach(String tenQuyHoach) {
        this.tenQuyHoach = tenQuyHoach;
    }

    public String getQuyHoachTinhId() {
        return quyHoachTinhId;
    }

    public void setQuyHoachTinhId(String quyHoachTinhId) {
        this.quyHoachTinhId = quyHoachTinhId;
    }



}
