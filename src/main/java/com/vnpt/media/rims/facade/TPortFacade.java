package com.vnpt.media.rims.facade;


import com.vnpt.media.rims.bean.TPortBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ITPort;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TPortFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    public TPortFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public TPortFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<TPortBO> findAllNetworkSpaceInfo(int startRow, int endRow, String tNodeId, String tEq3Id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITPort i = factory.getTPortDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            return i.findAllPortByDevice(startRow, endRow, tNodeId, tEq3Id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalNetworkSpaceInfo(String tnode_id, String teq3_id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITPort i = factory.getTPortDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            return i.getTotalPortByDevice(tnode_id, teq3_id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }


}
