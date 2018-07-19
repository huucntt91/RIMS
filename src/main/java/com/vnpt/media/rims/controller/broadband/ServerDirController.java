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
import com.vnpt.media.rims.facade.ServerDirFacade;
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
@RequestMapping(value = "/serverDir")
public class ServerDirController {

    private static Logger logger = LogManager.getLogger(ServerDirController.class);

    private static final String LIST = "broadband/dmMayChu/list";
    private static final String VIEW = "broadband/dmMayChu/edit";
    private static final String POPUP = "broadband/dmMayChu/popup";
    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "name", required = false) String name,
            HttpServletRequest request) {
        logger.info("Action init serverDir");
        name = name == null ? "" : name;
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        ServerDirFacade facade = new ServerDirFacade();
        int totalRows = facade.getTotalServerInfo(name, "");
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
            return ("redirect:/serverDir/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/serverDir/init?page=" + pageInt);
        }
        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Danh mục máy chủ");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<ServerDirBO> lst = facade.findAllServerInfo(startRow, endRow, name, "");
        mm.put("startRow", startRow);
        mm.put("list", lst);
        mm.put("name", name);

        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm)  {
        ServerDirBO item = new ServerDirBO();
        mm.addAttribute("item", item);
        return VIEW;
    }
    
//    @RequestMapping(value = "/popup/{nodeId}", method = RequestMethod.GET)
//    public String popup(@PathVariable(value = "nodeId") String nodeId,
//            Locale locale, RedirectAttributes attr, ModelMap mm)  {
//        view(nodeId,locale,attr,mm);
//        return POPUP;
//    }
    
    @RequestMapping(value = "/view/{nodeId}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr, ModelMap mm)  {
        try {
            if (StringUtils.hasText(nodeId)) {
                ServerDirFacade facade = new ServerDirFacade();
                List<ServerDirBO> cpBO = facade.findAllServerInfo(1, 10, null, nodeId);
                mm.put("item", cpBO.get(0));
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return VIEW;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "item") ServerDirBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request)  {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                mm.addAttribute("item", item);
                return "redirect:/serverDir/preAdd";
            }
            ServerDirFacade coreFacade = new ServerDirFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            coreFacade.addServerInfo(item, user.getId());
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {

            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/serverDir/preAdd";
        }

        return "redirect:/serverDir/init";

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "item") ServerDirBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request)  {
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
                return "redirect:/serverDir/view/" + item.getId();
            }
            ServerDirFacade coreFacade = new ServerDirFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            coreFacade.updateServerInfo(item, user.getId());
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/serverDir/view/" + item.getId();
        }
        return "redirect:/serverDir/init";
    }

    @RequestMapping(value = "/delete/{nodeId}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr, HttpServletRequest request)  {

        logger.debug("Delete Don Vi Action");
        try {
            ServerDirFacade coreFacade = new ServerDirFacade();
            List<ServerDirBO> serverDirInfo = coreFacade.findAllServerInfo(1, 10, "", nodeId);
            if (serverDirInfo.size() > 0) {
                UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
                coreFacade.deleteServerInfo(Long.valueOf(nodeId), user.getId());
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/serverDir/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/serverDir/init";
            }
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("PKG_DONVI") != -1) { // 
                    String msg = messageSource.getMessage("admin.excute.sql.violated.FK_USER_ID", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/serverDir/init";
                }
            }
            logger.error("Exception :", e);

            return "redirect:/serverDir/init";
        }

    }
}
