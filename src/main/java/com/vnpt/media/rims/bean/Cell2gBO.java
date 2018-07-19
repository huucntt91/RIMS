/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author VNP
 */
public class Cell2gBO implements Serializable {

    private Cell2gInfoBO cell2gInfoBO;
    private OmcCell2gInfoBO omcCell2gInfoBO;

    public Cell2gBO() {
    }

    public Cell2gInfoBO getCell2gInfoBO() {
        return cell2gInfoBO;
    }

    public void setCell2gInfoBO(Cell2gInfoBO cell2gInfoBO) {
        this.cell2gInfoBO = cell2gInfoBO;
    }

    public OmcCell2gInfoBO getOmcCell2gInfoBO() {
        return omcCell2gInfoBO;
    }

    public void setOmcCell2gInfoBO(OmcCell2gInfoBO omcCell2gInfoBO) {
        this.omcCell2gInfoBO = omcCell2gInfoBO;
    }

}
