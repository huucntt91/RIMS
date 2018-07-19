package com.vnpt.media.rims.facade;


import com.vnpt.media.rims.bean.TPortBO;
import com.vnpt.media.rims.bean.ViewCardBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IViewCard;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewCardFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    public ViewCardFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public ViewCardFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<ViewCardBO> findCardbyDevice(String tNodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IViewCard i = factory.getViewCardDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            return i.findCardbyDevice(tNodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public List<ViewCardBO> findHangSlot(String tNodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IViewCard i = factory.getViewCardDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            return i.findHangSlot(tNodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }



}
