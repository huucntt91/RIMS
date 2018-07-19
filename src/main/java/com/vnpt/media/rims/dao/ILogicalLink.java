package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.LogicalLinkBO;
import com.vnpt.media.rims.bean.NeLinkBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ILogicalLink extends IGeneric {

    public List<LogicalLinkBO> findAllLogicalLinkInfo(int startRow, int endRow, String name, String id) throws DAOException;
    
    public int getTotalLogicalLinkInfo(String name, String id) throws ServiceException;

    public int updateLogicalLinkInfo(LogicalLinkBO item, Long userInsert) throws ServiceException;

    public int addLogicalLinkInfo(LogicalLinkBO item, Long userInsert) throws ServiceException;

    public int deleteLogicalLinkInfo(Long id, Long userInsert) throws DAOException;

}
