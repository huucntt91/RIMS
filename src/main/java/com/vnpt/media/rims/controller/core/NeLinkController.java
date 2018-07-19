/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.core;

import com.google.gson.Gson;
import com.vnpt.media.rims.bean.NeLinkBO;
import com.vnpt.media.rims.bean.OpcBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.ContentDataTableItem;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.CoreFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.NeLinkFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
@RequestMapping(value = "/nelink")
public class NeLinkController extends BaseController {

    private static Logger logger = LogManager.getLogger(NeLinkController.class);
    private static final String LIST = "core/nelink/list";
    private static final String POPUP = "core/nelink/popup";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request, RedirectAttributes attr) {
        try {
            //phan quyen theo nhom thuoc tinh
//            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
//            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
//            mm.addAttribute("classAtrrEdit", classAtrrEdit);
//            mm.addAttribute("classAtrrView", classAtrrView);

        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String list(HttpServletRequest request, @Valid @ModelAttribute(value = "node_code") String node_code) throws IOException {

        try {
//            
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
            if (node_code != null && !node_code.isEmpty()) {
                ar_name.add("node_code1");
                ar_search_value.add(node_code);
            }
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

            String[] recordsTotal = new String[1];
            String[] recordsFiltered = new String[1];
            ArrayList<NeLinkBO> list = NeLinkFacade.find_ne_link(prs_start_record,
                    prs_length_page, prs_global_search, prs_list_column_name,
                    prs_list_column_search, prs_column_to_sort, prs_sort_direction,
                    recordsTotal, recordsFiltered);
            Map<Integer, OpcBO> opcs = NeLinkFacade.find_opc("");
            int count = 0;
            // chuyển list mailItem thành list String
            List<List<String>> data = new ArrayList();

            if (list != null) {
                for (NeLinkBO item : list) {
                    ArrayList<String> ls = new ArrayList();
                    // thứ tự phải khớp với thứ tự của các cột trong table của trang jsp
                    count++;
                    ls.add(count + "");
                    ls.add(item.getNodeCode1());
                    OpcBO opc1 = opcs.get(item.getNode_id1());
                    if (opc1 != null) {
                        if ((item.getNe_type_id1() != 11 && item.getNe_type_id2() == 11)) {
                            ls.add(opc1.getOpc1());
                        } else {
                            ls.add(opc1.getOpc());
                        }
                        ls.add(opc1.getNumeral_system());
                    } else {
                        ls.add("");
                        ls.add("");
                    }
                    ls.add(item.getNodeCode2());
                    OpcBO opc2 = opcs.get(item.getNode_id2());
                    if (opc2 != null) {
                        if ((item.getNe_type_id1() == 11 && item.getNe_type_id2() != 11)) {
                            ls.add(opc2.getOpc1());
                        } else {
                            ls.add(opc2.getOpc());
                        }
                        ls.add(opc2.getNumeral_system());
                    } else {
                        ls.add("");
                        ls.add("");
                    }
                    ls.add("");
                    ls.add(item.getNe_link_id() == null ? "" : item.getNe_link_id().toString());
                    data.add(ls);
                }
            }

            // chuyển thành object json
            ContentDataTableItem responseObj = new ContentDataTableItem(draw, recordsTotal[0], recordsFiltered[0], data);
            Gson gson = new Gson();
//            
            return gson.toJson(responseObj);
        } catch (DAOException | NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(HttpServletRequest request,
            HttpServletResponse response) {
        ArrayList<NeLinkBO> neLinkBOs = new ArrayList<>();
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "Core-Link.xlsx";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            neLinkBOs = NeLinkFacade.find_all_ne_link();
            Map<Integer, OpcBO> opcs = NeLinkFacade.find_opc("");
            for (NeLinkBO item : neLinkBOs) {

                OpcBO opc1 = opcs.get(item.getNode_id1());
                if (opc1 != null) {
                    item.setOpc1(opc1.getOpc());
                    item.setNumeral_system1(opc1.getNumeral_system());
                }

                OpcBO opc2 = opcs.get(item.getNode_id2());
                if (opc2 != null) {
                    item.setOpc2(opc2.getOpc());
                    item.setNumeral_system2(opc2.getNumeral_system());
                }
            }

            File fileResult = writeExcel(fileTemplate, fileName, neLinkBOs);
            if (fileResult != null && fileResult.exists()) {
                response.setContentType("application/excel");

                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("writed excel ...............");
    }

    private File writeExcel(File fileTemplate, String fileName, List<NeLinkBO> datas) {
        FileInputStream fin = null;
        File result = null;
        Workbook workbook = null;
        FileOutputStream fos = null;
        try {

            fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            try {
                Cell cell = null;
                Row row = null;
                int rowIndex = 1;
                int stt = 0;
                //header
                for (NeLinkBO item : datas) {
                    stt += 1;
                    row = sheet.createRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    cell = row.createCell(0);
                    cell.setCellValue(stt);
                    cell = row.createCell(1);
                    cell.setCellValue(item.getNodeCode1());

                    cell = row.createCell(2);
                    cell.setCellValue(item.getOpc1());

                    cell = row.createCell(3);
                    cell.setCellValue(item.getNumeral_system1());

                    cell = row.createCell(4);
                    cell.setCellValue(item.getNodeCode2());

                    cell = row.createCell(5);
                    cell.setCellValue(item.getOpc2());
                    cell = row.createCell(6);
                    cell.setCellValue(item.getNumeral_system2());
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            result = new File(fileName);
            fos = new FileOutputStream(result);
            workbook.write(fos);
            workbook.close();

        } catch (FileNotFoundException ex) {
            logger.error(ex.getMessage(), ex);;
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);;
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);;
            }
        }
        return result;

    }

    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String popup(@Valid @ModelAttribute(value = "node_code") String node_code, @RequestParam(value = "nodeId", required = false) String nodeId, @RequestParam(value = "neTypeId", required = false) String neTypeId,
            Locale locale, RedirectAttributes attr, ModelMap mm, HttpServletRequest request) throws Exception {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
            mm.addAttribute("classAtrrEdit", classAtrrEdit);
            mm.addAttribute("classAtrrView", classAtrrView);
            mm.put("node_code1", node_code);
            mm.put("nodeId", nodeId);
            mm.put("neTypeId", neTypeId);
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return POPUP;
    }

    //delete project
    @RequestMapping(value = "/delete/{ne_link_id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "ne_link_id") String ne_link_id,
            @Valid @ModelAttribute(value = "node_code") String node_code,
            Locale locale, RedirectAttributes attr) throws Exception {
        Message message = null;
        try {
            if (NeLinkFacade.delete(ne_link_id)) {
                message = new Message(Message.TYPE_SUCCESS, null, "Xóa thành công!");
            } else {
                message = new Message(Message.TYPE_WARNING, null, "Xóa không thành công!");
            }
        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            message = new Message(Message.TYPE_WARNING, null, "Xóa không thành công!");
        }
        attr.addFlashAttribute("info", message);
        return "redirect:/nelink/popup?node_code=" + node_code;
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

    //@ModelAttribute("nodeList")
    public List nodeList(String neTypeIdList) {
        try {
            CoreFacade coreFacade = new CoreFacade();
//        String neTypeIdList = "11,12,13,14,15,16,17,18,19,20";
            return coreFacade.getNode(neTypeIdList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/preOpcDpc/{nodeId}/{nodeCode}/{neTypeId}", method = RequestMethod.GET)
    public String preOpcDpc(ModelMap mm, @PathVariable(value = "nodeId") String nodeId,
            @PathVariable(value = "neTypeId") String neTypeId,
            @PathVariable(value = "nodeCode") String nodeCode,
            Locale locale, RedirectAttributes attr,
            @RequestParam(value = "node2Id", required = false) String listNode2Id,
            @RequestParam(value = "loaiTruyenDanId", required = false) String loaiTruyenDanId) throws Exception {
        try {
            mm.addAttribute("nodeId", nodeId);
            mm.addAttribute("neTypeId", neTypeId);
            mm.addAttribute("nodeCode", nodeCode);

            CoreFacade coreFacade = new CoreFacade();
//        coreFacade.addOpcDpc(nodeId, listNode2Id, Integer.valueOf("loaiTruyenDanId"));

//        
//        coreFacade.getOpcDpc(nodeId);
//        mm.put("nodeOPCDPC", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "core/nelink/neLinkList";
    }

    @RequestMapping(value = "/preAdd/{node1}/{neTypeId}/{node_code1}", method = RequestMethod.GET)
    public String preAdd(ModelMap mm,
            @PathVariable(value = "node1") String node1,
            @PathVariable(value = "neTypeId") String neTypeId,
            @PathVariable(value = "node_code1") String node_code1,
            HttpServletRequest request, RedirectAttributes attr) {
        try {
            mm.addAttribute("nodeId", node1);
            mm.addAttribute("neTypeId", neTypeId);
            mm.addAttribute("node_code1", node_code1);
            List listNe = Arrays.asList("11", "12", "13", "14", "15", "16", "17", "18", "19", "20");
            List listNepscore = Arrays.asList("22", "23", "24", "25");
            if (listNe.contains(neTypeId)) {
                mm.put("nodeList", nodeList("11,12,13,14,15,16,17,18,19,20"));
            } else if (listNepscore.contains(neTypeId)) {
                mm.put("nodeList", nodeList("11,22,23,24,25"));
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return "core/nelink/addNeLink";
    }

    @RequestMapping(value = "/insertNeLink", method = RequestMethod.POST)
    public String insertNeLink(ModelMap mm,
            @RequestParam(value = "node_code1", required = false) String node_code1,
            @RequestParam(value = "neTypeId", required = false) String neTypeId,
            @RequestParam(value = "nodeId", required = false) String nodeId,
            @RequestParam(value = "node2Id", required = false) String listNode2Id,
            @RequestParam(value = "loaiTruyenDanId", required = false) String loaiTruyenDanId, HttpServletRequest request, RedirectAttributes attr) {
        try {
            mm.addAttribute("nodeId", listNode2Id);
            mm.addAttribute("nodeCode", node_code1);

            CoreFacade coreFacade = new CoreFacade();
//        coreFacade.addOpcDpc(nodeId, listNode2Id, Integer.valueOf("loaiTruyenDanId"));

            coreFacade.insertNeLink(nodeId, listNode2Id, Integer.valueOf(loaiTruyenDanId));
//      
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
//        return "core/nelink/addNeLink";
        return "redirect:/nelink/popup?node_code=" + node_code1 + "&nodeId=" + nodeId + "&neTypeId=" + neTypeId;
    }
}
