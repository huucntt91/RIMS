/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.BtsNodeB;
import com.vnpt.media.rims.formbean.Cell;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Cyano
 */
public class ApiFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static List findLocation(String lac, String ci) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_api_app.find_location(?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, lac);
            cstmt.setString(3, ci);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cell record = new Cell();
                record.setLac(rs.getString("lac"));
                record.setCi(rs.getString("ci"));
                record.setBuildingId(rs.getString("building_id"));
                record.setBuildingCode(rs.getString("ma_building"));
                record.setProvinceId(rs.getString("tinhtp_id"));
                record.setProvinceCode(rs.getString("ma_tinh_tp"));
                record.setProvinceName(rs.getString("ten_tinh_tp"));
                record.setDistrictId(rs.getString("quanhuyen_id"));
                record.setDistrictCode(rs.getString("code"));
                record.setDistrictName(rs.getString("ten_quan_huyen"));
                record.setWardId(rs.getString("phuongxa_id"));
                record.setWardName(rs.getString("ten_phuong_xa"));
                record.setAddress(rs.getString("dia_chi"));
                record.setLongitude(rs.getString("longitude"));
                record.setLatitude(rs.getString("latitude"));

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
}
