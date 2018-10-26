package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.LoaiDieuHoaBO;
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
import com.vnpt.media.rims.dao.IDieuHoa;
import com.vnpt.media.rims.jdbc.DbSql;

public class DieuHoaDAO extends GenericDAO implements IDieuHoa {

    private static Logger logger = LogManager.getLogger(DieuHoaDAO.class);

    @Override
    public List<LoaiDieuHoaBO> findAll(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DIEU_HOA.fc_find_all(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LoaiDieuHoaBO loaiDieuHoaBO = new LoaiDieuHoaBO();
                    loaiDieuHoaBO.setId(rs.getLong("id"));
                    loaiDieuHoaBO.setTen(rs.getString("ten"));
                    return loaiDieuHoaBO;
                }   
            }, vars);

            return (List<LoaiDieuHoaBO>) list;
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
    public List<LoaiDieuHoaBO> findDieuHoaByName(String name) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DIEU_HOA.fc_find_all_by_name(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(name);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LoaiDieuHoaBO dieuHoaBO = new LoaiDieuHoaBO();
                    dieuHoaBO.setId(rs.getLong("id"));
                    dieuHoaBO.setTen(rs.getString("ten"));
                    return dieuHoaBO;
                }   
            }, vars);

            return (List<LoaiDieuHoaBO>) list;
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
    public int modify(String action, LoaiDieuHoaBO dieuHoaBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DIEU_HOA.fn_modify(?,?,?)}";
            if (dieuHoaBO == null) {
                return -1;
            }
            logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(dieuHoaBO.getId());
            vars.add(dieuHoaBO.getTen());

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
