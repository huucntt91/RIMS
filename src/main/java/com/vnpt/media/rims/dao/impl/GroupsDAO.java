package com.vnpt.media.rims.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.IGroups;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;

public class GroupsDAO extends GenericDAO implements IGroups {

    private static Logger logger = LogManager.getLogger(GroupsDAO.class);

    public List<GroupBO> findAll(String name) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "select * from cms_group ";

            if (StringUtils.hasText(name)) {
                querySql += " where LOWER(name) like ? ";
                
            }
            querySql += " order by id desc";


            List<Object> vars = new Vector<Object>();
            if (StringUtils.hasText(name)) {
                vars.add("%" + name.toLowerCase() + "%");
            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.query(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GroupBO groupBO = new GroupBO();
                    groupBO.setName(rs.getString("name"));
                    groupBO.setId(rs.getLong("id"));
                    return groupBO;
                }

            }, vars);

            return (List<GroupBO>) list;
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
    public GroupBO findByGroupId(String groupId) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "select * from CMS_GROUP where 1 = 1 ";
            String querySql = "{? = call PKG_GROUP.fc_find_by_group_id(?)}";

//            if (StringUtils.hasText(groupId)) {
//                querySql = querySql + " and id = ?  ";
//            }


            List<Object> vars = new Vector<Object>();

//            if (StringUtils.hasText(groupId)) {
            vars.add(groupId.trim());
//            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            GroupBO userBO = (GroupBO) sqlTemplate.queryFunctionForObject(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GroupBO groupBO = new GroupBO();
                    groupBO.setId(rs.getLong("id"));
                    groupBO.setName(rs.getString("name"));
                    groupBO.setFamilyTypeId(rs.getLong("family_type_id"));
                    groupBO.setNetworkTypeId(rs.getLong("network_type_id"));
                    groupBO.setPhancapId(rs.getLong("phan_cap_id"));

                    return groupBO;  //To change body of implemented methods use File | Settings | File Templates.
                }
            }, vars);

            return userBO;

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

    public void update(GroupBO groupBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            String querySql = "update CMS_Group set  name = ? ";
            String querySql = "{? = call PKG_GROUP.fn_update(?,?,?,?,?)}";

            if (groupBO == null) {
                return;
            }
            //logger.info("SQL : " + querySql);

//            querySql += " where id = ?";
            List<Object> vars = new Vector<Object>();
            vars.add(groupBO.getId());
            vars.add(groupBO.getName().trim());
            vars.add(groupBO.getPhancapId());
            vars.add(groupBO.getNetworkTypeId());
            vars.add(groupBO.getFamilyTypeId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            
            if (count > 0) {
                logger.debug("Records update : " + count);
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

    public void add(GroupBO groupBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            String querySql = "insert into CMS_group(id, name)"
//                    + " values(sq_cms_group_id.nextval, ?)";
            String querySql = "{? = call PKG_GROUP.fn_add(?,?,?,?)}";

            if (groupBO == null) {
                return;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(groupBO.getName().trim());
            vars.add(groupBO.getPhancapId());
            vars.add(groupBO.getNetworkTypeId());
            vars.add(groupBO.getFamilyTypeId());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            
            if (count > 0) {
                logger.debug("Records update : " + count);
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

    public void delete(String groupId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "delete from CMS_Group where id = ? ";
            if (!StringUtils.hasText(groupId)) {
                return;
            }


            List<Object> vars = new Vector<Object>();
            vars.add(groupId.trim());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdate(querySql, vars);

            if (count > 0) {
                logger.debug("Records delete : " + count);
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

    public void clone(String groupId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            String querySql = "insert into CMS_group(id, name)"
//                    + " values(sq_cms_group_id.nextval, ?)";
            String querySql = "{? = call PKG_GROUP.fn_clone(?)}";

            if (groupId == null) {
                return;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(groupId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            
            if (count > 0) {
                logger.debug("Records update : " + count);
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

    @Override
    public List<GroupBO> getGroupByUserId(String userId) throws ServiceException {
        Connection conn = null;
        try {

            conn = this.getConnection();

            String querySql = "select g.* from CMS_GROUP g inner join cms_user_group ug on "
                    + " g.id = ug.group_id where 1 = 1 ";

            if (StringUtils.hasText(userId)) {
                querySql = querySql + " and ug.user_id = ?  ";
            }



            List<Object> vars = new Vector<Object>();

            if (StringUtils.hasText(userId)) {
                vars.add(userId.trim());
            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.query(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GroupBO groupBO = new GroupBO();
                    groupBO.setName(rs.getString("name"));
                    groupBO.setId(rs.getLong("id"));
                    return groupBO;
                }
            }, vars);

            return (List<GroupBO>) list;
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
    public List<GroupBO> getGroupByNotUserId(String userId) throws ServiceException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "select g.* from CMS_GROUP g left join  cms_user_group ug on "
//                    + " g.id = ug.group_id where 1 = 1 ";
//            querySql += " and (ug.group_id is null or ug.user_id <>" + userId + ")";
            String querySql = "select g.* from CMS_GROUP g left join   "
                    + "(select group_id from cms_user_group ug  "
                    + " where 1 = 1  and ug.user_id =? ) tt on g.id=tt.group_id where tt.group_id is null";

            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();

            if (StringUtils.hasText(userId)) {
                vars.add(userId.trim());
            }
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.query(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GroupBO groupBO = new GroupBO();
                    groupBO.setName(rs.getString("name"));
                    groupBO.setId(rs.getLong("id"));
                    return groupBO;
                }
            }, vars);

            return (List<GroupBO>) list;
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
