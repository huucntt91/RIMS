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
public class TramQuyHoachUpdateExcel {

    @Column(name = "note", index = "0")
    private String note;

    @Column(name = "maQHTinh", index = "1")
    private String maQHTinh;
    @Column(name = "maTramQH", index = "2")
    private String maTramQH;

    @Column(name = "tenTramQH", index = "3")
    private String tenTramQH;
    @Column(name = "maBuilding", index = "4")
    private String maBuilding;
    @Column(name = "namKhoiTao", index = "5")
    private String namKhoiTao;
    @Column(name = "loaiCongNghe", index = "6")
    private String loaiCongNghe;
    @Column(name = "bangtan", index = "7")
    private String bangtan;
    @Column(name = "ctPTCSHT", index = "8")
    private String ctPTCSHT;    
    @Column(name = "ttCSHT", index = "9")
    private String ttCSHT;
    @Column(name = "dvpheduyetTTC", index = "10")
    private String dvpheduyetTTC;
    @Column(name = "sohieuVB", index = "11")
    private String sohieuVB;
    @Column(name = "ngaypheduyetTTC", index = "12")
    private String ngaypheduyetTTC;
    @Column(name = "ngaydieuchinhTTC", index = "13")
    private String ngaydieuchinhTTC;
    @Column(name = "donviDieuChinh", index = "14")
    private String donviDieuChinh;

    // -- cam ket thiet bi
    @Column(name = "ngayPhatSongTTC", index = "15")
    private String ngayPhatSongTTC;
    @Column(name = "ttCkDonViChiuTrachNhiem", index = "16")
    private String ttCkDonViChiuTrachNhiem;
    @Column(name = "ttCkNguonThietBi", index = "17")
    private String ttCkNguonThietBi;
    @Column(name = "ttCkThoiDiemDapUngDuKien", index = "18")
    private String ttCkThoiDiemDapUngDuKien;
    @Column(name = "ttCkCongNgheDapUng", index = "19")
    private String ttCkCongNgheDapUng;
    @Column(name = "ttCkChungLoaiThietBi", index = "20")
    private String ttCkChungLoaiThietBi;

    // -- cam ket ha tang
    @Column(name = "ttCkThoiGianDapUngThucTe", index = "21")
    private String ttCkThoiGianDapUngThucTe;
    @Column(name = "ttCkKhoKhanVuongMac", index = "22")
    private String ttCkKhoKhanVuongMac;
//   Ngày xin phép xây dựng nhà trạm	Ngày hoàn thành thủ tục xây	Ngày khởi công xây dựng	Ngày hoàn thành xây	Ngày xin phép độ cao cột	Ngày cấp phép độ cao cột	Ngày hoàn thành thủ tục xây dựng cột	Ngày khởi công dựng cột	Ngày hoàn thành cột	Loại cột	Độ cao cột	Độ cao chân cột so với mực nước biển	Phương thức truyền dẫn	Giao diện truyền dẫn E1	Giao diện truyền dẫn FE	Giao diện truyền dẫn GE	Giao diện truyền dẫn STM1	Ngày khởi công truyền dẫn	Ngày hoàn thành truyền dẫn	Ngày đáp ứng điện AC	Hệ thống điện trong nhà trạm	Hệ thống điều hòa	Hệ thống tiếp đất	Máy nổ	Ngày hoàn thành phụ trợ	Đối tượng thông báo	Số hiệu thông báo	Ngày thông báo hoàn thành CSHT	Khó khăn vướng mắc

