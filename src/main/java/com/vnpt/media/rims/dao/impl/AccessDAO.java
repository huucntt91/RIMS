package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
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
import com.vnpt.media.rims.dao.*;

public class AccessDAO extends GenericDAO implements IAccess {

    private static Logger logger = LogManager.getLogger(AccessDAO.class);

    @Override
    public List<DsLamBO> findAllDsLam(Integer startRow, Integer endRow, String tNodeId,String typeId,
            String tNodeCode, String tNodeName,
            String khuVucId, String provinceId, String districtId, String wardsId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DSLAM.fn_find(?,?,?,?,?,?,?,?,?,?) }";
//       
             

            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tNodeId);
            vars.add(typeId);
            vars.add(tNodeCode);
            vars.add(tNodeName);
            vars.add(khuVucId);
            vars.add(provinceId);
            vars.add(districtId);
            vars.add(wardsId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DsLamBO item = new DsLamBO();
                    item.setId(rs.getString("TNODE_ID"));
                    item.setCode(rs.getString("TNODE_CODE"));
                    item.setName(rs.getString("TNODE_NAME"));
                    item.setChaId(rs.getString("tlist_node_cha_id"));
                    item.setChaName(rs.getString("tlist_node_cha_name"));
                    item.setDongTBiName(rs.getString("ten_dong_tbi"));
                    item.setDongTBiId(String.valueOf(rs.getInt("DONG_TBI_ID")));
                    item.setStatus(rs.getString("status"));
                    item.setTotalSlot(rs.getString("total_slot"));
                    item.setTypeId(rs.getString("TNODE_TYPE_ID"));
                    item.setBuildingCode(rs.getString("ma_building"));
                    item.setVodLan(rs.getString("VOD_LAN"));
                    item.setSerialNo(rs.getString("SERIAL_NO"));
                    item.setNote(rs.getString("note"));
                    item.setIp(rs.getString("ip"));
                    item.setTenTinh(rs.getString("ten_tinh_tp"));
                    item.setTypeName(rs.getString("type_name"));
                    return item;
                }
            }, vars);

            return (List<DsLamBO>) list;
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
    public List<SwitchBO> findAllSwitch(Integer startRow, Integer endRow, String tNodeId,
            String tNodeCode, String tNodeName,
            String khuVucId, String provinceId, String districtId, String wardsId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_SWITCH.fn_find(?,?,?,?,?,?,?,?,?) }";
//       
             

            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tNodeId);
            vars.add(tNodeCode);
            vars.add(tNodeName);
            vars.add(khuVucId);
            vars.add(provinceId);
            vars.add(districtId);
            vars.add(wardsId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    SwitchBO item = new SwitchBO();
                    item.setId(rs.getString("TNODE_ID"));
                    item.setCode(rs.getString("TNODE_CODE"));
                    item.setName(rs.getString("TNODE_NAME"));
                    item.setChaId(rs.getString("tlist_node_cha_id"));
                    item.setChaName(rs.getString("tlist_node_cha_name"));
                    item.setDongTBiName(rs.getString("ten_dong_tbi"));
                    item.setDongTBiId(String.valueOf(rs.getInt("DONG_TBI_ID")));
                    item.setStatus(rs.getString("status"));
                    item.setTotalSlot(rs.getString("total_slot"));
                    item.setTypeId(rs.getString("TNODE_TYPE_ID"));
                    item.setBuildingCode(rs.getString("ma_building"));
                    item.setVodLan(rs.getString("VOD_LAN"));
                    item.setSerialNo(rs.getString("SERIAL_NO"));
                    item.setNote(rs.getString("note"));
                    item.setIp(rs.getString("ip"));

                    return item;
                }
            }, vars);

            return (List<SwitchBO>) list;
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
    public List<GponBO> findAllGpon(Integer startRow, Integer endRow, String tNodeId,
            String tNodeCode, String tNodeName,
            String khuVucId, String provinceId, String districtId, String wardsId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_GPON.fn_find(?,?,?,?,?,?,?,?,?) }";
