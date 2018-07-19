/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.EvcBO;
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
public class EvcFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");
    
    
    public static List<EvcBO> findAll(String startRow, String endRow,String tnode_id) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EvcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_evc.fn_find(?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow);
            cs.setString(3, endRow);
            cs.setString(4, tnode_id);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                EvcBO item = new EvcBO();
                item.setEvcId(rs.getString("evc_id"));
                item.setTnodeId(rs.getString("tnode_id"));
                item.setVcIndex(rs.getString("vc_index"));
                item.setVcId(rs.getString("vc_id"));
                int status1 = rs.getInt("status1");
                if(status1 == 0) item.setStatus1("Không hoạt động");
                else item.setStatus1("Hoạt động");
                item.setInterfaceName(rs.getString("interface_name"));
                item.setDeviceName(rs.getString("device_name"));
                int status2 = rs.getInt("status2");
                if(status2 == 0) item.setStatus2("Không hoạt động");
                else item.setStatus2("Hoạt động");
                item.setIp(rs.getString("ip"));
                item.setDescription(rs.getString("description"));
                item.setActiveTime(new Date(rs.getDate("active_time").getTime()));
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
            String sql = "begin ?:=pkg_evc.count_search(?); end;";
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
