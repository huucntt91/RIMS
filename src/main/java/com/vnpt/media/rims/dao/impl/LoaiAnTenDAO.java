package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.LoaiAnTenBO;
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
import com.vnpt.media.rims.dao.ILoaiAnTen;
import com.vnpt.media.rims.dao.IThietBi;
import com.vnpt.media.rims.jdbc.DbSql;

public class LoaiAnTenDAO extends GenericDAO implements ILoaiAnTen {

    private static Logger logger = LogManager.getLogger(LoaiAnTenDAO.class);

    @Override
    public List<LoaiAnTenBO> findAll(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_LOAI_ANTEN.fc_find_all(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LoaiAnTenBO loaiAnTen = new LoaiAnTenBO();
                    loaiAnTen.setId(rs.getLong("loai_anten_id"));
                    loaiAnTen.setName(rs.getString("ten_loai_anten"));
                    return loaiAnTen;
                }   
            }, vars);

            return (List<LoaiAnTenBO>) list;
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
    public int modify(String action, LoaiAnTenBO loaiAnTenBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_LOAI_ANTEN.fn_modify(?,?,?)}";
            if (loaiAnTenBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(loaiAnTenBO.getId());
            vars.add(loaiAnTenBO.getName());

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
