/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.project;

import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.TramQuyHoachDKExcel;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.StationPlansFacade;
import com.vnpt.media.rims.formbean.ImportNodeForm;
import com.vnpt.media.rims.formbean.RegTramQuyHoachExcel;
import com.vnpt.media.rims.formbean.SearchTramQhForm;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Cyanoa
 */
@Controller
@RequestMapping(value = "/excel_project_station")
public class ExcelTramQHController extends BaseController {

    private static Logger logger = LogManager.getLogger(ExcelTramQHController.class);
    private static final String FORM_NEW = "project/project_station/importExcel/excelAddTramQH";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request, RedirectAttributes attr, @Valid @ModelAttribute(value = "searchTramQhForm") SearchTramQhForm searchTramQhForm) {
        try {
//            //phan quyen theo nhom thuoc tinh
//            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
//            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
//            mm.addAttribute("classAtrrEdit", classAtrrEdit);
//            mm.addAttribute("classAtrrView", classAtrrView);
//            
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            ImportNodeForm groupContactForm = new ImportNodeForm();
            mm.put("groupContactForm", groupContactForm);

            return FORM_NEW;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM_NEW;
    }

    @RequestMapping(value = "/checkFile", method = RequestMethod.POST)
    public void add(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {},mem: {}, them tram quy hoach", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            logger.info("user: {}, ip: {},mem: {}, them tram quy hoach, read file: {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), convFile.getName());
            List<RegTramQuyHoachExcel> items = ExOM.mapFromExcel(convFile)
                    .to(RegTramQuyHoachExcel.class)
                    .map(3);

            List<RegTramQuyHoachExcel> temp = new ArrayList<RegTramQuyHoachExcel>();
            logger.info("user: {}, ip: {},mem: {}, them tram quy hoach, check database items: {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), items.size());
            for (RegTramQuyHoachExcel item : items) {
                if (item.getTenTramQH() == null || item.getTenTramQH().trim().equals("")) {
                    continue;
                }
                String output = face.addtramQHExcel(item, String.valueOf(user.getId()));
//                
//                
//                
                item.setNote(StringUtils.errorRegTramQuyHoach(output));
                temp.add(item);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_reg_tram_quy_hoach.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            logger.info("user: {}, ip: {},mem: {}, them tram quy hoach, write items: {} to excel ", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), items.size());
            File fileResult = writeAddTramQuyHoach(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_reg_tram_quy_hoach.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

            logger.info("user: {}, ip: {},mem: {}, them tram quy hoach ket thuc", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

        // return "redirect:/stationPlansExcelImport/init";
    }

    private File writeAddTramQuyHoach(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<RegTramQuyHoachExcel> iterator = (Iterator<RegTramQuyHoachExcel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                RegTramQuyHoachExcel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());

                cell = row.createCell(1);
                cell.setCellValue(item.getTenTramQH());
                cell = row.createCell(2);
                cell.setCellValue(item.getMaTinh());
                cell = row.createCell(3);
                cell.setCellValue(item.getNamKhoiTao());
                cell = row.createCell(4);
                cell.setCellValue(item.getLatitude());
                cell = row.createCell(5);
                cell.setCellValue(item.getLongiude());
                cell = row.createCell(6);
                cell.setCellValue(item.getLoaiCongNghe());
                cell = row.createCell(7);
                cell.setCellValue(item.getBangTan2G());
                cell = row.createCell(8);
                cell.setCellValue(item.getBangTan3G());
                cell = row.createCell(9);
                cell.setCellValue(item.getBangTan4G());
                cell = row.createCell(10);
                cell.setCellValue(item.getCtPTCSHT());
                cell = row.createCell(11);
                cell.setCellValue(item.getTrangThaiCSHT());
                cell = row.createCell(12);
                cell.setCellValue(item.getDvpheduyetTTC());
                cell = row.createCell(13);
                cell.setCellValue(item.getSohieuVB());
                cell = row.createCell(14);
                cell.setCellValue(item.getNgaypheduyetTTC());
            }
            fin.close();
            File file = new File("reg_tram_quy_hoach.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
