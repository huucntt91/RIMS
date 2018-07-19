/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.bean.ReportTrafficTramBO;
import com.vnpt.media.rims.controller.broadband.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ReportFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@RequestMapping(value = "/reportCiIub")
public class ReportCiIubController {

    private static Logger logger = LogManager.getLogger(ReportCiIubController.class);
    private static final String LIST = "report/cIIub/list";

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
            @RequestParam(value = "timeType", required = false) String timeType,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "fromYear", required = false) String fromYear,
            @RequestParam(value = "fromWeek", required = false) String fromWeek,
            @RequestParam(value = "fromMonth", required = false) String fromMonth,
            @RequestParam(value = "toYear", required = false) String toYear,
            @RequestParam(value = "toWeek", required = false) String toWeek,
            @RequestParam(value = "toMonth", required = false) String toMonth,
            @RequestParam(value = "hiddenTime", required = false) String hiddenTime,
            @RequestParam(value = "fromDateWeek", required = false) String fromDateWeek,
            @RequestParam(value = "toDateWeek", required = false) String toDateWeek,
            @RequestParam(value = "fromDateMonth", required = false) String fromDateMonth,
            @RequestParam(value = "toDateMonth", required = false) String toDateMonth,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            ModelMap mm, HttpServletRequest request) {
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        
      
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        
        
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String date = dateFormat.format(calendar.getTime());
        
        
        
        
        
        
        
        techType = techType == null ? "2" : techType;
        timeType = timeType == null ? "1" : timeType;
        fromDate = fromDate == null ? date : fromDate;
        toDate = toDate == null ? date : toDate;
        fromYear = fromYear == null ? String.valueOf(year) : fromYear;
        toYear = toYear == null ? String.valueOf(year) : toYear;
        fromWeek = fromWeek == null ? String.valueOf(weekOfYear) : fromWeek;
        toWeek = toWeek == null ? String.valueOf(weekOfYear) : toWeek;
        fromMonth = fromMonth == null ? String.valueOf(monthOfYear) : fromMonth;
        toMonth = toMonth == null ? String.valueOf(monthOfYear) : toMonth;

        page = page == null ? "1" : page;
        //lay tu ngay den ngay khi chon tuan
        calendar.set(Calendar.YEAR, Integer.parseInt(fromYear));
        calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(fromWeek));        
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        
        
        fromDateWeek = fromDateWeek == null ? dateFormat.format(calendar.getTime()) : fromDateWeek;
        
        calendar1.set(Calendar.YEAR, Integer.parseInt(toYear));
        calendar1.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(toWeek)+1);        
        calendar1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        
        
        toDateWeek = toDateWeek == null ? dateFormat.format(calendar1.getTime()) : toDateWeek;
        
        //lay tu ngay den ngay khi chon thang
        calendar2.set(Calendar.YEAR, Integer.parseInt(fromYear));
        calendar2.set(Calendar.MONTH, Integer.parseInt(fromMonth) - 1);        
        calendar2.set(Calendar.DAY_OF_MONTH, 1);
        
        
        fromDateMonth = fromDateMonth == null ? dateFormat.format(calendar2.getTime()) : fromDateMonth;
        
        calendar3.set(Calendar.YEAR, Integer.parseInt(toYear));
        calendar3.set(Calendar.MONTH, Integer.parseInt(toMonth) - 1);        
        calendar3.set(Calendar.DAY_OF_MONTH, calendar3.getActualMaximum(Calendar.DATE));
        
        
        toDateMonth = toDateMonth == null ? dateFormat.format(calendar3.getTime()) : toDateMonth;
        
        Integer pageInt = Integer.parseInt(page);

        int totalRows = ReportFacade.countTrafficTram(techType, timeType, khuvucId, tinhTpId, fromDate, toDate, fromDateWeek, toDateWeek, fromDateMonth, toDateMonth); 

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
            return ("redirect:/reportTrafficTram/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportTrafficTram/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Report traffic muc tram");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        
        List<ReportTrafficTramBO> list = ReportFacade.searchTrafficTram(startRow, endRow, techType, timeType, khuvucId, tinhTpId, fromDate, toDate, fromDateWeek, toDateWeek, fromDateMonth, toDateMonth);//(List<DsLamBO>) AccessFacade.getInstance().findAll(startRow, endRow, "", code, name, typeId,khuvucId, provinceId, districtId, wardsId);
        mm.addAttribute("list", list);
        mm.put("techType", techType);
        mm.put("timeType", timeType);
        mm.put("startRow", startRow);
        mm.put("toDate", toDate);
        mm.put("fromDate", fromDate);
        mm.put("fromYear", fromYear);
        mm.put("toYear", toYear);
        mm.put("fromWeek", fromWeek);
        mm.put("toWeek", toWeek);
        mm.put("fromMonth", fromMonth);
        mm.put("toMonth", toMonth);
        mm.put("fromDateWeek", fromDateWeek);
        mm.put("toDateWeek", toDateWeek);
        mm.put("tinhTpId", tinhTpId);
        mm.put("khuvucId", khuvucId);     
                
        mm.put("hiddenTime", "21/12/1989");

        return LIST;
    }
    
    @RequestMapping(value = "/reportTrafficTram", method = RequestMethod.GET)
    public void reportThGpon(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "techType", required = false) String techType,
            @RequestParam(value = "timeType", required = false) String timeType,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "fromWeek", required = false) String fromWeek,
            @RequestParam(value = "toWeek", required = false) String toWeek,
            @RequestParam(value = "fromMonth", required = false) String fromMonth,
            @RequestParam(value = "toMonth", required = false) String toMonth,
            @RequestParam(value = "fromYear", required = false) String fromYear,
            @RequestParam(value = "toYear", required = false) String toYear,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId) {
        try {
            
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId.replace("null", "");
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            Calendar calendar3 = Calendar.getInstance();


            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
            int monthOfYear = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String date = dateFormat.format(calendar.getTime());
            
            //lay tu ngay den ngay khi chon tuan
            calendar.set(Calendar.YEAR, Integer.parseInt(fromYear));
            calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(fromWeek));        
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            

            String fromDateWeek = dateFormat.format(calendar.getTime());

            calendar1.set(Calendar.YEAR, Integer.parseInt(toYear));
            calendar1.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(toWeek)+1);        
            calendar1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            

            String toDateWeek = dateFormat.format(calendar1.getTime());

            //lay tu ngay den ngay khi chon thang
            calendar2.set(Calendar.YEAR, Integer.parseInt(fromYear));
            calendar2.set(Calendar.MONTH, Integer.parseInt(fromMonth) - 1);        
            calendar2.set(Calendar.DAY_OF_MONTH, 1);
            

            String fromDateMonth = dateFormat.format(calendar2.getTime());

            calendar3.set(Calendar.YEAR, Integer.parseInt(toYear));
            calendar3.set(Calendar.MONTH, Integer.parseInt(toMonth) - 1);        
            calendar3.set(Calendar.DAY_OF_MONTH, calendar3.getActualMaximum(Calendar.DATE));
            

            String toDateMonth = dateFormat.format(calendar3.getTime());
        
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<ReportTrafficTramBO> datas = ReportFacade.reportTrafficTram(techType, timeType, khuvucId, tinhTpId, fromDate, toDate, fromDateWeek, toDateWeek, fromDateMonth, toDateMonth);
            File fileResult = null;
            if(techType.equals("2")){
                fileResult = writeTongHopGpon(dataDirectory + "/BaoCaoTrafficMucTram2G.xlsx", datas, techType);
            }
            if(techType.equals("3")){
                fileResult = writeTongHopGpon(dataDirectory + "/BaoCaoTrafficMucTram3G.xlsx", datas, techType);
            }
            if(techType.equals("4")){
                fileResult = writeTongHopGpon(dataDirectory + "/BaoCaoTrafficMucTram4G.xlsx", datas, techType);
            }
            
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    private File writeTongHopGpon(String fileTemplate, List<ReportTrafficTramBO> datas, String techType) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            if(techType.equals("2")){
                result = new File("BaoCaoTrafficMucTram2G.xlsx");
            }
            if(techType.equals("3")){
                result = new File("BaoCaoTrafficMucTram3G.xlsx");
            }
            if(techType.equals("4")){
                result = new File("BaoCaoTrafficMucTram4G.xlsx");
            }
            
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 1;
                Cell cell = null;
                Row row = null;
                for (ReportTrafficTramBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getProvinceCode()== "0" ? "" : it.getProvinceCode());
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getProvinceName()== "0" ? "" : it.getProvinceName());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getDate()== "0" ? "" : it.getDate());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getTenNode()== "0" ? "" : it.getTenNode());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getVendor()== "0" ? "" : it.getVendor());
                    
                    if(techType.equals("2") || techType.equals("3")){
                        cell = row.createCell(5);
                        cell.setCellValue(it.getBscRncName()== "0" ? "" : it.getBscRncName());
                        cell = row.createCell(6);
                        cell.setCellValue(it.getTraffic2g3gCs()== "0" ? "" : it.getTraffic2g3gCs());
                        cell = row.createCell(7);
                        cell.setCellValue(it.getTraffic2g3g4gPs()== "0" ? "" : it.getTraffic2g3g4gPs());
                    }
                    if(techType.equals("4")){
                        cell = row.createCell(5);
                        cell.setCellValue(it.getTraffic2g3g4gPs()== "0" ? "" : it.getTraffic2g3g4gPs());
                    }
              
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
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        return result;
    }

}
