package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.HlrInfoBO;
import com.vnpt.media.rims.bean.HssInfoBO;
import com.vnpt.media.rims.bean.ImsInfoBO;
import com.vnpt.media.rims.bean.MgwInfoBO;
import com.vnpt.media.rims.bean.MscInfoBO;
import com.vnpt.media.rims.bean.MssInfoBO;
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
import com.vnpt.media.rims.dao.IMss;
import com.vnpt.media.rims.dao.INgn;
import com.vnpt.media.rims.dao.IStp;
import com.vnpt.media.rims.dao.ITss;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoreFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public CoreFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public CoreFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<HlrInfoBO> findAllHlrInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHlr i = factory.getHlrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllHlrInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalHlrInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHlr i = factory.getHlrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalHlrInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addHlrInfo(HlrInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHlr i = factory.getHlrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addHlrInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateHlrInfo(HlrInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHlr i = factory.getHlrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateHlrInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteHlrInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHlr i = factory.getHlrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteHlrInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<HssInfoBO> findAllHssInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHss i = factory.getHssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllHssInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalHssInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHss i = factory.getHssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalHssInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addHssInfo(HssInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHss i = factory.getHssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addHssInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateHssInfo(HssInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHss i = factory.getHssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateHssInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteHssInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHss i = factory.getHssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteHssInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ImsInfoBO> findAllImsInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IIms i = factory.getImsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllImsInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalImsInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IIms i = factory.getImsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalImsInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addImsInfo(ImsInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IIms i = factory.getImsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addImsInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateImsInfo(ImsInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IIms i = factory.getImsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateImsInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteImsInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IIms i = factory.getImsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteImsInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<MgwInfoBO> findAllMgwInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMgw i = factory.getMgwDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllMgwInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalMgwInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMgw i = factory.getMgwDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalMgwInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addMgwInfo(MgwInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMgw i = factory.getMgwDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addMgwInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateMgwInfo(MgwInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMgw i = factory.getMgwDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateMgwInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteMgwInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMgw i = factory.getMgwDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteMgwInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<MscInfoBO> findAllMscInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMsc i = factory.getMscDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllMscInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalMscInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMsc i = factory.getMscDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalMscInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addMscInfo(MscInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMsc i = factory.getMscDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addMscInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateMscInfo(MscInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMsc i = factory.getMscDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateMscInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteMscInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMsc i = factory.getMscDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteMscInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<MssInfoBO> findAllMssInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMss i = factory.getMssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllMssInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalMssInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMss i = factory.getMssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalMssInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addMssInfo(MssInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMss i = factory.getMssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addMssInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateMssInfo(MssInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMss i = factory.getMssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateMssInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteMssInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMss i = factory.getMssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteMssInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NgnInfoBO> findAllNgnInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INgn i = factory.getNgnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllNgnInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalNgnInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INgn i = factory.getNgnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalNgnInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addNgnInfo(NgnInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INgn i = factory.getNgnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addNgnInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateNgnInfo(NgnInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INgn i = factory.getNgnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateNgnInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteNgnInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INgn i = factory.getNgnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteNgnInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<StpInfoBO> findAllStpInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IStp i = factory.getStpDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllStpInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalStpInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IStp i = factory.getStpDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalStpInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addStpInfo(StpInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IStp i = factory.getStpDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addStpInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateStpInfo(StpInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IStp i = factory.getStpDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateStpInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteStpInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IStp i = factory.getStpDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteStpInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<TssInfoBO> findAllTssInfo(int startRow, int endRow, String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITss i = factory.getTssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllTssInfo(startRow, endRow, name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalTssInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITss i = factory.getTssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalTssInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addTssInfo(TssInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITss i = factory.getTssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addTssInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateTssInfo(TssInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITss i = factory.getTssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateTssInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteTssInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITss i = factory.getTssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteTssInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NodeBO> getNode(String listNeTypeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITss i = factory.getTssDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getNode(listNeTypeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int insertNeLink(String node1Id, String listNode2Id, int loaitruyendanId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHlr i = factory.getHlrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            
            for (int j = 0; j < listNode2Id.split(",").length; j++) {
                i.addOpcDpc(node1Id, listNode2Id.split(",")[j], loaitruyendanId);
            }

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
        return 1;
    }

    public int insertDeleteNeLink(String node1Id, String listNode2Id, int loaitruyendanId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IHlr i = factory.getHlrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            
            i.delAllNeLink(node1Id);
            if (listNode2Id != null && listNode2Id != "") {
                for (int j = 0; j < listNode2Id.split(",").length; j++) {
                    i.addOpcDpc(node1Id, listNode2Id.split(",")[j], loaitruyendanId);
                }
            }
            trans.commit();
        } catch (DAOException de) {
            trans.rollback();
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
        return 1;
    }
//    public List<NeLinkBO> getNeLink(String nodeId) throws ServiceException {
//        ITransaction trans = null;
//        try {
//            trans = factory.getTransaction();
//            IHlr i = factory.getHlrDAO();
//            trans.connectionType(DB_ADMIN);
//            i.setTransaction(trans);
//            return i.getNeLink(nodeId);
//        } catch (DAOException de) {
//            logger.error("DAOException : ", de);
//            throw new ServiceException(de);
//        } finally {
//            DatabaseUtils.close(trans);
//        }
//    }

}
