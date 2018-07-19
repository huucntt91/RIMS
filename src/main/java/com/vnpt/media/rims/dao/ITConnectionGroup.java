package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.TConnectionGroupInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITConnectionGroup extends IGeneric {

    public List<TConnectionGroupInfoBO> findAllTConnectionGroupInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalTConnectionGroupInfo(String name, String id) throws ServiceException;

    public int updateTConnectionGroupInfo(TConnectionGroupInfoBO item,Long userInsert) throws ServiceException;

    public int addTConnectionGroupInfo(TConnectionGroupInfoBO item,Long userInsert) throws ServiceException;

    public int deleteTConnectionGroupInfo(Long id, Long userInsert) throws DAOException;

}
