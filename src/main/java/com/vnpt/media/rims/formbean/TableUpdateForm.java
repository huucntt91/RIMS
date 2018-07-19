/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.ExcelCellUpdateBO;
import com.vnpt.media.rims.bean.ImportCellModel;
import com.vnpt.media.rims.common.utils.Model;
import java.util.List;

/**
 *
 * @author VNP
 */
public class TableUpdateForm {

    private List<ExcelCellUpdateBO> models;

    public TableUpdateForm() {
    }

    public List<ExcelCellUpdateBO> getModels() {
        return models;
    }

    public void setModels(List<ExcelCellUpdateBO> models) {
        this.models = models;
    }
}
