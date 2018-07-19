/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.DsLamBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.DsLamFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/dslam1")
public class DsLamControllerBU {

    private static Logger logger = LogManager.getLogger(DsLamControllerBU.class);
    private static final String FORM = "broadband/dslam/new";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(@RequestParam(value = "tnodeName", required = false) String tnodeName,
            ModelMap mm, HttpServletRequest request) {
        try {
            DsLamFacade facade = new DsLamFacade();
            List<DsLamBO> list = facade.findAll("", tnodeName);

            mm.addAttribute("list", list);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/dslam/list";
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) {
        try {
            DsLamBO dsLamBO = new DsLamBO();
            mm.addAttribute("dslamForm", dsLamBO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM;
    }

    @RequestMapping(value = "/preUpdate/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") String id, ModelMap mm) {
        try {
            DsLamFacade facade = new DsLamFacade();
            DsLamBO dsLamBO = facade.findAll(id, "").get(0);
            mm.addAttribute("dslamForm", dsLamBO);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return FORM;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @ModelAttribute(value = "dsLamBO") DsLamBO dsLamBO,
            @RequestParam(value = "nodeId2", required = false) String[] nodeId2,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("dslamForm", dsLamBO);

            for (int i = 0; i < nodeId2.length; i++) {
                String string = nodeId2[i];

            }
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            DsLamFacade facade = new DsLamFacade();
            if (dsLamBO.getId() == null || dsLamBO.getId().equalsIgnoreCase("")) {
                facade.insert(dsLamBO, user.getId());
            } else {
                facade.update(dsLamBO, user.getId());
            }

            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/dslam/init";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(ModelMap mm, @PathVariable(value = "id") String id,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            DsLamFacade facade = new DsLamFacade();
            facade.delete(Long.valueOf(id), user.getId());
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/dslam/init";
    }
}
