package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class DMBangTanBO implements Serializable {

    public String bang_tan_id;
    public String id_loai_cong_nghe;
    public String ten_bang_tan;
    public String ten_loai_cong_nghe;

    public String getBang_tan_id() {
        return bang_tan_id;
    }

    public void setBang_tan_id(String bang_tan_id) {
        this.bang_tan_id = bang_tan_id;
    }

    public String getId_loai_cong_nghe() {
        return id_loai_cong_nghe;
    }

    public void setId_loai_cong_nghe(String id_loai_cong_nghe) {
        this.id_loai_cong_nghe = id_loai_cong_nghe;
    }

    public String getTen_bang_tan() {
        return ten_bang_tan;
    }

    public void setTen_bang_tan(String ten_bang_tan) {
        this.ten_bang_tan = ten_bang_tan;
    }

    public String getTen_loai_cong_nghe() {
        return ten_loai_cong_nghe;
    }

    public void setTen_loai_cong_nghe(String ten_loai_cong_nghe) {
        this.ten_loai_cong_nghe = ten_loai_cong_nghe;
    }

}
