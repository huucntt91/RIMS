package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.formbean.ToPoForm;
import com.vnpt.media.rims.bean.TLinkBO;
import com.vnpt.media.rims.bean.TNodeBO;
import com.vnpt.media.rims.formbean.SearchTopoForm;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class TopoManEFacade {

    private static final String RIMS_BB_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    public static ArrayList<TNodeBO> listTopoByTinh(String prs_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<TNodeBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_topo_mane.list_topo_by_tinh(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                TNodeBO item = new TNodeBO();
                item.setId(rs.getString("tnode_id") == null ? "" : rs.getString("tnode_id"));
                item.setCode(rs.getString("tnode_code") == null ? "" : rs.getString("tnode_code"));
                item.setName(rs.getString("tnode_name") == null ? "" : rs.getString("tnode_name"));
                item.setBuildingId(rs.getString("building_id") == null ? "" : rs.getString("building_id"));
                item.setStatus(rs.getString("status") == null ? "" : rs.getString("status"));
                item.setChaId(rs.getString("tlist_node_cha_id") == null ? "" : rs.getString("tlist_node_cha_id"));
                item.setTotalSlot(rs.getString("total_slot") == null ? "" : rs.getString("total_slot"));
                item.setImageUrl(rs.getString("image_url") == null ? "" : rs.getString("image_url"));
                item.setTypeId(rs.getString("tnode_type_id") == null ? "" : rs.getString("tnode_type_id"));
                item.setIp(rs.getString("ip") == null ? "" : rs.getString("ip"));
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
    
    
    public static SearchTopoForm searchTopo(String tnode1,String tnode2) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<TNodeBO> arrayTNode = new ArrayList<>();
        ArrayList<TLinkBO> arrayTLink = new ArrayList<>();
        SearchTopoForm result = new SearchTopoForm();
        result.setTnode1(tnode1);
        result.setTnode2(tnode2);
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin PKG_DIRECTION.get_direction_ab(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, tnode1);
            cstmt.setString(2, tnode2);
            cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.executeQuery();
            // get list node
            rs = (ResultSet) cstmt.getObject(3);
            while (rs.next()) {
                TNodeBO item = new TNodeBO();
                item.setId(rs.getString("tnode_id") == null ? "" : rs.getString("tnode_id"));
                item.setCode(rs.getString("tnode_code") == null ? "" : rs.getString("tnode_code"));
                item.setName(rs.getString("tnode_name") == null ? "" : rs.getString("tnode_name"));
                item.setBuildingId(rs.getString("building_id") == null ? "" : rs.getString("building_id"));
                item.setStatus(rs.getString("status") == null ? "" : rs.getString("status"));
                item.setChaId(rs.getString("tlist_node_cha_id") == null ? "" : rs.getString("tlist_node_cha_id"));
                item.setTotalSlot(rs.getString("total_slot") == null ? "" : rs.getString("total_slot"));
                item.setImageUrl(rs.getString("image_url") == null ? "" : rs.getString("image_url"));
                item.setTypeId(rs.getString("tnode_type_id") == null ? "" : rs.getString("tnode_type_id"));
                item.setIp(rs.getString("ip") == null ? "" : rs.getString("ip"));
                item.setTinhTpId(rs.getString("tinhtp_id") == null ? "" : rs.getString("tinhtp_id"));
                item.setMaTinh(rs.getString("ma_tinh_tp") == null ? "" : rs.getString("ma_tinh_tp"));
                arrayTNode.add(item);
            }
            result.setListTNode(arrayTNode);
            // list node edges
            rs = (ResultSet) cstmt.getObject(4);
            while (rs.next()) {
                TLinkBO item = new TLinkBO();
                item.setId(rs.getString("tlink_id") == null ? "" : rs.getString("tlink_id"));
                item.setNodeId1(rs.getString("tnode_id1") == null ? "" : rs.getString("tnode_id1"));
                item.setNodeId2(rs.getString("tnode_id2") == null ? "" : rs.getString("tnode_id2"));
                item.setPort1(rs.getString("port1") == null ? "" : rs.getString("port1"));
                item.setPort2(rs.getString("port2") == null ? "" : rs.getString("port2"));
                item.setDistanceM(rs.getString("distance_m") == null ? "" : rs.getString("distance_m") + " (m)");
                    
                item.setType(rs.getString("type") == null ? "" : rs.getString("type"));
                item.setBandWidth(rs.getString("bandwidth") == null ? "" : rs.getString("bandwidth"));
                item.setBandWidthDv(rs.getString("bandwidth_dv") == null ? "" : rs.getString("bandwidth_dv"));
                item.setRownum(rs.getString("rownum") == null ? "" : rs.getString("rownum"));
                item.setLoai(rs.getString("loai") == null ? "" : rs.getString("loai"));
                arrayTLink.add(item);
            }
            result.setListTLink(arrayTLink);
            
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
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
    
    
    
    public static ArrayList<TLinkBO> listLinkTopoByTinh(String prs_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<TLinkBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_topo_mane.list_link_topo_by_tinh(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                TLinkBO item = new TLinkBO();
                item.setId(rs.getString("tlink_id") == null ? "" : rs.getString("tlink_id"));
                item.setNodeId1(rs.getString("tnode_id1") == null ? "" : rs.getString("tnode_id1"));
                item.setNodeId2(rs.getString("tnode_id2") == null ? "" : rs.getString("tnode_id2"));
                item.setPort1(rs.getString("port1") == null ? "" : rs.getString("port1"));
                item.setPort2(rs.getString("port2") == null ? "" : rs.getString("port2"));
                item.setDistanceM(rs.getString("distance_m") == null ? "" : rs.getString("distance_m") + " (m)");
                    
                item.setType(rs.getString("type") == null ? "" : rs.getString("type"));
                item.setBandWidth(rs.getString("bandwidth") == null ? "" : rs.getString("bandwidth"));
                item.setBandWidthDv(rs.getString("bandwidth_dv") == null ? "" : rs.getString("bandwidth_dv"));
                item.setRownum(rs.getString("rownum") == null ? "" : rs.getString("rownum"));
                item.setLoai(rs.getString("loai") == null ? "" : rs.getString("loai"));
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
    
    public static int fn_modify(ToPoForm model) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_topo_layout.fn_insert(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, model.getTinhTpId());
            cstmt.setString(3, model.getContent());
            cstmt.setString(4, model.getTypeId());
            cstmt.setString(5, model.getEdges());
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
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
    
    public static String[] getTopoManEContent(String prs_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String[] content = new String[2];
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_topo_layout.list_topo_mane(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Clob clobContent = rs.getClob("content"); //
                Clob clobEdges = rs.getClob("edges"); //
                content[0] = clobContent == null ?  "" : clobContent.getSubString(1, (int) clobContent.length());
                content[1] = clobEdges == null ?  "" : clobEdges.getSubString(1, (int) clobEdges.length());
               
            }
            return content;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
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
    
    public static int findTNodeIdByRadio(String nodeId) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_topo_mane.gettnodebynode(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, nodeId);
        
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
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
