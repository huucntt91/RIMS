package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class DMLoaiNhaTramBO implements Serializable {

    public String loai_nha_tram_id;
    public String ten_loai_nha_tram;

    public String getLoai_nha_tram_id() {
        return loai_nha_tram_id;
    }

    public void setLoai_nha_tram_id(String loai_nha_tram_id) {
        this.loai_nha_tram_id = loai_nha_tram_id;
    }

    public String getTen_loai_nha_tram() {
        return ten_loai_nha_tram;
    }

    public void setTen_loai_nha_tram(String ten_loai_nha_tram) {
        this.ten_loai_nha_tram = ten_loai_nha_tram;
    }

}
