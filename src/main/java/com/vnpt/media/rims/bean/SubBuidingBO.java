/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;
import java.util.List;

/**
 *
 * @author tunv1
 */
public class SubBuidingBO {

    List<PhuTroBO> phuTroBO;
    List<NodeBO> tram;
    List<NodeBO> cell;

    public List<NodeBO> getCell() {
        return cell;
    }

    public void setCell(List<NodeBO> cell) {
        this.cell = cell;
    }
    
    BuildingBO buildingBO;

    public List<PhuTroBO> getPhuTroBO() {
        return phuTroBO;
    }

    public void setPhuTroBO(List<PhuTroBO> phuTroBO) {
        this.phuTroBO = phuTroBO;
    }

    public List<NodeBO> getTram() {
        return tram;
    }

    public void setTram(List<NodeBO> tram) {
        this.tram = tram;
    }

    public BuildingBO getBuildingBO() {
        return buildingBO;
    }

    public void setBuildingBO(BuildingBO buildingBO) {
        this.buildingBO = buildingBO;
    }

}
