package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.TEq1BO;
import com.vnpt.media.rims.bean.TEq2BO;
import com.vnpt.media.rims.bean.TEq3BO;
import com.vnpt.media.rims.bean.TModuleQuangBO;
import com.vnpt.media.rims.bean.TPortBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ITEq;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TEqFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    public TEqFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public TEqFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public JasperPrint export(String jasperFileName, Map<String, Object> params) {
        String pathJasper = "/com/jasper/template/";
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
//            List<TEq1BO> iRet = i.findAllEq1(id, name, tnodeId, startRow, endRow);
            Connection con = trans.getConnection();
//            String source = "D:\\svn_94\\RIMS_155\\SourceCode\\RIMS\\src\\main\\java\\test\\report1.jrxml";
//            InputStream resourceAsStream = getClass().getResourceAsStream("/com/jasper/template/report1.jrxml");
            InputStream resourceAsStream = getClass().getResourceAsStream(pathJasper + jasperFileName);
            JasperReport jr = JasperCompileManager.compileReport(resourceAsStream);

//            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", "Lạc Tân");
//                params.put("className", className); 
//                con = getConnection();  
            JasperPrint jp = JasperFillManager.fillReport(jr, params, con);

            trans.commit();
            trans.endTransaction();
            return jp;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } catch (Exception de) {
//            dlogger.error(e.getMessage(), e);
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<TEq1BO> findAllEq1(String id, String name, String tnodeId, int startRow, int endRow) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TEq1BO> iRet = i.findAllEq1(id, name, tnodeId, startRow, endRow);
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

    public List<TEq2BO> findAllEq2(String id, String name, String tnodeId, int startRow, int endRow) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TEq2BO> iRet = i.findAllEq2(id, name, tnodeId, startRow, endRow);
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

    public List<TEq3BO> findAllEq3(String id, String name, String tnodeId, int startRow, int endRow) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TEq3BO> iRet = i.findAllEq3(id, name, tnodeId, startRow, endRow);
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

    public List<TPortBO> findAllPort(String id, String name, String tnodeId, int startRow, int endRow) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TPortBO> iRet = i.findAllTPort(id, name, tnodeId, startRow, endRow);
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

    public List<TModuleQuangBO> findAllModuleQuang(String id, String name, String tnodeId, int startRow, int endRow) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TModuleQuangBO> iRet = i.findAllModuleQuang(id, name, tnodeId, startRow, endRow);
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

//    public int getTotal(String id, String name) throws ServiceException {
//        ITransaction trans = null;
//        try {
//            trans = factory.getTransaction();
//            ITlink i = factory.getTlinkDAO();
//            trans.connectionType(DB_RIMS_BB);
//            i.setTransaction(trans);
//            trans.beginTransaction();
//            int iRet = i.getTotal(id, name);
//            trans.commit();
//            trans.endTransaction();
//            return iRet;
//        } catch (DAOException de) {
//            logger.error("DAOException : ", de);
//            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
//        } finally {
//            DatabaseUtils.close(trans);
//        }
//    }
//    public int update(TLinkBO temp, Long userId) throws ServiceException {
//        ITransaction trans = null;
//        try {
//            trans = factory.getTransaction();
//            ITlink i = factory.getTlinkDAO();
//            trans.connectionType(DB_RIMS_BB);
//            i.setTransaction(trans);
//            trans.beginTransaction();
//            int iRet = i.update(temp, userId);
//            trans.commit();
//            trans.endTransaction();
//            return iRet;
//        } catch (DAOException de) {
//            logger.error("DAOException : ", de);
//            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
//        } finally {
//            DatabaseUtils.close(trans);
//        }
//    }
//    public int insert(TLinkBO temp, Long userId) throws ServiceException {
//        ITransaction trans = null;
//        try {
//            trans = factory.getTransaction();
//            ITlink i = factory.getTlinkDAO();
//            trans.connectionType(DB_RIMS_BB);
//            i.setTransaction(trans);
//            trans.beginTransaction();
//            int iRet = i.insert(temp, userId);
//            trans.commit();
//            trans.endTransaction();
//            return iRet;
//        } catch (DAOException de) {
//            logger.error("DAOException : ", de);
//            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
//        } finally {
//            DatabaseUtils.close(trans);
//        }
//    }
    public int updateEq1(TEq1BO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateTEq1(temp, userId);
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

    public int updateEq2(TEq2BO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateTEq2(temp, userId);
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

    public int updateEq3(TEq3BO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateTEq3(temp, userId);
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

    public int updateTPort(TPortBO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateTPort(temp, userId);
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

    public int updateTQuang(TModuleQuangBO temp, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateTQuang(temp, userId);
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

    public int deleteEq1(String id, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.deleteEq1(id, userId);
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

    public int deleteEq2(String id, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.deleteEq2(id, userId);
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

    public int deleteEq3(String id, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.deleteEq3(id, userId);
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

    public int deleteTport(String id, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.deleteTport(id, userId);
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

    public int deleteTquang(String id, Long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.deleteTquang(id, userId);
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
