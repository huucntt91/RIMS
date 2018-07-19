/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.util.Date;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author VNP
 */
public class MonitoringJobAuditForm {

    private String NE_NAME;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date startTime;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date endTime;

    public String getNE_NAME() {
        return NE_NAME;
    }

    public void setNE_NAME(String NE_NAME) {
        this.NE_NAME = NE_NAME;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
   
}
