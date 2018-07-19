/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.lang.reflect.Field;
import java.util.Date;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author VNP
 */
public class ApproveAllForm {

    private String ids;
    private String comment;
    private String status;
    public ApproveAllForm() {
        
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String listParam() {
        try {
            String list = "";
            for (Field field : ApproveAllForm.class.getDeclaredFields()) {
                String value = (String) field.get(this);
                String column = field.getName();
                list += "," + column + "=" + value;
            }
            return list.substring(1);
        } catch (Exception ex) {
            return "";
        }
    }
}
