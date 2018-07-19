package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.TVendorBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ITVendor;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TVendorFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    private static class AccessFacadeHolder {

        private static final TVendorFacade INSTANCE = new TVendorFacade();
    }

    public TVendorFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public TVendorFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public static TVendorFacade getInstance() {
        return AccessFacadeHolder.INSTANCE;
    }

//    public List<TNodeBO> findAll(String tnodeid, String code, String name) throws ServiceException {
    public List<TVendorBO> findAll(String id, String name, Integer startRow, Integer endRow) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITVendor i = factory.getTVendorDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TVendorBO> iRet = new ArrayList<>();
            iRet = i.findAll(id, name, startRow, endRow);
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
