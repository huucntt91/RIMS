package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.dao.ITEq;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class TEqDAO extends GenericDAO implements ITEq {

    private static Logger logger = LogManager.getLogger(TEqDAO.class);

    @Override
    public List<TEq1BO> findAllEq1(String id, String name, String tNodeId, int startRow, int endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_find_eq1(?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(tNodeId);
            vars.add(name);

            vars.add(startRow);
            vars.add(endRow);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TEq1BO item = new TEq1BO();
                    item.setId(rs.getLong("TEQ1_ID"));
                    item.setTnodeId(rs.getLong("TNODE_ID"));
                    item.setTnodeName(rs.getString("tnode_name"));
                    item.setNumber(rs.getString("TEQ1_NUMBER"));
                    item.setName(rs.getString("TEQ1_NAME"));
                    item.setVendor(rs.getString("VENDOR_NAME"));
                    item.setNote(rs.getString("NOTE"));
                    return item;
                }
            }, vars);

            return (List<TEq1BO>) list;
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
    public List<TEq2BO> findAllEq2(String id, String name, String eq1Id, int startRow, int endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_find_eq2(?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(eq1Id);
            vars.add(name);

            vars.add(startRow);
            vars.add(endRow);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TEq2BO item = new TEq2BO();
                    item.setId(rs.getLong("TEQ2_ID"));
                    item.setEq1Id(rs.getLong("TEQ1_ID"));
                    item.setEq1Name(rs.getString("teq1_name"));
                    
                    item.setNumber(rs.getString("TEQ2_NUMBER"));
                    item.setName(rs.getString("TEQ2_NAME"));
                    item.setVendor(rs.getString("VENDOR_NAME"));
                    item.setNote(rs.getString("NOTE"));
                    item.setTotalColum(rs.getLong("TOTAL_EQ3"));
                    return item;
                }
            }, vars);

            return (List<TEq2BO>) list;
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
    public List<TEq3BO> findAllEq3(String id, String name, String eq2Id, int startRow, int endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_find_eq3(?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(eq2Id);
            vars.add(name);

            vars.add(startRow);
            vars.add(endRow);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TEq3BO item = new TEq3BO();
                    item.setId(rs.getLong("TEQ3_ID"));
                    item.setEq2Id(rs.getLong("TEQ2_ID"));
                    item.setEq2Name(rs.getString("TEQ2_NAME"));
                    item.setNumber(rs.getString("TEQ3_NUMBER"));
                    item.setName(rs.getString("TEQ3_NAME"));
                    item.setVendor(rs.getString("VENDOR_NAME"));
                    item.setNote(rs.getString("NOTE"));
                    item.setPartNumber(rs.getString("PART_NUMBER"));
                    item.setMaVach(rs.getString("VNPT_BARCODE"));
                    item.setSeriaNumber(rs.getString("SERIAL_NUMBER"));
                    return item;
                }
            }, vars);

            return (List<TEq3BO>) list;
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
    public List<TPortBO> findAllTPort(String id, String name, String eq3Id, int startRow, int endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TPORT.fn_find(?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(eq3Id);
            vars.add(name);
            vars.add(startRow);
            vars.add(endRow);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TPortBO item = new TPortBO();
                    item.setId(rs.getLong("TPORT_ID"));
                    item.setEq3Id(rs.getLong("TEQ3_ID"));
                    item.setEq3Name(rs.getString("TEQ3_NAME"));

                    item.setSerialNo(rs.getString("SERIAL_NO"));
                    item.setTportName(rs.getString("TPORT_NAME"));

                    item.setNote(rs.getString("NOTE"));
                    item.setStatus(rs.getString("status"));
                    return item;
                }
            }, vars);

            return (List<TPortBO>) list;
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
    public List<TModuleQuangBO> findAllModuleQuang(String id, String name, String portId, int startRow, int endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TMODULEQUANG.fn_find(?,?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(portId);
            vars.add(name);
            vars.add(startRow);
            vars.add(endRow);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TModuleQuangBO item = new TModuleQuangBO();
                    item.setId(rs.getLong("TMODULE_QUANG_ID"));
                    item.setPortId(rs.getLong("TPORT_ID"));
                    item.setPortName(rs.getString("TPORT_NAME"));
                    item.setSerialNo(rs.getString("SERIAL_NO"));
                    item.setTransceiverType(rs.getString("TRANSCEIVER_TYPE"));
                    item.setTxPower(rs.getString("TX_POWER"));
                    item.setRxPower(rs.getString("RX_POWER"));
                    item.setNote(rs.getString("note"));
                    item.setName(rs.getString("name"));
                    item.setStatus(rs.getString("status"));
                    return item;
                }
            }, vars);

            return (List<TModuleQuangBO>) list;
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

