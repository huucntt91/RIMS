/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.bean.ReportBadCellWeekBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.LogMenuFacade;
import com.vnpt.media.rims.facade.ReportFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
@RequestMapping(value = "/reportBadCellWeek")
public class ReportBadCellWeekController {

    private static Logger logger = LogManager.getLogger(ReportBadCellWeekController.class);
    private static final String LIST = "report/badCellWeek/list";

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
            @RequestParam(value = "fromYear", required = false) String fromYear,
            @RequestParam(value = "fromWeek", required = false) String fromWeek,
            @RequestParam(value = "fromDateWeek", required = false) String fromDateWeek,
            @RequestParam(value = "toDateWeek", required = false) String toDateWeek,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            ModelMap mm, HttpServletRequest request) {
        try{
        // log user request menu
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LogMenuFacade.logMenu(String.valueOf(user.getId()), request.getRemoteAddr(), "Báo cáo bad cell theo tuần");
        // end log
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
    
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        
        
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String date = dateFormat.format(calendar.getTime());
        

        fromYear = fromYear == null ? String.valueOf(year) : fromYear;
        fromWeek = fromWeek == null ? String.valueOf(weekOfYear) : fromWeek;

        page = page == null ? "1" : page;
        //lay tu ngay den ngay khi chon tuan
        calendar.set(Calendar.YEAR, Integer.parseInt(fromYear));
        calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(fromWeek));        
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        
        
        fromDateWeek = fromDateWeek == null ? dateFormat.format(calendar.getTime()) : fromDateWeek;
        
        calendar1.set(Calendar.YEAR, Integer.parseInt(fromYear));
        calendar1.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(fromWeek)+1);        
        calendar1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        
        
        toDateWeek = toDateWeek == null ? dateFormat.format(calendar1.getTime()) : toDateWeek;
        
        
        mm.put("fromWeek", fromWeek);
        mm.put("fromDateWeek", fromDateWeek);
        mm.put("toDateWeek", toDateWeek);
        mm.put("tinhTpId", tinhTpId);
        mm.put("khuvucId", khuvucId);    
        mm.put("fromYear", fromYear); 
                
        mm.put("hiddenTime", "21/12/1989");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }
    
    @RequestMapping(value = "/reportBadCellWeek", method = RequestMethod.GET)
    public void reportBadCellWeek(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "fromYear", required = false) String fromYear,
            @RequestParam(value = "fromWeek", required = false) String fromWeek,
            @RequestParam(value = "fromDateWeek", required = false) String fromDateWeek,
            @RequestParam(value = "toDateWeek", required = false) String toDateWeek,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId) {
        try {
            
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId.replace("null", "");
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            Calendar calendar1 = Calendar.getInstance();


            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
            int monthOfYear = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String date = dateFormat.format(calendar.getTime());



            fromYear = fromYear == null ? String.valueOf(year) : fromYear;
            fromWeek = fromWeek == null ? String.valueOf(weekOfYear) : fromWeek;

    //        page = page == null ? "1" : page;
            //lay tu ngay den ngay khi chon tuan
            calendar.set(Calendar.YEAR, Integer.parseInt(fromYear));
            calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(fromWeek));        
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            

            fromDateWeek = fromDateWeek == null ? dateFormat.format(calendar.getTime()) : fromDateWeek;

            calendar1.set(Calendar.YEAR, Integer.parseInt(fromYear));
            calendar1.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(fromWeek)+1);        
            calendar1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            

            toDateWeek = toDateWeek == null ? dateFormat.format(calendar1.getTime()) : toDateWeek;
        
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<ReportBadCellWeekBO> datas2gTh = new ArrayList<>();
            try{
                datas2gTh = ReportFacade.report_clk_2g_tonghop(tinhTpId, fromDateWeek, toDateWeek);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            List<ReportBadCellWeekBO> datas2gDetail = new ArrayList<>();
            try{
                datas2gDetail = ReportFacade.report_clk_2g_detail(tinhTpId, fromDateWeek, toDateWeek);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            List<ReportBadCellWeekBO> datas3gTh = new ArrayList<>();
            try{
                datas3gTh = ReportFacade.report_clk_3g_tonghop(tinhTpId, fromDateWeek, toDateWeek);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            List<ReportBadCellWeekBO> datas3gDetail = new ArrayList<>();
            try{
                datas3gDetail = ReportFacade.report_clk_3g_detail(tinhTpId, fromDateWeek, toDateWeek);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            List<ReportBadCellWeekBO> datas2g50 = new ArrayList<>();
            try{
                datas2g50 = ReportFacade.report_sgll_2g_50(tinhTpId, fromDateWeek, toDateWeek);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            List<ReportBadCellWeekBO> datas3g50 = new ArrayList<>();
            try{
                datas3g50 = ReportFacade.report_sgll_3g_50(tinhTpId, fromDateWeek, toDateWeek);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            List<ReportBadCellWeekBO> datas2g20 = new ArrayList<>();
            try{
                datas2g20 = ReportFacade.report_sgll_2g_20(tinhTpId, fromDateWeek, toDateWeek);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            List<ReportBadCellWeekBO> datas3g20 = new ArrayList<>();
            try{
                datas3g20 = ReportFacade.report_sgll_3g_20(tinhTpId, fromDateWeek, toDateWeek);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            
            
            File fileResult = writeTongHopGpon(dataDirectory + "/BaoCaoBadCellTheoTuan.xlsx", datas2gTh, datas2gDetail, datas3gTh, datas3gDetail, datas2g50, datas3g50, datas2g20, datas3g20);
            
            
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
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    private File writeTongHopGpon(String fileTemplate, List<ReportBadCellWeekBO> datas2gTh, List<ReportBadCellWeekBO>datas2gDetail, List<ReportBadCellWeekBO>datas3gTh, List<ReportBadCellWeekBO> datas3gDetail, List<ReportBadCellWeekBO> datas2g50, List<ReportBadCellWeekBO> datas3g50, List<ReportBadCellWeekBO> datas2g20, List<ReportBadCellWeekBO> datas3g20) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            
            result = new File("BaoCaoBadCellTheoTuan.xlsx");

            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            Sheet sheet1 = workbook.getSheetAt(1);
            Sheet sheet2 = workbook.getSheetAt(2);
            Sheet sheet3 = workbook.getSheetAt(3);
            Sheet sheet4 = workbook.getSheetAt(4);
            Sheet sheet5 = workbook.getSheetAt(5);
            Sheet sheet6 = workbook.getSheetAt(6);
            Sheet sheet7 = workbook.getSheetAt(7);
            if (datas2gTh != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (ReportBadCellWeekBO it : datas2gTh) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getThoiGian()== null ? "" : it.getThoiGian());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getKhuVuc()== null ? "" : it.getKhuVuc());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getTenTinh()== null ? "" : it.getTenTinh());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getTongSoCell()== null ? "" : it.getTongSoCell());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getCssr()== null ? "" : it.getCssr());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getDcr()== null ? "" : it.getDcr());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getHosr()== null ? "" : it.getHosr());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getSoBadCell()== null ? "" : it.getSoBadCell());
                    
                    cell = row.createCell(8);
                    cell.setCellValue(it.getPhanTramBadCell()== null ? "" : it.getPhanTramBadCell());
                                 
                }
            }
            
            if (datas2gDetail != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (ReportBadCellWeekBO it : datas2gDetail) {
                    row = sheet1.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getThoiGian()== null ? "" : it.getThoiGian());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getKhuVuc()== null ? "" : it.getKhuVuc());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getTenTinh()== null ? "" : it.getTenTinh());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getVendor()== null ? "" : it.getVendor());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getBscRncName()== null ? "" : it.getBscRncName());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getBtsNodebBName()== null ? "" : it.getBtsNodebBName());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getCellName()== null ? "" : it.getCellName());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getLac()== null ? "" : it.getLac());
                    
                    cell = row.createCell(8);
                    cell.setCellValue(it.getCi()== null ? "" : it.getCi());
                    
                    cell = row.createCell(9);
                    cell.setCellValue(it.getCssr()== null ? "" : it.getCssr());
                    
                    cell = row.createCell(10);
                    cell.setCellValue(it.getDcr()== null ? "" : it.getDcr());
                    
                    cell = row.createCell(11);
                    cell.setCellValue(it.getHosr()== null ? "" : it.getHosr());
                    
                    
                                 
                }
            }
            
            if (datas3gTh != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (ReportBadCellWeekBO it : datas3gTh) {
                    row = sheet2.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getThoiGian()== null ? "" : it.getThoiGian());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getKhuVuc()== null ? "" : it.getKhuVuc());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getTenTinh()== null ? "" : it.getTenTinh());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getTongSoCell()== null ? "" : it.getTongSoCell());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getCsCssr()== null ? "" : it.getCsCssr());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getCsDcr()== null ? "" : it.getCsDcr());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getCsSoftHors()== null ? "" : it.getCsSoftHors());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getSoBadCell()== null ? "" : it.getSoBadCell());
                    
                    cell = row.createCell(8);
                    cell.setCellValue(it.getPhanTramBadCell()== null ? "" : it.getPhanTramBadCell());
                                 
                }
            }
            
            if (datas3gDetail != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (ReportBadCellWeekBO it : datas3gDetail) {
                    row = sheet3.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getThoiGian()== null ? "" : it.getThoiGian());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getKhuVuc()== null ? "" : it.getKhuVuc());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getTenTinh()== null ? "" : it.getTenTinh());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getVendor()== null ? "" : it.getVendor());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getBscRncName()== null ? "" : it.getBscRncName());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getBtsNodebBName()== null ? "" : it.getBtsNodebBName());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getCellName()== null ? "" : it.getCellName());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getLac()== null ? "" : it.getLac());
                    
                    cell = row.createCell(8);
                    cell.setCellValue(it.getCi()== null ? "" : it.getCi());
                    
                    cell = row.createCell(9);
                    cell.setCellValue(it.getCsCssr()== null ? "" : it.getCsCssr());
                    
                    cell = row.createCell(10);
                    cell.setCellValue(it.getCsDcr()== null ? "" : it.getCsDcr());
                    
                    cell = row.createCell(11);
                    cell.setCellValue(it.getCsSoftHors()== null ? "" : it.getCsSoftHors());
                                 
                }
            }
            
            if (datas2g50 != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (ReportBadCellWeekBO it : datas2g50) {
                    row = sheet4.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getThoiGian()== null ? "" : it.getThoiGian());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getTenTinh()== null ? "" : it.getTenTinh());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getVendor()== null ? "" : it.getVendor());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getBscRncName()== null ? "" : it.getBscRncName());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getBtsNodebBName()== null ? "" : it.getBtsNodebBName());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getCellName()== null ? "" : it.getCellName());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getNgayBatDauT1()== null ? "" : it.getNgayBatDauT1());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getNgayKetThucT1()== null ? "" : it.getNgayKetThucT1());
                    
                    cell = row.createCell(8);
                    cell.setCellValue(it.getNgayBatDauT2()== null ? "" : it.getNgayBatDauT2());
                    
                    cell = row.createCell(9);
                    cell.setCellValue(it.getNgayKetThucT2()== null ? "" : it.getNgayKetThucT2());
                    
                    cell = row.createCell(10);
                    cell.setCellValue(it.getCsTrafficT1()== null ? "" : it.getCsTrafficT1());
                    
                    cell = row.createCell(11);
                    cell.setCellValue(it.getCsTrafficT2()== null ? "" : it.getCsTrafficT2());
                                 
                }
            }
            
            if (datas3g50 != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (ReportBadCellWeekBO it : datas3g50) {
                    row = sheet5.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getThoiGian()== null ? "" : it.getThoiGian());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getTenTinh()== null ? "" : it.getTenTinh());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getVendor()== null ? "" : it.getVendor());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getBscRncName()== null ? "" : it.getBscRncName());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getBtsNodebBName()== null ? "" : it.getBtsNodebBName());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getCellName()== null ? "" : it.getCellName());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getNgayBatDauT1()== null ? "" : it.getNgayBatDauT1());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getNgayKetThucT1()== null ? "" : it.getNgayKetThucT1());
                    
                    cell = row.createCell(8);
                    cell.setCellValue(it.getNgayBatDauT2()== null ? "" : it.getNgayBatDauT2());
                    
                    cell = row.createCell(9);
                    cell.setCellValue(it.getNgayKetThucT2()== null ? "" : it.getNgayKetThucT2());
                    
                    cell = row.createCell(10);
                    cell.setCellValue(it.getCsTrafficT1()== null ? "" : it.getCsTrafficT1());
                    
                    cell = row.createCell(11);
                    cell.setCellValue(it.getCsTrafficT2()== null ? "" : it.getCsTrafficT2());
                                 
                }
            }
            
            if (datas2g20 != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (ReportBadCellWeekBO it : datas2g20) {
                    row = sheet6.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getThoiGian()== null ? "" : it.getThoiGian());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getTenTinh()== null ? "" : it.getTenTinh());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getVendor()== null ? "" : it.getVendor());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getBscRncName()== null ? "" : it.getBscRncName());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getBtsNodebBName()== null ? "" : it.getBtsNodebBName());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getCellName()== null ? "" : it.getCellName());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getNgayBatDauT1()== null ? "" : it.getNgayBatDauT1());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getNgayKetThucT1()== null ? "" : it.getNgayKetThucT1());
                    
                    cell = row.createCell(8);
                    cell.setCellValue(it.getNgayBatDauT2()== null ? "" : it.getNgayBatDauT2());
                    
                    cell = row.createCell(9);
                    cell.setCellValue(it.getNgayKetThucT2()== null ? "" : it.getNgayKetThucT2());
                    
                    cell = row.createCell(10);
                    cell.setCellValue(it.getNgayBatDauT3()== null ? "" : it.getNgayBatDauT3());
                    
                    cell = row.createCell(11);
                    cell.setCellValue(it.getNgayKetThucT3()== null ? "" : it.getNgayKetThucT3());
                    
                    cell = row.createCell(12);
                    cell.setCellValue(it.getCsTrafficT1()== null ? "" : it.getCsTrafficT1());
                    
                    cell = row.createCell(13);
                    cell.setCellValue(it.getCsTrafficT2()== null ? "" : it.getCsTrafficT2());
                    
                    cell = row.createCell(14);
                    cell.setCellValue(it.getCsTrafficT3()== null ? "" : it.getCsTrafficT3());
                                 
                }
            }
   
            if (datas3g20 != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (ReportBadCellWeekBO it : datas3g20) {
                    row = sheet7.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getThoiGian()== null ? "" : it.getThoiGian());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getTenTinh()== null ? "" : it.getTenTinh());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getVendor()== null ? "" : it.getVendor());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getBscRncName()== null ? "" : it.getBscRncName());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getBtsNodebBName()== null ? "" : it.getBtsNodebBName());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getCellName()== null ? "" : it.getCellName());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getNgayBatDauT1()== null ? "" : it.getNgayBatDauT1());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getNgayKetThucT1()== null ? "" : it.getNgayKetThucT1());
                    
                    cell = row.createCell(8);
                    cell.setCellValue(it.getNgayBatDauT2()== null ? "" : it.getNgayBatDauT2());
                    
                    cell = row.createCell(9);
                    cell.setCellValue(it.getNgayKetThucT2()== null ? "" : it.getNgayKetThucT2());
                    
                    cell = row.createCell(10);
                    cell.setCellValue(it.getNgayBatDauT3()== null ? "" : it.getNgayBatDauT3());
                    
                    cell = row.createCell(11);
                    cell.setCellValue(it.getNgayKetThucT3()== null ? "" : it.getNgayKetThucT3());
                    
                    cell = row.createCell(12);
                    cell.setCellValue(it.getCsTrafficT1()== null ? "" : it.getCsTrafficT1());
                    
                    cell = row.createCell(13);
                    cell.setCellValue(it.getCsTrafficT2()== null ? "" : it.getCsTrafficT2());
                    
                    cell = row.createCell(14);
                    cell.setCellValue(it.getCsTrafficT3()== null ? "" : it.getCsTrafficT3());
                                 
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
