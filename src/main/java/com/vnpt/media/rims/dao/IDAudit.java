package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.AuditSummaryBO;
import com.vnpt.media.rims.bean.ComBtsInfoBO;
import com.vnpt.media.rims.bean.ComCell2gInfoBO;
import com.vnpt.media.rims.bean.ComCell3gInfoBO;
import com.vnpt.media.rims.bean.ComCell4gInfoBO;
import com.vnpt.media.rims.bean.ComEnodeBInfoBO;
import com.vnpt.media.rims.bean.ComNodeBInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IDAudit extends IGeneric {

    public List<AuditSummaryBO> getSummary() throws DAOException;

    public List<ComCell2gInfoBO> findAllComCell2gInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String  tinh) throws DAOException;

    public List<ComCell3gInfoBO> findAllComCell3gInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String  tinh) throws DAOException;

    public List<ComCell4gInfoBO> findAllComCell4gInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String  tinh) throws DAOException;

    public List<ComBtsInfoBO> findAllComBtsInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String tinh) throws DAOException;

    public List<ComNodeBInfoBO> findAllComNodeBInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String  tinh) throws DAOException;

    public List<ComEnodeBInfoBO> findAllComEnodeBInfo(Long id, String type, int status, String startRow, String endRow, String tenTrenHeThong, String  tinh) throws DAOException;

    public int getTotalComInfo(Long id, String type, int status, String name, String tenTrenHeThong,String tinh) throws DAOException;

    public int updateStatusCell2gComInfo(String id, int status) throws DAOException;

    public int updateStatusCell3gComInfo(String id, int status) throws DAOException;

    public int updateStatusCell4gComInfo(String id, int status) throws DAOException;

    public int updateStatusBtsComInfo(String id, int status) throws DAOException;

    public int updateStatusNodebComInfo(String id, int status) throws DAOException;

    public int updateStatusEnodeBComInfo(String id, int status) throws DAOException;

}
