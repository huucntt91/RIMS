/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.bean.ReportCellInfoBO;
import com.vnpt.media.rims.controller.broadband.*;
import com.vnpt.media.rims.bean.ReportTramChuaDkCSHTBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.AccessFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DongTbiFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.ManeFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
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
@RequestMapping(value = "/reportCellInfo")
public class ReportCellInfoController {

    private static Logger logger = LogManager.getLogger(ReportCellInfoController.class);
    private static final String LIST = "report/thongTinCell/list";

    @Autowired
    private MessageSource messageSource;


    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllKhuVuc(String.join(",", tinhManager));
    }
    
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "techType", required = false) String techType,
            @RequestParam(value = "khuVucId", required = false) String khuVucId,
            ModelMap mm, HttpServletRequest request) {

        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        int totalRows = 10; //AccessFacade.getInstance().getTotal("", code, name, typeId, khuvucId, provinceId, districtId, wardsId);

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
            return ("redirect:/reportCellInfo/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportCellInfo/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Report tram chua dang ky CSHT");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        
        List<ReportCellInfoBO> list = null;//(List<DsLamBO>) AccessFacade.getInstance().findAll(startRow, endRow, "", code, name, typeId,khuvucId, provinceId, districtId, wardsId);
        mm.addAttribute("list", list);
        mm.put("techType", techType);
        mm.put("khuVucId", khuVucId);
        mm.put("startRow", startRow);
        return LIST;
    }

}
