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
public class TramQuyHoachUpdatePtm {

    @Column(name = "maTramQH", index = "0")
    private String maTramQH;

    @Column(name = "tenTramQH", index = "1")
    private String tenTramQH;

    @Column(name = "maBuilding", index = "2")
    private String maBuilding;

    @Column(name = "tinhTp", index = "3")
    private String tinhTp;

    @Column(name = "namKhoiTao", index = "4")
    private String namKhoiTao;

    @Column(name = "longitude", index = "5")
    private String longitude;

    @Column(name = "latitude", index = "6")
    private String latitude;

    @Column(name = "loaiCongNghe", index = "7")
    private String loaiCongNghe;

    @Column(name = "bangTan", index = "8")
    private String bangTan;

    @Column(name = "chuongTrinhPtCSHT", index = "9")
    private String chuongTrinhPtCSHT;

    @Column(name = "trangThaiCSHT", index = "10")
    private String trangThaiCSHT;

    @Column(name = "dvPheDuyet", index = "11")
    private String dvPheDuyet;

    @Column(name = "soHieuVanBan", index = "12")
    private String soHieuVanBan;

    @Column(name = "ngayPheDuyet", index = "13")
    private String ngayPheDuyet;

    @Column(name = "ngayDieuChinhGanNhat", index = "14")
    private String ngayDieuChinhGanNhat;

    @Column(name = "dvDieuChinh", index = "15")
    private String dvDieuChinh;

    @Column(name = "ngayPhatSong", index = "16")
    private String ngayPhatSong;

    //thong tin cam ket thiet bi
    @Column(name = "dvChiuTrachNhiemCktb", index = "17")
    private String dvChiuTrachNhiemCktb;

    @Column(name = "nguonThietBi", index = "18")
    private String nguonThietBi;

    @Column(name = "thoiDiemDapUngDuKien", index = "19")
    private String thoiDiemDapUngDuKien;

    @Column(name = "congNgheDapUng", index = "20")
    private String congNgheDapUng;

    @Column(name = "chungLoaiThietBi", index = "21")
    private String chungLoaiThietBi;

    @Column(name = "thoiGianDapUngThucTe", index = "22")
    private String thoiGianDapUngThucTe;

    @Column(name = "khoKhanVuongMac", index = "23")
    private String khoKhanVuongMac;
    
    @Column(name = "result", index = "24")
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

    public String getMaBuilding() {
        return maBuilding;
    }

    public void setMaBuilding(String maBuilding) {
        this.maBuilding = maBuilding;
    }

    public String getTinhTp() {
        return tinhTp;
    }

    public void setTinhTp(String tinhTp) {
        this.tinhTp = tinhTp;
    }

    public String getNamKhoiTao() {
        return namKhoiTao;
    }

    public void setNamKhoiTao(String namKhoiTao) {
        this.namKhoiTao = namKhoiTao;
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

    public String getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(String loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

    public String getBangTan() {
        return bangTan;
    }

    public void setBangTan(String bangTan) {
        this.bangTan = bangTan;
    }

    public String getChuongTrinhPtCSHT() {
        return chuongTrinhPtCSHT;
    }

    public void setChuongTrinhPtCSHT(String chuongTrinhPtCSHT) {
        this.chuongTrinhPtCSHT = chuongTrinhPtCSHT;
    }

    public String getTrangThaiCSHT() {
        return trangThaiCSHT;
    }

    public void setTrangThaiCSHT(String trangThaiCSHT) {
        this.trangThaiCSHT = trangThaiCSHT;
    }

    public String getDvPheDuyet() {
        return dvPheDuyet;
    }

    public void setDvPheDuyet(String dvPheDuyet) {
        this.dvPheDuyet = dvPheDuyet;
    }

    public String getSoHieuVanBan() {
        return soHieuVanBan;
    }

    public void setSoHieuVanBan(String soHieuVanBan) {
        this.soHieuVanBan = soHieuVanBan;
    }

    public String getNgayPheDuyet() {
        return ngayPheDuyet;
    }

    public void setNgayPheDuyet(String ngayPheDuyet) {
        this.ngayPheDuyet = ngayPheDuyet;
    }

    public String getNgayDieuChinhGanNhat() {
        return ngayDieuChinhGanNhat;
    }

    public void setNgayDieuChinhGanNhat(String ngayDieuChinhGanNhat) {
        this.ngayDieuChinhGanNhat = ngayDieuChinhGanNhat;
    }

    public String getDvDieuChinh() {
        return dvDieuChinh;
    }

    public void setDvDieuChinh(String dvDieuChinh) {
        this.dvDieuChinh = dvDieuChinh;
    }

    public String getNgayPhatSong() {
        return ngayPhatSong;
    }

    public void setNgayPhatSong(String ngayPhatSong) {
        this.ngayPhatSong = ngayPhatSong;
    }

    public String getDvChiuTrachNhiemCktb() {
        return dvChiuTrachNhiemCktb;
    }

    public void setDvChiuTrachNhiemCktb(String dvChiuTrachNhiemCktb) {
        this.dvChiuTrachNhiemCktb = dvChiuTrachNhiemCktb;
    }

    public String getNguonThietBi() {
        return nguonThietBi;
    }

    public void setNguonThietBi(String nguonThietBi) {
        this.nguonThietBi = nguonThietBi;
    }

    public String getThoiDiemDapUngDuKien() {
        return thoiDiemDapUngDuKien;
    }

    public void setThoiDiemDapUngDuKien(String thoiDiemDapUngDuKien) {
        this.thoiDiemDapUngDuKien = thoiDiemDapUngDuKien;
    }

    public String getCongNgheDapUng() {
        return congNgheDapUng;
    }

    public void setCongNgheDapUng(String congNgheDapUng) {
        this.congNgheDapUng = congNgheDapUng;
    }

    public String getChungLoaiThietBi() {
        return chungLoaiThietBi;
    }

    public void setChungLoaiThietBi(String chungLoaiThietBi) {
        this.chungLoaiThietBi = chungLoaiThietBi;
    }

    public String getThoiGianDapUngThucTe() {
        return thoiGianDapUngThucTe;
    }

    public void setThoiGianDapUngThucTe(String thoiGianDapUngThucTe) {
        this.thoiGianDapUngThucTe = thoiGianDapUngThucTe;
    }

    public String getKhoKhanVuongMac() {
        return khoKhanVuongMac;
    }

    public void setKhoKhanVuongMac(String khoKhanVuongMac) {
        this.khoKhanVuongMac = khoKhanVuongMac;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
