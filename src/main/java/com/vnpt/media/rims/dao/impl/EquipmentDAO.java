package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.CardBO;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import com.vnpt.media.rims.bean.DataViewBO;
import com.vnpt.media.rims.bean.PortBO;
import com.vnpt.media.rims.bean.RackBO;
import com.vnpt.media.rims.bean.SlotBO;
import com.vnpt.media.rims.bean.SubRackBO;
import com.vnpt.media.rims.dao.IEquipment;
import com.vnpt.media.rims.exception.ServiceException;

public class EquipmentDAO extends GenericDAO implements IEquipment {

    private static Logger logger = LogManager.getLogger(EquipmentDAO.class);
//select x.id as EQ_RACK_id--, x.creation_date
//     , y.id as EQ_SUB_RACK_id--, y.introduced as biopsy_p0_introduction
//     , z.id as EQ_SLOT_id--, z.introduced as biopsy_p1_introduction 
//from EQ_RACK  x 
//left join EQ_SUB_RACK  y 
//    on y.EQ_RACK_id = x.id 
//left join EQ_SLOT  z 
//    on z.EQ_sub_rack_id = y.id 
//order by x.id, y.id, z.id;

    @Override
    public List<DataViewBO> getDataView(Long nodeId,int startRow, int endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_EQUIPMENT.fc_dataView(?,?,?)}";



            List<Object> vars = new Vector<Object>();
            vars.add(nodeId);
            vars.add(startRow);
            vars.add(endRow);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list;
            list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DataViewBO dataViewBO = new DataViewBO();

                    long rackIdTemp = rs.getLong("EQ_RACK_id");
                    dataViewBO.setRackId(rs.wasNull() ? -1L : rackIdTemp);
                    dataViewBO.setRackNo(rs.getString("eq_rack_no"));
                    dataViewBO.setRackInventoryUnitType(rs.getString("eq_rack_inventory_unit_type"));
                    dataViewBO.setRackVendorName(rs.getString("eq_rack_vendor_name"));
                    dataViewBO.setRackSerialNumber(rs.getString("eq_rack_serial_number"));
                    dataViewBO.setRackManufacturerData(rs.getString("eq_rack_manufacturer_Data"));
                    dataViewBO.setRackBomCode(rs.getString("eq_rack_bom_code"));
                    dataViewBO.setRackName(rs.getString("eq_rack_name"));
                    dataViewBO.setRackNeType(rs.getString("eq_rack_ne_type"));
                    dataViewBO.setRackNeName(rs.getString("eq_rack_ne_name"));
                    dataViewBO.setRackNeFdn(rs.getString("eq_rack_ne_fdn"));
                    dataViewBO.setRackStatus(rs.getInt("eq_rack_status"));
                    //
                    long subrackIdTemp = rs.getLong("EQ_subrack_id");
                    dataViewBO.setSubrackId(rs.wasNull() ? -1L : subrackIdTemp);
                    dataViewBO.setSubrackNo(rs.getString("eq_subrack_no"));
                    dataViewBO.setSubrackInventoryUnitType(rs.getString("eq_subrack_inventory_unit_type"));
                    dataViewBO.setSubrackVendorName(rs.getString("eq_subrack_vendor_name"));
                    dataViewBO.setSubrackSerialNumber(rs.getString("eq_subrack_serial_number"));
                    dataViewBO.setSubrackManufacturerData(rs.getString("eq_subrack_manufacturer_Data"));
                    dataViewBO.setSubrackBomCode(rs.getString("eq_subrack_bom_code"));
                    dataViewBO.setSubrackName(rs.getString("eq_subrack_name"));

                    dataViewBO.setSubrackNeType(rs.getString("eq_subrack_ne_type"));
                    dataViewBO.setSubrackNeName(rs.getString("eq_subrack_ne_name"));
                    dataViewBO.setSubrackNeFdn(rs.getString("eq_subrack_ne_fdn"));
                    dataViewBO.setSubrackStatus(rs.getInt("eq_subrack_status"));
                    dataViewBO.setSubrackEqRackId(rs.getLong("eq_subrack_eq_rack_id"));

