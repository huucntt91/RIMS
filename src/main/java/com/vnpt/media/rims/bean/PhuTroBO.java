/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.lang.reflect.Field;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author VNP
 */
public class PhuTroBO {

    private Long id;

    private Long buildingId;
    private Long loaiTruyenDanId;
    private Long loaiAcQuyId;
    private Long loaiMayNoId;
    private Long loaiTuNguonId;
    private Long loaiAnTenId;

    private String code;
    private String truyenDan;
    private String acQuy;
    private String mayNo;
    private String tuNguon;
    private String anTen;

    private Long tinhTpId;
    private String tenTinhTP;

    private String chungCsht;
    private String doCaoAnTen;
    private String doCaoNhaDatAnTen;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHDTuNguon;
    private String soModuleTuNguon;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHDMayNo;
    private String congSuatMayNo;
    private String trangThaiMayNo;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHDAccu;

    private String thoigianHDSauMatDien;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayBaoDuongAccu;
    private String dungLuongTruyenDan;
    private String dienTroTiepDia;
    private String loaiTramCsht;
    private String loaiCSHT;
    private String slDieuHoa;
    private String csDieuHoa;
    private String loaiHinhMayNo;
    private String loaiCotAnTen;
    private String dongCungCapTuNguon;
    private String dongTieuThuTuNguon;
    private String dienApAccu;
    private String slAccuBinh;
    private String giaoDienTruyenDan;
    private String userId;
    private String cshtName;
    private String dungLuongAccu;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHDTuNguon2;
    private Long loaiTuNguonId2;
    private String dongCungCapTuNguon2;
    private String soModuleTuNguon2;
    private String dongTieuThuTuNguon2;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHDAccu2;
    private Long loaiAcQuyId2;
    private String dungLuongAccu2;
    private String dienApAccu2;
    private String slAccuBinh2;
    private String thoigianHDSauMatDien2;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayBaoDuongAccu2;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getLoaiTruyenDanId() {
        return loaiTruyenDanId;
    }

    public void setLoaiTruyenDanId(Long loaiTruyenDanId) {
        this.loaiTruyenDanId = loaiTruyenDanId;
    }

    public Long getLoaiAcQuyId() {
        return loaiAcQuyId;
    }

    public void setLoaiAcQuyId(Long loaiAcQuyId) {
        this.loaiAcQuyId = loaiAcQuyId;
    }

    public Long getLoaiMayNoId() {
        return loaiMayNoId;
    }

    public void setLoaiMayNoId(Long loaiMayNoId) {
        this.loaiMayNoId = loaiMayNoId;
    }

    public Long getLoaiTuNguonId() {
        return loaiTuNguonId;
    }

    public void setLoaiTuNguonId(Long loaiTuNguonId) {
        this.loaiTuNguonId = loaiTuNguonId;
    }

    public Long getLoaiAnTenId() {
        return loaiAnTenId;
    }

