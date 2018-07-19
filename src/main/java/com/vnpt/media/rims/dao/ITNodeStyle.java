package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.TLinkBO;
import com.vnpt.media.rims.bean.TnodeStyleBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITNodeStyle extends IGeneric {

    public List<TnodeStyleBO> findAll(String id, String typeId) throws DAOException;

    public boolean addTnodeStyle(TnodeStyleBO item, Long userId) throws DAOException;

    public boolean updateTnodeStyle(TnodeStyleBO item, Long userId) throws DAOException;

}
