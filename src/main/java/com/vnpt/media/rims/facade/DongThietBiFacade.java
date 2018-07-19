package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.DongTbiBO;
import com.vnpt.media.rims.bean.TVendorBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IDongThietBi;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DongThietBiFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    private static class AccessFacadeHolder {

        private static final DongThietBiFacade INSTANCE = new DongThietBiFacade();
    }

    public DongThietBiFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public DongThietBiFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public static DongThietBiFacade getInstance() {
        return AccessFacadeHolder.INSTANCE;
    }

//    public List<TNodeBO> findAll(String tnodeid, String code, String name) throws ServiceException {
    public List<DongTbiBO> findAll(Long id, String name, Long vendorid,Integer startRow, Integer endRow) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDongThietBi i = factory.getDongThietBiDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<DongTbiBO> iRet = new ArrayList<>();
            iRet = i.findAll(id, name, vendorid,startRow, endRow);
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
    
    public int getTotal(Long id, String name, Long tVendorId) throws DAOException{
      ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDongThietBi i = factory.getDongThietBiDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = 0;
            iRet = i.getTotal(id, name, tVendorId);
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

    public boolean add(DongTbiBO item, Long userId) throws DAOException{
       ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDongThietBi i = factory.getDongThietBiDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = false;
            iRet = i.add(item, userId);
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

    public boolean update(DongTbiBO item, Long userId) throws DAOException{
         ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDongThietBi i = factory.getDongThietBiDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = false;
            iRet = i.update(item, userId);
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

    public boolean delete(Long id, Long userId) throws DAOException{
          ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDongThietBi i = factory.getDongThietBiDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean iRet = false;
            iRet = i.delete(id, userId);
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
