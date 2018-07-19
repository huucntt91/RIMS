/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;
import com.vnpt.media.rims.bean.PortInNotinUsedBO;
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
public class PortInNotinUsedFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");


    public static List<PortInNotinUsedBO> findAll(Integer startRow, Integer endRow, String tNodeId,
            String tNodeCode, String tNodeName, 
            String khuVucId, String provinceId, String districtId, String wardsId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<PortInNotinUsedBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_port_inused.fn_find(?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow == null ? "0" : startRow.toString());
            cs.setString(3, endRow == null ? "0" : endRow.toString());
            cs.setString(4, tNodeId);
            cs.setString(5, tNodeCode);
            cs.setString(6, tNodeName);
            cs.setString(7, khuVucId);
            cs.setString(8, provinceId);
            cs.setString(9, districtId);
            cs.setString(10, wardsId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                PortInNotinUsedBO item = new PortInNotinUsedBO();
                item.setId(rs.getString("tnode_id"));
                item.setCode(rs.getString("tnode_code"));
                item.setName(rs.getString("tnode_name"));
                item.setCardName(rs.getString("teq3_name"));
                item.setTotalPort(rs.getString("total_port"));
                item.setPortInused(rs.getString("portInUse"));
                item.setPortNotInused(rs.getString("portNotInUse"));
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

    public static int countSearch(String tNodeCode, String tNodeName, String khuVucId,
            String provinceId, String districtId, String wardsId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_port_inused.count_search(?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, tNodeCode);
            cs.setString(3, tNodeName);
            cs.setString(4, khuVucId);
            cs.setString(5, provinceId);
            cs.setString(6, districtId);
            cs.setString(7, wardsId);
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
