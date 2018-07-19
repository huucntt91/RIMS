package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class DMTrangThaiBO implements Serializable {

    public String trangthai_id;
    public String ten_trangthai;

    public String getTrangthai_id() {
        return trangthai_id;
    }

    public void setTrangthai_id(String trangthai_id) {
        this.trangthai_id = trangthai_id;
    }

    public String getTen_trangthai() {
        return ten_trangthai;
    }

    public void setTen_trangthai(String ten_trangthai) {
        this.ten_trangthai = ten_trangthai;
    }

}