    @Column(name = "cshtDonViChiuTrachNhiem", index = "23")
    private String cshtDonViChiuTrachNhiem;
    @Column(name = "cshtTenTram", index = "24")
    private String cshtTenTram;
    @Column(name = "cshtCachThucXD", index = "25")
    private String cshtCachThucXD;
    @Column(name = "cshtLoaiDat", index = "26")
    private String cshtLoaiDat;
    @Column(name = "cshtNgayCapThueDat", index = "27")
    private String cshtNgayCapThueDat;
    @Column(name = "cshtNgayXinPhepXDNhaTram", index = "28")
    private String cshtNgayXinPhepXDNhaTram;
    @Column(name = "cshtNgayHoanThanhThuTucXay", index = "29")
    private String cshtNgayHoanThanhThuTucXay;
    @Column(name = "cshtNgayKhoiCongXD", index = "30")
    private String cshtNgayKhoiCongXD;
    @Column(name = "cshtNgayHoanThanhXay", index = "31")
    private String cshtNgayHoanThanhXay;
    @Column(name = "cshtNgayXinPhepDoCaoCot", index = "32")
    private String cshtNgayXinPhepDoCaoCot;
    @Column(name = "cshtNgayCapPhepDoCaoCot", index = "33")
    private String cshtNgayCapPhepDoCaoCot;
    @Column(name = "cshtNgayHoanThanhThuTucXDCot", index = "34")
    private String cshtNgayHoanThanhThuTucXDCot;
    @Column(name = "cshtNgayKhoiCongDungCot", index = "35")
    private String cshtNgayKhoiCongDungCot;
    @Column(name = "ngayHoanThanhCot", index = "36")
    private String ngayHoanThanhCot;
    @Column(name = "loaiCot", index = "37")
    private String loaiCot;
    @Column(name = "doCaoCot", index = "38")
    private String doCaoCot;
    @Column(name = "doCaoChanCot", index = "39")
    private String doCaoChanCot;
    @Column(name = "phuongThucTruyenDan", index = "40")
    private String phuongThucTruyenDan;

    @Column(name = "E1", index = "41")
    private String e1;
    @Column(name = "fe", index = "42")
    private String fe;
    @Column(name = "ge", index = "43")
    private String ge;

    @Column(name = "stm", index = "44")
    private String stm;

    // -- triển khai dự án - NetX
    @Column(name = "ngayKhoiCongTruyenDan", index = "45")
    private String ngayKhoiCongTruyenDan;
    @Column(name = "ngayHoanThanhTruyenDan", index = "46")
    private String ngayHoanThanhTruyenDan;

    //-- trien khai  du an - QLHT
    @Column(name = "ngayDienApAC", index = "47")
    private String ngayDienApAC;
    @Column(name = "heThongDienTrongNhaTram", index = "48")
    private String heThongDienTrongNhaTram;
    @Column(name = "heThongDieuHoa", index = "49")
    private String heThongDieuHoa;

    @Column(name = "heThongTiepDat", index = "50")
    private String heThongTiepDat;
    @Column(name = "mayNo", index = "51")
    private String mayNo;
    @Column(name = "ngayHoanThanhPHuTro", index = "52")
    private String ngayHoanThanhPHuTro;
    @Column(name = "doiTuongThongBao", index = "53")
    private String doiTuongThongBao;
    @Column(name = "soHieuThongBao", index = "54")
    private String soHieuThongBao;
    @Column(name = "ngayThongBaoHoanThanhCSHT", index = "55")
    private String ngayThongBaoHoanThanhCSHT;
    @Column(name = "khoKhanVuongMac", index = "56")
    private String khoKhanVuongMac;

