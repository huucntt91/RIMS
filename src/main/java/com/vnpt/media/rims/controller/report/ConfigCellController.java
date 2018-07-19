/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.bean.BtsReportBO;
import com.vnpt.media.rims.bean.Cell2GReportBO;
import com.vnpt.media.rims.bean.FilterReportBO;
import com.vnpt.media.rims.bean.NodeBReportBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.facade.LogMenuFacade;
import com.vnpt.media.rims.facade.ReportFacade;
import com.vnpt.media.rims.formbean.FilterForm;
import com.vnpt.media.rims.formbean.ReportConfigForm;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller

@RequestMapping(value = "/report/configCell")
public class ConfigCellController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(ConfigCellController.class);

//    @ModelAttribute("filterReportList")
//    public List findFilterReport() {
//        ReportFacade facade = new ReportFacade();
//        return facade.findFilterReport();
//    }
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, HttpServletRequest request) {
        // log user request menu
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LogMenuFacade.logMenu(String.valueOf(user.getId()), request.getRemoteAddr(), "Báo cáo cấu hình thiết bị");
        // end log

        mm.put("configForm", new ReportConfigForm());
        ReportFacade facade = new ReportFacade();
        mm.put("filterReportList", facade.findFilterReport(0));
        mm.put("filterForm", new FilterForm());
        return "report/cauhinhthietbi/list";
    }

    @RequestMapping(value = "/changeObject", method = RequestMethod.POST)
    public String changeObject(ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "type", required = false) String type) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ReportFacade facade = new ReportFacade();
        List<FilterReportBO> list = new ArrayList<>();
        try {
            LOGGER.info("user: {}, ip: {}, call findFilterReport({})", user.getUsername(), request.getRemoteAddr(), type);
            list = facade.findFilterReport(Integer.valueOf(type));
            LOGGER.info("user: {}, ip: {}, end findFilterReport: {}", user.getUsername(), request.getRemoteAddr(), list.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        mm.put("filterReportList", list);
        mm.put("filterForm", new FilterForm());
        mm.put("type", type);
        return "report/cauhinhthietbi/list";
    }

    @RequestMapping(value = "/changeColumn/{columnId}", method = RequestMethod.GET,
            produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String changeColumn(ModelMap mm, @PathVariable(value = "columnId") String columnId, RedirectAttributes attr, Locale locale) throws Exception {
//        logger.debug("Reset password user action");
        StringBuilder xmlAjax = new StringBuilder();
        xmlAjax.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        try {
//            UserBO userBO = new ManagerAdminFacade().findByUserId(columnId.trim());
//            new ManagerAdminFacade().resetPassword(userBO);
            xmlAjax.append("<responseCode>00</responseCode>");
        } catch (Exception e) {
//            logger.error("Exception : ", e);
            xmlAjax.append("<responseCode>01</responseCode>");

            return xmlAjax.toString();
        }
        return xmlAjax.toString();
    }

    @RequestMapping(value = "/addFilter", method = RequestMethod.GET)
    public String addFilter(ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "type", required = false) String type,
            //            @RequestParam(value = "listFilterForm", required = false) List<FilterForm> listFilterForm,
            @Valid @ModelAttribute(value = "filterForm") FilterForm filterForm) {
        ReportFacade facade = new ReportFacade();

        if (filterForm.getListFilterForm() == null) {
            filterForm.setListFilterForm(new ArrayList<FilterForm>());
        }
        String id = "1";
        try {
            id = DateTimeUtils.getSysdate("yyyyMMddHHmmss");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        filterForm.getListFilterForm().add(new FilterForm(id, filterForm.getColumn(), filterForm.getDataType(),
                filterForm.getOperator(), filterForm.getValue_(), filterForm.getDescription()));
        mm.put("filterReportList", facade.findFilterReport(Integer.valueOf(type)));
        mm.put("type", type);
        mm.put("filterForm", filterForm);
        return "report/cauhinhthietbi/list";
    }

    @RequestMapping(value = "/addFilter", method = RequestMethod.GET, params = {"changeColumn"})
    public String changeColumn(ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "page", required = false) String page,
            @Valid @ModelAttribute(value = "filterForm") FilterForm filterForm) {

        ReportFacade facade = new ReportFacade();
        mm.put("filterReportList", facade.findFilterReport(Integer.valueOf(type)));
        mm.put("type", type);

        int dataType = facade.findDataType(type, filterForm.getColumn());
        filterForm.setDataType("" + dataType);
        filterForm.setValue_(null);
        mm.put("dataType", dataType);
        return "report/cauhinhthietbi/list";
    }

    @RequestMapping(value = "/addFilter", method = RequestMethod.GET, params = {"search"})
    public String search(ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "page", required = false) String page,
            @Valid @ModelAttribute(value = "filterForm") FilterForm filterForm) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ReportFacade facade = new ReportFacade();
        mm.put("filterReportList", facade.findFilterReport(Integer.valueOf(type)));
        mm.put("type", type);

        // paging
        page = page == null ? "1" : page;
        Integer pageInt;
        try {
            pageInt = Integer.parseInt(page);
        } catch (Exception ex) {
            pageInt = 1;
        }
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        FilterForm filter = new FilterForm();
        if (tinhManager != null && tinhManager.length > 0) {

            filter.setColumn("building.TINHTP_ID");
            filter.setOperator("IN");
            filter.setValue_("(" + String.join(",", tinhManager) + ")");

            List<FilterForm> listFilter = new ArrayList<FilterForm>();
            //slistFilter.addAll(filterForm.getListFilterForm());
            listFilter.add(filter);
            if (filterForm != null && filterForm.getListFilterForm() != null) {
                for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {
                    listFilter.add(filterForm.getListFilterForm().get(i));
                }
            }
            filter.setListFilterForm(listFilter);
        }

        int totalRows;
        try {
            totalRows = facade.getTotalCell2GReport(type, filter);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
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
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Nodes");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        mm.put("startRow", startRow);

        List<?> temp = null;
        try {
            LOGGER.info("user: {}, ip: {}, call cell2GReport({},{},{},{})", user.getUsername(), request.getRemoteAddr(), type, filter, startRow, endRow);
            temp = facade.cell2GReport(type, filter, String.valueOf(startRow), String.valueOf(endRow));
            LOGGER.info("user: {}, ip: {}, done cell2GReport: {}", user.getUsername(), request.getRemoteAddr(), temp.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        mm.put("searchs", filterForm);

        mm.put("type", type);
        if (type.equals("5") || type.equals("6")) {
            List<Cell2GReportBO> list = (List<Cell2GReportBO>) temp;
            mm.put("list", list);
        } else if (type.equals("2")) {
            List<BtsReportBO> list = (List<BtsReportBO>) temp;

            mm.put("list", list);
        } else if (type.equals("3")) {
            List<NodeBReportBO> list = (List<NodeBReportBO>) temp;
            mm.put("list", list);
        }
        return "report/cauhinhthietbi/list";
    }

    @RequestMapping(value = "/addFilter", method = RequestMethod.GET, params = {"removeFilter"})
    public String removeFilter(ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "id", required = false) String id,
            @Valid @ModelAttribute(value = "filterForm") FilterForm filterForm) {

        ReportFacade facade = new ReportFacade();
        mm.put("filterReportList", facade.findFilterReport(Integer.valueOf(type)));
        mm.put("type", type);
        ArrayList<FilterForm> temp = new ArrayList<FilterForm>();
        for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {
            if (!filterForm.getListFilterForm().get(i).getId().equals(id)) {
                temp.add(filterForm.getListFilterForm().get(i));
            }
        }
        filterForm.setListFilterForm(temp);
        mm.put("filterReportList", facade.findFilterReport(Integer.valueOf(type)));
        mm.put("type", type);
        mm.put("filterForm", filterForm);
        return "report/cauhinhthietbi/list";
    }

    @RequestMapping(value = "/addFilter", method = RequestMethod.GET, params = {"export"})
    public void export(ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "page", required = false) String page,
            @Valid @ModelAttribute(value = "filterForm") FilterForm filterForm,
            HttpServletResponse response) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ReportFacade facade = new ReportFacade();

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        FilterForm filter = new FilterForm();
        if (tinhManager != null && tinhManager.length > 0) {

            filter.setColumn("building.TINHTP_ID");
            filter.setOperator("IN");
            filter.setValue_("(" + String.join(",", tinhManager) + ")");

            List<FilterForm> listFilter = new ArrayList<FilterForm>();
            //slistFilter.addAll(filterForm.getListFilterForm());
            listFilter.add(filter);
            if (filterForm != null && filterForm.getListFilterForm() != null) {
                for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {
                    listFilter.add(filterForm.getListFilterForm().get(i));
                }
            }
            filter.setListFilterForm(listFilter);
        }

        List<?> temp = null;
        try {
            LOGGER.info("user: {}, ip: {}, call cell2GReport({},{},{},{})", user.getUsername(), request.getRemoteAddr(), type, filter.listParam(), null, null);
            temp = facade.cell2GReport(type, filter, null, null);
            LOGGER.info("user: {}, ip: {}, done cell2GReport: {}", user.getUsername(), request.getRemoteAddr(), temp.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");

        String fileName = "Template_BCCH_Cell2G.xlsx";
        if (type.equals("5")) {
            fileName = "Template_BCCH_Cell2G.xlsx";
        } else if (type.equals("6")) {
            fileName = "Template_BCCH_Cell3G.xlsx";
        } else if (type.equals("2")) {
            fileName = "Template_BCCH_BTS.xlsx";
        } else if (type.equals("3")) {
            fileName = "Template_BCCH_NODEB.xlsx";
        }
        File fileTemplate = new File(dataDirectory + File.separator + fileName);
        File fileResult = null;

        LOGGER.debug("user: {}, ip: {}, call write Excel cau hinh thiet bi {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        if (type.equals("5") || type.equals("6")) {
            writeBc(type, fileTemplate, temp);
        } else {
            fileResult = writeBcTemplate(type, fileTemplate, temp);
        }

//        if (fileResult.exists()) {
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        try {
            if (type.equals("5") || type.equals("6")) {
//                FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileTemplate)), response.getOutputStream());
                ServletOutputStream sos = response.getOutputStream();
                FileInputStream fis = new FileInputStream(fileTemplate);
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileCopyUtils.copy(bis, sos);
            } else {
//                FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                ServletOutputStream sos = response.getOutputStream();
                FileInputStream fis = new FileInputStream(fileResult);
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileCopyUtils.copy(bis, sos);
            }

            response.getOutputStream().flush();
            if (temp != null) {
                temp.clear();
            }
            LOGGER.debug("user: {}, ip: {}, end write Excel cau hinh thiet bi {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        } catch (IOException e) {
            if (e.getMessage().contains("An established connection was aborted by the software in your host machine") || e.getMessage().contains("An existing connection was forcibly closed by the remote host")) {
                LOGGER.info(e.getMessage());
            } else {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void writeBc(String type, File fileTemplate, List<?> temp) {
        SXSSFWorkbook streamWorkbook = null;
        try {
            FileOutputStream fos;
            streamWorkbook = new SXSSFWorkbook();
            switch (type) {
                case "5": {
                    Sheet sheet = streamWorkbook.createSheet("cell 2G");
                    write2Gconfig((List<Cell2GReportBO>) temp, sheet);
                    break;
                }
                case "6": {
                    Sheet sheet = streamWorkbook.createSheet("cell 3G");
                    write3Gconfig((List<Cell2GReportBO>) temp, sheet);
                    break;
                }
                case "2": {
                    Sheet sheet = streamWorkbook.createSheet("bts");
                    List<BtsReportBO> list = (List<BtsReportBO>) temp;
                    writeBtsconfig((List<BtsReportBO>) temp, sheet);
                    break;
                }
                case "3": {
                    Sheet sheet = streamWorkbook.createSheet("nodeb");
                    List<NodeBReportBO> list = (List<NodeBReportBO>) temp;
                    writeNodeBconfig((List<NodeBReportBO>) temp, sheet);
                    break;
                }
                default:
                    break;
            }

            fos = new FileOutputStream(fileTemplate);
            streamWorkbook.write(fos);
//            return file;

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                if (streamWorkbook != null) {
                    streamWorkbook.close();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }

        }
//        return null;
    }

    public File writeBcTemplate(String type, File fileTemplate, List<?> temp) {
        try {

            Workbook workbook;
            try (FileInputStream fin = new FileInputStream(fileTemplate)) {
                workbook = new XSSFWorkbook(fin);
                Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
                switch (type) {
                    case "5":
                        write2Gconfig((List<Cell2GReportBO>) temp, sheet);
                        break;
                    case "6":
                        write2Gconfig((List<Cell2GReportBO>) temp, sheet);
                        break;
                    case "2": {
                        writeBtsconfig((List<BtsReportBO>) temp, sheet);
                        break;
                    }
                    case "3": {
                        writeNodeBconfig((List<NodeBReportBO>) temp, sheet);
                        break;
                    }
                    default:
                        break;
                }
            }
            File file = new File("Report_Cau_Hinh_Thiet_Bi.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {

        }
        return null;
    }

    private void write2Gconfig(List<Cell2GReportBO> temp, Sheet sheet) {
        try {
            Iterator<Cell2GReportBO> iterator = temp.iterator();
            Cell cell;
            Row row;
            int rowIndex = 3;
            //header
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
            CellStyle styleHeader2 = sheet.getWorkbook().createCellStyle();
            styleHeader2.setFont(font);
            styleHeader2.setAlignment(CellStyle.ALIGN_CENTER);
            styleHeader2.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            styleHeader2.setFillPattern(CellStyle.SOLID_FOREGROUND);

            Row row1 = sheet.createRow(0);
            CellStyle style1 = sheet.getWorkbook().createCellStyle();
            row1.setRowStyle(style1);
            //vendor
            Cell cell1 = row1.createCell(0);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
            cell1.setCellStyle(styleHeader);
            cell1.setCellValue("Khu vực");
            Cell cell11 = row1.createCell(4);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 9));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin khai sinh");
            cell11 = row1.createCell(10);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 19));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin trên OMC");
            cell11 = row1.createCell(19);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 20, 21));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin trạng thái");
            cell11 = row1.createCell(21);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 22, 30));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin RF");
            cell11 = row1.createCell(29);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 31, 36));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin cấu hình");
            cell11 = row1.createCell(36);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 37, 38));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin chất lượng");
            ////
            Row row2 = sheet.createRow(1);
            CellStyle style2 = sheet.getWorkbook().createCellStyle();
            row2.setRowStyle(style2);
            //vendor
            Cell cell2 = row2.createCell(0);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Tỉnh/TP");
            cell2 = row2.createCell(1);
            cell2.setCellStyle(styleHeader2);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));

            cell2.setCellValue("Quận/Huyện");
            cell2 = row2.createCell(2);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Phường/Xã");
            cell2 = row2.createCell(3);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Location Number");
            cell2 = row2.createCell(4);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Ngày hoạt động");
            cell2 = row2.createCell(5);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Hoàn cảnh ra đời");
            cell2 = row2.createCell(6);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Tên cho quản lý");
            cell2 = row2.createCell(7);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Ngày đăng ký");
            cell2 = row2.createCell(8);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Ngày kiểm duyệt");
            cell2 = row2.createCell(9);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Ngày cấp phép");
            cell2 = row2.createCell(10);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 10, 10));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Tên trên hệ thống");
            cell2 = row2.createCell(11);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("LAC");
            cell2 = row2.createCell(12);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("CI");
            cell2 = row2.createCell(13);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Mã BTS-NodeB");
            cell2 = row2.createCell(14);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Tên BSC/RNC");
            cell2 = row2.createCell(15);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 15, 15));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("code");
            cell2 = row2.createCell(16);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 16, 16));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("DC-HSDPA 42M");
            cell2 = row2.createCell(17);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 17, 17));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Frequency Band");
            cell2 = row2.createCell(18);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 18, 18));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Số TRX");
            cell2 = row2.createCell(19);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 19, 19));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Cell2g ID");
            cell2 = row2.createCell(20);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 20, 20));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Trạng thái hoạt động");
            cell2 = row2.createCell(21);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 21, 21));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Trạng thái quản lý");
            cell2 = row2.createCell(22);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 22, 22));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Latitude");
            cell2 = row2.createCell(23);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 23, 23));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Longitude");
            cell2 = row2.createCell(24);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 24, 24));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Azimuth");
            cell2 = row2.createCell(25);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 25, 25));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Mechanical Tilt");
            cell2 = row2.createCell(26);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 26, 26));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Total Tilt");
            cell2 = row2.createCell(27);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 27, 27));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Antenna high");
            cell2 = row2.createCell(28);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 28, 28));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Antenna Type");
            cell2 = row2.createCell(29);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 29, 29));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Antena Gain");
            cell2 = row2.createCell(30);

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 30, 30));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Special Coverage");
            cell2 = row2.createCell(31);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 31, 31));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Vendor");
            cell2 = row2.createCell(32);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 32, 32));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Loại Cell");
            cell2 = row2.createCell(33);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 33, 33));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("No of Carrier");

            cell2 = row2.createCell(34);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 34, 34));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("boster tma");
            cell2 = row2.createCell(35);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 35, 35));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Cpich Power");

            cell2 = row2.createCell(36);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 36, 36));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Total Power");

            cell2 = row2.createCell(37);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 37, 37));
            cell2.setCellStyle(styleHeader2);
            cell2.setCellValue("Black list Flag");
            cell2 = row2.createCell(38);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 38, 38));
            cell2.setCellValue("Lý do");

