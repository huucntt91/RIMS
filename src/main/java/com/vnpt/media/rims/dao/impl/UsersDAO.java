package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.vnpt.media.rims.dao.IUsers;
import com.vnpt.media.rims.jdbc.DbSql;
import java.util.ArrayList;

public class UsersDAO extends GenericDAO implements IUsers {

    private static final Logger LOGGER = LogManager.getLogger(UsersDAO.class);

    @Override
    public List<UserBO> findAll(String fullname, int startRow, int endRow) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fc_find_all(?,?,?)}";
            List<Object> vars = new ArrayList<>();
            vars.add(fullname == null ? "" : fullname);
            vars.add(startRow);
            vars.add(endRow);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.queryFunction(querySql, (ResultSet rs, int rowNum) -> {
                UserBO userBO = new UserBO();
                userBO.setUsername(rs.getString("username"));
                userBO.setFullname(rs.getString("fullname"));
                userBO.setMsisdn(rs.getString("msisdn"));
                userBO.setEmail(rs.getString("email"));
                userBO.setStatus(rs.getInt("status"));
                userBO.setId(rs.getLong("id"));
                userBO.setCreateDate(rs.getTimestamp("create_date"));
                userBO.setTenDonVi(rs.getString("name"));
                return userBO;
            }, vars);
            return (List<UserBO>) list;
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int getTotal(String fullname) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fc_get_total(?)}";
            List<Object> vars = new ArrayList<>();
            vars.add(fullname == null ? "" : fullname);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            return sqlTemplate.queryFunctionForInt(querySql, vars);
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public UserBO checkLogin(String username, String password) throws DAOException {
        Connection conn;
        try {
            if (!StringUtils.hasText(username)) {
                return null;
            }
            if (!StringUtils.hasText(password)) {
                return null;
            }
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fc_check_login(?,?)}";
            List<Object> vars = new ArrayList<>();
            vars.add(username.trim().toLowerCase());
            vars.add(StringUtils.encodePassword(password.trim(), "MD5"));
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            UserBO userBO = (UserBO) sqlTemplate.queryFunctionForObject(querySql, (ResultSet rs, int rowNum) -> {
                UserBO userBO1 = new UserBO();
                userBO1.setEmail(rs.getString("email"));
                userBO1.setUsername(rs.getString("username"));
                userBO1.setPassword(rs.getString("PASSWORD"));
                userBO1.setFullname(rs.getString("FULLNAME"));
                userBO1.setMsisdn(rs.getString("MSISDN"));
                userBO1.setStatus(rs.getInt("STATUS"));
                return userBO1;
            }, vars);
            return userBO;
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<GroupBO> findGroupByUserId(Long userId) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fc_find_group_by_user_id(?)}";
            List<Object> vars = new ArrayList<>();
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.queryFunction(querySql, (ResultSet rs, int rowNum) -> {
                GroupBO groupBO = new GroupBO();
                groupBO.setId(rs.getLong("id"));
                groupBO.setName(rs.getString("name"));
                return groupBO;
            }, vars);
            return (List<GroupBO>) list;
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public UserBO findByUserId(String userId) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fc_find_by_user_id(?)}";
            List<Object> vars = new ArrayList<>();
            vars.add(userId.trim());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            UserBO userBO = (UserBO) sqlTemplate.queryFunctionForObject(querySql, (ResultSet rs, int rowNum) -> {
                UserBO userBO1 = new UserBO();
                userBO1.setId(rs.getLong("id"));
                userBO1.setUsername(rs.getString("username"));
                userBO1.setEmail(rs.getString("email"));
                userBO1.setFullname(rs.getString("fullname"));
                userBO1.setPassword(rs.getString("password"));
                userBO1.setMsisdn(rs.getString("msisdn"));
                userBO1.setStatus(rs.getInt("status"));
                userBO1.setTenDonVi(rs.getString("cpname"));
                userBO1.setDonViId(rs.getLong("cpid"));
                return userBO1;
            }, vars);
            return userBO;
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public UserBO findByUsername(String username) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fc_find_by_user_name(?)}";
            List<Object> vars = new ArrayList<>();
            vars.add(username.trim());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            UserBO userBO = (UserBO) sqlTemplate.queryFunctionForObject(querySql, (ResultSet rs, int rowNum) -> {
                UserBO userBO1 = new UserBO();
                userBO1.setId(rs.getLong("id"));
                userBO1.setUsername(rs.getString("username"));
                userBO1.setEmail(rs.getString("email"));
                userBO1.setFullname(rs.getString("fullname"));
                userBO1.setPassword(rs.getString("password"));
                userBO1.setMsisdn(rs.getString("msisdn"));
                userBO1.setStatus(rs.getInt("status"));
                userBO1.setTenDonVi(rs.getString("cpname"));
                userBO1.setDonViId(rs.getLong("cpid"));
                return userBO1;
            }, vars);
            return userBO;
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void resetPassword(UserBO userBO) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fn_reset_password(?,?)}";
            if (userBO == null) {
                return;
            }
            List<Object> vars = new ArrayList<>();
            vars.add(userBO.getId());
            vars.add(StringUtils.encodePassword(Constants.DEFAULT_PASSWORD, "MD5"));
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(UserBO userBO) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fn_update(?,?,?,?,?,?)}";
            if (userBO == null) {
                return;
            }
            List<Object> vars = new ArrayList<>();
            vars.add(userBO.getId());
            vars.add(userBO.getEmail().trim().toLowerCase());
            vars.add(userBO.getFullname().trim());
            vars.add(userBO.getMsisdn().trim());
            vars.add(userBO.getDonViId());
            vars.add(userBO.getPassword() == null ? "" : StringUtils.encodePassword(userBO.getPassword(), "MD5"));
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            LOGGER.info("PKG_USER.fn_update({},{},{},{},{},{}):{}", userBO.getId(),
                    userBO.getEmail().trim().toLowerCase(), userBO.getFullname().trim(),
                    userBO.getMsisdn().trim(), userBO.getDonViId(), "", count);
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void add(UserBO userBO) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fn_add(?,?,?,?,?,?)}";
            if (userBO == null) {
                return;
            }
            List<Object> vars = new ArrayList<>();
            vars.add(userBO.getUsername().trim().toLowerCase().replace("@vnpt.vn", ""));
            vars.add(null);
            vars.add(userBO.getFullname().trim());
            vars.add(userBO.getMsisdn().trim());
            vars.add(userBO.getDonViId());
            vars.add(userBO.getUsername().trim().toLowerCase().replace("@vnpt.vn", "") + "@vnpt.vn");
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            LOGGER.info("PKG_USER.fn_add({},{},{},{},{},{}):{}", userBO.getUsername().trim().toLowerCase().replace("@vnpt.vn", ""),
                    "", userBO.getFullname().trim(),
                    userBO.getMsisdn().trim(),
                    userBO.getUsername().trim().toLowerCase().replace("@vnpt.vn", "") + "@vnpt.vn", "",
                    count);
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(String userId) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fn_delete(?)}";
            if (!StringUtils.hasText(userId)) {
                return;
            }
            List<Object> vars = new ArrayList<>();
            vars.add(userId.trim());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            LOGGER.info("PKG_USER.fn_delete({}):{}", userId.trim(), count);
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void activeUser(UserBO userBO) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fn_active_user(?,?)}";
            if (userBO == null) {
                return;
            }
            List<Object> vars = new ArrayList<>();
            vars.add(userBO.getId());
            vars.add(userBO.getStatus());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            LOGGER.info("PKG_USER.fn_active_user({},{}):{}", userBO.getId(), userBO.getStatus(), count);
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int UserLogin(String userId, String Ip) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_utils.insert_action_log(?,?,?)}";
            List<Object> vars = new ArrayList<>();
            vars.add(userId);
            vars.add("login");
            vars.add(Ip);
            DbSql sqlTemplate = new DbSql(conn);
            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            return count;
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<UserAttrBO> findUserAttrByUserId(String userId) throws DAOException {
        Connection conn;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_USER.fn_get_usr_attr_by_user_id(?)}";
            List<Object> vars = new ArrayList<>();
            vars.add(userId);
            String action = "";
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.queryFunction(querySql, (ResultSet rs, int rowNum) -> {
                UserAttrBO userBO = new UserAttrBO();
                AttributeBO attributeBO = new AttributeBO();
                AttClassListBO attClassListBO = new AttClassListBO();

                userBO.setUserId(rs.getLong("USER_ID"));
                userBO.setUserAttrId(rs.getLong("USER_ATTR_ID"));
                userBO.setAction(rs.getString("ACTION"));

                attributeBO.setId(rs.getLong("ATTR_ID"));
                attributeBO.setAttrCode(rs.getString("ATTR_CODE"));
                attributeBO.setAttrName(rs.getString("ATTR_NAME"));

                attClassListBO.setId(rs.getLong("ATTR_CLASS_ID"));
                attClassListBO.setCode(rs.getString("ATTR_CLASS_CODE"));
                attClassListBO.setName(rs.getString("attr_class_name"));

                userBO.setAttr(attributeBO);
                userBO.setAttClass(attClassListBO);
                return userBO;
            }, vars);
            return (List<UserAttrBO>) list;
        } catch (ConnectionException e) {
            LOGGER.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            LOGGER.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            throw new DAOException(e);
        }
    }
}
