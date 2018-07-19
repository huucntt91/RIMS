/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;

/**
 *
 * @author Cyano
 */
public class ChartBO {
    private String host;
    private String vendor;
    private Integer file_num;
    private Date download_time;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Integer getFile_num() {
        return file_num;
    }

    public void setFile_num(Integer file_num) {
        this.file_num = file_num;
    }

    public Date getDownload_time() {
        return download_time;
    }

    public void setDownload_time(Date download_time) {
        this.download_time = download_time;
    }

    public ChartBO(String host, String vendor, Integer file_num, Date download_time) {
        this.host = host;
        this.vendor = vendor;
        this.file_num = file_num;
        this.download_time = download_time;
    }

    public ChartBO() {
    }
    
    
    
}
