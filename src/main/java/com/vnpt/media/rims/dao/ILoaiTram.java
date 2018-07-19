package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.LoaiNeBO;
import com.vnpt.media.rims.bean.LoaiTramBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.ThietBiBO;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ILoaiTram extends IGeneric {

    public List<LoaiTramBO> findAll(String id) throws DAOException;

    public List<LoaiTramBO> findAllByNeTypeId(String id,int neTypeId) throws DAOException;

    public int modify(String action, LoaiTramBO thietBiBO) throws DAOException;

    public List<LoaiNeBO> findAllNe(String id) throws DAOException;
}
