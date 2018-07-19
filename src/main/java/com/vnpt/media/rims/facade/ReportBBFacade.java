/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ChiTietCardBO;
import com.vnpt.media.rims.bean.EthernetBcBO;
import com.vnpt.media.rims.bean.ManETaiNguyenMangCiscoBO;
import com.vnpt.media.rims.bean.ManETaiNguyenMangHuaweiBO;
import com.vnpt.media.rims.bean.ManETaiNguyenMangJuniperBO;
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
public class ReportBBFacade {

    private static final String RIMS_BB_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    public static Integer countChiTietCardTp(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_report.count_card_theotb(?,?); end;";
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

    public static List<ChiTietCardBO> findChiTietCardTp(Integer startRow, Integer endRow, String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ChiTietCardBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_report.find_card_theotb(?,?,?,?); end;";
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
                ChiTietCardBO item = new ChiTietCardBO();
                item.setTenTinh(rs.getString("ten_tinh_tp"));
                item.setCode(rs.getString("tnode_code"));
                item.setIp(rs.getString("tnode_code"));
                item.setDongTBiName(rs.getString("ten_dong_tbi"));
                item.setTvendorName(rs.getString("tvendor_name"));
                item.setFrame(rs.getInt("teq2_number"));
                item.setSlot(rs.getInt("teq3_number"));
                item.setCardName(rs.getString("teq3_name"));
                item.setSerialNumber(rs.getString("serial_number"));
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

    public static List<ChiTietCardBO> reportChiTietCardTp(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ChiTietCardBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_report.report_card_theotb(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.setString(3, tnodeTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                ChiTietCardBO item = new ChiTietCardBO();
                item.setTenTinh(rs.getString("ten_tinh_tp"));
                item.setCode(rs.getString("tnode_code"));
                item.setIp(rs.getString("tnode_code"));
                item.setDongTBiName(rs.getString("ten_dong_tbi"));
                item.setTvendorName(rs.getString("tvendor_name"));
                item.setFrame(rs.getInt("teq2_number"));
                item.setSlot(rs.getInt("teq3_number"));
                item.setCardName(rs.getString("teq3_name"));
                item.setSerialNumber(rs.getString("serial_number"));
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

    public static List<EthernetBcBO> findEthernetTbVn2(Integer startRow, Integer endRow, String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EthernetBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
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

                item.setUse100G(rs.getInt("use100g"));
                item.setNotUse100G(rs.getInt("notuse100g"));
                item.setPort100G(item.getUse100G() + item.getNotUse100G());

                item.setUseCfp100G(rs.getInt("usecfp100g"));
                item.setNotUseCfp100G(rs.getInt("notusecfp100g"));
                item.setCfp100G(item.getUseCfp100G() + item.getNotUseCfp100G());

                item.setUseStm16(rs.getInt("usestm16"));
                item.setNotUseStm16(rs.getInt("notusestm16"));
                item.setStm16(item.getUseStm16() + item.getNotUseStm16());

                item.setUseSfpOc48(rs.getInt("usesfpoc48"));
                item.setNotUseSfpOc48(rs.getInt("notusesfpoc48"));
                item.setSfpOc48(item.getUseSfpOc48() + item.getNotUseSfpOc48());

                item.setUseStm64(rs.getInt("usestm64"));
                item.setNotUseStm64(rs.getInt("notusestm64"));
                item.setStm64(item.getUseStm64() + item.getNotUseStm64());

                item.setUseSfpOc192(rs.getInt("usesfpoc192"));
                item.setNotUseSfpOc192(rs.getInt("notusesfpoc192"));
                item.setSfpOc192(item.getUseSfpOc192() + item.getNotUseSfpOc192());

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

    public static Integer countEthernetTbVn2(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
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

    public static List<EthernetBcBO> ethernetTbVn2(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EthernetBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
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

                item.setUse100G(rs.getInt("use100g"));
                item.setNotUse100G(rs.getInt("notuse100g"));
                item.setPort100G(item.getUse100G() + item.getNotUse100G());

                item.setUseCfp100G(rs.getInt("usecfp100g"));
                item.setNotUseCfp100G(rs.getInt("notusecfp100g"));
                item.setCfp100G(item.getUseCfp100G() + item.getNotUseCfp100G());

                item.setUseStm16(rs.getInt("usestm16"));
                item.setNotUseStm16(rs.getInt("notusestm16"));
                item.setStm16(item.getUseStm16() + item.getNotUseStm16());

                item.setUseSfpOc48(rs.getInt("usesfpoc48"));
                item.setNotUseSfpOc48(rs.getInt("notusesfpoc48"));
                item.setSfpOc48(item.getUseSfpOc48() + item.getNotUseSfpOc48());

                item.setUseStm64(rs.getInt("usestm64"));
                item.setNotUseStm64(rs.getInt("notusestm64"));
                item.setStm64(item.getUseStm64() + item.getNotUseStm64());

                item.setUseSfpOc192(rs.getInt("usesfpoc192"));
                item.setNotUseSfpOc192(rs.getInt("notusesfpoc192"));
                item.setSfpOc192(item.getUseSfpOc192() + item.getNotUseSfpOc192());

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

    public static Integer countEthernetTpVn2(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
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

    public static List<EthernetBcBO> findEthernetTpVn2(Integer startRow, Integer endRow, String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EthernetBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_report.find_ethernet_theotp(?,?,?,?); end;";
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

                item.setUse100G(rs.getInt("use100g"));
                item.setNotUse100G(rs.getInt("notuse100g"));
                item.setPort100G(item.getUse100G() + item.getNotUse100G());

                item.setUseCfp100G(rs.getInt("usecfp100g"));
                item.setNotUseCfp100G(rs.getInt("notusecfp100g"));
                item.setCfp100G(item.getUseCfp100G() + item.getNotUseCfp100G());

                item.setUseStm16(rs.getInt("usestm16"));
                item.setNotUseStm16(rs.getInt("notusestm16"));
                item.setStm16(item.getUseStm16() + item.getNotUseStm16());

                item.setUseSfpOc48(rs.getInt("usesfpoc48"));
                item.setNotUseSfpOc48(rs.getInt("notusesfpoc48"));
                item.setSfpOc48(item.getUseSfpOc48() + item.getNotUseSfpOc48());

                item.setUseStm64(rs.getInt("usestm64"));
                item.setNotUseStm64(rs.getInt("notusestm64"));
                item.setStm64(item.getUseStm64() + item.getNotUseStm64());

                item.setUseSfpOc192(rs.getInt("usesfpoc192"));
                item.setNotUseSfpOc192(rs.getInt("notusesfpoc192"));
                item.setSfpOc192(item.getUseSfpOc192() + item.getNotUseSfpOc192());
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

    public static List<EthernetBcBO> ethernetTpVn2(String tinhTpId, String tnodeTypeId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EthernetBcBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
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

                item.setUse100G(rs.getInt("use100g"));
                item.setNotUse100G(rs.getInt("notuse100g"));
                item.setPort100G(item.getUse100G() + item.getNotUse100G());

                item.setUseCfp100G(rs.getInt("usecfp100g"));
                item.setNotUseCfp100G(rs.getInt("notusecfp100g"));
                item.setCfp100G(item.getUseCfp100G() + item.getNotUseCfp100G());

                item.setUseStm16(rs.getInt("usestm16"));
                item.setNotUseStm16(rs.getInt("notusestm16"));
                item.setStm16(item.getUseStm16() + item.getNotUseStm16());

                item.setUseSfpOc48(rs.getInt("usesfpoc48"));
                item.setNotUseSfpOc48(rs.getInt("notusesfpoc48"));
                item.setSfpOc48(item.getUseSfpOc48() + item.getNotUseSfpOc48());

                item.setUseStm64(rs.getInt("usestm64"));
                item.setNotUseStm64(rs.getInt("notusestm64"));
                item.setStm64(item.getUseStm64() + item.getNotUseStm64());

                item.setUseSfpOc192(rs.getInt("usesfpoc192"));
                item.setNotUseSfpOc192(rs.getInt("notusesfpoc192"));
                item.setSfpOc192(item.getUseSfpOc192() + item.getNotUseSfpOc192());
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

    public static List<Object> findTaiNguyenMangMane(Integer startRow, Integer endRow, String typeId, String khuVucId, String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Object> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_report.find_tainguyenmang(?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, startRow == null ? "0" : startRow.toString());
            cs.setString(3, endRow == null ? "0" : endRow.toString());
            cs.setString(4, khuVucId);
            cs.setString(5, tinhTpId);
            cs.setString(6, "1,2,3");
            cs.setString(7, typeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            if (typeId.equalsIgnoreCase("1")) {
                while (rs.next()) {
                    ManETaiNguyenMangHuaweiBO item = new ManETaiNguyenMangHuaweiBO();
                    item.setKhuVuc(rs.getString("khu_vuc"));
                    item.setTenTinh(rs.getString("ten_tinh_tp"));
                    item.setCode(rs.getString("tnode_code"));
                    item.setVendor(rs.getString("tvendor_name"));
                    item.setTypeName(rs.getString("tnode_name"));
                    result.add(item);
                }
            } else if (typeId.equalsIgnoreCase("2")) {
                while (rs.next()) {
                    ManETaiNguyenMangCiscoBO item = new ManETaiNguyenMangCiscoBO();
                    item.setKhuVuc(rs.getString("khu_vuc"));
                    item.setTenTinh(rs.getString("ten_tinh_tp"));
                    item.setCode(rs.getString("tnode_code"));
                    item.setVendor(rs.getString("tvendor_name"));
                    item.setTypeName(rs.getString("tnode_name"));
                    result.add(item);
                }
            } else if (typeId.equalsIgnoreCase("3")) {
                while (rs.next()) {
                    ManETaiNguyenMangJuniperBO item = new ManETaiNguyenMangJuniperBO();
                    item.setKhuVuc(rs.getString("khu_vuc"));
                    item.setTenTinh(rs.getString("ten_tinh_tp"));
                    item.setCode(rs.getString("tnode_code"));
                    item.setVendor(rs.getString("tvendor_name"));
                    item.setTypeName(rs.getString("tnode_name"));
                    result.add(item);
                }
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

    public static Integer countTaiNguyenMangMane(String typeId, String khuVucId, String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_report.count_tainguyenmang(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, khuVucId);
            cs.setString(3, tinhTpId);
            cs.setString(4, "1,2,3");
            cs.setString(5, typeId);
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
    
    public static List<Object> taiNguyenMangMane( String typeId, String khuVucId, String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Object> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_report.report_tainguyenmang(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, khuVucId);
            cs.setString(3, tinhTpId);
            cs.setString(4, "1,2,3");
            cs.setString(5, typeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            if (typeId.equalsIgnoreCase("1")) {
                while (rs.next()) {
                    ManETaiNguyenMangHuaweiBO item = new ManETaiNguyenMangHuaweiBO();
                    item.setKhuVuc(rs.getString("khu_vuc"));
                    item.setTenTinh(rs.getString("ten_tinh_tp"));
                    item.setCode(rs.getString("tnode_code"));
                    item.setVendor(rs.getString("tvendor_name"));
                    item.setTypeName(rs.getString("tnode_name"));
                    result.add(item);
                }
            } else if (typeId.equalsIgnoreCase("2")) {
                while (rs.next()) {
                    ManETaiNguyenMangCiscoBO item = new ManETaiNguyenMangCiscoBO();
                    item.setKhuVuc(rs.getString("khu_vuc"));
                    item.setTenTinh(rs.getString("ten_tinh_tp"));
                    item.setCode(rs.getString("tnode_code"));
                    item.setVendor(rs.getString("tvendor_name"));
                    item.setTypeName(rs.getString("tnode_name"));
                    result.add(item);
                }
            } else if (typeId.equalsIgnoreCase("3")) {
                while (rs.next()) {
                    ManETaiNguyenMangJuniperBO item = new ManETaiNguyenMangJuniperBO();
                    item.setKhuVuc(rs.getString("khu_vuc"));
                    item.setTenTinh(rs.getString("ten_tinh_tp"));
                    item.setCode(rs.getString("tnode_code"));
                    item.setVendor(rs.getString("tvendor_name"));
                    item.setTypeName(rs.getString("tnode_name"));
                    result.add(item);
                }
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
