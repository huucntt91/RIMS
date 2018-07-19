/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.*;

import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IHistory;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.dao.IReport;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import com.vnpt.media.rims.formbean.FilterForm;

/**
 *
 * @author Cyano
 */
public class HistoryFacade {

    private Logger logger = LogManager.getLogger(HistoryFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public HistoryFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public HistoryFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<Cell2GReportBO> cell2GReport(String startTime, String endTime, String action, String userId,String code) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IHistory iReport = factory.getHistoryDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.cell2GReport(startTime, endTime, action, userId,code);
//            return null;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    

    public List<BtsReportBO> btsHistory(String startTime, String endTime, String action, String userId, String neTypeId, String code) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IHistory iReport = factory.getHistoryDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.btsReport(startTime, endTime, action, userId,neTypeId,code);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public List<CshtHistoryBO> cshtHistory(String startTime, String endTime, String action, String userId, String code) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IHistory iReport = factory.getHistoryDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.cshtHistory(startTime, endTime, action, userId,code);

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public List<TramDAHistoryBO> tramDAHistory(String startTime, String endTime, String action, String userId, String code) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IHistory iReport = factory.getHistoryDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.tramDAHistory(startTime, endTime, action, userId,code);

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public List<TramQHHistoryBO> tramQHHistory(String startTime, String endTime, String action, String userId, String code) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IHistory iReport = factory.getHistoryDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.tramQHHistory(startTime, endTime, action, userId,code);

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<PgwInfoBO> psHistory(String startTime, String endTime, String action, String userId, String code) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IHistory iReport = factory.getHistoryDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.hisPsCore(startTime, endTime, action, userId,code);

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public List<CsCoreBO> csHistory(String startTime, String endTime, String action, String userId, String code) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IHistory iReport = factory.getHistoryDAO();
            trans.connectionType(DB_ADMIN);
            iReport.setTransaction(trans);
            return iReport.hisCsCore(startTime, endTime, action, userId,code);

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
   
}
