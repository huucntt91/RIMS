package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.BSCRNCInfoBO;
import com.vnpt.media.rims.bean.HlrInfoBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IBscRnc;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BSCRNCFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public BSCRNCFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public BSCRNCFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<BSCRNCInfoBO> findAllBscRncInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBscRnc i = factory.getBscRncDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllBscRncInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalBscRncInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBscRnc i = factory.getBscRncDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalBscRncInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addBscRncInfo(BSCRNCInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBscRnc i = factory.getBscRncDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addBscRncInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateBscRncInfo(BSCRNCInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IBscRnc i = factory.getBscRncDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.updateBscRncInfo(item, userInsert);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {            
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int deleteBscRncInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBscRnc i = factory.getBscRncDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteBscRncInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }



}
