package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.NgnInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface INgn extends IGeneric {

    public List<NgnInfoBO> findAllNgnInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalNgnInfo(String name, String id) throws ServiceException;

    public int updateNgnInfo(NgnInfoBO item,Long userInsert) throws ServiceException;

    public int addNgnInfo(NgnInfoBO item,Long userInsert) throws ServiceException;

    public int deleteNgnInfo(Long id) throws DAOException;

}
