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
public class OpcBO {
    private Integer node_id;
    private String opc;
    private String numeral_system;
    private String opc1;
    private String numeral_system1;

    public Integer getNode_id() {
        return node_id;
    }

    public void setNode_id(Integer node_id) {
        this.node_id = node_id;
    }

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    public String getNumeral_system() {
        return numeral_system;
    }

    public void setNumeral_system(String numeral_system) {
        this.numeral_system = numeral_system;
    }

    public OpcBO() {
    }

    public OpcBO(Integer node_id, String opc, String numeral_system) {
        this.node_id = node_id;
        this.opc = opc;
        this.numeral_system = numeral_system;
    }

    public String getOpc1() {
        return opc1;
    }

    public void setOpc1(String opc1) {
        this.opc1 = opc1;
    }

    public String getNumeral_system1() {
        return numeral_system1;
    }

    public void setNumeral_system1(String numeral_system1) {
        this.numeral_system1 = numeral_system1;
    }
    
    
    
    
    
    
}
