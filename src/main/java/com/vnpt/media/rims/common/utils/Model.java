/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package com.vnpt.media.rims.common.utils;

import com.blogspot.na5cent.exom.annotation.Column;
import java.util.Date;

/**
 * @author redcrow
 */
public class Model {

    @Column(name = "check", index = "")
    private boolean check;
    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "neType", index = "0")
    private String neType;
    @Column(name = "ma bsc/rnc", index = "1")
    private String bsc_rnc_code;

    @Column(name = "code", index = "2")
    private String code;

    @Column(name = "ten don vi quan ly", index = "3")
    private String tenDonViQuanLy;

    @Column(name = "ma building", index = "4")
    private String building_code;
    @Column(name = "ngay hoat dong",  index = "5")
    private String startDate;
    @Column(name = "hoan canh ra doi", index = "6")
    private String hoanCanhRaDoi;
    @Column(name = "ten nguoi quan ly", index = "7")
    private String tenNguoiQuanLy;
    @Column(name = "ten cho quan ly", index = "8")
    private String tenChoQuanLy;
    @Column(name = "ten thiet bi", index = "9")
    private String tenThietBi;
    @Column(name = "Loai tram", index = "10")
    private String loaiTram;
    @Column(name = "Cau hinh", index = "11")
    private String cauHinh;

    public Model() {
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getNeType() {
        return neType;
    }

    public void setNeType(String neType) {
        this.neType = neType;
    }

    public String getBsc_rnc_code() {
        return bsc_rnc_code;
    }

    public void setBsc_rnc_code(String bsc_rnc_code) {
        this.bsc_rnc_code = bsc_rnc_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTenDonViQuanLy() {
        return tenDonViQuanLy;
    }

    public void setTenDonViQuanLy(String tenDonViQuanLy) {
        this.tenDonViQuanLy = tenDonViQuanLy;
    }

    public String getBuilding_code() {
        return building_code;
    }

    public void setBuilding_code(String building_code) {
        this.building_code = building_code;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
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

    public String getCheckDB() {
        return checkDB;
    }

    public void setCheckDB(String checkDB) {
        this.checkDB = checkDB;
    }

}
