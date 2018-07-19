package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class BSCRNCInfoBO extends NodeBO {

    private long idInfo;
    private String name;
    private String hoanCanhRaDoi;
    private Long omcId;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date ngayDangKy;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date ngayKiemDuyet;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date ngayCapPhep;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date ngayHoatDong;
    private Float lat;
    private Float lon;
    private String typeBSCRNC;

    //omc_bsc_rnc_info
    private Long nodeId;
    private String tenTrenHT;
    private String mscMss;
    private String sgsn;
    private String opc;
    private String numeralSystem;

    /**
     * @return the idInfo
     */
    public long getIdInfo() {
        return idInfo;
    }

    public String getNumeralSystem() {
        return numeralSystem;
    }

    public void setNumeralSystem(String numeralSystem) {
        this.numeralSystem = numeralSystem;
    }

    /**
     * @param idInfo the idInfo to set
     */
    public void setIdInfo(long idInfo) {
        this.idInfo = idInfo;
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

    /**
     * @return the lat
     */
    public Float getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(Float lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public Float getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(Float lon) {
        this.lon = lon;
    }

    /**
     * @return the typeBSCRNC
     */
    public String getTypeBSCRNC() {
        return typeBSCRNC;
    }

    /**
     * @param typeBSCRNC the typeBSCRNC to set
     */
    public void setTypeBSCRNC(String typeBSCRNC) {
        this.typeBSCRNC = typeBSCRNC;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getTenTrenHT() {
        return tenTrenHT;
    }

    public void setTenTrenHT(String tenTrenHT) {
        this.tenTrenHT = tenTrenHT;
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

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

}
