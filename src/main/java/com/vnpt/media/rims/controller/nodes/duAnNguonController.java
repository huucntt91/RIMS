package com.vnpt.media.rims.controller.nodes;

import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.DuAnNguonBO;
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
import com.vnpt.media.rims.formbean.ImportNodeForm;
import com.vnpt.media.rims.formbean.RegDuAnNguonExcel;
import com.vnpt.media.rims.formbean.UpdateDuAnNguonPTMExcel;
import com.vnpt.media.rims.formbean.UpdateDuAnNguonQLDAHT1Excel;
import com.vnpt.media.rims.formbean.UpdateDuAnNguonVTTExcel;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.FileCopyUtils;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/duAnNguon")
public class duAnNguonController extends BaseController {

    private static Logger logger = LogManager.getLogger(duAnNguonController.class);
    private static final String separator = ",";
    private static final String LIST_PLANS = "project/duAnNguon/duAnNguonList";
    private static final String FORM_EDIT = "project/duAnNguonImportExels/editDuAnNguonImport";
    private static final String FORM_NEW = "project/duAnNguonImportExels/addDuAnNguonImport";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
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

        StationPlansFacade facade = new StationPlansFacade();
        int totalRows;
        try{
            totalRows = StationPlansFacade.countDuAnNguon(khuvucId,tinhTpId);
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
            return ("redirect:/duAnNguon/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/duAnNguon/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý dự án nguồn");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<DuAnNguonBO> lst = new ArrayList<DuAnNguonBO>();
        try{
            lst = StationPlansFacade.searchDuAnNguon(String.valueOf(startRow), String.valueOf(endRow), khuvucId, tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } 
        mm.put("startRow", startRow);
        mm.put("lstDANguon", lst);
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
            ModelMap mm,
            HttpServletRequest request) {
        try{
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
        khuvucId = khuvucId == null ? "" : khuvucId;
        
        StationPlansFacade facade = new StationPlansFacade();
        int totalRows;
        try{
            totalRows = StationPlansFacade.countDuAnNguon(khuvucId,tinhTpId);
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
            return ("redirect:/duAnNguon/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/duAnNguon/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý dự án nguồn");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        logger.info("Action search dự án nguồn");
        List<DuAnNguonBO> lst = new ArrayList<DuAnNguonBO>();
        try{
            lst = StationPlansFacade.searchDuAnNguon(String.valueOf(startRow), String.valueOf(endRow), khuvucId, tinhTpId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } 
        mm.put("startRow", startRow);
        mm.put("lstDANguon", lst);
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        logger.debug("Delete du an nguon");
        try {
            StationPlansFacade adminFacade = new StationPlansFacade();

            if (adminFacade.deleteDuAnNguon(id) > -1) {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/duAnNguon/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/duAnNguon/init";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));


            return "redirect:/duAnNguon/init";
        }

    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId) {
        List<DuAnNguonBO> data = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId.replace("null", "");
            khuvucId = khuvucId == null ? "" : khuvucId.replace("null", "");

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            data = StationPlansFacade.exportDuAnNguon(khuvucId, tinhTpId);

            File fileResult = writeExportExcel(dataDirectory + "/export_du_an_nguon.xlsx", data);
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

    private File writeExportExcel(String fileTemplate, List<DuAnNguonBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File(StringUtils.getFolderTemp() + File.separator + "Du_an_nguon" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (DuAnNguonBO it : datas) {
                    row = sheet.createRow(rowIndex++);
                    cell = row.createCell(0);
                    cell.setCellValue(it.getArea());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getTen_tinh_tp()== null ? "" : it.getTen_tinh_tp());

                    cell = row.createCell(2);
                    cell.setCellValue(it.getTen_quy_hoach()== null ? "" : it.getTen_quy_hoach());

                    cell = row.createCell(3);
                    cell.setCellValue(it.getMa_quy_hoach()== null ? "" : it.getMa_quy_hoach());

                    cell = row.createCell(4);
                    cell.setCellValue(it.getBuilding_name()== null ? "" : it.getBuilding_name());

                    cell = row.createCell(5);
                    cell.setCellValue(it.getMa_building()== null ? "" : it.getMa_building());

                    cell = row.createCell(6);
                    cell.setCellValue(it.getDia_chi()== null ? "" : it.getDia_chi());

                    cell = row.createCell(7);
                    cell.setCellValue(it.getLongitude()== null ? "" : it.getLongitude());

                    cell = row.createCell(8);
                    cell.setCellValue(it.getLatitude()== null ? "" : it.getLatitude());

                    cell = row.createCell(9);
                    if (it.getStatus() != null && it.getStatus() == 1) {
                        cell.setCellValue("Có sẵn");
                    } else if (it.getStatus() != null && it.getStatus() == 2) {
                        cell.setCellValue("Mới");
                    } else {
                        cell.setCellValue("");
                    }
                    cell = row.createCell(10);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_hoat_dong_csht(), "dd/MM/yyyy"));

                    cell = row.createCell(11);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_hoan_thanh_csht(), "dd/MM/yyyy"));

                    cell = row.createCell(12);
                    cell.setCellValue(it.getTu_nguon());

                    cell = row.createCell(13);
                    cell.setCellValue(it.getChungloai_accu()== null ? "" : it.getChungloai_accu());

                    cell = row.createCell(14);
                    cell.setCellValue(it.getSo_to_accu());

                    cell = row.createCell(15);
                    cell.setCellValue(it.getMuc_dich_tb_nguon()== null ? "" : it.getMuc_dich_tb_nguon());

                    cell = row.createCell(16);
                    cell.setCellValue(it.getSo_van_ban()== null ? "" : it.getSo_van_ban());

                    cell = row.createCell(17);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_van_ban(), "dd/MM/yyyy"));

                    cell = row.createCell(18);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_can_lap_tb_nguon(), "dd/MM/yyyy"));

