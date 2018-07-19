/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.BcTongHopBO;
import com.vnpt.media.rims.bean.BtsReportBO;
import com.vnpt.media.rims.bean.Cell2GReportBO;
import com.vnpt.media.rims.bean.Detail2gBO;
import com.vnpt.media.rims.bean.Detail3gBO;
import com.vnpt.media.rims.bean.FilterReportBO;
import com.vnpt.media.rims.bean.NodeBReportBO;
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
public interface IReport extends IGeneric {

    public List<BtsNodeB> findBtsNodeB(String vendor) throws DAOException;

    public List<Cell2G> findCell2G() throws DAOException;

    public List<Cell3G> findCell3G() throws DAOException;

    public List<FilterReportBO> findFilterReport(int objectType) throws DAOException;

    public List<Cell2GReportBO> cell2GReport(int type, FilterForm filterForm, String startRow, String endRow) throws DAOException;

    public int getTotalCell2GReport(int type, FilterForm filterForm) throws DAOException;

    public List<BtsReportBO> btsReport(int type, FilterForm filterForm, String startRow, String endRow) throws DAOException;

    public int getTotalBtsReport(int type, FilterForm filterForm) throws DAOException;

    public List<NodeBReportBO> nodeBReport(int type, FilterForm filterForm, String startRow, String endRow) throws DAOException;

    public int getTotalNodeBReport(int type, FilterForm filterForm) throws DAOException;

    public int findDataType(String type, String column) throws DAOException;

    public List<BcTongHopBO> findConfigReport() throws DAOException;
    
    public List<Detail2gBO> findDetail2G() throws ServiceException ;
    
    public List<Detail3gBO> findDetail3G() throws ServiceException ;
}
