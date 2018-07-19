package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.NeLinkBO;
import com.vnpt.media.rims.bean.GgsnInfoBO;
import com.vnpt.media.rims.bean.DsrInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IDsr extends IGeneric {

    public List<DsrInfoBO> findAllDsrInfo(String name, String id) throws DAOException;

    public int updateDsrInfo(DsrInfoBO item, Long userInsert) throws ServiceException;

    public int addDsrInfo(DsrInfoBO item, Long userInsert) throws ServiceException;

    public int deleteDsrInfo(Long id) throws DAOException;

//    public List<NeLinkBO> getNeLink(String nodeId) throws DAOException;

    public int addOpcDpc(String node1Id, String listNode2Id, int loaitruyendanId) throws DAOException;
}
