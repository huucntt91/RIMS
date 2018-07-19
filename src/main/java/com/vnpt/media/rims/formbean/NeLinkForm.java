/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.util.Date;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author VNP
 */
public class NeLinkForm {

    private Long id;

    private String code;

    private String tenLoaiTruyenDan;
    private Long neTypeId;
    private Long id2;
    private Long id1;
    private String lat;
    private String lon;
    

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

    
    public Long getId1() {
        return id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }

    private Long loaiTruyenDanId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTenLoaiTruyenDan() {
        return tenLoaiTruyenDan;
    }

    public void setTenLoaiTruyenDan(String tenLoaiTruyenDan) {
        this.tenLoaiTruyenDan = tenLoaiTruyenDan;
    }

    public Long getNeTypeId() {
        return neTypeId;
    }

    public void setNeTypeId(Long neTypeId) {
        this.neTypeId = neTypeId;
    }

    public Long getId2() {
        return id2;
    }

    public void setId2(Long id2) {
        this.id2 = id2;
    }

    public Long getLoaiTruyenDanId() {
        return loaiTruyenDanId;
    }

    public void setLoaiTruyenDanId(Long loaiTruyenDanId) {
        this.loaiTruyenDanId = loaiTruyenDanId;
    }

   
}
