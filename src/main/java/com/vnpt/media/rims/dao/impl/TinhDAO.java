package com.vnpt.media.rims.dao.impl;

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
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.KhuvucBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.ITinh;
import com.vnpt.media.rims.jdbc.DbSql;

public class TinhDAO extends GenericDAO implements ITinh {

    private static Logger logger = LogManager.getLogger(TinhDAO.class);

    @Override
    public List<TinhBO> findAllTinh(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fc_find_all_tinh() }";
//       

            List<Object> vars = new Vector<Object>();
            if (StringUtils.hasText(id)) {
                querySql = "{? = call PKG_TINH.fc_find_by_id(?,?) }";
                vars.add(id);
                vars.add("TINH_TP");
            }
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TinhBO dvBO = new TinhBO();
                    dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                    dvBO.setKhuVuc(rs.getLong("khu_vuc"));
                    dvBO.setTenTinhTp(rs.getString("ten_tinh_tp"));
                    dvBO.setMaTinhTp(rs.getString("ma_tinh_tp"));
                    return dvBO;
                }

            }, vars);

            return (List<TinhBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (DAOException e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<KhuvucBO> findAllKhuVuc(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fc_find_all_khuvuc() }";

            List<Object> vars = new Vector<Object>();
            if (StringUtils.hasText(id)) {
                querySql = "{? = call PKG_TINH.fc_find_all_khuvuc_by_id(?) }";
                vars.add(id);
            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    KhuvucBO khuvucBO = new KhuvucBO();
                    khuvucBO.setId(rs.getLong("id"));
                    khuvucBO.setName(rs.getString("name"));

                    return khuvucBO;
                }

            }, vars);

            return (List<KhuvucBO>) list;
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
    public List<HuyenBO> findAllHuyen(String id, String tinhId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fc_find_huyen_by_tinh_id(?)}";

            List<Object> vars = new Vector<Object>();
            if (StringUtils.hasText(id)) {
                querySql = "{? = call PKG_TINH.fc_find_by_id(?,?) }";
                vars.add(id);
                vars.add("QUAN_HUYEN");
            } else {
                vars.add(tinhId);
            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    HuyenBO dvBO = new HuyenBO();
                    dvBO.setQuanHuyenId(rs.getLong("quanhuyen_id"));
                    dvBO.setTenQuanHuyen(rs.getString("ten_quan_huyen"));
                    dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                    dvBO.setTenTinhTp(rs.getString("ten_tinh_tp"));
                    return dvBO;
                }

            }, vars);

            return (List<HuyenBO>) list;
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
    public List<PhuongXaBO> findAllPhuongXa(String id, String huyenId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fc_find_phuongxa_by_huyen_id(?)}";
//       

            List<Object> vars = new Vector<Object>();
            if (StringUtils.hasText(id)) {
                querySql = "{? = call PKG_TINH.fc_find_by_id(?,?) }";
                vars.add(id);
                vars.add("PHUONG_XA");
            } else {
                vars.add(huyenId);
            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PhuongXaBO dvBO = new PhuongXaBO();
                    dvBO.setPhuongXaId(rs.getLong("phuongxa_id"));
                    dvBO.setTenPhuongXa(rs.getString("ten_phuong_xa"));
                    dvBO.setQuanHuyenId(rs.getLong("quanhuyen_id"));
                    dvBO.setTenQuanHuyen(rs.getString("ten_quan_huyen"));
                    return dvBO;
                }

            }, vars);

            return (List<PhuongXaBO>) list;
        } catch (ConnectionException e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    public List<HuyenBO> findAllHuyen(String listTinhId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fc_find_huyen_by_list_tinh_id(?)}";

            List<Object> vars = new Vector<Object>();
            vars.add(listTinhId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    HuyenBO dvBO = new HuyenBO();
                    dvBO.setQuanHuyenId(rs.getLong("quanhuyen_id"));
                    dvBO.setTenQuanHuyen(rs.getString("ten_quan_huyen"));
                    dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                    dvBO.setTenTinhTp(rs.getString("ten_tinh_tp"));
                    return dvBO;
                }

            }, vars);

            return (List<HuyenBO>) list;
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
    public List<PhuongXaBO> findAllPhuongXa(String listHuyenId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fc_find_phuongxa_by_list_huyen(?)}";
//       

            List<Object> vars = new Vector<Object>();

            vars.add(listHuyenId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PhuongXaBO dvBO = new PhuongXaBO();
                    dvBO.setPhuongXaId(rs.getLong("phuongxa_id"));
                    dvBO.setTenPhuongXa(rs.getString("ten_phuong_xa"));
                    dvBO.setQuanHuyenId(rs.getLong("quanhuyen_id"));
                    dvBO.setTenQuanHuyen(rs.getString("ten_quan_huyen"));
                    return dvBO;
                }

            }, vars);

            return (List<PhuongXaBO>) list;
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
    public int modifyTinh(String action, TinhBO tinhBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fn_modify_tinh(?,?,?,?,?)}";
            if (tinhBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(tinhBO.getTinhTpId());
            vars.add(tinhBO.getTenTinhTp());
            vars.add(tinhBO.getMaTinhTp());
            vars.add(tinhBO.getKhuVuc());

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
    public int modifyQuan(String action, HuyenBO huyenBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fn_modify_quan(?,?,?,?)}";
            if (huyenBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(huyenBO.getQuanHuyenId());
            vars.add(huyenBO.getTenQuanHuyen());
            vars.add(huyenBO.getTinhTpId());

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
    public int modifyPhuong(String action, PhuongXaBO phuongBO) throws DAOException {
        Connection conn = null;
        try {
            logger.info("modifyPhuong: {}", phuongBO.listParam());
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fn_modify_phuong(?,?,?,?)}";
            if (phuongBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(phuongBO.getPhuongXaId());
            vars.add(phuongBO.getTenPhuongXa());
            vars.add(phuongBO.getQuanHuyenId());

            DbSql sqlTemplate = new DbSql(conn);

            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            logger.info("end  PKG_TINH.fn_modify_phuong : {}", count);

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
    public List<TinhBO> findListTinhByUserId(String id) throws DAOException {
        Connection conn = this.getConnection();
        String querySql = "{? = call PKG_GROUP.fc_manage_tinh(?) }";
        List<Object> vars = new Vector<>();
        vars.add(id);
        SQLTemplate sqlTemplate = new SQLTemplate(conn);
        List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                TinhBO dvBO = new TinhBO();
                dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                return dvBO;
            }
        }, vars);
        return (List<TinhBO>) list;
    }
}
