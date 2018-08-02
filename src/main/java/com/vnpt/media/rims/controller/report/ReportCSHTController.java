/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.google.gson.Gson;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.ContentDataTableItem;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ReportCSHTFacade;
import com.vnpt.media.rims.formbean.ReportCSHT;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
            //bổ sung phân quyền vào điều kiện tìm kiếm
            Integer index =  ar_name.indexOf("tinhtp_id");
            if(index !=null){
                String temp = ar_search_value.get(index);
                if(temp == null || temp.isEmpty()){
                    ar_search_value.set(index, tinhManagers);
                }
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

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpIds,
            @RequestParam(value = "khuvucId", required = false) String khuvucIds,
            @RequestParam(value = "quanHuyenId", required = false) String quanHuyenIds,
            @RequestParam(value = "phuongXaId", required = false) String phuongXaIds,
            @RequestParam(value = "buildingCode", required = false) String buildingCode,
            @RequestParam(value = "buildingName", required = false) String buildingName) {
        List<ReportCSHT> data = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhManagers = String.join(",", tinhManager);

            if (khuvucIds != null && khuvucIds.equalsIgnoreCase("null")) {
                khuvucIds = null;
            }
            if (tinhTpIds != null && tinhTpIds.equalsIgnoreCase("null")) {
                tinhTpIds = null;
            }
            if (quanHuyenIds != null && quanHuyenIds.equalsIgnoreCase("null")) {
                quanHuyenIds = null;
            }
            if (phuongXaIds != null && phuongXaIds.equalsIgnoreCase("null")) {
                phuongXaIds = null;
            }
            //neu user search theo tinh tp thi search theo gia tri nguoi dung chon
            if (tinhTpIds != null && !tinhTpIds.isEmpty()) {
                tinhManagers = tinhTpIds;
            }
            logger.info("user: {}, ip: {},mem: {} function 'exportExcel' buildingCode:{}, buildingName:{}, khuVucIds:{}, tinhTpIds:{}, quanHuyenIds:{}, phuongXaIds:{}, permisson:{}",
                    user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), buildingCode, buildingName, khuvucIds, tinhTpIds, quanHuyenIds, phuongXaIds, tinhManagers);
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            data = ReportCSHTFacade.exportExcel(khuvucIds, tinhManagers, quanHuyenIds, phuongXaIds, buildingCode, buildingName);
            logger.info("user: {}, ip: {},mem: {}, function 'exportExcel'  {} ban ghi", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), data.size());
            File fileResult = writeExportExcel(dataDirectory + "/TempBaoCaoCSHT.xlsx", data);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
            logger.info("user: {}, ip: {},mem: {}, function 'exportExcel' end", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (data != null) {
                data.clear();
            }
        }
    }

    private File writeExportExcel(String fileTemplate, List<ReportCSHT> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "CSHT" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (ReportCSHT it : datas) {
                    row = sheet.createRow(rowIndex++);
                    cell = row.createCell(0);
                    cell.setCellValue(it.getBuildingCode());
                    cell = row.createCell(1);
                    cell.setCellValue(it.getBuildingName());
                    cell = row.createCell(2);
                    cell.setCellValue(it.getNodeCode());
                    cell = row.createCell(3);
                    cell.setCellValue(it.getManagementName());
                    cell = row.createCell(4);
                    cell.setCellValue(it.getSystemName());
                    cell = row.createCell(5);
                    cell.setCellValue(it.getType());
                    cell = row.createCell(6);
                    cell.setCellValue(it.getProvinceName());
                    cell = row.createCell(7);
                    cell.setCellValue(it.getDistrictName());
                    cell = row.createCell(8);
                    cell.setCellValue(it.getWardName());
                    cell = row.createCell(9);
                    cell.setCellValue(it.getLongitude());
                    cell = row.createCell(10);
                    cell.setCellValue(it.getLatitude());
                    cell = row.createCell(11);
                    cell.setCellValue(it.getAccreditationCode());
                    cell = row.createCell(12);
                    cell.setCellValue(it.getAccreStartDate());
                    cell = row.createCell(13);
                    cell.setCellValue(it.getAccreEndDate());

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
                logger.error(ex.getMessage(), ex);
            }

        }

        return result;
    }
}
