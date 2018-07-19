package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.MgwInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IMgw extends IGeneric {

    public List<MgwInfoBO> findAllMgwInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalMgwInfo(String name, String id) throws ServiceException;

    public int updateMgwInfo(MgwInfoBO item,Long userInsert) throws ServiceException;

    public int addMgwInfo(MgwInfoBO item,Long userInsert) throws ServiceException;

    public int deleteMgwInfo(Long id) throws DAOException;

}
