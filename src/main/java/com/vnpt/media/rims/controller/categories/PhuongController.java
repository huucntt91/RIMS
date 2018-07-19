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
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ResponseBody;
import org.codehaus.jackson.map.ObjectMapper;
/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/phuong")
public class PhuongController {

    private static Logger logger = LogManager.getLogger(PhuongController.class);
    private static final String TINH_LIST = "categories/phuong/phuongList";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(@RequestParam(value =  "tinhTpId", required = false) String tinhTpId,@RequestParam(value =  "quanHuyenId", required = false) String quanHuyenId, ModelMap mm, HttpServletRequest request) {
        logger.info("Action init Phuong Xa");
        
        List<PhuongXaBO> listPhuong = new ArrayList<PhuongXaBO>();
          
        if(StringUtils.hasText(quanHuyenId)){
            CategoriesFacade facade = new CategoriesFacade();
            listPhuong = facade.findAllPhuongXa("",quanHuyenId);
        }
//      
        mm.put("phuongList", listPhuong);
        mm.put("tinhTpId", tinhTpId);
        mm.put("quanHuyenId", quanHuyenId);
        return TINH_LIST;
    }
   

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        PhuongXaBO phuongBO = new PhuongXaBO();
        mm.addAttribute("phuongBO", phuongBO);
        return "categories/phuong/editPhuong";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        logger.debug("view Phường Xã");
        try {
            if (StringUtils.hasText(id)) {
                CategoriesFacade facade = new CategoriesFacade();
                List<PhuongXaBO> phuongBOList = facade.findAllPhuongXa(id,"");

                List<HuyenBO> huyenBOList = facade.findAllHuyen(String.valueOf(phuongBOList.get(0).getQuanHuyenId()), "");
                mm.put("phuongBO", phuongBOList.get(0));
                mm.put("tinhTpId", huyenBOList.get(0).getTinhTpId());
                mm.addAttribute("phuongBO", phuongBOList.get(0));
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return "categories/phuong/editPhuong";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "phuongBO") PhuongXaBO phuongBO, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
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
                mm.addAttribute("phuongBO", phuongBO);
                return "redirect:/phuong/preAdd";
            }
            CategoriesFacade adminFacade = new CategoriesFacade();
            
            int result = adminFacade.modifyPhuong("add", phuongBO);
            if (result > 0)
            {
                 attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                 return "redirect:/phuong/init";
            }
            else if( result == -2 ){
                String msg = "Tên phường xã đã tồn tại thuộc quận huyện này";
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/phuong/preAdd";
            }
            else
            {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    //mm.addAttribute("dvbo", cPbo);
                return "redirect:/phuong/preAdd";
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
            return "redirect:/phuong/preAdd";
        }



    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "phuongBO") PhuongXaBO phuongBO, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
        logger.info("update Phuong Xa Action");
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
                mm.addAttribute("phuongBO", phuongBO);
                return "redirect:/phuong/view/" + phuongBO.getPhuongXaId();
            }
            CategoriesFacade adminFacade = new CategoriesFacade();

            if ( adminFacade.modifyPhuong("edit", phuongBO) > 0)
            {
                 attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                 return "redirect:/phuong/init";
            }
            else
            {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));  
                return "redirect:/phuong/view/" + phuongBO.getPhuongXaId();
            }

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
            mm.addAttribute("phuongBO", phuongBO);
            return "redirect:/phuong/view/" + phuongBO.getPhuongXaId();
        }
  
    }
    
    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "Id") String Id,
            Locale locale, RedirectAttributes attr) throws Exception {
        
        logger.debug("Delete Phuong Xa Action");
        try {
            CategoriesFacade adminFacade = new CategoriesFacade();
            PhuongXaBO phuongBO = new PhuongXaBO();
            phuongBO.setPhuongXaId(Integer.parseInt(Id));
            
            if(adminFacade.modifyPhuong("del", phuongBO) > -1)
            {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/phuong/init";
            }
            else
            {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/phuong/init";
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            logger.error("Exception :", e);
            
            return "redirect:/phuong/init";
        }
 
    }
    
    
     @ModelAttribute("tinhList")
    public List findAllTinh() {
        Map referenceData = new HashMap();
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTinh("");
    }
    

}
