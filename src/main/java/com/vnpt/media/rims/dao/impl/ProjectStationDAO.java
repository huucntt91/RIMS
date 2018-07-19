/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.ProjectStationBO;
import com.vnpt.media.rims.bean.TechnologyTypeBO;
import com.vnpt.media.rims.controller.project.StationStatus;
import com.vnpt.media.rims.dao.IProjectStation;
import com.vnpt.media.rims.exception.DAOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectStationDAO extends GenericDAO implements IProjectStation {

    @Override
    public List<TechnologyTypeBO> searchTechType(String techType) throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = this.getConnection();
            String sql = "begin ?:=pkg_dm_loai_cong_nghe.fc_find_all(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2, 0);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                TechnologyTypeBO record = new TechnologyTypeBO();
                record.setTechId(rs.getInt("id_loai_cong_nghe"));
                record.setTechName(rs.getString("ten_loai_cong_nghe"));
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    @Override
    public List<ProjectStationBO> searchProjectStation(Integer tramQhId, Integer qhTinhId, String status, String tinhTpIds, String maTramQh, String tenTramQh, String tinhTpId, String loaiCongNgheId) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = this.getConnection();
            String sql = "begin ?:=PKG_PROJECT_STATION.search_tram_quy_hoach(?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tramQhId == null ? "" : tramQhId.toString());
            cs.setString(3, qhTinhId == null ? "" : qhTinhId.toString());
            cs.setString(4, status);
            cs.setString(5, tinhTpIds);
            cs.setString(6, maTramQh);
            cs.setString(7, tenTramQh);
            cs.setString(8, tinhTpId);
            cs.setString(9, loaiCongNgheId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
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
                //bo sung 1 so thong tin 
                record.setNgay_duoc_pd_cap_von_csht(rs.getDate("ngay_duoc_pd_cap_von_csht"));
                record.setTinh_trang_nha_tram(rs.getInt("tinh_trang_nha_tram"));
                record.setTinh_trang_cot_anten(rs.getInt("tinh_trang_cot_anten"));
                record.setTinh_trang_truyen_dan(rs.getInt("tinh_trang_truyen_dan"));
                record.setTinh_trang_nguon_dien(rs.getInt("tinh_trang_nguon_dien"));
                record.setCsht_danh_gia_netx(rs.getString("csht_danh_gia_netx"));
                record.setCsht_y_kien_netx(rs.getString("csht_y_kien_netx"));

                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    @Override
    public boolean insertProjectStation(ProjectStationBO tramQh, Long userId) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "begin ?:=PKG_PROJECT_STATION.insert_tram_quy_hoach(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);

            cs.setString(2, tramQh.getTramDaId() == null ? "" : tramQh.getTramDaId().toString());
            cs.setString(3, tramQh.getQhTinhId() == null ? "" : tramQh.getQhTinhId().toString());
            cs.setString(4, tramQh.getMaQh());
            cs.setString(5, tramQh.getTenQh());
            if (tramQh.getNamKhoiTao() == null) {
                cs.setNull(6, java.sql.Types.TIMESTAMP);
            } else {
                cs.setTimestamp(6, new java.sql.Timestamp(tramQh.getNamKhoiTao().getTime()));
            }
            cs.setString(7, tramQh.getLongitude() == null ? "" : tramQh.getLongitude().toString());
            cs.setString(8, tramQh.getLatitude() == null ? "" : tramQh.getLatitude().toString());
            cs.setString(9, tramQh.getLoaiCongNgheId() == null ? "" : tramQh.getLoaiCongNgheId().toString());
            cs.setString(10, tramQh.getBangTanId() == null ? "" : tramQh.getBangTanId().toString());
            cs.setString(11, tramQh.getLoaiPtCshtId() == null ? "" : tramQh.getLoaiPtCshtId().toString());
            cs.setString(12, tramQh.getTrangThaiCshtId() == null ? "" : tramQh.getTrangThaiCshtId().toString());
            cs.setString(13, tramQh.getDonViPheDuyet() == null ? "" : tramQh.getDonViPheDuyet().toString());
            cs.setString(14, tramQh.getSoHieuVanBan());
            if (tramQh.getNgayPheDuyet() == null) {
                cs.setNull(15, java.sql.Types.TIMESTAMP);
            } else {
                cs.setTimestamp(15, new java.sql.Timestamp(tramQh.getNgayPheDuyet().getTime()));
            }

            if (tramQh.getNgayDieuChinhGanNhat() == null) {
                cs.setNull(16, java.sql.Types.TIMESTAMP);
            } else {
                cs.setTimestamp(16, new java.sql.Timestamp(tramQh.getNgayDieuChinhGanNhat().getTime()));
            }

            cs.setString(17, tramQh.getDonViDieuChinhId() == null ? "" : tramQh.getDonViDieuChinhId().toString());

            if (tramQh.getNgayPhatSong() == null) {
                cs.setNull(18, java.sql.Types.TIMESTAMP);
            } else {
                cs.setTimestamp(18, new java.sql.Timestamp(tramQh.getNgayPhatSong().getTime()));
            }
            cs.setString(19, tramQh.getBuildingId() == null ? "" : tramQh.getBuildingId().toString());
            cs.setString(20, StationStatus.status0);
            cs.setString(21, tramQh.getTinhTpId() == null ? "" : tramQh.getTinhTpId().toString());
            cs.setString(22, tramQh.getQuanHuyenId() == null ? "" : tramQh.getQuanHuyenId().toString());
            cs.setString(23, tramQh.getPhuongXaId() == null ? "" : tramQh.getPhuongXaId().toString());
            cs.setString(24, tramQh.getDIA_CHI());
            cs.setString(25, userId == null ? "" : userId.toString());
            cs.executeQuery();
            int tramQhId = cs.getInt(1);
            if (tramQhId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateThongTinChung(ProjectStationBO tramQh, Long userId) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            if (tramQh.getTramQhId() == null
                    || tramQh.getMaQh() == null || tramQh.getTenQh() == null) {
                return false;
            }
            conn = this.getConnection();
            String sql = "begin ? := PKG_PROJECT_STATION.update_thong_tin_chung(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, tramQh.getTramQhId() == null ? "" : tramQh.getTramQhId().toString());
            cs.setString(3, tramQh.getTramDaId() == null ? "" : tramQh.getTramDaId().toString());
            cs.setString(4, tramQh.getQhTinhId() == null ? "" : tramQh.getQhTinhId().toString());
            cs.setString(5, tramQh.getMaQh());
            cs.setString(6, tramQh.getTenQh());
            if (tramQh.getNamKhoiTao() == null) {
                cs.setNull(7, java.sql.Types.TIMESTAMP);
            } else {
                cs.setTimestamp(7, new java.sql.Timestamp(tramQh.getNamKhoiTao().getTime()));
            }
            cs.setString(8, tramQh.getLongitude() == null ? "" : tramQh.getLongitude().toString());
            cs.setString(9, tramQh.getLatitude() == null ? "" : tramQh.getLatitude().toString());
            cs.setString(10, tramQh.getLoaiCongNgheId() == null ? "" : tramQh.getLoaiCongNgheId().toString());
            cs.setString(11, tramQh.getBangTanId() == null ? "" : tramQh.getBangTanId().toString());
            cs.setString(12, tramQh.getLoaiPtCshtId() == null ? "" : tramQh.getLoaiPtCshtId().toString());
            cs.setString(13, tramQh.getTrangThaiCshtId() == null ? "" : tramQh.getTrangThaiCshtId().toString());
            cs.setString(14, tramQh.getDonViPheDuyet() == null ? "" : tramQh.getDonViPheDuyet().toString());
            cs.setString(15, tramQh.getSoHieuVanBan());
            if (tramQh.getNgayPheDuyet() == null) {
                cs.setNull(16, java.sql.Types.TIMESTAMP);
            } else {
                cs.setTimestamp(16, new java.sql.Timestamp(tramQh.getNgayPheDuyet().getTime()));
            }
            if (tramQh.getNgayDieuChinhGanNhat() == null) {
                cs.setNull(17, java.sql.Types.TIMESTAMP);
            } else {
                cs.setTimestamp(17, new java.sql.Timestamp(tramQh.getNgayDieuChinhGanNhat().getTime()));
            }
            cs.setString(18, tramQh.getDonViDieuChinhId() == null ? "" : tramQh.getDonViDieuChinhId().toString());
            if (tramQh.getNgayPhatSong() == null) {
                cs.setNull(19, java.sql.Types.TIMESTAMP);
            } else {
                cs.setTimestamp(19, new java.sql.Timestamp(tramQh.getNgayPhatSong().getTime()));
            }
            cs.setString(20, tramQh.getBuildingId() == null ? "" : tramQh.getBuildingId().toString());
            cs.setString(21, StationStatus.status10);
            if (tramQh.getTinhTpId() == null || tramQh.getTinhTpId() == 0) {
                cs.setString(22, "");
            } else {
                cs.setString(22, tramQh.getTinhTpId().toString());
            }
            cs.setString(23, tramQh.getQuanHuyenId() == null ? "" : tramQh.getQuanHuyenId().toString());
            cs.setString(24, tramQh.getPhuongXaId() == null ? "" : tramQh.getPhuongXaId().toString());
            cs.setString(25, tramQh.getDIA_CHI());
            cs.setString(26, userId == null ? "" : userId.toString());
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            
            if (projectInforId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateCamKet(ProjectStationBO tramQh, Long userId) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := PKG_PROJECT_STATION.update_cam_ket(?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            if (tramQh.getTramQhId() == null) {
                return false;
            }
            cs.setInt(2, tramQh.getTramQhId());
            if (tramQh.getDVI_TRACH_NHIEM_CCTB_ID() == null) {
                cs.setNull(3, java.sql.Types.NUMERIC);
            } else {
                cs.setInt(3, tramQh.getDVI_TRACH_NHIEM_CCTB_ID());
            }
            if (tramQh.getNGUON_THIET_BI_ID() == null) {
                cs.setNull(4, java.sql.Types.NUMERIC);
            } else {
                cs.setInt(4, tramQh.getNGUON_THIET_BI_ID());
            }
            cs.setString(5, dateToString(tramQh.getTHOI_DIEM_DAP_UNG_DU_KIEN()));
            if (tramQh.getCONG_NGHE_DAP_UNG() == null) {
                cs.setNull(6, java.sql.Types.NUMERIC);
            } else {
                cs.setInt(6, tramQh.getCONG_NGHE_DAP_UNG());
            }
            cs.setString(7, tramQh.getCHUNG_LOAI_THIET_BI());
            cs.setString(8, dateToString(tramQh.getTHOI_DIEM_DAP_UNG_THUC_TE()));
            cs.setString(9, tramQh.getKHO_KHAN_VUONG_MAC());
            cs.setString(10, StationStatus.status20);
            cs.setString(11, userId == null ? "" : userId.toString());
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            if (projectInforId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

//    trunglk_start
    @Override
    public boolean updateTuNguon(ProjectStationBO tramQh, Long userId) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := PKG_PROJECT_STATION.update_tu_nguon(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            if (tramQh.getTramQhId() == null) {
                return false;
            }
            cs.setString(2, tramQh.getTramQhId().toString());
            cs.setString(3, tramQh.getDVI_TRACH_NHIEM_NGUON_DC_ID() == null ? "" : tramQh.getDVI_TRACH_NHIEM_NGUON_DC_ID().toString());
            cs.setString(4, tramQh.getNGUON_THIET_BI_TU_NGUON_ID() == null ? "" : tramQh.getNGUON_THIET_BI_TU_NGUON_ID().toString());
            cs.setString(5, tramQh.getLOAI_TU_NGUON_ID() == null ? "" : tramQh.getLOAI_TU_NGUON_ID().toString());
            cs.setString(6, tramQh.getDUNG_LUONG_TU_NGUON() == null ? "" : tramQh.getDUNG_LUONG_TU_NGUON().toString());
            cs.setString(7, tramQh.getSO_RACTIFIER() == null ? "" : tramQh.getSO_RACTIFIER().toString());
            cs.setString(8, tramQh.getDUNG_LUONG_ACCU() == null ? "" : tramQh.getDUNG_LUONG_ACCU().toString());
            cs.setString(9, tramQh.getSO_LUONG_ACCU() == null ? "" : tramQh.getSO_LUONG_ACCU().toString());
            cs.setString(10, tramQh.getDIEN_AP_ACCU() == null ? "" : tramQh.getDIEN_AP_ACCU().toString());
            cs.setString(11, dateToString(tramQh.getNGAY_DAP_UNG_NGUON_DC_DU_KIEN()));
            cs.setString(12, dateToString(tramQh.getNGAY_DAP_UNG_NGUON_DC_TT()));
            cs.setString(13, tramQh.getTinh_trang_nguon_dien()== null ? "" : tramQh.getTinh_trang_nguon_dien().toString());
            cs.setString(14, StationStatus.status40);
            cs.setString(15, userId == null ? "" : userId.toString());
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            if (projectInforId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }
//    trunglk_end

    @Override
    public boolean updateCSHT(ProjectStationBO tramQh, Long userId) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := PKG_PROJECT_STATION.update_csht(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            if (tramQh.getTramQhId() == null) {
                return false;
            }
            if (tramQh.getTramQhId() == null) {
                return false;
            }
            cs.setString(2, tramQh.getDVI_TRACH_NHIEM_CSHT_ID() == null ? "" : tramQh.getDVI_TRACH_NHIEM_CSHT_ID().toString());
            cs.setString(3, tramQh.getTinhTpId() == null ? "" : tramQh.getTinhTpId().toString());
            cs.setString(4, tramQh.getQuanHuyenId() == null ? "" : tramQh.getQuanHuyenId().toString());
            cs.setString(5, tramQh.getPhuongXaId() == null ? "" : tramQh.getPhuongXaId().toString());
            cs.setString(6, tramQh.getDIA_CHI());
            cs.setString(7, tramQh.getTEN_TRAM());
            cs.setString(8, tramQh.getCACH_XAY_CSHT_ID() == null ? "" : tramQh.getCACH_XAY_CSHT_ID().toString());
            cs.setString(9, tramQh.getLOAI_DAT_ID() == null ? "" : tramQh.getLOAI_DAT_ID().toString());
            cs.setString(10, dateToString(tramQh.getNGAY_CAP_DAT()));
            cs.setString(11, dateToString(tramQh.getNGAY_XIN_PHEP_XD_NHA_TRAM()));
            cs.setString(12, dateToString(tramQh.getNGAY_HOAN_THANH_THU_TUC()));
            cs.setString(13, dateToString(tramQh.getNGAY_KHOI_CONG_XD_NHA_TRAM()));
            cs.setString(14, dateToString(tramQh.getNGAY_HOAN_THANH_XD_NHA_TRAM()));
            cs.setString(15, tramQh.getLOAI_NHA_TRAM_ID() == null ? "" : tramQh.getLOAI_NHA_TRAM_ID().toString());
            cs.setString(16, dateToString(tramQh.getNGAY_XIN_PHEP_DO_CAO_COT()));
            cs.setString(17, dateToString(tramQh.getNGAY_CAP_PHEP_DO_CAO_COT()));
            cs.setString(18, dateToString(tramQh.getNGAY_HOAN_THANH_THU_TUC_XD_COT()));
            cs.setString(19, dateToString(tramQh.getNGAY_KHOI_CONG_XD_COT()));
            cs.setString(20, dateToString(tramQh.getNGAY_HOAN_THANH_XD_COT()));
            cs.setString(21, tramQh.getLOAI_COT_ANTEN_ID() == null ? "" : tramQh.getLOAI_COT_ANTEN_ID().toString());
            cs.setString(22, tramQh.getDO_CAO_COT() == null ? "" : tramQh.getDO_CAO_COT().toString());
            cs.setString(23, tramQh.getDO_CAO_VI_TRI_XAY_COT_ANTTEN() == null ? "" : tramQh.getDO_CAO_VI_TRI_XAY_COT_ANTTEN().toString());
            cs.setString(24, tramQh.getLOAI_TRUYEN_DAN_ID() == null ? "" : tramQh.getLOAI_TRUYEN_DAN_ID().toString());
            cs.setString(25, tramQh.getGIAODIEN_TD_E1() == null ? "" : tramQh.getGIAODIEN_TD_E1().toString());
            cs.setString(26, tramQh.getGIAODIEN_TD_FE_ID() == null ? "" : tramQh.getGIAODIEN_TD_FE_ID().toString());
            cs.setString(27, tramQh.getGIAODIEN_TD_GE_ID() == null ? "" : tramQh.getGIAODIEN_TD_GE_ID().toString());
            cs.setString(28, tramQh.getGIAODIEN_TD_STM1() == null ? "" : tramQh.getGIAODIEN_TD_STM1().toString());
            cs.setString(29, dateToString(tramQh.getNGAY_KHOI_CONG_TD()));
            cs.setString(30, dateToString(tramQh.getNGAY_HOAN_THANH_TD()));
            cs.setString(31, dateToString(tramQh.getNGAY_DAP_UNG_DIEN_AC()));
            cs.setString(32, tramQh.getHE_THONG_DIEN_NHA_TRAM_ID() == null ? "" : tramQh.getHE_THONG_DIEN_NHA_TRAM_ID().toString());
            cs.setString(33, tramQh.getHE_THONG_DIEU_HOA_ID() == null ? "" : tramQh.getHE_THONG_DIEU_HOA_ID().toString());
            cs.setString(34, tramQh.getHE_THONG_TIEP_DAT_ID() == null ? "" : tramQh.getHE_THONG_TIEP_DAT_ID().toString());
            cs.setString(35, tramQh.getHE_THONG_MAY_NO_ID() == null ? "" : tramQh.getHE_THONG_MAY_NO_ID().toString());
            cs.setString(36, dateToString(tramQh.getNGAY_HOAN_THANH_PHU_TRO()));
            cs.setString(37, tramQh.getDOI_TUONG_THONG_BAO());
            cs.setString(38, tramQh.getSO_HIEU_THONG_BAO());
            cs.setString(39, dateToString(tramQh.getNGAY_THONG_BAO_HT_CSHT()));
            cs.setString(40, tramQh.getKHO_KHAN_VUONG_MAC_CSHT());
            cs.setString(41, tramQh.getTramQhId().toString());
            cs.setString(42, StationStatus.status30);
            cs.setString(43, userId == null ? "" : userId.toString());
            //bo sung
            cs.setString(44, dateToString(tramQh.getNgay_duoc_pd_cap_von_csht()));
            cs.setString(45, tramQh.getTinh_trang_nha_tram() == null ? "" : tramQh.getTinh_trang_nha_tram().toString());
            cs.setString(46, tramQh.getTinh_trang_cot_anten()== null ? "" : tramQh.getTinh_trang_cot_anten().toString());
            cs.setString(47, tramQh.getTinh_trang_truyen_dan()== null ? "" : tramQh.getTinh_trang_truyen_dan().toString());
            cs.setString(48, tramQh.getCsht_danh_gia_netx());
            cs.setString(49, tramQh.getCsht_y_kien_netx());
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            if (projectInforId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    private String dateToString(Date date) {
        try {
            if (date != null) {
                SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
                return sp.format(date);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    @Override
    public boolean updateAntena(ProjectStationBO tramQh, Long userId) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := PKG_PROJECT_STATION.update_antena(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            if (tramQh.getTramQhId() == null) {
                return false;
            }
            if (tramQh.getTramQhId() == null) {
                return false;
            }
            cs.setString(2, tramQh.getTramQhId().toString());
            cs.setString(3, tramQh.getDVI_TRACH_NHIEM_ANTEN() == null ? "" : tramQh.getDVI_TRACH_NHIEM_ANTEN().toString());
            cs.setString(4, tramQh.getLOAI_ANTEN_ID1() == null ? "" : tramQh.getLOAI_ANTEN_ID1().toString());
            cs.setString(5, tramQh.getTEN_ANTENA1());
            cs.setString(6, tramQh.getHANG_SX_ANTENA1());
            cs.setString(7, tramQh.getSO_LUONG_ANTENA1() == null ? "" : tramQh.getSO_LUONG_ANTENA1().toString());
            cs.setString(8, tramQh.getBANG_TANG_ANTENA_ID1() == null ? "" : tramQh.getBANG_TANG_ANTENA_ID1().toString());
            cs.setString(9, tramQh.getCAU_HINH_PORT_ID1() == null ? "" : tramQh.getCAU_HINH_PORT_ID1().toString());
            cs.setString(10, tramQh.getCAU_HINH_GAIN1());
            cs.setString(11, tramQh.getCAU_HINH_BEAM_WIDTH1());
            cs.setString(12, tramQh.getTRONG_LUONG1() == null ? "" : tramQh.getTRONG_LUONG1().toString());
            cs.setString(13, tramQh.getKICH_CO_CAO1() == null ? "" : tramQh.getKICH_CO_CAO1().toString());
            cs.setString(14, tramQh.getKICH_CO_RONG1() == null ? "" : tramQh.getKICH_CO_RONG1().toString());
            cs.setString(15, tramQh.getKICH_CO_SAU1() == null ? "" : tramQh.getKICH_CO_SAU1().toString());
            cs.setString(16, tramQh.getDO_CAO_ANTENA_SO_VOI_MAT_DAT1() == null ? "" : tramQh.getDO_CAO_ANTENA_SO_VOI_MAT_DAT1().toString());
            //
            cs.setString(17, tramQh.getLOAI_ANTEN_ID2() == null ? "" : tramQh.getLOAI_ANTEN_ID2().toString());
            cs.setString(18, tramQh.getTEN_ANTENA2());
            cs.setString(19, tramQh.getHANG_SX_ANTENA2());
            cs.setString(20, tramQh.getSO_LUONG_ANTENA2() == null ? "" : tramQh.getSO_LUONG_ANTENA2().toString());
            cs.setString(21, tramQh.getBANG_TANG_ANTENA_ID2() == null ? "" : tramQh.getBANG_TANG_ANTENA_ID2().toString());
            cs.setString(22, tramQh.getCAU_HINH_PORT_ID2() == null ? "" : tramQh.getCAU_HINH_PORT_ID2().toString());
            cs.setString(23, tramQh.getCAU_HINH_GAIN2());
            cs.setString(24, tramQh.getCAU_HINH_BEAM_WIDTH2());
            cs.setString(25, tramQh.getTRONG_LUONG2() == null ? "" : tramQh.getTRONG_LUONG2().toString());
            cs.setString(26, tramQh.getKICH_CO_CAO2() == null ? "" : tramQh.getKICH_CO_CAO2().toString());
            cs.setString(27, tramQh.getKICH_CO_RONG2() == null ? "" : tramQh.getKICH_CO_RONG2().toString());
            cs.setString(28, tramQh.getKICH_CO_SAU2() == null ? "" : tramQh.getKICH_CO_SAU2().toString());
            cs.setString(29, tramQh.getDO_CAO_ANTENA_SO_VOI_MAT_DAT2() == null ? "" : tramQh.getDO_CAO_ANTENA_SO_VOI_MAT_DAT2().toString());
            //
            cs.setString(30, tramQh.getLOAI_ANTEN_ID3() == null ? "" : tramQh.getLOAI_ANTEN_ID3().toString());
            cs.setString(31, tramQh.getTEN_ANTENA3());
            cs.setString(32, tramQh.getHANG_SX_ANTENA3());
            cs.setString(33, tramQh.getSO_LUONG_ANTENA3() == null ? "" : tramQh.getSO_LUONG_ANTENA3().toString());
            cs.setString(34, tramQh.getBANG_TANG_ANTENA_ID3() == null ? "" : tramQh.getBANG_TANG_ANTENA_ID3().toString());
            cs.setString(35, tramQh.getCAU_HINH_PORT_ID3() == null ? "" : tramQh.getCAU_HINH_PORT_ID3().toString());
            cs.setString(36, tramQh.getCAU_HINH_GAIN3());
            cs.setString(37, tramQh.getCAU_HINH_BEAM_WIDTH3());
            cs.setString(38, tramQh.getTRONG_LUONG3() == null ? "" : tramQh.getTRONG_LUONG3().toString());
            cs.setString(39, tramQh.getKICH_CO_CAO3() == null ? "" : tramQh.getKICH_CO_CAO3().toString());
            cs.setString(40, tramQh.getKICH_CO_RONG3() == null ? "" : tramQh.getKICH_CO_RONG3().toString());
            cs.setString(41, tramQh.getKICH_CO_SAU3() == null ? "" : tramQh.getKICH_CO_SAU3().toString());
            cs.setString(42, tramQh.getDO_CAO_ANTENA_SO_VOI_MAT_DAT3() == null ? "" : tramQh.getDO_CAO_ANTENA_SO_VOI_MAT_DAT3().toString());

            cs.setString(43, dateToString(tramQh.getNGAY_DAP_UNG_ANTENA_DU_KIEN()));
            cs.setString(44, dateToString(tramQh.getNGAY_DAP_UNG_ANTENA_THUC_TE()));
            cs.setString(45, StationStatus.status50);
            cs.setString(46, userId == null ? "" : userId.toString());
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            if (projectInforId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateStatus(ProjectStationBO tramQh, Long userId) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := PKG_PROJECT_STATION.update_status(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            if (tramQh.getTramQhId() == null) {
                return false;
            }
            cs.setString(2, tramQh.getTramQhId().toString());
            cs.setString(3, tramQh.getTRANG_THAI_TRAM());
            cs.setString(4, tramQh.getNOTE());
            cs.setString(5, userId == null ? "" : userId.toString());
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            if (projectInforId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<ProjectStationBO> fn_search(String prs_start_record, String prs_length_page, String prs_global_search,
            String prs_list_column_name, String prs_list_column_search, String prs_column_to_sort, String prs_sort_direction,
            String[] recordsTotal, String[] recordsFiltered, String tinhTpIds, String status) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<ProjectStationBO> ar = new ArrayList<ProjectStationBO>();
        try {
            conn = this.getConnection();
            String sql = "begin ?:=PKG_PROJECT_STATION.fn_search(?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            cs.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
            cs.setString(2, prs_start_record);
            cs.setString(3, prs_length_page);
            cs.setString(4, prs_global_search);
            cs.setString(5, prs_list_column_name);
            cs.setString(6, prs_list_column_search);
            cs.setString(7, prs_column_to_sort);
            cs.setString(8, prs_sort_direction);
            cs.setString(9, tinhTpIds);
            cs.setString(12, status);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            recordsTotal[0] = cs.getString(10);
            recordsFiltered[0] = cs.getString(11);
            if (status == null || status.isEmpty()) {
                while (rs.next()) {
                    ProjectStationBO record = new ProjectStationBO();
                    record.setTramQhId(rs.getInt("TRAM_QH_ID"));
                    record.setMaQh(rs.getString("MA_QUY_HOACH"));
                    record.setTenQh(rs.getString("TEN_QUY_HOACH"));
                    record.setNamKhoiTao(rs.getDate("NAM_KHOI_TAO"));
                    record.setLongitude(rs.getFloat("LONGITUDE"));
                    record.setLatitude(rs.getFloat("LATITUDE"));
                    record.setLoaiCongNghe(rs.getString("ten_loai_cong_nghe"));
                    record.setBangTan(rs.getString("ten_bang_tan"));
                    record.setPtCsht(rs.getString("ten_loai_pt_csht"));
                    record.setTRANG_THAI_TRAM(rs.getString("TRANG_THAI_TRAM"));
                    record.setTinhTpId(rs.getInt("TINHTP_ID"));
                    ar.add(record);
                }
            } else if (status.equalsIgnoreCase("10")) {
                while (rs.next()) {
                    ProjectStationBO record = new ProjectStationBO();
                    record.setTramQhId(rs.getInt("TRAM_QH_ID"));
                    record.setMaQh(rs.getString("MA_QUY_HOACH"));
                    record.setTenQh(rs.getString("TEN_QUY_HOACH"));
                    record.setDVI_TRACH_NHIEM_CCTB(rs.getString("DVI_TRACH_NHIEM_CCTB"));
                    record.setTEN_CONG_NGHE_DAP_UNG(rs.getString("TEN_CONG_NGHE_DAP_UNG"));
                    record.setNGUON_THIET_BI(rs.getString("ten_nguon_thiet_bi"));
                    record.setTHOI_DIEM_DAP_UNG_DU_KIEN(rs.getDate("THOI_DIEM_DAP_UNG_DU_KIEN"));
                    record.setCHUNG_LOAI_THIET_BI(rs.getString("CHUNG_LOAI_THIET_BI"));
                    record.setTHOI_DIEM_DAP_UNG_THUC_TE(rs.getDate("THOI_DIEM_DAP_UNG_THUC_TE"));
                    record.setKHO_KHAN_VUONG_MAC(rs.getString("KHO_KHAN_VUONG_MAC"));
                    ar.add(record);
                }
            } else if (status.equalsIgnoreCase("20")) {
                while (rs.next()) {
                    ProjectStationBO record = new ProjectStationBO();
                    record.setTramQhId(rs.getInt("TRAM_QH_ID"));
                    record.setMaQh(rs.getString("MA_QUY_HOACH"));
                    record.setTenQh(rs.getString("TEN_QUY_HOACH"));
                    record.setTinhTp(rs.getString("TEN_TINH_TP"));
                    record.setQuanHuyen(rs.getString("ten_quan_huyen"));
                    record.setDIA_CHI(rs.getString("DIA_CHI"));
                    record.setTEN_TRAM(rs.getString("TEN_TRAM"));
                    record.setCACH_XAY_CSHT(rs.getString("ten_cach_xay_csht"));
                    ar.add(record);
                }
            } else if (status.equalsIgnoreCase("30")) {
                while (rs.next()) {
                    ProjectStationBO record = new ProjectStationBO();
                    record.setTramQhId(rs.getInt("TRAM_QH_ID"));
                    record.setMaQh(rs.getString("MA_QUY_HOACH"));
                    record.setTenQh(rs.getString("TEN_QUY_HOACH"));
                    record.setTU_NGUON_DC(rs.getString("TEN_NGUON_THIET_BI"));
                    record.setTEN_LOAI_TU_NGUON(rs.getString("TEN_LOAI_TU_NGUON"));
                    record.setDUNG_LUONG_TU_NGUON(rs.getInt("DUNG_LUONG_TU_NGUON"));
                    record.setSO_RACTIFIER(rs.getInt("SO_RACTIFIER"));
                    record.setDUNG_LUONG_ACCU(rs.getInt("DUNG_LUONG_ACCU"));
                    record.setSO_LUONG_ACCU(rs.getInt("SO_LUONG_ACCU"));
                    record.setDIEN_AP_ACCU(rs.getInt("DIEN_AP_ACCU"));
                    record.setNGAY_DAP_UNG_NGUON_DC_DU_KIEN(rs.getDate("NGAY_DAP_UNG_NGUON_DC_DU_KIEN"));
                    record.setNGAY_DAP_UNG_NGUON_DC_TT(rs.getDate("NGAY_DAP_UNG_NGUON_DC_TT"));
                    ar.add(record);
                }
            } else if (status.equalsIgnoreCase("40")) {
                while (rs.next()) {
                    ProjectStationBO record = new ProjectStationBO();
                    record.setTramQhId(rs.getInt("TRAM_QH_ID"));
                    record.setMaQh(rs.getString("MA_QUY_HOACH"));
                    record.setTenQh(rs.getString("TEN_QUY_HOACH"));
                    record.setTEN_ANTENA1(rs.getString("TEN_ANTENA1"));
                    record.setHANG_SX_ANTENA1(rs.getString("HANG_SX_ANTENA1"));
                    record.setSO_LUONG_ANTENA1(rs.getInt("SO_LUONG_ANTENA1"));
                    record.setTEN_ANTENA2(rs.getString("TEN_ANTENA2"));
                    record.setHANG_SX_ANTENA2(rs.getString("HANG_SX_ANTENA2"));
                    record.setSO_LUONG_ANTENA2(rs.getInt("SO_LUONG_ANTENA2"));
                    record.setTEN_ANTENA3(rs.getString("TEN_ANTENA3"));
                    record.setHANG_SX_ANTENA3(rs.getString("HANG_SX_ANTENA3"));
                    record.setSO_LUONG_ANTENA3(rs.getInt("SO_LUONG_ANTENA3"));
                    ar.add(record);
                }
            }
            return ar;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    @Override
    public boolean deleteTramQh(String tram_qh_id) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := PKG_PROJECT_STATION.delete_tram_qh(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, tram_qh_id == null ? "" : tram_qh_id);
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            if (projectInforId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

}
