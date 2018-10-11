/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.*;
import java.sql.Timestamp;
import com.blogspot.na5cent.exom.annotation.Column;
import java.lang.reflect.Field;

/**
 *
 * @author VNP
 */
public class UpdateCSHTExcel {

    @Column(name = "note", index = "")
    private String note;

    @Column(name = "code", index = "0")
    private String code;

    @Column(name = "name", index = "1")
    private String name;
    
    @Column(name = "planningCode", index = "2")
    private String planningCode;
    @Column(name = "ngayHdCsht", index = "3")
    private String ngayHdCsht;
    @Column(name = "donViQl", index = "4")
    private String donViQl;
    @Column(name = "quanHuyen", index = "5")
    private String quanHuyen;
    @Column(name = "xaPhuong", index = "6")
    private String xaPhuong;
    @Column(name = "diaChi", index = "7")
    private String diaChi;
    @Column(name = "lat", index = "8")
    private String lat;
    @Column(name = "lon", index = "9")
    private String lon;
    @Column(name = "chungCsht", index = "10")
    private String chungCsht;
    @Column(name = "loaiCSHT", index = "11")
    private String loaiCSHT;
    @Column(name = "loaiTramCsht", index = "12")
    private String loaiTramCsht;
    @Column(name = "doCaoAnTen", index = "13")
    private String doCaoAnTen;
    @Column(name = "doCaoNhaDatAnTen", index = "14")
    private String doCaoNhaDatAnTen;
    @Column(name = "loaiCotAnTen", index = "15")
    private String loaiCotAnTen;
    @Column(name = "ngayHDTuNguon", index = "16")
    private String ngayHDTuNguon;
    @Column(name = "loaiTuNguon", index = "17")
    private String loaiTuNguon;
    @Column(name = "dongCungCapTuNguon", index = "18")
    private String dongCungCapTuNguon;
    @Column(name = "soModuleTuNguon", index = "19")
    private String soModuleTuNguon;
    @Column(name = "dongTieuThuTuNguon", index = "20")
    private String dongTieuThuTuNguon;
    @Column(name = "ngayHDTuNguon2", index = "21")
    private String ngayHDTuNguon2;
    @Column(name = "loaiTuNguon2", index = "22")
    private String loaiTuNguon2;
    @Column(name = "dongCungCapTuNguon2", index = "23")
    private String dongCungCapTuNguon2;
    @Column(name = "soModuleTuNguon2", index = "24")
    private String soModuleTuNguon2;
    @Column(name = "dongTieuThuTuNguon2", index = "25")
    private String dongTieuThuTuNguon2;
    @Column(name = "ngayHDMayNo", index = "26")
    private String ngayHDMayNo;
    @Column(name = "loaiHinhMayNo", index = "27")
    private String loaiHinhMayNo;
    @Column(name = "loatMayNo", index = "28")
    private String loatMayNo;
    @Column(name = "congSuatMayNo", index = "29")
    private String congSuatMayNo;
    @Column(name = "trangThaiMayNo", index = "30")
    private String trangThaiMayNo;
    @Column(name = "ngayHDAccu", index = "31")
    private String ngayHDAccu;
    @Column(name = "loaiAcQuy", index = "32")
    private String loaiAcQuy;
    @Column(name = "dungLuongAccu", index = "33")
    private String dungLuongAccu;
    @Column(name = "dienApAccu", index = "34")
    private String dienApAccu;
    @Column(name = "slAccuBinh", index = "35")
    private String slAccuBinh;
    @Column(name = "thoigianHDSauMatDien", index = "36")
    private String thoigianHDSauMatDien;
    @Column(name = "ngayBaoDuongAccu", index = "37")
    private String ngayBaoDuongAccu;
    @Column(name = "ngayHDAccu2", index = "38")
    private String ngayHDAccu2;
    @Column(name = "loaiAcQuy2", index = "39")
    private String loaiAcQuy2;
    @Column(name = "dungLuongAccu2", index = "40")
    private String dungLuongAccu2;
    @Column(name = "dienApAccu2", index = "41")
    private String dienApAccu2;
    @Column(name = "slAccuBinh2", index = "42")
    private String slAccuBinh2;
    @Column(name = "thoigianHDSauMatDien2", index = "43")
    private String thoigianHDSauMatDien2;
    @Column(name = "ngayBaoDuongAccu2", index = "44")
    private String ngayBaoDuongAccu2;
    @Column(name = "loaiTruyenDan", index = "45")
    private String loaiTruyenDan;
    @Column(name = "giaoDienTruyenDan", index = "46")
    private String giaoDienTruyenDan;
    @Column(name = "dungLuongTruyenDan", index = "47")
    private String dungLuongTruyenDan;
    @Column(name = "dienTroTiepDia", index = "48")
    private String dienTroTiepDia;
    @Column(name = "slDieuHoa", index = "49")
    private String slDieuHoa;
    @Column(name = "tongCSDieuHoa", index = "50")
    private String tongCSDieuHoa;
    
    

    public String getPlanningCode() {
        return planningCode;
    }

