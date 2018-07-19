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
public class DataViewBO {
//select rack.id as EQ_RACK_id,rack.rack_no eq_rack_no,rack.inventory_unit_type eq_rack_inventory_unit_type,
//                     rack.vendor_name eq_rack_vendor_name,rack.serial_number eq_rack_serial_number,rack.manufacturer_Data eq_rack_manufacturer_Data, 
//                     rack.bom_code eq_rack_bom_code,rack.name eq_rack_name,rack.ne_fdn as eq_rack_ne_fdn,rack.ne_name as eq_rack_ne_name,
//                    rack.ne_type as eq_rack_ne_type,rack.status as eq_rack_status,                    
//                    --
//                    subrack.id as EQ_subrack_id,subrack.sub_rack_no eq_subrack_no,subrack.inventory_unit_type as eq_subrack_inventory_unit_type
//                    ,subrack.vendor_name as eq_subrack_vendor_name
//                    ,subrack.serial_number as eq_subrack_serial_number
//                    ,subrack.manufacturer_Data  as eq_subrack_manufacturer_Data
//                    ,subrack.bom_code as eq_subrack_bom_code,subrack.name  eq_subrack_name
//                    ,subrack.ne_fdn as eq_subrack_ne_fdn
//                    ,subrack.ne_name as eq_subrack_ne_name
//                    ,subrack.ne_type as eq_subrack_ne_type,
//                    subrack.status as eq_subrack_status,
//                    subrack.eq_rack_id as eq_subrack_eq_rack_id,                    
//                                
//                    slot.id as EQ_slot_id,slot.slot_no eq_slot_no,slot.status as eq_slot_status,slot.eq_sub_rack_id as eq_slot_eq_sub_rack_id,
//                    card.id as EQ_card_id,card.card_no as eq_card_no
//                    ,card.inventory_unit_type as eq_card_inventory_unit_type
//                    , card.vendor_name as eq_card_vendor_name
//                    , card.serial_number as eq_card_serial_number
//                    , card.manufacturer_Data  as eq_card_manufacturer_Data
//                    ,card.bom_code as eq_card_bom_code            
//                    ,card.ne_fdn as eq_card_ne_fdn//                    ,card.ne_name as eq_card_ne_name                                //                    ,card.ne_type as eq_card_ne_type//                    ,card.status as eq_card_status//                    ,card.name as eq_card_name//                    ,card.eq_slot_id as eq_card_eq_slot_id
//                    port.id as EQ_port_id,port.port_no eq_port_no
//                    ,port.status eq_port_status
//                    ,port.eq_card_id eq_port_eq_card_id
//                                                              

    private Long id;
    private Long parentId;
    private String value;
    private String icon;
    private String type;

    private Long rackId;
    private String rackNo;
    private String rackInventoryUnitType;
    private String rackVendorName;
    private String rackSerialNumber;
    private String rackManufacturerData;
    private String rackBomCode;
    private String rackName;
    private String rackNeType;
    private String rackNeName;
    private String rackNeFdn;
    private int rackStatus;

    private Long subrackId;
    private String subrackNo;
    private String subrackInventoryUnitType;
    private String subrackVendorName;
    private String subrackSerialNumber;
    private String subrackManufacturerData;
    private String subrackBomCode;
    private String subrackName;
    private String subrackNeType;
    private String subrackNeName;
    private String subrackNeFdn;
    private int subrackStatus;
    private Long subrackEqRackId;
    ////


    private Long slotId;
    private String slotNo;
    private int slotStatus;
    private Long slotEqSubRackId;
    
    private Long cardId;
    private String cardNo;
    private String cardInventoryUnitType;
    private String cardVendorName;
    private String cardSerialNumber;
    private String cardManufacturerData;
    private String cardBomCode;    
    private String cardNeType;
    private String cardNeName;
    private String cardNeFdn;
    private int cardStatus;
    private String cardName;
    private Long cardEqSlotId;
    
    
    
    
    

    private Long portId;
    private String portNo;
    private int portStatus;
    private Long portEqCardId;
    
