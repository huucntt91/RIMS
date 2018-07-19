/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.InterfaceBO;
import com.vnpt.media.rims.bean.InterfaceBcBO;
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
public class InterfaceFacade {

    private static final String RIMSBB_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    public static List<InterfaceBO> findAll(String startRow, String endRow, String tnode_id) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<InterfaceBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMSBB_DS);
            String sql = "begin ?:=pkg_interface.fn_find(?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow);
            cs.setString(3, endRow);
            cs.setString(4, tnode_id);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                InterfaceBO item = new InterfaceBO();
                item.setInterfaceId(rs.getString("interface_id"));
                item.setTnodeId(rs.getString("tnode_id"));
                item.setInterfaceName(rs.getString("interface_name"));
                item.setInterfaceType(rs.getString("interface_type"));
                item.setMtu(rs.getString("mtu"));
                item.setSpeed(rs.getString("speed"));
                item.setUpdateDate(new Date(rs.getDate("update_date").getTime()));
                int status = rs.getInt("status");
                if (status == 0) {
                    item.setStatus("Không hoạt động");
                } else {
                    item.setStatus("Hoạt động");
                }
                item.setDescription(rs.getString("description"));
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
            conn = EnvManager.getDbConnection(RIMSBB_DS);
            String sql = "begin ?:=pkg_interface.count_search(?); end;";
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

    //report
    public static int countInterfaceTb(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMSBB_DS);
            String sql = "begin ?:=pkg_report.count_search_interface_theotb(?,?); end;";
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

    public static List<InterfaceBcBO> searchInterfaceTb(Integer startRow, Integer endRow, String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<InterfaceBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMSBB_DS);
            String sql = "begin ?:=pkg_report.search_interface_theotb(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, startRow == null ? 0 : startRow.intValue());
            cs.setInt(3, endRow == null ? 0 : endRow.intValue());
            cs.setString(4, tinhTpId);
            cs.setString(5, tnodeTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                InterfaceBcBO item = new InterfaceBcBO();
                item.setCode(rs.getString("tnode_code"));
                item.setIp(rs.getString("ip"));
                item.setLoopBack(rs.getInt("LoopBack"));
                item.setEthernet(rs.getInt("Ethernet"));
                item.setAdLag(rs.getInt("AdLag"));
                item.setServiceInstance(rs.getInt("ServiceInstance"));
                item.setSubInterface(rs.getInt("SubInterface"));
                item.setPropVirtual(rs.getInt("PropVirtual"));
                item.setL3IpVlan(rs.getInt("L3IpVlan"));
                item.setMpls(rs.getInt("Mpls"));
                item.setTunnel(rs.getInt("Tunnel"));
                item.setMplsTunnel(rs.getInt("MplsTunnel"));
                item.setOther(rs.getInt("Other"));
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

    public static List<InterfaceBcBO> interfaceTb(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<InterfaceBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMSBB_DS);
            String sql = "begin ?:=pkg_report.report_interface_theotb(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.setString(3, tnodeTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                InterfaceBcBO item = new InterfaceBcBO();
                item.setCode(rs.getString("tnode_code"));
                item.setIp(rs.getString("ip"));
                item.setLoopBack(rs.getInt("LoopBack"));
                item.setEthernet(rs.getInt("Ethernet"));
                item.setAdLag(rs.getInt("AdLag"));
                item.setServiceInstance(rs.getInt("ServiceInstance"));
                item.setSubInterface(rs.getInt("SubInterface"));
                item.setPropVirtual(rs.getInt("PropVirtual"));
                item.setL3IpVlan(rs.getInt("L3IpVlan"));
                item.setMpls(rs.getInt("Mpls"));
                item.setTunnel(rs.getInt("Tunnel"));
                item.setMplsTunnel(rs.getInt("MplsTunnel"));
                item.setOther(rs.getInt("Other"));
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
  public static int countInterfaceTp(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMSBB_DS);
            String sql = "begin ?:=pkg_report.count_search_interface_theotp(?,?); end;";
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
    public static List<InterfaceBcBO> searchInterfaceTp(Integer startRow, Integer endRow, String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<InterfaceBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMSBB_DS);
            String sql = "begin ?:=pkg_report.search_interface_theotp(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, startRow == null ? 0 : startRow.intValue());
            cs.setInt(3, endRow == null ? 0 : endRow.intValue());
            cs.setString(4, tinhTpId);
            cs.setString(5, tnodeTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                InterfaceBcBO item = new InterfaceBcBO();
                item.setMaTinh(rs.getString("ma_tinh_tp"));
                item.setTenTinhTp(rs.getString("ten_tinh_tp"));
                item.setLoopBack(rs.getInt("LoopBack"));
                item.setEthernet(rs.getInt("Ethernet"));
                item.setAdLag(rs.getInt("AdLag"));
                item.setServiceInstance(rs.getInt("ServiceInstance"));
                item.setSubInterface(rs.getInt("SubInterface"));
                item.setPropVirtual(rs.getInt("PropVirtual"));
                item.setL3IpVlan(rs.getInt("L3IpVlan"));
                item.setMpls(rs.getInt("Mpls"));
                item.setTunnel(rs.getInt("Tunnel"));
                item.setMplsTunnel(rs.getInt("MplsTunnel"));
                item.setOther(rs.getInt("Other"));
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

    public static List<InterfaceBcBO> interfaceTp(String tinhTpId,String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<InterfaceBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMSBB_DS);
            String sql = "begin ?:=pkg_report.report_interface_theotp(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.setString(3, tnodeTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                InterfaceBcBO item = new InterfaceBcBO();
                item.setMaTinh(rs.getString("ma_tinh_tp"));
                item.setTenTinhTp(rs.getString("ten_tinh_tp"));
                item.setLoopBack(rs.getInt("LoopBack"));
                item.setEthernet(rs.getInt("Ethernet"));
                item.setAdLag(rs.getInt("AdLag"));
                item.setServiceInstance(rs.getInt("ServiceInstance"));
                item.setSubInterface(rs.getInt("SubInterface"));
                item.setPropVirtual(rs.getInt("PropVirtual"));
                item.setL3IpVlan(rs.getInt("L3IpVlan"));
                item.setMpls(rs.getInt("Mpls"));
                item.setTunnel(rs.getInt("Tunnel"));
                item.setMplsTunnel(rs.getInt("MplsTunnel"));
                item.setOther(rs.getInt("Other"));
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
