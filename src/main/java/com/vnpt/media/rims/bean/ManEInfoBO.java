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
public class ManEInfoBO {

    private Integer TNODE_ID;
    private String TNODE_CODE;
    private String TNODE_NAME;
    private String TLIST_NODE_CHA_ID;
    private Integer DONG_TBI_ID;
    private String TEN_DONG_TBI;
    private Integer STATUS;
    private String statusValue;
    private String TOTAL_SLOT;
    private Integer BUILDING_ID;
    private String ma_building;
    private Integer TNODE_TYPE_ID;
    private String IP;
    private String RING;
    private String NOTE;
    private String tenTinh;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    
    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public ManEInfoBO() {
    }

    public String getMa_building() {
        return ma_building;
    }

    public void setMa_building(String ma_building) {
        this.ma_building = ma_building;
    }

    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    public String getTEN_DONG_TBI() {
        return TEN_DONG_TBI;
    }

    public void setTEN_DONG_TBI(String TEN_DONG_TBI) {
        this.TEN_DONG_TBI = TEN_DONG_TBI;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public Integer getTNODE_ID() {
        return TNODE_ID;
    }

    public void setTNODE_ID(Integer TNODE_ID) {
        this.TNODE_ID = TNODE_ID;
    }

    public String getTNODE_CODE() {
        return TNODE_CODE;
    }

    public void setTNODE_CODE(String TNODE_CODE) {
        this.TNODE_CODE = TNODE_CODE;
    }

    public String getTNODE_NAME() {
        return TNODE_NAME;
    }

    public void setTNODE_NAME(String TNODE_NAME) {
        this.TNODE_NAME = TNODE_NAME;
    }

    public String getTLIST_NODE_CHA_ID() {
        return TLIST_NODE_CHA_ID;
    }

    public void setTLIST_NODE_CHA_ID(String TLIST_NODE_CHA_ID) {
        this.TLIST_NODE_CHA_ID = TLIST_NODE_CHA_ID;
    }

    public Integer getDONG_TBI_ID() {
        return DONG_TBI_ID;
    }

    public void setDONG_TBI_ID(Integer DONG_TBI_ID) {
        this.DONG_TBI_ID = DONG_TBI_ID;
    }

    public String getTOTAL_SLOT() {
        return TOTAL_SLOT;
    }

    public void setTOTAL_SLOT(String TOTAL_SLOT) {
        this.TOTAL_SLOT = TOTAL_SLOT;
    }

    public Integer getBUILDING_ID() {
        return BUILDING_ID;
    }

    public void setBUILDING_ID(Integer BUILDING_ID) {
        this.BUILDING_ID = BUILDING_ID;
    }

    public Integer getTNODE_TYPE_ID() {
        return TNODE_TYPE_ID;
    }

    public void setTNODE_TYPE_ID(Integer TNODE_TYPE_ID) {
        this.TNODE_TYPE_ID = TNODE_TYPE_ID;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getRING() {
        return RING;
    }

    public void setRING(String RING) {
        this.RING = RING;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

}
