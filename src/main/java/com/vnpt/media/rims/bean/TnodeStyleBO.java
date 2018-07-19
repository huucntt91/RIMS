/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author VNP
 */
public class TnodeStyleBO {
    private Long id;
    private Long typeId;
    private String typeName;
    private String url;
    private String status;
    private String note;
    private MultipartFile file;
    
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    

    public TnodeStyleBO() {
    }

    public String getTypeName() {
        return typeName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
