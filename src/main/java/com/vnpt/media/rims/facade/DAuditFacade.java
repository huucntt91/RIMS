/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.AuditSummaryBO;
import com.vnpt.media.rims.bean.BTSInfoBO;
import com.vnpt.media.rims.bean.ComBtsInfoBO;
import com.vnpt.media.rims.bean.ComCell2gInfoBO;
import com.vnpt.media.rims.bean.ComCell3gInfoBO;
import com.vnpt.media.rims.bean.ComCell4gInfoBO;
import com.vnpt.media.rims.bean.ComEnodeBInfoBO;
import com.vnpt.media.rims.bean.ComNodeBInfoBO;
import com.vnpt.media.rims.bean.DMBangTanBO;
import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.OmcCell2gInfoBO;
import com.vnpt.media.rims.bean.OmcCell3gInfoBO;
import com.vnpt.media.rims.bean.OmcCell4gInfoBO;
import com.vnpt.media.rims.bean.ThietBiBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IDAudit;
import com.vnpt.media.rims.dao.INode;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author VNP
 */
public class DAuditFacade {

    private Logger logger = LogManager.getLogger(ManagerAdminFacade.class);
    private DAOFactory factory = null;

    public DAuditFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public DAuditFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<AuditSummaryBO> getSummary() throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            trans.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(trans);
            return iAuditDao.getSummary();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ComCell2gInfoBO> findAllComCell2gInfo(Long id, String type, String startRow, String endRow, String tenTrenHeThong, String  tinh) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            trans.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(trans);
            return iAuditDao.findAllComCell2gInfo(id, type, 0, startRow, endRow, tenTrenHeThong,tinh);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ComCell3gInfoBO> findAllComCell3gInfo(Long id, String type, String startRow, String endRow, String tenTrenHeThong, String tinh) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            trans.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(trans);
            return iAuditDao.findAllComCell3gInfo(id, type, 0, startRow, endRow, tenTrenHeThong,tinh);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ComCell4gInfoBO> findAllComCell4gInfo(Long id, String type, String startRow, String endRow, String tenTrenHeThong,String tinh) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            trans.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(trans);
            return iAuditDao.findAllComCell4gInfo(id, type, 0, startRow, endRow, tenTrenHeThong,tinh);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ComBtsInfoBO> findAllComBtsInfo(Long id, String type, String startRow, String endRow, String tenTrenHeThong, String tinh) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            trans.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(trans);
            return iAuditDao.findAllComBtsInfo(id, type, 0, startRow, endRow, tenTrenHeThong, tinh);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ComNodeBInfoBO> findAllComNodeBInfo(Long id, String type, String startRow, String endRow, String tenTrenHeThong, String tinh) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            trans.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(trans);
            return iAuditDao.findAllComNodeBInfo(id, type, 0, startRow, endRow, tenTrenHeThong,tinh);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ComEnodeBInfoBO> findAllComEnodeBInfo(Long id, String type, String startRow, String endRow, String tenTrenHeThong,String tinh) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            trans.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(trans);
            return iAuditDao.findAllComEnodeBInfo(id, type, 0, startRow, endRow, tenTrenHeThong,tinh);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalComInfo(Long id, String type, String name, String tenTrenHeThong,String tinh) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            trans.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(trans);
            return iAuditDao.getTotalComInfo(id, type, 0, name, tenTrenHeThong, tinh);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void offNode(String name, String idAudit, ApproveForm approveForm, Long userUpdate) throws ServiceException {

        if (name.equalsIgnoreCase("cell_info")) {
//            offCell(idAudit, approveForm, userUpdate);
        } else if (name.equalsIgnoreCase("bts_info")) {

        } else if (name.equalsIgnoreCase("nodeb_info")) {

        } else if (name.equalsIgnoreCase("enodeb_info")) {

        }
    }

