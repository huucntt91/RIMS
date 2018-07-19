/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.NeLinkBO;
import com.vnpt.media.rims.bean.OpcBO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author Cyano
 */
public class NeLinkFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static ArrayList<NeLinkBO> find_ne_link(String prs_start_record, String prs_length_page,
            String prs_global_search, String prs_list_column_name, String prs_list_column_search, String prs_column_to_sort,
            String prs_sort_direction, String[] recordsTotal, String[] recordsFiltered) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<NeLinkBO> arrayList = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_NE_LINK.find_ne_link(?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            cs.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            cs.setString(2, prs_start_record);
            cs.setString(3, prs_length_page);
            cs.setString(4, prs_global_search);
            cs.setString(5, prs_list_column_name);
            cs.setString(6, prs_list_column_search);
            cs.setString(7, prs_column_to_sort);
            cs.setString(8, prs_sort_direction);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            recordsTotal[0] = cs.getString(9);
            recordsFiltered[0] = cs.getString(10);
            arrayList = new ArrayList<>();
            while (rs.next()) {
                NeLinkBO item = new NeLinkBO();
                item.setNe_link_id(rs.getInt("ne_link_id"));
                item.setNode_id1(rs.getInt("node_id1"));
                item.setNodeCode1(rs.getString("node_code1"));
                item.setNe_type_id1(rs.getInt("ne_type_id1"));
                item.setNode_id2(rs.getInt("node_id2"));
                item.setNodeCode2(rs.getString("node_code2"));
                item.setNe_type_id2(rs.getInt("ne_type_id2"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return arrayList;
    }

    public static ArrayList<NeLinkBO> find_all_ne_link() {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<NeLinkBO> arrayList = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_NE_LINK.find_all_ne_link; end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);

            arrayList = new ArrayList<>();
            while (rs.next()) {
                NeLinkBO item = new NeLinkBO();
                item.setNe_link_id(rs.getInt("ne_link_id"));
                item.setNode_id1(rs.getInt("node_id1"));
                item.setNodeCode1(rs.getString("node_code1"));
                item.setNode_id2(rs.getInt("node_id2"));
                item.setNodeCode2(rs.getString("node_code2"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return arrayList;
    }

    public static Integer countTotal(String p_node_code1) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int count = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_NE_LINK.count_total; end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.executeQuery();
            count = cs.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return count;
    }

    public static Map<Integer, OpcBO> find_opc(String p_node_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        Map<Integer, OpcBO> map = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_NE_LINK.find_opc(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, p_node_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            map = new HashMap<>();
            while (rs.next()) {
                OpcBO item = new OpcBO();
                item.setNode_id(rs.getInt("node_id"));
                item.setOpc(rs.getString("opc"));
                item.setNumeral_system(rs.getString("numeral_system"));
                item.setOpc1(rs.getString("opc1"));
                item.setNumeral_system1(rs.getString("numeral_system1"));
                map.put(item.getNode_id(), item);
            }
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return map;
    }

    public static boolean delete(String ne_link_id) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_NE_LINK.delete_ne_link(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, ne_link_id);
            cs.executeQuery();
            if (cs.getInt(1) == 1) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return false;
    }
}
