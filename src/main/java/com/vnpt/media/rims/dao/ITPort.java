package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.TPortBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITPort extends IGeneric {

    public List<TPortBO> findAllPortByDevice(int startRow, int endRow, String tNodeId, String tEq3Id) throws DAOException;

    public int getTotalPortByDevice(String tnode_id, String teq3_id) throws ServiceException;


}
