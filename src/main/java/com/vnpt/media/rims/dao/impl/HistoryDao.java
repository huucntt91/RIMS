/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;

import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.dao.IHistory;
import com.vnpt.media.rims.formbean.BtsNodeB;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.dao.IReport;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import com.vnpt.media.rims.formbean.FilterForm;
import com.vnpt.media.rims.formbean.ReportConfigForm;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyano
 */
public class HistoryDao extends GenericDAO implements IHistory {

    @Override
    public List<Cell2GReportBO> cell2GReport(String startTime, String endTime, String action, String userId, String code) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_history.find_list_history_cell(?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startTime);
            vars.add(endTime);
            vars.add(action);
            vars.add(userId);
            vars.add(code);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Cell2GReportBO item = new Cell2GReportBO();

                    item.setUserName(rs.getString("action_user"));
                    item.setCreateTime(rs.getDate("date_created"));
                    item.setAction(rs.getString("action"));
                    item.setIpClient(rs.getString("ip_client"));
                    item.setTinh(rs.getString("don_vi_tinh_tp"));

                    item.setDiachi(rs.getString("dia_chi"));
                    item.setNgayHoatDong(rs.getString("ngay_hoat_dong"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setTenCell(rs.getString("ten_cell"));
                    item.setNgayDangKy(rs.getString("ngay_dang_ky"));
                    item.setNgayKiemDuyet(rs.getString("ngay_kiem_duyet"));
                    item.setNgayCapPhep(rs.getString("ngay_cap_phep"));
                    item.setTenTrenHeThong(rs.getString("ten_tren_he_thong"));
                    item.setLac(rs.getString("lac"));
                    item.setCi(rs.getString("ci"));
                    item.setMaBTS(rs.getString("ma_node_cha_id"));
                    //item.setTenBscRnc(rs.getString("TEN_BSC_RNC"));
                    item.setCode(rs.getString("ma_node"));
                    item.setDcHsdpa42m(rs.getString("DC_HSDPA_42M"));
                    item.setFrequencyBand(rs.getString("Ten_bang_tan"));
                    item.setTrangThaiHdId(rs.getString("TRANG_THAI_HD_ID"));
                    item.setTrangThaiHd(rs.getString("TEN_TRANGTHAI_HD"));
                    item.setTrangThaiQlId(rs.getString("TRANG_THAI_QL_ID"));
                    item.setTrangThaiQl(rs.getString("TEN_TRANGTHAI_QL"));
                    item.setLatitude(rs.getString("LATITUDE"));
                    item.setLongitude(rs.getString("LONGITUDE"));
                    item.setAzimuth(rs.getString("AZIMUTH"));
                    item.setMechanical(rs.getString("MECHANICAL_TILT"));
                    item.setTotalTilt(rs.getString("TOTAL_TILT"));
                    item.setAntennaHigh(rs.getString("ANTENNA_HIGH"));
                    item.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                    item.setAntennaType(rs.getString("ANTENNA_TYPE"));
                    item.setThietBiId(rs.getString("THIET_BI_ID"));
                    item.setTenThietBi(rs.getString("TEN_THIET_BI"));
                    item.setLoaiTramId(rs.getString("loai_tram_id"));
                    item.setTenLoaiTram(rs.getString("TEN_LOAI_TRAM"));

                    item.setNoOfCarrier(rs.getString("NO_OF_CARRIER"));
                    item.setCpichPower(rs.getString("CPICH_POWER"));
                    item.setTotalTilt(rs.getString("TOTAL_POWER"));
                    item.setBosterTma(rs.getString("BOSTER_TMA"));
                    item.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                    item.setBlackListFlag(rs.getString("BLACK_LIST_FLAG"));
                    item.setLyDo(rs.getString("LY_DO"));
                    item.setNote(rs.getString("note"));
                    return item;
                }
            }, vars);
            
            return (List<Cell2GReportBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public int getTotalCell2GReport(int type, FilterForm filterForm) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_total_cell_2g(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            String sWhere = "  ";
            if (filterForm != null && filterForm.getListFilterForm() != null) {
                for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {

                    if (!filterForm.getListFilterForm().get(i).getColumn().equalsIgnoreCase("-1")) {
                        sWhere += " and " + Convert.convertFilter(filterForm.getListFilterForm().get(i).getColumn(), filterForm.getListFilterForm().get(i).getOperator(), filterForm.getListFilterForm().get(i).getValue_());
                    }
                }
            }

            
            vars.add(sWhere);
            vars.add(type);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int total = sqlTemplate.queryFunctionForInt(querySql, vars);
            return total;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<BtsReportBO> btsReport(String startTime, String endTime, String action, String userId, String neTypeId, String code) throws DAOException {
        Connection conn = null;
        try {
            
            conn = this.getConnection();
            String querySql = "{? = call pkg_history.find_list_history_bts(?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startTime);
            vars.add(endTime);
            vars.add(action);
            vars.add(userId);
            vars.add(neTypeId);
            vars.add(code);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BtsReportBO item = new BtsReportBO();
                    item.setUserName(rs.getString("action_user"));
                    item.setCreateTime(rs.getDate("date_created"));
                    item.setAction(rs.getString("action"));
                    item.setIpClient(rs.getString("ip_client"));
                    item.setTinh(rs.getString("don_vi_tinh_tp"));
                    item.setBuildingId(rs.getString("building_id"));
                    item.setTramDaId(rs.getString("tram_du_an_id"));

//                    item.setQuan(rs.getString("TEN_QUAN_HUYEN"));
//                    item.setXa(rs.getString("TEN_PHUONG_XA"));
                    item.setDiachi(rs.getString("dia_chi"));
                    item.setNgayHoatDong(rs.getString("ngay_hoat_dong"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setTenBts(rs.getString("ten_bts"));
                    item.setNgayDangKy(rs.getString("ngay_dang_ky"));
                    item.setNgayKiemDuyet(rs.getString("ngay_kiem_duyet"));
                    item.setNgayCapPhep(rs.getString("ngay_cap_phep"));

                    item.setTrangThaiHdId(rs.getString("TRANG_THAI_HD_ID"));
                    item.setTrangThaiHd(rs.getString("TEN_TRANGTHAI_HD"));
                    item.setTrangThaiQlId(rs.getString("TRANG_THAI_QL_ID"));
                    item.setTrangThaiQl(rs.getString("TEN_TRANGTHAI_QL"));

                    item.setTenTrenHeThong(rs.getString("ten_tren_he_thong"));
                    item.setTenBscRnc(rs.getString("ten_bsc_rnc"));
                    item.setTenBscRncQl(rs.getString("ten_bsc_rnc_ql"));
                    item.setMscMss(rs.getString("msc_mss"));
                    item.setSgsn(rs.getString("sgsn"));
                    item.setMaNode(rs.getString("MA_NODE"));
                    item.setDcHsdpa42M(rs.getString("DC_HSDPA_42M"));
                    item.setFilterUser(rs.getString("FILTER_USER"));
                    item.setFrequencyBand(rs.getString("Ten_bang_tan"));
                    item.setLatitude(rs.getString("LATITUDE"));
                    item.setLongitude(rs.getString("LONGITUDE"));
                    item.setCosite2G3GType(rs.getString("COSITE_2G_3G_TYPE"));
                    item.setMaCosite2G3GType(rs.getString("MA_COSITE_2G_3G"));
                    item.setThietbiId(rs.getString("THIET_BI_ID"));
                    item.setLoaiTramId(rs.getString("LOAI_TRAM_ID"));
                    item.setTenThietBi(rs.getString("TEN_THIET_BI"));
                    item.setTenLoaiTram(rs.getString("TEN_LOAI_TRAM"));
                    item.setCauhinh(rs.getString("CAU_HINH_PORT"));
                    item.setCauhinhSoTRX("");
//                    item.setNgayBaoDuong(rs.getString("NGAY_BAO_DUONG"));
//                    item.setDonViThucHien(rs.getString("DON_VI_THUC_HIEN"));
//                    item.setMaKiemDinh(rs.getString("MA_KIEM_DINH"));
//                    item.setNgayHieuLuc(rs.getString("NGAY_HIEU_LUC"));
//                    item.setNgayHetHieuLuc(rs.getString("NGAY_HET_HIEU_LUC"));
                    item.setTenNguoiQL(rs.getString("TEN_NG_QLY"));
                    item.setSoDTNgQL(rs.getString("SDT_NG_QLY"));
                    //item.setChungCSHT(rs.getString("CHUNG_CSHT"));
//                    item.setLoaiTramCSHT(rs.getString("LOAI_TRAM_CSHT"));
//                    item.setDocaoAnTen(rs.getString("DO_CAO_ANTEN"));
//                    item.setDoCaoNhaDatAnten(rs.getString("DO_CAO_NHA_DAT_ANTEN"));
//                    item.setLoaiAnTenId(rs.getString("LOAI_ANTEN_ID"));
//                    item.setNgayHDTuNguon(rs.getString("NGAY_HD_TU_NGUON"));
//                    item.setLoaiTuNguonId(rs.getString("LOAI_TU_NGUON_ID"));
//                    item.setSoModuleTuNguon(rs.getString("SO_MODULE_TU_NGUON"));
//                    item.setNgayHDMayNo(rs.getString("NGAY_HD_MAY_NO"));
//                    item.setLoaiMayNoId(rs.getString("LOAI_MAY_NO_ID"));
//                    item.setCongSuatMayNo(rs.getString("CONG_SUAT_MAY_NO"));
//                    item.setMayNoCoDinhDiDong(rs.getString("trang_thai_dat_may_no"));
//                    item.setNgayHDAccu(rs.getString("NGAY_HD_ACCU"));
//                    item.setLoaiAcQuyId(rs.getString("LOAI_AC_QUY_ID"));
//                    item.setDungLuongAcc(rs.getString("dung_luong_accu"));
//                    item.setThoiGianHdSauMatDien(rs.getString("THOI_GIAN_HD_SAU_MAT_DIEN"));
//                    item.setNgayBaoDuongAccu(rs.getString("NGAY_BAO_DUONG_ACCU"));
//                    item.setLoaiTruyenDanId(rs.getString("LOAI_TRUYEN_DAN_ID"));
//                    item.setDuongLuongTruyenDan(rs.getString("DUNG_LUONG_TRUYEN_DAN"));
//                    item.setDienTroTiepDia(rs.getString("DIEN_TRO_TIEP_DIA"));

                       item.setNote(rs.getString("note"));
                    return item;
                }
            }, vars);
            
            return (List<BtsReportBO>) list;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<CshtHistoryBO> cshtHistory(String startTime, String endTime, String action, String userId, String buildingId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_HISTORY.find_list_history_csht(?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startTime);
            vars.add(endTime);
            vars.add(action);
            vars.add(userId);
            vars.add(buildingId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CshtHistoryBO item = new CshtHistoryBO();
                    PhuTroBO phutro = new PhuTroBO();
                    BuildingBO buid = new BuildingBO();
                    phutro.setId(rs.getLong("phu_tro_id"));
                    buid.setCode(rs.getString("ma_building"));
                    buid.setId(rs.getLong("building_id"));
                    buid.setTinhName(rs.getString("ten_tinh_tp"));
                    buid.setQuanName(rs.getString("ten_quan_huyen"));
                    buid.setAddress(rs.getString("dia_chi"));

                    phutro.setAcQuy(rs.getString("ten_loai_ac_quy"));

                    phutro.setDungLuongAccu(rs.getString("dung_luong_accu"));
                    phutro.setNgayHDAccu(rs.getDate("ngay_hd_accu"));
                    phutro.setThoigianHDSauMatDien(rs.getString("thoi_gian_hd_sau_mat_dien"));
                    phutro.setNgayBaoDuongAccu(rs.getDate("ngay_bao_duong_accu"));
                    //phutro.setLoaiMayNoId(rs.getLong("loai_may_no_id"));
                    phutro.setMayNo(rs.getString("ten_loai_may_no"));
                    phutro.setCongSuatMayNo(rs.getString("cong_suat_may_no"));
                    phutro.setNgayHDMayNo(rs.getDate("ngay_hd_may_no"));
                    phutro.setTrangThaiMayNo(rs.getString("trang_thai_dat_may_no"));

                    //tem.setLoaiTruyenDanId(rs.getLong("loai_truyen_dan_id"));
                    phutro.setTruyenDan(rs.getString("ten_loai_truyen_dan"));
                    phutro.setDungLuongTruyenDan(rs.getString("dung_luong_truyen_dan"));
                    //item.setLoaiAnTenId(rs.getLong("loai_anten_id"));
                    phutro.setAnTen(rs.getString("ten_loai_anten"));
                    phutro.setDoCaoAnTen(rs.getString("do_cao_anten"));
                    phutro.setDoCaoNhaDatAnTen(rs.getString("do_cao_nha_dat_anten"));
                    phutro.setTuNguon(rs.getString("ten_loai_tu_nguon"));
                    //item.setLoaiTuNguonId(rs.getLong("loai_tu_nguon_id"));
                    phutro.setNgayHDTuNguon(rs.getDate("ngay_hd_tu_nguon"));
                    phutro.setSoModuleTuNguon(rs.getString("so_module_tu_nguon"));
                    phutro.setDienTroTiepDia(rs.getString("dien_tro_tiep_dia"));
                    phutro.setChungCsht(rs.getString("chung_csht"));
                    phutro.setLoaiTramCsht(rs.getString("loai_tram_csht"));

                    item.setUserName(rs.getString("username"));
                    item.setCreateTime(rs.getDate("date_created"));
                    item.setAction(rs.getString("action"));
                    item.setIpClient(rs.getString("ip_client"));

                    item.setBuildingBO(buid);
                    item.setPhuTroBO(phutro);
                    
                    item.setNote(rs.getString("note"));
                    return item;
                }
            }, vars);

            return (List<CshtHistoryBO>) list;
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
    public List<TramQHHistoryBO> tramQHHistory(String startTime, String endTime, String action, String userId, String qhId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_HISTORY.find_list_history_tram_qh(?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startTime);
            vars.add(endTime);
            vars.add(action);
            vars.add(userId);
            vars.add(qhId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TramQHHistoryBO item = new TramQHHistoryBO();
                    item.setUserName(rs.getString("user_name"));
                    item.setCreateTime(rs.getDate("date_created"));
                    item.setAction(rs.getString("action"));
                    item.setIpClient(rs.getString("ip_client"));

                    ProjectStationBO record = new ProjectStationBO();
                    record.setTramQhId(rs.getInt("TRAM_QH_ID"));
                    record.setQhTinhId(rs.getInt("QH_TINH_ID"));
                    record.setMaQh(rs.getString("MA_QUY_HOACH"));
                    record.setTenQh(rs.getString("TEN_QUY_HOACH"));
                    record.setTramDaId(rs.getInt("TRAM_DU_AN_ID"));
                    record.setBuildingId(rs.getInt("BUILDING_ID"));
                    record.setBuildingCode(rs.getString("MA_BUILDING"));
                    record.setNamKhoiTao(rs.getDate("NAM_KHOI_TAO"));
                    record.setLongitude(rs.getFloat("LONGITUDE"));
                    record.setLatitude(rs.getFloat("LATITUDE"));
                    record.setLoaiCongNgheId(rs.getInt("ID_LOAI_CONG_NGHE"));
                    record.setLoaiCongNghe(rs.getString("TEN_LOAI_CONG_NGHE"));
                    record.setBangTanId(rs.getInt("BANG_TAN_ID"));
                    record.setBangTan(rs.getString("ten_bang_tan"));
                    record.setLoaiPtCshtId(rs.getInt("LOAI_PT_CSHT_ID"));
                    record.setPtCsht(rs.getString("ten_loai_pt_csht"));
                    record.setTrangThaiCshtId(rs.getInt("TRANG_THAI_CSHT_ID"));
                    record.setTrangThaiCsht(rs.getString("ten_trang_thai_csht"));
                    record.setDonViPheDuyet(rs.getInt("DON_VI_PHE_DUYET_ID"));
                    record.setSoHieuVanBan(rs.getString("SO_HIEU_VAN_BAN"));
                    record.setNgayPheDuyet(rs.getDate("NGAY_PHE_DUYET"));
                    record.setNgayDieuChinhGanNhat(rs.getDate("NGAY_DIEU_CHINH_GAN_NHAT"));
                    record.setDonViDieuChinhId(rs.getInt("DON_VI_DIEU_CHINH_ID"));
                    record.setNgayPhatSong(rs.getDate("NGAY_PHAT_SONG"));
                    //cam ket
                    record.setDVI_TRACH_NHIEM_CCTB_ID(rs.getInt("DVI_TRACH_NHIEM_CCTB_ID"));
                    record.setDVI_TRACH_NHIEM_CCTB(rs.getString("DVI_TRACH_NHIEM_CCTB"));
                    record.setNGUON_THIET_BI_ID(rs.getInt("NGUON_THIET_BI_ID"));
                    record.setNGUON_THIET_BI(rs.getString("ten_nguon_thiet_bi"));
                    record.setTHOI_DIEM_DAP_UNG_DU_KIEN(rs.getDate("THOI_DIEM_DAP_UNG_DU_KIEN"));
                    record.setCONG_NGHE_DAP_UNG(rs.getInt("CONG_NGHE_DAP_UNG"));
                    record.setTEN_CONG_NGHE_DAP_UNG(rs.getString("TEN_CONG_NGHE_DAP_UNG"));
                    record.setCHUNG_LOAI_THIET_BI(rs.getString("CHUNG_LOAI_THIET_BI"));
                    record.setTHOI_DIEM_DAP_UNG_THUC_TE(rs.getDate("THOI_DIEM_DAP_UNG_THUC_TE"));
                    record.setKHO_KHAN_VUONG_MAC(rs.getString("KHO_KHAN_VUONG_MAC"));
                    //trunglk_start
                    record.setDVI_TRACH_NHIEM_TU_NGUON(rs.getString("DVI_TRACH_NHIEM_TU_NGUON"));
                    record.setDVI_TRACH_NHIEM_NGUON_DC_ID(rs.getInt("DVI_TRACH_NHIEM_NGUON_DC_ID"));
                    record.setNGUON_THIET_BI_TU_NGUON_ID(rs.getInt("NGUON_THIET_BI_TU_NGUON_ID"));
                    record.setTU_NGUON_DC(rs.getString("TU_NGUON_DC"));
                    record.setLOAI_TU_NGUON_ID(rs.getInt("LOAI_TU_NGUON_ID"));
                    record.setTEN_LOAI_TU_NGUON(rs.getString("TEN_LOAI_TU_NGUON"));
                    record.setDUNG_LUONG_TU_NGUON(rs.getInt("DUNG_LUONG_TU_NGUON"));
                    record.setSO_RACTIFIER(rs.getInt("SO_RACTIFIER"));
                    record.setDUNG_LUONG_ACCU(rs.getInt("DUNG_LUONG_ACCU"));
                    record.setSO_LUONG_ACCU(rs.getInt("SO_LUONG_ACCU"));
                    record.setDIEN_AP_ACCU(rs.getInt("DIEN_AP_ACCU"));
                    record.setNGAY_DAP_UNG_NGUON_DC_DU_KIEN(rs.getDate("NGAY_DAP_UNG_NGUON_DC_DU_KIEN"));
                    record.setNGAY_DAP_UNG_NGUON_DC_TT(rs.getDate("NGAY_DAP_UNG_NGUON_DC_TT"));
                    //trunglk_end

                    //co so ha tang
                    record.setDVI_TRACH_NHIEM_CSHT_ID(rs.getInt("DVI_TRACH_NHIEM_CSHT_ID"));
                    record.setTinhTpId(rs.getInt("TINHTP_ID"));
                    record.setTinhTp(rs.getString("TEN_TINH_TP"));
                    record.setQuanHuyenId(rs.getInt("QUANHUYEN_ID"));
                    record.setQuanHuyen(rs.getString("ten_quan_huyen"));
                    record.setPhuongXaId(rs.getInt("PHUONGXA_ID"));
                    record.setPhuongXa(rs.getString("ten_phuong_xa"));
                    record.setDIA_CHI(rs.getString("DIA_CHI"));
                    record.setTEN_TRAM(rs.getString("TEN_TRAM"));
                    record.setCACH_XAY_CSHT_ID(rs.getInt("CACH_XAY_CSHT_ID"));
                    record.setCACH_XAY_CSHT(rs.getString("ten_cach_xay_csht"));
                    record.setLOAI_DAT_ID(rs.getInt("LOAI_DAT_ID"));
                    record.setNGAY_CAP_DAT(rs.getDate("NGAY_CAP_DAT"));
                    record.setNGAY_XIN_PHEP_XD_NHA_TRAM(rs.getDate("NGAY_XIN_PHEP_XD_NHA_TRAM"));
                    record.setNGAY_HOAN_THANH_THU_TUC(rs.getDate("NGAY_HOAN_THANH_THU_TUC"));
                    record.setNGAY_KHOI_CONG_XD_NHA_TRAM(rs.getDate("NGAY_KHOI_CONG_XD_NHA_TRAM"));
                    record.setNGAY_HOAN_THANH_XD_NHA_TRAM(rs.getDate("NGAY_HOAN_THANH_XD_NHA_TRAM"));
                    record.setLOAI_NHA_TRAM_ID(rs.getInt("LOAI_NHA_TRAM_ID"));
                    record.setNGAY_XIN_PHEP_DO_CAO_COT(rs.getDate("NGAY_XIN_PHEP_DO_CAO_COT"));
                    record.setNGAY_CAP_PHEP_DO_CAO_COT(rs.getDate("NGAY_CAP_PHEP_DO_CAO_COT"));
                    record.setNGAY_HOAN_THANH_THU_TUC_XD_COT(rs.getDate("NGAY_HOAN_THANH_THU_TUC_XD_COT"));
                    record.setNGAY_KHOI_CONG_XD_COT(rs.getDate("NGAY_KHOI_CONG_XD_COT"));
                    record.setNGAY_HOAN_THANH_XD_COT(rs.getDate("NGAY_HOAN_THANH_XD_COT"));
                    record.setLOAI_COT_ANTEN_ID(rs.getInt("LOAI_COT_ANTEN_ID"));
                    record.setDO_CAO_COT(rs.getInt("DO_CAO_COT"));
                    record.setDO_CAO_VI_TRI_XAY_COT_ANTTEN(rs.getInt("DO_CAO_VI_TRI_XAY_COT_ANTTEN"));
                    record.setLOAI_TRUYEN_DAN_ID(rs.getInt("LOAI_TRUYEN_DAN_ID"));
                    record.setGIAODIEN_TD_E1(rs.getInt("GIAODIEN_TD_E1"));
                    record.setGIAODIEN_TD_FE_ID(rs.getInt("GIAODIEN_TD_FE_ID"));
                    record.setGIAODIEN_TD_GE_ID(rs.getInt("GIAODIEN_TD_GE_ID"));
                    record.setGIAODIEN_TD_STM1(rs.getInt("GIAODIEN_TD_STM1"));
                    record.setNGAY_KHOI_CONG_TD(rs.getDate("NGAY_KHOI_CONG_TD"));
                    record.setNGAY_HOAN_THANH_TD(rs.getDate("NGAY_HOAN_THANH_TD"));
                    record.setNGAY_DAP_UNG_DIEN_AC(rs.getDate("NGAY_DAP_UNG_DIEN_AC"));
                    record.setHE_THONG_DIEN_NHA_TRAM_ID(rs.getInt("HE_THONG_DIEN_NHA_TRAM_ID"));
                    record.setHE_THONG_DIEU_HOA_ID(rs.getInt("HE_THONG_DIEU_HOA_ID"));
                    record.setHE_THONG_TIEP_DAT_ID(rs.getInt("HE_THONG_TIEP_DAT_ID"));
                    record.setHE_THONG_MAY_NO_ID(rs.getInt("HE_THONG_MAY_NO_ID"));
                    record.setNGAY_HOAN_THANH_PHU_TRO(rs.getDate("NGAY_HOAN_THANH_PHU_TRO"));
                    record.setDOI_TUONG_THONG_BAO(rs.getString("DOI_TUONG_THONG_BAO"));
                    record.setSO_HIEU_THONG_BAO(rs.getString("SO_HIEU_THONG_BAO"));
                    record.setNGAY_THONG_BAO_HT_CSHT(rs.getDate("NGAY_THONG_BAO_HT_CSHT"));
                    record.setKHO_KHAN_VUONG_MAC_CSHT(rs.getString("KHO_KHAN_VUONG_MAC_CSHT"));
                    //antena
                    record.setDVI_TRACH_NHIEM_ANTEN(rs.getInt("DVI_TRACH_NHIEM_ANTEN"));
                    record.setNGAY_DAP_UNG_ANTENA_DU_KIEN(rs.getDate("NGAY_DAP_UNG_ANTENA_DU_KIEN"));
                    record.setNGAY_DAP_UNG_ANTENA_THUC_TE(rs.getDate("NGAY_DAP_UNG_ANTENA_THUC_TE"));
                    record.setLOAI_ANTEN_ID1(rs.getInt("LOAI_ANTEN_ID1"));
                    record.setTEN_ANTENA1(rs.getString("TEN_ANTENA1"));
                    record.setHANG_SX_ANTENA1(rs.getString("HANG_SX_ANTENA1"));
                    record.setSO_LUONG_ANTENA1(rs.getInt("SO_LUONG_ANTENA1"));
                    record.setBANG_TANG_ANTENA_ID1(rs.getInt("BANG_TANG_ANTENA_ID1"));
                    record.setCAU_HINH_PORT_ID1(rs.getInt("CAU_HINH_PORT_ID1"));
                    record.setCAU_HINH_GAIN1(rs.getString("CAU_HINH_GAIN1"));
                    record.setCAU_HINH_BEAM_WIDTH1(rs.getString("CAU_HINH_BEAM_WIDTH1"));
                    record.setTRONG_LUONG1(rs.getInt("TRONG_LUONG1"));
                    record.setKICH_CO_CAO1(rs.getInt("KICH_CO_CAO1"));
                    record.setKICH_CO_RONG1(rs.getInt("KICH_CO_RONG1"));
                    record.setKICH_CO_SAU1(rs.getInt("KICH_CO_SAU1"));
                    record.setDO_CAO_ANTENA_SO_VOI_MAT_DAT1(rs.getInt("DO_CAO_ANTENA_LAP_DAT1"));
                    //antena
                    record.setLOAI_ANTEN_ID2(rs.getInt("LOAI_ANTEN_ID2"));
                    record.setTEN_ANTENA2(rs.getString("TEN_ANTENA2"));
                    record.setHANG_SX_ANTENA2(rs.getString("HANG_SX_ANTENA2"));
                    record.setSO_LUONG_ANTENA2(rs.getInt("SO_LUONG_ANTENA2"));
                    record.setBANG_TANG_ANTENA_ID2(rs.getInt("BANG_TANG_ANTENA_ID2"));
                    record.setCAU_HINH_PORT_ID2(rs.getInt("CAU_HINH_PORT_ID2"));
                    record.setCAU_HINH_GAIN2(rs.getString("CAU_HINH_GAIN2"));
                    record.setCAU_HINH_BEAM_WIDTH2(rs.getString("CAU_HINH_BEAM_WIDTH2"));
                    record.setTRONG_LUONG2(rs.getInt("TRONG_LUONG2"));
                    record.setKICH_CO_CAO2(rs.getInt("KICH_CO_CAO2"));
                    record.setKICH_CO_RONG2(rs.getInt("KICH_CO_RONG2"));
                    record.setKICH_CO_SAU2(rs.getInt("KICH_CO_SAU2"));
                    record.setDO_CAO_ANTENA_SO_VOI_MAT_DAT2(rs.getInt("DO_CAO_ANTENA_LAP_DAT2"));
                    //antena
                    record.setLOAI_ANTEN_ID3(rs.getInt("LOAI_ANTEN_ID3"));
                    record.setTEN_ANTENA3(rs.getString("TEN_ANTENA3"));
                    record.setHANG_SX_ANTENA3(rs.getString("HANG_SX_ANTENA3"));
                    record.setSO_LUONG_ANTENA3(rs.getInt("SO_LUONG_ANTENA3"));
                    record.setBANG_TANG_ANTENA_ID3(rs.getInt("BANG_TANG_ANTENA_ID3"));
                    record.setCAU_HINH_PORT_ID3(rs.getInt("CAU_HINH_PORT_ID3"));
                    record.setCAU_HINH_GAIN3(rs.getString("CAU_HINH_GAIN3"));
                    record.setCAU_HINH_BEAM_WIDTH3(rs.getString("CAU_HINH_BEAM_WIDTH3"));
                    record.setTRONG_LUONG3(rs.getInt("TRONG_LUONG3"));
                    record.setKICH_CO_CAO3(rs.getInt("KICH_CO_CAO3"));
                    record.setKICH_CO_RONG3(rs.getInt("KICH_CO_RONG3"));
                    record.setKICH_CO_SAU3(rs.getInt("KICH_CO_SAU3"));
                    record.setDO_CAO_ANTENA_SO_VOI_MAT_DAT3(rs.getInt("DO_CAO_ANTENA_LAP_DAT3"));
                    //
                    record.setTRANG_THAI_TRAM(rs.getString("TRANG_THAI_TRAM"));
                    record.setNOTE(rs.getString("NOTE"));

                    item.setTram(record);
                    item.setNote(rs.getString("note"));
                    return item;
                }
            }, vars);

            return (List<TramQHHistoryBO>) list;
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
    public List<TramDAHistoryBO> tramDAHistory(String startTime, String endTime, String action, String userId, String tramId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_HISTORY.find_list_history_tram_da(?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startTime);
            vars.add(endTime);
            vars.add(action);
            vars.add(userId);
            vars.add(tramId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TramDuAnBO tramDABO = new TramDuAnBO();
                    Long tramDuAnId = rs.getLong("tram_du_an_id");
                    tramDABO.setId(rs.wasNull() ? null : tramDuAnId);
                    Long duAnId = rs.getLong("du_an_id");
                    tramDABO.setDuAnId(rs.wasNull() ? null : duAnId);
                    tramDABO.setMaDuAn(rs.getString("ma_du_an"));
                    tramDABO.setTenDuAn(rs.getString("ten_du_an"));
                    Long tramQhId = rs.getLong("tram_qh_id");
                    tramDABO.setTramQHId(rs.wasNull() ? null : tramQhId);
                    tramDABO.setMaQuyHoach(rs.getString("ma_quy_hoach"));
                    tramDABO.setTenQuyHoach(rs.getString("ten_quy_hoach"));
                    tramDABO.setMaSoHopDong(rs.getString("ma_so_hop_dong"));
                    Long tinhTpId = rs.getLong("tinhtp_id");
                    tramDABO.setTinhTpId(rs.wasNull() ? null : tinhTpId);

                    tramDABO.setTenTinhTp(rs.getString("tinh_tp"));
                    //Long quanHuyenId = rs.getLong("quan_huyen_id");
                    //tramDABO.setQuanHuyenId(rs.wasNull() ? null : quanHuyenId);
                    tramDABO.setTenQuanHuyen(rs.getString("quan_huyen"));
                    tramDABO.setAddress(rs.getString("dia_chi"));
                    tramDABO.setMaTramDuAn(rs.getString("ma_tram_da"));
                    tramDABO.setTenTramDuAn(rs.getString("ten_tram"));
                    tramDABO.setMaTramBTS(rs.getString("ma_tram_bts"));
                    tramDABO.setMaTramNodeB(rs.getString("ma_tram_nodeB"));
                    tramDABO.setMaTramQuyHoach(rs.getString("ma_tram_quy_hoach"));
                    tramDABO.setHienTrangTram(rs.getString("hien_trang_tram"));
                    tramDABO.setLongitude(rs.wasNull() ? null : rs.getString("longitude"));
                    tramDABO.setLatitude(rs.wasNull() ? null : rs.getString("latitude"));
//cam ket thiet bi                   
                    Long vnptNetPheDuyet = rs.getLong("vnpt_net_phe_duyet");
                    tramDABO.setVnptNetPheDuyet(rs.wasNull() ? null : vnptNetPheDuyet);
                    tramDABO.setCauHinhThietBi(rs.getString("cau_hinh_thiet_bi"));
                    //Long nguonThietBi = rs.getLong("nguon_thiet_bi");
                    tramDABO.setStrNguonThietBi(rs.getString("nguon_thiet_bi"));
                    tramDABO.setStrLoaiCongNghe(rs.getString("loai_cong_nghe"));
                    //tramDABO.setNguonThietBi(rs.wasNull() ? null : nguonThietBi);
                    //Long loaiCongNghe = rs.getLong("loai_cong_nghe");
                    //tramDABO.setLoaiCongNghe(rs.wasNull() ? null : loaiCongNghe);
                    tramDABO.setChungLoaiThietBi(rs.getString("chung_loai_thiet_bi"));
                    tramDABO.setChungLoaiAnten(rs.getString("chung_loai_anten"));
//cam ket ha tang
                    tramDABO.setLongitudeKhaoSat(rs.wasNull() ? null : rs.getString("longitude_khao_sat"));
                    tramDABO.setLatitudeKhaoSat(rs.wasNull() ? null : rs.getString("latitude_khao_sat"));
                    Long nhaTram = rs.getLong("nha_tram");
                    tramDABO.setNhaTram(rs.wasNull() ? null : nhaTram);
                    Long cotAnten = rs.getLong("cot_anten");
                    tramDABO.setCotAnten(rs.wasNull() ? null : cotAnten);
                    Long cauCapNgoai = rs.getLong("cau_cap_ngoai");
                    tramDABO.setCauCapNgoai(rs.wasNull() ? null : cauCapNgoai);
                    //Long tuNguon = rs.getLong("tu_nguon");
                    tramDABO.setStrTuNguon(rs.getString("tu_nguon"));
                    //tramDABO.setTuNguon(rs.wasNull() ? null : tuNguon);
                    tramDABO.setDungLuongTuNguon(rs.getString("dung_luong_tu_nguon"));
                    tramDABO.setSoModuleTuNguon(rs.getString("so_module_tu_nguon"));
                    Long chungLoaiAccu = rs.getLong("chung_loai_accu");
                    tramDABO.setChungLoaiAccu(rs.wasNull() ? null : chungLoaiAccu);
                    tramDABO.setDungLuongAccu(rs.getString("dung_luong_accu"));
                    tramDABO.setSoLuongToAccu(rs.getString("so_luong_to_accu"));
                    Long truyenDan = rs.getLong("truyen_dan");
                    tramDABO.setTruyenDan(rs.wasNull() ? null : truyenDan);
                    Long dieuHoa = rs.getLong("dieu_hoa");
                    tramDABO.setDieuHoa(rs.wasNull() ? null : dieuHoa);
                    Long dienAc = rs.getLong("dien_ac");
                    tramDABO.setDienAc(rs.wasNull() ? null : dienAc);
                    Long dienAcNoiTram = rs.getLong("dien_ac_noi_tram");
                    tramDABO.setDienAcNoiTram(rs.wasNull() ? null : dienAcNoiTram);
                    Long duDkLapEnodeB = rs.getLong("du_dk_lap_enodeb");
                    tramDABO.setDuDkLapEnodeb(rs.wasNull() ? null : duDkLapEnodeB);
                    Long capMoiTuNguonDc = rs.getLong("cap_moi_tu_nguon_dc");
                    tramDABO.setCapMoiTuNguonDc(rs.wasNull() ? null : capMoiTuNguonDc);
                    Long capMoiAccu = rs.getLong("cap_moi_accu");
                    tramDABO.setCapMoiAccu(rs.wasNull() ? null : capMoiAccu);
                    tramDABO.setSwapAnten(rs.getString("swap_anten"));
                    tramDABO.setNgayHoanThanhKs(rs.getDate("ngay_hoan_thanh_ks"));
                    tramDABO.setNgayGuiSoLieu(rs.getDate("ngay_gui_so_lieu"));
                    tramDABO.setDauMoiNhanThietBi(rs.getString("dau_moi_nhan_thiet_bi"));
                    tramDABO.setDauMoiQLCSHT(rs.getString("DAU_MOI_QL_CSHT_TRAM"));
                    tramDABO.setDonViLapDat(rs.getString("don_vi_lap_dat"));
//trang thai trien khai netx
                    tramDABO.setNgayPheDuyetKhaoSat(rs.getDate("ngay_phe_duyet_khao_sat"));
                    tramDABO.setNgayTiepNhanTruyenDan(rs.getDate("ngay_tiep_nhan_truyen_dan"));
//trang thai trien khai
                    tramDABO.setKeHoachXuatThietBi(rs.getDate("ke_hoach_xuat_thiet_bi"));
                    tramDABO.setNgayXuatThietBiThucTe(rs.getDate("ngay_xuat_thiet_bi_thuc_te"));
                    tramDABO.setNgayTiepNhanTb(rs.getDate("ngay_tiep_nhan_tb"));
                    tramDABO.setKeHoachTbDenSite(rs.getDate("ke_hoach_tb_den_site"));
                    tramDABO.setKeHoachLapDat(rs.getDate("ke_hoach_lap_dat"));
                    tramDABO.setNgayBatDauLapDat(rs.getDate("ngay_bat_dau_lap_dat"));
                    tramDABO.setNgayHTLapDatTb(rs.getDate("NGAY_HT_LAP_TB"));
                    tramDABO.setKeHoachHoaMang(rs.getDate("ke_hoach_hoa_mang"));
                    tramDABO.setNgayHoaMangThucTe(rs.getDate("ngay_hoa_mang_thuc_te"));
                    tramDABO.setKeHoachPhatSongCt(rs.getDate("ke_hoach_phat_song_ct"));
                    tramDABO.setNgayPhatSongCt(rs.getDate("ngay_phat_song_ct"));
                    tramDABO.setKeHoachNghiemThu(rs.getDate("ke_hoach_nghiem_thu"));
                    tramDABO.setNgayNghiemThu(rs.getDate("ngay_nghiem_thu"));
                    tramDABO.setDauMoiVnptNet(rs.getString("dau_moi_vnpt_net"));
                    tramDABO.setDonViVanChuyen(rs.getString("don_vi_van_chuyen"));
                    tramDABO.setGhiChu(rs.getString("ghi_chu"));
                    //Long trangThaiTram = rs.getLong("trang_thai_tram");
                    //tramDABO.setTrangThaiTram(rs.wasNull() ? null : trangThaiTram);
                    tramDABO.setTenTrangThaiTram(rs.getString("trang_thai_tram"));
//                    Long trangthaiAction = Long.parseLong(status);
//                    tramDABO.setTrangThaiTramAction(status);

                    TramDAHistoryBO item = new TramDAHistoryBO();
                    item.setUserName(rs.getString("user_name"));
                    item.setCreateTime(rs.getDate("date_created"));
                    item.setAction(rs.getString("action"));
                    item.setIpClient(rs.getString("ip_client"));

                    item.setTram(tramDABO);
                    
                    item.setNote(rs.getString("note"));
                    return item;
                }
            }, vars);

            return (List<TramDAHistoryBO>) list;
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
    public List<PgwInfoBO> hisPsCore(String startTime, String endTime, String action, String userId, String code) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_cs_core_history.find_list_history(?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startTime);
            vars.add(endTime);
            vars.add(action);
            vars.add(userId);
            vars.add(code);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PgwInfoBO item = new PgwInfoBO();
                    item.setUserName(rs.getString("action_user"));
                    item.setCreateTime(rs.getDate("date_created"));
                    item.setAction(rs.getString("action"));
                    item.setIpClient(rs.getString("ip_client"));
                    item.setCode(rs.getString("ma_node"));
                    item.setCodeBuilding(rs.getString("ma_building"));
                    item.setAddress(rs.getString("dia_diem_lap_dat"));
                    item.setNgayHoatDong(rs.getDate("ngay_hoat_dong"));
                    item.setNeStatus(rs.getString("ne_status"));
                    item.setIpNe(rs.getString("ip_ne"));
                    item.setOpc(rs.getString("opc"));
                    item.setName(rs.getString("name"));
                    item.setHwFlatForm(rs.getString("hw_flatform"));
                    item.setSoftVersion(rs.getString("software_version"));
                    item.setDonViName(rs.getString("ten_don_vi"));
                    item.setLicense(rs.getString("license"));
                    item.setTenCard(rs.getString("ten_card"));
                    item.setSeria(rs.getString("serial_part_number"));
                    item.setCardStatus(rs.getString("card_status"));
                    item.setCardSL(rs.getInt("so_luong_card"));
                    item.setCardVersion(rs.getString("card_version"));
                    item.setTenHeThong(rs.getString("ten_tren_he_thong"));
                    item.setTenNeType(rs.getString("ten_loai_ne"));
                    item.setTenNgQLy(rs.getString("ten_ng_qly"));
                    item.setSDTQLy(rs.getString("sdt_ng_qly"));
                    return item;
                }
            }, vars);
            
            return (List<PgwInfoBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<CsCoreBO> hisCsCore(String startTime, String endTime, String action, String userId, String code) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_cs_core_history.find_list_history(?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startTime);
            vars.add(endTime);
            vars.add(action);
            vars.add(userId);
            vars.add(code);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CsCoreBO item = new CsCoreBO();
                    item.setUserName(rs.getString("action_user"));
                    item.setCreateTime(rs.getDate("date_created"));
                    item.setAction(rs.getString("action"));
                    item.setIpClient(rs.getString("ip_client"));
                    item.setCode(rs.getString("ma_node"));
                    item.setCodeBuilding(rs.getString("ma_building"));
                    //item.setAddress(rs.getString("dia_diem_lap_dat"));
                    item.setNgayHoatDong(rs.getDate("ngay_hoat_dong"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setNgayDangKy(rs.getDate("ngay_dang_ky"));
                    item.setOpc(rs.getString("opc"));
                    item.setNumberalSystem(rs.getString("numeral_system"));
                    item.setOpc(rs.getString("opc1"));
                    item.setNumberalSystem(rs.getString("numeral_system1"));

                    item.setName(rs.getString("name"));

                    item.setSoftVersion(rs.getString("software_version"));
                    item.setDonViName(rs.getString("ten_don_vi"));

                    item.setSeria(rs.getString("serial_number"));

                    item.setTenHeThong(rs.getString("ten_tren_he_thong"));
                    item.setTenNeType(rs.getString("ten_loai_ne"));
                    item.setTenNgQLy(rs.getString("ten_ng_qly"));
                    item.setSDTQLy(rs.getString("sdt_ng_qly"));
                    return item;
                }
            }, vars);
            
            return (List<CsCoreBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }
}
