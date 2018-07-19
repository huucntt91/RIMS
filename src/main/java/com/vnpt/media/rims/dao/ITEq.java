package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.TEq1BO;
import com.vnpt.media.rims.bean.TEq2BO;
import com.vnpt.media.rims.bean.TEq3BO;
import com.vnpt.media.rims.bean.TLinkBO;
import com.vnpt.media.rims.bean.TModuleQuangBO;
import com.vnpt.media.rims.bean.TPortBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITEq extends IGeneric {

    public List<TEq1BO> findAllEq1(String id, String name, String tnodeId, int startRow, int endRow) throws ServiceException;

    public List<TEq2BO> findAllEq2(String id, String name, String eq1Id, int startRow, int endRow) throws DAOException;

    public List<TEq3BO> findAllEq3(String id, String name, String eq2Id, int startRow, int endRow) throws DAOException;

    public List<TPortBO> findAllTPort(String id, String name, String eq3Id, int startRow, int endRow) throws DAOException;

    public List<TModuleQuangBO> findAllModuleQuang(String id, String name, String portId, int startRow, int endRow) throws DAOException;

    public int updateTEq1(TEq1BO temp, Long userId) throws ServiceException;

    public int updateTEq2(TEq2BO temp, Long userId) throws ServiceException;

    public int updateTEq3(TEq3BO temp, Long userId) throws ServiceException;

    public int updateTPort(TPortBO temp, Long userId) throws ServiceException;

    public int updateTQuang(TModuleQuangBO temp, Long userId) throws ServiceException;

    public int deleteEq1(String id, Long userId) throws ServiceException;

    public int deleteEq2(String id, Long userId) throws ServiceException;

    public int deleteEq3(String id, Long userId) throws ServiceException;

    public int deleteTport(String id, Long userId) throws ServiceException;

    public int deleteTquang(String id, Long userId) throws ServiceException;
//    public int getTotal(String id, String name) throws ServiceException;
//
//    public int update(TLinkBO temp, Long userId) throws ServiceException;
//
//    public int insert(TLinkBO temp, Long userId) throws ServiceException;

}
