/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import com.blogspot.na5cent.exom.annotation.Column;
import java.lang.reflect.Field;

/**
 *
 * @author VNP
 */
public class TramQuyHoachUpdateCshtNguonDc {

    @Column(name = "maTramQH", index = "0")
    private String maTramQH;

    @Column(name = "tenTramQH", index = "1")
    private String tenTramQH;

    @Column(name = "cshtDonViChiuTrachNhiem", index = "2")
    private String cshtDonViChiuTrachNhiem;

    @Column(name = "quanHuyen", index = "3")
    private String quanHuyen;

    @Column(name = "phuongXa", index = "4")
    private String phuongXa;

    @Column(name = "diaChi", index = "5")
    private String diaChi;

    @Column(name = "cshtTenTram", index = "6")
    private String cshtTenTram;

    @Column(name = "cshtNgayPheDuyetCapVon", index = "7")
    private String cshtNgayPheDuyetCapVon;

    @Column(name = "cshtCachThucXD", index = "8")
    private String cshtCachThucXD;

    @Column(name = "cshtLoaiDat", index = "9")
    private String cshtLoaiDat;

    @Column(name = "cshtNgayCapThueDat", index = "10")
    private String cshtNgayCapThueDat;

    @Column(name = "cshtNgayXinPhepXDNhaTram", index = "11")
    private String cshtNgayXinPhepXDNhaTram;

    @Column(name = "cshtNgayHoanThanhThuTucXay", index = "12")
    private String cshtNgayHoanThanhThuTucXay;

    @Column(name = "cshtNgayKhoiCongXD", index = "13")
    private String cshtNgayKhoiCongXD;

    @Column(name = "cshtNgayHoanThanhXay", index = "14")
    private String cshtNgayHoanThanhXay;

    @Column(name = "cshtLoaiNhaTram", index = "15")
    private String cshtLoaiNhaTram;

    @Column(name = "cshtTinhTrangNhaTram", index = "16")
    private String cshtTinhTrangNhaTram;

    @Column(name = "cshtNgayXinPhepDoCaoCot", index = "17")
    private String cshtNgayXinPhepDoCaoCot;

    @Column(name = "cshtNgayCapPhepDoCaoCot", index = "18")
    private String cshtNgayCapPhepDoCaoCot;

    @Column(name = "cshtNgayHoanThanhThuTucXDCot", index = "19")
    private String cshtNgayHoanThanhThuTucXDCot;

    @Column(name = "cshtNgayKhoiCongDungCot", index = "20")
    private String cshtNgayKhoiCongDungCot;

    @Column(name = "ngayHoanThanhCot", index = "21")
    private String ngayHoanThanhCot;

    @Column(name = "loaiCot", index = "22")
    private String loaiCot;

    @Column(name = "doCaoCot", index = "23")
    private String doCaoCot;

    @Column(name = "doCaoChanCot", index = "24")
    private String doCaoChanCot;

    @Column(name = "cshtTinhTrangCotAnten", index = "25")
    private String cshtTinhTrangCotAnten;

    @Column(name = "phuongThucTruyenDan", index = "26")
    private String phuongThucTruyenDan;

    @Column(name = "E1", index = "27")
    private String e1;
    @Column(name = "fe", index = "28")
    private String fe;
    @Column(name = "ge", index = "29")
    private String ge;

    @Column(name = "stm", index = "30")
    private String stm;

    @Column(name = "ngayKhoiCongTruyenDan", index = "31")
    private String ngayKhoiCongTruyenDan;

    @Column(name = "ngayHoanThanhTruyenDan", index = "32")
    private String ngayHoanThanhTruyenDan;

    @Column(name = "cshtTinhTrangTruyenDan", index = "33")
    private String cshtTinhTrangTruyenDan;

    @Column(name = "ngayDienApAC", index = "34")
    private String ngayDienApAC;

    @Column(name = "heThongDienTrongNhaTram", index = "35")
    private String heThongDienTrongNhaTram;

    @Column(name = "heThongDieuHoa", index = "36")
    private String heThongDieuHoa;

    @Column(name = "heThongTiepDat", index = "37")
    private String heThongTiepDat;

    @Column(name = "mayNo", index = "38")
    private String mayNo;

    @Column(name = "ngayHoanThanhPHuTro", index = "39")
    private String ngayHoanThanhPHuTro;

