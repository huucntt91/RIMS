package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class DMDviPheDuyetBO implements Serializable {

    public String don_vi_phe_duyet_id;
    public String ten_don_vi_phe_duyet;

    public String getDon_vi_phe_duyet_id() {
        return don_vi_phe_duyet_id;
    }

    public void setDon_vi_phe_duyet_id(String don_vi_phe_duyet_id) {
        this.don_vi_phe_duyet_id = don_vi_phe_duyet_id;
    }

    public String getTen_don_vi_phe_duyet() {
        return ten_don_vi_phe_duyet;
    }

    public void setTen_don_vi_phe_duyet(String ten_don_vi_phe_duyet) {
        this.ten_don_vi_phe_duyet = ten_don_vi_phe_duyet;
    }

}
