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
public class TnodeNodeBO {
    private String id;
    private String tnode_id;
    private String tnode_code;
    private String ma_node;
    private String node_id;
    private String portIn;
    private String portOut;
    private String tenTruyenDan;

    public String getPortIn() {
        return portIn;
    }

    public void setPortIn(String portIn) {
        this.portIn = portIn;
    }

    public String getPortOut() {
        return portOut;
    }

    public void setPortOut(String portOut) {
        this.portOut = portOut;
    }

    public String getTenTruyenDan() {
        return tenTruyenDan;
    }

    public void setTenTruyenDan(String tenTruyenDan) {
        this.tenTruyenDan = tenTruyenDan;
    }
    
    
    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTnode_id() {
        return tnode_id;
    }

    public void setTnode_id(String tnode_id) {
        this.tnode_id = tnode_id;
    }

    public String getTnode_code() {
        return tnode_code;
    }

    public void setTnode_code(String tnode_code) {
        this.tnode_code = tnode_code;
    }

    public String getMa_node() {
        return ma_node;
    }

    public void setMa_node(String ma_node) {
        this.ma_node = ma_node;
    }
    
    
    
}
