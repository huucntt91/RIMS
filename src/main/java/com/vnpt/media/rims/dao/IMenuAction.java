package com.vnpt.media.rims.dao;



import java.util.List;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.bean.GroupMenuBO;
import com.vnpt.media.rims.bean.HierarchicalFunction;
import com.vnpt.media.rims.bean.MenuBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;

/**
 * Created with IntelliJ IDEA.
 * User: thanhnd.ho
 * Date: 6/17/13
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IMenuAction extends IGeneric {

    List<MenuBO> getChildrenMenu(String parentId, UserBO userBO) throws DAOException;
    public List<MenuBO> getAllChildrenMenu(String parentId,String userName) throws DAOException ;
    public List<GroupMenuBO> getGroupMenu() throws DAOException ;
    public List<GroupBO> getGroup(String groupId) throws DAOException;
    String updateBatch(List<String> sql) throws DAOException;
    public List<HierarchicalFunction> getHierarchicalFunction() throws DAOException ;
    public List<MenuBO> getAllFunctionByUser(String username) throws ServiceException ;
    public List<MenuBO> getChildrenMenuByGroupId(String parentId, String groupId) throws DAOException;
//    List<MenuBO> findByUserId(String parentId, String moduleId, String UserId) throws DAOException;
//
//    MenuBO findByMenuId(String menuId) throws DAOException;
//
//    void insert(MenuBO menuBO) throws DAOException;
//
//    void update(MenuBO menuBO) throws  DAOException;
//
//    void delete(String menuId) throws DAOException;
//
//    void activeMenu(MenuBO menuBO) throws DAOException;
//
//    List<MenuBO> findModuleNotViewByUserId(String userId, boolean isDistinct) throws DAOException;
//
//    List<MenuBO> findByParentId(String parentId, String status, String permission) throws DAOException;
//
//    List<MenuBO> findModuleNotViewAdmin(boolean isDistinct) throws DAOException;
}
