package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.TramDuAnBO;
import com.vnpt.media.rims.bean.TramDuAnTinhExcel;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IPlanStation extends IGeneric {

//    trunglk_start
    List<TramDuAnBO> findTramDAAll(String startRow, String endRow,
            String khuVucId,
            String tinhTpId,
            String idDuAn,
            String maTramDuAn, 
            String tenTramDuAn,
            String msHopDong,
            String maTramBTS,
            String maTramNodeB,
            String maTramQuyHoach,
            Long status) throws DAOException;

    public void addTramDA(TramDuAnBO tramDABO) throws DAOException;

    public int updateTramDA(String action, TramDuAnBO tramDABO) throws DAOException;
    
    public int deleteTramDA(String tramDaId, Long userId) throws DAOException;

    public int updateCCTBTramDA(TramDuAnBO tramDABO) throws DAOException;

    public int updateThongTinChungTramDA(TramDuAnBO tramDABO) throws DAOException;

    public int updateCCHTTramDA(TramDuAnBO tramDABO) throws DAOException;

    public int updateDeployNetXTramDA(TramDuAnBO tramDABO) throws DAOException;

    public int updateDeployQLHTTramDA(TramDuAnBO tramDABO) throws DAOException;

    public int approve(TramDuAnBO tramDABO) throws DAOException;

    public List<TramDuAnBO> findAllDuAn(String startRow, String endRow, String name, String tinhId) throws DAOException;

    public int getTotalAllDuAn(String name, String tinhId) throws DAOException;

    public int getTotalAll(String name, String tinhId) throws DAOException;

    public List<TramDuAnBO> findAllTramKH(String startRow, String endRow, String name, String tinhId) throws DAOException;

//    trunglk_end
//    public String addExcelTramDA(TramDuAnTinhExcel tramDABO, String userId) throws DAOException;
    
    public int getTotalAll(String khuVucId,
                            String tinhTpId,
                            String idDuAn,
                            String maTramDuAn,
                            String tenTramDuAn,
                            String msHopDong,
                            String maTramBTS,
                            String maTramNodeB,
                            String maTramQuyHoach,
                            Long status) throws DAOException;

}
