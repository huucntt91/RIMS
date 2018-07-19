package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.KhuvucBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.common.utils.StringUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TinhDSFacade {

    private static final Logger LOGGER = LogManager.getLogger(TinhDSFacade.class);
    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static ArrayList<PhuongXaBO> findAllPhuongXa(String id, String huyenId) {
        LOGGER.debug("findAllPhuongXa ({},{})", id, huyenId);
        if (huyenId.contains("undefined")) {
            huyenId = "0";
        }
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<PhuongXaBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_TINH.fc_find_phuongxa_by_list_huyen(?); end;";
            if (StringUtils.hasText(id)) {
                sql = "begin ?:=PKG_TINH.fc_find_by_id(?,?); end;";
            }
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            if (StringUtils.hasText(id)) {
                cstmt.setString(2, id);
                cstmt.setString(3, "PHUONG_XA");
            } else {
                cstmt.setString(2, huyenId);
            }
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                PhuongXaBO dvBO = new PhuongXaBO();
                dvBO.setPhuongXaId(rs.getLong("phuongxa_id"));
                dvBO.setTenPhuongXa(rs.getString("ten_phuong_xa") == null ? "" : rs.getString("ten_phuong_xa"));
                dvBO.setQuanHuyenId(rs.getLong("quanhuyen_id"));
                dvBO.setTenQuanHuyen(rs.getString("ten_quan_huyen") == null ? "" : rs.getString("ten_quan_huyen"));
                arrayList.add(dvBO);
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

    public static ArrayList<HuyenBO> findAllHuyen(String id, String tinhId) {
        LOGGER.debug("findAllHuyen ({},{})", id, tinhId);
        if (tinhId.contains("undefined")) {
            tinhId = "0";
        }
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<HuyenBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_TINH.fc_find_huyen_by_tinh_id(?); end;";
            if (StringUtils.hasText(id)) {
                sql = "begin ?:=PKG_TINH.fc_find_by_id(?,?); end;";
            }
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            if (StringUtils.hasText(id)) {
                cstmt.setString(2, id);
                cstmt.setString(3, "QUAN_HUYEN");
            } else {
                cstmt.setString(2, tinhId);
            }
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                HuyenBO dvBO = new HuyenBO();
                dvBO.setQuanHuyenId(rs.getLong("quanhuyen_id"));
                dvBO.setTenQuanHuyen(rs.getString("ten_quan_huyen") == null ? "" : rs.getString("ten_quan_huyen"));
                dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                dvBO.setTenTinhTp(rs.getString("ten_tinh_tp") == null ? "" : rs.getString("ten_tinh_tp"));
                arrayList.add(dvBO);
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

    public static ArrayList<HuyenBO> findAllHuyen(String listTinhId) {
        LOGGER.debug("findAllHuyen list tinh({})", listTinhId);
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<HuyenBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_TINH.fc_find_huyen_by_list_tinh_id(?); end;";

            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, listTinhId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                HuyenBO dvBO = new HuyenBO();
                dvBO.setQuanHuyenId(rs.getLong("quanhuyen_id"));
                dvBO.setTenQuanHuyen(rs.getString("ten_quan_huyen") == null ? "" : rs.getString("ten_quan_huyen"));
                dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                dvBO.setTenTinhTp(rs.getString("ten_tinh_tp") == null ? "" : rs.getString("ten_tinh_tp"));
                arrayList.add(dvBO);
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

    public static ArrayList<TinhBO> findAllTinh(String id) {
        //LOGGER.debug("findAllTinh ({},{})", id);
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<TinhBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_TINH.fc_find_all_tinh(); end;";
            if (StringUtils.hasText(id)) {
                sql = "begin ?:=PKG_TINH.fc_find_by_id(?,?); end;";
            }
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            if (StringUtils.hasText(id)) {
                cstmt.setString(2, id);
                cstmt.setString(3, "TINH_TP");
            }
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                TinhBO dvBO = new TinhBO();
                dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                dvBO.setKhuVuc(rs.getLong("khu_vuc"));
                dvBO.setTenTinhTp(rs.getString("ten_tinh_tp") == null ? "" : rs.getString("ten_tinh_tp"));
                dvBO.setMaTinhTp(rs.getString("ma_tinh_tp") == null ? "" : rs.getString("ma_tinh_tp"));
                arrayList.add(dvBO);
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

    public static ArrayList<KhuvucBO> findAllKhuVuc(String id) {
        //LOGGER.debug("findAllTinh ({},{})", id);
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<KhuvucBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_TINH.fc_find_all_khuvuc(); end;";
            if (StringUtils.hasText(id)) {
                sql = "begin ?:=PKG_TINH.fc_find_all_khuvuc_by_id(?); end;";
            }
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            if (StringUtils.hasText(id)) {
                cstmt.setString(2, id);
            }
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                KhuvucBO khuvucBO = new KhuvucBO();
                khuvucBO.setId(rs.getLong("id"));
                khuvucBO.setName(rs.getString("name") == null ? "" : rs.getString("name"));
                arrayList.add(khuvucBO);
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

    public static ArrayList<TinhBO> findTinhByKv(String khuVucId) {
        LOGGER.debug("findTinhByKv ({})", khuVucId);
        if (khuVucId.contains("undefined")) {
            khuVucId = "0";
        }
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<TinhBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_tinh.find_tinh_by_kv(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, khuVucId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                TinhBO dvBO = new TinhBO();
                dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                dvBO.setKhuVuc(rs.getLong("khu_vuc"));
                dvBO.setTenTinhTp(rs.getString("ten_tinh_tp") == null ? "" : rs.getString("ten_tinh_tp"));
                dvBO.setMaTinhTp(rs.getString("ma_tinh_tp") == null ? "" : rs.getString("ma_tinh_tp"));
                arrayList.add(dvBO);
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
