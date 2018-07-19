package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ServerDirBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IServerDirMsc;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerDirFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public ServerDirFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public ServerDirFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }


    public List<ServerDirBO> findAllServerInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IServerDirMsc i = factory.getServerDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllServerInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalServerInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IServerDirMsc i = factory.getServerDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalServerInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addServerInfo(ServerDirBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IServerDirMsc i = factory.getServerDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addServerInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateServerInfo(ServerDirBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IServerDirMsc i = factory.getServerDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateServerInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteServerInfo(Long nodeId, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IServerDirMsc i = factory.getServerDirDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteServerInfo(nodeId, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }



}
