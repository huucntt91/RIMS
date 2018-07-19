/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.project;

import com.vnpt.media.rims.bean.BuildingBO;
import com.vnpt.media.rims.bean.ProjectBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IProject;
import com.vnpt.media.rims.dao.ITinh;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {

    private static Logger logger = LogManager.getLogger(ProjectController.class);
    private static String projectPage = "project/project/index";
    private static final String POPUP = "project/project/popup";
    List<ProjectBO> projectInfors;
    List<ProjectBO> projects;
    List<TinhBO> provinces;
    @Autowired
    private MessageSource messageSource;
    String btnName1;
    String btnName0;
    Integer qhInforId;
    Integer qhId;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request, @RequestParam(value = "id", required = false) String id, @RequestParam(value = "qhId", required = false) Integer quyhoachId) {
        logger.info("List all project from database RIMS");
        try {
            projects = projectLst("");
            projectInfors = projectInforLst("");
            provinces = provinceLst();
            btnName1 = "Thêm mới";
            btnName0 = "Thêm mới";
            qhInforId = null;
            qhId = null;
            if (id != null && !id.equals("")) {
                for (ProjectBO projectInfor : projectInfors) {
                    if (projectInfor.getQhInforId().intValue() == Integer.valueOf(id)) {

                        btnName1 = "Cập nhật";
                        qhInforId = projectInfor.getQhInforId();
                        mm.put("projectInfor", projectInfor);
                        mm.put("tab", "1");
                        break;
                    }
                }
            }
            if (quyhoachId != null) {
                for (ProjectBO project : projects) {
                    if (project.getQhId().intValue() == quyhoachId.intValue()) {

                        btnName0 = "Cập nhật";
                        qhId = project.getQhId();
                        mm.put("project", project);
                        mm.put("parentId", project.getParentId());
                        mm.put("tab", "0");
                        break;
                    }
                }
            }
            mm.put("btnName1", btnName1);
            mm.put("btnName0", btnName0);
            mm.put("projects", projects);
            mm.put("projectInfors", projectInfors);
            mm.put("provinces", provinces);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return projectPage;
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

    @ModelAttribute("qhs")
    public List<ProjectBO> findQhLst() {
        List<ProjectBO> list = null;
        try {
            list = projectLst("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @RequestMapping(value = "/preAddQh", method = RequestMethod.GET)
    public String preAddQh(ModelMap mm, HttpServletRequest request) {
        try {
            ProjectBO qh = new ProjectBO();
            mm.addAttribute("qh", qh);
            mm.put("btnName", "Thêm mới");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return "project/project/viewQh";
    }

    @RequestMapping(value = "/preAddQhTinh", method = RequestMethod.GET)
    public String preAddQhTinh(ModelMap mm, HttpServletRequest request) {
        try {
            ProjectBO qh = new ProjectBO();
            mm.addAttribute("qhTinh", qh);
            mm.put("btnName", "Thêm mới");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return "project/project/viewQhTinh";
    }

    //edit or update project
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public String addProject(ModelMap mm, @Valid @ModelAttribute(value = "projectBO") ProjectBO projectBO,
            RedirectAttributes attr, HttpServletRequest request) {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {},mem: {}, function 'addProject' ", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProject iproject = factory.getProjectDAO();
            trans.connectionType(Constants.DB_CB);
            iproject.setTransaction(trans);
            logger.info("user: {}, ip: {},mem: {},function 'addProject' call database projectcode: {} ", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), projectBO.getProjectCode());
            if (projectBO.getQhId() == null) {
                int result = iproject.addProject(projectBO, user.getId());
                System.out.println("result: " + result);
                if (result > 0) {
                    message = new Message(Message.TYPE_SUCCESS, null, "Thêm mới thành công");
                } else if (result == 0) {
                    message = new Message(Message.TYPE_WARNING, null, "Mã quy hoạch đã tồn tại");
                } else {
                    message = new Message(Message.TYPE_WARNING, null, "Thêm mới không thành công");
                }
            } else {
                int result = iproject.updateProject(projectBO, user.getId());
                System.out.println("result: " + result);
                if (result > 0) {
                    message = new Message(Message.TYPE_SUCCESS, null, "Câp nhật  thành công");
                } else if (result == 0) {
                    message = new Message(Message.TYPE_WARNING, null, "Mã quy hoạch đã tồn tại");
                } else {
                    message = new Message(Message.TYPE_WARNING, null, "Cập nhật không thành công");
                }
            }
            logger.info("user: {}, ip: {},mem: {},function 'addProject' end", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Có lỗi xảy ra");
        } finally {
            DatabaseUtils.close(trans);
        }
        attr.addFlashAttribute("info", message);
        //Object to JSON in String
        return "redirect:/project/init";
    }

    //view thong tin co so ha tang
    @RequestMapping(value = "/viewQuyHoach/{id}", method = RequestMethod.GET)
    public String viewQuyHoach(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm, HttpServletRequest request) throws Exception {
        try {
            ProjectBO qh = null;
            if (id == null || id.isEmpty()) {
                qh = new ProjectBO();
                mm.put("btnName", "Thêm mới");
            } else {
                List<ProjectBO> qhs = projectLst(id);
                if (qhs != null && !qhs.isEmpty()) {
                    qh = qhs.get(0);
                    mm.put("btnName", "Cập nhật");
                }

            }
            mm.put("qh", qh);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "project/project/viewQh";
    }

    //view thong tin co so ha tang
    @RequestMapping(value = "/viewQuyHoachTinh/{id}", method = RequestMethod.GET)
    public String viewQuyHoachTinh(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm, HttpServletRequest request) throws Exception {
        try {
//            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
//            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
//            mm.addAttribute("classAtrrEdit", classAtrrEdit);
//            mm.addAttribute("classAtrrView", classAtrrView);
            ProjectBO qh = null;
            if (id == null || id.isEmpty()) {
                qh = new ProjectBO();
                mm.put("btnName", "Thêm mới");
            } else {
                List<ProjectBO> qhs = projectInforLst(id);
                if (qhs != null && !qhs.isEmpty()) {
                    qh = qhs.get(0);
                    mm.put("btnName", "Cập nhật");
                }

            }
            mm.put("qhTinh", qh);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return "project/project/viewQhTinh";
    }

    //delete project
    @RequestMapping(value = "/deleteProject/{qhId}", method = RequestMethod.GET)
    public String deleteProject(ModelMap mm, @PathVariable(value = "qhId") Integer qhId,
            Locale locale, RedirectAttributes attr) throws Exception {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;
        try {
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProject iproject = factory.getProjectDAO();
            trans.connectionType(Constants.DB_CB);
            iproject.setTransaction(trans);
            if (iproject.deleteProject(qhId)) {
                message = new Message(Message.TYPE_SUCCESS, null, "Xóa thành công!");
            } else {
                message = new Message(Message.TYPE_WARNING, null, "Xóa không thành công!");
            }
        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Xóa không thành công!");
        } finally {
            DatabaseUtils.close(trans);
        }
        mm.put("tab", "0");
        attr.addFlashAttribute("info", message);
        return "redirect:/project/init";
    }

    @RequestMapping(value = "/addProjectInfor", method = RequestMethod.POST)
    public String addProjectInfor(ModelMap mm, @Valid @ModelAttribute(value = "projectBO") ProjectBO projectBO,
            RedirectAttributes attr, HttpServletRequest request) {
        DAOFactory factory = null;
        ITransaction trans = null;
        Message message = null;

        try {
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProject iproject = factory.getProjectDAO();
            trans.connectionType(Constants.DB_CB);
            iproject.setTransaction(trans);

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            int chi_2g = projectBO.getSoLuongChi2G() == null ? 0 : projectBO.getSoLuongChi2G();
            int so_luong_2g_3g = projectBO.getSoLuong2G3G() == null ? 0 : projectBO.getSoLuong2G3G();
            int tong_thiet_bi_2g = chi_2g + so_luong_2g_3g;
            int lap_tren_csht_co_san_3g_2100 = projectBO.getCshtNew() == null ? 0 : projectBO.getCshtNew();
            int lap_tren_csht_moi_3g_2100 = projectBO.getChstActive() == null ? 0 : projectBO.getChstActive();
            int tong_thiet_bi_3g_2100 = lap_tren_csht_co_san_3g_2100 + lap_tren_csht_moi_3g_2100;
            projectBO.setTongThietBi2G3G(tong_thiet_bi_2g);
            projectBO.setTongThieBi(tong_thiet_bi_3g_2100);
            if (projectBO.getQhInforId() != null) {
                if (iproject.updateProjectInfor(projectBO, user.getId())) {
                    message = new Message(Message.TYPE_SUCCESS, null, "Cập nhật thành công!");
                } else {
                    message = new Message(Message.TYPE_WARNING, null, "Cập nhật không thành công!");
                }
            } else {
                if (iproject.addProjectInfor(projectBO, user.getId())) {
                    message = new Message(Message.TYPE_SUCCESS, null, "Thêm mới thành công!");
                } else {
                    message = new Message(Message.TYPE_WARNING, null, "Thêm mới không thành công!");
                }
            }
        } catch (DAOException e) {
            message = new Message(Message.TYPE_WARNING, null, "Có lỗi xảy ra");
            logger.error(e.getMessage(), e);
        } finally {
            DatabaseUtils.close(trans);
        }
        attr.addFlashAttribute("tab", "1");
        attr.addFlashAttribute("info", message);
        //Object to JSON in String
        return "redirect:/project/init";
    }

    @RequestMapping(value = "/deleteProjectInfor/{qhInforId}", method = RequestMethod.GET)
    public String deleteProjectInfor(ModelMap mm, @PathVariable(value = "qhInforId") Integer qhInforId,
            Locale locale, RedirectAttributes attr) throws Exception {
        DAOFactory factory = null;
        ITransaction trans = null;
        String msg = "";
        Message message = null;
        try {
            logger.info("qhInforId: " + qhInforId);
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            IProject iproject = factory.getProjectDAO();
            trans.connectionType(Constants.DB_CB);
            iproject.setTransaction(trans);
            if (iproject.deleteProjectInfor(qhInforId)) {
                msg = "Xóa thành công!";
                message = new Message(Message.TYPE_SUCCESS, null, msg);
            } else {
                msg = "Xóa không thành công!";
            }
        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            msg = "Xóa không thành công!";
            message = new Message(Message.TYPE_WARNING, null, msg);
        } finally {
            DatabaseUtils.close(trans);
        }
        attr.addFlashAttribute("tab", "1");
        attr.addFlashAttribute("info", message);
        return "redirect:/project/init";
    }

    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String popup(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId, @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, @RequestParam(value = "phuongXaId", required = false) String phuongXaId,
            ModelMap mm, HttpServletRequest request, HttpServletResponse response) {

        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        quanHuyenId = quanHuyenId == null ? "" : quanHuyenId;
        phuongXaId = phuongXaId == null ? "" : phuongXaId;
        code = code == null ? "" : code;
        CategoriesFacade facade = new CategoriesFacade();

        int totalRows = facade.getTotalUser(code, null, tinhTpId, quanHuyenId, phuongXaId);

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
        List<BuildingBO> list = facade.findAllBuildingBO(String.valueOf(startRow), String.valueOf(endRow), "", code, null, tinhTpId, quanHuyenId, phuongXaId);
        mm.put("list", list);
        mm.put("phuongXaId", phuongXaId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("quanHuyenId", quanHuyenId);
        mm.put("code", code);
        mm.put("startRow", startRow);
        return POPUP;
    }

    private List<ProjectBO> projectLst(String quy_hoach_id) {
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
            list = iproject.searchProject(quy_hoach_id);

        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            DatabaseUtils.close(trans);
        }
        return list;
    }

    private List<TinhBO> provinceLst() {
        DAOFactory factory = null;
        ITransaction trans = null;
        List<TinhBO> list = null;
        try {
            if (factory == null) {
                factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            }
            trans = factory.getTransaction();
            ITinh iTinh = factory.getTinhDAO();
            trans.connectionType(Constants.DB_CB);
            iTinh.setTransaction(trans);
            list = iTinh.findAllTinh("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            DatabaseUtils.close(trans);
        }
        return list;
    }

    private List<ProjectBO> projectInforLst(String quy_hoach_tinh_id) {
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
            list = iproject.searchProjectInfor(quy_hoach_tinh_id, null);

        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            DatabaseUtils.close(trans);
        }
        return list;
    }
}
