package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.LogicalLinkBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ILogicalLink;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogicalLinkFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public LogicalLinkFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public LogicalLinkFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }
    
    public int getTotalLogicalLinkInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILogicalLink i = factory.getLogicalLinkDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalLogicalLinkInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<LogicalLinkBO> findAllLogicalLinkInfo(int startRow, int endRow,String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILogicalLink i = factory.getLogicalLinkDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllLogicalLinkInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }   

    public int addLogicalLinkInfo(LogicalLinkBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILogicalLink i = factory.getLogicalLinkDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addLogicalLinkInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateLogicalLinkInfo(LogicalLinkBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILogicalLink i = factory.getLogicalLinkDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateLogicalLinkInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteLogicalLinkInfo(Long nodeId, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILogicalLink i = factory.getLogicalLinkDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteLogicalLinkInfo(nodeId,userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans); 
        }
    }

    
}
