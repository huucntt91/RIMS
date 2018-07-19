/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Cyano
 */
public class ObjectTaiSan {
    private String Id;
    private String MaDuAn;
    private String TenDuAn;
    private String LoaiDuAn;
    private String DonVi;
    private String TenHopDong;
    private String SoHopDong;
    private String TenTaiSan;
    private String TheTaiSan;
    private String PartNumber;
    private String MaVach;
    private String SerialNumber;
    private String KhoiLuong;
    private String HangSx;
    private String ThoiGianSuDung;
    private String NgayTang;
    private String GiaTri;
    private String NguyenTe;
    private String CoKhauHao;
    
    private String LyDoKhongKhauHaoId; 
    private List<String> LyDoKhongKhauHao;
    private String PhuongPhapKhauHao;
    private List<NguonVon> ThongTinNguonVon;
    private String TongGiaTri;
    private String TongGiaTriConLai;
    private String HienTrangSuDungTaiSan;
    private String MucDichSuDung;
    private String LinhVucSuDung;
    private String DonViQuanLy;
    private String DonViSuDung;
    private String BoPhanSuDung;
    private String ChiPhiVanChuyen;
    private String ChiPhiChayThu;
    private String ViTriLapDat;
    private String TrangThaiBd ;
    private String LoaiTS;
    private String LoaiTaiSan;
    private String HinhThucTang;
    private List<BienDong> LichSuTaiSan;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

   

    public String getTenDuAn() {
        return TenDuAn;
    }

    public void setTenDuAn(String TenDuAn) {
        this.TenDuAn = TenDuAn;
    }

    public String getLoaiDuAn() {
        return LoaiDuAn;
    }

    public void setLoaiDuAn(String LoaiDuAn) {
        this.LoaiDuAn = LoaiDuAn;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
    }

    public String getTenHopDong() {
        return TenHopDong;
    }

    public void setTenHopDong(String TenHopDong) {
        this.TenHopDong = TenHopDong;
    }

    public String getSoHopDong() {
        return SoHopDong;
    }

    public void setSoHopDong(String SoHopDong) {
        this.SoHopDong = SoHopDong;
    }

    public String getTenTaiSan() {
        return TenTaiSan;
    }

    public void setTenTaiSan(String TenTaiSan) {
        this.TenTaiSan = TenTaiSan;
    }

    public String getTheTaiSan() {
        return TheTaiSan;
    }

    public void setTheTaiSan(String TheTaiSan) {
        this.TheTaiSan = TheTaiSan;
    }

    public String getPartNumber() {
        return PartNumber;
    }

    public void setPartNumber(String PartNumber) {
        this.PartNumber = PartNumber;
    }

    public String getMaVach() {
        return MaVach;
    }

    public void setMaVach(String MaVach) {
        this.MaVach = MaVach;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String SerialNumber) {
        this.SerialNumber = SerialNumber;
    }

    public String getKhoiLuong() {
        return KhoiLuong;
    }

    public void setKhoiLuong(String KhoiLuong) {
        this.KhoiLuong = KhoiLuong;
    }

    public String getHangSx() {
        return HangSx;
    }

    public void setHangSx(String HangSx) {
        this.HangSx = HangSx;
    }

    public String getThoiGianSuDung() {
        return ThoiGianSuDung;
    }

    public void setThoiGianSuDung(String ThoiGianSuDung) {
        this.ThoiGianSuDung = ThoiGianSuDung;
    }

    public String getNgayTang() {
        return NgayTang;
    }

    public void setNgayTang(String NgayTang) {
        this.NgayTang = NgayTang;
    }

    public String getNguyenTe() {
        return NguyenTe;
    }

    public void setNguyenTe(String NguyenTe) {
        this.NguyenTe = NguyenTe;
    }

    public String getCoKhauHao() {
        return CoKhauHao;
    }

    public void setCoKhauHao(String CoKhauHao) {
        this.CoKhauHao = CoKhauHao;
    }

    public String getLyDoKhongKhauHaoId() {
        return LyDoKhongKhauHaoId;
    }

    public void setLyDoKhongKhauHaoId(String LyDoKhongKhauHaoId) {
        this.LyDoKhongKhauHaoId = LyDoKhongKhauHaoId;
    }

    public List<String> getLyDoKhongKhauHao() {
        return LyDoKhongKhauHao;
    }

