package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.HlrInfoBO;
import com.vnpt.media.rims.bean.HssInfoBO;
import com.vnpt.media.rims.bean.ImsInfoBO;
import com.vnpt.media.rims.bean.MgwInfoBO;
import com.vnpt.media.rims.bean.MscInfoBO;
import com.vnpt.media.rims.bean.NetworkSpaceInfoBO;
import com.vnpt.media.rims.bean.NeLinkBO;
import com.vnpt.media.rims.bean.NgnInfoBO;
import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.StpInfoBO;
import com.vnpt.media.rims.bean.TssInfoBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IHlr;
import com.vnpt.media.rims.dao.IHss;
import com.vnpt.media.rims.dao.IIms;
import com.vnpt.media.rims.dao.IMgw;
import com.vnpt.media.rims.dao.IMsc;
import com.vnpt.media.rims.dao.INetworkSpace;
import com.vnpt.media.rims.dao.INgn;
import com.vnpt.media.rims.dao.IStp;
import com.vnpt.media.rims.dao.ITss;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkSpaceFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public NetworkSpaceFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public NetworkSpaceFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<NetworkSpaceInfoBO> findAllNetworkSpaceInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INetworkSpace i = factory.getNetworkSpaceDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllNetworkSpaceInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalNetworkSpaceInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INetworkSpace i = factory.getNetworkSpaceDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalNetworkSpaceInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addNetworkSpaceInfo(NetworkSpaceInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INetworkSpace i = factory.getNetworkSpaceDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addNetworkSpaceInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateNetworkSpaceInfo(NetworkSpaceInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INetworkSpace i = factory.getNetworkSpaceDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateNetworkSpaceInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteNetworkSpaceInfo(Long networkSpaceId, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INetworkSpace i = factory.getNetworkSpaceDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteNetworkSpaceInfo(networkSpaceId, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

}
