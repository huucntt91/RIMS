/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.List;

/**
 *
 * @author tunv1
 */
public class SubEqBO {

    TEq1BO tEq1BO;
    List<TEq2BO> tEq2BO;
    List<TEq3BO> tEq3BO;
    List<TPortBO> tPortBO;

    public SubEqBO() {
    }

    public TEq1BO gettEq1BO() {
        return tEq1BO;
    }

    public void settEq1BO(TEq1BO tEq1BO) {
        this.tEq1BO = tEq1BO;
    }

   

    public List<TEq2BO> gettEq2BO() {
        return tEq2BO;
    }

    public void settEq2BO(List<TEq2BO> tEq2BO) {
        this.tEq2BO = tEq2BO;
    }

    public List<TEq3BO> gettEq3BO() {
        return tEq3BO;
    }

    public void settEq3BO(List<TEq3BO> tEq3BO) {
        this.tEq3BO = tEq3BO;
    }

    public List<TPortBO> gettPortBO() {
        return tPortBO;
    }

    public void settPortBO(List<TPortBO> tPortBO) {
        this.tPortBO = tPortBO;
    }

    
}
