/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.csht;

import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.*;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.nodes.StationPlansImportController;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.CellsFacade;
import com.vnpt.media.rims.facade.ExcelCSHTFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.ApproveAllForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.ImportNodeForm;
import com.vnpt.media.rims.formbean.RegCSHTExcel;
import com.vnpt.media.rims.formbean.UpdateCSHTExcel;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.ResponseBody;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.FileCopyUtils;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/building")
public class BuildingController {

    private static final Logger LOGGER = LogManager.getLogger(BuildingController.class);
    private static final String LIST = "csht/building/list";
    private static final String LIST_APPROVE = "csht/building/listDuyet";
    private static final String FORM = "csht/building/form";
    private static final String POPUP = "csht/building/popup";
    ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllKhuVuc(String.join(",", tinhManager));
    }

    @RequestMapping(value = "/approveOn", method = RequestMethod.GET)
    public String approveOn(
            ModelMap mm, HttpServletRequest request) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        LOGGER.info("user: {}, ip: {}, call getCSHTDuyet({})", user.getUsername(), request.getRemoteAddr(), tinhTpId);
        List<BuildingBO> list = ExcelCSHTFacade.getCSHTDuyet(tinhTpId);
        LOGGER.debug("user: {}, ip: {}, end getCSHTDuyet {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        mm.put("startRow", 0);
        mm.put("list", list);
        return LIST_APPROVE;
    }

    @RequestMapping(value = "/approveall", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String approveAll(@ModelAttribute(value = "approveForm") ApproveAllForm approveForm, ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        NodesFacade nodesFacade = new NodesFacade();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LOGGER.info("user: {}, ip: {}, call approveAllBuilding({})", user.getUsername(), request.getRemoteAddr(), approveForm.listParam());
        return String.valueOf(nodesFacade.approveAllBuilding(approveForm, user.getId()));
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String sublist(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, @RequestParam(value = "phuongXaId", required = false) String phuongXaId,
            ModelMap mm, HttpServletRequest request) {

        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LOGGER.info("user: {}, ip: {}, Danh sach building {} {} {} {} {} {}", user.getUsername(), request.getRemoteAddr(), page, code, tinhTpId, khuvucId, quanHuyenId, phuongXaId);

        page = page == null ? "1" : page;
        Integer pageInt;
        try {
            pageInt = Integer.parseInt(page);
        } catch (NumberFormatException ex) {
            pageInt = 1;
        }
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        quanHuyenId = quanHuyenId == null ? "" : quanHuyenId;
        phuongXaId = phuongXaId == null ? "" : phuongXaId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        code = code == null ? "" : code.trim();
        CategoriesFacade facade = new CategoriesFacade();

        int totalRows;
        try {
            totalRows = facade.getTotalUser(code, khuvucId, tinhTpId, quanHuyenId, phuongXaId);
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
            return ("redirect:/building/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/building/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Building");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }

        List<BuildingBO> list = new ArrayList<>();
        try {
            LOGGER.info("user: {}, ip: {}, call findAllBuildingBO({},{},{},{},{},{},{})", user.getUsername(), request.getRemoteAddr(), startRow, endRow, code, khuvucId, tinhTpId, quanHuyenId, phuongXaId);
            list = facade.findAllBuildingBO(String.valueOf(startRow), String.valueOf(endRow), "", code, khuvucId, tinhTpId, quanHuyenId, phuongXaId);
            LOGGER.info("user: {}, ip: {}, done findAllBuildingBO: {}", user.getUsername(), request.getRemoteAddr(), list.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        List<SubBuidingBO> subList = new ArrayList<>();
        try {
            for (BuildingBO item : list) {
                SubBuidingBO sub = new SubBuidingBO();
                sub.setBuildingBO(item);

                List<PhuTroBO> phuList = facade.findPhuTroBO(String.valueOf(item.getId()));
                sub.setPhuTroBO(phuList);
                List<NodeBO> tramList = facade.findNodeBOByBuilding(String.valueOf(item.getId()), "");
                List<NodeBO> cellList = new ArrayList<>();
                if (tramList != null && tramList.size() > 0) {
                    for (int i = 0; i < tramList.size(); i++) {
                        List<NodeBO> cell = facade.findNodeBOByBuilding("", String.valueOf(tramList.get(i).getId()));
                        cellList.addAll(cell);
                    }
                }
                sub.setTram(tramList);
                sub.setCell(cellList);

                subList.add(sub);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        mm.put("startRow", startRow);
        mm.put("list", subList);
        mm.put("phuongXaId", phuongXaId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("khuvucId", khuvucId);
        mm.put("quanHuyenId", quanHuyenId);

        mm.put("code", code);
        return "csht/building/sublist";
    }

    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String popup(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId, @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, @RequestParam(value = "phuongXaId", required = false) String phuongXaId,
            ModelMap mm, HttpServletRequest request, HttpServletResponse response) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LOGGER.info("user: {}, ip: {}, popup building {} {} {} {} {} {}", user.getUsername(), request.getRemoteAddr(), page, code, tinhTpId, khuvucId, quanHuyenId, phuongXaId);

        page = page == null ? "1" : page;
        Integer pageInt;
        try {
            pageInt = Integer.parseInt(page);
        } catch (NumberFormatException ex) {
            pageInt = 1;
        }
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        quanHuyenId = quanHuyenId == null ? "" : quanHuyenId;
        phuongXaId = phuongXaId == null ? "" : phuongXaId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        code = code == null ? "" : code;
        CategoriesFacade facade = new CategoriesFacade();

        int totalRows;
        try {
            totalRows = facade.getTotalUser(code, khuvucId, tinhTpId, quanHuyenId, phuongXaId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            totalRows = 0;
        }

        Page objPage = new Page();

        int numPerPage = Constants.NUMBER_FOR_PAGING;
        int totalPages;

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
            return ("redirect:/building/popup?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/building/popup?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        //objPage.setSubject("Quản lý Building");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<BuildingBO> list = new ArrayList<>();
        try {
            LOGGER.info("user: {}, ip: {}, call findAllBuildingBO({},{},{},{},{},{},{})", user.getUsername(), request.getRemoteAddr(), startRow, endRow, code, khuvucId, tinhTpId, quanHuyenId, phuongXaId);
            list = facade.findAllBuildingBO(String.valueOf(startRow), String.valueOf(endRow), "", code, khuvucId, tinhTpId, quanHuyenId, phuongXaId);
            LOGGER.info("user: {}, ip: {}, done findAllBuildingBO: {}", user.getUsername(), request.getRemoteAddr(), list.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        mm.put("list", list);
        mm.put("phuongXaId", phuongXaId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("quanHuyenId", quanHuyenId);
        mm.put("khuvucId", khuvucId);
        mm.put("code", code);
        mm.put("startRow", startRow);

        return POPUP;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        BuildingBO model = new BuildingBO();
        mm.addAttribute("model", model);
        return FORM;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        LOGGER.debug("view Building");
        try {
            if (StringUtils.hasText(id)) {
                CategoriesFacade facade = new CategoriesFacade();
                List<BuildingBO> list = facade.findAllBuildingBO("", "", id, "", null, "", "", "");
                mm.addAttribute("model", list.get(0));
            }
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return FORM;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        try {
            if (StringUtils.hasText(id)) {
                CategoriesFacade facade = new CategoriesFacade();
                List<BuildingBO> list = facade.findAllBuildingBO("", "", id, "", null, "", "", "");
                if (list != null && !list.isEmpty()) {
                    mm.addAttribute("model", list.get(0));
                }
                mm.addAttribute("isView", 1);
            }
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return "csht/building/detail";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(HttpServletRequest request, ModelMap mm, @Valid @ModelAttribute(value = "model") BuildingBO model, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
                        break;
                    } else {
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                    }
                }
                mm.addAttribute("model", model);
                return "redirect:/building/preAdd";
            }
            CategoriesFacade adminFacade = new CategoriesFacade();
            //check long lat co thuoc quan huyen hay tinh do khong
            if (!adminFacade.checkLongLat(model.getLon(), model.getLat(), model.getQuanHuyenId().toString())) {
                String msg = messageSource.getMessage("buildling.validate.long.lat", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                mm.addAttribute("model", model);
                return "redirect:/building/preAdd";
            }
            //check long lat đã tồn tại hay chưa
            if (!adminFacade.checkLongLatExist(model.getLon(), model.getLat(), null)) {
                //String backUrl = request.getHeader("Referer");
                String msg = messageSource.getMessage("buildling.exist.long.lat", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                mm.addAttribute("model", model);
                return "redirect:/building/preAdd";
            }

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            model.setUserId(String.valueOf(user.getId()));
            if (adminFacade.modifyBuilding("add", model) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/building/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                //mm.addAttribute("dvbo", cPbo);
                return "redirect:/building/preAdd";
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/building/preAdd";
        }

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, ModelMap mm, @Valid @ModelAttribute(value = "model") BuildingBO model, BindingResult bindingResult, RedirectAttributes attr) throws Exception {

        Locale locale = LocaleContextHolder.getLocale();
        try {
            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
                        break;
                    } else {
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                    }
                }
                mm.addAttribute("model", model);
                return "redirect:/building/view/" + model.getId();
            }
            CategoriesFacade adminFacade = new CategoriesFacade();
            //check long lat co thuoc quan huyen hay tinh do khong
            if (!adminFacade.checkLongLat(model.getLon(), model.getLat(), model.getQuanHuyenId().toString())) {
                String msg = messageSource.getMessage("buildling.validate.long.lat", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                mm.addAttribute("model", model);
                return "redirect:/building/view/" + model.getId();
            }
            //check xem longitude, latidue đã tồn tại hay chưa
            //check long lat đã tồn tại hay chưa
            if (!adminFacade.checkLongLatExist(model.getLon(), model.getLat(), model.getId().toString())) {
                String msg = messageSource.getMessage("buildling.exist.long.lat", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                mm.addAttribute("model", model);
                return "redirect:/building/view/" + model.getId();
            }

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            model.setUserId(String.valueOf(user.getId()));
            if (adminFacade.modifyBuilding("edit", model) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/building/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/building/view/" + model.getId();
            }

        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/building/view/" + model.getId();
        }

    }

    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "Id") String Id,
            Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {

        try {
            CategoriesFacade adminFacade = new CategoriesFacade();
            BuildingBO model = new BuildingBO();
            model.setId(Long.parseLong(Id));
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String checkDelete = adminFacade.checkBeforeDeleteBuilding(Id);
            if (checkDelete == null || checkDelete.isEmpty()) {
                LOGGER.info("user: {}, ip: {}, call modifyBuilding({},{})", user.getUsername(), request.getRemoteAddr(), "del", model.getId());
                if (adminFacade.modifyBuilding("del", model) > -1) {

                    String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                    return "redirect:/building/init";
                } else {
                    String msg = messageSource.getMessage("admin.common.error", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/building/init";
                }
            } else {
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, "Bạn phải offair hoặc mapping sang CSHT của những trạm/cell sau: " + checkDelete));
                return "redirect:/building/init";
            }
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/building/init";
        }

    }

    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
    }

    @ModelAttribute("dvList")
    public List findAllDonVi(HttpServletRequest request) {
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhs = String.join(",", tinhManager);
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllDonVi("", "", tinhs);
    }

    @RequestMapping(value = "/getBuildingLink/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getBuildingLink(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        CategoriesFacade facade = new CategoriesFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(facade.findBuildingLink(id));
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId,
            @RequestParam(value = "phuongXaId", required = false) String phuongXaId,
            HttpServletResponse response) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;

        LOGGER.info("user: {}, ip: {}, call reportCSHT({},{},{},{},{})", user.getUsername(), request.getRemoteAddr(), code, khuvucId, tinhTpId, quanHuyenId, phuongXaId);
        List<BuildingExportBO> temp = ExcelCSHTFacade.reportCSHT(code, khuvucId, tinhTpId, quanHuyenId, phuongXaId);
        LOGGER.info("user: {}, ip: {}, done reportCSHT: {}", user.getUsername(), request.getRemoteAddr(), temp.size());

        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
        String fileName = "Template_CSHT.xlsx";
        File fileTemplate = new File(dataDirectory + File.separator + fileName);
        LOGGER.debug("user: {}, ip: {}, call write excel csht {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        File fileResult = writeBcTemplate(fileTemplate, temp);
        //
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
        try {
            FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
            response.getOutputStream().flush();
            temp.clear();
            LOGGER.debug("user: {}, ip: {}, end write excel csht {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());

        } catch (IOException e) {
            if (e.getMessage().contains("An established connection was aborted by the software in your host machine") || e.getMessage().contains("An existing connection was forcibly closed by the remote host")) {
                LOGGER.info(e.getMessage());
            } else {
                LOGGER.error(e.getMessage(), e);
            }
            temp.clear();
        }

    }

    @RequestMapping(value = "/exceladdcsht", method = RequestMethod.GET)
    public String excelAddCSHT(ModelMap mm, HttpServletRequest request, RedirectAttributes attr) {
        try {
            ImportNodeForm groupContactForm = new ImportNodeForm();
            mm.put("groupContactForm", groupContactForm);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "csht/excel/addCSHT";
    }

    @RequestMapping(value = "/excelupdatecsht", method = RequestMethod.GET)
    public String excelUpdateCSHT(ModelMap mm, HttpServletRequest request, RedirectAttributes attr) {
        try {
            ImportNodeForm groupContactForm = new ImportNodeForm();
            mm.put("groupContactForm", groupContactForm);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "csht/excel/editCSHT";
    }

    @RequestMapping(value = "/khaibaoExcel", method = RequestMethod.POST)
    public void KhaiBaoExcel(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm importFile,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {

        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String folderTemp = StringUtils.getFolderTemp();
            File convFile = new File(folderTemp + File.separator + importFile.getFile().getOriginalFilename());
            importFile.getFile().transferTo(convFile);
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String templateDirectory = request.getServletContext().getRealPath("/resources/excel/");
            List<RegCSHTExcel> items = ExOM.mapFromExcel(convFile)
                    .to(RegCSHTExcel.class)
                    .mapSheet(0, 3);
            Integer[] checkRows = {1, 2, 3};
            boolean resultCheckFile = false;
            LOGGER.info("user: {}, ip: {}, call excelAddCsht ", user.getUsername(), request.getRemoteAddr());
            resultCheckFile = StringUtils.checkImportFile(convFile, new File(templateDirectory + File.separator + "Template_Reg_CSHT.xlsx"), checkRows
            );
            for (RegCSHTExcel item : items) {
                if (resultCheckFile) {
                    item = (RegCSHTExcel) StringUtils.trimObject(item);
                    String result = ExcelCSHTFacade.excelAddCsht(item, String.valueOf(user.getId()));
                    item.setNote(result);
                } else {
                    item.setNote(resourceBundle.getString("cell.new.import.validate.file"));
                }
            }

            LOGGER.info("user: {}, ip: {}, end excelAddCsht {}", user.getUsername(), request.getRemoteAddr(), items.size());
            File fileTemplate = new File(dataDirectory + File.separator + "Result_Reg_CSHT.xlsx");
            LOGGER.debug("user: {}, ip: {}, call write Excel khai bao csht {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            File fileResult = writeRegCsht(fileTemplate, folderTemp, items);
            LOGGER.debug("user: {}, ip: {}, end write Excel khai bao csht {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + "Result_Reg_CSHT_" + dateFormat.format(date) + ".xlsx");
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                    items.clear();
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

    }

    @RequestMapping(value = "/CapNhapExcel", method = RequestMethod.POST)
    public void CapNhapExcel(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm importFile,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String folderTemp = StringUtils.getFolderTemp();
            File convFile = new File(folderTemp + File.separator + importFile.getFile().getOriginalFilename());
            importFile.getFile().transferTo(convFile);
            String templateDirectory = request.getServletContext().getRealPath("/resources/excel/");
            List<UpdateCSHTExcel> items = ExOM.mapFromExcel(convFile)
                    .to(UpdateCSHTExcel.class)
                    .mapSheet(0, 3);
            Integer[] checkRows = {1, 2, 3};
            boolean resultCheckFile = false;
            resultCheckFile = StringUtils.checkImportFile(convFile, new File(templateDirectory + File.separator + "Template_Update_CSHT.xlsx"), checkRows);
            LOGGER.info("user: {}, ip: {}, call excelUpdateCsht ", user.getUsername(), request.getRemoteAddr());
            for (UpdateCSHTExcel item : items) {
                if (resultCheckFile) {
                    item = (UpdateCSHTExcel) StringUtils.trimObject(item);
                    String result = ExcelCSHTFacade.excelUpdateCsht(item, String.valueOf(user.getId()));
                    item.setNote(result);
                } else {
                    item.setNote(resourceBundle.getString("cell.new.import.validate.file"));
                }
            }
            LOGGER.info("user: {}, ip: {}, end excelUpdateCsht {}", user.getUsername(), request.getRemoteAddr(), items.size());

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileTemplate = new File(dataDirectory + File.separator + "Result_Update_CSHT.xlsx");

            LOGGER.debug("user: {}, ip: {}, call write Excel cap nhat csht {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            File fileResult = writeUpdateCsht(fileTemplate, folderTemp, items);
            LOGGER.debug("user: {}, ip: {}, end write Excel cap nhat csht {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                    items.clear();
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

            //attr.addFlashAttribute("listTramDA", temp);
        } catch (Exception e) {
            LOGGER.error("Exception :", e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

        // return "redirect:/stationPlansExcelImport/init";
    }

    public File writeRegCsht(File fileTemplate, String folderTemp, List<?> temp) {
        try {

            XSSFWorkbook workbook;
            try (FileInputStream fin = new FileInputStream(fileTemplate)) {
                workbook = new XSSFWorkbook(fin);
                Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
                Iterator<RegCSHTExcel> iterator = (Iterator<RegCSHTExcel>) temp.iterator();
                Cell cell;
                Row row;
                int rowIndex = 3;
                //header
                while (iterator.hasNext()) {
                    RegCSHTExcel item = iterator.next();
                    row = sheet.createRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    cell = row.createCell(0);
                    cell.setCellValue(item.getNote());
                    cell = row.createCell(1);
                    cell.setCellValue(item.getName());
//
                    cell = row.createCell(2);
                    cell.setCellValue(item.getNgayHdCsht());

                    cell = row.createCell(3);
                    cell.setCellValue(item.getDonVi());

                    cell = row.createCell(4);
                    cell.setCellValue(item.getTinh());

                    cell = row.createCell(5);
                    cell.setCellValue(item.getQuanHuyen());

                    cell = row.createCell(6);
                    cell.setCellValue(item.getXaPhuong());

                    cell = row.createCell(7);
                    cell.setCellValue(item.getDiaChi());

                    DecimalFormat f = new DecimalFormat("0.000000");
                    cell = row.createCell(8);
                    if (item.getLat() == null || item.getLat().isEmpty()) {
                        cell.setCellValue("");
                    } else {
                        try {
                            cell.setCellValue(f.format(Double.parseDouble(item.getLat())));
                        } catch (NumberFormatException e) {
                            cell.setCellValue("");
                            LOGGER.error(e.getMessage(), e);
                        }
                    }

                    cell = row.createCell(9);
                    if (item.getLon() == null || item.getLon().isEmpty()) {
                        cell.setCellValue("");
                    } else {
                        try {
                            cell.setCellValue(f.format(Double.parseDouble(item.getLon())));
                        } catch (NumberFormatException e) {
                            cell.setCellValue("");
                            LOGGER.error(e.getMessage(), e);
                        }
                    }

                    cell = row.createCell(10);
                    cell.setCellValue(item.getChungCsht());

                    cell = row.createCell(11);
                    cell.setCellValue(item.getLoaiCSHT());

                    cell = row.createCell(12);
                    cell.setCellValue(item.getLoaiTramCsht());

                    cell = row.createCell(13);
                    cell.setCellValue(item.getDoCaoAnTen());

                    cell = row.createCell(14);
                    cell.setCellValue(item.getDoCaoNhaDatAnTen());

                    cell = row.createCell(15);
                    cell.setCellValue(item.getLoaiCotAnTen());

                    cell = row.createCell(16);
                    cell.setCellValue(item.getNgayHDTuNguon());

                    cell = row.createCell(17);
                    cell.setCellValue(item.getLoaiTuNguon());

                    f = new DecimalFormat("0.0");
                    cell = row.createCell(18);
                    if (item.getDongCungCapTuNguon() == null || item.getDongCungCapTuNguon().isEmpty()) {
                        cell.setCellValue("");
                    } else {
                        try {
                            cell.setCellValue(item.getDongCungCapTuNguon() == null || item.getDongCungCapTuNguon().isEmpty() ? "" : f.format(Double.parseDouble(item.getDongCungCapTuNguon())));
                        } catch (NumberFormatException e) {
                            LOGGER.error(e.getMessage(), e);
                            cell.setCellValue("");
                        }
                    }

                    cell = row.createCell(19);
                    cell.setCellValue(item.getSoModuleTuNguon());

                    cell = row.createCell(20);
                    cell.setCellValue(item.getDongTieuThuTuNguon());

                    cell = row.createCell(21);
                    cell.setCellValue(item.getNgayHDTuNguon2());

                    cell = row.createCell(22);
                    cell.setCellValue(item.getLoaiTuNguon2());

                    cell = row.createCell(23);
                    if (item.getDongCungCapTuNguon2() == null || item.getDongCungCapTuNguon2().isEmpty()) {
                        cell.setCellValue("");
                    } else {
                        try {
                            cell.setCellValue(item.getDongCungCapTuNguon2() == null || item.getDongCungCapTuNguon2().isEmpty() ? "" : f.format(Double.parseDouble(item.getDongCungCapTuNguon2())));
                        } catch (NumberFormatException e) {
                            LOGGER.error(e.getMessage(), e);
                            cell.setCellValue("");
                        }
                    }

                    cell = row.createCell(24);
                    cell.setCellValue(item.getSoModuleTuNguon2());

                    cell = row.createCell(25);
                    cell.setCellValue(item.getDongTieuThuTuNguon2());

                    cell = row.createCell(26);
                    cell.setCellValue(item.getNgayHDMayNo());

                    cell = row.createCell(27);
                    cell.setCellValue(item.getLoaiHinhMayNo());

                    cell = row.createCell(28);
                    cell.setCellValue(item.getLoatMayNo());

                    cell = row.createCell(29);
                    cell.setCellValue(item.getCongSuatMayNo() == null || item.getCongSuatMayNo().isEmpty() ? "" : f.format(Double.parseDouble(item.getCongSuatMayNo())));

                    cell = row.createCell(30);
                    cell.setCellValue(item.getTrangThaiMayNo());

                    cell = row.createCell(31);
                    cell.setCellValue(item.getNgayHDAccu());

                    cell = row.createCell(32);
                    cell.setCellValue(item.getLoaiAcQuy());
                    cell = row.createCell(33);
                    cell.setCellValue(item.getDungLuongAccu());
                    cell = row.createCell(34);
                    cell.setCellValue(item.getDienApAccu());

                    cell = row.createCell(35);
                    cell.setCellValue(item.getSlAccuBinh());
                    cell = row.createCell(36);
                    cell.setCellValue(item.getThoigianHDSauMatDien());
                    cell = row.createCell(37);
                    cell.setCellValue(item.getNgayBaoDuongAccu());
                    cell = row.createCell(38);
                    cell.setCellValue(item.getNgayHDAccu2());
                    cell = row.createCell(39);
                    cell.setCellValue(item.getLoaiAcQuy2());
                    cell = row.createCell(40);
                    cell.setCellValue(item.getDungLuongAccu2());
                    cell = row.createCell(41);
                    cell.setCellValue(item.getDienApAccu2());
                    cell = row.createCell(42);
                    cell.setCellValue(item.getSlAccuBinh2());
                    cell = row.createCell(43);
                    cell.setCellValue(item.getThoigianHDSauMatDien2());
                    cell = row.createCell(44);
                    cell.setCellValue(item.getNgayBaoDuongAccu2());
                    cell = row.createCell(45);
                    cell.setCellValue(item.getLoaiTruyenDan());
                    cell = row.createCell(46);
                    cell.setCellValue(item.getGiaoDienTruyenDan());
                    cell = row.createCell(47);
                    cell.setCellValue(item.getDungLuongTruyenDan());
                    cell = row.createCell(48);
                    cell.setCellValue(item.getDienTroTiepDia());
                    cell = row.createCell(49);
                    cell.setCellValue(item.getSlDieuHoa());
                    cell = row.createCell(50);
                    cell.setCellValue(item.getTongCSDieuHoa());

                }
            }
            File file = new File(folderTemp + File.separator + "Result_Reg_CSHT_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            workbook.close();
            return file;

        } catch (IOException e) {

        }
        return null;
    }

    public File writeUpdateCsht(File fileTemplate, String folderTemp, List<?> temp) {
        try {
            XSSFWorkbook workbook;
            try (FileInputStream fin = new FileInputStream(fileTemplate)) {
                workbook = new XSSFWorkbook(fin);
                Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
                Iterator<UpdateCSHTExcel> iterator = (Iterator<UpdateCSHTExcel>) temp.iterator();
                Cell cell;
                Row row;
                int rowIndex = 3;
                //header
                while (iterator.hasNext()) {
                    UpdateCSHTExcel item = iterator.next();
//                    if (item.getName() == null || item.getName().isEmpty()) {
//                        continue;
//                    }
                    row = sheet.createRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    cell = row.createCell(0);
                    cell.setCellValue(item.getNote());

                    cell = row.createCell(1);
                    cell.setCellValue(item.getCode());

                    cell = row.createCell(2);
                    cell.setCellValue(item.getName());
//                  

                    cell = row.createCell(3);
                    cell.setCellValue(item.getNgayHdCsht());
                    cell = row.createCell(4);
                    cell.setCellValue(item.getDonViQl());

                    cell = row.createCell(5);
                    cell.setCellValue(item.getQuanHuyen());

                    cell = row.createCell(6);
                    cell.setCellValue(item.getXaPhuong());

                    cell = row.createCell(7);
                    cell.setCellValue(item.getDiaChi());
                    cell = row.createCell(8);
                    DecimalFormat f = new DecimalFormat("0.000000");
                    if (item.getLat() != null && StringUtils.isNumeric(item.getLat())) {
                        try {
                            cell.setCellValue(f.format(Double.parseDouble(item.getLat())));
                        } catch (NumberFormatException e) {
                            cell.setCellValue("");
                            LOGGER.error(e.getMessage(), e);
                        }
                    } else {
                        cell.setCellValue("");
                    }

                    cell = row.createCell(9);
                    if (item.getLon() != null && StringUtils.isNumeric(item.getLon())) {
                        try {
                            cell.setCellValue(f.format(Double.parseDouble(item.getLon())));
                        } catch (NumberFormatException e) {
                            cell.setCellValue("");
                            LOGGER.error(e.getMessage(), e);
                        }
                    } else {
                        cell.setCellValue("");
                    }

                    cell = row.createCell(10);
                    cell.setCellValue(item.getChungCsht());

                    cell = row.createCell(11);
                    cell.setCellValue(item.getLoaiCSHT());

                    cell = row.createCell(12);
                    cell.setCellValue(item.getLoaiTramCsht());

                    cell = row.createCell(13);
                    cell.setCellValue(item.getDoCaoAnTen());

                    cell = row.createCell(14);
                    cell.setCellValue(item.getDoCaoNhaDatAnTen());

                    cell = row.createCell(15);
                    cell.setCellValue(item.getLoaiCotAnTen());

                    cell = row.createCell(16);
                    cell.setCellValue(item.getNgayHDTuNguon());

                    cell = row.createCell(17);
                    cell.setCellValue(item.getLoaiTuNguon());

                    cell = row.createCell(18);
                    f = new DecimalFormat("0.0");
                    if (item.getDongCungCapTuNguon() == null || item.getDongCungCapTuNguon().isEmpty()) {
                        cell.setCellValue("");
                    } else if (!StationPlansImportController.isNumeric(item.getDongCungCapTuNguon())) {
                        cell.setCellValue(item.getDongCungCapTuNguon());
                    } else {
                        try {
                            cell.setCellValue(item.getDongCungCapTuNguon().equals("0") ? "0" : f.format(Double.parseDouble(item.getDongCungCapTuNguon())));
                        } catch (NumberFormatException e) {
                            LOGGER.error(e.getMessage(), e);
                            cell.setCellValue("");
                        }
                    }
                    cell = row.createCell(19);
                    cell.setCellValue(item.getSoModuleTuNguon());

                    cell = row.createCell(20);
                    cell.setCellValue(item.getDongTieuThuTuNguon());

                    cell = row.createCell(21);
                    cell.setCellValue(item.getNgayHDTuNguon2());

                    cell = row.createCell(22);
                    cell.setCellValue(item.getLoaiTuNguon2());

                    cell = row.createCell(23);
                    if (item.getDongCungCapTuNguon2() == null || item.getDongCungCapTuNguon2().isEmpty()) {
                        cell.setCellValue("");
                    } else if (!StationPlansImportController.isNumeric(item.getDongCungCapTuNguon2())) {
                        cell.setCellValue(item.getDongCungCapTuNguon2());
                    } else {
                        try {
                            cell.setCellValue(item.getDongCungCapTuNguon2().equals("0") ? "0" : f.format(Double.parseDouble(item.getDongCungCapTuNguon2())));
                        } catch (NumberFormatException e) {
                            LOGGER.error(e.getMessage(), e);
                            cell.setCellValue("");
                        }
                    }
                    cell = row.createCell(24);
                    cell.setCellValue(item.getSoModuleTuNguon2());

                    cell = row.createCell(25);
                    cell.setCellValue(item.getDongTieuThuTuNguon2());

                    cell = row.createCell(26);
                    cell.setCellValue(item.getNgayHDMayNo());

                    cell = row.createCell(27);
                    cell.setCellValue(item.getLoaiHinhMayNo());

                    cell = row.createCell(28);
                    cell.setCellValue(item.getLoatMayNo());

                    cell = row.createCell(29);
                    if (item.getCongSuatMayNo() == null || item.getCongSuatMayNo().isEmpty()) {
                        cell.setCellValue("");
                    } else if (!StationPlansImportController.isNumeric(item.getCongSuatMayNo())) {
                        cell.setCellValue(item.getCongSuatMayNo());
                    } else {
                        try {
                            cell.setCellValue(item.getCongSuatMayNo().equals("0") ? "0" : f.format(Double.parseDouble(item.getCongSuatMayNo())));
                        } catch (NumberFormatException e) {
                            LOGGER.error(e.getMessage(), e);
                            cell.setCellValue("");
                        }
                    }

                    cell = row.createCell(30);
                    cell.setCellValue(item.getTrangThaiMayNo());

                    cell = row.createCell(31);
                    cell.setCellValue(item.getNgayHDAccu());

                    cell = row.createCell(32);
                    cell.setCellValue(item.getLoaiAcQuy());
                    cell = row.createCell(33);
                    cell.setCellValue(item.getDungLuongAccu());
                    cell = row.createCell(34);
                    cell.setCellValue(item.getDienApAccu());

                    cell = row.createCell(35);
                    cell.setCellValue(item.getSlAccuBinh());
                    cell = row.createCell(36);
                    cell.setCellValue(item.getThoigianHDSauMatDien());
                    cell = row.createCell(37);
                    cell.setCellValue(item.getNgayBaoDuongAccu());
                    cell = row.createCell(38);
                    cell.setCellValue(item.getNgayHDAccu2());
                    cell = row.createCell(39);
                    cell.setCellValue(item.getLoaiAcQuy2());
                    cell = row.createCell(40);
                    cell.setCellValue(item.getDungLuongAccu2());
                    cell = row.createCell(41);
                    cell.setCellValue(item.getDienApAccu2());
                    cell = row.createCell(42);
                    cell.setCellValue(item.getSlAccuBinh2());
                    cell = row.createCell(43);
                    cell.setCellValue(item.getThoigianHDSauMatDien2());
                    cell = row.createCell(44);
                    cell.setCellValue(item.getNgayBaoDuongAccu2());
                    cell = row.createCell(45);
                    cell.setCellValue(item.getLoaiTruyenDan());
                    cell = row.createCell(46);
                    cell.setCellValue(item.getGiaoDienTruyenDan());
                    cell = row.createCell(47);
                    cell.setCellValue(item.getDungLuongTruyenDan());
                    cell = row.createCell(48);
                    cell.setCellValue(item.getDienTroTiepDia());
                    cell = row.createCell(49);
                    cell.setCellValue(item.getSlDieuHoa());
                    cell = row.createCell(50);
                    cell.setCellValue(item.getTongCSDieuHoa());

                }
            }
            File file = new File(folderTemp + File.separator + "Result_Update_CSHT_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            workbook.close();
            return file;

        } catch (IOException e) {

        }
        return null;
    }

    public File writeBcTemplate(File fileTemplate, List<BuildingExportBO> temp) {
        try {

            Workbook workbook;
            try (FileInputStream fin = new FileInputStream(fileTemplate)) {
                workbook = new XSSFWorkbook(fin);
                Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
                writeCSHT((List<BuildingExportBO>) temp, sheet);
            }
            File file = new File(StringUtils.getFolderTemp() + File.separator + "CSHT_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            workbook.close();
            return file;

        } catch (IOException e) {

        }
        return null;
    }

    private void writeCSHT(List<BuildingExportBO> temp, Sheet sheet) {

        try {
            Iterator<BuildingExportBO> iterator = temp.iterator();
            Cell cell;
            Row row;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                BuildingExportBO building = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(StringUtils.getValue(building.getMaBuilding()));

                cell = row.createCell(1);
                cell.setCellValue(StringUtils.getValue(building.getBuildingName()));

                cell = row.createCell(2);
                cell.setCellValue(StringUtils.getValue(building.getNgayHdCsht()));
                cell = row.createCell(3);
                cell.setCellValue(StringUtils.getValue(building.getTenDonViQL()));

                cell = row.createCell(4);
                cell.setCellValue(StringUtils.getValue(building.getTinh()));

                cell = row.createCell(5);
                cell.setCellValue(StringUtils.getValue(building.getQuan()));

                cell = row.createCell(6);
                cell.setCellValue(StringUtils.getValue(building.getNhomCSHT()));

                cell = row.createCell(7);
                cell.setCellValue(StringUtils.getValue(building.getXa()));

                cell = row.createCell(8);
                cell.setCellValue(StringUtils.getValue(building.getDiachi()));

                DecimalFormat f = new DecimalFormat("0.000000");
                cell = row.createCell(9);
                if (building.getLongitude() == null || building.getLongitude().isEmpty()) {
                    cell.setCellValue("");
                } else {
                    try {
                        cell.setCellValue(f.format(Double.parseDouble(building.getLongitude())));
                    } catch (NumberFormatException e) {
                        cell.setCellValue("");
                        LOGGER.error(e.getMessage(), e);
                    }
                }

                cell = row.createCell(10);
                if (building.getLatitude() == null || building.getLatitude().isEmpty()) {
                    cell.setCellValue("");
                } else {
                    try {
                        cell.setCellValue(f.format(Double.parseDouble(building.getLatitude())));
                    } catch (NumberFormatException e) {
                        cell.setCellValue("");
                        LOGGER.error(e.getMessage(), e);
                    }
                }
                cell = row.createCell(11);
                cell.setCellValue(StringUtils.getValue(building.getChungCSHT())); // chua lay duoc du lieu

                cell = row.createCell(12);
                cell.setCellValue(StringUtils.getValue(building.getLoaiCSHT()));

                cell = row.createCell(13);
                cell.setCellValue(StringUtils.getValue(building.getLoaiTramCSHT()));

                cell = row.createCell(14);
                cell.setCellValue(StringUtils.getValue(building.getDocaoAnTen()));

                cell = row.createCell(15);
                cell.setCellValue(StringUtils.getValue(building.getDoCaoNhaDatAnten()));

                cell = row.createCell(16);
                cell.setCellValue(StringUtils.getValue(building.getLoaiCotAnten()));

                cell = row.createCell(17);
                cell.setCellValue(StringUtils.getValue(building.getNgayHDTuNguon()));

                cell = row.createCell(18);
                cell.setCellValue(StringUtils.getValue(building.getLoaiTuNguonId()));

                f = new DecimalFormat("0.0");
                cell = row.createCell(19);
                cell.setCellValue(StringUtils.getValue(building.getDongCungCapTuNguon()).equals("") ? "" : (StringUtils.getValue(building.getDongCungCapTuNguon()).equals("0") ? "0" : f.format(Double.parseDouble(StringUtils.getValue(building.getDongCungCapTuNguon())))));

                cell = row.createCell(20);
                cell.setCellValue(StringUtils.getValue(building.getSoModuleTuNguon()));

                cell = row.createCell(21);
                cell.setCellValue(StringUtils.getValue(building.getDongTieuThuTuNguon()));

                //update tu nguon 2
                cell = row.createCell(22);
                cell.setCellValue(StringUtils.getValue(building.getNgayHDTuNguon2()));

                cell = row.createCell(23);
                cell.setCellValue(StringUtils.getValue(building.getLoaiTuNguonId2()));

                cell = row.createCell(24);
                cell.setCellValue(StringUtils.getValue(building.getDongCungCapTuNguon2()).equals("") ? "" : (StringUtils.getValue(building.getDongCungCapTuNguon2()).equals("0") ? "0" : f.format(Double.parseDouble(StringUtils.getValue(building.getDongCungCapTuNguon2())))));

                cell = row.createCell(25);
                cell.setCellValue(StringUtils.getValue(building.getSoModuleTuNguon2()));

                cell = row.createCell(26);
                cell.setCellValue(StringUtils.getValue(building.getDongTieuThuTuNguon2()));
                // end tu nguon 2
                cell = row.createCell(27);
                cell.setCellValue(StringUtils.getValue(building.getNgayHDMayNo()));

                cell = row.createCell(28);
                cell.setCellValue(StringUtils.getValue(building.getLoaiHinhMayNo()));

                cell = row.createCell(29);
                cell.setCellValue(StringUtils.getValue(building.getLoaiMayNoId()));

                cell = row.createCell(30);
                cell.setCellValue(StringUtils.getValue(building.getCongSuatMayNo()).equals("") ? "" : (StringUtils.getValue(building.getCongSuatMayNo()).equals("0") ? "0" : f.format(Double.parseDouble(StringUtils.getValue(building.getCongSuatMayNo())))));

                cell = row.createCell(31);
                cell.setCellValue(StringUtils.getValue(building.getMayNoCoDinhDiDong()));

                cell = row.createCell(32);
                cell.setCellValue(StringUtils.getValue(building.getNgayHDAccu()));

                cell = row.createCell(33);
                cell.setCellValue(StringUtils.getValue(building.getLoaiAcQuyId()));

                cell = row.createCell(34);
                cell.setCellValue(StringUtils.getValue(building.getDungLuongAcc()));

                cell = row.createCell(35);
                cell.setCellValue(StringUtils.getValue(building.getDienApBinh()));

                cell = row.createCell(36);
                cell.setCellValue(StringUtils.getValue(building.getSoLuongAccuBinh()));

                cell = row.createCell(37);
                cell.setCellValue(StringUtils.getValue(building.getThoiGianHdSauMatDien()));

                cell = row.createCell(38);
                cell.setCellValue(StringUtils.getValue(building.getNgayBaoDuongAccu()));

                //update accu 2
                cell = row.createCell(39);
                cell.setCellValue(StringUtils.getValue(building.getNgayHDAccu2()));

                cell = row.createCell(40);
                cell.setCellValue(StringUtils.getValue(building.getLoaiAcQuyId2()));

                cell = row.createCell(41);
                cell.setCellValue(StringUtils.getValue(building.getDungLuongAcc2()));

                cell = row.createCell(42);
                cell.setCellValue(StringUtils.getValue(building.getDienApBinh2()));

                cell = row.createCell(43);
                cell.setCellValue(StringUtils.getValue(building.getSoLuongAccuBinh2()));

                cell = row.createCell(44);
                cell.setCellValue(StringUtils.getValue(building.getThoiGianHdSauMatDien2()));

                cell = row.createCell(45);
                cell.setCellValue(StringUtils.getValue(building.getNgayBaoDuongAccu2()));
                //end accu2

                cell = row.createCell(46);
                cell.setCellValue(StringUtils.getValue(building.getLoaiTruyenDanId()));

                cell = row.createCell(47);
                cell.setCellValue(StringUtils.getValue(building.getGiaoDienTruyenDan()));

                cell = row.createCell(48);
                cell.setCellValue(StringUtils.getValue(building.getDuongLuongTruyenDan()));

                cell = row.createCell(49);
                cell.setCellValue(StringUtils.getValue(building.getDienTroTiepDia()));

                cell = row.createCell(50);
                cell.setCellValue(StringUtils.getValue(building.getSoLuongDieuHoa()));

                cell = row.createCell(51);
                cell.setCellValue(StringUtils.getValue(building.getTongCongSuatDieuHoa()));

            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/kiemdinh", method = RequestMethod.GET)
    public String updateInit(
            @ModelAttribute("cellNewForm") CellNewForm cellNewForm,
            ModelMap mm, HttpServletRequest request) {
        CellNewExcelBO cellNewExcelBO = new CellNewExcelBO();
        mm.put("cellNewExcelBO", cellNewExcelBO);
        return "csht/excel/kiemDinhExcel";
    }

    @RequestMapping(value = "/kiemdinh/update", method = RequestMethod.POST)
    public String updateKiemDinh(ModelMap mm, @ModelAttribute(value = "cellNewExcelBO") CellNewExcelBO cellNewExcelBO,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {

        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(cellNewExcelBO.getFile().getOriginalFilename());
            cellNewExcelBO.getFile().transferTo(convFile);

//            List<KiemDinhNetExcel> items = ExOM.mapFromExcel(convFile)
//                    .to(KiemDinhNetExcel.class)
//                    .map(2);
            List<KiemDinhNetExcel> items = ExOM.mapFromExcel(convFile) 
                    .to(KiemDinhNetExcel.class)
                    .mapSheet(0, 2);
            
            CellsFacade cellsFacade = new CellsFacade();
            List<String> result = new ArrayList<>();
            String temp;
            for (int i = 0; i < items.size(); i++) {
                temp = cellsFacade.updateKiemDinhNetExcel(items.get(i), user.getId());
                result.add(temp);
            }
            writeResult(convFile, result, "result_update_kiem_dinh_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx", response);

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
        return "redirect:/building/kiemdinh";
    }

    public File writeResult(File inputFile, List<String> temp, String name, HttpServletResponse response) {
        try {
            XSSFWorkbook workbook;
            try (FileInputStream fin = new FileInputStream(inputFile)) {
                workbook = new XSSFWorkbook(fin);
                Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
                Cell cell;
                Row row;
                int rowIndex = 2;
                for (int i = 0; i < temp.size(); i++) {

                    row = sheet.getRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);

                    cell = row.createCell(0);
                    cell.setCellValue(temp.get(i));
                }
            }
            File file = new File(name);
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
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }
            }
            return file;

        } catch (IOException e) {

        }
        return null;
    }
}
