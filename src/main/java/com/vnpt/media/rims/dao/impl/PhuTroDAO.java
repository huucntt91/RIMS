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
import com.vnpt.media.rims.jdbc.DbSql;

public class PhuTroDAO extends GenericDAO implements IPhuTro {

    private static Logger logger = LogManager.getLogger(PhuTroDAO.class);

    @Override
    public List<PhuTroBO> findAll(String startRow, String endRow, String id, String khuvucId, String tinhId, String quanId, String phuongId, String code) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_PHUTRO.fc_find_all(?,?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(id);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
            vars.add(code);
            for (int i = 0; i < vars.size(); i++) {
                

            }
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PhuTroBO item = new PhuTroBO();
                    item.setId(rs.getLong("phu_tro_id"));
                    item.setCode(rs.getString("ma_building"));
                    item.setCshtName(rs.getString("building_name"));
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
                    item.setLoaiCSHT(rs.getString("loai_nha_tram_id"));
                    item.setBuildingId(rs.getLong("building_id"));
                    item.setLoaiCotAnTen(rs.getString("loai_cot_anten_id"));
                    item.setDongCungCapTuNguon(rs.getString("dong_cung_cap_tu_nguon"));
                    item.setGiaoDienTruyenDan(rs.getString("giao_dien_truyen_dan"));
                    item.setSlAccuBinh(rs.getString("sl_accu_binh"));
                    item.setSlDieuHoa(rs.getString("sl_dieu_hoa"));
                    item.setLoaiHinhMayNo(rs.getString("loai_hinh_may_no"));
                    item.setCsDieuHoa(rs.getString("tong_cs_dieuhoa"));
                    item.setDongTieuThuTuNguon(rs.getString("tieu_thu_tu_nguon"));
                    item.setDienApAccu(rs.getString("dien_ap_accu"));
                   
                    item.setNgayHDTuNguon2(rs.getDate("ngay_hd_tu_nguon2"));
                    item.setLoaiTuNguonId2(rs.getLong("loai_tu_nguon_id2"));
                    item.setDongCungCapTuNguon2(rs.getString("dong_cung_cap_tu_nguon2"));
                    item.setSoModuleTuNguon2(rs.getString("so_module_tu_nguon2"));
                    item.setDongTieuThuTuNguon2(rs.getString("tieu_thu_tu_nguon2"));
                    
                    item.setNgayHDAccu2(rs.getDate("ngay_hd_accu2"));
                    item.setLoaiAcQuyId2(rs.getLong("loai_ac_quy_id2"));
                    item.setDungLuongAccu2(rs.getString("dung_luong_accu2"));
                    item.setDienApAccu2(rs.getString("dien_ap_accu2"));
                    item.setSlAccuBinh2(rs.getString("sl_accu_binh2"));
                    item.setThoigianHDSauMatDien2(rs.getString("thoi_gian_hd_sau_mat_dien2"));
                    item.setNgayBaoDuongAccu2(rs.getDate("ngay_bao_duong_accu2"));
                    
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
    public int modify(String action, PhuTroBO item) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_PHUTRO.fn_modify(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            if (item == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);
            logger.debug("Phu tro modify: " + item.listParam());
            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(item.getId());
            vars.add(item.getBuildingId());
            vars.add(item.getChungCsht());
            vars.add(item.getLoaiCSHT());
            vars.add(item.getLoaiTramCsht());
            vars.add(item.getDoCaoAnTen());
            vars.add(item.getDoCaoNhaDatAnTen());
            vars.add(item.getLoaiCotAnTen());
            vars.add(item.getNgayHDTuNguon());
            vars.add(item.getLoaiTuNguonId());
            vars.add(item.getDongCungCapTuNguon());
            vars.add(item.getSoModuleTuNguon());
            vars.add(item.getDongTieuThuTuNguon());
            vars.add(item.getNgayHDMayNo());
            vars.add(item.getLoaiHinhMayNo());
            vars.add(item.getLoaiMayNoId());
            vars.add(item.getCongSuatMayNo());
            vars.add(item.getTrangThaiMayNo());
            vars.add(item.getNgayHDAccu());
            vars.add(item.getLoaiAcQuyId());
            vars.add(item.getDungLuongAccu());
            vars.add(item.getDienApAccu());
            vars.add(item.getSlAccuBinh());
            vars.add(item.getThoigianHDSauMatDien());
            vars.add(item.getNgayBaoDuongAccu());
            vars.add(item.getLoaiTruyenDanId());
            vars.add(item.getGiaoDienTruyenDan());
            vars.add(item.getDungLuongTruyenDan());
            vars.add(item.getDienTroTiepDia());
            vars.add(item.getSlDieuHoa());
            vars.add(item.getCsDieuHoa());
            
            vars.add(item.getNgayHDTuNguon2());
            vars.add(item.getLoaiTuNguonId2());
            vars.add(item.getDongCungCapTuNguon2());
            vars.add(item.getSoModuleTuNguon2());
            vars.add(item.getDongTieuThuTuNguon2());
            
            vars.add(item.getNgayHDAccu2());
            vars.add(item.getLoaiAcQuyId2());
            vars.add(item.getDungLuongAccu2());
            vars.add(item.getDienApAccu2());
            vars.add(item.getSlAccuBinh2());
            vars.add(item.getThoigianHDSauMatDien2());
            vars.add(item.getNgayBaoDuongAccu2());
            
            vars.add(item.getUserId());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
   
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
    public int getTotalAll(String id, String khuvucId, String tinhId, String quanId, String phuongId, String code) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_PHUTRO.fc_total_all(?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
            vars.add(code);

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
    public List<PhuTroBO> findSupportBuilding(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_PHUTRO.fc_support_building(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PhuTroBO item = new PhuTroBO();

                    item.setLoaiAcQuyId(rs.getLong("loai_ac_quy_id"));
                    item.setDungLuongAccu(rs.getString("dung_luong_accu"));
                    item.setLoaiAnTenId(rs.getLong("loai_cot_anten_id"));
                    item.setDoCaoAnTen(rs.getString("do_cao_cot"));
                    item.setDoCaoNhaDatAnTen(rs.getString("DO_CAO_VI_TRI_XAY_COT_ANTTEN"));
                    item.setLoaiTuNguonId(rs.getLong("loai_tu_nguon_id"));
                    item.setLoaiTruyenDanId(rs.getLong("loai_truyen_dan_id"));

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
}
