package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.TLinkBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ITlink;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TlinkFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    public TlinkFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public TlinkFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<TLinkBO> findAll(String id, String name, int startRow, int endRow) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITlink i = factory.getTlinkDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TLinkBO> iRet = i.findAll(id, name, startRow, endRow);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotal(String id, String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITlink i = factory.getTlinkDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.getTotal(id, name);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int update(TLinkBO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITlink i = factory.getTlinkDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.update(temp, userId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int insert(TLinkBO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITlink i = factory.getTlinkDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.insert(temp, userId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int delete(TLinkBO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITlink i = factory.getTlinkDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.delete(temp, userId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
}
