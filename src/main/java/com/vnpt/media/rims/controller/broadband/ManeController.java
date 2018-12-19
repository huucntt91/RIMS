/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.DongTbiBO;
import com.vnpt.media.rims.bean.EthernetBcBO;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.KhuvucBO;
import com.vnpt.media.rims.bean.ManEInfoBO;
import com.vnpt.media.rims.bean.ModuleQuangBcBO;
import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.TnodeNodeBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DongTbiFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.ManeFacade;
import com.vnpt.media.rims.facade.TnodeFacade;
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
import javax.validation.Valid;
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
@RequestMapping(value = "/mane")
public class ManeController {

    private static Logger logger = LogManager.getLogger(ManeController.class);
    private static final String FORM = "broadband/mane/form";
    private static final String LIST = "broadband/mane/list";
    private static final String REPORT = "broadband/report/mane_module_quang_theotb";
    private static final String REPORT_TP = "broadband/report/mane_module_quang_theotp";
    private static final String ETHERNET_REPORT_TP = "broadband/report/mane_ethernet_theotp";
    private static final String ETHERNET_REPORT_TB = "broadband/report/mane_ethernet_theotb";
    private static final String REDIRECT = "redirect:/mane/init";
    private static final String TNODE_NODE = "broadband/tnode/tnodeNode";
    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("dongTbis")
    public List findDongTbi() {
        ArrayList<DongTbiBO> list = null;
        try {
            list = DongTbiFacade.fc_find_all("", "MANE");
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

    @RequestMapping(value = "/getArea", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getArea(ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<KhuvucBO> list;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            list = facade.findAllKhuVuc(String.join(",", tinhManager));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @ModelAttribute("loaitruyendanList")
    public List findAllLoaiTruyenDan() {
        try {
            CategoriesFacade facade = new CategoriesFacade();
            return facade.findAllTruyenDan("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //list tinh
    @RequestMapping(value = "/getTinhTp", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getTinhTp(@RequestParam(value = "khuVucId", required = false) String khuVucId, @RequestParam(value = "getAll", required = false) String getAll, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<TinhBO> list;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            list = facade.findTinhByKv(khuVucId);
            List<TinhBO> temp = new ArrayList<>();
            if(getAll != null && getAll.equals("1")){
                temp = list;
            }
            else{
                String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
                if (tinhManager != null && tinhManager.length > 0) {
                    for (String tinh : tinhManager) {
                        for (TinhBO item : list) {
                            if (String.valueOf(item.getTinhTpId()).equals(tinh)) {
                                temp.add(item);
                            }
                        }
                    }
                } else {
                    temp = list;
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(temp);
        } catch (Exception e) {
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
        List<PhuongXaBO> list = null;
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
            int totalRows = ManeFacade.countSearch(code, name, typeId, khuvucId, provinceId, districtId, wardsId);
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
                return ("redirect:/mane/init?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/mane/init?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            objPage.setSubject("Quản lý Mane");
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<ManEInfoBO> list = ManeFacade.findAll(startRow, endRow, "", code, name, typeId, khuvucId,
                    provinceId, districtId, wardsId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("code", code);
            mm.put("name", name);
            mm.put("khuvucId", khuvucId);
            mm.put("tinhTpId", provinceId);
            mm.put("quanHuyenId", districtId);
            mm.put("phuongXaId", wardsId);
            mm.put("startRow", startRow);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) {
        try {
            ManEInfoBO mane = new ManEInfoBO();
            mm.addAttribute("mane", mane);
            mm.put("btnName", "Thêm mới");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM;
    }

    @RequestMapping(value = "/preAddTp/{type}", method = RequestMethod.GET)
    public String preAddTp(ModelMap mm, @PathVariable(value = "type") String type) {
        try {
            ManEInfoBO mane = new ManEInfoBO();
            mm.addAttribute("mane", mane);
            mm.put("btnName", "Thêm mới");
            mm.put("typeId", type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM;
    }

    @RequestMapping(value = "/preUpdate/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") String id, ModelMap mm) {
        List<ManEInfoBO> list = null;
        ManEInfoBO mane = null;
        try {

            mane = ManeFacade.findById(id);
            try {
                if (mane.getTLIST_NODE_CHA_ID() != null && !mane.getTLIST_NODE_CHA_ID().isEmpty()) {
                    list = ManeFacade.findChaList(mane.getTLIST_NODE_CHA_ID());
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            mm.addAttribute("mane", mane);
            mm.put("btnName", "Cập nhật");
            mm.put("list", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap mm,
            @Valid @ModelAttribute(value = "model") ManEInfoBO model,
            BindingResult bindingResult,
            RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("mane", model);
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            if (model.getTNODE_ID() == null) {
                if (ManeFacade.addMane(model, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_ADD_SUCCESS));
                    return REDIRECT;
                } else {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_ADD_ERROR));
                    mm.put("btnName", "Thêm mới");
                    return FORM;
                }

            } else {
                if (ManeFacade.updateMane(model, user.getId())) {
                    attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                    return REDIRECT;
                } else {
                    List<ManEInfoBO> list = null;
                    if (model.getTLIST_NODE_CHA_ID() != null && !model.getTLIST_NODE_CHA_ID().isEmpty()) {
                        list = ManeFacade.findChaList(model.getTLIST_NODE_CHA_ID());
                    }
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_UPDATE_ERROR));
                    mm.put("btnName", "Cập nhật");
                    mm.put("list", list);
                    return FORM;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, ModelMap mm,
            @PathVariable(value = "id") String id,
            Locale locale,
            RedirectAttributes attr
    ) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManeFacade.delete(id, String.valueOf(user.getId()));
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_DELETE_SUCCESS));
        } catch (Exception e) {
            logger.error(e, e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_DELETE_ERROR));
        }
        return REDIRECT;
    }

//    @ModelAttribute("tinhThanhPhoLst")
//    public List findTinhThanhPho(HttpServletRequest request) {
//        List<TinhBO> list = null;
//        try {
//            ManagerAdminFacade facade = new ManagerAdminFacade();
//            list = facade.findAllTinh("");
//        } catch (ServiceException e) {
//            logger.error(e.getMessage(), e);
//        }
//        return list;
//    }
    @RequestMapping(value = "/findNodes", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String findNodes(HttpServletRequest request,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        List<NodeBO> list;
        try {
            if (tinhTpId != null && !tinhTpId.isEmpty()) {
                TnodeFacade facade = new TnodeFacade();
                list = facade.findNode(typeId, tinhTpId);
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(list);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/preTnodeNode/{id}", method = RequestMethod.GET)
    public String preTnodeNode(@PathVariable(value = "id") String id, ModelMap mm
    ) {
        try {
            mm.put("tnode_id", id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return TNODE_NODE;
    }

    @RequestMapping(value = "/searchTnodeNode", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String searchTnodeNode(HttpServletRequest request,
            @RequestParam(value = "tnodeId", required = false) String tnodeId,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        List<TnodeNodeBO> list;
        try {
            TnodeFacade facade = new TnodeFacade();
            list = facade.findTNodeNode(tnodeId, typeId, tinhTpId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/addTnodeNode", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String addTnodeNode(HttpServletRequest request,
            @RequestParam(value = "tnodeId", required = false) String tnodeId,
            @RequestParam(value = "nodes", required = false) String nodes,
            @RequestParam(value = "portIn", required = false) String portIn,
            @RequestParam(value = "portOut", required = false) String portOut,
            @RequestParam(value = "truyenDanId", required = false) String truyenDanId
    ) {
        try {
            TnodeFacade facade = new TnodeFacade();

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(facade.addTNodeNode(tnodeId, nodes, portIn, portOut, truyenDanId));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/deleteTnodeNode", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String deleteTnodeNode(HttpServletRequest request,
            @RequestParam(value = "id", required = false) String id
    ) {
        try {
            TnodeFacade facade = new TnodeFacade();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(facade.deleteTNodeNode(id));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //report
    //
    @RequestMapping(value = "/preReport", method = RequestMethod.GET)
    public String preReport(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    ) {
        List<TinhBO> tinhThanhPhoLst;
        try {
            ManagerAdminFacade facade = new ManagerAdminFacade();
            tinhThanhPhoLst = facade.findAllTinh("");
            typeId = "1,2,3";
            List<ModuleQuangBcBO> datas = ManeFacade.report(tinhTpId, typeId);
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = 0;
            if (datas != null) {
                totalRows = datas.size();
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
                return ("redirect:/mane/preReport?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/mane/preReport?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<ModuleQuangBcBO> list = ManeFacade.find_module_quangtb(startRow, endRow, tinhTpId, typeId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("tinhTpId", tinhTpId);
            mm.put("startRow", startRow);
            mm.put("tinhThanhPhoLst", tinhThanhPhoLst);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT;
    }

    @RequestMapping(value = "/reportModuleQuangTheoTb", method = RequestMethod.GET)
    public void reportModuleQuangTheoTb(HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            String tinhTpId = request.getParameter("tinhTpId");
            String typeId = typeId = "1,2,3";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<ModuleQuangBcBO> datas = ManeFacade.report(tinhTpId, typeId);
            File fileResult = writeModuleQuangTheoTb(dataDirectory + "/ThongKeModuleQuangTheoTB.xlsx", datas);
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

    private File writeModuleQuangTheoTb(String fileTemplate, List<ModuleQuangBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("ThongKeModuleQuang.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (ModuleQuangBcBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getCode() == null ? "" : it.getCode());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getIp() == null ? "" : it.getIp());

                    cell = row.createCell(2);
                    cell.setCellValue(it.getBaseEx() == 0 ? "" : String.valueOf(it.getBaseEx()));

                    cell = row.createCell(3);
                    cell.setCellValue(it.getNotUseBaseEx() == 0 ? "" : String.valueOf(it.getNotUseBaseEx()));

                    cell = row.createCell(4);
                    cell.setCellValue(it.getBaseLx() == 0 ? "" : String.valueOf(it.getBaseLx()));

                    cell = row.createCell(5);
                    cell.setCellValue(it.getNotUseBaseLx() == 0 ? "" : String.valueOf(it.getNotUseBaseLx()));

                    cell = row.createCell(6);
                    cell.setCellValue(it.getBaseZx() == 0 ? "" : String.valueOf(it.getBaseZx()));

                    cell = row.createCell(7);
                    cell.setCellValue(it.getNotUseBaseZx() == 0 ? "" : String.valueOf(it.getNotUseBaseZx()));

                    cell = row.createCell(8);
                    cell.setCellValue(it.getSfp1G() == 0 ? "" : String.valueOf(it.getSfp1G()));

                    cell = row.createCell(9);
                    cell.setCellValue(it.getBaseEr() == 0 ? "" : String.valueOf(it.getBaseEr()));

                    cell = row.createCell(10);
                    cell.setCellValue(it.getNotUseBaseEr() == 0 ? "" : String.valueOf(it.getNotUseBaseEr()));

                    cell = row.createCell(11);
                    cell.setCellValue(it.getBaseLr() == 0 ? "" : String.valueOf(it.getBaseLr()));

                    cell = row.createCell(12);
                    cell.setCellValue(it.getNotUseBaseLr() == 0 ? "" : String.valueOf(it.getNotUseBaseLr()));

                    cell = row.createCell(13);
                    cell.setCellValue(it.getBaseZr() == 0 ? "" : String.valueOf(it.getBaseZr()));

                    cell = row.createCell(14);
                    cell.setCellValue(it.getNotUseBaseZr() == 0 ? "" : String.valueOf(it.getNotUseBaseZr()));

                    cell = row.createCell(15);
                    cell.setCellValue(it.getSfp10G() == 0 ? "" : String.valueOf(it.getSfp10G()));

                    cell = row.createCell(16);
                    cell.setCellValue(it.getOther() == 0 ? "" : String.valueOf(it.getOther()));

                    cell = row.createCell(17);
                    cell.setCellValue(it.getNotUseOther() == 0 ? "" : String.valueOf(it.getNotUseOther()));

                    cell = row.createCell(18);
                    cell.setCellValue(it.getOtherTotal() == 0 ? "" : String.valueOf(it.getOtherTotal()));

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

    //module quang theo tinh t/p
    @RequestMapping(value = "/preReportTp", method = RequestMethod.GET)
    public String preReportTp(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        List<TinhBO> tinhThanhPhoLst;
        try {
            ManagerAdminFacade facade = new ManagerAdminFacade();
            tinhThanhPhoLst = facade.findAllTinh("");
            typeId = "1,2,3";
            List<ModuleQuangBcBO> datas = ManeFacade.reportTp(typeId, tinhTpId);
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = 0;
            if (datas != null) {
                totalRows = datas.size();
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
                return ("redirect:/mane/preReportTp?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/mane/preReportTp?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<ModuleQuangBcBO> list = ManeFacade.find_module_quangtp(startRow, endRow, tinhTpId, typeId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("tinhTpId", tinhTpId);
            mm.put("startRow", startRow);
            mm.put("tinhThanhPhoLst", tinhThanhPhoLst);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return REPORT_TP;
    }

    @RequestMapping(value = "/reportTp", method = RequestMethod.GET)
    public void reportTp(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String typeId = typeId = "1,2,3";
            String tinhTpId = request.getParameter("tinhTpId");

            List<ModuleQuangBcBO> datas = ManeFacade.reportTp(typeId, tinhTpId);
            File fileResult = writeModuleQuangTheoTP(dataDirectory + "/ThongKeModuleQuangTheoTP.xlsx", datas);
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

    private File writeModuleQuangTheoTP(String fileTemplate, List<ModuleQuangBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("ThongKeModuleQuangTP.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                for (ModuleQuangBcBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getTenTinhTP() == null ? "" : it.getTenTinhTP());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getBaseEx() == 0 ? "" : String.valueOf(it.getBaseEx()));

                    cell = row.createCell(2);
                    cell.setCellValue(it.getNotUseBaseEx() == 0 ? "" : String.valueOf(it.getNotUseBaseEx()));

                    cell = row.createCell(3);
                    cell.setCellValue(it.getBaseLx() == 0 ? "" : String.valueOf(it.getBaseLx()));

                    cell = row.createCell(4);
                    cell.setCellValue(it.getNotUseBaseLx() == 0 ? "" : String.valueOf(it.getNotUseBaseLx()));

                    cell = row.createCell(5);
                    cell.setCellValue(it.getBaseZx() == 0 ? "" : String.valueOf(it.getBaseZx()));

                    cell = row.createCell(6);
                    cell.setCellValue(it.getNotUseBaseZx() == 0 ? "" : String.valueOf(it.getNotUseBaseZx()));

                    cell = row.createCell(7);
                    cell.setCellValue(it.getSfp1G() == 0 ? "" : String.valueOf(it.getSfp1G()));

                    cell = row.createCell(8);
                    cell.setCellValue(it.getBaseEr() == 0 ? "" : String.valueOf(it.getBaseEr()));

                    cell = row.createCell(9);
                    cell.setCellValue(it.getNotUseBaseEr() == 0 ? "" : String.valueOf(it.getNotUseBaseEr()));

                    cell = row.createCell(10);
                    cell.setCellValue(it.getBaseLr() == 0 ? "" : String.valueOf(it.getBaseLr()));

                    cell = row.createCell(11);
                    cell.setCellValue(it.getNotUseBaseLr() == 0 ? "" : String.valueOf(it.getNotUseBaseLr()));

                    cell = row.createCell(12);
                    cell.setCellValue(it.getBaseZr() == 0 ? "" : String.valueOf(it.getBaseZr()));

                    cell = row.createCell(13);
                    cell.setCellValue(it.getNotUseBaseZr() == 0 ? "" : String.valueOf(it.getNotUseBaseZr()));

                    cell = row.createCell(14);
                    cell.setCellValue(it.getSfp10G() == 0 ? "" : String.valueOf(it.getSfp10G()));

                    cell = row.createCell(15);
                    cell.setCellValue(it.getOther() == 0 ? "" : String.valueOf(it.getOther()));

                    cell = row.createCell(16);
                    cell.setCellValue(it.getNotUseOther() == 0 ? "" : String.valueOf(it.getNotUseOther()));

                    cell = row.createCell(17);
                    cell.setCellValue(it.getOtherTotal() == 0 ? "" : String.valueOf(it.getOtherTotal()));
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
//ETHERNET_REPORT_TP

    @RequestMapping(value = "/preEthernetTp", method = RequestMethod.GET)
    public String preEthernetTp(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        List<TinhBO> tinhThanhPhoLst;
        try {
            ManagerAdminFacade facade = new ManagerAdminFacade();
            tinhThanhPhoLst = facade.findAllTinh("");
            typeId = "1,2,3";
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = ManeFacade.countEthernetTp(tinhTpId, typeId);
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
                return ("redirect:/mane/preEthernetTp?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/mane/preEthernetTp?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<EthernetBcBO> list = ManeFacade.findEthernetTp(startRow, endRow, tinhTpId, typeId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("tinhTpId", tinhTpId);
            mm.put("startRow", startRow);
            mm.put("tinhThanhPhoLst", tinhThanhPhoLst);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ETHERNET_REPORT_TP;
    }

    @RequestMapping(value = "/ethernetTp", method = RequestMethod.GET)
    public void ethernetTp(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {
            typeId = "1,2,3";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<EthernetBcBO> datas = ManeFacade.ethernetTp(tinhTpId, typeId);

            File fileResult = writeEthernetTP(dataDirectory + "/ThongKeEthernetTP.xlsx", datas);
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

    private File writeEthernetTP(String fileTemplate, List<EthernetBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("ThongKeEthernetTP.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (EthernetBcBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getTenTinhTp() == null ? "" : it.getTenTinhTp());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getPort_1g() == 0 ? "" : String.valueOf(it.getPort_1g()));

                    cell = row.createCell(2);
                    cell.setCellValue(it.getUse1G() == 0 ? "" : String.valueOf(it.getUse1G()));

                    cell = row.createCell(3);
                    cell.setCellValue(it.getNotUse1G() == 0 ? "" : String.valueOf(it.getNotUse1G()));

                    cell = row.createCell(4);
                    cell.setCellValue(it.getSfp1g() == 0 ? "" : String.valueOf(it.getSfp1g()));

                    cell = row.createCell(5);
                    cell.setCellValue(it.getUseSfp1G() == 0 ? "" : String.valueOf(it.getUseSfp1G()));

                    cell = row.createCell(6);
                    cell.setCellValue(it.getNotUseSfp1G() == 0 ? "" : String.valueOf(it.getNotUseSfp1G()));

                    cell = row.createCell(7);
                    cell.setCellValue(it.getPort_10g() == 0 ? "" : String.valueOf(it.getPort_10g()));

                    cell = row.createCell(8);
                    cell.setCellValue(it.getUse10G() == 0 ? "" : String.valueOf(it.getUse10G()));

                    cell = row.createCell(9);
                    cell.setCellValue(it.getNotUse10G() == 0 ? "" : String.valueOf(it.getNotUse10G()));

                    cell = row.createCell(10);
                    cell.setCellValue(it.getSfp10g() == 0 ? "" : String.valueOf(it.getSfp10g()));

                    cell = row.createCell(11);
                    cell.setCellValue(it.getUseSfp10G() == 0 ? "" : String.valueOf(it.getUseSfp10G()));

                    cell = row.createCell(12);
                    cell.setCellValue(it.getNotUseSfp10G() == 0 ? "" : String.valueOf(it.getNotUseSfp10G()));
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

    @RequestMapping(value = "/preEthernetTb", method = RequestMethod.GET)
    public String preEthernetTb(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        List<TinhBO> tinhThanhPhoLst;
        try {
            ManagerAdminFacade facade = new ManagerAdminFacade();
            tinhThanhPhoLst = facade.findAllTinh("");
            typeId = "1,2,3";
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = ManeFacade.countEthernetTb(tinhTpId, typeId);
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
                return ("redirect:/mane/preEthernetTb?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/mane/preEthernetTb?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<EthernetBcBO> list = ManeFacade.findEthernetTb(startRow, endRow, tinhTpId, typeId);
            mm.addAttribute("list", list);
            mm.put("typeId", typeId);
            mm.put("tinhTpId", tinhTpId);
            mm.put("startRow", startRow);
            mm.put("tinhThanhPhoLst", tinhThanhPhoLst);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ETHERNET_REPORT_TB;
    }

    @RequestMapping(value = "/ethernetTb", method = RequestMethod.GET)
    public void ethernetTb(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        typeId = "1,2,3";
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<EthernetBcBO> datas = ManeFacade.ethernetTb(tinhTpId, typeId);

            File fileResult = writeEthernetTB(dataDirectory + "/ThongKeEthernetTB.xlsx", datas);
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

    private File writeEthernetTB(String fileTemplate, List<EthernetBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("ThongKeEthernetTB.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (EthernetBcBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getTenTinhTp() == null ? "" : it.getTenTinhTp());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getCode() == null ? "" : it.getCode());

                    cell = row.createCell(2);
                    cell.setCellValue(it.getIp() == null ? "" : it.getIp());

                    cell = row.createCell(3);
                    cell.setCellValue(it.getPort_1g() == 0 ? "" : String.valueOf(it.getPort_1g()));

                    cell = row.createCell(4);
                    cell.setCellValue(it.getUse1G() == 0 ? "" : String.valueOf(it.getUse1G()));

                    cell = row.createCell(5);
                    cell.setCellValue(it.getNotUse1G() == 0 ? "" : String.valueOf(it.getNotUse1G()));

                    cell = row.createCell(6);
                    cell.setCellValue(it.getSfp1g() == 0 ? "" : String.valueOf(it.getSfp1g()));

                    cell = row.createCell(7);
                    cell.setCellValue(it.getUseSfp1G() == 0 ? "" : String.valueOf(it.getUseSfp1G()));

                    cell = row.createCell(8);
                    cell.setCellValue(it.getNotUseSfp1G() == 0 ? "" : String.valueOf(it.getNotUseSfp1G()));

                    cell = row.createCell(9);
                    cell.setCellValue(it.getPort_10g() == 0 ? "" : String.valueOf(it.getPort_10g()));

                    cell = row.createCell(10);
                    cell.setCellValue(it.getUse10G() == 0 ? "" : String.valueOf(it.getUse10G()));

                    cell = row.createCell(11);
                    cell.setCellValue(it.getNotUse10G() == 0 ? "" : String.valueOf(it.getNotUse10G()));

                    cell = row.createCell(12);
                    cell.setCellValue(it.getSfp10g() == 0 ? "" : String.valueOf(it.getSfp10g()));

                    cell = row.createCell(13);
                    cell.setCellValue(it.getUseSfp10G() == 0 ? "" : String.valueOf(it.getUseSfp10G()));

                    cell = row.createCell(14);
                    cell.setCellValue(it.getNotUseSfp10G() == 0 ? "" : String.valueOf(it.getNotUseSfp10G()));
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

    @RequestMapping(value = "/checkIP", method = RequestMethod.GET,
            produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String checkIP(@RequestParam(value = "ip", required = false) String ip, ModelMap mm,
            HttpServletRequest request) throws IOException {
        try {
            return ManeFacade.checkIsIP(ip);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "0";
        }
    }
}
