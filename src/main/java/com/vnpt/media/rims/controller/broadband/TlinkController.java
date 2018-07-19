package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.TLinkBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.facade.TlinkFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@RequestMapping(value = "/tlink")
public class TlinkController {

    private static Logger logger = LogManager.getLogger(TlinkController.class);
    private static final String List = "broadband/tlink/list";
    private static final String FORM = "broadband/tlink/new";
    private static final String POPUP = "broadband/tlink/popup";

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "name", required = false) String name,
            ModelMap mm, HttpServletRequest request) {
        TlinkFacade facade = new TlinkFacade();
        
        mm.addAttribute("name", name);
        int totalRows = facade.getTotal(null, name == null ? "" : name);
        
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
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        if (pageInt < 1) {
            pageInt = 1;
            return ("redirect:/tlink/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/tlink/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý TLink");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }

        List<TLinkBO> list = facade.findAll(null, name == null ? "" : name,startRow, endRow);

        mm.put("startRow", startRow);
        mm.put("list", list);
        return List;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm)  {
        TLinkBO tlinkBO = new TLinkBO();
        mm.addAttribute("tlinkForm", tlinkBO);
        return FORM;
    }

    @RequestMapping(value = "/preUpdate/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") String id, ModelMap mm)  {
        TlinkFacade facade = new TlinkFacade();
        TLinkBO tlinkBO = facade.findAll(id, null,0,10).get(0);
        mm.addAttribute("tlinkForm", tlinkBO);
        return FORM;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @ModelAttribute(value = "tlinkBO") TLinkBO tlinkBO,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request)  {
        mm.addAttribute("tlinkForm", tlinkBO);
        
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        TlinkFacade facade = new TlinkFacade();
        if (tlinkBO.getId() == null || tlinkBO.getId().equalsIgnoreCase("")) {
            facade.insert(tlinkBO, user.getId());
        } else {
            facade.update(tlinkBO, user.getId());
        }
        
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));

        return "redirect:/tlink/init";
    }

//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    public String insert(ModelMap mm, @ModelAttribute(value = "tlinkBO") TLinkBO tlinkBO,
//            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request)  {
//        mm.addAttribute("tlinkBO", tlinkBO);
//        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//        TlinkFacade facade = new TlinkFacade();
//        facade.insert(tlinkBO, user.getId());
//        return FORM;
//    }
//    trunglk_start
    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String popup(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "name", required = false) String name,
            ModelMap mm, HttpServletRequest request) {
        TlinkFacade facade = new TlinkFacade();
        
        mm.addAttribute("name", name);
        int totalRows = facade.getTotal(null, name == null ? "" : name);
        
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
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        if (pageInt < 1) {
            pageInt = 1;
            return ("redirect:/tlink/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/tlink/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý TLink");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }

        List<TLinkBO> list = facade.findAll(null, name == null ? "" : name,startRow, endRow);

        mm.put("startRow", startRow);
        mm.put("list", list);
        return POPUP;
    }
//    trunglk_end
    
}
