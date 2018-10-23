/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;

/**
 *
 * @author VNP
 */
public class NodeBO {

    /**
     * @return the tenTinhTp
     */
    public String getTenTinhTp() {
        return tenTinhTp;
    }

    /**
     * @param tenTinhTp the tenTinhTp to set
     */
    public void setTenTinhTp(String tenTinhTp) {
        this.tenTinhTp = tenTinhTp;
    }

    /**
     * @return the tramDAId
     */
    public Long getTramDAId() {
        return tramDAId;
    }

    /**
     * @param tramDAId the tramDAId to set
     */
    public void setTramDAId(Long tramDAId) {
        this.tramDAId = tramDAId;
    }

    /**
     * @return the tenNodeCha
     */
    public String getTenNodeCha() {
        return tenNodeCha;
    }

    /**
     * @param tenNodeCha the tenNodeCha to set
     */
    public void setTenNodeCha(String tenNodeCha) {
        this.tenNodeCha = tenNodeCha;
    }

    private Long id;
    
    private double longitude;
    private double latitude;
    
    

    public double getLongitude() {
        return longitude;
    }

    

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private String code;
    private String tenHeThong;
    private Long donViId;

    public String getTenHeThong() {
        return tenHeThong;
    }

    public void setTenHeThong(String tenHeThong) {
        this.tenHeThong = tenHeThong;
    }
    private String donViName;
    private Long thietBiId;
    private Long buildingId;
    private Long neTypeId;
    private Long loaiTramId;
    private Long trangThaiHDId;
    private String tenTrangThaiHD;
    private String tenTrangThaiQL;
    private Long trangThaiQLId;
    private Long nodeChaId;

    private String address;
    private String tenDonVi;
    private String codeBuilding;
    private String tenLoaiTram;
    private String tenNeType;
    private String tenThietBi;
    private String tenNgQLy;
    private String SDTQLy;
    private String tenNodeCha;
    private String maNodeCha;
    private String tenTinhTp;
    private String tinhTpId;
    private int status;
    private String note;

    private Long tramDAId;
    private String codeTramDA;

    private Long userInsert;
    private Long userUpdate;
    private Date insertDate;
    private Date updateDate;

    private String loaiTruyenDan;

    
    
    public String getTinhTpId() {
        return tinhTpId;
    }

    public void setTinhTpId(String tinhTpId) {
        this.tinhTpId = tinhTpId;
    }

    
    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public String getMaNodeCha() {
        return maNodeCha;
    }

    public void setMaNodeCha(String maNodeCha) {
        this.maNodeCha = maNodeCha;
    }


    public String getLoaiTruyenDan() {
        return loaiTruyenDan;
    }

