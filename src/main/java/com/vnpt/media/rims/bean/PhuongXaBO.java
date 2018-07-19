/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.lang.reflect.Field;

/**
 *
 * @author VNP
 */
public class PhuongXaBO {

    private long phuongXaId;

    private long quanHuyenId;
    private String tenPhuongXa;
    private String tenQuanHuyen;

    /**
     * @return the phuongXaId
     */

    public PhuongXaBO() {
        phuongXaId = -1;
        tenPhuongXa = "";
        quanHuyenId = -1;

    }

    public long getPhuongXaId() {
        return phuongXaId;
    }

    /**
     * @param phuongXaId the phuongXaId to set
     */
    public void setPhuongXaId(long phuongXaId) {
        this.phuongXaId = phuongXaId;
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

    public String listParam() {
        try {
            String list = "";
            for (Field field : PhuongXaBO.class.getDeclaredFields()) {
                String value = (String) field.get(this);
                String column = field.getName();
                list += "," + column + "=" + value;
            }
            return list.substring(1);
        } catch (Exception ex) {
            return "";
        }
    }

}
