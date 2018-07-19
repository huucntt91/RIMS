package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.StpInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IStp extends IGeneric {

    public List<StpInfoBO> findAllStpInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalStpInfo(String name, String id) throws ServiceException;

    public int updateStpInfo(StpInfoBO item,Long userInsert) throws ServiceException;

    public int addStpInfo(StpInfoBO item,Long userInsert) throws ServiceException;

    public int deleteStpInfo(Long id) throws DAOException;

}
