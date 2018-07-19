/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.project;

import com.google.gson.Gson;
import com.vnpt.media.rims.bean.DMBangTanBO;
import com.vnpt.media.rims.bean.DMCachXayCSHTBO;
import com.vnpt.media.rims.bean.DMCauHinhPortBO;
import com.vnpt.media.rims.bean.DMDviPheDuyetBO;
import com.vnpt.media.rims.bean.DMGiaoDienTdBO;
import com.vnpt.media.rims.bean.DMLoaiCongNgheBO;
import com.vnpt.media.rims.bean.DMLoaiDatBO;
import com.vnpt.media.rims.bean.DMLoaiNhaTramBO;
import com.vnpt.media.rims.bean.DMLoaiPtCshtBO;
import com.vnpt.media.rims.bean.DMNguonThietBiBO;
import com.vnpt.media.rims.bean.LoaiTuNguonBO;
import com.vnpt.media.rims.bean.DMTrangThaiCshtBO;
import com.vnpt.media.rims.bean.DmLoaiCotAntenBO;
import com.vnpt.media.rims.bean.LoaiAnTenBO;
import com.vnpt.media.rims.bean.LoaiTruyenDanBO;
import com.vnpt.media.rims.bean.ProjectBO;
import com.vnpt.media.rims.bean.ProjectStationBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.ContentDataTableItem;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IProject;
import com.vnpt.media.rims.dao.IProjectStation;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.BangTanFacade;
import com.vnpt.media.rims.facade.CachXayCSHTFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DmCauHinhPortFacade;
import com.vnpt.media.rims.facade.DmLoaiCotAntenFacade;
import com.vnpt.media.rims.facade.DviPheDuyetFacade;
import com.vnpt.media.rims.facade.GiaoDienTdFacade;
import com.vnpt.media.rims.facade.LoaiCongNgheFacade;
import com.vnpt.media.rims.facade.LoaiDatFacade;
import com.vnpt.media.rims.facade.LoaiNhaTramFacade;
import com.vnpt.media.rims.facade.LoaiPtCshtFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.NguonTbiFacade;
import com.vnpt.media.rims.facade.TramQHFacade;
import com.vnpt.media.rims.facade.TrangThaiCshtFacade;
import com.vnpt.media.rims.formbean.SearchTramQhForm;
import com.vnpt.media.rims.transaction.ITransaction;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/project_station")
public class ProjectStationController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectStationController.class);
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request, RedirectAttributes attr, @Valid @ModelAttribute(value = "searchTramQhForm") SearchTramQhForm searchTramQhForm) {
        try {
            //phan quyen theo nhom thuoc tinh
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);

        } catch (ServiceException e) {
//            logger.error(e.getMessage(), e);
            logger.error(e.getMessage(), e);
        }
        attr.addFlashAttribute("tab", "0");
        return "project/project_station/index";
    }

