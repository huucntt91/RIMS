/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ObjectLog;
import com.vnpt.media.rims.common.Constants;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Cyano
 */
public class ObjectLogFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");
    private static final Logger logger = LogManager.getLogger(ObjectLogFacade.class);

    public static void addObjectLog(ObjectLog objectLog) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        Integer result = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_object_log.add_object_log(?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, objectLog.getObjectName());
            cs.setString(3, objectLog.getAttributeName());
            cs.setString(4, objectLog.getOldValue());
            cs.setString(5, objectLog.getNewValue());
            cs.setString(6, objectLog.getUserId());
            cs.setString(7, objectLog.getActionName());
            cs.setString(8, objectLog.getObjectId());
            cs.executeQuery();
            result = cs.getInt(1);
            if (result <= 0) {
                logger.debug("insert log object error: {}, attribute: {}", objectLog.getObjectName(), objectLog.getAttributeName());
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
    }

    public static void findObjectByCode(String p_code, String objectType) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List result = null;

        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_object_log.add_object_log(?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, p_code);
            cs.setString(3, objectType);
            cs.executeQuery();
            result = new ArrayList<>();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                if (objectType.equalsIgnoreCase(Constants.BUILDING_OBJECT)) {
                    
                } else {

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
    }

}
