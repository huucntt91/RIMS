package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.HlrInfoBO;
import com.vnpt.media.rims.bean.HssInfoBO;
import com.vnpt.media.rims.bean.ImsInfoBO;
import com.vnpt.media.rims.bean.MgwInfoBO;
import com.vnpt.media.rims.bean.MscInfoBO;
import com.vnpt.media.rims.bean.TPathInfoBO;
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
import com.vnpt.media.rims.dao.ITPath;
import com.vnpt.media.rims.dao.INgn;
import com.vnpt.media.rims.dao.IStp;
import com.vnpt.media.rims.dao.ITPath;
import com.vnpt.media.rims.dao.ITss;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TPathFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public TPathFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public TPathFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<TPathInfoBO> findAllTPathInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITPath i = factory.getTPathDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllTPathInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalTPathInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITPath i = factory.getTPathDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalTPathInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addTPathInfo(TPathInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITPath i = factory.getTPathDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addTPathInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateTPathInfo(TPathInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITPath i = factory.getTPathDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateTPathInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteTPathInfo(Long nodeId, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITPath i = factory.getTPathDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteTPathInfo(nodeId, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

}
