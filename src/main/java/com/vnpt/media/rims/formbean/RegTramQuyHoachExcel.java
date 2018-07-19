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
public class RegTramQuyHoachExcel {

    @Column(name = "note", index = "")
    private String note;

    @Column(name = "tenTramQH", index = "0")
    private String tenTramQH;

    @Column(name = "maTinh", index = "1")
    private String maTinh;

    @Column(name = "namKhoiTao", index = "2")
    private String namKhoiTao;
    @Column(name = "latitude", index = "3")
    private String latitude;
    @Column(name = "longiude", index = "4")
    private String longiude;
    @Column(name = "loaiCongNghe", index = "5")
    private String loaiCongNghe;
    @Column(name = "bangTan2G", index = "6")
    private String bangTan2G;
    @Column(name = "bangTan3G", index = "7")
    private String bangTan3G;
    @Column(name = "bangTan4G", index = "8")
    private String bangTan4G;
    @Column(name = "ctPTCSHT", index = "9")
    private String ctPTCSHT;
    @Column(name = "trangThaiCSHT", index = "10")
    private String trangThaiCSHT;
    @Column(name = "dvpheduyetTTC", index = "11")
    private String dvpheduyetTTC;
    @Column(name = "sohieuVB", index = "12")
    private String sohieuVB;
    @Column(name = "ngaypheduyetTTC", index = "13")
    private String ngaypheduyetTTC;

    public RegTramQuyHoachExcel() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTenTramQH() {
        return tenTramQH;
    }

    public void setTenTramQH(String tenTramQH) {
        this.tenTramQH = tenTramQH;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getNamKhoiTao() {
        return namKhoiTao;
    }

    public void setNamKhoiTao(String namKhoiTao) {
        this.namKhoiTao = namKhoiTao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongiude() {
        return longiude;
    }

    public void setLongiude(String longiude) {
        this.longiude = longiude;
    }

    public String getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(String loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

    public String getBangTan2G() {
        return bangTan2G;
    }

    public void setBangTan2G(String bangTan2G) {
        this.bangTan2G = bangTan2G;
    }

    public String getBangTan3G() {
        return bangTan3G;
    }

    public void setBangTan3G(String bangTan3G) {
        this.bangTan3G = bangTan3G;
    }

    public String getBangTan4G() {
        return bangTan4G;
    }

    public void setBangTan4G(String bangTan4G) {
        this.bangTan4G = bangTan4G;
    }

    public String getCtPTCSHT() {
        return ctPTCSHT;
    }

    public void setCtPTCSHT(String ctPTCSHT) {
        this.ctPTCSHT = ctPTCSHT;
    }

    public String getTrangThaiCSHT() {
        return trangThaiCSHT;
    }

    public void setTrangThaiCSHT(String trangThaiCSHT) {
        this.trangThaiCSHT = trangThaiCSHT;
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

}