                    cell = row.createCell(19);
                    cell.setCellValue(it.getPhone_number()== null ? "" : it.getPhone_number());

                    cell = row.createCell(20);
                    cell.setCellValue(it.getTen_tu_nguon()== null ? "" : it.getTen_tu_nguon());

                    cell = row.createCell(21);
                    cell.setCellValue(it.getSo_tu_nguon());

                    cell = row.createCell(22);
                    cell.setCellValue(it.getSo_module_nan());

                    cell = row.createCell(23);
                    cell.setCellValue(it.getCong_suat_module_nan()== null ? "" : it.getCong_suat_module_nan());
                    
                    cell = row.createCell(24);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_du_kien_ban_giao_tu_nguon(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(25);
                    cell.setCellValue(it.getThuoc_du_an_tu_nguon()== null ? "" : it.getThuoc_du_an_tu_nguon());
                    
                    cell = row.createCell(26);
                    cell.setCellValue(it.getSo_po_tu_nguon());
                    
                    cell = row.createCell(27);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_thuc_te_giao_tu_nguon(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(28);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_nghiem_thu(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(29);
                    cell.setCellValue(it.getTen_accu()== null ? "" : it.getTen_accu());
                    
                    cell = row.createCell(30);
                    cell.setCellValue(it.getChung_loai_accu_ptm()== null ? "" : it.getChung_loai_accu_ptm());
                    
                    cell = row.createCell(31);
                    cell.setCellValue(it.getSo_to_accu_ptm());
                    
                    cell = row.createCell(32);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_du_kien_ban_giao_accu(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(33);
                    cell.setCellValue(it.getThuoc_du_an_accu()== null ? "" : it.getThuoc_du_an_accu());
                    
                    cell = row.createCell(34);
                    cell.setCellValue(it.getSo_po_accu());
                    
                    cell = row.createCell(35);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_thuc_te_giao_accu(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(36);
                    cell.setCellValue(DateTimeUtils.convertDateString(it.getNgay_nghiem_thu_accu(), "dd/MM/yyyy"));
                    
                    cell = row.createCell(37);
                    cell.setCellValue(it.getGhi_chu_net()== null ? "" : it.getGhi_chu_net());
                    
                    cell = row.createCell(38);
                    cell.setCellValue(it.getGhi_chu_vtt()== null ? "" : it.getGhi_chu_vtt());
                   
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
    
    @RequestMapping(value = "/insertExels", method = RequestMethod.GET)
    public String init(
            ModelMap mm, HttpServletRequest request) {
        
        ImportNodeForm groupContactForm = new ImportNodeForm();
        mm.put("groupContactForm", groupContactForm);

        return FORM_NEW;
    }
    
    @RequestMapping(value = "/editExels", method = RequestMethod.GET)
    public String edit(
            ModelMap mm, HttpServletRequest request) {
        ImportNodeForm groupContactForm = new ImportNodeForm();
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "10");
        List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "10");
        mm.addAttribute("classAtrrEdit", classAtrrEdit);
        mm.addAttribute("classAtrrView", classAtrrView);
        mm.put("groupContactForm", groupContactForm);

        return FORM_EDIT;
    }
    
    @RequestMapping(value = "/importFileVTT", method = RequestMethod.POST)
    public void add(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<RegDuAnNguonExcel> items = ExOM.mapFromExcel(convFile) 
                    .to(RegDuAnNguonExcel.class)
                    .mapSheet(0, 2);

            List<RegDuAnNguonExcel> temp = new ArrayList<RegDuAnNguonExcel>();
            for (RegDuAnNguonExcel item : items) {
                if (item.getMaTramQuyHoach()== null || item.getMaTramQuyHoach().trim().equals("")) {
                    continue;
                }
                RegDuAnNguonExcel excel = new RegDuAnNguonExcel();
                excel = item;
                String output = face.addDuAnNguonExcel(excel, String.valueOf(user.getId()));
                if(output.isEmpty() || output.equalsIgnoreCase("-1")){
                    excel.setNote("Lỗi");
                }else{
                    excel.setNote(output);
                }
                temp.add(excel);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_dk_nguon_da_vtt.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeAddDuAnNguon(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_dk_nguon_da_vtt.xlsx";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

    }
    
    public File writeAddDuAnNguon(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<RegDuAnNguonExcel> iterator = (Iterator<RegDuAnNguonExcel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                RegDuAnNguonExcel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());

                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramQuyHoach());
                cell = row.createCell(2);
                cell.setCellValue(item.getMaCSHT());

                cell = row.createCell(3);
                cell.setCellValue(item.getNgayDuKienHtCSHT());

                cell = row.createCell(4);
                cell.setCellValue(item.getTuNguon());

                cell = row.createCell(5);
                cell.setCellValue(item.getChungLoaiACCU());

                cell = row.createCell(6);
                cell.setCellValue(item.getSoToACCU());
                
                cell = row.createCell(7);
                cell.setCellValue(item.getMucDichTrangBiNguon());
                
                cell = row.createCell(8);
                cell.setCellValue(item.getSoVanBan());
                
                cell = row.createCell(9);
                cell.setCellValue(item.getNgayVanBan());
                
                cell = row.createCell(10);
                cell.setCellValue(item.getNgayCanLapTbNguon());
                
                cell = row.createCell(11);
                cell.setCellValue(item.getSoDienThoai());
            }
            fin.close();
            File file = new File("result_dk_nguon_da_vtt.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    @RequestMapping(value = "/upDateFileVTT", method = RequestMethod.POST)
    public void upDateFileVTT(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<UpdateDuAnNguonVTTExcel> items = ExOM.mapFromExcel(convFile) 
                    .to(UpdateDuAnNguonVTTExcel.class)
                    .mapSheet(0, 2);

            List<UpdateDuAnNguonVTTExcel> temp = new ArrayList<UpdateDuAnNguonVTTExcel>();
            for (UpdateDuAnNguonVTTExcel item : items) {
                if (item.getMaTramQuyHoach()== null || item.getMaTramQuyHoach().trim().equals("")) {
                    continue;
                }
                UpdateDuAnNguonVTTExcel excel = new UpdateDuAnNguonVTTExcel();
                excel = item;
                String output = face.updateDuAnNguonVTT(excel, String.valueOf(user.getId()));
                if(output.isEmpty() || output.equalsIgnoreCase("-1")){
                    excel.setNote("Lỗi");
                }else{
                    excel.setNote(output);
                };
                temp.add(excel);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_update_nguon_da_vtt.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeUpdateDuAnNguonVTT(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_update_nguon_da_vtt.xlsx";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

    }
    
    public File writeUpdateDuAnNguonVTT(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<UpdateDuAnNguonVTTExcel> iterator = (Iterator<UpdateDuAnNguonVTTExcel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                UpdateDuAnNguonVTTExcel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());

                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramQuyHoach());
                cell = row.createCell(2);
                cell.setCellValue(item.getMaCSHT());

                cell = row.createCell(3);
                cell.setCellValue(item.getNgayDuKienHtCSHT());

                cell = row.createCell(4);
                cell.setCellValue(item.getTuNguon());

                cell = row.createCell(5);
                cell.setCellValue(item.getChungLoaiACCU());

                cell = row.createCell(6);
                cell.setCellValue(item.getSoToACCU());
                
                cell = row.createCell(7);
                cell.setCellValue(item.getMucDichTrangBiNguon());
                
                cell = row.createCell(8);
                cell.setCellValue(item.getSoVanBan());
                
                cell = row.createCell(9);
                cell.setCellValue(item.getNgayVanBan());
                
                cell = row.createCell(10);
                cell.setCellValue(item.getNgayCanLapTbNguon());
                
                cell = row.createCell(11);
                cell.setCellValue(item.getSoDienThoai());
                
                cell = row.createCell(12);
                cell.setCellValue(item.getNgayNghiemThu());
                
                cell = row.createCell(13);
                cell.setCellValue(item.getNgayNghiemThuACCU());
                
                cell = row.createCell(14);
                cell.setCellValue(item.getGhiChuVTT());
            }
            fin.close();
            File file = new File("result_update_nguon_da_vtt.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    @RequestMapping(value = "/upDateFilePTM", method = RequestMethod.POST)
    public void upDateFilePTM(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<UpdateDuAnNguonPTMExcel> items = ExOM.mapFromExcel(convFile) 
                    .to(UpdateDuAnNguonPTMExcel.class)
                    .mapSheet(0, 2);

            List<UpdateDuAnNguonPTMExcel> temp = new ArrayList<UpdateDuAnNguonPTMExcel>();
            for (UpdateDuAnNguonPTMExcel item : items) {
                if (item.getMaTramQuyHoach()== null || item.getMaTramQuyHoach().trim().equals("")) {
                    continue;
                }
                UpdateDuAnNguonPTMExcel excel = new UpdateDuAnNguonPTMExcel();
                excel = item;
                String output = face.updateDuAnNguonPTM(excel, String.valueOf(user.getId()));
                if(output.isEmpty() || output.equalsIgnoreCase("-1")){
                    excel.setNote("Lỗi");
                }else{
                    excel.setNote(output);
                }
                temp.add(excel);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_update_nguon_da_ptm.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeUpdateDuAnNguonPTM(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_update_nguon_da_ptm.xlsx";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

    }
    
    public File writeUpdateDuAnNguonPTM(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<UpdateDuAnNguonPTMExcel> iterator = (Iterator<UpdateDuAnNguonPTMExcel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                UpdateDuAnNguonPTMExcel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());

                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramQuyHoach());
                cell = row.createCell(2);
                cell.setCellValue(item.getSoTuNguon());

                cell = row.createCell(3);
                cell.setCellValue(item.getSoModuleNan());

                cell = row.createCell(4);
                cell.setCellValue(item.getCongSuatModuleNan());

                cell = row.createCell(5);
                cell.setCellValue(item.getChungLoaiACCU());

                cell = row.createCell(6);
                cell.setCellValue(item.getSoToACCU());
                
                cell = row.createCell(7);
                cell.setCellValue(item.getGhiChuNet());
                
                
                
            }
            fin.close();
            File file = new File("result_update_nguon_da_ptm.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    @RequestMapping(value = "/upDateFileQLDAHT1", method = RequestMethod.POST)
    public void upDateFileQLDAHT1(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<UpdateDuAnNguonQLDAHT1Excel> items = ExOM.mapFromExcel(convFile) 
                    .to(UpdateDuAnNguonQLDAHT1Excel.class)
                    .mapSheet(0, 2);

            List<UpdateDuAnNguonQLDAHT1Excel> temp = new ArrayList<UpdateDuAnNguonQLDAHT1Excel>();
            for (UpdateDuAnNguonQLDAHT1Excel item : items) {
                if (item.getMaTramQuyHoach()== null || item.getMaTramQuyHoach().trim().equals("")) {
                    continue;
                }
                UpdateDuAnNguonQLDAHT1Excel excel = new UpdateDuAnNguonQLDAHT1Excel();
                excel = item;
                String output = face.updateDuAnNguonQLDAHT1(excel, String.valueOf(user.getId()));
                if(output.isEmpty() || output.equalsIgnoreCase("-1")){
                    excel.setNote("Lỗi");
                }else{
                    excel.setNote(output);
                }
                temp.add(excel);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_update_nguon_da_qldaht1.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeUpdateDuAnNguonQLDAHT1(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_update_nguon_da_qldaht1.xlsx";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

    }
    
    public File writeUpdateDuAnNguonQLDAHT1(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<UpdateDuAnNguonQLDAHT1Excel> iterator = (Iterator<UpdateDuAnNguonQLDAHT1Excel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                UpdateDuAnNguonQLDAHT1Excel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());

                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramQuyHoach());
                cell = row.createCell(2);
                cell.setCellValue(item.getTenTuNguon());

                cell = row.createCell(3);
                cell.setCellValue(item.getNgayDkBanGiaoTuNguon());

                cell = row.createCell(4);
                cell.setCellValue(item.getThuocDuAnTuNguon());

                cell = row.createCell(5);
                cell.setCellValue(item.getSoPoTuNguon());

                cell = row.createCell(6);
                cell.setCellValue(item.getNgayThucTeGiaoTuNguon());
                
                cell = row.createCell(7);
                cell.setCellValue(item.getTenACCU());
                
                cell = row.createCell(8);
                cell.setCellValue(item.getNgayDuKienBanGiaoACCU());
                
                cell = row.createCell(9);
                cell.setCellValue(item.getThuocDuAnACCU());
                
                cell = row.createCell(10);
                cell.setCellValue(item.getSoPoACCU());
                
                cell = row.createCell(11);
                cell.setCellValue(item.getNgayThucTeGiaoACCU());
                
                cell = row.createCell(12);
                cell.setCellValue(item.getGhiChuNet());
                
                
                
            }
            fin.close();
            File file = new File("result_update_nguon_da_qldaht1.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    
}
