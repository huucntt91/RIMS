/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Cyano
 */
public class AuditSummaryBO {

    private String name;
    private String title;
    private Long totalRim;
    private Long totalInventory;
    private Long totalDifference;

    public static class Builder {

        private String name;
        private String title;
        private Long totalRim;
        private Long totalInventory;
        private Long totalDifference;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder totalRim(Long totalRim) {
            this.totalRim = totalRim;
            return this;
        }

        public Builder totalInventory(Long totalInventory) {
            this.totalInventory = totalInventory;
            return this;
        }

        public Builder totalDifference(Long totalDifference) {
            this.totalDifference = totalDifference;
            return this;
        }

        public AuditSummaryBO build() {
            return new AuditSummaryBO(this);
        }

    }

    public AuditSummaryBO() {
    }

    public AuditSummaryBO(Builder builder) {
        name = builder.name;
        totalRim = builder.totalRim;
        totalInventory = builder.totalInventory;
        totalDifference = builder.totalDifference;
        title=builder.title;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalRim() {
        return totalRim;
    }

    public void setTotalRim(Long totalRim) {
        this.totalRim = totalRim;
    }

    public Long getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Long totalInventory) {
        this.totalInventory = totalInventory;
    }

    public Long getTotalDifference() {
        return totalDifference;
    }

    public void setTotalDifference(Long totalDifference) {
        this.totalDifference = totalDifference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
