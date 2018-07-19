package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.TssInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITss extends IGeneric {

    public List<TssInfoBO> findAllTssInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalTssInfo(String name, String id) throws ServiceException;

    public int updateTssInfo(TssInfoBO item, Long userInsert) throws ServiceException;

    public int addTssInfo(TssInfoBO item, Long userInsert) throws ServiceException;

    public int deleteTssInfo(Long id) throws DAOException;

    public List<NodeBO> getNode(String listNeTypeId) throws ServiceException;
}
