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

public class FirewallDirDAO extends GenericDAO implements IFirewallDir {

    private static final String RIMS_BB_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");
    private static Logger logger = LogManager.getLogger(FirewallDirDAO.class);

    @Override
    public List<FirewallDirBO> findAllFirewallInfo(int startRow, int endRow, String name, String id) throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<FirewallDirBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= pkg_dm_firewall.fc_find_all(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2, startRow);
            cstmt.setInt(3, endRow);
            cstmt.setString(4, name);
            cstmt.setString(5, id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                FirewallDirBO item = new FirewallDirBO();
                item.setId(rs.getString("id") == null ? "" : rs.getString("id"));
                item.setFireWallName(rs.getString("firewall_name") == null ? "" : rs.getString("firewall_name"));
                item.setNote(rs.getString("note") == null ? "" : rs.getString("note"));
                
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
    public int getTotalFirewallInfo(String name, String id) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= pkg_dm_firewall.fc_find_total(?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, name);
            cstmt.setString(3, id);
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

    @Override
    public int addFirewallInfo(FirewallDirBO item, Long userInsert) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_dm_firewall.fc_insert(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, item.getFireWallName());
            cstmt.setString(3, item.getNote());
            cstmt.setString(4, String.valueOf(userInsert));
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

    @Override
    public int updateFirewallInfo(FirewallDirBO item,Long userInsert) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_dm_firewall.fc_update(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, item.getId());
            cstmt.setString(3, item.getFireWallName());
            cstmt.setString(4, item.getNote());
            cstmt.setString(5, String.valueOf(userInsert));
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

    @Override
    public int deleteFirewallInfo(Long id, Long userInsert) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_dm_firewall.fc_delete(?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, String.valueOf(id));
            cstmt.setString(3, String.valueOf(userInsert));
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
