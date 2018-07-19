/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import com.blogspot.na5cent.exom.annotation.Column;

/**
 *
 * @author VNP
 */
public class TramQuyHoachUpdateNetx {
    
    //thong tin chung
    @Column(name = "maTramQH", index = "0")
    private String maTramQH;
    
    @Column(name = "ngayPhatSong", index = "1")
    private String ngayPhatSong;
    
    //thong tin cam ket thiet bi
    @Column(name = "dvChiuTrachNhiemCktb", index = "2")
    private String dvChiuTrachNhiemCktb;
    
    @Column(name = "nguonThietBi", index = "3")
    private String nguonThietBi;
    
    @Column(name = "thoiDiemDapUngDuKien", index = "4")
    private String thoiDiemDapUngDuKien;
    
    @Column(name = "congNgheDapUng", index = "5")
    private String congNgheDapUng;
    
    @Column(name = "chungLoaiThietBi", index = "6")
    private String chungLoaiThietBi;
    
    @Column(name = "thoiGianDapUngThucTe", index = "7")
    private String thoiGianDapUngThucTe;
    
    @Column(name = "khoKhanVuongMac", index = "8")
    private String khoKhanVuongMac;
    
    //csht
    @Column(name = "danhGiaNetX", index = "9")
    private String danhGiaNetX;
    
    @Column(name = "yKienDanhGiaNetX", index = "10")
    private String yKienDanhGiaNetX;
    
    //antena
    @Column(name = "dvChiuTrachNhiemAntena", index = "11")
    private String dvChiuTrachNhiemAntena;
    
    @Column(name = "loaiAntena1", index = "12")
    private String loaiAntena1;
    
    @Column(name = "tenAntena1", index = "13")
    private String tenAntena1;
    
    @Column(name = "hangSxAntena1", index = "14")
    private String hangSxAntena1;
    
    @Column(name = "soLuongAntena1", index = "15")
    private String soLuongAntena1;
    
    @Column(name = "bangTan1", index = "16")
    private String bangTan1;
    
    @Column(name = "cauHinhPort1", index = "17")
    private String cauHinhPort1;
    
    @Column(name = "cauHinhGain1", index = "18")
    private String cauHinhGain1;
    
    @Column(name = "cauHinhBeamWidth1", index = "19")
    private String cauHinhBeamWidth1;
    
    @Column(name = "trongLuong1", index = "20")
    private String trongLuong1;
    
    @Column(name = "kichCoCao1", index = "21")
    private String kichCoCao1;
    
    @Column(name = "kichCoRong1", index = "22")
    private String kichCoRong1;
    
    @Column(name = "kichCoSau1", index = "23")
    private String kichCoSau1;
    
    @Column(name = "doCaoLapDat1", index = "24")
    private String doCaoLapDat1;
    
    //
    @Column(name = "loaiAntena2", index = "25")
     private String loaiAntena2;
    
    @Column(name = "tenAntena2", index = "26")
    private String tenAntena2;
    
    @Column(name = "hangSxAntena2", index = "27")
    private String hangSxAntena2;
    
    @Column(name = "soLuongAntena2", index = "28")
    private String soLuongAntena2;
    
    @Column(name = "bangTan2", index = "29")
    private String bangTan2;
    
    @Column(name = "cauHinhPort2", index = "30")
    private String cauHinhPort2;
    
    @Column(name = "cauHinhGain2", index = "31")
    private String cauHinhGain2;
    
    @Column(name = "cauHinhBeamWidth2", index = "32")
    private String cauHinhBeamWidth2;
    
    @Column(name = "trongLuong2", index = "33")
    private String trongLuong2;
    
    @Column(name = "kichCoCao2", index = "34")
    private String kichCoCao2;
    
    @Column(name = "kichCoRong2", index = "35")
    private String kichCoRong2;
    
    @Column(name = "kichCoSau2", index = "36")
    private String kichCoSau2;
    
    @Column(name = "doCaoLapDat2", index = "37")
    private String doCaoLapDat2;
    //
    @Column(name = "loaiAntena3", index = "38")
     private String loaiAntena3;
    
    @Column(name = "tenAntena3", index = "39")
    private String tenAntena3;
    
