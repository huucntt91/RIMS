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
public class LoaiTramBO {

    /**
     * @return the tenLoaiNe
     */
    public String getTenLoaiNe() {
        return tenLoaiNe;
    }

    /**
     * @param tenLoaiNe the tenLoaiNe to set
     */
    public void setTenLoaiNe(String tenLoaiNe) {
        this.tenLoaiNe = tenLoaiNe;
    }
    private Long loaiTramId;
    private String tenLoaiTram;
    private Long neTypeId;
    private String tenLoaiNe;

    public LoaiTramBO()
    {
//        loaiTramId = -1;
        tenLoaiTram= "";
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
     * @return the thietBiId
     */
    
    
}
