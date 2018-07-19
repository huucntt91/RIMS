/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import com.blogspot.na5cent.exom.annotation.Column;

/**
 *
 * @author VNP
 */
public class BlackPointBO {

    @Column(name = "note", index = "0")
    private String note;

    private String id;

    private String code;

    @Column(name = "address", index = "6")
    private String address;

    @Column(name = "name", index = "3")
    private String name;
    @Column(name = "lat", index = "4")
    private String lat;
    @Column(name = "lon", index = "5")
    private String lon;
    @Column(name = "address", index = "7")
    private String des;

    @Column(name = "tenTinhTp", index = "1")
    private String tenTinhTp;
    @Column(name = "tenQuanHuyen", index = "2")
    private String tenQuanHuyen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTenTinhTp() {
        return tenTinhTp;
    }

    public void setTenTinhTp(String tenTinhTp) {
        this.tenTinhTp = tenTinhTp;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
