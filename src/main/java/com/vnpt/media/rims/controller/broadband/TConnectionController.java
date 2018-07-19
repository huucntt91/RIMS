/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.controller.core.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.TConnectionFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/tConnection")
public class TConnectionController {

    private static Logger logger = LogManager.getLogger(TConnectionController.class);

    private static final String LIST = "broadband/tConnection/list";
    private static final String VIEW = "broadband/tConnection/edit";
    private static final String POPUP = "broadband/tConnection/popup";
    
    
    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tConnectionName", required = false) String name,
            HttpServletRequest request) {
        logger.info("Action init tConnection");
        name = name == null ? "" : name;
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        TConnectionFacade facade = new TConnectionFacade();
        int totalRows = facade.getTotalTConnectionInfo(name, "");
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
            return ("redirect:/tConnection/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/tConnection/init?page=" + pageInt);
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
        List<TConnectionInfoBO> lst = facade.findAllTConnectionInfo(startRow,endRow, name, "");
        mm.put("startRow", startRow);
        
        mm.put("list", lst);
        mm.put("tConnectionName", name);

        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm)  {
        TConnectionInfoBO item = new TConnectionInfoBO();
        mm.addAttribute("item", item);
        return VIEW;
    }

    @RequestMapping(value = "/view/{tConnectionId}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "tConnectionId") String tConnectionId,
            Locale locale, RedirectAttributes attr, ModelMap mm)  {
        try {
            if (StringUtils.hasText(tConnectionId)) {
                TConnectionFacade facade = new TConnectionFacade();
                List<TConnectionInfoBO> cpBO = facade.findAllTConnectionInfo(1, 10, null, tConnectionId);
                mm.put("item", cpBO.get(0));
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return VIEW;
    }
    
    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String popup(ModelMap mm,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tConnectionName", required = false) String name,
            HttpServletRequest request) {
        logger.info("Action init tConnection");
        name = name == null ? "" : name;
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        TConnectionFacade facade = new TConnectionFacade();
        int totalRows = facade.getTotalTConnectionInfo(name, "");
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
            return ("redirect:/tConnection/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/tConnection/init?page=" + pageInt);
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
        List<TConnectionInfoBO> lst = facade.findAllTConnectionInfo(startRow,endRow, name, "");
        mm.put("startRow", startRow);
        
        mm.put("list", lst);
        mm.put("tConnectionName", name);

        return POPUP;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "item") TConnectionInfoBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request)  {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                mm.addAttribute("item", item);
                return "redirect:/tConnection/preAdd";
            }
            TConnectionFacade coreFacade = new TConnectionFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            coreFacade.addTConnectionInfo(item, user.getId());
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {

            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/tConnection/preAdd";
        }

        return "redirect:/tConnection/init";

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "item") TConnectionInfoBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request)  {
        logger.info("update DonVi Action");
        Locale locale = LocaleContextHolder.getLocale();
        try {
            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
                        break;
                    } else {
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                    }
                }
                mm.addAttribute("item", item);
                return "redirect:/tConnection/view/" + item.gettConnectionId();
            }
            TConnectionFacade coreFacade = new TConnectionFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            coreFacade.updateTConnectionInfo(item, user.getId());
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/tConnection/view/" + item.gettConnectionId();
        }
        return "redirect:/tConnection/init";
    }

    @RequestMapping(value = "/delete/{nodeId}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr, HttpServletRequest request)  {

        logger.debug("Delete Don Vi Action");
        try {
            TConnectionFacade coreFacade = new TConnectionFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            List<TConnectionInfoBO> tConnectionInfo = coreFacade.findAllTConnectionInfo(1, 10, "", nodeId);
            if (tConnectionInfo.size() > 0) {
                coreFacade.deleteTConnectionInfo(Long.valueOf(nodeId), user.getId());
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/tConnection/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/tConnection/init";
            }
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("PKG_DONVI") != -1) { // 
                    String msg = messageSource.getMessage("admin.excute.sql.violated.FK_USER_ID", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/tConnection/init";
                }
            }
            logger.error("Exception :", e);

            return "redirect:/tConnection/init";
        }

    }
    
}