//    @Override
//    public int update(TLinkBO temp, Long userId) throws ServiceException {
//        Connection conn = null;
//        try {
//            conn = this.getConnection();
//            String querySql = "{? = call PKG_TLINK.fn_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
////       
//
//
//            List<Object> vars = new Vector<Object>();
////            vars.add(startRow);
//            vars.add(temp.getId());
//
//            vars.add(temp.getNodeId2());
//            vars.add(temp.getNodeId1());
//            vars.add(temp.getMucThu1());
//            vars.add(temp.getMucThu2());
//            vars.add(temp.getIp2());
//            vars.add(temp.getIp1());
//
//            vars.add(temp.getDistanceM());
//            vars.add(temp.getPort1());
//            vars.add(temp.getPort2());
//            vars.add(temp.getExtraData1());
//            vars.add(temp.getExtraData2());
//            vars.add(temp.gettLinkName());
//            vars.add(temp.getNote());
//            vars.add(userId);
//            SQLTemplate sqlTemplate = new SQLTemplate(conn);
//
//            int iRet = sqlTemplate.executeUpdateFunction(querySql,
//                    vars);
//
//            return iRet;
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
//
//    @Override
//    public int insert(TLinkBO temp, Long userId) throws ServiceException {
//        Connection conn = null;
//        try {
//            conn = this.getConnection();
//            String querySql = "{? = call PKG_TLINK.fn_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
////       
//
//
//            List<Object> vars = new Vector<Object>();
////            vars.add(startRow);
//            vars.add(temp.getNodeId2());
//            vars.add(temp.getNodeId1());
//            vars.add(temp.getMucThu1());
//            vars.add(temp.getMucThu2());
//            vars.add(temp.getIp2());
//            vars.add(temp.getIp1());
//
//            vars.add(temp.getDistanceM());
//            vars.add(temp.getPort1());
//            vars.add(temp.getPort2());
//            vars.add(temp.getExtraData1());
//            vars.add(temp.getExtraData2());
//            vars.add(temp.gettLinkName());
//            vars.add(temp.getNote());
//            vars.add(userId);
//            SQLTemplate sqlTemplate = new SQLTemplate(conn);
//
//            int iRet = sqlTemplate.executeUpdateFunction(querySql,
//                    vars);
//
//            return iRet;
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
//
//    @Override
//    public int getTotal(String id, String name) throws ServiceException {
//        Connection conn = null;
//        try {
//            conn = this.getConnection();
//            String querySql = "{? = call PKG_TLINK.fn_find_total(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
////       
//
//
//            List<Object> vars = new Vector<Object>();
////            vars.add(startRow);
//            vars.add(id);
//            vars.add(null);
//            vars.add(null);
//            vars.add(null);
//            vars.add(null);
//            vars.add(null);
//            vars.add(null);
//            vars.add(null);
//            vars.add(null);
//            vars.add(null);
//            vars.add(null);
//            vars.add(null);
//            vars.add(name);
//            SQLTemplate sqlTemplate = new SQLTemplate(conn);
//
//            int iRet = sqlTemplate.queryFunctionForInt(querySql, vars);
//
//            return iRet;
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
    public int updateTEq1(TEq1BO temp, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_update_eq1(?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(temp.getId());
            vars.add(temp.getTnodeId());
            vars.add(temp.getNumber());
            vars.add(temp.getName());
            vars.add(temp.getVendor());
            vars.add(temp.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
    public int updateTEq2(TEq2BO temp, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_update_eq2(?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(temp.getId());
            vars.add(temp.getEq1Id());
            vars.add(temp.getNumber());
            vars.add(temp.getName());
            vars.add(temp.getVendor());
            vars.add(temp.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
    public int updateTEq3(TEq3BO temp, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_update_eq3(?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(temp.getId());
            vars.add(temp.getEq2Id());
            vars.add(temp.getNumber());
            vars.add(temp.getName());
            vars.add(temp.getVendor());
            vars.add(temp.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
    public int updateTPort(TPortBO temp, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_update_tport(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(temp.getId());
            vars.add(temp.getEq3Id());
            vars.add(temp.getPortNo());
            vars.add(temp.getNote());
            vars.add(temp.getMtu());
            vars.add(temp.getTransceiver());
            vars.add(temp.getCongsuatphat());
            vars.add(temp.getCongsuatthu());
            vars.add(temp.getNguongthumin());
            vars.add(temp.getNguongthumax());
            vars.add(temp.getSerialNo());
            vars.add(temp.getTportName());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
    public int updateTQuang(TModuleQuangBO temp, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_update_tquang(?,?,?,?,?,?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(temp.getId());
            vars.add(temp.getPortId());
            vars.add(temp.getTransceiverType());
            vars.add(temp.getSerialNo());
            vars.add(temp.getTxPower());
            vars.add(temp.getRxPower());
            vars.add(temp.getName());
            vars.add(temp.getNote());
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
    public int deleteEq1(String id, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_delete_eq1(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
    public int deleteEq2(String id, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_delete_eq2(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
    public int deleteEq3(String id, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_delete_eq3(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
    public int deleteTport(String id, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_delete_tport(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
    public int deleteTquang(String id, Long userId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TEQ.fn_delete_tquang(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql,
                    vars);
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
