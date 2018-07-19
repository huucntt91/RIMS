package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.SgsnInfoBO;
import com.vnpt.media.rims.bean.NeLinkBO;
import com.vnpt.media.rims.bean.SgsnInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ISgsn extends IGeneric {

    public List<SgsnInfoBO> findAllSgsnInfo(String name, String id) throws DAOException;

    public int updateSgsnInfo(SgsnInfoBO item, Long userInsert) throws ServiceException;

    public int addSgsnInfo(SgsnInfoBO item, Long userInsert) throws ServiceException;

    public int deleteSgsnInfo(Long id) throws DAOException;

//    public List<NeLinkBO> getNeLink(String nodeId) throws DAOException;

    public int addOpcDpc(String node1Id, String listNode2Id, int loaitruyendanId) throws DAOException;
}
