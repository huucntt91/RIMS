/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ActionLogBO;
import com.vnpt.media.rims.bean.ManEInfoBO;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
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
public class AutoMailFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

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

}
