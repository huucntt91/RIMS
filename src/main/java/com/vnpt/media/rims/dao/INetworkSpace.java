package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.NetworkSpaceInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface INetworkSpace extends IGeneric {

    public List<NetworkSpaceInfoBO> findAllNetworkSpaceInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalNetworkSpaceInfo(String name, String id) throws ServiceException;

    public int updateNetworkSpaceInfo(NetworkSpaceInfoBO item,Long userInsert) throws ServiceException;

    public int addNetworkSpaceInfo(NetworkSpaceInfoBO item,Long userInsert) throws ServiceException;

    public int deleteNetworkSpaceInfo(Long id, Long userInsert) throws DAOException;

}