    public DataViewBO() {
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

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public String getRackNo() {
        return rackNo;
    }

    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    public String getRackInventoryUnitType() {
        return rackInventoryUnitType;
    }

    public void setRackInventoryUnitType(String rackInventoryUnitType) {
        this.rackInventoryUnitType = rackInventoryUnitType;
    }

    public String getRackVendorName() {
        return rackVendorName;
    }

    public void setRackVendorName(String rackVendorName) {
        this.rackVendorName = rackVendorName;
    }

    public String getRackSerialNumber() {
        return rackSerialNumber;
    }

    public void setRackSerialNumber(String rackSerialNumber) {
        this.rackSerialNumber = rackSerialNumber;
    }

    public String getRackManufacturerData() {
        return rackManufacturerData;
    }

    public void setRackManufacturerData(String rackManufacturerData) {
        this.rackManufacturerData = rackManufacturerData;
    }

    public String getRackBomCode() {
        return rackBomCode;
    }

    public void setRackBomCode(String rackBomCode) {
        this.rackBomCode = rackBomCode;
    }

    public String getRackName() {
        return rackName;
    }

    public void setRackName(String rackName) {
        this.rackName = rackName;
    }

    public String getRackNeType() {
        return rackNeType;
    }

    public void setRackNeType(String rackNeType) {
        this.rackNeType = rackNeType;
    }

    public String getRackNeName() {
        return rackNeName;
    }

    public void setRackNeName(String rackNeName) {
        this.rackNeName = rackNeName;
    }

    public String getRackNeFdn() {
        return rackNeFdn;
    }

    public void setRackNeFdn(String rackNeFdn) {
        this.rackNeFdn = rackNeFdn;
    }

    public int getRackStatus() {
        return rackStatus;
    }

    public void setRackStatus(int rackStatus) {
        this.rackStatus = rackStatus;
    }

    public Long getSubrackId() {
        return subrackId;
    }

    public void setSubrackId(Long subrackId) {
        this.subrackId = subrackId;
    }

    public String getSubrackNo() {
        return subrackNo;
    }

    public void setSubrackNo(String subrackNo) {
        this.subrackNo = subrackNo;
    }

    public String getSubrackInventoryUnitType() {
        return subrackInventoryUnitType;
    }

    public void setSubrackInventoryUnitType(String subrackInventoryUnitType) {
        this.subrackInventoryUnitType = subrackInventoryUnitType;
    }

    public String getSubrackVendorName() {
        return subrackVendorName;
    }

    public void setSubrackVendorName(String subrackVendorName) {
        this.subrackVendorName = subrackVendorName;
    }

    public String getSubrackSerialNumber() {
        return subrackSerialNumber;
    }

    public void setSubrackSerialNumber(String subrackSerialNumber) {
        this.subrackSerialNumber = subrackSerialNumber;
    }

    public String getSubrackManufacturerData() {
        return subrackManufacturerData;
    }

    public void setSubrackManufacturerData(String subrackManufacturerData) {
        this.subrackManufacturerData = subrackManufacturerData;
    }

    public String getSubrackBomCode() {
        return subrackBomCode;
    }

    public void setSubrackBomCode(String subrackBomCode) {
        this.subrackBomCode = subrackBomCode;
    }

    public String getSubrackName() {
        return subrackName;
    }

    public void setSubrackName(String subrackName) {
        this.subrackName = subrackName;
    }

    public String getSubrackNeType() {
        return subrackNeType;
    }

    public void setSubrackNeType(String subrackNeType) {
        this.subrackNeType = subrackNeType;
    }

    public String getSubrackNeName() {
        return subrackNeName;
    }

    public void setSubrackNeName(String subrackNeName) {
        this.subrackNeName = subrackNeName;
    }

    public String getSubrackNeFdn() {
        return subrackNeFdn;
    }

    public void setSubrackNeFdn(String subrackNeFdn) {
        this.subrackNeFdn = subrackNeFdn;
    }

    public int getSubrackStatus() {
        return subrackStatus;
    }

    public void setSubrackStatus(int subrackStatus) {
        this.subrackStatus = subrackStatus;
    }

    public Long getSubrackEqRackId() {
        return subrackEqRackId;
    }

    public void setSubrackEqRackId(Long subrackEqRackId) {
        this.subrackEqRackId = subrackEqRackId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }

    public int getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(int slotStatus) {
        this.slotStatus = slotStatus;
    }

    public Long getSlotEqSubRackId() {
        return slotEqSubRackId;
    }

    public void setSlotEqSubRackId(Long slotEqSubRackId) {
        this.slotEqSubRackId = slotEqSubRackId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardInventoryUnitType() {
        return cardInventoryUnitType;
    }

    public void setCardInventoryUnitType(String cardInventoryUnitType) {
        this.cardInventoryUnitType = cardInventoryUnitType;
    }

    public String getCardVendorName() {
        return cardVendorName;
    }

    public void setCardVendorName(String cardVendorName) {
        this.cardVendorName = cardVendorName;
    }

    public String getCardSerialNumber() {
        return cardSerialNumber;
    }

    public void setCardSerialNumber(String cardSerialNumber) {
        this.cardSerialNumber = cardSerialNumber;
    }

    public String getCardManufacturerData() {
        return cardManufacturerData;
    }

    public void setCardManufacturerData(String cardManufacturerData) {
        this.cardManufacturerData = cardManufacturerData;
    }

    public String getCardBomCode() {
        return cardBomCode;
    }

    public void setCardBomCode(String cardBomCode) {
        this.cardBomCode = cardBomCode;
    }

    public String getCardNeType() {
        return cardNeType;
    }

    public void setCardNeType(String cardNeType) {
        this.cardNeType = cardNeType;
    }

    public String getCardNeName() {
        return cardNeName;
    }

    public void setCardNeName(String cardNeName) {
        this.cardNeName = cardNeName;
    }

    public String getCardNeFdn() {
        return cardNeFdn;
    }

    public void setCardNeFdn(String cardNeFdn) {
        this.cardNeFdn = cardNeFdn;
    }

    public int getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(int cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Long getCardEqSlotId() {
        return cardEqSlotId;
    }

    public void setCardEqSlotId(Long cardEqSlotId) {
        this.cardEqSlotId = cardEqSlotId;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo;
    }

    public int getPortStatus() {
        return portStatus;
    }

    public void setPortStatus(int portStatus) {
        this.portStatus = portStatus;
    }

    public Long getPortEqCardId() {
        return portEqCardId;
    }

    public void setPortEqCardId(Long portEqCardId) {
        this.portEqCardId = portEqCardId;
    }

  

}
