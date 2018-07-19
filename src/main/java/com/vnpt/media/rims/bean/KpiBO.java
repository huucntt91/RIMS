/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;

/**
 *
 * @author Cyano
 */
public class KpiBO {
    Integer kpiMappingId;
    String code;
    String name;
    Integer neTypeId;

    public KpiBO() {
    }

    public KpiBO(Integer kpiMappingId, String code, String name) {
        this.kpiMappingId = kpiMappingId;
        this.code = code;
        this.name = name;
        
    }

    
    
    public Integer getKpiMappingId() {
        return kpiMappingId;
    }

    public void setKpiMappingId(Integer kpiMappingId) {
        this.kpiMappingId = kpiMappingId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNeTypeId() {
        return neTypeId;
    }

    public void setNeTypeId(Integer neTypeId) {
        this.neTypeId = neTypeId;
    }

   
    
    
    
    
}
