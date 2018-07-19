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
public class ThietBiBO {
    private long thietBiId;
    
    private String tenThietBi;

    public ThietBiBO()
    {
        thietBiId = -1;
        tenThietBi= "";
    }
    
    /**
     * @return the thietBiId
     */
    public long getThietBiId() {
        return thietBiId;
    }

    /**
     * @param thietBiId the thietBiId to set
     */
    public void setThietBiId(long thietBiId) {
        this.thietBiId = thietBiId;
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
    
}