    public void setPlanningCode(String planningCode) {
        this.planningCode = planningCode;
    }
    
    

    public String getDonViQl() {
        return donViQl;
    }

    public void setDonViQl(String donViQl) {
        this.donViQl = donViQl;
    }
    
    

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getXaPhuong() {
        return xaPhuong;
    }

    public void setXaPhuong(String xaPhuong) {
        this.xaPhuong = xaPhuong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public String getChungCsht() {
        return chungCsht;
    }

    public void setChungCsht(String chungCsht) {
        this.chungCsht = chungCsht;
    }

    public String getLoaiCSHT() {
        return loaiCSHT;
    }

    public void setLoaiCSHT(String loaiCSHT) {
        this.loaiCSHT = loaiCSHT;
    }

    public String getLoaiTramCsht() {
        return loaiTramCsht;
    }

    public void setLoaiTramCsht(String loaiTramCsht) {
        this.loaiTramCsht = loaiTramCsht;
    }

    public String getDoCaoAnTen() {
        return doCaoAnTen;
    }

    public void setDoCaoAnTen(String doCaoAnTen) {
        this.doCaoAnTen = doCaoAnTen;
    }

    public String getDoCaoNhaDatAnTen() {
        return doCaoNhaDatAnTen;
    }

    public void setDoCaoNhaDatAnTen(String doCaoNhaDatAnTen) {
        this.doCaoNhaDatAnTen = doCaoNhaDatAnTen;
    }

    public String getLoaiCotAnTen() {
        return loaiCotAnTen;
    }

    public void setLoaiCotAnTen(String loaiCotAnTen) {
        this.loaiCotAnTen = loaiCotAnTen;
    }

    public String getLoaiTuNguon() {
        return loaiTuNguon;
    }

    public void setLoaiTuNguon(String loaiTuNguon) {
        this.loaiTuNguon = loaiTuNguon;
    }

    public String getDongCungCapTuNguon() {
        return dongCungCapTuNguon;
    }

    public void setDongCungCapTuNguon(String dongCungCapTuNguon) {
        this.dongCungCapTuNguon = dongCungCapTuNguon;
    }

    public String getSoModuleTuNguon() {
        return soModuleTuNguon;
    }

    public void setSoModuleTuNguon(String soModuleTuNguon) {
        this.soModuleTuNguon = soModuleTuNguon;
    }

    public String getDongTieuThuTuNguon() {
        return dongTieuThuTuNguon;
    }

    public void setDongTieuThuTuNguon(String dongTieuThuTuNguon) {
        this.dongTieuThuTuNguon = dongTieuThuTuNguon;
    }

    public String getLoaiTuNguon2() {
        return loaiTuNguon2;
    }

    public void setLoaiTuNguon2(String loaiTuNguon2) {
        this.loaiTuNguon2 = loaiTuNguon2;
    }

    public String getDongCungCapTuNguon2() {
        return dongCungCapTuNguon2;
    }

    public void setDongCungCapTuNguon2(String dongCungCapTuNguon2) {
        this.dongCungCapTuNguon2 = dongCungCapTuNguon2;
    }

    public String getSoModuleTuNguon2() {
        return soModuleTuNguon2;
    }

    public void setSoModuleTuNguon2(String soModuleTuNguon2) {
        this.soModuleTuNguon2 = soModuleTuNguon2;
    }

    public String getDongTieuThuTuNguon2() {
        return dongTieuThuTuNguon2;
    }

    public void setDongTieuThuTuNguon2(String dongTieuThuTuNguon2) {
        this.dongTieuThuTuNguon2 = dongTieuThuTuNguon2;
    }

    public String getLoaiHinhMayNo() {
        return loaiHinhMayNo;
    }

    public void setLoaiHinhMayNo(String loaiHinhMayNo) {
        this.loaiHinhMayNo = loaiHinhMayNo;
    }

    public String getLoatMayNo() {
        return loatMayNo;
    }

    public void setLoatMayNo(String loatMayNo) {
        this.loatMayNo = loatMayNo;
    }

    public String getCongSuatMayNo() {
        return congSuatMayNo;
    }

    public void setCongSuatMayNo(String congSuatMayNo) {
        this.congSuatMayNo = congSuatMayNo;
    }

    public String getTrangThaiMayNo() {
        return trangThaiMayNo;
    }

    public void setTrangThaiMayNo(String trangThaiMayNo) {
        this.trangThaiMayNo = trangThaiMayNo;
    }

    public String getLoaiAcQuy() {
        return loaiAcQuy;
    }

    public void setLoaiAcQuy(String loaiAcQuy) {
        this.loaiAcQuy = loaiAcQuy;
    }

    public String getDungLuongAccu() {
        return dungLuongAccu;
    }

    public void setDungLuongAccu(String dungLuongAccu) {
        this.dungLuongAccu = dungLuongAccu;
    }

    public String getDienApAccu() {
        return dienApAccu;
    }

    public void setDienApAccu(String dienApAccu) {
        this.dienApAccu = dienApAccu;
    }

    public String getSlAccuBinh() {
        return slAccuBinh;
    }

    public void setSlAccuBinh(String slAccuBinh) {
        this.slAccuBinh = slAccuBinh;
    }

    public String getThoigianHDSauMatDien() {
        return thoigianHDSauMatDien;
    }

    public void setThoigianHDSauMatDien(String thoigianHDSauMatDien) {
        this.thoigianHDSauMatDien = thoigianHDSauMatDien;
    }

    public String getLoaiAcQuy2() {
        return loaiAcQuy2;
    }

    public void setLoaiAcQuy2(String loaiAcQuy2) {
        this.loaiAcQuy2 = loaiAcQuy2;
    }

    public String getDungLuongAccu2() {
        return dungLuongAccu2;
    }

    public void setDungLuongAccu2(String dungLuongAccu2) {
        this.dungLuongAccu2 = dungLuongAccu2;
    }

    public String getDienApAccu2() {
        return dienApAccu2;
    }

    public void setDienApAccu2(String dienApAccu2) {
        this.dienApAccu2 = dienApAccu2;
    }

    public String getSlAccuBinh2() {
        return slAccuBinh2;
    }

    public void setSlAccuBinh2(String slAccuBinh2) {
        this.slAccuBinh2 = slAccuBinh2;
    }

    public String getThoigianHDSauMatDien2() {
        return thoigianHDSauMatDien2;
    }

    public void setThoigianHDSauMatDien2(String thoigianHDSauMatDien2) {
        this.thoigianHDSauMatDien2 = thoigianHDSauMatDien2;
    }

    public String getNgayBaoDuongAccu2() {
        return ngayBaoDuongAccu2;
    }

    public void setNgayBaoDuongAccu2(String ngayBaoDuongAccu2) {
        this.ngayBaoDuongAccu2 = ngayBaoDuongAccu2;
    }

    public String getLoaiTruyenDan() {
        return loaiTruyenDan;
    }

    public void setLoaiTruyenDan(String loaiTruyenDan) {
        this.loaiTruyenDan = loaiTruyenDan;
    }

    public String getGiaoDienTruyenDan() {
        return giaoDienTruyenDan;
    }

    public void setGiaoDienTruyenDan(String giaoDienTruyenDan) {
        this.giaoDienTruyenDan = giaoDienTruyenDan;
    }

    public String getDungLuongTruyenDan() {
        return dungLuongTruyenDan;
    }

    public void setDungLuongTruyenDan(String dungLuongTruyenDan) {
        this.dungLuongTruyenDan = dungLuongTruyenDan;
    }

    public String getDienTroTiepDia() {
        return dienTroTiepDia;
    }

    public void setDienTroTiepDia(String dienTroTiepDia) {
        this.dienTroTiepDia = dienTroTiepDia;
    }

    public String getSlDieuHoa() {
        return slDieuHoa;
    }

    public void setSlDieuHoa(String slDieuHoa) {
        this.slDieuHoa = slDieuHoa;
    }

    public String getTongCSDieuHoa() {
        return tongCSDieuHoa;
    }

    public void setTongCSDieuHoa(String tongCSDieuHoa) {
        this.tongCSDieuHoa = tongCSDieuHoa;
    }

    public String getNgayHdCsht() {
        return ngayHdCsht;
    }

    public void setNgayHdCsht(String ngayHdCsht) {
        this.ngayHdCsht = ngayHdCsht;
    }

    public String getNgayHDTuNguon() {
        return ngayHDTuNguon;
    }

    public void setNgayHDTuNguon(String ngayHDTuNguon) {
        this.ngayHDTuNguon = ngayHDTuNguon;
    }

    public String getNgayHDTuNguon2() {
        return ngayHDTuNguon2;
    }

    public void setNgayHDTuNguon2(String ngayHDTuNguon2) {
        this.ngayHDTuNguon2 = ngayHDTuNguon2;
    }

    public String getNgayHDMayNo() {
        return ngayHDMayNo;
    }

    public void setNgayHDMayNo(String ngayHDMayNo) {
        this.ngayHDMayNo = ngayHDMayNo;
    }

    public String getNgayHDAccu() {
        return ngayHDAccu;
    }

    public void setNgayHDAccu(String ngayHDAccu) {
        this.ngayHDAccu = ngayHDAccu;
    }

    public String getNgayBaoDuongAccu() {
        return ngayBaoDuongAccu;
    }

    public void setNgayBaoDuongAccu(String ngayBaoDuongAccu) {
        this.ngayBaoDuongAccu = ngayBaoDuongAccu;
    }

    public String getNgayHDAccu2() {
        return ngayHDAccu2;
    }

    public void setNgayHDAccu2(String ngayHDAccu2) {
        this.ngayHDAccu2 = ngayHDAccu2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String listParam() {
        try {
            String list = "";
            for (Field field : ApproveAllForm.class.getDeclaredFields()) {
                String value = (String) field.get(this);
                String column = field.getName();
                list += "," + column + "=" + value;
            }
            return list.substring(1);
        } catch (Exception ex) {
            return "";
        }
    }
}