    @Column(name = "hangSxAntena3", index = "40")
    private String hangSxAntena3;
    
    @Column(name = "soLuongAntena3", index = "41")
    private String soLuongAntena3;
    
    @Column(name = "bangTan3", index = "42")
    private String bangTan3;
    
    @Column(name = "cauHinhPort3", index = "43")
    private String cauHinhPort3;
    
    @Column(name = "cauHinhGain3", index = "44")
    private String cauHinhGain3;
    
    @Column(name = "cauHinhBeamWidth3", index = "45")
    private String cauHinhBeamWidth3;
    
    @Column(name = "trongLuong3", index = "46")
    private String trongLuong3;
    
   @Column(name = "kichCoCao3", index = "47")
    private String kichCoCao3;
    
    @Column(name = "kichCoRong3", index = "48")
    private String kichCoRong3;
    
    @Column(name = "kichCoSau3", index = "49")
    private String kichCoSau3;
    
    @Column(name = "doCaoLapDat3", index = "50")
    private String doCaoLapDat3;
    
    @Column(name = "ngayDapUngDuKienAntena", index = "51")
    private String ngayDapUngDuKienAntena;
    
    @Column(name = "ngayDapUngThucTeAntena", index = "52")
    private String ngayDapUngThucTeAntena;
 
    @Column(name = "result", index = "53")
    private String result;

    public String getMaTramQH() {
        return maTramQH;
    }

    public void setMaTramQH(String maTramQH) {
        this.maTramQH = maTramQH;
    }

    public String getNgayPhatSong() {
        return ngayPhatSong;
    }

    public void setNgayPhatSong(String ngayPhatSong) {
        this.ngayPhatSong = ngayPhatSong;
    }

    public String getDvChiuTrachNhiemCktb() {
        return dvChiuTrachNhiemCktb;
    }

    public void setDvChiuTrachNhiemCktb(String dvChiuTrachNhiemCktb) {
        this.dvChiuTrachNhiemCktb = dvChiuTrachNhiemCktb;
    }

    public String getNguonThietBi() {
        return nguonThietBi;
    }

    public void setNguonThietBi(String nguonThietBi) {
        this.nguonThietBi = nguonThietBi;
    }

    public String getThoiDiemDapUngDuKien() {
        return thoiDiemDapUngDuKien;
    }

    public void setThoiDiemDapUngDuKien(String thoiDiemDapUngDuKien) {
        this.thoiDiemDapUngDuKien = thoiDiemDapUngDuKien;
    }

    public String getCongNgheDapUng() {
        return congNgheDapUng;
    }

    public void setCongNgheDapUng(String congNgheDapUng) {
        this.congNgheDapUng = congNgheDapUng;
    }

    public String getChungLoaiThietBi() {
        return chungLoaiThietBi;
    }

    public void setChungLoaiThietBi(String chungLoaiThietBi) {
        this.chungLoaiThietBi = chungLoaiThietBi;
    }

    public String getThoiGianDapUngThucTe() {
        return thoiGianDapUngThucTe;
    }

    public void setThoiGianDapUngThucTe(String thoiGianDapUngThucTe) {
        this.thoiGianDapUngThucTe = thoiGianDapUngThucTe;
    }

    public String getKhoKhanVuongMac() {
        return khoKhanVuongMac;
    }

    public void setKhoKhanVuongMac(String khoKhanVuongMac) {
        this.khoKhanVuongMac = khoKhanVuongMac;
    }

    public String getDanhGiaNetX() {
        return danhGiaNetX;
    }

    public void setDanhGiaNetX(String danhGiaNetX) {
        this.danhGiaNetX = danhGiaNetX;
    }

    public String getYKienDanhGiaNetX() {
        return yKienDanhGiaNetX;
    }

    public void setYKienDanhGiaNetX(String yKienDanhGiaNetX) {
        this.yKienDanhGiaNetX = yKienDanhGiaNetX;
    }

    public String getDvChiuTrachNhiemAntena() {
        return dvChiuTrachNhiemAntena;
    }

    public void setDvChiuTrachNhiemAntena(String dvChiuTrachNhiemAntena) {
        this.dvChiuTrachNhiemAntena = dvChiuTrachNhiemAntena;
    }

