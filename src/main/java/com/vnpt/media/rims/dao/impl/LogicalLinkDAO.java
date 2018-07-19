package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.dao.impl.*;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.facade.EnvManager;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class LogicalLinkDAO extends GenericDAO implements ILogicalLink {
    
    private static final String RIMS_BB_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    private static Logger logger = LogManager.getLogger(LogicalLinkDAO.class);

    @Override
    public List<LogicalLinkBO> findAllLogicalLinkInfo(int startRow, int endRow, String name, String id) throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<LogicalLinkBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= pkg_logical_link.fn_find_logical_link(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2, startRow);
            cstmt.setInt(3, endRow);
            cstmt.setString(4, name);
            cstmt.setString(5, id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                LogicalLinkBO item = new LogicalLinkBO();
                item.setLogicalLinkName(rs.getString("logical_link_name") == null ? "" : rs.getString("logical_link_name"));
                item.setTlinkName(rs.getString("TLINK_NAME") == null ? "" : rs.getString("TLINK_NAME"));
                item.setTpathName(rs.getString("TPATH_NAME") == null ? "" : rs.getString("TPATH_NAME"));
                item.setNote(rs.getString("note") == null ? "" : rs.getString("note"));
                item.setLogicalLinkId(rs.getString("logical_link_id") == null ? "" : rs.getString("logical_link_id"));
                item.setTlinkId(rs.getString("tlink_id") == null ? "" : rs.getString("tlink_id"));
                item.setTpathId(rs.getString("tpath_id") == null ? "" : rs.getString("tpath_id"));
                item.setBandwidth(rs.getString("BANDWIDTH") == null ? "" : rs.getString("BANDWIDTH"));
                item.setBandwidth_dv(rs.getString("BANDWIDTH_DV") == null ? "" : rs.getString("BANDWIDTH_DV"));
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
    public int getTotalLogicalLinkInfo(String name, String id) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= pkg_logical_link.fc_find_total(?,?); end;";
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
    public int addLogicalLinkInfo(LogicalLinkBO item, Long userInsert) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_logical_link.fn_insert(?,?,?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, item.getLogicalLinkId());
            cstmt.setString(3, item.getLogicalLinkName());
            cstmt.setString(4, item.getTlinkId());
            cstmt.setString(5, item.getTpathId());
            cstmt.setString(6, item.getNote());
            cstmt.setString(7, item.getBandwidth());
            cstmt.setString(8, item.getBandwidth_dv());
            cstmt.setString(9, String.valueOf(userInsert));
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
    public int updateLogicalLinkInfo(LogicalLinkBO item, Long userInsert) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_logical_link.fn_update(?,?,?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, item.getLogicalLinkId());
            cstmt.setString(3, item.getLogicalLinkName());
            cstmt.setString(4, item.getTlinkId());
            cstmt.setString(5, item.getTpathId());
            cstmt.setString(6, item.getNote());
            cstmt.setString(7, item.getBandwidth());
            cstmt.setString(8, item.getBandwidth_dv());
            cstmt.setString(9, String.valueOf(userInsert));
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
    public int deleteLogicalLinkInfo(Long id, Long userInsert) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_logical_link.fn_delete(?,?); end;";
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
