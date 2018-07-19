package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class DMLoaiCongNgheBO implements Serializable {

    public String id_loai_cong_nghe;
    public String ten_loai_cong_nghe;

    public String getId_loai_cong_nghe() {
        return id_loai_cong_nghe;
    }

    public void setId_loai_cong_nghe(String id_loai_cong_nghe) {
        this.id_loai_cong_nghe = id_loai_cong_nghe;
    }

    public String getTen_loai_cong_nghe() {
        return ten_loai_cong_nghe;
    }

    public void setTen_loai_cong_nghe(String ten_loai_cong_nghe) {
        this.ten_loai_cong_nghe = ten_loai_cong_nghe;
    }

}
