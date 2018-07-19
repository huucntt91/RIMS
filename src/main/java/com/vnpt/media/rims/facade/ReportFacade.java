/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.BcTongHopBO;
import com.vnpt.media.rims.bean.Cell4GConfig;
import com.vnpt.media.rims.bean.ConfEventDefine;
import com.vnpt.media.rims.bean.ConfObjectTypeBO;
import com.vnpt.media.rims.bean.Detail2gBO;
import com.vnpt.media.rims.bean.Detail3gBO;
import com.vnpt.media.rims.bean.EventReportBO;
import com.vnpt.media.rims.bean.FilterReportBO;
import com.vnpt.media.rims.bean.ReportBadCellWeekBO;
import com.vnpt.media.rims.bean.ReportPmBO;
import com.vnpt.media.rims.bean.ReportTrafficTinhBO;
import com.vnpt.media.rims.bean.ReportTrafficTramBO;
import com.vnpt.media.rims.bean.ReportTramChuaDkCSHTBO;
import com.vnpt.media.rims.formbean.BtsNodeB;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.dao.IReport;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import com.vnpt.media.rims.formbean.FilterForm;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Cyano
 */
public class ReportFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    private Logger logger = LogManager.getLogger(ReportFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public ReportFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public ReportFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<BtsNodeB> findBts(String vendor_id) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.find_bts(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, vendor_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                BtsNodeB record = new BtsNodeB();
                record.setType1("2G");
                record.setType2("2G");
                record.setBscRnc(rs.getString("ten_bsc_rnc"));
                record.setBtsNodeB(rs.getString("ten_bts"));
                record.setSiteType(rs.getString("ten_bang_tan"));
                record.setVendor(rs.getString("ten_thiet_bi"));
                record.setPcCode(rs.getString("ma_tinh_tp"));
                record.setVnp(rs.getString("khu_vuc"));
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return null;
    }

    public List<BtsNodeB> findNodeB(String vendor_id) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.find_nodeb(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, vendor_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                BtsNodeB record = new BtsNodeB();
                record.setType1("3G");
                record.setType2("3G");
                record.setBscRnc(rs.getString("ten_bsc_rnc"));
                record.setBtsNodeB(rs.getString("ten_nodeb"));
                record.setSiteType(rs.getString("ten_bang_tan"));
                record.setVendor(rs.getString("ten_thiet_bi"));
                record.setPcCode(rs.getString("ma_tinh_tp"));
                record.setVnp(rs.getString("khu_vuc"));
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return null;
    }

    public List<Cell2G> findCell2G(String vendor_id) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.findcell2g(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, vendor_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cell2G record = new Cell2G();
                record.setmBsc(rs.getString("ten_bsc_rnc"));
                record.setCellName(rs.getString("ten_cell"));
                record.setVendor(rs.getString("ten_thiet_bi"));
                record.setFreqBand(rs.getString("ten_bang_tan"));
                record.setLac(rs.getLong("lac"));
                record.setCi(rs.getLong("ci"));
                record.setBtsName(rs.getString("ten_bts"));
                record.setBsic(rs.getString("bsic"));
                record.setBcch(rs.getString("bcch"));
                record.setFrequency(rs.getString("tch"));
                record.setConfig(rs.getString("trx_config"));
                record.setType1("2G");
                record.setType2("2G");
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return null;
    }

    public List<Cell3G> findCell3G(String vendor_id) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Cell3G> ar = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.findcell3g(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, vendor_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cell3G record = new Cell3G();

                record.setVendor(rs.getString("ten_thiet_bi"));
                record.setType1("3G");
                record.setType2("3G");
                record.setmBsc(rs.getString("ten_bsc_rnc"));
                record.setCellName(rs.getString("ten_cell"));
                record.setCellType(rs.getString("ten_bang_tan"));
                record.setLac(rs.getLong("lac"));
                record.setCi(rs.getLong("ci"));
                record.setNodeBname(rs.getString("ten_nodeb"));
                record.setDlpsc(rs.getString("dl_psc"));
                record.setFreq(rs.getString("frequency"));
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return null;
    }

    public List<Cell4GConfig> findCell4G(String vendor_id) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Cell4GConfig> ar = null;
        try {
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.findcell4g(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, vendor_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cell4GConfig record = new Cell4GConfig();

                record.setVendor(rs.getString("ten_thiet_bi"));
                record.setEnodeb_name(rs.getString("ten_enodeb"));
                record.setCell_name(rs.getString("ten_cell"));
                record.setTac(rs.getString("tac"));
                record.setPhyCellId(rs.getString("pci"));
                record.setLcrId(rs.getString("lcrid"));
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }

    public List<FilterReportBO> findFilterReport(int objectType) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IReport iReport = factory.getReportDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.findFilterReport(objectType);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int findDataType(String type, String column) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IReport iReport = factory.getReportDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.findDataType(type, column);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<?> cell2GReport(String type, FilterForm filterForm, String startRow, String endRow) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IReport iReport = factory.getReportDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            
            if (type.equals("5") || type.equals("6")) {
                return iReport.cell2GReport(Integer.valueOf(type), filterForm, startRow, endRow);
            } else if (type.equals("2")) {
                return iReport.btsReport(Integer.valueOf(type), filterForm, startRow, endRow);
            } else {
                return iReport.nodeBReport(Integer.valueOf(type), filterForm, startRow, endRow);
            }
//            return null;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalCell2GReport(String type, FilterForm filterForm) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IReport iReport = factory.getReportDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            
            if (type.equals("5") || type.equals("6")) {
                return iReport.getTotalCell2GReport(Integer.valueOf(type), filterForm);
            } else if (type.equals("2")) {
                return iReport.getTotalBtsReport(Integer.valueOf(type), filterForm);
            } else {
                return iReport.getTotalNodeBReport(Integer.valueOf(type), filterForm);
            }
            //return 1;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<BcTongHopBO> findConfigReport() throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IReport iReport = factory.getReportDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.findConfigReport();

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<Detail2gBO> findDetail2G() throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IReport iReport = factory.getReportDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.findDetail2G();

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<Detail3gBO> findDetail3G() throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IReport iReport = factory.getReportDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.findDetail3G();

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    //event report start

    public List<ConfEventDefine> findEvent(String objectTypeId) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ConfEventDefine> ar = null;
        try {
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.find_event(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, objectTypeId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ConfEventDefine record = new ConfEventDefine();
                record.setEventDefineId(rs.getInt("event_define_id"));
                record.setEventDefineCode(rs.getString("event_define_code"));
                record.setEventDefineName(rs.getString("event_define_name"));
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }

    public List<ConfObjectTypeBO> findObject() throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ConfObjectTypeBO> ar = null;
        try {
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.find_object_type(); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ConfObjectTypeBO record = new ConfObjectTypeBO();
                record.setObjectTypeId(rs.getInt("object_type_id"));
                record.setObjectTypeCode(rs.getString("object_type_code"));
                record.setObjectTypeName(rs.getString("object_type_name"));
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }

    public static List<EventReportBO> searchEvent(Integer startRow, Integer endRow, String khuvucId,
            String tinhTpId, String eventDefineId, String objectTypeId, String startTime, String endTime) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EventReportBO> ar = null;
        try {
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.search_data_event(?,?,?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, startRow == null ? "0" : startRow.toString());
            cstmt.setString(3, endRow == null ? "0" : endRow.toString());
            cstmt.setString(4, objectTypeId);
            cstmt.setString(5, eventDefineId);
            cstmt.setString(6, tinhTpId);
            cstmt.setString(7, khuvucId);
            cstmt.setString(8, startTime);
            cstmt.setString(9, endTime);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                EventReportBO record = new EventReportBO();
                record.setObjectCode(rs.getString("object_code"));
                record.setEventName(rs.getString("event_define_name"));
                record.setEventDescription(rs.getString("event_description"));
                record.setEventValue(rs.getString("trigger_value"));
                record.setStartTime(rs.getString("sta_datetime"));
                record.setEndTime(rs.getString("end_datetime"));
                record.setStatus(rs.getString("status"));
                record.setHandlingStatus(rs.getString("handling_status"));
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }

    public static Integer countEvent(String khuvucId,
            String tinhTpId, String eventDefineId, String objectTypeId, String startTime, String endTime) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.count_data_event(?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, objectTypeId);
            cs.setString(3, eventDefineId);
            cs.setString(4, tinhTpId);
            cs.setString(5, khuvucId);
            cs.setString(6, startTime);
            cs.setString(7, endTime);
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

    public static List<EventReportBO> eventReport(String khuvucId,
            String tinhTpId, String eventDefineId, String objectTypeId, String startTime, String endTime) throws ServiceException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EventReportBO> ar = null;
        try {
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report.report_data_event(?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, objectTypeId);
            cs.setString(3, eventDefineId);
            cs.setString(4, tinhTpId);
            cs.setString(5, khuvucId);
            cs.setString(6, startTime);
            cs.setString(7, endTime);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                EventReportBO record = new EventReportBO();
                record.setObjectCode(rs.getString("object_code"));
                record.setEventName(rs.getString("event_define_name"));
                record.setEventDescription(rs.getString("event_description"));
                record.setEventValue(rs.getString("trigger_value"));
                record.setStartTime(rs.getString("sta_datetime"));
                record.setEndTime(rs.getString("end_datetime"));
                record.setStatus(rs.getString("status"));
                record.setHandlingStatus(rs.getString("handling_status"));
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                } catch (SQLException ex) {
                }
            }
        }
        return ar;
    }

    //envet report end
    
