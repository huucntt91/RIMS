package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.DongTbiBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IDongThietBi extends IGeneric {

    public List<DongTbiBO> findAll(Long id, String name, Long tVendorId, Integer startRow, Integer endRow) throws DAOException;

    public int getTotal(Long id, String name, Long tVendorId) throws DAOException;

    public boolean add(DongTbiBO item, Long userId) throws DAOException;

    public boolean update(DongTbiBO item, Long userId) throws DAOException;

    public boolean delete(Long id, Long userId) throws DAOException;

}
