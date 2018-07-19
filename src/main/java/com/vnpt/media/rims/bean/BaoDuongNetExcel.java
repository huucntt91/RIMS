/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package com.vnpt.media.rims.bean;

import com.vnpt.media.rims.common.utils.*;
import com.blogspot.na5cent.exom.annotation.Column;
import java.util.Date;

/**
 * @author redcrow
 */
public class BaoDuongNetExcel {

    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ma node", index = "1")
    private String code;
    @Column(name = "ngayBaoDuong", index = "2")
    private String ngayBaoDuong;
    @Column(name = "donvi", index = "3")
    private String donvi;
    @Column(name = "note", index = "4")
    private String note;
 
    public BaoDuongNetExcel() {
    }

    public String getCheckDB() {
        return checkDB;
    }

    public void setCheckDB(String checkDB) {
        this.checkDB = checkDB;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNgayBaoDuong() {
        return ngayBaoDuong;
    }

    public void setNgayBaoDuong(String ngayBaoDuong) {
        this.ngayBaoDuong = ngayBaoDuong;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
    
}
