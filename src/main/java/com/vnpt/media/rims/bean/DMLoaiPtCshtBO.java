package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class DMLoaiPtCshtBO implements Serializable {

    public String loai_pt_csht_id;
    public String ten_loai_pt_csht;

    public String getLoai_pt_csht_id() {
        return loai_pt_csht_id;
    }

    public void setLoai_pt_csht_id(String loai_pt_csht_id) {
        this.loai_pt_csht_id = loai_pt_csht_id;
    }

    public String getTen_loai_pt_csht() {
        return ten_loai_pt_csht;
    }

    public void setTen_loai_pt_csht(String ten_loai_pt_csht) {
        this.ten_loai_pt_csht = ten_loai_pt_csht;
    }

}
