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
public class RegTramDuAnExcel {

    @Column(name = "note", index = "")
    private String note;
    
    @Column(name = "maTramDuAn", index = "0")
    private String maTramDuAn;
    @Column(name = "name", index = "1")
    private String name;
    @Column(name = "maSoHopDong", index = "2")
    private String maSoHopDong;
    @Column(name = "maCSHT", index = "3")
    private String maCSHT;
    @Column(name = "maQuyHoach", index = "4")
    private String maQuyHoach;
    @Column(name = "hienTrangTram", index = "5")
    private String hienTrangTram;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMaTramDuAn() {
        return maTramDuAn;
    }

    public void setMaTramDuAn(String maTramDuAn) {
        this.maTramDuAn = maTramDuAn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaSoHopDong() {
        return maSoHopDong;
    }

    public void setMaSoHopDong(String maSoHopDong) {
        this.maSoHopDong = maSoHopDong;
    }

    public String getMaCSHT() {
        return maCSHT;
    }

    public void setMaCSHT(String maCSHT) {
        this.maCSHT = maCSHT;
    }

    public String getMaQuyHoach() {
        return maQuyHoach;
    }

    public void setMaQuyHoach(String maQuyHoach) {
        this.maQuyHoach = maQuyHoach;
    }

    public String getHienTrangTram() {
        return hienTrangTram;
    }

    public void setHienTrangTram(String hienTrangTram) {
        this.hienTrangTram = hienTrangTram;
    }
    
    

}
