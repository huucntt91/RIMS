/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ActionLogBO;
import com.vnpt.media.rims.bean.LogConfigBO;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author Cyano
 */
public class LogFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static List<LogConfigBO> findConfig(String group_name, String object_name) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<LogConfigBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_log.find_log_config(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, group_name == null ? "" : group_name.trim());
            cs.setString(3, object_name == null ? "" : object_name.trim());
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                LogConfigBO item = new LogConfigBO();
                item.setId(rs.getInt("id"));
                item.setGroup_name(rs.getString("group_name"));
                item.setObject_name(rs.getString("object_name"));
                item.setSql_select(rs.getString("sql_select"));
                item.setColumn_name(rs.getString("column_name"));
                item.setColumn_title(rs.getString("column_title"));
                item.setCreate_time(rs.getString("create_time"));
                item.setUser_id(rs.getString("user_id"));
                item.setAction(rs.getString("action"));
                item.setOrder_by(rs.getString("order_by"));
                item.setFrom_date(rs.getString("from_date"));
                item.setTo_date(rs.getString("to_date"));
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

    public static List<LogConfigBO> findGroup() {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<LogConfigBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_log.FIND_GROUP ; end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                LogConfigBO item = new LogConfigBO();
                item.setGroup_name(rs.getString("group_name"));
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

    public static List<Map<String, String>> findData(LogConfigBO obj) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Map<String, String>> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_log.find_data(?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            String colums[] = obj.getColumn_name().split(",");

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, obj.getSql_select());
            cs.setString(3, obj.getCreate_time());
            cs.setString(4, obj.getUser_id());
            cs.setString(5, obj.getAction());
            cs.setString(6, obj.getOrder_by());
            cs.setString(7, obj.getFrom_date());
            cs.setString(8, obj.getTo_date());
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                for (String colum : colums) {
                    map.put(colum.toLowerCase().trim(), rs.getString(colum.toLowerCase().trim()) == null ? "" : rs.getString(colum.toLowerCase().trim()));
                }
                result.add(map);
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