//header
            while (iterator.hasNext()) {
                Cell2GReportBO cell3G = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(StringUtils.getValue(cell3G.getTinh()));

                cell = row.createCell(1);
                cell.setCellValue(StringUtils.getValue(cell3G.getQuan()));

                cell = row.createCell(2);
                cell.setCellValue(cell3G.getXa() == null ? "" : cell3G.getXa());

                cell = row.createCell(3);
                cell.setCellValue(StringUtils.getValue(cell3G.getLocationNumber()));

                cell = row.createCell(4);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHoatDong()));
                cell = row.createCell(5);
                cell.setCellValue(StringUtils.getValue(cell3G.getHoanCanhRaDoi()));
                cell = row.createCell(6);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenCell()));
                cell = row.createCell(7);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayDangKy()));
                cell = row.createCell(8);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayKiemDuyet()));
                cell = row.createCell(9);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayCapPhep()));
                cell = row.createCell(10);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenTrenHeThong()));
                cell = row.createCell(11);
                cell.setCellValue(StringUtils.getValue(cell3G.getLac()));
                cell = row.createCell(12);
                cell.setCellValue(StringUtils.getValue(cell3G.getCi()));
                cell = row.createCell(13);
                cell.setCellValue(StringUtils.getValue(cell3G.getMaBTS()));
                cell = row.createCell(14);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenBscRnc()));
                cell = row.createCell(15);
                cell.setCellValue(StringUtils.getValue(cell3G.getCode()));
                cell = row.createCell(16);
                cell.setCellValue(StringUtils.getValue(cell3G.getDcHsdpa42m()));
                cell = row.createCell(17);
                cell.setCellValue(StringUtils.getValue(cell3G.getFrequencyBand()));
                cell = row.createCell(18);
                cell.setCellValue(StringUtils.getValue(cell3G.getSoTrx()));
                cell = row.createCell(19);
                cell.setCellValue(StringUtils.getValue(String.valueOf(cell3G.getNodeId())));
                cell = row.createCell(20);
                cell.setCellValue(StringUtils.getValue(cell3G.getTrangThaiHd()));
                cell = row.createCell(21);
                cell.setCellValue(StringUtils.getValue(cell3G.getTrangThaiQl()));
                cell = row.createCell(22);
                cell.setCellValue(StringUtils.getValue(cell3G.getLatitude()));
                cell = row.createCell(23);
                cell.setCellValue(StringUtils.getValue(cell3G.getLongitude()));

                cell = row.createCell(24);
                cell.setCellValue(StringUtils.getValue(cell3G.getAzimuth()));
                cell = row.createCell(25);
                cell.setCellValue(StringUtils.getValue(cell3G.getMechanical()));
                cell = row.createCell(26);
                cell.setCellValue(StringUtils.getValue(cell3G.getTotalTilt()));
                cell = row.createCell(27);
                cell.setCellValue(StringUtils.getValue(cell3G.getAntennaHigh()));

                cell = row.createCell(28);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenAnten()));

                cell = row.createCell(29);
                cell.setCellValue(StringUtils.getValue(cell3G.getAntennaGain()));
                cell = row.createCell(30);
                cell.setCellValue(StringUtils.getValue(cell3G.getSpecialCoverage()));
                cell = row.createCell(31);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenThietBi()));
                cell = row.createCell(32);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenLoaiTram()));
                cell = row.createCell(33);
                cell.setCellValue(StringUtils.getValue(cell3G.getNoOfCarrier()));
                cell = row.createCell(34);
                cell.setCellValue(StringUtils.getValue(cell3G.getBosterTma()));
                cell = row.createCell(35);
                cell.setCellValue(StringUtils.getValue(cell3G.getCpichPower()));
                cell = row.createCell(36);
                cell.setCellValue(StringUtils.getValue(cell3G.getTotalPower()));

                cell = row.createCell(37);
                cell.setCellValue(StringUtils.getValue(cell3G.getBlackListFlag()));
                cell = row.createCell(38);
                cell.setCellValue(StringUtils.getValue(cell3G.getLyDo()));

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void write3Gconfig(List<Cell2GReportBO> temp, Sheet sheet) {
        try {
            Iterator<Cell2GReportBO> iterator = temp.iterator();
            Cell cell;
            Row row;
            int rowIndex = 3;
            //header
            Row row1 = sheet.createRow(0);
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
            CellStyle style1 = sheet.getWorkbook().createCellStyle();
//            style1.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
//            style1.setFillPattern(CellStyle.BIG_SPOTS);
            row1.setRowStyle(style1);
            //vendor
            Cell cell1 = row1.createCell(0);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
            cell1.setCellStyle(styleHeader);
            cell1.setCellValue("Khu vực");
            Cell cell11 = row1.createCell(4);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 9));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin khai sinh");
            cell11 = row1.createCell(10);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 18));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin trên OMC");
            cell11 = row1.createCell(19);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 19, 20));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin trạng thái");
            cell11 = row1.createCell(21);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 21, 29));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin RF");
            cell11 = row1.createCell(29);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 30, 35));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin cấu hình");
            cell11 = row1.createCell(36);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 36, 37));
            cell11.setCellStyle(styleHeader);
            cell11.setCellValue("Thông tin chất lượng");
            ////
            Row row2 = sheet.createRow(1);
            CellStyle style2 = sheet.getWorkbook().createCellStyle();
