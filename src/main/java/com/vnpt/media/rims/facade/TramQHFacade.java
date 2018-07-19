/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ProjectStationBO;
import com.vnpt.media.rims.bean.TramQuyHoachUpdateCshtNguonDc;
import com.vnpt.media.rims.bean.TramQuyHoachUpdateNetx;
import com.vnpt.media.rims.bean.TramQuyHoachUpdatePtm;
import com.vnpt.media.rims.bean.TramQuyHoachUpdateQlda;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Cyano
 */
public class TramQHFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static String excelUpdateCshtNguonDc(TramQuyHoachUpdateCshtNguonDc qh, String userId, String donviId, String tinhTpIds) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excel_quy_hoach.excelupdatecshtnguondc(?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cs.setString(2, qh.getMaTramQH());
            cs.setString(3, qh.getCshtDonViChiuTrachNhiem());
            cs.setString(4, qh.getQuanHuyen());
            cs.setString(5, qh.getPhuongXa());
            cs.setString(6, qh.getDiaChi());
            cs.setString(7, qh.getCshtTenTram());
            cs.setString(8, qh.getCshtNgayPheDuyetCapVon());
            cs.setString(9, qh.getCshtCachThucXD());
            cs.setString(10, qh.getCshtLoaiDat());
            cs.setString(11, qh.getCshtNgayCapThueDat());
            cs.setString(12, qh.getCshtNgayXinPhepXDNhaTram());
            cs.setString(13, qh.getCshtNgayHoanThanhThuTucXay());
            cs.setString(14, qh.getCshtNgayKhoiCongXD());
            cs.setString(15, qh.getCshtNgayHoanThanhXay());
            cs.setString(16, qh.getCshtLoaiNhaTram());
            cs.setString(17, qh.getCshtTinhTrangNhaTram());
            cs.setString(18, qh.getCshtNgayXinPhepDoCaoCot());
            cs.setString(19, qh.getCshtNgayCapPhepDoCaoCot());
            cs.setString(20, qh.getCshtNgayHoanThanhThuTucXDCot());
            cs.setString(21, qh.getCshtNgayKhoiCongDungCot());
            cs.setString(22, qh.getNgayHoanThanhCot());
            cs.setString(23, qh.getLoaiCot());
            cs.setString(24, qh.getDoCaoCot());
            cs.setString(25, qh.getDoCaoChanCot());
            cs.setString(26, qh.getCshtTinhTrangCotAnten());
            cs.setString(27, qh.getPhuongThucTruyenDan());
            cs.setString(28, qh.getE1());
            cs.setString(29, qh.getFe());
            cs.setString(30, qh.getGe());
            cs.setString(31, qh.getStm());
            cs.setString(32, qh.getNgayKhoiCongTruyenDan());
            cs.setString(33, qh.getNgayHoanThanhTruyenDan());
            cs.setString(34, qh.getCshtTinhTrangTruyenDan());
            cs.setString(35, qh.getNgayDienApAC());
            cs.setString(36, qh.getHeThongDienTrongNhaTram());
            cs.setString(37, qh.getHeThongDieuHoa());
            cs.setString(38, qh.getHeThongTiepDat());
            cs.setString(39, qh.getMayNo());
            cs.setString(40, qh.getNgayHoanThanhPHuTro());
            cs.setString(41, qh.getDoiTuongThongBao());
            cs.setString(42, qh.getSoHieuThongBao());
            cs.setString(43, qh.getNgayThongBaoHoanThanhCSHT());
            cs.setString(44, qh.getKhoKhanVuongMac());
            //
            cs.setString(45, qh.getNguonDonViChiuTrachNhiem());
            cs.setString(46, qh.getTuNguon());
            cs.setString(47, qh.getLoaiTuNguon());
            cs.setString(48, qh.getDungLuongTuNguon());
            cs.setString(49, qh.getSoLuongRacktifier());
            cs.setString(50, qh.getDungLuongAcquy());
            cs.setString(51, qh.getSoLuongToAcquy());
            cs.setString(52, qh.getDienApAcquy());
            cs.setString(53, qh.getNgayDapUngDcDuKien());
            cs.setString(54, qh.getNgayDapUngDcThucTe());
            cs.setString(55, qh.getTinhTrangNguonDien());
            cs.setString(56, userId);
            cs.setString(57, tinhTpIds);
            cs.executeQuery();

            return cs.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
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

    public static String updateQhPtm(TramQuyHoachUpdatePtm qh, String userId, String tinhTpIds) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excel_quy_hoach.excel_update_ptm(?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cs.setString(2, qh.getMaTramQH());
            cs.setString(3, qh.getTenTramQH());
            cs.setString(4, qh.getMaBuilding());
            cs.setString(5, qh.getTinhTp());
            cs.setString(6, qh.getNamKhoiTao());
            cs.setString(7, qh.getLongitude());
            cs.setString(8, qh.getLatitude());
            cs.setString(9, qh.getLoaiCongNghe());
            cs.setString(10, qh.getBangTan());
            cs.setString(11, qh.getChuongTrinhPtCSHT());
            cs.setString(12, qh.getTrangThaiCSHT());
            cs.setString(13, qh.getDvPheDuyet());
            cs.setString(14, qh.getSoHieuVanBan());
            cs.setString(15, qh.getNgayPheDuyet());
            cs.setString(16, qh.getNgayDieuChinhGanNhat());
            cs.setString(17, qh.getDvDieuChinh());
            cs.setString(18, qh.getNgayPhatSong());
            //
            cs.setString(19, qh.getDvChiuTrachNhiemCktb());
            cs.setString(20, qh.getNguonThietBi());
            cs.setString(21, qh.getThoiDiemDapUngDuKien());
            cs.setString(22, qh.getCongNgheDapUng());
            cs.setString(23, qh.getChungLoaiThietBi());
            cs.setString(24, qh.getThoiGianDapUngThucTe());
            cs.setString(25, qh.getKhoKhanVuongMac());

            cs.setString(26, tinhTpIds);
            cs.setString(27, userId);
            cs.executeQuery();

            return cs.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
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

    public static String updateQhQlda(TramQuyHoachUpdateQlda qh, String userId, String tinhTpIds) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excel_quy_hoach.excel_update_qlda(?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cs.setString(2, qh.getMaTramQH());
            cs.setString(3, qh.getTenTramQH());
            cs.setString(4, qh.getDvChiuTrachNhiemNguon());
            cs.setString(5, qh.getTuNguon());
            cs.setString(6, qh.getLoaiTuNguon());
            cs.setString(7, qh.getDungLuongTuNguon());
            cs.setString(8, qh.getSoLuongRacktifier());
            cs.setString(9, qh.getDungLuongAcquy());
            cs.setString(10, qh.getSoLuongToAcquy());
            cs.setString(11, qh.getDienApAcquy());
            cs.setString(12, qh.getNgayDapUngNguonDuKien());
            cs.setString(13, qh.getNgayDapUngNguonThucTe());
            cs.setString(14, qh.getTinhTrangNguonDien());
            cs.setString(15, tinhTpIds);
            cs.setString(16, userId);
            cs.executeQuery();

            return cs.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
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

    public static String updateQhNetx(TramQuyHoachUpdateNetx qh, String userId, String tinhTpIds) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excel_quy_hoach.excel_update_netx(?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cs.setString(2, qh.getMaTramQH());
            cs.setString(3, qh.getNgayPhatSong());
            cs.setString(4, qh.getDvChiuTrachNhiemCktb());
            cs.setString(5, qh.getNguonThietBi());
            cs.setString(6, qh.getThoiDiemDapUngDuKien());
            cs.setString(7, qh.getCongNgheDapUng());
            cs.setString(8, qh.getChungLoaiThietBi());
            cs.setString(9, qh.getThoiGianDapUngThucTe());
            cs.setString(10, qh.getKhoKhanVuongMac());
            cs.setString(11, qh.getDanhGiaNetX());
            cs.setString(12, qh.getYKienDanhGiaNetX());
            cs.setString(13, qh.getDvChiuTrachNhiemAntena());

            cs.setString(14, qh.getLoaiAntena1());
            cs.setString(15, qh.getTenAntena1());
            cs.setString(16, qh.getHangSxAntena1());
            cs.setString(17, qh.getSoLuongAntena1());
            cs.setString(18, qh.getBangTan1());
            cs.setString(19, qh.getCauHinhPort1());
            cs.setString(20, qh.getCauHinhGain1());
            cs.setString(21, qh.getCauHinhBeamWidth1());
            cs.setString(22, qh.getTrongLuong1());
            cs.setString(23, qh.getKichCoCao1());
            cs.setString(24, qh.getKichCoRong1());
            cs.setString(25, qh.getKichCoSau1());
            cs.setString(26, qh.getDoCaoLapDat1());

            cs.setString(27, qh.getLoaiAntena2());
            cs.setString(28, qh.getTenAntena2());
            cs.setString(29, qh.getHangSxAntena2());
            cs.setString(30, qh.getSoLuongAntena2());
            cs.setString(31, qh.getBangTan2());
            cs.setString(32, qh.getCauHinhPort2());
            cs.setString(33, qh.getCauHinhGain2());
            cs.setString(34, qh.getCauHinhBeamWidth2());
            cs.setString(35, qh.getTrongLuong2());
            cs.setString(36, qh.getKichCoCao2());
            cs.setString(37, qh.getKichCoRong2());
            cs.setString(38, qh.getKichCoSau2());
            cs.setString(39, qh.getDoCaoLapDat2());

            cs.setString(40, qh.getLoaiAntena3());
            cs.setString(41, qh.getTenAntena3());
            cs.setString(42, qh.getHangSxAntena3());
            cs.setString(43, qh.getSoLuongAntena3());
            cs.setString(44, qh.getBangTan3());
            cs.setString(45, qh.getCauHinhPort3());
            cs.setString(46, qh.getCauHinhGain3());
            cs.setString(47, qh.getCauHinhBeamWidth3());
            cs.setString(48, qh.getTrongLuong3());
            cs.setString(49, qh.getKichCoCao3());
            cs.setString(50, qh.getKichCoRong3());
            cs.setString(51, qh.getKichCoSau3());
            cs.setString(52, qh.getDoCaoLapDat3());

            cs.setString(53, qh.getNgayDapUngDuKienAntena());
            cs.setString(54, qh.getNgayDapUngThucTeAntena());
            cs.setString(55, tinhTpIds);
            cs.setString(56, userId);
            cs.executeQuery();

            return cs.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
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

    public static List<ProjectStationBO> exportQH(String MA_QUY_HOACH, String tenQh, String khuVucIds,
            String tinhTpIds, String loaiCongNghe, String status, String permisson) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ProjectStationBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_project_station.export_qh(?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, MA_QUY_HOACH);
            cs.setString(3, tenQh);
            cs.setString(4, khuVucIds);
            cs.setString(5, tinhTpIds);
            cs.setString(6, loaiCongNghe);
            cs.setString(7, status);
            cs.setString(8, permisson);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                ProjectStationBO record = new ProjectStationBO();
                record.setMaQh(rs.getString("ma_quy_hoach"));
                record.setTenQh(rs.getString("ten_quy_hoach"));
                record.setBuildingCode(rs.getString("ma_building"));
                record.setTinhTp(rs.getString("ten_tinh_tp"));
                record.setNamKhoiTao(rs.getDate("nam_khoi_tao"));
                record.setLongitude(rs.getFloat("longitude"));
                record.setLatitude(rs.getFloat("LATITUDE"));
                record.setLoaiCongNghe(rs.getString("TEN_LOAI_CONG_NGHE"));
                record.setBangTan(rs.getString("ten_bang_tan"));
                record.setPtCsht(rs.getString("ten_loai_pt_csht"));
                record.setTrangThaiCsht(rs.getString("ten_trang_thai_csht"));
                record.setSoHieuVanBan(rs.getString("SO_HIEU_VAN_BAN"));
                record.setNgayPheDuyet(rs.getDate("NGAY_PHE_DUYET"));
                record.setNgayDieuChinhGanNhat(rs.getDate("NGAY_DIEU_CHINH_GAN_NHAT"));
                record.setNgayPhatSong(rs.getDate("NGAY_PHAT_SONG"));
                //cam ket
                record.setDVI_TRACH_NHIEM_CCTB(rs.getString("DVI_TRACH_NHIEM_CCTB"));
                record.setNGUON_THIET_BI(rs.getString("ten_nguon_thiet_bi"));
                record.setTHOI_DIEM_DAP_UNG_DU_KIEN(rs.getDate("THOI_DIEM_DAP_UNG_DU_KIEN"));
                record.setCONG_NGHE_DAP_UNG(rs.getInt("CONG_NGHE_DAP_UNG"));
                record.setTEN_CONG_NGHE_DAP_UNG(rs.getString("TEN_CONG_NGHE_DAP_UNG"));
                record.setCHUNG_LOAI_THIET_BI(rs.getString("CHUNG_LOAI_THIET_BI"));
                record.setTHOI_DIEM_DAP_UNG_THUC_TE(rs.getDate("THOI_DIEM_DAP_UNG_THUC_TE"));
                record.setKHO_KHAN_VUONG_MAC(rs.getString("KHO_KHAN_VUONG_MAC"));
                //csht
                record.setDVI_TRACH_NHIEM_CSHT(rs.getString("dvi_trach_nhiem_csht"));
                record.setQuanHuyen(rs.getString("ten_quan_huyen"));
                record.setPhuongXa(rs.getString("ten_phuong_xa"));
                record.setDIA_CHI(rs.getString("dia_chi"));
                record.setTEN_TRAM(rs.getString("ten_tram"));
                record.setNgay_duoc_pd_cap_von_csht(rs.getDate("ngay_duoc_pd_cap_von_csht"));
                record.setCACH_XAY_CSHT(rs.getString("ten_cach_xay_csht"));
                record.setLOAI_DAT(rs.getString("ten_loai_dat"));
                record.setNGAY_CAP_DAT(rs.getDate("NGAY_CAP_DAT"));
                record.setNGAY_XIN_PHEP_XD_NHA_TRAM(rs.getDate("NGAY_XIN_PHEP_XD_NHA_TRAM"));
                record.setNGAY_HOAN_THANH_THU_TUC(rs.getDate("NGAY_HOAN_THANH_THU_TUC"));
                record.setNGAY_KHOI_CONG_XD_NHA_TRAM(rs.getDate("NGAY_KHOI_CONG_XD_NHA_TRAM"));
                record.setNGAY_HOAN_THANH_XD_NHA_TRAM(rs.getDate("NGAY_HOAN_THANH_XD_NHA_TRAM"));
                record.setLOAI_NHA_TRAM(rs.getString("ten_loai_nha_tram"));
                record.setTinh_trang_nha_tram(rs.getInt("tinh_trang_nha_tram"));
                record.setNGAY_XIN_PHEP_DO_CAO_COT(rs.getDate("NGAY_XIN_PHEP_DO_CAO_COT"));
                record.setNGAY_CAP_PHEP_DO_CAO_COT(rs.getDate("NGAY_CAP_PHEP_DO_CAO_COT"));
                record.setNGAY_HOAN_THANH_THU_TUC_XD_COT(rs.getDate("NGAY_HOAN_THANH_THU_TUC_XD_COT"));
                record.setNGAY_KHOI_CONG_XD_COT(rs.getDate("NGAY_KHOI_CONG_XD_COT"));
                record.setNGAY_HOAN_THANH_XD_COT(rs.getDate("NGAY_HOAN_THANH_XD_COT"));
                record.setLOAI_COT_ANTEN(rs.getString("loai_cot_anten"));
                record.setDO_CAO_COT(rs.getInt("DO_CAO_COT"));
                record.setDO_CAO_VI_TRI_XAY_COT_ANTTEN(rs.getInt("do_cao_vi_tri_xay_cot_antten"));
                record.setTinh_trang_cot_anten(rs.getInt("tinh_trang_cot_anten"));
                record.setLOAI_TRUYEN_DAN(rs.getString("ten_loai_truyen_dan"));
                record.setGIAODIEN_TD_E1(rs.getInt("giaodien_td_e1"));
                record.setGIAODIEN_TD_FE(rs.getString("GIAODIEN_TD_FE"));
                record.setGIAODIEN_TD_GE(rs.getString("GIAODIEN_TD_GE"));
                record.setGIAODIEN_TD_STM1(rs.getInt("giaodien_td_stm1"));
                record.setNGAY_KHOI_CONG_TD(rs.getDate("ngay_khoi_cong_td"));
                record.setNGAY_HOAN_THANH_TD(rs.getDate("ngay_hoan_thanh_td"));
                record.setTinh_trang_truyen_dan(rs.getInt("tinh_trang_truyen_dan"));
                record.setNGAY_DAP_UNG_DIEN_AC(rs.getDate("ngay_dap_ung_dien_ac"));
                record.setHE_THONG_DIEN_NHA_TRAM_ID(rs.getInt("he_thong_dien_nha_tram_id"));
                record.setHE_THONG_DIEU_HOA_ID(rs.getInt("he_thong_dieu_hoa_id"));
                record.setHE_THONG_TIEP_DAT_ID(rs.getInt("he_thong_tiep_dat_id"));
                record.setHE_THONG_MAY_NO_ID(rs.getInt("he_thong_may_no_id"));
                record.setNGAY_HOAN_THANH_PHU_TRO(rs.getDate("ngay_hoan_thanh_phu_tro"));
                record.setDOI_TUONG_THONG_BAO(rs.getString("doi_tuong_thong_bao"));
                record.setSO_HIEU_THONG_BAO(rs.getString("so_hieu_thong_bao"));
                record.setNGAY_THONG_BAO_HT_CSHT(rs.getDate("ngay_thong_bao_ht_csht"));
                record.setKHO_KHAN_VUONG_MAC_CSHT(rs.getString("kho_khan_vuong_mac_csht"));
                //nguon dc
                record.setDVI_TRACH_NHIEM_TU_NGUON(rs.getString("dvi_trach_nhiem_tu_nguon"));
                record.setTU_NGUON_DC(rs.getString("tu_nguon_dc"));
                record.setTEN_LOAI_TU_NGUON(rs.getString("ten_loai_tu_nguon"));
                record.setDUNG_LUONG_TU_NGUON(rs.getInt("dung_luong_tu_nguon"));
                record.setSO_RACTIFIER(rs.getInt("so_ractifier"));
                record.setDUNG_LUONG_ACCU(rs.getInt("dung_luong_accu"));
                record.setSO_LUONG_ACCU(rs.getInt("so_luong_accu"));
                record.setDIEN_AP_ACCU(rs.getInt("dien_ap_accu"));
                record.setNGAY_DAP_UNG_NGUON_DC_DU_KIEN(rs.getDate("ngay_dap_ung_nguon_dc_du_kien"));
                record.setNGAY_DAP_UNG_NGUON_DC_TT(rs.getDate("ngay_dap_ung_nguon_dc_tt"));
                record.setTinh_trang_nguon_dien(rs.getInt("tinh_trang_nguon_dien"));
                result.add(record);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

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
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return result;
    }

}
