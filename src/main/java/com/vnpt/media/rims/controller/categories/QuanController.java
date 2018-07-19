/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.categories;

import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.controller.managerAdmin.*;
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
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ResponseBody;
import org.codehaus.jackson.map.ObjectMapper;
/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/quan")
public class QuanController {

    private static Logger logger = LogManager.getLogger(QuanController.class);
    private static final String TINH_LIST = "categories/quan/quanList";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initGroup(ModelMap mm, HttpServletRequest request) {
        logger.info("Action init Tinh TP");
        CategoriesFacade facade = new CategoriesFacade();
        List<HuyenBO> lst = facade.findAllHuyen("","");
        mm.put("quanList", lst);
        return TINH_LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        HuyenBO quanBO = new HuyenBO();
        mm.addAttribute("quanBO", quanBO);
        return "categories/quan/editQuan";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        logger.debug("view QUAN_HUYEN");
        try {
            if (StringUtils.hasText(id)) {
                CategoriesFacade facade = new CategoriesFacade();
                List<HuyenBO> quanBOList = facade.findAllHuyen(id,"");
                mm.put("quanBO", quanBOList.get(0));
                mm.addAttribute("quanBO", quanBOList.get(0));
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return "categories/quan/editQuan";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "quanBO") HuyenBO quanBO, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
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
                mm.addAttribute("quanBO", quanBO);
                return "redirect:/tinh/preAdd";
            }
            CategoriesFacade adminFacade = new CategoriesFacade();
            if (adminFacade.modifyQuan("add", quanBO) > 0)
            {
                 return "redirect:/quan/init";
            }
            else
            {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    //mm.addAttribute("dvbo", cPbo);
                return "redirect:/quan/preAdd";
            }
       
        } catch (Exception e) {

            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
//                if (message.indexOf("UK_CMSCP_NAME") != -1) {
//                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_CMSCP_NAME", null, locale);
//                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                    mm.addAttribute("dvbo", cPbo);
//                    return "redirect:/dv/preAdd";
//                }
            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            //mm.addAttribute("dvbo", cPbo);
            return "redirect:/quan/preAdd";
        }



    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "quanBO") HuyenBO quanBO, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
        logger.info("update Quan Action");
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
                mm.addAttribute("quanBO", quanBO);
                return "redirect:/quan/view/" + quanBO.getQuanHuyenId();
            }
            CategoriesFacade adminFacade = new CategoriesFacade();

            adminFacade.modifyQuan("edit", quanBO);
            return "redirect:/quan/init";
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
//            if (StringUtils.hasText(message)) {
//                if (message.indexOf("UK_CMSCP_NAME") != -1) {
//                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_CMSCP_NAME", null, locale);
//                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                    mm.addAttribute("cpbo", cPbo);
//                    return "redirect:/cp/view/" + cPbo.getId();
//                }
//            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("quanBO", quanBO);
            return "redirect:/quan/view/" + quanBO.getQuanHuyenId();
        }
  
    }
    
    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "Id") String Id,
            Locale locale, RedirectAttributes attr) throws Exception {
        
        logger.debug("Delete Quan Huyen Action");
        try {
            CategoriesFacade adminFacade = new CategoriesFacade();
            HuyenBO quanBO = new HuyenBO();
            quanBO.setQuanHuyenId(Integer.parseInt(Id));
            
            if(adminFacade.modifyQuan("del", quanBO) > -1)
            {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/quan/init";
            }
            else
            {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/quan/init";
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            logger.error("Exception :", e);
            
            return "redirect:/quan/init";
        }
 
    }
    
    
     @ModelAttribute("tinhList")
    public List findAllTinh() {
        Map referenceData = new HashMap();
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTinh("");
    }
    

}
