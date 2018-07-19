///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.vnpt.media.rims.controller.managerAdmin;
//
//import java.util.List;
//import java.util.Locale;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import com.vnpt.media.rims.bean.CpBO;
//import com.vnpt.media.rims.bean.GroupBO;
//import com.vnpt.media.rims.common.Message;
//import com.vnpt.media.rims.common.utils.StringUtils;
//import com.vnpt.media.rims.facade.ManagerAdminFacade;
//
///**
// *
// * @author VNP
// */
//@Controller
//@RequestMapping(value = "/cp")
//public class CpController {
//
//    private static Logger logger = LogManager.getLogger(CpController.class);
//    private static final String CP_LIST = "managerAdmin/cp/cpList";
//
//    @Autowired
//    private MessageSource messageSource;
//
//    @RequestMapping(value = "/init", method = RequestMethod.GET)
//    public String initGroup(ModelMap mm, HttpServletRequest request) {
//        logger.info("Action init cp");
//        ManagerAdminFacade facade = new ManagerAdminFacade();
//        List<CpBO> lst = facade.findAllCp("", "");
//        mm.put("list_cp", lst);
//        return CP_LIST;
//    }
//
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String search(@RequestParam("name") String name, ModelMap mm, HttpServletRequest request) {
//        name = ((name == null || name.equals("")) ? "null" : name);
//        ManagerAdminFacade facade = new ManagerAdminFacade();
//        logger.info("Action search user");
//        List<CpBO> lst = facade.findAllCp("", name);
//        mm.put("list_cp", lst);
//        mm.put("name", name);
//        return CP_LIST;//"managerAdmin/user/userList";
//    }
//
//    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
//    public String preAdd(ModelMap mm) throws Exception {
//        CpBO CpBO = new CpBO();
//        mm.addAttribute("cpbo", CpBO);
//        return "managerAdmin/cp/editCp";
//    }
//
//    @RequestMapping(value = "/view/{cpId}", method = RequestMethod.GET)
//    public String view(@PathVariable(value = "cpId") String cpId,
//            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
//        logger.debug("view User CP");
//        try {
//            if (StringUtils.hasText(cpId)) {
//                ManagerAdminFacade facade = new ManagerAdminFacade();
//                List<CpBO> cpBO = facade.findAllCp(cpId, "");
//                mm.put("cp", cpBO.get(0));
//                mm.addAttribute("cpbo", cpBO.get(0));
//            }
//        } catch (Exception e) {
//            logger.error("Exception :", e);
//            String msg = messageSource.getMessage("admin.common.error", null, locale);
//            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//        }
//
//        return "managerAdmin/cp/editCp";
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String add(ModelMap mm, @Valid @ModelAttribute(value = "cpbo") CpBO cPbo, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
//        Locale locale = LocaleContextHolder.getLocale();
//        try {
//            if (bindingResult.hasErrors()) {
//                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//                for (Object object : bindingResult.getAllErrors()) {
//                    if (object instanceof FieldError) {
//                        FieldError fieldError = (FieldError) object;
//                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
//                        break;
//                    } else {
//                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
//                    }
//                }
//                mm.addAttribute("cpbo", cPbo);
//                return "redirect:/cp/preAdd";
//            }
//            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//            adminFacade.addCp(cPbo);
//        } catch (Exception e) {
//
//            String message = StringUtils.captureStackTrace(e);
//            if (StringUtils.hasText(message)) {
//                if (message.indexOf("UK_CMSCP_NAME") != -1) {
//                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_CMSCP_NAME", null, locale);
//                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                    mm.addAttribute("cpbo", cPbo);
//                    return "redirect:/cp/preAdd";
//                }
//            }
//            logger.error("Exception :", e);
//            String msg = messageSource.getMessage("admin.common.error", null, locale);
//            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//            mm.addAttribute("cpbo", cPbo);
//            return "redirect:/cp/preAdd";
//        }
//        CpBO cPBO = new CpBO();
//        mm.addAttribute("cpbo", cPBO);
//        return "redirect:/cp/init";
//
//    }
//
//    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
//    public String update(ModelMap mm, @Valid @ModelAttribute(value = "cpbo") CpBO cPbo, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
//        logger.info("update Cp Action");
//        Locale locale = LocaleContextHolder.getLocale();
//        try {
//            if (bindingResult.hasErrors()) {
//                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//                for (Object object : bindingResult.getAllErrors()) {
//                    if (object instanceof FieldError) {
//                        FieldError fieldError = (FieldError) object;
//                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
//                        break;
//                    } else {
//                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
//                    }
//                }
//                mm.addAttribute("cpbo", cPbo);
//                return "redirect:/cp/view/" + cPbo.getId();
//            }
//            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//
//            adminFacade.updateCp(cPbo);
//        } catch (Exception e) {
//            String message = StringUtils.captureStackTrace(e);
//            if (StringUtils.hasText(message)) {
//                if (message.indexOf("UK_CMSCP_NAME") != -1) {
//                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_CMSCP_NAME", null, locale);
//                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                    mm.addAttribute("cpbo", cPbo);
//                    return "redirect:/cp/view/" + cPbo.getId();
//                }
//            }
//            logger.error("Exception :", e);
//            String msg = messageSource.getMessage("admin.common.error", null, locale);
//            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//            mm.addAttribute("cpbo", cPbo);
//            return "redirect:/cp/view/" + cPbo.getId();
//        }
//        return "redirect:/cp/init";
//    }
//}
