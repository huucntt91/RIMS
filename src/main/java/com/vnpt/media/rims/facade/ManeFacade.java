/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.EthernetBcBO;
import com.vnpt.media.rims.bean.ManEInfoBO;
import com.vnpt.media.rims.bean.ModuleQuangBcBO;
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
public class ManeFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    public static ManEInfoBO findById(String tnodeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_mane.find_by_id(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tnodeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                ManEInfoBO item = new ManEInfoBO();
                item.setTNODE_ID(rs.getInt("tnode_id"));
                item.setTNODE_CODE(rs.getString("tnode_code"));
                item.setTNODE_NAME(rs.getString("tnode_name"));
                item.setTLIST_NODE_CHA_ID(rs.getString("tlist_node_cha_id"));
                item.setDONG_TBI_ID(rs.getInt("dong_tbi_id"));
                item.setTEN_DONG_TBI(rs.getString("ten_dong_tbi"));
                item.setTOTAL_SLOT(rs.getString("total_slot"));
                item.setBUILDING_ID(rs.getInt("building_id"));
                item.setTNODE_TYPE_ID(rs.getInt("tnode_type_id"));
                item.setIP(rs.getString("ip"));
                item.setRING(rs.getString("ring"));
                item.setSTATUS(rs.getInt("status"));
                if (item.getSTATUS().intValue() == 1) {
                    item.setStatusValue("Hoạt động");
                }
                item.setMa_building(rs.getString("ma_building"));
                item.setNOTE(rs.getString("note"));
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

    public static List<ManEInfoBO> findAll(Integer startRow, Integer endRow, String tNodeId,
            String tNodeCode, String tNodeName, String tNodeTypeId,
            String khuVucId, String provinceId, String districtId, String wardsId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ManEInfoBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_mane.fn_find(?,?,?,?,?,?,?,?,?,?); end;";
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
                ManEInfoBO item = new ManEInfoBO();
                item.setTNODE_ID(rs.getInt("tnode_id"));
                item.setTNODE_CODE(rs.getString("tnode_code"));
                item.setTNODE_NAME(rs.getString("tnode_name"));
                item.setTLIST_NODE_CHA_ID(rs.getString("tlist_node_cha_id"));
                item.setDONG_TBI_ID(rs.getInt("dong_tbi_id"));
                item.setTEN_DONG_TBI(rs.getString("ten_dong_tbi"));
                item.setTOTAL_SLOT(rs.getString("total_slot"));
                item.setBUILDING_ID(rs.getInt("building_id"));
                item.setTNODE_TYPE_ID(rs.getInt("tnode_type_id"));
                item.setIP(rs.getString("ip"));
                item.setRING(rs.getString("ring"));
                item.setSTATUS(rs.getInt("status"));
                if (item.getSTATUS().intValue() == 1) {
                    item.setStatusValue("Hoạt động");
                }
                item.setMa_building(rs.getString("ma_building"));
                item.setNOTE(rs.getString("note"));
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
            String sql = "begin ?:=pkg_mane.count_search(?,?,?,?,?,?,?); end;";
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
            String sql = "begin ?:=pkg_mane.fn_delete(?,?); end;";
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

    public static boolean addMane(ManEInfoBO mane, Long userId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_mane.fn_add(?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, mane.getTNODE_CODE());
            cs.setString(3, mane.getTNODE_NAME());
            cs.setString(4, mane.getTLIST_NODE_CHA_ID());
            cs.setString(5, mane.getDONG_TBI_ID() == null ? "" : mane.getDONG_TBI_ID().toString());
            cs.setString(6, mane.getTOTAL_SLOT());
            cs.setString(7, mane.getBUILDING_ID() == null ? "" : mane.getBUILDING_ID().toString());
            cs.setString(8, mane.getTNODE_TYPE_ID() == null ? "" : mane.getTNODE_TYPE_ID().toString());
            cs.setString(9, mane.getIP());
            cs.setString(10, mane.getRING());
            cs.setString(11, mane.getNOTE());
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

    public static boolean updateMane(ManEInfoBO mane, Long userId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_mane.fn_update(?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, mane.getTNODE_ID() == null ? "" : mane.getTNODE_ID().toString());
            cs.setString(3, mane.getTNODE_CODE());
            cs.setString(4, mane.getTNODE_NAME());
            cs.setString(5, mane.getTLIST_NODE_CHA_ID());
            cs.setString(6, mane.getDONG_TBI_ID() == null ? "" : mane.getDONG_TBI_ID().toString());
            cs.setString(7, mane.getTOTAL_SLOT());
            cs.setString(8, mane.getBUILDING_ID() == null ? "" : mane.getBUILDING_ID().toString());
            cs.setString(9, mane.getTNODE_TYPE_ID() == null ? "" : mane.getTNODE_TYPE_ID().toString());
            cs.setString(10, mane.getIP());
            cs.setString(11, mane.getRING());
            cs.setString(12, mane.getNOTE());
            cs.setString(13, userId == null ? "" : userId.toString());
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

    public static List<ManEInfoBO> findChaList(String tNodeChaIdList) {

        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ManEInfoBO> result = null;

        if (tNodeChaIdList.equalsIgnoreCase(",")) {
            return null;
        }
        if (tNodeChaIdList != null && !tNodeChaIdList.isEmpty()) {
            tNodeChaIdList = tNodeChaIdList.substring(1);
            tNodeChaIdList = tNodeChaIdList.substring(0, tNodeChaIdList.length() - 1);
        }
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_mane.find_cha_list(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tNodeChaIdList);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                ManEInfoBO item = new ManEInfoBO();
                item.setTNODE_ID(rs.getInt("tnode_id"));
                item.setTNODE_CODE(rs.getString("tnode_code"));
                item.setTNODE_NAME(rs.getString("tnode_name"));
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

    //bao cao modulequang theo thiet bi
    public static List<ModuleQuangBcBO> report(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ModuleQuangBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_module_quang(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.setString(3, tnodeTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                ModuleQuangBcBO item = new ModuleQuangBcBO();
                item.setCode(rs.getString("tnode_code"));
                item.setIp(rs.getString("ip"));
                item.setOtherTotal(rs.getInt("othertotal"));
                item.setBaseEx(rs.getInt("base_ex"));
                item.setNotUseBaseEx(rs.getInt("notusebaseex"));
                item.setBaseLx(rs.getInt("base_lx"));
                item.setNotUseBaseLx(rs.getInt("notusebaselx"));
                item.setBaseZx(rs.getInt("base_zx"));
                item.setNotUseBaseZx(rs.getInt("notusebasezx"));
                item.setSfp1G(rs.getInt("sfp1g"));
                item.setBaseEr(rs.getInt("base_er"));
                item.setNotUseBaseEr(rs.getInt("notusebaseer"));
                item.setBaseLr(rs.getInt("base_lr"));
                item.setNotUseBaseLr(rs.getInt("notusebaselr"));
                item.setBaseZr(rs.getInt("base_zr"));
                item.setNotUseBaseZr(rs.getInt("notusebasezr"));
                item.setSfp10G(rs.getInt("sfp10g"));
                item.setOther(rs.getInt("other"));
                item.setNotUseOther(rs.getInt("notuseother"));
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

    public static List<ModuleQuangBcBO> find_module_quangtb(Integer startRow, Integer endRow, String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ModuleQuangBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.find_module_quangtb(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow == null ? "0" : startRow.toString());
            cs.setString(3, endRow == null ? "0" : endRow.toString());
            cs.setString(4, tinhTpId);
            cs.setString(5, tnodeTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                ModuleQuangBcBO item = new ModuleQuangBcBO();
                item.setCode(rs.getString("tnode_code"));
                item.setIp(rs.getString("ip"));
                item.setOtherTotal(rs.getInt("othertotal"));
                item.setBaseEx(rs.getInt("base_ex"));
                item.setNotUseBaseEx(rs.getInt("notusebaseex"));
                item.setBaseLx(rs.getInt("base_lx"));
                item.setNotUseBaseLx(rs.getInt("notusebaselx"));
                item.setBaseZx(rs.getInt("base_zx"));
                item.setNotUseBaseZx(rs.getInt("notusebasezx"));
                item.setSfp1G(rs.getInt("sfp1g"));
                item.setBaseEr(rs.getInt("base_er"));
                item.setNotUseBaseEr(rs.getInt("notusebaseer"));
                item.setBaseLr(rs.getInt("base_lr"));
                item.setNotUseBaseLr(rs.getInt("notusebaselr"));
                item.setBaseZr(rs.getInt("base_zr"));
                item.setNotUseBaseZr(rs.getInt("notusebasezr"));
                item.setSfp10G(rs.getInt("sfp10g"));
                item.setOther(rs.getInt("other"));
                item.setNotUseOther(rs.getInt("notuseother"));
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

    //module quang theo tinh tp
    public static List<ModuleQuangBcBO> find_module_quangtp(Integer startRow, Integer endRow, String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ModuleQuangBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.find_module_quang_tp(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow == null ? "0" : startRow.toString());
            cs.setString(3, endRow == null ? "0" : endRow.toString());
            cs.setString(4, tnodeTypeId);
            cs.setString(5, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                ModuleQuangBcBO item = new ModuleQuangBcBO();
                item.setTenTinhTP(rs.getString("ten_tinh_tp"));
                item.setOtherTotal(rs.getInt("othertotal"));
                item.setBaseEx(rs.getInt("base_ex"));
                item.setNotUseBaseEx(rs.getInt("notusebaseex"));
                item.setBaseLx(rs.getInt("base_lx"));
                item.setNotUseBaseLx(rs.getInt("notusebaselx"));
                item.setBaseZx(rs.getInt("base_zx"));
                item.setNotUseBaseZx(rs.getInt("notusebasezx"));
                item.setSfp1G(rs.getInt("sfp1g"));
                item.setBaseEr(rs.getInt("base_er"));
                item.setNotUseBaseEr(rs.getInt("notusebaseer"));
                item.setBaseLr(rs.getInt("base_lr"));
                item.setNotUseBaseLr(rs.getInt("notusebaselr"));
                item.setBaseZr(rs.getInt("base_zr"));
                item.setNotUseBaseZr(rs.getInt("notusebasezr"));
                item.setSfp10G(rs.getInt("sfp10g"));
                item.setOther(rs.getInt("other"));
                item.setNotUseOther(rs.getInt("notuseother"));
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

    public static List<ModuleQuangBcBO> reportTp(String tnodeTypeId, String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ModuleQuangBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_module_quang_tp(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tnodeTypeId);
            cs.setString(3, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                ModuleQuangBcBO item = new ModuleQuangBcBO();
                item.setTenTinhTP(rs.getString("ten_tinh_tp"));
                item.setOtherTotal(rs.getInt("othertotal"));
                item.setBaseEx(rs.getInt("base_ex"));
                item.setNotUseBaseEx(rs.getInt("notusebaseex"));
                item.setBaseLx(rs.getInt("base_lx"));
                item.setNotUseBaseLx(rs.getInt("notusebaselx"));
                item.setBaseZx(rs.getInt("base_zx"));
                item.setNotUseBaseZx(rs.getInt("notusebasezx"));
                item.setSfp1G(rs.getInt("sfp1g"));
                item.setBaseEr(rs.getInt("base_er"));
                item.setNotUseBaseEr(rs.getInt("notusebaseer"));
                item.setBaseLr(rs.getInt("base_lr"));
                item.setNotUseBaseLr(rs.getInt("notusebaselr"));
                item.setBaseZr(rs.getInt("base_zr"));
                item.setNotUseBaseZr(rs.getInt("notusebasezr"));
                item.setSfp10G(rs.getInt("sfp10g"));
                item.setOther(rs.getInt("other"));
                item.setNotUseOther(rs.getInt("notuseother"));
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

    //
    public static List<EthernetBcBO> ethernetTp(String tinhTpId,String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EthernetBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_ethernet_tinhtp(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tnodeTypeId);
            cs.setString(3, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                EthernetBcBO item = new EthernetBcBO();
                item.setTenTinhTp(rs.getString("ten_tinh_tp"));
                item.setUse1G(rs.getInt("use1g"));
                item.setNotUse1G(rs.getInt("notuse1g"));
                item.setPort_1g(item.getUse1G() + item.getNotUse1G());
                item.setUse10G(rs.getInt("use10g"));
                item.setNotUse10G(rs.getInt("notuse10g"));
                item.setPort_10g(item.getUse10G() + item.getNotUse10G());
                item.setUseSfp1G(rs.getInt("usesfp1g"));
                item.setNotUseSfp1G(rs.getInt("notusesfp1g"));
                item.setSfp1g(item.getUseSfp1G() + item.getNotUseSfp1G());
                item.setUseSfp10G(rs.getInt("usesfp10g"));
                item.setNotUseSfp10G(rs.getInt("notusesfp10g"));
                item.setSfp10g(item.getUseSfp10G() + item.getNotUseSfp10G());
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

    public static List<EthernetBcBO> ethernetTb(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EthernetBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_ethernet_theotb(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.setString(3, tnodeTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                EthernetBcBO item = new EthernetBcBO();
                item.setTenTinhTp(rs.getString("ten_tinh_tp"));
                item.setCode(rs.getString("tnode_code"));
                item.setIp(rs.getString("ip"));
                item.setUse1G(rs.getInt("use1g"));
                item.setNotUse1G(rs.getInt("notuse1g"));
                item.setPort_1g(item.getUse1G() + item.getNotUse1G());
                item.setUse10G(rs.getInt("use10g"));
                item.setNotUse10G(rs.getInt("notuse10g"));
                item.setPort_10g(item.getUse10G() + item.getNotUse10G());
                item.setUseSfp1G(rs.getInt("usesfp1g"));
                item.setNotUseSfp1G(rs.getInt("notusesfp1g"));
                item.setSfp1g(item.getUseSfp1G() + item.getNotUseSfp1G());
                item.setUseSfp10G(rs.getInt("usesfp10g"));
                item.setNotUseSfp10G(rs.getInt("notusesfp10g"));
                item.setSfp10g(item.getUseSfp10G() + item.getNotUseSfp10G());
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
    
    public static List<EthernetBcBO> findEthernetTb(Integer startRow, Integer endRow,String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EthernetBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.find_ethernet_theotb(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow == null ? "0" : startRow.toString());
            cs.setString(3, endRow == null ? "0" : endRow.toString());
            cs.setString(4, tinhTpId);
            cs.setString(5, tnodeTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                EthernetBcBO item = new EthernetBcBO();
                item.setTenTinhTp(rs.getString("ten_tinh_tp"));
                item.setCode(rs.getString("tnode_code"));
                item.setIp(rs.getString("ip"));
                item.setUse1G(rs.getInt("use1g"));
                item.setNotUse1G(rs.getInt("notuse1g"));
                item.setPort_1g(item.getUse1G() + item.getNotUse1G());
                item.setUse10G(rs.getInt("use10g"));
                item.setNotUse10G(rs.getInt("notuse10g"));
                item.setPort_10g(item.getUse10G() + item.getNotUse10G());
                item.setUseSfp1G(rs.getInt("usesfp1g"));
                item.setNotUseSfp1G(rs.getInt("notusesfp1g"));
                item.setSfp1g(item.getUseSfp1G() + item.getNotUseSfp1G());
                item.setUseSfp10G(rs.getInt("usesfp10g"));
                item.setNotUseSfp10G(rs.getInt("notusesfp10g"));
                item.setSfp10g(item.getUseSfp10G() + item.getNotUseSfp10G());
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
    
    public static Integer countEthernetTb(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.count_search_ethernet_theotb(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, tinhTpId);
            cs.setString(3, tnodeTypeId);
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
    
     public static Integer countEthernetTp(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.count_ethernet_theotp(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, tnodeTypeId);
            cs.setString(3, tinhTpId);
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
     
     public static List<EthernetBcBO> findEthernetTp(Integer startRow, Integer endRow,String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EthernetBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.find_ethernet_theotp(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow == null ? "0" : startRow.toString());
            cs.setString(3, endRow == null ? "0" : endRow.toString());
            cs.setString(4,tnodeTypeId );
            cs.setString(5, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                EthernetBcBO item = new EthernetBcBO();
                item.setTenTinhTp(rs.getString("ten_tinh_tp"));
                item.setUse1G(rs.getInt("use1g"));
                item.setNotUse1G(rs.getInt("notuse1g"));
                item.setPort_1g(item.getUse1G() + item.getNotUse1G());
                item.setUse10G(rs.getInt("use10g"));
                item.setNotUse10G(rs.getInt("notuse10g"));
                item.setPort_10g(item.getUse10G() + item.getNotUse10G());
                item.setUseSfp1G(rs.getInt("usesfp1g"));
                item.setNotUseSfp1G(rs.getInt("notusesfp1g"));
                item.setSfp1g(item.getUseSfp1G() + item.getNotUseSfp1G());
                item.setUseSfp10G(rs.getInt("usesfp10g"));
                item.setNotUseSfp10G(rs.getInt("notusesfp10g"));
                item.setSfp10g(item.getUseSfp10G() + item.getNotUseSfp10G());
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
    
    public static String checkIsIP(String ip) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_mane.check_ip(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, ip);
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
        return String.valueOf(result);
    }
}