    public String getLoaiAntena1() {
        return loaiAntena1;
    }

    public void setLoaiAntena1(String loaiAntena1) {
        this.loaiAntena1 = loaiAntena1;
    }

    public String getTenAntena1() {
        return tenAntena1;
    }

    public void setTenAntena1(String tenAntena1) {
        this.tenAntena1 = tenAntena1;
    }

    public String getHangSxAntena1() {
        return hangSxAntena1;
    }

    public void setHangSxAntena1(String hangSxAntena1) {
        this.hangSxAntena1 = hangSxAntena1;
    }

    public String getSoLuongAntena1() {
        return soLuongAntena1;
    }

    public void setSoLuongAntena1(String soLuongAntena1) {
        this.soLuongAntena1 = soLuongAntena1;
    }

    public String getBangTan1() {
        return bangTan1;
    }

    public void setBangTan1(String bangTan1) {
        this.bangTan1 = bangTan1;
    }

    public String getCauHinhPort1() {
        return cauHinhPort1;
    }

    public void setCauHinhPort1(String cauHinhPort1) {
        this.cauHinhPort1 = cauHinhPort1;
    }

    public String getCauHinhGain1() {
        return cauHinhGain1;
    }

    public void setCauHinhGain1(String cauHinhGain1) {
        this.cauHinhGain1 = cauHinhGain1;
    }

    public String getCauHinhBeamWidth1() {
        return cauHinhBeamWidth1;
    }

    public void setCauHinhBeamWidth1(String cauHinhBeamWidth1) {
        this.cauHinhBeamWidth1 = cauHinhBeamWidth1;
    }

    public String getTrongLuong1() {
        return trongLuong1;
    }

    public void setTrongLuong1(String trongLuong1) {
        this.trongLuong1 = trongLuong1;
    }

    public String getKichCoCao1() {
        return kichCoCao1;
    }

    public void setKichCoCao1(String kichCoCao1) {
        this.kichCoCao1 = kichCoCao1;
    }

    public String getKichCoRong1() {
        return kichCoRong1;
    }

    public void setKichCoRong1(String kichCoRong1) {
        this.kichCoRong1 = kichCoRong1;
    }

    public String getKichCoSau1() {
        return kichCoSau1;
    }

    public void setKichCoSau1(String kichCoSau1) {
        this.kichCoSau1 = kichCoSau1;
    }

    public String getDoCaoLapDat1() {
        return doCaoLapDat1;
    }

    public void setDoCaoLapDat1(String doCaoLapDat1) {
        this.doCaoLapDat1 = doCaoLapDat1;
    }

    public String getLoaiAntena2() {
        return loaiAntena2;
    }

    public void setLoaiAntena2(String loaiAntena2) {
        this.loaiAntena2 = loaiAntena2;
    }

    public String getTenAntena2() {
        return tenAntena2;
    }

    public void setTenAntena2(String tenAntena2) {
        this.tenAntena2 = tenAntena2;
    }

    public String getHangSxAntena2() {
        return hangSxAntena2;
    }

    public void setHangSxAntena2(String hangSxAntena2) {
        this.hangSxAntena2 = hangSxAntena2;
    }

    public String getSoLuongAntena2() {
        return soLuongAntena2;
    }

    public void setSoLuongAntena2(String soLuongAntena2) {
        this.soLuongAntena2 = soLuongAntena2;
    }

    public String getBangTan2() {
        return bangTan2;
    }

    public void setBangTan2(String bangTan2) {
        this.bangTan2 = bangTan2;
    }

    public String getCauHinhPort2() {
        return cauHinhPort2;
    }

    public void setCauHinhPort2(String cauHinhPort2) {
        this.cauHinhPort2 = cauHinhPort2;
    }

    public String getCauHinhGain2() {
        return cauHinhGain2;
    }

    public void setCauHinhGain2(String cauHinhGain2) {
        this.cauHinhGain2 = cauHinhGain2;
    }

    public String getCauHinhBeamWidth2() {
        return cauHinhBeamWidth2;
    }

    public void setCauHinhBeamWidth2(String cauHinhBeamWidth2) {
        this.cauHinhBeamWidth2 = cauHinhBeamWidth2;
    }

