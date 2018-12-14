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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author VNP
 */
public class OmcCell2gInfoBO extends NodeBO implements Serializable {

    private Long nodeId;
    private String tenTrenHeThong;
    private Long lac;
    private Long ci;
    private Long bangTanId;
    private String tenBangTan;
    private Long bcch;
    private String bsic;
    private String tch;
    private String trxConfig;

///
//    cellinfo
//     private Long nodeId;
    private Long cellType;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHoatDong;
    private String hoanCanhRaDoi;
    private String tenCell;
    private String vnpCode;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDangKy;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayKiemDuyet;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayCapPhep;
    private Long azimuth;
    private Long mechanicalTilt;
    private Long electricalTilt;
    private Long totalTilt;
    private Long antennaType;
    private String antennaName;
    private String antennaModel;
    private String antennaPattern;
    private Long antennaHigh;
    private String bosterTma;
    private String specialCoverage;
    private String antennaGain;
    private String listCellGroupName;
    private String listCellGroupId;
    private String tenNodeCha;
    
    private String lat;
    private String lon;
    
    String bscRncCode;

    public String getBscRncCode() {
        return bscRncCode;
    }

    public void setBscRncCode(String bscRncCode) {
        this.bscRncCode = bscRncCode;
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
    

    public String getTenNodeCha() {
        return tenNodeCha;
    }

    public void setTenNodeCha(String tenNodeCha) {
        this.tenNodeCha = tenNodeCha;
    }

    public String getAntennaName() {
        return antennaName;
    }

    public void setAntennaName(String antennaName) {
        this.antennaName = antennaName;
    }
    
    

    public OmcCell2gInfoBO() {
    }

    public OmcCell2gInfoBO(NodeBO temp) {

        this.setId(temp.getId());
        this.setCode(temp.getCode());
        this.setTenNgQLy(temp.getTenNgQLy());
        this.setSDTQLy(temp.getSDTQLy());
        this.setDonViName(temp.getDonViName());
        this.setThietBiId(temp.getThietBiId());
        this.setTenThietBi(temp.getTenThietBi());
        this.setCodeBuilding(temp.getCodeBuilding());
        this.setTenNeType(temp.getTenNeType());
        this.setNeTypeId(temp.getNeTypeId());
        this.setLoaiTramId(temp.getLoaiTramId());
        this.setTenLoaiTram(temp.getTenLoaiTram());
        this.setTenTrangThaiHD(temp.getTenTrangThaiHD());
        this.setTenTrangThaiQL(temp.getTenTrangThaiQL());
        this.setNote(temp.getNote());
        this.setMaNodeCha(temp.getMaNodeCha());
        this.setNodeChaId(temp.getNodeChaId());
        this.setCodeTramDA(temp.getCodeTramDA());
        this.setUserInsert(temp.getUserInsert());
        this.setStatus(temp.getStatus());
        this.setBuildingId(temp.getBuildingId());
    }

    public String getListCellGroupId() {
        return listCellGroupId;
    }

    public void setListCellGroupId(String listCellGroupId) {
        this.listCellGroupId = listCellGroupId;
    }

    public String getTenBangTan() {
        return tenBangTan;
    }

    public void setTenBangTan(String tenBangTan) {
        this.tenBangTan = tenBangTan;
    }

    public OmcCell2gInfoBO(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getTenTrenHeThong() {
        return tenTrenHeThong;
    }

    public void setTenTrenHeThong(String tenTrenHeThong) {
        this.tenTrenHeThong = tenTrenHeThong;
    }

    public Long getLac() {
        return lac;
    }

    public void setLac(Long lac) {
        this.lac = lac;
    }

    public Long getCi() {
        return ci;
    }

    public void setCi(Long ci) {
        this.ci = ci;
    }

    public Long getBangTanId() {
        return bangTanId;
    }

    public void setBangTanId(Long bangTanId) {
        this.bangTanId = bangTanId;
    }

    public Long getBcch() {
        return bcch;
    }

    public void setBcch(Long bcch) {
        this.bcch = bcch;
    }

    public String getBsic() {
        return bsic;
    }

    public void setBsic(String bsic) {
        this.bsic = bsic;
    }

    public String getTch() {
        return tch;
    }

    public void setTch(String tch) {
        this.tch = tch;
    }

    public String getTrxConfig() {
        return trxConfig;
    }

    public void setTrxConfig(String trxConfig) {
        this.trxConfig = trxConfig;
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
        if (!(object instanceof OmcCell2gInfoBO)) {
            return false;
        }
        OmcCell2gInfoBO other = (OmcCell2gInfoBO) object;
        if ((this.nodeId == null && other.nodeId != null) || (this.nodeId != null && !this.nodeId.equals(other.nodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vnpt.media.rims.bean.OmcCell2gInfoBO[ nodeId=" + nodeId + " ]";
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

    public Long getElectricalTilt() {
        return electricalTilt;
    }

    public void setElectricalTilt(Long electricalTilt) {
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

    public String getListCellGroupName() {
        return listCellGroupName;
    }

    public void setListCellGroupName(String listCellGroupName) {
        this.listCellGroupName = listCellGroupName;
    }

}
