/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import java.util.Date;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author VNP
 */
public class LoaiNeBO {
    private long id;
    private String name;    
    private long familyId;
    private long networkId;

    public LoaiNeBO() {
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the familyId
     */
    public long getFamilyId() {
        return familyId;
    }

    /**
     * @param familyId the familyId to set
     */
    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }

    /**
     * @return the networkId
     */
    public long getNetworkId() {
        return networkId;
    }

    /**
     * @param networkId the networkId to set
     */
    public void setNetworkId(long networkId) {
        this.networkId = networkId;
    }

    

}
