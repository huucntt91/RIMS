package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.DsLamBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IDsLam;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DsLamFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    public DsLamFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public DsLamFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<DsLamBO> findAll(String id, String tnodeName) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDsLam i = factory.getDsLamDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<DsLamBO> iRet = i.findAll(id,tnodeName);
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

    public int update(DsLamBO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDsLam i = factory.getDsLamDAO();
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

    public int insert(DsLamBO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDsLam i = factory.getDsLamDAO();
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

    public int delete(Long id, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDsLam i = factory.getDsLamDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.delete(id, userId);
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
