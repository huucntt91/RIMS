/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

/**
 *
 * @author VNP
 */
public class BuildingBO {

    private Long id;

    private String code;

    private String address;

    private Long donViId;
    private String donViName;
    private Long tinhTpId;
    private String tinhName;
    private Long quanHuyenId;
    private String quanName;
    private Long phuongXaId;
    private String phuongName;
    private String lat;
    private String lon;
    
    private String userId;
    private String name;
    private String nhomCSHT;
    private String ngayHdCsht;
    public BuildingBO() {
      
    }

    public String getNgayHdCsht() {
        return ngayHdCsht;
    }

    public void setNgayHdCsht(String ngayHdCsht) {
        this.ngayHdCsht = ngayHdCsht;
    }

    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return the tinhTpId
     */
    public Long getTinhTpId() {
        return tinhTpId;
    }

    /**
     * @param tinhTpId the tinhTpId to set
     */
    public void setTinhTpId(Long tinhTpId) {
        this.tinhTpId = tinhTpId;
    }

    /**
     * @return the tinhName
     */
    public String getTinhName() {
        return tinhName;
    }

    /**
     * @param tinhName the tinhName to set
     */
    public void setTinhName(String tinhName) {
        this.tinhName = tinhName;
    }

    /**
     * @return the quanHuyenId
     */
    public Long getQuanHuyenId() {
        return quanHuyenId;
    }

    /**
     * @param quanHuyenId the quanHuyenId to set
     */
    public void setQuanHuyenId(Long quanHuyenId) {
        this.quanHuyenId = quanHuyenId;
    }

    /**
     * @return the quanName
     */
    public String getQuanName() {
        return quanName;
    }

    /**
     * @param quanName the quanName to set
     */
    public void setQuanName(String quanName) {
        this.quanName = quanName;
    }

    /**
     * @return the phuongXaId
     */
    public Long getPhuongXaId() {
        return phuongXaId;
    }

    /**
     * @param phuongXaId the phuongXaId to set
     */
    public void setPhuongXaId(Long phuongXaId) {
        this.phuongXaId = phuongXaId;
    }

    /**
     * @return the phuongName
     */
    public String getPhuongName() {
        return phuongName;
    }

    /**
     * @param phuongName the phuongName to set
     */
    public void setPhuongName(String phuongName) {
        this.phuongName = phuongName;
    }

    /**
     * @return the lat
     */
  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNhomCSHT() {
        return nhomCSHT;
    }

    public void setNhomCSHT(String nhomCSHT) {
        this.nhomCSHT = nhomCSHT;
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
    
   
}
