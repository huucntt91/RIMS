package com.vnpt.media.rims.controller.nodes;

import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.ExcelDeleteNodeBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.ImportNodeForm;
import com.vnpt.media.rims.formbean.TableFormDeleteNode;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/excelDeleteNode")
public class ExcelDeleteController {

    private static final Logger logger = LogManager.getLogger(ExcelDeleteController.class);
    private static final String FORM_NEW = "nodes/cell/excelImportDelete";
    private static final String FORM_DESTROY = "nodes/removeNode/destroyObject";
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @ModelAttribute("cellNewForm") CellNewForm cellNewForm,
            ModelMap mm, HttpServletRequest request) {
        ImportNodeForm importNodeForm = new ImportNodeForm();
        mm.put("importNodeForm", importNodeForm);
        return FORM_NEW;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @ModelAttribute(value = "importNodeForm") ImportNodeForm importNodeForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request,HttpServletResponse response) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        List<ExcelDeleteNodeBO> items = null;
        try {
            
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(StringUtils.getFolderTemp() + File.separator + importNodeForm.getFile().getOriginalFilename());
            importNodeForm.getFile().transferTo(convFile);
            NodesFacade nodeFacade = new NodesFacade();
            
             items = ExOM.mapFromExcel(convFile)
                    .to(ExcelDeleteNodeBO.class)
                    .mapSheet(0,2);
            List<String> result = new ArrayList<>();
            logger.info("user: {}, ip: {}, call excelDeleteNode({})", user.getUsername(), request.getRemoteAddr(), items.size());
            for (int i = 0; i < items.size(); i++) {
                String strCheck = nodeFacade.excelDeleteNode(true, user.getId(), items.get(i), attr, messageSource, locale);
                result.add(strCheck);

            }
            logger.info("user: {}, ip: {}, call writeExcel {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            writeResult(convFile, result,StringUtils.getFolderTemp() + File.separator + "result_reg_off_node_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx", response);
            logger.info("user: {}, ip: {}, end writeExcel {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        } catch (Exception e) {
            e.printStackTrace();
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.contains("java.io.FileNotFoundException")) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "File import không tồn tại"));
                } else {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        finally{
            items = null;
        }
        return "redirect:/excelDeleteNode/init";
    }

