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
public class UpdateDuAnNguonPTMExcel {

    @Column(name = "note", index = "")
    private String note;
    
    @Column(name = "maTramQuyHoach", index = "0")
    private String maTramQuyHoach;
    @Column(name = "soTuNguon", index = "1")
    private String soTuNguon;
    @Column(name = "soModuleNan", index = "2")
    private String soModuleNan;
    @Column(name = "congSuatModuleNan", index = "3")
    private String congSuatModuleNan;
    @Column(name = "chungLoaiACCU", index = "4")
    private String chungLoaiACCU;
    @Column(name = "soToACCU", index = "5")
    private String soToACCU;
    @Column(name = "ghiChuNet", index = "6")
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

    public String getSoTuNguon() {
        return soTuNguon;
    }

    public void setSoTuNguon(String soTuNguon) {
        this.soTuNguon = soTuNguon;
    }

    public String getSoModuleNan() {
        return soModuleNan;
    }

    public void setSoModuleNan(String soModuleNan) {
        this.soModuleNan = soModuleNan;
    }

    public String getCongSuatModuleNan() {
        return congSuatModuleNan;
    }

    public void setCongSuatModuleNan(String congSuatModuleNan) {
        this.congSuatModuleNan = congSuatModuleNan;
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

    public String getGhiChuNet() {
        return ghiChuNet;
    }

    public void setGhiChuNet(String ghiChuNet) {
        this.ghiChuNet = ghiChuNet;
    }

    
    

}
