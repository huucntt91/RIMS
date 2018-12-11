/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package com.vnpt.media.rims.bean;

import com.blogspot.na5cent.exom.annotation.Column;

/**
 * @author redcrow
 */
public class ExcelDeleteNodeBO {

    @Column(name = "check", index = "")
    private boolean check;
    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ten NE", index = "1")
    private String loaiNE;
    @Column(name = "ma", index = "2")
    private String code;
    @Column(name = "ly do", index = "3")
    private String lyDo;

    public ExcelDeleteNodeBO() {
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getCheckDB() {
        return checkDB;
    }

    public void setCheckDB(String checkDB) {
        this.checkDB = checkDB;
    }

    public String getLoaiNE() {
        return loaiNE;
    }

    public void setLoaiNE(String loaiNE) {
        this.loaiNE = loaiNE;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

  
}
