package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.PhancapBO;


/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IPhancap extends IGeneric {

    public List<PhancapBO> findAllPhancap(String id, String name) throws DAOException;
    
}
