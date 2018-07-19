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
public class TramDuAnNetXExcel {

       
    @Column(name = "maTramDuAn", index = "0")
    private String maTramDuAn;
    @Column(name = "ngayPheDuyetKhaoSat", index = "1")
    private String ngayPheDuyetKhaoSat;
    @Column(name = "ngayTiepNhanTruyenDan", index = "2")
    private String ngayTiepNhanTruyenDan;
    @Column(name = "ghiChu", index = "3")
    private String ghiChu;
    @Column(name = "note", index = "4")
    private String note; 

    public String getNgayPheDuyetKhaoSat() {
        return ngayPheDuyetKhaoSat;
    }

    public void setNgayPheDuyetKhaoSat(String ngayPheDuyetKhaoSat) {
        this.ngayPheDuyetKhaoSat = ngayPheDuyetKhaoSat;
    }

    public String getNgayTiepNhanTruyenDan() {
        return ngayTiepNhanTruyenDan;
    }

    public void setNgayTiepNhanTruyenDan(String ngayTiepNhanTruyenDan) {
        this.ngayTiepNhanTruyenDan = ngayTiepNhanTruyenDan;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaTramDuAn() {
        return maTramDuAn;
    }

    public void setMaTramDuAn(String maTramDuAn) {
        this.maTramDuAn = maTramDuAn;
    }
    
    
}
