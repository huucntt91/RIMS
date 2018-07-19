package com.vnpt.media.rims.controller.cell;

import com.vnpt.media.rims.bean.DMCellGroupBO;
import com.vnpt.media.rims.bean.LoaiAnTenBO;
import com.vnpt.media.rims.bean.LoaiNeBO;
import com.vnpt.media.rims.bean.OmcCell2gInfoBO;
import com.vnpt.media.rims.bean.OmcCell3gInfoBO;
import com.vnpt.media.rims.bean.OmcCell4gInfoBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.facade.BangTanFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.CellGroupFacade;
import com.vnpt.media.rims.facade.CellsFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.Cell2gRegForm;
import com.vnpt.media.rims.formbean.Cell3gRegForm;
import com.vnpt.media.rims.formbean.Cell4gRegForm;
//import com.vnpt.media.rims.formbean.Cell2GList;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping(value = "/cells")
public class CellsController extends BaseController {

    private static Logger LOGGER = LogManager.getLogger(CellsController.class);
    private static final String LIST = "nodes/cell/cellList";
    private static final String FORM_NEW = "nodes/cells/cellNew";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("trangThaiHDList")
    public List findAllTrangThaiHD() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTrangThaiHD("");
    }

    @ModelAttribute("bangTanList2G")
    public List findAllBangTan2G() {
        BangTanFacade facade = new BangTanFacade();
        return BangTanFacade.findBangTan(null, 1);
    }

    @ModelAttribute("bangTanList3G")
    public List findAllBangTan3G() {
        BangTanFacade facade = new BangTanFacade();
        return BangTanFacade.findBangTan(null, 2);
    }

    @ModelAttribute("bangTanList4G")
    public List findAllBangTan4G() {
        BangTanFacade facade = new BangTanFacade();
        return BangTanFacade.findBangTan(null, 3);
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

    @ModelAttribute("tramList2G")
    public List findAllTram2G() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllByNeTypeId(null, 5);
    }

    @ModelAttribute("tramList3G")
    public List findAllTram3G() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllByNeTypeId(null, 6);
    }

    @ModelAttribute("tramList4G")
    public List findAllTram4G() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllByNeTypeId(null, 7);
    }

    @ModelAttribute("nhomcellList")
    public List findNhomCell() {
        return CellGroupFacade.fc_find_all("");
//        return facade.findAllLoaiTram("");
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(@RequestParam(value = "neTypeId", required = false) String neTypeId,
            @RequestParam(value = "tab_id", required = false) String tab_id,
            @ModelAttribute("cell2gRegForm") Cell2gRegForm cell2gRegForm,
            @ModelAttribute("cell3gRegForm") Cell3gRegForm cell3gRegForm,
            @ModelAttribute("cell4gRegForm") Cell4gRegForm cell4gRegForm,
            ModelMap mm, HttpServletRequest request) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();

        mm.put("tab_id", tab_id == null ? "0" : tab_id);
        neTypeId = neTypeId == null ? "5" : neTypeId;
        if (user != null) {
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(neTypeId));
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", Convert.convertNeTypeToObjectId(neTypeId));
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
        }
//        bangTanList

        mm.put("action", "Insert");
//        

        cell2gRegForm = cell2gRegForm == null ? new Cell2gRegForm() : cell2gRegForm;
        cell3gRegForm = cell3gRegForm == null ? new Cell3gRegForm() : cell3gRegForm;

        cell4gRegForm = cell4gRegForm == null ? new Cell4gRegForm() : cell4gRegForm;

        LoaiNeBO loaiNe = new LoaiNeBO();
        loaiNe.setId(Long.valueOf(neTypeId));
