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
public class ExcelCellUpdateBO {

    @Column(name = "check", index = "")
    private boolean check;
    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ma cell", index = "1")
    private String maCell;
    @Column(name = "kieu cell", index = "2")
    private String kieuCell;

    @Column(name = "ma node cha", index = "3")
    private String maNodeCha;

    @Column(name = "ten cho quan ly", index = "4")
    private String tenChoQuanLy;

    @Column(name = "hoan canh ra doi", index = "5")
    private String hoanCanhRaDoi;
    @Column(name = "Ngay hoat dong", index = "6")
    private String ngayHoatDong;
    @Column(name = "Ten tren he thong", index = "7")
    private String tenTrenHeThong;
    @Column(name = "lac", index = "8")
    private String lac;
    @Column(name = "ci", index = "9")
    private String ci;
    @Column(name = "loai cong nghe", index = "10")
    private String loaiCongNghe;
    @Column(name = "tan so", index = "11")
    private String frequencyBand;
    @Column(name = "thiet bi", index = "12")
    private String thietBi;
    @Column(name = "loai tram", index = "13")
    private String loaiTram;
    @Column(name = "no of carrier", index = "14")
    private String noOfCarrier;
    @Column(name = "blacklist", index = "15")
    private String blacklist;
    @Column(name = "ngay phe duyet", index = "16")
    private String ngayPheDuyet;

    @Column(name = "lydo", index = "17")
    private String lydo;
    @Column(name = "lat", index = "18")
    private String lat;
    @Column(name = "lon", index = "19")
    private String lon;
    @Column(name = "azimuth", index = "20")
    private String azimuth;
    @Column(name = "mechanicalTilt", index = "21")
    private String mechanicalTilt;
    @Column(name = "totalTilt", index = "22")
    private String totalTilt;
    @Column(name = "antennaType", index = "23")
    private String antennaType;
    @Column(name = "antennaHigh", index = "24")
    private String antennaHigh;
    @Column(name = "antennaGain", index = "25")
    private String antennaGain;

    public ExcelCellUpdateBO() {
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getCheckDB() {
        return checkDB;
    }

    public void setCheckDB(String checkDB) {
        this.checkDB = checkDB;
    }

    public String getMaCell() {
        return maCell;
    }

    public void setMaCell(String maCell) {
        this.maCell = maCell;
    }

    public String getKieuCell() {
        return kieuCell;
    }

    public void setKieuCell(String kieuCell) {
        this.kieuCell = kieuCell;
    }

    public String getMaNodeCha() {
        return maNodeCha;
    }

    public void setMaNodeCha(String maNodeCha) {
        this.maNodeCha = maNodeCha;
    }

    public String getTenChoQuanLy() {
        return tenChoQuanLy;
    }

    public void setTenChoQuanLy(String tenChoQuanLy) {
        this.tenChoQuanLy = tenChoQuanLy;
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

    public String getFrequencyBand() {
        return frequencyBand;
    }

    public void setFrequencyBand(String frequencyBand) {
        this.frequencyBand = frequencyBand;
    }

    public String getThietBi() {
        return thietBi;
    }

    public void setThietBi(String thietBi) {
        this.thietBi = thietBi;
    }

    public String getLoaiTram() {
        return loaiTram;
    }

    public void setLoaiTram(String loaiTram) {
        this.loaiTram = loaiTram;
    }

    public String getNoOfCarrier() {
        return noOfCarrier;
    }

    public void setNoOfCarrier(String noOfCarrier) {
        this.noOfCarrier = noOfCarrier;
    }

    public String getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
    }

    public String getNgayPheDuyet() {
        return ngayPheDuyet;
    }

    public void setNgayPheDuyet(String ngayPheDuyet) {
        this.ngayPheDuyet = ngayPheDuyet;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public String getMechanicalTilt() {
        return mechanicalTilt;
    }

    public void setMechanicalTilt(String mechanicalTilt) {
        this.mechanicalTilt = mechanicalTilt;
    }

    public String getTotalTilt() {
        return totalTilt;
    }

    public void setTotalTilt(String totalTilt) {
        this.totalTilt = totalTilt;
    }

    public String getAntennaType() {
        return antennaType;
    }

    public void setAntennaType(String antennaType) {
        this.antennaType = antennaType;
    }

    public String getAntennaHigh() {
        return antennaHigh;
    }

    public void setAntennaHigh(String antennaHigh) {
        this.antennaHigh = antennaHigh;
    }

    public String getAntennaGain() {
        return antennaGain;
    }

    public void setAntennaGain(String antennaGain) {
        this.antennaGain = antennaGain;
    }

    public String getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(String loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

   

}