    public String getTrongLuong2() {
        return trongLuong2;
    }

    public void setTrongLuong2(String trongLuong2) {
        this.trongLuong2 = trongLuong2;
    }

    public String getKichCoCao2() {
        return kichCoCao2;
    }

    public void setKichCoCao2(String kichCoCao2) {
        this.kichCoCao2 = kichCoCao2;
    }

    public String getKichCoRong2() {
        return kichCoRong2;
    }

    public void setKichCoRong2(String kichCoRong2) {
        this.kichCoRong2 = kichCoRong2;
    }

    public String getKichCoSau2() {
        return kichCoSau2;
    }

    public void setKichCoSau2(String kichCoSau2) {
        this.kichCoSau2 = kichCoSau2;
    }

    public String getDoCaoLapDat2() {
        return doCaoLapDat2;
    }

    public void setDoCaoLapDat2(String doCaoLapDat2) {
        this.doCaoLapDat2 = doCaoLapDat2;
    }

    public String getLoaiAntena3() {
        return loaiAntena3;
    }

    public void setLoaiAntena3(String loaiAntena3) {
        this.loaiAntena3 = loaiAntena3;
    }

    public String getTenAntena3() {
        return tenAntena3;
    }

    public void setTenAntena3(String tenAntena3) {
        this.tenAntena3 = tenAntena3;
    }

    public String getHangSxAntena3() {
        return hangSxAntena3;
    }

    public void setHangSxAntena3(String hangSxAntena3) {
        this.hangSxAntena3 = hangSxAntena3;
    }

    public String getSoLuongAntena3() {
        return soLuongAntena3;
    }

    public void setSoLuongAntena3(String soLuongAntena3) {
        this.soLuongAntena3 = soLuongAntena3;
    }

    public String getBangTan3() {
        return bangTan3;
    }

    public void setBangTan3(String bangTan3) {
        this.bangTan3 = bangTan3;
    }

    public String getCauHinhPort3() {
        return cauHinhPort3;
    }

    public void setCauHinhPort3(String cauHinhPort3) {
        this.cauHinhPort3 = cauHinhPort3;
    }

    public String getCauHinhGain3() {
        return cauHinhGain3;
    }

    public void setCauHinhGain3(String cauHinhGain3) {
        this.cauHinhGain3 = cauHinhGain3;
    }

    public String getCauHinhBeamWidth3() {
        return cauHinhBeamWidth3;
    }

    public void setCauHinhBeamWidth3(String cauHinhBeamWidth3) {
        this.cauHinhBeamWidth3 = cauHinhBeamWidth3;
    }

    public String getTrongLuong3() {
        return trongLuong3;
    }

    public void setTrongLuong3(String trongLuong3) {
        this.trongLuong3 = trongLuong3;
    }

    public String getKichCoCao3() {
        return kichCoCao3;
    }

    public void setKichCoCao3(String kichCoCao3) {
        this.kichCoCao3 = kichCoCao3;
    }

    public String getKichCoRong3() {
        return kichCoRong3;
    }

    public void setKichCoRong3(String kichCoRong3) {
        this.kichCoRong3 = kichCoRong3;
    }

    public String getKichCoSau3() {
        return kichCoSau3;
    }

    public void setKichCoSau3(String kichCoSau3) {
        this.kichCoSau3 = kichCoSau3;
    }

    public String getDoCaoLapDat3() {
        return doCaoLapDat3;
    }

    public void setDoCaoLapDat3(String doCaoLapDat3) {
        this.doCaoLapDat3 = doCaoLapDat3;
    }

    public String getNgayDapUngDuKienAntena() {
        return ngayDapUngDuKienAntena;
    }

    public void setNgayDapUngDuKienAntena(String ngayDapUngDuKienAntena) {
        this.ngayDapUngDuKienAntena = ngayDapUngDuKienAntena;
    }

    public String getNgayDapUngThucTeAntena() {
        return ngayDapUngThucTeAntena;
    }

    public void setNgayDapUngThucTeAntena(String ngayDapUngThucTeAntena) {
        this.ngayDapUngThucTeAntena = ngayDapUngThucTeAntena;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    
    
    
    
}
