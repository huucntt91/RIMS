/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.CellInfoBO;
import com.vnpt.media.rims.bean.LoaiNeBO;
import com.vnpt.media.rims.bean.LoaiTramBO;
import com.vnpt.media.rims.bean.OmcCellInfoBO;
import com.vnpt.media.rims.bean.ThietBiBO;
import java.io.Serializable;

/**
 *
 * @author VNP
 */
public class CellNewForm implements Serializable {

    private String btsCode;
    private String tenTinhTP;
    private Long buildingId;
    private CellInfoBO cellInfo;
    private OmcCellInfoBO omcCellInfo;
    private ThietBiBO thietBi;
    private LoaiTramBO loaiTram;
    private LoaiNeBO loaiNE;

    public CellNewForm() {
    }

    public String getBtsCode() {
        return btsCode;
    }

    public void setBtsCode(String btsCode) {
        this.btsCode = btsCode;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

   

    public CellInfoBO getCellInfo() {
        return cellInfo;
    }

    public void setCellInfo(CellInfoBO cellInfo) {
        this.cellInfo = cellInfo;
    }

    public OmcCellInfoBO getOmcCellInfo() {
        return omcCellInfo;
    }

    public void setOmcCellInfo(OmcCellInfoBO omcCellInfo) {
        this.omcCellInfo = omcCellInfo;
    }

    public ThietBiBO getThietBi() {
        return thietBi;
    }

    public void setThietBi(ThietBiBO thietBi) {
        this.thietBi = thietBi;
    }

    public LoaiTramBO getLoaiTram() {
        return loaiTram;
    }

    public void setLoaiTram(LoaiTramBO loaiTram) {
        this.loaiTram = loaiTram;
    }

    public LoaiNeBO getLoaiNE() {
        return loaiNE;
    }

    public void setLoaiNE(LoaiNeBO loaiNE) {
        this.loaiNE = loaiNE;
    }

    public String getTenTinhTP() {
        return tenTinhTP;
    }

    public void setTenTinhTP(String tenTinhTP) {
        this.tenTinhTP = tenTinhTP;
    }

}
