package com.vnpt.media.rims.controller.nodes;

import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.ExcelBtsUpdateBO;
import com.vnpt.media.rims.bean.ExcelCellUpdateBO;
import com.vnpt.media.rims.bean.ImportBtsModel;
import com.vnpt.media.rims.bean.ImportCellModel;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.PermissionUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.BangTanFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.ImportNodeForm;
import com.vnpt.media.rims.formbean.TableFormUpdateBts;
import com.vnpt.media.rims.formbean.TableUpdateForm;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/excelUpdateNode")
public class ExcelUpdateController {

    private static final Logger LOGGER = LogManager.getLogger(ExcelUpdateController.class);
    private static final String FORM_NEW = "nodes/cell/excelImportUpdate";

    @Autowired
    private MessageSource messageSource;

  
    @RequestMapping(value = "/init/{type}", method = RequestMethod.GET)
    public String init(@PathVariable(value = "type") String type,
            @ModelAttribute("cellNewForm") CellNewForm cellNewForm,
            ModelMap mm, HttpServletRequest request) {
        ImportNodeForm importNodeForm = new ImportNodeForm();
        mm.put("importNodeForm", importNodeForm);
        type = type == null ? "1" : type;
        return FORM_NEW;
    }

    @RequestMapping(value = "/preCheck", method = RequestMethod.POST)
    public String preCheck(ModelMap mm, @ModelAttribute(value = "importNodeForm") ImportNodeForm importNodeForm,
            @RequestParam(value = "type", required = false) String type,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(importNodeForm.getFile().getOriginalFilename());
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            importNodeForm.getFile().transferTo(convFile);
            NodesFacade nodeFacade = new NodesFacade();
            if (type.equals("1")) {
                List<ExcelCellUpdateBO> items = ExOM.mapFromExcel(convFile)
                        .to(ExcelCellUpdateBO.class)
                        .map(2);

                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(type));
                PermissionUtils.filterUserExcelAttr(items, classAtrr);

                for (int i = 0; i < items.size(); i++) {
                    String strCheck = nodeFacade.excelUpdateCell(false, user.getId(), items.get(i), attr, messageSource, locale);
                    items.get(i).setCheckDB(strCheck);
                }
                TableUpdateForm tableUpdateForm = new TableUpdateForm();
                tableUpdateForm.setModels(items);
                attr.addFlashAttribute("tableUpdateForm", tableUpdateForm);
            } else if (type.equals("2")) {

                List<ExcelBtsUpdateBO> items = ExOM.mapFromExcel(convFile)
                        .to(ExcelBtsUpdateBO.class)
                        .mapSheet(0, 2);

                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(type));
                PermissionUtils.filterUserExcelAttr(items, classAtrr);