    @Column(name = "nguonDonViChiuTrachNhiem", index = "57")
    private String nguonDonViChiuTrachNhiem;
    @Column(name = "tuNguon", index = "58")
    private String tuNguon;
    @Column(name = "loaiTuNguon", index = "59")
    private String loaiTuNguon;
    @Column(name = "dungLuongTuNguon", index = "60")
    private String dungLuongTuNguon;
    @Column(name = "soLuongRacktifier", index = "61")
    private String soLuongRacktifier;
    @Column(name = "dungLuongAcquy", index = "62")
    private String dungLuongAcquy;
    @Column(name = "soLuongToAcquy", index = "63")
    private String soLuongToAcquy;
    @Column(name = "dienApAcquy", index = "64")
    private String dienApAcquy;
    @Column(name = "ngayDapUngDcDuKien", index = "65")
    private String ngayDapUngDcDuKien;
    @Column(name = "ngayDapUngDcThucTe", index = "66")
    private String ngayDapUngDcThucTe;
    @Column(name = "antenDonViChiuTrachNhiem", index = "67")
    private String antenDonViChiuTrachNhiem;
    @Column(name = "antenNgayDapUngDuKien", index = "68")
    private String antenNgayDapUngDuKien;
    @Column(name = "antenNgayDapUngThucTe", index = "69")
    private String antenNgayDapUngThucTe;
    @Column(name = "loaiAnTen1", index = "70")
    private String loaiAnTen1;
    @Column(name = "tenAnTen1", index = "71")
    private String tenAnTen1;
    @Column(name = "hangSX1", index = "72")
    private String hangSX1;
    @Column(name = "soLuongAnten1", index = "73")
    private String soLuongAnten1;
    @Column(name = "bangTan1", index = "74")
    private String bangTan1;
    @Column(name = "cauHinhPort1", index = "75")
    private String cauHinhPort1;
    @Column(name = "cauHinhGain1", index = "76")
    private String cauHinhGain1;
    @Column(name = "cauHinhBeamWidth1", index = "77")
    private String cauHinhBeamWidth1;
    @Column(name = "trongLuong1", index = "78")
    private String trongLuong1;
    @Column(name = "kichCoCao1", index = "79")
    private String kichCoCao1;
    @Column(name = "kichCoRong1", index = "80")
    private String kichCoRong1;
    @Column(name = "kichCoSau1", index = "81")
    private String kichCoSau1;
    @Column(name = "doCaoLapDatAnten1", index = "82")
    private String doCaoLapDatAnten1;
//
    @Column(name = "loaiAnTen2", index = "83")
    private String loaiAnTen2;
    @Column(name = "tenAnTen2", index = "84")
    private String tenAnTen2;
    @Column(name = "hangSX2", index = "85")
    private String hangSX2;
    @Column(name = "soLuongAnten2", index = "86")
    private String soLuongAnten2;
    @Column(name = "bangTan2", index = "87")
    private String bangTan2;
    @Column(name = "cauHinhPort2", index = "88")
    private String cauHinhPort2;
    @Column(name = "cauHinhGain2", index = "89")
    private String cauHinhGain2;
    @Column(name = "cauHinhBeamWidth2", index = "90")
    private String cauHinhBeamWidth2;
    @Column(name = "trongLuong2", index = "91")
    private String trongLuong2;
    @Column(name = "kichCoCao2", index = "92")
    private String kichCoCao2;
    @Column(name = "kichCoRong2", index = "93")
    private String kichCoRong2;
    @Column(name = "kichCoSau2", index = "94")
    private String kichCoSau2;
    @Column(name = "doCaoLapDatAnten2", index = "95")
    private String doCaoLapDatAnten2;
    //
    @Column(name = "loaiAnTen3", index = "96")
    private String loaiAnTen3;
    @Column(name = "tenAnTen3", index = "97")
    private String tenAnTen3;
    @Column(name = "hangSX3", index = "98")
    private String hangSX3;
    @Column(name = "soLuongAnten3", index = "99")
    private String soLuongAnten3;
    @Column(name = "bangTan3", index = "100")
    private String bangTan3;
    @Column(name = "cauHinhPort3", index = "101")
    private String cauHinhPort3;
    @Column(name = "cauHinhGain3", index = "102")
    private String cauHinhGain3;
    @Column(name = "cauHinhBeamWidth3", index = "103")
    private String cauHinhBeamWidth3;
    @Column(name = "trongLuong3", index = "104")
    private String trongLuong3;
    @Column(name = "kichCoCao3", index = "105")
    private String kichCoCao3;
    @Column(name = "kichCoRong3", index = "106")
    private String kichCoRong3;
    @Column(name = "kichCoSau3", index = "107")
    private String kichCoSau3;
    @Column(name = "doCaoLapDatAnten3", index = "108")
    private String doCaoLapDatAnten3;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMaQHTinh() {
        return maQHTinh;
    }

