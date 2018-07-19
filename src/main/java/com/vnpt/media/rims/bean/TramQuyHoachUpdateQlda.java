/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import com.blogspot.na5cent.exom.annotation.Column;

/**
 *
 * @author VNP
 */
public class TramQuyHoachUpdateQlda {

    @Column(name = "maTramQH", index = "0")
    private String maTramQH;

    @Column(name = "tenTramQH", index = "1")
    private String tenTramQH;

    //thong tin nguon 
    @Column(name = "dvChiuTrachNhiemNguon", index = "2")
    private String dvChiuTrachNhiemNguon;

    @Column(name = "tuNguon", index = "3")
    private String tuNguon;

    @Column(name = "loaiTuNguon", index = "4")
    private String loaiTuNguon;

    @Column(name = "dungLuongTuNguon", index = "5")
    private String dungLuongTuNguon;

    @Column(name = "soLuongRacktifier", index = "6")
    private String soLuongRacktifier;

    @Column(name = "dungLuongAcquy", index = "7")
    private String dungLuongAcquy;
    
    @Column(name = "soLuongToAcquy", index = "8")
    private String soLuongToAcquy;

    @Column(name = "dienApAcquy", index = "9")
    private String dienApAcquy;

    @Column(name = "ngayDapUngNguonDuKien", index = "10")
    private String ngayDapUngNguonDuKien;

    @Column(name = "ngayDapUngNguonThucTe", index = "11")
    private String ngayDapUngNguonThucTe;

    @Column(name = "tinhTrangNguonDien", index = "12")
    private String tinhTrangNguonDien;

    @Column(name = "result", index = "13")
    private String result;

    public String getMaTramQH() {
        return maTramQH;
    }

    public void setMaTramQH(String maTramQH) {
        this.maTramQH = maTramQH;
    }

    public String getTenTramQH() {
        return tenTramQH;
    }

    public void setTenTramQH(String tenTramQH) {
        this.tenTramQH = tenTramQH;
    }

    public String getDvChiuTrachNhiemNguon() {
        return dvChiuTrachNhiemNguon;
    }

    public void setDvChiuTrachNhiemNguon(String dvChiuTrachNhiemNguon) {
        this.dvChiuTrachNhiemNguon = dvChiuTrachNhiemNguon;
    }

    public String getTuNguon() {
        return tuNguon;
    }

    public void setTuNguon(String tuNguon) {
        this.tuNguon = tuNguon;
    }

    public String getLoaiTuNguon() {
        return loaiTuNguon;
    }

    public void setLoaiTuNguon(String loaiTuNguon) {
        this.loaiTuNguon = loaiTuNguon;
    }

    public String getDungLuongTuNguon() {
        return dungLuongTuNguon;
    }

    public void setDungLuongTuNguon(String dungLuongTuNguon) {
        this.dungLuongTuNguon = dungLuongTuNguon;
    }

    public String getSoLuongRacktifier() {
        return soLuongRacktifier;
    }

    public void setSoLuongRacktifier(String soLuongRacktifier) {
        this.soLuongRacktifier = soLuongRacktifier;
    }

    public String getDungLuongAcquy() {
        return dungLuongAcquy;
    }

    public void setDungLuongAcquy(String dungLuongAcquy) {
        this.dungLuongAcquy = dungLuongAcquy;
    }

    public String getSoLuongToAcquy() {
        return soLuongToAcquy;
    }

    public void setSoLuongToAcquy(String soLuongToAcquy) {
        this.soLuongToAcquy = soLuongToAcquy;
    }

    public String getDienApAcquy() {
        return dienApAcquy;
    }

    public void setDienApAcquy(String dienApAcquy) {
        this.dienApAcquy = dienApAcquy;
    }

    public String getNgayDapUngNguonDuKien() {
        return ngayDapUngNguonDuKien;
    }

    public void setNgayDapUngNguonDuKien(String ngayDapUngNguonDuKien) {
        this.ngayDapUngNguonDuKien = ngayDapUngNguonDuKien;
    }

    public String getNgayDapUngNguonThucTe() {
        return ngayDapUngNguonThucTe;
    }

    public void setNgayDapUngNguonThucTe(String ngayDapUngNguonThucTe) {
        this.ngayDapUngNguonThucTe = ngayDapUngNguonThucTe;
    }

    public String getTinhTrangNguonDien() {
        return tinhTrangNguonDien;
    }

    public void setTinhTrangNguonDien(String tinhTrangNguonDien) {
        this.tinhTrangNguonDien = tinhTrangNguonDien;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    
}
