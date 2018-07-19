package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.ThietBiBO;



/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ILoaiAcQuy extends IGeneric {

    public List<LoaiAcQuyBO> findAll(String id) throws DAOException;
    public int modify(String action, LoaiAcQuyBO loaiAcQuyBO) throws DAOException;
}
