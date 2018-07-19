/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author VNP
 */
public class ImportNodeForm {

    private long id;
    
    @NotEmpty(message = "{admin.validate.notempty.groupcontact.name}")
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    MultipartFile file;
    MultipartFile fileQLDA;
    MultipartFile filePTM;
    MultipartFile fileNetX;
    private long cpid;



    public ImportNodeForm() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public long getCpid() {
        return cpid;
    }

    public void setCpid(long cpid) {
        this.cpid = cpid;
    }
    
    public MultipartFile getFileNetX() {
        return fileNetX;
    }

    public void setFileNetX(MultipartFile fileNetX) {
        this.fileNetX = fileNetX;
    }

    public MultipartFile getFilePTM() {
        return filePTM;
    }

    public void setFilePTM(MultipartFile filePTM) {
        this.filePTM = filePTM;
    }

    public MultipartFile getFileQLDA() {
        return fileQLDA;
    }

    public void setFileQLDA(MultipartFile fileQLDA) {
        this.fileQLDA = fileQLDA;
    }

}
