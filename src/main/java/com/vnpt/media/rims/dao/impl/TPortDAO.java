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


public class TPortDAO extends GenericDAO implements ITPort {
    
    private static final String RIMS_BB_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    private static Logger logger = LogManager.getLogger(TPortDAO.class);

    @Override
    public List<TPortBO> findAllPortByDevice(int startRow, int endRow, String tNodeId, String tEq3Id) throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<TPortBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= PKG_TPORT.fn_findPortView(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tNodeId);
            cstmt.setString(3, tEq3Id);
            cstmt.setInt(4, startRow);
            cstmt.setInt(5, endRow);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                TPortBO item = new TPortBO();
//                a.tnode_id, e.tport_id, e.teq3_id, e.port_no, e.note, e.mtu, e.transceiver, 
//                e.cong_suat_phat, e.cong_suat_thu, e.nguong_thu_min, e.nguong_thu_max, 
//                e.serial_no, e.tport_name
                item.settNodeId(rs.getString("tnode_id") == null ? "" : rs.getString("tnode_id"));
                item.settPortId(rs.getString("tport_id") == null ? "" : rs.getString("tport_id"));
                item.settEq3Id(rs.getString("teq3_id") == null ? "" : rs.getString("teq3_id"));
                item.setPortNo(rs.getString("port_no") == null ? "" : rs.getString("port_no"));
                item.setNote(rs.getString("note") == null ? "" : rs.getString("note"));
                item.setMtu(rs.getString("mtu") == null ? "" : rs.getString("mtu"));
                item.setTransceiver(rs.getString("transceiver") == null ? "" : rs.getString("transceiver"));
                item.setCongsuatphat(rs.getString("cong_suat_phat") == null ? "" : rs.getString("cong_suat_phat"));
                item.setCongsuatthu(rs.getString("cong_suat_thu") == null ? "" : rs.getString("cong_suat_thu"));
                item.setNguongthumin(rs.getString("nguong_thu_min") == null ? "" : rs.getString("nguong_thu_min"));
                item.setNguongthumax(rs.getString("nguong_thu_max") == null ? "" : rs.getString("nguong_thu_max"));
                item.setSerialNo(rs.getString("serial_no") == null ? "" : rs.getString("serial_no"));
                item.setTportName(rs.getString("tport_name") == null ? "" : rs.getString("tport_name"));
                
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
    public int getTotalPortByDevice(String tnode_id, String teq3_id) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= PKG_TPORT.fc_find_totalPort(?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, tnode_id);
            cstmt.setString(3, teq3_id);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally {
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