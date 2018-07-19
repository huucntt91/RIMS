package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.BTSInfoBO;
import com.vnpt.media.rims.formbean.ApproveAllForm;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NodeObjectFacade {

    private static final Logger LOGGER = LogManager.getLogger(NodeObjectFacade.class);
    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static int approveNodeAll(ApproveAllForm approveForm, Long userUpdate) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_NODE.fn_approve_all(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, approveForm.getIds());
            cstmt.setString(3, approveForm.getStatus());
            cstmt.setLong(4, userUpdate);
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


    public static ArrayList<BTSInfoBO> findDuyetTram(String nodeId, String statusList, String tinhTpId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<BTSInfoBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_NODE.fn_find_all_tram(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, nodeId);
            cstmt.setString(3, statusList);
            cstmt.setString(4, tinhTpId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                BTSInfoBO item = new BTSInfoBO();
                item.setId(rs.getLong("node_Id"));
                item.setCode(rs.getString("ma_node") == null ? "" : rs.getString("ma_node"));
                item.setNote(rs.getString("note") == null ? "" : rs.getString("note"));
                item.setStatus(rs.getInt("status"));
                item.setNeTypeId(rs.getLong("NE_TYPE_ID"));
                item.setTenNeType(rs.getString("ten_loai_ne") == null ? "" : rs.getString("ten_loai_ne"));
                item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi") == null ? "" : rs.getString("hoan_canh_ra_doi"));
                item.setNgayHoatDong(rs.getDate("ngay_hoat_dong"));
                item.setTenNgQLy(rs.getString("ten_ng_qly") == null ? "" : rs.getString("ten_ng_qly"));
                item.setCauHinh(rs.getString("cau_hinh") == null ? "" : rs.getString("cau_hinh"));
                item.setCodeBuilding(rs.getString("ma_building") == null ? "" : rs.getString("ma_building"));
                item.setTenThietBi(rs.getString("ten_thiet_bi") == null ? "" : rs.getString("ten_thiet_bi"));
                item.setTenLoaiTram(rs.getString("ten_loai_tram") == null ? "" : rs.getString("ten_loai_tram"));
                item.setTenTinhTp(rs.getString("ten_tinh_tp") == null ? "" : rs.getString("ten_tinh_tp"));
                item.setName(rs.getString("ten_tram") == null ? "" : rs.getString("ten_tram"));
                item.setTenTrenHeThong(rs.getString("ten_tren_he_thong") == null ? "" : rs.getString("ten_tren_he_thong"));
                item.setMaNodeCha(rs.getString("ma_node_cha") == null ? "" : rs.getString("ma_node_cha"));
                item.setCodeTramDA(rs.getString("ma_tram_da") == null ? "" : rs.getString("ma_tram_da"));
                item.setDonViName(rs.getString("ten_don_vi") == null ? "" : rs.getString("ten_don_vi"));
                item.setCodeProvince(rs.getString("ma_tinh_tp") == null ? "" : rs.getString("ma_tinh_tp"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage(),ex);
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
