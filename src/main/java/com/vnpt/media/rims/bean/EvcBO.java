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
public class EvcBO {
    private String tnodeId;
    private String evcId;
    private String vcId;
    private String vcIndex;
    private String status1;
    private String interfaceName;
    private String deviceName;
    private String status2;
    private String ip;
    private String description;
    private Date activeTime;

    public String getTnodeId() {
        return tnodeId;
    }

    public void setTnodeId(String tnodeId) {
        this.tnodeId = tnodeId;
    }

    public String getEvcId() {
        return evcId;
    }

    public String getVcId() {
        return vcId;
    }

    public void setVcId(String vcId) {
        this.vcId = vcId;
    }
    

    public void setEvcId(String evcId) {
        this.evcId = evcId;
    }

    public String getVcIndex() {
        return vcIndex;
    }

    public void setVcIndex(String vcIndex) {
        this.vcIndex = vcIndex;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }
    
    
    
}
