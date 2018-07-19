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

public class DongThietBiDAO extends GenericDAO implements IDongThietBi {

    private static Logger logger = LogManager.getLogger(DongThietBiDAO.class);

    @Override
    public List<DongTbiBO> findAll(Long id, String name, Long tVendorId, Integer startRow, Integer endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DONG_TBI.fn_find_all(?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(name);
            vars.add(tVendorId);
            vars.add(startRow);
            vars.add(endRow);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DongTbiBO item = new DongTbiBO();
                    item.setDongTbiId(rs.getInt("DONG_TBI_ID"));
                    item.setTenDongTbi(rs.getString("TEN_DONG_TBI"));
                    item.setTvendorId(rs.getInt("TVENDOR_ID"));
                    item.setTvendorName(rs.getString("TVENDOR_NAME"));
                    item.setNote(rs.getString("NOTE"));
                    return item;
                }
            }, vars);

            return (List<DongTbiBO>) list;
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
    public int getTotal(Long id, String name, Long tVendorId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DONG_TBI.fn_get_total(?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();

            vars.add(id);
            vars.add(name);
            vars.add(tVendorId);
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
    public boolean add(DongTbiBO item, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DONG_TBI.fn_insert(?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(item.getTenDongTbi());
            vars.add(item.getTvendorId());
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
    public boolean update(DongTbiBO item, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DONG_TBI.fn_update(?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(item.getDongTbiId());
            vars.add(item.getTenDongTbi());
            vars.add(item.getTvendorId());
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
    public boolean delete(Long id, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DONG_TBI.fn_delete(?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(id);
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

}
