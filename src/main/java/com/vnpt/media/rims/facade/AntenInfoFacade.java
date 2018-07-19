package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.AntenInfoBO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class AntenInfoFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static ArrayList<AntenInfoBO> fc_find_all(String startRow, String endRow, String id, String name, String khuvucId, String tinhId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<AntenInfoBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_anten_info.fc_find_all(?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, startRow);
            cstmt.setString(3, endRow);
            cstmt.setString(4, id);
            cstmt.setString(5, name);
            cstmt.setString(6, khuvucId);
            cstmt.setString(7, tinhId);

            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                AntenInfoBO item = new AntenInfoBO();
                item.setName(rs.getString("ten_anten") == null ? "" : rs.getString("ten_anten"));
                item.setHangSX(rs.getString("hang_sx") == null ? "" : rs.getString("hang_sx"));
                item.setTrongLuong(rs.getString("trong_luong") == null ? "" : rs.getString("trong_luong"));
                item.setKichCo(rs.getString("kich_co") == null ? "" : rs.getString("kich_co"));
                item.setDoCao(rs.getString("do_cao_lap_dat") == null ? "" : rs.getString("do_cao_lap_dat"));
                item.setGain(rs.getString("cau_hinh_gain") == null ? "" : rs.getString("cau_hinh_gain"));
                item.setBeamWidth(rs.getString("cau_hinh_beam_width") == null ? "" : rs.getString("cau_hinh_beam_width"));
                item.setPort(rs.getString("cau_hinh_port") == null ? "" : rs.getString("cau_hinh_port"));
                item.setPortId(rs.getString("cau_hinh_port_id") == null ? "" : rs.getString("cau_hinh_port_id"));
                item.setBangTanId(rs.getString("bang_tan_id") == null ? "" : rs.getString("bang_tan_id"));
                item.setBangTan(rs.getString("ten_bang_tan") == null ? "" : rs.getString("ten_bang_tan"));
                item.setLoaiAnten(rs.getString("ten_loai_anten") == null ? "" : rs.getString("ten_loai_anten"));
                item.setLoaiAntenId(rs.getString("loai_antena_id") == null ? "" : rs.getString("loai_antena_id"));
                
                item.setId(rs.getLong("node_id"));
                item.setCode(rs.getString("ma_node") == null ? "" : rs.getString("ma_node"));
                item.setBuildingId(rs.getLong("building_id"));
                item.setCodeBuilding(rs.getString("ma_building") == null ? "" : rs.getString("ma_building"));
                item.setTenTinhTp(rs.getString("ten_tinh_tp"));
                item.setDonViId(rs.getLong("donvi_id"));
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

    public static int fc_total_all(String id, String name, String khuvucId, String tinhId) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_anten_info.fc_total_all(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, id);
            cstmt.setString(3, name);
            cstmt.setString(4, khuvucId);
            cstmt.setString(5, tinhId);
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
    public static int fn_modify(String prn_action, AntenInfoBO model) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_anten_info.fn_modify(?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_action);
            cstmt.setString(3, model.getId() == null ? "" : String.valueOf(model.getId()));

            cstmt.setString(4, model.getName());
       
            cstmt.setString(5, model.getHangSX());
            cstmt.setString(6, model.getTrongLuong());

            cstmt.setString(7, model.getKichCo());
            cstmt.setString(8, model.getDoCao());
            cstmt.setString(9, model.getLoaiAntenId());
            cstmt.setString(10, model.getBangTanId());
            cstmt.setString(11, model.getPortId());
            cstmt.setString(12, model.getGain());
            cstmt.setString(13, model.getBeamWidth());
 
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
