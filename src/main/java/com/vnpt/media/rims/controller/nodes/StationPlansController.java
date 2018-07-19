package com.vnpt.media.rims.controller.nodes;

import com.vnpt.media.rims.bean.TramDuAnBO;
import com.vnpt.media.rims.controller.managerAdmin.*;
import com.vnpt.media.rims.common.Constants;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DmCauHinhPortFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.StationPlansFacade;
import com.vnpt.media.rims.facade.TrangThaiCshtFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.FileCopyUtils;
import java.io.FileOutputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/stationPlans")
public class StationPlansController extends BaseController {

    private static Logger logger = LogManager.getLogger(StationPlansController.class);
    private static int maxDisplayPages = 8;
    private static String pagerStyle = "pagelinks";
    private static final String[] strPrefix = {"&#272;&#7847;u", "Tr&#432;&#7899;c", "Sau", "Cu&#7889;i", "Trang", "T&#7899;i trang "};
    private static final String separator = ",";
    private static final String LIST_PLANS = "stationPlans/stationPlans/stationPlansList";
    private static final String ADD_PLAN = "stationPlans/addStationPlans";
    private static final String EDIT_PLAN = "stationPlans/editStationPlans";
    private static final String EDIT_BIRD_INFO = "stationPlans/editBirdInfo";
    private static final String EDIT_COMMIT_DEVICE = "stationPlans/editCommitDevice";
    private static final String EDIT_INFRA = "stationPlans/editInfra";
    private static final String EDIT_DEPLOY_NETX = "stationPlans/editDeployNetx";
    private static final String EDIT_DEPLOY_QLHT = "stationPlans/editDeployQLHT";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
    }

    @ModelAttribute("dmOkNokList")
    public List findDmOkNok(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findDmOkNok();
    }

    @ModelAttribute("portList")
    public List findAllPortList() {
        DmCauHinhPortFacade facade = new DmCauHinhPortFacade();
        return facade.fc_find_all("");
    }

    @ModelAttribute("dmNguonThietBiList")
    public List findDmNguonThietBi(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findDmNguonThietBi();
    }

    @ModelAttribute("dmLoaiCongNgheList")
    public List findDmLoaiCongNghe(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findDmLoaiCongNghe();
    }

    @ModelAttribute("dmHienTrangTramList")
    public List findDmHienTrangTram(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findDmHienTrangTram();
    }

    @ModelAttribute("dmTrangThaiCshtList")
    public List findDmTrangThaiCsht(HttpServletRequest request) {
        TrangThaiCshtFacade facade = new TrangThaiCshtFacade();
        return facade.fc_find_all(null);
    }

    @ModelAttribute("dmDungLuongACCUList")
    public List findDmDungLuongACCU(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findDmDungLuongACCU();
    }
    
    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllKhuVuc(String.join(",", tinhManager));
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            Locale locale, ModelMap mm, HttpServletRequest request) {
        
        try{
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        khuvucId = khuvucId == null ? "" : khuvucId;

        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        TramDuAnBO tramDA = new TramDuAnBO();
        StationPlansFacade facade = new StationPlansFacade();
        int totalRows;
        try{
            totalRows = facade.getTotalRow(khuvucId,tinhTpId, null, null, null, null, null, null, null, 1L);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
        List<TramDuAnBO> lst = new ArrayList<TramDuAnBO>();
        try{
            lst = facade.findAllTramDA(String.valueOf(startRow), String.valueOf(endRow), khuvucId, tinhTpId, null, null, null, null, null, null, null, 1L);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } 
        mm.put("startRow", startRow);
        mm.put("list_tramDA", lst);
        mm.put("tramDABO", tramDA);
        mm.put("tinhTpId", tinhTpId);
        mm.put("khuvucId", khuvucId);
        // quyen nhom thuoc tinh tram du an
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "10");
        List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "10");
        mm.addAttribute("classAtrrEdit", classAtrrEdit);
        mm.addAttribute("classAtrrView", classAtrrView);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } 
        return LIST_PLANS;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam("maTramDuAn") String maTramDuAn,
            @RequestParam("tenTramDuAn") String tenTramDuAn,        
            //                         @RequestParam("fullname") String msHopDong, 
            //                         @RequestParam("fullname") String maTramBTS, 
            //                         @RequestParam("fullname") String maTramNodeB, 
            //                         @RequestParam("fullname") String maTramQuyHoach, 
            ModelMap mm,
            HttpServletRequest request) {
        try{
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        TramDuAnBO tramDA = new TramDuAnBO();
        maTramDuAn = ((maTramDuAn == null || maTramDuAn.equals("")) ? "" : maTramDuAn);
        tenTramDuAn = ((tenTramDuAn == null || tenTramDuAn.equals("")) ? "" : tenTramDuAn);
        StationPlansFacade facade = new StationPlansFacade();
        int totalRows;
        try{
            totalRows = facade.getTotalRow(khuvucId,tinhTpId, null, maTramDuAn, tenTramDuAn, null, null, null, null, 1L);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
        logger.info("Action search tram du an");
        List<TramDuAnBO> lst = new ArrayList<TramDuAnBO>();
        try{
            lst = facade.findAllTramDA(String.valueOf(startRow), String.valueOf(endRow), khuvucId, tinhTpId, null, maTramDuAn, tenTramDuAn, null, null, null, null, 1L);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } 
        mm.put("startRow", startRow);
        mm.put("list_tramDA", lst);
        mm.put("tramDABO", tramDA);
        mm.put("maTramDuAn", maTramDuAn);
        mm.put("tenTramDuAn", tenTramDuAn);
        mm.put("tinhTpId", tinhTpId);
        mm.put("khuvucId", khuvucId);

        // quyen nhom thuoc tinh tram du an
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "10");
        List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "10");
        mm.addAttribute("classAtrrEdit", classAtrrEdit);
        mm.addAttribute("classAtrrView", classAtrrView);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } 
        return LIST_PLANS;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        TramDuAnBO tramDABO = new TramDuAnBO();
        mm.addAttribute("tramDA", tramDABO);
        return ADD_PLAN;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "tramDABO") TramDuAnBO tramDABO,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request){
        Locale locale = LocaleContextHolder.getLocale();

        try {

            StationPlansFacade nodeFacade = new StationPlansFacade();

            CategoriesFacade adminFacade = new CategoriesFacade();
            //check long lat co thuoc quan huyen hay tinh do khong
            if (!adminFacade.checkLongLat(tramDABO.getLongitude(), tramDABO.getLatitude(), tramDABO.getQuanHuyenId().toString())) {
                String msg = messageSource.getMessage("buildling.validate.long.lat", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/stationPlans/preAdd";
            }
            nodeFacade.addtramDA(tramDABO);

            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            genMessageSystemError(attr, messageSource, locale);
            return "redirect:/stationPlans/preAdd";
        }
        TramDuAnBO tramDuAnBO = new TramDuAnBO();
        mm.addAttribute("tramDABO", tramDuAnBO);
        String msg = messageSource.getMessage("admin.common.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
        return "redirect:/stationPlans/init";

    }

    @RequestMapping(value = "/popupDuAn", method = RequestMethod.GET)
    public String popupDuAn(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            ModelMap mm, HttpServletRequest request) {
        logger.info("Action init Popup Tram Du An");

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;

        code = code == null ? "" : code;

        StationPlansFacade facade = new StationPlansFacade();
        int totalRows;
        try{
            totalRows = facade.getTotalAllDuAn(page, tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
            return ("redirect:/popupDuAn/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/popupDuAn/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý Tram Du An");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<TramDuAnBO> list = new ArrayList<TramDuAnBO>();
        try{
            list = facade.findAllDuAn(String.valueOf(startRow), String.valueOf(endRow), code, tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        mm.put("list", list);
        mm.put("tinhTpId", tinhTpId.contains(",") ? "" : tinhTpId);

        mm.put("startRow", startRow);
        return "stationPlans/popupDuAn";
    }

    @RequestMapping(value = "/popupTramKH", method = RequestMethod.GET)
    public String popup(@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            ModelMap mm, HttpServletRequest request) {
        logger.info("Action init Popup Tram ke hoach");

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        code = code == null ? "" : code;
        StationPlansFacade facade = new StationPlansFacade();
        int totalRows;
        try{
            totalRows = facade.getTotalTramKH(code, tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
            return ("redirect:/popupTramKH/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/popupTramKH/init?page=" + pageInt);
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
        List<TramDuAnBO> list = new ArrayList<TramDuAnBO>();
        try{
        list = facade.findAllTramKH(String.valueOf(startRow), String.valueOf(endRow), code, tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        mm.put("list", list);
        mm.put("tinhTpId", tinhTpId.contains(",") ? "" : tinhTpId);
        mm.put("startRow", startRow);
        return "stationPlans/popupTramKH";
    }

    @RequestMapping(value = "/preUpdate/{id}/{status}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") String id,
            @PathVariable(value = "status") String status,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "10");
        List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "10");
        for (int i = 0; i < classAtrrEdit.size(); i++) {
            

        }
        mm.addAttribute("classAtrrEdit", classAtrrEdit);
        mm.addAttribute("classAtrrView", classAtrrView);
        StationPlansFacade nodesFacade = new StationPlansFacade();

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        List<TramDuAnBO> list = new ArrayList<TramDuAnBO>();
        try{
            list = nodesFacade.findAllTramDA(null, null, khuvucId, tinhTpId, id, null, null, null, null, null, null, Long.parseLong(status));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        mm.put("tramDA", list.get(0));
        mm.put("trangThaiTram", status);
        //edit lai 
        if (Long.parseLong(status) == 1L) {
            return EDIT_BIRD_INFO;
        } else if (Long.parseLong(status) == 2L) {
            return EDIT_COMMIT_DEVICE;
        } else if (Long.parseLong(status) == 3L) {
            return EDIT_INFRA;
        } else if (Long.parseLong(status) == 4L) {
            return EDIT_DEPLOY_NETX;
        } else if (Long.parseLong(status) == 5L) {
            return EDIT_DEPLOY_QLHT;
        } else {
            return EDIT_PLAN;
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        logger.debug("Delete Thiet Action");
        try {
            StationPlansFacade adminFacade = new StationPlansFacade();
//            TramDuAnBO model = new TramDuAnBO();
//            model.setId(Long.parseLong(id));
//            model.setUserId(user.getId());

            if (adminFacade.deleteTramDA(id, user.getId()) > -1) {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/stationPlans/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/stationPlans/init";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));


            return "redirect:/stationPlans/init";
        }

    }

    @RequestMapping(value = "preUpdate/{id}/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "tramDABO") TramDuAnBO tramDABO,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        try {
            

            StationPlansFacade facade = new StationPlansFacade();

            tramDABO.setUserId(user.getId());

            if (tramDABO.getTrangThaiTram().equals(1L)) {

                CategoriesFacade adminFacade = new CategoriesFacade();
                //check long lat co thuoc quan huyen hay tinh do khong
                if (!adminFacade.checkLongLat(tramDABO.getLongitude(), tramDABO.getLatitude(), tramDABO.getQuanHuyenId().toString())) {
                    String msg = messageSource.getMessage("buildling.validate.long.lat", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/stationPlans/preUpdate/" + tramDABO.getId() + "/" + tramDABO.getTrangThaiTram();
                }

                if (facade.updateThongTinChungTramDA(tramDABO) > 0) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                    return "redirect:/stationPlans/init";
                } else {
                    String msg = messageSource.getMessage("admin.common.error", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/stationPlans/preUpdate/" + tramDABO.getId() + "/" + tramDABO.getTrangThaiTram();
                }
            } else if (tramDABO.getTrangThaiTram().equals(2L)) {
                if (facade.updateCCTBTramDA(tramDABO) > 0) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                    return "redirect:/stationPlans/init";
                } else {
                    String msg = messageSource.getMessage("admin.common.error", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/stationPlans/preUpdate/" + tramDABO.getId() + "/" + tramDABO.getTrangThaiTram();
                }
            } else if (tramDABO.getTrangThaiTram().equals(3L)) {
                if (facade.updateCCHTTramDA(tramDABO) > 0) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                    return "redirect:/stationPlans/init";
                } else {
                    String msg = messageSource.getMessage("admin.common.error", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/stationPlans/preUpdate/" + tramDABO.getId() + "/" + tramDABO.getTrangThaiTram();
                }
            } else if (tramDABO.getTrangThaiTram().equals(4L)) {
                if (facade.updateDeployNetXTramDA(tramDABO) > 0) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                    return "redirect:/stationPlans/init";
                } else {
                    String msg = messageSource.getMessage("admin.common.error", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/stationPlans/preUpdate/" + tramDABO.getId() + "/" + tramDABO.getTrangThaiTram();
                }
            } else if (tramDABO.getTrangThaiTram().equals(5L)) {
                if (facade.updateDeployQLHTTramDA(tramDABO) > 0) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                    return "redirect:/stationPlans/init";
                } else {
                    String msg = messageSource.getMessage("admin.common.error", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/stationPlans/preUpdate/" + tramDABO.getId() + "/" + tramDABO.getTrangThaiTram();
                }
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/stationPlans/preUpdate/" + tramDABO.getId() + "/" + tramDABO.getTrangThaiTram();
            }

//            if (facade.updateTramDA("edit", tramDABO) > 0) {
//                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//                return "redirect:/stationPlans/init";
//            } else {
//                String msg = messageSource.getMessage("admin.common.error", null, locale);
//                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                return "redirect:/stationPlans/preUpdate/" + tramDABO.getId() + "/" + tramDABO.getTrangThaiTramAction();
//            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("tramDABO", tramDABO);
            return "redirect:/stationPlans/preUpdate/" + tramDABO.getId() + "/" + tramDABO.getTrangThaiTramAction();
        }

    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public String approve(ModelMap mm, @Valid @ModelAttribute(value = "tramDABO") TramDuAnBO tramDABO,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        StationPlansFacade tramDAFacade = new StationPlansFacade();
        tramDABO.setUserId(user.getId());

        if (tramDAFacade.approve(tramDABO) > 0) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_DELETE_SUCCESS));
            return "redirect:/stationPlans/init";
        } else {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/stationPlans/init";
        }

//        tramDAFacade.approve(tramDABO);
//        return "redirect:/stationPlans/init";
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam("maTramDuAn") String maTramDuAn,
            @RequestParam("tenTramDuAn") String tenTramDuAn) {
        List<TramDuAnBO> data = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId.replace("null", "");
            khuvucId = khuvucId == null ? "" : khuvucId.replace("null", "");
            maTramDuAn = ((maTramDuAn == null || maTramDuAn.equals("")) ? "" : maTramDuAn);
            tenTramDuAn = ((tenTramDuAn == null || tenTramDuAn.equals("")) ? "" : tenTramDuAn);

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            data = StationPlansFacade.eportExelsTramDuAn(khuvucId, tinhTpId, maTramDuAn, tenTramDuAn);

            File fileResult = writeExportExcel(dataDirectory + "/export_tram_da.xlsx", data);
//            File fileResult = writeExportExcel(dataDirectory + "/export_qh.xlsx", data);
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

        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (data != null) {
                data.clear();
            }
        }
    }

    private File writeExportExcel(String fileTemplate, List<TramDuAnBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "KH" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 1;
                Cell cell = null;
                Row row = null;
                for (TramDuAnBO it : datas) {
                    row = sheet.createRow(rowIndex++);
                    cell = row.createCell(0);
                    cell.setCellValue(it.getMaDuAn()== null ? "" : it.getMaDuAn());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getTenDuAn()== null ? "" : it.getTenDuAn());

                    cell = row.createCell(2);
                    cell.setCellValue(it.getMaQuyHoach()== null ? "" : it.getMaQuyHoach());

                    cell = row.createCell(3);
                    cell.setCellValue(it.getTenQuyHoach()== null ? "" : it.getTenQuyHoach());

                    cell = row.createCell(4);
                    cell.setCellValue(it.getMaSoHopDong()== null ? "" : it.getMaSoHopDong());

                    cell = row.createCell(5);
                    cell.setCellValue(it.getTenTinhTp()== null ? "" : it.getTenTinhTp());

                    cell = row.createCell(6);
                    cell.setCellValue(it.getTenQuanHuyen()== null ? "" : it.getTenQuanHuyen());

                    cell = row.createCell(7);
                    cell.setCellValue(it.getAddress()== null ? "" : it.getAddress());

                    cell = row.createCell(8);
                    cell.setCellValue(it.getMaTramDuAn()== null ? "" : it.getMaTramDuAn());

                    cell = row.createCell(9);
                    cell.setCellValue(it.getTenTramDuAn()== null ? "" : it.getTenTramDuAn());

                    cell = row.createCell(10);
                    cell.setCellValue(it.getMaTramBTS()== null ? "" : it.getMaTramBTS());

                    cell = row.createCell(11);
                    cell.setCellValue(it.getMaTramNodeB()== null ? "" : it.getMaTramNodeB());

                    cell = row.createCell(12);
                    cell.setCellValue(it.getMaTramQuyHoach()== null ? "" : it.getMaTramQuyHoach());

                    cell = row.createCell(13);
                    cell.setCellValue(it.getHienTrangTramName()== null ? "" : it.getHienTrangTramName());

                    cell = row.createCell(14);
                    cell.setCellValue(it.getLongitude()== null ? "" : it.getLongitude());

                    cell = row.createCell(15);
                    cell.setCellValue(it.getLatitude()== null ? "" : it.getLatitude());

                    cell = row.createCell(16);
                    if (it.getVnptNetPheDuyet() != null && it.getVnptNetPheDuyet() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getVnptNetPheDuyet() != null && it.getVnptNetPheDuyet() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }

                    cell = row.createCell(17);
                    cell.setCellValue(it.getCauHinhThietBiName()== null ? "" : it.getCauHinhThietBiName());

                    cell = row.createCell(18);
                    cell.setCellValue(it.getNguongThietBiName()== null ? "" : it.getNguongThietBiName());

                    cell = row.createCell(19);
                    cell.setCellValue(it.getLoaiCongNgheName()== null ? "" : it.getLoaiCongNgheName());

                    cell = row.createCell(20);
                    cell.setCellValue(it.getChungLoaiThietBi()== null ? "" : it.getChungLoaiThietBi());

                    cell = row.createCell(21);
                    cell.setCellValue(it.getChungLoaiAnten()== null ? "" : it.getChungLoaiAnten());

                    cell = row.createCell(22);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayCungCapTb(), "dd/MM/yyyy"));

                    cell = row.createCell(23);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgaySwapTb(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(24);
                    cell.setCellValue(it.getLongitudeKhaoSat()== null ? "" : it.getLongitudeKhaoSat());
                    
                    cell = row.createCell(25);
                    cell.setCellValue(it.getLatitudeKhaoSat()== null ? "" : it.getLatitudeKhaoSat());
                    
                    cell = row.createCell(26);
                    if (it.getNhaTram() != null && it.getNhaTram() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getNhaTram() != null && it.getNhaTram() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(27);
                    if (it.getCotAnten() != null && it.getCotAnten() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getCotAnten() != null && it.getCotAnten() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(28);
                    if (it.getCauCapNgoai() != null && it.getCauCapNgoai() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getCauCapNgoai() != null && it.getCauCapNgoai() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(29);
                    if (it.getTuNguon() != null && it.getTuNguon() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getTuNguon() != null && it.getTuNguon() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(30);
                    cell.setCellValue(it.getDungLuongTuNguon()== null ? "" : it.getDungLuongTuNguon());
                    
                    cell = row.createCell(31);
                    cell.setCellValue(it.getSoModuleTuNguon()== null ? "" : it.getSoModuleTuNguon());
                    
                    cell = row.createCell(32);
                    cell.setCellValue(it.getChungLoaiAccuName()== null ? "" : it.getChungLoaiAccuName());
                    
                    cell = row.createCell(33);
                    cell.setCellValue(it.getDungLuongAccu()== null ? "" : it.getDungLuongAccu());
                    
                    cell = row.createCell(34);
                    cell.setCellValue(it.getSoLuongToAccu()== null ? "" : it.getSoLuongToAccu());
                    
                    cell = row.createCell(35);
                    if (it.getTruyenDan() != null && it.getTruyenDan() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getTruyenDan() != null && it.getTruyenDan() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(36);
                    if (it.getDieuHoa() != null && it.getDieuHoa() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getDieuHoa() != null && it.getDieuHoa() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(37);
                    if (it.getDienAc() != null && it.getDienAc() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getDienAc() != null && it.getDienAc() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(38);
                    if (it.getDienAcNoiTram() != null && it.getDienAcNoiTram() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getDienAcNoiTram() != null && it.getDienAcNoiTram() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(39);
                    if (it.getDuDkLapEnodeb() != null && it.getDuDkLapEnodeb() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getDuDkLapEnodeb() != null && it.getDuDkLapEnodeb() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(40);
                    if (it.getCapMoiTuNguonDc() != null && it.getCapMoiTuNguonDc() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getCapMoiTuNguonDc() != null && it.getCapMoiTuNguonDc() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(41);
                    if (it.getCapMoiAccu() != null && it.getCapMoiAccu() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getCapMoiAccu() != null && it.getCapMoiAccu() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    
                    cell = row.createCell(42);
                    cell.setCellValue(it.getSwapAnten()== null ? "" : it.getSwapAnten());
                    
                    cell = row.createCell(43);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayHoanThanhKs(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(44);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayGuiSoLieu(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(45);
                    cell.setCellValue(it.getDauMoiNhanThietBi()== null ? "" : it.getDauMoiNhanThietBi());
                    
                    cell = row.createCell(46);
                    cell.setCellValue(it.getDauMoiQLCSHT()== null ? "" : it.getDauMoiQLCSHT());
                    
                    cell = row.createCell(47);
                    cell.setCellValue(it.getDonViLapDat()== null ? "" : it.getDonViLapDat());
                    
                    cell = row.createCell(48);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayHTLapDatAnten(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(49);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayPheDuyetKhaoSat(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(50);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayTiepNhanTruyenDan(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(51);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getKeHoachXuatThietBi(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(52);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayXuatThietBiThucTe(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(53);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayTiepNhanTb(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(54);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getKeHoachTbDenSite(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(55);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getKeHoachLapDat(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(56);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayBatDauLapDat(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(57);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayHTLapDatTb(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(58);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getKeHoachHoaMang(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(59);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayHoaMangThucTe(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(60);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getKeHoachPhatSongCt(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(61);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayPhatSongCt(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(62);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getKeHoachNghiemThu(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(63);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayNghiemThu(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(64);
                    cell.setCellValue(it.getDauMoiVnptNet()== null ? "" : it.getDauMoiVnptNet());
                    
                    cell = row.createCell(65);
                    cell.setCellValue(it.getDonViVanChuyen()== null ? "" : it.getDonViVanChuyen());
                    
                    cell = row.createCell(66);
                    cell.setCellValue(it.getGhiChu()== null ? "" : it.getGhiChu());
                    
                    cell = row.createCell(67);
                    cell.setCellValue(it.getTenTrangThaiTram()== null ? "" : it.getTenTrangThaiTram());
                    
                    cell = row.createCell(68);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayXuatAntenThucTe(), "dd/MM/yyyy"));

                }
            }

            fos = new FileOutputStream(result);
            workbook.write(fos);

        } catch (IOException e) {
            e.printStackTrace();
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
