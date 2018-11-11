/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.*;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.blogspot.na5cent.exom.annotation.Column;

/**
 *
 * @author VNP
 */
public class UpdateDuAnNguonQLDAHT1Excel {

    @Column(name = "note", index = "")
    private String note;
    
    @Column(name = "maTramQuyHoach", index = "0")
    private String maTramQuyHoach;
    @Column(name = "tenTuNguon", index = "1")
    private String tenTuNguon;
    @Column(name = "ngayDkBanGiaoTuNguon", index = "2")
    private String ngayDkBanGiaoTuNguon;
    @Column(name = "thuocDuAnTuNguon", index = "3")
    private String thuocDuAnTuNguon;
    @Column(name = "soPoTuNguon", index = "4")
    private String soPoTuNguon;
    @Column(name = "ngayThucTeGiaoTuNguon", index = "6")
    private String ngayThucTeGiaoTuNguon;
    @Column(name = "tenACCU", index = "7")
    private String tenACCU;
    @Column(name = "ngayDuKienBanGiaoACCU", index = "8")
    private String ngayDuKienBanGiaoACCU;
    @Column(name = "thuocDuAnACCU", index = "9")
    private String thuocDuAnACCU;
    @Column(name = "soPoACCU", index = "10")
    private String soPoACCU;
    @Column(name = "ngayThucTeGiaoACCU", index = "11")
    private String ngayThucTeGiaoACCU;
    @Column(name = "ghiChuNet", index = "12")
    private String ghiChuNet;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMaTramQuyHoach() {
        return maTramQuyHoach;
    }

    public void setMaTramQuyHoach(String maTramQuyHoach) {
        this.maTramQuyHoach = maTramQuyHoach;
    }

    public String getTenTuNguon() {
        return tenTuNguon;
    }

    public void setTenTuNguon(String tenTuNguon) {
        this.tenTuNguon = tenTuNguon;
    }

    public String getNgayDkBanGiaoTuNguon() {
        return ngayDkBanGiaoTuNguon;
    }

    public void setNgayDkBanGiaoTuNguon(String ngayDkBanGiaoTuNguon) {
        this.ngayDkBanGiaoTuNguon = ngayDkBanGiaoTuNguon;
    }

    public String getThuocDuAnTuNguon() {
        return thuocDuAnTuNguon;
    }

    public void setThuocDuAnTuNguon(String thuocDuAnTuNguon) {
        this.thuocDuAnTuNguon = thuocDuAnTuNguon;
    }

    public String getSoPoTuNguon() {
        return soPoTuNguon;
    }

    public void setSoPoTuNguon(String soPoTuNguon) {
        this.soPoTuNguon = soPoTuNguon;
    }

    public String getNgayThucTeGiaoTuNguon() {
        return ngayThucTeGiaoTuNguon;
    }

    public void setNgayThucTeGiaoTuNguon(String ngayThucTeGiaoTuNguon) {
        this.ngayThucTeGiaoTuNguon = ngayThucTeGiaoTuNguon;
    }

    public String getTenACCU() {
        return tenACCU;
    }

    public void setTenACCU(String tenACCU) {
        this.tenACCU = tenACCU;
    }

    public String getNgayDuKienBanGiaoACCU() {
        return ngayDuKienBanGiaoACCU;
    }

    public void setNgayDuKienBanGiaoACCU(String ngayDuKienBanGiaoACCU) {
        this.ngayDuKienBanGiaoACCU = ngayDuKienBanGiaoACCU;
    }

    public String getThuocDuAnACCU() {
        return thuocDuAnACCU;
    }

    public void setThuocDuAnACCU(String thuocDuAnACCU) {
        this.thuocDuAnACCU = thuocDuAnACCU;
    }

    public String getSoPoACCU() {
        return soPoACCU;
    }

    public void setSoPoACCU(String soPoACCU) {
        this.soPoACCU = soPoACCU;
    }

    public String getNgayThucTeGiaoACCU() {
        return ngayThucTeGiaoACCU;
    }

    public void setNgayThucTeGiaoACCU(String ngayThucTeGiaoACCU) {
        this.ngayThucTeGiaoACCU = ngayThucTeGiaoACCU;
    }

    public String getGhiChuNet() {
        return ghiChuNet;
    }

    public void setGhiChuNet(String ghiChuNet) {
        this.ghiChuNet = ghiChuNet;
    }

    

}
