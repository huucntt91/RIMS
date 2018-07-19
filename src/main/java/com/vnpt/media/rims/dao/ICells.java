package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.BaoDuongNetExcel;
import com.vnpt.media.rims.bean.Cell2GNewExcelModel;
import com.vnpt.media.rims.bean.Cell2GUpdateExcelModel;
import com.vnpt.media.rims.bean.Cell3GNewExcelModel;
import com.vnpt.media.rims.bean.Cell3GUpdateExcelModel;
import com.vnpt.media.rims.bean.Cell4GNewExcelModel;
import com.vnpt.media.rims.bean.Cell4GUpdateExcelModel;
import com.vnpt.media.rims.bean.CellUpdateExcelNetModel;
import com.vnpt.media.rims.bean.ExcelDeleteNodeBO;
import com.vnpt.media.rims.bean.KiemDinhNetExcel;
import com.vnpt.media.rims.bean.OmcCell2gInfoBO;
import com.vnpt.media.rims.bean.OmcCell3gInfoBO;
import com.vnpt.media.rims.bean.OmcCell4gInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.Cell2gRegForm;
import com.vnpt.media.rims.formbean.Cell3gRegForm;
import com.vnpt.media.rims.formbean.Cell4gRegForm;
//import com.vnpt.media.rims.formbean.Cell2GList;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ICells extends IGeneric {

    public int addCell2g(Cell2gRegForm cell2gRegForm, long userId, String maNode) throws DAOException;

    public int updateAddCell2g(String nodeId, OmcCell2gInfoBO cell2gRegForm, long userId) throws DAOException;

    public String addCell2gExcel(String permission, Cell2GNewExcelModel cell2gExcel, long userId) throws DAOException;

    public String addCell3gExcel(String permission, Cell3GNewExcelModel cell3gExcel, long userId) throws DAOException;

    public String addCell4gExcel(String permission, Cell4GNewExcelModel cell4gExcel, long userId) throws DAOException;

    public int addCell3g(Cell3gRegForm cell3gRegForm, long userId, String maNode) throws DAOException;

    public int addCell4g(Cell4gRegForm cell4gRegForm, long userId, String maNode) throws DAOException;

    public int updateAddCell3g(String nodeId, OmcCell3gInfoBO cell3gRegForm, long userId) throws DAOException;

    public int updateAddCell4g(String nodeId, OmcCell4gInfoBO cell4gRegForm, long userId) throws DAOException;

//    public List<Cell2GList> findCell(Long nodeId, String statusList, String tinhTpId, String neTypeId) throws ServiceException;
    public int updateCell2g(OmcCell2gInfoBO omcCell4gInfoBO, long userId) throws DAOException;

    public String updateCellNetRFExcel(String permission, CellUpdateExcelNetModel cell, long userId, String type) throws ServiceException;
    
    public String updateCell2gExcel(String permission, Cell2GUpdateExcelModel cell2gExcel, long userId) throws DAOException;

    public String updateCell3gExcel(String permission, Cell3GUpdateExcelModel cell3gExcel, long userId) throws DAOException;

    public String updateCell4gExcel(String permission, Cell4GUpdateExcelModel cell4gExcel, long userId) throws DAOException;

    public int updateCell3g(OmcCell3gInfoBO omcCell4gInfoBO, long userId) throws DAOException;

    public int updateCell4g(OmcCell4gInfoBO omcCell4gInfoBO, long userId) throws DAOException;

    public String deleteCellExcel(String permission, ExcelDeleteNodeBO temp, long userId) throws DAOException;

    public int updateOmcCell2gInfo(OmcCell2gInfoBO temp, long userId) throws DAOException;

    public int updateOmcCell3gInfo(OmcCell3gInfoBO temp, long userId) throws DAOException;

    public int updateOmcCell4gInfo(OmcCell4gInfoBO temp, long userId) throws DAOException;

    public int swap2g(Long id, Long thietBiId, String tenNodeCha,long userId) throws DAOException;

    public int swap3g(Long id, Long thietBiId, String tenNodeCha,long userId) throws DAOException;

    public int swap4g(Long id, Long thietBiId, String tenNodeCha,long userId) throws DAOException;

    public String updateBaoDuongNetExcel(BaoDuongNetExcel baoduong, long userId) throws ServiceException;
    
    public String updateKiemDinhNetExcel(KiemDinhNetExcel kiemdinh, long userId) throws ServiceException;
}
