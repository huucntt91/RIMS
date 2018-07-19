package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.ComBtsInfoBO;
import com.vnpt.media.rims.bean.MonitoringJobAuditBO;
import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;
import com.vnpt.media.rims.formbean.DataAuditForm;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IDataAudit extends IGeneric {

    public List<NodeBO> findAll(String startRow, String endRow, String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws DAOException;

    public List<?> findDetail(String id, String neTypeId) throws DAOException;

    public int getTotalAll(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws DAOException;

    public List<DataAuditForm> findNodeLink(String id) throws DAOException;

    public int addUserConf(DataAuditForm bean) throws DAOException;

    public int removeUserNode(String id) throws DAOException;

    public List<MonitoringJobAuditBO> findMonitoringJobAudit(String neName, Date startDate, Date endDate) throws DAOException;

    
}
