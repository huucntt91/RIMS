package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.DAOFactory;
//import com.vnpt.media.rims.dao.ICps;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.DataAuditForm;
import com.vnpt.media.rims.formbean.MonitoringJobAuditForm;
import com.vnpt.media.rims.formbean.NeLinkForm;
import java.util.Date;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
public class DataAuditFacade {

    private Logger logger = LogManager.getLogger(DataAuditFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public DataAuditFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public DataAuditFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<NodeBO> findAllNodeBO(String startRow, String endRow, String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDataAudit i = factory.getDataAuditDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(startRow, endRow, name, khuvucId, tinhId, quanId, phuongId, neTypeId, venderId, statusList);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<?> findDetail(String id, String neTypeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDataAudit i = factory.getDataAuditDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findDetail(id, neTypeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalNode(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDataAudit i = factory.getDataAuditDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalAll(name, khuvucId, tinhId, quanId, phuongId, neTypeId, venderId, statusList);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<DataAuditForm> findNodeLink(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDataAudit i = factory.getDataAuditDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findNodeLink(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addUserConf(DataAuditForm model) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IDataAudit iNode = factory.getDataAuditDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            result = iNode.addUserConf(model);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int removeUserNode(String id) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IDataAudit iNode = factory.getDataAuditDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            result = iNode.removeUserNode(id);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public List<MonitoringJobAuditBO> findMonitoringJobAudit(String neName, Date startDate, Date endDate) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDataAudit i = factory.getDataAuditDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findMonitoringJobAudit(neName, startDate, endDate);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }   

}
