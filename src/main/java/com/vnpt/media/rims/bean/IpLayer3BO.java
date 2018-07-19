/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

/**
 *
 * @author Cyano
 */
public class IpLayer3BO {
    private String tnodeId;
    private String ipLayer3Id;
    private String ipLayer3Name;
    private String ip;
    private String subnet;

    public String getTnodeId() {
        return tnodeId;
    }

    public void setTnodeId(String tnodeId) {
        this.tnodeId = tnodeId;
    }

    public String getIpLayer3Id() {
        return ipLayer3Id;
    }

    public void setIpLayer3Id(String ipLayer3Id) {
        this.ipLayer3Id = ipLayer3Id;
    }

    public String getIpLayer3Name() {
        return ipLayer3Name;
    }

    public void setIpLayer3Name(String ipLayer3Name) {
        this.ipLayer3Name = ipLayer3Name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }
    
    
}
