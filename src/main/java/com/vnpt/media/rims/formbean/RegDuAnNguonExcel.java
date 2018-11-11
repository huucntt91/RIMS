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
public class RegDuAnNguonExcel {

    @Column(name = "note", index = "")
    private String note;
    
    @Column(name = "maTramQuyHoach", index = "0")
    private String maTramQuyHoach;
    @Column(name = "maCSHT", index = "1")
    private String maCSHT;
    @Column(name = "ngayDuKienHtCSHT", index = "2")
    private String ngayDuKienHtCSHT;
    @Column(name = "tuNguon", index = "3")
    private String tuNguon;
    @Column(name = "chungLoaiACCU", index = "4")
    private String chungLoaiACCU;
    @Column(name = "soToACCU", index = "5")
    private String soToACCU;
    @Column(name = "mucDichTrangBiNguon", index = "6")
    private String mucDichTrangBiNguon;
    @Column(name = "soVanBan", index = "7")
    private String soVanBan;
    @Column(name = "ngayVanBan", index = "8")
    private String ngayVanBan;
    @Column(name = "ngayCanLapTbNguon", index = "9")
    private String ngayCanLapTbNguon;
    @Column(name = "soDienThoai", index = "10")
    private String soDienThoai;

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

    public String getMaCSHT() {
        return maCSHT;
    }

    public void setMaCSHT(String maCSHT) {
        this.maCSHT = maCSHT;
    }

    public String getNgayDuKienHtCSHT() {
        return ngayDuKienHtCSHT;
    }

    public void setNgayDuKienHtCSHT(String ngayDuKienHtCSHT) {
        this.ngayDuKienHtCSHT = ngayDuKienHtCSHT;
    }

    public String getTuNguon() {
        return tuNguon;
    }

    public void setTuNguon(String tuNguon) {
        this.tuNguon = tuNguon;
    }

    public String getChungLoaiACCU() {
        return chungLoaiACCU;
    }

    public void setChungLoaiACCU(String chungLoaiACCU) {
        this.chungLoaiACCU = chungLoaiACCU;
    }

    public String getSoToACCU() {
        return soToACCU;
    }

    public void setSoToACCU(String soToACCU) {
        this.soToACCU = soToACCU;
    }

    public String getMucDichTrangBiNguon() {
        return mucDichTrangBiNguon;
    }

    public void setMucDichTrangBiNguon(String mucDichTrangBiNguon) {
        this.mucDichTrangBiNguon = mucDichTrangBiNguon;
    }

    public String getSoVanBan() {
        return soVanBan;
    }

    public void setSoVanBan(String soVanBan) {
        this.soVanBan = soVanBan;
    }

    public String getNgayVanBan() {
        return ngayVanBan;
    }

    public void setNgayVanBan(String ngayVanBan) {
        this.ngayVanBan = ngayVanBan;
    }

    public String getNgayCanLapTbNguon() {
        return ngayCanLapTbNguon;
    }

    public void setNgayCanLapTbNguon(String ngayCanLapTbNguon) {
        this.ngayCanLapTbNguon = ngayCanLapTbNguon;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    
    

}
