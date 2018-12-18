package com.vnpt.media.rims.controller.nodes;

import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.ImportBtsModel;
import com.vnpt.media.rims.bean.ImportCellModel;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.report.ConfigCellController;
import com.vnpt.media.rims.facade.BangTanFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.ImportNodeForm;
import com.vnpt.media.rims.formbean.TableForm;
import com.vnpt.media.rims.formbean.TableFormImportBts;
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
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "/cellExcelImport")
public class ExcelAddController {

    private static final Logger LOGGER = LogManager.getLogger(ExcelAddController.class);
    private static final String LIST = "nodes/cell/cellList";
    private static final String FORM_NEW = "nodes/cell/siteNewImport";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("trangThaiHDList")
    public List findAllTrangThaiHD() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTrangThaiHD("");
    }

    @ModelAttribute("bangTanList")
    public List findAllBangTan() {
        BangTanFacade facade = new BangTanFacade();
        return facade.fc_find_all("");
    }

    @ModelAttribute("trangThaiQLList")
    public List findAllTrangThaiQL() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTrangThaiQL("");
    }

    @ModelAttribute("thietBiList")
    public List findAllThietBi() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllThietBi("");
    }

    @ModelAttribute("neList")
    public List findAllNE() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllLoaiNe("");
    }

    @ModelAttribute("tramList")
    public List findAllTram() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllLoaiTram("");
    }

    @RequestMapping(value = "/init/{type}", method = RequestMethod.GET)
    public String init(
            @PathVariable(value = "type") String type,
            @ModelAttribute("cellNewForm") CellNewForm cellNewForm,
            ModelMap mm, HttpServletRequest request) {
        ImportNodeForm importNodeForm = new ImportNodeForm();
        mm.put("importNodeForm", importNodeForm);
        mm.put("type", type);
        return FORM_NEW;
    }

    @RequestMapping(value = "/khaiBaoTram", method = RequestMethod.POST)
    public void khaiBaoTram(ModelMap mm, @ModelAttribute(value = "importNodeForm") ImportNodeForm importNodeForm,
            @RequestParam(value = "type", required = false) String type,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response){
        Locale locale = LocaleContextHolder.getLocale();
        try {
            String folderTemp = StringUtils.getFolderTemp();
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(folderTemp + File.separator + importNodeForm.getFile().getOriginalFilename());
            importNodeForm.getFile().transferTo(convFile);
            NodesFacade nodeFacade = new NodesFacade();
            boolean resultCheckFile = false;
            if (type.equals("2")) {
                List<ImportBtsModel> items = ExOM.mapFromExcel(convFile)
                        .to(ImportBtsModel.class)
                        .mapSheet(0, 2);
                Integer[] checkRows = {1, 2};
                resultCheckFile = StringUtils.checkImportFile(convFile, new File(dataDirectory + File.separator + "Template_DK_SITE.xls"), checkRows);
                LOGGER.debug("user: {}, ip: {}, call check importDkBts {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                items = nodeFacade.importSite(items, resultCheckFile, user.getId(), attr, messageSource, locale);
                LOGGER.debug("user: {}, ip: {}, end check importDkBts, size {}, ram: {} ", user.getUsername(), request.getRemoteAddr(), items.size(), Function.getInfoMemory());
                File fileResult = writeExcelBTS(new File(dataDirectory + File.separator + "Template_Result_DK_SITE.xls"), folderTemp, items, "dk_bts");
                if (fileResult.exists()) {
                    response.setContentType("application/excel");
                    response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                    try {
                        FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                        response.getOutputStream().flush();

                    } catch (IOException ex) {
                        LOGGER.error(ex.getMessage(), ex);;
                    }
                }
            }
            attr.addFlashAttribute("type", type);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } catch (Throwable ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
//        return "redirect:/cellExcelImport/init/" + type;

    }

    /*   @RequestMapping(value = "/preCheck", method = RequestMethod.POST)
    public String preCheck(ModelMap mm, @ModelAttribute(value = "importNodeForm") ImportNodeForm importNodeForm,
            @RequestParam(value = "type", required = false) String type,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(importNodeForm.getFile().getOriginalFilename());
            importNodeForm.getFile().transferTo(convFile);
            NodesFacade nodeFacade = new NodesFacade();
            if (type.equals("1")) {
                List<ImportCellModel> items = ExOM.mapFromExcel(convFile)
                        .to(ImportCellModel.class)
                        .mapSheet(0, 2);
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getTenTrenHeThong().isEmpty()) {
                        continue;
                    }
                    String strCheck = nodeFacade.importDkCell(false, user.getId(), items.get(i), attr, messageSource, locale);

                    items.get(i).setCheckDB(strCheck);
                }
                TableForm tableForm = new TableForm();
                tableForm.setModels(items);
                attr.addFlashAttribute("tableForm", tableForm);

            } else if (type.equals("2")) {
                List<ImportBtsModel> items = ExOM.mapFromExcel(convFile)
                        .to(ImportBtsModel.class)
                        .mapSheet(0, 2);

                LOGGER.debug("user: {}, ip: {}, call check importDkBts {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getMaDK() == null || items.get(i).getMaDK().equals("")) {
                        continue;
                    }
                    String strCheck = nodeFacade.importDkBts(false, user.getId(), items.get(i), attr, messageSource, locale);
                    items.get(i).setCheckDB(strCheck);
                }
                LOGGER.debug("user: {}, ip: {}, end check importDkBts, size {}, ram: {} ", user.getUsername(), request.getRemoteAddr(), items.size(), Function.getInfoMemory());

                TableFormImportBts tableForm = new TableFormImportBts();
                tableForm.setModels(items);
                attr.addFlashAttribute("tableFormBts", tableForm);
//                export(request, response, items, "dk_bts");
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

        return "redirect:/cellExcelImport/init/" + type;

    }
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String insert(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableForm") TableForm tableForm,
            RedirectAttributes attr, Locale locale, HttpServletRequest request
    ) {
        NodesFacade nodeFacade = new NodesFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

        List<ImportCellModel> items = tableForm.getModels();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCheck()) {
                String strCheck = nodeFacade.importDkCell(true, user.getId(), items.get(i), attr, messageSource, locale);
                items.get(i).setCheckDB(strCheck);
            }
        }

        attr.addFlashAttribute("tableForm", tableForm);

        attr.addFlashAttribute("type", type);
        return "redirect:/cellExcelImport/init/" + type;

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, params = {"excel"})
    public String excelCell(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableForm") TableForm tableForm,
            RedirectAttributes attr, Locale locale, HttpServletRequest request,
            HttpServletResponse response
    ) {
        export(request, response, tableForm.getModels(), "dk_cell", type);
        return "redirect:/cellExcelImport/init/" + type;

    }

    @RequestMapping(value = "/updateBts", method = RequestMethod.POST, params = {"excel"})
    public String insertBts(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableFormBts") TableFormImportBts tableForm,
            RedirectAttributes attr, Locale locale, HttpServletRequest request,
            HttpServletResponse response
    ) {
        export(request, response, tableForm.getModels(), "dk_bts", type);
        return "redirect:/cellExcelImport/init/" + type;

    }

    @RequestMapping(value = "/updateBts", method = RequestMethod.POST)
    public String insertBts(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableFormBts") TableFormImportBts tableForm,
            RedirectAttributes attr, Locale locale, HttpServletRequest request
    ) {
        NodesFacade nodeFacade = new NodesFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

        LOGGER.debug("user: {}, ip: {}, call insert importDkBts {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        List<ImportBtsModel> items = tableForm.getModels();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCheck()) {
                String strCheck = nodeFacade.importDkBts(true, user.getId(), items.get(i), attr, messageSource, locale);
                items.get(i).setCheckDB(strCheck);
            }
        }

        LOGGER.debug("user: {}, ip: {}, end insert importDkBts, size {}, ram: {} ", user.getUsername(), request.getRemoteAddr(), items.size(), Function.getInfoMemory());

        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        return "redirect:/cellExcelImport/init/" + type;

    }

    public void export(HttpServletRequest request, HttpServletResponse response, List<?> items, String name, String type) {
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
        if (type.equals("1")) {
            String fileName = "Template_DK_CELL.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeExcelCell(fileTemplate, items, name);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_DK_CELL.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
        } else {
            String folderTemp = StringUtils.getFolderTemp();
            String fileName = "Template_DK_BTS.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeExcelBTS(fileTemplate, folderTemp, items, name);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_DK_BTS.xls";
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
            Iterator<ImportCellModel> iterator = (Iterator<ImportCellModel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 2;

            //header
            while (iterator.hasNext()) {
                ImportCellModel item = iterator.next();

                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getCheckDB());
                cell = row.createCell(1);
                cell.setCellValue(item.getNe_name());
                cell = row.createCell(2);
                cell.setCellValue(item.getMaNodeCha());

                cell = row.createCell(3);
                cell.setCellValue(item.getTenChoQuanLy());
                cell = row.createCell(4);
                cell.setCellValue(item.getHoanCanhRaDoi());
                cell = row.createCell(5);
                cell.setCellValue(item.getNgayHoatDong());
                cell = row.createCell(6);
                cell.setCellValue(item.getTenTrenHeThong());
                cell = row.createCell(7);
                cell.setCellValue(item.getLac());
                cell = row.createCell(8);
                cell.setCellValue(item.getCi());

                cell = row.createCell(9);
                cell.setCellValue(item.getFrequenctyBand());
                cell = row.createCell(10);
                cell.setCellValue(item.getTenThietBi());
                cell = row.createCell(11);
                cell.setCellValue(item.getTenTram());
                cell = row.createCell(12);
                cell.setCellValue(item.getNoOfCarrier());
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

    public File writeExcelBTS(File fileTemplate, String folderTemp, List<?> temp, String name) {
        try {
            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<ImportBtsModel> iterator = (Iterator<ImportBtsModel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 2;

            //header
            while (iterator.hasNext()) {
                ImportBtsModel item = iterator.next();

                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getCheckDB());
                cell = row.createCell(1);
                cell.setCellValue(item.getMaDK());
                cell = row.createCell(2);
                cell.setCellValue(item.getNeTypeName());
                cell = row.createCell(3);
                cell.setCellValue(item.getMaBscRnc());
                cell = row.createCell(4);
                cell.setCellValue(item.getMaTramDuAn());
                cell = row.createCell(5);
                cell.setCellValue(item.getTenDonViQuanLy());
                cell = row.createCell(6);
                cell.setCellValue(item.getMaBuilding());
                cell = row.createCell(7);
                cell.setCellValue(item.getNgayHoatDong());
                cell = row.createCell(8);
                cell.setCellValue(item.getHoanCanhRaDoi());
                cell = row.createCell(9);
                cell.setCellValue(item.getTenNguoiQuanLy());

                cell = row.createCell(10);
                cell.setCellValue(item.getTenChoQuanLy());
                cell = row.createCell(11);
                cell.setCellValue(item.getTenTrenHeThong());
                cell = row.createCell(12);
                cell.setCellValue(item.getThietBi());
                cell = row.createCell(13);
                cell.setCellValue(item.getDeviceType());
                cell = row.createCell(14);
                cell.setCellValue(item.getLoaiTram());
                cell = row.createCell(15);
                cell.setCellValue(item.getCauHinh());
                cell = row.createCell(16);
                cell.setCellValue(item.getEnodebId());
                cell = row.createCell(17);
                cell.setCellValue(item.getFrequencyBand());
            }
            fin.close();
            File file = new File(folderTemp + File.separator + "RESULT_DK_SITE_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xls");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {

        }
        return null;
    }

    @RequestMapping(value = "/files/{fileName}", method = RequestMethod.GET)
    public void downloadTemplateContact(@PathVariable("fileName") String fileName, HttpServletRequest request,
            HttpServletResponse response
    ) {
        //If user is not authorized - he should be thrown out from here itself

        //Authorized user will download the file
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
        Path file = Paths.get(dataDirectory, fileName + ".xls");
        if (Files.exists(file)) {

            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
