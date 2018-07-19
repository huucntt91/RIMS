package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.ThietBiBO;
import com.vnpt.media.rims.common.Constants;
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
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.IThietBi;
import com.vnpt.media.rims.jdbc.DbSql;

public class ThietBiDAO extends GenericDAO implements IThietBi {

    private static Logger logger = LogManager.getLogger(ThietBiDAO.class);

    @Override
    public List<ThietBiBO> findAll(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_THIET_BI.fc_find_all(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ThietBiBO thietBiBO = new ThietBiBO();
                    thietBiBO.setThietBiId(rs.getLong("thiet_bi_id"));
                    thietBiBO.setTenThietBi(rs.getString("ten_thiet_bi"));
                    return thietBiBO;
                }   
            }, vars);

            return (List<ThietBiBO>) list;
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
    public List<ThietBiBO> findThietBiByName(String name) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_THIET_BI.fc_find_all_by_name(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(name);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ThietBiBO thietBiBO = new ThietBiBO();
                    thietBiBO.setThietBiId(rs.getLong("thiet_bi_id"));
                    thietBiBO.setTenThietBi(rs.getString("ten_thiet_bi"));
                    return thietBiBO;
                }   
            }, vars);

            return (List<ThietBiBO>) list;
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
    public int modify(String action, ThietBiBO thietBiBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_THIET_BI.fn_modify(?,?,?)}";
            if (thietBiBO == null) {
                return -1;
            }
            logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(thietBiBO.getThietBiId());
            vars.add(thietBiBO.getTenThietBi());

            DbSql sqlTemplate = new DbSql(conn);

            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
   
            return count;

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
