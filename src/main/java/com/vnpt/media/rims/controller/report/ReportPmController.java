/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.bean.KpiPmBO;
import com.vnpt.media.rims.bean.ReportPmBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.exception.ServiceException;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
@RequestMapping(value = "/reportPm")
public class ReportPmController {

    private static Logger logger = LogManager.getLogger(ReportPmController.class);
    private static final String LIST = "report/kpi/list";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllKhuVuc(String.join(",", tinhManager));
    }
    
    //list tinh
    @RequestMapping(value = "/getKpiPm", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getKpiPm(@RequestParam(value = "techType", required = false) String neTypeId, 
            ModelMap mm,
            HttpServletRequest request) {
        List<KpiPmBO> list = null;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            list = facade.findKpiListByNeType(neTypeId);
//            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            List<KpiPmBO> temp = new ArrayList<KpiPmBO>();

                temp = list;


            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(temp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "techType", required = false) String techType,
            @RequestParam(value = "kpiId", required = false) String kpiId,
            @RequestParam(value = "timeType", required = false) String timeType,
            @RequestParam(value = "dbSelect", required = false) String dbSelect,
            @RequestParam(value = "fromDateHour", required = false) String fromDateHour,
            @RequestParam(value = "toDateHour", required = false) String toDateHour,
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
            ModelMap mm, HttpServletRequest request) {
        try{
        // log user request menu
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LogMenuFacade.logMenu(String.valueOf(user.getId()), request.getRemoteAddr(), "Báo cáo PM");
        // end log
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;

        DateFormat dateFormatHour = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();

        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String date = dateFormat.format(calendar.getTime());
        String dateHour = dateFormatHour.format(calendar.getTime());

        
        
        
        

        techType = techType == null ? "2" : techType;
        timeType = timeType == null ? "1" : timeType;
        kpiId = kpiId == null ? "" : kpiId;
        dbSelect = dbSelect == null ? "1" : dbSelect;
        fromDateHour = fromDateHour == null ? dateHour : fromDateHour;
        toDateHour = toDateHour == null ? dateHour : toDateHour;
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
        calendar1.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(toWeek) + 1);
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


        mm.put("khuvucId", khuvucId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("techType", techType);
        mm.put("timeType", timeType);
        mm.put("kpiId", kpiId);
        mm.put("dbSelect", dbSelect);
        mm.put("fromDateHour", fromDateHour);
        mm.put("toDateHour", toDateHour);       
        mm.put("fromDate", fromDate);
        mm.put("toDate", toDate);        
        mm.put("fromYear", fromYear);
        mm.put("toYear", toYear);
        mm.put("fromWeek", fromWeek);
        mm.put("toWeek", toWeek);
        mm.put("fromMonth", fromMonth);
        mm.put("toMonth", toMonth);
        mm.put("fromDateWeek", fromDateWeek);
        mm.put("toDateWeek", toDateWeek);
        

        mm.put("hiddenTime", "21/12/1989");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }

    @RequestMapping(value = "/reportKpiPm", method = RequestMethod.GET)
    public void reportKpiPm(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "techType", required = false) String techType,
            @RequestParam(value = "kpiId", required = false) String kpiId,
            @RequestParam(value = "timeType", required = false) String timeType,
            @RequestParam(value = "dbSelect", required = false) String dbSelect,
            @RequestParam(value = "fromDateHour", required = false) String fromDateHour,
            @RequestParam(value = "toDateHour", required = false) String toDateHour,
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
            @RequestParam(value = "toDateMonth", required = false) String toDateMonth
    ) {
        List<ReportPmBO> datas = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {}, start reportPM...", user.getUsername(),request.getRemoteAddr());
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId.replace("null", "");

            DateFormat dateFormatHour = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            Calendar calendar3 = Calendar.getInstance();

            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
            int monthOfYear = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String date = dateFormat.format(calendar.getTime());
            String dateHour = dateFormatHour.format(calendar.getTime());

            
            techType = techType == null ? "2" : techType;
            timeType = timeType == null ? "1" : timeType;
            kpiId = kpiId == null ? "" : kpiId;
            dbSelect = dbSelect == null ? "1" : dbSelect;
            fromDateHour = fromDateHour == null ? dateHour : fromDateHour;
            toDateHour = toDateHour == null ? dateHour : toDateHour;
            fromDate = fromDate == null ? date : fromDate;
            toDate = toDate == null ? date : toDate;
            fromYear = fromYear == null ? String.valueOf(year) : fromYear;
            toYear = toYear == null ? String.valueOf(year) : toYear;
            fromWeek = fromWeek == null ? String.valueOf(weekOfYear) : fromWeek;
            toWeek = toWeek == null ? String.valueOf(weekOfYear) : toWeek;
            fromMonth = fromMonth == null ? String.valueOf(monthOfYear) : fromMonth;
            toMonth = toMonth == null ? String.valueOf(monthOfYear) : toMonth;

    //        page = page == null ? "1" : page;
            //lay tu ngay den ngay khi chon tuan
            calendar.set(Calendar.YEAR, Integer.parseInt(fromYear));
            calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(fromWeek));
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            

            fromDateWeek = fromDateWeek == null ? dateFormat.format(calendar.getTime()) : fromDateWeek;

            calendar1.set(Calendar.YEAR, Integer.parseInt(toYear));
            calendar1.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(toWeek) + 1);
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

            
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            int[] total_ar = new int[1];
            try{
            logger.debug("start get data reportPM {}", Function.getInfoMemory());
            logger.info("user: {}, ip: {},techType: {}, timeType: {}, kpiId: {}, tinhTpId: {}, dbSelect: {}, fromDateHour: {}, toDateHour: {}, fromDate: {}, toDate: {}, fromDateWeek: {}, toDateWeek: {}, fromDateMonth: {}, toDateMonth: {}, total_ar: {}, start get list data reportPM...", user.getUsername(),request.getRemoteAddr(),techType, timeType, kpiId, tinhTpId, dbSelect, fromDateHour, toDateHour, fromDate, toDate, fromDateWeek, toDateWeek, fromDateMonth, toDateMonth, total_ar);
            
             datas = ReportFacade.reportPm(-1, -1, techType, timeType, kpiId, tinhTpId, dbSelect, fromDateHour, toDateHour, fromDate, toDate, fromDateWeek, toDateWeek, fromDateMonth, toDateMonth, total_ar);
            
            logger.info("user: {}, ip: {},techType: {}, timeType: {}, kpiId: {}, tinhTpId: {}, dbSelect: {}, fromDateHour: {}, toDateHour: {}, fromDate: {}, toDate: {}, fromDateWeek: {}, toDateWeek: {}, fromDateMonth: {}, toDateMonth: {}, total_ar: {}, end get list data reportPM...", user.getUsername(),request.getRemoteAddr(),techType, timeType, kpiId, tinhTpId, dbSelect, fromDateHour, toDateHour, fromDate, toDate, fromDateWeek, toDateWeek, fromDateMonth, toDateMonth, total_ar);            
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } 
            
            logger.info("user: {}, ip: {}, start output exels reportPM...", user.getUsername(),request.getRemoteAddr());
            
            File fileResult =  writeKpiPm(dataDirectory + "/KpiPmReport.xlsx", datas, techType, kpiId);

            logger.info("user: {}, ip: {}, end output exels reportPM...", user.getUsername(),request.getRemoteAddr());
            
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
            logger.debug("end get data reportPM {}", Function.getInfoMemory());
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }finally{
            if (datas != null) {
            datas.clear();
            }
        }
    }

    private File writeKpiPm(String fileTemplate, List<ReportPmBO> datas, String techType, String listKpi) {
        File result = null;
        FileOutputStream fos = null;
        SXSSFWorkbook workbook = null;
        try {

            result = new File("KpiPmReport.xlsx");
            
//            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new SXSSFWorkbook();
            
            Sheet sheet = workbook.createSheet("Sheet 1");
            
            CellStyle style;
            DataFormat format = workbook.createDataFormat();
            style = workbook.createCellStyle();
            style.setDataFormat(format.getFormat("0.00000"));
            if (datas != null) {
                
                int label = 8;
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                int x = 0;
                int y = 0;
                
                row = sheet.createRow(2);
                
                cell = row.createCell(0);
                cell.setCellValue("START_DATE");

                cell = row.createCell(1);
                cell.setCellValue("CELL CODE");
                    
                cell = row.createCell(2);
                cell.setCellValue("CELL NAME");
                    
                cell = row.createCell(3);
                cell.setCellValue("BTS");
                    
                cell = row.createCell(4);
                cell.setCellValue("BSC_RNC");
                    
                cell = row.createCell(5);
                cell.setCellValue("Khu vực");
                    
                cell = row.createCell(6);
                cell.setCellValue("Tỉnh/ Thành phố");
                    
                cell = row.createCell(7);
                cell.setCellValue("Quận/ Huyện");
                
                String[] kpi = null;
                if(listKpi != null && !listKpi.isEmpty()){
                     kpi = listKpi.split(",");

                     for (String kpiColum: kpi) {
                         if(techType.equals("2")){
                             if (kpiColum.equalsIgnoreCase("CALVOL")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Call volume");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSRV1")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSRV2")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Call Setup Success Rate V2");
                             }
                             if (kpiColum.equalsIgnoreCase("DCR")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Drop call rate");
                             }
                             if (kpiColum.equalsIgnoreCase("DCRV2")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Drop Call Rate V2");
                             }
                             if (kpiColum.equalsIgnoreCase("DL_TBF_DROP_RATE")){
                                cell = row.createCell(label++);
                                cell.setCellValue("DownLink TBF Drop Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSRV2")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Handover Success Rate V2");
                             }
                             if (kpiColum.equalsIgnoreCase("PS_TBF_SR_2G")){
                                cell = row.createCell(label++);
                                cell.setCellValue("DownLink TBF Establishment Success Rate (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("PS_TRAFFIC_2G")){
                                cell = row.createCell(label++);
                                cell.setCellValue("PS_TRAFFIC_2G");
                             }
                             if (kpiColum.equalsIgnoreCase("PS_UL_TBF_SR_2G")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Uplink TBF Establishment Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("SDCCHBLKR")){
                                cell = row.createCell(label++);
                                cell.setCellValue("SDCCH Blocking Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("TCHBLR")){
                                cell = row.createCell(label++);
                                cell.setCellValue("TCH Blocking Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Traffic");
                             }
                             if (kpiColum.equalsIgnoreCase("UL_TBF_DROP_RATE")){
                                cell = row.createCell(label++);
                                cell.setCellValue("UpLink TBF Drop Rate");
                             }
                             
                         }
                         if(techType.equals("3")){
                             if (kpiColum.equalsIgnoreCase("CALLVOLUME")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Voice Call Volume");
                             }
                             if (kpiColum.equalsIgnoreCase("CSCONGES")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_RAB Congestion Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSINTERFREQHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Inter-Freq Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSIRATHOSRWEIGHT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Inter-RAT Handover Success Rate weight");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSRVIDEOPHONE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Video Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSVIDEODROPCALLRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Video Drop Call Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSVIDEOTRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Video Traffic");
                             }
                             if (kpiColum.equalsIgnoreCase("CSVOICECSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Voice Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSVOICEDROPCALLRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Voice Drop Call Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("DCR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Drop Call Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("DLTRAFFICPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("V2_DL Traffic PS");
                             }
                             if (kpiColum.equalsIgnoreCase("HSDPATHROUGHPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSDPA Cell Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("IRATHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Inter-RAT Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSCONGES")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_RAB Congestion Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSCSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSDCR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99_HSPA_D_R");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSDPATPKBPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSDPA Cell Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSDPATRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSDPA Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSPACALLDROPRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSPA Call Drop Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSPACSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSPA Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSPATRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSPA Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSUPATPKBPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSUPA Cell Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSUPATRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSUPA Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSIRATHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Inter-RAT Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99CALLDROPRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Call Drop Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99CALLSETUPSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99DLTRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Down Link Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99TRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99UPLINKTRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Up Link Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSTRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Total Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("R99DLTHROUGHPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Cell Down Link Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("R99ULTHROUGHPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Cell Up Link Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("SOFTHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Soft/Softer Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("SOFTHOSRPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Soft/Softer Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Total Traffic");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFICACTIVESETCS64")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Total Active Set Traffic");
                             }
                             if (kpiColum.equalsIgnoreCase("ULTRAFFICPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("V2_UL Traffic PS");
                             }
                             if (kpiColum.equalsIgnoreCase("V2INTERFREQHOSRPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Inter-Freq Handover Success Rate");
                             }
                         }
                         if(techType.equals("4")){
                             if (kpiColum.equalsIgnoreCase("AVAILABLE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Avaiable");
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_DL_AVG_THPUTs")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Cell PDCP Downlink Average Throughput");
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_DL_MAX_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Cell PDCP Downlink Max Throughput");
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_UL_AVG_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Cell PDCP Uplink Average Throughput");
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_UL_MAX_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CEll PDCP Uplink Max Throughput");
                             }
                             if (kpiColum.equalsIgnoreCase("CSFB_SSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("E-UTRAN Initial Context Setup Success Ratio being Subject for CS Fallback (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Call Setup Success Rate (CSSR) (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("DL_LATENCY")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Downlink Latency");
                             }
                             if (kpiColum.equalsIgnoreCase("ERAB_SSRATE_ALL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("eRAB Setup Success Rate (all services) (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSRX2")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Handover Success Rate via X2 (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_EXE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter RAT HO SR (execution phase)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_LTE_GSM")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter-RAT HOSR (LTE to GSM) (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_LTE_WCDMA")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter-RAT HOSR (LTE to WCDMA) (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_PRE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter RAT HO Preparation Success Ratio (preparation phase)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_PRE_EXE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter RAT Total HO SR (from HO preparation start until successful HO execution)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_S1")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Handover Success Rate via S1 (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("INTER_FREQUENCY_HO")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter-frequency HO (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("INTRA_ENODEB_HOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Intra eNB HO SR total");
                             }
                             if (kpiColum.equalsIgnoreCase("INTRA_FREQUENCY_HO")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Intra-frequency HO (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("RES_BLK_DL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Resource Block Untilizing Rate Downlink (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("RES_BLK_UL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Resource Block Untilizing Rate Uplink (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("RRC_SSRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("RRC Setup Success Rate (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("SERVICE_DROP_ALL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Service Drop (all service)");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Total Data Traffic Volume (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC_VOL_DL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Traffic Volumn DL (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC_VOL_UL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Traffic Volume UL (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("UL_LATENCY")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Uplink Latency");
                             }
                             if (kpiColum.equalsIgnoreCase("UNVAILABLE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Unavailable");
                             }
                             if (kpiColum.equalsIgnoreCase("USER_DL_AVG_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("User Downlink Average Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("USER_UL_AVG_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("User Uplink Average Throughput (Kbps)");
                             }
                         }
                         
                        
                     }
                }
                
                
                for (ReportPmBO it : datas) {
                    x = x+1;
                    label = 8;
                    if(x%30000 == 0){
                        rowIndex = 3;
                        label = 8;
                        sheet = workbook.createSheet(String.valueOf(y++)); 
                        row = sheet.createRow(0);
                
                        cell = row.createCell(0);
                        cell.setCellValue("START_DATE");

                        cell = row.createCell(1);
                        cell.setCellValue("CELL CODE");

                        cell = row.createCell(2);
                        cell.setCellValue("CELL NAME");

                        cell = row.createCell(3);
                        cell.setCellValue("BTS");

                        cell = row.createCell(4);
                        cell.setCellValue("BSC_RNC");

                        cell = row.createCell(5);
                        cell.setCellValue("Khu vực");

                        cell = row.createCell(6);
                        cell.setCellValue("Tỉnh/ Thành phố");

                        cell = row.createCell(7);
                        cell.setCellValue("Quận/ Huyện");

//                        String[] kpi = null;
                        if(listKpi != null && !listKpi.isEmpty()){
                             kpi = listKpi.split(",");

                             for (String kpiColum: kpi) {
                                if(techType.equals("2")){
                             if (kpiColum.equalsIgnoreCase("CALVOL")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Call volume");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSRV1")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSRV2")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Call Setup Success Rate V2");
                             }
                             if (kpiColum.equalsIgnoreCase("DCR")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Drop call rate");
                             }
                             if (kpiColum.equalsIgnoreCase("DCRV2")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Drop Call Rate V2");
                             }
                             if (kpiColum.equalsIgnoreCase("DL_TBF_DROP_RATE")){
                                cell = row.createCell(label++);
                                cell.setCellValue("DownLink TBF Drop Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSRV2")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Handover Success Rate V2");
                             }
                             if (kpiColum.equalsIgnoreCase("PS_TBF_SR_2G")){
                                cell = row.createCell(label++);
                                cell.setCellValue("DownLink TBF Establishment Success Rate (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("PS_TRAFFIC_2G")){
                                cell = row.createCell(label++);
                                cell.setCellValue("PS_TRAFFIC_2G");
                             }
                             if (kpiColum.equalsIgnoreCase("PS_UL_TBF_SR_2G")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Uplink TBF Establishment Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("SDCCHBLKR")){
                                cell = row.createCell(label++);
                                cell.setCellValue("SDCCH Blocking Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("TCHBLR")){
                                cell = row.createCell(label++);
                                cell.setCellValue("TCH Blocking Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC")){
                                cell = row.createCell(label++);
                                cell.setCellValue("Traffic");
                             }
                             if (kpiColum.equalsIgnoreCase("UL_TBF_DROP_RATE")){
                                cell = row.createCell(label++);
                                cell.setCellValue("UpLink TBF Drop Rate");
                             }
                             
                         }
                         if(techType.equals("3")){
                             if (kpiColum.equalsIgnoreCase("CALLVOLUME")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Voice Call Volume");
                             }
                             if (kpiColum.equalsIgnoreCase("CSCONGES")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_RAB Congestion Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSINTERFREQHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Inter-Freq Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSIRATHOSRWEIGHT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Inter-RAT Handover Success Rate weight");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSRVIDEOPHONE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Video Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSVIDEODROPCALLRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Video Drop Call Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSVIDEOTRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Video Traffic");
                             }
                             if (kpiColum.equalsIgnoreCase("CSVOICECSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Voice Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("CSVOICEDROPCALLRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Voice Drop Call Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("DCR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Drop Call Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("DLTRAFFICPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("V2_DL Traffic PS");
                             }
                             if (kpiColum.equalsIgnoreCase("HSDPATHROUGHPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSDPA Cell Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("IRATHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Inter-RAT Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSCONGES")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_RAB Congestion Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSCSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSDCR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99_HSPA_D_R");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSDPATPKBPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSDPA Cell Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSDPATRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSDPA Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSPACALLDROPRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSPA Call Drop Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSPACSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSPA Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSPATRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSPA Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSUPATPKBPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSUPA Cell Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSUPATRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_HSUPA Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSIRATHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Inter-RAT Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99CALLDROPRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Call Drop Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99CALLSETUPSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Call Setup Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99DLTRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Down Link Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99TRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99UPLINKTRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Up Link Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("PSTRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Total Traffic (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("R99DLTHROUGHPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Cell Down Link Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("R99ULTHROUGHPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_R99 Cell Up Link Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("SOFTHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Soft/Softer Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("SOFTHOSRPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Soft/Softer Handover Success Rate");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Total Traffic");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFICACTIVESETCS64")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CS_Total Active Set Traffic");
                             }
                             if (kpiColum.equalsIgnoreCase("ULTRAFFICPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("V2_UL Traffic PS");
                             }
                             if (kpiColum.equalsIgnoreCase("V2INTERFREQHOSRPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("PS_Inter-Freq Handover Success Rate");
                             }
                         }
                         if(techType.equals("4")){
                             if (kpiColum.equalsIgnoreCase("AVAILABLE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Avaiable");
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_DL_AVG_THPUTs")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Cell PDCP Downlink Average Throughput");
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_DL_MAX_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Cell PDCP Downlink Max Throughput");
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_UL_AVG_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Cell PDCP Uplink Average Throughput");
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_UL_MAX_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("CEll PDCP Uplink Max Throughput");
                             }
                             if (kpiColum.equalsIgnoreCase("CSFB_SSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("E-UTRAN Initial Context Setup Success Ratio being Subject for CS Fallback (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("CSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Call Setup Success Rate (CSSR) (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("DL_LATENCY")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Downlink Latency");
                             }
                             if (kpiColum.equalsIgnoreCase("ERAB_SSRATE_ALL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("eRAB Setup Success Rate (all services) (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSRX2")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Handover Success Rate via X2 (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_EXE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter RAT HO SR (execution phase)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_LTE_GSM")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter-RAT HOSR (LTE to GSM) (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_LTE_WCDMA")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter-RAT HOSR (LTE to WCDMA) (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_PRE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter RAT HO Preparation Success Ratio (preparation phase)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_PRE_EXE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter RAT Total HO SR (from HO preparation start until successful HO execution)");
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_S1")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Handover Success Rate via S1 (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("INTER_FREQUENCY_HO")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Inter-frequency HO (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("INTRA_ENODEB_HOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Intra eNB HO SR total");
                             }
                             if (kpiColum.equalsIgnoreCase("INTRA_FREQUENCY_HO")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Intra-frequency HO (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("RES_BLK_DL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Resource Block Untilizing Rate Downlink (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("RES_BLK_UL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Resource Block Untilizing Rate Uplink (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("RRC_SSRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("RRC Setup Success Rate (%)");
                             }
                             if (kpiColum.equalsIgnoreCase("SERVICE_DROP_ALL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Service Drop (all service)");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Total Data Traffic Volume (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC_VOL_DL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Traffic Volumn DL (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC_VOL_UL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Traffic Volume UL (GB)");
                             }
                             if (kpiColum.equalsIgnoreCase("UL_LATENCY")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Uplink Latency");
                             }
                             if (kpiColum.equalsIgnoreCase("UNVAILABLE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("Unavailable");
                             }
                             if (kpiColum.equalsIgnoreCase("USER_DL_AVG_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("User Downlink Average Throughput (Kbps)");
                             }
                             if (kpiColum.equalsIgnoreCase("USER_UL_AVG_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue("User Uplink Average Throughput (Kbps)");
                             }
                         }
                             }
                        }
                    }
                    
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getDate() == null ? "" : it.getDate());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getMaNode()== null ? "" : it.getMaNode());
                    
                    cell = row.createCell(2);
                    cell.setCellValue(it.getTenNode()== null ? "" : it.getTenNode());
                    
                    cell = row.createCell(3);
                    cell.setCellValue(it.getBtsName()== null ? "" : it.getBtsName());
                    
                    cell = row.createCell(4);
                    cell.setCellValue(it.getBscRncName()== null ? "" : it.getBscRncName());
                    
                    cell = row.createCell(5);
                    cell.setCellValue(it.getKhuVuc()== null ? "" : it.getKhuVuc());
                    
                    cell = row.createCell(6);
                    cell.setCellValue(it.getProvinceName()== null ? "" : it.getProvinceName());
                    
                    cell = row.createCell(7);
                    cell.setCellValue(it.getQuanHuyen()== null ? "" : it.getQuanHuyen());

                    if(listKpi != null && !listKpi.isEmpty()){
                             kpi = listKpi.split(",");

                             for (String kpiColum: kpi) {
                                if(techType.equals("2")){
                             if (kpiColum.equalsIgnoreCase("CALVOL")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getcALVOL()== null ? "" : it.getcALVOL());
                             }
                             if (kpiColum.equalsIgnoreCase("CSSRV1")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getcSSRV1()== null ? "" : it.getcSSRV1());
                             }
                             if (kpiColum.equalsIgnoreCase("CSSRV2")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getcSSRV2()== null ? "" : it.getcSSRV2());
                             }
                             if (kpiColum.equalsIgnoreCase("DCR")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getdCR()== null ? "" : it.getdCR());
                             }
                             if (kpiColum.equalsIgnoreCase("DCRV2")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getdCRV2()== null ? "" : it.getdCRV2());
                             }
                             if (kpiColum.equalsIgnoreCase("DL_TBF_DROP_RATE")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getdL_TBF_DROP_RATE()== null ? "" : it.getdL_TBF_DROP_RATE());
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.gethOSR()== null ? "" : it.gethOSR());
                             }
                             if (kpiColum.equalsIgnoreCase("HOSRV2")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.gethOSRV2()== null ? "" : it.gethOSRV2());
                             }
                             if (kpiColum.equalsIgnoreCase("PS_TBF_SR_2G")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getpS_TBF_SR_2G()== null ? "" : it.getpS_TBF_SR_2G());
                             }
                             if (kpiColum.equalsIgnoreCase("PS_TRAFFIC_2G")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getpS_TRAFFIC_2G()== null ? "" : it.getpS_TRAFFIC_2G());
                             }
                             if (kpiColum.equalsIgnoreCase("PS_UL_TBF_SR_2G")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getpS_UL_TBF_SR_2G()== null ? "" : it.getpS_UL_TBF_SR_2G());
                             }
                             if (kpiColum.equalsIgnoreCase("SDCCHBLKR")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getsDCCHBLKR()== null ? "" : it.getsDCCHBLKR());
                             }
                             if (kpiColum.equalsIgnoreCase("TCHBLR")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.gettCHBLR()== null ? "" : it.gettCHBLR());
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.gettRAFFIC()== null ? "" : it.gettRAFFIC());
                             }
                             if (kpiColum.equalsIgnoreCase("UL_TBF_DROP_RATE")){
                                cell = row.createCell(label++);
                                cell.setCellValue(it.getuL_TBF_DROP_RATE()== null ? "" : it.getuL_TBF_DROP_RATE());
                             }
                             
                         }
                         if(techType.equals("3")){
                             if (kpiColum.equalsIgnoreCase("CALLVOLUME")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcALLVOLUME()== null ? "" : it.getcALLVOLUME());
                             }
                             if (kpiColum.equalsIgnoreCase("CSCONGES")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSCONGES()== null ? "" : it.getcSCONGES());
                             }
                             if (kpiColum.equalsIgnoreCase("CSINTERFREQHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSINTERFREQHOSR()== null ? "" : it.getcSINTERFREQHOSR());
                             }
                             if (kpiColum.equalsIgnoreCase("CSIRATHOSRWEIGHT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSIRATHOSRWEIGHT()== null ? "" : it.getcSIRATHOSRWEIGHT());
                             }
                             if (kpiColum.equalsIgnoreCase("CSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSSR()== null ? "" : it.getcSSR());
                             }
                             if (kpiColum.equalsIgnoreCase("CSSRVIDEOPHONE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSSRVIDEOPHONE()== null ? "" : it.getcSSRVIDEOPHONE());
                             }
                             if (kpiColum.equalsIgnoreCase("CSVIDEODROPCALLRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSVIDEODROPCALLRATE()== null ? "" : it.getcSVIDEODROPCALLRATE());
                             }
                             if (kpiColum.equalsIgnoreCase("CSVIDEOTRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSVIDEOTRAFFIC()== null ? "" : it.getcSVIDEOTRAFFIC());
                             }
                             if (kpiColum.equalsIgnoreCase("CSVOICECSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSVOICECSSR()== null ? "" : it.getcSVOICECSSR());
                             }
                             if (kpiColum.equalsIgnoreCase("CSVOICEDROPCALLRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSVOICEDROPCALLRATE()== null ? "" : it.getcSVOICEDROPCALLRATE());
                             }
                             if (kpiColum.equalsIgnoreCase("DCR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getdCR()== null ? "" : it.getdCR());
                             }
                             if (kpiColum.equalsIgnoreCase("DLTRAFFICPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getdLTRAFFICPS()== null ? "" : it.getdLTRAFFICPS());
                             }
                             if (kpiColum.equalsIgnoreCase("HSDPATHROUGHPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gethSDPATHROUGHPUT()== null ? "" : it.gethSDPATHROUGHPUT());
                             }
                             if (kpiColum.equalsIgnoreCase("IRATHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getiRATHOSR()== null ? "" : it.getiRATHOSR());
                             }
                             if (kpiColum.equalsIgnoreCase("PSCONGES")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSCONGES()== null ? "" : it.getpSCONGES());
                             }
                             if (kpiColum.equalsIgnoreCase("PSCSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSCSSR()== null ? "" : it.getpSCSSR());
                             }
                             if (kpiColum.equalsIgnoreCase("PSDCR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSDCR()== null ? "" : it.getpSDCR());
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSDPATPKBPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSHSDPATPKBPS()== null ? "" : it.getpSHSDPATPKBPS());
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSDPATRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSHSDPATRAFFICGB()== null ? "" : it.getpSHSDPATRAFFICGB());
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSPACALLDROPRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSHSPACALLDROPRATE()== null ? "" : it.getpSHSPACALLDROPRATE());
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSPACSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSHSPACSSR()== null ? "" : it.getpSHSPACSSR());
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSPATRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSHSPATRAFFICGB()== null ? "" : it.getpSHSPATRAFFICGB());
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSUPATPKBPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSHSUPATPKBPS()== null ? "" : it.getpSHSUPATPKBPS());
                             }
                             if (kpiColum.equalsIgnoreCase("PSHSUPATRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSHSUPATRAFFICGB()== null ? "" : it.getpSHSUPATRAFFICGB());
                             }
                             if (kpiColum.equalsIgnoreCase("PSIRATHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSIRATHOSR()== null ? "" : it.getpSIRATHOSR());
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99CALLDROPRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSR99CALLDROPRATE()== null ? "" : it.getpSR99CALLDROPRATE());
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99CALLSETUPSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSR99CALLSETUPSR()== null ? "" : it.getpSR99CALLSETUPSR());
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99DLTRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSR99DLTRAFFICGB()== null ? "" : it.getpSR99DLTRAFFICGB());
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99TRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSR99TRAFFICGB()== null ? "" : it.getpSR99TRAFFICGB());
                             }
                             if (kpiColum.equalsIgnoreCase("PSR99UPLINKTRAFFICGB")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSR99UPLINKTRAFFICGB()== null ? "" : it.getpSR99UPLINKTRAFFICGB());
                             }
                             if (kpiColum.equalsIgnoreCase("PSTRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getpSTRAFFIC()== null ? "" : it.getpSTRAFFIC());
                             }
                             if (kpiColum.equalsIgnoreCase("R99DLTHROUGHPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getR99DLTHROUGHPUT()== null ? "" : it.getR99DLTHROUGHPUT());
                             }
                             if (kpiColum.equalsIgnoreCase("R99ULTHROUGHPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getR99ULTHROUGHPUT()== null ? "" : it.getR99ULTHROUGHPUT());
                             }
                             if (kpiColum.equalsIgnoreCase("SOFTHOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getsOFTHOSR()== null ? "" : it.getsOFTHOSR());
                             }
                             if (kpiColum.equalsIgnoreCase("SOFTHOSRPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getsOFTHOSRPS()== null ? "" : it.getsOFTHOSRPS());
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gettRAFFIC()== null ? "" : it.gettRAFFIC());
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFICACTIVESETCS64")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gettRAFFICACTIVESETCS64()== null ? "" : it.gettRAFFICACTIVESETCS64());
                             }
                             if (kpiColum.equalsIgnoreCase("ULTRAFFICPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getuLTRAFFICPS()== null ? "" : it.getuLTRAFFICPS());
                             }
                             if (kpiColum.equalsIgnoreCase("V2INTERFREQHOSRPS")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getV2INTERFREQHOSRPS()== null ? "" : it.getV2INTERFREQHOSRPS());
                             }
                         }
                         if(techType.equals("4")){
                             if (kpiColum.equalsIgnoreCase("AVAILABLE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getaVAILABLE()== null ? "" : it.getaVAILABLE());
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_DL_AVG_THPUTs")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcELL_DL_AVG_THPUTs()== null ? "" : it.getcELL_DL_AVG_THPUTs());
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_DL_MAX_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcELL_DL_MAX_THPUT()== null ? "" : it.getcELL_DL_MAX_THPUT());
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_UL_AVG_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcELL_UL_AVG_THPUT()== null ? "" : it.getcELL_UL_AVG_THPUT());
                             }
                             if (kpiColum.equalsIgnoreCase("CELL_UL_MAX_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcELL_UL_MAX_THPUT()== null ? "" : it.getcELL_UL_MAX_THPUT());
                             }
                             if (kpiColum.equalsIgnoreCase("CSFB_SSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSFB_SSR()== null ? "" : it.getcSFB_SSR());
                             }
                             if (kpiColum.equalsIgnoreCase("CSSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getcSSR()== null ? "" : it.getcSSR());
                             }
                             if (kpiColum.equalsIgnoreCase("DL_LATENCY")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getdL_LATENCY()== null ? "" : it.getdL_LATENCY());
                             }
                             if (kpiColum.equalsIgnoreCase("ERAB_SSRATE_ALL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.geteRAB_SSRATE_ALL()== null ? "" : it.geteRAB_SSRATE_ALL());
                             }
                             if (kpiColum.equalsIgnoreCase("HOSRX2")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gethOSRX2()== null ? "" : it.gethOSRX2());
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_EXE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gethOSR_IRAT_EXE()== null ? "" : it.gethOSR_IRAT_EXE());
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_LTE_GSM")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gethOSR_IRAT_LTE_GSM()== null ? "" : it.gethOSR_IRAT_LTE_GSM());
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_LTE_WCDMA")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gethOSR_IRAT_LTE_WCDMA()== null ? "" : it.gethOSR_IRAT_LTE_WCDMA());
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_PRE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gethOSR_IRAT_PRE()== null ? "" : it.gethOSR_IRAT_PRE());
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_IRAT_PRE_EXE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gethOSR_IRAT_PRE_EXE()== null ? "" : it.gethOSR_IRAT_PRE_EXE());
                             }
                             if (kpiColum.equalsIgnoreCase("HOSR_S1")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gethOSR_S1()== null ? "" : it.gethOSR_S1());
                             }
                             if (kpiColum.equalsIgnoreCase("INTER_FREQUENCY_HO")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getiNTER_FREQUENCY_HO()== null ? "" : it.getiNTER_FREQUENCY_HO());
                             }
                             if (kpiColum.equalsIgnoreCase("INTRA_ENODEB_HOSR")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getiNTRA_ENODEB_HOSR()== "0" ? "" : it.getiNTRA_ENODEB_HOSR());
                             }
                             if (kpiColum.equalsIgnoreCase("INTRA_FREQUENCY_HO")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getiNTRA_FREQUENCY_HO()== null ? "" : it.getiNTRA_FREQUENCY_HO());
                             }
                             if (kpiColum.equalsIgnoreCase("RES_BLK_DL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getrES_BLK_DL()== null ? "" : it.getrES_BLK_DL());
                             }
                             if (kpiColum.equalsIgnoreCase("RES_BLK_UL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getrES_BLK_UL()== null ? "" : it.getrES_BLK_UL());
                             }
                             if (kpiColum.equalsIgnoreCase("RRC_SSRATE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getrRC_SSRATE()== null ? "" : it.getrRC_SSRATE());
                             }
                             if (kpiColum.equalsIgnoreCase("SERVICE_DROP_ALL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getsERVICE_DROP_ALL()== null ? "" : it.getsERVICE_DROP_ALL());
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gettRAFFIC()== null ? "" : it.gettRAFFIC());
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC_VOL_DL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gettRAFFIC_VOL_DL()== null ? "" : it.gettRAFFIC_VOL_DL());
                             }
                             if (kpiColum.equalsIgnoreCase("TRAFFIC_VOL_UL")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.gettRAFFIC_VOL_UL()== null ? "" : it.gettRAFFIC_VOL_UL());
                             }
                             if (kpiColum.equalsIgnoreCase("UL_LATENCY")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getuL_LATENCY()== null ? "" : it.getuL_LATENCY());
                             }
                             if (kpiColum.equalsIgnoreCase("UNVAILABLE")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getuNVAILABLE()== null ? "" : it.getuNVAILABLE());
                             }
                             if (kpiColum.equalsIgnoreCase("USER_DL_AVG_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getuSER_DL_AVG_THPUT()== null ? "" : it.getuSER_DL_AVG_THPUT());
                             }
                             if (kpiColum.equalsIgnoreCase("USER_UL_AVG_THPUT")) {
                                 cell = row.createCell(label++);
                                 cell.setCellValue(it.getuSER_UL_AVG_THPUT()== null ? "" : it.getuSER_UL_AVG_THPUT());
                             }
                         }
                             }
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