    public void updateStatus(String name, String idAudit, int status) throws ServiceException {
        ITransaction transAudit = null;
        try {
            transAudit = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            transAudit.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(transAudit);

            if (name.equalsIgnoreCase("cell2g_info")) {
                iAuditDao.updateStatusCell2gComInfo(idAudit, status);
            }
            if (name.equalsIgnoreCase("cell3g_info")) {
                iAuditDao.updateStatusCell3gComInfo(idAudit, status);
            }
            if (name.equalsIgnoreCase("cell4g_info")) {
                iAuditDao.updateStatusCell4gComInfo(idAudit, status);
            } else if (name.equalsIgnoreCase("bts_info")) {
                iAuditDao.updateStatusBtsComInfo(idAudit, status);
            } else if (name.equalsIgnoreCase("nodeb_info")) {
                iAuditDao.updateStatusNodebComInfo(idAudit, status);
            } else if (name.equalsIgnoreCase("enodeb_info")) {
                iAuditDao.updateStatusEnodeBComInfo(idAudit, status);
            }
        } catch (DAOException de) {
            logger.error("DAOException : ", de);

            DatabaseUtils.rollback(transAudit);
            String message = StringUtils.captureStackTrace(de);
        } finally {

            DatabaseUtils.close(transAudit);
        }
    }

//    public void offCell(String name,String idAudit, ApproveForm approveForm, Long userUpdate) throws ServiceException {
//        ITransaction transAudit = null;
//        ITransaction trans = null;
//        try {
//            transAudit = factory.getTransaction();
//            IDAudit iAuditDao = factory.getDAuditDAO();
//            transAudit.connectionType(Constants.DB_AUDIT);
//            iAuditDao.setTransaction(transAudit);
//            iAuditDao.updateStatusCellComInfo(idAudit, 1);
//
//            trans = factory.getTransaction();
//            INode iNode = factory.getNodeDAO();
//            trans.connectionType(Constants.DB_CB);
//            iNode.setTransaction(trans);
//            trans.beginTransaction();
//
//            //tim so cell cua bts. 
////            List<CellNewForm> temp= iNode.findCell(approveForm.getNodeId(),null,null);
//            //danh sach cac cell cung node cha
//            String statusList = Constants.NE_APPROVE_ON + "," + Constants.NE_REG_OFF + "," + Constants.NE_UNAPPROVE_OFF;
//
//            List<NodeBO> listTemp = iNode.findByNodeChaId(approveForm.getNodeChaId(), statusList);
//            String maNode = "";
//            if (approveForm.getStatus().equals(Constants.NE_REG_ON)) {
//                if (listTemp != null && listTemp.size() > 8) {
//                    List<NodeBO> temp2 = iNode.findSpecialCell(approveForm.getNodeChaId());
//                    maNode = temp2.size() + 90001 + "";
//                    maNode = approveForm.getParentCode().substring(0, 5) + maNode;
//                } else {
//                    maNode = approveForm.getParentCode().substring(0, 9) + Convert.getCode(listTemp, 0);
//                }
//            }
////            maNode=temp.matinh+congnghe+maNode;
////            so cell <9. tim so cuoi ko ton tai cho vao
////                    so cell>=9. tim so 
////            String maNode//=iNode.getMaCell(approveForm.getNodeId());
//            iNode.approveCell(approveForm, maNode, userUpdate);
//            trans.commit();
//            trans.endTransaction();
//        } catch (DAOException de) {
//            logger.error("DAOException : ", de);
//            DatabaseUtils.rollback(trans);
//            DatabaseUtils.rollback(transAudit);
//            String message = StringUtils.captureStackTrace(de);
//        } finally {
//            DatabaseUtils.close(trans);
//            DatabaseUtils.close(transAudit);
//        }
//    }
    public void swap(String rNodeId, String auditId, String name, long userId) throws ServiceException {
        ITransaction transAudit = null;
        try {
            transAudit = factory.getTransaction();
            IDAudit iAuditDao = factory.getDAuditDAO();
            transAudit.connectionType(Constants.DB_AUDIT);
            iAuditDao.setTransaction(transAudit);
            transAudit.beginTransaction();
            CellsFacade cellsFacade = new CellsFacade();
            if (name.equals("cell2g_info")) {
                List<ComCell2gInfoBO> temp = findAllComCell2gInfo(Long.valueOf(auditId), "-1", "0", "100", "","");
                if (temp != null && temp.size() > 0) {
                    OmcCell2gInfoBO omcCell2G = new OmcCell2gInfoBO();
                    omcCell2G.setCi(temp.get(0).getiCi());
                    omcCell2G.setBangTanId(null);
                    omcCell2G.setTch(temp.get(0).getiTch());
                    omcCell2G.setBcch(temp.get(0).getiBcch());
                    omcCell2G.setTrxConfig(temp.get(0).getiTrxConfig());
                    omcCell2G.setId(Long.valueOf(rNodeId));
                    omcCell2G.setNodeId(Long.valueOf(rNodeId));
                    omcCell2G.setTenTrenHeThong(temp.get(0).getiCellName());
                    omcCell2G.setLac(temp.get(0).getiLac());

//                    cellsFacade.updateOmcCell2gInfo(omcCell2G, userId);
                    CategoriesFacade cate = new CategoriesFacade();
                    List<ThietBiBO> listThietBi = cate.findThietBiByName(temp.get(0).getiVendor());
                    
                    
                    if (listThietBi != null && listThietBi.size() > 0) {
                        
                        omcCell2G.setThietBiId(Long.valueOf(listThietBi.get(0).getThietBiId()));
                    } else {
                        omcCell2G.setThietBiId(null);
                    }
                    
                    cellsFacade.swap2g(omcCell2G, userId, temp.get(0).getiBtsNodeBName());
                    updateStatus("cell2g_info", auditId, 1);
                }
            } else if (name.equals("cell3g_info")) {
                List<ComCell3gInfoBO> temp = findAllComCell3gInfo(Long.valueOf(auditId), "-1", "0", "100", "","");
                if (temp != null && temp.size() > 0) {
                    OmcCell3gInfoBO omcCell3G = new OmcCell3gInfoBO();
                    omcCell3G.setCi(temp.get(0).getiCi());
                    ArrayList<DMBangTanBO> listbangtan = BangTanFacade.fc_find_all_by_name(temp.get(0).getiTenBangTan());

                    if (listbangtan != null && listbangtan.size() > 0) {
                        omcCell3G.setBangTanId(Long.valueOf(listbangtan.get(0).getBang_tan_id()));
                    } else {
                        omcCell3G.setBangTanId(null);
                    }

                    omcCell3G.setId(Long.valueOf(rNodeId));
                    omcCell3G.setNodeId(Long.valueOf(rNodeId));
                    omcCell3G.setTenTrenHeThong(temp.get(0).getiCellName());
                    omcCell3G.setDlPsc(temp.get(0).getiDlPsc());
                    omcCell3G.setCpichPower(temp.get(0).getiCpichPower());
                    omcCell3G.setTotalPower(temp.get(0).getiTotalPower());
                    omcCell3G.setLac(temp.get(0).getiLac());
                    omcCell3G.setFrequency(temp.get(0).getiFrequencyBand());
                    CategoriesFacade cate = new CategoriesFacade();
                    List<ThietBiBO> listThietBi = cate.findThietBiByName(temp.get(0).getiVendor());

                    if (listThietBi != null && listThietBi.size() > 0) {
                        omcCell3G.setThietBiId(Long.valueOf(listThietBi.get(0).getThietBiId()));
                    } else {
                        omcCell3G.setThietBiId(null);
                    }

                    cellsFacade.swap3g(omcCell3G, userId, temp.get(0).getiBtsNodeBName());
                    updateStatus("cell3g_info", auditId, 1);
                }
            } else if (name.equals("cell4g_info")) {
                List<ComCell4gInfoBO> temp = findAllComCell4gInfo(Long.valueOf(auditId), "-1", "0", "100", "","");
                if (temp != null && temp.size() > 0) {
                    OmcCell4gInfoBO omcCell4G = new OmcCell4gInfoBO();
                    omcCell4G.setCi(temp.get(0).getiCi());
                    omcCell4G.setPci(temp.get(0).getiPci());
                    omcCell4G.setTac(temp.get(0).getiTac());
                    omcCell4G.setBangTanId(null);
                    omcCell4G.setLcrid(temp.get(0).getiLcrId());
                    omcCell4G.setId(Long.valueOf(rNodeId));
                    omcCell4G.setNodeId(Long.valueOf(rNodeId));
                    omcCell4G.setTenTrenHeThong(temp.get(0).getiCellName());
                    omcCell4G.setLac(temp.get(0).getiLac());
//                    cellsFacade.updateOmcCell4gInfo(omcCell4G, userId);
                    CategoriesFacade cate = new CategoriesFacade();
                    List<ThietBiBO> listThietBi = cate.findThietBiByName(temp.get(0).getiVendor());

                    if (listThietBi != null && listThietBi.size() > 0) {
                        omcCell4G.setThietBiId(Long.valueOf(listThietBi.get(0).getThietBiId()));
                    } else {
                        omcCell4G.setThietBiId(null);
                    }
                    cellsFacade.swap4g(omcCell4G, userId, temp.get(0).getiBtsNodeBName());
                    updateStatus("cell4g_info", auditId, 1);
                }
            } else if (name.equals("bts_info")) {
                List<ComBtsInfoBO> temp = findAllComBtsInfo(Long.valueOf(auditId), "-1", "0", "100", "","");
                
                if (temp != null && temp.size() > 0) {
//                    OmcCell3gInfoBO omcCell3G = new OmcCell3gInfoBO();
                    NodesFacade nodesFacade = new NodesFacade();
                    BTSInfoBO bo = new BTSInfoBO();
                    bo.setId(Long.valueOf(rNodeId));
                    bo.setTenTrenHeThong(temp.get(0).getiBtsName());
                    bo.setTenBSCRNC(temp.get(0).getiBscRncName());
                    bo.setTenBSCRNCQL(temp.get(0).getiBscRncMngName());
                    bo.setFilterUser(temp.get(0).getiFilterUser());
//                    bo.setBangTanId(temp.get(0).getrFrequencyBand());                                       
//                    nodesFacade.updateOmcBtsInfo(bo, userId);
                    CategoriesFacade cate = new CategoriesFacade();
                    List<ThietBiBO> listThietBi = cate.findThietBiByName(temp.get(0).getiVendor());
                    Long vendor = null;
                    if (listThietBi != null && listThietBi.size() > 0) {
                        vendor = Long.valueOf(listThietBi.get(0).getThietBiId());
                    }
                    nodesFacade.swapBts(bo, Long.valueOf(rNodeId), userId, temp.get(0).getiBscRncName(), vendor);
                    updateStatus("bts_info", auditId, 1);
                }
            } else if (name.equals("nodeb_info")) {
                List<ComNodeBInfoBO> temp = findAllComNodeBInfo(Long.valueOf(auditId), "-1", "0", "100", "","");
                if (temp != null && temp.size() > 0) {
                    NodesFacade nodesFacade = new NodesFacade();

//                    nodesFacade.updateOmcNodeBInfo(temp.get(0).getrNodebId(), temp.get(0).getiNodebName(),
//                            temp.get(0).getiBscRncName(), temp.get(0).getiBscRncMngName(),
//                            temp.get(0).getiDcHsdpa42M(), temp.get(0).getiFilterUser(), null, userId);
                    CategoriesFacade cate = new CategoriesFacade();
                    List<ThietBiBO> listThietBi = cate.findThietBiByName(temp.get(0).getiVendor());
                    Long vendor = null;
                    if (listThietBi != null && listThietBi.size() > 0) {
                        vendor = Long.valueOf(listThietBi.get(0).getThietBiId());
                    }
                    nodesFacade.swapNodeB(Long.valueOf(rNodeId), temp.get(0).getiNodebName(),
                            temp.get(0).getiBscRncName(), temp.get(0).getiBscRncMngName(),
                            temp.get(0).getiDcHsdpa42M(), temp.get(0).getiFilterUser(), null, userId,
                            vendor);
                    updateStatus("nodeb_info", auditId, 1);
                }
            } else if (name.equals("enodeb_info")) {
                List<ComEnodeBInfoBO> temp = findAllComEnodeBInfo(Long.valueOf(auditId), "-1", "0", "100", "","");
                if (temp != null && temp.size() > 0) {
                    NodesFacade nodesFacade = new NodesFacade();

//                    nodesFacade.updateOmcEnodeBInfo(temp.get(0).getReNodebId(),
//                            temp.get(0).getiEnodebName(), temp.get(0).getiBscRncName(), temp.get(0).getiBscRncMngName(),
//                            temp.get(0).getiMscMss(), temp.get(0).getiSgsn(), temp.get(0).getiDcHsdpa42M(),
//                            temp.get(0).getiFilterUser(), null, userId);
                    CategoriesFacade cate = new CategoriesFacade();
                    List<ThietBiBO> listThietBi = cate.findThietBiByName(temp.get(0).getiVendor());
                    Long vendor = null;
                    if (listThietBi != null && listThietBi.size() > 0) {
                        vendor = Long.valueOf(listThietBi.get(0).getThietBiId());
                    }
                    nodesFacade.swapENodeB(Long.valueOf(rNodeId),
                            temp.get(0).getiEnodebName(), temp.get(0).getiBscRncName(), temp.get(0).getiBscRncMngName(),
                            temp.get(0).getiMscMss(), temp.get(0).getiSgsn(), temp.get(0).getiDcHsdpa42M(),
                            temp.get(0).getiFilterUser(), null, userId, vendor);
                    updateStatus("enodeb_info", auditId, 1);
                }
            }
            transAudit.commit();
            transAudit.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);

            DatabaseUtils.rollback(transAudit);
            String message = StringUtils.captureStackTrace(de);
        } finally {

            DatabaseUtils.close(transAudit);
        }
    }

}
