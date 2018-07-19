/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.ImportBtsModel;
import com.vnpt.media.rims.bean.ImportCellModel;
import com.vnpt.media.rims.common.utils.Model;
import java.util.List;

/**
 *
 * @author VNP
 */
public class TableFormImportBts {

    private List<ImportBtsModel> models;

    public TableFormImportBts() {
    }

    public List<ImportBtsModel> getModels() {
        return models;
    }

    public void setModels(List<ImportBtsModel> models) {
        this.models = models;
    }
}
