/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.bean.ChiTietCardBO;
import com.vnpt.media.rims.bean.ConfEventDefine;
import com.vnpt.media.rims.bean.EvcBO;
import com.vnpt.media.rims.bean.EventReportBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.EvcFacade;
import com.vnpt.media.rims.facade.LogMenuFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.ReportBBFacade;
import com.vnpt.media.rims.facade.ReportFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
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
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
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
@RequestMapping(value = "/event")
public class EventController {
    private static Logger logger = LogManager.getLogger(EventController.class);
    private static final String EVENT_REPORT = "report/event/event_report";
    
     //list khu vuc
    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllKhuVuc(String.join(",", tinhManager));
    }
    
      @ModelAttribute("tinhThanhPhoLst")
    public List findTinhThanhPho(HttpServletRequest request) {
        List<TinhBO> list = null;
        try {
            ManagerAdminFacade facade = new ManagerAdminFacade();
            list = facade.findAllTinh("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }
    
     //list objectype
    @ModelAttribute("objects")
    public List findObject(HttpServletRequest request) {
        ReportFacade facade = new ReportFacade();
        return facade.findObject();
    }
    //list event
//    @ModelAttribute("events")
//    public List findEvent(HttpServletRequest request) {
//        ReportFacade facade = new ReportFacade();
//        return facade.findEvent("");
//    }
    
     @RequestMapping(value = "/getEvent", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getEvent(@RequestParam(value = "objecTypeId", required = false) String objecTypeId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<ConfEventDefine> list = null;
        try {
            ReportFacade facade = new ReportFacade();
            list = facade.findEvent(objecTypeId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
     @RequestMapping(value = "/preEventReport", method = RequestMethod.GET)
    public String preEventReport(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "eventDefineId", required = false) String eventDefineId,
            @RequestParam(value = "objectTypeId", required = false) String objectTypeId,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            ModelMap mm, HttpServletRequest request) {
        // log user request menu
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LogMenuFacade.logMenu(String.valueOf(user.getId()), request.getRemoteAddr(), "Báo cáo Event");
        // end log
        
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = ReportFacade.countEvent(khuvucId, tinhTpId, eventDefineId, objectTypeId, startTime, endTime);
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
            return ("redirect:/event/preEventReport?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/event/preEventReport?page=" + pageInt);
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
        List<EventReportBO> list = ReportFacade.searchEvent(startRow, endRow,khuvucId,tinhTpId,eventDefineId,objectTypeId,startTime,endTime );
        mm.addAttribute("list", list);
//        mm.put("khuvucId", khuvucId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("eventDefineId", eventDefineId);
        mm.put("objectTypeId", objectTypeId);
       
        mm.put("startTime",DateTimeUtils.convertStringToTime(startTime, "dd/MM/yyyy"));
        mm.put("endTime",DateTimeUtils.convertStringToTime(endTime, "dd/MM/yyyy") );
        mm.put("startRow", startRow);
        return EVENT_REPORT;
    }
   @RequestMapping(value = "/eventReport", method = RequestMethod.GET)
    public void eventReport(HttpServletRequest request,
            HttpServletResponse response,
              @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "eventDefineId", required = false) String eventDefineId,
            @RequestParam(value = "objectTypeId", required = false) String objectTypeId,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            if (tinhTpId !=null) tinhTpId=tinhTpId.replace("null", "");
            if (eventDefineId !=null) eventDefineId=eventDefineId.replace("null", "");
            if (objectTypeId !=null) objectTypeId=objectTypeId.replace("null", "");
            if (khuvucId !=null) khuvucId=khuvucId.replace("null", "");
            List<EventReportBO> datas = ReportFacade.eventReport(khuvucId, tinhTpId, eventDefineId, objectTypeId, startTime, endTime);
            File fileResult = writeEventReport(dataDirectory + "/EventReport.xlsx", datas);
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
    private File writeEventReport(String fileTemplate, List<EventReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("EventReport.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 1;
                Cell cell = null;
                Row row = null;
                int count = 1;
                for (EventReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(count);
                    
                    cell = row.createCell(1);
                    cell.setCellValue(it.getObjectCode());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getEventName());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getEventDescription());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getEventValue());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getStartTime());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getEndTime());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getStatus());
                    
                    cell = row.createCell(8);
                    cell.setCellValue(it.getHandlingStatus());
                    count++;

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
