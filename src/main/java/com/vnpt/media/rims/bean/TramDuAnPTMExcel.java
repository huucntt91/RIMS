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
public class TramDuAnPTMExcel {

       
    @Column(name = "maTramDuAn", index = "0")
    private String maTramDuAn;      
    @Column(name = "tenDuAn", index = "1")
    private String tenDuAn;
    @Column(name = "maSoHopDong", index = "2")
    private String maSoHopDong;
    @Column(name = "tenTinhTp", index = "3")
    private String tenTinhTp;
    @Column(name = "tenQuanHuyen", index = "4")
    private String tenQuanHuyen;
    @Column(name = "address", index = "5")
    private String address;
    @Column(name = "tenTramDuAn", index = "6")
    private String tenTramDuAn;
    @Column(name = "maTramBTS", index = "7")
    private String maTramBTS;
    @Column(name = "maTramNodeB", index = "8")
    private String maTramNodeB;
    @Column(name = "maTramQuyHoach", index = "9")
    private String maTramQuyHoach;   
    @Column(name = "longitude", index = "10")
    private String longitude;
    @Column(name = "latitude", index = "11")
    private String latitude;
    @Column(name = "hienTrangTram", index = "12")
    private String hienTrangTram;
    @Column(name = "trangthaiCSHT", index = "13")
    private String trangthaiCSHT;
    @Column(name = "vnptNetPheDuyet", index = "14")
    private String vnptNetPheDuyet;
    @Column(name = "cauHinhThietBi", index = "15")
    private String cauHinhThietBi;
    @Column(name = "nguonThietBi", index = "16")
    private String nguonThietBi;
    @Column(name = "loaiCongNghe", index = "17")
    private String loaiCongNghe;
    @Column(name = "chungLoaiThietBi", index = "18")
    private String chungLoaiThietBi;
    @Column(name = "chungLoaiAnten", index = "19")
    private String chungLoaiAnten;
    @Column(name = "ngayCungCapThietBi", index = "20")
    private String ngayCungCapThietBi;
    @Column(name = "ngaySwapThietBi", index = "21")
    private String ngaySwapThietBi;
    @Column(name = "ghiChu", index = "22")
    private String ghiChu;
    @Column(name = "note", index = "23")
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

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public String getMaSoHopDong() {
        return maSoHopDong;
    }

    public void setMaSoHopDong(String maSoHopDong) {
        this.maSoHopDong = maSoHopDong;
    }

    public String getTenTinhTp() {
        return tenTinhTp;
    }

    public void setTenTinhTp(String tenTinhTp) {
        this.tenTinhTp = tenTinhTp;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTenTramDuAn() {
        return tenTramDuAn;
    }

    public void setTenTramDuAn(String tenTramDuAn) {
        this.tenTramDuAn = tenTramDuAn;
    }

    public String getMaTramBTS() {
        return maTramBTS;
    }

    public void setMaTramBTS(String maTramBTS) {
        this.maTramBTS = maTramBTS;
    }

    public String getMaTramNodeB() {
        return maTramNodeB;
    }

    public void setMaTramNodeB(String maTramNodeB) {
        this.maTramNodeB = maTramNodeB;
    }

    public String getMaTramQuyHoach() {
        return maTramQuyHoach;
    }

    public void setMaTramQuyHoach(String maTramQuyHoach) {
        this.maTramQuyHoach = maTramQuyHoach;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHienTrangTram() {
        return hienTrangTram;
    }

    public void setHienTrangTram(String hienTrangTram) {
        this.hienTrangTram = hienTrangTram;
    }

    public String getTrangthaiCSHT() {
        return trangthaiCSHT;
    }

    public void setTrangthaiCSHT(String trangthaiCSHT) {
        this.trangthaiCSHT = trangthaiCSHT;
    }

    public String getVnptNetPheDuyet() {
        return vnptNetPheDuyet;
    }

    public void setVnptNetPheDuyet(String vnptNetPheDuyet) {
        this.vnptNetPheDuyet = vnptNetPheDuyet;
    }

    public String getCauHinhThietBi() {
        return cauHinhThietBi;
    }

    public void setCauHinhThietBi(String cauHinhThietBi) {
        this.cauHinhThietBi = cauHinhThietBi;
    }

    public String getNguonThietBi() {
        return nguonThietBi;
    }

    public void setNguonThietBi(String nguonThietBi) {
        this.nguonThietBi = nguonThietBi;
    }

    public String getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(String loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

    public String getChungLoaiThietBi() {
        return chungLoaiThietBi;
    }

    public void setChungLoaiThietBi(String chungLoaiThietBi) {
        this.chungLoaiThietBi = chungLoaiThietBi;
    }

    public String getChungLoaiAnten() {
        return chungLoaiAnten;
    }

    public void setChungLoaiAnten(String chungLoaiAnten) {
        this.chungLoaiAnten = chungLoaiAnten;
    }

    public String getNgayCungCapThietBi() {
        return ngayCungCapThietBi;
    }

    public void setNgayCungCapThietBi(String ngayCungCapThietBi) {
        this.ngayCungCapThietBi = ngayCungCapThietBi;
    }

    public String getNgaySwapThietBi() {
        return ngaySwapThietBi;
    }

    public void setNgaySwapThietBi(String ngaySwapThietBi) {
        this.ngaySwapThietBi = ngaySwapThietBi;
    }

    

    
}
