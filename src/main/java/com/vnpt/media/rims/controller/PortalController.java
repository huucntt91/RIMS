/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller;

import com.vnpt.media.rims.bean.ActionLogBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.facade.LoginLogFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/portal")
public class PortalController {

    private static final Logger logger = LogManager.getLogger(PortalController.class);

    private static final String LIST = "portal";
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/", "/init"}, method = RequestMethod.GET)
    public String init(
            ModelMap mm, HttpServletRequest request) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LIST;
    }

}
