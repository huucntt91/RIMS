/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.nodes;

import com.vnpt.media.rims.controller.project.*;
import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.BlackPointBO;
import com.vnpt.media.rims.bean.TramQuyHoachDKExcel;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.facade.BlackPointFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.StationPlansFacade;
import com.vnpt.media.rims.formbean.ImportNodeForm;
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
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/excel_black_point")
public class ExcelBlackPointController extends BaseController {

    private static Logger logger = LogManager.getLogger(ExcelBlackPointController.class);
    private static final String FORM_NEW = "nodes/blackPoint/importExcel/excelAddBlackPoint";

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
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<BlackPointBO> items = ExOM.mapFromExcel(convFile)
                    .to(BlackPointBO.class)
                    .map(2);

            List<BlackPointBO> temp = new ArrayList<BlackPointBO>();
            for (BlackPointBO item : items) {

                String output = BlackPointFacade.addBlackPointExcel(item, String.valueOf(user.getId()));
                
                item.setNote(StringUtils.errorMessKDBlackList(output));
                temp.add(item);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "mau_dang_ky_diem_den.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeAddBlackPoint(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_mau_dang_ky_diem_den.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

            //attr.addFlashAttribute("listTramDA", temp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("Exception :", e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

        // return "redirect:/stationPlansExcelImport/init";
    }

    public File writeAddBlackPoint(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<BlackPointBO> iterator = (Iterator<BlackPointBO>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 2;
            //header
            while (iterator.hasNext()) {
                BlackPointBO item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());
                cell = row.createCell(1);
                cell.setCellValue(item.getTenTinhTp());
                cell = row.createCell(2);
                cell.setCellValue(item.getTenQuanHuyen());
                cell = row.createCell(3);
                cell.setCellValue(item.getName());
                cell = row.createCell(4);
                cell.setCellValue(item.getLon());
                cell = row.createCell(5);
                cell.setCellValue(item.getLat());
                cell = row.createCell(6);
                cell.setCellValue(item.getAddress());
                cell = row.createCell(7);
                cell.setCellValue(item.getDes());
              
            }
            fin.close();
            File file = new File("black_point.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {

        }
        return null;
    }
}
