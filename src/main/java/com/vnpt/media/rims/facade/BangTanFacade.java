package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.DMBangTanBO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class BangTanFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static ArrayList<DMBangTanBO> fc_find_all(String prs_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<DMBangTanBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_dm_bang_tan.fc_find_all(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                DMBangTanBO item = new DMBangTanBO();
                item.setBang_tan_id(rs.getString("bang_tan_id") == null ? "" : rs.getString("bang_tan_id"));
                item.setId_loai_cong_nghe(rs.getString("id_loai_cong_nghe") == null ? "" : rs.getString("id_loai_cong_nghe"));
                item.setTen_bang_tan(rs.getString("ten_bang_tan") == null ? "" : rs.getString("ten_bang_tan"));
                item.setTen_loai_cong_nghe(rs.getString("ten_loai_cong_nghe") == null ? "" : rs.getString("ten_loai_cong_nghe"));
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
public static ArrayList<DMBangTanBO> fc_find_all_by_name(String prs_name) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<DMBangTanBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_dm_bang_tan.fc_find_all_by_name(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_name);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                DMBangTanBO item = new DMBangTanBO();
                item.setBang_tan_id(rs.getString("bang_tan_id") == null ? "" : rs.getString("bang_tan_id"));
                item.setId_loai_cong_nghe(rs.getString("id_loai_cong_nghe") == null ? "" : rs.getString("id_loai_cong_nghe"));
                item.setTen_bang_tan(rs.getString("ten_bang_tan") == null ? "" : rs.getString("ten_bang_tan"));
                item.setTen_loai_cong_nghe(rs.getString("ten_loai_cong_nghe") == null ? "" : rs.getString("ten_loai_cong_nghe"));
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
    public static int fn_modify(String prn_action, String prs_id,
            String prs_ten_bang_tan, String prn_id_loai_cong_nghe) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_dm_bang_tan.fn_modify(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_action);
            cstmt.setString(3, prs_id);
            cstmt.setString(4, prs_ten_bang_tan);
            cstmt.setString(5, prn_id_loai_cong_nghe);
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
    
    //tim kiem bang tan theo loai cong nghe
     public static ArrayList<DMBangTanBO> findBangTan(Integer bangTanId, Integer loaiCongNgheId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<DMBangTanBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_dm_bang_tan.fc_list_bang_tan(?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, bangTanId == null ? "" : bangTanId.toString());
            cstmt.setString(3, loaiCongNgheId == null ? "" : loaiCongNgheId.toString());
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                DMBangTanBO item = new DMBangTanBO();
                item.setBang_tan_id(rs.getString("bang_tan_id") == null ? "" : rs.getString("bang_tan_id"));
                item.setId_loai_cong_nghe(rs.getString("id_loai_cong_nghe") == null ? "" : rs.getString("id_loai_cong_nghe"));
                item.setTen_bang_tan(rs.getString("ten_bang_tan") == null ? "" : rs.getString("ten_bang_tan"));
                item.setTen_loai_cong_nghe(rs.getString("ten_loai_cong_nghe") == null ? "" : rs.getString("ten_loai_cong_nghe"));
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
