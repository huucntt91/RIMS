package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
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
import com.vnpt.media.rims.dao.ISmsc;
import java.util.Vector;


public class SmscDAO extends GenericDAO implements ISmsc {

    private static Logger logger = LogManager.getLogger(SmscDAO.class);

    @Override
    public List<SmscInfoBO> findAllSmscInfo(String name, String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_SMSC_INFO.fc_find_all(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(name);
            vars.add(id);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GgsnInfoBO item = new GgsnInfoBO();
                    item.setNodeId(rs.getLong("node_id"));
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
                    item.setNeStatus(rs.getString("ne_status"));
                    item.setIpNe(rs.getString("ip_ne"));
                    item.setOpc(rs.getString("opc"));
                    item.setSoftVersion(rs.getString("software_version"));
                    item.setHwFlatForm(rs.getString("hw_flatform"));
                    item.setVender(rs.getString("vendor"));
                    item.setThuocDuAn(rs.getString("thuoc_du_an"));
                    item.setAddress(rs.getString("dia_diem_lap_dat"));
                    item.setLicense(rs.getString("license"));
                    item.setTenCard(rs.getString("ten_card"));
                    item.setSeria(rs.getString("serial_part_number"));
                    item.setCardStatus(rs.getString("card_status"));
                    item.setCardSL(rs.getInt("so_luong_card"));
                    item.setCardVersion(rs.getString("card_version"));
                    item.setFile(rs.getString("file_hld_lld"));
                    
                    item.setVender(rs.getString("ten_thiet_bi"));
                    return item;
                }
            }, vars);

            return (List<SmscInfoBO>) list;
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
    public int addSmscInfo(SmscInfoBO item, Long userInsert) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            prn_ma_node VARCHAR2,prn_donvi_id NUMBER,prn_thiet_bi_id NUMBER,prn_loai_tram_id NUMBER,
// prn_ten_ng_qly VARCHAR2,prn_sdt_ng_qly VARCHAR2,prn_user_insert NUMBER
// ,prs_node_id NUMBER, prs_name VARCHAR2, prs_ngay_hoat_dong Date, 
// prs_hoan_canh_ra_doi VARCHAR2, prs_ngay_dang_ky DATE, prs_type VARCHAR2
            String querySql = "{? = call PKG_SMSC_INFO.fc_insert(?,?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
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
            vars.add(item.getNeStatus());
            vars.add(item.getIpNe());
            vars.add(item.getOpc());
            vars.add(item.getSoftVersion());
            vars.add(item.getHwFlatForm());
            vars.add(item.getVender());
            vars.add(item.getThuocDuAn());
            vars.add(item.getAddress());
            vars.add(item.getLicense());
            vars.add(item.getTenCard());
            vars.add(item.getSeria());
            vars.add(item.getCardStatus());
            vars.add(item.getCardVersion());
            vars.add(item.getCardSL());
            vars.add(item.getFile());

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
    public int updateSmscInfo(SmscInfoBO item, Long userInsert) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_SMSC_INFO.fc_update(?,?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
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
            vars.add(item.getNeStatus());
            vars.add(item.getIpNe());
            vars.add(item.getOpc());
            vars.add(item.getSoftVersion());
            vars.add(item.getHwFlatForm());
            vars.add(item.getVender());
            vars.add(item.getThuocDuAn());
            vars.add(item.getAddress());
            vars.add(item.getLicense());
            vars.add(item.getTenCard());
            vars.add(item.getSeria());
            vars.add(item.getCardStatus());
            vars.add(item.getCardVersion());
            vars.add(item.getCardSL());
            vars.add(item.getFile());
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
    public int deleteSmscInfo(Long id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_SMSC_INFO.fc_delete(?) }";
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

    @Override
    public int addOpcDpc(String node1Id, String listNode2Id, int loaitruyendanId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            prn_ma_node VARCHAR2,prn_donvi_id NUMBER,prn_thiet_bi_id NUMBER,prn_loai_tram_id NUMBER,
// prn_ten_ng_qly VARCHAR2,prn_sdt_ng_qly VARCHAR2,prn_user_insert NUMBER
// ,prs_node_id NUMBER, prs_name VARCHAR2, prs_ngay_hoat_dong Date, 
// prs_hoan_canh_ra_doi VARCHAR2, prs_ngay_dang_ky DATE, prs_type VARCHAR2
            String querySql = "{? = call PKG_NE_LINK.fc_insert_ne_link(?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(Long.valueOf(node1Id));
            vars.add(Long.valueOf(listNode2Id));
            vars.add(Long.valueOf(loaitruyendanId));
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
