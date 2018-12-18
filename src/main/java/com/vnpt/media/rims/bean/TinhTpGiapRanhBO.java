/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.io.Serializable;

/**
 *
 * @author VNP
 */

public class TinhTpGiapRanhBO implements Serializable {

    private Long id;
    private String maDoiTuong;
    private String loaiDoiTuong;
    private String maTinhTp;
    private String maQuyen;

    public TinhTpGiapRanhBO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDoiTuong() {
        return maDoiTuong;
    }

    public void setMaDoiTuong(String maDoiTuong) {
        this.maDoiTuong = maDoiTuong;
    }

    public String getLoaiDoiTuong() {
        return loaiDoiTuong;
    }

    public void setLoaiDoiTuong(String loaiDoiTuong) {
        this.loaiDoiTuong = loaiDoiTuong;
    }

    public String getMaTinhTp() {
        return maTinhTp;
    }

    public void setMaTinhTp(String maTinhTp) {
        this.maTinhTp = maTinhTp;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }
}
