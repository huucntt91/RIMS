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
public class TinhBO {
    private long tinhTpId;
    
    private String tenTinhTp;
    private String maTinhTp;
    private long khuVuc;
    
    public TinhBO(){
        tinhTpId = -1;
        tenTinhTp = "";
        maTinhTp = "";
            
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

  
    /**
     * @return the khuVuc
     */
    public long getKhuVuc() {
        return khuVuc;
    }

    /**
     * @param khuVuc the khuVuc to set
     */
    public void setKhuVuc(long khuVuc) {
        this.khuVuc = khuVuc;
    }

    /**
     * @return the maTinhTp
     */
    public String getMaTinhTp() {
        return maTinhTp;
    }

    /**
     * @param maTinhTp the maTinhTp to set
     */
    public void setMaTinhTp(String maTinhTp) {
        this.maTinhTp = maTinhTp;
    }

    /**
     * @return the tinh_id
     */
    
}
