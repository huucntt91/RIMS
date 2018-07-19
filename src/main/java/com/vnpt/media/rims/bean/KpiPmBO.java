/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;


/**
 *
 * @author VNP
 */
public class KpiPmBO {
    private long kpiId;
    
    private String codeKpi;
    private String nameKpi;
    private long neType;
    
    public KpiPmBO(){
        kpiId = -1;
        nameKpi = "";
        codeKpi = "";
            
    }

    public long getKpiId() {
        return kpiId;
    }

    public void setKpiId(long kpiId) {
        this.kpiId = kpiId;
    }

    public String getCodeKpi() {
        return codeKpi;
    }

    public void setCodeKpi(String codeKpi) {
        this.codeKpi = codeKpi;
    }

    public String getNameKpi() {
        return nameKpi;
    }

    public void setNameKpi(String nameKpi) {
        this.nameKpi = nameKpi;
    }

    public long getNeType() {
        return neType;
    }

    public void setNeType(long neType) {
        this.neType = neType;
    }
    
}
