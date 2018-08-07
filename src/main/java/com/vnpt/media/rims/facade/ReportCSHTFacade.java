package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.formbean.ReportCSHT;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportCSHTFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");
    private static final Logger logger = LogManager.getLogger(ReportCSHTFacade.class);

    public static ArrayList<ReportCSHT> search(String prs_start_record, String prs_length_page,
            String prs_global_search, String prs_list_column_name, String prs_list_column_search, String prs_column_to_sort,
            String prs_sort_direction, String[] recordsTotal, String[] recordsFiltered) throws Exception {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<ReportCSHT> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report_csht.fn_search(?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, prs_start_record);
            cs.setString(3, prs_length_page);
            cs.setString(4, prs_global_search);
            cs.setString(5, prs_list_column_name);
            cs.setString(6, prs_list_column_search);
            cs.setString(7, prs_column_to_sort);
            cs.setString(8, prs_sort_direction);
            cs.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            cs.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            recordsTotal[0] = cs.getString(9);
            recordsFiltered[0] = cs.getString(10);
            while (rs.next()) {
                ReportCSHT item = new ReportCSHT();
                item.setBuildingCode(rs.getString("ma_building"));
                item.setBuildingName(rs.getString("building_name"));
                item.setNodeCode(rs.getString("ma_node"));
                item.setManagementName(rs.getString("management_name"));
                item.setSystemName(rs.getString("ten_tren_he_thong"));
                item.setType(rs.getString("ne_type"));
                item.setProvinceName(rs.getString("ten_tinh_tp"));
                item.setDistrictName(rs.getString("ten_quan_huyen"));
                item.setWardName(rs.getString("ten_phuong_xa"));
                item.setLongitude(rs.getString("longitude"));
                item.setLatitude(rs.getString("latitude"));
                item.setAccreditationCode(rs.getString("accreditation_code"));
                item.setAccreStartDate(rs.getString("accre_start_date"));
                item.setAccreEndDate(rs.getString("accre_end_date"));
                item.setAreaName(rs.getString("khu_vuc"));
                item.setProvinceId(rs.getString("tinhtp_id"));
                item.setDistrictId(rs.getString("quanhuyen_id"));
                item.setWardId(rs.getString("phuongxa_id"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
        }
    }

    public static ArrayList<ReportCSHT> exportExcel(String khuvucIds, String tinhTpIds, String quanHuyenIds, String phuongXaIds, String buildingCode, String buildingName) throws Exception {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<ReportCSHT> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report_csht.fn_export(?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, khuvucIds);
            cs.setString(3, tinhTpIds);
            cs.setString(4, quanHuyenIds);
            cs.setString(5, phuongXaIds);
            cs.setString(6, buildingCode);
            cs.setString(7, buildingName);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                ReportCSHT item = new ReportCSHT();
                item.setBuildingCode(rs.getString("ma_building"));
                item.setBuildingName(rs.getString("building_name"));
                item.setNodeCode(rs.getString("ma_node"));
                item.setManagementName(rs.getString("management_name"));
                item.setSystemName(rs.getString("ten_tren_he_thong"));
                item.setType(rs.getString("ne_type"));
                item.setProvinceName(rs.getString("ten_tinh_tp"));
                item.setDistrictName(rs.getString("ten_quan_huyen"));
                item.setWardName(rs.getString("ten_phuong_xa"));
                item.setLongitude(rs.getString("longitude"));
                item.setLatitude(rs.getString("latitude"));
                item.setAccreditationCode(rs.getString("accreditation_code"));
                item.setAccreStartDate(rs.getString("accre_start_date"));
                item.setAccreEndDate(rs.getString("accre_end_date"));
                item.setAreaName(rs.getString("khu_vuc"));
                item.setProvinceId(rs.getString("tinhtp_id"));
                item.setDistrictId(rs.getString("quanhuyen_id"));
                item.setWardId(rs.getString("phuongxa_id"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
        }
    }
}
