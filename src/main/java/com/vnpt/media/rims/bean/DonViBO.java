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
public class DonViBO {

    /**
     * @return the codeTinhTp
     */
    public String getCodeTinhTp() {
        return codeTinhTp;
    }

    /**
     * @param codeTinhTp the codeTinhTp to set
     */
    public void setCodeTinhTp(String codeTinhTp) {
        this.codeTinhTp = codeTinhTp;
    }
    private long donViId;
    
    @NotEmpty(message = "{admin.validate.notempty.cp.name}")
    private String tenDonVi;
    private String diaChiDonVi;


    private long parentId;
   
  
    private Long quanHuyenId;
    private Long phuongXaId;
    private Long tinhTpId;
    private String tenPhuongXa;
    private String tenQuanHuyen;
    private String tenTinhTp;
    private String codeTinhTp;
    private String tenParent;
    
    public DonViBO() {
    }

    /**
     * @return the donViId
     */
    public long getDonViId() {
        return donViId;
    }

    /**
     * @param donViId the donViId to set
     */
    public void setDonViId(long donViId) {
        this.donViId = donViId;
    }

    /**
     * @return the tenDonVi
     */
    public String getTenDonVi() {
        return tenDonVi;
    }

    /**
     * @param tenDonVi the tenDonVi to set
     */
    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    /**
     * @return the diaChiDonVi
     */
    public String getDiaChiDonVi() {
        return diaChiDonVi;
    }

    /**
     * @param diaChiDonVi the diaChiDonVi to set
     */
    public void setDiaChiDonVi(String diaChiDonVi) {
        this.diaChiDonVi = diaChiDonVi;
    }

    /**
     * @return the parentId
     */
    public long getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the quanHuyenId
     */
    public Long getQuanHuyenId() {
        return quanHuyenId;
    }

    /**
     * @param quanHuyenId the quanHuyenId to set
     */
    public void setQuanHuyenId(Long quanHuyenId) {
        this.quanHuyenId = quanHuyenId;
    }

    /**
     * @return the phuongXaId
     */
    public Long getPhuongXaId() {
        return phuongXaId;
    }

    /**
     * @param phuongXaId the phuongXaId to set
     */
    public void setPhuongXaId(Long phuongXaId) {
        this.phuongXaId = phuongXaId;
    }

    /**
     * @return the tinhTpId
     */
    public Long getTinhTpId() {
        return tinhTpId;
    }

    /**
     * @param tinhTpId the tinhTpId to set
     */
    public void setTinhTpId(Long tinhTpId) {
        this.tinhTpId = tinhTpId;
    }

    /**
     * @return the tenPhuongXa
     */
    public String getTenPhuongXa() {
        return tenPhuongXa;
    }

    /**
     * @param tenPhuongXa the tenPhuongXa to set
     */
    public void setTenPhuongXa(String tenPhuongXa) {
        this.tenPhuongXa = tenPhuongXa;
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

    /**
     * @return the tenParent
     */
    public String getTenParent() {
        return tenParent;
    }

    /**
     * @param tenParent the tenParent to set
     */
    public void setTenParent(String tenParent) {
        this.tenParent = tenParent;
    }

   
    
  
}
