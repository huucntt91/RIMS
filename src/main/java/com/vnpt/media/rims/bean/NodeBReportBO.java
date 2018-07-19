/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Cyano
 */
public class NodeBReportBO {
//select ngay_hoat_dong, hoan_canh_ra_doi, ten_cell,ngay_dang_ky,ngay_kiem_duyet, ngay_cap_phep 
//,c.LATITUDE,c.LONGITUDE,c.AZIMUTH,c.MECHANICAL_TILT,c.TOTAL_TILT,c.ANTENNA_HIGH,c.ANTENNA_GAIN,c.ANTENNA_TYPE
//,c.NO_OF_CARRIER,c.CPICH_POWER,c.TOTAL_POWER,c.BOSTER_TMA,c.SPECIAL_COVERAGE,c.BLACK_LIST_FLAG,c.LY_DO
//,o.ten_tren_he_thong,o.lac,o.ci,o.TEN_BSC_RNC, o.code,o.DC_HSDPA_42M,o.FREQUENCY_BAND
//,n.TRANG_THAI_HD_ID,n.TRANG_THAI_QL_ID,n.THIET_BI_ID,n.LOAI_TRAM_ID
//from cell_info c
//inner join omc_cell_info o on c.node_id = o.node_id
//inner join node n on c.node_id =n.node_id

   private String id;

    
    private String tinh;
    private String quan;
    private String xa;
    private String diachi;
    private String ngayHoatDong;
    private String hoanCanhRaDoi;
    private String tenBts;
    private String ngayDangKy;
    private String ngayKiemDuyet;
    private String ngayCapPhep;
    private String latitude;
    private String longitude;
    private String cosite2G3GType;
    private String maCosite2G3GType;
    private String cauhinh;
    private String cauhinhSoTRX;

    private String trangThaiHdId;
    private String trangThaiQlId;
    private String maNode;
    private String tenThietBi;
    private String tenLoaiTram;
    private String thietbiId;
    private String loaiTramId;
    private String tenNguoiQL;
    private String soDTNgQL;
    private String ngayBaoDuong;
    private String donViThucHien;
    private String maKiemDinh;
    private String ngayHieuLuc;
    private String ngayHetHieuLuc;
    private String tenTrenHeThong;
    private String tenBscRnc;
    private String tenBscRncQl;
    private String mscMss;
    private String sgsn;
    private String dcHsdpa42M;
    private String filterUser;
    private String frequencyBand;
    private String chungCSHT;
    private String loaiTramCSHT;
    private String docaoAnTen;
//    PHU_TRO.DO_CAO_NHA_DAT_ANTEN,PHU_TRO.LOAI_ANTEN_ID
////,PHU_TRO.NGAY_HD_TU_NGUON,PHU_TRO.LOAI_TU_NGUON_ID,PHU_TRO.SO_MODULE_TU_NGUON,PHU_TRO.NGAY_HD_MAY_NO,PHU_TRO.LOAI_MAY_NO_ID
////,PHU_TRO.CONG_SUAT_MAY_NO,PHU_TRO.NGAY_HD_ACCU,PHU_TRO.LOAI_AC_QUY_ID,PHU_TRO.THOI_GIAN_HD_SAU_MAT_DIEN,PHU_TRO.NGAY_BAO_DUONG_ACCU,PHU_TRO.LOAI_TRUYEN_DAN_ID
////,PHU_TRO.DUNG_LUONG_TRUYEN_DAN,PHU_TRO.DIEN_TRO_TIEP_DIA
    private String doCaoNhaDatAnten;
    private String loaiAnTenId;
    private String ngayHDTuNguon;
    private String loaiTuNguonId;
    private String soModuleTuNguon;
    private String ngayHDMayNo;
    private String loaiMayNoId;
    private String congSuatMayNo;
    private String mayNoCoDinhDiDong;
    private String ngayHDAccu;
    private String loaiAcQuyId;
    private String dungLuongAcc;
    private String thoiGianHdSauMatDien;
    private String ngayBaoDuongAccu;
    private String loaiTruyenDanId;
    private String duongLuongTruyenDan;
    private String dienTroTiepDia;

