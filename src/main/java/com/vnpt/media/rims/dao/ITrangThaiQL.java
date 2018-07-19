package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.TrangThaiQLBO;



/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITrangThaiQL extends IGeneric {

    public List<TrangThaiQLBO> findAll(String id) throws DAOException;
    
}
