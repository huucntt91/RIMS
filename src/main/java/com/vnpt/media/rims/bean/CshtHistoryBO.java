/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;

/**
 *
 * @author tunv1
 */
public class CshtHistoryBO {
     
    private String userName;
    private Date createTime;
    private String action;
    private String ipClient;
    private String note;
    PhuTroBO phuTroBO;
    BuildingBO buildingBO;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public PhuTroBO getPhuTroBO() {
        return phuTroBO;
    }

    public void setPhuTroBO(PhuTroBO phuTroBO) {
        this.phuTroBO = phuTroBO;
    }

    public BuildingBO getBuildingBO() {
        return buildingBO;
    }

    public void setBuildingBO(BuildingBO buildingBO) {
        this.buildingBO = buildingBO;
    }
}
