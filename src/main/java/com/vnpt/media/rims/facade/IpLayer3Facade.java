/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;


import com.vnpt.media.rims.bean.IpLayer3BO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Cyano
 */
public class IpLayer3Facade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");
    
    
    public static List<IpLayer3BO> findAll(String startRow, String endRow,String tnode_id) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<IpLayer3BO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_iplayer3.fn_find(?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow);
            cs.setString(3, endRow);
            cs.setString(4, tnode_id);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                IpLayer3BO item = new IpLayer3BO();
                item.setIpLayer3Name(rs.getString("iplayer3_name"));
                item.setIp(rs.getString("ip"));
                item.setSubnet(rs.getString("subnet"));
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
    
    public static int countSearch(String tnode_id) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_iplayer3.count_search(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, tnode_id);
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
