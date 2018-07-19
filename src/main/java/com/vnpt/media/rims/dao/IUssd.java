package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.NeLinkBO;
import com.vnpt.media.rims.bean.UssdInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IUssd extends IGeneric {

    public List<UssdInfoBO> findAllUssdInfo(String name, String id) throws DAOException;

    public int updateUssdInfo(UssdInfoBO item, Long userInsert) throws ServiceException;

    public int addUssdInfo(UssdInfoBO item, Long userInsert) throws ServiceException;

    public int deleteUssdInfo(Long id) throws DAOException;

//    public List<NeLinkBO> getNeLink(String nodeId) throws DAOException;

    public int addOpcDpc(String node1Id, String listNode2Id, int loaitruyendanId) throws DAOException;
}
