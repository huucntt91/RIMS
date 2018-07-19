package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.utils.StringUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DAuditDAO extends GenericDAO implements IDAudit {

    private static Logger logger = LogManager.getLogger(NodeDAO.class);

    @Override
    public List<AuditSummaryBO> getSummary() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_LIST.fn_summary() }";
//       


            List<Object> vars = new Vector<Object>();

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            Map mapTemp = new HashMap<String, AuditSummaryBO>();
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AuditSummaryBO item = new AuditSummaryBO();
                    String name = rs.getString("name");
                    String title = rs.getString("title");
                    int type = rs.getInt("type");
                    long count = rs.getLong("c");
                    if (mapTemp.get(name) == null) {

                        if (type == 1) {
                            mapTemp.put(name, new AuditSummaryBO.Builder().totalRim(count).title(title).build());
                        }
                        if (type == 2) {
                            mapTemp.put(name, new AuditSummaryBO.Builder().totalDifference(count).title(title).build());
                        }
                        if (type == 3) {
                            mapTemp.put(name, new AuditSummaryBO.Builder().totalInventory(count).title(title).build());
                        }
                        AuditSummaryBO temp = (AuditSummaryBO) mapTemp.get(name);
                        temp.setName(name);
                        mapTemp.put(name, temp);
                    } else {
                        AuditSummaryBO temp = (AuditSummaryBO) mapTemp.get(name);
                        temp.setTitle(title);
                        if (type == 1) {
                            temp.setTotalRim(count);
                        }
                        if (type == 2) {
                            temp.setTotalDifference(count);
                        }
                        if (type == 3) {
                            temp.setTotalInventory(count);
                        }
                        mapTemp.put(name, temp);
                    }

                    return item;
                }
            }, vars);

            return new ArrayList<AuditSummaryBO>(mapTemp.values());

//            return (List<AuditSummaryBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<ComCell2gInfoBO> findAllComCell2gInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String tinh) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_COM_CELL2G_INFO.fc_find_all(?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(type);
            vars.add(status);
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tenTrenHeThong);
            vars.add(tinh);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ComCell2gInfoBO item = new ComCell2gInfoBO();
                    item.setId(rs.getLong("id"));
                    item.setiCellId(rs.getLong("i_cell2g_id"));
//                    item.setiCellCode(rs.getString("i_cell_code"));
                    item.setiCellName(rs.getString("i_cell2g_name"));
                    item.setiLac(rs.getLong("i_lac"));
                    item.setiCi(rs.getLong("i_ci"));
                    item.setiBscRncName(rs.getString("I_BSC_RNC_NAME"));
//                    item.setiDcHsdpa42M(rs.getString("I_DC_HSDPA_42M"));
                    item.setiFrequencyBand(rs.getString("I_FREQUENCY_BAND"));
                    item.setiBcch(rs.getLong("I_BCCH"));
                    item.setiBsic(rs.getString("I_BSIC"));
                    item.setiTch(rs.getString("I_TCH"));
                    item.setiTrxConfig(rs.getString("I_TRX_CONFIG"));
                    item.setiBtsNodeBName(rs.getString("I_BTS_NODEB_NAME"));
                    item.setiVendor(rs.getString("I_VENDOR"));
                    item.setiFileName(rs.getString("I_FILENAME"));

                    item.setrCellId(rs.getLong("r_cell2g_id"));
//                    item.setiCellCode(rs.getString("i_cell_code"));
                    item.setrCellName(rs.getString("r_cell2g_name"));
                    item.setrLac(rs.getLong("r_lac"));
                    item.setrCi(rs.getLong("r_ci"));
//                    item.setrBscRncName(rs.getString("I_BSC_RNC_NAME"));

                    item.setrFrequencyBand(rs.getString("r_FREQUENCY_BAND"));
                    item.setrBcch(rs.getLong("r_BCCH"));
                    item.setrBsic(rs.getString("r_BSIC"));
                    item.setrTch(rs.getString("r_TCH"));
                    item.setrTrxConfig(rs.getString("r_TRX_CONFIG"));
                    item.setrVendor(rs.getString("R_VENDOR"));

                    item.setCompareTime(rs.getDate("COMPARE_TIME"));
                    item.setStatus(rs.getInt("STATUS"));
                    item.setType(rs.getLong("TYPE"));
                    return item;
                }
            }, vars);

            return (List<ComCell2gInfoBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<ComCell3gInfoBO> findAllComCell3gInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String tinh) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_COM_CELL3G_INFO.fc_find_all(?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(type);
            vars.add(status);
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tenTrenHeThong);
            vars.add(tinh);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ComCell3gInfoBO item = new ComCell3gInfoBO();
                    item.setId(rs.getLong("id"));
                    item.setiCellId(rs.getLong("i_cell3g_id"));
