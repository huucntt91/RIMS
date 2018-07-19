/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Cyano
 */
public class MonitoringJobAuditBO {
    
    private Long NE_TYPE;

    private String NE_NAME;

    private Long RI_Y_OMC_N;
    private Long RI_Y_OMC_Y;
    private Long RI_N_OMC_Y;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date INSERT_DATE;

    public Long getNE_TYPE() {
        return NE_TYPE;
    }

    public void setNE_TYPE(Long NE_TYPE) {
        this.NE_TYPE = NE_TYPE;
    }

    public String getNE_NAME() {
        return NE_NAME;
    }

    public void setNE_NAME(String NE_NAME) {
        this.NE_NAME = NE_NAME;
    }

    public Long getRI_Y_OMC_N() {
        return RI_Y_OMC_N;
    }

    public void setRI_Y_OMC_N(Long RI_Y_OMC_N) {
        this.RI_Y_OMC_N = RI_Y_OMC_N;
    }

    public Long getRI_Y_OMC_Y() {
        return RI_Y_OMC_Y;
    }

    public void setRI_Y_OMC_Y(Long RI_Y_OMC_Y) {
        this.RI_Y_OMC_Y = RI_Y_OMC_Y;
    }

    public Long getRI_N_OMC_Y() {
        return RI_N_OMC_Y;
    }

    public void setRI_N_OMC_Y(Long RI_N_OMC_Y) {
        this.RI_N_OMC_Y = RI_N_OMC_Y;
    }

    public Date getINSERT_DATE() {
        return INSERT_DATE;
    }

    public void setINSERT_DATE(Date INSERT_DATE) {
        this.INSERT_DATE = INSERT_DATE;
    }
    
    
    
    
}
