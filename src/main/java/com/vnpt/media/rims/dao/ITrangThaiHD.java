package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.TrangThaiHDBO;



/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITrangThaiHD extends IGeneric {

    public List<TrangThaiHDBO> findAll(String id) throws DAOException;
    
}