//                    item.setiCellCode(rs.getString("i_cell_code"));
                    item.setiCellName(rs.getString("i_cell3g_name"));
                    item.setiLac(rs.getLong("i_lac"));
                    item.setiCi(rs.getLong("i_ci"));
//                    item.setiBscRncName(rs.getString("I_BSC_RNC_NAME"));
//                    item.setiDcHsdpa42M(rs.getString("I_DC_HSDPA_42M"));
                    item.setiFrequencyBand(rs.getString("I_FREQUENCY_BAND"));
                    item.setiDlPsc(rs.getString("I_DL_PSC"));
                    item.setiCpichPower(rs.getString("I_CPICH_POWER"));
                    item.setiTotalPower(rs.getString("I_TOTAL_POWER"));
                    item.setiMaxPower(rs.getString("I_MAX_POWER"));
                    item.setiBtsNodeBName(rs.getString("I_BTS_NODEB_NAME"));
                    item.setiVendor(rs.getString("I_VENDOR"));
//item.setiMaxPower(rs.getString("I_MAX_POWER"));
//item.setiMaxPower(rs.getString("I_MAX_POWER"));
                    item.setrCellId(rs.getLong("r_cell3g_id"));
//                    item.setiCellCode(rs.getString("i_cell_code"));
                    item.setrCellName(rs.getString("r_cell3g_name"));
                    item.setrLac(rs.getLong("r_lac"));
                    item.setrCi(rs.getLong("r_ci"));
//                    item.setrBscRncName(rs.getString("I_BSC_RNC_NAME"));

                    item.setrFrequencyBand(rs.getString("r_FREQUENCY_BAND"));

                    item.setrDlPsc(rs.getString("R_DL_PSC"));
                    item.setrCpichPower(rs.getString("R_CPICH_POWER"));
                    item.setrTotalPower(rs.getString("R_TOTAL_POWER"));
                    item.setrMaxPower(rs.getString("R_MAX_POWER"));
                    item.setrVendor(rs.getString("R_VENDOR"));
                    item.setCompareTime(rs.getDate("COMPARE_TIME"));
                    item.setStatus(rs.getInt("STATUS"));
                    item.setType(rs.getLong("TYPE"));
                    return item;
                }
            }, vars);

            return (List<ComCell3gInfoBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<ComCell4gInfoBO> findAllComCell4gInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String tinh) throws DAOException {

        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_COM_CELL4G_INFO.fc_find_all(?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(type);
            vars.add(status);
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tenTrenHeThong);
            vars.add(tinh);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ComCell4gInfoBO item = new ComCell4gInfoBO();
                    item.setId(rs.getLong("id"));
                    item.setiCellId(rs.getLong("i_cell4g_id"));
//                    item.setiCellCode(rs.getString("i_cell_code"));
                    item.setiCellName(rs.getString("i_cell4g_name"));
                    item.setiLac(rs.getLong("i_lac"));
                    item.setiCi(rs.getLong("i_ci"));

                    item.setiFrequencyBand(rs.getString("I_FREQUENCY_BAND"));
                    item.setiPci(rs.getString("I_PCI"));
                    item.setiTac(rs.getString("I_TAC"));
                    item.setiLcrId(rs.getString("I_LCRID"));
                    item.setiVendor(rs.getString("I_VENDOR"));
                    item.setrCellId(rs.getLong("r_cell4g_id"));
//                    item.setiCellCode(rs.getString("i_cell_code"));
                    item.setrCellName(rs.getString("r_cell4g_name"));
                    item.setrLac(rs.getLong("R_lac"));
                    item.setrCi(rs.getLong("R_ci"));

                    item.setrFrequencyBand(rs.getString("R_FREQUENCY_BAND"));
                    item.setrPci(rs.getString("R_PCI"));
                    item.setrTac(rs.getString("R_TAC"));
                    item.setrLcrId(rs.getString("R_LCRID"));
                    item.setrVendor(rs.getString("R_VENDOR"));
                    item.setCompareTime(rs.getDate("COMPARE_TIME"));
                    item.setStatus(rs.getInt("STATUS"));
                    item.setType(rs.getLong("TYPE"));
                    return item;
                }
            }, vars);

            return (List<ComCell4gInfoBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<ComBtsInfoBO> findAllComBtsInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String tinh) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_COM_BTS_INFO.fc_find_all(?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(type);
            vars.add(status);
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tenTrenHeThong);
            vars.add(tinh);
            
//            
//            
//            
//            

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ComBtsInfoBO item = new ComBtsInfoBO();

                    item.setId(rs.getLong("id"));
                    item.setiBtsId(rs.getLong("I_BTS_ID"));
                    item.setiBtsName(rs.getString("I_BTS_NAME"));
//                    item.setiBtsCode(rs.getString("I_BTS_CODE"));
                    item.setiBscRncName(rs.getString("I_BSC_RNC_NAME"));
                    item.setiBscRncMngName(rs.getString("I_BSC_RNC_MNG_NAME"));
