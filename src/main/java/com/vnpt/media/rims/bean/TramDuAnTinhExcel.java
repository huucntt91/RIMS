/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.blogspot.na5cent.exom.annotation.Column;

/**
 *
 * @author VNP
 */
public class TramDuAnTinhExcel {

       
    @Column(name = "maTramDuAn", index = "0")
    private String maTramDuAn;
    @Column(name = "LonKS", index = "1")
    private String LonKS;
    @Column(name = "LatKS", index = "2")
    private String LatKS;
    @Column(name = "nhaTram", index = "3")
    private String nhaTram;
    @Column(name = "cotAnten", index = "4")
    private String cotAnten;
    @Column(name = "cauCapNgoai", index = "5")
    private String cauCapNgoai;
    @Column(name = "tuNguon", index = "6")
    private String tuNguon;
    @Column(name = "dungLuongTuNguon", index = "7")
    private String dungLuongTuNguon;
    @Column(name = "soModuleTuNguon", index = "8")
    private String soModuleTuNguon;
    @Column(name = "chungLoaiAccu", index = "9")
    private String chungLoaiAccu;
    @Column(name = "dungLuongAccu", index = "10")
    private String dungLuongAccu;
    @Column(name = "soLuongToAccu", index = "11")
    private String soLuongToAccu;
    @Column(name = "truyenDan", index = "12")
    private String truyenDan;
    @Column(name = "dieuHoa", index = "13")
    private String dieuHoa;
    @Column(name = "dienAc", index = "14")
    private String dienAc;
    @Column(name = "dienAcNoiTram", index = "15")
    private String dienAcNoiTram;
    @Column(name = "duDkLapEnodeb", index = "16")
    private String duDkLapEnodeb;
    @Column(name = "ngayDuDkLapThietBi", index = "17")
    private String ngayDuDkLapThietBi;
    @Column(name = "capMoiTuNguonDc", index = "18")
    private String capMoiTuNguonDc;
    @Column(name = "capMoiAccu", index = "19")
    private String capMoiAccu;
    @Column(name = "swapAnten", index = "20")
    private String swapAnten;
    @Column(name = "ngayHoanThanhKs", index = "21")
    private String ngayHoanThanhKs;   
    @Column(name = "ngayGuiSoLieu", index = "22")
    private String ngayGuiSoLieu;
    @Column(name = "dauMoiNhanThietBi", index = "23")
    private String dauMoiNhanThietBi;
    @Column(name = "dauMoiQLCSHT", index = "24")
    private String dauMoiQLCSHT;
    @Column(name = "donViLapDat", index = "25")
    private String donViLapDat;
    @Column(name = "ngayKeHoachLapDat", index = "26")
    private String ngayKeHoachLapDat;
    @Column(name = "ngayBatDauLapDat", index = "27")
    private String ngayBatDauLapDat;
    @Column(name = "ngayHTLapDatTb", index = "28")
    private String ngayHTLapDatTb;
    @Column(name = "ngayHTLapDatAnten", index = "29")
    private String ngayHTLapDatAnten;
    @Column(name = "ghiChu", index = "30")
    private String ghiChu;
    @Column(name = "note", index = "31")
    private String note; 
    
//    @Column(name = "code", index = "2")
//    private String code;
//
//    @Column(name = "maQuyHoach", index = "3")
//    private String maQuyHoach;
//    @Column(name = "maSoHopDong", index = "4")
//    private String maSoHopDong;
//    @Column(name = "tenTinhTp", index = "5")
//    private String tenTinhTp;
//    @Column(name = "tenQuanHuyen", index = "6")
//    private String tenQuanHuyen;
//    @Column(name = "address", index = "7")
//    private String address;
//
//    @Column(name = "tenTramDuAn", index = "8")
//    private String tenTramDuAn;
//    @Column(name = "maTramBTS", index = "9")
//    private String maTramBTS;
//    @Column(name = "maTramNodeB", index = "10")
//    private String maTramNodeB;
//    @Column(name = "maTramQuyHoach", index = "11")
//    private String maTramQuyHoach;
//    @Column(name = "hienTrangTram", index = "12")
//    private String hienTrangTram;
//    @Column(name = "longitude", index = "13")
//    private String longitude;
//    @Column(name = "latitude", index = "14")
//    private String latitude;
//
//    // -- cam ket thiet bi
//    @Column(name = "vnptNetPheDuyet", index = "15")
//    private String vnptNetPheDuyet;
//    @Column(name = "cauHinhThietBi", index = "16")
//    private String cauHinhThietBi;
//    @Column(name = "nguonThietBi", index = "17")
//    private String nguonThietBi;
//    @Column(name = "loaiCongNghe", index = "18")
//    private String loaiCongNghe;
//    @Column(name = "chungLoaiThietBi", index = "19")
//    private String chungLoaiThietBi;
//    @Column(name = "chungLoaiAnten", index = "20")
//    private String chungLoaiAnten;

