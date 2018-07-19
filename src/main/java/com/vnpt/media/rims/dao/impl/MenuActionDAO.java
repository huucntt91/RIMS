package com.vnpt.media.rims.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.bean.GroupMenuBO;
import com.vnpt.media.rims.bean.HierarchicalFunction;
import com.vnpt.media.rims.bean.MenuBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.IMenuAction;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;

/**
 * Created with IntelliJ IDEA. User: thanhnd.ho Date: 6/17/13 Time: 6:18 PM To
 * change this template use File | Settings | File Templates.
 */
public class MenuActionDAO extends GenericDAO implements IMenuAction {

    private static Logger logger = LogManager.getLogger(MenuActionDAO.class);

    @Override
    public List<MenuBO> getChildrenMenu(String parentId, UserBO userBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            String querySql = "SELECT m.* FROM cms_menu m "
//                    + "where id in(select menu_id from "
//                    + "     group_menu gm"
//                    + "    JOIN cms_user_group gu"
//                    + "        ON gu.group_id = gm.group_id"
//                    + "        where gu.user_id=?  "
//                    + "        )";
//            if (StringUtils.hasText(parentId)) {
//                querySql = querySql + " and m.parent_id = ? ";
//            }
//            querySql += " order by m.odr asc";

            String querySql = "{? = call PKG_MENU.fc_get_children_menu(?,?)}";
            //logger.info("SQL : " + querySql);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<Object> vars = new Vector<Object>();
            vars.add(userBO.getId());
            vars.add(parentId.trim());

            List<MenuBO> list = (List<MenuBO>) sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MenuBO menuBO = new MenuBO();
                    menuBO.setId(rs.getLong("ID"));
                    menuBO.setName(rs.getString("NAME"));
                    menuBO.setParentId(rs.getLong("PARENT_ID"));
                    menuBO.setMappingUrl(rs.getString("mapping_url"));
                    menuBO.setIcon(rs.getString("icon"));
                    return menuBO;
                }
            }, vars);

            return list;
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
    public List<MenuBO> getAllFunctionByUser(String userName) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_MENU.fc_get_all_function_by_user(?)}";
            //logger.info("SQL : " + querySql);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<Object> vars = new Vector<Object>();
            vars.add(userName);            

            List<MenuBO> list = (List<MenuBO>) sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MenuBO menuBO = new MenuBO();
                    menuBO.setId(rs.getLong("ID"));
                    menuBO.setName(rs.getString("NAME"));
                    menuBO.setParentId(rs.getLong("PARENT_ID"));
                    menuBO.setMappingUrl(rs.getString("mapping_url"));
                    menuBO.setIcon(rs.getString("icon"));
                    return menuBO;
                }
            }, vars);

            
            return list;
//            return list;
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
    public List<MenuBO> getAllChildrenMenu(String parentId, String userName) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            String querySql = "SELECT m.* FROM cms_menu m "
