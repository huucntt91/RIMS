/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.Cell4GNewExcelModel;
import com.vnpt.media.rims.bean.ImportCellModel;
import com.vnpt.media.rims.common.utils.Model;
import java.util.List;

/**
 *
 * @author VNP
 */
public class TableFormCell4G {

    private List<Cell4GNewExcelModel> models;

    public TableFormCell4G() {
    }

    public List<Cell4GNewExcelModel> getModels() {
        return models;
    }

    public void setModels(List<Cell4GNewExcelModel> models) {
        this.models = models;
    }
}