    // -- cam ket ha tang
//
//    // -- triển khai dự án - NetX
//    @Column(name = "ngayPheDuyetKhaoSat", index = "45")
//    private String ngayPheDuyetKhaoSat;
//    @Column(name = "ngayTiepNhanTruyenDan", index = "46")
//    private String ngayTiepNhanTruyenDan;
//
//
//    //-- trien khai  du an - QLHT
//   @Column(name = "keHoachXuatThietBi", index = "47")
//    private String keHoachXuatThietBi;
//    @Column(name = "ngayXuatThietBiThucTe", index = "48")
//    private String ngayXuatThietBiThucTe;
//    @Column(name = "ngayTiepNhanTb", index = "49")
//    private String ngayTiepNhanTb;
//    
//    @Column(name = "keHoachTbDenSite", index = "50")
//    private String keHoachTbDenSite;    
//    @Column(name = "keHoachHoaMang", index = "54")
//    private String keHoachHoaMang;
//    @Column(name = "ngayHoaMangThucTe", index = "55")
//    private String ngayHoaMangThucTe;
//    @Column(name = "keHoachPhatSongCt", index = "56")
//    private String keHoachPhatSongCt;
//    @Column(name = "ngayPhatSongCt", index = "57")
//    private String ngayPhatSongCt;
//    @Column(name = "keHoachNghiemThu", index = "58")
//    private String keHoachNghiemThu;
//    @Column(name = "ngayNghiemThu", index = "59")
//    private String ngayNghiemThu;
//    @Column(name = "dauMoiVnptNet", index = "60")
//    private String dauMoiVnptNet;
//    @Column(name = "donViVanChuyen", index = "61")
//    private String donViVanChuyen;
    
    

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLonKS() {
        return LonKS;
    }

    public void setLonKS(String LonKS) {
        this.LonKS = LonKS;
    }

    public String getLatKS() {
        return LatKS;
    }

    public void setLatKS(String LatKS) {
        this.LatKS = LatKS;
    }

    public String getNhaTram() {
        return nhaTram;
    }

    public void setNhaTram(String nhaTram) {
        this.nhaTram = nhaTram;
    }

    public String getCotAnten() {
        return cotAnten;
    }

    public void setCotAnten(String cotAnten) {
        this.cotAnten = cotAnten;
    }

    public String getCauCapNgoai() {
        return cauCapNgoai;
    }

    public void setCauCapNgoai(String cauCapNgoai) {
        this.cauCapNgoai = cauCapNgoai;
    }

    public String getTuNguon() {
        return tuNguon;
    }

    public void setTuNguon(String tuNguon) {
        this.tuNguon = tuNguon;
    }

    public String getDungLuongTuNguon() {
        return dungLuongTuNguon;
    }

    public void setDungLuongTuNguon(String dungLuongTuNguon) {
        this.dungLuongTuNguon = dungLuongTuNguon;
    }

    public String getSoModuleTuNguon() {
        return soModuleTuNguon;
    }

    public void setSoModuleTuNguon(String soModuleTuNguon) {
        this.soModuleTuNguon = soModuleTuNguon;
    }

    public String getChungLoaiAccu() {
        return chungLoaiAccu;
    }

    public void setChungLoaiAccu(String chungLoaiAccu) {
        this.chungLoaiAccu = chungLoaiAccu;
    }

    public String getDungLuongAccu() {
        return dungLuongAccu;
    }

    public void setDungLuongAccu(String dungLuongAccu) {
        this.dungLuongAccu = dungLuongAccu;
    }

    public String getSoLuongToAccu() {
        return soLuongToAccu;
    }

