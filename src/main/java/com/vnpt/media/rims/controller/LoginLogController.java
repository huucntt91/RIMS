/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller;

import com.vnpt.media.rims.bean.ActionLogBO;
import com.vnpt.media.rims.bean.ManEInfoBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.facade.LoginLogFacade;
import com.vnpt.media.rims.facade.ManeFacade;
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
@RequestMapping(value = "/log")
public class LoginLogController {
    
    private static final Logger logger = LogManager.getLogger(LoginLogController.class);
    
    private static final String LIST = "log/login/list";
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "ip", required = false) String ip,
            ModelMap mm, HttpServletRequest request) {
        try {
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = LoginLogFacade.countSearch(userName, ip, null);
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
                return ("redirect:/mane/init?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/mane/init?page=" + pageInt);
            }
            
            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            objPage.setSubject("Quản lý lịch sử đăng nhập");
            mm.addAttribute("pageInfo", objPage);
            
            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<ActionLogBO> list = LoginLogFacade.findAll(startRow, endRow, userName, ip, null);
            mm.addAttribute("list", list);
            mm.put("userName", userName);
            mm.put("ip", ip);
            mm.put("startRow", startRow);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }
    
}
