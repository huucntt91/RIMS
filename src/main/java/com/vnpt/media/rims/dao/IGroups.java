package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.exception.ServiceException;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IGroups extends IGeneric {

    List<GroupBO> findAll(String name) throws DAOException;

    public GroupBO findByGroupId(String groupId) throws DAOException;

    public void add(GroupBO groupBO) throws DAOException;

    void update(GroupBO groupBO) throws DAOException;
//

    void delete(String groupId) throws DAOException;

    void clone(String groupId) throws DAOException;

    public List<GroupBO> getGroupByUserId(String userId) throws ServiceException;

    public List<GroupBO> getGroupByNotUserId(String userId) throws ServiceException;

}