//        cellNewForm.setLoaiNE(loaiNe);
//        mm.put("cellNewForm", cellNewForm);
        mm.put("cell2gRegForm", cell2gRegForm);
        mm.put("cell3gRegForm", cell3gRegForm);
        mm.put("cell4gRegForm", cell4gRegForm);
        mm.put("neTypeId", neTypeId);

        return FORM_NEW;
    }

    @RequestMapping(value = "/updateAddCell2g", method = RequestMethod.POST)
    public String updateAddCell2g(ModelMap mm,
            @ModelAttribute(value = "cellNewForm") OmcCell2gInfoBO cell2gRegForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, @RequestParam(value = "action", required = false) String action
    ) {

        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
        mm.addAttribute("classAtrr", classAtrr);
//        mm.put("cell2gRegForm", cell2gRegForm);
        CellsFacade cellsFacade = new CellsFacade();
//        String maNode = "DK_" + cellsFacade.getMaNodeReg(cell2gRegForm.getNodeChaId(), cell2gRegForm.getBtsCode()) + "_" + StringUtils.getRandom();
        cellsFacade.updateAddCell2g(String.valueOf(cell2gRegForm.getNodeId()), cell2gRegForm, user.getId());
        attr.addFlashAttribute("cell2gRegForm", new Cell2gRegForm());
        mm.put("action", action);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));

        return "redirect:/cells/preRegUpdate/" + cell2gRegForm.getNodeId() + "/5";
    }

    @RequestMapping(value = "/updateAddCell3g", method = RequestMethod.POST)
    public String updateAddCell3g(ModelMap mm,
            @ModelAttribute(value = "cellNewForm") OmcCell3gInfoBO cell3gRegForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, @RequestParam(value = "action", required = false) String action
    ) {

        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
        mm.addAttribute("classAtrr", classAtrr);
//        mm.put("cell3gRegForm", cell3gRegForm);
        CellsFacade cellsFacade = new CellsFacade();
//        String maNode = "DK_" + cellsFacade.getMaNodeReg(cell3gRegForm.getNodeChaId(), cell3gRegForm.getBtsCode());

        cellsFacade.updateAddCell3g(String.valueOf(cell3gRegForm.getNodeId()), cell3gRegForm, user.getId());
//        attr.addFlashAttribute("cell3gRegForm", cell3gRegForm);
        attr.addFlashAttribute("cell3gRegForm", new Cell3gRegForm());
        mm.put("action", action);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//        return "redirect:/cells/init";
        return "redirect:/cells/preRegUpdate/" + cell3gRegForm.getNodeId() + "/6";
    }

    @RequestMapping(value = "/updateAddCell4g", method = RequestMethod.POST)
    public String updateAddCell4g(ModelMap mm,
            @ModelAttribute(value = "cellNewForm") OmcCell4gInfoBO cell4gRegForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, @RequestParam(value = "action", required = false) String action
    ) {

        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
        mm.addAttribute("classAtrr", classAtrr);
//        mm.put("cell4gRegForm", cell4gRegForm);
        CellsFacade cellsFacade = new CellsFacade();
//        String maNode = "DK_" + cellsFacade.getMaNodeReg(cell4gRegForm.getNodeChaId(), cell4gRegForm.getBtsCode());
        cellsFacade.updateAddCell4g(String.valueOf(cell4gRegForm.getNodeId()), cell4gRegForm, user.getId());
//        attr.addFlashAttribute("cell4gRegForm", cell4gRegForm);
        attr.addFlashAttribute("cell4gRegForm", new Cell4gRegForm());
        mm.put("action", action);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//        return "redirect:/cells/init";
        return "redirect:/cells/preRegUpdate/" + cell4gRegForm.getNodeId() + "/7";
    }

    @RequestMapping(value = "/addCell2g", method = RequestMethod.POST)
    public String addCell2g(ModelMap mm, Locale locale,
            @ModelAttribute(value = "cell2gRegForm") Cell2gRegForm cell2gRegForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request,
            @RequestParam(value = "action", required = false) String action) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
            mm.addAttribute("classAtrr", classAtrr);
