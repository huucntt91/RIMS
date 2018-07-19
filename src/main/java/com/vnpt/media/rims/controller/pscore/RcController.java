/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.pscore;

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
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.CorePSFacade;
import java.io.File;
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
@RequestMapping(value = "/rc")
public class RcController {

    private static Logger logger = LogManager.getLogger(RcController.class);

    private static final String LIST = "core_ps/rc/list";
    private static final String VIEW = "core_ps/rc/edit";
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
            @RequestParam(value = "name", required = false) String name,
            HttpServletRequest request) {
        logger.info("Action init rc");
        name = name == null ? "" : name;

        CorePSFacade facade = new CorePSFacade();

        List<RcInfoBO> lst = facade.findAllRcInfo(name, "");
        mm.put("list", lst);
        mm.put("name", name);

        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm, RedirectAttributes attr) throws Exception {
        RcInfoBO item = new RcInfoBO();
        item = (RcInfoBO) attr.getFlashAttributes().get("item");

        mm.addAttribute("item", item == null ? new RcInfoBO() : item);
        return VIEW;
    }

    @RequestMapping(value = "/view/{nodeId}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        try {
            if (StringUtils.hasText(nodeId)) {
                CorePSFacade facade = new CorePSFacade();
                List<RcInfoBO> cpBO = facade.findAllRcInfo(null, nodeId);
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
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "item") RcInfoBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            if (bindingResult.hasErrors()) {
                
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        
                    }
                }
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                mm.addAttribute("item", item);
                return "redirect:/rc/preAdd";
            }
            if (!item.getFileAttack().isEmpty()) {
                String uploadsDir = "/uploads/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }
                String orgName = item.getFileAttack().getOriginalFilename();
                String filePath = realPathtoUploads + orgName;
                File dest = new File(filePath);
                item.getFileAttack().transferTo(dest);
                item.setFile(uploadsDir + orgName);
            }
            CorePSFacade coreFacade = new CorePSFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            coreFacade.addRcInfo(item, user.getId());
        } catch (Exception e) {
            
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("NODE_UK1") != -1) { // 
                    String msg = messageSource.getMessage("node.manode.NODE_UK1", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    attr.addFlashAttribute("item", item);
                    return "redirect:/rc/preAdd";
                }
            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/rc/preAdd";
        }

        return "redirect:/rc/init";

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "item") RcInfoBO item, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
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
                return "redirect:/rc/view/" + item.getNodeId();
            }
            
            if (!item.getFileAttack().isEmpty()) {
                String uploadsDir = "/uploads/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }
                String orgName = item.getFileAttack().getOriginalFilename();
                String filePath = realPathtoUploads + orgName;
                File dest = new File(filePath);
                item.getFileAttack().transferTo(dest);
                item.setFile(uploadsDir + orgName);
            }
            CorePSFacade coreFacade = new CorePSFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            coreFacade.updateRcInfo(item, user.getId());
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("item", item);
            return "redirect:/rc/view/" + item.getNodeId();
        }
        return "redirect:/rc/init";
    }

    @RequestMapping(value = "/delete/{nodeId}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "nodeId") String nodeId,
            Locale locale, RedirectAttributes attr) throws Exception {

        logger.debug("Delete Don Vi Action");
        try {
            CorePSFacade coreFacade = new CorePSFacade();
            List<RcInfoBO> ggsnInfo = coreFacade.findAllRcInfo("", nodeId);
            if (ggsnInfo.size() > 0) {
                coreFacade.deleteRcInfo(Long.valueOf(nodeId));
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/rc/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/rc/init";
            }
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("PKG_DONVI") != -1) { // 
                    String msg = messageSource.getMessage("admin.excute.sql.violated.FK_USER_ID", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/rc/init";
                }
            }
            logger.error("Exception :", e);

            return "redirect:/rc/init";
        }

    }

}