//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String update(ModelMap mm, @ModelAttribute(value = "importNodeForm") ImportNodeForm importNodeForm,
//            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request
//    ) {
//        NodesFacade nodeFacade = new NodesFacade();
//        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//
//        try {
//            List<ExcelDeleteNodeBO> items = tableDeleteForm.getModels();
//            for (int i = 0; i < items.size(); i++) {
//                if (items.get(i).isCheck()) {
//                    String strCheck = nodeFacade.excelDeleteNode(true, user.getId(), items.get(i), attr, messageSource, locale);
//                    items.get(i).setCheckDB(strCheck);
//                }
//            }
//            //attr.addFlashAttribute("tableDeleteForm", tableDeleteForm);
//            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//        } catch (ServiceException e) {
//            LOGGER.error(e.getMessage(), e);
//            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xẩy ra vui lòng thử lại"));
//        }
//        return "redirect:/excelDeleteNode/init";
//
//    }
    
    public File writeResult(File inputFile, List<String> temp, String filePath, HttpServletResponse response) {
        try {
            XSSFWorkbook workbook;
            try (FileInputStream fin = new FileInputStream(inputFile)) {
                workbook = new XSSFWorkbook(fin);
                Sheet sheet = workbook.getSheetAt(0);
                Cell cell;
                Row row;
                int rowIndex = 2;
                for (int i = 0; i < temp.size(); i++) {
                    row = sheet.getRow(rowIndex++);
                    if (row != null) {
                        CellStyle style = sheet.getWorkbook().createCellStyle();
                        row.setRowStyle(style);
                        cell = row.createCell(0);
                        cell.setCellValue(temp.get(i));
                    }
                }
            }
            File file = new File(filePath);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            workbook.close();
            if (file.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(file)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            return file;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST, params = {"excel"})
    public String insertBts(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableDeleteForm") TableFormDeleteNode tableDeleteForm, RedirectAttributes attr, Locale locale, HttpServletRequest request,
            HttpServletResponse response
    ) {
        export(request, response, tableDeleteForm.getModels(), "huy_bts", type);
        return "redirect:/excelDeleteNode/init/" + type;

    }

    public void export(HttpServletRequest request, HttpServletResponse response, List<?> items, String name, String type) {
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");

        String fileName = "Template_CAPNHAT_CELL.xls";
        File fileTemplate = new File(dataDirectory + File.separator + fileName);
        File fileResult = writeExcel(fileTemplate, items, name);
        if (fileResult.exists()) {
            response.setContentType("application/excel");
            fileName = "result_HUY_NODE.xls";
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                response.getOutputStream().flush();

            } catch (IOException ex) {
            }
        }
    }

    public File writeExcel(File fileTemplate, List<?> temp, String name) {
        try {
            HSSFWorkbook workbook;
            try (FileInputStream fin = new FileInputStream(fileTemplate)) {
                workbook = new HSSFWorkbook(fin);
                Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
                Iterator<ExcelDeleteNodeBO> iterator = (Iterator<ExcelDeleteNodeBO>) temp.iterator();
                Cell cell;
                Row row;
                int rowIndex = 2;
                //header
                while (iterator.hasNext()) {
                    ExcelDeleteNodeBO item = iterator.next();

                    row = sheet.createRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    //vendor
                    cell = row.createCell(0);
                    cell.setCellValue(item.getCheckDB());
                    cell = row.createCell(1);
                    cell.setCellValue(item.getLoaiNE());
                    cell = row.createCell(2);
                    cell.setCellValue(item.getCode());

                    cell = row.createCell(3);
                    cell.setCellValue(item.getLyDo());
                }
            }
            File file = new File("result_off_node.xlsx");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            workbook.close();
            return file;

        } catch (IOException e) {

        }
        return null;
    }

    @RequestMapping(value = "/DestroyObject", method = RequestMethod.GET)
    public String DestroyObject(@ModelAttribute("cellNewForm") CellNewForm cellNewForm,
            ModelMap mm, HttpServletRequest request) {
        ImportNodeForm importNodeForm = new ImportNodeForm();
        mm.put("importNodeForm", importNodeForm);
        return FORM_DESTROY;
    }

    @RequestMapping(value = "/checkDestroy", method = RequestMethod.POST)
    public String checkDestroy(ModelMap mm, @ModelAttribute(value = "importNodeForm") ImportNodeForm importNodeForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(importNodeForm.getFile().getOriginalFilename());
            importNodeForm.getFile().transferTo(convFile);
            NodesFacade nodeFacade = new NodesFacade();

            List<ExcelDeleteNodeBO> items = ExOM.mapFromExcel(convFile)
                    .to(ExcelDeleteNodeBO.class)
                    .map(2);
            for (int i = 0; i < items.size(); i++) {
                String strCheck = nodeFacade.excelDestroyNode(false, user.getId(), items.get(i), attr, messageSource, locale);
                items.get(i).setCheckDB(strCheck);
            }
            TableFormDeleteNode tableDeleteForm = new TableFormDeleteNode();
            tableDeleteForm.setModels(items);
            attr.addFlashAttribute("tableDeleteForm", tableDeleteForm);
        } catch (Exception e) {
            logger.error("Exception :", e);
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.contains("java.io.FileNotFoundException")) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "File import không tồn tại"));
                }
            }
        }
        return "redirect:/excelDeleteNode/DestroyObject";
    }

    @RequestMapping(value = "/updateDestroy", method = RequestMethod.POST)
    public String updateDestroy(ModelMap mm,
            @ModelAttribute("tableDeleteForm") TableFormDeleteNode tableDeleteForm, RedirectAttributes attr, Locale locale, HttpServletRequest request
    ) {
        NodesFacade nodeFacade = new NodesFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

        List<ExcelDeleteNodeBO> items = tableDeleteForm.getModels();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCheck()) {
                String strCheck = nodeFacade.excelDestroyNode(true, user.getId(), items.get(i), attr, messageSource, locale);
                items.get(i).setCheckDB(strCheck);
            }
        }
        attr.addFlashAttribute("tableDeleteForm", tableDeleteForm);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        return "redirect:/excelDeleteNode/DestroyObject";

    }
}