//                    item.setiMscMss(rs.getString("I_MSC_MSS"));
//                    item.setiSgsn(rs.getString("I_SGSN"));
//                    item.setiDcHsdpa42M(rs.getString("I_DC_HSDPA_42M"));
                    item.setiFilterUser(rs.getString("I_FILTER_USER"));
                    item.setiFrequencyBand(rs.getString("I_FREQUENCY_BAND"));
                    item.setiVendor(rs.getString("I_VENDOR"));
                    item.setrBtsId(rs.getLong("R_BTS_ID"));
                    item.setrBtsName(rs.getString("R_BTS_NAME"));
//                    item.setrBtsCode(rs.getString("R_BTS_CODE"));
                    item.setrBscRncName(rs.getString("R_BSC_RNC_NAME"));
                    item.setrBscRncMngName(rs.getString("R_BSC_RNC_MNG_NAME"));
//                    item.setrMscMss(rs.getString("R_MSC_MSS"));
//                    item.setrSgsn(rs.getString("R_SGSN"));
//                    item.setrDcHsdpa42M(rs.getString("R_DC_HSDPA_42M"));
                    item.setrFilterUser(rs.getString("R_FILTER_USER"));
                    item.setrFrequencyBand(rs.getString("R_FREQUENCY_BAND"));
                    item.setrVendor(rs.getString("R_VENDOR"));
                    item.setCompareTime(rs.getDate("COMPARE_TIME"));
                    item.setType(rs.getLong("TYPE"));
                    item.setStatus(rs.getInt("status"));
                    return item;
                }
            }, vars);
            
            return (List<ComBtsInfoBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<ComNodeBInfoBO> findAllComNodeBInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String tinh) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_COM_NODEB_INFO.fc_find_all(?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(type);
            vars.add(status);
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tenTrenHeThong);
            vars.add(tinh);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ComNodeBInfoBO item = new ComNodeBInfoBO();

                    item.setId(rs.getLong("id"));
                    item.setiNodebId(rs.getLong("I_NODEB_ID"));
//                    item.setiNodebCode(rs.getString("I_NODEB_CODE"));
                    item.setiNodebName(rs.getString("I_NODEB_NAME"));
                    item.setiBscRncName(rs.getString("I_BSC_RNC_NAME"));
                    item.setiBscRncMngName(rs.getString("I_BSC_RNC_MNG_NAME"));
//                    item.setiMscMss(rs.getString("I_MSC_MSS"));
//                    item.setiSgsn(rs.getString("I_SGSN"));
                    item.setiDcHsdpa42M(rs.getString("I_DC_HSDPA_42M"));
                    item.setiFilterUser(rs.getString("I_FILTER_USER"));
                    item.setiFrequencyBand(rs.getString("I_FREQUENCY_BAND"));
                    item.setiVendor(rs.getString("I_VENDOR"));
                    item.setrNodebId(rs.getLong("R_NODEB_ID"));
//                    item.setrNodebCode(rs.getString("R_NODEB_CODE"));
                    item.setrNodeBName(rs.getString("R_NODEB_NAME"));
                    item.setrBscRncName(rs.getString("R_BSC_RNC_NAME"));
                    item.setrBscRncMngName(rs.getString("R_BSC_RNC_MNG_NAME"));
