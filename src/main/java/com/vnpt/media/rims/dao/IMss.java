package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.MssInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IMss extends IGeneric {

    public List<MssInfoBO> findAllMssInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalMssInfo(String name, String id) throws ServiceException;

    public int updateMssInfo(MssInfoBO item,Long userInsert) throws ServiceException;

    public int addMssInfo(MssInfoBO item,Long userInsert) throws ServiceException;

    public int deleteMssInfo(Long id) throws DAOException;

}
