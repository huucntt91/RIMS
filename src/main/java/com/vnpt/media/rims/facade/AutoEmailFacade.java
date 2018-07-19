/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.AutoEmailBO;
import com.vnpt.media.rims.bean.ReportTrafficTramBO;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.dao.IReport;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import com.vnpt.media.rims.formbean.FilterForm;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Cyano
 */
public class AutoEmailFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    private Logger logger = LogManager.getLogger(AutoEmailFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public AutoEmailFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public AutoEmailFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    
//    trunglk_start_email_sql
    public static Integer countSqlReport(String prs_name) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            String sql = "begin ?:=pkg_auto_email.count_email_sql(?); end;";
            conn = EnvManager.getDbConnection(RIMS_DS);
            
//            String sql = 
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, prs_name);

            cs.executeQuery();
            result = (int) cs.getObject(1);

        } catch (Exception ex) {
            ex.printStackTrace();

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
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return result;
    }

    
    public static List<AutoEmailBO> fc_find_sql(Integer startRow, 
                                                     Integer endRow, 
                                                     String prs_id,
                                                     String prs_name) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<AutoEmailBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.search_email_sql(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, startRow == null ? "0" : startRow.toString());
            cstmt.setString(3, endRow == null ? "0" : endRow.toString());
            cstmt.setString(4, prs_id);
            cstmt.setString(5, prs_name);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                AutoEmailBO item = new AutoEmailBO();
                item.setIdSql(rs.getString("id_sql") == null ? "" : rs.getString("id_sql"));
                item.setSqlName(rs.getString("sql_name") == null ? "" : rs.getString("sql_name"));
                item.setSqlValue(rs.getString("sql_value") == null ? "" : rs.getString("sql_value"));
                item.setDescription(rs.getString("description") == null ? "" : rs.getString("description"));
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
    
    public static int fn_addSql(String prs_sqlName, String prs_sqlValue, String prs_description) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.fn_add_sql(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prs_sqlName);
            cstmt.setString(3, prs_sqlValue);
            cstmt.setString(4, prs_description);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
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
    
    public static int fn_updateSql(String prn_id, String prs_sqlName, String prs_sqlValue, String prs_description) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.fn_update_sql(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_id);
            cstmt.setString(3, prs_sqlName);
            cstmt.setString(4, prs_sqlValue);
            cstmt.setString(5, prs_description);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
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
    
    public static int fn_deleteSql(String prn_id) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.fn_delete_sql(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_id);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
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
    
//    trunglk_start_email_report
    
    public static Integer countEmailReport(String prs_name) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            String sql = "begin ?:=pkg_auto_email.count_email_report(?); end;";
            conn = EnvManager.getDbConnection(RIMS_DS);
            
//            String sql = 
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, prs_name);

            cs.executeQuery();
            result = (int) cs.getObject(1);

        } catch (Exception ex) {
            ex.printStackTrace();

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
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return result;
    }

    
    public static List<AutoEmailBO> fc_find_email_report(Integer startRow, 
                                                     Integer endRow, 
                                                     String prs_id,
                                                     String prs_name) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<AutoEmailBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.search_email_report(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, startRow == null ? "0" : startRow.toString());
            cstmt.setString(3, endRow == null ? "0" : endRow.toString());
            cstmt.setString(4, prs_id);
            cstmt.setString(5, prs_name);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                AutoEmailBO item = new AutoEmailBO();
                item.setIdReportMail(rs.getString("id_report_mail") == null ? "" : rs.getString("id_report_mail"));
                item.setReportMailName(rs.getString("report_mail_name") == null ? "" : rs.getString("report_mail_name"));
                item.setListMail(rs.getString("list_email") == null ? "" : rs.getString("list_email"));
                item.setTimeSend(rs.getString("time_send") == null ? "" : rs.getString("time_send"));
                item.setEmailDetail(rs.getString("email_detail") == null ? "" : rs.getString("email_detail"));
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
    
    public static int fn_addReport(String reportName, String lstMail, String timeSend, String emailDetail) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.fn_add_report(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, reportName);
            cstmt.setString(3, lstMail);
            cstmt.setString(4, timeSend);
            cstmt.setString(5, emailDetail);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
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
    
    public static int fn_updateReport(String prn_id, String reportName, String lstMail, String timeSend, String emailDetail) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.fn_update_report(?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_id);
            cstmt.setString(3, reportName);
            cstmt.setString(4, lstMail);
            cstmt.setString(5, timeSend);
            cstmt.setString(6, emailDetail);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
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
    
    public static int fn_deleteReport(String prn_id) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.fn_delete_report(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_id);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
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
    
        public  List<AutoEmailBO> fc_find_sql_to_report(String prs_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<AutoEmailBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.search_sql_to_report(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                AutoEmailBO item = new AutoEmailBO();
                item.setIdSql(rs.getString("id_sql") == null ? "" : rs.getString("id_sql"));
                item.setSqlName(rs.getString("sql_name") == null ? "" : rs.getString("sql_name"));
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
        
        public  List<AutoEmailBO> fc_find_sql_no_report(String prs_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<AutoEmailBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_auto_email.search_sql_no_report(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                AutoEmailBO item = new AutoEmailBO();
                item.setIdSql(rs.getString("id_sql") == null ? "" : rs.getString("id_sql"));
                item.setSqlName(rs.getString("sql_name") == null ? "" : rs.getString("sql_name"));
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
