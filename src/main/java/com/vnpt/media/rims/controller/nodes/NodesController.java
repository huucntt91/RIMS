/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.nodes;

import com.blogspot.na5cent.exom.ExOM;
import com.google.gson.Gson;
import com.vnpt.media.rims.bean.*;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.ContentDataTableItem;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.facade.*;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.formbean.ApproveAllForm;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.FilterObject;
import com.vnpt.media.rims.formbean.NeLinkForm;
import com.vnpt.media.rims.formbean.ReportCSHT;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ResponseBody;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.FileCopyUtils;

@Controller
@RequestMapping(value = "/nodes")
public class NodesController {

    private static final Logger logger = LogManager.getLogger(NodesController.class);
    private static final String LIST = "nodes/list/list";
    private static final String FORM = "nodes/node/new";
    private static final String ADDTRAM = "nodes/tram/tramNew";
    private static final String LIST_BLACK_POINT = "nodes/blackPoint/list";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("cauHinhList")
    public List findAllCauHinh() {
        return DmCauHinhPortFacade.fc_find_all("");
    }

    @ModelAttribute("bangTanList")
    public List findAllBangTan() {
        return BangTanFacade.fc_find_all("");
    }

    @ModelAttribute("trangThaiHDList")
    public List findAllTrangThaiHD() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTrangThaiHD("");
    }

    @ModelAttribute("trangThaiQLList")
    public List findAllTrangThaiQL() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTrangThaiQL("");
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "neTypeId", required = false) String neTypeId,
            @RequestParam(value = "thietBiId", required = false) String thietBiId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId,
            @RequestParam(value = "phuongXaId", required = false) String phuongXaId,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "strFilter", required = false) String strFilter,
            //            @RequestParam(value = "filterObject", required = false) List<FilterObject> filterObject,
            //            @RequestParam(value = "objectFill", required = false) String objectFill,
            @RequestParam(value = "column", required = false) String column,
            @RequestParam(value = "filterType", required = false) String filterType,
            @RequestParam(value = "value", required = false) String value,
            ModelMap mm, HttpServletRequest request) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        logger.info("user: {}, ip: {}, danh sach doi tuong init : {} {} {} {} {} {} {} {} {}", user.getUsername(), request.getRemoteAddr(), page, code, neTypeId, thietBiId, tinhTpId, quanHuyenId, phuongXaId, status, khuvucId);
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        page = page == null ? "1" : page;
        Integer pageInt;
        try {
            pageInt = Integer.parseInt(page);
        } catch (Exception ex) {
            pageInt = 1;
        }

        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        quanHuyenId = quanHuyenId == null ? "" : quanHuyenId;
        phuongXaId = phuongXaId == null ? "" : phuongXaId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        strFilter = strFilter == null ? "" : strFilter;
