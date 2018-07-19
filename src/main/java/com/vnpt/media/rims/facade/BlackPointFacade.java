package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.BlackPointBO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class BlackPointFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static ArrayList<BlackPointBO> fc_find_all() {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<BlackPointBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_black_point.fc_find_all(); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                BlackPointBO item = new BlackPointBO();
                item.setId(rs.getString("id") == null ? "" : rs.getString("id"));
                item.setCode(rs.getString("code") == null ? "" : rs.getString("code"));
                item.setName(rs.getString("name") == null ? "" : rs.getString("name"));
                item.setTenTinhTp(rs.getString("ten_tinh_tp") == null ? "" : rs.getString("ten_tinh_tp"));
                item.setTenQuanHuyen(rs.getString("ten_quan_huyen") == null ? "" : rs.getString("ten_quan_huyen"));
                item.setLat(rs.getString("lat") == null ? "" : rs.getString("lat"));
                item.setLon(rs.getString("lon") == null ? "" : rs.getString("lon"));
                item.setAddress(rs.getString("address") == null ? "" : rs.getString("address"));
                item.setDes(rs.getString("des") == null ? "" : rs.getString("des"));
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

    public static String addBlackPointExcel(BlackPointBO item, String userId) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_black_point.fn_check_tram_quy_hoach(?,?,?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cstmt.setString(2, item.getName());
            cstmt.setString(3, item.getTenTinhTp());
            cstmt.setString(4, item.getTenQuanHuyen());
            cstmt.setString(5, item.getAddress());
            cstmt.setString(6, item.getDes());
            cstmt.setString(7, item.getLat());
            cstmt.setString(8, item.getLon());
            cstmt.setString(9, userId);
            cstmt.executeQuery();
            cstmt.executeQuery();
            return cstmt.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "-1";
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
