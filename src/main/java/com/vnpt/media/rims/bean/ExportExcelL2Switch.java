/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import java.util.List;
import java.util.Map;

/**
 *
 * @author VNP
 */
public class ExportExcelL2Switch {
    private String tenTinh;
    private Map<String,Long> data;
    private List<String> listDongthietbi;

    public ExportExcelL2Switch() {
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public Map<String, Long> getData() {
        return data;
    }

    public void setData(Map<String, Long> data) {
        this.data = data;
    }

    public List<String> getListDongthietbi() {
        return listDongthietbi;
    }

    public void setListDongthietbi(List<String> listDongthietbi) {
        this.listDongthietbi = listDongthietbi;
    }
    
    
}
