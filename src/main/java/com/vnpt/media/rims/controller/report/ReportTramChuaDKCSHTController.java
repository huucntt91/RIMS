/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.controller.broadband.*;
import com.vnpt.media.rims.bean.ReportTramChuaDkCSHTBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.LogMenuFacade;
import com.vnpt.media.rims.facade.ReportFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/reportTramChuaDkCSHT")
public class ReportTramChuaDKCSHTController {

    private static final Logger logger = LogManager.getLogger(ReportTramChuaDKCSHTController.class);
    private static final String LIST = "report/tramChuaDkCSHT/list";

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
        try{
        // log user request menu
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LogMenuFacade.logMenu(String.valueOf(user.getId()), request.getRemoteAddr(), "Báo cáo trạm chưa đăng ký cơ sở hạ tầng");
        // end log
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows;
        try{
            totalRows = ReportFacade.countTramChuaDkCsht(techType, khuVucId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            totalRows = 0;
        } 
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
            return ("redirect:/reportTramChuaDkCSHT/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportTramChuaDkCSHT/init?page=" + pageInt);
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
        List<ReportTramChuaDkCSHTBO> list = new ArrayList<ReportTramChuaDkCSHTBO>();
        try{
            list = ReportFacade.searchTramChuaDkCsht(startRow, endRow, techType, khuVucId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        mm.addAttribute("list", list);
        mm.put("techType", techType);
        mm.put("khuVucId", khuVucId);
        mm.put("startRow", startRow);
        
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return LIST;
    }
    
    @RequestMapping(value = "/reportTramChuaDkCsht", method = RequestMethod.GET)
    public void reportTramChuaDkCsht(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "techType", required = false) String techType,
            @RequestParam(value = "khuVucId", required = false) String khuVucId) {
        List<ReportTramChuaDkCSHTBO> datas = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpId = String.join(",", tinhManager);
            khuVucId = khuVucId == null ? "" : khuVucId.replace("null", "");
        
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            try{
                logger.debug("start get data reportTramChuaDkCsht {}", Function.getInfoMemory());
                logger.info("user: {}, ip: {},techType: {}, khuVucId: {}, start get list data reportTramChuaDkCsht...", user.getUsername(),request.getRemoteAddr(),techType, khuVucId);

                datas = ReportFacade.reportTramChuaDkCsht(techType, khuVucId);

                logger.info("user: {}, ip: {},techType: {}, khuVucId: {}, end get list data reportTramChuaDkCsht...", user.getUsername(),request.getRemoteAddr(),techType, khuVucId);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            
            File fileResult =  writeTongHopGpon(dataDirectory + "/BaoCaoTramChuaDkCsht.xlsx", datas);
            
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
            logger.debug("end get data reportTramChuaDkCsht {}", Function.getInfoMemory());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    private File writeTongHopGpon(String fileTemplate, List<ReportTramChuaDkCSHTBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        SXSSFWorkbook workbook = null;
        try {

                result = new File("BaoCaoTranChuaDangKyCSHT.xlsx");
            
//            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet 1");
            if (datas != null) {
                int rowIndex = 1;
                Cell cell = null;
                Row row = null;
                
                Font font = sheet.getWorkbook().createFont();
                font.setFontHeightInPoints((short) 10);
                font.setFontName("Arial");
                font.setColor(IndexedColors.BLACK.getIndex());
                font.setBold(true);
                font.setItalic(false);
                CellStyle styleHeader = sheet.getWorkbook().createCellStyle();
                styleHeader.setFont(font);
                styleHeader.setAlignment(CellStyle.ALIGN_CENTER);
                styleHeader.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
            
                row = sheet.createRow(0);
                cell = row.createCell(0);
                cell.setCellValue("Mã trạm");
                cell.setCellStyle(styleHeader);
                
                cell = row.createCell(1);
                cell.setCellValue("Tên quản lý");
                cell.setCellStyle(styleHeader);
                
                cell = row.createCell(2);
                cell.setCellValue("Tên trên hệ thống");
                cell.setCellStyle(styleHeader);
                
                cell = row.createCell(3);
                cell.setCellValue("Loại trạm");
                cell.setCellStyle(styleHeader);
                
                cell = row.createCell(4);
                cell.setCellValue("Tên tỉnh");
                cell.setCellStyle(styleHeader);
                
                for (ReportTramChuaDkCSHTBO it : datas) {
                    row = sheet.createRow(rowIndex++);
                    
                    cell = row.createCell(0);
                    cell.setCellValue(it.getMaNode()== null ? "" : it.getMaNode());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getTenNode()== null ? "" : it.getTenNode());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getTenTrenHeThong()== null ? "" : it.getTenTrenHeThong());

                    cell = row.createCell(3);
                    cell.setCellValue(it.getLoaiTram()== null ? "" : it.getLoaiTram());
                        
                    cell = row.createCell(4);
                    cell.setCellValue(it.getProvinceName()== null ? "" : it.getProvinceName());

                }
            }

            fos = new FileOutputStream(result);
            workbook.write(fos);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

        }

        return result;
    }

}
