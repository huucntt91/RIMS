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

public class CpeDAO extends GenericDAO implements ICpe {

    private static Logger logger = LogManager.getLogger(CpeDAO.class);

    @Override
    public List<CpeBO> findAll(Integer startRow, Integer endRow, String tNodeId, String tNodeCode, String tNodeName) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CPE.fn_find(?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tNodeId);
            vars.add(tNodeCode);
            vars.add(tNodeName);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CpeBO item = new CpeBO();
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
                    item.setAddress(rs.getString("address"));                    
                    item.setNote(rs.getString("note"));
                    item.setIp(rs.getString("ip"));
                    return item;
                }
            }, vars);

            return (List<CpeBO>) list;
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
    public boolean addCpe(CpeBO item, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CPE.fn_insert(?,?,?,?,?,?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(item.getCode());
            vars.add(item.getName());
            vars.add(item.getChaId());            
            vars.add(item.getDongTBiId());
            vars.add("1");
            vars.add(item.getTotalSlot());
            vars.add(item.getBuildingId());
            vars.add(4);
            vars.add(item.getIp());
            vars.add(item.getAddress());
            
            vars.add(item.getNote());
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
    public boolean updateCpe(CpeBO access, Long userId) throws DAOException {
         Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CPE.fn_update(?,?,?,?,?,?,?,?,?,?,?,?,?) }";

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
            vars.add(access.getAddress());
            
            vars.add(access.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            sqlTemplate.queryFunctionForInt(querySql,  vars);
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
    public int getTotal(String tNodeId, String tNodeCode, String tNodeName) throws DAOException {
         Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CPE.fn_get_total(?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            
            vars.add(tNodeId);
            vars.add(tNodeCode);
            vars.add(tNodeName);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            return sqlTemplate.queryFunctionForInt(querySql,  vars);

            
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
