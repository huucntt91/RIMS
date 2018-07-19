package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.FirewallDirBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IFirewallDir;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FirewallDirFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public FirewallDirFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public FirewallDirFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }


    public List<FirewallDirBO> findAllFirewallInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IFirewallDir i = factory.getFirewallDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllFirewallInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalFirewallInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IFirewallDir i = factory.getFirewallDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalFirewallInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addFirewallInfo(FirewallDirBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IFirewallDir i = factory.getFirewallDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addFirewallInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateFirewallInfo(FirewallDirBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IFirewallDir i = factory.getFirewallDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateFirewallInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteFirewallInfo(Long nodeId, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IFirewallDir i = factory.getFirewallDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteFirewallInfo(nodeId, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }



}
