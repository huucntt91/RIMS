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
public class Cell3gBO implements Serializable {

    private Cell3gInfoBO cell3gInfoBO;
    private OmcCell3gInfoBO omcCell3gInfoBO;

    public Cell3gBO() {
    }

    public Cell3gInfoBO getCell3gInfoBO() {
        return cell3gInfoBO;
    }

    public void setCell3gInfoBO(Cell3gInfoBO cell3gInfoBO) {
        this.cell3gInfoBO = cell3gInfoBO;
    }

    public OmcCell3gInfoBO getOmcCell3gInfoBO() {
        return omcCell3gInfoBO;
    }

    public void setOmcCell3gInfoBO(OmcCell3gInfoBO omcCell3gInfoBO) {
        this.omcCell3gInfoBO = omcCell3gInfoBO;
    }

    

}
