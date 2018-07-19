/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.common.utils;

import java.util.List;

/**
 *
 * @author LeChi
 */
public class Page { 
    private int totalRows;
    private int totalPages;
    private int destPage;
    private String subject;
    private List<String> titlesList;
    private int direction;
    private String url;
    private String url_page;
    private String totalRowsTitle = "Tổng số bản ghi: ";
    private String totalPagesTitle = "Tổng trang: ";
    private String goToPageTitle = "Đi tới trang: ";
    private String urlRoot;
    private String urlImage;

    public String getUrlRoot() {
        return urlRoot;
    }

    public void setUrlRoot(String urlRoot) {
        this.urlRoot = urlRoot;
    }
    

    public String getUrl_page() {
        return url_page;
    }

    public void setUrl_page(String url_page) {
        this.url_page = url_page;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getDestPage() {
        return destPage;
    }

    public void setDestPage(int destPage) {
        this.destPage = destPage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getTitlesList() {
        return titlesList;
    }

    public void setTitlesList(List<String> titlesList) {
        this.titlesList = titlesList;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTotalRowsTitle() {
        return totalRowsTitle;
    }

    public void setTotalRowsTitle(String totalRowsTitle) {
        this.totalRowsTitle = totalRowsTitle;
    }

    public String getTotalPagesTitle() {
        return totalPagesTitle;
    }

    public void setTotalPagesTitle(String totalPagesTitle) {
        this.totalPagesTitle = totalPagesTitle;
    }

    public String getGoToPageTitle() {
        return goToPageTitle;
    }

    public void setGoToPageTitle(String goToPageTitle) {
        this.goToPageTitle = goToPageTitle;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    
}
