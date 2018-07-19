package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.TPortBO;
import com.vnpt.media.rims.bean.ViewCardBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IViewCard extends IGeneric {

    public List<ViewCardBO> findCardbyDevice(String tNodeId) throws DAOException;

    public List<ViewCardBO> findHangSlot(String tNodeId) throws DAOException;

}
