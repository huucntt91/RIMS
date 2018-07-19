package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.StringUtils;
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
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.NeLinkForm;
import com.vnpt.media.rims.jdbc.DbSql;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class MssDAO extends GenericDAO implements IMss {

    private static Logger logger = LogManager.getLogger(MssDAO.class);

    @Override
    public List<MssInfoBO> findAllMssInfo(int startRow, int endRow, String name, String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_MSS_INFO.fc_find_all(?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(name);
            vars.add(id);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MssInfoBO item = new MssInfoBO();
                     item.setNodeId(rs.getLong("nodeId"));
                    item.setCode(rs.getString("ma_node"));
                    item.setName(rs.getString("name"));
                    item.setNeTypeId(rs.getLong("NE_TYPE_ID"));
//                    item.setDonViId(rs.getLong("DONVI_ID"));
                    Long donviid = rs.getLong("DONVI_ID");
                    item.setDonViId(rs.wasNull() ? null : donviid);

                    //item.setThietBiId(rs.getLong("THIET_BI_ID"));
                    Long thietbiid = rs.getLong("THIET_BI_ID");
                    item.setThietBiId(rs.wasNull() ? null : thietbiid);

                    Long building = rs.getLong("BUILDING_ID");
                    item.setBuildingId(rs.wasNull() ? null : building);

                    
                    item.setCodeBuilding(rs.getString("ma_building"));
//                    item.setNodeChaId(rs.getLong("NODE_CHA_ID"));
                    Long nodechaid = rs.getLong("NODE_CHA_ID");
                    item.setNodeChaId(rs.wasNull() ? null : nodechaid);
                    
                    item.setTenNodeCha(rs.getString("ma_node_cha"));
//                    item.setLoaiTramId(rs.getLong("LOAI_TRAM_ID"));
                    Long loaitramid = rs.getLong("LOAI_TRAM_ID");
                    item.setLoaiTramId(rs.wasNull() ? null : loaitramid);
                    
                    item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                    item.setSDTQLy(rs.getString("SDT_NG_QLY"));
                    item.setNgayHoatDong(rs.getDate("ngay_hoat_dong"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setNgayDangKy(rs.getDate("ngay_dang_ky"));
                    item.setType(rs.getString("type"));
                    item.setOpc(rs.getString("opc"));
                    item.setOpc1(rs.getString("opc1"));
                    item.setNumberalSystem(rs.getString("NUMERAL_SYSTEM"));
                    item.setNumberalSystem1(rs.getString("NUMERAL_SYSTEM1"));
                    return item;
                }
            }, vars);

            return (List<MssInfoBO>) list;
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
    public int getTotalMssInfo(String name, String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_MSS_INFO.fc_find_total(?,?) }";
//       


            List<Object> vars = new Vector<Object>();

            vars.add(name);
            vars.add(id);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int iRet = sqlTemplate.queryFunctionForInt(querySql, vars);

            return iRet;
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
    public int addMssInfo(MssInfoBO item, Long userInsert) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            prn_ma_node VARCHAR2,prn_donvi_id NUMBER,prn_thiet_bi_id NUMBER,prn_loai_tram_id NUMBER,
// prn_ten_ng_qly VARCHAR2,prn_sdt_ng_qly VARCHAR2,prn_user_insert NUMBER
// ,prs_node_id NUMBER, prs_name VARCHAR2, prs_ngay_hoat_dong Date, 
// prs_hoan_canh_ra_doi VARCHAR2, prs_ngay_dang_ky DATE, prs_type VARCHAR2
            String querySql = "{? = call PKG_MSS_INFO.fc_insert(?,?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(item.getNodeChaId());
            vars.add(item.getCode());
            vars.add(item.getDonViId());
            vars.add(item.getThietBiId());
            vars.add(item.getBuildingId());
            vars.add(item.getLoaiTramId());
            vars.add(item.getTenNgQLy());
            vars.add(item.getSDTQLy());
            vars.add(userInsert);

            vars.add(item.getNodeId());
            vars.add(item.getName());
            vars.add(item.getNgayHoatDong());
            vars.add(item.getHoanCanhRaDoi());
            vars.add(item.getNgayDangKy());
            vars.add(item.getType());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);

            return iRet;
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
    public int updateMssInfo(MssInfoBO item,Long userInsert) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_MSS_INFO.fc_update(?,?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();

             vars.add(item.getNodeChaId());
            vars.add(item.getCode());
            vars.add(item.getDonViId());
            vars.add(item.getThietBiId());
            vars.add(item.getBuildingId());
            vars.add(item.getLoaiTramId());
            vars.add(item.getTenNgQLy());
            vars.add(item.getSDTQLy());
            vars.add(userInsert);

            vars.add(item.getNodeId());
            vars.add(item.getName());
            vars.add(item.getNgayHoatDong());
            vars.add(item.getHoanCanhRaDoi());
            vars.add(item.getNgayDangKy());
            vars.add(item.getType());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);

            return iRet;
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
    public int deleteMssInfo(Long id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_MSS_INFO.fc_delete(?) }";
//       


            List<Object> vars = new Vector<Object>();

            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);

            return iRet;
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