//        mm.put("cell2gRegForm", cell2gRegForm);
            CellsFacade cellsFacade = new CellsFacade();
            String maNode = "DK_" + cellsFacade.getMaNodeReg(cell2gRegForm.getNodeChaId(), cell2gRegForm.getBtsCode()) + "_" + StringUtils.getRandom();

            NodesFacade nodesFacade = new NodesFacade();
            // kiem tra xem ten tren he thong cua node co ton tai hay khong. ton tai thi tra ve 1
            if (nodesFacade.fn_check_name_system(null, cell2gRegForm.getTenTrenHeThong(), 5) > 0) {
                String backUrl = request.getHeader("Referer");
                String noWhitespaceAllowed = messageSource.getMessage("tram.systemname.exist", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:" + backUrl;
            }
            cellsFacade.addCell2g(cell2gRegForm, user.getId(), maNode);
            attr.addFlashAttribute("cell2gRegForm", new Cell2gRegForm());
            mm.put("action", action);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
            return "redirect:/cells/init";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/cells/init";
        }
    }

    @RequestMapping(value = "/addCell3g", method = RequestMethod.POST)
    public String addCell3g(ModelMap mm, Locale locale,
            @ModelAttribute(value = "cell3gRegForm") Cell3gRegForm cell3gRegForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request,
            @RequestParam(value = "action", required = false) String action) {
        try {

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
            mm.addAttribute("classAtrr", classAtrr);
//        mm.put("cell3gRegForm", cell3gRegForm);
            CellsFacade cellsFacade = new CellsFacade();
            String maNode = "DK_" + cellsFacade.getMaNodeReg(cell3gRegForm.getNodeChaId(), cell3gRegForm.getBtsCode());
            NodesFacade nodesFacade = new NodesFacade();
            // kiem tra xem ten tren he thong cua node co ton tai hay khong. ton tai thi tra ve 1
            if (nodesFacade.fn_check_name_system(null, cell3gRegForm.getTenTrenHeThong(), 6) > 0) {
                String backUrl = request.getHeader("Referer");
                String noWhitespaceAllowed = messageSource.getMessage("tram.systemname.exist", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:" + backUrl;
            }
            cellsFacade.addCell3g(cell3gRegForm, user.getId(), maNode);
//        attr.addFlashAttribute("cell3gRegForm", cell3gRegForm);
            attr.addFlashAttribute("cell3gRegForm", new Cell3gRegForm());
            mm.put("action", action);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
            return "redirect:/cells/init";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/cells/init";
        }
    }

    @RequestMapping(value = "/addCell4g", method = RequestMethod.POST)
    public String addCell4g(ModelMap mm, Locale locale,
            @ModelAttribute(value = "cell4gRegForm") Cell4gRegForm cell4gRegForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request,
            @RequestParam(value = "action", required = false) String action) {
        try {

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
            mm.addAttribute("classAtrr", classAtrr);
//        mm.put("cell4gRegForm", cell4gRegForm);
            CellsFacade cellsFacade = new CellsFacade();
            String maNode = "DK_" + cellsFacade.getMaNodeReg(cell4gRegForm.getNodeChaId(), cell4gRegForm.getBtsCode());

            NodesFacade nodesFacade = new NodesFacade();
            // kiem tra xem ten tren he thong cua node co ton tai hay khong. ton tai thi tra ve 1
            if (nodesFacade.fn_check_name_system(null, cell4gRegForm.getTenTrenHeThong(), 7) > 0) {
                String backUrl = request.getHeader("Referer");
                String noWhitespaceAllowed = messageSource.getMessage("tram.systemname.exist", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:" + backUrl;
            }

            cellsFacade.addCell4g(cell4gRegForm, user.getId(), maNode);
//        attr.addFlashAttribute("cell4gRegForm", cell4gRegForm);
            attr.addFlashAttribute("cell4gRegForm", new Cell4gRegForm());
            mm.put("action", action);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
            return "redirect:/cells/init";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/cells/init";
        }
    }

    @RequestMapping(value = "/approveOn/init", method = RequestMethod.GET)
    public String approveOnInit(
            @RequestParam(value = "neTypeId", required = false) String neTypeId,
            @RequestParam(value = "page", required = false) String page,
            ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        StringBuilder permisMenu = (StringBuilder) request.getSession().getAttribute(Constants.FUNCTION_KEY);

        if (!permisMenu.toString().toLowerCase().contains("approveon/init")) {
            return "redirect:/home/";
        }

        CellsFacade cellsFacade = new CellsFacade();
        NodesFacade nodesFacade = new NodesFacade();

        String statusList = Constants.NE_REG_ON + "," + Constants.NE_REG_OFF;
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        neTypeId = neTypeId == null ? "5" : neTypeId;
        String tinhTpId = String.join(",", tinhManager);
//        List<Cell2GList> list = cellsFacade.findCell(null, statusList, tinhTpId, neTypeId);
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = nodesFacade.getTotalDetail(null, null, tinhTpId, null, null, neTypeId, null, statusList);
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
            return ("redirect:/nodes/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/nodes/init?page=" + pageInt);
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

        List<?> list = null;

        list = nodesFacade.findAllDetail("", String.valueOf(startRow),
                String.valueOf(totalRows), null,
                null, tinhTpId, null, null, neTypeId, null, statusList);
        //

        if (neTypeId.equals("5")) {
            mm.put("list_cell", (List<OmcCell2gInfoBO>) list);
        } else if (neTypeId.equals("6")) {
            mm.put("list_cell", (List<OmcCell3gInfoBO>) list);
        } else if (neTypeId.equals("7")) {
            mm.put("list_cell", (List<OmcCell4gInfoBO>) list);
        }

//        mm.put("list_cell", list);
        mm.put("approveForm", new ApproveForm());
        mm.put("neTypeId", neTypeId);

        return "nodes/cells/cellList";
    }

    @RequestMapping(value = "/preRegUpdate/{nodeId}/{neTypeId}", method = RequestMethod.GET)
    public String preUpdateReg(@PathVariable(value = "nodeId") String nodeId,
            @PathVariable(value = "neTypeId") String neTypeId,
            ModelMap mm, HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            neTypeId = neTypeId == null ? "5" : neTypeId;
            if (user != null) {
                List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(neTypeId));
                List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", Convert.convertNeTypeToObjectId(neTypeId));
                mm.addAttribute("classAtrrEdit", classAtrrEdit);
                mm.addAttribute("classAtrrView", classAtrrView);
            }
            NodesFacade nodesFacade = new NodesFacade();

            List<?> list = null;
            LOGGER.info("user: {}, ip: {}, call findAllDetail : nodeId={}, neTypeId={}", user.getUsername(), request.getRemoteAddr(), nodeId, neTypeId);
            list = nodesFacade.findAllDetail(nodeId, null, null, null,
                    null, null, null, null, neTypeId, null, null);
            LOGGER.info("user: {}, ip: {}, done findAllDetail: {}", user.getUsername(), request.getRemoteAddr(), list.size());
            switch (neTypeId) {
                case "5": {
                    OmcCell2gInfoBO temp = (OmcCell2gInfoBO) list.get(0);
                    temp.setNodeId(Long.valueOf(nodeId));
                    mm.put("cellNewForm", temp);
                    break;
                }
                case "6": {
                    OmcCell3gInfoBO temp = (OmcCell3gInfoBO) list.get(0);
                    temp.setNodeId(Long.valueOf(nodeId));
                    mm.put("cellNewForm", temp);
                    break;
                }
                case "7": {
                    OmcCell4gInfoBO temp = (OmcCell4gInfoBO) list.get(0);
                    temp.setNodeId(Long.valueOf(nodeId));
                    mm.put("cellNewForm", temp);
                    break;
                }
                default:
                    break;
            }
            mm.put("neTypeId", neTypeId);
            mm.put("nodeId", nodeId);
            mm.put("action", "Update");
            return "nodes/cells/cellNewUpdate";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return "redirect:/node/init";
        }
    }

    @RequestMapping(value = "/preUpdate/{id}/{neTypeId}", method = RequestMethod.GET)
    public String preUpdate(ModelMap mm, @PathVariable(value = "id") String id, @PathVariable(value = "neTypeId") String neTypeId,
            Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(neTypeId));
        List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", Convert.convertNeTypeToObjectId(neTypeId));
        mm.addAttribute("classAtrrEdit", classAtrrEdit);
        mm.addAttribute("classAtrrView", classAtrrView);
        CellsFacade cellsFacade = new CellsFacade();

