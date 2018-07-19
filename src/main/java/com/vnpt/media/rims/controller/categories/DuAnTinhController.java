/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.categories;

import com.vnpt.media.rims.bean.DuAnTinhBO;
import com.vnpt.media.rims.bean.ProjectBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IProject;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DuAnTinhFacade;
import com.vnpt.media.rims.facade.StationPlansFacade;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/duantinh")
public class DuAnTinhController extends BaseController{

    private static Logger logger = LogManager.getLogger(DuAnTinhController.class);
    private static final String DATinh_LIST = "categories/duAnTinh/duAnList";
    private static final String ADD_DATinh = "categories/duAnTinh/addDATinh";

    @Autowired
    private MessageSource messageSource;
    
    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
    }
    
    //tim kiem quy hoach tinh
    @ModelAttribute("qhTinhLst")
    public List<ProjectBO> searchQHTinh() {
        List<ProjectBO> list = null;
        DAOFactory factory = null;
        ITransaction trans = null;
        try {
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProject iproject = factory.getProjectDAO();
            trans.connectionType(Constants.DB_CB);
            iproject.setTransaction(trans);
            list = iproject.searchProjectInfor(null, null);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            DatabaseUtils.close(trans);
        }
        return list;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, HttpServletRequest request) {
        logger.info("Action init plansStation");
        
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);

        DuAnTinhBO tramDA = new DuAnTinhBO();
        DuAnTinhFacade facade = new DuAnTinhFacade();
        List<DuAnTinhBO> lst = facade.findDATinh(tinhTpId, null);
        mm.put("listDuAnTinh", lst);
//        mm.put("tramDABO", tramDA);
        return DATinh_LIST;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("maDuAn") String maDuAn,
                         ModelMap mm, 
                         HttpServletRequest request) {
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        DuAnTinhBO tramDA = new DuAnTinhBO();
        maDuAn = ((maDuAn == null || maDuAn.equals("")) ? "" : maDuAn);
        DuAnTinhFacade facade = new DuAnTinhFacade();
        logger.info("Action search tram du an");       
        List<DuAnTinhBO> lst = facade.findDATinh(tinhTpId, maDuAn);

        mm.put("listDuAnTinh", lst);
//        mm.put("tramDABO", tramDA);
        mm.put("maDuAn", maDuAn);
        
        return DATinh_LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        DuAnTinhBO daTinhBO = new DuAnTinhBO();
        mm.addAttribute("daTinh", daTinhBO);
        return ADD_DATinh;
    }
    
    @RequestMapping(value = "/preUpdate/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") String id, 
                            ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        
        DuAnTinhFacade facade = new DuAnTinhFacade();

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        List<DuAnTinhBO> list = facade.findDATinh(tinhTpId, id);

        mm.put("daTinh", list.get(0));
        return ADD_DATinh;

    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "daTinh") DuAnTinhBO duAnTinhBO,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        try {
            DuAnTinhFacade nodeFacade = new DuAnTinhFacade();
            duAnTinhBO.setUserId(String.valueOf(user.getId())); 
            nodeFacade.modifyDuAnTinh(duAnTinhBO);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {

            logger.error("Exception :", e);
            genMessageSystemError(attr, messageSource, locale);
            return "redirect:/duantinh/preAdd";
        }
        DuAnTinhBO daTinhBO = new DuAnTinhBO();
        mm.addAttribute("daTinh", daTinhBO);
        String msg = messageSource.getMessage("admin.common.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
        return "redirect:/duantinh/init";

    }
    
    @RequestMapping(value = "preUpdate/add", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "daTinh") DuAnTinhBO duAnTinhBO,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        try {
            DuAnTinhFacade nodeFacade = new DuAnTinhFacade();
            duAnTinhBO.setUserId(String.valueOf(user.getId())); 
            nodeFacade.modifyDuAnTinh(duAnTinhBO);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {

            logger.error("Exception :", e);
            genMessageSystemError(attr, messageSource, locale);
            return "redirect:/duantinh/preAdd";
        }
        DuAnTinhBO daTinhBO = new DuAnTinhBO();
        mm.addAttribute("daTinh", daTinhBO);
        String msg = messageSource.getMessage("admin.common.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
        return "redirect:/duantinh/init";

    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        logger.debug("Delete Du an tinh");
        try {
            DuAnTinhFacade facade = new DuAnTinhFacade();
            DuAnTinhBO model = new DuAnTinhBO();
            model.setDuAnId(id);

            if (facade.deleteDuAnTinh(model) > -1) {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/duantinh/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/duantinh/init";
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            logger.error("Exception :", e);

            return "redirect:/duantinh/init";
        }

    }

}
