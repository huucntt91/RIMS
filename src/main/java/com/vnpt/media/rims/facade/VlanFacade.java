/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;


import com.vnpt.media.rims.bean.VlanBO;
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
public class VlanFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");
    
    
    public static List<VlanBO> findAll(String startRow, String endRow,String tnode_id) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<VlanBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_vlan.fn_find(?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow);
            cs.setString(3, endRow);
            cs.setString(4, tnode_id);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                VlanBO item = new VlanBO();
                item.setVlanName(rs.getString("vlan_name"));
                item.setVlanId(rs.getString("vlan_id"));
                item.setVlanType(rs.getString("vlan_type"));
                int status = rs.getInt("status");
                if(status == 0) item.setStatus("Không hoạt động");
                else item.setStatus("Hoạt động");
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
            String sql = "begin ?:=pkg_vlan.count_search(?); end;";
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
