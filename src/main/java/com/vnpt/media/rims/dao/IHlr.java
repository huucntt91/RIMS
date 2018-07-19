package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.HlrInfoBO;
import com.vnpt.media.rims.bean.NeLinkBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IHlr extends IGeneric {

    public List<HlrInfoBO> findAllHlrInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalHlrInfo(String name, String id) throws ServiceException;

    public int updateHlrInfo(HlrInfoBO item, Long userInsert) throws ServiceException;

    public int addHlrInfo(HlrInfoBO item, Long userInsert) throws ServiceException;

    public int deleteHlrInfo(Long id) throws DAOException;

//    public List<NeLinkBO> getNeLink(String nodeId) throws DAOException;

    public int addOpcDpc(String node1Id, String listNode2Id, int loaitruyendanId) throws DAOException;
    
    public int delAllNeLink(String node1Id) throws DAOException;
    
}
