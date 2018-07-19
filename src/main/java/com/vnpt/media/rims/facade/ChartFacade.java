/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ChartBO;
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
public class ChartFacade {

    private static final String INVENTORY_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("INVENTORY_DS");

    public static List<ChartBO> fn_get_download_file(String p_host, String p_vendor, String p_start_time, String p_end_time) {

        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ChartBO> arrayList = null;
        try {
            arrayList = new ArrayList<>();
            conn = EnvManager.getDbConnection(INVENTORY_DS);
            String sql = "begin ?:=pkg_mediator.fn_get_download_file(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, p_host);
            cstmt.setString(3, p_vendor);
            cstmt.setString(4, p_start_time);
            cstmt.setString(5, p_end_time);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ChartBO item = new ChartBO();
//                item.setHost(rs.getString("ip"));
                item.setVendor(rs.getString("vendor"));
                item.setFile_num(rs.getInt("file_num"));
                item.setDownload_time(rs.getDate("download_time"));
                arrayList.add(item);
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
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
        return arrayList;
    }
    
    /*
    load du lieu de vieu bieu do so luong parsed
    */
    public static List<ChartBO> fn_get_parsing(String ne_type, String p_vendor, String p_start_time, String p_end_time) {

        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ChartBO> arrayList = null;
        try {
            arrayList = new ArrayList<>();
            conn = EnvManager.getDbConnection(INVENTORY_DS);
            String sql = "begin ?:=pkg_mediator.fn_get_parsing(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, ne_type);
            cstmt.setString(3, p_vendor);
            cstmt.setString(4, p_start_time);
            cstmt.setString(5, p_end_time);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ChartBO item = new ChartBO();
                item.setVendor(rs.getString("vendor"));
                item.setFile_num(rs.getInt("num"));
                item.setDownload_time(new Date(rs.getDate("parsing_time").getTime()));
                arrayList.add(item);
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
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
        return arrayList;
    }
}
