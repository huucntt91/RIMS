package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import com.vnpt.media.rims.jdbc.DbSql;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import com.vnpt.media.rims.bean.DonViBO;
import com.vnpt.media.rims.dao.IDonVi;
import java.util.ArrayList;

public class DonViDAO extends GenericDAO implements IDonVi {

    private static final Logger LOGGER = LogManager.getLogger(DonViDAO.class);

    @Override
    public List<DonViBO> findAll(String id, String name, String tinhs) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DONVI.fc_find_all(?,?,?)}";
            List<Object> vars = new ArrayList<>();
            vars.add(id);
            vars.add(name);
            vars.add(tinhs);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.queryFunction(querySql, (ResultSet rs, int rowNum) -> {
                DonViBO dvBO = new DonViBO();
                dvBO.setDonViId(rs.getLong("donvi_id"));
                dvBO.setTenDonVi(rs.getString("ten_don_vi"));
                dvBO.setDiaChiDonVi(rs.getString("dia_chi_don_vi"));
                dvBO.setParentId(rs.getLong("parent_id"));
                dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                dvBO.setQuanHuyenId(rs.getLong("quanhuyen_id"));
                dvBO.setPhuongXaId(rs.getLong("phuongxa_id"));
                dvBO.setTenTinhTp(rs.getString("ten_tinh_tp"));
                dvBO.setTenQuanHuyen(rs.getString("ten_quan_huyen"));
                dvBO.setTenPhuongXa(rs.getString("ten_phuong_xa"));
                dvBO.setTenParent(rs.getString("parent_name"));
                dvBO.setCodeTinhTp(rs.getString("ma_tinh_tp"));
                return dvBO;
            }, vars);
            return (List<DonViBO>) list;
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
    public void addDonVi(DonViBO dvBO) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DONVI.fn_add(?,?,?,?,?,?)}";
            if (dvBO == null) {
                return;
            }
            List<Object> vars = new ArrayList<>();
            vars.add(dvBO.getParentId());
            vars.add(dvBO.getTenDonVi());
            vars.add(dvBO.getTinhTpId());
            vars.add(dvBO.getQuanHuyenId());
            vars.add(dvBO.getPhuongXaId());
            vars.add(dvBO.getDiaChiDonVi());
            DbSql sqlTemplate = new DbSql(conn);
            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            LOGGER.info("PKG_DONVI.fn_add({},{},{},{},{},{}):{}", dvBO.getParentId(),
                    dvBO.getTenDonVi(), dvBO.getTinhTpId(),
                    dvBO.getQuanHuyenId(), dvBO.getPhuongXaId(), dvBO.getDiaChiDonVi(), count);
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
    public void updateDonVi(DonViBO dvBO) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DONVI.fn_update(?,?,?,?,?,?,?)} ";
            if (dvBO == null) {
                return;
            }
            List<Object> vars = new ArrayList<>();
            vars.add(dvBO.getDonViId());
            vars.add(dvBO.getParentId());
            vars.add(dvBO.getTenDonVi());
            vars.add(dvBO.getTinhTpId());
            vars.add(dvBO.getQuanHuyenId());
            vars.add(dvBO.getPhuongXaId());
            vars.add(dvBO.getDiaChiDonVi());
            DbSql sqlTemplate = new DbSql(conn);
            int count = Integer.valueOf(sqlTemplate.runProc(querySql, vars));
            LOGGER.info("PKG_DONVI.fn_update({},{},{},{},{},{},{}):{}", dvBO.getDonViId(), dvBO.getParentId(),
                    dvBO.getTenDonVi(), dvBO.getTinhTpId(), dvBO.getQuanHuyenId(), dvBO.getPhuongXaId(),
                    dvBO.getDiaChiDonVi(), count);
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
    public void delete(String Id) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_DONVI.fn_delete(?)} ";
            List<Object> vars = new ArrayList<>();
            vars.add(Id);
            DbSql sqlTemplate = new DbSql(conn);
            int count = Integer.valueOf(sqlTemplate.runProc(querySql, vars));
            LOGGER.info("PKG_DONVI.fn_delete({}):{}", Id, count);
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
