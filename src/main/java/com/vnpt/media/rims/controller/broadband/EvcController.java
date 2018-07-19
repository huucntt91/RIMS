/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.EvcBO;
import com.vnpt.media.rims.bean.TNodeBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.EvcFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.TnodeFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/evc")
public class EvcController {

    private static final String REDIRECT = "redirect:/evc/init";
    private static Logger logger = LogManager.getLogger(EvcController.class);
    private static final String LIST = "broadband/evc/list";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tnodes", required = false) String tnodes,
            ModelMap mm, HttpServletRequest request) {
        List<TinhBO> tinhThanhPhoLst;
        try {
            ManagerAdminFacade facade = new ManagerAdminFacade();
            tinhThanhPhoLst = facade.findAllTinh("");
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = EvcFacade.countSearch(tnodes);
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
                return ("redirect:/evc/init?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/evc/init?page=" + pageInt);
            }
            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<EvcBO> list = EvcFacade.findAll(String.valueOf(startRow), String.valueOf(endRow), tnodes);
            mm.addAttribute("list", list);
            mm.put("tinhTpId", tinhTpId);
            mm.put("typeId", typeId);
            mm.put("tnodes", tnodes);
            mm.put("startRow", startRow);
            mm.put("tinhThanhPhoLst", tinhThanhPhoLst);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }

    @RequestMapping(value = "/findTnodes", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String findTnodes(HttpServletRequest request, @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        List<TNodeBO> list = null;
        try {
            if (tinhTpId != null && !tinhTpId.isEmpty()) {
                TnodeFacade facade = new TnodeFacade();
                list = facade.findTnode(typeId, tinhTpId);
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(list);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

//    @ModelAttribute("tinhThanhPhoLst")
//    public List findTinhThanhPho(HttpServletRequest request) {
//        List<TinhBO> list = null;
//        try {
//            ManagerAdminFacade facade = new ManagerAdminFacade();
//            list = facade.findAllTinh("");
//        } catch (ServiceException e) {
//            logger.error(e.getMessage(), e);
//        }
//        return list;
//    }

}
