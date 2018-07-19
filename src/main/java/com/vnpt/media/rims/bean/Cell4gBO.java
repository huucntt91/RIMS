/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.io.Serializable;

/**
 *
 * @author VNP
 */
public class Cell4gBO implements Serializable {

    private Cell4gInfoBO cell4gInfoBO;
    private OmcCell4gInfoBO omcCell4gInfoBO;

    public Cell4gBO() {
    }

    public Cell4gInfoBO getCell4gInfoBO() {
        return cell4gInfoBO;
    }

    public void setCell4gInfoBO(Cell4gInfoBO cell4gInfoBO) {
        this.cell4gInfoBO = cell4gInfoBO;
    }

    public OmcCell4gInfoBO getOmcCell4gInfoBO() {
        return omcCell4gInfoBO;
    }

    public void setOmcCell4gInfoBO(OmcCell4gInfoBO omcCell4gInfoBO) {
        this.omcCell4gInfoBO = omcCell4gInfoBO;
    }


}
