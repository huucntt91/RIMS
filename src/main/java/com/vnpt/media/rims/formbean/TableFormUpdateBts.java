/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.ImportBtsModel;
import com.vnpt.media.rims.bean.ExcelBtsUpdateBO;
import java.util.List;

/**
 *
 * @author VNP
 */
public class TableFormUpdateBts {

    private List<ExcelBtsUpdateBO> models;

    public TableFormUpdateBts() {
    }

    public List<ExcelBtsUpdateBO> getModels() {
        return models;
    }

    public void setModels(List<ExcelBtsUpdateBO> models) {
        this.models = models;
    }
}
