package com.vnpt.media.rims.bean;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class BTSInfoBO extends NodeBO {

    private long idInfo;
    private String name;
    private String hoanCanhRaDoi;
    private Long omcId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDangKy;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayKiemDuyet;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayCapPhep;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHoatDong;
    private String lat;
    private String lon;
    private String cauHinh;
    private Long cauHinhPortId;
    private int cosite2G3GType;
    private String maCosite;

    private String tenTrenHeThong;
    private String tenBSCRNC;
    private String tenBSCRNCQL;
    private String mSCMSS;
    private String sGSN;
    private String dCHSPDA42M;
    private String filterUser;
    private String frequencyBand;
    private Long bangTanId;
    private String enodebId;
    private String codeProvince;

    private String tenQuan;
    private String tenPhuong;
    private String trangThaiMayNo;

    List<CellInfoBO> cell;

    public Long getBangTanId() {
        return bangTanId;
    }

    public List<CellInfoBO> getCell() {
        return cell;
    }

    public void setCell(List<CellInfoBO> cell) {
        this.cell = cell;
    }

    public void setBangTanId(Long bangTanId) {
        this.bangTanId = bangTanId;
    }

    /**
     * @return the idInfo
     */
    public long getIdInfo() {
        return idInfo;
    }

    /**
     * @param idInfo the idInfo to set
     */
    public void setIdInfo(long idInfo) {
        this.idInfo = idInfo;
    }

    public Long getCauHinhPortId() {
        return cauHinhPortId;
    }

    public void setCauHinhPortId(Long cauHinhPortId) {
        this.cauHinhPortId = cauHinhPortId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hoanCanhRaDoi
     */
    public String getHoanCanhRaDoi() {
        return hoanCanhRaDoi;
    }

    /**
     * @param hoanCanhRaDoi the hoanCanhRaDoi to set
     */
    public void setHoanCanhRaDoi(String hoanCanhRaDoi) {
        this.hoanCanhRaDoi = hoanCanhRaDoi;
    }

    /**
     * @return the omcId
     */
    public Long getOmcId() {
        return omcId;
    }

    /**
     * @param omcId the omcId to set
     */
    public void setOmcId(Long omcId) {
        this.omcId = omcId;
    }

    /**
     * @return the ngayDangKy
     */
    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    /**
     * @param ngayDangKy the ngayDangKy to set
     */
    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    /**
     * @return the ngayKiemDuyet
     */
    public Date getNgayKiemDuyet() {
        return ngayKiemDuyet;
    }

    /**
     * @param ngayKiemDuyet the ngayKiemDuyet to set
     */
    public void setNgayKiemDuyet(Date ngayKiemDuyet) {
        this.ngayKiemDuyet = ngayKiemDuyet;
    }

    /**
     * @return the ngayCapPhep
     */
    public Date getNgayCapPhep() {
        return ngayCapPhep;
    }

    /**
     * @param ngayCapPhep the ngayCapPhep to set
     */
    public void setNgayCapPhep(Date ngayCapPhep) {
        this.ngayCapPhep = ngayCapPhep;
    }

    /**
     * @return the ngayHoatDong
     */
    public Date getNgayHoatDong() {
        return ngayHoatDong;
    }

    /**
     * @param ngayHoatDong the ngayHoatDong to set
     */
    public void setNgayHoatDong(Date ngayHoatDong) {
        this.ngayHoatDong = ngayHoatDong;
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

    /**
     * @return the cauHinh
     */
    public String getCauHinh() {
        return cauHinh;
    }

    /**
     * @param cauHinh the cauHinh to set
     */
    public void setCauHinh(String cauHinh) {
        this.cauHinh = cauHinh;
    }

    /**
     * @return the cosite2G3GType
     */
    public int getCosite2G3GType() {
        return cosite2G3GType;
    }

    /**
     * @param cosite2G3GType the cosite2G3GType to set
     */
    public void setCosite2G3GType(int cosite2G3GType) {
        this.cosite2G3GType = cosite2G3GType;
    }

    /**
     * @return the maCosite
     */
    public String getMaCosite() {
        return maCosite;
    }

    /**
     * @param maCosite the maCosite to set
     */
    public void setMaCosite(String maCosite) {
        this.maCosite = maCosite;
    }

    /**
     * @return the tenTrenHeThong
     */
    public String getTenTrenHeThong() {
        return tenTrenHeThong;
    }

    /**
     * @param tenTrenHeThong the tenTrenHeThong to set
     */
    public void setTenTrenHeThong(String tenTrenHeThong) {
        this.tenTrenHeThong = tenTrenHeThong;
    }

    /**
     * @return the tenBSCRNC
     */
    public String getTenBSCRNC() {
        return tenBSCRNC;
    }

    /**
     * @param tenBSCRNC the tenBSCRNC to set
     */
    public void setTenBSCRNC(String tenBSCRNC) {
        this.tenBSCRNC = tenBSCRNC;
    }

    /**
     * @return the tenBSCRNCQL
     */
    public String getTenBSCRNCQL() {
        return tenBSCRNCQL;
    }

    /**
     * @param tenBSCRNCQL the tenBSCRNCQL to set
     */
    public void setTenBSCRNCQL(String tenBSCRNCQL) {
        this.tenBSCRNCQL = tenBSCRNCQL;
    }

    /**
     * @return the mSCMSS
     */
    public String getmSCMSS() {
        return mSCMSS;
    }

    /**
     * @param mSCMSS the mSCMSS to set
     */
    public void setmSCMSS(String mSCMSS) {
        this.mSCMSS = mSCMSS;
    }

    /**
     * @return the sGSN
     */
    public String getsGSN() {
        return sGSN;
    }

    /**
     * @param sGSN the sGSN to set
     */
    public void setsGSN(String sGSN) {
        this.sGSN = sGSN;
    }

    /**
     * @return the dCHSPDA42M
     */
    public String getdCHSPDA42M() {
        return dCHSPDA42M;
    }

    /**
     * @param dCHSPDA42M the dCHSPDA42M to set
     */
    public void setdCHSPDA42M(String dCHSPDA42M) {
        this.dCHSPDA42M = dCHSPDA42M;
    }

    /**
     * @return the filterUser
     */
    public String getFilterUser() {
        return filterUser;
    }

    /**
     * @param filterUser the filterUser to set
     */
    public void setFilterUser(String filterUser) {
        this.filterUser = filterUser;
    }

    /**
     * @return the frequencyBand
     */
    public String getFrequencyBand() {
        return frequencyBand;
    }

    /**
     * @param frequencyBand the frequencyBand to set
     */
    public void setFrequencyBand(String frequencyBand) {
        this.frequencyBand = frequencyBand;
    }

    public String getEnodebId() {
        return enodebId;
    }

    public void setEnodebId(String enodebId) {
        this.enodebId = enodebId;
    }

    public String getCodeProvince() {
        return codeProvince;
    }

    public void setCodeProvince(String codeProvince) {
        this.codeProvince = codeProvince;
    }

    public String getTenQuan() {
        return tenQuan;
    }

    public void setTenQuan(String tenQuan) {
        this.tenQuan = tenQuan;
    }

    public String getTenPhuong() {
        return tenPhuong;
    }

    public void setTenPhuong(String tenPhuong) {
        this.tenPhuong = tenPhuong;
    }

    public String getTrangThaiMayNo() {
        return trangThaiMayNo;
    }

    public void setTrangThaiMayNo(String trangThaiMayNo) {
        this.trangThaiMayNo = trangThaiMayNo;
    }

}
