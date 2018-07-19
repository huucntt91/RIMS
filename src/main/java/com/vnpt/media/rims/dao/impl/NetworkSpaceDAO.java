package com.vnpt.media.rims.dao.impl;

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
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.facade.EnvManager;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;


public class NetworkSpaceDAO extends GenericDAO implements INetworkSpace {
    
    private static final String RIMS_BB_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_BB_DS");

    private static Logger logger = LogManager.getLogger(NetworkSpaceDAO.class);

    @Override
    public List<NetworkSpaceInfoBO> findAllNetworkSpaceInfo(int startRow, int endRow, String name, String id) throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<NetworkSpaceInfoBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= pkg_network_space.fn_find_network_space(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2, startRow);
            cstmt.setInt(3, endRow);
            cstmt.setString(4, name);
            cstmt.setString(5, id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                NetworkSpaceInfoBO item = new NetworkSpaceInfoBO();
                item.setNetworkSpaceId(rs.getString("NETWORD_SPACE_ID") == null ? "" : rs.getString("NETWORD_SPACE_ID"));
                item.setNetworkSpaceName(rs.getString("NETWORD_SPACE_NAME") == null ? "" : rs.getString("NETWORD_SPACE_NAME"));
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
    public int getTotalNetworkSpaceInfo(String name, String id) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:= pkg_network_space.fc_find_total(?,?); end;";
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
    public int addNetworkSpaceInfo(NetworkSpaceInfoBO item, Long userInsert) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_network_space.fn_insert(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, item.getNetworkSpaceId());
            cstmt.setString(3, item.getNetworkSpaceName());
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
    public int updateNetworkSpaceInfo(NetworkSpaceInfoBO item,Long userInsert) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_network_space.fn_update(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, item.getNetworkSpaceId());
            cstmt.setString(3, item.getNetworkSpaceName());
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
    public int deleteNetworkSpaceInfo(Long id, Long userInsert) throws DAOException {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_BB_DS);
            String sql = "begin ?:=pkg_network_space.fn_delete(?,?); end;";
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