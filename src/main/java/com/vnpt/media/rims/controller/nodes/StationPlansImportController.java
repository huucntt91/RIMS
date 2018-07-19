package com.vnpt.media.rims.controller.nodes;

import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.TramDuAnNetXExcel;
import com.vnpt.media.rims.bean.TramDuAnPTMExcel;
import com.vnpt.media.rims.bean.TramDuAnQLDAExcel;
import com.vnpt.media.rims.bean.TramDuAnTinhExcel;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.StationPlansFacade;
import com.vnpt.media.rims.formbean.ImportNodeForm;
import com.vnpt.media.rims.formbean.RegTramDuAnExcel;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.poi.ss.usermodel.CellStyle;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 *
 * @author VNP
 */
import org.springframework.util.FileCopyUtils;

@Controller
@RequestMapping(value = "/stationPlansExcelImport")
public class StationPlansImportController {

    private static Logger logger = LogManager.getLogger(StationPlansImportController.class);
    private static final String FORM_EDIT = "stationPlans/importExcel/editStationPlansImport";
    private static final String FORM_NEW = "stationPlans/importExcel/addStationPlansImport";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            ModelMap mm, HttpServletRequest request) {
        
        ImportNodeForm groupContactForm = new ImportNodeForm();
        mm.put("groupContactForm", groupContactForm);

        return FORM_NEW;
    }

    @RequestMapping(value = "/checkFile", method = RequestMethod.POST)
    public void add(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<RegTramDuAnExcel> items = ExOM.mapFromExcel(convFile) 
                    .to(RegTramDuAnExcel.class)
                    .mapSheet(0, 2);

            List<RegTramDuAnExcel> temp = new ArrayList<RegTramDuAnExcel>();
            for (RegTramDuAnExcel item : items) {
                if (item.getMaTramDuAn() == null || item.getMaTramDuAn().trim().equals("")) {
                    continue;
                }
                RegTramDuAnExcel excel = new RegTramDuAnExcel();
                excel = item;
                String output = face.addtramDAExcel(excel, String.valueOf(user.getId()));
                excel.setNote(StringUtils.errorDKTramDA(output));
                temp.add(excel);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_dk_tram_du_an.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeAddTramDuAn(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_dk_tram_du_an.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

            //attr.addFlashAttribute("listTramDA", temp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

        //return "redirect:/stationPlansExcelImport/init";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
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

    @RequestMapping(value = "/editFile", method = RequestMethod.POST)
    public void editFile(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpIds = String.join(",", tinhManager);
            
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<TramDuAnTinhExcel> items = ExOM.mapFromExcel(convFile)
                    .to(TramDuAnTinhExcel.class)
                    .mapSheet(0, 3);

            List<TramDuAnTinhExcel> temp = new ArrayList<TramDuAnTinhExcel>();            
            for (TramDuAnTinhExcel item : items) {
                if (item.getMaTramDuAn().trim().equals("")) {
                    continue;
                }
//                TramDuAnTinhExcel item1 = (TramDuAnTinhExcel) StringUtils.trimObject(item);
                
                TramDuAnTinhExcel excel = new TramDuAnTinhExcel();
                excel = (TramDuAnTinhExcel) StringUtils.trimObject(item);
                String output = face.updatetramDAExcel(excel, String.valueOf(user.getId()), tinhTpIds);
                excel.setNote(StringUtils.errorMess(output));
                temp.add(excel);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_cap_nhat_tram_du_an_cua_tinh.xls";
        File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeEditTramDuAn(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_cap_nhat_tram_du_an_cua_tinh.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));
            //attr.addFlashAttribute("listTramDA", temp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

        // return "redirect:/stationPlansExcelImport/init";
    }
    
    @RequestMapping(value = "/editFileQLDA", method = RequestMethod.POST)
    public void editFileQLDA(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<TramDuAnQLDAExcel> items = ExOM.mapFromExcel(convFile)
                    .to(TramDuAnQLDAExcel.class)
                    .mapSheet(0, 3);

            List<TramDuAnQLDAExcel> temp = new ArrayList<TramDuAnQLDAExcel>();            
            for (TramDuAnQLDAExcel item : items) {
                if (item.getMaTramDuAn().trim().equals("")) {
                    continue;
                }
                TramDuAnQLDAExcel excel = new TramDuAnQLDAExcel();
                excel = (TramDuAnQLDAExcel) StringUtils.trimObject(item);
                String output = face.updatetramDAQLDAExcel(excel, String.valueOf(user.getId()));
                excel.setNote(StringUtils.errorMess(output));
                temp.add(excel);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_cap_nhat_tram_du_an_cua_QLDA2.xls";
        File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeEditTramDuAnQLDA(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_cap_nhat_tram_du_an_cua_QLDA2.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));
            //attr.addFlashAttribute("listTramDA", temp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

        // return "redirect:/stationPlansExcelImport/init";
    }
    
    @RequestMapping(value = "/editFilePTM", method = RequestMethod.POST)
    public void editFilePTM(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<TramDuAnPTMExcel> items = ExOM.mapFromExcel(convFile)
                    .to(TramDuAnPTMExcel.class).mapSheet(0, 3);

            List<TramDuAnPTMExcel> temp = new ArrayList<TramDuAnPTMExcel>();            
            for (TramDuAnPTMExcel item : items) {
                if (item.getMaTramDuAn().trim().equals("")) {
                    continue;
                }
                TramDuAnPTMExcel excel = new TramDuAnPTMExcel();
                excel = (TramDuAnPTMExcel) StringUtils.trimObject(item);
                String output = face.updatetramDAPTMExcel(excel, String.valueOf(user.getId()));
                excel.setNote(StringUtils.errorMess(output));
                temp.add(excel);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_cap_nhat_tram_du_an_cua_PTM.xls";
        File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeEditTramDuAnPTM(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_cap_nhat_tram_du_an_cua_PTM.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));
            //attr.addFlashAttribute("listTramDA", temp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

        // return "redirect:/stationPlansExcelImport/init";
    }
    
    @RequestMapping(value = "/editFileNetx", method = RequestMethod.POST)
    public void editFileNetx(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm groupContactForm,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            File convFile = new File(groupContactForm.getFile().getOriginalFilename());
            groupContactForm.getFile().transferTo(convFile);
            StationPlansFacade face = new StationPlansFacade();
            List<TramDuAnNetXExcel> items = ExOM.mapFromExcel(convFile)
                    .to(TramDuAnNetXExcel.class)
                    .mapSheet(0, 3);

            List<TramDuAnNetXExcel> temp = new ArrayList<TramDuAnNetXExcel>();            
            for (TramDuAnNetXExcel item : items) {
                if (item.getMaTramDuAn().trim().equals("")) {
                    continue;
                }
                TramDuAnNetXExcel excel = new TramDuAnNetXExcel();
                excel = (TramDuAnNetXExcel) StringUtils.trimObject(item);
                String output = face.updatetramDANetXExcel(excel, String.valueOf(user.getId()));
                excel.setNote(StringUtils.errorMess(output));
                temp.add(excel);
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String fileName = "result_cap_nhat_tram_du_an_cua_NetX.xls";
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            File fileResult = writeEditTramDuAnNetX(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                fileName = "result_cap_nhat_tram_du_an_cua_NetX.xls";
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));
            //attr.addFlashAttribute("listTramDA", temp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        }

        // return "redirect:/stationPlansExcelImport/init";
    }

    public File writeAddTramDuAn(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<RegTramDuAnExcel> iterator = (Iterator<RegTramDuAnExcel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                RegTramDuAnExcel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());

                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramDuAn());
                cell = row.createCell(2);
                cell.setCellValue(item.getName());

                cell = row.createCell(3);
                cell.setCellValue(item.getMaSoHopDong());

                cell = row.createCell(4);
                cell.setCellValue(item.getMaCSHT());

                cell = row.createCell(5);
                cell.setCellValue(item.getMaQuyHoach());

                cell = row.createCell(6);
                cell.setCellValue(item.getHienTrangTram());
            }
            fin.close();
            File file = new File("tram_du_an.xlsx");
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

    public File writeEditTramDuAn(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<TramDuAnTinhExcel> iterator = (Iterator<TramDuAnTinhExcel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            DecimalFormat f = new DecimalFormat("##.000000");
//            String formattedValue = f.format(i2);
            //header
            while (iterator.hasNext()) {
                TramDuAnTinhExcel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());

                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramDuAn());
           
                // -- cam ket ha tang
                cell = row.createCell(2);
                if(isNumeric(item.getLonKS() != null ? item.getLonKS().replace(",", ".") : "")){
                    cell.setCellValue(f.format(Double.parseDouble(item.getLonKS() != null ? item.getLonKS().replace(",", ".") : ""))); 
                }else{
                    cell.setCellValue(item.getLonKS()); 
                }                
                cell = row.createCell(3);
                if(isNumeric(item.getLatKS() != null ? item.getLatKS().replace(",", ".") : "")){
                    cell.setCellValue(f.format(Double.parseDouble(item.getLatKS() != null ? item.getLatKS().replace(",", ".") : "")));
                }else
                    cell.setCellValue(item.getLatKS());
                
                cell = row.createCell(4);
                cell.setCellValue(item.getNhaTram());
                cell = row.createCell(5);
                cell.setCellValue(item.getCotAnten());
                cell = row.createCell(6);
                cell.setCellValue(item.getCauCapNgoai());
                cell = row.createCell(7);
                cell.setCellValue(item.getTuNguon());

                cell = row.createCell(8);
                cell.setCellValue(item.getDungLuongTuNguon());
                cell = row.createCell(9);
                cell.setCellValue(item.getSoModuleTuNguon());
                cell = row.createCell(10);
                cell.setCellValue(item.getChungLoaiAccu());
                cell = row.createCell(11);
                cell.setCellValue(item.getDungLuongAccu());
                cell = row.createCell(12);
                cell.setCellValue(item.getSoLuongToAccu());
                cell = row.createCell(13);
                cell.setCellValue(item.getTruyenDan());

                cell = row.createCell(14);
                cell.setCellValue(item.getDieuHoa());
                cell = row.createCell(15);
                cell.setCellValue(item.getDienAc());
                cell = row.createCell(16);
                cell.setCellValue(item.getDienAcNoiTram());
                cell = row.createCell(17);
                cell.setCellValue(item.getDuDkLapEnodeb());
                cell = row.createCell(18);
                cell.setCellValue(item.getNgayDuDkLapThietBi());
                cell = row.createCell(19);
                cell.setCellValue(item.getCapMoiTuNguonDc());
                cell = row.createCell(20);
                cell.setCellValue(item.getCapMoiAccu());

                cell = row.createCell(21);
                cell.setCellValue(item.getSwapAnten());
                cell = row.createCell(22);
                cell.setCellValue(item.getNgayHoanThanhKs());
                cell = row.createCell(23);
                cell.setCellValue(item.getNgayGuiSoLieu());
                cell = row.createCell(24);
                cell.setCellValue(item.getDauMoiNhanThietBi());
                cell = row.createCell(25);
                cell.setCellValue(item.getDauMoiQLCSHT());
                cell = row.createCell(26);
                cell.setCellValue(item.getDonViLapDat());

                cell = row.createCell(27);
                cell.setCellValue(item.getNgayKeHoachLapDat());
                cell = row.createCell(28);
                cell.setCellValue(item.getNgayBatDauLapDat());
                cell = row.createCell(29);
                cell.setCellValue(item.getNgayHTLapDatTb());
                cell = row.createCell(30);
                cell.setCellValue(item.getNgayHTLapDatAnten());
                cell = row.createCell(31);
                cell.setCellValue(item.getGhiChu());
            }
            fin.close();
            File file = new File("result_cap_nhat_tram_du_an_cua_tinh.xls");
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
    
    public File writeEditTramDuAnQLDA(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<TramDuAnQLDAExcel> iterator = (Iterator<TramDuAnQLDAExcel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                TramDuAnQLDAExcel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());
                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramDuAn());          
                cell = row.createCell(2);
                cell.setCellValue(item.getKeHoachXuatThietBi());
                cell = row.createCell(3);
                cell.setCellValue(item.getNgayXuatThietBiThucTe());
                cell = row.createCell(4);
                cell.setCellValue(item.getNgayXuatAntenThucTe());
                cell = row.createCell(5);
                cell.setCellValue(item.getNgayTiepNhanTb());
                cell = row.createCell(6);
                cell.setCellValue(item.getKeHoachTbDenSite());
                cell = row.createCell(7);
                cell.setCellValue(item.getKeHoachHoaMang());
                cell = row.createCell(8);
                cell.setCellValue(item.getNgayHoaMangThucTe());
                cell = row.createCell(9);
                cell.setCellValue(item.getKeHoachPhatSongCt());
                cell = row.createCell(10);
                cell.setCellValue(item.getNgayPhatSongCt());
                cell = row.createCell(11);
                cell.setCellValue(item.getKeHoachNghiemThu());
                cell = row.createCell(12);
                cell.setCellValue(item.getNgayNghiemThu());
                cell = row.createCell(13);
                cell.setCellValue(item.getDauMoiVnptNet());
                cell = row.createCell(14);
                cell.setCellValue(item.getDonViVanChuyen());
                cell = row.createCell(15);
                cell.setCellValue(item.getGhiChu());
            }
            fin.close();
            File file = new File("result_cap_nhat_tram_du_an_cua_QLDA2.xls");
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
    
    public File writeEditTramDuAnPTM(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<TramDuAnPTMExcel> iterator = (Iterator<TramDuAnPTMExcel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                TramDuAnPTMExcel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());
                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramDuAn());          
                cell = row.createCell(2);
                cell.setCellValue(item.getTenDuAn());
                cell = row.createCell(3);
                cell.setCellValue(item.getMaSoHopDong());
                cell = row.createCell(4);
                cell.setCellValue(item.getTenTinhTp());
                cell = row.createCell(5);
                cell.setCellValue(item.getTenQuanHuyen());
                cell = row.createCell(6);
                cell.setCellValue(item.getAddress());
                cell = row.createCell(7);
                cell.setCellValue(item.getTenTramDuAn());
                cell = row.createCell(8);
                cell.setCellValue(item.getMaTramBTS());
                cell = row.createCell(9);
                cell.setCellValue(item.getMaTramNodeB());
                cell = row.createCell(10);
                cell.setCellValue(item.getMaTramQuyHoach());
                cell = row.createCell(11);
                cell.setCellValue(item.getLongitude());
                cell = row.createCell(12);
                cell.setCellValue(item.getLatitude());
                cell = row.createCell(13);
                cell.setCellValue(item.getHienTrangTram());
                cell = row.createCell(14);
                cell.setCellValue(item.getTrangthaiCSHT());
                cell = row.createCell(15);
                cell.setCellValue(item.getVnptNetPheDuyet());
                cell = row.createCell(16);
                cell.setCellValue(item.getCauHinhThietBi());
                cell = row.createCell(17);
                cell.setCellValue(item.getNguonThietBi());
                cell = row.createCell(18);
                cell.setCellValue(item.getLoaiCongNghe());
                cell = row.createCell(19);
                cell.setCellValue(item.getChungLoaiThietBi());
                cell = row.createCell(20);
                cell.setCellValue(item.getChungLoaiAnten());
                cell = row.createCell(21);
                cell.setCellValue(item.getThoiDiemCungCapThietBi());
                cell = row.createCell(22);
                cell.setCellValue(item.getThoiDiemSwapThietBi());
                cell = row.createCell(23);
                cell.setCellValue(item.getGhiChu());
            }
            fin.close();
            File file = new File("result_cap_nhat_tram_du_an_cua_PTM.xls");
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
    
    public File writeEditTramDuAnNetX(File fileTemplate, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = null;
            workbook = new HSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<TramDuAnNetXExcel> iterator = (Iterator<TramDuAnNetXExcel>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                TramDuAnNetXExcel item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor
                cell = row.createCell(0);
                cell.setCellValue(item.getNote());
                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramDuAn());          
                cell = row.createCell(2);
                cell.setCellValue(item.getNgayPheDuyetKhaoSat()); 
                cell = row.createCell(3);
                cell.setCellValue(item.getNgayTiepNhanTruyenDan()); 
                cell = row.createCell(4);
                cell.setCellValue(item.getGhiChu());
            }
            fin.close();
            File file = new File("result_cap_nhat_tram_du_an_cua_NetX.xls");
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

    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
}
