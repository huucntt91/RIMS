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
public class ImportCellModel {

    @Column(name = "check", index = "")
    private boolean check;
    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "Ten NE", index = "1")
    private String ne_name;
    @Column(name = "ma bts/nodeb/enode", index = "2")
    private String maNodeCha;
    @Column(name = "ten cho quan ly", index = "3")
    private String tenChoQuanLy;

    @Column(name = "hoan canh ra doi", index = "4")
    private String hoanCanhRaDoi;

    @Column(name = "Ngay hoat dong", index = "5")
    private String ngayHoatDong;

    @Column(name = "ten tren he thong", index = "6")
    private String tenTrenHeThong;
    @Column(name = "Lac",  index = "7")
    private String lac;
    @Column(name = "ci", index = "8")
    private String ci;
    @Column(name = "loai cong nghe", index = "9")
    private String loaiCongNghe;
    @Column(name = "frequency band", index = "10")
    private String frequenctyBand;
    @Column(name = "Ten thiet bi", index = "11")
    private String tenThietBi;
    @Column(name = "Ten tram", index = "12")
    private String tenTram;
    @Column(name = "no of carrier", index = "13")
    private String noOfCarrier;
    

    public ImportCellModel() {
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

    public String getNoOfCarrier() {
        return noOfCarrier;
    }

    public void setNoOfCarrier(String noOfCarrier) {
        this.noOfCarrier = noOfCarrier;
    }

    public String getNe_name() {
        return ne_name;
    }

    public void setNe_name(String ne_name) {
        this.ne_name = ne_name;
    }

    public String getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(String loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

    
    

}
