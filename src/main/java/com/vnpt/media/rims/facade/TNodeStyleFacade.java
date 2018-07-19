package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.TnodeStyleBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ITNodeStyle;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TNodeStyleFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    private static class TNodeStyleFacadeHolder {

        private static final TNodeStyleFacade INSTANCE = new TNodeStyleFacade();
    }

    public TNodeStyleFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public TNodeStyleFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public static TNodeStyleFacade getInstance() {
        return TNodeStyleFacade.TNodeStyleFacadeHolder.INSTANCE;
    }

    public List<TnodeStyleBO> findAll(String id, String typeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITNodeStyle i = factory.getTNodeStyleDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TnodeStyleBO> iRet = i.findAll(id, typeId);
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
    
    public boolean addTnodeStyle(TnodeStyleBO item, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITNodeStyle i = factory.getTNodeStyleDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.addTnodeStyle(item, userId);
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
    
       public boolean updateTnodeStyle(TnodeStyleBO item, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITNodeStyle i = factory.getTNodeStyleDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.updateTnodeStyle(item, userId);
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
