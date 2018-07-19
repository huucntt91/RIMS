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
public class NeLinkBO {
    private Integer ne_link_id;
    private Integer node_id1;
    private String nodeCode1;
    private String opc1;
    private String numeral_system1;
    private Integer ne_type_id1;
    private Integer node_id2;
    private String nodeCode2;
    private String opc2;
    private String numeral_system2;
    private Integer ne_type_id2;

    public Integer getNe_link_id() {
        return ne_link_id;
    }

    public void setNe_link_id(Integer ne_link_id) {
        this.ne_link_id = ne_link_id;
    }
    
    

    public String getNodeCode1() {
        return nodeCode1;
    }

    public void setNodeCode1(String nodeCode1) {
        this.nodeCode1 = nodeCode1;
    }

    public String getOpc1() {
        return opc1;
    }

    public void setOpc1(String opc1) {
        this.opc1 = opc1;
    }

    public String getNodeCode2() {
        return nodeCode2;
    }

    public void setNodeCode2(String nodeCode2) {
        this.nodeCode2 = nodeCode2;
    }

    public String getOpc2() {
        return opc2;
    }

    public void setOpc2(String opc2) {
        this.opc2 = opc2;
    }

    public NeLinkBO() {
    }

    public NeLinkBO(String nodeCode1, String opc1, String nodeCode2, String opc2) {
        this.nodeCode1 = nodeCode1;
        this.opc1 = opc1;
        this.nodeCode2 = nodeCode2;
        this.opc2 = opc2;
    }

    public Integer getNode_id1() {
        return node_id1;
    }

    public void setNode_id1(Integer node_id1) {
        this.node_id1 = node_id1;
    }

    public Integer getNode_id2() {
        return node_id2;
    }

    public void setNode_id2(Integer node_id2) {
        this.node_id2 = node_id2;
    }

    public String getNumeral_system1() {
        return numeral_system1;
    }

    public void setNumeral_system1(String numeral_system1) {
        this.numeral_system1 = numeral_system1;
    }

    public String getNumeral_system2() {
        return numeral_system2;
    }

    public void setNumeral_system2(String numeral_system2) {
        this.numeral_system2 = numeral_system2;
    }

    public Integer getNe_type_id1() {
        return ne_type_id1;
    }

    public void setNe_type_id1(Integer ne_type_id1) {
        this.ne_type_id1 = ne_type_id1;
    }

    public Integer getNe_type_id2() {
        return ne_type_id2;
    }

    public void setNe_type_id2(Integer ne_type_id2) {
        this.ne_type_id2 = ne_type_id2;
    }
    
    

}
