/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.ExcelDeleteNodeBO;
import java.util.List;

/**
 *
 * @author VNP
 */
public class TableFormDeleteNode {

    private List<ExcelDeleteNodeBO> models;

    public TableFormDeleteNode() {
    }

    public List<ExcelDeleteNodeBO> getModels() {
        return models;
    }

    public void setModels(List<ExcelDeleteNodeBO> models) {
        this.models = models;
    }
}
