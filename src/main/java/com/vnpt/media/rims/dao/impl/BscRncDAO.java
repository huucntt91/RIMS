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
import java.util.Vector;
import com.vnpt.media.rims.dao.*;


public class BscRncDAO extends GenericDAO implements IBscRnc {

    private static Logger logger = LogManager.getLogger(BscRncDAO.class);

    @Override
    public List<BSCRNCInfoBO> findAllBscRncInfo(int startRow, int endRow, String name, String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_bsc_rnc_info.fc_find_all(?,?,?,?) }";
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
                    BSCRNCInfoBO item = new BSCRNCInfoBO();
                    item.setNodeId(rs.getLong("node_id"));
                    item.setCode(rs.getString("ma_node"));
                    item.setName(rs.getString("ten_bsc_rnc"));
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
                    Long nodechaid = rs.getLong("node_cha_id");
                    item.setNodeChaId(rs.wasNull() ? null : nodechaid);

                    item.setTenNodeCha(rs.getString("ma_node_cha"));
//                    item.setLoaiTramId(rs.getLong("LOAI_TRAM_ID"));
                    Long loaitramid = rs.getLong("loai_tram_id");
                    item.setLoaiTramId(rs.wasNull() ? null : loaitramid);

                    item.setTenNgQLy(rs.getString("ten_ng_qly"));
                    item.setSDTQLy(rs.getString("sdt_ng_qly"));
                    item.setNgayHoatDong(rs.getDate("ngay_hoat_dong"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setNgayDangKy(rs.getDate("ngay_dang_ky"));
                    item.setNgayKiemDuyet(rs.getDate("ngay_kiem_duyet"));
                    item.setNgayCapPhep(rs.getDate("ngay_cap_phep"));
                    item.setTypeBSCRNC(rs.getString("type_bsc_rnc"));
                  
                    item.setTenTrenHT(rs.getString("ten_tren_he_thong"));
                    item.setMscMss(rs.getString("msc_mss"));
                    item.setSgsn(rs.getString("sgsn"));
//                    item.setTypeBSCRNC(rs.getString("bang_tan_id"));
                    item.setOpc(rs.getString("opc"));
                    item.setNumeralSystem(rs.getString("NUMERAL_SYSTEM"));

                    return item;
                }
            }, vars);

            return (List<BSCRNCInfoBO>) list;
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
    public int getTotalBscRncInfo(String name, String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_bsc_rnc_info.fc_find_total(?,?) }";
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
    public int addBscRncInfo(BSCRNCInfoBO item, Long userInsert) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();


            String querySql = "{? = call pkg_bsc_rnc_info.fc_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";      
      



            List<Object> vars = new Vector<Object>();
            vars.add(userInsert);
            vars.add(item.getCode());
            vars.add(item.getNodeChaId());
            vars.add(item.getDonViId());
            vars.add(item.getThietBiId());
            vars.add(item.getBuildingId());
            vars.add(item.getLoaiTramId());
            vars.add(item.getTenNgQLy());
            vars.add(item.getSDTQLy());
            vars.add(item.getNodeId());
            vars.add(item.getName());
            vars.add(item.getNgayHoatDong());
            vars.add(item.getHoanCanhRaDoi());
            vars.add(item.getNgayDangKy());
            vars.add(item.getNgayKiemDuyet());
            vars.add(item.getNgayCapPhep());
            vars.add(item.getTypeBSCRNC());
            vars.add(item.getTenTrenHT());
            vars.add(item.getMscMss());
            vars.add(item.getSgsn());
            vars.add(item.getOpc());
            vars.add(item.getNumeralSystem());


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
    public int updateBscRncInfo(BSCRNCInfoBO item, Long userInsert) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();

            String querySql = "{? = call pkg_bsc_rnc_info.fc_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";

//       


            List<Object> vars = new Vector<Object>();

            vars.add(userInsert);
            vars.add(item.getCode());
            vars.add(item.getNodeChaId());
            vars.add(item.getDonViId());
            vars.add(item.getThietBiId());
            vars.add(item.getBuildingId());
            vars.add(item.getLoaiTramId());
            vars.add(item.getTenNgQLy());
            vars.add(item.getSDTQLy());

            vars.add(item.getNodeId());
            vars.add(item.getName());
            vars.add(item.getNgayHoatDong());
            vars.add(item.getHoanCanhRaDoi());
            vars.add(item.getNgayDangKy());
            vars.add(item.getNgayKiemDuyet());
            vars.add(item.getNgayCapPhep());
            vars.add(item.getTypeBSCRNC());
            vars.add(item.getTenTrenHT());
            vars.add(item.getMscMss());
            vars.add(item.getSgsn());
            vars.add(item.getOpc());
            vars.add(item.getNumeralSystem());

//            SQLTemplate sqlTemplate = new SQLTemplate(conn);
//
//            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int iRet = sqlTemplate.executeUpdateFuncRet(querySql, vars).intValue();
            if (iRet > 0) {
                logger.debug("Records modify : " + iRet);
            } else {
                throw new DAOException();
            }
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
    public int deleteBscRncInfo(Long id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_bsc_rnc_info.fc_delete(?) }";
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
