package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.UserAttrBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.bean.UserGroupBO;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IUsers extends IGeneric {

    List<UserBO> findAll(String fullname, int startRow, int endRow) throws DAOException;

    int getTotal(String fullname) throws DAOException;
//    List<UserBO> findByGroupRoleId(String groupRoleId) throws DAOException;
//

//
    List<GroupBO> findGroupByUserId(Long userId) throws DAOException;
//

    public UserBO findByUserId(String userId) throws DAOException;

    public UserBO findByUsername(String username) throws DAOException;
//    UserBO findByUsername(String username) throws DAOException;
//
//    UserBO findByEmail(String email) throws DAOException;
//
//    void insert(UserBO userBO) throws DAOException;
//

    void resetPassword(UserBO userBO) throws DAOException;
//
//    void changePassword(UserBO userBO,String passwordOld) throws DAOException;
//
//    UserBO checkLogin(String username,String password) throws DAOException;

    public void add(UserBO userBO) throws DAOException;

    void update(UserBO userBO) throws DAOException;
//

    void delete(String userId) throws DAOException;
//

    void activeUser(UserBO userBO) throws DAOException;
//
//    List<UserBO> findUserByMoCommGroupId(String commandGroupId) throws DAOException;
//

    UserBO checkLogin(String email, String password) throws DAOException;
    
    int UserLogin(String userId, String Ip) throws DAOException;

    List<UserAttrBO> findUserAttrByUserId(String userId) throws DAOException;
}
