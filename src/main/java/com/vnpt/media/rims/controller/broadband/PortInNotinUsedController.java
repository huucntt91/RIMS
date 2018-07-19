/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.DongTbiBO;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.PortInNotinUsedBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DongTbiFacade;
import com.vnpt.media.rims.facade.PortInNotinUsedFacade;
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
@RequestMapping(value = "/portInusedNotinused")
public class PortInNotinUsedController {

    private static Logger logger = LogManager.getLogger(PortInNotinUsedController.class);
    private static final String LIST = "broadband/portInusedNotinused/list";

    @Autowired
    private MessageSource messageSource;

//    @ModelAttribute("dongTbis")
//    public List findDongTbi() {
//        ArrayList<DongTbiBO> list = null;
//        try {
//            list = DongTbiFacade.fc_find_all("");
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return list;
//    }

    //list khu vuc
    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllKhuVuc(String.join(",", tinhManager));
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String provinceId,
            @RequestParam(value = "quanHuyenId", required = false) String districtId,
            @RequestParam(value = "phuongXaId", required = false) String wardsId,
            ModelMap mm, HttpServletRequest request) {
//        if (typeId == null) {
//            typeId = "7";
//        }
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = PortInNotinUsedFacade.countSearch(code, name, khuvucId, provinceId, districtId, wardsId);
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
        List<PortInNotinUsedBO> list = PortInNotinUsedFacade.findAll(startRow, endRow, "", code, name, khuvucId,
                provinceId, districtId, wardsId);
        mm.addAttribute("list", list);
//        mm.put("typeId", typeId);
        mm.put("code", code);
        mm.put("name", name);
        mm.put("khuvucId", khuvucId);
        mm.put("tinhTpId", provinceId);
        mm.put("quanHuyenId", districtId);
        mm.put("phuongXaId", wardsId);
        mm.put("startRow", startRow);
        return LIST;
    }

}