//                    item.setrMscMss(rs.getString("R_MSC_MSS"));
//                    item.setrSgsn(rs.getString("R_SGSN"));
                    item.setrDcHsdpa42M(rs.getString("R_DC_HSDPA_42M"));
                    item.setrFilterUser(rs.getString("R_FILTER_USER"));
                    item.setrFrequencyBand(rs.getString("R_FREQUENCY_BAND"));
                    item.setrVendor(rs.getString("R_VENDOR"));
                    item.setCompareTime(rs.getDate("COMPARE_TIME"));
                    item.setType(rs.getLong("TYPE"));

                    return item;
                }
            }, vars);

            return (List<ComNodeBInfoBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<ComEnodeBInfoBO> findAllComEnodeBInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String tinh) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_COM_ENODEB_INFO.fc_find_all(?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(type);
            vars.add(status);
            vars.add(startRow);
            vars.add(endRow);
            vars.add(tenTrenHeThong);
            vars.add(tinh);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ComEnodeBInfoBO item = new ComEnodeBInfoBO();

                    item.setId(rs.getLong("id"));
                    item.setiEnodebId(rs.getLong("I_ENODEB_ID"));
//                    item.setiEnodebCode(rs.getString("I_ENODEB_CODE"));
                    item.setiEnodebName(rs.getString("I_ENODEB_NAME"));
                    item.setiBscRncName(rs.getString("I_BSC_RNC_NAME"));
                    item.setiBscRncMngName(rs.getString("I_BSC_RNC_MNG_NAME"));
                    item.setiMscMss(rs.getString("I_MSC_MSS"));
                    item.setiSgsn(rs.getString("I_SGSN"));
                    item.setiDcHsdpa42M(rs.getString("I_DC_HSDPA_42M"));
                    item.setiFilterUser(rs.getString("I_FILTER_USER"));
                    item.setiFrequencyBand(rs.getString("I_FREQUENCY_BAND"));
                    item.setiVendor(rs.getString("I_VENDOR"));
                    item.setReNodebId(rs.getLong("R_ENODEB_ID"));
//                    item.setrEnodebCode(rs.getString("R_ENODEB_CODE"));
                    item.setrEnodebName(rs.getString("R_ENODEB_NAME"));
                    item.setrBscRncName(rs.getString("R_BSC_RNC_NAME"));
                    item.setrBscRncMngName(rs.getString("R_BSC_RNC_MNG_NAME"));
                    item.setrMscMss(rs.getString("R_MSC_MSS"));
                    item.setrSgsn(rs.getString("R_SGSN"));
                    item.setrDcHsdpa42M(rs.getString("R_DC_HSDPA_42M"));
                    item.setrFilterUser(rs.getString("R_FILTER_USER"));
                    item.setrFrequencyBand(rs.getString("R_FREQUENCY_BAND"));
                    item.setrVendor(rs.getString("R_VENDOR"));
                    item.setCompareTime(rs.getDate("COMPARE_TIME"));
                    item.setType(rs.getLong("TYPE"));

                    return item;
                }
            }, vars);

            return (List<ComEnodeBInfoBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int getTotalComInfo(Long id, String type, int status, String name, String tenTrenHeThong, String tinh) throws DAOException {

        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_COM_CELL2g_INFO.fc_total(?,?,?,?,?) }";
            if (name.equals("cell2g_info")) {
                querySql = "{? = call PKG_COM_CELL2g_INFO.fc_total(?,?,?,?,?) }";
            } else if (name.equals("cell3g_info")) {
                querySql = "{? = call PKG_COM_CELL3g_INFO.fc_total(?,?,?,?,?) }";
            } else if (name.equals("cell4g_info")) {
                querySql = "{? = call PKG_COM_CELL4g_INFO.fc_total(?,?,?,?,?) }";
            } else if (name.equals("bts_info")) {
                querySql = "{? = call PKG_COM_BTS_INFO.fc_total(?,?,?,?,?) }";
            } else if (name.equals("nodeb_info")) {
                querySql = "{? = call PKG_COM_NODEB_INFO.fc_total(?,?,?,?,?) }";
            } else if (name.equals("enodeb_info")) {
                querySql = "{? = call PKG_COM_ENODEB_INFO.fc_total(?,?,?,?,?) }";
            }

//       
            
            
            
            
            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(type);
            vars.add(status);
            vars.add(tenTrenHeThong);
            vars.add(tinh);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int total = sqlTemplate.queryFunctionForInt(querySql, vars);
            return total;

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }

    }

    public int updateStatusCell2gComInfo(String id, int status) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call PKG_COM_CELL2G_INFO.fc_update_status(?,?)}";

            if (!StringUtils.hasText(id)) {
                return -1;
            }



            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(status);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                return count;
            } else {

                throw new DAOException();

            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }

    }

    public int updateStatusCell3gComInfo(String id, int status) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call PKG_COM_CELL3G_INFO.fc_update_status(?,?)}";

            if (!StringUtils.hasText(id)) {
                return -1;
            }



            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(status);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                return count;
            } else {

                throw new DAOException();

            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }

    }

    public int updateStatusCell4gComInfo(String id, int status) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call PKG_COM_CELL4G_INFO.fc_update_status(?,?)}";

            if (!StringUtils.hasText(id)) {
                return -1;
            }



            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(status);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                return count;
            } else {

                throw new DAOException();

            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }

    }

    public int updateStatusBtsComInfo(String id, int status) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call PKG_COM_BTS_INFO.fc_update_status(?,?)}";

            if (!StringUtils.hasText(id)) {
                return -1;
            }



            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(status);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                return count;
            } else {

                throw new DAOException();

            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }

    }

    public int updateStatusNodebComInfo(String id, int status) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call PKG_COM_NODEB_INFO.fc_update_status(?,?)}";

            if (!StringUtils.hasText(id)) {
                return -1;
            }



            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(status);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                return count;
            } else {

                throw new DAOException();

            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }

    }

    public int updateStatusEnodeBComInfo(String id, int status) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call PKG_COM_ENODEB_INFO.fc_update_status(?,?)}";

            if (!StringUtils.hasText(id)) {
                return -1;
            }



            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(status);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                return count;
            } else {

                throw new DAOException();

            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }

    }
}
