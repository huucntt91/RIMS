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
public class ImportBtsModel {

    @Column(name = "check", index = "")
    private boolean check;
    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    
    @Column(name = "Ma DK site", index = "0")
    private String maDK;
    
    @Column(name = "loai ne", index = "1")
    private String neTypeName;
    @Column(name = "ma bsc rnc", index = "2")
    private String maBscRnc;

    @Column(name = "ma tram du an", index = "3")
    private String maTramDuAn;

    @Column(name = "ten don vi quan ly", index = "4")
    private String tenDonViQuanLy;

    @Column(name = "ma csht", index = "5")
    private String maBuilding;
    @Column(name = "Ngay hoat dong",  index = "6")
    private String ngayHoatDong;
    @Column(name = "hoan canh ra doi", index = "7")
    private String hoanCanhRaDoi;
    @Column(name = "ten nguoi quan ly", index = "8")
    private String tenNguoiQuanLy;
    @Column(name = "Ten cho quan ly", index = "9")
    private String tenChoQuanLy;
    @Column(name = "Ten trên hệ thống", index = "10")
    private String tenTrenHeThong;
    @Column(name = "Ten thiet bi", index = "11")
    private String thietBi;
    @Column(name = "loai tram", index = "12")
    private String loaiTram;
    @Column(name = "cau hinh", index = "13")
    private String cauHinh;
    @Column(name = "enode id", index = "14")
    private String enodebId;
    public ImportBtsModel() {
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

    public String getNeTypeName() {
        return neTypeName;
    }

    public void setNeTypeName(String neTypeId) {
        this.neTypeName = neTypeId;
    }

    public String getMaBscRnc() {
        return maBscRnc;
    }

    public void setMaBscRnc(String maBscRnc) {
        this.maBscRnc = maBscRnc;
    }

    public String getMaTramDuAn() {
        return maTramDuAn;
    }

    public void setMaTramDuAn(String maTramDuAn) {
        this.maTramDuAn = maTramDuAn;
    }

    public String getTenDonViQuanLy() {
        return tenDonViQuanLy;
    }

    public void setTenDonViQuanLy(String tenDonViQuanLy) {
        this.tenDonViQuanLy = tenDonViQuanLy;
    }

    public String getMaBuilding() {
        return maBuilding;
    }

    public void setMaBuilding(String maBuilding) {
        this.maBuilding = maBuilding;
    }

    public String getNgayHoatDong() {
        return ngayHoatDong;
    }

    public void setNgayHoatDong(String ngayHoatDong) {
        this.ngayHoatDong = ngayHoatDong;
    }

    public String getHoanCanhRaDoi() {
        return hoanCanhRaDoi;
    }

    public void setHoanCanhRaDoi(String hoanCanhRaDoi) {
        this.hoanCanhRaDoi = hoanCanhRaDoi;
    }

    public String getTenNguoiQuanLy() {
        return tenNguoiQuanLy;
    }

    public void setTenNguoiQuanLy(String tenNguoiQuanLy) {
        this.tenNguoiQuanLy = tenNguoiQuanLy;
    }

    public String getTenChoQuanLy() {
        return tenChoQuanLy;
    }

    public void setTenChoQuanLy(String tenChoQuanLy) {
        this.tenChoQuanLy = tenChoQuanLy;
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

    public String getCauHinh() {
        return cauHinh;
    }

    public void setCauHinh(String cauHinh) {
        this.cauHinh = cauHinh;
    }

    public String getTenTrenHeThong() {
        return tenTrenHeThong;
    }

    public void setTenTrenHeThong(String tenTrenHeThong) {
        this.tenTrenHeThong = tenTrenHeThong;
    }

    public String getEnodebId() {
        return enodebId;
    }

    public void setEnodebId(String enodebId) {
        this.enodebId = enodebId;
    }

    public String getMaDK() {
        return maDK;
    }

    public void setMaDK(String maDK) {
        this.maDK = maDK;
    }

    
    
}
