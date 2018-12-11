package com.vnpt.media.rims.controller.cell;

import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.PermissionUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.facade.CellsFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.TableFormCell2G;
import com.vnpt.media.rims.formbean.TableFormCell3G;
import com.vnpt.media.rims.formbean.TableFormCell4G;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
@RequestMapping(value = "/cellsExcel")
public class CellsExcelController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(CellsExcelController.class);
    private static final String LIST = "nodes/cell/cellList";
    private static final String FORM_NEW = "nodes/cells/cellNewExcel";
    private static final String FORM_EDIT_2G = "nodes/cells/cellEditExcel";
    private static final String FORM_DELETE = "nodes/cells/cellDeleteExcel";
    ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
    @Autowired
    private MessageSource messageSource;

//    @ModelAttribute("trangThaiHDList")
//    public List findAllTrangThaiHD() {
//        CategoriesFacade facade = new CategoriesFacade();
//        return facade.findAllTrangThaiHD("");
//    }
//
//    @ModelAttribute("bangTanList")
//    public List findAllBangTan() {
//        return BangTanFacade.fc_find_all("");
//    }
//
//    @ModelAttribute("trangThaiQLList")
//    public List findAllTrangThaiQL() {
//        CategoriesFacade facade = new CategoriesFacade();
//        return facade.findAllTrangThaiQL("");
//    }
//
//    @ModelAttribute("thietBiList")
//    public List findAllThietBi() {
//        CategoriesFacade facade = new CategoriesFacade();
//        return facade.findAllThietBi("");
//    }
//
//    @ModelAttribute("neList")
//    public List findAllNE() {
//        CategoriesFacade facade = new CategoriesFacade();
//        return facade.findAllLoaiNe("");
//    }
//
//    @ModelAttribute("tramList")
//    public List findAllTram() {
//        CategoriesFacade facade = new CategoriesFacade();
//        return facade.findAllLoaiTram("");
//    }
//
//    @ModelAttribute("nhomcellList")
//    public List findNhomCell() {
//        return CellGroupFacade.fc_find_all("");
////        return facade.findAllLoaiTram("");
//    }
    @RequestMapping(value = "/init/{type}", method = RequestMethod.GET)
    public String init(//@RequestParam(value = "neTypeId", required = false) String neTypeId,
            @PathVariable(value = "type") String type,
            ModelMap mm, HttpServletRequest request) {
        CellNewExcelBO cellNewExcelBO = new CellNewExcelBO();
        mm.put("cellNewExcelBO", cellNewExcelBO);

        return FORM_NEW;
    }

    //khai bao cell bằng file excel
    @RequestMapping(value = "/importCellNew", method = RequestMethod.POST)
    public void importCellNew(ModelMap mm, @ModelAttribute(value = "importNodeForm") CellNewExcelBO importNodeForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) {
        try {
            String folderTemp = StringUtils.getFolderTemp();
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            ManagerAdminFacade adminFacade = new ManagerAdminFacade();

            File convFile = new File(folderTemp + File.separator + importNodeForm.getFile().getOriginalFilename());
            importNodeForm.getFile().transferTo(convFile);
            CellsFacade cellsFacade = new CellsFacade();
            File fileResult = null;
            boolean resultCheckFile = false;
            switch (importNodeForm.getType()) {
                case "7": {
                    List<Cell4GNewExcelModel> items = ExOM.mapFromExcel(convFile)
                            .to(Cell4GNewExcelModel.class)
                            .mapSheet(0, 2);
                    List<String> listClassCode = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("7"));
                    PermissionUtils.filterUserExcelAttr(items, listClassCode);
                    LOGGER.info("user: {}, ip: {}, call check addCell4gExcel", user.getUsername(), request.getRemoteAddr());
                    Integer[] checkRows = {1, 2};
                    resultCheckFile = StringUtils.checkImportFile(convFile, new File(dataDirectory + File.separator + "Template_DK_CELL_4G.xls"), checkRows);
                    items = cellsFacade.importCell4G(items, resultCheckFile, user.getId());
                    LOGGER.info("user: {}, ip: {}, end check addCell4gExcel: {}", user.getUsername(), request.getRemoteAddr(), items.size());
                    fileResult = writeImportCellExcel(new File(dataDirectory + File.separator + "Template_DK_CELL_4G_Result.xls"), folderTemp, items, "7");
                    break;
                }
                case "6": {
                    LOGGER.info("user: {}, ip: {}, call check addCell3gExcel", user.getUsername(), request.getRemoteAddr());
                    List<Cell3GNewExcelModel> items = ExOM.mapFromExcel(convFile)
                            .to(Cell3GNewExcelModel.class)
                            .mapSheet(0, 2);
                    List<String> listClassCode = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("6"));
                    PermissionUtils.filterUserExcelAttr(items, listClassCode);
                    Integer[] checkRows = {1, 2};
                    resultCheckFile = StringUtils.checkImportFile(convFile, new File(dataDirectory + File.separator + "Template_DK_CELL_3G.xls"), checkRows);
                    items = cellsFacade.importCell3G(items, resultCheckFile, user.getId());
                    fileResult = writeImportCellExcel(new File(dataDirectory + File.separator + "Template_DK_CELL_3G_Result.xls"), folderTemp, items, "6");
                    LOGGER.info("user: {}, ip: {}, end check addCell3gExcel: {}", user.getUsername(), request.getRemoteAddr(), items.size());
                    break;
                }
                case "5": {
                    List<Cell2GNewExcelModel> items = ExOM.mapFromExcel(convFile)
                            .to(Cell2GNewExcelModel.class)
                            .mapSheet(0, 2);
                    Integer[] checkRows = {1, 2};
                    List<String> listClassCode = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
                    PermissionUtils.filterUserExcelAttr(items, listClassCode);

                    resultCheckFile = StringUtils.checkImportFile(convFile, new File(dataDirectory + File.separator + "Template_DK_CELL_2G.xls"), checkRows);
                    LOGGER.info("user: {}, ip: {}, call check addCell2gExcel", user.getUsername(), request.getRemoteAddr());
                    items = cellsFacade.importCell2G(items, resultCheckFile, user.getId());
                    fileResult = writeImportCellExcel(new File(dataDirectory + File.separator + "Template_DK_CELL_2G_Result.xls"), folderTemp, items, "5");
                    LOGGER.info("user: {}, ip: {}, end check addCell2gExcel: {}", user.getUsername(), request.getRemoteAddr(), items.size());
                    break;
                }
                default:
                    break;
            }
            if (fileResult != null && fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }
            }
            attr.addFlashAttribute("type", importNodeForm.getType());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } catch (Throwable ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

    }

    /*
    @RequestMapping(value = "/preCheck", method = RequestMethod.POST)
    public String preCheck(ModelMap mm, @ModelAttribute(value = "importNodeForm") CellNewExcelBO importNodeForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(importNodeForm.getFile().getOriginalFilename());
            importNodeForm.getFile().transferTo(convFile);
            CellsFacade cellsFacade = new CellsFacade();
            switch (importNodeForm.getType()) {
                case "7": {
                    List<Cell4GNewExcelModel> items = ExOM.mapFromExcel(convFile)
                            .to(Cell4GNewExcelModel.class)
                            .mapSheet(0, 2);
                    LOGGER.info("user: {}, ip: {}, call check addCell4gExcel", user.getUsername(), request.getRemoteAddr());
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getTenTrenHeThong() == null || items.get(i).getTenTrenHeThong().isEmpty()) {
                            continue;
                        }
                        String strCheck = cellsFacade.addCell4gExcel(false, items.get(i), user.getId());

                        items.get(i).setCheckDB(strCheck);
                    }
                    LOGGER.info("user: {}, ip: {}, end check addCell4gExcel: {}", user.getUsername(), request.getRemoteAddr(), items.size());
                    TableFormCell4G tableForm = new TableFormCell4G();
                    tableForm.setModels(items);
                    attr.addFlashAttribute("tableFormCell4G", tableForm);
                    break;
                }
                case "6": {
                    LOGGER.info("user: {}, ip: {}, call check addCell3gExcel", user.getUsername(), request.getRemoteAddr());
                    List<Cell3GNewExcelModel> items = ExOM.mapFromExcel(convFile)
                            .to(Cell3GNewExcelModel.class)
                            .mapSheet(0, 2);
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getTenTrenHeThong() == null || items.get(i).getTenTrenHeThong().isEmpty()) {
                            continue;
                        }
                        String strCheck = cellsFacade.addCell3gExcel(false, items.get(i), user.getId());

                        items.get(i).setCheckDB(strCheck);
                    }
                    LOGGER.info("user: {}, ip: {}, end check addCell3gExcel: {}", user.getUsername(), request.getRemoteAddr(), items.size());
                    TableFormCell3G tableForm = new TableFormCell3G();
                    tableForm.setModels(items);
                    attr.addFlashAttribute("tableFormCell3G", tableForm);
                    break;
                }
                case "5": {

                    List<Cell2GNewExcelModel> items = ExOM.mapFromExcel(convFile)
                            .to(Cell2GNewExcelModel.class)
                            .mapSheet(0, 2);
                    LOGGER.info("user: {}, ip: {}, call check addCell2gExcel", user.getUsername(), request.getRemoteAddr());
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getTenTrenHeThong() == null || items.get(i).getTenTrenHeThong().isEmpty()) {
                            continue;
                        }
                        String strCheck = cellsFacade.addCell2gExcel(false, items.get(i), user.getId());

                        items.get(i).setCheckDB(strCheck);
                    }
                    LOGGER.info("user: {}, ip: {}, end check addCell2gExcel: {}", user.getUsername(), request.getRemoteAddr(), items.size());
                    TableFormCell2G tableForm = new TableFormCell2G();
                    tableForm.setModels(items);
                    attr.addFlashAttribute("tableFormCell2G", tableForm);
                    break;
                }
                default:
                    break;
            }

            attr.addFlashAttribute("type", importNodeForm.getType());
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

        return "redirect:/cellsExcel/init/" + importNodeForm.getType();
//        return FORM_NEW;

    }
     */
    @RequestMapping(value = "/updateCell4g", method = RequestMethod.POST)
    public String updateCell4g(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableForm") TableFormCell4G tableForm,
            RedirectAttributes attr, Locale locale, HttpServletRequest request
    ) {
        CellsFacade cellsFacade = new CellsFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

        List<Cell4GNewExcelModel> items = tableForm.getModels();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCheck()) {
                String strCheck = "";
                try {
                    cellsFacade.addCell4gExcel(true, items.get(i), user.getId());
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
                items.get(i).setCheckDB(strCheck);
            }
        }

        //attr.addFlashAttribute("tableForm", tableForm);
        attr.addFlashAttribute("type", type);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Khai báo CELL4G thành công"));
        return "redirect:/cellsExcel/init/" + type;

    }

    @RequestMapping(value = "/updateCell2g", method = RequestMethod.POST)
    public String updateCell2g(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableForm") TableFormCell2G tableForm,
            RedirectAttributes attr, Locale locale, HttpServletRequest request
    ) {
        CellsFacade cellsFacade = new CellsFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

        List<Cell2GNewExcelModel> items = tableForm.getModels();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCheck()) {
                String strCheck = "";
                try {
                    strCheck = cellsFacade.addCell2gExcel(true, items.get(i), user.getId());
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
                items.get(i).setCheckDB(strCheck);
            }
        }

        //attr.addFlashAttribute("tableForm", tableForm);
        attr.addFlashAttribute("type", type);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Khai báo CELL2G thành công"));
        return "redirect:/cellsExcel/init/" + type;

    }

    @RequestMapping(value = "/updateCell3g", method = RequestMethod.POST)
    public String updateCell3g(ModelMap mm,
            @RequestParam(value = "type", required = false) String type,
            @ModelAttribute("tableForm") TableFormCell3G tableForm,
            RedirectAttributes attr, Locale locale, HttpServletRequest request
    ) {
        CellsFacade cellsFacade = new CellsFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

        List<Cell3GNewExcelModel> items = tableForm.getModels();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCheck()) {
                String strCheck = "";
                try {
                    strCheck = cellsFacade.addCell3gExcel(true, items.get(i), user.getId());
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
                items.get(i).setCheckDB(strCheck);
            }
        }

        //attr.addFlashAttribute("tableForm", tableForm);
        attr.addFlashAttribute("type", type);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Khai báo CELL3G thành công"));
        return "redirect:/cellsExcel/init/" + type;

    }

    // code old anh tung
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(ModelMap mm, @ModelAttribute(value = "cellNewExcelBO") CellNewExcelBO cellNewExcelBO,
            @RequestParam(value = "type", required = false) String type,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(cellNewExcelBO.getFile().getOriginalFilename());
            cellNewExcelBO.getFile().transferTo(convFile);
            if (cellNewExcelBO.getType() == "5") {
                List<Cell2GNewExcelModel> items = ExOM.mapFromExcel(convFile)
                        .to(Cell2GNewExcelModel.class)
                        .map(2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
                mm.addAttribute("classAtrr", classAtrr);

                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<String>();
                String temp = "";
                for (int i = 0; i < items.size(); i++) {
                    //temp = cellsFacade.addCell2gExcel(items.get(i), user.getId());
//                    items.get(i).setCheckDB(Convert.error2String(temp));
                    result.add(Convert.errorDKCell2G(temp));
                }

                writeResult(convFile, result, "result_" + convFile.getName(), response);

            } else if (cellNewExcelBO.getType() == "6") {
                List<Cell3GNewExcelModel> items = ExOM.mapFromExcel(convFile)
                        .to(Cell3GNewExcelModel.class)
                        .map(2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("6"));
                mm.addAttribute("classAtrr", classAtrr);

                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<String>();
                String temp = "";
                for (int i = 0; i < items.size(); i++) {
                    //temp = cellsFacade.addCell3gExcel(items.get(i), user.getId());

//                    items.get(i).setCheckDB(Convert.error2String(temp));
                    result.add(Convert.errorDKCell3G(temp));
                }

                writeResult(convFile, result, "result_" + convFile.getName(), response);

            } else if (cellNewExcelBO.getType() == "7") {
                List<Cell4GNewExcelModel> items = ExOM.mapFromExcel(convFile)
                        .to(Cell4GNewExcelModel.class)
                        .map(2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("7"));
                mm.addAttribute("classAtrr", classAtrr);

                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<String>();
                String temp = "";
                for (int i = 0; i < items.size(); i++) {
                    //temp = cellsFacade.addCell4gExcel(items.get(i), user.getId());

//                    items.get(i).setCheckDB(Convert.error2String(temp));
                    result.add(Convert.errorDKCell4G(temp));
                }

                writeResult(convFile, result, "result_" + convFile.getName(), response);

            }
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
        return "redirect:/cellsExcel/init/" + type;
    }

    @RequestMapping(value = "/update/init", method = RequestMethod.GET)
    public String updateInit(
            @ModelAttribute("cellNewForm") CellNewForm cellNewForm,
            ModelMap mm, HttpServletRequest request) {
        CellNewExcelBO cellNewExcelBO = new CellNewExcelBO();
        mm.put("cellNewExcelBO", cellNewExcelBO);
        return FORM_EDIT_2G;
    }

    @RequestMapping(value = "/update/preCheck", method = RequestMethod.POST)
    public String updateCell(ModelMap mm, @ModelAttribute(value = "cellNewExcelBO") CellNewExcelBO cellNewExcelBO,
            @RequestParam(value = "type", required = false) String type,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {
        boolean resultCheckFile = false;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(StringUtils.getFolderTemp() + File.separator + cellNewExcelBO.getFile().getOriginalFilename());
            cellNewExcelBO.getFile().transferTo(convFile);

            // Quyen update ben NET 
            StringBuilder permisMenu = (StringBuilder) request.getSession().getAttribute(Constants.FUNCTION_KEY);
//            if (permisMenu.toString().toUpperCase().contains("UPDATE_CELL_NET_EXCEL")) {
//                List<CellUpdateExcelNetModel> items = ExOM.mapFromExcel(convFile)
//                        .to(CellUpdateExcelNetModel.class)
//                        .mapSheet(0, 2);
//                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
//                mm.addAttribute("classAtrr", classAtrr);
//                CellsFacade cellsFacade = new CellsFacade();
//                List<String> result = new ArrayList<>();
//                String temp;
//                LOGGER.info("user: {}, ip: {}, call updateCellNetRFExcel", user.getUsername(), request.getRemoteAddr());
//                for (int i = 0; i < items.size(); i++) {
//                    temp = cellsFacade.updateCellNetRFExcel(items.get(i), user.getId(), cellNewExcelBO.getType());
//                    result.add(temp);
//                }
//                LOGGER.info("user: {}, ip: {}, end updateCellNetRFExcel {}", user.getUsername(), request.getRemoteAddr(), result.size());
//                LOGGER.info("user: {}, ip: {}, call write Excel Update Cell NET RF", user.getUsername(), request.getRemoteAddr());
//                LOGGER.debug("user: {}, ip: {}, Start Write Excel NET RF {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
//                writeResult(convFile, result, "result_update_cell_net_", response);
//                result.clear();
//                LOGGER.debug("user: {}, ip: {}, End Write Excel NET RF {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
//                LOGGER.info("user: {}, ip: {}, end write Excel Update Cell NET RF", user.getUsername(), request.getRemoteAddr());
//            } else {
            if (cellNewExcelBO.getType().equals("5")) {
                List<Cell2GUpdateExcelModel> items = ExOM.mapFromExcel(convFile)
                        .to(Cell2GUpdateExcelModel.class)
                        .mapSheet(0, 2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
                mm.addAttribute("classAtrr", classAtrr);
                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<>();
                String temp;
                Integer[] checkRows = {1, 2};
                resultCheckFile = StringUtils.checkImportFile(convFile, new File(request.getServletContext().getRealPath("/resources/excel/") + File.separator + "Template_CAPNHAT_CELL_2G.xlsx"), checkRows);

                PermissionUtils.filterUserExcelAttr(items, classAtrr);

                LOGGER.info("user: {}, ip: {}, call updateCell2gExcel", user.getUsername(), request.getRemoteAddr());
                for (Cell2GUpdateExcelModel item : items) {
                    if (resultCheckFile) {
                        item = (Cell2GUpdateExcelModel) StringUtils.trimObject(item);
                        temp = cellsFacade.updateCell2gExcel(item, user.getId());
                        if (temp == null || temp.isEmpty()) {
                            temp = "OK";
                        }
                    } else {
                        temp = resourceBundle.getString("cell.new.import.validate.file");
                    }
                    result.add(temp);
                }
                LOGGER.info("user: {}, ip: {}, end updateCell2gExcel {}", user.getUsername(), request.getRemoteAddr(), result.size());
                LOGGER.debug("user: {}, ip: {}, call write Excel Update Cell 2G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                writeResult(convFile, result, "result_update_cell_2g_", response);
                result.clear();
                LOGGER.debug("user: {}, ip: {}, end write Excel Update Cell 2G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            } else if (cellNewExcelBO.getType().equals("6")) {
                List<Cell3GUpdateExcelModel> items = ExOM.mapFromExcel(convFile)
                        .to(Cell3GUpdateExcelModel.class)
                        .mapSheet(0, 2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("6"));
                mm.addAttribute("classAtrr", classAtrr);
                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<>();
                String temp;
                Integer[] checkRows = {1, 2};
                resultCheckFile = StringUtils.checkImportFile(convFile, new File(request.getServletContext().getRealPath("/resources/excel/") + File.separator + "Template_CAPNHAT_CELL_3G.xlsx"), checkRows);
                PermissionUtils.filterUserExcelAttr(items, classAtrr);
                LOGGER.info("user: {}, ip: {}, call updateCell3gExcel", user.getUsername(), request.getRemoteAddr());
                for (Cell3GUpdateExcelModel item : items) {
                    if (resultCheckFile) {
                        item = (Cell3GUpdateExcelModel) StringUtils.trimObject(item);
                        temp = cellsFacade.updateCell3gExcel(item, user.getId());
                        if (temp == null || temp.isEmpty()) {
                            temp = "OK";
                        }
                    } else {
                        temp = resourceBundle.getString("cell.new.import.validate.file");
                    }
                    result.add(temp);
                }
                LOGGER.info("user: {}, ip: {}, end updateCell3gExcel {}", user.getUsername(), request.getRemoteAddr(), result.size());
                LOGGER.debug("user: {}, ip: {}, call write Excel Update Cell 3G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                writeResult(convFile, result, "result_update_cell_3g_", response);
                result.clear();
                LOGGER.debug("user: {}, ip: {}, end write Excel Update Cell 3G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            } else if (cellNewExcelBO.getType().equals("7")) {
                List<Cell4GUpdateExcelModel> items = ExOM.mapFromExcel(convFile)
                        .to(Cell4GUpdateExcelModel.class)
                        .mapSheet(0, 2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("7"));
                mm.addAttribute("classAtrr", classAtrr);
                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<>();
                String temp;
                Integer[] checkRows = {1, 2};
                resultCheckFile = StringUtils.checkImportFile(convFile, new File(request.getServletContext().getRealPath("/resources/excel/") + File.separator + "Template_CAPNHAT_CELL_4G.xlsx"), checkRows);
                PermissionUtils.filterUserExcelAttr(items, classAtrr);
                LOGGER.info("user: {}, ip: {}, call updateCell4gExcel", user.getUsername(), request.getRemoteAddr());
                for (Cell4GUpdateExcelModel item : items) {
                    if (resultCheckFile) {
                        item = (Cell4GUpdateExcelModel) StringUtils.trimObject(item);
                        temp = cellsFacade.updateCell4gExcel(item, user.getId());
                        if (temp == null || temp.isEmpty()) {
                            temp = "OK";
                        }
                    } else {
                        temp = resourceBundle.getString("cell.new.import.validate.file");
                    }
                    result.add(temp);
                }
                LOGGER.info("user: {}, ip: {}, end updateCell3gExcel {}", user.getUsername(), request.getRemoteAddr(), result.size());
                LOGGER.debug("user: {}, ip: {}, call write Excel Update Cell 4G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                writeResult(convFile, result, "result_update_cell_4g_", response);
                LOGGER.debug("user: {}, ip: {}, end write Excel Update Cell 4G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                result.clear();
            }
//            }
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
        return "redirect:/cellsExcel/update/init";
    }

    @RequestMapping(value = "/update/updateCellCSHT", method = RequestMethod.POST)
    public String updateCellCSHT(ModelMap mm, @ModelAttribute(value = "cellNewExcelBO") CellNewExcelBO cellNewExcelBO,
            @RequestParam(value = "type", required = false) String type,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {
        boolean resultCheckFile = false;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(StringUtils.getFolderTemp() + File.separator + cellNewExcelBO.getFile().getOriginalFilename());
            cellNewExcelBO.getFile().transferTo(convFile);

            // Quyen update ben NET 
            StringBuilder permisMenu = (StringBuilder) request.getSession().getAttribute(Constants.FUNCTION_KEY);

            if (cellNewExcelBO.getType().equals("5")) {
                List<Cell2GUpdateExcelModel> items = ExOM.mapFromExcel(convFile)
                        .to(Cell2GUpdateExcelModel.class)
                        .mapSheet(0, 2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
                mm.addAttribute("classAtrr", classAtrr);
                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<>();
                String temp;
                Integer[] checkRows = {1, 2};
                resultCheckFile = StringUtils.checkImportFile(convFile, new File(request.getServletContext().getRealPath("/resources/excel/") + File.separator + "Template_CAPNHAT_CELL_2G_CSHT.xlsx"), checkRows);
                LOGGER.info("user: {}, ip: {}, call updateCell2gExcel", user.getUsername(), request.getRemoteAddr());
                for (Cell2GUpdateExcelModel item : items) {
                    if (resultCheckFile) {
                        item = (Cell2GUpdateExcelModel) StringUtils.trimObject(item);
                        temp = cellsFacade.updateCell2gExcel(item, user.getId());
                        if (temp == null || temp.isEmpty()) {
                            temp = "OK";
                        }
                    } else {
                        temp = resourceBundle.getString("cell.new.import.validate.file");
                    }
                    result.add(temp);
                }
                LOGGER.info("user: {}, ip: {}, end updateCell2gExcel {}", user.getUsername(), request.getRemoteAddr(), result.size());
                LOGGER.debug("user: {}, ip: {}, call write Excel Update Cell 2G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                writeResult(convFile, result, "result_update_cell_2g_", response);
                result.clear();
                LOGGER.debug("user: {}, ip: {}, end write Excel Update Cell 2G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            } else if (cellNewExcelBO.getType().equals("6")) {
                List<Cell3GUpdateExcelModel> items = ExOM.mapFromExcel(convFile)
                        .to(Cell3GUpdateExcelModel.class)
                        .mapSheet(0, 2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("6"));
                mm.addAttribute("classAtrr", classAtrr);
                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<>();
                String temp;
                Integer[] checkRows = {1, 2};
                resultCheckFile = StringUtils.checkImportFile(convFile, new File(request.getServletContext().getRealPath("/resources/excel/") + File.separator + "Template_CAPNHAT_CELL_3G_CSHT.xlsx"), checkRows);
                LOGGER.info("user: {}, ip: {}, call updateCell3gExcel", user.getUsername(), request.getRemoteAddr());
                for (Cell3GUpdateExcelModel item : items) {
                    if (resultCheckFile) {
                        item = (Cell3GUpdateExcelModel) StringUtils.trimObject(item);
                        temp = cellsFacade.updateCell3gExcel(item, user.getId());
                        if (temp == null || temp.isEmpty()) {
                            temp = "OK";
                        }
                    } else {
                        temp = resourceBundle.getString("cell.new.import.validate.file");
                    }
                    result.add(temp);
                }
                LOGGER.info("user: {}, ip: {}, end updateCell3gExcel {}", user.getUsername(), request.getRemoteAddr(), result.size());
                LOGGER.debug("user: {}, ip: {}, call write Excel Update Cell 3G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                writeResult(convFile, result, "result_update_cell_3g_", response);
                result.clear();
                LOGGER.debug("user: {}, ip: {}, end write Excel Update Cell 3G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            } else if (cellNewExcelBO.getType().equals("7")) {
                List<Cell4GUpdateExcelModel> items = ExOM.mapFromExcel(convFile)
                        .to(Cell4GUpdateExcelModel.class)
                        .mapSheet(0, 2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("7"));
                mm.addAttribute("classAtrr", classAtrr);
                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<>();
                String temp;
                Integer[] checkRows = {1, 2};
                resultCheckFile = StringUtils.checkImportFile(convFile, new File(request.getServletContext().getRealPath("/resources/excel/") + File.separator + "Template_CAPNHAT_CELL_4G_CSHT.xlsx"), checkRows);
                LOGGER.info("user: {}, ip: {}, call updateCell4gExcel", user.getUsername(), request.getRemoteAddr());
                for (Cell4GUpdateExcelModel item : items) {
                    if (resultCheckFile) {
                        item = (Cell4GUpdateExcelModel) StringUtils.trimObject(item);
                        temp = cellsFacade.updateCell4gExcel(item, user.getId());
                        if (temp == null || temp.isEmpty()) {
                            temp = "OK";
                        }
                    } else {
                        temp = resourceBundle.getString("cell.new.import.validate.file");
                    }
                    result.add(temp);
                }
                LOGGER.info("user: {}, ip: {}, end updateCell3gExcel {}", user.getUsername(), request.getRemoteAddr(), result.size());
                LOGGER.debug("user: {}, ip: {}, call write Excel Update Cell 4G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                writeResult(convFile, result, "result_update_cell_4g_", response);
                LOGGER.debug("user: {}, ip: {}, end write Excel Update Cell 4G {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
                result.clear();
            }
//            }
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
        return "redirect:/cellsExcel/update/init";
    }

    @RequestMapping(value = "/update/updateExcelRF", method = RequestMethod.POST)
    public String updateCellRF(ModelMap mm, @ModelAttribute(value = "cellNewExcelBO") CellNewExcelBO cellNewExcelBO,
            @RequestParam(value = "type", required = false) String type,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {
        boolean resultCheckFile = false;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(StringUtils.getFolderTemp() + File.separator + cellNewExcelBO.getFile().getOriginalFilename());
            cellNewExcelBO.getFile().transferTo(convFile);

            // Quyen update ben NET 
            StringBuilder permisMenu = (StringBuilder) request.getSession().getAttribute(Constants.FUNCTION_KEY);

            List<CellUpdateExcelNetModel> items = ExOM.mapFromExcel(convFile)
                    .to(CellUpdateExcelNetModel.class)
                    .mapSheet(0, 2);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
            mm.addAttribute("classAtrr", classAtrr);
            CellsFacade cellsFacade = new CellsFacade();
            List<String> result = new ArrayList<>();
            String temp;
            Integer[] checkRows = {1, 2};
            resultCheckFile = StringUtils.checkImportFile(convFile, new File(request.getServletContext().getRealPath("/resources/excel/") + File.separator + "Template_CAPNHAT_CELL_NET.xlsx"), checkRows);
            LOGGER.info("user: {}, ip: {}, call updateCellNetRFExcel", user.getUsername(), request.getRemoteAddr());
            for (CellUpdateExcelNetModel item : items) {
                if (resultCheckFile) {
                    item = (CellUpdateExcelNetModel) StringUtils.trimObject(item);
                    temp = cellsFacade.updateCellNetRFExcel(item, user.getId(), cellNewExcelBO.getType());
                    if (temp == null || temp.isEmpty()) {
                        temp = "OK";
                    }
                } else {
                    temp = resourceBundle.getString("cell.new.import.validate.file");
                }
                result.add(temp);
            }
//                    for (int i = 0; i < items.size(); i++) {
//                        temp = cellsFacade.updateCellNetRFExcel(items.get(i), user.getId(), cellNewExcelBO.getType());
//                        result.add(temp);
//                    }
            LOGGER.info("user: {}, ip: {}, end updateCellNetRFExcel {}", user.getUsername(), request.getRemoteAddr(), result.size());
            LOGGER.info("user: {}, ip: {}, call write Excel Update Cell NET RF", user.getUsername(), request.getRemoteAddr());
            LOGGER.debug("user: {}, ip: {}, Start Write Excel NET RF {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            writeResult(convFile, result, "result_update_cell_net_", response);
            result.clear();
            LOGGER.debug("user: {}, ip: {}, End Write Excel NET RF {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            LOGGER.info("user: {}, ip: {}, end write Excel Update Cell NET RF", user.getUsername(), request.getRemoteAddr());

//            }
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
        return "redirect:/cellsExcel/update/init";
    }

    @RequestMapping(value = "/delete/init", method = RequestMethod.GET)
    public String deleteInit(
            @ModelAttribute("cellNewForm") CellNewForm cellNewForm,
            ModelMap mm, HttpServletRequest request) {
        CellNewExcelBO cellNewExcelBO = new CellNewExcelBO();
        mm.put("cellNewExcelBO", cellNewExcelBO);
        return FORM_DELETE;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(ModelMap mm, @ModelAttribute(value = "cellNewExcelBO") CellNewExcelBO cellNewExcelBO,
            @RequestParam(value = "type", required = false) String type,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(cellNewExcelBO.getFile().getOriginalFilename());
            cellNewExcelBO.getFile().transferTo(convFile);
            if (cellNewExcelBO.getType() == "5") {
                List<ExcelDeleteNodeBO> items = ExOM.mapFromExcel(convFile)
                        .to(ExcelDeleteNodeBO.class)
                        .map(2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "D", Convert.convertNeTypeToObjectId("5"));
                mm.addAttribute("classAtrr", classAtrr);
                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<String>();
                String temp = "";
                for (int i = 0; i < items.size(); i++) {
                    temp = cellsFacade.deleteCellExcel(items.get(i), user.getId());
//                    items.get(i).setCheckDB(Convert.error2String(temp));
                    result.add(Convert.errorDeleteCell(temp));
                }

                writeResult(convFile, result, "result_" + convFile.getName(), response);

            } else if (cellNewExcelBO.getType() == "6") {
                List<ExcelDeleteNodeBO> items = ExOM.mapFromExcel(convFile)
                        .to(ExcelDeleteNodeBO.class)
                        .map(2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("6"));
                mm.addAttribute("classAtrr", classAtrr);

                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<String>();
                String temp = "";
                for (int i = 0; i < items.size(); i++) {
                    temp = cellsFacade.deleteCellExcel(items.get(i), user.getId());
//                    items.get(i).setCheckDB(Convert.error2String(temp));
                    result.add(Convert.errorDeleteCell(temp));
                }

                writeResult(convFile, result, "result_" + convFile.getName(), response);

            } else if (cellNewExcelBO.getType() == "7") {
                List<ExcelDeleteNodeBO> items = ExOM.mapFromExcel(convFile)
                        .to(ExcelDeleteNodeBO.class)
                        .map(2);
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("7"));
                mm.addAttribute("classAtrr", classAtrr);

                CellsFacade cellsFacade = new CellsFacade();
                List<String> result = new ArrayList<String>();
                String temp = "";
                for (int i = 0; i < items.size(); i++) {
                    temp = cellsFacade.deleteCellExcel(items.get(i), user.getId());
//                    items.get(i).setCheckDB(Convert.error2String(temp));
                    result.add(Convert.errorDeleteCell(temp));
                }

                writeResult(convFile, result, "result_" + convFile.getName(), response);

            }
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
        return "redirect:/cellsExcel/delete/init/";
    }

    public File writeResult(File inputFile, List<String> temp, String name, HttpServletResponse response) {
        try {
            LOGGER.info("Write Result File Excel Update! Name file : {}", inputFile.getName());
            Workbook workbook = null;
            try (FileInputStream fin = new FileInputStream(inputFile)) {
                if (inputFile.getName().endsWith(".xlsx") || inputFile.getName().endsWith(".XLSX")) {
                    name = name + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
                    workbook = new XSSFWorkbook(fin);
                } else if (inputFile.getName().endsWith(".xls") || inputFile.getName().endsWith(".XLS")) {
                    name = name + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xls";
                    workbook = new HSSFWorkbook(fin);
                }
                Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
                Cell cell;
                Row row;
                int rowIndex = 2;
                CellStyle style = sheet.getWorkbook().createCellStyle();
                for (int i = 0; i < temp.size(); i++) {
                    row = sheet.getRow(rowIndex++);
                    if (row != null) {
                        row.setRowStyle(style);
                        cell = row.createCell(0);
                        cell.setCellValue(temp.get(i));
                    }
                }
            }
            File file = new File(StringUtils.getFolderTemp() + File.separator + name);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            workbook.close();
            if (file.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + name);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(file)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            return file;

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private File writeImportCellExcel(File fileTemplate, String folderTemp, List<?> temp, String ne_type_id) {
        try {
            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<?> iterator = (Iterator<?>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 2;
            String fileName = "RESULT_DK_CELL";
            switch (ne_type_id) {
                case "5":
                    fileName = fileName.concat("_2G");
                    while (iterator.hasNext()) {
                        Cell2GNewExcelModel item = (Cell2GNewExcelModel) iterator.next();
                        row = sheet.createRow(rowIndex++);
                        CellStyle style = sheet.getWorkbook().createCellStyle();
                        row.setRowStyle(style);
                        //vendor
                        cell = row.createCell(0);
                        cell.setCellValue(item.getCheckDB());
                        cell = row.createCell(1);
                        cell.setCellValue(item.getMaNodeCha());
                        cell = row.createCell(2);
                        cell.setCellValue(item.getNgayHoatDong());
                        cell = row.createCell(3);
                        cell.setCellValue(item.getHoanCanhRaDoi());
                        cell = row.createCell(4);
                        cell.setCellValue(item.getTen_cell());
                        cell = row.createCell(5);
                        cell.setCellValue(item.getTenTrenHeThong());
                        cell = row.createCell(6);
                        cell.setCellValue(item.getLac());
                        cell = row.createCell(7);
                        cell.setCellValue(item.getCi());
                        cell = row.createCell(8);
                        cell.setCellValue(item.getTenThietBi());
                        cell = row.createCell(9);
                        cell.setCellValue(item.getTenTram());
                        cell = row.createCell(10);
                        cell.setCellValue(item.getNoOfCarrier());
                    }
                    break;
                case "6":
                    fileName = fileName.concat("_3G");
                    while (iterator.hasNext()) {
                        Cell3GNewExcelModel item = (Cell3GNewExcelModel) iterator.next();
                        row = sheet.createRow(rowIndex++);
                        CellStyle style = sheet.getWorkbook().createCellStyle();
                        row.setRowStyle(style);
                        //vendor
                        cell = row.createCell(0);
                        cell.setCellValue(item.getCheckDB());
                        cell = row.createCell(1);
                        cell.setCellValue(item.getMaNodeCha());
                        cell = row.createCell(2);
                        cell.setCellValue(item.getNgayHoatDong());
                        cell = row.createCell(3);
                        cell.setCellValue(item.getHoanCanhRaDoi());
                        cell = row.createCell(4);
                        cell.setCellValue(item.getTen_cell());
                        cell = row.createCell(5);
                        cell.setCellValue(item.getTenTrenHeThong());
                        cell = row.createCell(6);
                        cell.setCellValue(item.getLac());
                        cell = row.createCell(7);
                        cell.setCellValue(item.getCi());
                        cell = row.createCell(8);
                        cell.setCellValue(item.getTenThietBi());
                        cell = row.createCell(9);
                        cell.setCellValue(item.getTenTram());
                        cell = row.createCell(10);
                        cell.setCellValue(item.getNoOfCarrier());
                    }
                    break;
                case "7":
                    fileName = fileName.concat("_4G");
                    while (iterator.hasNext()) {
                        Cell4GNewExcelModel item = (Cell4GNewExcelModel) iterator.next();
                        row = sheet.createRow(rowIndex++);
                        CellStyle style = sheet.getWorkbook().createCellStyle();
                        row.setRowStyle(style);
                        //vendor
                        cell = row.createCell(0);
                        cell.setCellValue(item.getCheckDB());
                        cell = row.createCell(1);
                        cell.setCellValue(item.getEnodebId());
                        cell = row.createCell(2);
                        cell.setCellValue(item.getTen_cell());
                        cell = row.createCell(3);
                        cell.setCellValue(item.getHoanCanhRaDoi());
                        cell = row.createCell(4);
                        cell.setCellValue(item.getNgayHoatDong());
                        cell = row.createCell(5);
                        cell.setCellValue(item.getTenTrenHeThong());
                        cell = row.createCell(6);
                        cell.setCellValue(item.getTag());
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
                    break;
                default:
                    break;
            }

            fin.close();
            File file = new File(folderTemp + File.separator + fileName + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xls");
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
