//package com.vnpt.media.rims.dao.impl;
//
//import com.vnpt.media.rims.common.Constants;
//import com.vnpt.media.rims.exception.ConnectionException;
//import com.vnpt.media.rims.exception.DAOException;
//import com.vnpt.media.rims.exception.JdbcException;
//import com.vnpt.media.rims.jdbc.RowMapper;
//import com.vnpt.media.rims.jdbc.SQLTemplate;
//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Vector;
//import com.vnpt.media.rims.bean.CpBO;
//import com.vnpt.media.rims.bean.UserBO;
//import com.vnpt.media.rims.common.utils.StringUtils;
//import com.vnpt.media.rims.dao.ICps;
//
//public class CpsDAO extends GenericDAO implements ICps {
//
//    private static Logger logger = LogManager.getLogger(CpsDAO.class);
//
//    @Override
//    public List<CpBO> findAll(String id, String name) throws DAOException {
//        Connection conn = null;
//        try {
//            conn = this.getConnection();
////            String querySql = "select * from cms_cp where 1=1";
//                        String querySql = "{? = call PKG_DONVI.fc_find_all(?,?)}";
//
////             order by name""
////            if (StringUtils.hasText(name)) {
////                querySql += " and LOWER(name) like ? ";
////            }
////            if (StringUtils.hasText(id)) {
////                querySql += " and id = ? ";
////            }
////            querySql += " order by id desc ";
//
//
//            List<Object> vars = new Vector<Object>();
//            //            if (StringUtils.hasText(id)) {
//                vars.add(id);
////            }
////            if (StringUtils.hasText(name)) {
//                vars.add("%" + name.toLowerCase() + "%");
////            }
//
//            SQLTemplate sqlTemplate = new SQLTemplate(conn);
//
//            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
//                @Override
//                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    CpBO cpBO = new CpBO();
//                    cpBO.setName(rs.getString("name"));
//                    cpBO.setId(rs.getLong("id"));
//                    cpBO.setAddress(rs.getString("address"));
//                    cpBO.setDescription(rs.getString("description"));
//                    cpBO.setMsisdn(rs.getString("mobile"));
//                    cpBO.setEmail(rs.getString("email"));
//                    cpBO.setCreateDate(rs.getDate("create_time"));
//                    cpBO.setUpdateDate(rs.getDate("update_time"));
//                    cpBO.setStatus(rs.getInt("status"));
//
//                    return cpBO;
//                }
//
//            }, vars);
//
//            return (List<CpBO>) list;
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
//    public void addCp(CpBO cpBO) throws DAOException {
//        Connection conn = null;
//        try {
//            conn = this.getConnection();
//            String querySql = "insert into CMS_CP(id, name,address, description,"
//                    + "mobile, email, create_time, update_time, status)"
//                    + " values(seq_cp.nextval, ?,?,?,?,?,sysdate,sysdate,?)";
//            if (cpBO == null) {
//                return;
//            }
//            logger.info("SQL : " + querySql);
//
//            List<Object> vars = new Vector<Object>();
//            vars.add(cpBO.getName().trim());
//            vars.add(cpBO.getAddress().trim());
//            vars.add(cpBO.getDescription().trim());
//            vars.add(cpBO.getMsisdn().trim());
//            vars.add(cpBO.getEmail().trim());
//            vars.add(cpBO.getStatus());
//
//            SQLTemplate sqlTemplate = new SQLTemplate(conn);
//
//            int count = sqlTemplate.executeUpdate(querySql, vars);
//
//            if (count > 0) {
//                logger.debug("Records update : " + count);
//            } else {
//                throw new DAOException();
//            }
//
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
//    public void updateCp(CpBO cpBO) throws DAOException {
//        Connection conn = null;
//        try {
//            conn = this.getConnection();
//            String querySql = "update CMS_CP set  name = ?,address=?,email=?,description=?,mobile=?,status=? ,update_time=sysdate "
//                    + " where id=?";
//            if (cpBO == null) {
//                return;
//            }
//            logger.info("SQL : " + querySql);
//
//            List<Object> vars = new Vector<Object>();
//            vars.add(cpBO.getName().trim());
//            vars.add(cpBO.getAddress());
//            vars.add(cpBO.getEmail());
//            vars.add(cpBO.getDescription());
//            vars.add(cpBO.getMsisdn());
//            vars.add(cpBO.getStatus());
//            vars.add(cpBO.getId());
//
//            SQLTemplate sqlTemplate = new SQLTemplate(conn);
//
//            int count = sqlTemplate.executeUpdate(querySql, vars);
//            
//            if (count > 0) {
//                logger.debug("Records update : " + count);
//            } else {
//                throw new DAOException();
//            }
//
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
//}