    private String trangThaiDatMayNo;
    private String trangThaiHd;
    private String trangThaiQl;
    private String buildingId;
    private String tramDaId;
    
    private String loaiCotAnten;
    private String dongCungCapTuNguon;
    private String dongTieuThuTuNguon;
    private String loaiHinhMayNo;
    private String dienApBinh;
    private String soLuongAccuBinh;
    private String giaoDienTruyenDan;
    private String soLuongDieuHoa;
    private String tongCongSuatDieuHoa;
    private String maBuilding;
    
    private String locationNumber;
    

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }
    

    public String getGiaoDienTruyenDan() {
        return giaoDienTruyenDan;
    }

    public void setGiaoDienTruyenDan(String giaoDienTruyenDan) {
        this.giaoDienTruyenDan = giaoDienTruyenDan;
    }

    public String getSoLuongDieuHoa() {
        return soLuongDieuHoa;
    }

    public void setSoLuongDieuHoa(String soLuongDieuHoa) {
        this.soLuongDieuHoa = soLuongDieuHoa;
    }

    public String getTongCongSuatDieuHoa() {
        return tongCongSuatDieuHoa;
    }

    public void setTongCongSuatDieuHoa(String tongCongSuatDieuHoa) {
        this.tongCongSuatDieuHoa = tongCongSuatDieuHoa;
    }

    public String getMaBuilding() {
        return maBuilding;
    }

    public void setMaBuilding(String maBuilding) {
        this.maBuilding = maBuilding;
    }
    
    public String getDienApBinh() {
        return dienApBinh;
    }

    public void setDienApBinh(String dienApBinh) {
        this.dienApBinh = dienApBinh;
    }

    public String getSoLuongAccuBinh() {
        return soLuongAccuBinh;
    }

    public void setSoLuongAccuBinh(String soLuongAccuBinh) {
        this.soLuongAccuBinh = soLuongAccuBinh;
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

    public String getLoaiHinhMayNo() {
        return loaiHinhMayNo;
    }

    public void setLoaiHinhMayNo(String loaiHinhMayNo) {
        this.loaiHinhMayNo = loaiHinhMayNo;
    }
    
    public String getLoaiCotAnten() {
        return loaiCotAnten;
    }

    public void setLoaiCotAnten(String loaiCotAnten) {
        this.loaiCotAnten = loaiCotAnten;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getTramDaId() {
        return tramDaId;
    }

    public void setTramDaId(String tramDaId) {
        this.tramDaId = tramDaId;
    }
    private String userName;

    private Date createTime;
    private String action;
    private String ipClient;

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String IpClient) {
        this.ipClient = IpClient;
    }

    public NodeBReportBO() {
    }

    public String getUserName() {
        return userName;
    }

    public String getTrangThaiHd() {
        return trangThaiHd;
    }

    public void setTrangThaiHd(String trangThaiHd) {
        this.trangThaiHd = trangThaiHd;
    }

    public String getTrangThaiQl() {
        return trangThaiQl;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public String getTenLoaiTram() {
        return tenLoaiTram;
    }

    public void setTenLoaiTram(String tenLoaiTram) {
        this.tenLoaiTram = tenLoaiTram;
    }

    public void setTrangThaiQl(String trangThaiQl) {
        this.trangThaiQl = trangThaiQl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgayHoatDong() {
        return ngayHoatDong;
    }

    public void setNgayHoatDong(String ngayHoatDong) {
        this.ngayHoatDong = ngayHoatDong;
    }

    public String getHoanCanhRaDoi() {
        return hoanCanhRaDoi;
    }

    public void setHoanCanhRaDoi(String hoanCanhRaDoi) {
        this.hoanCanhRaDoi = hoanCanhRaDoi;
    }

    public String getTenBts() {
        return tenBts;
    }

    public void setTenBts(String tenBts) {
        this.tenBts = tenBts;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public String getCauhinhSoTRX() {
        return cauhinhSoTRX;
    }

    public void setCauhinhSoTRX(String cauhinhSoTRX) {
        this.cauhinhSoTRX = cauhinhSoTRX;
    }

    public String getTrangThaiDatMayNo() {
        return trangThaiDatMayNo;
    }

    public void setTrangThaiDatMayNo(String trangThaiDatMayNo) {
        this.trangThaiDatMayNo = trangThaiDatMayNo;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public String getNgayKiemDuyet() {
        return ngayKiemDuyet;
    }

    public void setNgayKiemDuyet(String ngayKiemDuyet) {
        this.ngayKiemDuyet = ngayKiemDuyet;
    }

    public String getNgayCapPhep() {
        return ngayCapPhep;
    }

    public void setNgayCapPhep(String ngayCapPhep) {
        this.ngayCapPhep = ngayCapPhep;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCosite2G3GType() {
        return cosite2G3GType;
    }

    public void setCosite2G3GType(String cosite2G3GType) {
        this.cosite2G3GType = cosite2G3GType;
    }

    public String getMaCosite2G3GType() {
        return maCosite2G3GType;
    }

    public void setMaCosite2G3GType(String maCosite2G3GType) {
        this.maCosite2G3GType = maCosite2G3GType;
    }

    public String getCauhinh() {
        return cauhinh;
    }

    public void setCauhinh(String cauhinh) {
        this.cauhinh = cauhinh;
    }

    public String getTrangThaiHdId() {
        return trangThaiHdId;
    }

    public void setTrangThaiHdId(String trangThaiHdId) {
        this.trangThaiHdId = trangThaiHdId;
    }

    public String getTrangThaiQlId() {
        return trangThaiQlId;
    }

    public void setTrangThaiQlId(String trangThaiQlId) {
        this.trangThaiQlId = trangThaiQlId;
    }

    public String getMaNode() {
        return maNode;
    }

    public void setMaNode(String maNode) {
        this.maNode = maNode;
    }

    public String getThietbiId() {
        return thietbiId;
    }

    public void setThietbiId(String thietbiId) {
        this.thietbiId = thietbiId;
    }

    public String getLoaiTramId() {
        return loaiTramId;
    }

    public void setLoaiTramId(String loaiTramId) {
        this.loaiTramId = loaiTramId;
    }

    public String getTenNguoiQL() {
        return tenNguoiQL;
    }

    public void setTenNguoiQL(String tenNguoiQL) {
        this.tenNguoiQL = tenNguoiQL;
    }

    public String getSoDTNgQL() {
        return soDTNgQL;
    }

    public void setSoDTNgQL(String soDTNgQL) {
        this.soDTNgQL = soDTNgQL;
    }

    public String getNgayBaoDuong() {
        return ngayBaoDuong;
    }

    public void setNgayBaoDuong(String ngayBaoDuong) {
        this.ngayBaoDuong = ngayBaoDuong;
    }

    public String getDonViThucHien() {
        return donViThucHien;
    }

    public void setDonViThucHien(String donViThucHien) {
        this.donViThucHien = donViThucHien;
    }

    public String getMaKiemDinh() {
        return maKiemDinh;
    }

    public void setMaKiemDinh(String maKiemDinh) {
        this.maKiemDinh = maKiemDinh;
    }

    public String getNgayHieuLuc() {
        return ngayHieuLuc;
    }

    public void setNgayHieuLuc(String ngayHieuLuc) {
        this.ngayHieuLuc = ngayHieuLuc;
    }

    public String getNgayHetHieuLuc() {
        return ngayHetHieuLuc;
    }

    public void setNgayHetHieuLuc(String ngayHetHieuLuc) {
        this.ngayHetHieuLuc = ngayHetHieuLuc;
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

    public String getTenBscRncQl() {
        return tenBscRncQl;
    }

    public void setTenBscRncQl(String tenBscRncQl) {
        this.tenBscRncQl = tenBscRncQl;
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

    public String getChungCSHT() {
        return chungCSHT;
    }

    public void setChungCSHT(String chungCSHT) {
        this.chungCSHT = chungCSHT;
    }

    public String getLoaiTramCSHT() {
        return loaiTramCSHT;
    }

    public void setLoaiTramCSHT(String loaiTramCSHT) {
        this.loaiTramCSHT = loaiTramCSHT;
    }

    public String getDocaoAnTen() {
        return docaoAnTen;
    }

    public void setDocaoAnTen(String docaoAnTen) {
        this.docaoAnTen = docaoAnTen;
    }

    public String getDoCaoNhaDatAnten() {
        return doCaoNhaDatAnten;
    }

    public void setDoCaoNhaDatAnten(String doCaoNhaDatAnten) {
        this.doCaoNhaDatAnten = doCaoNhaDatAnten;
    }

    public String getLoaiAnTenId() {
        return loaiAnTenId;
    }

    public void setLoaiAnTenId(String loaiAnTenId) {
        this.loaiAnTenId = loaiAnTenId;
    }

    public String getNgayHDTuNguon() {
        return ngayHDTuNguon;
    }

    public void setNgayHDTuNguon(String ngayHDTuNguon) {
        this.ngayHDTuNguon = ngayHDTuNguon;
    }

    public String getLoaiTuNguonId() {
        return loaiTuNguonId;
    }

    public void setLoaiTuNguonId(String loaiTuNguonId) {
        this.loaiTuNguonId = loaiTuNguonId;
    }

    public String getSoModuleTuNguon() {
        return soModuleTuNguon;
    }

    public void setSoModuleTuNguon(String soModuleTuNguon) {
        this.soModuleTuNguon = soModuleTuNguon;
    }

    public String getNgayHDMayNo() {
        return ngayHDMayNo;
    }

    public void setNgayHDMayNo(String ngayHDMayNo) {
        this.ngayHDMayNo = ngayHDMayNo;
    }

    public String getLoaiMayNoId() {
        return loaiMayNoId;
    }

    public void setLoaiMayNoId(String loaiMayNoId) {
        this.loaiMayNoId = loaiMayNoId;
    }

    public String getCongSuatMayNo() {
        return congSuatMayNo;
    }

    public void setCongSuatMayNo(String congSuatMayNo) {
        this.congSuatMayNo = congSuatMayNo;
    }

    public String getNgayHDAccu() {
        return ngayHDAccu;
    }

    public void setNgayHDAccu(String ngayHDAccu) {
        this.ngayHDAccu = ngayHDAccu;
    }

    public String getLoaiAcQuyId() {
        return loaiAcQuyId;
    }

    public void setLoaiAcQuyId(String loaiAcQuyId) {
        this.loaiAcQuyId = loaiAcQuyId;
    }

    public String getThoiGianHdSauMatDien() {
        return thoiGianHdSauMatDien;
    }

    public void setThoiGianHdSauMatDien(String thoiGianHdSauMatDien) {
        this.thoiGianHdSauMatDien = thoiGianHdSauMatDien;
    }

    public String getNgayBaoDuongAccu() {
        return ngayBaoDuongAccu;
    }

    public void setNgayBaoDuongAccu(String ngayBaoDuongAccu) {
        this.ngayBaoDuongAccu = ngayBaoDuongAccu;
    }

    public String getLoaiTruyenDanId() {
        return loaiTruyenDanId;
    }

    public void setLoaiTruyenDanId(String loaiTruyenDanId) {
        this.loaiTruyenDanId = loaiTruyenDanId;
    }

    public String getDuongLuongTruyenDan() {
        return duongLuongTruyenDan;
    }

    public void setDuongLuongTruyenDan(String duongLuongTruyenDan) {
        this.duongLuongTruyenDan = duongLuongTruyenDan;
    }

    public String getDienTroTiepDia() {
        return dienTroTiepDia;
    }

    public void setDienTroTiepDia(String dienTroTiepDia) {
        this.dienTroTiepDia = dienTroTiepDia;
    }

    public String getDungLuongAcc() {
        return dungLuongAcc;
    }

    public void setDungLuongAcc(String dungLuongAcc) {
        this.dungLuongAcc = dungLuongAcc;
    }

    public String getMayNoCoDinhDiDong() {
        return mayNoCoDinhDiDong;
    }

    public void setMayNoCoDinhDiDong(String mayNoCoDinhDiDong) {
        this.mayNoCoDinhDiDong = mayNoCoDinhDiDong;
    }
}
