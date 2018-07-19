/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import java.util.Date;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author VNP
 */
public class HuyenBO {
    private long quanHuyenId;
    
    private long tinhTpId;
    private String tenQuanHuyen;
    private String tenTinhTp;

    
    public HuyenBO(){
        quanHuyenId = -1;
        tenQuanHuyen = "";
        tinhTpId = -1;
            
    }
    /**
     * @return the quanHuyenId
     */
    public long getQuanHuyenId() {
        return quanHuyenId;
    }

    /**
     * @param quanHuyenId the quanHuyenId to set
     */
    public void setQuanHuyenId(long quanHuyenId) {
        this.quanHuyenId = quanHuyenId;
    }

    /**
     * @return the tinhTpId
     */
    public long getTinhTpId() {
        return tinhTpId;
    }

    /**
     * @param tinhTpId the tinhTpId to set
     */
    public void setTinhTpId(long tinhTpId) {
        this.tinhTpId = tinhTpId;
    }

    /**
     * @return the tenQuanHuyen
     */
    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    /**
     * @param tenQuanHuyen the tenQuanHuyen to set
     */
    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

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

  

}
