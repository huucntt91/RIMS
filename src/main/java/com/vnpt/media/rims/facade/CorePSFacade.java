package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.CrbtInfoBO;
import com.vnpt.media.rims.bean.DnsInfoBO;
import com.vnpt.media.rims.bean.DsrInfoBO;
import com.vnpt.media.rims.bean.FdaInfoBO;
import com.vnpt.media.rims.bean.GgsnInfoBO;
import com.vnpt.media.rims.bean.McaInfoBO;
import com.vnpt.media.rims.bean.MmeInfoBO;
import com.vnpt.media.rims.bean.PgwInfoBO;
import com.vnpt.media.rims.bean.RcInfoBO;
import com.vnpt.media.rims.bean.SapcInfoBO;
import com.vnpt.media.rims.bean.SdpInfoBO;
import com.vnpt.media.rims.bean.SgsnInfoBO;
import com.vnpt.media.rims.bean.SmppInfoBO;
import com.vnpt.media.rims.bean.SmscInfoBO;
import com.vnpt.media.rims.bean.UssdInfoBO;

import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ICrbt;
import com.vnpt.media.rims.dao.IDns;
import com.vnpt.media.rims.dao.IDsr;
import com.vnpt.media.rims.dao.IFda;
import com.vnpt.media.rims.dao.IGgsn;
import com.vnpt.media.rims.dao.IMca;
import com.vnpt.media.rims.dao.IMme;
import com.vnpt.media.rims.dao.IPgw;
import com.vnpt.media.rims.dao.IRc;
import com.vnpt.media.rims.dao.ISapc;
import com.vnpt.media.rims.dao.ISdp;
import com.vnpt.media.rims.dao.ISgsn;
import com.vnpt.media.rims.dao.ISmpp;
import com.vnpt.media.rims.dao.ISmsc;
import com.vnpt.media.rims.dao.IUssd;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CorePSFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public CorePSFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public CorePSFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<SgsnInfoBO> findAllSgsnInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISgsn i = factory.getSgsnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllSgsnInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    

    public int addSgsnInfo(SgsnInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISgsn i = factory.getSgsnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addSgsnInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateSgsnInfo(SgsnInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISgsn i = factory.getSgsnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateSgsnInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteSgsnInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISgsn i = factory.getSgsnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteSgsnInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<GgsnInfoBO> findAllGgsnInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGgsn i = factory.getGgsnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllGgsnInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    

    public int addGgsnInfo(GgsnInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGgsn i = factory.getGgsnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addGgsnInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateGgsnInfo(GgsnInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGgsn i = factory.getGgsnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateGgsnInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteGgsnInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGgsn i = factory.getGgsnDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteGgsnInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    
    public List<MmeInfoBO> findAllMmeInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMme i = factory.getMmeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllMmeInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    

    public int addMmeInfo(MmeInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMme i = factory.getMmeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addMmeInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateMmeInfo(MmeInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMme i = factory.getMmeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateMmeInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deleteMmeInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMme i = factory.getMmeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteMmeInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public List<PgwInfoBO> findAllPgwInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPgw i = factory.getPgwDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllPgwInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    

    public int addPgwInfo(PgwInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPgw i = factory.getPgwDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addPgwInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updatePgwInfo(PgwInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPgw i = factory.getPgwDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updatePgwInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int deletePgwInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPgw i = factory.getPgwDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deletePgwInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

//trunglk_start
//    smsc
    public List<SmscInfoBO> findAllSmscInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISmsc i = factory.getSmscDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllSmscInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addSmscInfo(SmscInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISmsc i = factory.getSmscDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addSmscInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateSmscInfo(SmscInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISmsc i = factory.getSmscDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateSmscInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteSmscInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISmsc i = factory.getSmscDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteSmscInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//    smpp
    public List<SmppInfoBO> findAllSmppInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISmpp i = factory.getSmppDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllSmppInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addSmppInfo(SmppInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISmpp i = factory.getSmppDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addSmppInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateSmppInfo(SmppInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISmpp i = factory.getSmppDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateSmppInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteSmppInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISmpp i = factory.getSmppDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteSmppInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
//    dsr
    public List<DsrInfoBO> findAllDsrInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDsr i = factory.getDsrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllDsrInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addDsrInfo(DsrInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDsr i = factory.getDsrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addDsrInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateDsrInfo(DsrInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDsr i = factory.getDsrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateDsrInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteDsrInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDsr i = factory.getDsrDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteDsrInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//    mca
    public List<McaInfoBO> findAllMcaInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMca i = factory.getMcaDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllMcaInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addMcaInfo(McaInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMca i = factory.getMcaDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addMcaInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateMcaInfo(McaInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMca i = factory.getMcaDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateMcaInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteMcaInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IMca i = factory.getMcaDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteMcaInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//    ussd
    public List<UssdInfoBO> findAllUssdInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUssd i = factory.getUssdDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllUssdInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addUssdInfo(UssdInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUssd i = factory.getUssdDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addUssdInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateUssdInfo(UssdInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUssd i = factory.getUssdDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateUssdInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteUssdInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IUssd i = factory.getUssdDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteUssdInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//    dns
    public List<DnsInfoBO> findAllDnsInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDns i = factory.getDnsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllDnsInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addDnsInfo(DnsInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDns i = factory.getDnsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addDnsInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateDnsInfo(DnsInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDns i = factory.getDnsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateDnsInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteDnsInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDns i = factory.getDnsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteDnsInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//    crbt
    public List<CrbtInfoBO> findAllCrbtInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICrbt i = factory.getCrbtDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllCrbtInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addCrbtInfo(CrbtInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICrbt i = factory.getCrbtDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addCrbtInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateCrbtInfo(CrbtInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICrbt i = factory.getCrbtDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateCrbtInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteCrbtInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICrbt i = factory.getCrbtDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteCrbtInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//    rc
    public List<RcInfoBO> findAllRcInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IRc i = factory.getRcDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllRcInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addRcInfo(RcInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IRc i = factory.getRcDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addRcInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateRcInfo(RcInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IRc i = factory.getRcDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateRcInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteRcInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IRc i = factory.getRcDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteRcInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//    sdp
    public List<SdpInfoBO> findAllSdpInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISdp i = factory.getSdpDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllSdpInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addSdpInfo(SdpInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISdp i = factory.getSdpDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addSdpInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateSdpInfo(SdpInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISdp i = factory.getSdpDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateSdpInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteSdpInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISdp i = factory.getSdpDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteSdpInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
//    fda
    public List<FdaInfoBO> findAllFdaInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IFda i = factory.getFdaDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllFdaInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addFdaInfo(FdaInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IFda i = factory.getFdaDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addFdaInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateFdaInfo(FdaInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IFda i = factory.getFdaDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateFdaInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteFdaInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IFda i = factory.getFdaDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteFdaInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
   //    sapc
    public List<SapcInfoBO> findAllSapcInfo(String name, String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISapc i = factory.getSapcDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllSapcInfo(name, id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int addSapcInfo(SapcInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISapc i = factory.getSapcDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addSapcInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int updateSapcInfo(SapcInfoBO item, Long userInsert) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISapc i = factory.getSapcDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateSapcInfo(item, userInsert);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public int deleteSapcInfo(Long nodeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ISapc i = factory.getSapcDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.deleteSapcInfo(nodeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    } 
    
    
//trunglk_end
    
    
    
}
