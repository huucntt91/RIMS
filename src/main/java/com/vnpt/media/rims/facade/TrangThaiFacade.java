package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.DMTrangThaiBO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class TrangThaiFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static ArrayList<DMTrangThaiBO> fc_find_all(String prs_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<DMTrangThaiBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_dm_trang_thai.fc_find_all(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                DMTrangThaiBO item = new DMTrangThaiBO();
                item.setTrangthai_id(rs.getString("trangthai_id") == null ? "" : rs.getString("trangthai_id"));
                item.setTen_trangthai(rs.getString("ten_trangthai") == null ? "" : rs.getString("ten_trangthai"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public static int fn_modify(String prn_action, String prs_id, String prs_ten_trangthai) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_dm_trang_thai.fn_modify(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_action);
            cstmt.setString(3, prs_id);
            cstmt.setString(4, prs_ten_trangthai);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
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
