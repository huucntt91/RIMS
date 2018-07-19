/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

/**
 *
 * @author VNP
 */
public class AttClassGroupBO {
    private Long id;
    
    private Long groupId;
    
    private Long attrClassId;
    
    private String permission;
    

    public AttClassGroupBO()
    {
        id = 0L;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the groupId
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the attrClassId
     */
    public Long getAttrClassId() {
        return attrClassId;
    }

    /**
     * @param attrClassId the attrClassId to set
     */
    public void setAttrClassId(Long attrClassId) {
        this.attrClassId = attrClassId;
    }

    /**
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission the permission to set
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

   

}
