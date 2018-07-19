package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.dao.ITlink;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.jdbc.DbSql;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class TlinkDAO extends GenericDAO implements ITlink {

    private static Logger logger = LogManager.getLogger(TlinkDAO.class);

    @Override
    public List<TLinkBO> findAll(String id, String name,int startRow,int endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TLINK.fn_find(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            
            vars.add(id);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(name);
            vars.add(startRow);
            vars.add(endRow);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TLinkBO item = new TLinkBO();
                    item.setId(rs.getString("TLINK_ID"));
                    item.setNodeId1(rs.getString("TNODE_ID1"));
                    item.setNodeId2(rs.getString("TNODE_ID2"));
                    item.setDistanceM(rs.getString("DISTANCE_M") == null ? "" : rs.getString("DISTANCE_M"));
                    item.setExtraData1(rs.getString("EXTRA_DATA1"));
                    item.setExtraData2(rs.getString("EXTRA_DATA2"));
                    item.settLinkName(rs.getString("TLINK_NAME"));
                    item.setNote(rs.getString("NOTE"));
                    item.setMucThu1(rs.getString("MUC_THU1") == null ? "" : rs.getString("MUC_THU1"));
                    item.setMucThu2(rs.getString("MUC_THU2") == null ? "" : rs.getString("MUC_THU2"));
                    item.setIp1(rs.getString("IP1") == null ? "" : rs.getString("IP1"));
                    item.setIp2(rs.getString("IP2")== null ? "" : rs.getString("IP2"));
                    item.setPort1(rs.getString("PORT1") == null ? "" : rs.getString("PORT1"));
                    item.setPort2(rs.getString("PORT2") == null ? "" : rs.getString("PORT2"));
                    item.setNodeName1(rs.getString("tnode_name1"));
                    item.setNodeName2(rs.getString("tnode_name2"));
                    item.setBandWidth(rs.getString("bandwidth") == null ? "" : rs.getString("bandwidth"));
                    item.setBandWidthDv(rs.getString("bandwidth_dv") == null ? "" : rs.getString("bandwidth_dv"));
//                    item.setCode(rs.getString("TNODE_CODE"));
//                    item.setName(rs.getString("TNODE_NAME"));
                    return item;
                }
            }, vars);

            return (List<TLinkBO>) list;
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
    public int update(TLinkBO temp, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TLINK.fn_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(temp.getId());

            vars.add(temp.getNodeId2());
            vars.add(temp.getNodeId1());
            vars.add(temp.getMucThu1());
            vars.add(temp.getMucThu2());
            vars.add(temp.getIp2());
            vars.add(temp.getIp1());

            vars.add(temp.getDistanceM());
            vars.add(temp.getPort1());
            vars.add(temp.getPort2());
            vars.add(temp.getExtraData1());
            vars.add(temp.getExtraData2());
            vars.add(temp.gettLinkName());
            vars.add(temp.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);

            return iRet;
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
    public int insert(TLinkBO temp, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TLINK.fn_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(temp.getNodeId2());
            vars.add(temp.getNodeId1());
            vars.add(temp.getMucThu1());
            vars.add(temp.getMucThu2());
            vars.add(temp.getIp2());
            vars.add(temp.getIp1());

            vars.add(temp.getDistanceM());
            vars.add(temp.getPort1());
            vars.add(temp.getPort2());
            vars.add(temp.getExtraData1());
            vars.add(temp.getExtraData2());
            vars.add(temp.gettLinkName());
            vars.add(temp.getNote());
            vars.add(userId);

            // int iRet = sqlTemplate.executeUpdateFunction(querySql,
            //        vars);
            DbSql sqlTemplate = new DbSql(conn);
            int iRet = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            
            return iRet;
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
    public int getTotal(String id, String name) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TLINK.fn_find_total(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(null);
            vars.add(name);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int iRet = sqlTemplate.queryFunctionForInt(querySql, vars);

            return iRet;
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
    public int delete(TLinkBO temp, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TLINK.fn_delete(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(temp.getId());
            vars.add(userId);

            // int iRet = sqlTemplate.executeUpdateFunction(querySql,
            //        vars);
            DbSql sqlTemplate = new DbSql(conn);
            int iRet = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            
            return iRet;
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
