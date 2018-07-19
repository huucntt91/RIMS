package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.BuildingBO;
import com.vnpt.media.rims.bean.PhuTroBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IPhuTro extends IGeneric {

    public List<PhuTroBO> findAll(String startRow, String endRow, String id, String khuvucId,String tinhId,String quanId, String phuongId, String code) throws DAOException;

    public int modify(String action, PhuTroBO item) throws DAOException;

    public int getTotalAll(String id, String khuvucId,String tinhId,String quanId, String phuongId, String code) throws DAOException;

    public List<PhuTroBO> findSupportBuilding(String id) throws DAOException;
    }
