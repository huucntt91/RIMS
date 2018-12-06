package com.vnpt.media.rims.controller.nodes;

import com.vnpt.media.rims.bean.LoaiNeBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.facade.BangTanFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.SearchCellForm;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/cell")
public class CellController {

    private static Logger logger = LogManager.getLogger(CellController.class);
    private static final String LIST = "nodes/cell/cellList";
    private static final String FORM_NEW = "nodes/cell/cellNew";

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
//value = {"/userDetails", "/userDetails/edit/{id}"}

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(@RequestParam(value = "neTypeId", required = false) String neTypeId,
            @ModelAttribute("cellNewForm") CellNewForm cellNewForm,
            ModelMap mm, HttpServletRequest request) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        
        neTypeId = neTypeId == null ? "5" : neTypeId;
        if (user != null) {
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(neTypeId));
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", Convert.convertNeTypeToObjectId(neTypeId));
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
        }
        mm.put("action", "Insert");
        
        cellNewForm = cellNewForm == null ? new CellNewForm() : cellNewForm;
        LoaiNeBO loaiNe = new LoaiNeBO();
        loaiNe.setId(Long.valueOf(neTypeId));
        cellNewForm.setLoaiNE(loaiNe);
        mm.put("cellNewForm", cellNewForm);
        
        return FORM_NEW;
    }

    @RequestMapping(value = "/preRegUpdate/{nodeId}/{neTypeId}", method = RequestMethod.GET)
    public String preUpdateReg(@PathVariable(value = "nodeId") String nodeId,
            @PathVariable(value = "neTypeId") String neTypeId,
            ModelMap mm, HttpServletRequest request) {
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
        
        List<CellNewForm> cellNewForm = nodesFacade.findCell(Long.valueOf(nodeId), null, null);
        mm.put("cellNewForm", cellNewForm.get(0));
        mm.put("action", "Update");
        return FORM_NEW;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@ModelAttribute(value = "searchCellForm") SearchCellForm searchCellForm, ModelMap mm, HttpServletRequest request) {
//        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//
//        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//        if (user != null) {
//            List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId("5"));
//            mm.addAttribute("classAtrr", classAtrr);
//        }
//        mm.put("cellNewForm", new CellNewForm());
        NodesFacade nodesFacade = new NodesFacade();
//        nodesFacade.updateCell(cellNewForm);
        if (searchCellForm.getStatus() == -1) {
            mm.put("list_cell", null);
            return LIST;
        }
        String statusList = searchCellForm.getStatus() + "";
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        List<CellNewForm> list = nodesFacade.findCell(null, statusList, tinhTpId);
        
        mm.put("list_cell", list);
        return LIST;
    }

    @RequestMapping(value = "/getParentNeTypeId/{neTypeid}", method = RequestMethod.GET,
            produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String getParentNeTypeId(@PathVariable(value = "neTypeid") String neTypeid, ModelMap mm,
            HttpServletRequest request) {
        StringBuilder xmlAjax = new StringBuilder();
        xmlAjax.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        xmlAjax.append("<responseCode>" + Convert.convertNeTypeIdToNeTypeParentId(neTypeid) + "</responseCode>");
        return xmlAjax.toString();
    }

    @RequestMapping(value = "/addCell", method = RequestMethod.POST)
    public String addCell(ModelMap mm, @ModelAttribute(value = "cellNewForm") CellNewForm cellNewForm,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes attr, Locale locale, HttpServletRequest request) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrr = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", Convert.convertNeTypeToObjectId(cellNewForm.getCellInfo().getNeTypeId() + ""));
        mm.addAttribute("classAtrr", classAtrr);
        mm.put("cellNewForm", cellNewForm);
        NodesFacade nodeFacade = new NodesFacade();
        if (action.equalsIgnoreCase("insert")) {
            cellNewForm.getCellInfo().setUserInsert(user.getId());
            nodeFacade.addCell(cellNewForm, attr, messageSource, locale);
            attr.addFlashAttribute("cellNewForm", cellNewForm);
            mm.put("action", action);
            return "redirect:/cell/init";
        } else {
            cellNewForm.getCellInfo().setStatus(Constants.NE_REG_ON);
            cellNewForm.getCellInfo().setId(cellNewForm.getCellInfo().getNodeId());
            cellNewForm.getCellInfo().setUserUpdate(user.getId());
            nodeFacade.updateCell(cellNewForm, attr);
            attr.addFlashAttribute("cellNewForm", cellNewForm);
            mm.put("action", action);
            return "redirect:/cell/preRegUpdate/" + cellNewForm.getCellInfo().getNodeId() + "/" + cellNewForm.getLoaiNE().getId();
        }

    }

    @RequestMapping(value = "/approveOn/init", method = RequestMethod.GET)
    public String approveOnInit(ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        NodesFacade nodesFacade = new NodesFacade();
        String statusList = Constants.NE_REG_ON + "," + Constants.NE_REG_OFF;
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);

        String tinhTpId = String.join(",", tinhManager);
        List<CellNewForm> list = nodesFacade.findCell(null, statusList, tinhTpId);
        mm.put("list_cell", list);
        mm.put("searchCellForm", new SearchCellForm());
        SearchCellForm statusTemp = new SearchCellForm();
        mm.put("approveForm", new ApproveForm());
        return "nodes/cell/cellList";
    }
    
    /*
    dang ky huy node
    */
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public String approve(@ModelAttribute(value = "approveForm") ApproveForm approveForm, ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        NodesFacade nodesFacade = new NodesFacade();
        String statusList = Constants.NE_APPROVE_ON + "";
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        nodesFacade.approveCell(approveForm, user.getId());

        
//        List<CellNewForm> list = nodesFacade.findCell(null, statusList, tinhTpId);
//        mm.put("list_cell", list);
//        if (Integer.valueOf(approveForm.getStatus()) == Constants.NE_REG_ON) {
//            return "redirect:/cells/approveOn/init?neTypeId=" + approveForm.getType();
//        } else if (Integer.valueOf(approveForm.getStatus()) == Constants.NE_REG_OFF) {
//            return "redirect:/cells/approveOn/init";
//        }
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//        return "redirect:/cells/approveOn/init?neTypeId=" + approveForm.getType();
        //huu_start sau khi dang ky huy thi se chuyen ve trang danh sach doi tuong
        return "redirect:/nodes/init?neTypeId=" + approveForm.getType() + "&status=" + approveForm.getStatus();
        //huu_end

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
        NodesFacade nodesFacade = new NodesFacade();

//        if (neTypeId.equalsIgnoreCase("5") || neTypeId.equalsIgnoreCase("6")
//                || neTypeId.equalsIgnoreCase("7")) {
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        
        List<CellNewForm> list = nodesFacade.findCell(Long.valueOf(id), null, tinhTpId);
        mm.put("cellNewForm", list.get(0));
        return "nodes/cell/cellEdit";
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
        List<?> list = nodesFacade.findAllDetail(id, "", "", "", "", tinhTpId, "", "", neTypeId, "", "");
        mm.put("cellNewForm", list.get(0));

        return "nodes/cell/cellDetail";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute(value = "cellNewForm") CellNewForm cellNewForm, ModelMap mm, Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        NodesFacade nodesFacade = new NodesFacade();
        String statusList = Constants.NE_REG_ON + "," + Constants.NE_REG_OFF;
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        cellNewForm.getCellInfo().setUserUpdate(user.getId());
        nodesFacade.updateCell(cellNewForm, attr);
        List<CellNewForm> list = nodesFacade.findCell(null, statusList, tinhTpId);
        mm.put("list_cell", list);
        return "redirect:/cell/preUpdate/" + cellNewForm.getCellInfo().getId() + "/" + cellNewForm.getCellInfo().getNeTypeId();
    }

    @RequestMapping(value = "/regOff/{id}/{neTypeId}", method = RequestMethod.GET)
    public String regOff(@PathVariable(value = "id") String id, @PathVariable(value = "neTypeId") String neTypeId,
            @ModelAttribute(value = "cellNewForm") CellNewForm cellNewForm, ModelMap mm, Locale locale,
            RedirectAttributes attr, HttpServletRequest request) throws Exception {
        NodesFacade nodesFacade = new NodesFacade();
        String statusList = Constants.NE_REG_ON + "," + Constants.NE_REG_OFF;
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);
        nodesFacade.updateCell(cellNewForm, attr);
        List<CellNewForm> list = nodesFacade.findCell(Long.valueOf(id), null, tinhTpId);
        mm.put("list_cell", list);
        return "redirect:/cell/preUpdate/" + cellNewForm.getCellInfo().getId() + "/" + cellNewForm.getCellInfo().getNeTypeId();
//         return request.getRequestURL().toString() + "?" + request.getQueryString();
    }

    //End TungPM
}
