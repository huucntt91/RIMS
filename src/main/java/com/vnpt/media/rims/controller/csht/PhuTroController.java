/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.csht;

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
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.CSHTFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.LoaiNhaTramFacade;
import com.vnpt.media.rims.facade.DmLoaiCotAntenFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ResponseBody;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/phutro")
public class PhuTroController {

    private static Logger logger = LogManager.getLogger(PhuTroController.class);
    private static final String LIST = "csht/phutro/list";
    private static final String FORM = "csht/phutro/form";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("tunguonList")
    public List findAllLoaiTuNguon() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTuNguon("");
    }

    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllKhuVuc(String.join(",", tinhManager));
    }

    @ModelAttribute("loaimaynoList")
    public List findAllLoaiMayNo() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllMayNo("");
    }

    @ModelAttribute("loaiacquyList")
    public List findAllLoaiAcQuy() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllAcQuy("");
    }

    @ModelAttribute("loaitruyendanList")
    public List findAllLoaiTruyenDan() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTruyenDan("");
    }

    @ModelAttribute("loaiantenList")
    public List findAllLoaiAnten() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllLoaiTram("");
    }
    @ModelAttribute("loaidieuhoaList")
    public List findAllLoaiDieuHoa() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllDieuHoa("");
    }
    @ModelAttribute("loaicotantenList")
    public List findAllLoaiCotAnten() {
        DmLoaiCotAntenFacade facade = new DmLoaiCotAntenFacade();
        return facade.fc_find_all("");
    }

    @ModelAttribute("loaiNhaTramLst")
    public List findLoaiNhaTram() {
        ArrayList<DMLoaiNhaTramBO> list = null;
        try {
            list = LoaiNhaTramFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, @RequestParam(value = "phuongXaId", required = false) String phuongXaId,
            ModelMap mm, HttpServletRequest request) {
        logger.info("Action init Phu Tro");

        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        quanHuyenId = quanHuyenId == null ? "" : quanHuyenId;
        phuongXaId = phuongXaId == null ? "" : phuongXaId;
        code = code == null ? "" : code;
        CSHTFacade facade = new CSHTFacade();
        
        int totalRows = facade.getTotalPhuTro("", khuvucId, tinhTpId, quanHuyenId, phuongXaId, code);

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
            return ("redirect:/phutro/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/phutro/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Phụ trợ");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }

        List<PhuTroBO> list = facade.findAllPhuTroBO(String.valueOf(startRow), String.valueOf(endRow), "", khuvucId, tinhTpId, quanHuyenId, phuongXaId, code);
        mm.put("tinhTpId", tinhTpId);
        mm.put("khuvucId", khuvucId);
        mm.put("quanHuyenId", quanHuyenId);
        mm.put("phuongXaId", phuongXaId);
        mm.put("code", code);
        mm.put("startRow", startRow);
        mm.put("list", list);
        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        PhuTroBO phuTroBO = new PhuTroBO();
        mm.addAttribute("phuTroBO", phuTroBO);
        return FORM;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        logger.debug("view Building");
        try {
            if (StringUtils.hasText(id)) {
                CSHTFacade facade = new CSHTFacade();
                List<PhuTroBO> list = facade.findAllPhuTroBO("", "", id, null, "", "", "", "");
                mm.addAttribute("model", list.get(0));
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return FORM;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        logger.debug("view phu tro");
        try {
            if (StringUtils.hasText(id)) {
                CSHTFacade facade = new CSHTFacade();
                List<PhuTroBO> list = facade.findAllPhuTroBO("", "", id, null, "", "", "", "");
                mm.addAttribute("model", list.get(0));
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return "csht/phutro/detail";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(HttpServletRequest request, ModelMap mm, @Valid @ModelAttribute(value = "model") PhuTroBO model, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
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
                return "redirect:/phutro/preAdd";
            }
            CategoriesFacade adminFacade = new CategoriesFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            model.setUserId(String.valueOf(user.getId()));
            if (adminFacade.modifyPhuTro("add", model) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/phutro/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                //mm.addAttribute("dvbo", cPbo);
                return "redirect:/phutro/preAdd";
            }

        } catch (Exception e) {

            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
//                if (message.indexOf("UK_CMSCP_NAME") != -1) {
//                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_CMSCP_NAME", null, locale);
//                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                    mm.addAttribute("dvbo", cPbo);
//                    return "redirect:/dv/preAdd";
//                }
            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/phutro/preAdd";
        }

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, ModelMap mm, @Valid @ModelAttribute(value = "model") PhuTroBO model, BindingResult bindingResult, RedirectAttributes attr) throws Exception {
        logger.info("update Building Action");
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
                return "redirect:/phutro/view/" + model.getId();
            }
            CategoriesFacade adminFacade = new CategoriesFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            model.setUserId(String.valueOf(user.getId()));
            if (adminFacade.modifyPhuTro("edit", model) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/phutro/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/phutro/view/" + model.getId();
            }

        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
//            if (StringUtils.hasText(message)) {
//                if (message.indexOf("UK_CMSCP_NAME") != -1) {
//                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_CMSCP_NAME", null, locale);
//                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                    mm.addAttribute("cpbo", cPbo);
//                    return "redirect:/cp/view/" + cPbo.getId();
//                }
//            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/phutro/view/" + model.getId();
        }

    }

    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, ModelMap mm, @PathVariable(value = "Id") String Id,
            Locale locale, RedirectAttributes attr) throws Exception {
        
        logger.debug("Delete Thiet Action");
        try {
            CategoriesFacade adminFacade = new CategoriesFacade();
            PhuTroBO model = new PhuTroBO();
            model.setId(Long.valueOf(Id));
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            model.setUserId(String.valueOf(user.getId()));
            if (adminFacade.modifyPhuTro("del", model) > -1) {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/phutro/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/phutro/init";
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            logger.error("Exception :", e);

            return "redirect:/phutro/init";
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
        String tinhs =  String.join(",", tinhManager);
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllDonVi("", "", tinhs);
    }

    @RequestMapping(value = "/getSupportBuilding/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getSupportBuilding(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        Map referenceData = new HashMap();
        CSHTFacade facade = new CSHTFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        if (facade.findSupportBuilding(id) != null) {
            return mapper.writeValueAsString(facade.findSupportBuilding(id));
        } else {
            return "";
        }
    }

}
