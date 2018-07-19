/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.managerAdmin;

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
import com.vnpt.media.rims.bean.DonViBO;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
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
@RequestMapping(value = "/dv")
public class DvController {

    private static Logger logger = LogManager.getLogger(DvController.class);
    private static final String CP_LIST = "managerAdmin/dv/cpList";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initGroup(ModelMap mm, HttpServletRequest request) {
        logger.info("Action init dv");
        ManagerAdminFacade facade = new ManagerAdminFacade();
        List<DonViBO> lst = facade.findAllDonVi("", "","");
        mm.put("list_cp", lst);
        return CP_LIST;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("name") String name, ModelMap mm, HttpServletRequest request) {
        name = ((name == null || name.equals("")) ? "null" : name);
        ManagerAdminFacade facade = new ManagerAdminFacade();
        logger.info("Action search dv");
        List<DonViBO> lst = facade.findAllDonVi("", name,"");
        mm.put("list_cp", lst);
        mm.put("name", name);
        return CP_LIST;//"managerAdmin/user/userList";
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        DonViBO dvBO = new DonViBO();
        mm.addAttribute("dvbo", dvBO);
        return "managerAdmin/dv/editCp";
    }

    @RequestMapping(value = "/view/{cpId}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "cpId") String cpId,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        logger.debug("view DonVi");
        try {
            if (StringUtils.hasText(cpId)) {
                ManagerAdminFacade facade = new ManagerAdminFacade();
                List<DonViBO> cpBO = facade.findAllDonVi(cpId, "","");
                mm.put("cp", cpBO.get(0));
                mm.addAttribute("dvbo", cpBO.get(0));
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return "managerAdmin/dv/editCp";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "dvbo") DonViBO cPbo, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
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
                mm.addAttribute("dvbo", cPbo);
                return "redirect:/dv/preAdd";
            }
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();

            adminFacade.addDonVi(cPbo);
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
            mm.addAttribute("dvbo", cPbo);
            return "redirect:/dv/preAdd";
        }
        DonViBO cPBO = new DonViBO();
        mm.addAttribute("dvbo", cPBO);
        return "redirect:/dv/init";

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "dvbo") DonViBO cPbo, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
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
                mm.addAttribute("dvbo", cPbo);
                return "redirect:/dv/view/" + cPbo.getDonViId();
            }
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();

            adminFacade.updateDonVi(cPbo);
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
            mm.addAttribute("cpbo", cPbo);
            return "redirect:/dv/view/" + cPbo.getDonViId();
        }
        return "redirect:/dv/init";
    }

    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "Id") String Id,
            Locale locale, RedirectAttributes attr) throws Exception {
        
        logger.debug("Delete Don Vi Action");
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<DonViBO> listDvBO = adminFacade.findAllDonVi(Id, "","");
            if (listDvBO.size() > 0) {
                adminFacade.deleteDonVi(Id);
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/dv/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/dv/init";
            }
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("PKG_DONVI") != -1) { // 
                    String msg = messageSource.getMessage("admin.excute.sql.violated.FK_USER_ID", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/dv/init";
                }
            }
            logger.error("Exception :", e);

            return "redirect:/dv/init";
        }

    }

    @ModelAttribute("cpList")
    public List findAllCp() {
        Map referenceData = new HashMap();
        ManagerAdminFacade facade = new ManagerAdminFacade();
        return facade.findAllDonVi("", "","");
    }

    @ModelAttribute("tinhList")
    public List findAllTinh() {
        Map referenceData = new HashMap();
        ManagerAdminFacade facade = new ManagerAdminFacade();
        return facade.findAllTinh("");
    }

    @RequestMapping(value = "/getHuyen/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getHuyen(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        Map referenceData = new HashMap();
        CategoriesFacade facade = new CategoriesFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(facade.findAllHuyen("", id));

    }

    @RequestMapping(value = "/getPhuong/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getPhuong(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        Map referenceData = new HashMap();
        CategoriesFacade facade = new CategoriesFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(facade.findAllPhuongXa("", id));
    }

    @RequestMapping(value = "/getHuyenByListTinh/{listTinhId}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getHuyenByListTinh(@PathVariable(value = "listTinhId") String listTinhId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        Map referenceData = new HashMap();
        CategoriesFacade facade = new CategoriesFacade();
        ObjectMapper mapper = new ObjectMapper();
       //Object to JSON in String
        return mapper.writeValueAsString(facade.findAllHuyen(listTinhId));

    }

    @RequestMapping(value = "/getPhuongByListHuyen/{listHuyenId}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getPhuongByListHuyen(@PathVariable(value = "listHuyenId") String listHuyenId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        Map referenceData = new HashMap();
        CategoriesFacade facade = new CategoriesFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(facade.findAllPhuongXa(listHuyenId));
    }
}
