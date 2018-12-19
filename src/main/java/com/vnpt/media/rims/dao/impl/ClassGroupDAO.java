package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
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
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.jdbc.DbSql;

public class ClassGroupDAO extends GenericDAO implements IClassGroup {

    private static Logger logger = LogManager.getLogger(ClassGroupDAO.class);

    @Override
    public List<ObjectListBO> findAllObjectList(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            List<Object> vars = new Vector<Object>();
            String querySql = "{? = call pkg_class_group.fc_find_all(?,?) }";
          
            vars.add(id);
            vars.add("OBJECT_LIST");
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ObjectListBO item = new ObjectListBO();
                    item.setId(rs.getLong("object_id"));
                    item.setName(rs.getString("object_name"));
                    item.setCode(rs.getString("object_code"));
                    item.setDes(rs.getString("object_desc"));
                    return item;
                }
            }, vars);

            return (List<ObjectListBO>) list;
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
    public List<AttClassListBO> findAttrClassListByObjectId(String objectId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_class_group.fc_find_all(?,?) }";
            List<Object> vars = new Vector<Object>();
            vars.add(objectId);
            vars.add("ATTR_CLASS_LIST");
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AttClassListBO item = new AttClassListBO();
                    item.setId(rs.getLong("attr_class_id"));
                    item.setName(rs.getString("attr_class_name"));
                    item.setCode(rs.getString("attr_class_code"));
                    item.setDes(rs.getString("attr_class_desc"));
                    item.setObjectId(rs.getLong("object_id"));
                    return item;
                }
            }, vars);

            return (List<AttClassListBO>) list;
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
    public List<AttClassGroupBO> findAttClassGroupByGroupId(String groupId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_class_group.fc_find_all(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(groupId);
            vars.add("ATTR_CLASS_GROUP");
         

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AttClassGroupBO item = new AttClassGroupBO();
                    item.setId(rs.getLong("ATTR_CLASS_GRP_ID"));
                    item.setGroupId(rs.getLong("CMS_GROUP_ID"));
                    item.setAttrClassId(rs.getLong("ATTR_CLASS_ID"));
                    item.setPermission(rs.getString("PERMISSION"));
                    return item;
                }

            }, vars);

            return (List<AttClassGroupBO>) list;
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
    public int modifyTinh(String action, TinhBO tinhBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fn_modify_tinh(?,?,?,?,?)}";
            if (tinhBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(tinhBO.getTinhTpId());
            vars.add(tinhBO.getTenTinhTp());
            vars.add(tinhBO.getMaTinhTp());
            vars.add(tinhBO.getKhuVuc());

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
    public int modifyQuan(String action, HuyenBO huyenBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fn_modify_quan(?,?,?,?)}";
            if (huyenBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(huyenBO.getQuanHuyenId());
            vars.add(huyenBO.getTenQuanHuyen());
            vars.add(huyenBO.getTinhTpId());

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
    public int modifyPhuong(String action, PhuongXaBO phuongBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TINH.fn_modify_phuong(?,?,?,?)}";
            if (phuongBO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(phuongBO.getPhuongXaId());
            vars.add(phuongBO.getTenPhuongXa());
            vars.add(phuongBO.getQuanHuyenId());

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
    public List<TinhBO> findListTinhByUserId(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_GROUP.fc_manage_tinh(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TinhBO dvBO = new TinhBO();
                    dvBO.setTinhTpId(rs.getLong("tinhtp_id"));
                    return dvBO;
                }

            }, vars);

            return (List<TinhBO>) list;
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
    public List<String> findClassAttrByUserId(String userId,String action, String objectId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_GROUP.fc_user_mange_attr(?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(userId);
            vars.add(action);
            vars.add(objectId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    String item = rs.getString("attr_class_code");
                    return item;
                }

            }, vars);

            return (List<String>) list;
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
    public List<AttributeBO> findAttrByClassId(long classId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_GROUP.fc_get_attr_by_classid(?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(classId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, (ResultSet rs, int rowNum) -> {
                AttributeBO attributeBO = new AttributeBO();
                attributeBO.setId(rs.getLong("attr_id"));
                attributeBO.setAttrName(rs.getString("attr_name"));
                attributeBO.setAttrCode(rs.getString("attr_code"));
                attributeBO.setAttrTableName(rs.getString("attr_table_name"));
                attributeBO.setAliasExcelAttr(rs.getString("alias_excel_attr"));
                attributeBO.setAttrClassId(classId);
                return attributeBO;
            }, vars);

            return (List<AttributeBO>) list;
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
    public String updateAttr(AttributeBO attr) throws DAOException{
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "insert into attr_list(attr_id, attr_name, attr_code, attr_desc,\n" +
                    "       attr_class_id, attr_table_name, alias_excel_attr)\n" +
                    " values(SEQ_ATTR.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            List<Object> vars = new Vector<Object>();
            vars.add(attr.getAttrName());
            vars.add(attr.getAttrCode());
            vars.add(""); //attr_desc
            vars.add(attr.getAttrClassId());
            vars.add(""); //attr_table_name
            vars.add(attr.getAliasExcelAttr());
            if(attr.getId() != null && attr.getId() > 0){
                querySql =
                "update attr_list set attr_name=?, attr_code=?, attr_desc=?, attr_class_id=?, attr_table_name=?, alias_excel_attr=?" +
                        " where attr_id=?";
                vars.add(attr.getId());
            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int response = sqlTemplate.executeUpdate(querySql, vars);

            return String.valueOf(response);
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
    public String deleteAttr(AttributeBO attr) throws DAOException{
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "delete from attr_list where attr_id=?";
            List<Object> vars = new Vector<Object>();
            vars.add(attr.getId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            sqlTemplate.executeUpdate(querySql, vars);

            return "1";
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
