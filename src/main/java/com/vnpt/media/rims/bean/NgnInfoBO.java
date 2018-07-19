/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author VNP
 */
public class NgnInfoBO extends NodeBO{
    private Long nodeId;
    private String name;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date ngayHoatDong;
    private String hoanCanhRaDoi;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date ngayDangKy;
    private String type;
//oss  info
    private String opc;    
    private String opc1;
    private String numberalSystem;
    private String numberalSystem1;
    public NgnInfoBO() {
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
    
}
