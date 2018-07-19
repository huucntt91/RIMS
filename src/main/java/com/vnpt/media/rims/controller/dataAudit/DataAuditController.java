/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.dataAudit;

import com.vnpt.media.rims.bean.*;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.PagingUtils;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DataAuditFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.DataAuditForm;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/dataAudit")
public class DataAuditController {

    private static Logger logger = LogManager.getLogger(DataAuditController.class);
    private static final String LIST = "dataAudit/listConfig/listConfig";

    @Autowired
    private MessageSource messageSource;

    protected String getBaseURL(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
        return baseURL;
    }

    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllKhuVuc(String.join(",", tinhManager));
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
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "neTypeId", required = false) String neTypeId, @RequestParam(value = "thietBiId", required = false) String thietBiId, @RequestParam(value = "tinhTpId", required = false) String tinhTpId, @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, @RequestParam(value = "phuongXaId", required = false) String phuongXaId, @RequestParam(value = "status", required = false) String status,
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
        DataAuditFacade facade = new DataAuditFacade();

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
        mm.put("neTypeId", neTypeId);
        mm.put("thietBiId", thietBiId);
        mm.put("startRow", startRow);
        mm.put("status", status);
        mm.put("khuvucId", khuvucId);
        mm.put("approveForm", new ApproveForm());
        CategoriesFacade cateFacade = new CategoriesFacade();
        mm.put("huyenList", cateFacade.findAllHuyen(tinhTpId));
//        mm.put("phuongXaList", cateFacade.findAllPhuongXa(quanHuyenId));
        return LIST;
    }

    @RequestMapping(value = "/getLstConfig/{id}", method = RequestMethod.GET)
    public String getNodeLink(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        DataAuditFacade facade = new DataAuditFacade();
        List<DataAuditForm> list = facade.findNodeLink(id);
        mm.put("list", list);
        mm.put("nodeId", id);
        return "dataAudit/listConfig/grantAudit";
    }

    @RequestMapping(value = "/getLstConfig/{id}", method = RequestMethod.POST)
    public String AddNodeLink(ModelMap mm, @ModelAttribute(value = "model") DataAuditForm model, RedirectAttributes attr, Locale locale,
            HttpServletRequest request) throws IOException {
        DataAuditFacade facade = new DataAuditFacade();
        if (facade.addUserConf(model) > 0) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } else {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "redirect:/dataAudit/getLstConfig/" + model.getNodeId();
    }

    @RequestMapping(value = "/removeUserNode/{id}/{id1}", method = RequestMethod.GET)
    public String removeUserNode(ModelMap mm, @PathVariable(value = "id") String id, @PathVariable(value = "id1") String id1, RedirectAttributes attr, Locale locale,
            HttpServletRequest request) throws IOException {
        DataAuditFacade facade = new DataAuditFacade();
        if (facade.removeUserNode(id) > 0) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } else {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "redirect:/dataAudit/getLstConfig/" + id1;
    }

    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, HttpServletRequest request) {
        logger.info("Action popup user");

        ManagerAdminFacade facade = new ManagerAdminFacade();
        int startRow = 0, endRow = 0, totalRecordPerPageList = Constants.NUMBER_FOR_PAGING, currentPage = 1;
        int totalRecords = facade.getTotalUser(null);
        // tao paging
        try {
            String urlWeb = getBaseURL(request) + "user/paging/null";
            String pagingUrl = PagingUtils.printPaging(urlWeb, totalRecords, Constants.NUMBER_FOR_PAGING, currentPage);
            mm.put("paging", pagingUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (currentPage > 1) {
            startRow = ((currentPage - 1) * (totalRecordPerPageList) + 1);
            endRow = (currentPage * (totalRecordPerPageList));
        } else if (currentPage == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<UserBO> lst = facade.findAllUsers(null, startRow, endRow);
        mm.put("list_user", lst);
        return "dataAudit/listConfig/userList";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("fullname") String fullname, ModelMap mm, HttpServletRequest request) {
        
        fullname = ((fullname == null || fullname.equals("")) ? "null" : fullname);
        ManagerAdminFacade facade = new ManagerAdminFacade();
        logger.info("Action search user");
        int startRow = 0, endRow = 0, totalRecordPerPageList = Constants.NUMBER_FOR_PAGING, currentPage = 1;
        int totalRecords = facade.getTotalUser(fullname);
        if (currentPage > 1) {
            startRow = ((currentPage - 1) * (totalRecordPerPageList) + 1);
            endRow = (currentPage * (totalRecordPerPageList));
        } else if (currentPage == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<UserBO> lst = facade.findAllUsers(fullname, startRow, endRow);

        mm.put("list_user", lst);
        mm.put("name", fullname);
        String paging;
        try {
            String urlWeb = getBaseURL(request) + "user/paging/" + fullname;
            paging = PagingUtils.printPaging(urlWeb, totalRecords, Constants.NUMBER_FOR_PAGING, currentPage);
            mm.put("paging", paging);
        } catch (Exception ex) {
            ex.printStackTrace();
//            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "dataAudit/listConfig/userList";
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
    
}