//                    + "where id in(select menu_id from "
//                    + "     group_menu gm"
//                    + "    JOIN cms_user_group gu"
//                    + "        ON gu.group_id = gm.group_id"
//                    + "        where gu.user_id=?  "
//                    + "        )";
//            if (StringUtils.hasText(parentId)) {
//                querySql = querySql + " and m.parent_id = ? ";
//            }
//            querySql += " order by m.odr asc";

            String querySql = "{? = call PKG_MENU.fc_get_all_children_menu(?,?)}";
            //logger.info("SQL : " + querySql);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<Object> vars = new Vector<Object>();
            vars.add(parentId.trim());
            vars.add(userName);
            List<MenuBO> list = (List<MenuBO>) sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MenuBO menuBO = new MenuBO();
                    menuBO.setId(rs.getLong("ID"));
                    menuBO.setName(rs.getString("NAME"));
                    menuBO.setParentId(rs.getLong("PARENT_ID"));
                    menuBO.setMappingUrl(rs.getString("mapping_url"));
                    menuBO.setIcon(rs.getString("icon"));
                    int selected = rs.getInt("selected");
                    
                    menuBO.setSelected(selected == 0 ? false : true);
                    return menuBO;
                }
            }, vars);

            return list;
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
    public List<MenuBO> getChildrenMenuByGroupId(String parentId, String groupId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            String querySql = "SELECT m.* FROM cms_menu m "
//                    + "where id in(select menu_id from "
//                    + "     group_menu gm"
//                    + "    JOIN cms_user_group gu"
//                    + "        ON gu.group_id = gm.group_id"
//                    + "        where gu.user_id=?  "
//                    + "        )";
//            if (StringUtils.hasText(parentId)) {
//                querySql = querySql + " and m.parent_id = ? ";
//            }
//            querySql += " order by m.odr asc";

            String querySql = "{? = call PKG_MENU.fc_get_children_menu_by_id(?,?)}";
            //logger.info("SQL : " + querySql);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<Object> vars = new Vector<Object>();
            vars.add(parentId.trim());
            vars.add(groupId);
            List<MenuBO> list = (List<MenuBO>) sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MenuBO menuBO = new MenuBO();
                    menuBO.setId(rs.getLong("ID"));
                    menuBO.setName(rs.getString("NAME"));
                    menuBO.setParentId(rs.getLong("PARENT_ID"));
                    menuBO.setMappingUrl(rs.getString("mapping_url"));
                    menuBO.setIcon(rs.getString("icon"));
                    int selected = rs.getInt("selected");
                    
                    menuBO.setSelected(selected == 0 ? false : true);
                    return menuBO;
                }
            }, vars);

            return list;
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
    public List<HierarchicalFunction> getHierarchicalFunction() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            String querySql = "SELECT id,parent_id,name,level,mapping_url,"
//                    + "       LTRIM(SYS_CONNECT_BY_PATH(id, '-'), '-') AS path "
//                    + "       FROM   cms_menu"
//                    + "       START WITH id in (select id from cms_menu where parent_id =0) "
//                    + "       CONNECT BY NOCYCLE parent_id = PRIOR id"
//                    + "       ORDER SIBLINGS BY odr asc";

//
            String querySql = "{? = call PKG_MENU.fc_get_hierarchical_function()}";
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<Object> vars = new Vector<Object>();

            List<HierarchicalFunction> list = (List<HierarchicalFunction>) sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    HierarchicalFunction function = new HierarchicalFunction();
                    function.setId(rs.getLong("ID"));
                    function.setName(rs.getString("NAME"));
                    function.setParentId(rs.getLong("PARENT_ID"));
                    function.setMappingUrl(rs.getString("mapping_url"));
                    function.setLevel(rs.getInt("level"));
                    
                    return function;
                }
            }, vars);

            return list;
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
    public List<GroupMenuBO> getGroupMenu() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            String querySql = "select mg.id id, mg.group_id group_id, mg.menu_id menu_id from GROUP_MENU mg inner join  cms_menu m on mg.menu_id = m.id WHERE 1 = 1";
            String querySql = "{? = call PKG_MENU.fc_get_group_menu()}";


            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<Object> vars = new Vector<Object>();

            List<GroupMenuBO> list = (List<GroupMenuBO>) sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GroupMenuBO groupMenuBO = new GroupMenuBO();
                    groupMenuBO.setId(rs.getLong("id"));
                    groupMenuBO.setGroupId(rs.getLong("group_id"));
                    groupMenuBO.setMenuId(rs.getLong("menu_id"));
                    return groupMenuBO;
                }
            }, vars);

            return list;
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
    public List<GroupBO> getGroup(String groupId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
//            String querySql = "select * from cms_group g";
            String querySql = "{? = call PKG_MENU.fc_get_group(?)}";



            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<Object> vars = new Vector<Object>();
            vars.add(groupId);
            List<GroupBO> list = (List<GroupBO>) sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GroupBO groupBO = new GroupBO();
                    groupBO.setId(rs.getLong("id"));
                    groupBO.setName(rs.getString("name"));
                    return groupBO;
                }
            }, vars);

            return list;
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

    public String updateBatch(List<String> monitorLogs) throws DAOException {
        Connection conn = null;
        String ret = "0";
        try {

            conn = this.getConnection();

            if (StringUtils.isEmpty(monitorLogs)) {
                return ret;
            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int[] total = sqlTemplate.executeBatchUpdate(monitorLogs, Constants.BATCH_ROW_COMMIT, logger, "DEBUG");
            ret = "1";
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (SQLException e) {
            
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
        return ret;
    }
}