//        objectFill = objectFill == null ? "" : objectFill;
        column = column == null ? "" : column;
        filterType = filterType == null ? "" : filterType;
        value = value == null ? "" : value;

        code = code == null ? "" : code;
        String neType = neTypeId == null ? "2" : neTypeId;
        thietBiId = thietBiId == null ? "" : thietBiId;
        status = status == null ? "" : status;
        NodesFacade facade = new NodesFacade();
        int totalRows;
        try {
            totalRows = neTypeId == null ? 0 : facade.getTotalDetailNode(code, khuvucId, tinhTpId, quanHuyenId, phuongXaId, neType, thietBiId, status, strFilter);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            totalRows = 0;
        }
        Page objPage = new Page();

        int numPerPage = Constants.NUMBER_FOR_PAGING;
        int totalPages;

        if (totalRows % numPerPage == 0) {
            totalPages = (int) totalRows / numPerPage;
        } else {
            totalPages = (int) totalRows / numPerPage + 1;
        }
        if (totalRows == 0) {
            totalPages = 0;
        }

        if (pageInt < 1) {
            pageInt = 1;
            return ("redirect:/nodes/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/nodes/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Nodes");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }

        if (neTypeId != null) {
            NodesFacade nodesFacade = new NodesFacade();
            List<?> list = null;
            try {
                logger.info("user: {}, ip: {}, call findAllDetail({},{},{},{},{},{},{},{},{},{})", user.getUsername(), request.getRemoteAddr(), startRow, endRow, code,
                        khuvucId, tinhTpId, quanHuyenId, phuongXaId, neType, thietBiId, status);
                list = nodesFacade.findAllDetailNode("", String.valueOf(startRow),
                        String.valueOf(endRow), code,
                        khuvucId, tinhTpId, quanHuyenId, phuongXaId, neType, thietBiId, status, strFilter);
                logger.info("user: {}, ip: {}, done findAllDetail: {}", user.getUsername(), request.getRemoteAddr(), list.size());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            switch (neType) {
                case "5":
                    mm.put("list", (List<OmcCell2gInfoBO>) list);
                    break;
                case "6":
                    mm.put("list", (List<OmcCell3gInfoBO>) list);
                    break;
                case "7":
                    mm.put("list", (List<OmcCell4gInfoBO>) list);
                    break;
                case "2":
                case "3":
                case "8":
                    mm.put("list", (List<BTSInfoBO>) list);
                    break;
                case "11":
                    mm.put("list", (List<BSCRNCInfoBO>) list);
                    break;
                default:
                    break;
            }
        }
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrrView = new ArrayList<>();
        try {
            logger.debug("user: {}, ip: {}, call findClassAttrByUserId({},{},{})", user.getUsername(), request.getRemoteAddr(), user.getId(), "S", Convert.convertNeTypeToObjectId(neType));
            classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", Convert.convertNeTypeToObjectId(neType));
            logger.debug("user: {}, ip: {}, call findClassAttrByUserId:{}", user.getUsername(), request.getRemoteAddr(), classAtrrView.size());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        mm.addAttribute("classAtrrView", classAtrrView);
        mm.put("phuongXaId", phuongXaId);
        mm.put("code", code);
        mm.put("tinhTpId", tinhTpId);
        mm.put("quanHuyenId", quanHuyenId);
        mm.put("neTypeId", neType);
        mm.put("khuvucId", khuvucId);
        mm.put("thietBiId", thietBiId);
        mm.put("startRow", startRow);
        mm.put("status", status);
        mm.put("userId", user.getId());
        mm.put("strFilter", strFilter);
//        mm.put("objectFill", objectFill);   
        mm.put("column", column);
        mm.put("filterType", filterType);
        mm.put("value", value);
        mm.put("approveForm", new ApproveForm());

        ArrayList<DMCellGroupBO> listCellGroup = CellGroupFacade.fc_find_all("");
        HashMap<String, Object> hashMapListCellGroup = new HashMap<>();
        for (int i = 0; i < listCellGroup.size(); i++) {
            DMCellGroupBO object = listCellGroup.get(i);
            hashMapListCellGroup.put(object.getId(), object);
        }
        mm.put("hashMapListCellGroup", hashMapListCellGroup);

        return LIST;
    }

    @RequestMapping(value = "/init_BK", method = RequestMethod.GET)
    public String init_BK(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "neTypeId", required = false) String neTypeId, @RequestParam(value = "thietBiId", required = false) String thietBiId, @RequestParam(value = "tinhTpId", required = false) String tinhTpId, @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, @RequestParam(value = "phuongXaId", required = false) String phuongXaId, @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            ModelMap mm, HttpServletRequest request) {
        logger.info("Action init Building");

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        quanHuyenId = quanHuyenId == null ? "" : quanHuyenId;
        phuongXaId = phuongXaId == null ? "" : phuongXaId;
        khuvucId = khuvucId == null ? "" : khuvucId;

        code = code == null ? "" : code;
        neTypeId = neTypeId == null ? "" : neTypeId;
        thietBiId = thietBiId == null ? "" : thietBiId;
        status = status == null ? "" : status;
        NodesFacade facade = new NodesFacade();

        int totalRows = facade.getTotalNode(code, khuvucId, tinhTpId, quanHuyenId, phuongXaId, neTypeId, thietBiId, status);

        Page objPage = new Page();

        int numPerPage = Constants.NUMBER_FOR_PAGING;
        int totalPages;

        if (totalRows % numPerPage == 0) {
            totalPages = (int) totalRows / numPerPage;
        } else {
            totalPages = (int) totalRows / numPerPage + 1;
        }
        if (totalRows == 0) {
            totalPages = 0;
        }

        if (pageInt < 1) {
            pageInt = 1;
            return ("redirect:/nodes/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/nodes/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Nodes");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<NodeBO> list = facade.findAllNodeBO(String.valueOf(startRow), String.valueOf(endRow), code, khuvucId, tinhTpId, quanHuyenId, phuongXaId, neTypeId, thietBiId, status);

        mm.put("list", list);
        mm.put("phuongXaId", phuongXaId);
//        mm.put("tinhTpId", tinhTpId.contains(",") ? "" : tinhTpId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("quanHuyenId", quanHuyenId);
        mm.put("neTypeId", neTypeId);
        mm.put("khuvucId", khuvucId);
        mm.put("thietBiId", thietBiId);
        mm.put("startRow", startRow);
        mm.put("status", status);
        mm.put("approveForm", new ApproveForm());
        CategoriesFacade cateFacade = new CategoriesFacade();
        mm.put("huyenList", cateFacade.findAllHuyen(tinhTpId));
        mm.put("phuongXaList", cateFacade.findAllPhuongXa(quanHuyenId));
        return LIST;
    }

    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String popup(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "neTypeId", required = false) String neTypeId, @RequestParam(value = "thietBiId", required = false) String thietBiId, @RequestParam(value = "tinhTpId", required = false) String tinhTpId, @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, @RequestParam(value = "phuongXaId", required = false) String phuongXaId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            ModelMap mm, HttpServletRequest request) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        logger.info("user: {}, ip: {}, popup:{} {} {} {} {} {} {} {} {}", user.getUsername(), request.getRemoteAddr(), page, code, neTypeId, thietBiId, tinhTpId, quanHuyenId, phuongXaId, khuvucId);

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        page = page == null ? "1" : page;
        Integer pageInt;
        try {
            pageInt = Integer.parseInt(page);
        } catch (Exception ex) {
            pageInt = 1;
        }
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        quanHuyenId = quanHuyenId == null ? "" : quanHuyenId;
        phuongXaId = phuongXaId == null ? "" : phuongXaId;

        code = code == null ? "" : code;
        neTypeId = neTypeId == null ? "" : neTypeId;
        thietBiId = thietBiId == null ? "" : thietBiId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        NodesFacade facade = new NodesFacade();

        String statusList = Constants.NE_APPROVE_ON + "," + Constants.NE_REG_OFF + "," + Constants.NE_UNAPPROVE_OFF;

        int totalRows;
        try {
            totalRows = neTypeId == null ? 0 : facade.getTotalDetail(code, khuvucId, tinhTpId, quanHuyenId, phuongXaId, neTypeId, thietBiId, statusList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            totalRows = 0;
        }
        Page objPage = new Page();

        int numPerPage = Constants.NUMBER_FOR_PAGING;
        int totalPages;

        if (totalRows % numPerPage == 0) {
            totalPages = (int) totalRows / numPerPage;
        } else {
            totalPages = (int) totalRows / numPerPage + 1;
        }
        if (totalRows == 0) {
            totalPages = 0;
        }

        if (pageInt < 1) {
            pageInt = 1;
            return ("redirect:/popup/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/popup/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Nodes");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<NodeBO> list = new ArrayList<NodeBO>();
        try {
            logger.info("user: {}, ip: {}, call findAllNodeBO({},{},{},{},{},{},{},{},{},{})", user.getUsername(), request.getRemoteAddr(), startRow, endRow, code, khuvucId, tinhTpId, quanHuyenId, phuongXaId, neTypeId, thietBiId, statusList);
            list = facade.findAllNodeBO(String.valueOf(startRow), String.valueOf(endRow), code, khuvucId, tinhTpId, quanHuyenId, phuongXaId, neTypeId, thietBiId, statusList);
            logger.info("user: {}, ip: {}, done findAllNodeBO: {}", user.getUsername(), request.getRemoteAddr(), list.size());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        mm.put("list", list);
        mm.put("phuongXaId", phuongXaId);
        mm.put("tinhTpId", tinhTpId.contains(",") ? "" : tinhTpId);
        mm.put("khuvucId", khuvucId.contains(",") ? "" : khuvucId);

        mm.put("quanHuyenId", quanHuyenId);
        mm.put("neTypeId", neTypeId);
        mm.put("thietBiId", thietBiId);

        mm.put("startRow", startRow);
        return "nodes/list/popup";
    }

    @RequestMapping(value = "/popupTramDuAn", method = RequestMethod.GET)
    public String popupTramDuAn(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            ModelMap mm, HttpServletRequest request) {
        logger.info("Action init Popup Tram Du An");

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;

        code = code == null ? "" : code;

        NodesFacade facade = new NodesFacade();

        int totalRows = facade.getTotalAllTramDuAn(page, tinhTpId);

        Page objPage = new Page();

        int numPerPage = Constants.NUMBER_FOR_PAGING;
        int totalPages;

        if (totalRows % numPerPage == 0) {
            totalPages = (int) totalRows / numPerPage;
        } else {
            totalPages = (int) totalRows / numPerPage + 1;
        }
        if (totalRows == 0) {
            totalPages = 0;
        }

        if (pageInt < 1) {
            pageInt = 1;
            return ("redirect:/popupTramDuAn/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/popupTramDuAn/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Tram Du An");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<TramDuAnBO> list = facade.findAllTramDuAn(String.valueOf(startRow), String.valueOf(endRow), code, tinhTpId);
        mm.put("list", list);
        mm.put("tinhTpId", tinhTpId.contains(",") ? "" : tinhTpId);

        mm.put("startRow", startRow);
        return "nodes/list/popupTramDuAn";
    }

    @RequestMapping(value = "/addtram", method = RequestMethod.GET)
    public String preAddTram(ModelMap mm, HttpServletRequest request) throws Exception {
        return "nodes/tram/tramNew";
    }

    @RequestMapping(value = "/postaddtram", method = RequestMethod.POST)
    public String addTram(ModelMap mm, @Valid @ModelAttribute(value = "model") BTSInfoBO model, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        String backUrl = request.getHeader("Referer");
        try {
            NodesFacade adminFacade = new NodesFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            // check valid form
            if (model.getNodeChaId() == null && model.getNeTypeId() != 8) { // enodeb chua co node cha la bsc ...
                String noWhitespaceAllowed = messageSource.getMessage("tram.new.parentId.notempty", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                mm.put("model", model);
                return "redirect:" + backUrl;
            }
//            if (model.getTramDAId() == null) {
//                String noWhitespaceAllowed = messageSource.getMessage("tram.new.tramDuAnId.notempty", null, locale);
//                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
//                return "redirect:" + backUrl;
//            }

//            if (model.getBuildingId() == null) {
//                String noWhitespaceAllowed = messageSource.getMessage("tram.new.buildingId.notempty", null, locale);
//                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
//                return "redirect:" + backUrl;
//            }
            // kiem tra xem ten tren he thong cua node co ton tai hay khong. ton tai thi tra ve 1
            if (adminFacade.fn_check_name_system(null, model.getTenTrenHeThong(), model.getNeTypeId().intValue()) > 0) {
                String noWhitespaceAllowed = messageSource.getMessage("tram.systemname.exist", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                mm.put("model", model);
                return "redirect:" + backUrl;
            }

            // end
            int result;
            if (model.getId() != null && model.getId() > 0) {
                model.setStatus(Constants.NE_REG_ON);
                model.setUserUpdate(user.getId());
                result = adminFacade.updateTram(model);

            } else {
                // Get VNPT code

                CategoriesFacade fcate = new CategoriesFacade();
                DonViBO donvi = fcate.findAllDonVi(String.valueOf(model.getDonViId()), "", "").get(0);
                String status = "DK";
                String codeVNPT = adminFacade.getCodeVNPT(donvi.getCodeTinhTp(), Convert.convertNeTypeToTechnology(model.getNeTypeId()), status);
                model.setCode(codeVNPT);
                model.setUserInsert(user.getId());
                result = adminFacade.addTram(model);
                if (result == -1) {
                    logger.info("Exception trung ma_node :{}", codeVNPT);
                }
            }

            if (result > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/nodes/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            }

        } catch (Exception e) {

            String message = StringUtils.captureStackTrace(e);
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "redirect:" + backUrl;
    }

    @RequestMapping(value = "/preRegUpdate/{nodeId}/{neTypeId}", method = RequestMethod.GET)
    public String preUpdateReg(@PathVariable(value = "nodeId") String nodeId,
            @PathVariable(value = "neTypeId") String neTypeId,
            ModelMap mm, HttpServletRequest request) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        neTypeId = neTypeId == null ? "" : neTypeId;

        NodesFacade nodesFacade = new NodesFacade();

        List<BTSInfoBO> list = (List<BTSInfoBO>) nodesFacade.findDetail(nodeId, neTypeId);
        mm.put("model", list.get(0));
        return "nodes/tram/tramNew";
    }

    @RequestMapping(value = "/preUpdate/{id}/{neTypeId}", method = RequestMethod.GET)
    public String preUpdate(ModelMap mm, @PathVariable(value = "id") String id, @PathVariable(value = "neTypeId") String neTypeId,
            Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(neTypeId));
        List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", Convert.convertNeTypeToObjectId(neTypeId));
        mm.addAttribute("classAtrrEdit", classAtrrEdit);
        mm.addAttribute("classAtrrView", classAtrrView);
        NodesFacade nodesFacade = new NodesFacade();

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        List<BTSInfoBO> list = (List<BTSInfoBO>) nodesFacade.findDetail(id, neTypeId);
        mm.put("model", list.get(0));
        return "nodes/tram/tramEdit";
//
    }

    /*
    update tram bang form
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute(value = "model") BTSInfoBO model, ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        model.setUserUpdate(user.getId());
        String backUrl = request.getHeader("Referer");
        NodesFacade nodesFacade = new NodesFacade();
        // kiem tra xem ten tren he thong cua node co ton tai hay khong. ton tai thi tra ve 1
        if (nodesFacade.fn_check_name_system(model.getId().intValue(), model.getTenTrenHeThong(), model.getNeTypeId().intValue()) > 0) {
            String noWhitespaceAllowed = messageSource.getMessage("tram.systemname.exist", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
            mm.put("model", model);
            return "redirect:" + backUrl;
        }
        if (nodesFacade.updateTram(model) > 0) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
            return "redirect:/nodes/init?neTypeId=" + model.getNeTypeId();
        } else {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/nodes/preUpdate/" + model.getId() + "/" + model.getNeTypeId();
        }

    }

    @RequestMapping(value = "/detail/{id}/{neTypeId}", method = RequestMethod.GET)
    public String detail(ModelMap mm, @PathVariable(value = "id") String id, @PathVariable(value = "neTypeId") String neTypeId,
            Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", Convert.convertNeTypeToObjectId(neTypeId));
        mm.addAttribute("classAtrrView", classAtrrView);
        NodesFacade nodesFacade = new NodesFacade();

        List<BTSInfoBO> list = (List<BTSInfoBO>) nodesFacade.findDetail(id, neTypeId);
        mm.put("model", list.get(0));
        return "nodes/tram/tramDetail";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String preAdd(@RequestParam(value = "neTypeId", required = false) String neTypeId, ModelMap mm, HttpServletRequest request) throws Exception {

        if (neTypeId == null || neTypeId.equals("")) {
            return FORM;
        }
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(neTypeId));
        mm.addAttribute("classAtrr", classAtrr);

        NodeBO model = new NodeBO();
        mm.addAttribute("model", model);
        mm.addAttribute("neTypeId", neTypeId);
        switch (neTypeId) {
            case "2":
                // return form BTS
                return "nodes/edit/tram";
            case "3":
                // return form nodeB
                return "nodes/node/nodeb";
            case "5":
                // return form  cell 2g
                return "nodes/node/cell";
            case "6":
                // return form  cell 3g
                return "nodes/node/cell";
            case "7":
                // return form  cell 4g
                return "nodes/node/cell";
            case "8":
                // return form  enodeb
                return "nodes/node/enodeb";
            default:
                return FORM;
        }
    }

    @RequestMapping(value = "/view/{type}/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        logger.debug("view Node");
        try {
            if (StringUtils.hasText(id) && StringUtils.hasText(type)) {
                NodesFacade facade = new NodesFacade();
                switch (type) {
                    //BTS
                    case "2": {
                        List<BTSInfoBO> list = (List<BTSInfoBO>) facade.findDetail(id, type);
                        mm.addAttribute("model", list.get(0));
                        return "nodes/view/bts";
                    }
                    //NodeB
                    case "3": {
                        List<NodeBInfoBO> list = (List<NodeBInfoBO>) facade.findDetail(id, type);
                        mm.addAttribute("model", list.get(0));
                        return "nodes/view/nodeb";
                    }
                    //Cell 2g,3g,4g
                    case "5":
                    case "6":
                    case "7": {
                        List<CellInfoBO> list = (List<CellInfoBO>) facade.findDetail(id, type);
                        mm.addAttribute("model", list.get(0));
                        return "nodes/view/cell";
                    }
                    //BSC/RNC/MBSC
                    case "11": {
                        List<BSCRNCInfoBO> list = (List<BSCRNCInfoBO>) facade.findDetail(id, type);
                        mm.addAttribute("model", list.get(0));
                        return "nodes/view/bsc_rnc";
                    }
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "";
    }

    @RequestMapping(value = "/approveOn", method = RequestMethod.GET)
    public String approveOn(ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        StringBuilder permisMenu = (StringBuilder) request.getSession().getAttribute(Constants.FUNCTION_KEY);
        if (!permisMenu.toString().toLowerCase().contains("nodes/approveon")) {
            return "redirect:/home/";
        }

        String statusList = Constants.NE_REG_ON + "," + Constants.NE_REG_OFF;
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        logger.info("user: {}, ip: {}, call findDuyetTram({},'{}',{})", user.getUsername(), request.getRemoteAddr(), "", statusList, tinhTpId);
        List<BTSInfoBO> list = NodeObjectFacade.findDuyetTram("", statusList, tinhTpId);
        logger.info("user: {}, ip: {}, done findDuyetTram: {}", user.getUsername(), request.getRemoteAddr(), list.size());
        mm.put("list", list);
        return "nodes/tram/tramList";
    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String approve(@ModelAttribute(value = "approveForm") ApproveForm approveForm, ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        NodesFacade nodesFacade = new NodesFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        return String.valueOf(nodesFacade.approveTram(approveForm, user.getId()));
    }

    @RequestMapping(value = "/approveall", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String approveAll(@ModelAttribute(value = "approveForm") ApproveAllForm approveForm, ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        logger.info("user: {}, ip: {}, approveAll: {}", user.getUsername(), request.getRemoteAddr(), approveForm.listParam());
        NodesFacade nodesFacade = new NodesFacade();
        return String.valueOf(nodesFacade.approveAllNode(approveForm, user.getId()));
    }

    @ModelAttribute("thietBiList")
    public List findAllThietBi() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllThietBi("");
    }

    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
    }

    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
//        CategoriesFacade facade = new CategoriesFacade();
//        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
//        return facade.findAllKhuVuc(String.join(",", tinhManager));
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);

        return facade.findAllKhuVuc(String.join(",", tinhManager));
    }

    @ModelAttribute("neList")
    public List findAllNE() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllLoaiNe("");
    }

    @ModelAttribute("tramList")
    public List findAllTram() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllLoaiTram("");
    }

    @ModelAttribute("dvList")
    public List findAllDonVi(HttpServletRequest request) {
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhs = String.join(",", tinhManager);
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllDonVi("", "", tinhs);
    }

    @RequestMapping(value = "/getNodeLink/{id}", method = RequestMethod.GET)
    public String getNodeLink(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        NodesFacade facade = new NodesFacade();
        List<NeLinkForm> list = facade.findNodeLink(id);
        mm.put("list", list);
        mm.put("nodeId", id);
        return "nodes/list/neLink";
    }

    @RequestMapping(value = "/getNodeLink/{id}", method = RequestMethod.POST)
    public String AddNodeLink(ModelMap mm, @ModelAttribute(value = "model") NeLinkForm model, RedirectAttributes attr, Locale locale,
            HttpServletRequest request) throws IOException {
        NodesFacade facade = new NodesFacade();
        if (facade.addNeLink(model) > 0) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } else {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "redirect:/nodes/getNodeLink/" + model.getId1();
    }

    @RequestMapping(value = "/removeNodeLink/{id}/{id1}", method = RequestMethod.GET)
    public String removeNodeLink(ModelMap mm, @PathVariable(value = "id") String id, @PathVariable(value = "id1") String id1, RedirectAttributes attr, Locale locale,
            HttpServletRequest request) throws IOException {
        NodesFacade facade = new NodesFacade();
        if (facade.removeNeLink(id) > 0) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } else {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "redirect:/nodes/getNodeLink/" + id1;
    }

    @ModelAttribute("loaitruyendanList")
    public List findAllLoaiTruyenDan() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTruyenDan("");
    }

    @RequestMapping(value = "/blackpoint", method = RequestMethod.GET)
    public String blackPoint(
            ModelMap mm, HttpServletRequest request) {
        mm.put("list", BlackPointFacade.fc_find_all());
        return LIST_BLACK_POINT;
    }

    @RequestMapping(value = "/getSiteBuilding/{id}/{type}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getSiteBuilding(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type, ModelMap mm,
            HttpServletRequest request) throws IOException {
        Map referenceData = new HashMap();
        NodesFacade facade = new NodesFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(facade.findSiteByBuilding(id, type));
    }

    @RequestMapping(value = "/baoduong", method = RequestMethod.GET)
    public String baoDuong(
            @ModelAttribute("cellNewForm") CellNewForm cellNewForm,
            ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "node_code", required = false) String node_code) {
        CellNewExcelBO cellNewExcelBO = new CellNewExcelBO();
        mm.put("cellNewExcelBO", cellNewExcelBO);
        mm.put("node_code", node_code);
        return "nodes/tram/baoDuongExcel";
    }

    @RequestMapping(value = "/baoduong/update", method = RequestMethod.POST)
    public String updateBaoDuong(ModelMap mm, @ModelAttribute(value = "cellNewExcelBO") CellNewExcelBO cellNewExcelBO,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {
        List<BaoDuongNetExcel> items = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(StringUtils.getFolderTemp() + File.separator + cellNewExcelBO.getFile().getOriginalFilename());
            cellNewExcelBO.getFile().transferTo(convFile);

            items = new ArrayList<>();
            try {
                items = ExOM.mapFromExcel(convFile)
                        .to(BaoDuongNetExcel.class)
                        .mapSheet(0, 2);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            CellsFacade cellsFacade = new CellsFacade();
            List<String> result = new ArrayList<>();
            String temp;
            logger.info("user: {}, ip: {}, call updateBaoDuongNetExcel({})", user.getUsername(), request.getRemoteAddr(), items.size());

            for (int i = 0; i < items.size(); i++) {
                temp = cellsFacade.updateBaoDuongNetExcel(items.get(i), user.getId());
                result.add(temp);
            }
            logger.info("user: {}, ip: {}, done updateBaoDuongNetExcel:", user.getUsername(), request.getRemoteAddr(), result.size());

            logger.info("user: {}, ip: {}, call writeExcelBaoDuong {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            writeResult(convFile, result, StringUtils.getFolderTemp() + File.separator + "result_update_bao_duong_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx", response);
            logger.info("user: {}, ip: {}, end writeExcelBaoDuong {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());

        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.contains("java.io.FileNotFoundException")) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "File import không tồn tại"));
                } else {
                    logger.error(e.getMessage(), e);
                }
            } else {
                logger.error(e.getMessage(), e);
            }
        } finally {
            items = null;
        }
        return "redirect:/nodes/baoduong";
    }

    public File writeResult(File inputFile, List<String> temp, String filePath, HttpServletResponse response) {
        try {
            XSSFWorkbook workbook;
            try (FileInputStream fin = new FileInputStream(inputFile)) {
                workbook = new XSSFWorkbook(fin);
                Sheet sheet = workbook.getSheetAt(0);
                Cell cell;
                Row row;
                int rowIndex = 2;
                for (int i = 0; i < temp.size(); i++) {
                    row = sheet.getRow(rowIndex++);
                    if (row != null) {
                        CellStyle style = sheet.getWorkbook().createCellStyle();
                        row.setRowStyle(style);
                        cell = row.createCell(0);
                        cell.setCellValue(temp.get(i));
                    }
                }
            }
            File file = new File(filePath);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            workbook.close();
            if (file.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(file)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            return file;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
//    trunglk_start_search_new

    @RequestMapping(value = "/fillAttrObject/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String fillAttrObject(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        NodesFacade facade = new NodesFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(facade.findFilterMap(id));
    }

//    trunglk_end_search_new
    @RequestMapping(value = "/searchBaoDuong", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String searchBaoDuong(HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {},mem: {}, search", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            String[] tinhManagerArr = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhManagers = String.join(",", tinhManagerArr);
            logger.debug("tinhManagers: {}", tinhManagers);
            int draw = -1;
            String prs_start_record = "";
            String prs_length_page = "";
            String prs_global_search = "";
            String prs_list_column_name = "";
            String prs_list_column_search = "";
            String prs_column_to_sort = "";
            String param_sort_column = "";
            String prs_sort_direction = "";
            String pList = "";
            ArrayList<String> ar_name = new ArrayList<>();
            ArrayList<String> ar_search_value = new ArrayList<>();

            String columnPermisson = "";
            // duyệt tất cả các tham số truyền vào request
            for (Enumeration items = request.getParameterNames(); items.hasMoreElements();) {
                String param_name = (String) items.nextElement();
                String param_value = request.getParameter(param_name);
                if (param_name.equals("draw")) {
                    draw = Integer.parseInt(param_value);
                } else if (param_name.startsWith("order") && param_name.contains("column")) {
                    param_sort_column = param_value;
                    prs_column_to_sort = param_value;
                } else if (param_name.startsWith("order") && param_name.contains("dir")) {
                    prs_sort_direction = param_value;
                } else if (param_name.equals("start")) {
                    prs_start_record = String.valueOf(Integer.parseInt(param_value) + 1);
                } else if (param_name.equals("length")) {
                    prs_length_page = param_value;
                } else if (param_name.equals("search[value]")) {
                    prs_global_search = param_value;
                } else if (param_name.startsWith("columns") && param_name.contains("name")) {
                    ar_name.add(param_value);
                } else if (param_name.startsWith("columns") && param_name.contains("search") && param_name.contains("value")) {
                    ar_search_value.add(param_value);
                }
                pList += param_name + "=" + param_value + ",";
            }
            //bổ sung phân quyền vào điều kiện tìm kiếm
            Integer index = ar_name.indexOf("tinhtp_id");
            if (index != null && !ar_search_value.isEmpty() && index >= 0) {
                String temp = ar_search_value.get(index);
                if (temp == null || temp.isEmpty()) {
                    ar_search_value.set(index, tinhManagers);
                }
            }
            //            
            int total_column = ar_name.size();
            for (int i = 0; i < total_column; i++) {
                String name = ar_name.get(i);
                String value = ar_search_value.get(i);
                // update tên cột sorting
                if (!param_sort_column.equals("0")) {
                    if (Integer.parseInt(param_sort_column) == i) {
                        prs_column_to_sort = ar_name.get(i);
                    }
                }
                // check xem cột đó có giá trị cần search ko
                if (!value.isEmpty()) {
                    prs_list_column_name = prs_list_column_name + ";" + name;
                    prs_list_column_search = prs_list_column_search + ";" + value;
                }
            }
            String[] recordsTotal = new String[1];
            String[] recordsFiltered = new String[1];
            ArrayList<BaoDuongNetExcel> list = null;
            CellsFacade cellsFacade = new CellsFacade();
            try {
                logger.debug("prs_start_record :{}, prs_length_page:{}, prs_global_search:{}, prs_list_column_name:{}, prs_list_column_search:{}, prs_column_to_sort:{}, prs_sort_direction:{} ",
                        prs_start_record, prs_length_page, prs_global_search, prs_list_column_name, prs_list_column_search, prs_column_to_sort, prs_sort_direction);
                list = cellsFacade.searchBaoDuong(prs_start_record, prs_length_page,
                        prs_global_search, prs_list_column_name, prs_list_column_search, prs_column_to_sort,
                        prs_sort_direction, recordsTotal, recordsFiltered);
                logger.info("user: {}, ip: {},mem: {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            } catch (DAOException ex) {
                logger.error(ex.getMessage(), ex);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            int count = 0;
            // chuyển list  thành list String
            List<List<String>> data = new ArrayList();
            if (list != null) {
                for (BaoDuongNetExcel item : list) {
                    ArrayList<String> ls = new ArrayList();
                    // thứ tự phải khớp với thứ tự của các cột trong table của trang jsp
                    count++;
                    ls.add(count + "");
                    ls.add(item.getArea());
                    ls.add(item.getProvinceId());
                    ls.add(item.getProvinceName());
                    ls.add(item.getCode() == null ? "" : item.getCode());
                    ls.add(item.getNeType() == null ? "" : item.getNeType());
                    ls.add(item.getNgayBaoDuong() == null ? "" : item.getNgayBaoDuong());
                    ls.add(item.getDonvi() == null ? "" : item.getDonvi());
                    ls.add(item.getNote() == null ? "" : item.getNote());
                    ls.add(item.getNeTypeId() == null ? "" : item.getNeTypeId());
                    ls.add(item.getBaoDuongId() == null ? "" : item.getBaoDuongId());
                    ls.add(item.getNodeId() == null ? "" : item.getNodeId());
                    data.add(ls);
                }
            }
            // chuyển thành object json
            ContentDataTableItem responseObj = new ContentDataTableItem(draw, recordsTotal[0], recordsFiltered[0], data);
            Gson gson = new Gson();
            logger.info("user: {}, ip: {},mem: {} end", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            return gson.toJson(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    @RequestMapping(value = "/exportBaoDuong", method = RequestMethod.GET)
    public void exportBaoDuong(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpIds,
            @RequestParam(value = "khuvucId", required = false) String khuvucIds,
            @RequestParam(value = "nodeCode", required = false) String nodeCode,
            @RequestParam(value = "neType", required = false) String neType) {
        List<BaoDuongNetExcel> data = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhManagers = String.join(",", tinhManager);

            if (khuvucIds != null && khuvucIds.equalsIgnoreCase("null")) {
                khuvucIds = null;
            }
            if (tinhTpIds != null && tinhTpIds.equalsIgnoreCase("null")) {
                tinhTpIds = null;
            }
            //neu user search theo tinh tp thi search theo gia tri nguoi dung chon
            if (tinhTpIds != null && !tinhTpIds.isEmpty()) {
                tinhManagers = tinhTpIds;
            }
            logger.info("user: {}, ip: {},mem: {} function 'exportExcel' nodeCode:{}, neType:{}, khuVucIds:{}, tinhTpIds:{}, permisson:{}",
                    user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), nodeCode, neType, khuvucIds, tinhTpIds, tinhManagers);
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            CellsFacade cellsFacade = new CellsFacade();
            data = cellsFacade.exportBaoDuong(khuvucIds, tinhManagers, nodeCode, neType);
            File fileResult = writeExportExcel(data);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
            logger.info("user: {}, ip: {},mem: {}, function 'exportExcel' end", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            data = null;
        }
    }

    private File writeExportExcel(List<BaoDuongNetExcel> datas) {
        File result = null;
        FileOutputStream fos = null;
        SXSSFWorkbook workbook = null;
        try {
            workbook = new SXSSFWorkbook(1000);
            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Sheet sheet = workbook.createSheet("data");
            if (datas != null) {
                int rowIndex = 1;
                Cell cell = null;
                Row row = null;
                //tạo header
                row = sheet.createRow(0);
                cell = row.createCell(0);
                cell.setCellValue("Khu vực");
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue("Tỉnh/TP");
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue("Mã trạm");
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue("Loại trạm");
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue("Ngày bảo dưỡng");
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue("Đơn vị thực hiện");
                cell.setCellStyle(style);
                cell = row.createCell(6);
                cell.setCellValue("Ghi chú");
                cell.setCellStyle(style);
                //
                for (BaoDuongNetExcel it : datas) {
                    row = sheet.createRow(rowIndex++);
                    cell = row.createCell(0);
                    cell.setCellValue(it.getArea() == null ? "" : it.getArea());
                    cell.setCellStyle(style);
                    cell = row.createCell(1);
                    cell.setCellValue(it.getProvinceName() == null ? "" : it.getProvinceName());
                    cell.setCellStyle(style);
                    cell = row.createCell(2);
                    cell.setCellValue(it.getCode() == null ? "" : it.getCode());
                    cell.setCellStyle(style);
                    cell = row.createCell(3);
                    cell.setCellValue(it.getNeType() == null ? "" : it.getNeType());
                    cell.setCellStyle(style);
                    cell = row.createCell(4);
                    cell.setCellValue(it.getNgayBaoDuong() == null ? "" : it.getNgayBaoDuong());
                    cell.setCellStyle(style);
                    cell = row.createCell(5);
                    cell.setCellValue(it.getDonvi() == null ? "" : it.getDonvi());
                    cell.setCellStyle(style);
                    cell = row.createCell(6);
                    cell.setCellValue(it.getNote() == null ? "" : it.getNote());
                    cell.setCellStyle(style);
                }
            }

            String pathFile = StringUtils.getFolderTemp() + File.separator + "BaoDuong_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
            result = new File(pathFile);
            fos = new FileOutputStream(result);
            workbook.write(fos);
            if (SXSSFWorkbook.class.equals(workbook.getClass())) {
                SXSSFWorkbook wb = (SXSSFWorkbook) workbook;
                wb.dispose();
            }
            fos.flush();
            fos.close();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }

        }

        return result;
    }

}