//    bang tan
    @RequestMapping(value = "/getBangTan/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getBangTan(@PathVariable(value = "id") Integer loaiCongNgheId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        ArrayList<DMBangTanBO> list = null;
        try {
            list = BangTanFacade.findBangTan(null, loaiCongNgheId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //
    @ModelAttribute("bangTanLst")
    public List findBangTanLst() {
        ArrayList<DMBangTanBO> list = null;
        try {
            list = BangTanFacade.fc_find_all(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("loaiPtCshtLst")
    public List findloaiPtCshtLst() {
        ArrayList<DMLoaiPtCshtBO> list = null;
        try {
            list = LoaiPtCshtFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("loaiCotLst")
    public ArrayList findLoaiCotLst() {
        ArrayList<DmLoaiCotAntenBO> list = null;
        try {
            list = DmLoaiCotAntenFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("cauHinhPortLst")
    public ArrayList findCauHinhPortLst() {
        ArrayList<DMCauHinhPortBO> list = null;
        try {
            list = DmCauHinhPortFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("trangThaiCshtLst")
    public ArrayList findtrangThaiCshtLst() {
        ArrayList<DMTrangThaiCshtBO> list = null;
        try {
            list = list = TrangThaiCshtFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("donViLst")
    public List findDonViPheDuyetLst() {
        ArrayList<DMDviPheDuyetBO> list = null;
        try {
            list = DviPheDuyetFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("loaiAntenaLst")
    public List findloaiAntenaLst() {
        List<LoaiAnTenBO> loaiAntenaLst = null;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            loaiAntenaLst = facade.findAllAnTen("");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return loaiAntenaLst;
    }

    @ModelAttribute("loaiCongNgheLst")
    public List findTechType() {
        ArrayList<DMLoaiCongNgheBO> list = null;
        try {
            list = LoaiCongNgheFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("nguonThietBiLst")
    public List findNguonThietBi() {
        ArrayList<DMNguonThietBiBO> list = null;
        try {
            list = NguonTbiFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("tinhThanhPhoLst")
    public List findTinhThanhPho(HttpServletRequest request) {
        try {
            CategoriesFacade facade = new CategoriesFacade();
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            return facade.findAllTinh(String.join(",", tinhManager));
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        try {
            CategoriesFacade facade = new CategoriesFacade();
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            return facade.findAllKhuVuc(String.join(",", tinhManager));
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @ModelAttribute("cachThucXayDungLst")
    public List findCachThucXayDung() {
        ArrayList<DMCachXayCSHTBO> list = null;
        try {
            list = CachXayCSHTFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("loaiDatLst")
    public List findLoaiDat() {
        ArrayList<DMLoaiDatBO> list = null;
        try {
            list = LoaiDatFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
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
//    trunglk_start

    @ModelAttribute("loaiTuNguon")
    public List findLoaiTuNguon() {
        List<LoaiTuNguonBO> list = null;
        try {
//            List<LoaiTuNguonBO> list = CategoriesFacade.findAllTuNguon("");
            CategoriesFacade facade = new CategoriesFacade();
            list = facade.findAllTuNguon("");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }
//    trunglk_end

    @ModelAttribute("loaiTruyenDanLst")
    public List findLoaiTruyenDan() {
        List<LoaiTruyenDanBO> list = null;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            list = facade.findAllTruyenDan("");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @ModelAttribute("gdTruyenDanLst")
    public List findGdLoaiTruyenDan() {
        List<DMGiaoDienTdBO> list = null;
        try {
            list = GiaoDienTdFacade.fc_find_all("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    //edit or update project
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTramQh(ModelMap mm, @Valid @ModelAttribute(value = "tramQhBo") ProjectStationBO tramQh,
            RedirectAttributes attr, HttpServletRequest request) {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {},mem: {} add tram quy hoach", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProjectStation dao = factory.getProjectStationDAO();
            trans.connectionType(Constants.DB_CB);
            dao.setTransaction(trans);

            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
            logger.info("user: {}, ip: {},mem: {} update database tram quy code: {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());
            if (tramQh.getTramQhId() == null) {
                if (dao.insertProjectStation(tramQh, user.getId())) {
                    message = new Message(Message.TYPE_SUCCESS, "Thêm mới trạm quy hoạch", "Thành công");
                } else {
                    message = new Message(Message.TYPE_WARNING, "Thêm mới trạm quy hoạch", "Không thành công");
                    mm.addAttribute("tramQuyHoach", tramQh);
                    mm.put("btnName", "Thêm mới");
                    mm.addAttribute("info", message);
                    return "project/project_station/editTab0";

                }
            } else {
                if (dao.updateThongTinChung(tramQh, user.getId())) {
                    message = new Message(Message.TYPE_SUCCESS, "Cập nhật thông tin chung", "Thành công");
                } else {
                    message = new Message(Message.TYPE_WARNING, "Cập nhật thông tin chung", "Không thành công");
                    mm.put("tramQuyHoach", tramQh);
                    mm.addAttribute("tramQuyHoach", tramQh);
                    mm.put("btnName", "Cập nhật");
                    mm.addAttribute("info", message);
                    return "project/project_station/editTab0";
                }
            }
            logger.info("user: {}, ip: {},mem: {} end update tram quy hoach", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        } catch (DAOException | ServiceException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Có lỗi xảy ra");
            mm.addAttribute("info", message);
            mm.put("btnName", "Cập nhật");
            return "project/project_station/editTab0";
        } finally {
            DatabaseUtils.close(trans);
        }

        attr.addFlashAttribute("info", message);
        return "redirect:/project_station/init";
    }

    //tim kiem quy hoach tinh
    @ModelAttribute("qhTinhLst")
    public List<ProjectBO> searchQHTinh() {
        List<ProjectBO> list = null;
        DAOFactory factory = null;
        ITransaction trans = null;
        try {
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProject iproject = factory.getProjectDAO();
            trans.connectionType(Constants.DB_CB);
            iproject.setTransaction(trans);
            list = iproject.searchProjectInfor(null, null);

        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            DatabaseUtils.close(trans);
        }
        return list;
    }

    @RequestMapping(value = "/preAddThongTinChung", method = RequestMethod.GET)
    public String preAddThongTinChung(ModelMap mm, HttpServletRequest request) throws Exception {
        try {
            ProjectStationBO tramQuyHoach = new ProjectStationBO();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
            mm.addAttribute("tramQuyHoach", tramQuyHoach);
            mm.put("btnName", "Thêm mới");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return "project/project_station/editTab0";
    }

    @RequestMapping(value = "/viewThongTinChung/{id}", method = RequestMethod.GET)
    public String viewThongTinChung(@PathVariable(value = "id") Integer id,
            Locale locale, RedirectAttributes attr, ModelMap mm, HttpServletRequest request) throws Exception {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
            List tramQuyHoachLst = searchTramQuyHoach(id, null, null, null, null, null, null, null);
            if (tramQuyHoachLst != null && tramQuyHoachLst.size() > 0) {
                mm.put("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.addAttribute("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.put("btnName", "Cập nhật");
            }

        } catch (ServiceException e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return "project/project_station/editTab0";
    }

    @RequestMapping(value = "/viewCamKet/{id}", method = RequestMethod.GET)
    public String viewCamKet(@PathVariable(value = "id") Integer id,
            Locale locale, RedirectAttributes attr, ModelMap mm, HttpServletRequest request) throws Exception {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
            List tramQuyHoachLst = searchTramQuyHoach(id, null, null, null, null, null, null, null);
            if (tramQuyHoachLst != null && tramQuyHoachLst.size() > 0) {
                mm.put("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.addAttribute("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.put("btnName", "Cập nhật");
            }
        } catch (ServiceException e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "project/project_station/editTab1";
    }

    //edit or update project
    @RequestMapping(value = "/updateCamKet", method = RequestMethod.POST)
    public String updateCamKet(ModelMap mm, @Valid @ModelAttribute(value = "tramQhBo") ProjectStationBO tramQh,
            RedirectAttributes attr, HttpServletRequest request) {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProjectStation dao = factory.getProjectStationDAO();
            trans.connectionType(Constants.DB_CB);
            dao.setTransaction(trans);
            logger.info("user: {}, ip: {},mem: {} update cam ket tram quy hoach:{}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());
            if (tramQh.getTramQhId() != null) {
                if (dao.updateCamKet(tramQh, user.getId())) {
                    message = new Message(Message.TYPE_SUCCESS, null, "Cập nhật thành công");
                } else {
                    message = new Message(Message.TYPE_WARNING, null, "Cập nhật không thành công");
                }
            }
            logger.info("user: {}, ip: {},mem: {} ket thuc update cam ket tram quy hoach:{}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());
        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Có lỗi xảy ra");
            attr.addFlashAttribute("info", message);

            return "project/project_station/editTab1";
        } finally {
            DatabaseUtils.close(trans);
        }
        attr.addFlashAttribute("tab", "1");
        attr.addFlashAttribute("info", message);
        return "redirect:/project_station/init";
    }

    //view thong tin co so ha tang
    @RequestMapping(value = "/viewCoSoHaTang/{id}", method = RequestMethod.GET)
    public String viewCoSoHaTang(@PathVariable(value = "id") Integer id,
            Locale locale, RedirectAttributes attr, ModelMap mm, HttpServletRequest request) throws Exception {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
            List tramQuyHoachLst = searchTramQuyHoach(id, null, null, null, null, null, null, null);
            if (tramQuyHoachLst != null && tramQuyHoachLst.size() > 0) {
                mm.put("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.addAttribute("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.put("btnName", "Cập nhật");
            }
        } catch (ServiceException e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "project/project_station/editTab2";
    }

    //edit or update project
    @RequestMapping(value = "/updateCSHT", method = RequestMethod.POST)
    public String updateCSHT(ModelMap mm, @Valid @ModelAttribute(value = "tramQhBo") ProjectStationBO tramQh,
            RedirectAttributes attr, HttpServletRequest request) {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProjectStation dao = factory.getProjectStationDAO();
            trans.connectionType(Constants.DB_CB);
            dao.setTransaction(trans);
            logger.info("user: {}, ip: {},mem: {} update csht tram quy hoach:{}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());
            if (tramQh.getTramQhId() != null) {
                if (dao.updateCSHT(tramQh, user.getId())) {
                    message = new Message(Message.TYPE_SUCCESS, null, "Cập nhật thành công");
                } else {
                    message = new Message(Message.TYPE_WARNING, null, "Cập nhật không thành công");
                }
            }
            logger.info("user: {}, ip: {},mem: {} ket thuc update tram quy hoach:{}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());

        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Có lỗi xảy ra");
            attr.addFlashAttribute("info", message);
            return "project/project_station/editTab1";
        } finally {
            DatabaseUtils.close(trans);
        }
        attr.addFlashAttribute("tab", "2");
        attr.addFlashAttribute("info", message);
        return "redirect:/project_station/init";
    }

    //view thong tin co so ha tang
    @RequestMapping(value = "/viewNguonDC/{id}", method = RequestMethod.GET)
    public String viewNguonDC(@PathVariable(value = "id") Integer id,
            Locale locale, RedirectAttributes attr, ModelMap mm, HttpServletRequest request) throws Exception {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
            List tramQuyHoachLst = searchTramQuyHoach(id, null, null, null, null, null, null, null);
            if (tramQuyHoachLst != null && tramQuyHoachLst.size() > 0) {
                mm.put("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.addAttribute("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.put("btnName", "Cập nhật");
            }
        } catch (ServiceException e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "project/project_station/editTab3";
    }

    @RequestMapping(value = "/viewAntena/{id}", method = RequestMethod.GET)
    public String viewAntena(@PathVariable(value = "id") Integer id,
            Locale locale, RedirectAttributes attr, ModelMap mm, HttpServletRequest request) throws Exception {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
            List tramQuyHoachLst = searchTramQuyHoach(id, null, null, null, null, null, null, null);
            if (tramQuyHoachLst != null && tramQuyHoachLst.size() > 0) {
                mm.put("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.addAttribute("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.put("btnName", "Cập nhật");
            }

        } catch (ServiceException e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "project/project_station/editTab4";
    }

    //edit or update project
    @RequestMapping(value = "/updateAntena", method = RequestMethod.POST)
    public String updateAntena(ModelMap mm, @Valid @ModelAttribute(value = "tramQhBo") ProjectStationBO tramQh,
            RedirectAttributes attr, HttpServletRequest request) {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProjectStation dao = factory.getProjectStationDAO();
            trans.connectionType(Constants.DB_CB);
            dao.setTransaction(trans);
            logger.info("user: {}, ip: {},mem: {} update antena tram quy hoach:{}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());
            if (tramQh.getTramQhId() != null) {
                if (dao.updateAntena(tramQh, user.getId())) {
                    message = new Message(Message.TYPE_SUCCESS, null, "Cập nhật thành công");
                } else {
                    message = new Message(Message.TYPE_WARNING, null, "Cập nhật không thành công");
                }
            }
            logger.info("user: {}, ip: {},mem: {} ket thuc update antena tram quy hoach:{}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());
        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Có lỗi xảy ra");
            attr.addFlashAttribute("info", message);
            return "project/project_station/editTab4";
        } finally {
            DatabaseUtils.close(trans);
        }
        attr.addFlashAttribute("tab", "4");
        attr.addFlashAttribute("info", message);
        return "redirect:/project_station/init";
    }

    //tim kiem tram quy hoach
    private List searchTramQuyHoach(Integer tramQhId, Integer qhTinhId, String status, String tinhTpIds, String maTramQh, String tenTramQh, String tinhTpId, String loaiCongNgheId) {
        List list = null;
        DAOFactory factory = null;
        ITransaction trans = null;
        try {
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProjectStation iproject = factory.getProjectStationDAO();
            trans.connectionType(Constants.DB_CB);
            iproject.setTransaction(trans);
            list = iproject.searchProjectStation(tramQhId, qhTinhId, status, tinhTpIds, maTramQh, tenTramQh, tinhTpId, loaiCongNgheId);

        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            DatabaseUtils.close(trans);
        }
        return list;
    }

//    trunglk_start
    @RequestMapping(value = "/updateTuNguon", method = RequestMethod.POST)
    public String updateTuNguon(ModelMap mm, @Valid @ModelAttribute(value = "tramQuyHoach") ProjectStationBO tramQh,
            RedirectAttributes attr, HttpServletRequest request) {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProjectStation dao = factory.getProjectStationDAO();
            trans.connectionType(Constants.DB_CB);
            dao.setTransaction(trans);
            logger.info("user: {}, ip: {},mem: {} update tu nguon tram quy hoach:{}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());
            if (tramQh.getTramQhId() != null) {
                if (dao.updateTuNguon(tramQh, user.getId())) {
                    message = new Message(Message.TYPE_SUCCESS, null, "Cập nhật thành công");
                } else {
                    message = new Message(Message.TYPE_WARNING, null, "Cập nhật không thành công");
                }
            }
            logger.info("user: {}, ip: {},mem: {} ket thuc update tu nguon tram quy hoach:{}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());
        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Có lỗi xảy ra");
            attr.addFlashAttribute("info", message);
            return "project/project_station/editTab3";
        } finally {
            DatabaseUtils.close(trans);
        }
        attr.addFlashAttribute("tab", "3");
        attr.addFlashAttribute("info", message);
        return "redirect:/project_station/init";
    }
//    trunglk_end

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public String approve(ModelMap mm, @Valid @ModelAttribute(value = "tramQuyHoach") ProjectStationBO tramQh,
            RedirectAttributes attr, HttpServletRequest request) {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProjectStation dao = factory.getProjectStationDAO();
            trans.connectionType(Constants.DB_CB);
            dao.setTransaction(trans);
            logger.info("user: {}, ip: {},mem: {} duyet tram quy hoach:{}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), tramQh.getMaQh());
            if (tramQh.getTramQhId() != null) {
                if (dao.updateStatus(tramQh, user.getId())) {
                    message = new Message(Message.TYPE_SUCCESS, null, "Xử lý thành công");
                } else {
                    message = new Message(Message.TYPE_WARNING, null, "Xử lý không thành công");
                }
            }
            logger.info("user: {}, ip: {},mem: {} ket thuc duyet tram quy hoach", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());

        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Có lỗi xảy ra");
            attr.addFlashAttribute("info", message);
        } finally {
            DatabaseUtils.close(trans);
        }
        attr.addFlashAttribute("tab", "0");
        attr.addFlashAttribute("info", message);
        return "redirect:/project_station/init";
    }

    @RequestMapping(value = "/initThongTinChung", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String initThongTinChung(HttpServletRequest request) {
        DAOFactory factory = null;
        ITransaction trans = null;

        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {},mem: {}, searching tram quy hoach", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProjectStation dao = factory.getProjectStationDAO();
            trans.connectionType(Constants.DB_CB);
            dao.setTransaction(trans);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpIds = String.join(",", tinhManager);

            int draw = -1;
            String prs_start_record = "";
            String prs_length_page = "";
            String prs_global_search = "";
            String prs_list_column_name = "";
            String prs_list_column_search = "";
            String prs_column_to_sort = "";
            String param_sort_column = "";
            String prs_sort_direction = "";
            String pList = "";
            ArrayList<String> ar_name = new ArrayList<>();
            ArrayList<String> ar_search_value = new ArrayList<>();
            // duyệt tất cả các tham số truyền vào request
            for (Enumeration items = request.getParameterNames(); items.hasMoreElements();) {
                String param_name = (String) items.nextElement();
                String param_value = request.getParameter(param_name);
                if (param_name.equals("draw")) {
                    draw = Integer.parseInt(param_value);
                } else if (param_name.startsWith("order") && param_name.contains("column")) {
                    param_sort_column = param_value;
                    prs_column_to_sort = param_value;
                } else if (param_name.startsWith("order") && param_name.contains("dir")) {
                    prs_sort_direction = param_value;
                } else if (param_name.equals("start")) {
                    prs_start_record = String.valueOf(Integer.parseInt(param_value) + 1);
                } else if (param_name.equals("length")) {
                    prs_length_page = param_value;
                } else if (param_name.equals("search[value]")) {
                    prs_global_search = param_value;
                } else if (param_name.startsWith("columns") && param_name.contains("name")) {
                    ar_name.add(param_value);
                } else if (param_name.startsWith("columns") && param_name.contains("search") && param_name.contains("value")) {
                    ar_search_value.add(param_value);
                }
                pList += param_name + "=" + param_value + ",";
            }
            //            
            int total_column = ar_name.size();
            for (int i = 0; i < total_column; i++) {
                String name = ar_name.get(i);
                String value = ar_search_value.get(i);
                // update tên cột sorting
                if (!param_sort_column.equals("0")) {
                    if (Integer.parseInt(param_sort_column) == i) {
                        prs_column_to_sort = ar_name.get(i);
                    }
                }
                // check xem cột đó có giá trị cần search ko
                if (!value.isEmpty()) {
                    prs_list_column_name = prs_list_column_name + ";" + name;
                    prs_list_column_search = prs_list_column_search + ";" + value;
                }
            }

            // gọi DB
//            
//                    + ", prs_length_page:" + prs_length_page
//                    + ", prs_global_search:" + prs_global_search
//                    + ", prs_list_column_name:" + prs_list_column_name
//                    + ", prs_list_column_search:" + prs_list_column_search
//                    + ", prs_column_to_sort:" + prs_column_to_sort
//                    + ", prs_sort_direction:" + prs_sort_direction);
            String[] recordsTotal = new String[1];
            String[] recordsFiltered = new String[1];
            ArrayList<ProjectStationBO> list_tram_qh = null;
            try {

                list_tram_qh = dao.fn_search(prs_start_record, prs_length_page,
                        prs_global_search, prs_list_column_name, prs_list_column_search, prs_column_to_sort,
                        prs_sort_direction, recordsTotal, recordsFiltered, tinhTpIds, null);
                logger.info("user: {}, ip: {},mem: {},record number : {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), list_tram_qh.size());
            } catch (DAOException ex) {
                logger.error(ex.getMessage(), ex);
            }
            int count = 0;
            // chuyển list mailItem thành list String
            List<List<String>> data = new ArrayList();
            if (list_tram_qh != null) {
                for (ProjectStationBO item : list_tram_qh) {
                    ArrayList<String> ls = new ArrayList();
                    // thứ tự phải khớp với thứ tự của các cột trong table của trang jsp
                    count++;
                    ls.add(count + "");
                    ls.add(item.getMaQh());
                    ls.add(item.getTenQh());
                    ls.add(dateToString(item.getNamKhoiTao()));
                    ls.add(item.getLongitude() == null ? "" : item.getLongitude().toString());
                    ls.add(item.getLatitude() == null ? "" : item.getLatitude().toString());
                    ls.add(item.getLoaiCongNghe());
                    ls.add(item.getBangTan());
                    ls.add(item.getPtCsht());
                    if (item.getTRANG_THAI_TRAM() != null && item.getTRANG_THAI_TRAM().equalsIgnoreCase(StationStatus.status60)) {
                        ls.add("Đã duyệt");
                    } else {
                        ls.add("Chưa duyệt");
                    }
                    ls.add("");
                    ls.add("");
                    ls.add("");
                    ls.add("");
                    ls.add("");
                    ls.add("");
                    ls.add("");
                    ls.add(item.getTramQhId().toString());
                    ls.add(item.getLoaiCongNgheId() == null ? "" : item.getLoaiCongNgheId().toString());
                    ls.add(item.getTinhTpId() == null ? "" : item.getTinhTpId().toString());
                    ls.add(item.getTRANG_THAI_TRAM() == null ? "" : item.getTRANG_THAI_TRAM());
                    data.add(ls);
                }
            }

            // chuyển thành object json
            ContentDataTableItem responseObj = new ContentDataTableItem(draw, recordsTotal[0], recordsFiltered[0], data);
            Gson gson = new Gson();
            logger.info("user: {}, ip: {},mem: {} end", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            return gson.toJson(responseObj);
        } catch (DAOException | NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    //delete project
    @RequestMapping(value = "/deleteTramQh/{tram_qh_id}", method = RequestMethod.GET)
    public String deleteTramQh(ModelMap mm, @PathVariable(value = "tram_qh_id") String qhId,
            Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {},mem: {} function 'deleteTramQh' start", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProjectStation iproject = factory.getProjectStationDAO();
            trans.connectionType(Constants.DB_CB);
            iproject.setTransaction(trans);
            logger.info("user: {}, ip: {},mem: {} function 'deleteTramQh' id: {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), qhId);
            if (iproject.deleteTramQh(qhId)) {
                message = new Message(Message.TYPE_SUCCESS, null, "Xóa thành công!");
            } else {
                message = new Message(Message.TYPE_WARNING, null, "Xóa không thành công!");
            }
            logger.info("user: {}, ip: {},mem: {} function 'deleteTramQh' end", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), qhId);
        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Xóa không thành công!");
        } finally {
            DatabaseUtils.close(trans);
        }
        mm.put("tab", "0");
        attr.addFlashAttribute("info", message);
        return "redirect:/project_station/init";
    }

    private String dateToString(Date date) {
        try {
            if (date != null) {
                SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
                return sp.format(date);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "id") Integer id,
            Locale locale, RedirectAttributes attr, ModelMap mm, HttpServletRequest request) throws Exception {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
            List tramQuyHoachLst = searchTramQuyHoach(id, null, null, null, null, null, null, null);
            if (tramQuyHoachLst != null && tramQuyHoachLst.size() > 0) {
                mm.put("tramQuyHoach", tramQuyHoachLst.get(0));
                mm.addAttribute("tramQuyHoach", tramQuyHoachLst.get(0));
            }
        } catch (ServiceException e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "project/project_station/detail";
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "maQh", required = false) String maQh,
            @RequestParam(value = "tenQh", required = false) String tenQh,
            @RequestParam(value = "khuVucIds", required = false) String khuVucIds,
            @RequestParam(value = "tinhTpIds", required = false) String tinhTpIds,
            @RequestParam(value = "loaiCongNghe", required = false) String loaiCongNghe,
            @RequestParam(value = "status", required = false) String status) {
        List<ProjectStationBO> data = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String permisson = String.join(",", tinhManager);

            if (khuVucIds != null && khuVucIds.equalsIgnoreCase("null")) {
                khuVucIds = null;
            }
            if (tinhTpIds != null && tinhTpIds.equalsIgnoreCase("null")) {
                tinhTpIds = null;
            }
            if (status != null && status.equalsIgnoreCase("0")) {
                status = null;
            }
            logger.info("user: {}, ip: {},mem: {} function 'exportExcel' maQh:{}, tenQh:{}, khuVucIds:{}, tinhTpIds:{}, loaiCongNghe:{}, status:{}, permisson:{}",
                    user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), maQh, tenQh, khuVucIds, tinhTpIds, loaiCongNghe, status, permisson);
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            data = TramQHFacade.exportQH(maQh, tenQh, khuVucIds, tinhTpIds, loaiCongNghe, status, permisson);
            logger.info("user: {}, ip: {},mem: {}, function 'exportExcel'  {} ban ghi", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), data.size());
            File fileResult = writeExportExcel(dataDirectory + "/export_qh.xlsx", data);
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
            logger.info("user: {}, ip: {},mem: {}, function 'exportExcel' end", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (data != null) {
                data.clear();
            }
        }
    }

    private File writeExportExcel(String fileTemplate, List<ProjectStationBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "QH" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (ProjectStationBO it : datas) {
                    row = sheet.createRow(rowIndex++);
                    cell = row.createCell(0);
                    cell.setCellValue(it.getMaQh() == null ? "" : it.getMaQh());
                    cell = row.createCell(1);
                    cell.setCellValue(it.getTenQh() == null ? "" : it.getTenQh());
                    cell = row.createCell(2);
                    cell.setCellValue(it.getBuildingCode() == null ? "" : it.getBuildingCode());
                    cell = row.createCell(3);
                    cell.setCellValue(it.getTinhTp() == null ? "" : it.getTinhTp());
                    cell = row.createCell(4);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNamKhoiTao(), "dd/MM/yyyy"));
                    cell = row.createCell(5);
                    cell.setCellValue(it.getLongitude());
                    cell = row.createCell(6);
                    cell.setCellValue(it.getLatitude());
                    cell = row.createCell(7);
                    cell.setCellValue(it.getLoaiCongNghe());
                    cell = row.createCell(8);
                    cell.setCellValue(it.getBangTan());
                    cell = row.createCell(9);
                    cell.setCellValue(it.getPtCsht());
                    cell = row.createCell(10);
                    cell.setCellValue(it.getTrangThaiCsht());
                    cell = row.createCell(11);
                    cell.setCellValue(it.getDonviPheDuyet());
                    cell = row.createCell(12);
                    cell.setCellValue(it.getSoHieuVanBan());
                    cell = row.createCell(13);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayPheDuyet(), "dd/MM/yyyy"));
                    cell = row.createCell(14);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayDieuChinhGanNhat(), "dd/MM/yyyy"));
                    cell = row.createCell(15);
                    cell.setCellValue(it.getDonViDieuChinh());
                    cell = row.createCell(16);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgayPhatSong(), "dd/MM/yyyy"));
                    cell = row.createCell(17);
                    cell.setCellValue(it.getDVI_TRACH_NHIEM_CCTB());
                    cell = row.createCell(18);
                    cell.setCellValue(it.getNGUON_THIET_BI());
                    cell = row.createCell(19);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getTHOI_DIEM_DAP_UNG_DU_KIEN(), "dd/MM/yyyy"));
                    cell = row.createCell(20);
                    cell.setCellValue(it.getTEN_CONG_NGHE_DAP_UNG());
                    cell = row.createCell(21);
                    cell.setCellValue(it.getCHUNG_LOAI_THIET_BI());
                    cell = row.createCell(22);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getTHOI_DIEM_DAP_UNG_THUC_TE(), "dd/MM/yyyy"));
                    cell = row.createCell(23);
                    cell.setCellValue(it.getKHO_KHAN_VUONG_MAC());
                    //csht
                    cell = row.createCell(24);
                    cell.setCellValue(it.getDVI_TRACH_NHIEM_CSHT());
                    cell = row.createCell(25);
                    cell.setCellValue(it.getQuanHuyen());
                    cell = row.createCell(26);
                    cell.setCellValue(it.getPhuongXa());
                    cell = row.createCell(27);
                    cell.setCellValue(it.getDIA_CHI());
                    cell = row.createCell(28);
                    cell.setCellValue(it.getTEN_TRAM());
                    cell = row.createCell(29);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_duoc_pd_cap_von_csht(), "dd/MM/yyyy"));
                    cell = row.createCell(30);
                    cell.setCellValue(it.getCACH_XAY_CSHT());
                    cell = row.createCell(31);
                    cell.setCellValue(it.getLOAI_DAT());
                    cell = row.createCell(32);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_CAP_DAT(), "dd/MM/yyyy"));
                    cell = row.createCell(33);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_XIN_PHEP_XD_NHA_TRAM(), "dd/MM/yyyy"));
                    cell = row.createCell(34);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_HOAN_THANH_THU_TUC(), "dd/MM/yyyy"));
                    cell = row.createCell(35);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_KHOI_CONG_XD_NHA_TRAM(), "dd/MM/yyyy"));
                    cell = row.createCell(36);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_HOAN_THANH_XD_NHA_TRAM(), "dd/MM/yyyy"));
                    cell = row.createCell(37);
                    cell.setCellValue(it.getLOAI_NHA_TRAM());
                    cell = row.createCell(38);
                    if (it.getTinh_trang_nha_tram() != null && it.getTinh_trang_nha_tram() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getTinh_trang_nha_tram() != null && it.getTinh_trang_nha_tram() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    cell = row.createCell(39);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_XIN_PHEP_DO_CAO_COT(), "dd/MM/yyyy"));
                    cell = row.createCell(40);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_CAP_PHEP_DO_CAO_COT(), "dd/MM/yyyy"));
                    cell = row.createCell(41);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_HOAN_THANH_THU_TUC_XD_COT(), "dd/MM/yyyy"));
                    cell = row.createCell(42);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_KHOI_CONG_XD_COT(), "dd/MM/yyyy"));
                    cell = row.createCell(43);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_HOAN_THANH_XD_COT(), "dd/MM/yyyy"));
                    cell = row.createCell(44);
                    cell.setCellValue(it.getLOAI_COT_ANTEN());
                    cell = row.createCell(45);
                    cell.setCellValue(it.getDO_CAO_COT());
                    cell = row.createCell(46);
                    cell.setCellValue(it.getDO_CAO_VI_TRI_XAY_COT_ANTTEN());
                    cell = row.createCell(47);
                    if (it.getTinh_trang_cot_anten() != null && it.getTinh_trang_cot_anten() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getTinh_trang_cot_anten() != null && it.getTinh_trang_cot_anten() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    cell = row.createCell(48);
                    cell.setCellValue(it.getLOAI_TRUYEN_DAN());
                    cell = row.createCell(49);
                    cell.setCellValue(it.getGIAODIEN_TD_E1());
                    cell = row.createCell(50);
                    cell.setCellValue(it.getGIAODIEN_TD_FE());
                    cell = row.createCell(51);
                    cell.setCellValue(it.getGIAODIEN_TD_GE());
                    cell = row.createCell(52);
                    cell.setCellValue(it.getGIAODIEN_TD_STM1());
                    cell = row.createCell(53);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_KHOI_CONG_TD(), "dd/MM/yyyy"));
                    cell = row.createCell(54);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_HOAN_THANH_TD(), "dd/MM/yyyy"));
                    cell = row.createCell(55);
                    if (it.getTinh_trang_truyen_dan() != null && it.getTinh_trang_truyen_dan() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getTinh_trang_truyen_dan() != null && it.getTinh_trang_truyen_dan() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }
                    cell = row.createCell(56);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_DAP_UNG_DIEN_AC(), "dd/MM/yyyy"));
                    cell = row.createCell(57);
                    if (it.getHE_THONG_DIEN_NHA_TRAM_ID() != null && it.getHE_THONG_DIEN_NHA_TRAM_ID() == 1) {
                        cell.setCellValue("Đã có");
                    } else if (it.getHE_THONG_DIEN_NHA_TRAM_ID() != null && it.getHE_THONG_DIEN_NHA_TRAM_ID() == 0) {
                        cell.setCellValue("Chưa có");
                    } else {
                        cell.setCellValue("");
                    }
                    cell = row.createCell(58);
                    if (it.getHE_THONG_DIEU_HOA_ID() != null && it.getHE_THONG_DIEU_HOA_ID() == 1) {
                        cell.setCellValue("Đã có");
                    } else if (it.getHE_THONG_DIEU_HOA_ID() != null && it.getHE_THONG_DIEU_HOA_ID() == 0) {
                        cell.setCellValue("Chưa có");
                    } else {
                        cell.setCellValue("");
                    }
                    cell = row.createCell(59);
                    if (it.getHE_THONG_TIEP_DAT_ID() != null && it.getHE_THONG_TIEP_DAT_ID() == 1) {
                        cell.setCellValue("Đã có");
                    } else if (it.getHE_THONG_TIEP_DAT_ID() != null && it.getHE_THONG_TIEP_DAT_ID() == 0) {
                        cell.setCellValue("Chưa có");
                    } else {
                        cell.setCellValue("");
                    }
                    cell = row.createCell(60);
                    if (it.getHE_THONG_MAY_NO_ID() != null && it.getHE_THONG_MAY_NO_ID() == 1) {
                        cell.setCellValue("Đã có");
                    } else if (it.getHE_THONG_MAY_NO_ID() != null && it.getHE_THONG_MAY_NO_ID() == 0) {
                        cell.setCellValue("Chưa có");
                    } else {
                        cell.setCellValue("");
                    }
                    cell = row.createCell(61);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_HOAN_THANH_PHU_TRO(), "dd/MM/yyyy"));
                    cell = row.createCell(62);
                    cell.setCellValue(it.getDOI_TUONG_THONG_BAO());
                    cell = row.createCell(63);
                    cell.setCellValue(it.getSO_HIEU_THONG_BAO());
                    cell = row.createCell(64);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_THONG_BAO_HT_CSHT(), "dd/MM/yyyy"));
                    cell = row.createCell(65);
                    cell.setCellValue(it.getKHO_KHAN_VUONG_MAC_CSHT());
                    //nguon dc
                    cell = row.createCell(66);
                    cell.setCellValue(it.getDVI_TRACH_NHIEM_TU_NGUON());
                    cell = row.createCell(67);
                    cell.setCellValue(it.getTU_NGUON_DC());
                    cell = row.createCell(68);
                    cell.setCellValue(it.getTEN_LOAI_TU_NGUON());
                    cell = row.createCell(69);
                    cell.setCellValue(it.getDUNG_LUONG_TU_NGUON());
                    cell = row.createCell(70);
                    cell.setCellValue(it.getSO_RACTIFIER());
                    cell = row.createCell(71);
                    cell.setCellValue(it.getDUNG_LUONG_ACCU());
                    cell = row.createCell(72);
                    cell.setCellValue(it.getSO_LUONG_ACCU());
                    cell = row.createCell(73);
                    cell.setCellValue(it.getDIEN_AP_ACCU());
                    cell = row.createCell(74);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_DAP_UNG_NGUON_DC_DU_KIEN(), "dd/MM/yyyy"));
                    cell = row.createCell(75);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNGAY_DAP_UNG_NGUON_DC_TT(), "dd/MM/yyyy"));
                    cell = row.createCell(76);
                    if (it.getTinh_trang_nguon_dien() != null && it.getTinh_trang_nguon_dien() == 1) {
                        cell.setCellValue("OK");
                    } else if (it.getTinh_trang_nguon_dien() != null && it.getTinh_trang_nguon_dien() == 0) {
                        cell.setCellValue("NOK");
                    } else {
                        cell.setCellValue("");
                    }

                }
            }

            fos = new FileOutputStream(result);
            workbook.write(fos);

        } catch (IOException e) {
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
