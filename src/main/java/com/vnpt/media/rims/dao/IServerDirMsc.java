package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.ServerDirBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IServerDirMsc extends IGeneric {

    public List<ServerDirBO> findAllServerInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalServerInfo(String name, String id) throws ServiceException;

    public int updateServerInfo(ServerDirBO item,Long userInsert) throws ServiceException;

    public int addServerInfo(ServerDirBO item,Long userInsert) throws ServiceException;

    public int deleteServerInfo(Long id, Long userInsert) throws DAOException;

}