                    long slotIdTemp = rs.getLong("EQ_slot_id");
                    dataViewBO.setSlotId(rs.wasNull() ? -1L : slotIdTemp);
                    dataViewBO.setSlotNo(rs.getString("eq_slot_no"));
                    dataViewBO.setSlotStatus(rs.getInt("eq_slot_status"));
                    dataViewBO.setSlotEqSubRackId(rs.getLong("eq_slot_eq_sub_rack_id"));
                    
                    long cardIdTemp = rs.getLong("EQ_card_id");
                    dataViewBO.setCardId(rs.wasNull() ? -1L : cardIdTemp);
                    dataViewBO.setCardNo("");
                    dataViewBO.setCardInventoryUnitType(rs.getString("eq_card_inventory_unit_type"));
                    dataViewBO.setCardVendorName(rs.getString("eq_card_vendor_name"));
                    dataViewBO.setCardSerialNumber(rs.getString("eq_card_serial_number"));
                    dataViewBO.setCardManufacturerData(rs.getString("eq_card_manufacturer_Data"));
                    dataViewBO.setCardBomCode(rs.getString("eq_card_bom_code"));
                    dataViewBO.setCardNeType(rs.getString("eq_card_ne_type"));
                    dataViewBO.setCardNeName(rs.getString("eq_card_ne_name"));
                    dataViewBO.setCardNeFdn(rs.getString("eq_card_ne_fdn"));
                    dataViewBO.setCardStatus(rs.getInt("eq_card_status"));
                    dataViewBO.setCardName(rs.getString("eq_card_name"));
                    dataViewBO.setCardEqSlotId(rs.getLong("eq_card_eq_slot_id"));
                    
                    
                    
                    

                    long portIdTemp = rs.getLong("EQ_port_id");
                    dataViewBO.setPortId(rs.wasNull() ? -1L : portIdTemp);
                    dataViewBO.setPortNo(rs.getString("eq_port_no"));
                    dataViewBO.setPortStatus(rs.getInt("eq_port_status"));
                    dataViewBO.setPortEqCardId(rs.getLong("eq_port_eq_card_id"));
                    
