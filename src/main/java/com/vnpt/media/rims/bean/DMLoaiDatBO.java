package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class DMLoaiDatBO implements Serializable {

    public String loai_dat_id;
    public String ten_loai_dat;

    public String getLoai_dat_id() {
        return loai_dat_id;
    }

    public void setLoai_dat_id(String loai_dat_id) {
        this.loai_dat_id = loai_dat_id;
    }

    public String getTen_loai_dat() {
        return ten_loai_dat;
    }

    public void setTen_loai_dat(String ten_loai_dat) {
        this.ten_loai_dat = ten_loai_dat;
    }

}
