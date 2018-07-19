package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.DsLamBO;
import com.vnpt.media.rims.bean.GponBO;
import com.vnpt.media.rims.bean.SwitchBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IAccess extends IGeneric {

    public List<DsLamBO> findAllDsLam(Integer startRow, Integer endRow, String tNodeId,String typeId, String tNodeCode, String tNodeName,
            String khuVucId,String provinceId, String districtId, String wardsId) throws DAOException;

    public List<SwitchBO> findAllSwitch(Integer startRow, Integer endRow, String tNodeId, String tNodeCode, String tNodeName,
            String khuVucId,String provinceId, String districtId, String wardsId) throws DAOException;

    public List<GponBO> findAllGpon(Integer startRow, Integer endRow, String tNodeId, String tNodeCode, String tNodeName,
            String khuVucId,String provinceId, String districtId, String wardsId) throws DAOException;

    public int getTotalDsLam(String tNodeId,String typeId, String tNodeCode, String tNodeName,
            String khuVucId,String provinceId, String districtId, String wardsId) throws DAOException;

    public int getTotalSwitch(String tNodeId, String tNodeCode, String tNodeName,
            String khuVucId,String provinceId, String districtId, String wardsId) throws DAOException;

    public int getTotalGpon(String tNodeId, String tNodeCode, String tNodeName,
            String khuVucId,String provinceId, String districtId, String wardsId) throws DAOException;   

    public boolean addDsLam(DsLamBO access, Long userId) throws DAOException;

    public boolean addSwitch(SwitchBO access, Long userId) throws DAOException;

    public boolean addGpon(GponBO access, Long userId) throws DAOException;

    public boolean updateDsLam(DsLamBO access, Long userId) throws DAOException;

    public boolean updateSwitch(SwitchBO access, Long userId) throws DAOException;

    public boolean updateGpon(GponBO access, Long userId) throws DAOException;

}
