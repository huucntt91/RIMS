package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.CpeBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ICpe;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CpeFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    private static class AccessFacadeHolder {

        private static final CpeFacade INSTANCE = new CpeFacade();
    }

    public CpeFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public CpeFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public static CpeFacade getInstance() {
        return AccessFacadeHolder.INSTANCE;
    }

//    public List<TNodeBO> findAll(String tnodeid, String code, String name) throws ServiceException {
    public List<?> findAll(Integer startRow, Integer endRow, String tNodeId, String tNodeCode, String tNodeName, String tNodeTypeId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICpe i = factory.getCpeDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<?> iRet = new ArrayList<>();
                iRet = i.findAll(startRow, endRow, tNodeId, tNodeCode, tNodeName);

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

    public int getTotal(String tNodeId, String tNodeCode, String tNodeName, String tNodeTypeId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICpe i = factory.getCpeDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = 0;
            
                iRet = i.getTotal(tNodeId, tNodeCode, tNodeName);
           

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
   
    public boolean addCpe(CpeBO cpe, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICpe i = factory.getCpeDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.addCpe(cpe, userId);
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

    
    public boolean updateCpe(CpeBO cpt, Long userId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICpe i = factory.getCpeDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = i.updateCpe(cpt, userId);
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
