package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.TnodeTypeBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ITNodeType;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TNodeTypeFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    private static class TNodeStyleFacadeHolder {

        private static final TNodeTypeFacade INSTANCE = new TNodeTypeFacade();
    }

    public TNodeTypeFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public TNodeTypeFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public static TNodeTypeFacade getInstance() {
        return TNodeTypeFacade.TNodeStyleFacadeHolder.INSTANCE;
    }

    public List<TnodeTypeBO> findAll(String id,String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITNodeType i = factory.getTNodeTypeDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TnodeTypeBO> iRet = i.findAll(id,name);
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
