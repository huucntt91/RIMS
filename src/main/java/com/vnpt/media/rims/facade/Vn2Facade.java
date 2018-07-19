/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;
import com.vnpt.media.rims.bean.TNodeBO;
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
public class Vn2Facade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    public static TNodeBO findById(String tnodeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_vn2.find_by_id(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tnodeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                TNodeBO item = new TNodeBO();
                item.setId(rs.getString("tnode_id"));
                item.setCode(rs.getString("tnode_code"));
                item.setName(rs.getString("tnode_name"));
                item.setChaId(rs.getString("tlist_node_cha_id"));
                item.setDongTBiId(rs.getString("dong_tbi_id"));
                item.setDongTBiName(rs.getString("ten_dong_tbi"));
                item.setTotalSlot(rs.getString("total_slot"));
                item.setBuildingId(rs.getString("building_id"));
                item.setTypeId(rs.getString("tnode_type_id"));
                item.setIp(rs.getString("ip"));
                item.setStatus(rs.getString("status"));
                item.setBuildingCode(rs.getString("ma_building"));
                item.setNote(rs.getString("note"));
                return item;

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
        return null;
    }

    public static List<TNodeBO> findAll(Integer startRow, Integer endRow, String tNodeId,
            String tNodeCode, String tNodeName, String tNodeTypeId,
            String khuVucId, String provinceId, String districtId, String wardsId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<TNodeBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_vn2.fn_find(?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow == null ? "0" : startRow.toString());
            cs.setString(3, endRow == null ? "0" : endRow.toString());
            cs.setString(4, tNodeId);
            cs.setString(5, tNodeCode);
            cs.setString(6, tNodeName);
            cs.setString(7, tNodeTypeId);
            cs.setString(8, khuVucId);
            cs.setString(9, provinceId);
            cs.setString(10, districtId);
            cs.setString(11, wardsId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                TNodeBO item = new TNodeBO();
                item.setId(rs.getString("tnode_id"));
                item.setCode(rs.getString("tnode_code"));
                item.setName(rs.getString("tnode_name"));
                item.setChaId(rs.getString("tlist_node_cha_id"));
                item.setDongTBiId(rs.getString("dong_tbi_id"));
                item.setDongTBiName(rs.getString("ten_dong_tbi"));
                item.setTotalSlot(rs.getString("total_slot"));
                item.setBuildingId(rs.getString("building_id"));
                item.setTypeId(rs.getString("tnode_type_id"));
                item.setIp(rs.getString("ip"));
                item.setStatus(rs.getString("status"));
                item.setBuildingCode(rs.getString("ma_building"));
                item.setNote(rs.getString("note"));
                item.setTenTinh(rs.getString("ten_tinh_tp"));
                item.setTypeName(rs.getString("type_name"));
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

    public static int countSearch(String tNodeCode, String tNodeName, String tNodeTypeId, String khuVucId,
            String provinceId, String districtId, String wardsId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_vn2.count_search(?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, tNodeCode);
            cs.setString(3, tNodeName);
            cs.setString(4, tNodeTypeId);
            cs.setString(5, khuVucId);
            cs.setString(6, provinceId);
            cs.setString(7, districtId);
            cs.setString(8, wardsId);
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

    public static void delete(String tNodeId, String userId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_vn2.fn_delete(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, tNodeId);
            cs.setString(3, userId);
            cs.executeQuery();
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

    }

    public static boolean add(TNodeBO node, Long userId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_vn2.fn_add(?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, node.getCode());
            cs.setString(3, node.getName());
            cs.setString(4, node.getChaId());
            cs.setString(5, node.getDongTBiId());
            cs.setString(6, node.getTotalSlot());
            cs.setString(7, node.getBuildingId());
            cs.setString(8, node.getTypeId());
            cs.setString(9, node.getIp());
            cs.setString(10, node.getNote());
            cs.setString(11, userId == null ? "" : userId.toString());
            cs.executeQuery();
            return true;
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
        return false;
    }

    public static boolean update(TNodeBO node, Long userId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_vn2.fn_update(?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, node.getId());
            cs.setString(3, node.getCode());
            cs.setString(4, node.getName());
            cs.setString(5, node.getChaId());
            cs.setString(6, node.getDongTBiId());
            cs.setString(7, node.getTotalSlot());
            cs.setString(8, node.getBuildingId());
            cs.setString(9, node.getTypeId());
            cs.setString(10, node.getIp());
            cs.setString(11, node.getNote());
            cs.setString(12, userId == null ? "" : userId.toString());
            cs.executeQuery();
            return true;
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
        return false;
    }

    public static List<TNodeBO> findChaList(String tNodeChaIdList) {

        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<TNodeBO> result = null;

        if (tNodeChaIdList.equalsIgnoreCase(",")) {
            return null;
        }
        if (tNodeChaIdList != null && !tNodeChaIdList.isEmpty()) {
            tNodeChaIdList = tNodeChaIdList.substring(1);
            tNodeChaIdList = tNodeChaIdList.substring(0, tNodeChaIdList.length() - 1);
        }
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_vn2.find_cha_list(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tNodeChaIdList);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                TNodeBO item = new TNodeBO();
                item.setId(rs.getString("tnode_id"));
                item.setCode(rs.getString("tnode_code"));
                item.setName(rs.getString("tnode_name"));
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
}
