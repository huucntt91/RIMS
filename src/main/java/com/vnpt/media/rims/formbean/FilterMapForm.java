/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.util.List;

/**
 *
 * @author VNP
 */
public class FilterMapForm {

    private String tinhId;

    private String huyenId;
    
    private String xaId;
    
    private String where;
    
    private String locationLat;
    
    private String locationLong;

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(String locationLong) {
        this.locationLong = locationLong;
    }

    public String getXaId() {
        return xaId;
    }

    public void setXaId(String xaId) {
        this.xaId = xaId;
    }

    
    public FilterMapForm() {
    }

    public String getTinhId() {
        return tinhId;
    }

    public void setTinhId(String tinhId) {
        this.tinhId = tinhId;
    }

    public String getHuyenId() {
        return huyenId;
    }

    public void setHuyenId(String huyenId) {
        this.huyenId = huyenId;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    private String objectType;
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    
}
