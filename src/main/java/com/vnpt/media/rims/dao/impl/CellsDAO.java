package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.BaoDuongNetExcel;
import com.vnpt.media.rims.bean.Cell2GNewExcelModel;
import com.vnpt.media.rims.bean.Cell2GUpdateExcelModel;
import com.vnpt.media.rims.bean.Cell3GNewExcelModel;
import com.vnpt.media.rims.bean.Cell3GUpdateExcelModel;
import com.vnpt.media.rims.bean.Cell4GNewExcelModel;
import com.vnpt.media.rims.bean.Cell4GUpdateExcelModel;
import com.vnpt.media.rims.bean.CellUpdateExcelNetModel;
import com.vnpt.media.rims.bean.ExcelDeleteNodeBO;
import com.vnpt.media.rims.bean.KiemDinhNetExcel;
import com.vnpt.media.rims.bean.OmcCell2gInfoBO;
import com.vnpt.media.rims.bean.OmcCell3gInfoBO;
import com.vnpt.media.rims.bean.OmcCell4gInfoBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.util.List;
import java.util.Vector;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.Cell2gRegForm;
import com.vnpt.media.rims.formbean.Cell3gRegForm;
import com.vnpt.media.rims.formbean.Cell4gRegForm;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.jdbc.SQLTemplate;

public class CellsDAO extends GenericDAO implements ICells {

    private static Logger logger = LogManager.getLogger(CellsDAO.class);

