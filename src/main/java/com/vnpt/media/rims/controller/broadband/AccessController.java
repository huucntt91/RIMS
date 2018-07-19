/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.DongTbiBO;
import com.vnpt.media.rims.bean.DsLamBO;
import com.vnpt.media.rims.bean.DslamPortDeviceReportBO;
import com.vnpt.media.rims.bean.DslamPortTinhReportBO;
import com.vnpt.media.rims.bean.DslamReportBO;
import com.vnpt.media.rims.bean.GponBO;
import com.vnpt.media.rims.bean.GponPortDeviceReportBO;
import com.vnpt.media.rims.bean.GponPortTinhReportBO;
import com.vnpt.media.rims.bean.GponReportBO;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.L2SWPortDeviceReportBO;
import com.vnpt.media.rims.bean.L2SWPortTinhReportBO;
import com.vnpt.media.rims.bean.L2swReportBO;
import com.vnpt.media.rims.bean.ManEInfoBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.SwitchBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.AccessFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DongTbiFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.ManeFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = "/access")
public class AccessController {

    private static final Logger logger = LogManager.getLogger(AccessController.class);
    private static final String FORM_DSLAM = "broadband/access/formDsLam";
    private static final String FORM_SWITCH = "broadband/access/formSwitch";
    private static final String FORM_GPON = "broadband/access/formGpon";
    private static final String LIST = "broadband/access/list";
    private static final String REDIRECT = "redirect:/access/init";
    private static final String REPORT_L2SW = "broadband/report/th_chungloai_l2sw";
    private static final String REPORT_GPON = "broadband/report/th_chungloai_gpon";
    private static final String REPORT_DSLAM = "broadband/report/th_chungloai_dslam";
    private static final String REPORT_PortGponTinh = "broadband/report/as_portGponTinh";
    private static final String REPORT_PortGponDevice = "broadband/report/as_portGponDevice";
    private static final String REPORT_PortL2SWTinh = "broadband/report/as_portL2swTinh";
    private static final String REPORT_PortL2SWDevice = "broadband/report/as_portL2swDevice";
    private static final String REPORT_PortDSLAMTinh = "broadband/report/as_portDslamTinh";
    private static final String REPORT_PortDSLAMDevice = "broadband/report/as_portDslamDevice";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("dongTbis")
    public List findDongTbi() {
        ArrayList<DongTbiBO> list = null;
        try {
            list = DongTbiFacade.fc_find_all("", "ACCESS");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    //list khu vuc
    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        try {
            CategoriesFacade facade = new CategoriesFacade();
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            return facade.findAllKhuVuc(String.join(",", tinhManager));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //list tinh
    @RequestMapping(value = "/getTinhTp", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getTinhTp(@RequestParam(value = "khuVucId", required = false) String khuVucId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<TinhBO> list;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            list = facade.findTinhByKv(khuVucId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //list huyen
    @RequestMapping(value = "/getQuanHuyen", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getQuanHuyen(@RequestParam(value = "tinhTpId", required = false) String tinhTpId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<HuyenBO> list;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            if (tinhTpId == null || tinhTpId.trim().isEmpty()) {
                list = facade.findAllHuyen(tinhTpId);
            } else {
                list = facade.findAllHuyen(tinhTpId);
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (ServiceException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //
    @RequestMapping(value = "/getPhuongXa", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getPhuongXa(@RequestParam(value = "quanHuyenId", required = false) String quanHuyenId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<PhuongXaBO> list;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            if (quanHuyenId == null || quanHuyenId.trim().isEmpty()) {
                list = facade.findAllPhuongXa(quanHuyenId);
            } else {
                list = facade.findAllPhuongXa(quanHuyenId);
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (ServiceException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @ModelAttribute("tinhThanhPhoLst")
    public List findTinhThanhPho(HttpServletRequest request) {
        List<TinhBO> list = null;
        try {
            ManagerAdminFacade facade = new ManagerAdminFacade();
            list = facade.findAllTinh("");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String provinceId,
            @RequestParam(value = "quanHuyenId", required = false) String districtId,
            @RequestParam(value = "phuongXaId", required = false) String wardsId,
            ModelMap mm, HttpServletRequest request) {
        try {
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);

            int totalRows = AccessFacade.getInstance().getTotal("", code, name, typeId, khuvucId, provinceId, districtId, wardsId);

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
                return ("redirect:/access/init?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/access/init?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            objPage.setSubject("Quản lý access");
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }

            List<DsLamBO> list = (List<DsLamBO>) AccessFacade.getInstance().findAll(startRow, endRow, "", code, name, typeId,
                    khuvucId, provinceId, districtId, wardsId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("code", code);
            mm.put("name", name);
            mm.put("startRow", startRow);
            mm.put("khuvucId", khuvucId);
            mm.put("tinhTpId", provinceId);
            mm.put("quanHuyenId", districtId);
            mm.put("phuongXaId", wardsId);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }

    @RequestMapping(value = "/preAdd/{typeId}", method = RequestMethod.GET)
    public String preAdd(@PathVariable(value = "typeId") String typeId, ModelMap mm) {
        try {
            mm.put("btnName", "Thêm mới");
            switch (typeId) {
                case "4": {
                    DsLamBO access = new DsLamBO();
                    mm.addAttribute("access", access);
                    return FORM_DSLAM;
                }
                case "5": {
                    SwitchBO access = new SwitchBO();
                    mm.addAttribute("access", access);
                    return FORM_SWITCH;
                }
                case "6": {
                    GponBO access = new GponBO();
                    mm.addAttribute("access", access);
                    return FORM_GPON;
                }
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM_DSLAM;
    }

    @RequestMapping(value = "/preUpdate/{id}/{typeId}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") String id,
            @PathVariable(value = "typeId") String typeId, ModelMap mm) {
        try {
            mm.put("btnName", "Cập nhật");
//        mm.addAttribute("mane", mane);
            switch (typeId) {
                case "4": {
                    List<DsLamBO> list = (List<DsLamBO>) AccessFacade.getInstance().findAll(0, 10, id, "", "", typeId, null, null, null, null);
                    List<ManEInfoBO> listParent = null;
                    DsLamBO access = list.get(0);
                    if (access.getChaId() != null && !access.getChaId().isEmpty()) {
                        listParent = ManeFacade.findChaList(String.valueOf(access.getChaId()));
                    }
                    mm.addAttribute("access", access);
                    mm.addAttribute("list", listParent);
                    return FORM_DSLAM;

                }
                case "5": {
                    List<ManEInfoBO> listParent = null;
                    List<SwitchBO> list = (List<SwitchBO>) AccessFacade.getInstance().findAll(0, 10, id, "", "", typeId, null, null, null, null);
                    SwitchBO access = list.get(0);
                    if (access.getChaId() != null && !access.getChaId().isEmpty()) {
                        listParent = ManeFacade.findChaList(String.valueOf(access.getChaId()));
                    }
                    mm.addAttribute("access", access);
                    mm.addAttribute("list", listParent);

                    return FORM_SWITCH;

                }
                case "6": {
                    List<ManEInfoBO> listParent = null;
                    List<GponBO> list = (List<GponBO>) AccessFacade.getInstance().findAll(0, 10, id, "", "", typeId, null, null, null, null);
                    GponBO access = list.get(0);
                    if (access.getChaId() != null && !access.getChaId().isEmpty()) {
                        listParent = ManeFacade.findChaList(String.valueOf(access.getChaId()));
                    }
                    mm.addAttribute("access", access);
                    mm.addAttribute("list", listParent);

                    return FORM_GPON;

                }
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM_DSLAM;
    }

    @RequestMapping(value = "/updateDsLam", method = RequestMethod.POST)
    public String update(ModelMap mm, @ModelAttribute(value = "access") DsLamBO access,
            RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("access", access);
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            if (access.getId() == null || access.getId().trim().equals("")) {
                if (AccessFacade.getInstance().addDsLam(access, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_ADD_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_ADD_ERROR));
                }

            } else {
                if (AccessFacade.getInstance().updateDsLam(access, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_UPDATE_ERROR));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REDIRECT;
    }

    @RequestMapping(value = "/updateSwitch", method = RequestMethod.POST)
    public String updateSwitch(ModelMap mm, @ModelAttribute(value = "access") SwitchBO access,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("access", access);
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            if (access.getId() == null || access.getId().trim().equals("")) {
                if (AccessFacade.getInstance().addSwitch(access, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_ADD_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_ADD_ERROR));
                }

            } else {
                if (AccessFacade.getInstance().updateSwitch(access, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_UPDATE_ERROR));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REDIRECT;
    }

    @RequestMapping(value = "/updateGpon", method = RequestMethod.POST)
    public String updateGpon(ModelMap mm, @ModelAttribute(value = "access") GponBO access,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("access", access);
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);

            if (access.getId() == null || access.getId().trim().equals("")) {
                if (AccessFacade.getInstance().addGpon(access, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_ADD_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_ADD_ERROR));
                }

            } else {
                if (AccessFacade.getInstance().updateGpon(access, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_UPDATE_ERROR));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REDIRECT;
    }

    @RequestMapping(value = "/delete/{id}/{typeId}", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, ModelMap mm, @PathVariable(value = "id") String id,
            @PathVariable(value = "typeId") String typeId,
            Locale locale, RedirectAttributes attr) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//            AccessFacade.getInstance().delete(id, typeId, user.getId());
            ManeFacade.delete(id, String.valueOf(user.getId()));
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_DELETE_SUCCESS));
        } catch (Exception e) {
            logger.error(e, e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_DELETE_ERROR));
        }
        return REDIRECT;
    }

    // add tnode_node
    @RequestMapping(value = "/preTnodeNode/{id}", method = RequestMethod.GET)
    public String preTnodeNode(ModelMap mm, @PathVariable(value = "id") String id) {
//        ManEInfoBO mane = new ManEInfoBO();
//        mm.addAttribute("mane", mane);
//        mm.put("btnName", "Thêm mới");
        return null;
    }

//    trunglk_start
    @RequestMapping(value = "/preReportL2sw", method = RequestMethod.GET)
    public String preReportL2sw(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {
            List<L2swReportBO> datas = AccessFacade.reportL2sw(tinhTpId);
            mm.addAttribute("list", datas);
            mm.put("tinhTpId", tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return REPORT_L2SW;
    }

    @RequestMapping(value = "/reportThL2sw", method = RequestMethod.GET)
    public void reportThL2sw(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<L2swReportBO> datas = AccessFacade.reportL2sw(tinhTpId);
            File fileResult = writeTongHopL2sw(dataDirectory + "/ThongKeChungLoaiThietBiL2SW.xlsx", datas);
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
        }
    }

    private File writeTongHopL2sw(String fileTemplate, List<L2swReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "TongHopL2sw.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (L2swReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue("0".equals(it.getTen_tinh_tp()) ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(1);
                    cell.setCellValue("0".equals(it.getTong_tb()) ? "" : it.getTong_tb());

                    cell = row.createCell(2);
                    cell.setCellValue("0".equals(it.getAT_FS750()) ? "" : it.getAT_FS750());

                    cell = row.createCell(3);
                    cell.setCellValue("0".equals(it.getBATM_T5C()) ? "" : it.getBATM_T5C());

                    cell = row.createCell(4);
                    cell.setCellValue("0".equals(it.getC2960()) ? "" : it.getC2960());

                    cell = row.createCell(5);
                    cell.setCellValue("0".equals(it.getC3560()) ? "" : it.getC3560());

                    cell = row.createCell(6);
                    cell.setCellValue("0".equals(it.getCE500()) ? "" : it.getCE500());

                    cell = row.createCell(7);
                    cell.setCellValue("0".equals(it.getES_2226C()) ? "" : it.getES_2226C());

                    cell = row.createCell(8);
                    cell.setCellValue("0".equals(it.getISCOM2126()) ? "" : it.getISCOM2126());

                    cell = row.createCell(9);
                    cell.setCellValue("0".equals(it.getLINKSYS()) ? "" : it.getLINKSYS());

                    cell = row.createCell(10);
                    cell.setCellValue("0".equals(it.getLS62XX()) ? "" : it.getLS62XX());

                    cell = row.createCell(11);
                    cell.setCellValue("0".equals(it.getME340X()) ? "" : it.getME340X());

                    cell = row.createCell(12);
                    cell.setCellValue("0".equals(it.getMES3500_24F()) ? "" : it.getMES3500_24F());

                    cell = row.createCell(13);
                    cell.setCellValue("0".equals(it.getO62XX()) ? "" : it.getO62XX());

                    cell = row.createCell(14);
                    cell.setCellValue("0".equals(it.getO6424()) ? "" : it.getO6424());

                    cell = row.createCell(15);
                    cell.setCellValue("0".equals(it.getO6450()) ? "" : it.getO6450());

                    cell = row.createCell(16);
                    cell.setCellValue("0".equals(it.getO64XX()) ? "" : it.getO64XX());

                    cell = row.createCell(17);
                    cell.setCellValue("0".equals(it.getRAISECOM2828()) ? "" : it.getRAISECOM2828());

                    cell = row.createCell(18);
                    cell.setCellValue("0".equals(it.getRT2126()) ? "" : it.getRT2126());

                    cell = row.createCell(19);
                    cell.setCellValue("0".equals(it.getS2226_SFP()) ? "" : it.getS2226_SFP());

                    cell = row.createCell(20);
                    cell.setCellValue("0".equals(it.getS3328()) ? "" : it.getS3328());

                    cell = row.createCell(21);
                    cell.setCellValue("0".equals(it.getS4100()) ? "" : it.getS4100());

                    cell = row.createCell(22);
                    cell.setCellValue("0".equals(it.getS53XX()) ? "" : it.getS53XX());

                    cell = row.createCell(23);
                    cell.setCellValue("0".equals(it.getSF300()) ? "" : it.getSF300());

                    cell = row.createCell(24);
                    cell.setCellValue("0".equals(it.getTERRABIT()) ? "" : it.getTERRABIT());

                    cell = row.createCell(25);
                    cell.setCellValue("0".equals(it.getV6328()) ? "" : it.getV6328());

                    cell = row.createCell(26);
                    cell.setCellValue("0".equals(it.getVFT22XX()) ? "" : it.getVFT22XX());

                    cell = row.createCell(27);
                    cell.setCellValue("0".equals(it.getZTE3928()) ? "" : it.getZTE3928());

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

            }

        }

        return result;
    }

    @RequestMapping(value = "/preReportGpon", method = RequestMethod.GET)
    public String preReportGpon(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {
            List<GponReportBO> datas = AccessFacade.reportGpon(tinhTpId);
            mm.addAttribute("list", datas);
            mm.put("tinhTpId", tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_GPON;
    }

    @RequestMapping(value = "/reportThGpon", method = RequestMethod.GET)
    public void reportThGpon(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<GponReportBO> datas = AccessFacade.reportGpon(tinhTpId);
            File fileResult = writeTongHopGpon(dataDirectory + "/ThongKeChungLoaiThietBiGpon.xlsx", datas);
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
        }
    }

    private File writeTongHopGpon(String fileTemplate, List<GponReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "TongHopGpon.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (GponReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue("0".equals(it.getTen_tinh_tp()) ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(1);
                    cell.setCellValue("0".equals(it.getTong_tb()) ? "" : it.getTong_tb());

                    cell = row.createCell(2);
                    cell.setCellValue("0".equals(it.getISAM7360()) ? "" : it.getISAM7360());

                    cell = row.createCell(3);
                    cell.setCellValue("0".equals(it.getMA5608()) ? "" : it.getMA5608());

                    cell = row.createCell(4);
                    cell.setCellValue("0".equals(it.getZTEC320()) ? "" : it.getZTEC320());

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

            }

        }

        return result;
    }

    @RequestMapping(value = "/preReportDslam", method = RequestMethod.GET)
    public String preReportDslam(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {
            List<DslamReportBO> datas = AccessFacade.reportDsLam(tinhTpId);
            mm.addAttribute("list", datas);
            mm.put("tinhTpId", tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_DSLAM;
    }

    @RequestMapping(value = "/reportThDslam", method = RequestMethod.GET)
    public void reportThDslam(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<DslamReportBO> datas = AccessFacade.reportDsLam(tinhTpId);
            File fileResult = writeTongHopDslam(dataDirectory + "/ThongKeChungLoaiThietBiDSLAM.xlsx", datas);
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
        }
    }

    private File writeTongHopDslam(String fileTemplate, List<DslamReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "TongHopDslam.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (DslamReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue("0".equals(it.getTen_tinh_tp()) ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(1);
                    cell.setCellValue("0".equals(it.getTong_tb()) ? "" : it.getTong_tb());

                    cell = row.createCell(2);
                    cell.setCellValue("0".equals(it.getHIX5630()) ? "" : it.getHIX5630());

                    cell = row.createCell(3);
                    cell.setCellValue("0".equals(it.getHIX5635()) ? "" : it.getHIX5635());

                    cell = row.createCell(4);
                    cell.setCellValue("0".equals(it.getISAM73XX()) ? "" : it.getISAM73XX());

                    cell = row.createCell(5);
                    cell.setCellValue("0".equals(it.getLS1540IP()) ? "" : it.getLS1540IP());

                    cell = row.createCell(6);
                    cell.setCellValue("0".equals(it.getMA5100()) ? "" : it.getMA5100());

                    cell = row.createCell(7);
                    cell.setCellValue("0".equals(it.getMA56XX()) ? "" : it.getMA56XX());

                    cell = row.createCell(8);
                    cell.setCellValue("0".equals(it.getS9806H()) ? "" : it.getS9806H());

                    cell = row.createCell(9);
                    cell.setCellValue("0".equals(it.getMA5616()) ? "" : it.getMA5616());

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
            }

        }

        return result;
    }

    @RequestMapping(value = "/prePortGponTinh", method = RequestMethod.GET)
    public String prePortGponTinh(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {
            List<GponPortTinhReportBO> datas = AccessFacade.reportGponPortTinh(tinhTpId);
            mm.addAttribute("list", datas);
            mm.put("tinhTpId", tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_PortGponTinh;
    }

    @RequestMapping(value = "/reportPortGponTinh", method = RequestMethod.GET)
    public void reportPortGponTinh(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<GponPortTinhReportBO> datas = AccessFacade.reportGponPortTinh(tinhTpId);
            File fileResult = writePortGponTinh(dataDirectory + "/HieuSuatPortGponTinh.xlsx", datas);
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
        }
    }

    private File writePortGponTinh(String fileTemplate, List<GponPortTinhReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "HieuSuatPortGponTinh.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (GponPortTinhReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue("0".equals(it.getTen_tinh_tp()) ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(1);
                    cell.setCellValue("0".equals(it.getSo_olt_lap_dat()) ? "" : it.getSo_olt_lap_dat());

                    cell = row.createCell(2);
                    cell.setCellValue("0".equals(it.getSo_card_pon_lap_dat()) ? "" : it.getSo_card_pon_lap_dat());

                    cell = row.createCell(3);
                    cell.setCellValue("0".equals(it.getSo_card_pon_mp_rong_td()) ? "" : it.getSo_card_pon_mp_rong_td());

                    cell = row.createCell(4);
                    cell.setCellValue("0".equals(it.getSo_cong_pon_toi_da()) ? "" : it.getSo_cong_pon_toi_da());

                    cell = row.createCell(5);
                    cell.setCellValue("0".equals(it.getSo_pon_da_lap_dat()) ? "" : it.getSo_pon_da_lap_dat());

                    cell = row.createCell(6);
                    cell.setCellValue("0".equals(it.getSo_pon_da_su_dung()) ? "" : it.getSo_pon_da_su_dung());

                    cell = row.createCell(7);
                    cell.setCellValue("0".equals(it.getSo_ont_co_the_lap_dat()) ? "" : it.getSo_ont_co_the_lap_dat());

                    cell = row.createCell(8);
                    cell.setCellValue("0".equals(it.getSo_ont_dang_hoat_dong()) ? "" : it.getSo_ont_dang_hoat_dong());

                    cell = row.createCell(9);
                    cell.setCellValue("0".equals(it.getHieu_suat_su_dung()) ? "" : it.getHieu_suat_su_dung());

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
            }

        }

        return result;
    }

    @RequestMapping(value = "/prePortGponDevice", method = RequestMethod.GET)
    public String prePortGponDevice(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {
            List<GponPortDeviceReportBO> datas = AccessFacade.reportGponPortDevice(tinhTpId);
            mm.addAttribute("list", datas);
            mm.put("tinhTpId", tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_PortGponDevice;
    }

    @RequestMapping(value = "/reportPortGponDevice", method = RequestMethod.GET)
    public void reportPortGponDevice(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<GponPortDeviceReportBO> datas = AccessFacade.reportGponPortDevice(tinhTpId);
            File fileResult = writePortGponDevice(dataDirectory + "/HieuSuatPortGponThietbi.xlsx", datas);
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
        }
    }

    private File writePortGponDevice(String fileTemplate, List<GponPortDeviceReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "HieuSuatPortGponThietbi.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (GponPortDeviceReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue("0".equals(it.getTen_tinh_tp()) ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(1);
                    cell.setCellValue("0".equals(it.getTenDonvi()) ? "" : it.getTenDonvi());

                    cell = row.createCell(2);
                    cell.setCellValue("0".equals(it.getSo_olt_lap_dat()) ? "" : it.getSo_olt_lap_dat());

                    cell = row.createCell(3);
                    cell.setCellValue("0".equals(it.getSo_card_pon_lap_dat()) ? "" : it.getSo_card_pon_lap_dat());

                    cell = row.createCell(4);
                    cell.setCellValue("0".equals(it.getSo_card_pon_mp_rong_td()) ? "" : it.getSo_card_pon_mp_rong_td());

                    cell = row.createCell(5);
                    cell.setCellValue("0".equals(it.getSo_cong_pon_toi_da()) ? "" : it.getSo_cong_pon_toi_da());

                    cell = row.createCell(6);
                    cell.setCellValue("0".equals(it.getSo_pon_da_lap_dat()) ? "" : it.getSo_pon_da_lap_dat());

                    cell = row.createCell(7);
                    cell.setCellValue("0".equals(it.getSo_pon_da_su_dung()) ? "" : it.getSo_pon_da_su_dung());

                    cell = row.createCell(8);
                    cell.setCellValue("0".equals(it.getSo_ont_co_the_lap_dat()) ? "" : it.getSo_ont_co_the_lap_dat());

                    cell = row.createCell(9);
                    cell.setCellValue("0".equals(it.getSo_ont_dang_hoat_dong()) ? "" : it.getSo_ont_dang_hoat_dong());

                    cell = row.createCell(10);
                    cell.setCellValue("0".equals(it.getHieu_suat_su_dung()) ? "" : it.getHieu_suat_su_dung());

                    cell = row.createCell(11);
                    cell.setCellValue("0".equals(it.getDiachi()) ? "" : it.getDiachi());

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

    @RequestMapping(value = "/prePortL2SWTinh", method = RequestMethod.GET)
    public String prePortL2SWTinh(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {
            List<L2SWPortTinhReportBO> datas = AccessFacade.reportL2swPortTinh(tinhTpId);
            mm.addAttribute("list", datas);
            mm.put("tinhTpId", tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_PortL2SWTinh;
    }

    @RequestMapping(value = "/reportPortL2SWTinh", method = RequestMethod.GET)
    public void reportPortL2SWTinh(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<L2SWPortTinhReportBO> datas = AccessFacade.reportL2swPortTinh(tinhTpId);
            File fileResult = writePortL2SWTinh(dataDirectory + "/HieuSuatPortL2swTinh.xlsx", datas);
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
        }
    }

    private File writePortL2SWTinh(String fileTemplate, List<L2SWPortTinhReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "HieuSuatPortL2swTinh.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (L2SWPortTinhReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue("0".equals(it.getTen_tinh_tp()) ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(1);
                    cell.setCellValue("0".equals(it.getTong_tb()) ? "" : it.getTong_tb());

                    cell = row.createCell(2);
                    cell.setCellValue("0".equals(it.getPortL2SWConnect()) ? "" : it.getPortL2SWConnect());

                    cell = row.createCell(3);
                    cell.setCellValue("0".equals(it.getPortL2SWUsed()) ? "" : it.getPortL2SWUsed());

                    cell = row.createCell(4);
                    cell.setCellValue("0".equals(it.getPerfomanceUsed()) ? "" : it.getPerfomanceUsed());

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

    @RequestMapping(value = "/prePortL2SWDevice", method = RequestMethod.GET)
    public String prePortL2SWDevice(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {
            List<L2SWPortDeviceReportBO> datas = AccessFacade.reportL2swPortDevice(tinhTpId);
            mm.addAttribute("list", datas);
            mm.put("tinhTpId", tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_PortL2SWDevice;
    }

    @RequestMapping(value = "/reportPortL2SWDevice", method = RequestMethod.GET)
    public void reportPortL2SWDevice(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<L2SWPortDeviceReportBO> datas = AccessFacade.reportL2swPortDevice(tinhTpId);
            File fileResult = writePortL2SWDevice(dataDirectory + "/HieuSuatPortL2swThietbi.xlsx", datas);
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
        }
    }

    private File writePortL2SWDevice(String fileTemplate, List<L2SWPortDeviceReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "HieuSuatPortL2swThietbi.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (L2SWPortDeviceReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue("0".equals(it.getTen_tinh_tp()) ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(1);
                    cell.setCellValue("0".equals(it.getTenDonvi()) ? "" : it.getTenDonvi());

                    cell = row.createCell(2);
                    cell.setCellValue("0".equals(it.getTong_tb()) ? "" : it.getTong_tb());

                    cell = row.createCell(3);
                    cell.setCellValue("0".equals(it.getPortL2SWConnect()) ? "" : it.getPortL2SWConnect());

                    cell = row.createCell(4);
                    cell.setCellValue("0".equals(it.getPortL2SWUsed()) ? "" : it.getPortL2SWUsed());

                    cell = row.createCell(5);
                    cell.setCellValue("0".equals(it.getPerfomanceUsed()) ? "" : it.getPerfomanceUsed());

                    cell = row.createCell(6);
                    cell.setCellValue("0".equals(it.getDiachi()) ? "" : it.getDiachi());

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

    @RequestMapping(value = "/prePortDSLAMTinh", method = RequestMethod.GET)
    public String prePortDSLAMTinh(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {
            List<DslamPortTinhReportBO> datas = AccessFacade.reportDsLamPortTinh(tinhTpId);
            mm.addAttribute("list", datas);
            mm.put("tinhTpId", tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_PortDSLAMTinh;
    }

    @RequestMapping(value = "/reportPortDSLAMTinh", method = RequestMethod.GET)
    public void reportPortDSLAMTinh(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<DslamPortTinhReportBO> datas = AccessFacade.reportDsLamPortTinh(tinhTpId);
            File fileResult = writePortDSLAMTinh(dataDirectory + "/HieuSuatPortDslamTinh.xlsx", datas);
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
        }
    }

    private File writePortDSLAMTinh(String fileTemplate, List<DslamPortTinhReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "HieuSuatPortDslamTinh.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (DslamPortTinhReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue("0".equals(it.getTen_tinh_tp()) ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(1);
                    cell.setCellValue("0".equals(it.getTong_tb()) ? "" : it.getTong_tb());

                    cell = row.createCell(2);
                    cell.setCellValue("0".equals(it.getPortDslamConnect()) ? "" : it.getPortDslamConnect());

                    cell = row.createCell(3);
                    cell.setCellValue("0".equals(it.getPortDslamUsed()) ? "" : it.getPortDslamUsed());

                    cell = row.createCell(4);
                    cell.setCellValue("0".equals(it.getPerfomanceUsed()) ? "" : it.getPerfomanceUsed());

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

    @RequestMapping(value = "/prePortDSLAMDevice", method = RequestMethod.GET)
    public String prePortDSLAMDevice(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        try {
            List<DslamPortDeviceReportBO> datas = AccessFacade.reportDsLamPortDevice(tinhTpId);
            mm.addAttribute("list", datas);
            mm.put("tinhTpId", tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_PortDSLAMDevice;
    }

    @RequestMapping(value = "/reportPortDSLAMDevice", method = RequestMethod.GET)
    public void reportPortDSLAMDevice(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<DslamPortDeviceReportBO> datas = AccessFacade.reportDsLamPortDevice(tinhTpId);
            File fileResult = writePortDSLAMDevice(dataDirectory + "/HieuSuatPortDslamTthietbi.xlsx", datas);
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
        }
    }

    private File writePortDSLAMDevice(String fileTemplate, List<DslamPortDeviceReportBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "HieuSuatPortDslamTthietbi.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (DslamPortDeviceReportBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getTen_tinh_tp() == "0" ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getTenDonvi() == "0" ? "" : it.getTenDonvi());

                    cell = row.createCell(2);
                    cell.setCellValue(it.getTong_tb() == "0" ? "" : it.getTong_tb());

                    cell = row.createCell(3);
                    cell.setCellValue(it.getPortDslamConnect() == "0" ? "" : it.getPortDslamConnect());

                    cell = row.createCell(4);
                    cell.setCellValue(it.getPortDslamUsed() == "0" ? "" : it.getPortDslamUsed());

                    cell = row.createCell(5);
                    cell.setCellValue(it.getPerfomanceUsed() == "0" ? "" : it.getPerfomanceUsed());

                    cell = row.createCell(6);
                    cell.setCellValue(it.getDiachi() == "0" ? "" : it.getDiachi());

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
//    trunglk_end
}
