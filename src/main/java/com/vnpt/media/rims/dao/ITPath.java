package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.TPathInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITPath extends IGeneric {

    public List<TPathInfoBO> findAllTPathInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalTPathInfo(String name, String id) throws ServiceException;

    public int updateTPathInfo(TPathInfoBO item,Long userInsert) throws ServiceException;

    public int addTPathInfo(TPathInfoBO item,Long userInsert) throws ServiceException;

    public int deleteTPathInfo(Long id, Long userInsert) throws DAOException;

}
