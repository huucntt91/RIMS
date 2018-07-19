/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.DongTbiBO;
import com.vnpt.media.rims.bean.TVendorBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.facade.DongThietBiFacade;
import com.vnpt.media.rims.facade.TVendorFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/dongthietbi")
public class DongThietBiController {

    private static Logger logger = LogManager.getLogger(DongThietBiController.class);
    private static final String FORM = "broadband/dongthietbi/form";
    private static final String LIST = "broadband/dongthietbi/list";
    private static final String REDIRECT = "redirect:/dongthietbi/init";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("vendor")
    public List findVendor() {
        List<TVendorBO> list = null;
        try {
            TVendorFacade facade = new TVendorFacade();
            list = facade.findAll(null, "", null, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tvendorId", required = false) Long tvendorId,
            @RequestParam(value = "name", required = false) String name,
            ModelMap mm, HttpServletRequest request) {
        try {

            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
//DongThietBiFacade facade=new DongThietBiFacade();
            int totalRows = DongThietBiFacade.getInstance().getTotal(null, name, tvendorId);

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
                return ("redirect:/dongthietbi/init?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/dongthietbi/init?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            objPage.setSubject("Quản lý dòng thiết bị");
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }

            List<DongTbiBO> list = (List<DongTbiBO>) DongThietBiFacade.getInstance().findAll(null, name, tvendorId, startRow, endRow);
            mm.addAttribute("list", list);
            mm.put("tvendorId", tvendorId);
            mm.put("name", name);
            mm.put("startRow", startRow);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) {
        try {
            mm.put("btnName", "Thêm mới");
            DongTbiBO access = new DongTbiBO();
            mm.addAttribute("dongthietbi", access);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM;
    }

    @RequestMapping(value = "/preUpdate/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") Long id, ModelMap mm) {
        try {
            mm.put("btnName", "Cập nhật");
            List<DongTbiBO> list = DongThietBiFacade.getInstance().findAll(id, null, null, 0, 10);
            DongTbiBO access = list.get(0);
            mm.addAttribute("dongthietbi", access);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM;

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @ModelAttribute(value = "dongthietbi") DongTbiBO dongthietbi,
            RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("dongthietbi", dongthietbi);
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            if (dongthietbi.getDongTbiId() == null) {
                if (DongThietBiFacade.getInstance().add(dongthietbi, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_ADD_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_ADD_ERROR));
                }

            } else {
                if (DongThietBiFacade.getInstance().update(dongthietbi, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_UPDATE_ERROR));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REDIRECT;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Long id, HttpServletRequest request, ModelMap mm,
            RedirectAttributes attr) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//            AccessFacade.getInstance().delete(id, typeId, user.getId());

            DongThietBiFacade.getInstance().delete(id, user.getId());
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_DELETE_SUCCESS));
        } catch (DAOException e) {
            logger.error(e, e);
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("TNODE_DONG_THIET_BI_FK") != -1) {
                    String msg = "Dòng thiết bị đã được sử dụng";
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return REDIRECT;
                }
            }
            logger.error("Exception :", e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_DELETE_ERROR));
            return REDIRECT;

        }
        return REDIRECT;
    }
}
