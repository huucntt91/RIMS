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
public class CsCoreBO extends NodeBO {

    private Long nodeId;
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHoatDong;

    private String softVersion;

    private String seria;

    private String tenHeThong;
    private String hoanCanhRaDoi;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDangKy;
    private String type;
    private String opc;    
    private String opc1;
    private String numberalSystem;
    private String numberalSystem1;
    private String userName;
    private Date createTime;
    private String action;
    private String ipClient;

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

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getTenHeThong() {
        return tenHeThong;
    }

    public void setTenHeThong(String tenHeThong) {
        this.tenHeThong = tenHeThong;
    }

    public String getHoanCanhRaDoi() {
        return hoanCanhRaDoi;
    }

    public void setHoanCanhRaDoi(String hoanCanhRaDoi) {
        this.hoanCanhRaDoi = hoanCanhRaDoi;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    public String getOpc1() {
        return opc1;
    }

    public void setOpc1(String opc1) {
        this.opc1 = opc1;
    }

    public String getNumberalSystem() {
        return numberalSystem;
    }

    public void setNumberalSystem(String numberalSystem) {
        this.numberalSystem = numberalSystem;
    }

    public String getNumberalSystem1() {
        return numberalSystem1;
    }

    public void setNumberalSystem1(String numberalSystem1) {
        this.numberalSystem1 = numberalSystem1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    
    
}
