package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
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
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.DataAuditForm;
import com.vnpt.media.rims.formbean.MonitoringJobAuditForm;
import com.vnpt.media.rims.formbean.NeLinkForm;
import com.vnpt.media.rims.jdbc.DbSql;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditDAO extends GenericDAO implements IDataAudit {

    private static Logger logger = LogManager.getLogger(NodeDAO.class);

    @Override
    public List<NodeBO> findAll(String startRow, String endRow, String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fc_find_all(?,?,?,?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(name);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
            vars.add(neTypeId);
            vars.add(venderId);
            vars.add(statusList);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NodeBO item = new NodeBO();
                    item.setId(rs.getLong("node_id"));
                    item.setNeTypeId(rs.getLong("ne_type_id"));
                    item.setCode(rs.getString("ma_node"));
                    item.setDonViId(rs.getLong("DONVI_ID"));
                    item.setCodeBuilding(rs.getString("ma_building"));
                    item.setTenNeType(rs.getString("ten_loai_ne"));
                    item.setTenThietBi(rs.getString("ten_thiet_bi"));
                    item.setDonViName(rs.getString("ten_don_vi"));
                    item.setAddress(rs.getString("DIA_CHI"));
                    item.setTrangThaiHDId(rs.getLong("TRANG_THAI_HD_ID"));
                    item.setTrangThaiQLId(rs.getLong("TRANG_THAI_QL_ID"));
                    item.setStatus(rs.getInt("status"));
                    item.setUserInsert(rs.getLong("USER_INSERT"));
                    return item;
                }
            }, vars);

            return (List<NodeBO>) list;
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
    public List<?> findDetail(String id, String neTypeId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fc_detail(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(neTypeId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    String neTypeId = rs.getString("ne_type_id");
                    if (neTypeId.equals("2") || neTypeId.equals("3") || neTypeId.equals("8")) //BTS
                    {
                        BTSInfoBO item = new BTSInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setTenNodeCha(rs.getString("ma_node_cha"));
                        item.setNodeChaId(rs.getLong("node_cha_id"));

                        item.setDonViId(rs.getLong("DONVI_ID"));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getLong("building_id"));
                        item.setTenNeType(rs.getString("ten_loai_ne"));

                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getString("Latitude"));
                        item.setLon(rs.getString("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getLong("thiet_bi_id"));
                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));

                        if (neTypeId.equals("2")) {
                            item.setName(rs.getString("TEN_BTS"));
                        } else if (neTypeId.equals("3")) {
                            item.setName(rs.getString("TEN_NODEB"));
                        } else if (neTypeId.equals("8")) {
                            item.setName(rs.getString("TEN_ENODEB"));
                        }
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setCauHinh(rs.getString("cau_hinh"));
                        item.setCosite2G3GType(rs.getInt("COSITE_2G_3G_TYPE"));
                        item.setMaCosite(rs.getString("MA_COSITE_2G_3G"));

                        item.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                        item.setTenBSCRNC(rs.getString("TEN_BSC_RNC"));
                        item.setTenBSCRNCQL(rs.getString("TEN_BSC_RNC_QL"));
                        item.setmSCMSS(rs.getString("MSC_MSS"));
                        item.setsGSN(rs.getString("SGSN"));
                        item.setdCHSPDA42M(rs.getString("DC_HSDPA_42M"));
                        item.setFilterUser(rs.getString("FILTER_USER"));
                        item.setFrequencyBand(rs.getString("FREQUENCY_BAND"));

                        item.setCodeTramDA(rs.getString("ma_tram_da"));
                        item.setTramDAId(rs.getLong("TRAM_DU_AN_ID"));
                        item.setStatus(rs.getInt("STATUS"));
                        return item;

                    } else if (neTypeId.equals("5") || neTypeId.equals("6") || neTypeId.equals("7")) //enodeB
                    {
                        CellInfoBO item = new CellInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setTenNodeCha(rs.getString("ma_node_cha"));
                        item.setDonViId(rs.getLong("DONVI_ID"));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getLong("building_id"));
                        item.setTenNeType(rs.getString("ten_loai_ne"));

                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getFloat("Latitude"));
                        item.setLon(rs.getFloat("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getLong("thiet_bi_id"));
                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));

                        item.setName(rs.getString("TEN_cell"));
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setAzimuth(rs.getLong("Azimuth"));
                        item.setMechanitalTilt(rs.getLong("MECHANICAL_TILT"));
                        item.setTotalTilt(rs.getLong("Total_Tilt"));
                        item.setAntennaType(rs.getLong("Antenna_Type"));
                        item.setAntennaHigh(rs.getLong("antenna_High"));
                        item.setAntennaGain(rs.getLong("antenna_Gain"));
                        item.setNoOfCarrier(rs.getLong("no_Of_Carrier"));
                        item.setBosterTma(rs.getString("boster_Tma"));

                        item.setCpichPower(rs.getString("cpich_Power"));

                        item.setTotalPower(rs.getString("total_Power"));
                        item.setSpecialCoverage(rs.getString("special_Coverage"));
                        item.setBlackListFlag(rs.getString("black_List_Flag"));
                        item.setLyDo(rs.getString("ly_Do"));
                        return item;
                    } else if (neTypeId.equals("11")) //BSC RNC
                    {
                        BSCRNCInfoBO item = new BSCRNCInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setTenNodeCha(rs.getString("ma_node_cha"));
                        item.setDonViId(rs.getLong("DONVI_ID"));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getLong("building_id"));
                        item.setTenNeType(rs.getString("ten_loai_ne"));
                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getFloat("Latitude"));
                        item.setLon(rs.getFloat("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getLong("thiet_bi_id"));
                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));

                        item.setName(rs.getString("TEN_BSC_RNC"));
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setTypeBSCRNC(rs.getString("TYPE_BSC_RNC"));

                        return item;
                    }
                    return null;
                }
            }, vars);

            return (List<NodeBO>) list;
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
    public int getTotalAll(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fc_total_all(?,?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(name);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
            vars.add(neTypeId);
            vars.add(venderId);
            vars.add(statusList);
            DbSql sqlTemplate = new DbSql(conn);
            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            return count;
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
    public List<DataAuditForm> findNodeLink(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_dataaudit.fc_list_grant(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DataAuditForm item = new DataAuditForm();
                    item.setDataAuditConfigId(rs.getLong("id"));
                    item.setNodeId(rs.getLong("node_id"));
                    item.setMaNode(rs.getString("ma_node"));
                    item.setUserId(rs.getLong("user_id"));
                    item.setUserName(rs.getString("username"));
                    item.setFullName(rs.getString("fullname"));
                    item.setEmail(rs.getString("email"));
                    item.setMsisdn(rs.getString("msisdn"));
                    return item;
                }
            }, vars);

            return (List<DataAuditForm>) list;
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
    public int addUserConf(DataAuditForm bean) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_dataaudit.add_user_node(?,?)}";

            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(bean.getNodeId());
            vars.add(bean.getUserId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            //int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            int count = sqlTemplate.executeUpdateFuncRet(querySql, vars).intValue();
            
            return count;

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
    public int removeUserNode(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_dataaudit.remove_user_node(?)}";

            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(id);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            //int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            int count = sqlTemplate.executeUpdateFuncRet(querySql, vars).intValue();
            
            return count;

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
    public List<MonitoringJobAuditBO> findMonitoringJobAudit(String neName, Date startDate, Date endDate) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_dataaudit.fc_list_monitoringJobAudit(?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(neName);
            vars.add(dateToString(startDate));
            vars.add(dateToString(endDate));
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MonitoringJobAuditBO item = new MonitoringJobAuditBO();
                    item.setNE_TYPE(rs.getLong("NE_TYPE"));
                    item.setNE_NAME(rs.getString("NE_NAME"));
                    item.setRI_Y_OMC_N(rs.getLong("RI_Y_OMC_N"));
                    item.setRI_Y_OMC_Y(rs.getLong("RI_Y_OMC_Y"));
                    item.setRI_N_OMC_Y(rs.getLong("RI_N_OMC_Y"));
                    item.setINSERT_DATE(rs.getDate("INSERT_DATE"));
                    return item;
                }
            }, vars);

            return (List<MonitoringJobAuditBO>) list;
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

    private String dateToString(Date date) {
        try {
            if (date != null) {
                SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
                return sp.format(date);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }
  
}
