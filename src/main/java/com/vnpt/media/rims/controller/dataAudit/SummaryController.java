package com.vnpt.media.rims.controller.dataAudit;

import com.vnpt.media.rims.bean.AuditSummaryBO;
import com.vnpt.media.rims.bean.BuildingBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DAuditFacade;
import com.vnpt.media.rims.facade.DataAuditFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.ApproveForm;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/dataAuditSummary")
public class SummaryController extends BaseController {

    private static Logger logger = LogManager.getLogger(SummaryController.class);

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, HttpServletRequest request) {
        DAuditFacade facade = new DAuditFacade();

        List<AuditSummaryBO> lst = facade.getSummary();
        for (int i = 0; i < lst.size(); i++) {
            
        }
        mm.put("list_audit_summary", lst);
        return "dataAudit/audit/auditList";
    }

    @RequestMapping(value = "/detail/{name}/{type}", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "name") String name, @PathVariable(value = "type") String type,
            @RequestParam(value = "typeSearch", required = false) String typeSearch,
            @RequestParam(value = "tenTrenHeThong", required = false) String tenTrenHeThong,
            @RequestParam(value = "page", required = false) String page, Locale locale, ModelMap mm, HttpServletRequest request) {
        logger.info("Action detail audit");
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinh = String.join(",", tinhManager);
        DAuditFacade facade = new DAuditFacade();
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        tenTrenHeThong = tenTrenHeThong == null ? "" : tenTrenHeThong;
        if (typeSearch != null) {
            type = typeSearch;
        } else {
            typeSearch = type;
        }
        Long id = -1L;
        
        int totalRows = facade.getTotalComInfo(id, type, name, tenTrenHeThong,tinh);
        mm.put("typeSearch", typeSearch == null ? -1 : typeSearch);
        mm.put("type", type);
        mm.put("name", name);
        mm.put("tenTrenHeThong", tenTrenHeThong);
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
            return ("redirect:/detail/" + name + "/" + type + "?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/detail/" + name + "/" + type + "?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
//        objPage.setSubject("Quản lý Nodes");
        mm.addAttribute("pageInfo", objPage);
        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        mm.put("startRow", startRow);
        mm.put("approveForm", new ApproveForm());
        List<?> lst = new ArrayList<>();
        
        if (name.equals("cell2g_info")) {
            lst = facade.findAllComCell2gInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong,tinh);
            mm.put("list_audit_detail", lst);
            return "dataAudit/audit/auditCell2gList";
        }
        if (name.equals("cell3g_info")) {
            lst = facade.findAllComCell3gInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong,tinh);
            mm.put("list_audit_detail", lst);
            return "dataAudit/audit/auditCell3gList";
        }
        if (name.equals("cell4g_info")) {
            lst = facade.findAllComCell4gInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong, tinh);
            mm.put("list_audit_detail", lst);
            return "dataAudit/audit/auditCell4gList";
        } else if (name.equals("bts_info")) {
            lst = facade.findAllComBtsInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong, tinh);
            mm.put("list_audit_detail", lst);
            return "dataAudit/audit/auditBtsList";
        } else if (name.equals("nodeb_info")) {
            lst = facade.findAllComNodeBInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong, tinh);
            
            mm.put("list_audit_detail", lst);
            return "dataAudit/audit/auditNodeBList";
        } else if (name.equals("enodeb_info")) {
            lst = facade.findAllComEnodeBInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong, tinh);
            mm.put("list_audit_detail", lst);
            return "dataAudit/audit/auditENodeBList";
        } else {
//            lst = facade.findAllComCellInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), null);
//            mm.put("list_audit_detail", lst);
            return "dataAudit/audit/auditCellList";
        }

    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public String approve(@ModelAttribute(value = "approveForm") ApproveForm approveForm,
            @RequestParam(value = "idAudit", required = false) String idAudit,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) String type,
            ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        DAuditFacade facade = new DAuditFacade();
        
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        facade.offNode(name, idAudit, approveForm, user.getId());
        return "redirect:/dataAuditSummary/detail/" + name + "/" + type;

    }

    @RequestMapping(value = "/update/{name}/{type}/{idAudit}/{status}", method = RequestMethod.GET)
    public String update(@PathVariable(value = "name") String name, @PathVariable(value = "type") String type,
            @PathVariable(value = "idAudit") String idAudit,
            @PathVariable(value = "status") String status,
            @RequestParam(value = "page", required = false) String page, Locale locale, ModelMap mm, HttpServletRequest request) {
        DAuditFacade facade = new DAuditFacade();
        facade.updateStatus(name, idAudit, Integer.valueOf(status));
        return "redirect:/dataAuditSummary/detail/" + name + "/" + type;

    }

