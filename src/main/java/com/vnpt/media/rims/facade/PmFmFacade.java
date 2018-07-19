package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PmFmFacade {

    private static final String PMFM_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("PMFM_DS");
//    private static final String RNAS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RNAS_DS");
    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public List<PmFmBO> getPm(String vnpCode, String nodeType, String kpiType, Date startDate, Date endDate, String listKpi) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<PmFmBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(PMFM_DS);
            String sql = "begin ?:=pkg_pm_fm.get_pm_info(?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, vnpCode);
            cs.setString(3, nodeType);
            cs.setString(4, kpiType);
            cs.setString(5, dateToString(startDate));
            cs.setString(6, dateToString(endDate));
            cs.setString(7, listKpi);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            String[] columns = listKpi.split(",");

            while (rs.next()) {
                PmFmBO item = new PmFmBO();
                item.setVnpCode(rs.getString("OBJECT_CODE"));
                item.setNodeType(nodeType);
                item.setCreateTime(rs.getDate("STA_DATETIME"));
                List<PmBO> list = new ArrayList<>();
                if (columns != null) {
                    for (String column : columns) {
                        if (column != null && !column.trim().equalsIgnoreCase("")) {
                            PmBO pm = new PmBO();
                            pm.setCode(column.trim());
                            if (nodeType.equalsIgnoreCase("cell2g") || nodeType.equalsIgnoreCase("bts")) {
                                pm.setValue(rs.getFloat(column.trim() + "_2G"));
                            } else if (nodeType.equalsIgnoreCase("cell3g") || nodeType.equalsIgnoreCase("nodeb")) {
                                pm.setValue(rs.getFloat(column.trim() + "_3G"));
                            }
                            list.add(pm);
                        }
                    }
                }
                item.setList(list);
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
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public List<KpiBO> getKpiBOs(String kpi_mapping_id, String nodeType) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<KpiBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_PM_FM.getKpiLst(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, kpi_mapping_id);
            cs.setString(3, nodeType);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                KpiBO item = new KpiBO();
                item.setKpiMappingId(rs.getInt("kpi_mapping_id"));
                item.setCode(rs.getString("code"));
                item.setName(rs.getString("name"));
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
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public int countKPI(String vnpCode, String nodeType, String kpiType, Date startDate, Date endDate, String listKpi) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        int count = 0;
        try {
            conn = EnvManager.getDbConnection(PMFM_DS);
            String sql = "begin ?:=pkg_pm_fm.get_pm_info(?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, vnpCode);
            cs.setString(3, nodeType);
            cs.setString(4, kpiType);
            cs.setString(5, dateToString(startDate));
            cs.setString(6, dateToString(endDate));
            cs.setString(7, listKpi);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                count++;
            }

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
        return count;
    }

    public List<EventBO> get_event_lst(String object_type) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<EventBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_PM_FM.get_event_lst(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, object_type);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                EventBO item = new EventBO();
                item.setEvent_define_id(rs.getInt("event_define_id"));
                item.setEvent_define_code(rs.getString("event_define_code"));
                item.setEvent_define_name(rs.getString("event_define_name"));
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
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public List<PmFmBO> getFm(String vnpCode, Date startDate, Date endDate, String events) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<PmFmBO> arrayList = new ArrayList<>();
        try {
            if (events.endsWith(",")) {
                events = events.substring(0, events.length() - 1);
            }
            conn = EnvManager.getDbConnection(PMFM_DS);
            String sql = "begin ?:=pkg_pm_fm.get_fm_info(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, vnpCode);
            cs.setString(3, events);
            cs.setString(4, dateToString(startDate));
            cs.setString(5, dateToString(endDate));
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                PmFmBO item = new PmFmBO();
                item.setVnpCode(rs.getString("OBJECT_CODE"));
                item.setCreateTime(rs.getDate("STA_DATETIME"));
                item.setEvent_define_name(rs.getString("EVENT_DEFINE_NAME"));
                item.setEvent_description(rs.getString("event_description"));
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
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    private String dateToString(Date date) {
        try {
            if (date != null) {
                SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
                return sp.format(date);
            }
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
        }
        return null;

    }

}
