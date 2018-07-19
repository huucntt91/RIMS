package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.BSCRNCInfoBO;
import com.vnpt.media.rims.bean.BTSInfoBO;
import com.vnpt.media.rims.bean.DMCellGroupBO;
import com.vnpt.media.rims.bean.DsLamBO;
import com.vnpt.media.rims.bean.ManEInfoBO;
import com.vnpt.media.rims.bean.OmcCell2gInfoBO;
import com.vnpt.media.rims.bean.OmcCell3gInfoBO;
import com.vnpt.media.rims.bean.OmcCell4gInfoBO;
import com.vnpt.media.rims.bean.SubEqBO;
import com.vnpt.media.rims.bean.TEq1BO;
import com.vnpt.media.rims.bean.TEq2BO;
import com.vnpt.media.rims.bean.TEq3BO;
import com.vnpt.media.rims.bean.TModuleQuangBO;
import com.vnpt.media.rims.bean.TNodeBO;
import com.vnpt.media.rims.bean.TPortBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.facade.AccessFacade;
import com.vnpt.media.rims.facade.BlackPointFacade;
import com.vnpt.media.rims.facade.CellGroupFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.ManeFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.facade.TEqFacade;
import com.vnpt.media.rims.facade.TnodeFacade;
import com.vnpt.media.rims.facade.Vn2Facade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/broadband/export")
public class ExportExcelController {

    private static final Logger LOGGER = LogManager.getLogger(ExportExcelController.class);
    private static final String VIEW_MANE = "broadband/export/mane";
    private static final String VIEW_ACCESS = "broadband/export/access";
    private static final String VIEW_VN2 = "broadband/export/vn2";
    private static final String VIEW_EQ = "broadband/export/eq";
    private static final String VIEW_BLACKPOINT = "broadband/export/blackpoint";
    private static final String VIEW_NODES = "broadband/export/nodes";

