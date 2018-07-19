package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.CardBO;
import com.vnpt.media.rims.bean.DataViewBO;
import com.vnpt.media.rims.bean.PortBO;
import com.vnpt.media.rims.bean.RackBO;
import com.vnpt.media.rims.bean.SlotBO;
import com.vnpt.media.rims.bean.SubRackBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IEquipment;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
//import com.vnpt.media.rims.dao.ICps;

/**
 *
 * @author VNP
 */
public class EquipmentFacade {

    private Logger logger = LogManager.getLogger(EquipmentFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public EquipmentFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public EquipmentFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<DataViewBO> getDataView(Long nodeId) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IEquipment iEquipment = factory.getEquipmentDAO();
            trans.connectionType(Constants.DB_CB);
            iEquipment.setTransaction(trans);
            return iEquipment.getDataView(nodeId,0, 0);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public RackBO viewRack(String id) throws ServiceException {

        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IEquipment iEquipment = factory.getEquipmentDAO();
            trans.connectionType(Constants.DB_CB);
            iEquipment.setTransaction(trans);
            return iEquipment.viewRack(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public SubRackBO viewSubRack(String id) throws ServiceException {

        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IEquipment iEquipment = factory.getEquipmentDAO();
            trans.connectionType(Constants.DB_CB);
            iEquipment.setTransaction(trans);
            return iEquipment.viewSubRack(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public SlotBO viewSlot(String id) throws ServiceException {

        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IEquipment iEquipment = factory.getEquipmentDAO();
            trans.connectionType(Constants.DB_CB);
            iEquipment.setTransaction(trans);
            return iEquipment.viewSlot(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public CardBO viewCard(String id) throws ServiceException {

        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IEquipment iEquipment = factory.getEquipmentDAO();
            trans.connectionType(Constants.DB_CB);
            iEquipment.setTransaction(trans);
            return iEquipment.viewCard(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public PortBO viewPort(String id) throws ServiceException {

        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IEquipment iEquipment = factory.getEquipmentDAO();
            trans.connectionType(Constants.DB_CB);
            iEquipment.setTransaction(trans);
            return iEquipment.viewPort(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
}
