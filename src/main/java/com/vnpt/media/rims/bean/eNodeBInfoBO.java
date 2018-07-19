package com.vnpt.media.rims.bean;

import java.sql.Timestamp;

public class eNodeBInfoBO extends NodeBO {

    private long idInfo;
    private String name;
    private String hoanCanhRaDoi;
    private Long omcId;

    private Timestamp ngayDangKy;
    private Timestamp ngayKiemDuyet;
    private Timestamp ngayCapPhep;
    private Timestamp ngayHoatDong;
    private Float lat;
    private Float lon;
    private String cauHinh;
    private int cosite2G3GType;
    private String maCosite;

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
     * @return the ngayDangKy
     */
    public Timestamp getNgayDangKy() {
        return ngayDangKy;
    }

    /**
     * @param ngayDangKy the ngayDangKy to set
     */
    public void setNgayDangKy(Timestamp ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    /**
     * @return the ngayKiemDuyet
     */
    public Timestamp getNgayKiemDuyet() {
        return ngayKiemDuyet;
    }

    /**
     * @param ngayKiemDuyet the ngayKiemDuyet to set
     */
    public void setNgayKiemDuyet(Timestamp ngayKiemDuyet) {
        this.ngayKiemDuyet = ngayKiemDuyet;
    }

    /**
     * @return the ngayCapPhep
     */
    public Timestamp getNgayCapPhep() {
        return ngayCapPhep;
    }

    /**
     * @param ngayCapPhep the ngayCapPhep to set
     */
    public void setNgayCapPhep(Timestamp ngayCapPhep) {
        this.ngayCapPhep = ngayCapPhep;
    }

    /**
     * @return the ngayHoatDong
     */
    public Timestamp getNgayHoatDong() {
        return ngayHoatDong;
    }

    /**
     * @param ngayHoatDong the ngayHoatDong to set
     */
    public void setNgayHoatDong(Timestamp ngayHoatDong) {
        this.ngayHoatDong = ngayHoatDong;
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

}
