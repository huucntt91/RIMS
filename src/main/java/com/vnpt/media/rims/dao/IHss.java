package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.HssInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IHss extends IGeneric {

    public List<HssInfoBO> findAllHssInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalHssInfo(String name, String id) throws ServiceException;

    public int updateHssInfo(HssInfoBO item,Long userInsert) throws ServiceException;

    public int addHssInfo(HssInfoBO item,Long userInsert) throws ServiceException;

    public int deleteHssInfo(Long id) throws DAOException;

}
