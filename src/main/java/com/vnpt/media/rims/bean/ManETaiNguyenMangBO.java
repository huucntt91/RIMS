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
public class ManETaiNguyenMangBO extends  TNodeBO{
    String khuVuc;
    String vendor;
    int port1GE;
    int port10GE;
    int totalLink;
    int upLinkPort1GE;
    int upLinkPort10GE;
    int downLinkPort1GE;
    int downLinkPort10GE;
    int totalUseLink;

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getPort1GE() {
        return port1GE;
    }

    public void setPort1GE(int port1GE) {
        this.port1GE = port1GE;
    }

    public int getPort10GE() {
        return port10GE;
    }

    public void setPort10GE(int port10GE) {
        this.port10GE = port10GE;
    }

    public int getTotalLink() {
        return totalLink;
    }

    public void setTotalLink(int totalLink) {
        this.totalLink = totalLink;
    }

    public int getUpLinkPort1GE() {
        return upLinkPort1GE;
    }

    public void setUpLinkPort1GE(int upLinkPort1GE) {
        this.upLinkPort1GE = upLinkPort1GE;
    }

    public int getUpLinkPort10GE() {
        return upLinkPort10GE;
    }

    public void setUpLinkPort10GE(int upLinkPort10GE) {
        this.upLinkPort10GE = upLinkPort10GE;
    }

    public int getDownLinkPort1GE() {
        return downLinkPort1GE;
    }

    public void setDownLinkPort1GE(int downLinkPort1GE) {
        this.downLinkPort1GE = downLinkPort1GE;
    }

    public int getDownLinkPort10GE() {
        return downLinkPort10GE;
    }

    public void setDownLinkPort10GE(int downLinkPort10GE) {
        this.downLinkPort10GE = downLinkPort10GE;
    }

    public int getTotalUseLink() {
        return totalUseLink;
    }

    public void setTotalUseLink(int totalUseLink) {
        this.totalUseLink = totalUseLink;
    }
    
    
    
}
