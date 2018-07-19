/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.nodes;

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
import com.vnpt.media.rims.facade.BSCRNCFacade;
import java.util.ArrayList;
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
@RequestMapping(value = "/bscRnc")
public class BSCRNCController {

    private static Logger logger = LogManager.getLogger(BSCRNCController.class);

    private static final String LIST = "nodes/bscRnc/list";
    private static final String VIEW = "nodes/bscRnc/edit";
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
    
//    @ModelAttribute("bangTanList")
//    public List findAllBangTan() {
//        return BangTanFacade.fc_find_all("");
//    }

    
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "name", required = false) String name,
            HttpServletRequest request) {
        logger.info("Action init bscRnc");
        name = name == null ? "" : name;
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        BSCRNCFacade facade = new BSCRNCFacade();
        int totalRows;
        try{
             totalRows = facade.getTotalBscRncInfo(name, "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            totalRows = 0;
        }
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
            return ("redirect:/bscRnc/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/bscRnc/init?page=" + pageInt);
        }
        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý BSC/RNC");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<BSCRNCInfoBO> lst = new ArrayList<BSCRNCInfoBO>();
        try{
        lst = facade.findAllBscRncInfo(startRow, endRow, name, "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        mm.put("startRow", startRow);
        mm.put("list", lst);
        mm.put("name", name);

        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm, RedirectAttributes attr) throws Exception {
        BSCRNCInfoBO item = new BSCRNCInfoBO();
        item = (BSCRNCInfoBO) attr.getFlashAttributes().get("item");

        mm.addAttribute("item", item == null ? new BSCRNCInfoBO() : item);
        return VIEW;
    }

    @RequestMapping(value = "/view/{nodeId}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        try {
            if (StringUtils.hasText(nodeId)) {
                BSCRNCFacade facade = new BSCRNCFacade();
                List<BSCRNCInfoBO> cpBO = facade.findAllBscRncInfo(1, 10, null, nodeId);
                mm.put("item", cpBO.get(0));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return VIEW;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "item") BSCRNCInfoBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            if (bindingResult.hasErrors()) {
                
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        
                    }
                }
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                mm.addAttribute("item", item);
                return "redirect:/bscRnc/preAdd";
            }
            BSCRNCFacade coreFacade = new BSCRNCFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            coreFacade.addBscRncInfo(item, user.getId());
        } catch (Exception e) {
            
            String message = StringUtils.captureStackTrace(e);
            
            if (StringUtils.hasText(message)) {
                if (message.indexOf("NODE_UK1") != -1) { // 
                    String msg = messageSource.getMessage("node.manode.NODE_UK1", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    attr.addFlashAttribute("item", item);
                    return "redirect:/bscRnc/preAdd";
                }
            }
            
            logger.error(e.getMessage(), e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/bscRnc/preAdd";
        }

        return "redirect:/bscRnc/init";

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "item") BSCRNCInfoBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
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
                return "redirect:/bscRnc/view/" + item.getNodeId();
            }
            BSCRNCFacade coreFacade = new BSCRNCFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            coreFacade.updateBscRncInfo(item, user.getId());
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);

            logger.error(e.getMessage(), e);
        
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/bscRnc/view/" + item.getNodeId();
        }
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        return "redirect:/bscRnc/init";
    }

    @RequestMapping(value = "/delete/{nodeId}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr) throws Exception {

        logger.debug("Delete BSC/RNC Action");
        try {
            BSCRNCFacade coreFacade = new BSCRNCFacade();
            List<BSCRNCInfoBO> bscRncInfo = coreFacade.findAllBscRncInfo(1, 10, "", nodeId);
            if (bscRncInfo.size() > 0) {
                coreFacade.deleteBscRncInfo(Long.valueOf(nodeId));
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/bscRnc/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/bscRnc/init";
            }
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("PKG_DONVI") != -1) { // 
                    String msg = messageSource.getMessage("admin.excute.sql.violated.FK_USER_ID", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/bscRnc/init";
                }
            }
            logger.error(e.getMessage(), e);       
            return "redirect:/bscRnc/init";
        }

    }

    @RequestMapping(value = "/detail/{nodeId}", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        try {
            if (StringUtils.hasText(nodeId)) {
                BSCRNCFacade facade = new BSCRNCFacade();
                List<BSCRNCInfoBO> cpBO = facade.findAllBscRncInfo(1, 10, null, nodeId);
                mm.put("item", cpBO.get(0));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return "nodes/bscRnc/detail";
    }
}
