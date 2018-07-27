/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.project;

import com.blogspot.na5cent.exom.ExOM;
import com.vnpt.media.rims.bean.TramQuyHoachUpdateCshtNguonDc;
import com.vnpt.media.rims.bean.TramQuyHoachUpdateNetx;
import com.vnpt.media.rims.bean.TramQuyHoachUpdatePtm;
import com.vnpt.media.rims.bean.TramQuyHoachUpdateQlda;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.common.Message;
import static com.vnpt.media.rims.common.utils.Convert.resourceBundle;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.TramQHFacade;
import com.vnpt.media.rims.formbean.ImportNodeForm;
import com.vnpt.media.rims.formbean.SearchTramQhForm;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/excel_update_project_station")
public class ExcelUpdateTramQHController extends BaseController {

    private static Logger logger = LogManager.getLogger(ExcelUpdateTramQHController.class);
    private static final String FORM_NEW = "project/project_station/importExcel/excelUpdateTramQH";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request, RedirectAttributes attr, @Valid @ModelAttribute(value = "searchTramQhForm") SearchTramQhForm searchTramQhForm) {
        try {
//            //phan quyen theo nhom thuoc tinh
//            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "U", "11");
//            List<String> classAtrrView = adminFacade.findClassAttrByUserId(String.valueOf(user.getId()), "S", "11");
//            mm.addAttribute("classAtrrEdit", classAtrrEdit);
//            mm.addAttribute("classAtrrView", classAtrrView);
//            
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            ImportNodeForm groupContactForm = new ImportNodeForm();
            mm.put("groupContactForm", groupContactForm);

            return FORM_NEW;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FORM_NEW;
    }

    @RequestMapping(value = "/templateQhTinh", method = RequestMethod.GET)
    public void templateQhTinh(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileResult = new File(dataDirectory + "/" + "update_qh_tinh.xlsx");
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

    @RequestMapping(value = "/templateQhQlda", method = RequestMethod.GET)
    public void templateQhQlda(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileResult = new File(dataDirectory + "/" + "update_qh_qlda.xlsx");
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

    @RequestMapping(value = "/templateQhNetx", method = RequestMethod.GET)
    public void templateQhNetx(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileResult = new File(dataDirectory + "/" + "update_qh_netx.xlsx");
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

    @RequestMapping(value = "/templateQhPtm", method = RequestMethod.GET)
    public void templateQhPtm(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileResult = new File(dataDirectory + "/" + "update_qh_ptm.xlsx");
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

    @RequestMapping(value = "/updateCshtNguonDc", method = RequestMethod.POST)
    public void updateCshtNguonDc(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm importFile,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        List<TramQuyHoachUpdateCshtNguonDc> items = null;
        try {

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {},mem: {}, start updating cshtnguondc...", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpIds = String.join(",", tinhManager);
            String folderTemp = StringUtils.getFolderTemp();
            File convFile = new File(folderTemp + File.separator + importFile.getFile().getOriginalFilename());
            importFile.getFile().transferTo(convFile);
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            logger.info("user: {}, ip: {}, mem: {},  start reading file excel", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            items = ExOM.mapFromExcel(convFile)
                    .to(TramQuyHoachUpdateCshtNguonDc.class)
                    .mapSheet(0, 3);
            logger.info("user: {}, ip: {}, mem: {},  start checking database, size: {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), items.size());
            boolean resultCheckFile;
            Integer[] checkRows = {1, 2, 3};
            resultCheckFile = StringUtils.checkImportFile(convFile, new File(dataDirectory + File.separator + "update_qh_tinh.xlsx"), checkRows);
            for (TramQuyHoachUpdateCshtNguonDc item : items) {
                if (resultCheckFile) {
                    item = (TramQuyHoachUpdateCshtNguonDc) StringUtils.trimObject(item);
                    String result = TramQHFacade.excelUpdateCshtNguonDc(item, String.valueOf(user.getId()), String.valueOf(user.getDonViId()), tinhTpIds);
                    item.setResult(StringUtils.errorMessUpdateTramQH(result));
                } else {
                    item.setResult(resourceBundle.getString("cell.new.import.validate.file"));
                }
            }
            logger.info("user: {}, ip: {},mem: {},  start writting file excel, size: {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory(), items.size());

            File fileTemplate = new File(dataDirectory + File.separator + "update_qh_tinh_result.xlsx");
            File fileResult = writeUpdateCshtNguonDc(fileTemplate, folderTemp, items);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    ServletOutputStream sos = response.getOutputStream();
                    try (FileInputStream fis = new FileInputStream(fileResult); BufferedInputStream bis = new BufferedInputStream(fis)) {
                        FileCopyUtils.copy(bis, sos);
                        response.getOutputStream().flush();
                    }
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

            logger.info("user: {}, ip: {},mem:{}, updated cshtnguondc", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
        } catch (Exception e) {
            logger.error("Exception :", e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        } finally {
            if (items != null) {
                items.clear();
            }
        }

        // return "redirect:/stationPlansExcelImport/init";
    }

    private File writeUpdateCshtNguonDc(File fileTemplate, String folderTemp, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<TramQuyHoachUpdateCshtNguonDc> iterator = (Iterator<TramQuyHoachUpdateCshtNguonDc>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                TramQuyHoachUpdateCshtNguonDc item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                cell = row.createCell(0);
                cell.setCellValue(item.getResult());
                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramQH());
//
                cell = row.createCell(2);
                cell.setCellValue(item.getTenTramQH());

                cell = row.createCell(3);
                cell.setCellValue(item.getCshtDonViChiuTrachNhiem());

                cell = row.createCell(4);
                cell.setCellValue(item.getQuanHuyen());

                cell = row.createCell(5);
                cell.setCellValue(item.getPhuongXa());

                cell = row.createCell(6);
                cell.setCellValue(item.getDiaChi());

                cell = row.createCell(7);
                cell.setCellValue(item.getCshtTenTram());

                cell = row.createCell(8);
                cell.setCellValue(item.getCshtNgayPheDuyetCapVon());

                cell = row.createCell(9);
                cell.setCellValue(item.getCshtCachThucXD());

                cell = row.createCell(10);
                cell.setCellValue(item.getCshtLoaiDat());

                cell = row.createCell(11);
                cell.setCellValue(item.getCshtNgayCapThueDat());

                cell = row.createCell(12);
                cell.setCellValue(item.getCshtNgayXinPhepXDNhaTram());

                cell = row.createCell(13);
                cell.setCellValue(item.getCshtNgayHoanThanhThuTucXay());

                cell = row.createCell(14);
                cell.setCellValue(item.getNgayKhoiCongTruyenDan());

                cell = row.createCell(15);
                cell.setCellValue(item.getCshtNgayHoanThanhXay());

                cell = row.createCell(16);
                cell.setCellValue(item.getCshtLoaiNhaTram());

                cell = row.createCell(17);
                cell.setCellValue(item.getCshtTinhTrangNhaTram());

                cell = row.createCell(18);
                cell.setCellValue(item.getCshtNgayXinPhepDoCaoCot());

                cell = row.createCell(19);
                cell.setCellValue(item.getCshtNgayCapPhepDoCaoCot());

                cell = row.createCell(20);
                cell.setCellValue(item.getCshtNgayHoanThanhThuTucXDCot());

                cell = row.createCell(21);
                cell.setCellValue(item.getCshtNgayKhoiCongDungCot());

                cell = row.createCell(22);
                cell.setCellValue(item.getNgayHoanThanhCot());

                cell = row.createCell(23);
                cell.setCellValue(item.getLoaiCot());

                cell = row.createCell(24);
                cell.setCellValue(item.getDoCaoCot());

                cell = row.createCell(25);
                cell.setCellValue(item.getDoCaoChanCot());

                cell = row.createCell(26);
                cell.setCellValue(item.getCshtTinhTrangCotAnten());

                cell = row.createCell(27);
                cell.setCellValue(item.getPhuongThucTruyenDan());

                cell = row.createCell(28);
                cell.setCellValue(item.getE1());

                cell = row.createCell(29);
                cell.setCellValue(item.getFe());

                cell = row.createCell(30);
                cell.setCellValue(item.getGe());

                cell = row.createCell(31);
                cell.setCellValue(item.getStm());

                cell = row.createCell(32);
                cell.setCellValue(item.getNgayKhoiCongTruyenDan());
                cell = row.createCell(33);
                cell.setCellValue(item.getNgayHoanThanhTruyenDan());
                cell = row.createCell(34);
                cell.setCellValue(item.getCshtTinhTrangTruyenDan());

                cell = row.createCell(35);
                cell.setCellValue(item.getNgayDienApAC());
                cell = row.createCell(36);
                cell.setCellValue(item.getHeThongDienTrongNhaTram());
                cell = row.createCell(37);
                cell.setCellValue(item.getHeThongDieuHoa());
                cell = row.createCell(38);
                cell.setCellValue(item.getHeThongTiepDat());
                cell = row.createCell(39);
                cell.setCellValue(item.getMayNo());
                cell = row.createCell(40);
                cell.setCellValue(item.getNgayHoanThanhPHuTro());
                cell = row.createCell(41);
                cell.setCellValue(item.getDoiTuongThongBao());
                cell = row.createCell(42);
                cell.setCellValue(item.getSoHieuThongBao());
                cell = row.createCell(43);
                cell.setCellValue(item.getNgayThongBaoHoanThanhCSHT());
                cell = row.createCell(44);
                cell.setCellValue(item.getKhoKhanVuongMac());

                cell = row.createCell(45);
                cell.setCellValue(item.getNguonDonViChiuTrachNhiem());
                cell = row.createCell(46);
                cell.setCellValue(item.getTuNguon());
                cell = row.createCell(47);
                cell.setCellValue(item.getLoaiTuNguon());
                cell = row.createCell(48);
                cell.setCellValue(item.getDungLuongTuNguon());
                cell = row.createCell(49);
                cell.setCellValue(item.getSoLuongRacktifier());
                cell = row.createCell(50);
                cell.setCellValue(item.getDungLuongAcquy());
                cell = row.createCell(51);
                cell.setCellValue(item.getSoLuongToAcquy());
                cell = row.createCell(52);
                cell.setCellValue(item.getDienApAcquy());
                cell = row.createCell(53);
                cell.setCellValue(item.getNgayDapUngDcDuKien());
                cell = row.createCell(54);
                cell.setCellValue(item.getNgayDapUngDcThucTe());
                cell = row.createCell(55);
                cell.setCellValue(item.getTinhTrangNguonDien());

            }
            fin.close();
            File file = new File(folderTemp + File.separator + "update_qh_tinh_result" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/updateQhPtm", method = RequestMethod.POST)
    public void updateQhPtm(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm importFile,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        List<TramQuyHoachUpdatePtm> items = null;
        try {

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip:{}, start update quy hoach ptm", user.getUsername(), request.getRemoteAddr());
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpIds = String.join(",", tinhManager);
            String folderTemp = StringUtils.getFolderTemp();

            File convFile = new File(folderTemp + File.separator + importFile.getFile().getOriginalFilename());
            logger.info("user: {}, ip:{}, start read file excel: {}", user.getUsername(), request.getRemoteAddr(), convFile.getName());
            importFile.getFile().transferTo(convFile);
            items = ExOM.mapFromExcel(convFile)
                    .to(TramQuyHoachUpdatePtm.class)
                    .mapSheet(0, 3);
            boolean resultCheckFile;
            Integer[] checkRows = {1, 2, 3};
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            resultCheckFile = StringUtils.checkImportFile(convFile, new File(dataDirectory + File.separator + "update_qh_ptm.xlsx"), checkRows);
            logger.info("user: {}, ip:{}, start check database: {}", user.getUsername(), request.getRemoteAddr(), items.size());
            for (TramQuyHoachUpdatePtm item : items) {
                if (resultCheckFile) {
                    item = (TramQuyHoachUpdatePtm) StringUtils.trimObject(item);
                    String result = TramQHFacade.updateQhPtm(item, String.valueOf(user.getId()), tinhTpIds);
                    item.setResult(StringUtils.errorMessUpdateTramQH(result));
                } else {
                    item.setResult(resourceBundle.getString("cell.new.import.validate.file"));
                }
            }
            logger.info("user: {}, ip:{}, start write excel: {}", user.getUsername(), request.getRemoteAddr(), items.size());

            File fileTemplate = new File(dataDirectory + File.separator + "update_qh_ptm_result.xlsx");
            File fileResult = writeUpdateQhPtm(fileTemplate, folderTemp, items);
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
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

            logger.info("user: {}, ip:{}, end update quy hoach ptm", user.getUsername(), request.getRemoteAddr());
        } catch (Exception e) {
            logger.error("Exception :", e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        } finally {
            if (items != null) {
                items.clear();
            }
        }
    }

    private File writeUpdateQhPtm(File fileTemplate, String folderTemp, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<TramQuyHoachUpdatePtm> iterator = (Iterator<TramQuyHoachUpdatePtm>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                TramQuyHoachUpdatePtm item = iterator.next();
                if (item.getMaTramQH() == null || item.getMaTramQH().isEmpty()) {
                    continue;
                }
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                cell = row.createCell(0);
                cell.setCellValue(item.getResult());
                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramQH());
                cell = row.createCell(2);
                cell.setCellValue(item.getTenTramQH());
                cell = row.createCell(3);
                cell.setCellValue(item.getMaBuilding());
                cell = row.createCell(4);
                cell.setCellValue(item.getTinhTp());
                cell = row.createCell(5);
                cell.setCellValue(item.getNamKhoiTao());
                cell = row.createCell(6);
                if (StringUtils.isNumeric(item.getLongitude())) {
                    cell.setCellValue(Double.parseDouble(item.getLongitude()));
                } else {
                    cell.setCellValue(item.getLongitude());
                }
                cell = row.createCell(7);
                if (StringUtils.isNumeric(item.getLatitude())) {
                    cell.setCellValue(Double.parseDouble(item.getLatitude()));
                } else {
                    cell.setCellValue(item.getLatitude());
                }
                cell = row.createCell(8);
                cell.setCellValue(item.getLoaiCongNghe());
                cell = row.createCell(9);
                cell.setCellValue(item.getBangTan());
                cell = row.createCell(10);
                cell.setCellValue(item.getChuongTrinhPtCSHT());
                cell = row.createCell(1);
                cell.setCellValue(item.getTrangThaiCSHT());
                cell = row.createCell(12);
                cell.setCellValue(item.getDvPheDuyet());
                cell = row.createCell(13);
                cell.setCellValue(item.getSoHieuVanBan());
                cell = row.createCell(14);
                cell.setCellValue(item.getNgayPheDuyet());
                cell = row.createCell(15);
                cell.setCellValue(item.getNgayDieuChinhGanNhat());
                cell = row.createCell(16);
                cell.setCellValue(item.getDvDieuChinh());
                cell = row.createCell(17);
                cell.setCellValue(item.getNgayPhatSong());
                cell = row.createCell(18);
                cell.setCellValue(item.getDvChiuTrachNhiemCktb());
                cell = row.createCell(19);
                cell.setCellValue(item.getNguonThietBi());
                cell = row.createCell(20);
                cell.setCellValue(item.getThoiDiemDapUngDuKien());
                cell = row.createCell(21);
                cell.setCellValue(item.getCongNgheDapUng());
                cell = row.createCell(22);
                cell.setCellValue(item.getChungLoaiThietBi());
                cell = row.createCell(23);
                cell.setCellValue(item.getThoiGianDapUngThucTe());
                cell = row.createCell(24);
                cell.setCellValue(item.getKhoKhanVuongMac());

            }
            fin.close();
            File file = new File(folderTemp + File.separator + "update_qh_ptm_result" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/updateQhQlda", method = RequestMethod.POST)
    public void updateQhQlda(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm importFile,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        List<TramQuyHoachUpdateQlda> items = null;
        try {

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpIds = String.join(",", tinhManager);
            String folderTemp = StringUtils.getFolderTemp();
            File convFile = new File(folderTemp + File.separator + importFile.getFile().getOriginalFilename());
            importFile.getFile().transferTo(convFile);
            items = ExOM.mapFromExcel(convFile)
                    .to(TramQuyHoachUpdateQlda.class)
                    .mapSheet(0, 3);
            boolean resultCheckFile;
            Integer[] checkRows = {1, 2, 3};
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            resultCheckFile = StringUtils.checkImportFile(convFile, new File(dataDirectory + File.separator + "update_qh_qlda.xlsx"), checkRows);
            for (TramQuyHoachUpdateQlda item : items) {
                if (resultCheckFile) {
                    item = (TramQuyHoachUpdateQlda) StringUtils.trimObject(item);
                    String result = TramQHFacade.updateQhQlda(item, String.valueOf(user.getId()), tinhTpIds);
                    item.setResult(StringUtils.errorMessUpdateTramQH(result));
                } else {
                    item.setResult(resourceBundle.getString("cell.new.import.validate.file"));
                }
            }

            File fileTemplate = new File(dataDirectory + File.separator + "update_qh_qlda_result.xlsx");
            File fileResult = writeUpdateQhQlda(fileTemplate, folderTemp, items);
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
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

            //attr.addFlashAttribute("listTramDA", temp);
        } catch (Exception e) {
            logger.error("Exception :", e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        } finally {
            if (items != null) {
                items.clear();
            }
        }
    }

    private File writeUpdateQhQlda(File fileTemplate, String folderTemp, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<TramQuyHoachUpdateQlda> iterator = (Iterator<TramQuyHoachUpdateQlda>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                TramQuyHoachUpdateQlda item = iterator.next();
                if (item.getMaTramQH() == null || item.getMaTramQH().isEmpty()) {
                    continue;
                }
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                cell = row.createCell(0);
                cell.setCellValue(item.getResult());
                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramQH());
                cell = row.createCell(2);
                cell.setCellValue(item.getTenTramQH());
                cell = row.createCell(3);
                cell.setCellValue(item.getDvChiuTrachNhiemNguon());
                cell = row.createCell(4);
                cell.setCellValue(item.getTuNguon());
                cell = row.createCell(5);
                cell.setCellValue(item.getLoaiTuNguon());
                cell = row.createCell(6);
                cell.setCellValue(item.getDungLuongTuNguon());
                cell = row.createCell(7);
                cell.setCellValue(item.getSoLuongRacktifier());
                cell = row.createCell(8);
                cell.setCellValue(item.getDungLuongAcquy());
                cell = row.createCell(9);
                cell.setCellValue(item.getSoLuongToAcquy());
                cell = row.createCell(10);
                cell.setCellValue(item.getDienApAcquy());
                cell = row.createCell(11);
                cell.setCellValue(item.getNgayDapUngNguonDuKien());
                cell = row.createCell(12);
                cell.setCellValue(item.getNgayDapUngNguonThucTe());
                cell = row.createCell(13);
                cell.setCellValue(item.getTinhTrangNguonDien());

            }
            fin.close();

            File file = new File(folderTemp + File.separator + "update_qh_qlda_result" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/updateQhNetx", method = RequestMethod.POST)
    public void updateQhNetx(HttpServletResponse response,
            ModelMap mm, @ModelAttribute(value = "groupContactForm") ImportNodeForm importFile,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception, Throwable {
        Locale locale = LocaleContextHolder.getLocale();
        List<TramQuyHoachUpdateNetx> items = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip:{}, start updating update quy hoach netX...", user.getUsername(), request.getRemoteAddr());
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String permisson = String.join(",", tinhManager);
            String folderTemp = StringUtils.getFolderTemp();
            File convFile = new File(folderTemp + File.separator + importFile.getFile().getOriginalFilename());
            importFile.getFile().transferTo(convFile);
            logger.info("user: {}, ip:{}, start reading file excel {}", user.getUsername(), request.getRemoteAddr(), convFile.getName());
            items = ExOM.mapFromExcel(convFile)
                    .to(TramQuyHoachUpdateNetx.class)
                    .mapSheet(0, 3);
            boolean resultCheckFile;
            Integer[] checkRows = {1, 2, 3};
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            resultCheckFile = StringUtils.checkImportFile(convFile, new File(dataDirectory + File.separator + "update_qh_netx.xlsx"), checkRows);
            logger.info("user: {}, ip:{}, start checking database, size {}", user.getUsername(), request.getRemoteAddr(), items.size());
            for (TramQuyHoachUpdateNetx item : items) {
                if (resultCheckFile) {
                    item = (TramQuyHoachUpdateNetx) StringUtils.trimObject(item);
                    String result = TramQHFacade.updateQhNetx(item, String.valueOf(user.getId()), permisson);
                    item.setResult(StringUtils.errorMessUpdateTramQH(result));
                } else {
                    item.setResult(resourceBundle.getString("cell.new.import.validate.file"));
                }
            }
            logger.info("user: {}, ip:{}, start writting excel,zise {}", user.getUsername(), request.getRemoteAddr(), items.size());
            File fileTemplate = new File(dataDirectory + File.separator + "update_qh_netx_result.xlsx");
            File fileResult = writeUpdateQhNetx(fileTemplate, folderTemp, items);
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
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, "Cập nhật thành công. Bạn check kết quả file download đi kèm"));

            logger.info("user: {}, ip:{}, end updating netx", user.getUsername(), request.getRemoteAddr());
        } catch (Exception e) {
            logger.error("Exception :", e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, "Có lỗi xảy ra file không đúng định dạng"));
        } finally {
            if (items != null) {
                items.clear();
            }
        }
    }

    private File writeUpdateQhNetx(File fileTemplate, String folderTemp, List<?> temp) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Iterator<TramQuyHoachUpdateNetx> iterator = (Iterator<TramQuyHoachUpdateNetx>) temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            while (iterator.hasNext()) {
                TramQuyHoachUpdateNetx item = iterator.next();
                if (item.getMaTramQH() == null || item.getMaTramQH().isEmpty()) {
                    continue;
                }
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                cell = row.createCell(0);
                cell.setCellValue(item.getResult());
                cell = row.createCell(1);
                cell.setCellValue(item.getMaTramQH());
                cell = row.createCell(2);
                cell.setCellValue(item.getNgayPhatSong());
                cell = row.createCell(3);
                cell.setCellValue(item.getDvChiuTrachNhiemCktb());
                cell = row.createCell(4);
                cell.setCellValue(item.getNguonThietBi());
                cell = row.createCell(5);
                cell.setCellValue(item.getThoiDiemDapUngDuKien());
                cell = row.createCell(6);
                cell.setCellValue(item.getCongNgheDapUng());
                cell = row.createCell(7);
                cell.setCellValue(item.getChungLoaiThietBi());
                cell = row.createCell(8);
                cell.setCellValue(item.getThoiGianDapUngThucTe());
                cell = row.createCell(9);
                cell.setCellValue(item.getKhoKhanVuongMac());
                cell = row.createCell(10);
                cell.setCellValue(item.getDanhGiaNetX());
                cell = row.createCell(11);
                cell.setCellValue(item.getYKienDanhGiaNetX());
                cell = row.createCell(12);
                cell.setCellValue(item.getDvChiuTrachNhiemAntena());
                cell = row.createCell(13);
                cell.setCellValue(item.getLoaiAntena1());
                cell = row.createCell(14);
                cell.setCellValue(item.getTenAntena1());
                cell = row.createCell(15);
                cell.setCellValue(item.getHangSxAntena1());
                cell = row.createCell(16);
                cell.setCellValue(item.getSoLuongAntena1());
                cell = row.createCell(17);
                cell.setCellValue(item.getBangTan1());
                cell = row.createCell(18);
                cell.setCellValue(item.getCauHinhPort1());
                cell = row.createCell(19);
                cell.setCellValue(item.getCauHinhGain1());
                cell = row.createCell(20);
                cell.setCellValue(item.getCauHinhBeamWidth1());
                cell = row.createCell(21);
                cell.setCellValue(item.getTrongLuong1());
                cell = row.createCell(22);
                cell.setCellValue(item.getKichCoCao1());
                cell = row.createCell(23);
                cell.setCellValue(item.getKichCoRong1());
                cell = row.createCell(24);
                cell.setCellValue(item.getKichCoSau1());
                cell = row.createCell(25);
                cell.setCellValue(item.getDoCaoLapDat1());

                cell = row.createCell(26);
                cell.setCellValue(item.getLoaiAntena2());
                cell = row.createCell(27);
                cell.setCellValue(item.getTenAntena2());
                cell = row.createCell(28);
                cell.setCellValue(item.getHangSxAntena2());
                cell = row.createCell(29);
                cell.setCellValue(item.getSoLuongAntena2());
                cell = row.createCell(30);
                cell.setCellValue(item.getBangTan2());
                cell = row.createCell(31);
                cell.setCellValue(item.getCauHinhPort2());
                cell = row.createCell(32);
                cell.setCellValue(item.getCauHinhGain2());
                cell = row.createCell(33);
                cell.setCellValue(item.getCauHinhBeamWidth2());
                cell = row.createCell(34);
                cell.setCellValue(item.getTrongLuong2());
                cell = row.createCell(35);
                cell.setCellValue(item.getKichCoCao2());
                cell = row.createCell(36);
                cell.setCellValue(item.getKichCoRong2());
                cell = row.createCell(37);
                cell.setCellValue(item.getKichCoSau2());
                cell = row.createCell(38);
                cell.setCellValue(item.getDoCaoLapDat2());

                cell = row.createCell(39);
                cell.setCellValue(item.getLoaiAntena3());
                cell = row.createCell(40);
                cell.setCellValue(item.getTenAntena3());
                cell = row.createCell(41);
                cell.setCellValue(item.getHangSxAntena3());
                cell = row.createCell(42);
                cell.setCellValue(item.getSoLuongAntena3());
                cell = row.createCell(43);
                cell.setCellValue(item.getBangTan3());
                cell = row.createCell(44);
                cell.setCellValue(item.getCauHinhPort3());
                cell = row.createCell(45);
                cell.setCellValue(item.getCauHinhGain3());
                cell = row.createCell(46);
                cell.setCellValue(item.getCauHinhBeamWidth3());
                cell = row.createCell(47);
                cell.setCellValue(item.getTrongLuong3());
                cell = row.createCell(48);
                cell.setCellValue(item.getKichCoCao3());
                cell = row.createCell(49);
                cell.setCellValue(item.getKichCoRong3());
                cell = row.createCell(50);
                cell.setCellValue(item.getKichCoSau3());
                cell = row.createCell(51);
                cell.setCellValue(item.getDoCaoLapDat3());

                cell = row.createCell(52);
                cell.setCellValue(item.getNgayDapUngDuKienAntena());
                cell = row.createCell(53);
                cell.setCellValue(item.getNgayDapUngThucTeAntena());

            }
            fin.close();

            File file = new File(folderTemp + File.separator + "update_qh_netx_result" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
