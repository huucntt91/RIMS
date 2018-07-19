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
public class KiemDinhNetExcel {

    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ma csht", index = "1")
    private String code;
    @Column(name = "ma kiem dinh", index = "2")
    private String maKiemDinh;
    @Column(name = "ngayKiemDinh", index = "3")
    private String ngayKiemDinh;
    @Column(name = "ngayHetKiemDinh", index = "4")
    private String ngayHetKiemDinh;
 
    public KiemDinhNetExcel() {
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

    public String getMaKiemDinh() {
        return maKiemDinh;
    }

    public void setMaKiemDinh(String maKiemDinh) {
        this.maKiemDinh = maKiemDinh;
    }

    public String getNgayHetKiemDinh() {
        return ngayHetKiemDinh;
    }

    public void setNgayHetKiemDinh(String ngayHetKiemDinh) {
        this.ngayHetKiemDinh = ngayHetKiemDinh;
    }

    public String getNgayKiemDinh() {
        return ngayKiemDinh;
    }

    public void setNgayKiemDinh(String ngayKiemDinh) {
        this.ngayKiemDinh = ngayKiemDinh;
    }

    
}
