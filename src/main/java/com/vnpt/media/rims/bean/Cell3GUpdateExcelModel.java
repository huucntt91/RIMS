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
public class Cell3GUpdateExcelModel {

    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ma cell", index = "1")
    private String code;
    @Column(name = "ma bts", index = "2")
    private String btsCode;
    @Column(name = "ma csht", index = "3")
    private String maCsht;
    @Column(name = "cellType", index = "4")
    private String cellType;
    @Column(name = "Ten cell", index = "5")
    private String ten_cell;
    @Column(name = "hoan canh ra doi", index = "6")
    private String hoanCanhRaDoi;
    @Column(name = "Ngay hoat dong", index = "7")
    private String ngayHoatDong;
    @Column(name = "ten tren he thong", index = "8")
    private String tenTrenHeThong;
    @Column(name = "Lac", index = "9")
    private String lac;
    @Column(name = "ci", index = "10")
    private String ci;
    @Column(name = "loaicongnghe", index = "11")
    private String loaiCN;
    @Column(name = "frequency band", index = "12")
    private String frequenctyBand;
    @Column(name = "Ten thiet bi", index = "13")
    private String tenThietBi;
    @Column(name = "Ten tram", index = "14")
    private String tenTram;
    @Column(name = "ngaypheduyet", index = "15")
    private String ngaypheduyet;
    @Column(name = "ly do", index = "16")
    private String lydo;
    @Column(name = "azimuth", index = "17")
    private String azimuth;
    @Column(name = "mechanicalTilt", index = "18")
    private String mechanicalTilt;
    @Column(name = "total tilt", index = "19")
    private String totalTilt;
    @Column(name = "antennaHigh", index = "20")
    private String antennaHigh;
    @Column(name = "antennaType", index = "21")
    private String antennaType;
    @Column(name = "antennaGain", index = "22")
    private String antennaGain;
    @Column(name = "nhomCell", index = "23")
    private String nhomCell;
    
    @Column(name = "ngaydangky", index = "24")
    private String ngaydangky;
    @Column(name = "ngaycapphep", index = "25")
    private String ngaycapphep;
    @Column(name = "electricalTitl", index = "26")
    private String electricalTitl;
    @Column(name = "antennaModel", index = "27")
    private String antennaModel;
    @Column(name = "antennaParttern", index = "28")
    private String antennaParttern;
    @Column(name = "noOfCarrier", index = "29")
    private String noOfCarrier;
//		Lý do			TOTALPOWER	MAX POWER	Tên NgQL	SDT NgQLy

    @Column(name = "bosterTma", index = "30")
    private String bosterTma;
    @Column(name = "specialCoverage", index = "31")
    private String specialCoverage;
    @Column(name = "DL_PSC", index = "32")
    private String DL_PSC;
    @Column(name = "CPICH_POWER", index = "33")
    private String CPICH_POWER;
    @Column(name = "totalPower", index = "34")
    private String totalPower;
    @Column(name = "maxPower", index = "35")
    private String maxPower;
    @Column(name = "tenNgQL", index = "36")
    private String tenNgQL;
    @Column(name = "sdtNgQL", index = "37")
    private String sdtNgQL;

    public Cell3GUpdateExcelModel() {
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

    public String getElectricalTitl() {
        return electricalTitl;
    }

    public void setElectricalTitl(String electricalTitl) {
        this.electricalTitl = electricalTitl;
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

    public String getNoOfCarrier() {
        return noOfCarrier;
    }

    public void setNoOfCarrier(String noOfCarrier) {
        this.noOfCarrier = noOfCarrier;
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

    public String getDL_PSC() {
        return DL_PSC;
    }

    public void setDL_PSC(String DL_PSC) {
        this.DL_PSC = DL_PSC;
    }

    public String getCPICH_POWER() {
        return CPICH_POWER;
    }

    public void setCPICH_POWER(String CPICH_POWER) {
        this.CPICH_POWER = CPICH_POWER;
    }

    public String getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(String totalPower) {
        this.totalPower = totalPower;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }

    public String getTenNgQL() {
        return tenNgQL;
    }

    public void setTenNgQL(String tenNgQL) {
        this.tenNgQL = tenNgQL;
    }

    public String getSdtNgQL() {
        return sdtNgQL;
    }

    public void setSdtNgQL(String sdtNgQL) {
        this.sdtNgQL = sdtNgQL;
    }

    public String getMaCsht() {
        return maCsht;
    }

    public void setMaCsht(String maCsht) {
        this.maCsht = maCsht;
    }

}
