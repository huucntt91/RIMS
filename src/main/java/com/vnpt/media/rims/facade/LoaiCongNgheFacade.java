package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.DMLoaiCongNgheBO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoaiCongNgheFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static ArrayList<DMLoaiCongNgheBO> fc_find_all(String prs_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<DMLoaiCongNgheBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_dm_loai_cong_nghe.fc_find_all(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                DMLoaiCongNgheBO item = new DMLoaiCongNgheBO();
                //node_id, ma_node, latitude, longitude, type, icon, azimuth, tinhtp_id, quanhuyen_id, phuongxa_id
                item.setId_loai_cong_nghe(rs.getString("id_loai_cong_nghe") == null ? "" : rs.getString("id_loai_cong_nghe"));
                item.setTen_loai_cong_nghe(rs.getString("ten_loai_cong_nghe") == null ? "" : rs.getString("ten_loai_cong_nghe"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
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

    public static int fn_modify(String prn_action, String prs_id, String prs_ten_loai_cong_nghe) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_dm_loai_cong_nghe.fn_modify(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_action);
            cstmt.setString(3, prs_id);
            cstmt.setString(4, prs_ten_loai_cong_nghe);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
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
}
