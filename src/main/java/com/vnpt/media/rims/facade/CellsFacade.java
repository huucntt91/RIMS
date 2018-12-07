package com.vnpt.media.rims.facade;

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
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ICells;
import com.vnpt.media.rims.dao.INode;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.Cell2gRegForm;
import com.vnpt.media.rims.formbean.Cell3gRegForm;
import com.vnpt.media.rims.formbean.Cell4gRegForm;
import com.vnpt.media.rims.formbean.ReportCSHT;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CellsFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public CellsFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public CellsFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public String getMaNodeApprove(Long nodeChaId, String btsCode) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            String statusList = Constants.NE_APPROVE_ON + "," + Constants.NE_REG_OFF + "," + Constants.NE_UNAPPROVE_OFF;
            List listNodeCha = iNode.findByNodeChaId(nodeChaId, statusList);

            String maNode = btsCode + "" + (((listNodeCha == null) || listNodeCha.size() < 1) ? 1 : listNodeCha.size() + 1);

            return maNode;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public String getMaNodeReg(Long nodeChaId, String btsCode) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            String statusList = "";//Constants.NE_APPROVE_ON + "," + Constants.NE_REG_OFF + "," + Constants.NE_UNAPPROVE_OFF;
            List listNodeCha = iNode.findByNodeChaId(nodeChaId, statusList);

            String maNode = btsCode + "" + (((listNodeCha == null) || listNodeCha.size() < 1) ? 1 : listNodeCha.size() + 1);

            return maNode;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addCell2g(Cell2gRegForm cell2gRegForm, long userId, String maNode) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addCell2g(cell2gRegForm, userId, maNode);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateAddCell2g(String nodeId, OmcCell2gInfoBO cell2gRegForm, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateAddCell2g(nodeId, cell2gRegForm, userId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public String addCell2gExcel(boolean commit, Cell2GNewExcelModel cell2gExcel, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            String objectId = "4";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();

            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";

            String sRet = i.addCell2gExcel(permission, cell2gExcel, userId);

            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {

                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
            return Convert.convertErrorImportDkCell2G(sRet);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            return "-99";
//            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public String addCell2gExcel(ICells i, Cell2GNewExcelModel cell2gExcel, long userId) throws ServiceException {

        try {
            String objectId = "4";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();

            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";

            String sRet = i.addCell2gExcel(permission, cell2gExcel, userId);
            return Convert.convertErrorImportDkCell2G(sRet);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            return "-99";
        }
    }

    /*
    neu toan bo danh sach import OK thì mới commit, ngược lại thì rollblack
     */
    public List<Cell2GNewExcelModel> importCell2G(List<Cell2GNewExcelModel> list, boolean resultCheckFile, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean commit = true;
            if (list != null && !list.isEmpty()) {
                for (Cell2GNewExcelModel cell : list) {
                    if (resultCheckFile) {
                        String strCheck = addCell2gExcel(i, cell, userId);
                        cell.setCheckDB(strCheck);
                        if (strCheck == null || !strCheck.equalsIgnoreCase("OK")) {
                            commit = false;
                        }
                    } else {
                        cell.setCheckDB(resourceBundle.getString("cell.new.import.validate.file"));
                    }
                }
            }
            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {
                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
        } finally {
            DatabaseUtils.close(trans);
        }
        return list;
    }

    public String addCell3gExcel(boolean commit, Cell3GNewExcelModel cell3gExcel, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            String objectId = "5";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String sRet = i.addCell3gExcel(permission, cell3gExcel, userId);
            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {

                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
            return Convert.convertErrorImportDkCell3G(sRet);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            return "-99";
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public String addCell3gExcel(ICells i, Cell3GNewExcelModel cell3gExcel, long userId) throws ServiceException {
        try {
            String objectId = "5";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String sRet = i.addCell3gExcel(permission, cell3gExcel, userId);

            return Convert.convertErrorImportDkCell3G(sRet);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            return "-99";
        }
    }

    public List<Cell3GNewExcelModel> importCell3G(List<Cell3GNewExcelModel> list, boolean resultCheckFile, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean commit = true;
            if (list != null && !list.isEmpty()) {
                for (Cell3GNewExcelModel cell : list) {
                    if (resultCheckFile) {
                        String strCheck = addCell3gExcel(i, cell, userId);
                        cell.setCheckDB(strCheck);
                        if (strCheck == null || !strCheck.equalsIgnoreCase("OK")) {
                            commit = false;
                        }
                    } else {
                        cell.setCheckDB(resourceBundle.getString("cell.new.import.validate.file"));
                    }
                }
            }
            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {
                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
        } finally {
            DatabaseUtils.close(trans);
        }
        return list;
    }

    public String addCell4gExcel(boolean commit, Cell4GNewExcelModel cell4gExcel, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            String objectId = "6";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String sRet = i.addCell4gExcel(permission, cell4gExcel, userId);

            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {

                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
            return Convert.convertErrorImportDkCell4G(sRet);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            return "-99";
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public String addCell4gExcel(ICells i, Cell4GNewExcelModel cell4gExcel, long userId) {
        try {
            String objectId = "6";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String sRet = i.addCell4gExcel(permission, cell4gExcel, userId);
            return Convert.convertErrorImportDkCell4G(sRet);
        } catch (DAOException de) {
            throw de;
        }
    }

    public List<Cell4GNewExcelModel> importCell4G(List<Cell4GNewExcelModel> list, boolean resultCheckFile, long userId) {
        ITransaction trans = null;
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean commit = true;
            if (list != null && !list.isEmpty()) {
                for (Cell4GNewExcelModel cell : list) {
                    if (resultCheckFile) {
                        String strCheck = addCell4gExcel(i, cell, userId);
                        cell.setCheckDB(strCheck);
                        if (strCheck == null || !strCheck.equalsIgnoreCase("OK")) {
                            commit = false;
                        }
                    } else {
                        cell.setCheckDB(resourceBundle.getString("cell.new.import.validate.file"));
                    }
                }
            }
            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {
                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
        } catch (DAOException de) {
            //de.printStackTrace();
        } finally {
            DatabaseUtils.close(trans);
        }
        return list;
    }

    public int addCell3g(Cell3gRegForm cell3gRegForm, long userId, String maNode) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addCell3g(cell3gRegForm, userId, maNode);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addCell4g(Cell4gRegForm cell4gRegForm, long userId, String maNode) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.addCell4g(cell4gRegForm, userId, maNode);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateAddCell3g(String nodeId, OmcCell3gInfoBO cell3gRegForm, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateAddCell3g(nodeId, cell3gRegForm, userId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateAddCell4g(String nodeId, OmcCell4gInfoBO cell4gRegForm, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateAddCell4g(nodeId, cell4gRegForm, userId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//
//    public List<Cell2GList> findCell(Long nodeId, String statusList, String tinhTpId, String neTypeId) throws ServiceException {
//        ITransaction trans = null;
//        try {
//            trans = factory.getTransaction();
//            ICells i = factory.getCellsDAO();
//            trans.connectionType(DB_ADMIN);
//            i.setTransaction(trans);
//            return i.findCell(nodeId, statusList, tinhTpId, neTypeId);
//        } catch (DAOException de) {
//            logger.error("DAOException : ", de);
//            throw new ServiceException(de);
//        } finally {
//            DatabaseUtils.close(trans);
//        }
//    }

    public int updateCell2g(OmcCell2gInfoBO omcCell2gInfoBO, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            int result = i.updateCell2g(omcCell2gInfoBO, userId);
            trans.commit();
            trans.endTransaction();
            return result;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public String updateCell2gExcel(Cell2GUpdateExcelModel cell2gExcel, long userId) throws ServiceException {
        ITransaction trans = null;
        String result = "";
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            String objectId = "4";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";

            result = i.updateCell2gExcel(permission, cell2gExcel, userId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            result = "-99";
        } finally {
            DatabaseUtils.close(trans);
        }
        return result;
    }

    public String updateCellNetRFExcel(CellUpdateExcelNetModel cell2gExcel, long userId, String type) throws ServiceException {
        ITransaction trans = null;
        String result = "";
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            String objectId = "4";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";

            result = i.updateCellNetRFExcel(permission, cell2gExcel, userId, type);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            result = "-99";
        } finally {
            DatabaseUtils.close(trans);
        }
        return result;
    }

    public String updateCell3gExcel(Cell3GUpdateExcelModel cell3gExcel, long userId) throws ServiceException {
        ITransaction trans = null;
        String result = "";
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            String objectId = "4";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            result = i.updateCell3gExcel(permission, cell3gExcel, userId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            result = "-99";
        } finally {
            DatabaseUtils.close(trans);
        }
        return result;
    }

    public String updateCell4gExcel(Cell4GUpdateExcelModel cell4gExcel, long userId) throws ServiceException {
        ITransaction trans = null;
        String result = "";
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            String objectId = "4";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            result = i.updateCell4gExcel(permission, cell4gExcel, userId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            result = "-99";
//            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
        return result;
    }

    public int updateCell3g(OmcCell3gInfoBO omcCell3gInfoBO, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateCell3g(omcCell3gInfoBO, userId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateCell4g(OmcCell4gInfoBO omcCell4gInfoBO, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.updateCell4g(omcCell4gInfoBO, userId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public String deleteCellExcel(ExcelDeleteNodeBO temp, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            String objectId = "4";
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userId), "D", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String sRet = i.deleteCellExcel(permission, temp, userId);
            trans.commit();
            trans.endTransaction();
            return sRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            return "-99";
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateOmcCell2gInfo(OmcCell2gInfoBO temp, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateOmcCell2gInfo(temp, userId);
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

    public int updateOmcCell3gInfo(OmcCell3gInfoBO temp, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateOmcCell3gInfo(temp, userId);
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

    public int swap2g(OmcCell2gInfoBO temp, long userId, String tenNodeCha) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateOmcCell2gInfo(temp, userId);
            i.swap2g(temp.getId(), temp.getThietBiId(), tenNodeCha, userId);
            //update ten node cha, vendor
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

    public int swap3g(OmcCell3gInfoBO temp, long userId, String tenNodeCha) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();

            int iRet = i.updateOmcCell3gInfo(temp, userId);
            i.swap3g(temp.getId(), temp.getThietBiId(), tenNodeCha, userId);
            //update ten node cha, vendor
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

    public int swap4g(OmcCell4gInfoBO temp, long userId, String tenNodeCha) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateOmcCell4gInfo(temp, userId);
            i.swap4g(temp.getId(), temp.getThietBiId(), tenNodeCha, userId);
            //update ten node cha, vendor
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

    public int updateOmcCell4gInfo(OmcCell4gInfoBO temp, long userId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            int iRet = i.updateOmcCell4gInfo(temp, userId);
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

    public String updateBaoDuongNetExcel(BaoDuongNetExcel baoduong, long userId) throws ServiceException {
        ITransaction trans = null;
        String result = "";
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.updateBaoDuongNetExcel(baoduong, userId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            result = "-99";
        } finally {
            DatabaseUtils.close(trans);
        }
        return result;
    }

    public String updateKiemDinhNetExcel(KiemDinhNetExcel kiemdinh, long userId) throws ServiceException {
        ITransaction trans = null;
        String result = "";
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.updateKiemDinhNetExcel(kiemdinh, userId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            result = "-99";
        } finally {
            DatabaseUtils.close(trans);
        }
        return result;
    }
    
    public  ArrayList<BaoDuongNetExcel> searchBaoDuong(String prs_start_record, String prs_length_page,
            String prs_global_search, String prs_list_column_name, String prs_list_column_search, String prs_column_to_sort,
            String prs_sort_direction, String[] recordsTotal, String[] recordsFiltered) throws Exception {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<BaoDuongNetExcel> arrayList = new ArrayList<>();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            conn = trans.getConnection();
            String sql = "begin ?:=pkg_excel_node.fn_search_bao_duong(?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, prs_start_record);
            cs.setString(3, prs_length_page);
            cs.setString(4, prs_global_search);
            cs.setString(5, prs_list_column_name);
            cs.setString(6, prs_list_column_search);
            cs.setString(7, prs_column_to_sort);
            cs.setString(8, prs_sort_direction);
            cs.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            cs.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            recordsTotal[0] = cs.getString(9);
            recordsFiltered[0] = cs.getString(10);
            while (rs.next()) {
                BaoDuongNetExcel item = new BaoDuongNetExcel();
                item.setCode(rs.getString("ma_node"));
                item.setNeType(rs.getString("ne_type"));
                item.setNgayBaoDuong(rs.getString("ngay_bao_duong"));
                item.setDonvi(rs.getString("don_vi_thuc_hien"));
                item.setNote(rs.getString("ghi_chu"));
                item.setNodeId(rs.getString("node_id"));
                item.setBaoDuongId(rs.getString("baoduong_id"));
                item.setNeTypeId(rs.getString("ne_type_id"));
                item.setArea(rs.getString("khu_vuc"));
                item.setProvinceId(rs.getString("tinhtp_id"));
                item.setProvinceName(rs.getString("ten_tinh_tp"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
        }
    }
    public  List exportBaoDuong(String khuvucIds, String tinhTpIds, String nodeCode, String neType) throws Exception {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List arrayList = new ArrayList<>();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ICells i = factory.getCellsDAO();
            trans.connectionType(DB_ADMIN);
            conn = trans.getConnection();
            String sql = "begin ?:=pkg_excel_node.fn_export_bao_duong(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, khuvucIds);
            cs.setString(3, tinhTpIds);
            cs.setString(4, nodeCode);
            cs.setString(5, neType);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                BaoDuongNetExcel item = new BaoDuongNetExcel();
                item.setCode(rs.getString("ma_node"));
                item.setNeType(rs.getString("ne_type"));
                item.setNgayBaoDuong(rs.getString("ngay_bao_duong"));
                item.setDonvi(rs.getString("don_vi_thuc_hien"));
                item.setNote(rs.getString("ghi_chu"));
                item.setNodeId(rs.getString("node_id"));
                item.setBaoDuongId(rs.getString("baoduong_id"));
                item.setNeTypeId(rs.getString("ne_type_id"));
                item.setArea(rs.getString("khu_vuc"));
                item.setProvinceId(rs.getString("tinhtp_id"));
                item.setProvinceName(rs.getString("ten_tinh_tp"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
        }
    }
}
