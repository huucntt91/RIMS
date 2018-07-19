package com.vnpt.media.rims.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class UserBO implements Serializable {

    private long id;
    @NotEmpty(message = "{admin.validate.notempty.username}")
    @Size(min = 4, max = 200, message = "{admin.validate.size.username}")
    private String username;
    private String password;
    private String newpassword;
    @NotEmpty(message = "{admin.validate.notempty.fullname}")
    @Size(min = 4, max = 200, message = "{admin.validate.size.fullname}")
    private String fullname;
    private String email;
    private String msisdn;
    private int status;
    private String tenDonVi;
    private long donViId;
    private Timestamp createDate;

    public UserBO() {
    }
    
    public String listParam() {
        try {
            String list = "";
            for (Field field : UserBO.class.getDeclaredFields()) {
                String value;
                try {
                    value = field.get(this).toString();
                } catch (Exception e) {
                    value = "";
                }
                String column = field.getName();
                list += "," + column + "=" + value;
            }
            return list.substring(1);
        } catch (Exception ex) {
            return "";
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public long getDonViId() {
        return donViId;
    }

    public void setDonViId(long donViId) {
        this.donViId = donViId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

}
