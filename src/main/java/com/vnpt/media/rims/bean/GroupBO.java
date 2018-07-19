/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author VNP
 */
public class GroupBO {

    private long id;
    @NotEmpty(message = "{admin.validate.notempty.group}")
    @Size(min = 6, max = 50, message = "{admin.validate.size.group}")
    private String name;

    private long phancapId;
    private long networkTypeId;
    private long familyTypeId;

    public GroupBO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhancapId() {
        return phancapId;
    }

    public void setPhancapId(long phancapId) {
        this.phancapId = phancapId;
    }

    public long getNetworkTypeId() {
        return networkTypeId;
    }

    public void setNetworkTypeId(long networkTypeId) {
        this.networkTypeId = networkTypeId;
    }

    public long getFamilyTypeId() {
        return familyTypeId;
    }

    public void setFamilyTypeId(long familyTypeId) {
        this.familyTypeId = familyTypeId;
    }

}