//       
             

            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tNodeId);
            vars.add(tNodeCode);
            vars.add(tNodeName);
            vars.add(khuVucId);
            vars.add(provinceId);
            vars.add(districtId);
            vars.add(wardsId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GponBO item = new GponBO();
                    item.setId(rs.getString("TNODE_ID"));
                    item.setCode(rs.getString("TNODE_CODE"));
                    item.setName(rs.getString("TNODE_NAME"));
                    item.setChaId(rs.getString("tlist_node_cha_id"));
                    item.setChaName(rs.getString("tlist_node_cha_name"));
                    item.setDongTBiName(rs.getString("ten_dong_tbi"));
                    item.setDongTBiId(String.valueOf(rs.getInt("DONG_TBI_ID")));
                    item.setStatus(rs.getString("status"));
                    item.setTotalSlot(rs.getString("total_slot"));
                    item.setTypeId(rs.getString("TNODE_TYPE_ID"));
                    item.setBuildingCode(rs.getString("ma_building"));
                    item.setVodLan(rs.getString("VOD_LAN"));
                    item.setSerialNo(rs.getString("SERIAL_NO"));
                    item.setNote(rs.getString("note"));
                    item.setIp(rs.getString("ip"));
                    return item;
                }
            }, vars);

            return (List<GponBO>) list;
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
    public boolean addDsLam(DsLamBO access, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DSLAM.fn_insert(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
//       
             
            List<Object> vars = new Vector<Object>();
            vars.add(access.getCode());
            vars.add(access.getName());
            vars.add(access.getChaId());
            vars.add(access.getDongTBiId());
            vars.add("1");
            vars.add(access.getTotalSlot());
            vars.add(access.getBuildingId());
            vars.add(4);
            vars.add(access.getIp());
            vars.add(access.getVodLan());
            vars.add(access.getSerialNo());
            vars.add(access.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            sqlTemplate.queryFunctionForInt(querySql, vars);
            return true;
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
    public boolean addSwitch(SwitchBO access, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_SWITCH.fn_insert(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
//       
             
            List<Object> vars = new Vector<Object>();
            vars.add(access.getCode());
            vars.add(access.getName());
            vars.add(access.getChaId());
            vars.add(access.getDongTBiId());
            vars.add("1");
            vars.add(access.getTotalSlot());
            vars.add(access.getBuildingId());
            vars.add(5);
            vars.add(access.getIp());
            vars.add(access.getVodLan());
            vars.add(access.getSerialNo());
            vars.add(access.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            sqlTemplate.queryFunctionForInt(querySql, vars);
            return true;
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
    public boolean addGpon(GponBO access, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_GPON.fn_insert(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
//       
             
            List<Object> vars = new Vector<Object>();
            vars.add(access.getCode());
            vars.add(access.getName());
            vars.add(access.getChaId());
            vars.add(access.getDongTBiId());
            vars.add("1");
            vars.add(access.getTotalSlot());
            vars.add(access.getBuildingId());
            vars.add(6);
            vars.add(access.getIp());
            vars.add(access.getVodLan());
            vars.add(access.getSerialNo());
            vars.add(access.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            sqlTemplate.queryFunctionForInt(querySql, vars);
            return true;
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
    public boolean updateDsLam(DsLamBO access, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DSLAM.fn_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
             
            List<Object> vars = new Vector<Object>();
            vars.add(access.getId());
            vars.add(access.getCode());
            vars.add(access.getName());
            vars.add(access.getChaId());
            vars.add(access.getDongTBiId());
            vars.add("1");
            vars.add(access.getTotalSlot());
            vars.add(access.getBuildingId());
            vars.add(4);
            vars.add(access.getIp());
            vars.add(access.getVodLan());
            vars.add(access.getSerialNo());
            vars.add(access.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            sqlTemplate.queryFunctionForInt(querySql, vars);
            return true;
//            return (List<TNodeBO>) list;
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
    public boolean updateSwitch(SwitchBO access, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_SWITCH.fn_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
             
            List<Object> vars = new Vector<Object>();
            vars.add(access.getId());
            vars.add(access.getCode());
            vars.add(access.getName());
            vars.add(access.getChaId());
            vars.add(access.getDongTBiId());
            vars.add("1");
            vars.add(access.getTotalSlot());
            vars.add(access.getBuildingId());
            vars.add(5);
            vars.add(access.getIp());
            vars.add(access.getVodLan());
            vars.add(access.getSerialNo());
            vars.add(access.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            sqlTemplate.queryFunctionForInt(querySql, vars);
            return true;
//            return (List<TNodeBO>) list;
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
    public boolean updateGpon(GponBO access, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_GPON.fn_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
             
            List<Object> vars = new Vector<Object>();
            vars.add(access.getId());
            vars.add(access.getCode());
            vars.add(access.getName());
            vars.add(access.getChaId());
            vars.add(access.getDongTBiId());
            vars.add("1");
            vars.add(access.getTotalSlot());
            vars.add(access.getBuildingId());
            vars.add(6);
            vars.add(access.getIp());
            vars.add(access.getVodLan());
            vars.add(access.getSerialNo());
            vars.add(access.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            sqlTemplate.queryFunctionForInt(querySql, vars);
            return true;
//            return (List<TNodeBO>) list;
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
    public int getTotalDsLam(String tNodeId,String typeId, String tNodeCode, String tNodeName,
            String khuVucId, String provinceId, String districtId, String wardsId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DSLAM.fn_get_total(?,?,?,?,?,?,?,?) }";
//       
             

            List<Object> vars = new Vector<Object>();

            vars.add(tNodeId);
            vars.add(typeId);
            vars.add(tNodeCode);
            vars.add(tNodeName);
            vars.add(khuVucId);
            vars.add(provinceId);
            vars.add(districtId);
            vars.add(wardsId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            return sqlTemplate.queryFunctionForInt(querySql, vars);

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
    public int getTotalSwitch(String tNodeId, String tNodeCode, String tNodeName,
            String khuVucId, String provinceId, String districtId, String wardsId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_SWITCH.fn_get_total(?,?,?,?,?,?,?) }";
//       
             

            List<Object> vars = new Vector<Object>();

            vars.add(tNodeId);
            vars.add(tNodeCode);
            vars.add(tNodeName);
            vars.add(khuVucId);
            vars.add(provinceId);
            vars.add(districtId);
            vars.add(wardsId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            return sqlTemplate.queryFunctionForInt(querySql, vars);

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
    public int getTotalGpon(String tNodeId, String tNodeCode, String tNodeName,
            String khuVucId, String provinceId, String districtId, String wardsId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_GPON.fn_get_total(?,?,?,?,?,?,?) }";
//       
             

            List<Object> vars = new Vector<Object>();

            vars.add(tNodeId);
            vars.add(tNodeCode);
            vars.add(tNodeName);
            vars.add(khuVucId);
            vars.add(provinceId);
            vars.add(districtId);
            vars.add(wardsId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            return sqlTemplate.queryFunctionForInt(querySql, vars);

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
