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
public class TramDuAnQLDAExcel {

       
    @Column(name = "maTramDuAn", index = "0")
    private String maTramDuAn;
    @Column(name = "keHoachXuatThietBi", index = "1")
    private String keHoachXuatThietBi;
    @Column(name = "ngayXuatThietBiThucTe", index = "2")
    private String ngayXuatThietBiThucTe;
    
    @Column(name = "ngayXuatAntenThucTe", index = "3")
    private String ngayXuatAntenThucTe;
    
    @Column(name = "ngayTiepNhanTb", index = "4")
    private String ngayTiepNhanTb;
    @Column(name = "keHoachTbDenSite", index = "5")
    private String keHoachTbDenSite;    
    @Column(name = "keHoachHoaMang", index = "6")
    private String keHoachHoaMang;
    @Column(name = "ngayHoaMangThucTe", index = "7")
    private String ngayHoaMangThucTe;
    @Column(name = "keHoachPhatSongCt", index = "8")
    private String keHoachPhatSongCt;
    @Column(name = "ngayPhatSongCt", index = "9")
    private String ngayPhatSongCt;
    @Column(name = "keHoachNghiemThu", index = "10")
    private String keHoachNghiemThu;
    @Column(name = "ngayNghiemThu", index = "11")
    private String ngayNghiemThu;
    @Column(name = "dauMoiVnptNet", index = "12")
    private String dauMoiVnptNet;
    @Column(name = "donViVanChuyen", index = "13")
    private String donViVanChuyen;
    @Column(name = "ghiChu", index = "14")
    private String ghiChu;
    @Column(name = "note", index = "15")
    private String note; 
    

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

    public String getKeHoachXuatThietBi() {
        return keHoachXuatThietBi;
    }

    public void setKeHoachXuatThietBi(String keHoachXuatThietBi) {
        this.keHoachXuatThietBi = keHoachXuatThietBi;
    }

    public String getNgayXuatThietBiThucTe() {
        return ngayXuatThietBiThucTe;
    }

    public void setNgayXuatThietBiThucTe(String ngayXuatThietBiThucTe) {
        this.ngayXuatThietBiThucTe = ngayXuatThietBiThucTe;
    }

    public String getNgayXuatAntenThucTe() {
        return ngayXuatAntenThucTe;
    }

    public void setNgayXuatAntenThucTe(String ngayXuatAntenThucTe) {
        this.ngayXuatAntenThucTe = ngayXuatAntenThucTe;
    }

    public String getNgayTiepNhanTb() {
        return ngayTiepNhanTb;
    }

    public void setNgayTiepNhanTb(String ngayTiepNhanTb) {
        this.ngayTiepNhanTb = ngayTiepNhanTb;
    }

    public String getKeHoachTbDenSite() {
        return keHoachTbDenSite;
    }

    public void setKeHoachTbDenSite(String keHoachTbDenSite) {
        this.keHoachTbDenSite = keHoachTbDenSite;
    }

    public String getKeHoachHoaMang() {
        return keHoachHoaMang;
    }

    public void setKeHoachHoaMang(String keHoachHoaMang) {
        this.keHoachHoaMang = keHoachHoaMang;
    }

    public String getNgayHoaMangThucTe() {
        return ngayHoaMangThucTe;
    }

    public void setNgayHoaMangThucTe(String ngayHoaMangThucTe) {
        this.ngayHoaMangThucTe = ngayHoaMangThucTe;
    }

    public String getKeHoachPhatSongCt() {
        return keHoachPhatSongCt;
    }

    public void setKeHoachPhatSongCt(String keHoachPhatSongCt) {
        this.keHoachPhatSongCt = keHoachPhatSongCt;
    }

    public String getNgayPhatSongCt() {
        return ngayPhatSongCt;
    }

    public void setNgayPhatSongCt(String ngayPhatSongCt) {
        this.ngayPhatSongCt = ngayPhatSongCt;
    }

    public String getKeHoachNghiemThu() {
        return keHoachNghiemThu;
    }

    public void setKeHoachNghiemThu(String keHoachNghiemThu) {
        this.keHoachNghiemThu = keHoachNghiemThu;
    }

    public String getNgayNghiemThu() {
        return ngayNghiemThu;
    }

    public void setNgayNghiemThu(String ngayNghiemThu) {
        this.ngayNghiemThu = ngayNghiemThu;
    }

    public String getDauMoiVnptNet() {
        return dauMoiVnptNet;
    }

    public void setDauMoiVnptNet(String dauMoiVnptNet) {
        this.dauMoiVnptNet = dauMoiVnptNet;
    }

    public String getDonViVanChuyen() {
        return donViVanChuyen;
    }

    public void setDonViVanChuyen(String donViVanChuyen) {
        this.donViVanChuyen = donViVanChuyen;
    }

    

    
}
