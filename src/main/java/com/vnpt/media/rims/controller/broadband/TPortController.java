/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

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
import com.vnpt.media.rims.facade.NetworkSpaceFacade;
import com.vnpt.media.rims.facade.TPortFacade;
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
@RequestMapping(value = "/tPortView")
public class TPortController {

    private static Logger logger = LogManager.getLogger(TPortController.class);

    private static final String LIST = "broadband/tPortView/list";
    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/view/{tCardId}", method = RequestMethod.GET)
    public String search(ModelMap mm,
//            @PathVariable(value = "tNodeId", required = false) String tNodeId,
            @RequestParam(value = "page", required = false) String page,
            @PathVariable(value = "tCardId") String tCardId,
            HttpServletRequest request) {
        logger.info("Action init tPortView");
        tCardId = tCardId == null ? "" : tCardId;
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        TPortFacade facade = new TPortFacade();
        int totalRows = facade.getTotalNetworkSpaceInfo("", tCardId);
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
            return ("redirect:/tPortView/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/tPortView/init?page=" + pageInt);
        }
        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Tport");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<TPortBO> lst = facade.findAllNetworkSpaceInfo(startRow,endRow, "", tCardId);
        mm.put("startRow", startRow);
        
        mm.put("list", lst);
        mm.put("tPortViewName", tCardId);

        return LIST;
    }


}
