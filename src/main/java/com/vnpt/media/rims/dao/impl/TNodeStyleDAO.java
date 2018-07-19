package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.dao.ITNodeStyle;
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

public class TNodeStyleDAO extends GenericDAO implements ITNodeStyle {

    private static Logger logger = LogManager.getLogger(TNodeStyleDAO.class);

    @Override
    public List<TnodeStyleBO> findAll(String id, String typeId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TNODESTYLE.fn_find(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(typeId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TnodeStyleBO item = new TnodeStyleBO();
                    item.setId(rs.getLong("TNODE_STYLE_ID"));
                    item.setTypeId(rs.getLong("TNODE_TYPE_ID"));
                    item.setUrl(rs.getString("IMAGE_URL"));
                    item.setStatus(rs.getString("STATUS_ICON"));
                    item.setTypeName(rs.getString("tnode_name"));
                    item.setNote(rs.getString("NOTE"));
                    item.setSize(rs.getString("STYLE_SIZE"));
                 
                    return item;
                }
            }, vars);

            return (List<TnodeStyleBO>) list;
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
    public boolean addTnodeStyle(TnodeStyleBO item, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TNODESTYLE.fn_insert(?,?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(item.getTypeId());
            vars.add(item.getUrl());
            vars.add(item.getStatus());
            vars.add(item.getNote());
            vars.add(item.getSize());
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
    public boolean updateTnodeStyle(TnodeStyleBO item, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TNODESTYLE.fn_update(?,?,?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(item.getId());
            vars.add(item.getTypeId());
            vars.add(item.getUrl());
            vars.add(item.getStatus());
            vars.add(item.getNote());
            vars.add(item.getSize());
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

}
