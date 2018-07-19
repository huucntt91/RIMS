package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.BSCRNCInfoBO;
import com.vnpt.media.rims.bean.NeLinkBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IBscRnc extends IGeneric {

    public List<BSCRNCInfoBO> findAllBscRncInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalBscRncInfo(String name, String id) throws ServiceException;

    public int updateBscRncInfo(BSCRNCInfoBO item, Long userInsert) throws ServiceException;

    public int addBscRncInfo(BSCRNCInfoBO item, Long userInsert) throws ServiceException;

    public int deleteBscRncInfo(Long id) throws DAOException;

//    public List<NeLinkBO> getNeLink(String nodeId) throws DAOException;

}
