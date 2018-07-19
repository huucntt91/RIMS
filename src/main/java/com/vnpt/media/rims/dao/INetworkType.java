package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.NetworkTypeBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;


/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface INetworkType extends IGeneric {

    public List<NetworkTypeBO> findAllNetworkType(String id, String name) throws DAOException;
    
}