    @Column(name = "doiTuongThongBao", index = "40")
    private String doiTuongThongBao;

    @Column(name = "soHieuThongBao", index = "41")
    private String soHieuThongBao;
    @Column(name = "ngayThongBaoHoanThanhCSHT", index = "42")
    private String ngayThongBaoHoanThanhCSHT;

    @Column(name = "khoKhanVuongMac", index = "43")
    private String khoKhanVuongMac;

    @Column(name = "nguonDonViChiuTrachNhiem", index = "44")
    private String nguonDonViChiuTrachNhiem;

    @Column(name = "tuNguon", index = "45")
    private String tuNguon;

    @Column(name = "loaiTuNguon", index = "46")
    private String loaiTuNguon;

    @Column(name = "dungLuongTuNguon", index = "47")
    private String dungLuongTuNguon;

    @Column(name = "soLuongRacktifier", index = "48")
    private String soLuongRacktifier;

    @Column(name = "dungLuongAcquy", index = "49")
    private String dungLuongAcquy;

    @Column(name = "soLuongToAcquy", index = "50")
    private String soLuongToAcquy;

    @Column(name = "dienApAcquy", index = "51")
    private String dienApAcquy;

    @Column(name = "ngayDapUngDcDuKien", index = "52")
    private String ngayDapUngDcDuKien;

    @Column(name = "ngayDapUngDcThucTe", index = "53")
    private String ngayDapUngDcThucTe;

    @Column(name = "tinhTrangNguonDien", index = "54")
    private String tinhTrangNguonDien;

    @Column(name = "result", index = "55")
    private String result;

    public Object trimObject(Object bean) {
        if (bean == null) {
            return bean;
        }

        Field[] fields = bean.getClass().getDeclaredFields();
        if (fields == null) {
            return bean;
        }

        for (Field f : fields) {
            if (f.getType().isPrimitive()) {
                continue;
            }

            if (f.getType().equals(String.class)) {
                try {
                    f.setAccessible(true);
                    String value = (String) f.get(bean);
                    if (value != null) {
                        f.set(bean, value.trim());
                    }
                } catch (IllegalAccessException e) {
                }
            }
        }
        return bean;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public String getCshtDonViChiuTrachNhiem() {
        return cshtDonViChiuTrachNhiem;
    }

    public void setCshtDonViChiuTrachNhiem(String cshtDonViChiuTrachNhiem) {
        this.cshtDonViChiuTrachNhiem = cshtDonViChiuTrachNhiem;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCshtTenTram() {
        return cshtTenTram;
    }

    public void setCshtTenTram(String cshtTenTram) {
        this.cshtTenTram = cshtTenTram;
    }

    public String getCshtNgayPheDuyetCapVon() {
        return cshtNgayPheDuyetCapVon;
    }

    public void setCshtNgayPheDuyetCapVon(String cshtNgayPheDuyetCapVon) {
        this.cshtNgayPheDuyetCapVon = cshtNgayPheDuyetCapVon;
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

    public String getCshtLoaiNhaTram() {
        return cshtLoaiNhaTram;
    }

    public void setCshtLoaiNhaTram(String cshtLoaiNhaTram) {
        this.cshtLoaiNhaTram = cshtLoaiNhaTram;
    }

    public String getCshtTinhTrangNhaTram() {
        return cshtTinhTrangNhaTram;
    }

    public void setCshtTinhTrangNhaTram(String cshtTinhTrangNhaTram) {
        this.cshtTinhTrangNhaTram = cshtTinhTrangNhaTram;
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

    public String getCshtTinhTrangCotAnten() {
        return cshtTinhTrangCotAnten;
    }

    public void setCshtTinhTrangCotAnten(String cshtTinhTrangCotAnten) {
        this.cshtTinhTrangCotAnten = cshtTinhTrangCotAnten;
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

    public String getCshtTinhTrangTruyenDan() {
        return cshtTinhTrangTruyenDan;
    }

    public void setCshtTinhTrangTruyenDan(String cshtTinhTrangTruyenDan) {
        this.cshtTinhTrangTruyenDan = cshtTinhTrangTruyenDan;
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

    public String getTinhTrangNguonDien() {
        return tinhTrangNguonDien;
    }

    public void setTinhTrangNguonDien(String tinhTrangNguonDien) {
        this.tinhTrangNguonDien = tinhTrangNguonDien;
    }

    public static void main(String[] args) {
    
    }

}
