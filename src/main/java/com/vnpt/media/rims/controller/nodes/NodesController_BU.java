/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.nodes;

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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.facade.*;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.NeLinkForm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ResponseBody;
import org.codehaus.jackson.map.ObjectMapper;
import com.vnpt.media.rims.formbean.SearchCellForm;
import java.util.ArrayList;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/nodes_BU")
public class NodesController_BU {

    private static Logger logger = LogManager.getLogger(NodesController_BU.class);
    private static final String LIST = "nodes/list/list";
    private static final String FORM = "nodes/node/new";
    private static final String ADDTRAM = "nodes/tram/tramNew";

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
    public String init(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "code", required = false) String code,
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
        int totalPages = 0;

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
        int totalPages = 0;

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
        logger.info("Action init Popup Tram Du An");

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        quanHuyenId = quanHuyenId == null ? "" : quanHuyenId;
        phuongXaId = phuongXaId == null ? "" : phuongXaId;

        code = code == null ? "" : code;
        neTypeId = neTypeId == null ? "" : neTypeId;
        thietBiId = thietBiId == null ? "" : thietBiId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        NodesFacade facade = new NodesFacade();

        String statusList = Constants.NE_APPROVE_ON + "," + Constants.NE_REG_OFF + "," + Constants.NE_UNAPPROVE_OFF;

        int totalRows = facade.getTotalNode(code, khuvucId, tinhTpId, quanHuyenId, phuongXaId, neTypeId, thietBiId, statusList);

        Page objPage = new Page();

        int numPerPage = Constants.NUMBER_FOR_PAGING;
        int totalPages = 0;

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
        List<NodeBO> list = facade.findAllNodeBO(String.valueOf(startRow), String.valueOf(endRow), code, khuvucId, tinhTpId, quanHuyenId, phuongXaId, neTypeId, thietBiId, statusList);
        mm.put("list", list);
        mm.put("phuongXaId", phuongXaId);
        mm.put("tinhTpId", tinhTpId.contains(",") ? "" : tinhTpId);
        mm.put("khuvucId", khuvucId.contains(",") ? "" : khuvucId);

        mm.put("quanHuyenId", quanHuyenId);
        mm.put("neTypeId", neTypeId);
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
        int totalPages = 0;

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

            // check valid form
            if (model.getNodeChaId() == null && model.getNeTypeId() != 8) { // enodeb chua co node cha la bsc ...
                String noWhitespaceAllowed = messageSource.getMessage("tram.new.parentId.notempty", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:" + backUrl;
            }
            if (model.getTramDAId() == null) {
                String noWhitespaceAllowed = messageSource.getMessage("tram.new.tramDuAnId.notempty", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:" + backUrl;
            }

            if (model.getBuildingId() == null) {
                String noWhitespaceAllowed = messageSource.getMessage("tram.new.buildingId.notempty", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:" + backUrl;
            }
            NodesFacade adminFacade = new NodesFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            // end
            int result = 0;
            if (model.getId() != null && model.getId() > 0) {
                model.setStatus(Constants.NE_REG_ON);
                model.setUserUpdate(user.getId());
                result = adminFacade.updateTram(model);

            } else {
                // Get VNPT code

                CategoriesFacade fcate = new CategoriesFacade();
                DonViBO donvi = fcate.findAllDonVi(String.valueOf(model.getDonViId()), "","").get(0);
                String status = "DK";
                String codeVNPT = adminFacade.getCodeVNPT(donvi.getCodeTinhTp(), Convert.convertNeTypeToTechnology(model.getNeTypeId()), status);
                model.setCode(codeVNPT);
                model.setUserInsert(user.getId());
                result = adminFacade.addTram(model);
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute(value = "model") BTSInfoBO model, ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        model.setUserUpdate(user.getId());
        NodesFacade nodesFacade = new NodesFacade();
        if (nodesFacade.updateTram(model) > 0) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
            return "redirect:/nodes/init";
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
        if (neTypeId.equals("2")) {
            // return form BTS 
            return "nodes/edit/tram";
        } else if (neTypeId.equals("3")) {
            // return form nodeB 
            return "nodes/node/nodeb";
        } else if (neTypeId.equals("5")) {
            // return form  cell 2g

            return "nodes/node/cell";
        } else if (neTypeId.equals("6")) {
            // return form  cell 3g 
            return "nodes/node/cell";
        } else if (neTypeId.equals("7")) {
            // return form  cell 4g 
            return "nodes/node/cell";
        } else if (neTypeId.equals("8")) {
            // return form  enodeb
            return "nodes/node/enodeb";
        } else {
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
                if (type.equals("2"))//BTS 
                {
                    List<BTSInfoBO> list = (List<BTSInfoBO>) facade.findDetail(id, type);
                    mm.addAttribute("model", list.get(0));
                    return "nodes/view/bts";
                } else if (type.equals("3"))//NodeB 
                {
                    List<NodeBInfoBO> list = (List<NodeBInfoBO>) facade.findDetail(id, type);
                    mm.addAttribute("model", list.get(0));
                    return "nodes/view/nodeb";
                } else if (type.equals("5") || type.equals("6") || type.equals("7"))//Cell 2g,3g,4g 
                {
                    List<CellInfoBO> list = (List<CellInfoBO>) facade.findDetail(id, type);
                    mm.addAttribute("model", list.get(0));
                    return "nodes/view/cell";
                } else if (type.equals("11"))//BSC/RNC/MBSC
                {
                    List<BSCRNCInfoBO> list = (List<BSCRNCInfoBO>) facade.findDetail(id, type);
                    mm.addAttribute("model", list.get(0));
                    return "nodes/view/bsc_rnc";
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
        NodesFacade nodesFacade = new NodesFacade();
//        nodesFacade.updateCell(cellNewForm);
        String statusList = Constants.NE_REG_ON + "," + Constants.NE_REG_OFF;
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
//        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        List<BTSInfoBO> list = nodesFacade.findDuyetTram(null, statusList, tinhTpId);
        mm.put("list", list);

        return "nodes/tram/tramList";
    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public @ResponseBody
    int approve(@ModelAttribute(value = "approveForm") ApproveForm approveForm, ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        NodesFacade nodesFacade = new NodesFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        return nodesFacade.approveTram(approveForm, user.getId());
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
        String tinhs =  String.join(",", tinhManager);
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllDonVi("", "",tinhs);
    }

//    @RequestMapping(value = "/getNodeLink/{id}", method = RequestMethod.GET,
//            produces = "application/json;charset=utf-8")
//    public @ResponseBody
//    String getNodeLink(@PathVariable(value = "id") String id, ModelMap mm,
//            HttpServletRequest request) throws IOException {
//        Map referenceData = new HashMap();
//        NodesFacade facade = new NodesFacade();
//        ObjectMapper mapper = new ObjectMapper();
//        //Object to JSON in String
//        return mapper.writeValueAsString(facade.findNodeLink(id));
//    }
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

}
