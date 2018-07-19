package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class AutoEmailBO implements Serializable {

    public String idReportMail;
    public String reportMailName;
    public String listMail;
    public String timeSend;
    public String emailDetail;
    public String idSql;
    public String sqlName;
    public String sqlValue;
    public String description;
    public String idEmailLink;
    public String status;

    public String getEmailDetail() {
        return emailDetail;
    }

    public void setEmailDetail(String emailDetail) {
        this.emailDetail = emailDetail;
    }

    public String getIdReportMail() {
        return idReportMail;
    }

    public void setIdReportMail(String idReportMail) {
        this.idReportMail = idReportMail;
    }

    public String getReportMailName() {
        return reportMailName;
    }

    public void setReportMailName(String reportMailName) {
        this.reportMailName = reportMailName;
    }

    public String getListMail() {
        return listMail;
    }

    public void setListMail(String listMail) {
        this.listMail = listMail;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public String getIdSql() {
        return idSql;
    }

    public void setIdSql(String idSql) {
        this.idSql = idSql;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getSqlValue() {
        return sqlValue;
    }

    public void setSqlValue(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdEmailLink() {
        return idEmailLink;
    }

    public void setIdEmailLink(String idEmailLink) {
        this.idEmailLink = idEmailLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
