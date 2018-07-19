/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.DongTbiBO;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.TNodeBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DongTbiFacade;
import com.vnpt.media.rims.facade.Vn2Facade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/vn2")
public class Vn2Controller {

    private static Logger logger = LogManager.getLogger(Vn2Controller.class);
    private static final String FORM = "broadband/vn2/form";
    private static final String LIST = "broadband/vn2/list";
    private static final String REDIRECT = "redirect:/vn2/init";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("dongTbis")
    public List findDongTbi() {
        ArrayList<DongTbiBO> list = null;
        try {
            list = DongTbiFacade.fc_find_all("", "VN2");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    //list khu vuc
    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        try {
            CategoriesFacade facade = new CategoriesFacade();
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            return facade.findAllKhuVuc(String.join(",", tinhManager));
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //list tinh
    @RequestMapping(value = "/getTinhTp", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getTinhTp(@RequestParam(value = "khuVucId", required = false) String khuVucId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<TinhBO> list = null;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            list = facade.findTinhByKv(khuVucId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //list huyen
    @RequestMapping(value = "/getQuanHuyen", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getQuanHuyen(@RequestParam(value = "tinhTpId", required = false) String tinhTpId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<HuyenBO> list = null;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            if (tinhTpId == null || tinhTpId.trim().isEmpty()) {
                list = facade.findAllHuyen(tinhTpId);
            } else {
                list = facade.findAllHuyen(tinhTpId);
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (ServiceException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //
    @RequestMapping(value = "/getPhuongXa", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getPhuongXa(@RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<PhuongXaBO> list = null;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            if (quanHuyenId == null || quanHuyenId.trim().isEmpty()) {
                list = facade.findAllPhuongXa(quanHuyenId);
            } else {
                list = facade.findAllPhuongXa(quanHuyenId);
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (ServiceException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String provinceId,
            @RequestParam(value = "quanHuyenId", required = false) String districtId,
            @RequestParam(value = "phuongXaId", required = false) String wardsId,
            ModelMap mm, HttpServletRequest request) {
        try {
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = Vn2Facade.countSearch(code, name, typeId, khuvucId, provinceId, districtId, wardsId);
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
                return ("redirect:/vn2/init?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/vn2/init?page=" + pageInt);
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
            List<TNodeBO> list = Vn2Facade.findAll(startRow, endRow, "", code, name, typeId, khuvucId,
                    provinceId, districtId, wardsId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("code", code);
            mm.put("name", name);
            mm.put("khuvucId", khuvucId);
            mm.put("tinhTpId", provinceId);
            mm.put("quanHuyenId", districtId);
            mm.put("phuongXaId", wardsId);
            mm.put("startRow", startRow);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) {
        try {
            TNodeBO node = new TNodeBO();
            mm.addAttribute("node", node);
            mm.put("btnName", "Thêm mới");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return FORM;
    }

    @RequestMapping(value = "/preAddTp/{type}", method = RequestMethod.GET)
    public String preAddTp(ModelMap mm, @PathVariable(value = "type") String type) {
        try {
            TNodeBO node = new TNodeBO();
            mm.addAttribute("node", node);
            mm.put("btnName", "Thêm mới");
            mm.put("typeId", type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM;
    }

    @RequestMapping(value = "/preUpdate/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") String id, ModelMap mm)  {
        List<TNodeBO> list = null;
        TNodeBO node = null;
        try {

            node = Vn2Facade.findById(id);
            try {
                if (node.getChaId() != null && !node.getChaId().isEmpty()) {
                    list = Vn2Facade.findChaList(node.getChaId());
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            mm.addAttribute("node", node);
            mm.put("btnName", "Cập nhật");
            mm.put("list", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "node") TNodeBO node,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request){
        try{
        mm.addAttribute("node", node);
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        if (node.getId() == null || node.getId().isEmpty()) {
            if (Vn2Facade.add(node, user.getId())) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_ADD_SUCCESS));
                return REDIRECT;
            } else {
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_ADD_ERROR));
                mm.put("btnName", "Thêm mới");
                return FORM;
            }

        } else {
            if (Vn2Facade.update(node, user.getId())) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return REDIRECT;
            } else {
                List<TNodeBO> list = null;
                if (node.getChaId() != null && !node.getChaId().isEmpty()) {
                    list = Vn2Facade.findChaList(node.getChaId());
                }
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_UPDATE_ERROR));
                mm.put("btnName", "Cập nhật");
                mm.put("list", list);
                return FORM;
            }
        }
         } catch (Exception e ) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, ModelMap mm, @PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr)  {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            Vn2Facade.delete(id, String.valueOf(user.getId()));
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_DELETE_SUCCESS));
        } catch (Exception e) {
            logger.error(e, e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_DELETE_ERROR));
        }
        return REDIRECT;
    }
}
