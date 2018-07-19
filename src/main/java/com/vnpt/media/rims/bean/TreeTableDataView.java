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
public class TreeTableDataView {

    private Long id;
    private Long parentId;
    private String value;
    private String icon;
    private String type;

    private String no;
    private String inventoryUnitType;
    private String vendorName;
    private String serialNumber;
    private String manufacturerData;
    private String bomCode;
    private String name;
    private String neType;
    private String neName;
    private String neFdn;
    private int status;

    public static class Builder {

        private Long id;
        private Long parentId;
        private String value;
        private String icon;
        private String type;

        private String neType;
        private String neName;
        private String neFdn;
        private String inventoryUnitType;
        private String bomCode;
        private String name;
        private String manufacturerData;
        private String serialNumber;
        private String vendorName;
        private String no;
        private int status;

//NEType	NEName	Board Name	Inventory Unit Type	BOM Code(Path_Number)	Manufacturer Data	Serial Number	Vendor Name
//BTS	2G_Q01061E_HCM	DX   DUG 20 01                0	HW	KDU 137 569/1         R2B		X174320245	Ericsson
        public Builder(Long id, Long parentId, String value, String icon, String type) {
            this.id = id;
            this.parentId = parentId;
            this.value = value;
            this.icon = icon;
            this.type = type;
        }

        public Builder neType(String neType) {
            this.neType = neType;
            return this;
        }

        public Builder neName(String neName) {
            this.neName = neName;
            return this;
        }

        public Builder neFdn(String neFdn) {
            this.neFdn = neFdn;
            return this;
        }

        public Builder inventoryUnitType(String inventoryUnitType) {
            this.inventoryUnitType = inventoryUnitType;
            return this;
        }

        public Builder bomCode(String bomCode) {
            this.bomCode = bomCode;
            return this;
        }

        public Builder manufacturerData(String manufacturerData) {
            this.manufacturerData = manufacturerData;
            return this;
        }

        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder vendorName(String vendorName) {
            this.vendorName = vendorName;
            return this;
        }

        public Builder no(String no) {
            this.no = no;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public TreeTableDataView build() {
            return new TreeTableDataView(this);
        }

    }

    public TreeTableDataView(Builder builder) {
        id = builder.id;
        parentId = builder.parentId;
        value = builder.value;
        icon = builder.icon;
        type = builder.type;
        neType = builder.neType;
        neName = builder.neName;
        neFdn = builder.neFdn;
        inventoryUnitType = builder.inventoryUnitType;
        bomCode = builder.bomCode;
        manufacturerData = builder.manufacturerData;
        serialNumber = builder.serialNumber;
        vendorName = builder.vendorName;
        no = builder.no;
        name = builder.name;
        status = builder.status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNeType() {
        return neType;
    }

    public void setNeType(String neType) {
        this.neType = neType;
    }

    public String getNeName() {
        return neName;
    }

    public void setNeName(String neName) {
        this.neName = neName;
    }

    public String getNeFdn() {
        return neFdn;
    }

    public void setNeFdn(String neFdn) {
        this.neFdn = neFdn;
    }

    public String getInventoryUnitType() {
        return inventoryUnitType;
    }

    public void setInventoryUnitType(String inventoryUnitType) {
        this.inventoryUnitType = inventoryUnitType;
    }

    public String getBomCode() {
        return bomCode;
    }

    public void setBomCode(String bomCode) {
        this.bomCode = bomCode;
    }

    public String getManufacturerData() {
        return manufacturerData;
    }

    public void setManufacturerData(String manufacturerData) {
        this.manufacturerData = manufacturerData;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
}