//    trunglk_start_trafficTram
    public static Integer countTrafficTram(String techType, 
                                                String timeType, 
                                                String khuvucId,
                                                String tinhTpId, 
                                                String startTime, 
                                                String endTime,
                                                String fromDateWeek,
                                                String toDateWeek,
                                                String fromDateMonth,
                                                String toDateMonth) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            String sql = "";
            conn = EnvManager.getDbConnection(RIMS_DS);
            if(techType.equals("2") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.count_traffic_tram_2g_ngay(?,?,?,?); end;";
            }else if((techType.equals("2") && timeType.equals("2")) || (techType.equals("2") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.count_traffic_tram_2g(?,?,?,?); end;";
            }
            if(techType.equals("3") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.count_traffic_tram_3g_ngay(?,?,?,?); end;";
            }else if((techType.equals("3") && timeType.equals("2")) || (techType.equals("3") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.count_traffic_tram_3g(?,?,?,?); end;";
            }
            if(techType.equals("4") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.count_traffic_tram_4g_ngay(?,?,?,?); end;";
            }else if((techType.equals("4") && timeType.equals("2")) || (techType.equals("4") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.count_traffic_tram_4g(?,?,?,?); end;";
            }
//            String sql = 
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, khuvucId);
            cs.setString(3, tinhTpId);
            if(timeType.equals("1")){
                cs.setString(4, startTime);
                cs.setString(5, endTime);
            }    
            if(timeType.equals("2")){
                cs.setString(4, fromDateWeek);
                cs.setString(5, toDateWeek);
            } 
            if(timeType.equals("3")){
                cs.setString(4, fromDateMonth);
                cs.setString(5, toDateMonth);
            } 
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
    
    public static List<ReportTrafficTramBO> searchTrafficTram(Integer startRow, 
                                                                Integer endRow, 
                                                                String techType, 
                                                                String timeType, 
                                                                String khuvucId,
                                                                String tinhTpId, 
                                                                String startTime, 
                                                                String endTime,
                                                                String fromDateWeek,
                                                                String toDateWeek,
                                                                String fromDateMonth,
                                                                String toDateMonth) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportTrafficTramBO> ar = null;
        try {
            String sql = "";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);

            if(techType.equals("2") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.search_traffic_tram_2g_ngay(?,?,?,?,?,?); end;";
            }else if((techType.equals("2") && timeType.equals("2")) || (techType.equals("2") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.search_traffic_tram_2g(?,?,?,?,?,?); end;";
            }
            if(techType.equals("3") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.search_traffic_tram_3g_ngay(?,?,?,?,?,?); end;";
            }else if((techType.equals("3") && timeType.equals("2")) || (techType.equals("3") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.search_traffic_tram_3g(?,?,?,?,?,?); end;";
            }
            if(techType.equals("4") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.search_traffic_tram_4g_ngay(?,?,?,?,?,?); end;";
            }else if((techType.equals("4") && timeType.equals("2")) || (techType.equals("4") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.search_traffic_tram_4g(?,?,?,?,?,?); end;";
            }
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, startRow == null ? "0" : startRow.toString());
            cstmt.setString(3, endRow == null ? "0" : endRow.toString());
            cstmt.setString(4, khuvucId);
            cstmt.setString(5, tinhTpId);
            if(timeType.equals("1")){
                cstmt.setString(6, startTime);
                cstmt.setString(7, endTime);
            }    
            if(timeType.equals("2")){
                cstmt.setString(6, fromDateWeek);
                cstmt.setString(7, toDateWeek);
            } 
            if(timeType.equals("3")){
                cstmt.setString(6, fromDateMonth);
                cstmt.setString(7, toDateMonth);
            } 
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportTrafficTramBO record = new ReportTrafficTramBO();
//                Hãng	Mã Tỉnh	Tỉnh	Thời gian	BSC	BTS	Traffic 2G CS [Erl]	Traffic 2G PS [MB]
                  record.setProvinceCode(rs.getString("ma_tinh_tp"));
                  record.setProvinceName(rs.getString("ten_tinh_tp"));
                  record.setVendor(rs.getString("vendor"));
                  if(timeType.equals("1")){
                      record.setDate(rs.getString("sta_datetime"));
                  }else{
                      record.setDate(rs.getString("thoigian"));
                  }
                  
                  if(techType.equals("2")){
                  record.setBscRncName(rs.getString("bscname"));
                  record.setTenNode(rs.getString("btsname"));
                  record.setTraffic2g3gCs(rs.getString("traffic2gcs"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic2gps"));
                  record.setNhomTram(rs.getString("nhom_tram")); 
//                  record.setTenQuanLy(rs.getString("ten_bts"));
                  }
                  if(techType.equals("3")){
                  record.setBscRncName(rs.getString("rncname"));
                  record.setTenNode(rs.getString("nodebname"));
                  record.setTraffic2g3gCs(rs.getString("traffic3gcs"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic3gps"));
                  record.setNhomTram(rs.getString("nhom_tram"));
//                  record.setTenQuanLy(rs.getString("ten_nodeb"));
                  }
                  if(techType.equals("4")){
                  record.setTenNode(rs.getString("enodebname"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic4gps"));
//                  record.setTenQuanLy(rs.getString("ten_enodeb"));
                  }
//                  record.setTenCsht(rs.getString("building_name"));
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return ar;
    }
    
    public static List<ReportTrafficTramBO> reportTrafficTram(String techType, 
                                                                String timeType, 
                                                                String khuvucId,
                                                                String tinhTpId, 
                                                                String startTime, 
                                                                String endTime,
                                                                String fromDateWeek,
                                                                String toDateWeek,
                                                                String fromDateMonth,
                                                                String toDateMonth) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportTrafficTramBO> ar = null;
        try {
            String sql = "";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            
            if(techType.equals("2") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.report_traffic_tram_2g_ngay(?,?,?,?); end;";
            }else if((techType.equals("2") && timeType.equals("2")) || (techType.equals("2") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.report_traffic_tram_2g(?,?,?,?); end;";
            }
            if(techType.equals("3") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.report_traffic_tram_3g_ngay(?,?,?,?); end;";
            }else if((techType.equals("3") && timeType.equals("2")) || (techType.equals("3") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.report_traffic_tram_3g(?,?,?,?); end;";
            }
            if(techType.equals("4") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.report_traffic_tram_4g_ngay(?,?,?,?); end;";
            }else if((techType.equals("4") && timeType.equals("2")) || (techType.equals("4") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.report_traffic_tram_4g(?,?,?,?); end;";
            }
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, khuvucId);
            cstmt.setString(3, tinhTpId);
            if(timeType.equals("1")){
                cstmt.setString(4, startTime);
                cstmt.setString(5, endTime);
            }    
            if(timeType.equals("2")){
                cstmt.setString(4, fromDateWeek);
                cstmt.setString(5, toDateWeek);
            } 
            if(timeType.equals("3")){
                cstmt.setString(4, fromDateMonth);
                cstmt.setString(5, toDateMonth);
            } 
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportTrafficTramBO record = new ReportTrafficTramBO();
                  record.setProvinceCode(rs.getString("ma_tinh_tp"));
                  record.setProvinceName(rs.getString("ten_tinh_tp"));
                  record.setVendor(rs.getString("vendor"));
                  if(timeType.equals("1")){
                      record.setDate(rs.getString("sta_datetime"));
                  }else{
                      record.setDate(rs.getString("thoigian"));
                  }
                  if(techType.equals("2")){
                  record.setBscRncName(rs.getString("bscname"));
                  record.setTenNode(rs.getString("btsname"));
                  record.setTraffic2g3gCs(rs.getString("traffic2gcs"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic2gps"));
                  record.setNhomTram(rs.getString("nhom_tram"));
//                  record.setTenQuanLy(rs.getString("ten_bts"));
                  }
                  if(techType.equals("3")){
                  record.setBscRncName(rs.getString("rncname"));
                  record.setTenNode(rs.getString("nodebname"));
                  record.setTraffic2g3gCs(rs.getString("traffic3gcs"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic3gps"));
                  record.setNhomTram(rs.getString("nhom_tram"));
//                  record.setTenQuanLy(rs.getString("ten_nodeb"));
                  }
                  if(techType.equals("4")){
                  record.setTenNode(rs.getString("enodebname"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic4gps"));
//                  record.setTenQuanLy(rs.getString("ten_enodeb"));
                  }
//                  record.setTenCsht(rs.getString("building_name"));
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
//    trunglk_end_trafficTram
    
//    trunglk_start_trafficTinh
    public static Integer countTrafficTinh(String techType, 
                                                String timeType,
                                                String khuvucId,
                                                String tinhTpId, 
                                                String startTime, 
                                                String endTime,
                                                String fromDateWeek,
                                                String toDateWeek,
                                                String fromDateMonth,
                                                String toDateMonth) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            String sql = "";
            conn = EnvManager.getDbConnection(RIMS_DS);

            if(techType.equals("2") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.count_traffic_tinh_2g_ngay(?,?,?,?); end;";
            }else if((techType.equals("2") && timeType.equals("2")) || (techType.equals("2") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.count_traffic_tinh_2g(?,?,?,?); end;";
            }
            if(techType.equals("3") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.count_traffic_tinh_3g_ngay(?,?,?,?); end;";
            }else if((techType.equals("3") && timeType.equals("2")) || (techType.equals("3") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.count_traffic_tinh_3g(?,?,?,?); end;";
            }
            if(techType.equals("4") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.count_traffic_tinh_4g_ngay(?,?,?,?); end;";
            }else if((techType.equals("4") && timeType.equals("2")) || (techType.equals("4") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.count_traffic_tinh_4g(?,?,?,?); end;";
            }
//            String sql = 
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, khuvucId);
            cs.setString(3, tinhTpId);
            if(timeType.equals("1")){
                cs.setString(4, startTime);
                cs.setString(5, endTime);
            }    
            if(timeType.equals("2")){
                cs.setString(4, fromDateWeek);
                cs.setString(5, toDateWeek);
            } 
            if(timeType.equals("3")){
                cs.setString(4, fromDateMonth);
                cs.setString(5, toDateMonth);
            } 
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
    
    public static List<ReportTrafficTinhBO> searchTrafficTinh(Integer startRow, 
                                                                Integer endRow, 
                                                                String techType, 
                                                                String timeType, 
                                                                String khuvucId,
                                                                String tinhTpId, 
                                                                String startTime, 
                                                                String endTime,
                                                                String fromDateWeek,
                                                                String toDateWeek,
                                                                String fromDateMonth,
                                                                String toDateMonth) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportTrafficTinhBO> ar = null;
        try {
            String sql = "";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            
            if(techType.equals("2") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.search_traffic_tinh_2g_ngay(?,?,?,?,?,?); end;";
            }else if((techType.equals("2") && timeType.equals("2")) || (techType.equals("2") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.search_traffic_tinh_2g(?,?,?,?,?,?); end;";
            }
            if(techType.equals("3") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.search_traffic_tinh_3g_ngay(?,?,?,?,?,?); end;";
            }else if((techType.equals("3") && timeType.equals("2")) || (techType.equals("3") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.search_traffic_tinh_3g(?,?,?,?,?,?); end;";
            }
            if(techType.equals("4") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.search_traffic_tinh_4g_ngay(?,?,?,?,?,?); end;";
            }else if((techType.equals("4") && timeType.equals("2")) || (techType.equals("4") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.search_traffic_tinh_4g(?,?,?,?,?,?); end;";
            }
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, startRow == null ? "0" : startRow.toString());
            cstmt.setString(3, endRow == null ? "0" : endRow.toString());
            cstmt.setString(4, khuvucId);
            cstmt.setString(5, tinhTpId);
            if(timeType.equals("1")){
                cstmt.setString(6, startTime);
                cstmt.setString(7, endTime);
            }    
            if(timeType.equals("2")){
                cstmt.setString(6, fromDateWeek);
                cstmt.setString(7, toDateWeek);
            } 
            if(timeType.equals("3")){
                cstmt.setString(6, fromDateMonth);
                cstmt.setString(7, toDateMonth);
            } 
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportTrafficTinhBO record = new ReportTrafficTinhBO();
//                Hãng	Mã Tỉnh	Tỉnh	Thời gian	BSC	BTS	Traffic 2G CS [Erl]	Traffic 2G PS [MB]
                  record.setProvinceCode(rs.getString("ma_tinh_tp"));
                  record.setProvinceName(rs.getString("ten_tinh_tp"));
                  if(timeType.equals("1")){
                      record.setDate(rs.getString("sta_datetime"));
                  }else{
                      record.setDate(rs.getString("thoigian"));
                  }
                  if(techType.equals("2")){
                  record.setTraffic2g3gCs(rs.getString("traffic2gcs"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic2gps"));
                  }
                  if(techType.equals("3")){
                  record.setTraffic2g3gCs(rs.getString("traffic3gcs"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic3gps"));
                  }
                  if(techType.equals("4")){
                  record.setTraffic2g3g4gPs(rs.getString("traffic4gps"));
                  }
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
    public static List<ReportTrafficTinhBO> reportTrafficTinh(String techType, 
                                                                String timeType, 
                                                                String khuvucId,
                                                                String tinhTpId, 
                                                                String startTime, 
                                                                String endTime,
                                                                String fromDateWeek,
                                                                String toDateWeek,
                                                                String fromDateMonth,
                                                                String toDateMonth) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportTrafficTinhBO> ar = null;
        try {
            String sql = "";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            
            if(techType.equals("2") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.report_traffic_tinh_2g_ngay(?,?,?,?); end;";
            }else if((techType.equals("2") && timeType.equals("2")) || (techType.equals("2") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.report_traffic_tinh_2g(?,?,?,?); end;";
            }
            if(techType.equals("3") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.report_traffic_tinh_3g_ngay(?,?,?,?); end;";
            }else if((techType.equals("3") && timeType.equals("2")) || (techType.equals("3") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.report_traffic_tinh_3g(?,?,?,?); end;";
            }
            if(techType.equals("4") && timeType.equals("1")){
                sql = "begin ?:=pkg_report.report_traffic_tinh_4g_ngay(?,?,?,?); end;";
            }else if((techType.equals("4") && timeType.equals("2")) || (techType.equals("4") && timeType.equals("3"))){
                sql = "begin ?:=pkg_report.report_traffic_tinh_4g(?,?,?,?); end;";
            }
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, khuvucId);
            cstmt.setString(3, tinhTpId);
            if(timeType.equals("1")){
                cstmt.setString(4, startTime);
                cstmt.setString(5, endTime);
            }    
            if(timeType.equals("2")){
                cstmt.setString(4, fromDateWeek);
                cstmt.setString(5, toDateWeek);
            } 
            if(timeType.equals("3")){
                cstmt.setString(4, fromDateMonth);
                cstmt.setString(5, toDateMonth);
            } 
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportTrafficTinhBO record = new ReportTrafficTinhBO();
//                Hãng	Mã Tỉnh	Tỉnh	Thời gian	BSC	BTS	Traffic 2G CS [Erl]	Traffic 2G PS [MB]
                  record.setProvinceCode(rs.getString("ma_tinh_tp"));
                  record.setProvinceName(rs.getString("ten_tinh_tp"));
                  if(timeType.equals("1")){
                      record.setDate(rs.getString("sta_datetime"));
                  }else{
                      record.setDate(rs.getString("thoigian"));
                  }
                  if(techType.equals("2")){
                  record.setTraffic2g3gCs(rs.getString("traffic2gcs"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic2gps"));
                  }
                  if(techType.equals("3")){
                  record.setTraffic2g3gCs(rs.getString("traffic3gcs"));
                  record.setTraffic2g3g4gPs(rs.getString("traffic3gps"));
                  }
                  if(techType.equals("4")){
                  record.setTraffic2g3g4gPs(rs.getString("traffic4gps"));
                  }
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    
    }
    
    public static Integer countTramChuaDkCsht(String neTypeId, 
                                           String listKhuVuc) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {

            conn = EnvManager.getDbConnection(RIMS_DS);

              String  sql = "begin ?:=pkg_report.count_report_tram_no_csht(?,?); end;";

//            String sql = 
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, neTypeId);
            cs.setString(3, listKhuVuc);
                
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
    
    
    public static List<ReportTramChuaDkCSHTBO> searchTramChuaDkCsht(Integer startRow, 
                                                                Integer endRow, 
                                                                String techType, 
                                                                String listKhuVuc) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportTramChuaDkCSHTBO> ar = null;
        try {
            String sql = "";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            
            if(techType.equals("2")){
                sql = "begin ?:=pkg_report.search_report_tram2G_no_csht(?,?,?,?); end;";
            }
            if(techType.equals("3")){
                sql = "begin ?:=pkg_report.search_report_tram3G_no_csht(?,?,?,?); end;";
            }
            if(techType.equals("8")){
                sql = "begin ?:=pkg_report.search_report_tram4G_no_csht(?,?,?,?); end;";
            }
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, startRow == null ? "0" : startRow.toString());
            cstmt.setString(3, endRow == null ? "0" : endRow.toString());
            cstmt.setString(4, techType);
            cstmt.setString(5, listKhuVuc);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportTramChuaDkCSHTBO record = new ReportTramChuaDkCSHTBO();
                  record.setMaNode(rs.getString("MA_NODE"));
                  record.setTenNode(rs.getString("TEN_BTS"));
                  record.setLoaiTram(rs.getString("TEN_LOAI_TRAM"));
                  record.setProvinceName(rs.getString("TEN_TINH_TP"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
    public static List<ReportTramChuaDkCSHTBO> reportTramChuaDkCsht(String techType, 
                                                                String lstKhuVuc) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportTramChuaDkCSHTBO> ar = null;
        try {
            String sql = "";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            
            if(techType.equals("2")){
                sql = "begin ?:=pkg_report.report_tram2G_no_csht(?,?); end;";
            }
            if(techType.equals("3")){
                sql = "begin ?:=pkg_report.report_tram3G_no_csht(?,?); end;";
            }
            if(techType.equals("8")){
                sql = "begin ?:=pkg_report.report_tram4G_no_csht(?,?); end;";
            }
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, techType);
            cstmt.setString(3, lstKhuVuc);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportTramChuaDkCSHTBO record = new ReportTramChuaDkCSHTBO();
                  record.setMaNode(rs.getString("MA_NODE"));
                  record.setTenNode(rs.getString("TEN_BTS"));
                  record.setLoaiTram(rs.getString("TEN_LOAI_TRAM"));
                  record.setProvinceName(rs.getString("TEN_TINH_TP"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
//    trunglk_end_trafficTinh
    
//    report Bad cell theo tuan start
    public static List<ReportBadCellWeekBO> report_clk_2g_tonghop(String tinhTp, 
                                                                String startTime,
                                                                String endTime) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportBadCellWeekBO> ar = null;
        try {
            String sql = "begin ?:=pkg_report.report_clk_2g_tonghop(?,?,?); end;";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);           
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhTp);
            cstmt.setString(3, startTime);
            cstmt.setString(4, endTime);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportBadCellWeekBO record = new ReportBadCellWeekBO();
                  record.setThoiGian(rs.getString("sta_time"));
                  record.setKhuVuc(rs.getString("net"));
                  record.setTenTinh(rs.getString("tinh"));
                  record.setTongSoCell(rs.getString("cell_total"));
                  record.setCssr(rs.getString("cssr"));
                  record.setDcr(rs.getString("dcr"));
                  record.setHosr(rs.getString("hosr"));
                  record.setSoBadCell(rs.getString("sobadcell"));
                  record.setPhanTramBadCell(rs.getString("percent"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
    public static List<ReportBadCellWeekBO> report_clk_2g_detail(String tinhTp, 
                                                                String startTime,
                                                                String endTime) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportBadCellWeekBO> ar = null;
        try {
            String sql = "begin ?:=pkg_report.report_clk_2g_detail(?,?,?); end;";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);           
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhTp);
            cstmt.setString(3, startTime);
            cstmt.setString(4, endTime);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportBadCellWeekBO record = new ReportBadCellWeekBO();
                  record.setThoiGian(rs.getString("sta_time"));
                  record.setKhuVuc(rs.getString("net"));
                  record.setTenTinh(rs.getString("tinh"));
                  record.setVendor(rs.getString("vendor"));
                  record.setBscRncName(rs.getString("bsc_name"));
                  record.setBtsNodebBName(rs.getString("bts_name"));
                  record.setCellName(rs.getString("cell_name"));
                  record.setLac(rs.getString("lac"));
                  record.setCi(rs.getString("ci"));
                  record.setCssr(rs.getString("cssrv1"));
                  record.setDcr(rs.getString("dcr"));
                  record.setHosr(rs.getString("hosr"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
    public static List<ReportBadCellWeekBO> report_clk_3g_tonghop(String tinhTp, 
                                                                String startTime,
                                                                String endTime) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportBadCellWeekBO> ar = null;
        try {
            String sql = "begin ?:=pkg_report.report_clk_3g_tonghop(?,?,?); end;";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);           
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhTp);
            cstmt.setString(3, startTime);
            cstmt.setString(4, endTime);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportBadCellWeekBO record = new ReportBadCellWeekBO();
                  record.setThoiGian(rs.getString("datetime"));
                  record.setKhuVuc(rs.getString("net"));
                  record.setTenTinh(rs.getString("tinh"));
                  record.setTongSoCell(rs.getString("cell_total"));
                  record.setCsCssr(rs.getString("cssr"));
                  record.setCsDcr(rs.getString("dcr"));
                  record.setCsSoftHors(rs.getString("hosr"));
                  record.setSoBadCell(rs.getString("sobadcell"));
                  record.setPhanTramBadCell(rs.getString("percent"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
    public static List<ReportBadCellWeekBO> report_clk_3g_detail(String tinhTp, 
                                                                String startTime,
                                                                String endTime) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportBadCellWeekBO> ar = null;
        try {
            String sql = "begin ?:=pkg_report.report_clk_3g_detail(?,?,?); end;";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);           
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhTp);
            cstmt.setString(3, startTime);
            cstmt.setString(4, endTime);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportBadCellWeekBO record = new ReportBadCellWeekBO();
                  record.setThoiGian(rs.getString("sta_time"));
                  record.setKhuVuc(rs.getString("net"));
                  record.setTenTinh(rs.getString("tinh"));
                  record.setVendor(rs.getString("vendor"));
                  record.setBscRncName(rs.getString("bsc_name"));
                  record.setBtsNodebBName(rs.getString("bts_name"));
                  record.setCellName(rs.getString("cell_name"));
                  record.setLac(rs.getString("lac"));
                  record.setCi(rs.getString("ci"));
                  record.setCsCssr(rs.getString("cssrv1"));
                  record.setCsDcr(rs.getString("dcr"));
                  record.setCsSoftHors(rs.getString("HOSR"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
    public static List<ReportBadCellWeekBO> report_sgll_2g_50(String tinhTp, 
                                                                String startTime,
                                                                String endTime) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportBadCellWeekBO> ar = null;
        try {
            String sql = "begin ?:=pkg_report.report_sgll_2g_50(?,?,?); end;";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);           
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhTp);
            cstmt.setString(3, startTime);
            cstmt.setString(4, endTime);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportBadCellWeekBO record = new ReportBadCellWeekBO();
                  record.setThoiGian(rs.getString("PROCESS_DATE"));
                  record.setTenTinh(rs.getString("tinh"));
                  record.setVendor(rs.getString("hang"));
                  record.setBscRncName(rs.getString("RNCNAME"));
                  record.setBtsNodebBName(rs.getString("NODEBNAME"));
                  record.setCellName(rs.getString("CELLNAME"));
                  record.setNgayBatDauT1(rs.getString("NGAYBD1"));
                  record.setNgayKetThucT1(rs.getString("NGAYKT1"));
                  record.setNgayBatDauT2(rs.getString("NGAYBD2"));
                  record.setNgayKetThucT2(rs.getString("NGAYKT2"));
                  record.setCsTrafficT1(rs.getString("TRAFFICT1"));
                  record.setCsTrafficT2(rs.getString("TRAFFICT2"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
    public static List<ReportBadCellWeekBO> report_sgll_3g_50(String tinhTp, 
                                                                String startTime,
                                                                String endTime) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportBadCellWeekBO> ar = null;
        try {
            String sql = "begin ?:=pkg_report.report_sgll_3g_50(?,?,?); end;";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);           
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhTp);
            cstmt.setString(3, startTime);
            cstmt.setString(4, endTime);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportBadCellWeekBO record = new ReportBadCellWeekBO();
                  record.setThoiGian(rs.getString("PROCESS_DATE"));
                  record.setTenTinh(rs.getString("tinh"));
                  record.setVendor(rs.getString("hang"));
                  record.setBscRncName(rs.getString("RNCNAME"));
                  record.setBtsNodebBName(rs.getString("NODEBNAME"));
                  record.setCellName(rs.getString("CELLNAME"));
                  record.setNgayBatDauT1(rs.getString("NGAYBD1"));
                  record.setNgayKetThucT1(rs.getString("NGAYKT1"));
                  record.setNgayBatDauT2(rs.getString("NGAYBD2"));
                  record.setNgayKetThucT2(rs.getString("NGAYKT2"));
                  record.setCsTrafficT1(rs.getString("TRAFFICT1"));
                  record.setCsTrafficT2(rs.getString("TRAFFICT2"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
    public static List<ReportBadCellWeekBO> report_sgll_2g_20(String tinhTp, 
                                                                String startTime,
                                                                String endTime) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportBadCellWeekBO> ar = null;
        try {
            String sql = "begin ?:=pkg_report.report_sgll_2g_20(?,?,?); end;";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);           
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhTp);
            cstmt.setString(3, startTime);
            cstmt.setString(4, endTime);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportBadCellWeekBO record = new ReportBadCellWeekBO();
                  record.setThoiGian(rs.getString("PROCESS_DATE"));
                  record.setTenTinh(rs.getString("tinh"));
                  record.setVendor(rs.getString("hang"));
                  record.setBscRncName(rs.getString("RNCNAME"));
                  record.setBtsNodebBName(rs.getString("NODEBNAME"));
                  record.setCellName(rs.getString("CELLNAME"));
                  record.setNgayBatDauT1(rs.getString("NGAYBD1"));
                  record.setNgayKetThucT1(rs.getString("NGAYKT1"));
                  record.setNgayBatDauT2(rs.getString("NGAYBD2"));
                  record.setNgayKetThucT2(rs.getString("NGAYKT2"));
                  record.setNgayBatDauT3(rs.getString("NGAYBD3"));
                  record.setNgayKetThucT3(rs.getString("NGAYKT3"));
                  record.setCsTrafficT1(rs.getString("TRAFFICT1"));
                  record.setCsTrafficT2(rs.getString("TRAFFICT2"));
                  record.setCsTrafficT3(rs.getString("TRAFFICT3"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    
    public static List<ReportBadCellWeekBO> report_sgll_3g_20(String tinhTp, 
                                                                String startTime,
                                                                String endTime) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportBadCellWeekBO> ar = null;
        try {
            String sql = "begin ?:=pkg_report.report_sgll_3g_20(?,?,?); end;";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);           
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhTp);
            cstmt.setString(3, startTime);
            cstmt.setString(4, endTime);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ReportBadCellWeekBO record = new ReportBadCellWeekBO();
                  record.setThoiGian(rs.getString("PROCESS_DATE"));
                  record.setTenTinh(rs.getString("tinh"));
                  record.setVendor(rs.getString("hang"));
                  record.setBscRncName(rs.getString("RNCNAME"));
                  record.setBtsNodebBName(rs.getString("NODEBNAME"));
                  record.setCellName(rs.getString("CELLNAME"));
                  record.setNgayBatDauT1(rs.getString("NGAYBD1"));
                  record.setNgayKetThucT1(rs.getString("NGAYKT1"));
                  record.setNgayBatDauT2(rs.getString("NGAYBD2"));
                  record.setNgayKetThucT2(rs.getString("NGAYKT2"));
                  record.setNgayBatDauT3(rs.getString("NGAYBD3"));
                  record.setNgayKetThucT3(rs.getString("NGAYKT3"));
                  record.setCsTrafficT1(rs.getString("TRAFFICT1"));
                  record.setCsTrafficT2(rs.getString("TRAFFICT2"));
                  record.setCsTrafficT3(rs.getString("TRAFFICT3"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
//    report Bad cell theo tuan end
//    report KPI PM start
    public static Integer countKpiCell(String techType, 
                                                String timeType, 
                                                String listKpi, 
                                                String tinhTpId, 
                                                String fromDateHour, 
                                                String toDateHour, 
                                                String fromDate, 
                                                String toDate, 
                                                String fromDateWeek, 
                                                String toDateWeek, 
                                                String fromDateMonth, 
                                                String toDateMonth) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            String sql = "begin ?:=pkg_report_kpi.get_count_kpi_cell(?,?,?,?,?,?); end;";
            conn = EnvManager.getDbConnection(RIMS_DS);
//            String sql = 
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, techType);
            cs.setString(3, timeType);
            cs.setString(4, listKpi);
            cs.setString(5, tinhTpId);
            if(timeType.equals("1")){
                cs.setString(6, fromDateHour);
                cs.setString(7, toDateHour);
            }
            if(timeType.equals("2")){
                cs.setString(6, fromDate);
                cs.setString(7, toDate);
            }    
            if(timeType.equals("3")){
                cs.setString(6, fromDateWeek);
                cs.setString(7, toDateWeek);
            } 
            if(timeType.equals("4")){
                cs.setString(6, fromDateMonth);
                cs.setString(7, toDateMonth);
            } 
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
    
    public static List<ReportPmBO> reportPm(Integer startRow, 
                                                        Integer endRow, 
                                                        String techType, 
                                                        String timeType, 
                                                        String listKpi, 
                                                        String tinhTpId, 
                                                        String dbSelect,
                                                        String fromDateHour, 
                                                        String toDateHour, 
                                                        String fromDate, 
                                                        String toDate, 
                                                        String fromDateWeek, 
                                                        String toDateWeek, 
                                                        String fromDateMonth, 
                                                        String toDateMonth,
                                                        int[] recordsTotal) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<ReportPmBO> ar = null;
        try {
            String[] kpi = null;
            if(listKpi != null && !listKpi.isEmpty()){
                 kpi = listKpi.split(",");
                 
                 for (String retval: kpi) {
                    
                 }
            }
                    
            
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);

            String  sql = "begin ?:=pkg_report_kpi.get_kpi_cell(?,?,?,?,?,?,?,?,?,?); end;";

            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, startRow == null ? "0" : startRow.toString());
            cstmt.setString(3, endRow == null ? "0" : endRow.toString());
            cstmt.setString(4, techType);
            cstmt.setString(5, timeType);
            cstmt.setString(6, listKpi.equalsIgnoreCase("null") ? "" :listKpi);
            cstmt.setString(7, tinhTpId);
            if(timeType.equals("1")){
                cstmt.setString(8, fromDateHour);
                cstmt.setString(9, toDateHour);
            }    
            if(timeType.equals("2")){
                cstmt.setString(8, fromDate);
                cstmt.setString(9, toDate);
            } 
            if(timeType.equals("3")){
                cstmt.setString(8, fromDateWeek);
                cstmt.setString(9, toDateWeek);
            } 
            if(timeType.equals("4")){
                cstmt.setString(8, fromDateMonth);
                cstmt.setString(9, toDateMonth);
            } 
            cstmt.setString(10, dbSelect);
            cstmt.registerOutParameter(11, oracle.jdbc.OracleTypes.INTEGER);
            
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            recordsTotal[0] = cstmt.getInt(11);

//            VENDOR, NE_ID, CODE, NAME, VNP_CODE, PROVINCE_ID_PM_TOOL, TINHTP_ID, TEN_TINH_TP,
//                                MA_TINH_TP, NE_TYPE_ID, BTS_NODEB_ENODEB_NAME, BTS_NODEB_ENODEB_VNP_CODE,
//                                BTS_NODEB_ENODEB_CODE, BSC_RNC_VNP_CODE, BSC_RNC_CODE, BSC_RNC_NAME, KHU_VUC,
//                                TEN_QUAN_HUYEN, QUAN_HUYEN_CODE, STA_DATETIME
            while (rs.next()) {
                ReportPmBO record = new ReportPmBO();
                  record.setDate(rs.getString("STA_DATETIME"));
                  record.setMaNode(rs.getString("VNP_CODE"));
                  record.setTenNode(rs.getString("NAME"));
                  record.setBtsName(rs.getString("BTS_NODEB_ENODEB_NAME"));
                  record.setBscRncName(rs.getString("BSC_RNC_NAME"));
                  record.setKhuVuc(rs.getString("KHU_VUC"));
                  record.setProvinceName(rs.getString("TEN_TINH_TP"));
                  record.setQuanHuyen(rs.getString("TEN_QUAN_HUYEN"));
                    if(techType.equals("2")){
                        try{
                            record.setcALVOL(rs.getString("CALVOL"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSSRV1(rs.getString("CSSRV1"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSSRV2(rs.getString("CSSRV2"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setdCR(rs.getString("DCR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setdCRV2(rs.getString("DCRV2"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setdL_TBF_DROP_RATE(rs.getString("DL_TBF_DROP_RATE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethOSR(rs.getString("HOSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethOSRV2(rs.getString("HOSRV2"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpS_TBF_SR_2G(rs.getString("PS_TBF_SR_2G"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpS_TRAFFIC_2G(rs.getString("PS_TRAFFIC_2G"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpS_UL_TBF_SR_2G(rs.getString("PS_UL_TBF_SR_2G"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setsDCCHBLKR(rs.getString("SDCCHBLKR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.settCHBLR(rs.getString("TCHBLR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.settRAFFIC(rs.getString("TRAFFIC"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setuL_TBF_DROP_RATE(rs.getString("UL_TBF_DROP_RATE"));  
                        }catch(SQLException ex){
                        }
                    }
                    if(techType.equals("3")){
                        try{
                        record.setcALLVOLUME(rs.getString("CALLVOLUME"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSCONGES(rs.getString("CSCONGES"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSINTERFREQHOSR(rs.getString("CSINTERFREQHOSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSIRATHOSRWEIGHT(rs.getString("CSIRATHOSRWEIGHT"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSSR(rs.getString("CSSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSSRVIDEOPHONE(rs.getString("CSSRVIDEOPHONE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSVIDEODROPCALLRATE(rs.getString("CSVIDEODROPCALLRATE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSVIDEOTRAFFIC(rs.getString("CSVIDEOTRAFFIC"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSVOICECSSR(rs.getString("CSVOICECSSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSVOICEDROPCALLRATE(rs.getString("CSVOICEDROPCALLRATE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setdCR(rs.getString("DCR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setdLTRAFFICPS(rs.getString("DLTRAFFICPS"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethSDPATHROUGHPUT(rs.getString("HSDPATHROUGHPUT"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setiRATHOSR(rs.getString("IRATHOSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSCONGES(rs.getString("PSCONGES"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSCSSR(rs.getString("PSCSSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSDCR(rs.getString("PSDCR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSHSDPATPKBPS(rs.getString("PSHSDPATPKBPS"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSHSDPATRAFFICGB(rs.getString("PSHSDPATRAFFICGB"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSHSPACALLDROPRATE(rs.getString("PSHSPACALLDROPRATE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSHSPACSSR(rs.getString("PSHSPACSSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSHSPATRAFFICGB(rs.getString("PSHSPATRAFFICGB"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSHSUPATPKBPS(rs.getString("PSHSUPATPKBPS"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSHSUPATRAFFICGB(rs.getString("PSHSUPATRAFFICGB"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSIRATHOSR(rs.getString("PSIRATHOSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSR99CALLDROPRATE(rs.getString("PSR99CALLDROPRATE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSR99CALLSETUPSR(rs.getString("PSR99CALLSETUPSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSR99DLTRAFFICGB(rs.getString("PSR99DLTRAFFICGB"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSR99TRAFFICGB(rs.getString("PSR99TRAFFICGB"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSR99UPLINKTRAFFICGB(rs.getString("PSR99UPLINKTRAFFICGB"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setpSTRAFFIC(rs.getString("PSTRAFFIC"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setR99DLTHROUGHPUT(rs.getString("R99DLTHROUGHPUT"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setR99ULTHROUGHPUT(rs.getString("R99ULTHROUGHPUT"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setsOFTHOSR(rs.getString("SOFTHOSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setsOFTHOSRPS(rs.getString("SOFTHOSRPS"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.settRAFFIC(rs.getString("TRAFFIC"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.settRAFFICACTIVESETCS64(rs.getString("TRAFFICACTIVESETCS64"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setuLTRAFFICPS(rs.getString("ULTRAFFICPS"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setV2INTERFREQHOSRPS(rs.getString("V2INTERFREQHOSRPS"));
                        }catch(SQLException ex){
                        }
                    }
                    if(techType.equals("4")){
                        try{
                        record.setaVAILABLE(rs.getString("AVAILABLE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcELL_DL_AVG_THPUTs(rs.getString("CELL_DL_AVG_THPUTs"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcELL_DL_MAX_THPUT(rs.getString("CELL_DL_MAX_THPUT"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcELL_UL_AVG_THPUT(rs.getString("CELL_UL_AVG_THPUT"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcELL_UL_MAX_THPUT(rs.getString("CELL_UL_MAX_THPUT"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSFB_SSR(rs.getString("CSFB_SSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setcSSR(rs.getString("CSSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setdL_LATENCY(rs.getString("DL_LATENCY"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.seteRAB_SSRATE_ALL(rs.getString("ERAB_SSRATE_ALL"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethOSRX2(rs.getString("HOSRX2"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethOSR_IRAT_EXE(rs.getString("HOSR_IRAT_EXE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethOSR_IRAT_LTE_GSM(rs.getString("HOSR_IRAT_LTE_GSM"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethOSR_IRAT_LTE_WCDMA(rs.getString("HOSR_IRAT_LTE_WCDMA"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethOSR_IRAT_PRE(rs.getString("HOSR_IRAT_PRE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethOSR_IRAT_PRE_EXE(rs.getString("HOSR_IRAT_PRE_EXE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.sethOSR_S1(rs.getString("HOSR_S1"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setiNTER_FREQUENCY_HO(rs.getString("INTER_FREQUENCY_HO"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setiNTRA_ENODEB_HOSR(rs.getString("INTRA_ENODEB_HOSR"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setiNTRA_FREQUENCY_HO(rs.getString("INTRA_FREQUENCY_HO"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setrES_BLK_DL(rs.getString("RES_BLK_DL"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setrES_BLK_UL(rs.getString("RES_BLK_UL"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setrRC_SSRATE(rs.getString("RRC_SSRATE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setsERVICE_DROP_ALL(rs.getString("SERVICE_DROP_ALL"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.settRAFFIC(rs.getString("TRAFFIC"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.settRAFFIC_VOL_DL(rs.getString("TRAFFIC_VOL_DL"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.settRAFFIC_VOL_UL(rs.getString("TRAFFIC_VOL_UL"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setuL_LATENCY(rs.getString("UL_LATENCY"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setuNVAILABLE(rs.getString("UNVAILABLE"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setuSER_DL_AVG_THPUT(rs.getString("USER_DL_AVG_THPUT"));
                        }catch(SQLException ex){
                        }
                        try{
                        record.setuSER_UL_AVG_THPUT(rs.getString("USER_UL_AVG_THPUT"));
                        }catch(SQLException ex){
                        }
                    }
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return ar;
    }
    

//    report KPI PM end
    
}
