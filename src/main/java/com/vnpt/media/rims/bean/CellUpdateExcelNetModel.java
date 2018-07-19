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
public class CellUpdateExcelNetModel {

    @Column(name = "Kiem tra du lieu db", index = "")
    private String checkDB;
    @Column(name = "ma cell", index = "1")
    private String code;
    @Column(name = "azimuth", index = "2")
    private String azimuth;
    @Column(name = "mechanicalTilt", index = "3")
    private String mechanicalTilt;
    @Column(name = "total tilt", index = "4")
    private String totalTilt;
    @Column(name = "antennaHigh", index = "5")
    private String antennaHigh;
    @Column(name = "antennaType", index = "6")
    private String antennaType;
    @Column(name = "antennaGain", index = "7")
    private String antennaGain;
    @Column(name = "antennaHignGain", index = "8")
    private String antennaHignGain;
    @Column(name = "specialCoverage", index = "9")
    private String specialCoverage;


    public CellUpdateExcelNetModel() {
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

    public String getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public String getMechanicalTilt() {
        return mechanicalTilt;
    }

    public void setMechanicalTilt(String mechanicalTilt) {
        this.mechanicalTilt = mechanicalTilt;
    }

    public String getTotalTilt() {
        return totalTilt;
    }

    public void setTotalTilt(String totalTilt) {
        this.totalTilt = totalTilt;
    }

    public String getAntennaHigh() {
        return antennaHigh;
    }

    public void setAntennaHigh(String antennaHigh) {
        this.antennaHigh = antennaHigh;
    }

    public String getAntennaType() {
        return antennaType;
    }

    public void setAntennaType(String antennaType) {
        this.antennaType = antennaType;
    }

    public String getAntennaGain() {
        return antennaGain;
    }

    public void setAntennaGain(String antennaGain) {
        this.antennaGain = antennaGain;
    }

    public String getAntennaHignGain() {
        return antennaHignGain;
    }

    public void setAntennaHignGain(String antennaHignGain) {
        this.antennaHignGain = antennaHignGain;
    }

    public String getSpecialCoverage() {
        return specialCoverage;
    }

    public void setSpecialCoverage(String specialCoverage) {
        this.specialCoverage = specialCoverage;
    }

    
}