    public void setLoaiTruyenDan(String loaiTruyenDan) {
        this.loaiTruyenDan = loaiTruyenDan;
    }
    public NodeBO() {

    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTrangThaiHD() {
        return tenTrangThaiHD;
    }

    public void setTenTrangThaiHD(String tenTrangThaiHD) {
        this.tenTrangThaiHD = tenTrangThaiHD;
    }

    public String getTenTrangThaiQL() {
        return tenTrangThaiQL;
    }

    public void setTenTrangThaiQL(String tenTrangThaiQL) {
        this.tenTrangThaiQL = tenTrangThaiQL;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the donViId
     */
    public Long getDonViId() {
        return donViId;
    }

    /**
     * @param donViId the donViId to set
     */
    public void setDonViId(Long donViId) {
        this.donViId = donViId;
    }

    /**
     * @return the donViName
     */
    public String getDonViName() {
        return donViName;
    }

    /**
     * @param donViName the donViName to set
     */
    public void setDonViName(String donViName) {
        this.donViName = donViName;
    }

    /**
     * @return the thietBiId
     */
    public Long getThietBiId() {
        return thietBiId;
    }

    /**
     * @param thietBiId the thietBiId to set
     */
    public void setThietBiId(Long thietBiId) {
        this.thietBiId = thietBiId;
    }

    /**
     * @return the buildingId
     */
    public Long getBuildingId() {
        return buildingId;
    }

    /**
     * @param buildingId the buildingId to set
     */
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * @return the neTypeId
     */
    public Long getNeTypeId() {
        return neTypeId;
    }

    /**
     * @param neTypeId the neTypeId to set
     */
    public void setNeTypeId(Long neTypeId) {
        this.neTypeId = neTypeId;
    }

    /**
     * @return the loaiTramId
     */
    public Long getLoaiTramId() {
        return loaiTramId;
    }

    /**
     * @param loaiTramId the loaiTramId to set
     */
    public void setLoaiTramId(Long loaiTramId) {
        this.loaiTramId = loaiTramId;
    }

    /**
     * @return the trangThaiHDId
     */
    public Long getTrangThaiHDId() {
        return trangThaiHDId;
    }

    /**
     * @param trangThaiHDId the trangThaiHDId to set
     */
    public void setTrangThaiHDId(Long trangThaiHDId) {
        this.trangThaiHDId = trangThaiHDId;
    }

    /**
     * @return the trangThaiQLId
     */
    public Long getTrangThaiQLId() {
        return trangThaiQLId;
    }

    /**
     * @param trangThaiQLId the trangThaiQLId to set
     */
    public void setTrangThaiQLId(Long trangThaiQLId) {
        this.trangThaiQLId = trangThaiQLId;
    }

    /**
     * @return the nodeChaId
     */
    public Long getNodeChaId() {
        return nodeChaId;
    }

    /**
     * @param nodeChaId the nodeChaId to set
     */
    public void setNodeChaId(Long nodeChaId) {
        this.nodeChaId = nodeChaId;
    }

    /**
     * @return the tenNgQLy
     */
    public String getTenNgQLy() {
        return tenNgQLy;
    }

    /**
     * @param tenNgQLy the tenNgQLy to set
     */
    public void setTenNgQLy(String tenNgQLy) {
        this.tenNgQLy = tenNgQLy;
    }

    /**
     * @return the SDTQLy
     */
    public String getSDTQLy() {
        return SDTQLy;
    }

    /**
     * @param SDTQLy the SDTQLy to set
     */
    public void setSDTQLy(String SDTQLy) {
        this.SDTQLy = SDTQLy;
    }

//    /**
//     * @return the tenDonVi
//     */
//    public String getTenDonVi() {
//        return tenDonVi;
//    }
//
//    /**
//     * @param tenDonVi the tenDonVi to set
//     */
//    public void setTenDonVi(String tenDonVi) {
//        this.tenDonVi = tenDonVi;
//    }

    /**
     * @return the codeBuilding
     */
    public String getCodeBuilding() {
        return codeBuilding;
    }

    /**
     * @param codeBuilding the codeBuilding to set
     */
    public void setCodeBuilding(String codeBuilding) {
        this.codeBuilding = codeBuilding;
    }

    /**
     * @return the tenLoaiTram
     */
    public String getTenLoaiTram() {
        return tenLoaiTram;
    }

    /**
     * @param tenLoaiTram the tenLoaiTram to set
     */
    public void setTenLoaiTram(String tenLoaiTram) {
        this.tenLoaiTram = tenLoaiTram;
    }

    /**
     * @return the tenNeType
     */
    public String getTenNeType() {
        return tenNeType;
    }

    /**
     * @param tenNeType the tenNeType to set
     */
    public void setTenNeType(String tenNeType) {
        this.tenNeType = tenNeType;
    }

    /**
     * @return the tenThietBi
     */
    public String getTenThietBi() {
        return tenThietBi;
    }

    /**
     * @param tenThietBi the tenThietBi to set
     */
    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the codeTramDA
     */
    public String getCodeTramDA() {
        return codeTramDA;
    }

    /**
     * @param codeTramDA the codeTramDA to set
     */
    public void setCodeTramDA(String codeTramDA) {
        this.codeTramDA = codeTramDA;
    }

    public Long getUserInsert() {
        return userInsert;
    }

    public void setUserInsert(Long userInsert) {
        this.userInsert = userInsert;
    }

    public Long getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Long userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

   

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
