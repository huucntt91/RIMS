/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.List;

/**
 *
 * @author VNP
 */
public class TPortBO {

    private Long id;
    private Long eq3Id;
    private String eq3Name;
    private String portNo;
    private String note;
    private String mtu;
    private String transceiver;
    private String congsuatphat;
    private String congsuatthu;
    private String nguongthumin;
    private String nguongthumax;
    private String serialNo;
    private String tportName;
    private List<TModuleQuangBO> tModuleQuangBOList;
//    trunglk_start
    private String tNodeId;
    private String tPortId;
    private String tEq3Id;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public String gettNodeId() {
        return tNodeId;
    }

    public void settNodeId(String tNodeId) {
        this.tNodeId = tNodeId;
    }

    public String gettPortId() {
        return tPortId;
    }

    public void settPortId(String tPortId) {
        this.tPortId = tPortId;
    }

    public String gettEq3Id() {
        return tEq3Id;
    }

    public void settEq3Id(String tEq3Id) {
        this.tEq3Id = tEq3Id;
    }
    
//    trunglk_end

    public Long getId() {
        return id;
    }

    public String getEq3Name() {
        return eq3Name;
    }

    public void setEq3Name(String eq3Name) {
        this.eq3Name = eq3Name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEq3Id() {
        return eq3Id;
    }

    public void setEq3Id(Long eq3Id) {
        this.eq3Id = eq3Id;
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMtu() {
        return mtu;
    }

    public void setMtu(String mtu) {
        this.mtu = mtu;
    }

    public String getTransceiver() {
        return transceiver;
    }

    public void setTransceiver(String transceiver) {
        this.transceiver = transceiver;
    }

    public String getCongsuatphat() {
        return congsuatphat;
    }

    public void setCongsuatphat(String congsuatphat) {
        this.congsuatphat = congsuatphat;
    }

    public String getCongsuatthu() {
        return congsuatthu;
    }

    public void setCongsuatthu(String congsuatthu) {
        this.congsuatthu = congsuatthu;
    }

    public String getNguongthumin() {
        return nguongthumin;
    }

    public void setNguongthumin(String nguongthumin) {
        this.nguongthumin = nguongthumin;
    }

    public String getNguongthumax() {
        return nguongthumax;
    }

    public void setNguongthumax(String nguongthumax) {
        this.nguongthumax = nguongthumax;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getTportName() {
        return tportName;
    }

    public void setTportName(String tportName) {
        this.tportName = tportName;
    }

    public List<TModuleQuangBO> gettModuleQuangBOList() {
        return tModuleQuangBOList;
    }

    public void settModuleQuangBOList(List<TModuleQuangBO> tModuleQuangBOList) {
        this.tModuleQuangBOList = tModuleQuangBOList;
    }

    
}
