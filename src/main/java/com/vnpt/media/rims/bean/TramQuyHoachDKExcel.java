/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.blogspot.na5cent.exom.annotation.Column;

/**
 *
 * @author VNP
 */
public class TramQuyHoachDKExcel {

    @Column(name = "note", index = "0")
    private String note;

    @Column(name = "maQHTinh", index = "1")
    private String maQHTinh;
    @Column(name = "maTramQH", index = "2")
    private String maTramQH;

    @Column(name = "tenTramQH", index = "3")
    private String tenTramQH;
    @Column(name = "maBuilding", index = "4")
    private String maBuilding;
    @Column(name = "namKhoiTao", index = "5")
    private String namKhoiTao;
    @Column(name = "loaiCongNghe", index = "6")
    private String loaiCongNghe;
    @Column(name = "bangtan", index = "7")
    private String bangtan;
    @Column(name = "ctPTCSHT", index = "8")
    private String ctPTCSHT;
    @Column(name = "ttCSHT", index = "9")
    private String ttCSHT;
    @Column(name = "dvpheduyetTTC", index = "10")
    private String dvpheduyetTTC;
    @Column(name = "sohieuVB", index = "11")
    private String sohieuVB;
    @Column(name = "ngaypheduyetTTC", index = "12")
    private String ngaypheduyetTTC;
    @Column(name = "ngaydieuchinhTTC", index = "13")
    private String ngaydieuchinhTTC;
    @Column(name = "donviDieuChinh", index = "14")
    private String donviDieuChinh;

    // -- cam ket thiet bi
    @Column(name = "ngayPhatSongTTC", index = "15")
    private String ngayPhatSongTTC;

    public TramQuyHoachDKExcel() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMaQHTinh() {
        return maQHTinh;
    }

    public void setMaQHTinh(String maQHTinh) {
        this.maQHTinh = maQHTinh;
    }

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

    public String getMaBuilding() {
        return maBuilding;
    }

    public void setMaBuilding(String maBuilding) {
        this.maBuilding = maBuilding;
    }

    public String getNamKhoiTao() {
        return namKhoiTao;
    }

    public void setNamKhoiTao(String namKhoiTao) {
        this.namKhoiTao = namKhoiTao;
    }

    public String getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(String loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

    public String getBangtan() {
        return bangtan;
    }

    public void setBangtan(String bangtan) {
        this.bangtan = bangtan;
    }

    public String getCtPTCSHT() {
        return ctPTCSHT;
    }

    public void setCtPTCSHT(String ctPTCSHT) {
        this.ctPTCSHT = ctPTCSHT;
    }

    public String getTtCSHT() {
        return ttCSHT;
    }

    public void setTtCSHT(String ttCSHT) {
        this.ttCSHT = ttCSHT;
    }

    public String getDvpheduyetTTC() {
        return dvpheduyetTTC;
    }

    public void setDvpheduyetTTC(String dvpheduyetTTC) {
        this.dvpheduyetTTC = dvpheduyetTTC;
    }

    public String getSohieuVB() {
        return sohieuVB;
    }

    public void setSohieuVB(String sohieuVB) {
        this.sohieuVB = sohieuVB;
    }

    public String getNgaypheduyetTTC() {
        return ngaypheduyetTTC;
    }

    public void setNgaypheduyetTTC(String ngaypheduyetTTC) {
        this.ngaypheduyetTTC = ngaypheduyetTTC;
    }

    public String getNgaydieuchinhTTC() {
        return ngaydieuchinhTTC;
    }

    public void setNgaydieuchinhTTC(String ngaydieuchinhTTC) {
        this.ngaydieuchinhTTC = ngaydieuchinhTTC;
    }

    public String getDonviDieuChinh() {
        return donviDieuChinh;
    }

    public void setDonviDieuChinh(String donviDieuChinh) {
        this.donviDieuChinh = donviDieuChinh;
    }

    public String getNgayPhatSongTTC() {
        return ngayPhatSongTTC;
    }

    public void setNgayPhatSongTTC(String ngayPhatSongTTC) {
        this.ngayPhatSongTTC = ngayPhatSongTTC;
    }


}
