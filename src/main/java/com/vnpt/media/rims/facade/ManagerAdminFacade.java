package com.vnpt.media.rims.facade;

import java.util.ArrayList;
import java.util.HashMap;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.bean.FamilyTypeBO;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.bean.GroupMenuBO;
import com.vnpt.media.rims.bean.HierarchicalFunction;
import com.vnpt.media.rims.bean.MenuBO;
import com.vnpt.media.rims.bean.NetworkTypeBO;
import com.vnpt.media.rims.bean.PhancapBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.dao.IFamilyType;
import com.vnpt.media.rims.dao.IGroups;
import com.vnpt.media.rims.dao.IMenuAction;
import com.vnpt.media.rims.dao.INetworkType;
import com.vnpt.media.rims.dao.IPhancap;
import com.vnpt.media.rims.dao.ITinh;
import com.vnpt.media.rims.dao.IUsers;

/**
 *
 * @author VNP
 */
public class ManagerAdminFacade {

    private Logger logger = LogManager.getLogger(ManagerAdminFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public ManagerAdminFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public ManagerAdminFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<GroupBO> findAllGroup(String name) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGroups iGroupDao = factory.getGroupsDAO();
            trans.connectionType(Constants.DB_CB);
            iGroupDao.setTransaction(trans);
            return iGroupDao.findAll(name);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<UserBO> findAllUsers(String fullname, int startRow, int endRow) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(Constants.DB_CB);
            users.setTransaction(trans);
            return users.findAll(fullname, startRow, endRow);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalUser(String fullname) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            return users.getTotal(fullname);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public UserBO checkLogin(String username, String password) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            return users.checkLogin(username, password.trim());
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<GroupBO> findGroupByUserId(long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            return users.findGroupByUserId(userId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public UserBO findByUserId(String userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            return users.findByUserId(userId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public GroupBO findByGroupId(String groupId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGroups groups = factory.getGroupsDAO();
            trans.connectionType(DB_ADMIN);
            groups.setTransaction(trans);
            return groups.findByGroupId(groupId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void resetPassword(UserBO userBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            trans.beginTransaction();
            users.resetPassword(userBO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void updateUser(UserBO userBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            trans.beginTransaction();
            users.update(userBO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void addUser(UserBO userBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            trans.beginTransaction();
            users.add(userBO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void deleteUser(String userId, UserBO userBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            trans.beginTransaction();
            users.delete(userId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void activeUser(UserBO userBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            trans.beginTransaction();
            users.activeUser(userBO);
            trans.commit();
            trans.endTransaction();

        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<MenuBO> getChildrenMenuByUserId(String parentId, String moduleId, UserBO userBO, String permission) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMenuAction menu = factory.getMenuActionDAO();
            trans.connectionType(DB_ADMIN);
            menu.setTransaction(trans);
            return menu.getChildrenMenu(parentId, userBO);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<MenuBO> getAllChildrenMenuById(String parentId, String groupId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMenuAction menu = factory.getMenuActionDAO();
            trans.connectionType(DB_ADMIN);
            menu.setTransaction(trans);
            return menu.getChildrenMenuByGroupId(parentId, groupId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<MenuBO> getAllChildrenMenu(String parentId, String userName) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMenuAction menu = factory.getMenuActionDAO();
            trans.connectionType(DB_ADMIN);
            menu.setTransaction(trans);
            return menu.getAllChildrenMenu(parentId, userName);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<HierarchicalFunction> getHierarchicalFunction() throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMenuAction menu = factory.getMenuActionDAO();
            trans.connectionType(DB_ADMIN);
            menu.setTransaction(trans);
            return menu.getHierarchicalFunction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<GroupBO> getGroup(String groupId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMenuAction menu = factory.getMenuActionDAO();
            trans.connectionType(DB_ADMIN);
            menu.setTransaction(trans);
            return menu.getGroup(groupId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<GroupBO> getGroupByUserId(String userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGroups group = factory.getGroupsDAO();
            trans.connectionType(DB_ADMIN);
            group.setTransaction(trans);
            return group.getGroupByUserId(userId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<GroupBO> getGroupByNotUserId(String userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGroups group = factory.getGroupsDAO();
            trans.connectionType(DB_ADMIN);
            group.setTransaction(trans);
            return group.getGroupByNotUserId(userId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public Map<String, Long> putGroupMenu() throws ServiceException {
        Map<String, Long> ret = new HashMap<String, Long>();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMenuAction menu = factory.getMenuActionDAO();
            trans.connectionType(DB_ADMIN);
            menu.setTransaction(trans);
            List<GroupMenuBO> listGroupMenuBO = menu.getGroupMenu();
            for (int i = 0; i < listGroupMenuBO.size(); i++) {
                ret.put(listGroupMenuBO.get(i).getMenuId() + "-" + listGroupMenuBO.get(i).getGroupId(), listGroupMenuBO.get(i).getGroupId());
            }
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
        return ret;
    }

    public String updateGroupMenu(List<String> sqls) {
        ITransaction trans = null;
        String ret = "";
        try {
            trans = factory.getTransaction();
            IMenuAction menu = factory.getMenuActionDAO();
            trans.connectionType(DB_ADMIN);
            menu.setTransaction(trans);
            trans.beginTransaction();
            ret = menu.updateBatch(sqls);
            if (ret.equals("0")) {
                trans.rollback();
                return "0";
            } else {
                trans.commit();
            }
            trans.endTransaction();
            return "1";
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            trans.rollback();
            throw new DAOException(de);

        } finally {
            DatabaseUtils.close(trans);
        }
    }

    //group
    public void updateGroup(GroupBO groupBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGroups groups = factory.getGroupsDAO();
            trans.connectionType(DB_ADMIN);
            groups.setTransaction(trans);
            trans.beginTransaction();
            groups.update(groupBO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void addGroup(GroupBO groupBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGroups groups = factory.getGroupsDAO();
            trans.connectionType(DB_ADMIN);
            groups.setTransaction(trans);
            trans.beginTransaction();
            groups.add(groupBO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void deleteGroup(String groupId, GroupBO groupBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGroups groups = factory.getGroupsDAO();
            trans.connectionType(DB_ADMIN);
            groups.setTransaction(trans);
            trans.beginTransaction();
            groups.delete(groupId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void clone(String groupId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGroups groups = factory.getGroupsDAO();
            trans.connectionType(DB_ADMIN);
            groups.setTransaction(trans);
            trans.beginTransaction();
            groups.clone(groupId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<MenuBO> getAllFunctionByUser(String username) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMenuAction iMenuAction = factory.getMenuActionDAO();
            trans.connectionType(DB_ADMIN);
            iMenuAction.setTransaction(trans);
            return iMenuAction.getAllFunctionByUser(username);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public UserBO findByUserName(String username) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUsers users = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            return users.findByUsername(username);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    //CP
    public List<DonViBO> findAllCp(String id, String name, String tinhs) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
//            ICps iCps = factory.getCpsDAO();
            IDonVi iDonvi = factory.getDonViDAO();
            trans.connectionType(DB_ADMIN);
            iDonvi.setTransaction(trans);
            return iDonvi.findAll(id, name, tinhs);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

//    public void addCp(CpBO cpBO) throws ServiceException {
//        ITransaction trans = null;
//        try {
//            trans = factory.getTransaction();
////            ICps iCps = factory.getCpsDAO();
//            IDonVi iDonvi=factory.getDonViDAO();
//            trans.connectionType(DB_ADMIN);
//            iDonvi.setTransaction(trans);
//            trans.beginTransaction();
//            iDonvi.addCp(cpBO);
//            trans.commit();
//            trans.endTransaction();
//        } catch (DAOException de) {
//            DatabaseUtils.rollback(trans);
//            logger.error("DAOException : ", de);
//            throw new ServiceException(de);
//        } finally {
//            DatabaseUtils.close(trans);
//        }
//    }
//    public void updateCp(CpBO cpBO) throws ServiceException {
//        ITransaction trans = null;
//        try {
//            trans = factory.getTransaction();
//            ICps iCps = factory.getCpsDAO();
//            trans.connectionType(DB_ADMIN);
//            iCps.setTransaction(trans);
//            trans.beginTransaction();
//            iCps.updateCp(cpBO);
//            trans.commit();
//            trans.endTransaction();
//        } catch (DAOException de) {
//            logger.error("DAOException : ", de);
//            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
//        } finally {
//            DatabaseUtils.close(trans);
//        }
//    }
    //End CP
    //DonVi
    public List<DonViBO> findAllDonVi(String id, String name, String tinhs) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IDonVi iDonVi = factory.getDonViDAO();
            trans.connectionType(DB_ADMIN);
            iDonVi.setTransaction(trans);
            return iDonVi.findAll(id, name, tinhs);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void addDonVi(DonViBO dvBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDonVi iDonVi = factory.getDonViDAO();
            trans.connectionType(DB_ADMIN);
            iDonVi.setTransaction(trans);
            trans.beginTransaction();
            iDonVi.addDonVi(dvBO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void updateDonVi(DonViBO dvBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDonVi iDonVi = factory.getDonViDAO();
            trans.connectionType(DB_ADMIN);
            iDonVi.setTransaction(trans);
            trans.beginTransaction();
            iDonVi.updateDonVi(dvBO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void deleteDonVi(String Id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDonVi users = factory.getDonViDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            trans.beginTransaction();
            users.delete(Id);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Tinh danh muc
    public List<TinhBO> findAllTinh(String id) throws ServiceException {
        return TinhDSFacade.findAllTinh(id);
    }

    // Tinh danh muc
    public String[] findListTinhByUserId(String id) throws Exception {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITinh iTinh = factory.getTinhDAO();
            trans.connectionType(DB_ADMIN);
            iTinh.setTransaction(trans);
            List<TinhBO> list = iTinh.findListTinhByUserId(id);
            String[] tinhIds = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                tinhIds[i] = String.valueOf(list.get(i).getTinhTpId());
            }
            return tinhIds;
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // modify Tinh (add,edit,del)
    public int modifyTinh(String action, TinhBO tinhBO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ITinh iTinh = factory.getTinhDAO();
            trans.connectionType(DB_ADMIN);
            iTinh.setTransaction(trans);
            trans.beginTransaction();
            result = iTinh.modifyTinh(action, tinhBO);
            trans.commit();
            trans.endTransaction();

        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public List<PhancapBO> findAllPhancap(String id, String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IPhancap iPhancap = factory.getPhancapDAO();
            trans.connectionType(DB_ADMIN);
            iPhancap.setTransaction(trans);
            return iPhancap.findAllPhancap(id, name);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NetworkTypeBO> findAllNetworkType(String id, String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            INetworkType iNetworkType = factory.getNetworkTypeDAO();
            trans.connectionType(DB_ADMIN);
            iNetworkType.setTransaction(trans);
            return iNetworkType.findAllNetworkType(id, name);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<FamilyTypeBO> findAllFamilyType(String id, String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IFamilyType iFamilyType = factory.getFamilyTypeDAO();
            trans.connectionType(DB_ADMIN);
            iFamilyType.setTransaction(trans);
            return iFamilyType.findAllFamilyType(id, name);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<AttClassGroupBO> findAttClassGroupByGroupId(String groupId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IClassGroup i = factory.getClassGroupDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAttClassGroupByGroupId(groupId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<String> findClassAttrByUserId(String userId, String action, String objectId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IClassGroup i = factory.getClassGroupDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findClassAttrByUserId(userId, action, objectId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<AttClassListBO> findAttrClassListByObjectId(String objectId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IClassGroup i = factory.getClassGroupDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAttrClassListByObjectId(objectId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ObjectListBO> findAllObjectList(String objectId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IClassGroup i = factory.getClassGroupDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllObjectList(objectId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // History UserLogin
    public int UserLogin(String userId, String Ip) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IUsers iTinh = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            iTinh.setTransaction(trans);
            trans.beginTransaction();
            result = iTinh.UserLogin(userId, Ip);
            trans.commit();
            trans.endTransaction();

        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    //huan.nguyen start
    public List<UserAttrBO> findUserAttrByUserId(String userId) throws ServiceException {
        ITransaction trans = null;
        List<UserAttrBO> result = new ArrayList<>();
        try {
            trans = factory.getTransaction();
            IUsers iUser = factory.getUsersDAO();
            trans.connectionType(DB_ADMIN);
            iUser.setTransaction(trans);
            trans.beginTransaction();
            result = iUser.findUserAttrByUserId(userId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public String updateUserAttr(List<String> sqls) {
        ITransaction trans = null;
        String ret = "";
        try {
            trans = factory.getTransaction();
            IMenuAction menu = factory.getMenuActionDAO();
            trans.connectionType(DB_ADMIN);
            menu.setTransaction(trans);
            trans.beginTransaction();
            ret = menu.updateBatch(sqls);
            if (ret.equals("0")) {
                trans.rollback();
                return "0";
            } else {
                trans.commit();
            }
            trans.endTransaction();
            return "1";
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            trans.rollback();
            throw new DAOException(de);

        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<AttributeBO> findAttrByClassId(long classId) throws ServiceException {
        ITransaction trans = null;
        List<AttributeBO> result = new ArrayList<>();
        try {
            trans = factory.getTransaction();
            IClassGroup classGroupDAO = factory.getClassGroupDAO();
            trans.connectionType(DB_ADMIN);
            classGroupDAO.setTransaction(trans);
            trans.beginTransaction();
            result = classGroupDAO.findAttrByClassId(classId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public List<AttClassListBO> findAllAttClass() throws ServiceException {
        ITransaction trans = null;
        try {
            List<AttClassListBO> listAttClass = this.findAttrClassListByObjectId("");
            return  listAttClass;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public boolean updateAttr(AttributeBO attr) {
        ITransaction trans = null;
        String ret = "";
        try {
            trans = factory.getTransaction();
            IClassGroup classGroupDAO = factory.getClassGroupDAO();
            trans.connectionType(DB_ADMIN);
            classGroupDAO.setTransaction(trans);
            trans.beginTransaction();
            ret = classGroupDAO.updateAttr(attr);
            trans.commit();
            trans.endTransaction();
            return ret.equals("1");
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            trans.rollback();
            throw new DAOException(de);

        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public boolean deleteAttr(AttributeBO attr) {
        ITransaction trans = null;
        String ret = "";
        try {
            trans = factory.getTransaction();
            IClassGroup classGroupDAO = factory.getClassGroupDAO();
            trans.connectionType(DB_ADMIN);
            classGroupDAO.setTransaction(trans);
            trans.beginTransaction();
            ret = classGroupDAO.updateAttr(attr);
            trans.commit();
            trans.endTransaction();
            return ret.equals("1");
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            trans.rollback();
            throw new DAOException(de);

        } finally {
            DatabaseUtils.close(trans);
        }
    }
    //huan.nguyen end
}
