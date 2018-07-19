/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author VNP
 */
public class Cell3gRegForm implements Serializable {

    private Long nodeId;
    private String btsCode;
    private Long nodeChaId;
    private String tenCell;
    private String hoanCanhRaDoi;
    private Date ngayHoatDong;
    private String tenTrenHeThong;
    private Long lac;
    private Long ci;
    private Long bangTanId;
    private Long thietBiId;
    private Long loaiTramId;
    private Long noOfCarrier;

    public Cell3gRegForm() {
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getBtsCode() {
        return btsCode;
    }

    public void setBtsCode(String btsCode) {
        this.btsCode = btsCode;
    }

    public Long getNodeChaId() {
        return nodeChaId;
    }

    public void setNodeChaId(Long nodeChaId) {
        this.nodeChaId = nodeChaId;
    }

    public String getTenCell() {
        return tenCell;
    }

    public void setTenCell(String tenCell) {
        this.tenCell = tenCell;
    }

    public String getHoanCanhRaDoi() {
        return hoanCanhRaDoi;
    }

    public void setHoanCanhRaDoi(String hoanCanhRaDoi) {
        this.hoanCanhRaDoi = hoanCanhRaDoi;
    }

    public Date getNgayHoatDong() {
        return ngayHoatDong;
    }

    public void setNgayHoatDong(Date ngayHoatDong) {
        this.ngayHoatDong = ngayHoatDong;
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

    public Long getThietBiId() {
        return thietBiId;
    }

    public void setThietBiId(Long thietBiId) {
        this.thietBiId = thietBiId;
    }

    public Long getLoaiTramId() {
        return loaiTramId;
    }

    public void setLoaiTramId(Long loaiTramId) {
        this.loaiTramId = loaiTramId;
    }

    public Long getNoOfCarrier() {
        return noOfCarrier;
    }

    public void setNoOfCarrier(Long noOfCarrier) {
        this.noOfCarrier = noOfCarrier;
    }

}
