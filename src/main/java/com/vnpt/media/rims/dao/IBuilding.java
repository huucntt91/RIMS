package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.BuildingBO;
import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.PhuTroBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.ThietBiBO;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IBuilding extends IGeneric {

    public List<BuildingBO> findAll(String startRow, String endRow, String id, String name,String khuvucId, String tinhId, String quanId, String phuongId) throws DAOException;

    public int modify(String action, BuildingBO item) throws DAOException;

    public int getTotalAll(String name,String khuvucId,String tinhId, String quanId, String phuongId) throws DAOException;

    public List<BuildingBO> findBuildingLink(String id) throws DAOException;

    public List<PhuTroBO> findListPhuTroByBuidingId(String id) throws DAOException;

    public List<NodeBO> findNodeById(String buidingId, String chaId) throws DAOException;
    
    public List<NodeBO> findCellByBuildingId(String buidingId) throws DAOException;
}
