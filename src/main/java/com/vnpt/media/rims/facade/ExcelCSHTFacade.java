package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.BtsReportBO;
import com.vnpt.media.rims.bean.BuildingBO;
import com.vnpt.media.rims.bean.BuildingExportBO;
import com.vnpt.media.rims.bean.DMTrangThaiCshtBO;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.controller.csht.BuildingController;
import com.vnpt.media.rims.formbean.RegCSHTExcel;
import com.vnpt.media.rims.formbean.UpdateCSHTExcel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExcelCSHTFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");
    private static final Logger LOGGER = LogManager.getLogger(ExcelCSHTFacade.class);

    public static ArrayList<BuildingExportBO> reportCSHT(String name, String khuvucId, String tinhId, String quanId, String phuongId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<BuildingExportBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excelcsht.report(?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, name == null ? "" : name);
            cstmt.setString(3, khuvucId == null ? "" : khuvucId);
            cstmt.setString(4, tinhId == null ? "" : tinhId);
            cstmt.setString(5, quanId == null ? "" : quanId);
            cstmt.setString(6, phuongId == null ? "" : phuongId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                BuildingExportBO item = new BuildingExportBO();
                item.setId(rs.getString("building_id") == null ? "" : rs.getString("building_id"));
                item.setTinh(rs.getString("ten_tinh_tp") == null ? "" : rs.getString("ten_tinh_tp"));
                item.setQuan(rs.getString("TEN_QUAN_HUYEN") == null ? "" : rs.getString("TEN_QUAN_HUYEN"));
                item.setXa(rs.getString("TEN_PHUONG_XA") == null ? "" : rs.getString("TEN_PHUONG_XA"));
                item.setTenDonViQL(rs.getString("ten_don_vi"));
                item.setDiachi(rs.getString("dia_chi") == null ? "" : rs.getString("dia_chi"));
                item.setLatitude(rs.getString("LATITUDE") == null ? "" : rs.getString("LATITUDE"));
                item.setLongitude(rs.getString("LONGITUDE") == null ? "" : rs.getString("LONGITUDE"));
                item.setBuildingName(rs.getString("building_name") == null ? "" : rs.getString("building_name"));
                item.setLoaiCSHT(rs.getString("ten_loai_nha_tram") == null ? "" : rs.getString("ten_loai_nha_tram")); // cho huu de co ten truong moi
                item.setChungCSHT(rs.getString("CHUNG_CSHT") == null ? "" : rs.getString("CHUNG_CSHT"));
                item.setLoaiTramCSHT(rs.getString("LOAI_TRAM_CSHT") == null ? "" : rs.getString("LOAI_TRAM_CSHT"));
                item.setDocaoAnTen(rs.getString("DO_CAO_ANTEN") == null ? "" : rs.getString("DO_CAO_ANTEN"));
                item.setDoCaoNhaDatAnten(rs.getString("DO_CAO_NHA_DAT_ANTEN") == null ? "" : rs.getString("DO_CAO_NHA_DAT_ANTEN"));
                item.setLoaiAnTenId(rs.getString("LOAI_ANTEN_ID") == null ? "" : rs.getString("LOAI_ANTEN_ID"));
                item.setNgayHDTuNguon(rs.getString("NGAY_HD_TU_NGUON") == null ? "" : rs.getString("NGAY_HD_TU_NGUON"));
                item.setLoaiTuNguonId(rs.getString("LOAI_TU_NGUON_ID") == null ? "" : rs.getString("LOAI_TU_NGUON_ID"));
                item.setSoModuleTuNguon(rs.getString("SO_MODULE_TU_NGUON") == null ? "" : rs.getString("SO_MODULE_TU_NGUON"));
                item.setNgayHDMayNo(rs.getString("NGAY_HD_MAY_NO") == null ? "" : rs.getString("NGAY_HD_MAY_NO"));
                item.setLoaiMayNoId(rs.getString("LOAI_MAY_NO_ID") == null ? "" : rs.getString("LOAI_MAY_NO_ID"));
                item.setCongSuatMayNo(rs.getString("CONG_SUAT_MAY_NO") == null ? "" : rs.getString("CONG_SUAT_MAY_NO"));
                item.setMayNoCoDinhDiDong(rs.getString("trang_thai_dat_may_no") == null ? "" : rs.getString("trang_thai_dat_may_no"));
                item.setNgayHDAccu(rs.getString("NGAY_HD_ACCU") == null ? "" : rs.getString("NGAY_HD_ACCU"));
                item.setLoaiAcQuyId(rs.getString("LOAI_AC_QUY_ID") == null ? "" : rs.getString("LOAI_AC_QUY_ID"));
                item.setDungLuongAcc(rs.getString("dung_luong_accu") == null ? "" : rs.getString("dung_luong_accu"));
                item.setThoiGianHdSauMatDien(rs.getString("THOI_GIAN_HD_SAU_MAT_DIEN") == null ? "" : rs.getString("THOI_GIAN_HD_SAU_MAT_DIEN"));
                item.setNgayBaoDuongAccu(rs.getString("NGAY_BAO_DUONG_ACCU") == null ? "" : rs.getString("NGAY_BAO_DUONG_ACCU"));
                item.setLoaiTruyenDanId(rs.getString("LOAI_TRUYEN_DAN_ID") == null ? "" : rs.getString("LOAI_TRUYEN_DAN_ID"));
                item.setDuongLuongTruyenDan(rs.getString("DUNG_LUONG_TRUYEN_DAN") == null ? "" : rs.getString("DUNG_LUONG_TRUYEN_DAN"));
                item.setDienTroTiepDia(rs.getString("DIEN_TRO_TIEP_DIA") == null ? "" : rs.getString("DIEN_TRO_TIEP_DIA"));
                item.setLoaiCotAnten(rs.getString("loai_cot_anten") == null ? "" : rs.getString("loai_cot_anten"));
                item.setMaBuilding(rs.getString("ma_building") == null ? "" : rs.getString("ma_building"));
                item.setSoLuongDieuHoa(rs.getString("sl_dieu_hoa") == null ? "" : rs.getString("sl_dieu_hoa"));
                item.setSoLuongAccuBinh(rs.getString("sl_accu_binh") == null ? "" : rs.getString("sl_accu_binh"));
                item.setDienApBinh(rs.getString("dien_ap_accu") == null ? "" : rs.getString("dien_ap_accu"));
                item.setDongTieuThuTuNguon(rs.getString("tieu_thu_tu_nguon") == null ? "" : rs.getString("tieu_thu_tu_nguon"));
                item.setDongCungCapTuNguon(rs.getString("dong_cung_cap_tu_nguon") == null ? "" : rs.getString("dong_cung_cap_tu_nguon"));
                item.setGiaoDienTruyenDan(rs.getString("giao_dien_truyen_dan") == null ? "" : rs.getString("giao_dien_truyen_dan"));
                item.setTongCongSuatDieuHoa(rs.getString("tong_cs_dieuhoa") == null ? "" : rs.getString("tong_cs_dieuhoa"));
                item.setLoaiHinhMayNo(rs.getString("LOAI_HINH_MAY_NO") == null ? "" : rs.getString("LOAI_HINH_MAY_NO"));

                item.setNgayHDTuNguon2(rs.getString("NGAY_HD_TU_NGUON2") == null ? "" : rs.getString("NGAY_HD_TU_NGUON2"));
                item.setLoaiTuNguonId2(rs.getString("LOAI_TU_NGUON_ID2") == null ? "" : rs.getString("LOAI_TU_NGUON_ID2"));
                item.setDongCungCapTuNguon2(rs.getString("dong_cung_cap_tu_nguon2") == null ? "" : rs.getString("dong_cung_cap_tu_nguon2"));
                item.setSoModuleTuNguon2(rs.getString("SO_MODULE_TU_NGUON2") == null ? "" : rs.getString("SO_MODULE_TU_NGUON2"));
                item.setDongTieuThuTuNguon2(rs.getString("tieu_thu_tu_nguon2") == null ? "" : rs.getString("tieu_thu_tu_nguon2"));

                item.setNgayHDAccu2(rs.getString("NGAY_HD_ACCU2") == null ? "" : rs.getString("NGAY_HD_ACCU2"));
                item.setLoaiAcQuyId2(rs.getString("LOAI_AC_QUY_ID2") == null ? "" : rs.getString("LOAI_AC_QUY_ID2"));
                item.setDungLuongAcc2(rs.getString("dung_luong_accu2") == null ? "" : rs.getString("dung_luong_accu2"));
                item.setDienApBinh2(rs.getString("dien_ap_accu2") == null ? "" : rs.getString("dien_ap_accu2"));
                item.setSoLuongAccuBinh2(rs.getString("sl_accu_binh2") == null ? "" : rs.getString("sl_accu_binh2"));
                item.setThoiGianHdSauMatDien2(rs.getString("THOI_GIAN_HD_SAU_MAT_DIEN2") == null ? "" : rs.getString("THOI_GIAN_HD_SAU_MAT_DIEN2"));
                item.setNgayBaoDuongAccu2(rs.getString("NGAY_BAO_DUONG_ACCU2") == null ? "" : rs.getString("NGAY_BAO_DUONG_ACCU2"));
                item.setNgayHdCsht(rs.getString("infras_active_date") == null ? "" : rs.getString("infras_active_date"));
                item.setNhomCSHT(rs.getString("area_classify_group") == null ? "" : rs.getString("area_classify_group"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public static int fn_modify(String prn_action, String prs_id, String prs_ten_trang_thai_csht) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_dm_trang_thai_csht.fn_modify(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_action);
            cstmt.setString(3, prs_id);
            cstmt.setString(4, prs_ten_trang_thai_csht);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return -1;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public static String excelAddCsht(RegCSHTExcel qh, String userId) {
        LOGGER.info("excelAddCsht ({})",qh.listParam());
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_building.fn_add_csht_excel(?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cs.setString(2, qh.getName());
            cs.setString(3, qh.getNgayHdCsht());
            cs.setString(4, qh.getDonVi());
            cs.setString(5, qh.getTinh());
            cs.setString(6, qh.getQuanHuyen());
            cs.setString(7, qh.getXaPhuong());
            cs.setString(8, qh.getDiaChi());
            cs.setString(9, qh.getLat());
            cs.setString(10, qh.getLon());
            cs.setString(11, qh.getChungCsht());
            cs.setString(12, qh.getLoaiCSHT());
            cs.setString(13, qh.getLoaiTramCsht());
            cs.setString(14, qh.getDoCaoAnTen());
            cs.setString(15, qh.getDoCaoNhaDatAnTen());
            cs.setString(16, qh.getLoaiCotAnTen());
            cs.setString(17, qh.getNgayHDTuNguon());
            cs.setString(18, qh.getLoaiTuNguon());
            cs.setString(19, qh.getDongCungCapTuNguon());
            cs.setString(20, qh.getSoModuleTuNguon());
            cs.setString(21, qh.getDongTieuThuTuNguon());
            cs.setString(22, qh.getNgayHDTuNguon2());
            cs.setString(23, qh.getLoaiTuNguon2());
            cs.setString(24, qh.getDongCungCapTuNguon2());
            cs.setString(25, qh.getSoModuleTuNguon2());
            cs.setString(26, qh.getDongTieuThuTuNguon2());
            cs.setString(27, qh.getNgayHDMayNo());
            cs.setString(28, qh.getLoaiHinhMayNo());
            cs.setString(29, qh.getLoatMayNo());
            cs.setString(30, qh.getCongSuatMayNo());
            cs.setString(31, qh.getTrangThaiMayNo());
            cs.setString(32, qh.getNgayHDAccu());
            cs.setString(33, qh.getLoaiAcQuy());
            cs.setString(34, qh.getDungLuongAccu());
            cs.setString(35, qh.getDienApAccu());
            cs.setString(36, qh.getSlAccuBinh());
            cs.setString(37, qh.getThoigianHDSauMatDien());
            cs.setString(38, qh.getNgayBaoDuongAccu());
            cs.setString(39, qh.getNgayHDAccu2());
            cs.setString(40, qh.getLoaiAcQuy2());
            cs.setString(41, qh.getDungLuongAccu2());
            cs.setString(42, qh.getDienApAccu2());
            cs.setString(43, qh.getSlAccuBinh2());
            cs.setString(44, qh.getThoigianHDSauMatDien2());
            cs.setString(45, qh.getNgayBaoDuongAccu2());
            cs.setString(46, qh.getLoaiTruyenDan());
            cs.setString(47, qh.getGiaoDienTruyenDan());
            cs.setString(48, qh.getDungLuongTruyenDan());
            cs.setString(49, qh.getDienTroTiepDia());
            cs.setString(50, qh.getSlDieuHoa());
            cs.setString(51, qh.getTongCSDieuHoa());
            cs.setString(52, userId);
            cs.executeQuery();
            return cs.getString(1);
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
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

    public static String excelUpdateCsht(UpdateCSHTExcel qh, String userId) {
        LOGGER.info("UpdateCSHTExcel ({})",qh.listParam());
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_building.fn_update_csht_excel(?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?"
                    + "); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cs.setString(2, qh.getCode());
            cs.setString(3, qh.getName());
            cs.setString(4, qh.getNgayHdCsht());
             cs.setString(5, qh.getDonViQl());
            cs.setString(6, qh.getQuanHuyen());
            cs.setString(7, qh.getXaPhuong());
            cs.setString(8, qh.getDiaChi());
            cs.setString(9, qh.getLat());
            cs.setString(10, qh.getLon());
            cs.setString(11, qh.getChungCsht());
            cs.setString(12, qh.getLoaiCSHT());
            cs.setString(13, qh.getLoaiTramCsht());
            cs.setString(14, qh.getDoCaoAnTen());
            cs.setString(15, qh.getDoCaoNhaDatAnTen());
            cs.setString(16, qh.getLoaiCotAnTen());
            cs.setString(17, qh.getNgayHDTuNguon());
            cs.setString(18, qh.getLoaiTuNguon());
            cs.setString(19, qh.getDongCungCapTuNguon());
            cs.setString(20, qh.getSoModuleTuNguon());
            cs.setString(21, qh.getDongTieuThuTuNguon());
            cs.setString(22, qh.getNgayHDTuNguon2());
            cs.setString(23, qh.getLoaiTuNguon2());
            cs.setString(24, qh.getDongCungCapTuNguon2());
            cs.setString(25, qh.getSoModuleTuNguon2());
            cs.setString(26, qh.getDongTieuThuTuNguon2());
            cs.setString(27, qh.getNgayHDMayNo());
            cs.setString(28, qh.getLoaiHinhMayNo());
            cs.setString(29, qh.getLoatMayNo());
            cs.setString(30, qh.getCongSuatMayNo());
            cs.setString(31, qh.getTrangThaiMayNo());
            cs.setString(32, qh.getNgayHDAccu());
            cs.setString(33, qh.getLoaiAcQuy());
            cs.setString(34, qh.getDungLuongAccu());
            cs.setString(35, qh.getDienApAccu());
            cs.setString(36, qh.getSlAccuBinh());
            cs.setString(37, qh.getThoigianHDSauMatDien());
            cs.setString(38, qh.getNgayBaoDuongAccu());
            cs.setString(39, qh.getNgayHDAccu2());
            cs.setString(40, qh.getLoaiAcQuy2());
            cs.setString(41, qh.getDungLuongAccu2());
            cs.setString(42, qh.getDienApAccu2());
            cs.setString(43, qh.getSlAccuBinh2());
            cs.setString(44, qh.getThoigianHDSauMatDien2());
            cs.setString(45, qh.getNgayBaoDuongAccu2());
            cs.setString(46, qh.getLoaiTruyenDan());
            cs.setString(47, qh.getGiaoDienTruyenDan());
            cs.setString(48, qh.getDungLuongTruyenDan());
            cs.setString(49, qh.getDienTroTiepDia());
            cs.setString(50, qh.getSlDieuHoa());
            cs.setString(51, qh.getTongCSDieuHoa());
            cs.setString(52, userId);
            cs.executeQuery();
            return cs.getString(1);
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
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

    public static ArrayList<BuildingBO> getCSHTDuyet(String tinhId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<BuildingBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_building.find_duyet_building(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhId == null ? "" : tinhId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                BuildingBO item = new BuildingBO();
                item.setId(rs.getLong("building_id"));
                item.setCode(rs.getString("ma_building") == null ? "" : rs.getString("ma_building"));
                item.setAddress(rs.getString("dia_chi") == null ? "" : rs.getString("dia_chi"));
                item.setLat(rs.getString("LATITUDE") == null ? "" : rs.getString("LATITUDE"));
                item.setLon(rs.getString("LONGITUDE") == null ? "" : rs.getString("LONGITUDE"));
                item.setTinhTpId(rs.getLong("TINHTP_ID"));
                item.setQuanHuyenId(rs.getLong("QUANHUYEN_ID"));
                item.setPhuongXaId(rs.getLong("PHUONGXA_ID"));
                item.setDonViId(rs.getLong("DONVI_ID"));
                item.setTinhName(rs.getString("ten_tinh_tp") == null ? "" : rs.getString("ten_tinh_tp"));
                item.setQuanName(rs.getString("ten_quan_huyen") == null ? "" : rs.getString("ten_quan_huyen"));
                item.setPhuongName(rs.getString("ten_phuong_xa") == null ? "" : rs.getString("ten_phuong_xa"));
                item.setDonViName(rs.getString("ten_don_vi") == null ? "" : rs.getString("ten_don_vi"));
                item.setName(rs.getString("building_name") == null ? "" : rs.getString("building_name"));
                item.setNhomCSHT(rs.getString("AREA_CLASSIFY_GROUP") == null ? "" : rs.getString("AREA_CLASSIFY_GROUP"));
                item.setNgayHdCsht(rs.getString("infras_active_date") == null ? "" : rs.getString("infras_active_date"));

                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

}
