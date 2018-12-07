/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package com.vnpt.media.rims.bean;

import com.vnpt.media.rims.common.utils.*;
import com.blogspot.na5cent.exom.annotation.Column;
import java.util.Date;

/**
 * @author redcrow
 */
public class BaoDuongNetExcel {

    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ma node", index = "1")
    private String code;
    @Column(name = "ngayBaoDuong", index = "2")
    private String ngayBaoDuong;
    @Column(name = "donvi", index = "3")
    private String donvi;
    @Column(name = "note", index = "4")
    private String note;
    @Column(name = "neType", index = "")
    private String neType;
    @Column(name = "nodeId", index = "")
    private String nodeId;
    @Column(name = "baoDuongId", index = "")
    private String baoDuongId;
    @Column(name = "neTypeId", index = "")
    private String neTypeId;

    @Column(name = "area", index = "")
    private String area;

    @Column(name = "provinceCode", index = "")
    private String provinceCode;
    @Column(name = "provinceName", index = "")
    private String provinceName;
    @Column(name = "provinceId", index = "")
    private String provinceId;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
    
    
    

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    
    
    

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getBaoDuongId() {
        return baoDuongId;
    }

    public void setBaoDuongId(String baoDuongId) {
        this.baoDuongId = baoDuongId;
    }

    public String getNeTypeId() {
        return neTypeId;
    }

    public void setNeTypeId(String neTypeId) {
        this.neTypeId = neTypeId;
    }

    public String getNeType() {
        return neType;
    }

    public void setNeType(String neType) {
        this.neType = neType;
    }

    public BaoDuongNetExcel() {
    }

    public String getCheckDB() {
        return checkDB;
    }

    public void setCheckDB(String checkDB) {
        this.checkDB = checkDB;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNgayBaoDuong() {
        return ngayBaoDuong;
    }

    public void setNgayBaoDuong(String ngayBaoDuong) {
        this.ngayBaoDuong = ngayBaoDuong;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
