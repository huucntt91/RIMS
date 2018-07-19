/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.DsLamBO;
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
@RequestMapping(value = "/cellUnexpect")
public class CellUnexpectedController {

    private static Logger logger = LogManager.getLogger(CellUnexpectedController.class);
    private static final String LIST = "nodes/cellBatThuong/list";

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
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
            return ("redirect:/cellUnexpect/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/cellUnexpect/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý cell bat thuong");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        
        List<DsLamBO> list = null;//(List<DsLamBO>) AccessFacade.getInstance().findAll(startRow, endRow, "", code, name, typeId,khuvucId, provinceId, districtId, wardsId);
        mm.addAttribute("list", list);
        mm.put("fromDate", fromDate);
        mm.put("toDate", toDate);
        mm.put("startRow", startRow);
        return LIST;
    }

//    trunglk_start
//    @RequestMapping(value = "/preReportL2sw", method = RequestMethod.GET)
//    public String preReportL2sw(ModelMap mm, HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestParam(value = "page", required = false) String page,
//            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
//    )  {
//        List<L2swReportBO> datas = AccessFacade.reportL2sw(tinhTpId);
//        mm.addAttribute("list", datas);
//        mm.put("tinhTpId", tinhTpId);
//        
//        return REPORT_L2SW;
//    }
//    
//    @RequestMapping(value = "/reportThL2sw", method = RequestMethod.GET)
//    public void reportThL2sw(HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
//        try {
//
//            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
//            List<L2swReportBO> datas = AccessFacade.reportL2sw(tinhTpId);
//            File fileResult = writeTongHopL2sw(dataDirectory + "/ThongKeChungLoaiThietBiL2SW.xlsx", datas);
//            if (fileResult.exists()) {
//                response.setContentType("application/excel");
//                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
//                try {
//                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
//                    response.getOutputStream().flush();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        } catch (ServiceException e) {
//            logger.error(e.getMessage(), e);
//        }
//    }
//
//    private File writeTongHopL2sw(String fileTemplate, List<L2swReportBO> datas) {
//        File result = null;
//        FileOutputStream fos = null;
//        Workbook workbook = null;
//        try {
//            result = new File("TongHopL2sw.xlsx");
//            FileInputStream fin = new FileInputStream(fileTemplate);
//            workbook = new XSSFWorkbook(fin);
//            Sheet sheet = workbook.getSheetAt(0);
//            if (datas != null) {
//                int rowIndex = 3;
//                Cell cell = null;
//                Row row = null;
//                for (L2swReportBO it : datas) {
//                    row = sheet.createRow(rowIndex++);
//
//                    cell = row.createCell(0);
//                    cell.setCellValue(it.getTen_tinh_tp()== "0" ? "" : it.getTen_tinh_tp());
//                    
//                    cell = row.createCell(1);
//                    cell.setCellValue(it.getTong_tb()== "0" ? "" : it.getTong_tb());
//                    
//                    cell = row.createCell(2);
//                    cell.setCellValue(it.getAT_FS750()== "0" ? "" : it.getAT_FS750());
//                    
//                    cell = row.createCell(3);
//                    cell.setCellValue(it.getBATM_T5C()== "0" ? "" : it.getBATM_T5C());
//                    
//                    cell = row.createCell(4);
//                    cell.setCellValue(it.getC2960()== "0" ? "" : it.getC2960());
//                    
//                    cell = row.createCell(5);
//                    cell.setCellValue(it.getC3560()== "0" ? "" : it.getC3560());
//                    
//                    cell = row.createCell(6);
//                    cell.setCellValue(it.getCE500()== "0" ? "" : it.getCE500());
//                    
//                    cell = row.createCell(7);
//                    cell.setCellValue(it.getES_2226C()== "0" ? "" : it.getES_2226C());
//                    
//                    cell = row.createCell(8);
//                    cell.setCellValue(it.getISCOM2126()== "0" ? "" : it.getISCOM2126());
//                    
//                    cell = row.createCell(9);
//                    cell.setCellValue(it.getLINKSYS()== "0" ? "" : it.getLINKSYS());
//                    
//                    cell = row.createCell(10);
//                    cell.setCellValue(it.getLS62XX()== "0" ? "" : it.getLS62XX());
//                    
//                    cell = row.createCell(11);
//                    cell.setCellValue(it.getME340X()== "0" ? "" : it.getME340X());
//                    
//                    cell = row.createCell(12);
//                    cell.setCellValue(it.getMES3500_24F()== "0" ? "" : it.getMES3500_24F());
//                    
//                    cell = row.createCell(13);
//                    cell.setCellValue(it.getO62XX()== "0" ? "" : it.getO62XX());
//                    
//                    cell = row.createCell(14);
//                    cell.setCellValue(it.getO6424()== "0" ? "" : it.getO6424());
//                    
//                    cell = row.createCell(15);
//                    cell.setCellValue(it.getO6450()== "0" ? "" : it.getO6450());
//                    
//                    cell = row.createCell(16);
//                    cell.setCellValue(it.getO64XX()== "0" ? "" : it.getO64XX());
//                    
//                    cell = row.createCell(17);
//                    cell.setCellValue(it.getRAISECOM2828()== "0" ? "" : it.getRAISECOM2828());
//                    
//                    cell = row.createCell(18);
//                    cell.setCellValue(it.getRT2126()== "0" ? "" : it.getRT2126());
//                    
//                    cell = row.createCell(19);
//                    cell.setCellValue(it.getS2226_SFP()== "0" ? "" : it.getS2226_SFP());
//                    
//                    cell = row.createCell(20);
//                    cell.setCellValue(it.getS3328()== "0" ? "" : it.getS3328());
//                    
//                    cell = row.createCell(21);
//                    cell.setCellValue(it.getS4100()== "0" ? "" : it.getS4100());
//                    
//                    cell = row.createCell(22);
//                    cell.setCellValue(it.getS53XX()== "0" ? "" : it.getS53XX());
//                    
//                    cell = row.createCell(23);
//                    cell.setCellValue(it.getSF300()== "0" ? "" : it.getSF300());
//                    
//                    cell = row.createCell(24);
//                    cell.setCellValue(it.getTERRABIT()== "0" ? "" : it.getTERRABIT());
//                    
//                    cell = row.createCell(25);
//                    cell.setCellValue(it.getV6328()== "0" ? "" : it.getV6328());
//                    
//                    cell = row.createCell(26);
//                    cell.setCellValue(it.getVFT22XX()== "0" ? "" : it.getVFT22XX());
//                    
//                    cell = row.createCell(27);
//                    cell.setCellValue(it.getZTE3928()== "0" ? "" : it.getZTE3928());
//                    
//                    
//
//                    
//
//                }
//            }
//
//            fos = new FileOutputStream(result);
//            workbook.write(fos);
//
//        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//
//            try {
//                if (workbook != null) {
//                    workbook.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
//        }
//
//        return result;
//    }
//    
//    
//    trunglk_end
}
