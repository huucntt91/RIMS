/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author VNP
 */
public class CellInfoBO extends NodeBO {

    private long idInfo;
    private String name;
    private long nodeId;
    private String hoanCanhRaDoi;
    private Long omcId;
    private int cellType;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date ngayDangKy;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date ngayKiemDuyet;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date ngayCapPhep;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date ngayHoatDong;
    private Float lat;
    private Float lon;
    private Long azimuth;
    private Long mechanitalTilt;
    private Long totalTilt;
    private Long antennaType;
    private Long antennaHigh;
    private Long antennaGain;
    private Long noOfCarrier;
    private String bosterTma;
    private String cpichPower;
    private String totalPower;
    private String specialCoverage;
    private String blackListFlag;
    private String lyDo;
    private String tenTrenHeThong;

    public String getTenTrenHeThong() {
        return tenTrenHeThong;
    }

    public void setTenTrenHeThong(String tenTrenHeThong) {
        this.tenTrenHeThong = tenTrenHeThong;
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
     * @return the nodeId
     */
    public long getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
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
     * @return the cellType
     */
    public int getCellType() {
        return cellType;
    }

    /**
     * @param cellType the cellType to set
     */
    public void setCellType(int cellType) {
        this.cellType = cellType;
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
     * @return the azimuth
     */
    public Long getAzimuth() {
        return azimuth;
    }

    /**
     * @param azimuth the azimuth to set
     */
    public void setAzimuth(Long azimuth) {
        this.azimuth = azimuth;
    }

    /**
     * @return the mechanitalTilt
     */
    public Long getMechanitalTilt() {
        return mechanitalTilt;
    }

    /**
     * @param mechanitalTilt the mechanitalTilt to set
     */
    public void setMechanitalTilt(Long mechanitalTilt) {
        this.mechanitalTilt = mechanitalTilt;
    }

    /**
     * @return the totalTilt
     */
    public Long getTotalTilt() {
        return totalTilt;
    }

    /**
     * @param totalTilt the totalTilt to set
     */
    public void setTotalTilt(Long totalTilt) {
        this.totalTilt = totalTilt;
    }

    /**
     * @return the antennaType
     */
    public Long getAntennaType() {
        return antennaType;
    }

    /**
     * @param antennaType the antennaType to set
     */
    public void setAntennaType(Long antennaType) {
        this.antennaType = antennaType;
    }

    /**
     * @return the antennaHigh
     */
    public Long getAntennaHigh() {
        return antennaHigh;
    }

    /**
     * @param antennaHigh the antennaHigh to set
     */
    public void setAntennaHigh(Long antennaHigh) {
        this.antennaHigh = antennaHigh;
    }

    /**
     * @return the antennaGain
     */
    public Long getAntennaGain() {
        return antennaGain;
    }

    /**
     * @param antennaGain the antennaGain to set
     */
    public void setAntennaGain(Long antennaGain) {
        this.antennaGain = antennaGain;
    }

    /**
     * @return the noOfCarrier
     */
    public Long getNoOfCarrier() {
        return noOfCarrier;
    }

    /**
     * @param noOfCarrier the noOfCarrier to set
     */
    public void setNoOfCarrier(Long noOfCarrier) {
        this.noOfCarrier = noOfCarrier;
    }

    /**
     * @return the bosterTma
     */
    public String getBosterTma() {
        return bosterTma;
    }

    /**
     * @param bosterTma the bosterTma to set
     */
    public void setBosterTma(String bosterTma) {
        this.bosterTma = bosterTma;
    }

    /**
     * @return the cpichPower
     */
    public String getCpichPower() {
        return cpichPower;
    }

    /**
     * @param cpichPower the cpichPower to set
     */
    public void setCpichPower(String cpichPower) {
        this.cpichPower = cpichPower;
    }

    /**
     * @return the totalPower
     */
    public String getTotalPower() {
        return totalPower;
    }

    /**
     * @param totalPower the totalPower to set
     */
    public void setTotalPower(String totalPower) {
        this.totalPower = totalPower;
    }

    /**
     * @return the specialCoverage
     */
    public String getSpecialCoverage() {
        return specialCoverage;
    }

    /**
     * @param specialCoverage the specialCoverage to set
     */
    public void setSpecialCoverage(String specialCoverage) {
        this.specialCoverage = specialCoverage;
    }

    /**
     * @return the blackListFlag
     */
    public String getBlackListFlag() {
        return blackListFlag;
    }

    /**
     * @param blackListFlag the blackListFlag to set
     */
    public void setBlackListFlag(String blackListFlag) {
        this.blackListFlag = blackListFlag;
    }

    /**
     * @return the lyDo
     */
    public String getLyDo() {
        return lyDo;
    }

    /**
     * @param lyDo the lyDo to set
     */
    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
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
