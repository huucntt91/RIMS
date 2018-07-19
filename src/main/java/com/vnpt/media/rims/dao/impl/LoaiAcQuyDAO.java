package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.LoaiMayNoBO;
import com.vnpt.media.rims.bean.*;
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
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.jdbc.DbSql;

public class LoaiAcQuyDAO extends GenericDAO implements ILoaiAcQuy {

    private static Logger logger = LogManager.getLogger(LoaiAcQuyDAO.class);

    @Override
    public List<LoaiAcQuyBO> findAll(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_LOAI_AC_QUY.fc_find_all(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LoaiAcQuyBO item = new LoaiAcQuyBO();
                    item.setId(rs.getLong("loai_ac_quy_id"));
                    item.setName(rs.getString("ten_loai_ac_quy"));
                    return item;
                }   
            }, vars);

            return (List<LoaiAcQuyBO>) list;
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
    public int modify(String action, LoaiAcQuyBO loaiAcQuyBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_LOAI_AC_QUY.fn_modify(?,?,?)}";
            if (loaiAcQuyBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(loaiAcQuyBO.getId());
            vars.add(loaiAcQuyBO.getName());

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