                LOGGER.info("user: {}, ip: {}, call excelUpdateBts ", user.getUsername(), request.getRemoteAddr());
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getLoaiNE() == null || items.get(i).getLoaiNE().isEmpty()) {
                        continue;
                    }
                    String strCheck = nodeFacade.excelUpdateBts(true, user.getId(), items.get(i), attr, messageSource, locale);
                    items.get(i).setCheckDB(strCheck);
                }
                LOGGER.info("user: {}, ip: {}, done excelUpdateBts: {}", user.getUsername(), request.getRemoteAddr(), items.size());

                String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
                String fileName = "result_update_site.xlsx";
                File fileTemplate = new File(dataDirectory + File.separator + fileName);

                LOGGER.debug("user: {}, ip: {}, call write excel update tram {} ", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                File fileResult = writeExcelBTS(fileTemplate, items);
                if (fileResult.exists()) {
                    response.setContentType("application/excel");
                    fileName = "result_update_site" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
                    response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                    try {
                        FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                        response.getOutputStream().flush();
                        items.clear();
                        LOGGER.debug("user: {}, ip: {}, end write excel update tram {} ", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                    } catch (IOException ex) {
                    }
                }
            }

            attr.addFlashAttribute("type", type);
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.contains("java.io.FileNotFoundException")) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "File import không tồn tại"));
                } else {
                    LOGGER.error(e.getMessage(), e);
                }
            } else {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return "redirect:/excelUpdateNode/init/" + type;
//        return FORM_NEW;

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableUpdateForm") TableUpdateForm tableUpdateForm, RedirectAttributes attr, Locale locale, HttpServletRequest request
    ) {
        NodesFacade nodeFacade = new NodesFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

        List<ExcelCellUpdateBO> items = tableUpdateForm.getModels();
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(type));
        PermissionUtils.filterUserExcelAttr(items, classAtrr);

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCheck()) {
                String strCheck = nodeFacade.excelUpdateCell(true, user.getId(), items.get(i), attr, messageSource, locale);
                items.get(i).setCheckDB(strCheck);
            }
        }
        attr.addFlashAttribute("tableUpdateForm", tableUpdateForm);

        attr.addFlashAttribute("type", type);
        attr.addFlashAttribute("tableUpdateForm", tableUpdateForm);
        return "redirect:/excelUpdateNode/init/" + type;

    }

    @RequestMapping(value = "/updateBts", method = RequestMethod.POST)
    public String updateBts(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableFormUpdateBts") TableFormUpdateBts tableFormUpdateBts, RedirectAttributes attr, Locale locale, HttpServletRequest request
    ) {
        NodesFacade nodeFacade = new NodesFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        List<ExcelBtsUpdateBO> items = tableFormUpdateBts.getModels();
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(type));
        PermissionUtils.filterUserExcelAttr(items, classAtrr);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCheck()) {
                String strCheck = nodeFacade.excelUpdateBts(true, user.getId(), items.get(i), attr, messageSource, locale);
                items.get(i).setCheckDB(strCheck);
            }
        }
        attr.addFlashAttribute("tableFormUpdateBts", tableFormUpdateBts);

        attr.addFlashAttribute("type", type);
        attr.addFlashAttribute("tableFormUpdateBts", tableFormUpdateBts);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        return "redirect:/excelUpdateNode/init/" + type;

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, params = {"excel"})
    public String excelCell(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableUpdateForm") TableUpdateForm tableUpdateForm, RedirectAttributes attr, Locale locale, HttpServletRequest request,
            HttpServletResponse response
    ) {
        export(request, response, tableUpdateForm.getModels(), "dk_cell", type);
        return "redirect:/excelUpdateNode/init/" + type;

    }

    @RequestMapping(value = "/updateBts", method = RequestMethod.POST, params = {"excel"})
    public String insertBts(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableFormUpdateBts") TableFormUpdateBts tableFormUpdateBts, RedirectAttributes attr, Locale locale, HttpServletRequest request,
            HttpServletResponse response
    ) {
        export(request, response, tableFormUpdateBts.getModels(), "update_bts", type);
        return "redirect:/excelUpdateNode/init/" + type;

    }

    public void export(HttpServletRequest request, HttpServletResponse response, List<?> items, String name, String type) {
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
        if (type.equals("1")) {
            String fileName = "Template_CAPNHAT_CELL.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeExcelCell(fileTemplate, items, name);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_CAPNHAT_CELL.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
        } else {
            String fileName = "Template_CAPNHAT_BTS.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeExcelBTS(fileTemplate, items);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_CAPNHAT_BTS.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
        }
    }

    public File writeExcelCell(File fileTemplate, List<?> temp, String name) {
        try {
            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<ExcelCellUpdateBO> iterator = (Iterator<ExcelCellUpdateBO>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 2;

            //header
            while (iterator.hasNext()) {
                ExcelCellUpdateBO item = iterator.next();

                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getCheckDB());
                cell = row.createCell(1);
                cell.setCellValue(item.getMaCell());
                cell = row.createCell(2);
                cell.setCellValue(item.getKieuCell());

                cell = row.createCell(3);
                cell.setCellValue(item.getMaNodeCha());
                cell = row.createCell(4);
                cell.setCellValue(item.getTenChoQuanLy());
                cell = row.createCell(5);
                cell.setCellValue(item.getHoanCanhRaDoi());
                cell = row.createCell(6);
                cell.setCellValue(item.getNgayHoatDong());
                cell = row.createCell(7);
                cell.setCellValue(item.getTenTrenHeThong());
                cell = row.createCell(8);
                cell.setCellValue(item.getLac());

                cell = row.createCell(9);
                cell.setCellValue(item.getCi());
                cell = row.createCell(10);
                cell.setCellValue(item.getFrequencyBand());
                cell = row.createCell(11);
                cell.setCellValue(item.getThietBi());
                cell = row.createCell(12);
                cell.setCellValue(item.getLoaiTram());
                cell = row.createCell(13);
                cell.setCellValue(item.getNoOfCarrier());
                cell = row.createCell(14);
                cell.setCellValue(item.getBlacklist());
                cell = row.createCell(15);
                cell.setCellValue(item.getNgayPheDuyet());
            }
            fin.close();
            File file = new File("tram_ke_hoach.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {

        }
        return null;
    }

    public File writeExcelBTS(File fileTemplate, List<?> temp) {
        try {
            FileInputStream fin = new FileInputStream(fileTemplate);
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<ExcelBtsUpdateBO> iterator = (Iterator<ExcelBtsUpdateBO>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 2;

            //header
            while (iterator.hasNext()) {
                ExcelBtsUpdateBO item = iterator.next();

                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getCheckDB());
                cell = row.createCell(1);
                cell.setCellValue(item.getLoaiNE() == null || item.getLoaiNE().isEmpty() ? "Cần phải nhập Loại NE" : item.getLoaiNE());
                cell = row.createCell(2);
                cell.setCellValue(item.getMaBts());

                cell = row.createCell(3);
                cell.setCellValue(item.getBscCode());
                cell = row.createCell(4);
                cell.setCellValue(item.getMaTramDuAn());
                cell = row.createCell(5);

                cell.setCellValue(item.getMaBuilding());
                cell = row.createCell(6);
                cell.setCellValue(item.getTenNguoiQuanLy());
                cell = row.createCell(7);
                cell.setCellValue(item.getSdtNguoiQuanLy());

                cell = row.createCell(8);
                cell.setCellValue(item.getTenChoQuanLy());
                cell = row.createCell(9);
                cell.setCellValue(item.getHoanCanhRaDoi());
                cell = row.createCell(10);
                cell.setCellValue(item.getNgayHoatDong());
                cell = row.createCell(11);
                cell.setCellValue(item.getTenTrenHeThong());
                cell = row.createCell(12);
                cell.setCellValue(item.getTenBscRnc());
                cell = row.createCell(13);
                cell.setCellValue(item.getMscMss());
                cell = row.createCell(14);
                cell.setCellValue(item.getSgsn());
                cell = row.createCell(15);
                cell.setCellValue(item.getDcHsdpa42M());
                cell = row.createCell(16);
                cell.setCellValue(item.getFilterUser());

                cell = row.createCell(17);
                cell.setCellValue(item.getLoaiCongNghe());

                cell = row.createCell(18);
                cell.setCellValue(item.getFrequencyBand());

                cell = row.createCell(19);
                cell.setCellValue(item.getEnodebId());

                cell = row.createCell(20);
                cell.setCellValue(item.getThietBi());
                cell = row.createCell(21);
                cell.setCellValue(item.getLoaiTram());
                cell = row.createCell(22);
                cell.setCellValue(item.getCauHinh());
            }
            fin.close();
            File file = new File("result_update_site.xlsx");
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
