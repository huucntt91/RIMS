package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.DsLamBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IDsLam extends IGeneric {

    public List<DsLamBO> findAll(String id, String name) throws DAOException;

    public int update(DsLamBO temp, Long userId) throws ServiceException;

    public int insert(DsLamBO temp, Long userId) throws ServiceException;

    public int delete(Long id, Long userId) throws ServiceException;

}
