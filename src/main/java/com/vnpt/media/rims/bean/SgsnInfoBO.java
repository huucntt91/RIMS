/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author VNP
 */
public class SgsnInfoBO extends NodeBO {

    private Long nodeId;
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHoatDong;
    private String neStatus;
    private String ipNe;
    private String opc;
    private String softVersion;
    private String hwFlatForm;
    private String vender;
    private String thuocDuAn;
    private String address;
    private String license;
    private String tenCard;
    private String seria;
    private String cardStatus;
    private Integer cardSL;
    private String cardVersion;
    private String file;
    private MultipartFile fileAttack;
    private  String tenHeThong;
    private MultipartFile fileAttackConfig;
    private String fileConfig;
    
    public String getTenHeThong() {
        return tenHeThong;
    }

    public void setTenHeThong(String tenHeThong) {
        this.tenHeThong = tenHeThong;
    }

    public MultipartFile getFileAttackConfig() {
        return fileAttackConfig;
    }

    public void setFileAttackConfig(MultipartFile fileAttackConfig) {
        this.fileAttackConfig = fileAttackConfig;
    }

    public String getFileConfig() {
        return fileConfig;
    }

    public void setFileConfig(String fileConfig) {
        this.fileConfig = fileConfig;
    }
    
    public SgsnInfoBO() {
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNgayHoatDong() {
        return ngayHoatDong;
    }

    public void setNgayHoatDong(Date ngayHoatDong) {
        this.ngayHoatDong = ngayHoatDong;
    }

    public String getNeStatus() {
        return neStatus;
    }

    public void setNeStatus(String neStatus) {
        this.neStatus = neStatus;
    }

    public String getIpNe() {
        return ipNe;
    }

    public void setIpNe(String ipNe) {
        this.ipNe = ipNe;
    }

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getHwFlatForm() {
        return hwFlatForm;
    }

    public void setHwFlatForm(String hwFlatForm) {
        this.hwFlatForm = hwFlatForm;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public String getThuocDuAn() {
        return thuocDuAn;
    }

    public void setThuocDuAn(String thuocDuAn) {
        this.thuocDuAn = thuocDuAn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTenCard() {
        return tenCard;
    }

    public void setTenCard(String tenCard) {
        this.tenCard = tenCard;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Integer getCardSL() {
        return cardSL;
    }

    public void setCardSL(Integer cardSL) {
        this.cardSL = cardSL;
    }

    public String getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(String cardVersion) {
        this.cardVersion = cardVersion;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public MultipartFile getFileAttack() {
        return fileAttack;
    }

    public void setFileAttack(MultipartFile fileAttack) {
        this.fileAttack = fileAttack;
    }
    

}