    public void setLyDoKhongKhauHao(List<String> LyDoKhongKhauHao) {
        this.LyDoKhongKhauHao = LyDoKhongKhauHao;
    }

    public String getPhuongPhapKhauHao() {
        return PhuongPhapKhauHao;
    }

    public void setPhuongPhapKhauHao(String PhuongPhapKhauHao) {
        this.PhuongPhapKhauHao = PhuongPhapKhauHao;
    }

    public List<NguonVon> getThongTinNguonVon() {
        return ThongTinNguonVon;
    }

    public void setThongTinNguonVon(List<NguonVon> ThongTinNguonVon) {
        this.ThongTinNguonVon = ThongTinNguonVon;
    }

    public String getTongGiaTri() {
        return TongGiaTri;
    }

    public void setTongGiaTri(String TongGiaTri) {
        this.TongGiaTri = TongGiaTri;
    }

    public String getTongGiaTriConLai() {
        return TongGiaTriConLai;
    }

    public void setTongGiaTriConLai(String TongGiaTriConLai) {
        this.TongGiaTriConLai = TongGiaTriConLai;
    }

    public String getHienTrangSuDungTaiSan() {
        return HienTrangSuDungTaiSan;
    }

    public void setHienTrangSuDungTaiSan(String HienTrangSuDungTaiSan) {
        this.HienTrangSuDungTaiSan = HienTrangSuDungTaiSan;
    }

    public String getMucDichSuDung() {
        return MucDichSuDung;
    }

    public void setMucDichSuDung(String MucDichSuDung) {
        this.MucDichSuDung = MucDichSuDung;
    }

    public String getLinhVucSuDung() {
        return LinhVucSuDung;
    }

    public void setLinhVucSuDung(String LinhVucSuDung) {
        this.LinhVucSuDung = LinhVucSuDung;
    }

    public String getDonViQuanLy() {
        return DonViQuanLy;
    }

    public void setDonViQuanLy(String DonViQuanLy) {
        this.DonViQuanLy = DonViQuanLy;
    }

    public String getDonViSuDung() {
        return DonViSuDung;
    }

    public void setDonViSuDung(String DonViSuDung) {
        this.DonViSuDung = DonViSuDung;
    }

    public String getBoPhanSuDung() {
        return BoPhanSuDung;
    }

    public void setBoPhanSuDung(String BoPhanSuDung) {
        this.BoPhanSuDung = BoPhanSuDung;
    }

    public String getChiPhiVanChuyen() {
        return ChiPhiVanChuyen;
    }

    public void setChiPhiVanChuyen(String ChiPhiVanChuyen) {
        this.ChiPhiVanChuyen = ChiPhiVanChuyen;
    }

    public String getChiPhiChayThu() {
        return ChiPhiChayThu;
    }

    public void setChiPhiChayThu(String ChiPhiChayThu) {
        this.ChiPhiChayThu = ChiPhiChayThu;
    }

    public String getViTriLapDat() {
        return ViTriLapDat;
    }

    public void setViTriLapDat(String ViTriLapDat) {
        this.ViTriLapDat = ViTriLapDat;
    }

    public String getTrangThaiBd() {
        return TrangThaiBd;
    }

    public void setTrangThaiBd(String TrangThaiBd) {
        this.TrangThaiBd = TrangThaiBd;
    }

    public String getLoaiTS() {
        return LoaiTS;
    }

    public void setLoaiTS(String LoaiTS) {
        this.LoaiTS = LoaiTS;
    }

    public String getLoaiTaiSan() {
        return LoaiTaiSan;
    }

    public void setLoaiTaiSan(String LoaiTaiSan) {
        this.LoaiTaiSan = LoaiTaiSan;
    }

    public String getHinhThucTang() {
        return HinhThucTang;
    }

    public void setHinhThucTang(String HinhThucTang) {
        this.HinhThucTang = HinhThucTang;
    }

    public List<BienDong> getLichSuTaiSan() {
        return LichSuTaiSan;
    }

    public void setLichSuTaiSan(List<BienDong> LichSuTaiSan) {
        this.LichSuTaiSan = LichSuTaiSan;
    }

    public String getMaDuAn() {
        return MaDuAn;
    }

    public void setMaDuAn(String MaDuAn) {
        this.MaDuAn = MaDuAn;
    }

    public String getGiaTri() {
        return GiaTri;
    }

    public void setGiaTri(String GiaTri) {
        this.GiaTri = GiaTri;
    }
    
    
    

}
