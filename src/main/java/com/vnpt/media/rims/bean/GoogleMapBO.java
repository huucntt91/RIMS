package com.vnpt.media.rims.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class GoogleMapBO implements Serializable {
    
    private long nodeId;
    private String maMode;
    private double latitude;
    private double longitude;
    private long type;
    private String icon;
    private String[] classType;
    private long azimuth;
    
    private String maNode;
    private String action;
    private String[] neType;
    private long tinhTpId;
    private long quanHuyenId;
    private long phuongXaId;
    private long donviId;
    
    private double lat1;
    private double lon1;
    private double lat2;
    private double lon2;

    public long getDonviId() {
        return donviId;
    }

    public void setDonviId(long donviId) {
        this.donviId = donviId;
    }

    public double getLat1() {
        return lat1;
    }

    public void setLat1(double lat1) {
        this.lat1 = lat1;
    }

    public double getLon1() {
        return lon1;
    }

    public void setLon1(double lon1) {
        this.lon1 = lon1;
    }

    public double getLat2() {
        return lat2;
    }

    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }

    public double getLon2() {
        return lon2;
    }

    public void setLon2(double lon2) {
        this.lon2 = lon2;
    }

    public String getMaNode() {
        return maNode;
    }

    public void setMaNode(String maNode) {
        this.maNode = maNode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String[] getNeType() {
        return neType;
    }

    public void setNeType(String[] neType) {
        this.neType = neType;
    }

    public long getTinhTpId() {
        return tinhTpId;
    }

    public void setTinhTpId(long tinhTpId) {
        this.tinhTpId = tinhTpId;
    }

    public long getQuanHuyenId() {
        return quanHuyenId;
    }

    public void setQuanHuyenId(long quanHuyenId) {
        this.quanHuyenId = quanHuyenId;
    }

    public long getPhuongXaId() {
        return phuongXaId;
    }

    public void setPhuongXaId(long phuongXaId) {
        this.phuongXaId = phuongXaId;
    }

    public long getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(long azimuth) {
        this.azimuth = azimuth;
    }
    
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String[] getClassType() {
        return classType;
    }

    public void setClassType(String[] classType) {
        this.classType = classType;
    }

    public GoogleMapBO() {
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public String getMaMode() {
        return maMode;
    }

    public void setMaMode(String maMode) {
        this.maMode = maMode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }


}
