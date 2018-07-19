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
public class InterfaceBcBO extends  TNodeBO{
    
    private String tenTinhTp;
    private int loopBack;
    private int ethernet;
    private int adLag;
    private int serviceInstance;
    private int subInterface;
    private int propVirtual;
    private int l3IpVlan;
    private int mpls;
    private int tunnel;
    private int mplsTunnel;
    private int other;

    public String getTenTinhTp() {
        return tenTinhTp;
    }

    public void setTenTinhTp(String tenTinhTp) {
        this.tenTinhTp = tenTinhTp;
    }
    
    

    public int getLoopBack() {
        return loopBack;
    }

    public void setLoopBack(int loopBack) {
        this.loopBack = loopBack;
    }

    public int getEthernet() {
        return ethernet;
    }

    public void setEthernet(int ethernet) {
        this.ethernet = ethernet;
    }

    public int getAdLag() {
        return adLag;
    }

    public void setAdLag(int adLag) {
        this.adLag = adLag;
    }

    public int getServiceInstance() {
        return serviceInstance;
    }

    public void setServiceInstance(int serviceInstance) {
        this.serviceInstance = serviceInstance;
    }

    public int getSubInterface() {
        return subInterface;
    }

    public void setSubInterface(int subInterface) {
        this.subInterface = subInterface;
    }

    public int getPropVirtual() {
        return propVirtual;
    }

    public void setPropVirtual(int propVirtual) {
        this.propVirtual = propVirtual;
    }

    public int getL3IpVlan() {
        return l3IpVlan;
    }

    public void setL3IpVlan(int l3IpVlan) {
        this.l3IpVlan = l3IpVlan;
    }

    public int getMpls() {
        return mpls;
    }

    public void setMpls(int mpls) {
        this.mpls = mpls;
    }

    public int getTunnel() {
        return tunnel;
    }

    public void setTunnel(int tunnel) {
        this.tunnel = tunnel;
    }

    public int getMplsTunnel() {
        return mplsTunnel;
    }

    public void setMplsTunnel(int mplsTunnel) {
        this.mplsTunnel = mplsTunnel;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
    
    
}