//            style2.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
//            style2.setFillPattern(CellStyle.BIG_SPOTS);
            row2.setRowStyle(style2);
            //vendor
            Cell cell2 = row2.createCell(0);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Tỉnh/TP");
            cell2 = row2.createCell(1);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Quận/Huyện");
            cell2 = row2.createCell(2);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Phường/Xã");
            cell2 = row2.createCell(3);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Location Number");
            cell2 = row2.createCell(4);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Ngày hoạt động");
            cell2 = row2.createCell(5);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Hoàn cảnh ra đời");
            cell2 = row2.createCell(6);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Tên cho quản lý");
            cell2 = row2.createCell(7);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Ngày đăng ký");
            cell2 = row2.createCell(8);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Ngày kiểm duyệt");
            cell2 = row2.createCell(9);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Ngày cấp phép");
            cell2 = row2.createCell(10);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 10, 10));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Tên trên hệ thống");
            cell2 = row2.createCell(11);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("LAC");
            cell2 = row2.createCell(12);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("CI");
            cell2 = row2.createCell(13);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Mã BTS-NodeB");
            cell2 = row2.createCell(14);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Tên BSC/RNC");
            cell2 = row2.createCell(15);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 15, 15));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("code");
            cell2 = row2.createCell(16);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 16, 16));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("DC-HSDPA 42M");
            cell2 = row2.createCell(17);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 17, 17));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Frequency Band");
            cell2 = row2.createCell(18);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 18, 18));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Cell3g ID");
            cell2 = row2.createCell(19);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 19, 19));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Trạng thái hoạt động");
            cell2 = row2.createCell(20);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 20, 20));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Trạng thái quản lý");
            cell2 = row2.createCell(21);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 21, 21));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Latitude");
            cell2 = row2.createCell(22);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 22, 22));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Longitude");
            cell2 = row2.createCell(23);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 23, 23));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Azimuth");
            cell2 = row2.createCell(24);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 24, 24));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Mechanical Tilt");
            cell2 = row2.createCell(25);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 25, 25));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Total Tilt");
            cell2 = row2.createCell(26);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 26, 26));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Antenna high");
            cell2 = row2.createCell(27);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 27, 27));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Antenna Type");
            cell2 = row2.createCell(28);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 28, 28));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Antena Gain");
            cell2 = row2.createCell(29);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 29, 29));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Special Coverage");
            cell2 = row2.createCell(30);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 30, 30));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Vendor");
            cell2 = row2.createCell(31);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 31, 31));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Loại Cell");
            cell2 = row2.createCell(32);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 32, 32));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("No of Carrier");
            cell2 = row2.createCell(33);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 33, 33));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Cpich Power");
            cell2 = row2.createCell(34);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 34, 34));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Total Power");
            cell2 = row2.createCell(35);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 35, 35));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("boster tma");
            cell2 = row2.createCell(36);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 36, 36));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Black list Flag");
            cell2 = row2.createCell(37);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 37, 37));
            cell2.setCellStyle(styleHeader);
            cell2.setCellValue("Lý do");

