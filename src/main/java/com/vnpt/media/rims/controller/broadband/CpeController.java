/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.CpeBO;
import com.vnpt.media.rims.bean.DongTbiBO;
import com.vnpt.media.rims.bean.ManEInfoBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.facade.CpeFacade;
import com.vnpt.media.rims.facade.DongTbiFacade;
import com.vnpt.media.rims.facade.ManeFacade;
import java.util.ArrayList;
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

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/cpe")
public class CpeController {

    private static Logger logger = LogManager.getLogger(CpeController.class);
    private static final String FORM_CPE = "broadband/cpe/formCpe";
    private static final String LIST = "broadband/cpe/list";
    private static final String REDIRECT = "redirect:/cpe/init";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("dongTbis")
    public List findDongTbi() {
        ArrayList<DongTbiBO> list = null;
        try {
            list = DongTbiFacade.fc_find_all("", "ACCESS");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "typeId", required = false) String typeId,
            ModelMap mm, HttpServletRequest request) {
        try {
            typeId = typeId == null ? "12" : typeId;
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);

            int totalRows = CpeFacade.getInstance().getTotal("", code, name, typeId);

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
                return ("redirect:/cpe/init?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/cpe/init?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            objPage.setSubject("Quản lý cpe");
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }

            List<CpeBO> list = (List<CpeBO>) CpeFacade.getInstance().findAll(startRow, endRow, "", code, name, typeId);
            mm.addAttribute("list", list);

            mm.put("typeId", typeId);
            mm.put("code", code);
            mm.put("name", name);
            mm.put("startRow", startRow);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }

    @RequestMapping(value = "/preAdd/{typeId}", method = RequestMethod.GET)
    public String preAdd(@PathVariable(value = "typeId") String typeId, ModelMap mm) {
        try {
            mm.put("btnName", "Thêm mới");
            CpeBO cpe = new CpeBO();
            mm.addAttribute("cpe", cpe);
            return FORM_CPE;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/preUpdate/{id}/{typeId}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") String id,
            @PathVariable(value = "typeId") String typeId, ModelMap mm) {
        try {
            mm.put("btnName", "Cập nhật");
//        mm.addAttribute("mane", mane);

            List<CpeBO> list = (List<CpeBO>) CpeFacade.getInstance().findAll(0, 10, id, "", "", typeId);
            List<ManEInfoBO> listParent = null;
            CpeBO cpe = list.get(0);
            if (cpe.getChaId() != null && !cpe.getChaId().isEmpty()) {
                listParent = ManeFacade.findChaList(String.valueOf(cpe.getChaId()));
            }
            mm.addAttribute("cpe", cpe);
            mm.addAttribute("list", listParent);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM_CPE;

    }

    @RequestMapping(value = "/updateDsLam", method = RequestMethod.POST)
    public String update(ModelMap mm, @ModelAttribute(value = "cpe") CpeBO cpe,
            RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("cpe", cpe);
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            if (cpe.getId() == null || cpe.getId().trim().equals("")) {
                if (CpeFacade.getInstance().addCpe(cpe, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_ADD_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_ADD_ERROR));
                }

            } else {
                if (CpeFacade.getInstance().updateCpe(cpe, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_UPDATE_ERROR));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REDIRECT;
    }

    @RequestMapping(value = "/delete/{id}/{typeId}", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, ModelMap mm, @PathVariable(value = "id") String id,
            @PathVariable(value = "typeId") String typeId,
            Locale locale, RedirectAttributes attr) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//            AccessFacade.getInstance().delete(id, typeId, user.getId());
            ManeFacade.delete(id, String.valueOf(user.getId()));
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_DELETE_SUCCESS));
        } catch (Exception e) {
            logger.error(e, e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_DELETE_ERROR));
        }
        return REDIRECT;
    }
}
