/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author VNP
 */
public class Cell4gInfoBO implements Serializable {
    private Long nodeId;
    private Long cellType;
    private Date ngayHoatDong;
    private String hoanCanhRaDoi;
    private String tenCell;
    private Date ngayDangKy;
    private Date ngayKiemDuyet;
    private Date ngayCapPhep;
    private Long azimuth;
    private Long mechanicalTilt;
    private BigInteger electricalTilt;
    private Long totalTilt;
    private Long antennaType;
    private String antennaModel;
    private String antennaPattern;
    private Long antennaHigh;
    private Long noOfCarrier;
    private String bosterTma;
    private String specialCoverage;
    private String blackListFlag;
    private String lyDo;
    private String antennaGain;

    public Cell4gInfoBO() {
    }

    public Cell4gInfoBO(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getCellType() {
        return cellType;
    }

    public void setCellType(Long cellType) {
        this.cellType = cellType;
    }

    public Date getNgayHoatDong() {
        return ngayHoatDong;
    }

    public void setNgayHoatDong(Date ngayHoatDong) {
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

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public Date getNgayKiemDuyet() {
        return ngayKiemDuyet;
    }

    public void setNgayKiemDuyet(Date ngayKiemDuyet) {
        this.ngayKiemDuyet = ngayKiemDuyet;
    }

    public Date getNgayCapPhep() {
        return ngayCapPhep;
    }

    public void setNgayCapPhep(Date ngayCapPhep) {
        this.ngayCapPhep = ngayCapPhep;
    }

    public Long getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(Long azimuth) {
        this.azimuth = azimuth;
    }

    public Long getMechanicalTilt() {
        return mechanicalTilt;
    }

    public void setMechanicalTilt(Long mechanicalTilt) {
        this.mechanicalTilt = mechanicalTilt;
    }

    public BigInteger getElectricalTilt() {
        return electricalTilt;
    }

    public void setElectricalTilt(BigInteger electricalTilt) {
        this.electricalTilt = electricalTilt;
    }

    public Long getTotalTilt() {
        return totalTilt;
    }

    public void setTotalTilt(Long totalTilt) {
        this.totalTilt = totalTilt;
    }

    public Long getAntennaType() {
        return antennaType;
    }

    public void setAntennaType(Long antennaType) {
        this.antennaType = antennaType;
    }

    public String getAntennaModel() {
        return antennaModel;
    }

    public void setAntennaModel(String antennaModel) {
        this.antennaModel = antennaModel;
    }

    public String getAntennaPattern() {
        return antennaPattern;
    }

    public void setAntennaPattern(String antennaPattern) {
        this.antennaPattern = antennaPattern;
    }

    public Long getAntennaHigh() {
        return antennaHigh;
    }

    public void setAntennaHigh(Long antennaHigh) {
        this.antennaHigh = antennaHigh;
    }

    public Long getNoOfCarrier() {
        return noOfCarrier;
    }

    public void setNoOfCarrier(Long noOfCarrier) {
        this.noOfCarrier = noOfCarrier;
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

    public String getAntennaGain() {
        return antennaGain;
    }

    public void setAntennaGain(String antennaGain) {
        this.antennaGain = antennaGain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeId != null ? nodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cell4gInfoBO)) {
            return false;
        }
        Cell4gInfoBO other = (Cell4gInfoBO) object;
        if ((this.nodeId == null && other.nodeId != null) || (this.nodeId != null && !this.nodeId.equals(other.nodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vnpt.media.rims.bean.Cell4gInfoBO[ nodeId=" + nodeId + " ]";
    }
    
}
