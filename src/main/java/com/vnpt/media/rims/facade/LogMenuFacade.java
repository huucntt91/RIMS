package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.DMCachXayCSHTBO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class LogMenuFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");


    public static void logMenu(String userId, String ip, String menu) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin pkg_log_menu.insert_log_menu(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, userId);
            cstmt.setString(2, ip);
            cstmt.setString(3, menu);
            cstmt.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
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
