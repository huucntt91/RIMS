package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.facade.EnvManager;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;


public class ViewCardDAO extends GenericDAO implements IViewCard {
    
    private static final String RIMS_BB_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    private static Logger logger = LogManager.getLogger(ViewCardDAO.class);

    @Override
    public List<ViewCardBO> findCardbyDevice(String tNodeId) throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<ViewCardBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= pkg_viewcard.fc_findCardbyDevice(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tNodeId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ViewCardBO item = new ViewCardBO();
                item.setTnode_id(rs.getString("tnode_id") == null ? "" : rs.getString("tnode_id"));
                item.setTnode_name(rs.getString("tnode_name") == null ? "" : rs.getString("tnode_name"));
                item.setTeq1_id(rs.getString("teq1_id") == null ? "" : rs.getString("teq1_id"));
                item.setTeq1_number(rs.getString("teq1_number") == null ? "" : rs.getString("teq1_number"));
                item.setTotal_eq2(rs.getString("total_eq2") == null ? "" : rs.getString("total_eq2"));
                item.setTeq2_id(rs.getString("teq2_id") == null ? "" : rs.getString("teq2_id"));
                item.setTeq2_number(rs.getString("teq2_number") == null ? "" : rs.getString("teq2_number"));
                item.setTotal_eq3(rs.getString("total_eq3") == null ? "" : rs.getString("total_eq3"));
                item.setTeq3_id(rs.getString("teq3_id") == null ? "" : rs.getString("teq3_id"));
                item.setTeq3_number(rs.getString("teq3_number") == null ? "" : rs.getString("teq3_number"));
                item.setTeq3_name(rs.getString("teq3_name") == null ? "" : rs.getString("teq3_name"));
                
                
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
    
    @Override
    public List<ViewCardBO> findHangSlot(String tNodeId) throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<ViewCardBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= pkg_viewcard.fc_findHangSlot(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tNodeId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ViewCardBO item = new ViewCardBO();
                item.setTnode_id(rs.getString("tnode_id") == null ? "" : rs.getString("tnode_id"));
                item.setTeq1_id(rs.getString("teq1_id") == null ? "" : rs.getString("teq1_id"));
                item.setTeq1_number(rs.getString("teq1_number") == null ? "" : rs.getString("teq1_number"));
                item.setTotal_eq2(rs.getString("total_eq2") == null ? "" : rs.getString("total_eq2"));
                item.setTeq2_id(rs.getString("teq2_id") == null ? "" : rs.getString("teq2_id"));
                item.setTeq2_number(rs.getString("teq2_number") == null ? "" : rs.getString("teq2_number"));
                item.setTotal_eq3(rs.getString("total_eq3") == null ? "" : rs.getString("total_eq3"));
                
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
    
    
    
    

}