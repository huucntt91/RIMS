package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.FirewallDirBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IFirewallDir extends IGeneric {

    public List<FirewallDirBO> findAllFirewallInfo(int startRow, int endRow, String name, String id) throws DAOException;

    public int getTotalFirewallInfo(String name, String id) throws ServiceException;

    public int updateFirewallInfo(FirewallDirBO item,Long userInsert) throws ServiceException;

    public int addFirewallInfo(FirewallDirBO item,Long userInsert) throws ServiceException;

    public int deleteFirewallInfo(Long id, Long userInsert) throws DAOException;

}