    public void setLoaiAnTenId(Long loaiAnTenId) {
        this.loaiAnTenId = loaiAnTenId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTruyenDan() {
        return truyenDan;
    }

    public void setTruyenDan(String truyenDan) {
        this.truyenDan = truyenDan;
    }

    public String getAcQuy() {
        return acQuy;
    }

    public void setAcQuy(String acQuy) {
        this.acQuy = acQuy;
    }

    public String getMayNo() {
        return mayNo;
    }

    public void setMayNo(String mayNo) {
        this.mayNo = mayNo;
    }

    public String getTuNguon() {
        return tuNguon;
    }

    public void setTuNguon(String tuNguon) {
        this.tuNguon = tuNguon;
    }

    public String getAnTen() {
        return anTen;
    }

    public void setAnTen(String anTen) {
        this.anTen = anTen;
    }

    public Long getTinhTpId() {
        return tinhTpId;
    }

    public void setTinhTpId(Long tinhTpId) {
        this.tinhTpId = tinhTpId;
    }

    public String getTenTinhTP() {
        return tenTinhTP;
    }

    public void setTenTinhTP(String tenTinhTP) {
        this.tenTinhTP = tenTinhTP;
    }

    public String getChungCsht() {
        return chungCsht;
    }

    public void setChungCsht(String chungCsht) {
        this.chungCsht = chungCsht;
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

    public Date getNgayHDTuNguon() {
        return ngayHDTuNguon;
    }

    public void setNgayHDTuNguon(Date ngayHDTuNguon) {
        this.ngayHDTuNguon = ngayHDTuNguon;
    }

    public String getSoModuleTuNguon() {
        return soModuleTuNguon;
    }

    public void setSoModuleTuNguon(String soModuleTuNguon) {
        this.soModuleTuNguon = soModuleTuNguon;
    }

    public Date getNgayHDMayNo() {
        return ngayHDMayNo;
    }

    public void setNgayHDMayNo(Date ngayHDMayNo) {
        this.ngayHDMayNo = ngayHDMayNo;
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

    public Date getNgayHDAccu() {
        return ngayHDAccu;
    }

    public void setNgayHDAccu(Date ngayHDAccu) {
        this.ngayHDAccu = ngayHDAccu;
    }

    public String getThoigianHDSauMatDien() {
        return thoigianHDSauMatDien;
    }

    public void setThoigianHDSauMatDien(String thoigianHDSauMatDien) {
        this.thoigianHDSauMatDien = thoigianHDSauMatDien;
    }

    public Date getNgayBaoDuongAccu() {
        return ngayBaoDuongAccu;
    }

    public void setNgayBaoDuongAccu(Date ngayBaoDuongAccu) {
        this.ngayBaoDuongAccu = ngayBaoDuongAccu;
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

    public String getLoaiTramCsht() {
        return loaiTramCsht;
    }

    public void setLoaiTramCsht(String loaiTramCsht) {
        this.loaiTramCsht = loaiTramCsht;
    }

    public String getLoaiCSHT() {
        return loaiCSHT;
    }

    public void setLoaiCSHT(String loaiCSHT) {
        this.loaiCSHT = loaiCSHT;
    }

    public String getSlDieuHoa() {
        return slDieuHoa;
    }

    public void setSlDieuHoa(String slDieuHoa) {
        this.slDieuHoa = slDieuHoa;
    }

    public String getCsDieuHoa() {
        return csDieuHoa;
    }

    public void setCsDieuHoa(String csDieuHoa) {
        this.csDieuHoa = csDieuHoa;
    }

    public String getLoaiHinhMayNo() {
        return loaiHinhMayNo;
    }

    public void setLoaiHinhMayNo(String loaiHinhMayNo) {
        this.loaiHinhMayNo = loaiHinhMayNo;
    }

    public String getLoaiCotAnTen() {
        return loaiCotAnTen;
    }

    public void setLoaiCotAnTen(String loaiCotAnTen) {
        this.loaiCotAnTen = loaiCotAnTen;
    }

    public String getDongCungCapTuNguon() {
        return dongCungCapTuNguon;
    }

    public void setDongCungCapTuNguon(String dongCungCapTuNguon) {
        this.dongCungCapTuNguon = dongCungCapTuNguon;
    }

    public String getDongTieuThuTuNguon() {
        return dongTieuThuTuNguon;
    }

    public void setDongTieuThuTuNguon(String dongTieuThuTuNguon) {
        this.dongTieuThuTuNguon = dongTieuThuTuNguon;
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

    public String getGiaoDienTruyenDan() {
        return giaoDienTruyenDan;
    }

    public void setGiaoDienTruyenDan(String giaoDienTruyenDan) {
        this.giaoDienTruyenDan = giaoDienTruyenDan;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCshtName() {
        return cshtName;
    }

    public void setCshtName(String cshtName) {
        this.cshtName = cshtName;
    }

    public String getDungLuongAccu() {
        return dungLuongAccu;
    }

    public void setDungLuongAccu(String dungLuongAccu) {
        this.dungLuongAccu = dungLuongAccu;
    }

    public Date getNgayHDTuNguon2() {
        return ngayHDTuNguon2;
    }

    public void setNgayHDTuNguon2(Date ngayHDTuNguon2) {
        this.ngayHDTuNguon2 = ngayHDTuNguon2;
    }

    public Long getLoaiTuNguonId2() {
        return loaiTuNguonId2;
    }

    public void setLoaiTuNguonId2(Long loaiTuNguonId2) {
        this.loaiTuNguonId2 = loaiTuNguonId2;
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

    public Date getNgayHDAccu2() {
        return ngayHDAccu2;
    }

    public void setNgayHDAccu2(Date ngayHDAccu2) {
        this.ngayHDAccu2 = ngayHDAccu2;
    }
   

    public Long getLoaiAcQuyId2() {
        return loaiAcQuyId2;
    }

    public void setLoaiAcQuyId2(Long loaiAcQuyId2) {
        this.loaiAcQuyId2 = loaiAcQuyId2;
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

    public Date getNgayBaoDuongAccu2() {
        return ngayBaoDuongAccu2;
    }

    public void setNgayBaoDuongAccu2(Date ngayBaoDuongAccu2) {
        this.ngayBaoDuongAccu2 = ngayBaoDuongAccu2;
    }

    public String listParam() {
        try {
            String list = "";
            for (Field field : PhuTroBO.class.getDeclaredFields()) {
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
