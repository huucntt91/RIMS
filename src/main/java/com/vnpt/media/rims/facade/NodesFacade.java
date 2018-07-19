package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.DAOFactory;
//import com.vnpt.media.rims.dao.ICps;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.formbean.ApproveAllForm;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.NeLinkForm;
import java.util.Locale;
import java.util.ResourceBundle;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
public class NodesFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public NodesFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public NodesFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<NodeBO> findAllNodeBO(String startRow, String endRow, String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(startRow, endRow, name, khuvucId, tinhId, quanId, phuongId, neTypeId, venderId, statusList);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<?> findDetail(String id, String neTypeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findDetail(id, neTypeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<?> findAllDetail(String nodeId, String startRow, String endRow, String code,
            String khuvucId, String tinhTpId, String quanHuyenId, String phuongXaId,
            String neTypeId, String thietBiId, String status) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllDetail(nodeId, startRow, endRow, code, khuvucId, tinhTpId, quanHuyenId, phuongXaId,
                    neTypeId, thietBiId, status);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalNode(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalAll(name, khuvucId, tinhId, quanId, phuongId, neTypeId, venderId, statusList);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalDetail(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalDetail(name, khuvucId, tinhId, quanId, phuongId, neTypeId, venderId, statusList);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NeLinkForm> findNodeLink(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findNodeLink(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NeLinkForm> findNode1Link(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findNode1Link(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NeLinkForm> findNode2Link(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findNode2Link(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int addNeLink(NeLinkForm model) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            result = iNode.addNeLink(model);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int removeNeLink(String id) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            result = iNode.removeNeLink(id);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public void updateCell(CellNewForm cellNewForm, RedirectAttributes attr) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            NodeBO nodeBO = new NodeBO();
//            nodeBO.setId(cellNewForm.getCellInfo().getId());
//            nodeBO.setBuildingId(cellNewForm.getBuildingId());
            nodeBO.setId(cellNewForm.getCellInfo().getId());
            nodeBO.setBuildingId(cellNewForm.getBuildingId());
            nodeBO.setCode(cellNewForm.getCellInfo().getCode());
            nodeBO.setThietBiId(cellNewForm.getThietBi().getThietBiId());

            nodeBO.setBuildingId(cellNewForm.getBuildingId());
            nodeBO.setNeTypeId(cellNewForm.getCellInfo().getNeTypeId());
            nodeBO.setLoaiTramId(cellNewForm.getLoaiTram().getLoaiTramId());
            nodeBO.setTrangThaiHDId(cellNewForm.getCellInfo().getTrangThaiHDId());
            nodeBO.setTrangThaiQLId(cellNewForm.getCellInfo().getTrangThaiQLId());
            nodeBO.setNodeChaId(cellNewForm.getCellInfo().getNodeChaId());
            nodeBO.setTenNgQLy(cellNewForm.getCellInfo().getTenNgQLy());
            nodeBO.setSDTQLy(cellNewForm.getCellInfo().getSDTQLy());
            nodeBO.setStatus(cellNewForm.getCellInfo().getStatus());
            nodeBO.setUserUpdate(cellNewForm.getCellInfo().getUserUpdate());

            iNode.updateNode(nodeBO);

            CellInfoBO cellInfoBO = new CellInfoBO();
            cellInfoBO.setId(cellNewForm.getCellInfo().getId());
            cellInfoBO.setLat(cellNewForm.getCellInfo().getLat());
            cellInfoBO.setLon(cellNewForm.getCellInfo().getLon());
            cellInfoBO.setAzimuth(cellNewForm.getCellInfo().getAzimuth());
            cellInfoBO.setMechanitalTilt(cellNewForm.getCellInfo().getMechanitalTilt());
            cellInfoBO.setTotalTilt(cellNewForm.getCellInfo().getTotalTilt());
            cellInfoBO.setAntennaHigh(cellNewForm.getCellInfo().getAntennaHigh());
            cellInfoBO.setAntennaType(cellNewForm.getCellInfo().getAntennaType());
            cellInfoBO.setAntennaGain(cellNewForm.getCellInfo().getAntennaGain());
            cellInfoBO.setHoanCanhRaDoi(cellNewForm.getCellInfo().getHoanCanhRaDoi());
            cellInfoBO.setNgayHoatDong(cellNewForm.getCellInfo().getNgayHoatDong());
            cellInfoBO.setNgayDangKy(cellNewForm.getCellInfo().getNgayDangKy());
            cellInfoBO.setNgayCapPhep(cellNewForm.getCellInfo().getNgayCapPhep());
            cellInfoBO.setNgayKiemDuyet(cellNewForm.getCellInfo().getNgayKiemDuyet());
            cellInfoBO.setBlackListFlag(cellNewForm.getCellInfo().getBlackListFlag());
            cellInfoBO.setLyDo(cellNewForm.getCellInfo().getLyDo());
            cellInfoBO.setNoOfCarrier(cellNewForm.getCellInfo().getNoOfCarrier());
            iNode.updateCellInfo(cellInfoBO);

            OmcCellInfoBO omcCellInfoBO = new OmcCellInfoBO();

            omcCellInfoBO.setCi(cellNewForm.getOmcCellInfo().getCi());
            omcCellInfoBO.setCode(cellNewForm.getOmcCellInfo().getCode());
            omcCellInfoBO.setDcHsdpa42M(cellNewForm.getOmcCellInfo().getDcHsdpa42M());
            omcCellInfoBO.setBangTanId(cellNewForm.getOmcCellInfo().getBangTanId());
            omcCellInfoBO.setLac(cellNewForm.getOmcCellInfo().getLac());
            omcCellInfoBO.setOmcCellId(cellNewForm.getCellInfo().getId());
            omcCellInfoBO.setTenBscRnc(cellNewForm.getOmcCellInfo().getTenBscRnc());
            omcCellInfoBO.setTenTrenHeThong(cellNewForm.getOmcCellInfo().getTenTrenHeThong());
            iNode.updateOmcCellInfo(omcCellInfoBO);
            trans.commit();
            trans.endTransaction();
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_UPDATE_ERROR));

            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateTram(BTSInfoBO model) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            NodeBO nodeBO = new NodeBO();
            nodeBO.setId(model.getId());
            nodeBO.setBuildingId(model.getBuildingId());
            nodeBO.setCode(model.getCode());
            nodeBO.setDonViId(model.getDonViId());
            nodeBO.setThietBiId(model.getThietBiId());
            nodeBO.setNeTypeId(model.getNeTypeId());
            nodeBO.setLoaiTramId(model.getLoaiTramId());
            nodeBO.setTrangThaiHDId(model.getTrangThaiHDId());
            nodeBO.setTrangThaiQLId(model.getTrangThaiQLId());
            nodeBO.setNodeChaId(model.getNodeChaId());
            nodeBO.setTenNgQLy(model.getTenNgQLy());
            nodeBO.setSDTQLy(model.getSDTQLy());
            nodeBO.setStatus(model.getStatus());
            nodeBO.setUserUpdate(model.getUserUpdate());

            iNode.updateNode(nodeBO);
            iNode.updateTramInfo(model);
            trans.commit();
            trans.endTransaction();
            return 1;

        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            return 0;

        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int approveTram(ApproveForm approveForm, Long userUpdate) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();

            NodesFacade nodesFacade = new NodesFacade();
            String status = "";
            String codeVNPT = "";
            if (approveForm.getParentCode() != null && !approveForm.getParentCode().equals("")) {
                if (approveForm.getStatus().equals(String.valueOf(Constants.NE_APPROVE_OFF))) {
                    codeVNPT = "OFF_" + approveForm.getParentCode();
                } else if (approveForm.getStatus().equals(String.valueOf(Constants.NE_UNAPPROVE_OFF)) || approveForm.getStatus().equals(String.valueOf(Constants.NE_UNAPPROVE_ON))) {
                    codeVNPT = "";
                } else {
                    //String codeOld = approveForm.getParentCode().replace("DK_", "");
                    String codeTechnology = approveForm.getType(); //codeOld.substring(0, 2);
                    //codeOld = codeOld.replace(codeTechnology, "");
                    String codeTinh = approveForm.getCodeProvince(); //codeOld.substring(0, 3);

                    codeVNPT = nodesFacade.getCodeVNPT(codeTinh, codeTechnology, status);
                    if (codeVNPT.equals("")) {
                        // loi sinh ma dung chuogn trinh 
                        return 0;
                    }
                }
            }

            iNode.approveCell(approveForm, codeVNPT, userUpdate);

            trans.commit();
            trans.endTransaction();
            return 1;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            String message = StringUtils.captureStackTrace(de);
            return 0;
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void approveCell(ApproveForm approveForm, Long userUpdate) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();

            //tim so cell cua bts. 
//            List<CellNewForm> temp= iNode.findCell(approveForm.getNodeId(),null,null);
            //danh sach cac cell cung node cha
            String statusList = Constants.NE_APPROVE_ON + "," + Constants.NE_REG_OFF + "," + Constants.NE_UNAPPROVE_OFF;

            List<NodeBO> listTemp = iNode.findByNodeChaId(approveForm.getNodeChaId(), statusList);
            String maNode = "";
            if (approveForm.getStatus().equals(Constants.NE_REG_ON + "")
                    || approveForm.getStatus().equals(Constants.NE_APPROVE_ON + "")) {
                if (listTemp != null && listTemp.size() > 8) {
                    maNode = String.valueOf(iNode.findSpecialCell(approveForm.getNodeChaId()));
                    maNode = approveForm.getParentCode().substring(0, 5) + maNode;
                } else {

                    maNode = approveForm.getParentCode().substring(0, 9) + Convert.getCode(listTemp, 0);
                }
            } else if (approveForm.getStatus().equals(Constants.NE_APPROVE_OFF + "")) {
                List<NodeBO> listTemp1 = iNode.findByNodeChaId(approveForm.getNodeChaId(), "");
                maNode = "OFF_" + approveForm.getParentCode().substring(0, 9) + ((listTemp1 == null || listTemp1.size() == 0) ? 0 : listTemp1.size() + 1);

            }
//            maNode=temp.matinh+congnghe+maNode;
//            so cell <9. tim so cuoi ko ton tai cho vao
//                    so cell>=9. tim so 
//            String maNode//=iNode.getMaCell(approveForm.getNodeId());

            iNode.approveCell(approveForm, maNode, userUpdate);
            trans.commit();

            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            String message = StringUtils.captureStackTrace(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void addCell(CellNewForm cellNewForm, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            NodeBO nodeBO = new NodeBO();
//            nodeBO.setId(cellNewForm.getCellInfo().getId());
//            nodeBO.setBuildingId(cellNewForm.getBuildingId());

            if (cellNewForm.getCellInfo().getNodeChaId() == null) {
                String noWhitespaceAllowed = messageSource.getMessage("cell.new.parentId.notempty", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
//                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return;
            }

            if (cellNewForm.getThietBi().getThietBiId() == -1) {
                String noWhitespaceAllowed = messageSource.getMessage("cell.new.thietbiId.notempty", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
//                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return;
            }

            if (cellNewForm.getLoaiTram().getLoaiTramId() == -1) {
                String noWhitespaceAllowed = messageSource.getMessage("cell.new.netypeid.notempty", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
//                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return;
            }
            String statusList = Constants.NE_APPROVE_ON + "," + Constants.NE_REG_OFF + "," + Constants.NE_UNAPPROVE_OFF;
            List listNodeCha = iNode.findByNodeChaId(cellNewForm.getCellInfo().getNodeChaId(), statusList);

            nodeBO.setId(cellNewForm.getCellInfo().getId());
            nodeBO.setBuildingId(cellNewForm.getBuildingId());
            nodeBO.setCode("DK_" + cellNewForm.getBtsCode() + "" + (((listNodeCha == null) || listNodeCha.size() < 1) ? 1 : listNodeCha.size() + 1));
            nodeBO.setThietBiId(cellNewForm.getThietBi() == null ? null : cellNewForm.getThietBi().getThietBiId());
            nodeBO.setBuildingId(cellNewForm.getBuildingId());
            nodeBO.setNeTypeId(cellNewForm.getLoaiNE().getId());
            nodeBO.setLoaiTramId(cellNewForm.getLoaiTram() == null ? null : cellNewForm.getLoaiTram().getLoaiTramId());

            nodeBO.setNodeChaId(cellNewForm.getCellInfo().getNodeChaId());
            nodeBO.setTenNgQLy(cellNewForm.getCellInfo().getTenNgQLy());
            nodeBO.setSDTQLy(cellNewForm.getCellInfo().getSDTQLy());
            nodeBO.setStatus(Constants.NE_REG_ON);
            nodeBO.setTrangThaiHDId(2l);
            nodeBO.setTrangThaiQLId(1l);
            nodeBO.setUserInsert(cellNewForm.getCellInfo().getUserInsert());
            Long nodeId = iNode.addNode(nodeBO);
            if (nodeId < 0l) {
                throw new DAOException();
            }
            CellInfoBO cellInfoBO = new CellInfoBO();
            cellInfoBO.setId(nodeId);
            cellInfoBO.setLat(cellNewForm.getCellInfo().getLat());
            cellInfoBO.setLon(cellNewForm.getCellInfo().getLon());
            cellInfoBO.setAzimuth(cellNewForm.getCellInfo().getAzimuth());
            cellInfoBO.setMechanitalTilt(cellNewForm.getCellInfo().getMechanitalTilt());
            cellInfoBO.setTotalTilt(cellNewForm.getCellInfo().getTotalTilt());
            cellInfoBO.setAntennaHigh(cellNewForm.getCellInfo().getAntennaHigh());
            cellInfoBO.setAntennaType(cellNewForm.getCellInfo().getAntennaType());
            cellInfoBO.setAntennaGain(cellNewForm.getCellInfo().getAntennaGain());
            cellInfoBO.setHoanCanhRaDoi(cellNewForm.getCellInfo().getHoanCanhRaDoi());
            cellInfoBO.setNgayHoatDong(cellNewForm.getCellInfo().getNgayHoatDong());
            cellInfoBO.setNoOfCarrier(cellNewForm.getCellInfo().getNoOfCarrier());
            iNode.addCellInfo(cellInfoBO);

            OmcCellInfoBO omcCellInfoBO = new OmcCellInfoBO();
            omcCellInfoBO.setOmcCellId(nodeId);
            omcCellInfoBO.setTenTrenHeThong(cellNewForm.getOmcCellInfo().getTenTrenHeThong());
            omcCellInfoBO.setLac(cellNewForm.getOmcCellInfo().getLac());
            omcCellInfoBO.setCi(cellNewForm.getOmcCellInfo().getCi());
            omcCellInfoBO.setBangTanId(cellNewForm.getOmcCellInfo().getBangTanId());
            iNode.addOmcCellInfo(omcCellInfoBO);

            trans.commit();
            trans.endTransaction();
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            String message = StringUtils.captureStackTrace(de);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("UK_OMC_CELL_INFO_LAC_CI") != -1) {
                    String msg = messageSource.getMessage("cell.omc.info.excute.sql.violated.UK_OMC_CELL_INFO_LAC_CI", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                    return "redirect:/user/preAdd";
                }
            }

            if (StringUtils.hasText(message)) {
                if (message.indexOf("NODE_TRAM_DU_AN_FK") != -1) {
                    String msg = messageSource.getMessage("cell.node.excute.sql.notfound.NODE_TRAM_DU_AN_FK", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                    return "redirect:/user/preAdd";
                }
            }

            if (StringUtils.hasText(message)) {
                if (message.indexOf("SYS_C0074255") != -1) {

                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Mã node đã tồn tại"));
//                    return "redirect:/user/preAdd";
                }
            }
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public String importDkCell(boolean commit, long userInsertId, ImportCellModel importcellModel, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
//            NodeBO nodeBO = new NodeBO();
//            nodeBO.setId(cellNewForm.getCellInfo().getId());
//            nodeBO.setBuildingId(cellNewForm.getBuildingId());

//            cellNewForm.getMaNodeCha();
//            
//
//            cellNewForm.setCellInfo(new CellInfoBO());
////            cellNewForm.getCellInfo().setNodeChaId(iNode.getNodeId(cell.getMaNodeCha()));
//            CategoriesFacade cate = new CategoriesFacade();
//            List<ThietBiBO> listThietBiBO = cate.findThietBiByName(cell.getTenThietBi());
//            if (listThietBiBO != null && listThietBiBO.size() > 0) {
//                cellNewForm.setThietBi(listThietBiBO.get(0));
//            }
////            cate.fin
//            cellNewForm.getCellInfo().setNoOfCarrier(Long.valueOf(cell.getNoOfCarrier()));
//
//            if (cellNewForm.getCellInfo().getNodeChaId() == null) {
//                return messageSource.getMessage("cell.new.parentId.notempty", null, locale);
//            }
//
//            if (cellNewForm.getThietBi().getThietBiId() == -1) {
//                return messageSource.getMessage("cell.new.thietbiId.notempty", null, locale);
//            }
//
//            if (cellNewForm.getLoaiTram().getLoaiTramId() == -1) {
//                return messageSource.getMessage("cell.new.netypeid.notempty", null, locale);
//            }
            String statusList = Constants.NE_APPROVE_ON + "," + Constants.NE_REG_OFF + "," + Constants.NE_UNAPPROVE_OFF;
            String maNode = "";
            List<NodeBO> listNode = iNode.findIdByMaNode(importcellModel.getMaNodeCha());
            if (listNode == null || listNode.size() < 1) {
                maNode = "";
            } else {
                List listNodeCha = iNode.findByNodeChaId(listNode.get(0).getId(), statusList);
                maNode = "DK_" + importcellModel.getMaNodeCha() + "" + (((listNodeCha == null) || listNodeCha.size() < 1) ? 1 : listNodeCha.size() + 1);
            }
//            nodeBO.setId(cellNewForm.getCellInfo().getId());
//            nodeBO.setBuildingId(cellNewForm.getBuildingId());
//            nodeBO.setCode("DK_" + cellNewForm.getBtsCode() + "" + (((listNodeCha == null) || listNodeCha.size() < 1) ? 1 : listNodeCha.size() + 1));
//            nodeBO.setThietBiId(cellNewForm.getThietBi() == null ? null : cellNewForm.getThietBi().getThietBiId());
//            nodeBO.setBuildingId(cellNewForm.getBuildingId());
//            nodeBO.setNeTypeName(cellNewForm.getLoaiNE().getId());
//            nodeBO.setLoaiTramId(cellNewForm.getLoaiTram() == null ? null : cellNewForm.getLoaiTram().getLoaiTramId());
//
//            nodeBO.setNodeChaId(cellNewForm.getCellInfo().getNodeChaId());
//            nodeBO.setTenNgQLy(cellNewForm.getCellInfo().getTenNgQLy());
//            nodeBO.setSDTQLy(cellNewForm.getCellInfo().getSDTQLy());
//            nodeBO.setStatus(Constants.NE_REG_ON);
//            nodeBO.setTrangThaiHDId(2l);
//            nodeBO.setTrangThaiQLId(1l);
//            nodeBO.setUserInsert(cellNewForm.getCellInfo().getUserInsert());
//            Long nodeId = iNode.addNode(nodeBO);
//            if (nodeId < 0l) {
//                throw new DAOException();
//            }
//            CellInfoBO cellInfoBO = new CellInfoBO();
//            cellInfoBO.setId(nodeId);
//            cellInfoBO.setLat(cellNewForm.getCellInfo().getLat());
//            cellInfoBO.setLon(cellNewForm.getCellInfo().getLon());
//            cellInfoBO.setAzimuth(cellNewForm.getCellInfo().getAzimuth());
//            cellInfoBO.setMechanitalTilt(cellNewForm.getCellInfo().getMechanitalTilt());
//            cellInfoBO.setTotalTilt(cellNewForm.getCellInfo().getTotalTilt());
//            cellInfoBO.setAntennaHigh(cellNewForm.getCellInfo().getAntennaHigh());
//            cellInfoBO.setAntennaType(cellNewForm.getCellInfo().getAntennaType());
//            cellInfoBO.setAntennaGain(cellNewForm.getCellInfo().getAntennaGain());
//            cellInfoBO.setHoanCanhRaDoi(cellNewForm.getCellInfo().getHoanCanhRaDoi());
//            cellInfoBO.setNgayHoatDong(cellNewForm.getCellInfo().getNgayHoatDong());
//            cellInfoBO.setNoOfCarrier(cellNewForm.getCellInfo().getNoOfCarrier());
//            iNode.addCellInfo(cellInfoBO);
//
//            OmcCellInfoBO omcCellInfoBO = new OmcCellInfoBO();
//            omcCellInfoBO.setOmcCellId(nodeId);
//            omcCellInfoBO.setTenTrenHeThong(cellNewForm.getOmcCellInfo().getTenTrenHeThong());
//            omcCellInfoBO.setLac(cellNewForm.getOmcCellInfo().getLac());
//            omcCellInfoBO.setCi(cellNewForm.getOmcCellInfo().getCi());
//            omcCellInfoBO.setBangTanId(cellNewForm.getOmcCellInfo().getBangTanId());
//            iNode.addOmcCellInfo(omcCellInfoBO);
            String objectId = Convert.convertNeTypeNameToObjectId(importcellModel.getNe_name());
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userInsertId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String ret = iNode.importCell(permission, maNode, userInsertId, importcellModel);
            if (commit) {

                trans.commit();
                trans.endTransaction();
            } else {

                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }

            ret = Convert.convertErrorCode(ret);
            return ret;
        } catch (DAOException de) {

            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            String message = StringUtils.captureStackTrace(de);

            if (StringUtils.hasText(message)) {
                if (message.indexOf("UK_OMC_CELL_INFO_LAC_CI") != -1) {
                    return messageSource.getMessage("cell.omc.info.excute.sql.violated.UK_OMC_CELL_INFO_LAC_CI", null, locale);
                }
            }

            if (StringUtils.hasText(message)) {
                if (message.indexOf("NODE_TRAM_DU_AN_FK") != -1) {
                    return messageSource.getMessage("cell.node.excute.sql.notfound.NODE_TRAM_DU_AN_FK", null, locale);
                }
            }
            return "Lỗi hệ thống";
        } catch (Exception de) {

            return "Lỗi hệ thống";
        } finally {
            DatabaseUtils.close(trans);
        }
//        return "Ok";
    }

    public String importDkBts(boolean commit, long userInsertId, ImportBtsModel importBtsModel, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws ServiceException {
        ITransaction trans = null;
        String result = "-1";
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            String status = "DK";
            CategoriesFacade fcate = new CategoriesFacade();

            //DonViBO donvi = fcate.findAllDonVi(null, importBtsModel.getTenDonViQuanLy(),"").get(0);
            String codeVNPT = "";//getCodeVNPT(importBtsModel.getTenDonViQuanLy(), Convert.convertNeTypeToTechnology(importBtsModel.getNeTypeName()), status);

            result = iNode.importDkBts(codeVNPT, userInsertId, importBtsModel, attr, messageSource, locale);
            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {
                DatabaseUtils.rollback(trans);
            }

            result = Convert.convertErrorCodeBTS(result, importBtsModel.getNeTypeName());

            return result;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);

            return "Lỗi hệ thống";
        } finally {
            DatabaseUtils.close(trans);

        }
//        return "Ok";
    }

    public List<ImportBtsModel> importSite(List<ImportBtsModel> list, boolean resultCheckFile, long userId, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws ServiceException {
        ITransaction trans = null;
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            boolean commit = true;
            if (list != null && !list.isEmpty()) {
                for (ImportBtsModel item : list) {
                    if (resultCheckFile) {
                        String strCheck = addSite(i, userId, item, attr, messageSource, locale);
                        item.setCheckDB(strCheck);
                        if (strCheck == null || !strCheck.equalsIgnoreCase("OK")) {
                            commit = false;
                        }
                    } else {
                        item.setCheckDB(resourceBundle.getString("cell.new.import.validate.file"));
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

    public String addSite(INode iNode, long userInsertId, ImportBtsModel importBtsModel, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws ServiceException {
        String result;
        try {
//            String status = "DK";
//            CategoriesFacade fcate = new CategoriesFacade();
            //DonViBO donvi = fcate.findAllDonVi(null, importBtsModel.getTenDonViQuanLy(),"").get(0);
            String codeVNPT = "";//getCodeVNPT(importBtsModel.getTenDonViQuanLy(), Convert.convertNeTypeToTechnology(importBtsModel.getNeTypeName()), status);

            result = iNode.importDkBts(codeVNPT, userInsertId, importBtsModel, attr, messageSource, locale);
            result = Convert.convertErrorCodeBTS(result, importBtsModel.getNeTypeName());
            return result;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            return "Lỗi hệ thống";
        }
    }

    public String excelUpdateCell(boolean commit, long userInsertId, ExcelCellUpdateBO excelCellUpdateBO, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            String objectId = Convert.convertNeTypeNameToObjectId(excelCellUpdateBO.getKieuCell());
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userInsertId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String ret = iNode.excelCellUpdate(permission, userInsertId, excelCellUpdateBO);
            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {

                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
            ret = Convert.convertErrorCodeUpdateCell(ret);

            return ret;
        } catch (DAOException de) {

            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
//            throw new ServiceException(de);
            String message = StringUtils.captureStackTrace(de);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("UK_OMC_CELL_INFO_LAC_CI") != -1) {
                    return messageSource.getMessage("cell.omc.info.excute.sql.violated.UK_OMC_CELL_INFO_LAC_CI", null, locale);
                }
            }

            if (StringUtils.hasText(message)) {
                if (message.indexOf("NODE_TRAM_DU_AN_FK") != -1) {
                    return messageSource.getMessage("cell.node.excute.sql.notfound.NODE_TRAM_DU_AN_FK", null, locale);
                }
            }
            return "Lỗi hệ thống";
        } catch (Exception de) {
            return "Lỗi hệ thống";
        } finally {
            DatabaseUtils.close(trans);
        }

    }

    public String excelUpdateBts(boolean commit, long userInsertId, ExcelBtsUpdateBO excelBtsUpdateBO, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            String objectId = Convert.convertNeTypeNameToObjectId(excelBtsUpdateBO.getLoaiNE());
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userInsertId), "U", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String ret = iNode.excelBtsUpdate(permission, userInsertId, excelBtsUpdateBO);
            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {

                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
            //ret = Convert.convertErrorCodeUpdateBTS(ret);

            return ret;
        } catch (DAOException de) {

            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            return "Lỗi hệ thống";
        } catch (Exception de) {
            logger.error(de.getMessage(), de);
            return "Lỗi hệ thống";
        } finally {
            DatabaseUtils.close(trans);
        }

    }

    public String excelDeleteNode(boolean commit, long userInsertId, ExcelDeleteNodeBO excelDeleteNodeBO, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            String objectId = Convert.convertNeTypeNameToObjectId(excelDeleteNodeBO.getLoaiNE());
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userInsertId), "D", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String ret = iNode.excelDeleteNode(permission, userInsertId, excelDeleteNodeBO);
            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {

                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
            //ret = Convert.convertErrorCodeDeleteNode(ret);

            return ret;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            return "Lỗi hệ thống";
        } catch (Exception de) {
            logger.error(de.getMessage(), de);
            return "Lỗi hệ thống";
        } finally {
            DatabaseUtils.close(trans);
        }

    }

    public String excelDestroyNode(boolean commit, long userInsertId, ExcelDeleteNodeBO excelDeleteNodeBO, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            String objectId = Convert.convertNeTypeNameToObjectId(excelDeleteNodeBO.getLoaiNE());
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(userInsertId), "D", objectId);
            String permission = classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "";
            String ret = iNode.excelDestroyNode(permission, userInsertId, excelDeleteNodeBO);
            if (commit) {
                trans.commit();
                trans.endTransaction();
            } else {

                DatabaseUtils.rollback(trans);
                trans.endTransaction();
            }
            ret = Convert.convertErrorCodeDeleteNode(ret);

            return ret;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            return "Lỗi hệ thống";
        } catch (Exception de) {
            logger.error(de.getMessage(), de);
            return "Lỗi hệ thống";
        } finally {
            DatabaseUtils.close(trans);
        }

    }

    public int addTram(BTSInfoBO model) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            result = iNode.addTram(model);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    // get code new VNPT TramCell 
    public String getCodeVNPT(String tinhCode, String ne, String status) throws ServiceException {
        ITransaction trans = null;
        String result = "";
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            result = iNode.getCodeVNPT(tinhCode, ne, status);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public List<ClassAttributeBO> getAttributeFormName(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            List<ClassAttributeBO> temp = i.getAttributeFormName(id);
            for (int j = 0; j < temp.size(); j++) {
                ClassAttributeBO c = temp.get(j);
                temp.get(j).setAttrBO(i.getAttributeByClass(c.getId() + ""));
            }
            return temp;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<TramDuAnBO> findAllTramDuAn(String startRow, String endRow, String name, String tinhId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllTramDuAn(startRow, endRow, name, tinhId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalAllTramDuAn(String name, String tinhId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalAllTramDuAn(name, tinhId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<CellNewForm> findCell(Long nodeId, String statusList, String tinhTpId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findCell(nodeId, statusList, tinhTpId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

//      trunglk_start
    public int getTotalUser(String maDuAn,
            String tenDuAn,
            String msHopDong,
            String maTramBTS,
            String maTramNodeB,
            String maTramQuyHoach) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotal(maDuAn, tenDuAn, msHopDong, maTramBTS, maTramNodeB, maTramQuyHoach);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<TramDuAnBO> findAllTramDA(String maDuAn,
            String tenDuAn,
            String msHopDong,
            String maTramBTS,
            String maTramNodeB,
            String maTramQuyHoach,
            int startRow,
            int endRow) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(Constants.DB_CB);
            i.setTransaction(trans);
            return i.findTramDAAll(maDuAn, tenDuAn, msHopDong, maTramBTS, maTramNodeB, maTramQuyHoach, startRow, endRow);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void addtramDA(TramDuAnBO tramDABO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            i.addTramDA(tramDABO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//      trunglk_end

    public OmcCellInfoBO findOmcCellInfo(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findOmcCellInfo(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<BTSInfoBO> findDuyetTram(Long nodeId, String statusList, String tinhTpId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findDuyetTram(nodeId, statusList, tinhTpId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public Long addNode(AntenInfoBO model) throws ServiceException {
        ITransaction trans = null;
        Long result = -1L;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            NodeBO nodeBO = new NodeBO();
            nodeBO.setCode(model.getCode());
            nodeBO.setBuildingId(model.getBuildingId());
            nodeBO.setDonViId(model.getDonViId());
            nodeBO.setNeTypeId(model.getNeTypeId());
            nodeBO.setStatus(Constants.NE_REG_ON);
            nodeBO.setTrangThaiHDId(2l);
            nodeBO.setTrangThaiQLId(1l);
            nodeBO.setUserInsert(model.getUserInsert());
            result = i.addNode(nodeBO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public Long updateNode(AntenInfoBO model) throws ServiceException {
        ITransaction trans = null;
        Long result = -1L;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            NodeBO nodeBO = new NodeBO();
            nodeBO.setCode(model.getCode());
            nodeBO.setBuildingId(model.getBuildingId());
            nodeBO.setDonViId(model.getDonViId());
            nodeBO.setStatus(Constants.NE_REG_ON);
            nodeBO.setUserUpdate(model.getUserUpdate());

            i.updateNode(nodeBO);
            trans.commit();
            trans.endTransaction();
            result = model.getId();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int updateOmcBtsInfo(BTSInfoBO bo, Long userId) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();

            result = i.updateOmcBtsInfo(bo, userId);
            trans.commit();
            trans.endTransaction();

        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int updateOmcNodeBInfo(Long nodeId, String tenTrenHeThong, String tenBSCRNC, String tenBSCRNCQly,
            String dcHsdpa42M, String fileterUser, String bangTanId, Long userId) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();

            result = i.updateOmcNodeBInfo(nodeId, tenTrenHeThong, tenBSCRNC, tenBSCRNCQly,
                    dcHsdpa42M, fileterUser, bangTanId, userId);
            trans.commit();
            trans.endTransaction();

        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int updateOmcEnodeBInfo(Long nodeId,
            String tenTrenHeThong, String tenBSCRNC, String tenBSCRNCQly,
            String MscMss, String sgsn, String dcHsdpa42M, String fileterUser, Long bangTanId, Long userId) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();

            result = i.updateOmcEnodeBInfo(nodeId,
                    tenTrenHeThong, tenBSCRNC, tenBSCRNCQly,
                    MscMss, sgsn, dcHsdpa42M, fileterUser, bangTanId, userId);
            trans.commit();
            trans.endTransaction();

        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    void swapBts(BTSInfoBO bo, Long btsId, long userId, String iBscRncName, Long vendorId) {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();

            updateOmcBtsInfo(bo, userId);
            i.swapBts(btsId, userId, iBscRncName, vendorId);
            trans.commit();
            trans.endTransaction();

        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            // return result;
        }
    }

    void swapNodeB(Long rNodebId, String iNodebName, String iBscRncName,
            String iBscRncMngName, String iDcHsdpa42M, String iFilterUser, Object object, long userId, Long vendor) {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();

            updateOmcNodeBInfo(rNodebId, iNodebName,
                    iBscRncName, iBscRncMngName,
                    iDcHsdpa42M, iFilterUser, null, userId);

            i.swapNodeB(rNodebId, vendor, userId, iBscRncName);
            trans.commit();
            trans.endTransaction();

        } catch (DAOException de) {
            logger.error(de.getMessage(), de);
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            // return result;
        }
    }

    void swapENodeB(Long reNodebId, String iEnodebName, String iBscRncName, String iBscRncMngName,
            String iMscMss, String iSgsn, String iDcHsdpa42M, String iFilterUser, Object object, long userId, Long iVendor) {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();

            updateOmcEnodeBInfo(reNodebId,
                    iEnodebName, iBscRncName, iBscRncMngName,
                    iMscMss, iSgsn, iDcHsdpa42M,
                    iFilterUser, null, userId);
            i.swapENodeB(reNodebId, iVendor, userId, iBscRncName);
            trans.commit();
            trans.endTransaction();

        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            // return result;
        }
    }

    public List<?> findAllDetailMap(String nodeId, String neTypeId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllNodeDetailMap(nodeId, neTypeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NodeBO> findSiteByBuilding(String buildingId, String neTypeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode i = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findSiteByBuilding(buildingId, neTypeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int approveAllNode(ApproveAllForm approveForm, Long userUpdate) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            int result = iNode.approveNodeAll(approveForm, userUpdate);
            trans.commit();
            trans.endTransaction();
            return result;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            String message = StringUtils.captureStackTrace(de);
            return 0;
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int approveAllBuilding(ApproveAllForm approveForm, Long userUpdate) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            int result = iNode.approveAllBuilding(approveForm, userUpdate);
            trans.commit();
            trans.endTransaction();
            return result;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            String message = StringUtils.captureStackTrace(de);
            return 0;
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // kiem tra xem ten tren he thong cua node co ton tai hay khong. ton tai thi tra ve 1
    public int fn_check_name_system(Integer prn_node_id, String prn_name_system, Integer prn_ne_type_id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            INode iNode = factory.getNodeDAO();
            trans.connectionType(DB_ADMIN);
            iNode.setTransaction(trans);
            trans.beginTransaction();
            int result = iNode.fn_check_name_system(prn_node_id, prn_name_system, prn_ne_type_id);
//            
//            
//            
            return result;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            return 0;
        }
    }
}