                    return dataViewBO;
                }

            }, vars
            );

            return (List<DataViewBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public RackBO viewRack(String id) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_EQUIPMENT.fc_view_rack(?)}";



            List<Object> vars = new Vector<Object>();
            vars.add(Long.valueOf(id.trim()));

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            RackBO temp = (RackBO) sqlTemplate.queryFunctionForObject(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    RackBO rackBO = new RackBO();
                    rackBO.setId(rs.getLong("id"));
                    rackBO.setRackNo(rs.getString("rack_no"));
                    rackBO.setInventoryUnitId(rs.getString("inventory_unit_id"));
                    rackBO.setRackType(rs.getString("rack_type"));
                    rackBO.setInventoryUnitType(rs.getString("inventory_unit_type"));
                    rackBO.setVendorUnitFamilyType(rs.getString("vendor_unit_family_type"));
                    rackBO.setVendorUnitTypeNumber(rs.getString("vendor_unit_type_number"));
                    rackBO.setVendorName(rs.getString("Vendor_name"));
                    rackBO.setSerialNumber(rs.getString("serial_number"));
                    rackBO.setHardwareVersion(rs.getString("hardware_version"));
                    rackBO.setDateOfManufacture(rs.getString("date_of_manufacture"));
                    rackBO.setDateOfLastService(rs.getString("date_of_last_service"));
                    rackBO.setUnitPositioni(rs.getString("unit_position"));
                    rackBO.setManufactoryData(rs.getString("manufacturer_data"));
                    rackBO.setIssueNumber(rs.getString("issue_number"));
                    rackBO.setBomCode(rs.getString("bom_code"));
                    rackBO.setShareMode(rs.getString("share_mode"));
                    rackBO.setNeFdn(rs.getString("ne_fdn"));
                    rackBO.setNeName(rs.getString("ne_name"));
                    rackBO.setNeType(rs.getString("ne_type"));
                    rackBO.setBomRackType(rs.getString("bom_rack_type"));
                    return rackBO;
                }

            }, vars
            );

            return temp;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public SubRackBO viewSubRack(String id) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_EQUIPMENT.fc_view_sub_rack(?)}";



            List<Object> vars = new Vector<Object>();
            vars.add(Long.valueOf(id.trim()));

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            SubRackBO temp = (SubRackBO) sqlTemplate.queryFunctionForObject(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    ID
//RACK_NO
//FRAME_NO
//FRAME_TYPE
//INVENTORY_UNIT_ID
//RACK_FRAME_NO
//BOM_RACK_TYPE
//INVENTORY_UNIT_TYPE
//VENDOR_UNIT_FAMILY_TYPE
//VENDOR_UNIT_TYPE_NUMBER
//VENDOR_NAME
//SERIAL_NUMBER
//HARDWARE_VERSION
//DATE_OF_MANUFACTURE
//DATE_OF_LAST_SERVICE
//UNIT_POSITION
//MANUFACTURER_DATA
//ISSUE_NUMBER
//BOM_CODE
//SHARE_MODE
//MODULE_NO
//NE_FDN
//NE_NAME
//NE_TYPE
//EQ_RACK_ID
                    SubRackBO subRackBO = new SubRackBO();
                    subRackBO.setId(rs.getLong("id"));
                    subRackBO.setRackNo(rs.getString("rack_no"));
                    subRackBO.setFrameNo(rs.getString("FRAME_NO"));
                    subRackBO.setFrameType(rs.getString("FRAME_TYPE"));
                    subRackBO.setInventoryUnitId(rs.getString("inventory_unit_id"));
                    subRackBO.setRackFrameNo(rs.getString("RACK_FRAME_NO"));
                    subRackBO.setBomRackType(rs.getString("BOM_RACK_TYPE"));
                    subRackBO.setInventoryUnitType(rs.getString("inventory_unit_type"));
                    subRackBO.setVendorUnitFamilyType(rs.getString("vendor_unit_family_type"));
                    subRackBO.setVendorUnitTypeNumber(rs.getString("vendor_unit_type_number"));
                    subRackBO.setVendorName(rs.getString("Vendor_name"));
                    subRackBO.setSerialNumber(rs.getString("serial_number"));
                    subRackBO.setHardwareVersion(rs.getString("hardware_version"));
                    subRackBO.setDateOfManufacture(rs.getString("date_of_manufacture"));
                    subRackBO.setDateOfLastService(rs.getString("date_of_last_service"));
                    subRackBO.setUnitPosition(rs.getString("unit_position"));
                    subRackBO.setManufacturerData(rs.getString("manufacturer_data"));
                    subRackBO.setIssueNumber(rs.getString("issue_number"));
                    subRackBO.setBomCode(rs.getString("bom_code"));
                    subRackBO.setShareMode(rs.getString("share_mode"));
                    subRackBO.setModuleNo(rs.getString("module_no"));
                    subRackBO.setNeFdn(rs.getString("ne_fdn"));
                    subRackBO.setNeName(rs.getString("ne_name"));
                    subRackBO.setNeType(rs.getString("ne_type"));
                    subRackBO.setEqRackId(rs.getLong("EQ_RACK_ID"));
                    return subRackBO;
                }

            }, vars
            );

            return temp;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public SlotBO viewSlot(String id) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_EQUIPMENT.fc_view_slot(?)}";



            List<Object> vars = new Vector<Object>();
            vars.add(Long.valueOf(id.trim()));

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            SlotBO temp = (SlotBO) sqlTemplate.queryFunctionForObject(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    SlotBO slotBO = new SlotBO();
//                    ID
//RACK_NO
//FRAME_NO
//SLOT_NO
//SLOT_POS
//INVENTORY_UNIT_ID
//INVENTORY_UNIT_TYPE
//VENDOR_UNIT_FAMILY_TYPE
//VENDOR_UNIT_TYPE_NUMBER
//VENDOR_NAME
//SERIAL_NUMBER
//HARDWARE_VERSION
//DATE_OF_MANUFACTURE
//DATE_OF_LAST_SERVICE
//UNIT_POSITION
//MANUFACTURER_DATA
//SHARE_MODE
//NE_FDN
//NE_NAME
//NE_TYPE
//EQ_SUB_RACK_ID
//BOM_CODE
                    slotBO.setId(rs.getLong("id"));
                    slotBO.setRackNo(rs.getString("rack_no"));
                    slotBO.setFrameNo(rs.getString("frame_no"));
                    slotBO.setSlotNo(rs.getString("slot_no"));
                    slotBO.setInventoryUnitId(rs.getString("inventory_unit_id"));
                    slotBO.setInventoryUnitType(rs.getString("inventory_unit_type"));
                    slotBO.setVendorUnitFamilyType(rs.getString("vendor_unit_family_type"));
                    slotBO.setVendorUnitTypeNumber(rs.getString("vendor_unit_type_number"));
                    slotBO.setVendorName(rs.getString("Vendor_name"));
                    slotBO.setSerialNumber(rs.getString("serial_number"));
                    slotBO.setHardwareVersion(rs.getString("hardware_version"));
                    slotBO.setDateOfManufacture(rs.getString("date_of_manufacture"));
                    slotBO.setDateOfLastService(rs.getString("date_of_last_service"));
                    slotBO.setUnitPosition(rs.getString("unit_position"));
                    slotBO.setManufacturerData(rs.getString("manufacturer_data"));

                    slotBO.setBomCode(rs.getString("bom_code"));
                    slotBO.setShareMode(rs.getString("share_mode"));
                    slotBO.setNeFdn(rs.getString("ne_fdn"));
                    slotBO.setNeName(rs.getString("ne_name"));
                    slotBO.setNeType(rs.getString("ne_type"));
                    slotBO.setEqSubRackId(rs.getLong("EQ_SUB_RACK_ID"));
                    return slotBO;
                }

            }, vars
            );

            return temp;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public CardBO viewCard(String id) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_EQUIPMENT.fc_view_card(?)}";



            List<Object> vars = new Vector<Object>();
            vars.add(Long.valueOf(id.trim()));

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            CardBO temp = (CardBO) sqlTemplate.queryFunctionForObject(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CardBO cardBO = new CardBO();

//////INVENTORY_UNIT_TYPE//VENDOR_UNIT_FAMILY_TYPE//VENDOR_UNIT_TYPE_NUMBER
//VENDOR_NAME//SERIAL_NUMBER//HARDWARE_VERSION//DATE_OF_MANUFACTURE//DATE_OF_LAST_SERVICE
//UNIT_POSITION
//MANUFACTURER_DATA
//
//LOGIC_VER
//BIOS_VER
//BIOS_VER_EX
//LAN_VER
//MBUS_VER
//ISSUE_NUMBER
//BOM_CODE
//SHARE_MODE
//NE_FDN
//NE_NAME
//NE_TYPE
//EQ_SLOT_ID
                    cardBO.setId(rs.getLong("id"));
                    cardBO.setRackNo(rs.getString("rack_no"));
                    cardBO.setFrameNo(rs.getString("Frame_no"));
                    cardBO.setSlotNo(rs.getString("slot_no"));
                    cardBO.setSlotPos(rs.getString("slot_pos"));
                    cardBO.setSubSlotPos(rs.getString("SUB_SLOT_NO"));
                    cardBO.setInventoryUnitId(rs.getString("inventory_unit_id"));
                    cardBO.setModuleNo(rs.getString("MODULE_NO"));
                    cardBO.setPortNo(rs.getString("PORT_NO"));
                    cardBO.setBoardName(rs.getString("BOARD_NAME"));
                    cardBO.setBoardType(rs.getString("BOARD_TYPE"));
                    cardBO.setInventoryUnitType(rs.getString("inventory_unit_type"));
                    cardBO.setVendorUnitFamilyType(rs.getString("vendor_unit_family_type"));
                    cardBO.setVendorUnitTypeNumber(rs.getString("vendor_unit_type_number"));
                    cardBO.setVendorName(rs.getString("Vendor_name"));
                    cardBO.setSerialNumber(rs.getString("serial_number"));
                    cardBO.setHardwareVersion(rs.getString("hardware_version"));
                    cardBO.setDateOfManufacture(rs.getString("date_of_manufacture"));
                    cardBO.setDateOfLastService(rs.getString("date_of_last_service"));
                    cardBO.setUnitPosition(rs.getString("unit_position"));
                    cardBO.setManufacturerData(rs.getString("manufacturer_data"));
                    cardBO.setSoftVer(rs.getString("SOFT_VER"));
                    cardBO.setLogicVer(rs.getString("LOGIC_VER"));
                    cardBO.setBiosVer(rs.getString("BIOS_VER"));
                    cardBO.setBiosVerEx(rs.getString("BIOS_VER_EX"));
                    cardBO.setLanVer(rs.getString("LAN_VER"));
                    cardBO.setMbusVer(rs.getString("MBUS_VER"));
                    cardBO.setIssueNumber(rs.getString("issue_number"));
                    cardBO.setBomCode(rs.getString("bom_code"));
                    cardBO.setShareMode(rs.getString("share_mode"));
                    cardBO.setNeFdn(rs.getString("ne_fdn"));
                    cardBO.setNeName(rs.getString("ne_name"));
                    cardBO.setNeType(rs.getString("ne_type"));
                    cardBO.setEqSlotId(rs.getLong("EQ_SLOT_ID"));
                    return cardBO;
                }

            }, vars
            );

            return temp;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public PortBO viewPort(String id) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_EQUIPMENT.fc_view_port(?)}";



            List<Object> vars = new Vector<Object>();
            vars.add(Long.valueOf(id.trim()));

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            PortBO temp = (PortBO) sqlTemplate.queryFunctionForObject(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PortBO portBO = new PortBO();
                    portBO.setId(rs.getLong("id"));
                    portBO.setRackNo(rs.getString("rack_no"));
                    portBO.setFrameNo(rs.getString("FRAME_NO"));
                    portBO.setSlotNo(rs.getString("SLOT_NO"));
                    portBO.setSlotPos(rs.getString("SLOT_POS"));
                    portBO.setSubSlotPos(rs.getString("SUB_SLOT_NO"));
                    portBO.setInventoryUnitId(rs.getString("inventory_unit_id"));
                    portBO.setPortNo(rs.getString("PORT_NO"));
                    portBO.setInventoryUnitType(rs.getString("inventory_unit_type"));
                    portBO.setVendorUnitFamilyType(rs.getString("vendor_unit_family_type"));
                    portBO.setVendorUnitTypeNumber(rs.getString("vendor_unit_type_number"));
                    portBO.setVendorName(rs.getString("Vendor_name"));
                    portBO.setSerialNumber(rs.getString("serial_number"));
                    portBO.setHardwareVersion(rs.getString("hardware_version"));
                    portBO.setDateOfManufacture(rs.getString("date_of_manufacture"));
                    portBO.setDateOfLastService(rs.getString("date_of_last_service"));
                    portBO.setUnitPosition(rs.getString("unit_position"));
                    portBO.setManufacturerData(rs.getString("manufacturer_data"));

                    portBO.setMacAdd(rs.getString("MAC_ADD"));
                    portBO.setBomCode(rs.getString("bom_code"));
                    portBO.setShareMode(rs.getString("share_mode"));
                    portBO.setNeFdn(rs.getString("ne_fdn"));
                    portBO.setNeName(rs.getString("ne_name"));
                    portBO.setNeType(rs.getString("ne_type"));
                    portBO.setEqCardId(rs.getLong("EQ_CARD_ID"));
                    return portBO;
                }

            }, vars
            );

            return temp;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }
}
