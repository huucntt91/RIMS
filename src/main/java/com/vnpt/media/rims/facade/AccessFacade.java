package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.DsLamBO;
import com.vnpt.media.rims.bean.DslamPortDeviceReportBO;
import com.vnpt.media.rims.bean.DslamPortTinhReportBO;
import com.vnpt.media.rims.bean.DslamReportBO;
import com.vnpt.media.rims.bean.GponBO;
import com.vnpt.media.rims.bean.GponPortDeviceReportBO;
import com.vnpt.media.rims.bean.GponPortTinhReportBO;
import com.vnpt.media.rims.bean.GponReportBO;
import com.vnpt.media.rims.bean.L2SWPortDeviceReportBO;
import com.vnpt.media.rims.bean.L2SWPortTinhReportBO;
import com.vnpt.media.rims.bean.L2swReportBO;
import com.vnpt.media.rims.bean.SwitchBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IAccess;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccessFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";
    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    private static class AccessFacadeHolder {

        private static final AccessFacade INSTANCE = new AccessFacade();
    }

    public AccessFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public AccessFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public static AccessFacade getInstance() {
        return AccessFacadeHolder.INSTANCE;
    }

//    public List<TNodeBO> findAll(String tnodeid, String code, String name) throws ServiceException {
    public List<?> findAll(Integer startRow, Integer endRow, String tNodeId,
            String tNodeCode, String tNodeName, String tNodeTypeId,
            String khuVucId,String provinceId, String districtId, String wardsId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IAccess i = factory.getAccessDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<?> iRet = new ArrayList<>();
            iRet = i.findAllDsLam(startRow, endRow, tNodeId,tNodeTypeId, tNodeCode, tNodeName,khuVucId,provinceId,districtId,wardsId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotal(String tNodeId, String tNodeCode, String tNodeName, String tNodeTypeId,
            String khuVucId,String provinceId, String districtId, String wardsId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IAccess i = factory.getAccessDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.getTotalDsLam(tNodeId,tNodeTypeId, tNodeCode, tNodeName,khuVucId,provinceId,districtId,wardsId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
   
    public boolean addDsLam(DsLamBO access, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IAccess i = factory.getAccessDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.addDsLam(access, userId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public boolean addSwitch(SwitchBO access, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IAccess i = factory.getAccessDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.addSwitch(access, userId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public boolean addGpon(GponBO access, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IAccess i = factory.getAccessDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.addGpon(access, userId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public boolean updateDsLam(DsLamBO access, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IAccess i = factory.getAccessDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.updateDsLam(access, userId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public boolean updateSwitch(SwitchBO access, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IAccess i = factory.getAccessDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.updateSwitch(access, userId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public boolean updateGpon(GponBO access, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IAccess i = factory.getAccessDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.updateGpon(access, userId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public static List<L2swReportBO> reportL2sw(String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<L2swReportBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_sum_l2sw(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                L2swReportBO item = new L2swReportBO();
                    item.setTinhtp_id(rs.getString("tinhtp_id"));
                    item.setTen_tinh_tp(rs.getString("ten_tinh_tp"));
                    item.setTong_tb(rs.getString("tong_tb"));
                    item.setAT_FS750(rs.getString("AT_FS750"));
                    item.setBATM_T5C(rs.getString("BATM_T5C"));
                    item.setC2960(rs.getString("C2960"));
                    item.setC3560(rs.getString("C3560"));
                    item.setCE500(rs.getString("CE500"));
                    item.setISCOM2126(rs.getString("ISCOM2126"));
                    item.setLINKSYS(rs.getString("LINKSYS"));
                    item.setLS62XX(rs.getString("LS62XX"));
                    item.setME340X(rs.getString("ME340X"));
                    item.setMES3500_24F(rs.getString("MES3500_24F"));
                    item.setO62XX(rs.getString("O62XX"));
                    item.setO6424(rs.getString("O6424"));
                    item.setO6450(rs.getString("O6450"));
                    item.setO64XX(rs.getString("O64XX"));
                    item.setRAISECOM2828(rs.getString("RAISECOM2828"));
                    item.setRT2126(rs.getString("RT2126"));
                    item.setS2226_SFP(rs.getString("S2226_SFP"));
                    item.setS3328(rs.getString("S3328"));
                    item.setS4100(rs.getString("S4100"));
                    item.setS53XX(rs.getString("S53XX"));
                    item.setSF300(rs.getString("SF300"));
                    item.setTERRABIT(rs.getString("TERRABIT"));
                    item.setV6328(rs.getString("V6328"));
                    item.setVFT22XX(rs.getString("VFT22XX"));
                    item.setZTE3928(rs.getString("ZTE3928"));
                    item.setES_2226C(rs.getString("ES_2226C"));
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
    
    public static List<GponReportBO> reportGpon(String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<GponReportBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_sum_gpon(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                GponReportBO item = new GponReportBO();
                    item.setTinhtp_id(rs.getString("tinhtp_id"));
                    item.setTen_tinh_tp(rs.getString("ten_tinh_tp"));
                    item.setTong_tb(rs.getString("tong_tb"));
                    item.setISAM7360(rs.getString("ISAM7360"));
                    item.setMA5608(rs.getString("MA5608"));
                    item.setZTEC320(rs.getString("ZTEC320"));
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
    
    public static List<DslamReportBO> reportDsLam(String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<DslamReportBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_sum_dslam(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                DslamReportBO item = new DslamReportBO();
                    item.setTinhtp_id(rs.getString("tinhtp_id"));
                    item.setTen_tinh_tp(rs.getString("ten_tinh_tp"));
                    item.setTong_tb(rs.getString("tong_tb"));
                    item.setHIX5630(rs.getString("HIX5630"));
                    item.setHIX5635(rs.getString("HIX5635"));
                    item.setISAM73XX(rs.getString("ISAM73XX"));
                    item.setLS1540IP(rs.getString("LS1540IP"));
                    item.setMA5100(rs.getString("MA5100"));
                    item.setMA56XX(rs.getString("MA56XX"));
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
    
    public static List<GponPortTinhReportBO> reportGponPortTinh(String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<GponPortTinhReportBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_gpon_port_tinh(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                GponPortTinhReportBO item = new GponPortTinhReportBO();
                    item.setTinhtp_id(rs.getString("tinhtp_id"));
                    item.setTen_tinh_tp(rs.getString("ten_tinh_tp"));
                    item.setHieu_suat_su_dung(rs.getString("hieu_suat_su_dung"));
                    item.setSo_card_pon_lap_dat(rs.getString("so_card_pon_lap_dat"));
                    item.setSo_card_pon_mp_rong_td(rs.getString("so_card_pon_mp_rong_td"));
                    item.setSo_cong_pon_toi_da(rs.getString("so_cong_pon_toi_da"));
                    item.setSo_olt_lap_dat(rs.getString("so_olt_lap_dat"));
                    item.setSo_ont_co_the_lap_dat(rs.getString("so_ont_co_the_lap_dat"));
                    item.setSo_ont_dang_hoat_dong(rs.getString("so_ont_dang_hoat_dong"));
                    item.setSo_pon_da_lap_dat(rs.getString("so_pon_da_lap_dat"));
                    item.setSo_pon_da_su_dung(rs.getString("so_pon_da_su_dung"));
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
    
    public static List<GponPortDeviceReportBO> reportGponPortDevice(String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<GponPortDeviceReportBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_gpon_port_device(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                GponPortDeviceReportBO item = new GponPortDeviceReportBO();
                    item.setTinhtp_id(rs.getString("tinhtp_id"));
                    item.setTen_tinh_tp(rs.getString("ten_tinh_tp"));
                    item.setHieu_suat_su_dung(rs.getString("hieu_suat_su_dung"));
                    item.setSo_card_pon_lap_dat(rs.getString("so_card_pon_lap_dat"));
                    item.setSo_card_pon_mp_rong_td(rs.getString("so_card_pon_mp_rong_td"));
                    item.setSo_cong_pon_toi_da(rs.getString("so_cong_pon_toi_da"));
                    item.setSo_olt_lap_dat(rs.getString("so_olt_lap_dat"));
                    item.setSo_ont_co_the_lap_dat(rs.getString("so_ont_co_the_lap_dat"));
                    item.setSo_ont_dang_hoat_dong(rs.getString("so_ont_dang_hoat_dong"));
                    item.setSo_pon_da_lap_dat(rs.getString("so_pon_da_lap_dat"));
                    item.setSo_pon_da_su_dung(rs.getString("so_pon_da_su_dung"));
                    item.setDonviId(rs.getString("donviId"));
                    item.setTenDonvi(rs.getString("tenDonvi"));
                    item.setDiachi(rs.getString("diachi"));
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
    
    public static List<L2SWPortTinhReportBO> reportL2swPortTinh(String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<L2SWPortTinhReportBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_l2sw_port_tinh(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                L2SWPortTinhReportBO item = new L2SWPortTinhReportBO();
                    item.setTinhtp_id(rs.getString("tinhtp_id"));
                    item.setTen_tinh_tp(rs.getString("ten_tinh_tp"));
                    item.setTong_tb(rs.getString("tong_tb"));
                    item.setPortL2SWConnect(rs.getString("portL2SWConnect"));
                    item.setPortL2SWUsed(rs.getString("portL2SWUsed"));
                    item.setPerfomanceUsed(rs.getString("perfomanceUsed"));
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
    
    public static List<L2SWPortDeviceReportBO> reportL2swPortDevice(String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<L2SWPortDeviceReportBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_l2sw_port_device(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                L2SWPortDeviceReportBO item = new L2SWPortDeviceReportBO();
                    item.setTinhtp_id(rs.getString("tinhtp_id"));
                    item.setTen_tinh_tp(rs.getString("ten_tinh_tp"));
                    item.setTong_tb(rs.getString("tong_tb"));
                    item.setPortL2SWConnect(rs.getString("portL2SWConnect"));
                    item.setPortL2SWUsed(rs.getString("portL2SWUsed"));
                    item.setPerfomanceUsed(rs.getString("perfomanceUsed"));
                    item.setDonviId(rs.getString("donviId"));
                    item.setTenDonvi(rs.getString("tenDonvi"));
                    item.setDiachi(rs.getString("diachi"));
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
    
    public static List<DslamPortTinhReportBO> reportDsLamPortTinh(String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<DslamPortTinhReportBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_dslam_port_tinh(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                DslamPortTinhReportBO item = new DslamPortTinhReportBO();
                    item.setTinhtp_id(rs.getString("tinhtp_id"));
                    item.setTen_tinh_tp(rs.getString("ten_tinh_tp"));
                    item.setTong_tb(rs.getString("tong_tb"));
                    item.setPortDslamConnect(rs.getString("portDslamConnect"));
                    item.setPortDslamUsed(rs.getString("portDslamUsed"));
                    item.setPerfomanceUsed(rs.getString("perfomanceUsed"));
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
    
    public static List<DslamPortDeviceReportBO> reportDsLamPortDevice(String tinhTpId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<DslamPortDeviceReportBO> result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.fn_report_dslam_port_device(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                DslamPortDeviceReportBO item = new DslamPortDeviceReportBO();
                    item.setTinhtp_id(rs.getString("tinhtp_id"));
                    item.setTen_tinh_tp(rs.getString("ten_tinh_tp"));
                    item.setTong_tb(rs.getString("tong_tb"));
                    item.setPortDslamConnect(rs.getString("portDslamConnect"));
                    item.setPortDslamUsed(rs.getString("portDslamUsed"));
                    item.setPerfomanceUsed(rs.getString("perfomanceUsed"));
                    item.setDonviId(rs.getString("donviId"));
                    item.setTenDonvi(rs.getString("tenDonvi"));
                    item.setDiachi(rs.getString("diachi"));
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
