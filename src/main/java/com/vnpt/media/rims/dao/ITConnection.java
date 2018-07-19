package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.TConnectionInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITConnection extends IGeneric {

    public List<TConnectionInfoBO> findAllTConnectionInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalTConnectionInfo(String name, String id) throws ServiceException;

    public int updateTConnectionInfo(TConnectionInfoBO item,Long userInsert) throws ServiceException;

    public int addTConnectionInfo(TConnectionInfoBO item,Long userInsert) throws ServiceException;

    public int deleteTConnectionInfo(Long id, Long userInsert) throws DAOException;

}
