package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.DuAnTinhBO;
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
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.TramDuAnBO;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.IDuAnTinh;
import com.vnpt.media.rims.jdbc.DbSql;

public class DuAnTinhDAO extends GenericDAO implements IDuAnTinh {

    private static Logger logger = LogManager.getLogger(DuAnTinhDAO.class);

    public List<DuAnTinhBO> findDATinh(String tinhTpId, String maDA) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_du_an_tinh.fc_find_da_tinh(?,?)}";

            List<Object> vars = new Vector<Object>();

//            if (StringUtils.hasText(fullname)) {
//                vars.add("%" + fullname.trim().toLowerCase() + "%");
//            }
            vars.add(tinhTpId == null ? "" : tinhTpId);
            vars.add(maDA == null ? "" : maDA);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DuAnTinhBO duAnTinh = new DuAnTinhBO();

                    duAnTinh.setDuAnId(rs.getString("DU_AN_ID"));
                    duAnTinh.setMaDuAn(rs.getString("MA_DU_AN"));
                    duAnTinh.setTenDuAn(rs.getString("TEN_DU_AN"));
                    duAnTinh.setTinhId(rs.getString("TINHTP_ID"));
                    duAnTinh.setTenTinh(rs.getString("TEN_TINH_TP"));
                    duAnTinh.setQuyHoachTinhId(rs.getString("QH_TINH_ID"));
                    duAnTinh.setMaQuyHoach(rs.getString("MA_QUY_HOACH"));
                    duAnTinh.setTenQuyHoach(rs.getString("TEN_QUY_HOACH"));
                    
//                    Long trangthaiAction = Long.parseLong(status);
                    return duAnTinh;
                }

            }, vars);

            return (List<DuAnTinhBO>) list;
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
    
    public void modifyDuAnTinh(DuAnTinhBO daTinhBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_du_an_tinh.fn_mod_du_an_tinh(?,?,?,?,?,?)}";

            if (daTinhBO == null) {
                return;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(daTinhBO.getDuAnId());
            vars.add(daTinhBO.getQuyHoachTinhId());
            vars.add(daTinhBO.getMaDuAn().trim());
            vars.add(daTinhBO.getTenDuAn().trim());
            vars.add(daTinhBO.getTinhId());
            vars.add(daTinhBO.getUserId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }

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
    public int deleteDuAnTinh(DuAnTinhBO daTinhBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_du_an_tinh.fn_del_du_an_tinh(?)}";

            if (daTinhBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            
            vars.add(daTinhBO.getDuAnId());           
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);

//            DbSql sqlTemplate = new DbSql(conn);
//
//            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }
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