//header
            while (iterator.hasNext()) {
                Cell2GReportBO cell3G = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(StringUtils.getValue(cell3G.getTinh()));

                cell = row.createCell(1);
                cell.setCellValue(StringUtils.getValue(cell3G.getQuan()));

                cell = row.createCell(2);
                cell.setCellValue(cell3G.getXa() == null ? "" : cell3G.getXa());

                cell = row.createCell(3);
                cell.setCellValue(StringUtils.getValue(cell3G.getLocationNumber()));

                cell = row.createCell(4);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHoatDong()));
                cell = row.createCell(5);
                cell.setCellValue(StringUtils.getValue(cell3G.getHoanCanhRaDoi()));
                cell = row.createCell(6);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenCell()));
                cell = row.createCell(7);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayDangKy()));
                cell = row.createCell(8);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayKiemDuyet()));
                cell = row.createCell(9);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayCapPhep()));
                cell = row.createCell(10);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenTrenHeThong()));
                cell = row.createCell(11);
                cell.setCellValue(StringUtils.getValue(cell3G.getLac()));
                cell = row.createCell(12);
                cell.setCellValue(StringUtils.getValue(cell3G.getCi()));
                cell = row.createCell(13);
                cell.setCellValue(StringUtils.getValue(cell3G.getMaBTS()));
                cell = row.createCell(14);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenBscRnc()));
                cell = row.createCell(15);
                cell.setCellValue(StringUtils.getValue(cell3G.getCode()));
                cell = row.createCell(16);
                cell.setCellValue(StringUtils.getValue(cell3G.getDcHsdpa42m()));
                cell = row.createCell(17);
                cell.setCellValue(StringUtils.getValue(cell3G.getFrequencyBand()));

                cell = row.createCell(18);
                cell.setCellValue(StringUtils.getValue(String.valueOf(cell3G.getNodeId())));
                cell = row.createCell(19);
                cell.setCellValue(StringUtils.getValue(cell3G.getTrangThaiHd()));
                cell = row.createCell(20);
                cell.setCellValue(StringUtils.getValue(cell3G.getTrangThaiQl()));
                cell = row.createCell(21);
                cell.setCellValue(StringUtils.getValue(cell3G.getLatitude()));
                cell = row.createCell(22);
                cell.setCellValue(StringUtils.getValue(cell3G.getLongitude()));

                cell = row.createCell(23);
                cell.setCellValue(StringUtils.getValue(cell3G.getAzimuth()));
                cell = row.createCell(24);
                cell.setCellValue(StringUtils.getValue(cell3G.getMechanical()));
                cell = row.createCell(25);
                cell.setCellValue(StringUtils.getValue(cell3G.getTotalTilt()));
                cell = row.createCell(26);
                cell.setCellValue(StringUtils.getValue(cell3G.getAntennaHigh()));

                cell = row.createCell(27);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenAnten()));

                cell = row.createCell(28);
                cell.setCellValue(StringUtils.getValue(cell3G.getAntennaGain()));
                cell = row.createCell(29);

                cell.setCellValue(StringUtils.getValue(cell3G.getSpecialCoverage()));
                cell = row.createCell(30);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenThietBi()));
                cell = row.createCell(31);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenLoaiTram()));
                cell = row.createCell(32);
                cell.setCellValue(StringUtils.getValue(cell3G.getNoOfCarrier()));

                cell = row.createCell(33);
                cell.setCellValue(StringUtils.getValue(cell3G.getCpichPower()));
                cell = row.createCell(34);
                cell.setCellValue(StringUtils.getValue(cell3G.getTotalPower()));
                cell = row.createCell(35);
                cell.setCellValue(StringUtils.getValue(cell3G.getBosterTma()));

                cell = row.createCell(36);
                cell.setCellValue(StringUtils.getValue(cell3G.getBlackListFlag()));
                cell = row.createCell(37);
                cell.setCellValue(StringUtils.getValue(cell3G.getLyDo()));

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void writeBtsconfig(List<BtsReportBO> temp, Sheet sheet) {
        try {
            Iterator<BtsReportBO> iterator = temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                BtsReportBO cell3G = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(StringUtils.getValue(cell3G.getTinh()));

                cell = row.createCell(1);
                cell.setCellValue(StringUtils.getValue(cell3G.getQuan()));

                cell = row.createCell(2);
                cell.setCellValue(StringUtils.getValue(cell3G.getXa()));

                cell = row.createCell(3);
                cell.setCellValue(StringUtils.getValue(cell3G.getDiachi()));

                cell = row.createCell(4);
                cell.setCellValue(StringUtils.getValue(cell3G.getLocationNumber()));

                cell = row.createCell(5);
                cell.setCellValue(""); //thon-doi

                cell = row.createCell(6);
                cell.setCellValue(""); //xom

                cell = row.createCell(7);
                cell.setCellValue("");  //pho-duong

                cell = row.createCell(8);
                cell.setCellValue("");  //ngo
                cell = row.createCell(9);
                cell.setCellValue("");  //ngach
                cell = row.createCell(10);
                cell.setCellValue("");  //so nha

                cell = row.createCell(11);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHoatDong()));

                cell = row.createCell(12);
                cell.setCellValue(StringUtils.getValue(cell3G.getHoanCanhRaDoi()));

                cell = row.createCell(13);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenBts()));

                cell = row.createCell(14);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayDangKy()));

                cell = row.createCell(15);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayKiemDuyet()));

                cell = row.createCell(16);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayCapPhep()));

                cell = row.createCell(17);
                cell.setCellValue(StringUtils.getValue(cell3G.getTrangThaiHdId()));

                cell = row.createCell(18);
                cell.setCellValue(StringUtils.getValue(cell3G.getTrangThaiQlId()));

                cell = row.createCell(19);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenTrenHeThong()));

                cell = row.createCell(20);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenBscRnc()));

                cell = row.createCell(21);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenBscRncQl()));

                cell = row.createCell(22);
                cell.setCellValue(StringUtils.getValue(cell3G.getMscMss()));

                cell = row.createCell(23);
                cell.setCellValue(StringUtils.getValue(cell3G.getSgsn()));

                cell = row.createCell(24);
                cell.setCellValue(StringUtils.getValue(cell3G.getMaNode()));

                cell = row.createCell(25);
                cell.setCellValue(StringUtils.getValue(cell3G.getDcHsdpa42M()));

                cell = row.createCell(26);
                cell.setCellValue(StringUtils.getValue(cell3G.getId()));

                cell = row.createCell(27);
                cell.setCellValue(StringUtils.getValue(cell3G.getFilterUser()));
