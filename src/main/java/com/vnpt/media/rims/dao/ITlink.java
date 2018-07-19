package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.TLinkBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITlink extends IGeneric {

    public List<TLinkBO> findAll(String id, String name,int startRow, int endRow) throws DAOException;

    public int getTotal(String id, String name) throws ServiceException;

    public int update(TLinkBO temp, Long userId) throws ServiceException;

    public int insert(TLinkBO temp, Long userId) throws ServiceException;

    public int delete(TLinkBO temp, Long userId) throws ServiceException;
}
