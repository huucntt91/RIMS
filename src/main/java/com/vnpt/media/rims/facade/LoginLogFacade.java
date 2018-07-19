/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ActionLogBO;
import com.vnpt.media.rims.bean.ManEInfoBO;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Cyano
 */
public class LoginLogFacade {
    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");
    
     public static List<ActionLogBO> findAll(Integer startRow, Integer endRow, String user_name, String ip, Date create_date) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ActionLogBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_log.find_all(?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow == null ? "0" : startRow.toString());
            cs.setString(3, endRow == null ? "0" : endRow.toString());
            cs.setString(4, user_name == null ? "": user_name.trim());
            cs.setString(5, ip == null ? "" : ip.trim());
            cs.setString(6, DateTimeUtils.convertDateString(create_date, "dd/MM/yyyy HH:mm:ss"));
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                ActionLogBO item = new ActionLogBO();
                item.setActionlog_id(rs.getInt("actionlog_id"));
                item.setActionlog_name(rs.getString("actionlog_name"));
                item.setIp(rs.getString("actionlog_ip"));
                item.setUser_name(rs.getString("username"));
                item.setActionlog_time(rs.getDate("actionlog_time"));
                item.setActionlog_time_value(DateTimeUtils.convertDateString(item.getActionlog_time(), "dd/MM/yyyy HH:mm:ss"));
                result.add(item);
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

    public static int countSearch(String user_name, String ip, Date create_date) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_log.count_search(?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, user_name == null? "" : user_name.trim());
            cs.setString(3, ip == null ? "": ip.trim());
            cs.setString(4, DateTimeUtils.convertDateString(create_date, "dd/MM/yyyy HH:mm:ss"));
            cs.executeQuery();
            result = (int) cs.getObject(1);
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