//
                cell = row.createCell(28);
                cell.setCellValue(StringUtils.getValue(cell3G.getFrequencyBand()));

                cell = row.createCell(29);
                cell.setCellValue(StringUtils.getValue(cell3G.getLatitude()));

                cell = row.createCell(30);
                cell.setCellValue(StringUtils.getValue(cell3G.getLongitude()));

                cell = row.createCell(31);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenThietBi()));

                cell = row.createCell(32);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenLoaiTram()));

                cell = row.createCell(33);
                cell.setCellValue(StringUtils.getValue(cell3G.getCauhinh()));

                cell = row.createCell(34);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayBaoDuong()));

                cell = row.createCell(35);
                cell.setCellValue(StringUtils.getValue(cell3G.getDonViThucHien()));

                cell = row.createCell(36);
                cell.setCellValue(StringUtils.getValue(cell3G.getMaKiemDinh()));

                cell = row.createCell(37);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHieuLuc()));

                cell = row.createCell(38);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHetHieuLuc()));

                cell = row.createCell(39);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenNguoiQL()));

                cell = row.createCell(40);
                cell.setCellValue(StringUtils.getValue(cell3G.getSoDTNgQL()));

                cell = row.createCell(41);
                cell.setCellValue(StringUtils.getValue(cell3G.getChungCSHT())); // chua lay duoc du lieu

                cell = row.createCell(42);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiTramCSHT()));

                cell = row.createCell(43);
                cell.setCellValue(StringUtils.getValue(cell3G.getDocaoAnTen()));

                cell = row.createCell(44);
                cell.setCellValue(StringUtils.getValue(cell3G.getDoCaoNhaDatAnten()));

                cell = row.createCell(45);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiCotAnten()));

                cell = row.createCell(46);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHDTuNguon()));

                cell = row.createCell(47);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiTuNguonId()));

                cell = row.createCell(48);
                cell.setCellValue(StringUtils.getValue(cell3G.getDongCungCapTuNguon()));

                cell = row.createCell(49);
                cell.setCellValue(StringUtils.getValue(cell3G.getSoModuleTuNguon()));

                cell = row.createCell(50);
                cell.setCellValue(StringUtils.getValue(cell3G.getDongTieuThuTuNguon()));

                cell = row.createCell(51);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHDMayNo()));

                cell = row.createCell(52);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiHinhMayNo()));

                cell = row.createCell(53);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiMayNoId()));

                cell = row.createCell(54);
                cell.setCellValue(StringUtils.getValue(cell3G.getCongSuatMayNo()));

                cell = row.createCell(55);
                cell.setCellValue(StringUtils.getValue(cell3G.getMayNoCoDinhDiDong()));

                cell = row.createCell(56);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHDAccu()));

                cell = row.createCell(57);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiAcQuyId()));

                cell = row.createCell(58);
                cell.setCellValue(StringUtils.getValue(cell3G.getDungLuongAcc()));

                cell = row.createCell(59);
                cell.setCellValue(StringUtils.getValue(cell3G.getDienApBinh()));

                cell = row.createCell(60);
                cell.setCellValue(StringUtils.getValue(cell3G.getSoLuongAccuBinh()));

                cell = row.createCell(61);
                cell.setCellValue(StringUtils.getValue(cell3G.getThoiGianHdSauMatDien()));

                cell = row.createCell(62);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayBaoDuongAccu()));

                cell = row.createCell(63);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiTruyenDanId()));

                cell = row.createCell(64);
                cell.setCellValue(StringUtils.getValue(cell3G.getGiaoDienTruyenDan()));

                cell = row.createCell(65);
                cell.setCellValue(StringUtils.getValue(cell3G.getDuongLuongTruyenDan()));

                cell = row.createCell(66);
                cell.setCellValue(StringUtils.getValue(cell3G.getDienTroTiepDia()));

                cell = row.createCell(67);
                cell.setCellValue(StringUtils.getValue(cell3G.getSoLuongDieuHoa()));

                cell = row.createCell(68);
                cell.setCellValue(StringUtils.getValue(cell3G.getTongCongSuatDieuHoa()));

                cell = row.createCell(69);
                cell.setCellValue(StringUtils.getValue(cell3G.getMaBuilding()));

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void writeNodeBconfig(List<NodeBReportBO> temp, Sheet sheet) {
        try {
            Iterator<NodeBReportBO> iterator = temp.iterator();
            Cell cell;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                NodeBReportBO cell3G = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(StringUtils.getValue(cell3G.getTinh()));

                cell = row.createCell(1);
                cell.setCellValue(StringUtils.getValue(cell3G.getQuan()));

                cell = row.createCell(2);
                cell.setCellValue(StringUtils.getValue(cell3G.getXa()));

                cell = row.createCell(3);
                cell.setCellValue(StringUtils.getValue(cell3G.getDiachi()));

                cell = row.createCell(4);
                cell.setCellValue(StringUtils.getValue(cell3G.getLocationNumber()));

                cell = row.createCell(5);
                cell.setCellValue(""); //thon-doi

                cell = row.createCell(6);
                cell.setCellValue(""); //xom

                cell = row.createCell(7);
                cell.setCellValue("");  //pho-duong

                cell = row.createCell(8);
                cell.setCellValue("");  //ngo
                cell = row.createCell(9);
                cell.setCellValue("");  //ngach
                cell = row.createCell(10);
                cell.setCellValue("");  //so nha

                cell = row.createCell(11);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHoatDong()));

                cell = row.createCell(12);
                cell.setCellValue(StringUtils.getValue(cell3G.getHoanCanhRaDoi()));

                cell = row.createCell(13);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenBts()));

                cell = row.createCell(14);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayDangKy()));

                cell = row.createCell(15);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayKiemDuyet()));

                cell = row.createCell(16);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayCapPhep()));

                cell = row.createCell(17);
                cell.setCellValue(StringUtils.getValue(cell3G.getTrangThaiHdId()));

                cell = row.createCell(18);
                cell.setCellValue(StringUtils.getValue(cell3G.getTrangThaiQlId()));

                cell = row.createCell(19);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenTrenHeThong()));

                cell = row.createCell(20);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenBscRnc()));

                cell = row.createCell(21);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenBscRncQl()));

                cell = row.createCell(22);
                cell.setCellValue(StringUtils.getValue(cell3G.getMscMss()));

                cell = row.createCell(23);
                cell.setCellValue(StringUtils.getValue(cell3G.getSgsn()));

                cell = row.createCell(24);
                cell.setCellValue(StringUtils.getValue(cell3G.getMaNode()));

                cell = row.createCell(25);
                cell.setCellValue(StringUtils.getValue(cell3G.getDcHsdpa42M()));

                cell = row.createCell(26);
                cell.setCellValue(StringUtils.getValue(cell3G.getId()));

                cell = row.createCell(27);
                cell.setCellValue(StringUtils.getValue(cell3G.getFilterUser()));
