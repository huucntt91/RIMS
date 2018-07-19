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
public class Cell2GReportBO {
//select ngay_hoat_dong, hoan_canh_ra_doi, ten_cell,ngay_dang_ky,ngay_kiem_duyet, ngay_cap_phep 
//,c.LATITUDE,c.LONGITUDE,c.AZIMUTH,c.MECHANICAL_TILT,c.TOTAL_TILT,c.ANTENNA_HIGH,c.ANTENNA_GAIN,c.ANTENNA_TYPE
//,c.NO_OF_CARRIER,c.CPICH_POWER,c.TOTAL_POWER,c.BOSTER_TMA,c.SPECIAL_COVERAGE,c.BLACK_LIST_FLAG,c.LY_DO
//,o.ten_tren_he_thong,o.lac,o.ci,o.TEN_BSC_RNC, o.code,o.DC_HSDPA_42M,o.FREQUENCY_BAND
//,n.TRANG_THAI_HD_ID,n.TRANG_THAI_QL_ID,n.THIET_BI_ID,n.LOAI_TRAM_ID
//from cell_info c
//inner join omc_cell_info o on c.node_id = o.node_id
//inner join node n on c.node_id =n.node_id

    private Long nodeId;
    private String locationNumber;
    private String tinh;
    private String quan;
    private String xa;
    private String diachi;
    private String ngayHoatDong;
    private String hoanCanhRaDoi;
    private String tenCell;
    private String ngayDangKy;
    private String ngayKiemDuyet;
    private String ngayCapPhep;
    private String latitude;
    private String longitude;
    private String azimuth;
    private String mechanical;
    private String totalTilt;
    private String antennaHigh;
    private String antennaGain;
    private String antennaType;
    private String noOfCarrier;
    private String cpichPower;
    private String totalPower;
    private String bosterTma;
    private String specialCoverage;
    private String blackListFlag;
    private String lyDo;
    private String tenTrenHeThong;
    private String lac;
    private String ci;
    private String maBTS;
    private String tenBscRnc;
    private String code;
    private String dcHsdpa42m;
    private String frequencyBand;
    private String trangThaiHdId;
    private String trangThaiQlId;
    private String thietBiId;
    private String loaiTramId;
    private String tenAnten;
    private String tenThietBi;
    private String tenLoaiTram;
    private String trangThaiHd;
    private String trangThaiQl;

    private String userName;
    private String SDT_NG_QLY;

    private Date createTime;
    private String action;
    private String ipClient;
    private String soTrx;

    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String IpClient) {
        this.ipClient = IpClient;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getSDT_NG_QLY() {
        return SDT_NG_QLY;
    }

    public void setSDT_NG_QLY(String SDT_NG_QLY) {
        this.SDT_NG_QLY = SDT_NG_QLY;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getTrangThaiHd() {
        return trangThaiHd;
    }

    public void setTrangThaiHd(String trangThaiHd) {
        this.trangThaiHd = trangThaiHd;
    }

    public String getTrangThaiQl() {
        return trangThaiQl;
    }

    public void setTrangThaiQl(String trangThaiQl) {
        this.trangThaiQl = trangThaiQl;
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

    public Cell2GReportBO() {
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

    public String getTenCell() {
        return tenCell;
    }

    public void setTenCell(String tenCell) {
        this.tenCell = tenCell;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
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

    public String getNoOfCarrier() {
        return noOfCarrier;
    }

    public void setNoOfCarrier(String noOfCarrier) {
        this.noOfCarrier = noOfCarrier;
    }

    public String getCpichPower() {
        return cpichPower;
    }

    public void setCpichPower(String cpichPower) {
        this.cpichPower = cpichPower;
    }

    public String getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(String totalPower) {
        this.totalPower = totalPower;
    }

    public String getBosterTma() {
        return bosterTma;
    }

    public void setBosterTma(String bosterTma) {
        this.bosterTma = bosterTma;
    }

    public String getSpecialCoverage() {
        return specialCoverage;
    }

    public void setSpecialCoverage(String specialCoverage) {
        this.specialCoverage = specialCoverage;
    }

    public String getBlackListFlag() {
        return blackListFlag;
    }

    public void setBlackListFlag(String blackListFlag) {
        this.blackListFlag = blackListFlag;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
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

    public String getTenBscRnc() {
        return tenBscRnc;
    }

    public void setTenBscRnc(String tenBscRnc) {
        this.tenBscRnc = tenBscRnc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDcHsdpa42m() {
        return dcHsdpa42m;
    }

    public void setDcHsdpa42m(String dcHsdpa42m) {
        this.dcHsdpa42m = dcHsdpa42m;
    }

    public String getFrequencyBand() {
        return frequencyBand;
    }

    public void setFrequencyBand(String frequencyBand) {
        this.frequencyBand = frequencyBand;
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

    public String getThietBiId() {
        return thietBiId;
    }

    public void setThietBiId(String thietBiId) {
        this.thietBiId = thietBiId;
    }

    public String getLoaiTramId() {
        return loaiTramId;
    }

    public void setLoaiTramId(String loaiTramId) {
        this.loaiTramId = loaiTramId;
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

    public String getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public String getMechanical() {
        return mechanical;
    }

    public void setMechanical(String mechanical) {
        this.mechanical = mechanical;
    }

    public String getTotalTilt() {
        return totalTilt;
    }

    public void setTotalTilt(String totalTilt) {
        this.totalTilt = totalTilt;
    }

    public String getAntennaHigh() {
        return antennaHigh;
    }

    public void setAntennaHigh(String antennaHigh) {
        this.antennaHigh = antennaHigh;
    }

    public String getAntennaGain() {
        return antennaGain;
    }

    public void setAntennaGain(String antennaGain) {
        this.antennaGain = antennaGain;
    }

    public String getAntennaType() {
        return antennaType;
    }

    public void setAntennaType(String antennaType) {
        this.antennaType = antennaType;
    }

    public String getMaBTS() {
        return maBTS;
    }

    public void setMaBTS(String maBTS) {
        this.maBTS = maBTS;
    }

    public String getTenAnten() {
        return tenAnten;
    }

    public void setTenAnten(String tenAnten) {
        this.tenAnten = tenAnten;
    }

    public String getSoTrx() {
        return soTrx;
    }

    public void setSoTrx(String soTrx) {
        this.soTrx = soTrx;
    }

}