    @Override
    public int addCell2g(Cell2gRegForm cell2gRegForm, long userId, String maNode) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_reg_cell_2g(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(cell2gRegForm.getBtsCode());
            vars.add(cell2gRegForm.getTenCell());
            vars.add(cell2gRegForm.getHoanCanhRaDoi());
            vars.add(cell2gRegForm.getNgayHoatDong());
            vars.add(cell2gRegForm.getTenTrenHeThong());
            vars.add(cell2gRegForm.getLac());
            vars.add(cell2gRegForm.getCi());
            vars.add(cell2gRegForm.getBangTanId());
            vars.add(cell2gRegForm.getThietBiId());
            vars.add(cell2gRegForm.getLoaiTramId());
            vars.add(maNode);
            vars.add(1);
            vars.add("");
            vars.add("");

            vars.add(userId);
            vars.add(Constants.NE_REG_ON);
            vars.add("");
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int updateAddCell2g(String nodeId, OmcCell2gInfoBO cell2gRegForm, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_reg_cell_2g(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();

            vars.add(nodeId);
            vars.add(cell2gRegForm.getMaNodeCha());
            vars.add(cell2gRegForm.getTenCell());
            vars.add(cell2gRegForm.getHoanCanhRaDoi());
            vars.add(cell2gRegForm.getNgayHoatDong());
            vars.add(cell2gRegForm.getTenTrenHeThong());
            vars.add(cell2gRegForm.getLac());
            vars.add(cell2gRegForm.getCi());
            vars.add(cell2gRegForm.getBangTanId());
            vars.add(cell2gRegForm.getThietBiId());
            vars.add(cell2gRegForm.getLoaiTramId());
            vars.add("");
            vars.add(2);
            vars.add(1);
            vars.add("");
            vars.add(userId);
            vars.add(Constants.NE_REG_ON);
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public String addCell2gExcel(String permission, Cell2GNewExcelModel cell2gRegForm, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_reg_cell_2g_excel(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(permission);
            vars.add(userId);
            vars.add(cell2gRegForm.getMaNodeCha());
            vars.add(cell2gRegForm.getTen_cell());
            vars.add(cell2gRegForm.getHoanCanhRaDoi());
            vars.add(cell2gRegForm.getNgayHoatDong());
            vars.add(cell2gRegForm.getTenTrenHeThong());
            vars.add(cell2gRegForm.getLac());
            vars.add(cell2gRegForm.getCi());
            vars.add(cell2gRegForm.getTenThietBi());
            vars.add(cell2gRegForm.getTenTram());
            vars.add(cell2gRegForm.getNoOfCarrier());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String iRet = sqlTemplate.executeUpdateFunction2(querySql, vars);

            return iRet;
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
    public String addCell3gExcel(String permission, Cell3GNewExcelModel cell3gRegForm, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_reg_cell_3g_excel(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(permission == null ? "" : permission);

            vars.add(userId);
            vars.add(cell3gRegForm.getMaNodeCha());
            vars.add(cell3gRegForm.getTen_cell());
            vars.add(cell3gRegForm.getHoanCanhRaDoi());
            vars.add(cell3gRegForm.getNgayHoatDong());
            vars.add(cell3gRegForm.getTenTrenHeThong());
            vars.add(cell3gRegForm.getLac());
            vars.add(cell3gRegForm.getCi());
            vars.add(cell3gRegForm.getTenThietBi());
            vars.add(cell3gRegForm.getTenTram());
            vars.add(cell3gRegForm.getNoOfCarrier());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String iRet = sqlTemplate.executeUpdateFunction2(querySql, vars);

            return iRet;
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
    public String addCell4gExcel(String permission, Cell4GNewExcelModel cell4gRegForm, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_reg_cell_4g_excel(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(permission);
            vars.add(userId);
            vars.add(cell4gRegForm.getEnodebId());
            vars.add(cell4gRegForm.getTen_cell());
            vars.add(cell4gRegForm.getHoanCanhRaDoi());
            vars.add(cell4gRegForm.getNgayHoatDong());
            vars.add(cell4gRegForm.getTenTrenHeThong());
            vars.add(cell4gRegForm.getLac());
            vars.add(cell4gRegForm.getCi());
            vars.add(cell4gRegForm.getTag());
            vars.add(cell4gRegForm.getFrequenctyBand());
            vars.add(cell4gRegForm.getTenThietBi());
            vars.add(cell4gRegForm.getTenTram());
            vars.add(cell4gRegForm.getNoOfCarrier());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String iRet = sqlTemplate.executeUpdateFunction2(querySql, vars);

            return iRet;
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
    public int addCell3g(Cell3gRegForm cell3gRegForm, long userId, String maNode) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_reg_cell_3g(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();

            vars.add(cell3gRegForm.getBtsCode());
            vars.add(cell3gRegForm.getTenCell());
            vars.add(cell3gRegForm.getHoanCanhRaDoi());
            vars.add(cell3gRegForm.getNgayHoatDong());
            vars.add(cell3gRegForm.getTenTrenHeThong());
            vars.add(cell3gRegForm.getLac());
            vars.add(cell3gRegForm.getCi());
            vars.add(cell3gRegForm.getBangTanId());
            vars.add(cell3gRegForm.getThietBiId());
            vars.add(cell3gRegForm.getLoaiTramId());
            vars.add(maNode);
            vars.add(2);
            vars.add(1);
            vars.add("");
            vars.add(userId);
            vars.add(Constants.NE_REG_ON);
            vars.add(cell3gRegForm.getNoOfCarrier());
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int addCell4g(Cell4gRegForm cell4gRegForm, long userId, String maNode) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_reg_cell_4g(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(cell4gRegForm.getBtsCode());
            vars.add(cell4gRegForm.getTenCell());
            vars.add(cell4gRegForm.getHoanCanhRaDoi());
            vars.add(cell4gRegForm.getNgayHoatDong());
            vars.add(cell4gRegForm.getTenTrenHeThong());
            vars.add(cell4gRegForm.getLac());
            vars.add(cell4gRegForm.getCi());
            vars.add(cell4gRegForm.getTac());
            vars.add(cell4gRegForm.getBangTanId());
            vars.add(cell4gRegForm.getThietBiId());
            vars.add(cell4gRegForm.getLoaiTramId());
            vars.add(maNode);
            vars.add(1);
            vars.add("");
            vars.add("");
            vars.add(userId);
            vars.add(Constants.NE_REG_ON);
            vars.add(cell4gRegForm.getNoOfCarrier());
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
//        return 1;
    }

    @Override
    public int updateAddCell3g(String nodeId, OmcCell3gInfoBO cell3gRegForm, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_reg_cell_3g(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
//            
            vars.add(nodeId);
            vars.add(cell3gRegForm.getMaNodeCha());
            vars.add(cell3gRegForm.getTenCell());
            vars.add(cell3gRegForm.getNgayHoatDong());
            vars.add(cell3gRegForm.getTenTrenHeThong());
            vars.add(cell3gRegForm.getLac());
            vars.add(cell3gRegForm.getCi());
            vars.add(cell3gRegForm.getBangTanId());
            vars.add(cell3gRegForm.getThietBiId());
            vars.add(cell3gRegForm.getLoaiTramId());

            vars.add("");
            vars.add(2);
            vars.add(1);
            vars.add("");
            vars.add(userId);
            vars.add(Constants.NE_REG_ON);
            vars.add(cell3gRegForm.getNoOfCarrier());
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int updateAddCell4g(String nodeId, OmcCell4gInfoBO cell4gRegForm, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_reg_cell_4g(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(nodeId);
            vars.add(cell4gRegForm.getMaNodeCha());
            vars.add(cell4gRegForm.getTenCell());
            vars.add(cell4gRegForm.getNgayHoatDong());
            vars.add(cell4gRegForm.getTenTrenHeThong());
            vars.add(cell4gRegForm.getLac());
            vars.add(cell4gRegForm.getCi());
            vars.add(cell4gRegForm.getBangTanId());
            vars.add(cell4gRegForm.getThietBiId());
            vars.add(cell4gRegForm.getLoaiTramId());

            vars.add("");
            vars.add(2);
            vars.add(1);
            vars.add("");
            vars.add(userId);
            vars.add(Constants.NE_REG_ON);
            vars.add(cell4gRegForm.getNoOfCarrier());
//            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
//        return 1;
    }
//    @Override
//    public List<Cell2GList> findCell(Long nodeId, String statusList, String tinhTpId, String neTypeId) throws ServiceException {
//
//        Connection conn = null;
//        try {
//            conn = this.getConnection();
//            String querySql = "{? = call PKG_CELL.fn_find_all(?,?,?,?)}";
//
//
//            List<Object> vars = new Vector<Object>();
////            vars.add(id);
////            }
////            if (StringUtils.hasText(name)) {
////            vars.add("%" + name.toLowerCase() + "%");
////            }
//            
//            
//            
//            vars.add(nodeId);
//            vars.add(statusList);
//            vars.add(tinhTpId);
//            vars.add(neTypeId);
//            SQLTemplate sqlTemplate = new SQLTemplate(conn);
//
//            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
//                @Override
//                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    Cell2GList temp = new Cell2GList();
//                    temp.setNodeId(rs.getLong("node_Id"));
//                    temp.setCode(rs.getString("ma_node"));
//                    temp.setStatus(rs.getInt("status"));
//                    Long nodeChaId = rs.getLong("NODE_CHA_ID");
//                    temp.setNodeChaId(rs.wasNull() ? null : nodeChaId);
//                    temp.setBtsCode(rs.getString("ma_cha"));
//                    temp.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
//                    temp.setNgayHoatDong(rs.getDate("ngay_hoat_dong"));
//                    temp.setTenTrenHeThong(rs.getString("ten_tren_he_thong"));
//                    Long lac = rs.getLong("lac");
//                    temp.setLac(rs.wasNull() ? null : lac);
//                    Long ci = rs.getLong("ci");
//                    temp.setCi(rs.wasNull() ? null : ci);
//                    temp.setBangTanName(rs.getString("TEN_BANG_TAN"));
//                    temp.setTenLoaiTram(rs.getString("TEN_LOAI_TRAM"));
//                    temp.setTenTinhTP(rs.getString("TEN_TINH_TP"));
//
//                    return temp;
//                }
//
//            }, vars);
//
//            return (List<Cell2GList>) list;
//        } catch (ConnectionException e) {
//            logger.error("ConnectionException :", e);
//            throw new DAOException(e);
//        } catch (JdbcException e) {
//            logger.error("JdbcException :", e);
//            throw new DAOException(e);
//        } catch (Exception e) {
//            logger.error("Exception :", e);
//            throw new DAOException(e);
//        }
//    }

    @Override
    public int updateCell2g(OmcCell2gInfoBO cell, long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_cell_2g(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(cell.getId());
            vars.add(cell.getCode());
            vars.add(cell.getTenCell());
            vars.add(cell.getListCellGroupId());
//            
//            
//            
            vars.add(cell.getHoanCanhRaDoi());
            vars.add(cell.getNgayHoatDong());
            vars.add(cell.getTenTrenHeThong());
            vars.add(cell.getLac());
            vars.add(cell.getCi());
            vars.add(cell.getBangTanId());
            vars.add(cell.getThietBiId());
            vars.add(cell.getLoaiTramId());
            vars.add(cell.getNgayKiemDuyet());
            vars.add(cell.getNote());
            vars.add(cell.getTrangThaiHDId());
            vars.add(cell.getTrangThaiQLId());
            vars.add(cell.getAzimuth());
            vars.add(cell.getMechanicalTilt());
            vars.add(cell.getTotalTilt());
            vars.add(cell.getAntennaHigh());
            vars.add(cell.getAntennaType());
            vars.add(cell.getAntennaGain());
            vars.add(cell.getCellType());
            vars.add(cell.getVnpCode());
            vars.add(cell.getNgayDangKy());
            vars.add(cell.getNgayCapPhep());
            vars.add(cell.getElectricalTilt());
            vars.add(cell.getAntennaModel());
            vars.add(cell.getAntennaPattern());
            vars.add(cell.getBosterTma());
            vars.add(cell.getSpecialCoverage());
            vars.add(cell.getBcch());
            vars.add(cell.getBsic());
            vars.add(cell.getTch());
            vars.add(cell.getTrxConfig());
            vars.add(cell.getTenNgQLy());
            vars.add(cell.getSDTQLy());
            vars.add(cell.getBuildingId() != null && cell.getBuildingId() == 0 ? null : cell.getBuildingId());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public String updateCell2gExcel(String permission, Cell2GUpdateExcelModel cell2gRegForm, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_cell_2g_excel(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(userId);
            vars.add(permission);
            vars.add(cell2gRegForm.getCode());
            vars.add(cell2gRegForm.getBtsCode());
            vars.add(cell2gRegForm.getTen_cell());

            vars.add(cell2gRegForm.getHoanCanhRaDoi());
            vars.add(cell2gRegForm.getNgayHoatDong());
            vars.add(cell2gRegForm.getTenTrenHeThong());

            vars.add(cell2gRegForm.getLac());
            vars.add(cell2gRegForm.getCi());
            vars.add(cell2gRegForm.getLoaiCN());
            vars.add(cell2gRegForm.getFrequenctyBand());
            vars.add(cell2gRegForm.getTenThietBi());
            vars.add(cell2gRegForm.getTenTram());
            vars.add(cell2gRegForm.getNgaypheduyet());
            vars.add(cell2gRegForm.getLydo());
            vars.add(cell2gRegForm.getAzimuth());
            vars.add(cell2gRegForm.getMechanicalTilt());
            vars.add(cell2gRegForm.getTotalTilt());
            vars.add(cell2gRegForm.getAntennaHigh());
            vars.add(cell2gRegForm.getAntennaType());
            vars.add(cell2gRegForm.getAntennaGain());
            vars.add(cell2gRegForm.getNhomCell());
            vars.add(cell2gRegForm.getCellType());
            vars.add(cell2gRegForm.getVnpCode());
            vars.add(cell2gRegForm.getNgaydangky());
            vars.add(cell2gRegForm.getNgaycapphep());
            vars.add(cell2gRegForm.getElectricalTilt());
            vars.add(cell2gRegForm.getAntennaModel());
            vars.add(cell2gRegForm.getAntennaParttern());
            vars.add(cell2gRegForm.getBosterTma());
            vars.add(cell2gRegForm.getSpecialCoverage());
            vars.add(cell2gRegForm.getBcch());
            vars.add(cell2gRegForm.getBsic());
            vars.add(cell2gRegForm.getTch());
            vars.add(cell2gRegForm.getTrxConfig());
            vars.add(cell2gRegForm.getTenNgQly());
            vars.add(cell2gRegForm.getSdtNgQly());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String iRet = sqlTemplate.executeUpdateFunction2(querySql, vars);

            return iRet;
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
    public String updateCellNetRFExcel(String permission, CellUpdateExcelNetModel cell, long userId, String type) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_cell_net_excel(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(userId);
            vars.add(permission);
            vars.add(cell.getCode());
            vars.add(cell.getAzimuth());
            vars.add(cell.getMechanicalTilt());
            vars.add(cell.getTotalTilt());
            vars.add(cell.getAntennaHigh());
            vars.add(cell.getAntennaType());
            vars.add(cell.getAntennaGain());
            vars.add(cell.getAntennaHignGain());
            vars.add(cell.getSpecialCoverage());
            vars.add(type);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String iRet = sqlTemplate.executeUpdateFunction2(querySql, vars);

            return iRet;
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
    public String updateCell3gExcel(String permission, Cell3GUpdateExcelModel cell3gRegForm, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_cell_3g_excel(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();

            vars.add(userId);
            vars.add(permission);
            vars.add(cell3gRegForm.getCode());
            vars.add(cell3gRegForm.getBtsCode());
            vars.add(cell3gRegForm.getTen_cell());
            vars.add(cell3gRegForm.getHoanCanhRaDoi());
            vars.add(cell3gRegForm.getNgayHoatDong());
            vars.add(cell3gRegForm.getTenTrenHeThong());
            vars.add(cell3gRegForm.getLac());
            vars.add(cell3gRegForm.getCi());
            vars.add(cell3gRegForm.getLoaiCN());
            vars.add(cell3gRegForm.getFrequenctyBand());
            vars.add(cell3gRegForm.getTenThietBi());
            vars.add(cell3gRegForm.getTenTram());
            vars.add(cell3gRegForm.getNgaypheduyet());
            vars.add(cell3gRegForm.getLydo());
            vars.add(cell3gRegForm.getAzimuth());
            vars.add(cell3gRegForm.getMechanicalTilt());
            vars.add(cell3gRegForm.getTotalTilt());
            vars.add(cell3gRegForm.getAntennaHigh());
            vars.add(cell3gRegForm.getAntennaType());
            vars.add(cell3gRegForm.getAntennaGain());
            vars.add(cell3gRegForm.getNhomCell());

            vars.add(cell3gRegForm.getCellType());
            vars.add(cell3gRegForm.getNgaydangky());
            vars.add(cell3gRegForm.getNgaycapphep());
            vars.add(cell3gRegForm.getElectricalTitl());
            vars.add(cell3gRegForm.getAntennaModel());
            vars.add(cell3gRegForm.getAntennaParttern());

            vars.add(cell3gRegForm.getBosterTma());
            vars.add(cell3gRegForm.getSpecialCoverage());
            vars.add(cell3gRegForm.getNoOfCarrier());
            vars.add(cell3gRegForm.getDL_PSC());
            vars.add(cell3gRegForm.getCPICH_POWER());
            vars.add(cell3gRegForm.getTotalPower());
            vars.add(cell3gRegForm.getMaxPower());
            vars.add(cell3gRegForm.getTenNgQL());
            vars.add(cell3gRegForm.getSdtNgQL());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String iRet = sqlTemplate.executeUpdateFunction2(querySql, vars);

            return iRet;
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
    public String updateCell4gExcel(String permission, Cell4GUpdateExcelModel cell4gRegForm, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_cell_4g_excel(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();

            vars.add(userId);
            vars.add(permission);
            vars.add(cell4gRegForm.getCode());
            vars.add(cell4gRegForm.getBtsCode());
            vars.add(cell4gRegForm.getTen_cell());
            vars.add(cell4gRegForm.getHoanCanhRaDoi());
            vars.add(cell4gRegForm.getNgayHoatDong());
            vars.add(cell4gRegForm.getTenTrenHeThong());
            vars.add("");
            vars.add(cell4gRegForm.getCi());
            vars.add("");
            vars.add(cell4gRegForm.getFrequenctyBand());
            vars.add(cell4gRegForm.getTenThietBi());
            vars.add(cell4gRegForm.getTenTram());
            vars.add(cell4gRegForm.getNgaypheduyet());
            vars.add(cell4gRegForm.getLydo());
            vars.add(cell4gRegForm.getAzimuth());
            vars.add(cell4gRegForm.getMechanicalTilt());
            vars.add(cell4gRegForm.getTotalTilt());
            vars.add(cell4gRegForm.getAntennaHigh());
            vars.add(cell4gRegForm.getAntennaType());
            vars.add(cell4gRegForm.getAntennaGain());
            vars.add(cell4gRegForm.getNhomCell());

            vars.add(cell4gRegForm.getCellType());
            vars.add(cell4gRegForm.getNgaydangky());
            vars.add(cell4gRegForm.getNgaycapphep());
            vars.add(cell4gRegForm.getElectricalTilt());
            vars.add(cell4gRegForm.getAntennaModel());
            vars.add(cell4gRegForm.getAntennaParttern());

            vars.add(cell4gRegForm.getBosterTma());
            vars.add(cell4gRegForm.getSpecialCoverage());
            vars.add(cell4gRegForm.getNoOfCarrier());
            vars.add(cell4gRegForm.getPci());
            vars.add(cell4gRegForm.getTac());
            vars.add(cell4gRegForm.getLcrid());
            vars.add(cell4gRegForm.getTenNgQly());
            vars.add(cell4gRegForm.getSdtNgQly());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String iRet = sqlTemplate.executeUpdateFunction2(querySql, vars);

            return iRet;
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
    public int updateCell3g(OmcCell3gInfoBO cell, long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_cell_3g("
                    + " ?,?,?,?,?,?,?,?,?,?,"
                    + " ?,?,?,?,?,?,?,?,?,?,"
                    + " ?,?,?,?,?,?,?,?,?,?,"
                    + " ?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(null);
            vars.add(cell.getLoaiTramId());
            vars.add(cell.getCode());
            vars.add(null);
            vars.add(userId);
            vars.add(null);
            vars.add(cell.getSDTQLy());
            vars.add(cell.getId());

            vars.add(cell.getThietBiId());

            vars.add(cell.getNeTypeId());
            vars.add(cell.getDonViId());
            vars.add(cell.getStatus());
            vars.add(cell.getNote());
            vars.add(cell.getTrangThaiQLId());
            vars.add(cell.getBuildingId() != null && cell.getBuildingId() == 0 ? null : cell.getBuildingId());
            vars.add(cell.getTrangThaiHDId());
            vars.add(cell.getTramDAId());
            vars.add(null);
            vars.add(cell.getTenNgQLy());
            vars.add(cell.getTenCell());
            vars.add(cell.getElectricalTilt());
            vars.add(cell.getListCellGroupName());
            vars.add(cell.getAntennaHigh());
            vars.add(cell.getNgayDangKy());
            vars.add(cell.getLyDo());
            vars.add(cell.getSpecialCoverage());
            vars.add(cell.getAntennaGain());
            vars.add(cell.getNgayCapPhep());
            vars.add(cell.getAntennaModel());
            vars.add(cell.getNoOfCarrier());//
            vars.add(cell.getBosterTma());
            vars.add(cell.getNgayHoatDong());
            vars.add(cell.getCellType());
            vars.add(cell.getNgayKiemDuyet());
            vars.add(cell.getAntennaType());
            vars.add(cell.getAntennaPattern());
            vars.add(cell.getTotalTilt());
            vars.add(cell.getHoanCanhRaDoi());
            vars.add(cell.getAzimuth());
            vars.add(cell.getMechanicalTilt());//
            vars.add(cell.getMaxPower());
            vars.add(cell.getCi());
            vars.add(cell.getFrequency());
            vars.add(cell.getBangTanId());
            vars.add(cell.getTenTrenHeThong());
            vars.add(cell.getDlPsc());
            vars.add(cell.getCpichPower());
            vars.add(cell.getTotalPower());
            vars.add(cell.getLac());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int updateCell4g(OmcCell4gInfoBO cell, long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_update_cell_4g("
                    + " ?,?,?,?,?,?,?,?,?,?,"
                    + " ?,?,?,?,?,?,?,?,?,?,"
                    + " ?,?,?,?,?,?,?,?,?,?,"
                    + " ?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(null);
            vars.add(cell.getLoaiTramId());
            vars.add(cell.getCode());
            vars.add(null);
            vars.add(userId);
            vars.add(cell.getNodeChaId());
            vars.add(cell.getSDTQLy());
            vars.add(cell.getId());

            vars.add(cell.getThietBiId());

            vars.add(cell.getNeTypeId());
            vars.add(cell.getDonViId());
            vars.add(cell.getStatus());
            vars.add(cell.getNote());
            vars.add(cell.getTrangThaiQLId());
            vars.add(cell.getBuildingId() != null && cell.getBuildingId() == 0 ? null : cell.getBuildingId());
            vars.add(cell.getTrangThaiHDId());
            vars.add(cell.getTramDAId());
            vars.add(null);
            vars.add(cell.getTenNgQLy());

            vars.add(cell.getTenCell());
            vars.add(cell.getElectricalTilt());
            vars.add(cell.getListCellGroupName());
            vars.add(cell.getAntennaHigh());
            vars.add(cell.getNgayDangKy());
            vars.add(cell.getLyDo());
            vars.add(cell.getSpecialCoverage());
            vars.add(cell.getAntennaGain());
            vars.add(cell.getNgayCapPhep());
            vars.add(cell.getAntennaModel());
            vars.add(cell.getNoOfCarrier());//
            vars.add(cell.getBosterTma());
            vars.add(cell.getNgayHoatDong());
            vars.add(cell.getCellType());
            vars.add(cell.getNgayKiemDuyet());
            vars.add(cell.getAntennaType());
            vars.add(cell.getAntennaPattern());
            vars.add(cell.getTotalTilt());
            vars.add(cell.getHoanCanhRaDoi());
            vars.add(cell.getAzimuth());
            vars.add(cell.getMechanicalTilt());//
            vars.add(cell.getTenTrenHeThong());
            vars.add(cell.getLac());
            vars.add(cell.getCi());
            vars.add(cell.getBangTanId());
            vars.add(cell.getPci());
            vars.add(cell.getTac());
            vars.add(cell.getLcrid());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public String deleteCellExcel(String permission, ExcelDeleteNodeBO temp, long userId) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();
            String querySql = "{? = call PKG_EXCEL_NODE.fn_delete_node(?,?,?,?)}";
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(temp.getLoaiNE());
            vars.add(temp.getCode());
            vars.add(temp.getLyDo());
            vars.add(userId);
            for (int i = 0; i < vars.size(); i++) {

            }
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            long out = sqlTemplate.executeUpdateFuncRet(querySql, vars);
            return String.valueOf(out);//String.valueOf(count);
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
    public int updateOmcCell2gInfo(OmcCell2gInfoBO temp, long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_OMC_CELL_2G_INFO.fn_update(?,?,?,?,?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(temp.getBsic());
            vars.add(temp.getCi());
            vars.add(temp.getBangTanId());
            vars.add(temp.getTch());
            vars.add(temp.getBcch());
            vars.add(temp.getTrxConfig());
            vars.add(temp.getNodeId());
            vars.add(temp.getTenTrenHeThong());
            vars.add(temp.getLac());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int updateOmcCell3gInfo(OmcCell3gInfoBO temp, long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            p_MAX_POWER         IN OMC_CELL3G_INFO.MAX_POWER%type DEFAULT NULL ,
//      p_CI                IN OMC_CELL3G_INFO.CI%type DEFAULT NULL ,
//      p_FREQUENCY         IN OMC_CELL3G_INFO.FREQUENCY%type DEFAULT NULL ,
//      p_BANG_TAN_ID       IN OMC_CELL3G_INFO.BANG_TAN_ID%type DEFAULT NULL ,
//      p_NODE_ID           IN OMC_CELL3G_INFO.NODE_ID%type ,
//      p_TEN_TREN_HE_THONG IN OMC_CELL3G_INFO.TEN_TREN_HE_THONG%type DEFAULT NULL ,
//      p_DL_PSC            IN OMC_CELL3G_INFO.DL_PSC%type DEFAULT NULL ,
//      p_CPICH_POWER       IN OMC_CELL3G_INFO.CPICH_POWER%type DEFAULT NULL ,
//      p_TOTAL_POWER       IN OMC_CELL3G_INFO.TOTAL_POWER%type DEFAULT NULL ,
//      p_LAC               IN OMC_CELL3G_INFO.LAC%type DEFAULT NULL
            String querySql = "{? = call PKG_OMC_CELL_3G_INFO.fn_update(?,?,?,?,?,?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(temp.getMaxPower());
            vars.add(temp.getCi());
            vars.add(temp.getFrequency());
            vars.add(temp.getBangTanId());

            vars.add(temp.getNodeId());
            vars.add(temp.getTenTrenHeThong());
            vars.add(temp.getDlPsc());
            vars.add(temp.getCpichPower());
            vars.add(temp.getTotalPower());
            vars.add(temp.getLac());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int updateOmcCell4gInfo(OmcCell4gInfoBO temp, long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
// p_CI                IN OMC_CELL4G_INFO.CI%type DEFAULT NULL ,
//      p_PCI               IN OMC_CELL4G_INFO.PCI%type DEFAULT NULL ,
//      p_TAC               IN OMC_CELL4G_INFO.TAC%type DEFAULT NULL ,
//      p_BANG_TAN_ID       IN OMC_CELL4G_INFO.BANG_TAN_ID%type DEFAULT NULL ,
//      p_LCRID             IN OMC_CELL4G_INFO.LCRID%type DEFAULT NULL ,
//      p_NODE_ID           IN OMC_CELL4G_INFO.NODE_ID%type ,
//      p_TEN_TREN_HE_THONG IN OMC_CELL4G_INFO.TEN_TREN_HE_THONG%type DEFAULT NULL ,
//      p_LAC               IN OMC_CELL4G_INFO.LAC%type DEFAULT NULL
            String querySql = "{? = call PKG_OMC_CELL_4G_INFO.fn_update(?,?,?,?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();

            vars.add(temp.getCi());
            vars.add(temp.getPci());
            vars.add(temp.getTac());
            vars.add(temp.getBangTanId());
            vars.add(temp.getLcrid());
            vars.add(temp.getNodeId());
            vars.add(temp.getTenTrenHeThong());
            vars.add(temp.getLac());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int swap2g(Long id, Long thietBiId, String tenNodeCha, long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_swap_cell_2g(?,?,?,?) }";

            List<Object> vars = new Vector<Object>();

            vars.add(id);
            vars.add(thietBiId);
            vars.add(tenNodeCha);
            vars.add(userId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int swap3g(Long id, Long thietBiId, String tenNodeCha, long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_swap_cell_3g(?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(thietBiId);
            vars.add(tenNodeCha);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int swap4g(Long id, Long thietBiId, String tenNodeCha, long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_CELL.fn_swap_cell_4g(?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(thietBiId);
            vars.add(tenNodeCha);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public String updateBaoDuongNetExcel(BaoDuongNetExcel baoduong, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_excel_node.fn_update_bao_duong_excel(?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(userId);
            vars.add(baoduong.getCode());
            vars.add(baoduong.getNgayBaoDuong());
            vars.add(baoduong.getDonvi());
            vars.add(baoduong.getNote());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String iRet = sqlTemplate.executeUpdateFunction2(querySql, vars);

            return iRet;
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
    public String updateKiemDinhNetExcel(KiemDinhNetExcel kiemdinh, long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_building.fn_update_kiem_dinh_excel(?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(userId);
            vars.add(kiemdinh.getCode());
            vars.add(kiemdinh.getMaKiemDinh());
            vars.add(kiemdinh.getNgayKiemDinh());
            vars.add(kiemdinh.getNgayHetKiemDinh());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String iRet = sqlTemplate.executeUpdateFunction2(querySql, vars);

            return iRet;
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
