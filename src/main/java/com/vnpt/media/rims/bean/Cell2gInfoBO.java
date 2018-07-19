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
public class Cell2gInfoBO implements Serializable {

    private Long nodeId;
    private Long cellType;
    private Date ngayHoatDong;
    private String hoanCanhRaDoi;
    private String tenCell;
    private String vnpCode;
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
    private String bosterTma;
    private String specialCoverage;
    private String antennaGain;

    public Cell2gInfoBO() {
    }

    public Cell2gInfoBO(Long nodeId) {
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

    public String getVnpCode() {
        return vnpCode;
    }

    public void setVnpCode(String vnpCode) {
        this.vnpCode = vnpCode;
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


    public String getAntennaGain() {
        return antennaGain;
    }

    public void setAntennaGain(String antennaGain) {
        this.antennaGain = antennaGain;
    }  
}