    public void setMaQHTinh(String maQHTinh) {
        this.maQHTinh = maQHTinh;
    }

    public String getMaTramQH() {
        return maTramQH;
    }

    public void setMaTramQH(String maTramQH) {
        this.maTramQH = maTramQH;
    }

    public String getTenTramQH() {
        return tenTramQH;
    }

    public void setTenTramQH(String tenTramQH) {
        this.tenTramQH = tenTramQH;
    }

    public String getMaBuilding() {
        return maBuilding;
    }

    public void setMaBuilding(String maBuilding) {
        this.maBuilding = maBuilding;
    }

    public String getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(String loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

    public String getBangtan() {
        return bangtan;
    }

    public void setBangtan(String bangtan) {
        this.bangtan = bangtan;
    }

    public String getCtPTCSHT() {
        return ctPTCSHT;
    }

    public void setCtPTCSHT(String ctPTCSHT) {
        this.ctPTCSHT = ctPTCSHT;
    }

    public String getNamKhoiTao() {
        return namKhoiTao;
    }

    public void setNamKhoiTao(String namKhoiTao) {
        this.namKhoiTao = namKhoiTao;
    }

  

    public String getTtCSHT() {
        return ttCSHT;
    }

    public void setTtCSHT(String ttCSHT) {
        this.ttCSHT = ttCSHT;
    }

    public String getDvpheduyetTTC() {
        return dvpheduyetTTC;
    }

    public void setDvpheduyetTTC(String dvpheduyetTTC) {
        this.dvpheduyetTTC = dvpheduyetTTC;
    }

    public String getSohieuVB() {
        return sohieuVB;
    }

    public void setSohieuVB(String sohieuVB) {
        this.sohieuVB = sohieuVB;
    }

    public String getNgaypheduyetTTC() {
        return ngaypheduyetTTC;
    }

    public void setNgaypheduyetTTC(String ngaypheduyetTTC) {
        this.ngaypheduyetTTC = ngaypheduyetTTC;
    }

    public String getNgaydieuchinhTTC() {
        return ngaydieuchinhTTC;
    }

    public void setNgaydieuchinhTTC(String ngaydieuchinhTTC) {
        this.ngaydieuchinhTTC = ngaydieuchinhTTC;
    }

    public String getDonviDieuChinh() {
        return donviDieuChinh;
    }

    public void setDonviDieuChinh(String donviDieuChinh) {
        this.donviDieuChinh = donviDieuChinh;
    }

    public String getNgayPhatSongTTC() {
        return ngayPhatSongTTC;
    }

    public void setNgayPhatSongTTC(String ngayPhatSongTTC) {
        this.ngayPhatSongTTC = ngayPhatSongTTC;
    }

    public String getTtCkDonViChiuTrachNhiem() {
        return ttCkDonViChiuTrachNhiem;
    }

    public void setTtCkDonViChiuTrachNhiem(String ttCkDonViChiuTrachNhiem) {
        this.ttCkDonViChiuTrachNhiem = ttCkDonViChiuTrachNhiem;
    }

    public String getTtCkNguonThietBi() {
        return ttCkNguonThietBi;
    }

    public void setTtCkNguonThietBi(String ttCkNguonThietBi) {
        this.ttCkNguonThietBi = ttCkNguonThietBi;
    }

    public String getTtCkThoiDiemDapUngDuKien() {
        return ttCkThoiDiemDapUngDuKien;
    }

    public void setTtCkThoiDiemDapUngDuKien(String ttCkThoiDiemDapUngDuKien) {
        this.ttCkThoiDiemDapUngDuKien = ttCkThoiDiemDapUngDuKien;
    }

    public String getTtCkCongNgheDapUng() {
        return ttCkCongNgheDapUng;
    }

    public void setTtCkCongNgheDapUng(String ttCkCongNgheDapUng) {
        this.ttCkCongNgheDapUng = ttCkCongNgheDapUng;
    }

    public String getTtCkChungLoaiThietBi() {
        return ttCkChungLoaiThietBi;
    }

    public void setTtCkChungLoaiThietBi(String ttCkChungLoaiThietBi) {
        this.ttCkChungLoaiThietBi = ttCkChungLoaiThietBi;
    }

    public String getTtCkThoiGianDapUngThucTe() {
        return ttCkThoiGianDapUngThucTe;
    }

    public void setTtCkThoiGianDapUngThucTe(String ttCkThoiGianDapUngThucTe) {
        this.ttCkThoiGianDapUngThucTe = ttCkThoiGianDapUngThucTe;
    }

    public String getTtCkKhoKhanVuongMac() {
        return ttCkKhoKhanVuongMac;
    }

    public void setTtCkKhoKhanVuongMac(String ttCkKhoKhanVuongMac) {
        this.ttCkKhoKhanVuongMac = ttCkKhoKhanVuongMac;
    }

    public String getCshtDonViChiuTrachNhiem() {
        return cshtDonViChiuTrachNhiem;
    }

    public void setCshtDonViChiuTrachNhiem(String cshtDonViChiuTrachNhiem) {
        this.cshtDonViChiuTrachNhiem = cshtDonViChiuTrachNhiem;
    }

    public String getCshtTenTram() {
        return cshtTenTram;
    }

    public void setCshtTenTram(String cshtTenTram) {
        this.cshtTenTram = cshtTenTram;
    }

    public String getCshtCachThucXD() {
        return cshtCachThucXD;
    }

    public void setCshtCachThucXD(String cshtCachThucXD) {
        this.cshtCachThucXD = cshtCachThucXD;
    }

    public String getCshtLoaiDat() {
        return cshtLoaiDat;
    }

    public void setCshtLoaiDat(String cshtLoaiDat) {
        this.cshtLoaiDat = cshtLoaiDat;
    }

    public String getCshtNgayCapThueDat() {
        return cshtNgayCapThueDat;
    }

    public void setCshtNgayCapThueDat(String cshtNgayCapThueDat) {
        this.cshtNgayCapThueDat = cshtNgayCapThueDat;
    }

    public String getCshtNgayXinPhepXDNhaTram() {
        return cshtNgayXinPhepXDNhaTram;
    }

    public void setCshtNgayXinPhepXDNhaTram(String cshtNgayXinPhepXDNhaTram) {
        this.cshtNgayXinPhepXDNhaTram = cshtNgayXinPhepXDNhaTram;
    }

    public String getCshtNgayHoanThanhThuTucXay() {
        return cshtNgayHoanThanhThuTucXay;
    }

    public void setCshtNgayHoanThanhThuTucXay(String cshtNgayHoanThanhThuTucXay) {
        this.cshtNgayHoanThanhThuTucXay = cshtNgayHoanThanhThuTucXay;
    }

    public String getCshtNgayKhoiCongXD() {
        return cshtNgayKhoiCongXD;
    }

    public void setCshtNgayKhoiCongXD(String cshtNgayKhoiCongXD) {
        this.cshtNgayKhoiCongXD = cshtNgayKhoiCongXD;
    }

    public String getCshtNgayHoanThanhXay() {
        return cshtNgayHoanThanhXay;
    }

    public void setCshtNgayHoanThanhXay(String cshtNgayHoanThanhXay) {
        this.cshtNgayHoanThanhXay = cshtNgayHoanThanhXay;
    }

    public String getCshtNgayXinPhepDoCaoCot() {
        return cshtNgayXinPhepDoCaoCot;
    }

    public void setCshtNgayXinPhepDoCaoCot(String cshtNgayXinPhepDoCaoCot) {
        this.cshtNgayXinPhepDoCaoCot = cshtNgayXinPhepDoCaoCot;
    }

    public String getCshtNgayCapPhepDoCaoCot() {
        return cshtNgayCapPhepDoCaoCot;
    }

    public void setCshtNgayCapPhepDoCaoCot(String cshtNgayCapPhepDoCaoCot) {
        this.cshtNgayCapPhepDoCaoCot = cshtNgayCapPhepDoCaoCot;
    }

    public String getCshtNgayHoanThanhThuTucXDCot() {
        return cshtNgayHoanThanhThuTucXDCot;
    }

    public void setCshtNgayHoanThanhThuTucXDCot(String cshtNgayHoanThanhThuTucXDCot) {
        this.cshtNgayHoanThanhThuTucXDCot = cshtNgayHoanThanhThuTucXDCot;
    }

    public String getCshtNgayKhoiCongDungCot() {
        return cshtNgayKhoiCongDungCot;
    }

    public void setCshtNgayKhoiCongDungCot(String cshtNgayKhoiCongDungCot) {
        this.cshtNgayKhoiCongDungCot = cshtNgayKhoiCongDungCot;
    }

    public String getNgayHoanThanhCot() {
        return ngayHoanThanhCot;
    }

    public void setNgayHoanThanhCot(String ngayHoanThanhCot) {
        this.ngayHoanThanhCot = ngayHoanThanhCot;
    }

    public String getLoaiCot() {
        return loaiCot;
    }

    public void setLoaiCot(String loaiCot) {
        this.loaiCot = loaiCot;
    }

    public String getDoCaoCot() {
        return doCaoCot;
    }

    public void setDoCaoCot(String doCaoCot) {
        this.doCaoCot = doCaoCot;
    }

    public String getDoCaoChanCot() {
        return doCaoChanCot;
    }

    public void setDoCaoChanCot(String doCaoChanCot) {
        this.doCaoChanCot = doCaoChanCot;
    }

    public String getPhuongThucTruyenDan() {
        return phuongThucTruyenDan;
    }

    public void setPhuongThucTruyenDan(String phuongThucTruyenDan) {
        this.phuongThucTruyenDan = phuongThucTruyenDan;
    }

    public String getE1() {
        return e1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public String getFe() {
        return fe;
    }

    public void setFe(String fe) {
        this.fe = fe;
    }

    public String getGe() {
        return ge;
    }

    public void setGe(String ge) {
        this.ge = ge;
    }

    public String getStm() {
        return stm;
    }

    public void setStm(String stm) {
        this.stm = stm;
    }

    public String getNgayKhoiCongTruyenDan() {
        return ngayKhoiCongTruyenDan;
    }

    public void setNgayKhoiCongTruyenDan(String ngayKhoiCongTruyenDan) {
        this.ngayKhoiCongTruyenDan = ngayKhoiCongTruyenDan;
    }

    public String getNgayHoanThanhTruyenDan() {
        return ngayHoanThanhTruyenDan;
    }

    public void setNgayHoanThanhTruyenDan(String ngayHoanThanhTruyenDan) {
        this.ngayHoanThanhTruyenDan = ngayHoanThanhTruyenDan;
    }

    public String getNgayDienApAC() {
        return ngayDienApAC;
    }

    public void setNgayDienApAC(String ngayDienApAC) {
        this.ngayDienApAC = ngayDienApAC;
    }

    public String getHeThongDienTrongNhaTram() {
        return heThongDienTrongNhaTram;
    }

    public void setHeThongDienTrongNhaTram(String heThongDienTrongNhaTram) {
        this.heThongDienTrongNhaTram = heThongDienTrongNhaTram;
    }

    public String getHeThongDieuHoa() {
        return heThongDieuHoa;
    }

    public void setHeThongDieuHoa(String heThongDieuHoa) {
        this.heThongDieuHoa = heThongDieuHoa;
    }

    public String getHeThongTiepDat() {
        return heThongTiepDat;
    }

    public void setHeThongTiepDat(String heThongTiepDat) {
        this.heThongTiepDat = heThongTiepDat;
    }

    public String getMayNo() {
        return mayNo;
    }

    public void setMayNo(String mayNo) {
        this.mayNo = mayNo;
    }

    public String getNgayHoanThanhPHuTro() {
        return ngayHoanThanhPHuTro;
    }

    public void setNgayHoanThanhPHuTro(String ngayHoanThanhPHuTro) {
        this.ngayHoanThanhPHuTro = ngayHoanThanhPHuTro;
    }

    public String getDoiTuongThongBao() {
        return doiTuongThongBao;
    }

    public void setDoiTuongThongBao(String doiTuongThongBao) {
        this.doiTuongThongBao = doiTuongThongBao;
    }

    public String getSoHieuThongBao() {
        return soHieuThongBao;
    }

    public void setSoHieuThongBao(String soHieuThongBao) {
        this.soHieuThongBao = soHieuThongBao;
    }

    public String getNgayThongBaoHoanThanhCSHT() {
        return ngayThongBaoHoanThanhCSHT;
    }

    public void setNgayThongBaoHoanThanhCSHT(String ngayThongBaoHoanThanhCSHT) {
        this.ngayThongBaoHoanThanhCSHT = ngayThongBaoHoanThanhCSHT;
    }

    public String getKhoKhanVuongMac() {
        return khoKhanVuongMac;
    }

    public void setKhoKhanVuongMac(String khoKhanVuongMac) {
        this.khoKhanVuongMac = khoKhanVuongMac;
    }

    public String getNguonDonViChiuTrachNhiem() {
        return nguonDonViChiuTrachNhiem;
    }

    public void setNguonDonViChiuTrachNhiem(String nguonDonViChiuTrachNhiem) {
        this.nguonDonViChiuTrachNhiem = nguonDonViChiuTrachNhiem;
    }

    public String getTuNguon() {
        return tuNguon;
    }

    public void setTuNguon(String tuNguon) {
        this.tuNguon = tuNguon;
    }

    public String getLoaiTuNguon() {
        return loaiTuNguon;
    }

    public void setLoaiTuNguon(String loaiTuNguon) {
        this.loaiTuNguon = loaiTuNguon;
    }

    public String getDungLuongTuNguon() {
        return dungLuongTuNguon;
    }

    public void setDungLuongTuNguon(String dungLuongTuNguon) {
        this.dungLuongTuNguon = dungLuongTuNguon;
    }

    public String getSoLuongRacktifier() {
        return soLuongRacktifier;
    }

    public void setSoLuongRacktifier(String soLuongRacktifier) {
        this.soLuongRacktifier = soLuongRacktifier;
    }

    public String getDungLuongAcquy() {
        return dungLuongAcquy;
    }

    public void setDungLuongAcquy(String dungLuongAcquy) {
        this.dungLuongAcquy = dungLuongAcquy;
    }

    public String getSoLuongToAcquy() {
        return soLuongToAcquy;
    }

    public void setSoLuongToAcquy(String soLuongToAcquy) {
        this.soLuongToAcquy = soLuongToAcquy;
    }

    public String getDienApAcquy() {
        return dienApAcquy;
    }

    public void setDienApAcquy(String dienApAcquy) {
        this.dienApAcquy = dienApAcquy;
    }

    public String getNgayDapUngDcDuKien() {
        return ngayDapUngDcDuKien;
    }

    public void setNgayDapUngDcDuKien(String ngayDapUngDcDuKien) {
        this.ngayDapUngDcDuKien = ngayDapUngDcDuKien;
    }

    public String getNgayDapUngDcThucTe() {
        return ngayDapUngDcThucTe;
    }

    public void setNgayDapUngDcThucTe(String ngayDapUngDcThucTe) {
        this.ngayDapUngDcThucTe = ngayDapUngDcThucTe;
    }

    public String getAntenDonViChiuTrachNhiem() {
        return antenDonViChiuTrachNhiem;
    }

    public void setAntenDonViChiuTrachNhiem(String antenDonViChiuTrachNhiem) {
        this.antenDonViChiuTrachNhiem = antenDonViChiuTrachNhiem;
    }

    public String getAntenNgayDapUngDuKien() {
        return antenNgayDapUngDuKien;
    }

    public void setAntenNgayDapUngDuKien(String antenNgayDapUngDuKien) {
        this.antenNgayDapUngDuKien = antenNgayDapUngDuKien;
    }

    public String getAntenNgayDapUngThucTe() {
        return antenNgayDapUngThucTe;
    }

    public void setAntenNgayDapUngThucTe(String antenNgayDapUngThucTe) {
        this.antenNgayDapUngThucTe = antenNgayDapUngThucTe;
    }

    public String getLoaiAnTen1() {
        return loaiAnTen1;
    }

    public void setLoaiAnTen1(String loaiAnTen1) {
        this.loaiAnTen1 = loaiAnTen1;
    }

    public String getTenAnTen1() {
        return tenAnTen1;
    }

    public void setTenAnTen1(String tenAnTen1) {
        this.tenAnTen1 = tenAnTen1;
    }

    public String getHangSX1() {
        return hangSX1;
    }

    public void setHangSX1(String hangSX1) {
        this.hangSX1 = hangSX1;
    }

    public String getSoLuongAnten1() {
        return soLuongAnten1;
    }

    public void setSoLuongAnten1(String soLuongAnten1) {
        this.soLuongAnten1 = soLuongAnten1;
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

    public String getDoCaoLapDatAnten1() {
        return doCaoLapDatAnten1;
    }

    public void setDoCaoLapDatAnten1(String doCaoLapDatAnten1) {
        this.doCaoLapDatAnten1 = doCaoLapDatAnten1;
    }

    public String getLoaiAnTen2() {
        return loaiAnTen2;
    }

    public void setLoaiAnTen2(String loaiAnTen2) {
        this.loaiAnTen2 = loaiAnTen2;
    }

    public String getTenAnTen2() {
        return tenAnTen2;
    }

    public void setTenAnTen2(String tenAnTen2) {
        this.tenAnTen2 = tenAnTen2;
    }

    public String getHangSX2() {
        return hangSX2;
    }

    public void setHangSX2(String hangSX2) {
        this.hangSX2 = hangSX2;
    }

    public String getSoLuongAnten2() {
        return soLuongAnten2;
    }

    public void setSoLuongAnten2(String soLuongAnten2) {
        this.soLuongAnten2 = soLuongAnten2;
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

    public String getDoCaoLapDatAnten2() {
        return doCaoLapDatAnten2;
    }

    public void setDoCaoLapDatAnten2(String doCaoLapDatAnten2) {
        this.doCaoLapDatAnten2 = doCaoLapDatAnten2;
    }

    public String getLoaiAnTen3() {
        return loaiAnTen3;
    }

    public void setLoaiAnTen3(String loaiAnTen3) {
        this.loaiAnTen3 = loaiAnTen3;
    }

    public String getTenAnTen3() {
        return tenAnTen3;
    }

    public void setTenAnTen3(String tenAnTen3) {
        this.tenAnTen3 = tenAnTen3;
    }

    public String getHangSX3() {
        return hangSX3;
    }

    public void setHangSX3(String hangSX3) {
        this.hangSX3 = hangSX3;
    }

    public String getSoLuongAnten3() {
        return soLuongAnten3;
    }

    public void setSoLuongAnten3(String soLuongAnten3) {
        this.soLuongAnten3 = soLuongAnten3;
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

    public String getDoCaoLapDatAnten3() {
        return doCaoLapDatAnten3;
    }

    public void setDoCaoLapDatAnten3(String doCaoLapDatAnten3) {
        this.doCaoLapDatAnten3 = doCaoLapDatAnten3;
    }

  
}
