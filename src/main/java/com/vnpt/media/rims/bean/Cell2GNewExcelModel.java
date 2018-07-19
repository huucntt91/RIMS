/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package com.vnpt.media.rims.bean;

import com.vnpt.media.rims.common.utils.*;
import com.blogspot.na5cent.exom.annotation.Column;
import java.util.Date;

/**
 * @author redcrow
 */
public class Cell2GNewExcelModel {

    @Column(name = "check", index = "")
    private boolean check;
    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ma bts", index = "0")
    private String maNodeCha;
    
    @Column(name = "Ngay hoat dong", index = "1")
    private String ngayHoatDong;
    
    @Column(name = "hoan canh ra doi", index = "2")
    private String hoanCanhRaDoi;
    
    
    @Column(name = "Ten cell", index = "3")
    private String ten_cell;

    @Column(name = "ten tren he thong", index = "4")
    private String tenTrenHeThong;
    @Column(name = "Lac", index = "5")
    private String lac;
    @Column(name = "ci", index = "6")
    private String ci;
    @Column(name = "Ten thiet bi", index = "7")
    private String tenThietBi;
    @Column(name = "Ten tram", index = "8")
    private String tenTram;
    @Column(name = "Ten tram", index = "9")
    private String noOfCarrier;

    public Cell2GNewExcelModel() {
    }

    public String getCheckDB() {
        return checkDB;
    }

    public void setCheckDB(String checkDB) {
        this.checkDB = checkDB;
    }

    public String getMaNodeCha() {
        return maNodeCha;
    }

    public void setMaNodeCha(String maNodeCha) {
        this.maNodeCha = maNodeCha;
    }

    public String getHoanCanhRaDoi() {
        return hoanCanhRaDoi;
    }

    public void setHoanCanhRaDoi(String hoanCanhRaDoi) {
        this.hoanCanhRaDoi = hoanCanhRaDoi;
    }

    public String getNgayHoatDong() {
        return ngayHoatDong;
    }

    public void setNgayHoatDong(String ngayHoatDong) {
        this.ngayHoatDong = ngayHoatDong;
    }

    public String getTenTrenHeThong() {
        return tenTrenHeThong;
    }

    public void setTenTrenHeThong(String tenTrenHeThong) {
        this.tenTrenHeThong = tenTrenHeThong;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public String getTenTram() {
        return tenTram;
    }

    public void setTenTram(String tenTram) {
        this.tenTram = tenTram;
    }

    public String getTen_cell() {
        return ten_cell;
    }

    public void setTen_cell(String ten_cell) {
        this.ten_cell = ten_cell;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getNoOfCarrier() {
        return noOfCarrier;
    }

    public void setNoOfCarrier(String noOfCarrier) {
        this.noOfCarrier = noOfCarrier;
    }
}
