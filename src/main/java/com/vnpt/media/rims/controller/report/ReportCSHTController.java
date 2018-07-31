/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.google.gson.Gson;
import com.vnpt.media.rims.bean.ReportTrafficTinhBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.ContentDataTableItem;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ReportCSHTFacade;
import com.vnpt.media.rims.facade.ReportFacade;
import com.vnpt.media.rims.formbean.ReportCSHT;
import com.vnpt.media.rims.transaction.ITransaction;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
@RequestMapping(value = "/reportCSHT")
public class ReportCSHTController {

    private static Logger logger = LogManager.getLogger(ReportCSHTController.class);
    private static final String REPORT_CSHT = "report/csht/report_csht";

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
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId,
            @RequestParam(value = "phuongXaId", required = false) String phuongXaId,
            ModelMap mm, HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            logger.debug("khuvucId: {}, tinhTpId: {}, quanHuyenId: {}, phuongXaId: {}", khuvucId, tinhTpId, quanHuyenId, phuongXaId);

            mm.put("phuongXaId", phuongXaId);
            mm.put("tinhTpId", tinhTpId);
            mm.put("khuvucId", khuvucId);
            mm.put("quanHuyenId", quanHuyenId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_CSHT;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String search(HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {},mem: {}, search", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            String[] tinhManagerArr = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhManagers = String.join(",", tinhManagerArr);
            logger.debug("tinhManagers: {}", tinhManagers);
            int draw = -1;
            String prs_start_record = "";
            String prs_length_page = "";
            String prs_global_search = "";
            String prs_list_column_name = "";
            String prs_list_column_search = "";
            String prs_column_to_sort = "";
            String param_sort_column = "";
            String prs_sort_direction = "";
            String pList = "";
            ArrayList<String> ar_name = new ArrayList<>();
            ArrayList<String> ar_search_value = new ArrayList<>();
            //bổ sung phân quyền vào điều kiện tìm kiếm
//            ar_name.add("tinhtp_id");
//            ar_search_value.add(tinhManagers);
            String columnPermisson = "";
           // duyệt tất cả các tham số truyền vào request
            for (Enumeration items = request.getParameterNames(); items.hasMoreElements();) {
                String param_name = (String) items.nextElement();
                String param_value = request.getParameter(param_name);
                if (param_name.equals("draw")) {
                    draw = Integer.parseInt(param_value);
                } else if (param_name.startsWith("order") && param_name.contains("column")) {
                    param_sort_column = param_value;
                    prs_column_to_sort = param_value;
                } else if (param_name.startsWith("order") && param_name.contains("dir")) {
                    prs_sort_direction = param_value;
                } else if (param_name.equals("start")) {
                    prs_start_record = String.valueOf(Integer.parseInt(param_value) + 1);
                } else if (param_name.equals("length")) {
                    prs_length_page = param_value;
                } else if (param_name.equals("search[value]")) {
                    prs_global_search = param_value;
                } else if (param_name.startsWith("columns") && param_name.contains("name")) {
                    ar_name.add(param_value);
                } else if (param_name.startsWith("columns") && param_name.contains("search") && param_name.contains("value")) {
                    ar_search_value.add(param_value);
                }
                pList += param_name + "=" + param_value + ",";
            }
            //            
            int total_column = ar_name.size();
            for (int i = 0; i < total_column; i++) {
                String name = ar_name.get(i);
                String value = ar_search_value.get(i);
                // update tên cột sorting
                if (!param_sort_column.equals("0")) {
                    if (Integer.parseInt(param_sort_column) == i) {
                        prs_column_to_sort = ar_name.get(i);
                    }
                }
                // check xem cột đó có giá trị cần search ko
                if (!value.isEmpty()) {
                    prs_list_column_name = prs_list_column_name + ";" + name;
                    prs_list_column_search = prs_list_column_search + ";" + value;
                }
            }
            String[] recordsTotal = new String[1];
            String[] recordsFiltered = new String[1];
            ArrayList<ReportCSHT> list = null;
            try {
                logger.debug("prs_start_record :{}, prs_length_page:{}, prs_global_search:{}, prs_list_column_name:{}, prs_list_column_search:{}, prs_column_to_sort:{}, prs_sort_direction:{} ",
                        prs_start_record, prs_length_page, prs_global_search, prs_list_column_name, prs_list_column_search, prs_column_to_sort, prs_sort_direction);
                list = ReportCSHTFacade.search(prs_start_record, prs_length_page,
                        prs_global_search, prs_list_column_name, prs_list_column_search, prs_column_to_sort,
                        prs_sort_direction, recordsTotal, recordsFiltered);
                logger.info("user: {}, ip: {},mem: {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            } catch (DAOException ex) {
                logger.error(ex.getMessage(), ex);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            int count = 0;
            // chuyển list  thành list String
            List<List<String>> data = new ArrayList();
            if (list != null) {
                for (ReportCSHT item : list) {
                    ArrayList<String> ls = new ArrayList();
                    // thứ tự phải khớp với thứ tự của các cột trong table của trang jsp
                    count++;
                    ls.add(count + "");
                    ls.add(item.getBuildingCode());
                    ls.add(item.getBuildingName());
                    ls.add(item.getNodeCode());
                    ls.add(item.getManagementName());
                    ls.add(item.getSystemName());
                    ls.add(item.getType());
                    ls.add(item.getAreaName());
                    ls.add(item.getProvinceId());
                    ls.add(item.getProvinceName());
                    ls.add(item.getDistrictId());
                    ls.add(item.getDistrictName());
                    ls.add(item.getWardId());
                    ls.add(item.getWardName());
                    ls.add(item.getLongitude());
                    ls.add(item.getLatitude());
                    ls.add(item.getAccreditationCode());
                    ls.add(item.getAccreStartDate());
                    ls.add(item.getAccreEndDate());
                    data.add(ls);
                }
            }
            // chuyển thành object json
            ContentDataTableItem responseObj = new ContentDataTableItem(draw, recordsTotal[0], recordsFiltered[0], data);
            Gson gson = new Gson();
            logger.info("user: {}, ip: {},mem: {} end", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            return gson.toJson(responseObj);
        } catch (DAOException | NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    @RequestMapping(value = "/reportTrafficTinh", method = RequestMethod.GET)
    public void reportTrafficTinh(HttpServletRequest request,
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
        List<ReportTrafficTinhBO> datas = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {}, end reportTrafficTinh...", user.getUsername(), request.getRemoteAddr());
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId.replace("null", "");
            khuvucId = khuvucId == null ? "" : khuvucId.replace("null", "");

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
            calendar1.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(toWeek) + 1);
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
            try {
                logger.info("user: {}, ip: {},techType:{}, timeType:{}, khuvucId:{}, tinhTpId:{}, fromDate:{}, toDate:{}, fromDateWeek:{}, toDateWeek:{}, fromDateMonth:{}, toDateMonth:{}, before get data reportTrafficTinh...", user.getUsername(), request.getRemoteAddr(), techType, timeType, khuvucId, tinhTpId, fromDate, toDate, fromDateWeek, toDateWeek, fromDateMonth, toDateMonth);
                logger.debug("start get data reportTrafficTinh {}", Function.getInfoMemory());
                datas = ReportFacade.reportTrafficTinh(techType, timeType, khuvucId, tinhTpId, fromDate, toDate, fromDateWeek, toDateWeek, fromDateMonth, toDateMonth);
                logger.info("user: {}, ip: {}, size:{},techType:{}, timeType:{}, khuvucId:{}, tinhTpId:{}, fromDate:{}, toDate:{}, fromDateWeek:{}, toDateWeek:{}, fromDateMonth:{}, toDateMonth:{}, count data reportTrafficTinh...", user.getUsername(), request.getRemoteAddr(), datas.size(), techType, timeType, khuvucId, tinhTpId, fromDate, toDate, fromDateWeek, toDateWeek, fromDateMonth, toDateMonth);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            File fileResult = null;
            if (techType.equals("2")) {
                fileResult = writeReportTrafficTinh(dataDirectory + "/BaoCaoTrafficMucTinh2G.xlsx", datas, techType);
            }
            if (techType.equals("3")) {
                fileResult = writeReportTrafficTinh(dataDirectory + "/BaoCaoTrafficMucTinh3G.xlsx", datas, techType);
            }
            if (techType.equals("4")) {
                fileResult = writeReportTrafficTinh(dataDirectory + "/BaoCaoTrafficMucTinh4G.xlsx", datas, techType);
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
            logger.debug("end get data reportTrafficTinh {}", Function.getInfoMemory());
            logger.info("user: {}, ip: {}, end reportTrafficTinh...", user.getUsername(), request.getRemoteAddr());
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (datas != null) {
                datas.clear();
            }
        }

    }

    private File writeReportTrafficTinh(String fileTemplate, List<ReportTrafficTinhBO> datas, String techType) {
        File result = null;
        FileOutputStream fos = null;
        SXSSFWorkbook workbook = null;
        try {
            if (techType.equals("2")) {
                result = new File("BaoCaoTrafficMucTinh2G.xlsx");
            }
            if (techType.equals("3")) {
                result = new File("BaoCaoTrafficMucTinh3G.xlsx");
            }
            if (techType.equals("4")) {
                result = new File("BaoCaoTrafficMucTinh4G.xlsx");
            }

//            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet 1");
            CellStyle style;
            DataFormat format = workbook.createDataFormat();
            style = workbook.createCellStyle();
            style.setDataFormat(format.getFormat("0.00000"));
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
                cell.setCellValue("Thời gian");
                cell.setCellStyle(styleHeader);

                cell = row.createCell(1);
                cell.setCellValue("Tỉnh");
                cell.setCellStyle(styleHeader);

                if (techType.equals("2")) {
                    cell = row.createCell(2);
                    cell.setCellValue("Traffic 2G CS [Erl]");
                    cell.setCellStyle(styleHeader);

                    cell = row.createCell(3);
                    cell.setCellValue("Traffic 2G PS [MB]");
                    cell.setCellStyle(styleHeader);
                }
                if (techType.equals("3")) {
                    cell = row.createCell(2);
                    cell.setCellValue("CS_Total Traffic [Erl]");
                    cell.setCellStyle(styleHeader);

                    cell = row.createCell(3);
                    cell.setCellValue("PS_Total Traffic [GB]");
                    cell.setCellStyle(styleHeader);
                }
                if (techType.equals("4")) {
                    cell = row.createCell(2);
                    cell.setCellValue("4G PS_Total Traffic [GB]");
                    cell.setCellStyle(styleHeader);
                }

                for (ReportTrafficTinhBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getDate() == null ? "" : it.getDate());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getProvinceName() == null ? "" : it.getProvinceName());

                    if (techType.equals("2") || techType.equals("3")) {
                        cell = row.createCell(2);
                        double a;
                        if (it.getTraffic2g3gCs() == null || it.getTraffic2g3gCs().isEmpty()) {
                            a = 0;
                        } else {
                            a = Double.parseDouble(it.getTraffic2g3gCs());
                        }
                        cell.setCellValue(a);
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        cell = row.createCell(3);
                        double b;
                        if (it.getTraffic2g3g4gPs() == null || it.getTraffic2g3g4gPs().isEmpty()) {
                            b = 0;
                        } else {
                            b = Double.parseDouble(it.getTraffic2g3g4gPs());
                        }
                        cell.setCellValue(b);
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    }
                    if (techType.equals("4")) {
                        cell = row.createCell(2);
                        double c;
                        if (it.getTraffic2g3g4gPs() == null || it.getTraffic2g3g4gPs().isEmpty()) {
                            c = 0;
                        } else {
                            c = Double.parseDouble(it.getTraffic2g3g4gPs());
                        }
                        cell.setCellValue(c);
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
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