//        if (neTypeId.equalsIgnoreCase("5") || neTypeId.equalsIgnoreCase("6")
//                || neTypeId.equalsIgnoreCase("7")) {
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);

//        List<OmcCell2gInfoBO> list = cellsFacade.findCell(Long.valueOf(id), null, tinhTpId,neTypeId);
        NodesFacade nodesFacade = new NodesFacade();
        List<?> list = null;

        list = nodesFacade.findAllDetail(id, null, null, null,
                null, null, null, null, neTypeId, null, null);
        if (neTypeId.equals("5")) {
            OmcCell2gInfoBO cell = (OmcCell2gInfoBO) list.get(0);

            mm.put("cellNewForm", cell);
            mm.put("bangTanId", cell.getBangTanId());
        } else if (neTypeId.equals("6")) {
            OmcCell3gInfoBO cell = (OmcCell3gInfoBO) list.get(0);
            mm.put("cellNewForm", cell);
            mm.put("bangTanId", cell.getBangTanId());

        } else if (neTypeId.equals("7")) {
            OmcCell4gInfoBO cell = (OmcCell4gInfoBO) list.get(0);
            mm.put("cellNewForm", cell);
            mm.put("bangTanId", cell.getBangTanId());
        }
        ArrayList<DMCellGroupBO> nhomcellId = CellGroupFacade.fc_find_all_by_node_id(id);
        mm.put("nhomcellId", nhomcellId);

        CategoriesFacade facade = new CategoriesFacade();
        List<LoaiAnTenBO> listAnTen = facade.findAllAnTen("");
        mm.put("loaiAnTen", listAnTen);

        mm.put("nhomcellId", nhomcellId);

        mm.put("cellNewForm", list.get(0));
        if (neTypeId.equals("5")) {
            return "nodes/cells/cell2gEdit";
        } else if (neTypeId.equals("6")) {
            return "nodes/cells/cell3gEdit";
        } else {
            return "nodes/cells/cell4gEdit";
        }

    }

    @RequestMapping(value = "/updateCell2g", method = RequestMethod.POST)
    public String updateCell2g(ModelMap mm, @ModelAttribute(value = "cellNewForm") OmcCell2gInfoBO cellNewForm,
            Locale locale,
            //            @RequestParam(value = "nhomcellId", required = false) String nhomcellId,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            String[] nhomcellId = request.getParameterValues("nhomcellId");
            String temp = "";
            if (nhomcellId != null) {
                for (int i = 0; i < nhomcellId.length; i++) {

                    if (i < nhomcellId.length - 1) {
                        temp += nhomcellId[i] + ",";
                    } else {
                        temp += nhomcellId[i];
                    }
                }
            }
            cellNewForm.setListCellGroupId(temp);
            String statusList = Constants.NE_REG_ON + "," + Constants.NE_REG_OFF;
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpId = String.join(",", tinhManager);
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//        cellNewForm.getCellInfo().setUserUpdate(user.getId());
//        nodesFacade.updateCell(cellNewForm, attr);
            NodesFacade nodesFacade = new NodesFacade();
            // kiem tra xem ten tren he thong cua node co ton tai hay khong. ton tai thi tra ve 1
            if (nodesFacade.fn_check_name_system(cellNewForm.getId().intValue(), cellNewForm.getTenTrenHeThong(), cellNewForm.getNeTypeId().intValue()) > 0) {
                String backUrl = request.getHeader("Referer");
                String noWhitespaceAllowed = messageSource.getMessage("tram.systemname.exist", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:" + backUrl;
            }

            CellsFacade cellsFacade = new CellsFacade();
            cellsFacade.updateCell2g(cellNewForm, user.getId());
//        List<CellNewForm> list = nodesFacade.findCell(null, statusList, tinhTpId);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//        mm.put("list_cell", list);
            return "redirect:/nodes/init?neTypeId=" + cellNewForm.getNeTypeId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/preUpdate/" + cellNewForm.getId().toString() + "/" + cellNewForm.getNeTypeId();
        }
    }

    @RequestMapping(value = "/updateCell3g", method = RequestMethod.POST)
    public String updatecell3g(ModelMap mm, @ModelAttribute(value = "cellNewForm") OmcCell3gInfoBO cellNewForm,
            Locale locale,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {

        try {
            NodesFacade nodesFacade = new NodesFacade();
            String[] nhomcellId = request.getParameterValues("nhomcellId");
            String temp = "";
            if (nhomcellId != null) {
                for (int i = 0; i < nhomcellId.length; i++) {

                    if (i < nhomcellId.length - 1) {
                        temp += nhomcellId[i] + ",";
                    } else {
                        temp += nhomcellId[i];
                    }
                }
                cellNewForm.setListCellGroupId(temp);
            } else {
//            cellNewForm.setListCellGroupId(temp);
            }
            String statusList = Constants.NE_REG_ON + "," + Constants.NE_REG_OFF;
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpId = String.join(",", tinhManager);
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//        cellNewForm.getCellInfo().setUserUpdate(user.getId());
//        nodesFacade.updateCell(cellNewForm, attr);
            // kiem tra xem ten tren he thong cua node co ton tai hay khong. ton tai thi tra ve 1
            if (nodesFacade.fn_check_name_system(cellNewForm.getId().intValue(), cellNewForm.getTenTrenHeThong(), cellNewForm.getNeTypeId().intValue()) > 0) {
                String backUrl = request.getHeader("Referer");
                String noWhitespaceAllowed = messageSource.getMessage("tram.systemname.exist", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:" + backUrl;
            }

            CellsFacade cellsFacade = new CellsFacade();

            cellsFacade.updateCell3g(cellNewForm, user.getId());
//        List<CellNewForm> list = nodesFacade.findCell(null, statusList, tinhTpId);
//        mm.put("list_cell", list);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
            return "redirect:/nodes/init?neTypeId=" + cellNewForm.getNeTypeId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/preUpdate/" + cellNewForm.getId().toString() + "/" + cellNewForm.getNeTypeId();
        }
    }

    @RequestMapping(value = "/updateCell4g", method = RequestMethod.POST)
    public String updatecell4g(ModelMap mm, @ModelAttribute(value = "cellNewForm") OmcCell4gInfoBO cellNewForm,
            Locale locale,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {

        try {
            NodesFacade nodesFacade = new NodesFacade();
            String[] nhomcellId = request.getParameterValues("nhomcellId");
            String temp = "";
            if (nhomcellId != null) {
                for (int i = 0; i < nhomcellId.length; i++) {

                    if (i < nhomcellId.length - 1) {
                        temp += nhomcellId[i] + ",";
                    } else {
                        temp += nhomcellId[i];
                    }
                }
            }
            cellNewForm.setListCellGroupId(temp);
            String statusList = Constants.NE_REG_ON + "," + Constants.NE_REG_OFF;
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpId = String.join(",", tinhManager);
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//        cellNewForm.getCellInfo().setUserUpdate(user.getId());
//        nodesFacade.updateCell(cellNewForm, attr);

            // kiem tra xem ten tren he thong cua node co ton tai hay khong. ton tai thi tra ve 1
            if (nodesFacade.fn_check_name_system(cellNewForm.getId().intValue(), cellNewForm.getTenTrenHeThong(), cellNewForm.getNeTypeId().intValue()) > 0) {
                String backUrl = request.getHeader("Referer");
                String noWhitespaceAllowed = messageSource.getMessage("tram.systemname.exist", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:" + backUrl;
            }

            CellsFacade cellsFacade = new CellsFacade();
            cellsFacade.updateCell4g(cellNewForm, user.getId());
//        List<CellNewForm> list = nodesFacade.findCell(null, statusList, tinhTpId);
//        mm.put("list_cell", list);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
            return "redirect:/nodes/init?neTypeId=" + cellNewForm.getNeTypeId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/preUpdate/" + cellNewForm.getId().toString() + "/" + cellNewForm.getNeTypeId();
        }
    }

    @RequestMapping(value = "/detail/{id}/{neTypeId}", method = RequestMethod.GET)
    public String detail(ModelMap mm, @PathVariable(value = "id") String id, @PathVariable(value = "neTypeId") String neTypeId,
            Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", Convert.convertNeTypeToObjectId(neTypeId));
        mm.addAttribute("classAtrrView", classAtrrView);
        NodesFacade nodesFacade = new NodesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        //List<CellNewForm> list = nodesFacade.findCell(Long.valueOf(id), null, tinhTpId);
        List<?> list = null;
        list = nodesFacade.findAllDetail(id, null, null, null,
                null, tinhTpId, null, null, neTypeId, null, null);
        if (neTypeId.equals("5")) {
            OmcCell2gInfoBO temp = (OmcCell2gInfoBO) list.get(0);

            mm.put("cellNewForm", ((OmcCell2gInfoBO) list.get(0)));
        } else if (neTypeId.equals("6")) {
            mm.put("cellNewForm", ((OmcCell3gInfoBO) list.get(0)));
        } else if (neTypeId.equals("7")) {
            mm.put("cellNewForm", ((OmcCell4gInfoBO) list.get(0)));
        }
        ArrayList<DMCellGroupBO> nhomcellId = CellGroupFacade.fc_find_all_by_node_id(id);
        mm.put("nhomcellId", nhomcellId);

        mm.put("cellNewForm", list.get(0));
        CategoriesFacade facade = new CategoriesFacade();
        List<LoaiAnTenBO> listAnTen = facade.findAllAnTen("");
        mm.put("loaiAnTen", listAnTen);
        if (neTypeId.equals("5")) {
            return "nodes/cells/cell2gDetail";
        } else if (neTypeId.equals("6")) {
            return "nodes/cells/cell3gDetail";
        } else {
            return "nodes/cells/cell4gDetail";
        }
    }
}
