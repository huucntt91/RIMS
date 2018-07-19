package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.CpeBO;
import com.vnpt.media.rims.bean.DsLamBO;
import com.vnpt.media.rims.bean.GponBO;
import com.vnpt.media.rims.bean.SwitchBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ICpe extends IGeneric {

    public List<CpeBO> findAll(Integer startRow, Integer endRow, String tNodeId, String tNodeCode, String tNodeName) throws DAOException;

    public int getTotal(String tNodeId, String tNodeCode, String tNodeName) throws DAOException;

    public boolean addCpe(CpeBO access, Long userId) throws DAOException;

    public boolean updateCpe(CpeBO access, Long userId) throws DAOException;


}
