package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.ImsInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IIms extends IGeneric {

    public List<ImsInfoBO> findAllImsInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalImsInfo(String name, String id) throws ServiceException;

    public int updateImsInfo(ImsInfoBO item,Long userInsert) throws ServiceException;

    public int addImsInfo(ImsInfoBO item,Long userInsert) throws ServiceException;

    public int deleteImsInfo(Long id) throws DAOException;

}
