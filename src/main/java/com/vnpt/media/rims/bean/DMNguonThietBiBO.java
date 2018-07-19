package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class DMNguonThietBiBO implements Serializable {

    public String nguon_thiet_bi_id;
    public String ten_nguon_thiet_bi;

    public String getNguon_thiet_bi_id() {
        return nguon_thiet_bi_id;
    }

    public void setNguon_thiet_bi_id(String nguon_thiet_bi_id) {
        this.nguon_thiet_bi_id = nguon_thiet_bi_id;
    }

    public String getTen_nguon_thiet_bi() {
        return ten_nguon_thiet_bi;
    }

    public void setTen_nguon_thiet_bi(String ten_nguon_thiet_bi) {
        this.ten_nguon_thiet_bi = ten_nguon_thiet_bi;
    }

}
