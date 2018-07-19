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
public class ExcelBtsUpdateBO {

    @Column(name = "check", index = "")
    private boolean check;
    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ten NE", index = "0")
    private String loaiNE;
    @Column(name = "ma bts nodeb enodeb", index = "1")
    private String maBts;
    @Column(name = "ma bsc rnc", index = "2")
    private String bscCode;

    @Column(name = "ma tram du an", index = "3")
    private String maTramDuAn;

    @Column(name = "ma building", index = "4")
    private String maBuilding;
    @Column(name = "ten nguoi quan ly", index = "5")
    private String tenNguoiQuanLy;
    @Column(name = "so dien thoai nguoi quan ly", index = "6")
    private String sdtNguoiQuanLy;
    @Column(name = "ten cho quan ly", index = "7")
    private String tenChoQuanLy;
    @Column(name = "hoan canh ra doi", index = "8")
    private String hoanCanhRaDoi;
    @Column(name = "Ngay hoat dong", index = "9")
    private String ngayHoatDong;
    @Column(name = "ten tren he thong", index = "10")
    private String tenTrenHeThong;
    @Column(name = "ten bsc rnc", index = "11")
    private String tenBscRnc;
    @Column(name = "msc mss", index = "12")
    private String mscMss;
    @Column(name = "sgsn", index = "13")
    private String sgsn;
    @Column(name = "dcHsdpa42M", index = "14")
    private String dcHsdpa42M;
    @Column(name = "filterUser", index = "15")
    private String filterUser;
    @Column(name = "loai cong nghe", index = "16")
    private String loaiCongNghe;
    @Column(name = "frequencyBand", index = "17")
    private String frequencyBand;
    
    @Column(name = "enodeb Id", index = "18")
    private String enodebId;
    
    @Column(name = "ten thiet bi", index = "19")
    private String thietBi;
    @Column(name = "loai Tram", index = "20")
    private String loaiTram;
    @Column(name = "cauHinh", index = "21")
    private String cauHinh;

    public ExcelBtsUpdateBO() {
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

    public String getMaBts() {
        return maBts;
    }

    public void setMaBts(String maBts) {
        this.maBts = maBts;
    }

    public String getBscCode() {
        return bscCode;
    }

    public void setBscCode(String bscCode) {
        this.bscCode = bscCode;
    }

    public String getMaTramDuAn() {
        return maTramDuAn;
    }

    public void setMaTramDuAn(String maTramDuAn) {
        this.maTramDuAn = maTramDuAn;
    }

   
    public String getMaBuilding() {
        return maBuilding;
    }

    public void setMaBuilding(String maBuilding) {
        this.maBuilding = maBuilding;
    }

    public String getTenNguoiQuanLy() {
        return tenNguoiQuanLy;
    }

    public void setTenNguoiQuanLy(String tenNguoiQuanLy) {
        this.tenNguoiQuanLy = tenNguoiQuanLy;
    }

    public String getSdtNguoiQuanLy() {
        return sdtNguoiQuanLy;
    }

    public void setSdtNguoiQuanLy(String sdtNguoiQuanLy) {
        this.sdtNguoiQuanLy = sdtNguoiQuanLy;
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

    public String getTenBscRnc() {
        return tenBscRnc;
    }

    public void setTenBscRnc(String tenBscRnc) {
        this.tenBscRnc = tenBscRnc;
    }

    public String getMscMss() {
        return mscMss;
    }

    public void setMscMss(String mscMss) {
        this.mscMss = mscMss;
    }

    public String getSgsn() {
        return sgsn;
    }

    public void setSgsn(String sgsn) {
        this.sgsn = sgsn;
    }

    public String getDcHsdpa42M() {
        return dcHsdpa42M;
    }

    public void setDcHsdpa42M(String dcHsdpa42M) {
        this.dcHsdpa42M = dcHsdpa42M;
    }

    public String getFilterUser() {
        return filterUser;
    }

    public void setFilterUser(String filterUser) {
        this.filterUser = filterUser;
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

    public String getCauHinh() {
        return cauHinh;
    }

    public void setCauHinh(String cauHinh) {
        this.cauHinh = cauHinh;
    }

    public String getLoaiNE() {
        return loaiNE;
    }

    public void setLoaiNE(String loaiNE) {
        this.loaiNE = loaiNE;
    }

    public String getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(String loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

    public String getEnodebId() {
        return enodebId;
    }

    public void setEnodebId(String enodebId) {
        this.enodebId = enodebId;
    }


}
