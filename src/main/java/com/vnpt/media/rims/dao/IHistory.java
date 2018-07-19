/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.formbean.BtsNodeB;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import com.vnpt.media.rims.formbean.FilterForm;
import com.vnpt.media.rims.formbean.ReportConfigForm;
import java.util.List;

/**
 *
 * @author Cyano
 */
public interface IHistory extends IGeneric {

    public List<Cell2GReportBO> cell2GReport(String startTime, String endTime, String action, String userId, String code) throws DAOException;

    public int getTotalCell2GReport(int type, FilterForm filterForm) throws DAOException;

    public List<BtsReportBO> btsReport(String startTime, String endTime, String action, String userId, String neTypeId, String code) throws DAOException;

    public List<CshtHistoryBO> cshtHistory(String startTime, String endTime, String action, String userId, String buildingId) throws DAOException;

    public List<TramDAHistoryBO> tramDAHistory(String startTime, String endTime, String action, String userId, String tramId) throws DAOException;

    public List<TramQHHistoryBO> tramQHHistory(String startTime, String endTime, String action, String userId, String qhId) throws DAOException;
    
    public List<PgwInfoBO> hisPsCore(String startTime, String endTime, String action, String userId, String code) throws DAOException;
    
    public List<CsCoreBO> hisCsCore(String startTime, String endTime, String action, String userId, String code) throws DAOException;
}
