package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.LoaiTramBO;
import com.vnpt.media.rims.bean.LoaiNeBO;
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
import com.vnpt.media.rims.dao.ILoaiTram;

public class LoaiTramDAO extends GenericDAO implements ILoaiTram {

    private static Logger logger = LogManager.getLogger(LoaiTramDAO.class);

    @Override
    public List<LoaiTramBO> findAll(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_LOAI_TRAM.fc_find_all(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LoaiTramBO loaiTramBO = new LoaiTramBO();
                    loaiTramBO.setLoaiTramId(rs.getLong("loai_tram_id"));
                    loaiTramBO.setTenLoaiTram(rs.getString("ten_loai_tram"));
                    loaiTramBO.setNeTypeId(rs.getLong("ne_type_id"));
                    loaiTramBO.setTenLoaiNe(rs.getString("ten_loai_ne"));
                    return loaiTramBO;
                }   
            }, vars);
            
            return (List<LoaiTramBO>) list;
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
    public List<LoaiNeBO> findAllNe(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_LOAI_NE.fc_find_all(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LoaiNeBO loaiNeBO = new LoaiNeBO();
                    loaiNeBO.setId(rs.getLong("ne_type_id"));
                    loaiNeBO.setName(rs.getString("ten_loai_ne"));
                    loaiNeBO.setFamilyId(rs.getLong("family_type_id"));
                    loaiNeBO.setNetworkId(rs.getLong("network_type_id"));
                    return loaiNeBO;
                }   
            }, vars);

            return (List<LoaiNeBO>) list;
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
    public int modify(String action, LoaiTramBO loaiTramBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_LOAI_TRAM.fn_modify(?,?,?,?)}";
            if (loaiTramBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(loaiTramBO.getLoaiTramId());
            vars.add(loaiTramBO.getTenLoaiTram());
            vars.add(loaiTramBO.getNeTypeId());

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


   @Override
    public List<LoaiTramBO> findAllByNeTypeId(String id,int neTypeId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_LOAI_TRAM.fc_find_all_by_neTypeId(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(neTypeId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LoaiTramBO loaiTramBO = new LoaiTramBO();
                    loaiTramBO.setLoaiTramId(rs.getLong("loai_tram_id"));
                    loaiTramBO.setTenLoaiTram(rs.getString("ten_loai_tram"));
                    loaiTramBO.setNeTypeId(rs.getLong("ne_type_id"));
                    loaiTramBO.setTenLoaiNe(rs.getString("ten_loai_ne"));
                    return loaiTramBO;
                }   
            }, vars);

            return (List<LoaiTramBO>) list;
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