//    @RequestMapping(value = "/popup/{name}/{type}", method = RequestMethod.GET)
//    public String popup(@PathVariable(value = "name") String name, @PathVariable(value = "type") String type,
//            @RequestParam(value = "tenTrenHeThong", required = false) String tenTrenHeThong,
//            @RequestParam(value = "page", required = false) String page, Locale locale, ModelMap mm, HttpServletRequest request) {
//        logger.info("Action detail audit");
//        DAuditFacade facade = new DAuditFacade();
//        page = page == null ? "1" : page;
//        name = name == null ? "" : name;
//        Integer pageInt = Integer.parseInt(page);
//        tenTrenHeThong = tenTrenHeThong == null ? "" : tenTrenHeThong;
//        Long id = -1L;
//        int totalRows = facade.getTotalComInfo(id, type, name, tenTrenHeThong);
//        mm.put("type", type);
//        mm.put("name", name);
//        Page objPage = new Page();
//
//        int numPerPage = Constants.NUMBER_FOR_PAGING;
//        int totalPages = 0;
//
//        if (totalRows % numPerPage == 0) {
//            totalPages = (int) totalRows / numPerPage;
//        } else {
//            totalPages = (int) totalRows / numPerPage + 1;
//        }
//        if (totalRows == 0) {
//            totalPages = 0;
//        }
//
//        if (pageInt < 1) {
//            pageInt = 1;
//            return ("redirect:/popup/" + name + "/" + type + "?page=" + pageInt);
//        } else if (pageInt > totalPages && totalPages > 0) {
//            pageInt = totalPages;
//            return ("redirect:/popup/" + name + "/" + type + "?page=" + pageInt);
//        }
//
//        objPage.setTotalPages(totalPages);
//        objPage.setTotalRows(totalRows);
//        objPage.setDestPage(pageInt);
//        objPage.setDirection(1);
////        objPage.setSubject("Quản lý Nodes");
//        mm.addAttribute("pageInfo", objPage);
//        int startRow = 0, endRow = 0;
//        if (pageInt > 1) {
//            startRow = ((pageInt - 1) * (numPerPage) + 1);
//            endRow = (pageInt * (numPerPage));
//        } else if (pageInt == 1) {
//            startRow = 1;
//            endRow = Constants.NUMBER_FOR_PAGING;
//        }
//        mm.put("startRow", startRow);
//        mm.put("approveForm", new ApproveForm());
//        List<?> lst = new ArrayList<>();
//
//        if (name.equals("cell_info")) {
////            lst = facade.findAllComCellInfo(id,type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong);
////            mm.put("list_audit_detail", lst);
//            return "dataAudit/popup/omccell2g";
//        } else if (name.equals("bts_info")) {
//            lst = facade.findAllComBtsInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong);
//            mm.put("list_audit_detail", lst);
//            return "dataAudit/popup/popupauditBtsList";
//        } else if (name.equals("nodeb_info")) {
//            lst = facade.findAllComNodeBInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong);
//            
//            mm.put("list_audit_detail", lst);
//            return "dataAudit/popup/omcnodeb";
//        } else if (name.equals("enodeb_info")) {
//            lst = facade.findAllComEnodeBInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong);
//            mm.put("list_audit_detail", lst);
//            return "dataAudit/popup/omcenodeb";
//        } else {
////            lst = facade.findAllComCellInfo(id,type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong);
////            mm.put("list_audit_detail", lst);
//            return "dataAudit/popup/omcenodeb";
//        }
//
//    }
 @RequestMapping(value = "/popup/{name}/{type}", method = RequestMethod.GET)
    public String popup(@PathVariable(value = "name") String name, @PathVariable(value = "type") String type,
            @RequestParam(value = "tenTrenHeThong", required = false) String tenTrenHeThong,
            @RequestParam(value = "page", required = false) String page, Locale locale, ModelMap mm, HttpServletRequest request) {
        logger.info("Action detail audit");
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinh = String.join(",", tinhManager);
        DAuditFacade facade = new DAuditFacade();
        page = page == null ? "1" : page;
        name = name == null ? "" : name;
        Integer pageInt = Integer.parseInt(page);
        tenTrenHeThong = tenTrenHeThong == null ? "" : tenTrenHeThong;
        Long id = -1L;
        int totalRows = facade.getTotalComInfo(id, type, name, tenTrenHeThong,tinh);
        mm.put("type", type);
        mm.put("name", name);
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
            return ("redirect:/popup/" + name + "/" + type + "?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/popup/" + name + "/" + type + "?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
//        objPage.setSubject("Quản lý Nodes");
        mm.addAttribute("pageInfo", objPage);
        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        mm.put("startRow", startRow);
        mm.put("approveForm", new ApproveForm());
        List<?> lst = new ArrayList<>();

        if (name.equals("cell_info")) {
//            lst = facade.findAllComCellInfo(id,type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong);
//            mm.put("list_audit_detail", lst);
            return "dataAudit/popup/omccell2g";
        } else if (name.equals("bts_info")) {
            lst = facade.findAllComBtsInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong, tinh);
            mm.put("list_audit_detail", lst);
            return "dataAudit/popup/popupauditBtsList";
        } else if (name.equals("nodeb_info")) {
            lst = facade.findAllComNodeBInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong, tinh);
            
            mm.put("list_audit_detail", lst);
            return "dataAudit/popup/omcnodeb";
        } else if (name.equals("enodeb_info")) {
            lst = facade.findAllComEnodeBInfo(id, type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong, tinh);
            mm.put("list_audit_detail", lst);
            return "dataAudit/popup/omcenodeb";
        } else {
//            lst = facade.findAllComCellInfo(id,type, String.valueOf(startRow), String.valueOf(endRow), tenTrenHeThong);
//            mm.put("list_audit_detail", lst);
            return "dataAudit/popup/omcenodeb";
        }

    }
    @RequestMapping(value = "/swap", method = RequestMethod.POST)
    public String swap(
            @RequestParam(value = "auditId", required = false) String auditId,
            @RequestParam(value = "rNodeId", required = false) String rNodeId,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "name", required = false) String name,
            ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        DAuditFacade facade = new DAuditFacade();
//        
//        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//        facade.offNode(name, idAudit, approveForm, user.getId());
        
        
        
        
        
//        
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

        facade.swap(rNodeId, auditId, name, user.getId());

        return "redirect:/dataAuditSummary/detail/" + name + "/" + type;

    }

}
