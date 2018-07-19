package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.DanhMucBO;
import com.vnpt.media.rims.bean.ThietBiBO;
import com.vnpt.media.rims.bean.TrangThaiHDBO;
import com.vnpt.media.rims.dao.IDanhMuc;
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

public class DanhMucDAO extends GenericDAO implements IDanhMuc {

    private static Logger logger = LogManager.getLogger(DanhMucDAO.class);

    @Override
    public List<DanhMucBO> findDmOkNok() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_danhmuc.fc_find_dm_ok_nok() }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DanhMucBO temp = new DanhMucBO();
                    temp.setId(rs.getLong("id"));
                    temp.setName(rs.getString("name"));
                    return temp;
                }   
            }, vars);
            
            return (List<DanhMucBO>) list;
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
    public List<DanhMucBO> findDmNguonThietBi() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_danhmuc.fc_find_dm_nguon_thiet_bi() }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DanhMucBO temp = new DanhMucBO();
                    temp.setId(rs.getLong("nguon_thiet_bi_id"));
                    temp.setName(rs.getString("ten_nguon_thiet_bi"));
                    return temp;
                }   
            }, vars);
            
            return (List<DanhMucBO>) list;
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
    public List<DanhMucBO> findDmLoaiCongNghe() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_danhmuc.fc_find_dm_loai_cn() }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DanhMucBO temp = new DanhMucBO();
                    temp.setId(rs.getLong("id_loai_cong_nghe"));
                    temp.setName(rs.getString("ten_loai_cong_nghe"));
                    return temp;
                }   
            }, vars);
            
            return (List<DanhMucBO>) list;
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
    public List<DanhMucBO> findDmHienTrangTram() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_danhmuc.fc_find_dm_hien_trang_tram() }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DanhMucBO temp = new DanhMucBO();
                    temp.setId(rs.getLong("id"));
                    temp.setName(rs.getString("name"));
                    return temp;
                }   
            }, vars);
            
            return (List<DanhMucBO>) list;
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
    public List<DanhMucBO> findDmDungLuongACCU() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_danhmuc.fc_find_dm_dung_luong_accu() }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DanhMucBO temp = new DanhMucBO();
                    temp.setId(rs.getLong("id"));
                    temp.setName(rs.getString("name"));
                    return temp;
                }   
            }, vars);
            
            return (List<DanhMucBO>) list;
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
