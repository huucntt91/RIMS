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
public class Cell2GUpdateExcelModel {

    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ma cell", index = "1")
    private String code;
    @Column(name = "ma bts", index = "2")
    private String btsCode;
    @Column(name = "Ten cell", index = "3")
    private String ten_cell;
    @Column(name = "hoan canh ra doi", index = "4")
    private String hoanCanhRaDoi;
    @Column(name = "Ngay hoat dong", index = "5")
    private String ngayHoatDong;
    @Column(name = "ten tren he thong", index = "6")
    private String tenTrenHeThong;
    @Column(name = "Lac", index = "7")
    private String lac;
    @Column(name = "ci", index = "8")
    private String ci;
    @Column(name = "loaicongnghe", index = "9")
    private String loaiCN;
    @Column(name = "frequency band", index = "10")
    private String frequenctyBand;
    @Column(name = "Ten thiet bi", index = "11")
    private String tenThietBi;
    @Column(name = "Ten tram", index = "12")
    private String tenTram;
    @Column(name = "ngaypheduyet", index = "13")
    private String ngaypheduyet;
    @Column(name = "ly do", index = "14")
    private String lydo;
    @Column(name = "azimuth", index = "15")
    private String azimuth;
    @Column(name = "mechanicalTilt", index = "16")
    private String mechanicalTilt;
    @Column(name = "total tilt", index = "17")
    private String totalTilt;
    @Column(name = "antennaHigh", index = "18")
    private String antennaHigh;
    @Column(name = "antennaType", index = "19")
    private String antennaType;
    @Column(name = "antennaGain", index = "20")
    private String antennaGain;
    @Column(name = "nhomCell", index = "21")
    private String nhomCell;
    @Column(name = "cell type", index = "22")
    private String cellType;
    @Column(name = "vnp code", index = "23")
    private String vnpCode;
    @Column(name = "ngaydangky", index = "24")
    private String ngaydangky;
    @Column(name = "ngaycapphep", index = "25")
    private String ngaycapphep;
    @Column(name = "electricalTilt", index = "26")
    private String electricalTilt;
    @Column(name = "antennaModel", index = "27")
    private String antennaModel;
    @Column(name = "antennaParttern", index = "28")
    private String antennaParttern;
    @Column(name = "bosterTma", index = "29")
    private String bosterTma;
    @Column(name = "specialCoverage", index = "30")
    private String specialCoverage;
    @Column(name = "bcch", index = "31")
    private String bcch;
    @Column(name = "bsic", index = "32")
    private String bsic;
    @Column(name = "tch", index = "33")
    private String tch;
    @Column(name = "trxConfig", index = "34")
    private String trxConfig;
    @Column(name = "tenNgQly", index = "35")
    private String tenNgQly;
    @Column(name = "sdtNgQly", index = "36")
    private String sdtNgQly;

    public Cell2GUpdateExcelModel() {
    }

    public String getCheckDB() {
        return checkDB;
    }

    public void setCheckDB(String checkDB) {
        this.checkDB = checkDB;
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

    public String getFrequenctyBand() {
        return frequenctyBand;
    }

    public void setFrequenctyBand(String frequenctyBand) {
        this.frequenctyBand = frequenctyBand;
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

    public String getLoaiCN() {
        return loaiCN;
    }

    public void setLoaiCN(String loaiCN) {
        this.loaiCN = loaiCN;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBtsCode() {
        return btsCode;
    }

    public void setBtsCode(String btsCode) {
        this.btsCode = btsCode;
    }

    public String getNgaypheduyet() {
        return ngaypheduyet;
    }

    public void setNgaypheduyet(String ngaypheduyet) {
        this.ngaypheduyet = ngaypheduyet;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
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

    public String getAntennaHigh() {
        return antennaHigh;
    }

    public void setAntennaHigh(String antennaHigh) {
        this.antennaHigh = antennaHigh;
    }

    public String getAntennaType() {
        return antennaType;
    }

    public void setAntennaType(String antennaType) {
        this.antennaType = antennaType;
    }

    public String getAntennaGain() {
        return antennaGain;
    }

    public void setAntennaGain(String antennaGain) {
        this.antennaGain = antennaGain;
    }

    public String getNhomCell() {
        return nhomCell;
    }

    public void setNhomCell(String nhomCell) {
        this.nhomCell = nhomCell;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public String getVnpCode() {
        return vnpCode;
    }

    public void setVnpCode(String vnpCode) {
        this.vnpCode = vnpCode;
    }

    public String getNgaydangky() {
        return ngaydangky;
    }

    public void setNgaydangky(String ngaydangky) {
        this.ngaydangky = ngaydangky;
    }

    public String getNgaycapphep() {
        return ngaycapphep;
    }

    public void setNgaycapphep(String ngaycapphep) {
        this.ngaycapphep = ngaycapphep;
    }

    public String getElectricalTilt() {
        return electricalTilt;
    }

    public void setElectricalTilt(String electricalTilt) {
        this.electricalTilt = electricalTilt;
    }

    public String getAntennaModel() {
        return antennaModel;
    }

    public void setAntennaModel(String antennaModel) {
        this.antennaModel = antennaModel;
    }

    public String getAntennaParttern() {
        return antennaParttern;
    }

    public void setAntennaParttern(String antennaParttern) {
        this.antennaParttern = antennaParttern;
    }

    public String getBosterTma() {
        return bosterTma;
    }

    public void setBosterTma(String bosterTma) {
        this.bosterTma = bosterTma;
    }

    public String getSpecialCoverage() {
        return specialCoverage;
    }

    public void setSpecialCoverage(String specialCoverage) {
        this.specialCoverage = specialCoverage;
    }

    public String getBcch() {
        return bcch;
    }

    public void setBcch(String bcch) {
        this.bcch = bcch;
    }

    public String getBsic() {
        return bsic;
    }

    public void setBsic(String bsic) {
        this.bsic = bsic;
    }

    public String getTch() {
        return tch;
    }

    public void setTch(String tch) {
        this.tch = tch;
    }

    public String getTrxConfig() {
        return trxConfig;
    }

    public void setTrxConfig(String trxConfig) {
        this.trxConfig = trxConfig;
    }

    public String getTenNgQly() {
        return tenNgQly;
    }

    public void setTenNgQly(String tenNgQly) {
        this.tenNgQly = tenNgQly;
    }

    public String getSdtNgQly() {
        return sdtNgQly;
    }

    public void setSdtNgQly(String sdtNgQly) {
        this.sdtNgQly = sdtNgQly;
    }

}
