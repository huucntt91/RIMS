/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.core;

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
import com.vnpt.media.rims.facade.CoreFacade;
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
@RequestMapping(value = "/hss")
public class HssController {

    private static Logger logger = LogManager.getLogger(HssController.class);

    private static final String LIST = "core/hss/list";
    private static final String VIEW = "core/hss/edit";
    private static final String POPUP = "core/hss/popup";
    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("thietBiList")
    public List findAllThietBi() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllThietBi("");
    }

    @ModelAttribute("dvList")
    public List findAllDonVi(HttpServletRequest request) {
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhs =  String.join(",", tinhManager);
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllDonVi("", "",tinhs);
    }

    @ModelAttribute("tramList")
    public List findAllTram() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllLoaiTram("");
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "name", required = false) String name,
            HttpServletRequest request) {
        logger.info("Action init hss");
        name = name == null ? "" : name;
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        CoreFacade facade = new CoreFacade();
        int totalRows = facade.getTotalHssInfo(name, "");
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
            return ("redirect:/hss/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/hss/init?page=" + pageInt);
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
        List<HssInfoBO> lst = facade.findAllHssInfo(startRow, endRow, name, "");
        mm.put("startRow", startRow);
        mm.put("list", lst);
        mm.put("name", name);

        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        HssInfoBO item = new HssInfoBO();
        mm.addAttribute("item", item);
        return VIEW;
    }

    @RequestMapping(value = "/popup/{nodeId}", method = RequestMethod.GET)
    public String popup(@PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        view(nodeId, locale, attr, mm);
        return POPUP;
    }

    @RequestMapping(value = "/view/{nodeId}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        try {
            if (StringUtils.hasText(nodeId)) {
                CoreFacade facade = new CoreFacade();
                List<HssInfoBO> cpBO = facade.findAllHssInfo(1, 10, null, nodeId);
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
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "item") HssInfoBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                mm.addAttribute("item", item);
                return "redirect:/hss/preAdd";
            }
            CoreFacade coreFacade = new CoreFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            coreFacade.addHssInfo(item, user.getId());
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {

            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/hss/preAdd";
        }

        return "redirect:/hss/init";

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "item") HssInfoBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
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
                return "redirect:/hss/view/" + item.getNodeId();
            }
            CoreFacade coreFacade = new CoreFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            coreFacade.updateHssInfo(item, user.getId());
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/hss/view/" + item.getNodeId();
        }
        return "redirect:/hss/init";
    }

    @RequestMapping(value = "/delete/{nodeId}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr) throws Exception {

        logger.debug("Delete Don Vi Action");
        try {
            CoreFacade coreFacade = new CoreFacade();
            List<HssInfoBO> hssInfo = coreFacade.findAllHssInfo(1, 10, "", nodeId);
            if (hssInfo.size() > 0) {
                coreFacade.deleteHssInfo(Long.valueOf(nodeId));
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/hss/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/hss/init";
            }
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("PKG_DONVI") != -1) { // 
                    String msg = messageSource.getMessage("admin.excute.sql.violated.FK_USER_ID", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/hss/init";
                }
            }
            logger.error("Exception :", e);

            return "redirect:/hss/init";
        }

    }
}
