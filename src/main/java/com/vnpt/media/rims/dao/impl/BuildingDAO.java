package com.vnpt.media.rims.dao.impl;

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
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.jdbc.DbSql;

public class BuildingDAO extends GenericDAO implements IBuilding {
    
    private static Logger logger = LogManager.getLogger(BuildingDAO.class);
    
    @Override
    public List<BuildingBO> findAll(String startRow, String endRow, String id, String name, String khuvucId, String tinhId, String quanId, String phuongId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_BUILDING.fc_find_all(?,?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(id);
            vars.add(name);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BuildingBO item = new BuildingBO();
                    item.setId(rs.getLong("building_id"));
                    item.setCode(rs.getString("ma_building"));
                    item.setAddress(rs.getString("dia_chi"));
                    item.setLat(rs.getString("LATITUDE"));
                    item.setLon(rs.getString("LONGITUDE"));
                    item.setTinhTpId(rs.getLong("TINHTP_ID"));
                    item.setQuanHuyenId(rs.getLong("QUANHUYEN_ID"));
                    item.setPhuongXaId(rs.getLong("PHUONGXA_ID"));
                    item.setDonViId(rs.getLong("DONVI_ID"));
                    item.setTinhName(rs.getString("ten_tinh_tp"));
                    item.setQuanName(rs.getString("ten_quan_huyen"));
                    item.setPhuongName(rs.getString("ten_phuong_xa"));
                    item.setDonViName(rs.getString("ten_don_vi"));
                    item.setName(rs.getString("building_name"));
                    item.setNhomCSHT(rs.getString("AREA_CLASSIFY_GROUP"));
                    item.setNgayHdCsht(rs.getString("infras_active_date"));
                    item.setACCREDITATION_CODE(rs.getString("ACCREDITATION_CODE"));
                    item.setACCRE_START_DATE(rs.getString("ACCRE_START_DATE"));
                    item.setACCRE_END_DATE(rs.getString("ACCRE_END_DATE"));
                    item.setPlanningCode(rs.getString("planning_code"));
                    return item;
                }
            }, vars);
            
            return (List<BuildingBO>) list;
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
    public int modify(String action, BuildingBO item) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_BUILDING.fn_modify(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            if (item == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(item.getId());
            vars.add(item.getCode());
            vars.add(item.getAddress());
            vars.add(item.getTinhTpId());
            vars.add(item.getQuanHuyenId());
            vars.add(item.getPhuongXaId());
//            vars.add(item.getDonViId());

            vars.add(item.getLat() != null ? item.getLat().replace(".", ",") : "");
            vars.add(item.getLat() != null ? item.getLon().replace(".", ",") : "");
            vars.add(item.getNhomCSHT());
            vars.add(item.getNgayHdCsht());
            vars.add(item.getUserId());
            vars.add(item.getPlanningCode());
            //DbSql sqlTemplate = new DbSql(conn);
            //int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

//            
//            
            int count = sqlTemplate.executeUpdateFuncRet(querySql, vars).intValue();
            
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
    public int getTotalAll(String name, String khuvucId, String tinhId, String quanId, String phuongId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_BUILDING.fc_total_all(?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(name);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
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
    public List<BuildingBO> findBuildingLink(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_BUILDING.fc_buiding_links(?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BuildingBO item = new BuildingBO();
                    item.setId(rs.getLong("building_id"));
                    item.setCode(rs.getString("ma_building"));
                    return item;
                }
            }, vars);
            
            return (List<BuildingBO>) list;
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
    public List<PhuTroBO> findListPhuTroByBuidingId(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_BUILDING.fc_find_phutro(?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PhuTroBO item = new PhuTroBO();
                    item.setId(rs.getLong("phu_tro_id"));
                    item.setCode(rs.getString("ma_building"));
                    item.setTinhTpId(rs.getLong("TINHTP_ID"));
                    item.setTenTinhTP(rs.getString("ten_tinh_tp"));
                    item.setAcQuy(rs.getString("ten_loai_ac_quy"));
                    item.setLoaiAcQuyId(rs.getLong("loai_ac_quy_id"));
                    item.setDungLuongAccu(rs.getString("dung_luong_accu"));
                    item.setNgayHDAccu(rs.getDate("ngay_hd_accu"));
                    item.setThoigianHDSauMatDien(rs.getString("thoi_gian_hd_sau_mat_dien"));
                    item.setNgayBaoDuongAccu(rs.getDate("ngay_bao_duong_accu"));
                    item.setLoaiMayNoId(rs.getLong("loai_may_no_id"));
                    item.setMayNo(rs.getString("ten_loai_may_no"));
                    item.setCongSuatMayNo(rs.getString("cong_suat_may_no"));
                    item.setNgayHDMayNo(rs.getDate("ngay_hd_may_no"));
                    item.setTrangThaiMayNo(rs.getString("trang_thai_dat_may_no"));
                    
                    item.setLoaiTruyenDanId(rs.getLong("loai_truyen_dan_id"));
                    item.setTruyenDan(rs.getString("ten_loai_truyen_dan"));
                    item.setDungLuongTruyenDan(rs.getString("dung_luong_truyen_dan"));
                    item.setLoaiAnTenId(rs.getLong("loai_anten_id"));
                    item.setAnTen(rs.getString("ten_loai_anten"));
                    item.setDoCaoAnTen(rs.getString("do_cao_anten"));
                    item.setDoCaoNhaDatAnTen(rs.getString("do_cao_nha_dat_anten"));
                    item.setTuNguon(rs.getString("ten_loai_tu_nguon"));
                    item.setLoaiTuNguonId(rs.getLong("loai_tu_nguon_id"));
                    item.setNgayHDTuNguon(rs.getDate("ngay_hd_tu_nguon"));
                    item.setSoModuleTuNguon(rs.getString("so_module_tu_nguon"));
                    item.setDienTroTiepDia(rs.getString("dien_tro_tiep_dia"));
                    item.setChungCsht(rs.getString("chung_csht"));
                    item.setLoaiTramCsht(rs.getString("loai_tram_csht"));
                    
                    item.setBuildingId(rs.getLong("building_id"));
                    return item;
                }
            }, vars);
            
            return (List<PhuTroBO>) list;
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
    public List<NodeBO> findNodeById(String buidingId, String chaId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_building.fc_find_node_by_id(?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(buidingId);
            vars.add(chaId);
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NodeBO item = new NodeBO();
                    item.setId(rs.getLong("node_id"));
                    item.setNeTypeId(rs.getLong("ne_type_id"));
                    item.setCode(rs.getString("ma_node"));
                    item.setDonViId(rs.getLong("DONVI_ID"));
                    item.setCodeBuilding(rs.getString("ma_building"));
                    item.setTenNeType(rs.getString("ten_loai_ne"));
                    item.setTenThietBi(rs.getString("ten_thiet_bi"));
                    item.setDonViName(rs.getString("ten_don_vi"));
                    item.setAddress(rs.getString("DIA_CHI"));
                    item.setTrangThaiHDId(rs.getLong("TRANG_THAI_HD_ID"));
                    item.setTrangThaiQLId(rs.getLong("TRANG_THAI_QL_ID"));
                    item.setStatus(rs.getInt("status"));
                    item.setUserInsert(rs.getLong("USER_INSERT"));
                    item.setNodeChaId(rs.getLong("node_cha_id"));
                    return item;
                }
            }, vars);
            
            return (List<NodeBO>) list;
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
    public List<NodeBO> findCellByBuildingId(String buidingId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_building.FIND_CELL_BY_ID(?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(buidingId);
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NodeBO item = new NodeBO();
                    item.setId(rs.getLong("node_id"));
                    item.setNeTypeId(rs.getLong("ne_type_id"));
                    item.setCode(rs.getString("ma_node"));
                    item.setDonViId(rs.getLong("DONVI_ID"));
                    item.setCodeBuilding(rs.getString("ma_building"));
                    item.setTenNeType(rs.getString("ten_loai_ne"));
                    item.setTenThietBi(rs.getString("ten_thiet_bi"));
                    item.setDonViName(rs.getString("ten_don_vi"));
                    item.setAddress(rs.getString("DIA_CHI"));
                    item.setTrangThaiHDId(rs.getLong("TRANG_THAI_HD_ID"));
                    item.setTrangThaiQLId(rs.getLong("TRANG_THAI_QL_ID"));
                    item.setStatus(rs.getInt("status"));
                    item.setUserInsert(rs.getLong("USER_INSERT"));
                    item.setNodeChaId(rs.getLong("node_cha_id"));
                    return item;
                }
            }, vars);
            
            return (List<NodeBO>) list;
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