    @RequestMapping(value = "/mane", method = RequestMethod.GET)
    public String mane(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String provinceId,
            @RequestParam(value = "quanHuyenId", required = false) String districtId,
            @RequestParam(value = "phuongXaId", required = false) String wardsId,
            ModelMap mm, HttpServletRequest request) {
        try {
            int totalRows = ManeFacade.countSearch(code, name, typeId, khuvucId, provinceId, districtId, wardsId);

            List<ManEInfoBO> list = ManeFacade.findAll(0, totalRows, "", code, name, typeId, khuvucId,
                    provinceId, districtId, wardsId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("code", code);
            mm.put("name", name);
            mm.put("khuvucId", khuvucId);
            mm.put("tinhTpId", provinceId);
            mm.put("quanHuyenId", districtId);
            mm.put("phuongXaId", wardsId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return VIEW_MANE;
    }

    @RequestMapping(value = "/access", method = RequestMethod.GET)
    public String access(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String provinceId,
            @RequestParam(value = "quanHuyenId", required = false) String districtId,
            @RequestParam(value = "phuongXaId", required = false) String wardsId,
            ModelMap mm, HttpServletRequest request) {
        try {
            int totalRows = AccessFacade.getInstance().getTotal("", code, name, typeId, khuvucId, provinceId, districtId, wardsId);

            List<DsLamBO> list = (List<DsLamBO>) AccessFacade.getInstance().findAll(0, totalRows, "", code, name, typeId,
                    khuvucId, provinceId, districtId, wardsId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("code", code);
            mm.put("name", name);
            mm.put("khuvucId", khuvucId);
            mm.put("tinhTpId", provinceId);
            mm.put("quanHuyenId", districtId);
            mm.put("phuongXaId", wardsId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return VIEW_ACCESS;
    }

    @RequestMapping(value = "/vn2", method = RequestMethod.GET)
    public String vn2(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String provinceId,
            @RequestParam(value = "quanHuyenId", required = false) String districtId,
            @RequestParam(value = "phuongXaId", required = false) String wardsId,
            ModelMap mm, HttpServletRequest request) {
        try {
            int totalRows = Vn2Facade.countSearch(code, name, typeId, khuvucId, provinceId, districtId, wardsId);

            List<TNodeBO> list = Vn2Facade.findAll(0, totalRows, "", code, name, typeId, khuvucId,
                    provinceId, districtId, wardsId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("code", code);
            mm.put("name", name);
            mm.put("khuvucId", khuvucId);
            mm.put("tinhTpId", provinceId);
            mm.put("quanHuyenId", districtId);
            mm.put("phuongXaId", wardsId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return VIEW_VN2;
    }

    @RequestMapping(value = "/eq", method = RequestMethod.GET)
    public String eq(@RequestParam(value = "tnodeId", required = false) String tnodeId,
            ModelMap mm, HttpServletRequest request) {
        try {
            TEqFacade facade = new TEqFacade();
            List<TEq1BO> list = facade.findAllEq1(null, null, tnodeId, 0, 100);
            List<SubEqBO> subList = new ArrayList<>();
            for (TEq1BO item : list) {
                SubEqBO sub = new SubEqBO();
                List<TEq2BO> listEq2 = facade.findAllEq2(null, null, String.valueOf(item.getId()), 0, 100);
                item.settEq2BOList(listEq2);
                for (int i = 0; i < listEq2.size(); i++) {
                    List<TEq3BO> listEq3 = facade.findAllEq3(null, null, listEq2.get(i).getId() + "", 0, 100);
                    listEq2.get(i).settEq3BOList(listEq3);
                    item.settEq2BOList(listEq2);
                    for (int j = 0; j < listEq3.size(); j++) {
                        List<TPortBO> listPort = facade.findAllPort(null, null, listEq3.get(j).getId() + "", 0, 100);
                        listEq3.get(j).settPortBOList(listPort);
                        listEq2.get(i).settEq3BOList(listEq3);
                        item.settEq2BOList(listEq2);
                        for (int k = 0; k < listPort.size(); k++) {
                            List<TModuleQuangBO> listModuleQuang = facade.findAllModuleQuang(null, null, listPort.get(k).getId() + "", 0, 100);
                            listPort.get(k).settModuleQuangBOList(listModuleQuang);
                            listEq3.get(j).settPortBOList(listPort);
                            listEq2.get(i).settEq3BOList(listEq3);
                            item.settEq2BOList(listEq2);
                        }
                    }
                }
                sub.settEq1BO(item);
                subList.add(sub);
            }
            request.getSession().setAttribute("listEq", subList);
            mm.put("list", subList);
            TnodeFacade tnodefacade = new TnodeFacade();
            if (tnodeId != null) {
                List<TNodeBO> temp = tnodefacade.findAll(tnodeId, null, null);
                if (temp != null && temp.size() > 0) {
                    mm.put("tnodeName", temp.get(0).getName());
                    mm.put("tnodeId", tnodeId);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return VIEW_EQ;
    }

    @RequestMapping(value = "/blackpoint", method = RequestMethod.GET)
    public String blackPoint(
            ModelMap mm, HttpServletRequest request) {
        try {
            mm.put("list", BlackPointFacade.fc_find_all());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return VIEW_BLACKPOINT;
    }

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public String init(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "neTypeId", required = false) String neTypeId, @RequestParam(value = "thietBiId", required = false) String thietBiId, @RequestParam(value = "tinhTpId", required = false) String tinhTpId, @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, @RequestParam(value = "phuongXaId", required = false) String phuongXaId, @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            ModelMap mm, HttpServletRequest request) {
        try {
            LOGGER.info("Action export Danh sach doi tuong");
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
            quanHuyenId = quanHuyenId == null ? "" : quanHuyenId;
            phuongXaId = phuongXaId == null ? "" : phuongXaId;
            khuvucId = khuvucId == null ? "" : khuvucId;
            code = code == null ? "" : code;
            neTypeId = neTypeId == null ? "2" : neTypeId;
            thietBiId = thietBiId == null ? "" : thietBiId;
            status = status == null ? "" : status;
            NodesFacade facade = new NodesFacade();
            int totalRows = facade.getTotalDetail(code, khuvucId, tinhTpId, quanHuyenId, phuongXaId, neTypeId, thietBiId, status);
            NodesFacade nodesFacade = new NodesFacade();
            List<?> list = nodesFacade.findAllDetail("", String.valueOf(0),
                    String.valueOf(totalRows), code,
                    khuvucId, tinhTpId, quanHuyenId, phuongXaId, neTypeId, thietBiId, status);
            switch (neTypeId) {
                case "5":
                    mm.put("list", (List<OmcCell2gInfoBO>) list);
                    break;
                case "6":
                    mm.put("list", (List<OmcCell3gInfoBO>) list);
                    break;
                case "7":
                    mm.put("list", (List<OmcCell4gInfoBO>) list);
                    break;
                case "2":
                case "3":
                case "8":
                    mm.put("list", (List<BTSInfoBO>) list);
                    break;
                case "11":
                    mm.put("list", (List<BSCRNCInfoBO>) list);
                    break;
                default:
                    break;
            }
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", Convert.convertNeTypeToObjectId(neTypeId));
            mm.addAttribute("classAtrrView", classAtrrView);
            mm.put("phuongXaId", phuongXaId);
            mm.put("code", code);
            mm.put("tinhTpId", tinhTpId);
            mm.put("quanHuyenId", quanHuyenId);
            mm.put("neTypeId", neTypeId);
            mm.put("khuvucId", khuvucId);
            mm.put("thietBiId", thietBiId);
            mm.put("startRow", 1);
            mm.put("status", status);
            ArrayList<DMCellGroupBO> listCellGroup = CellGroupFacade.fc_find_all("");
            HashMap<String, Object> hashMapListCellGroup = new HashMap<>();
            for (int i = 0; i < listCellGroup.size(); i++) {
                DMCellGroupBO object = listCellGroup.get(i);
                hashMapListCellGroup.put(object.getId(), object);
            }
            mm.put("hashMapListCellGroup", hashMapListCellGroup);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return VIEW_NODES;
    }
}