    public void setSoLuongToAccu(String soLuongToAccu) {
        this.soLuongToAccu = soLuongToAccu;
    }

    public String getTruyenDan() {
        return truyenDan;
    }

    public void setTruyenDan(String truyenDan) {
        this.truyenDan = truyenDan;
    }

    public String getDieuHoa() {
        return dieuHoa;
    }

    public void setDieuHoa(String dieuHoa) {
        this.dieuHoa = dieuHoa;
    }

    public String getDienAc() {
        return dienAc;
    }

    public void setDienAc(String dienAc) {
        this.dienAc = dienAc;
    }

    public String getDienAcNoiTram() {
        return dienAcNoiTram;
    }

    public void setDienAcNoiTram(String dienAcNoiTram) {
        this.dienAcNoiTram = dienAcNoiTram;
    }

    public String getDuDkLapEnodeb() {
        return duDkLapEnodeb;
    }

    public void setDuDkLapEnodeb(String duDkLapEnodeb) {
        this.duDkLapEnodeb = duDkLapEnodeb;
    }

    public String getCapMoiTuNguonDc() {
        return capMoiTuNguonDc;
    }

    public void setCapMoiTuNguonDc(String capMoiTuNguonDc) {
        this.capMoiTuNguonDc = capMoiTuNguonDc;
    }

    public String getCapMoiAccu() {
        return capMoiAccu;
    }

    public void setCapMoiAccu(String capMoiAccu) {
        this.capMoiAccu = capMoiAccu;
    }

    public String getSwapAnten() {
        return swapAnten;
    }

    public void setSwapAnten(String swapAnten) {
        this.swapAnten = swapAnten;
    }

    public String getNgayHoanThanhKs() {
        return ngayHoanThanhKs;
    }

    public void setNgayHoanThanhKs(String ngayHoanThanhKs) {
        this.ngayHoanThanhKs = ngayHoanThanhKs;
    }

    public String getNgayGuiSoLieu() {
        return ngayGuiSoLieu;
    }

    public void setNgayGuiSoLieu(String ngayGuiSoLieu) {
        this.ngayGuiSoLieu = ngayGuiSoLieu;
    }

    public String getDauMoiNhanThietBi() {
        return dauMoiNhanThietBi;
    }

    public void setDauMoiNhanThietBi(String dauMoiNhanThietBi) {
        this.dauMoiNhanThietBi = dauMoiNhanThietBi;
    }

    public String getDauMoiQLCSHT() {
        return dauMoiQLCSHT;
    }

    public void setDauMoiQLCSHT(String dauMoiQLCSHT) {
        this.dauMoiQLCSHT = dauMoiQLCSHT;
    }

    public String getDonViLapDat() {
        return donViLapDat;
    }

    public void setDonViLapDat(String donViLapDat) {
        this.donViLapDat = donViLapDat;
    }

    public String getNgayDuDkLapThietBi() {
        return ngayDuDkLapThietBi;
    }

    public void setNgayDuDkLapThietBi(String ngayDuDkLapThietBi) {
        this.ngayDuDkLapThietBi = ngayDuDkLapThietBi;
    }


    public String getNgayBatDauLapDat() {
        return ngayBatDauLapDat;
    }

    public void setNgayBatDauLapDat(String ngayBatDauLapDat) {
        this.ngayBatDauLapDat = ngayBatDauLapDat;
    }

    public String getNgayHTLapDatTb() {
        return ngayHTLapDatTb;
    }

    public void setNgayHTLapDatTb(String ngayHTLapDatTb) {
        this.ngayHTLapDatTb = ngayHTLapDatTb;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaTramDuAn() {
        return maTramDuAn;
    }

    public void setMaTramDuAn(String maTramDuAn) {
        this.maTramDuAn = maTramDuAn;
    }

    public String getNgayKeHoachLapDat() {
        return ngayKeHoachLapDat;
    }

    public void setNgayKeHoachLapDat(String ngayKeHoachLapDat) {
        this.ngayKeHoachLapDat = ngayKeHoachLapDat;
    }


    public String getNgayHTLapDatAnten() {
        return ngayHTLapDatAnten;
    }

    public void setNgayHTLapDatAnten(String ngayHTLapDatAnten) {
        this.ngayHTLapDatAnten = ngayHTLapDatAnten;
    }

    
}