//
                cell = row.createCell(28);
                cell.setCellValue(StringUtils.getValue(cell3G.getFrequencyBand()));

                cell = row.createCell(29);
                cell.setCellValue(StringUtils.getValue(cell3G.getLatitude()));

                cell = row.createCell(30);
                cell.setCellValue(StringUtils.getValue(cell3G.getLongitude()));

                cell = row.createCell(31);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenThietBi()));

                cell = row.createCell(32);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenLoaiTram()));

                cell = row.createCell(33);
                cell.setCellValue(StringUtils.getValue(cell3G.getCauhinh()));

                cell = row.createCell(34);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayBaoDuong()));

                cell = row.createCell(35);
                cell.setCellValue(StringUtils.getValue(cell3G.getDonViThucHien()));

                cell = row.createCell(36);
                cell.setCellValue(StringUtils.getValue(cell3G.getMaKiemDinh()));

                cell = row.createCell(37);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHieuLuc()));

                cell = row.createCell(38);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHetHieuLuc()));

                cell = row.createCell(39);
                cell.setCellValue(StringUtils.getValue(cell3G.getTenNguoiQL()));

                cell = row.createCell(40);
                cell.setCellValue(StringUtils.getValue(cell3G.getSoDTNgQL()));

                cell = row.createCell(41);
                cell.setCellValue(StringUtils.getValue(cell3G.getChungCSHT())); // chua lay duoc du lieu

                cell = row.createCell(42);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiTramCSHT()));

                cell = row.createCell(43);
                cell.setCellValue(StringUtils.getValue(cell3G.getDocaoAnTen()));

                cell = row.createCell(44);
                cell.setCellValue(StringUtils.getValue(cell3G.getDoCaoNhaDatAnten()));

                cell = row.createCell(45);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiCotAnten()));

                cell = row.createCell(46);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHDTuNguon()));

                cell = row.createCell(47);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiTuNguonId()));

                cell = row.createCell(48);
                cell.setCellValue(StringUtils.getValue(cell3G.getDongCungCapTuNguon()));

                cell = row.createCell(49);
                cell.setCellValue(StringUtils.getValue(cell3G.getSoModuleTuNguon()));

                cell = row.createCell(50);
                cell.setCellValue(StringUtils.getValue(cell3G.getDongTieuThuTuNguon()));

                cell = row.createCell(51);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHDMayNo()));

                cell = row.createCell(52);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiHinhMayNo()));

                cell = row.createCell(53);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiMayNoId()));

                cell = row.createCell(54);
                cell.setCellValue(StringUtils.getValue(cell3G.getCongSuatMayNo()));

                cell = row.createCell(55);
                cell.setCellValue(StringUtils.getValue(cell3G.getMayNoCoDinhDiDong()));

                cell = row.createCell(56);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayHDAccu()));

                cell = row.createCell(57);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiAcQuyId()));

                cell = row.createCell(58);
                cell.setCellValue(StringUtils.getValue(cell3G.getDungLuongAcc()));

                cell = row.createCell(59);
                cell.setCellValue(StringUtils.getValue(cell3G.getDienApBinh()));

                cell = row.createCell(60);
                cell.setCellValue(StringUtils.getValue(cell3G.getSoLuongAccuBinh()));

                cell = row.createCell(61);
                cell.setCellValue(StringUtils.getValue(cell3G.getThoiGianHdSauMatDien()));

                cell = row.createCell(62);
                cell.setCellValue(StringUtils.getValue(cell3G.getNgayBaoDuongAccu()));

                cell = row.createCell(63);
                cell.setCellValue(StringUtils.getValue(cell3G.getLoaiTruyenDanId()));

                cell = row.createCell(64);
                cell.setCellValue(StringUtils.getValue(cell3G.getGiaoDienTruyenDan()));

                cell = row.createCell(65);
                cell.setCellValue(StringUtils.getValue(cell3G.getDuongLuongTruyenDan()));

                cell = row.createCell(66);
                cell.setCellValue(StringUtils.getValue(cell3G.getDienTroTiepDia()));

                cell = row.createCell(67);
                cell.setCellValue(StringUtils.getValue(cell3G.getSoLuongDieuHoa()));

                cell = row.createCell(68);
                cell.setCellValue(StringUtils.getValue(cell3G.getTongCongSuatDieuHoa()));

//                cell = row.createCell(69);
//                cell.setCellValue(StringUtils.getValue(cell3G.getMaBuilding()));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
