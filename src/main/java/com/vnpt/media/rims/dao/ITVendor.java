package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.TVendorBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITVendor extends IGeneric {

    public List<TVendorBO> findAll(String id, String name, Integer startRow, Integer endRow) throws DAOException;

}
