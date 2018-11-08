/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.EmailReportBO;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import com.vnpt.media.rims.formbean.Cell4G;
import com.vnpt.media.rims.job.AutoMailJob;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Cyano
 */
public class AutoMailFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(AutoMailFacade.class);
    public static List findCell2G() {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.duplicate_cell(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, "5");
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                Cell2G item = new Cell2G();
                item.setCellName(rs.getString("cell_name"));
                item.setLac(rs.getLong("lac"));
                item.setCi(rs.getLong("ci"));
                item.setFreqBand(rs.getString("frequency_band"));
                item.setBcch(rs.getString("bcch"));
                item.setBsic(rs.getString("bsic"));
                item.setTch(rs.getString("tch"));
                item.setTrxConfig(rs.getString("trx_config"));
                item.setBtsName(rs.getString("bts_nodeb_name"));
                item.setmBsc(rs.getString("bsc_rnc_name"));
                item.setFileName(rs.getString("filename"));
                item.setCheckDate(rs.getString("check_date"));
                item.setVendor(rs.getString("vendor"));
                item.setCellCode(rs.getString("cell_code"));
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

    public static List findCell3G() {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.duplicate_cell(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, "6");
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                Cell3G item = new Cell3G();
                item.setCellName(rs.getString("cell_name"));
                item.setLac(rs.getLong("lac"));
                item.setCi(rs.getLong("ci"));
                item.setFreqBand(rs.getString("frequency_band"));
                item.setDlpsc(rs.getString("dl_psc"));
                item.setFreq(rs.getString("frequency"));
                item.setCpichPower(rs.getString("cpich_power"));
                item.setMaxPower(rs.getString("max_power"));
                item.setTotalPower(rs.getString("total_power"));
                item.setmBsc(rs.getString("bsc_rnc_name"));
                item.setNodeBname(rs.getString("bts_nodeb_name"));
                item.setFileName(rs.getString("filename"));
                item.setCheckDate(rs.getString("check_date"));
                item.setVendor(rs.getString("vendor"));
                item.setCellCode(rs.getString("cell_code"));
                item.setRac(rs.getString("rac"));
                item.setDlUarfcn(rs.getString("dl_uarfcn"));
                item.setDcSupport(rs.getString("dc_support"));
                item.setOamIp(rs.getString("oam_ip"));
                item.setServiceIp(rs.getString("service_ip"));
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

    public static List findSmrsInventory() {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.pima_compare_inventory; end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                Cell2G item = new Cell2G();
                item.setLacStr(rs.getString("lac"));
                item.setCiStr(rs.getString("ci"));
                item.setCell_id_ocs(rs.getString("cell_id_ocs"));
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

    public static List inventoryRims(String ne_type_id) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.inventory_compare_rims(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, ne_type_id);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                if (ne_type_id.equalsIgnoreCase("5")) {
                    Cell2G item = new Cell2G();
                    item.setCellName(rs.getString("cell_name"));
                    item.setLac(rs.getLong("lac"));
                    item.setCi(rs.getLong("ci"));
                    item.setFreqBand(rs.getString("frequency_band"));
                    item.setBcch(rs.getString("bcch"));
                    item.setBsic(rs.getString("bsic"));
                    item.setTch(rs.getString("tch"));
                    item.setTrxConfig(rs.getString("trx_config"));
                    item.setBtsName(rs.getString("bts_nodeb_name"));
                    item.setmBsc(rs.getString("bsc_rnc_name"));
                    item.setFileName(rs.getString("filename"));
                    item.setCheckDate(rs.getString("check_date"));
                    item.setVendor(rs.getString("vendor"));
                    item.setCellCode(rs.getString("cell_code"));
                    item.setProvinceName(rs.getString("ten_tinh_tp"));
                    item.setAreaName(rs.getString("khu_vuc"));
                    result.add(item);
                } else if (ne_type_id.equalsIgnoreCase("6")) {
                    Cell3G item = new Cell3G();
                    item.setCellName(rs.getString("cell_name"));
                    item.setLac(rs.getLong("lac"));
                    item.setCi(rs.getLong("ci"));
                    item.setFreqBand(rs.getString("frequency_band"));
                    item.setDlpsc(rs.getString("dl_psc"));
                    item.setFreq(rs.getString("frequency"));
                    item.setCpichPower(rs.getString("cpich_power"));
                    item.setMaxPower(rs.getString("max_power"));
                    item.setTotalPower(rs.getString("total_power"));
                    item.setmBsc(rs.getString("bsc_rnc_name"));
                    item.setNodeBname(rs.getString("bts_nodeb_name"));
                    item.setFileName(rs.getString("filename"));
                    item.setCheckDate(rs.getString("check_date"));
                    item.setVendor(rs.getString("vendor"));
                    item.setCellCode(rs.getString("cell_code"));
                    item.setRac(rs.getString("rac"));
                    item.setDlUarfcn(rs.getString("dl_uarfcn"));
                    item.setDcSupport(rs.getString("dc_support"));
                    item.setOamIp(rs.getString("oam_ip"));
                    item.setServiceIp(rs.getString("service_ip"));
                    item.setProvinceName(rs.getString("ten_tinh_tp"));
                    item.setAreaName(rs.getString("khu_vuc"));
                    result.add(item);
                } else if (ne_type_id.equalsIgnoreCase("7")) {
                    Cell4G item = new Cell4G();
                    item.setCell_name(rs.getString("cell_name"));
                    item.setCi(rs.getString("ci"));
                    item.setFrequency_band(rs.getString("frequency_band"));
                    item.setPci(rs.getString("pci"));
                    item.setTac(rs.getString("tac"));
                    item.setLcrid(rs.getString("lcrid"));
                    item.setEnodeb_name(rs.getString("bts_nodeb_name"));
                    item.setVendor(rs.getString("vendor"));
                    item.setFilename(rs.getString("filename"));
                    item.setEnodeb_id(rs.getString("enodeb_id"));
                    item.setCheck_date(rs.getString("check_date"));
                    item.setCell_code(rs.getString("cell_code"));
                    item.setBandwith(rs.getString("bandwidth"));
                    item.setOam_ip(rs.getString("oam_ip"));
                    item.setService_ip(rs.getString("service_ip"));
                    item.setUarcn(rs.getString("uarfcn"));
                    item.setProvinceName(rs.getString("ten_tinh_tp"));
                    item.setAreaName(rs.getString("khu_vuc"));

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

    public static List rimsInventory(String ne_type_id) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.rims_compare_inventory(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, ne_type_id);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                if (ne_type_id.equalsIgnoreCase("5")) {
                    Cell2G item = new Cell2G();
                    item.setCellName(rs.getString("ten_tren_he_thong"));
                    item.setLac(rs.getLong("lac"));
                    item.setCi(rs.getLong("ci"));
                    result.add(item);
                } else if (ne_type_id.equalsIgnoreCase("6")) {
                    Cell3G item = new Cell3G();
                    item.setCellName(rs.getString("ten_tren_he_thong"));
                    item.setLac(rs.getLong("lac"));
                    item.setCi(rs.getLong("ci"));
                    result.add(item);
                } else if (ne_type_id.equalsIgnoreCase("7")) {
                    Cell4G item = new Cell4G();
                    item.setCell_name(rs.getString("ten_tren_he_thong"));
                    item.setCi(rs.getString("ci"));
                    item.setTac(rs.getString("tac"));
                    item.setEnodeb_id(rs.getString("enodeb_id"));
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

    public static List<EmailReportBO> getEmailReport(String id) throws Exception {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EmailReportBO> result = null;
        try {
            result = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ? := pkg_auto_email.search_email_report_job(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, id);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                EmailReportBO emailReport = new EmailReportBO();
                emailReport.setIdReportMail(rs.getString("id_report_mail"));
                emailReport.setReportMailName(rs.getString("report_mail_name"));
                emailReport.setEmailDetail(rs.getString("email_detail"));
                emailReport.setListMail(rs.getString("list_email"));
                emailReport.setTimeSend(rs.getString("time_send"));
                emailReport.setStatus(rs.getString("status"));
                result.add(emailReport);
            }
        } catch (Exception e) {
            throw e;
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
        return result;
    }

//    lay thong tin chi tiet cua tung bao cao
    public static List<EmailReportBO> getEmailReportDetail(String id) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<EmailReportBO> result = null;
        try {
            result = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ? := pkg_auto_email.search_email_report_detail(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, id);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                EmailReportBO emailReport = new EmailReportBO();
                emailReport.setIdReportMail(rs.getString("id_report_mail"));
                emailReport.setReportMailName(rs.getString("report_mail_name"));
                emailReport.setListMail(rs.getString("list_email"));
                emailReport.setTimeSend(rs.getString("time_send"));
                emailReport.setStatus(rs.getString("status"));
                emailReport.setSqlName(rs.getString("sql_name"));
                emailReport.setSqlValue(rs.getString("sql_value"));

                result.add(emailReport);
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(AutoMailFacade.class.getName()).log(Level.SEVERE, null, ex);
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
        return result;
    }

    public static String findData(String sqlValue, String sqlName) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        String result = "";
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.find_data_sql(?); end;";
            cs = conn.prepareCall(sql);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, sqlValue);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);

            String folderTemp = StringUtils.getFolderTemp();

            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("1");
            Cell cell = null;
            Row row = null;
            int rowIndex = 1;

            row = sheet.createRow(0);
            CellStyle style = sheet.getWorkbook().createCellStyle();
            row.setRowStyle(style);

            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            for (int i = 1; i <= count; i++) {
                cell = row.createCell(i-1);
                cell.setCellValue(rsmd.getColumnName(i));
            }
            String name = rsmd.getColumnName(1);

//            result = new ArrayList<>();
//          ghi gia tri vao file exels
            while (rs.next()) {
                row = sheet.createRow(rowIndex++);
                row.setRowStyle(style);
                for (int i = 1; i <= count; i++) {
                    cell = row.createCell(i-1);
                    cell.setCellValue(rs.getString(i));
                }
            }
//            fin.close();
            String pathFile = folderTemp + File.separator + sqlName.replace(" ", "_") + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
            File file = new File(pathFile);
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return pathFile;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(),ex);
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
