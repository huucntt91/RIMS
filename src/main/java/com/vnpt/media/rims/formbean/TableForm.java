/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.ImportCellModel;
import com.vnpt.media.rims.common.utils.Model;
import java.util.List;

/**
 *
 * @author VNP
 */
public class TableForm {

    private List<ImportCellModel> models;

    public TableForm() {
    }

    public List<ImportCellModel> getModels() {
        return models;
    }

    public void setModels(List<ImportCellModel> models) {
        this.models = models;
    }
}
