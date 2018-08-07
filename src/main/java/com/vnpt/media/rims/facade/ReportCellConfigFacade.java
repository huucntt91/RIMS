package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.formbean.Cell2GConfig;
import com.vnpt.media.rims.formbean.Cell3GConfig;
import com.vnpt.media.rims.formbean.Cell4GConfig;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportCellConfigFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");
    private static final Logger logger = LogManager.getLogger(ReportCellConfigFacade.class);

    public static List search(String prs_start_record, String prs_length_page,
            String prs_global_search, String prs_list_column_name, String prs_list_column_search, String prs_column_to_sort,
            String prs_sort_direction, String[] recordsTotal, String[] recordsFiltered, String neTypeId) throws Exception {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report_config.fn_search(?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, prs_start_record);
            cs.setString(3, prs_length_page);
            cs.setString(4, prs_global_search);
            cs.setString(5, prs_list_column_name);
            cs.setString(6, prs_list_column_search);
            cs.setString(7, prs_column_to_sort);
            cs.setString(8, prs_sort_direction);
            cs.setString(9, neTypeId);
            cs.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            cs.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            recordsTotal[0] = cs.getString(10);
            recordsFiltered[0] = cs.getString(11);
            if (neTypeId.equalsIgnoreCase("5")) {
                arrayList = new ArrayList();
                while (rs.next()) {
                    Cell2GConfig item = new Cell2GConfig();
                    item.setNodeCode(rs.getString("ma_node"));
                    item.setManagementUnit(rs.getString("ten_don_vi"));
                    item.setVendorName(rs.getString("ten_thiet_bi"));
                    item.setBuildingCode(rs.getString("ma_building"));
                    item.setProvinceName(rs.getString("ten_tinh_tp"));
                    item.setDistrictName(rs.getString("ten_quan_huyen"));
                    item.setLongitude(rs.getString("longitude"));
                    item.setLatitude(rs.getString("latitude"));
                    item.setSiteType(rs.getString("ten_loai_tram"));
                    item.setParentCode(rs.getString("ma_node_cha"));
                    item.setProjectCode(rs.getString("ma_tram_da"));
                    item.setBscName(rs.getString("bsc_rnc_name"));
                    item.setManagementName(rs.getString("ten_cell"));
                    item.setSystemName(rs.getString("ten_tren_he_thong"));
                    item.setLac(rs.getString("lac"));
                    item.setCi(rs.getString("ci"));
                    item.setFrequencyBand(rs.getString("ten_bang_tan"));
                    item.setBcch(rs.getString("bcch"));
                    item.setBsic(rs.getString("bsic"));
                    item.setTch(rs.getString("tch"));
                    item.setTrxConfig(rs.getString("trx_config"));
                    item.setCellType(rs.getString("cell_type"));
                    item.setAzimuth(rs.getString("azimuth"));
                    item.setMechanicalTilt(rs.getString("mechanical_tilt"));
                    item.setElectricalTilt(rs.getString("electrical_tilt"));
                    item.setTotalTilt(rs.getString("total_tilt"));
                    item.setAntennaType(rs.getString("ten_loai_anten"));
                    item.setAntennaHigh(rs.getString("antenna_high"));
                    item.setBosterTma(rs.getString("boster_tma"));
                    item.setSpecialCoverage(rs.getString("special_coverage"));
                    item.setAntennaGain(rs.getString("antenna_gain"));
                    item.setStatus(rs.getString("status"));
                    item.setNote(rs.getString("note"));
                    arrayList.add(item);
                }

            } else if (neTypeId.equalsIgnoreCase("6")) {
                arrayList = new ArrayList();
                while (rs.next()) {
                    Cell3GConfig item = new Cell3GConfig();
                    item.setNodeCode(rs.getString("ma_node"));
                    item.setManagementUnit(rs.getString("ten_don_vi"));
                    item.setVendorName(rs.getString("ten_thiet_bi"));
                    item.setBuildingCode(rs.getString("ma_building"));
                    item.setProvinceName(rs.getString("ten_tinh_tp"));
                    item.setDistrictName(rs.getString("ten_quan_huyen"));
                    item.setLongitude(rs.getString("longitude"));
                    item.setLatitude(rs.getString("latitude"));
                    item.setSiteType(rs.getString("ten_loai_tram"));
                    item.setParentCode(rs.getString("ma_node_cha"));
                    item.setProjectCode(rs.getString("ma_tram_da"));
                    item.setRncName(rs.getString("bsc_rnc_name"));
                    item.setManagementName(rs.getString("ten_cell"));
                    item.setSystemName(rs.getString("ten_tren_he_thong"));
                    item.setLac(rs.getString("lac"));
                    item.setCi(rs.getString("ci"));
                    item.setFrequencyBand(rs.getString("ten_bang_tan"));
                    item.setDlPsc(rs.getString("dl_psc"));
                    item.setCpichPower(rs.getString("cpich_power"));
                    item.setTotalPower(rs.getString("total_power"));
                    item.setMaxPower(rs.getString("max_power"));
                    item.setCellType(rs.getString("cell_type"));
                    item.setAzimuth(rs.getString("azimuth"));
                    item.setMechanicalTilt(rs.getString("mechanical_tilt"));
                    item.setElectricalTilt(rs.getString("electrical_tilt"));
                    item.setTotalTilt(rs.getString("total_tilt"));
                    item.setAntennaType(rs.getString("ten_loai_anten"));
                    item.setAntennaHigh(rs.getString("antenna_high"));
                    item.setNoOfCarrier(rs.getString("no_of_carrier"));
                    item.setReason(rs.getString("ly_do"));
                    item.setBosterTma(rs.getString("boster_tma"));
                    item.setSpecialCoverage(rs.getString("special_coverage"));
                    item.setAntennaGain(rs.getString("antenna_gain"));
                    item.setStatus(rs.getString("status"));
                    item.setNote(rs.getString("note"));
                    arrayList.add(item);
                }
            } else if (neTypeId.equalsIgnoreCase("7")) {
                arrayList = new ArrayList();
                while (rs.next()) {
                    Cell4GConfig item = new Cell4GConfig();
                    item.setNodeCode(rs.getString("ma_node"));
                    item.setManagementUnit(rs.getString("ten_don_vi"));
                    item.setVendorName(rs.getString("ten_thiet_bi"));
                    item.setBuildingCode(rs.getString("ma_building"));
                    item.setProvinceName(rs.getString("ten_tinh_tp"));
                    item.setDistrictName(rs.getString("ten_quan_huyen"));
                    item.setLongitude(rs.getString("longitude"));
                    item.setLatitude(rs.getString("latitude"));
                    item.setSiteType(rs.getString("ten_loai_tram"));
                    item.setParentCode(rs.getString("ma_node_cha"));
                    item.setProjectCode(rs.getString("ma_tram_da"));
                    item.setEnodebId(rs.getString("enodeb_id"));
                    item.setManagementName(rs.getString("ten_cell"));
                    item.setSystemName(rs.getString("ten_tren_he_thong"));
                    item.setCi(rs.getString("ci"));
                    item.setFrequencyBand(rs.getString("ten_bang_tan"));
                    item.setPci(rs.getString("pci"));
                    item.setTac(rs.getString("tac"));
                    item.setLcrid(rs.getString("lcrid"));
                    item.setCellType(rs.getString("cell_type"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setAzimuth(rs.getString("azimuth"));
                    item.setMechanicalTilt(rs.getString("mechanical_tilt"));
                    item.setElectricalTilt(rs.getString("electrical_tilt"));
                    item.setTotalTilt(rs.getString("total_tilt"));
                    item.setAntennaType(rs.getString("ten_loai_anten"));
                    item.setAntennaHigh(rs.getString("antenna_high"));
                    item.setNoOfCarrier(rs.getString("no_of_carrier"));
                    item.setReason(rs.getString("ly_do"));
                    item.setBosterTma(rs.getString("boster_tma"));
                    item.setSpecialCoverage(rs.getString("special_coverage"));
                    item.setAntennaGain(rs.getString("antenna_gain"));
                    item.setStatus(rs.getString("status"));
                    item.setNote(rs.getString("note"));
                    arrayList.add(item);
                }
            }

            return (ArrayList<?>) arrayList;
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

    public static List exportExcel(String khuvucIds, String tinhTpIds, String quanHuyenIds, String phuongXaIds, String neTypeId) throws Exception {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List arrayList = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_report_config.fn_export(?,?,?,?,?); end;";

            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, khuvucIds);
            cs.setString(3, tinhTpIds);
            cs.setString(4, quanHuyenIds);
            cs.setString(5, phuongXaIds);
            cs.setString(6, neTypeId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            if (neTypeId.equalsIgnoreCase("5")) {
                arrayList = new ArrayList();
                while (rs.next()) {
                    Cell2GConfig item = new Cell2GConfig();
                    item.setNodeCode(rs.getString("ma_node"));
                    item.setManagementUnit(rs.getString("ten_don_vi"));
                    item.setVendorName(rs.getString("ten_thiet_bi"));
                    item.setBuildingCode(rs.getString("ma_building"));
                    item.setProvinceName(rs.getString("ten_tinh_tp"));
                    item.setDistrictName(rs.getString("ten_quan_huyen"));
                    item.setLongitude(rs.getString("longitude"));
                    item.setLatitude(rs.getString("latitude"));
                    item.setSiteType(rs.getString("ten_loai_tram"));
                    item.setParentCode(rs.getString("ma_node_cha"));
                    item.setProjectCode(rs.getString("ma_tram_da"));
                    item.setBscName(rs.getString("bsc_rnc_name"));
                    item.setManagementName(rs.getString("ten_cell"));
                    item.setSystemName(rs.getString("ten_tren_he_thong"));
                    item.setLac(rs.getString("lac"));
                    item.setCi(rs.getString("ci"));
                    item.setFrequencyBand(rs.getString("ten_bang_tan"));
                    item.setBcch(rs.getString("bcch"));
                    item.setBsic(rs.getString("bsic"));
                    item.setTch(rs.getString("tch"));
                    item.setTrxConfig(rs.getString("trx_config"));
                    item.setCellType(rs.getString("cell_type"));
                    item.setAzimuth(rs.getString("azimuth"));
                    item.setMechanicalTilt(rs.getString("mechanical_tilt"));
                    item.setElectricalTilt(rs.getString("electrical_tilt"));
                    item.setTotalTilt(rs.getString("total_tilt"));
                    item.setAntennaType(rs.getString("ten_loai_anten"));
                    item.setAntennaHigh(rs.getString("antenna_high"));
                    item.setBosterTma(rs.getString("boster_tma"));
                    item.setSpecialCoverage(rs.getString("special_coverage"));
                    item.setAntennaGain(rs.getString("antenna_gain"));
                    item.setStatus(rs.getString("status"));
                    item.setNote(rs.getString("note"));
                    arrayList.add(item);
                }

            } else if (neTypeId.equalsIgnoreCase("6")) {
                arrayList = new ArrayList();
                while (rs.next()) {
                    Cell3GConfig item = new Cell3GConfig();
                    item.setNodeCode(rs.getString("ma_node"));
                    item.setManagementUnit(rs.getString("ten_don_vi"));
                    item.setVendorName(rs.getString("ten_thiet_bi"));
                    item.setBuildingCode(rs.getString("ma_building"));
                    item.setProvinceName(rs.getString("ten_tinh_tp"));
                    item.setDistrictName(rs.getString("ten_quan_huyen"));
                    item.setLongitude(rs.getString("longitude"));
                    item.setLatitude(rs.getString("latitude"));
                    item.setSiteType(rs.getString("ten_loai_tram"));
                    item.setParentCode(rs.getString("ma_node_cha"));
                    item.setProjectCode(rs.getString("ma_tram_da"));
                    item.setRncName(rs.getString("bsc_rnc_name"));
                    item.setManagementName(rs.getString("ten_cell"));
                    item.setSystemName(rs.getString("ten_tren_he_thong"));
                    item.setLac(rs.getString("lac"));
                    item.setCi(rs.getString("ci"));
                    item.setFrequencyBand(rs.getString("ten_bang_tan"));
                    item.setDlPsc(rs.getString("dl_psc"));
                    item.setCpichPower(rs.getString("cpich_power"));
                    item.setTotalPower(rs.getString("total_power"));
                    item.setMaxPower(rs.getString("max_power"));
                    item.setCellType(rs.getString("cell_type"));
                    item.setAzimuth(rs.getString("azimuth"));
                    item.setMechanicalTilt(rs.getString("mechanical_tilt"));
                    item.setElectricalTilt(rs.getString("electrical_tilt"));
                    item.setTotalTilt(rs.getString("total_tilt"));
                    item.setAntennaType(rs.getString("ten_loai_anten"));
                    item.setAntennaHigh(rs.getString("antenna_high"));
                    item.setNoOfCarrier(rs.getString("no_of_carrier"));
                    item.setReason(rs.getString("ly_do"));
                    item.setBosterTma(rs.getString("boster_tma"));
                    item.setSpecialCoverage(rs.getString("special_coverage"));
                    item.setAntennaGain(rs.getString("antenna_gain"));
                    item.setStatus(rs.getString("status"));
                    item.setNote(rs.getString("note"));
                    arrayList.add(item);
                }
            } else if (neTypeId.equalsIgnoreCase("7")) {
                arrayList = new ArrayList();
                while (rs.next()) {
                    Cell4GConfig item = new Cell4GConfig();
                    item.setNodeCode(rs.getString("ma_node"));
                    item.setManagementUnit(rs.getString("ten_don_vi"));
                    item.setVendorName(rs.getString("ten_thiet_bi"));
                    item.setBuildingCode(rs.getString("ma_building"));
                    item.setProvinceName(rs.getString("ten_tinh_tp"));
                    item.setDistrictName(rs.getString("ten_quan_huyen"));
                    item.setLongitude(rs.getString("longitude"));
                    item.setLatitude(rs.getString("latitude"));
                    item.setSiteType(rs.getString("ten_loai_tram"));
                    item.setParentCode(rs.getString("ma_node_cha"));
                    item.setProjectCode(rs.getString("ma_tram_da"));
                    item.setEnodebId(rs.getString("enodeb_id"));
                    item.setManagementName(rs.getString("ten_cell"));
                    item.setSystemName(rs.getString("ten_tren_he_thong"));
                    item.setCi(rs.getString("ci"));
                    item.setFrequencyBand(rs.getString("ten_bang_tan"));
                    item.setPci(rs.getString("pci"));
                    item.setTac(rs.getString("tac"));
                    item.setLcrid(rs.getString("lcrid"));
                    item.setCellType(rs.getString("cell_type"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setAzimuth(rs.getString("azimuth"));
                    item.setMechanicalTilt(rs.getString("mechanical_tilt"));
                    item.setElectricalTilt(rs.getString("electrical_tilt"));
                    item.setTotalTilt(rs.getString("total_tilt"));
                    item.setAntennaType(rs.getString("ten_loai_anten"));
                    item.setAntennaHigh(rs.getString("antenna_high"));
                    item.setNoOfCarrier(rs.getString("no_of_carrier"));
                    item.setReason(rs.getString("ly_do"));
                    item.setBosterTma(rs.getString("boster_tma"));
                    item.setSpecialCoverage(rs.getString("special_coverage"));
                    item.setAntennaGain(rs.getString("antenna_gain"));
                    item.setStatus(rs.getString("status"));
                    item.setNote(rs.getString("note"));
                    arrayList.add(item);
                }
            }

            return (ArrayList<?>) arrayList;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return (ArrayList<?>) arrayList;
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
