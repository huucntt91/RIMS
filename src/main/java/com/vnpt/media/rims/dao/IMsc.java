package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.MscInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IMsc extends IGeneric {

    public List<MscInfoBO> findAllMscInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalMscInfo(String name, String id) throws ServiceException;

    public int updateMscInfo(MscInfoBO item,Long userInsert) throws ServiceException;

    public int addMscInfo(MscInfoBO item,Long userInsert) throws ServiceException;

    public int deleteMscInfo(Long id) throws DAOException;

}
