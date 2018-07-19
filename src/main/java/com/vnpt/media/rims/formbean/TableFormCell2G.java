/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.Cell2GNewExcelModel;
import com.vnpt.media.rims.bean.ImportCellModel;
import com.vnpt.media.rims.common.utils.Model;
import java.util.List;

/**
 *
 * @author VNP
 */
public class TableFormCell2G {

    private List<Cell2GNewExcelModel> models;

    public TableFormCell2G() {
    }

    public List<Cell2GNewExcelModel> getModels() {
        return models;
    }

    public void setModels(List<Cell2GNewExcelModel> models) {
        this.models = models;
    }
}
