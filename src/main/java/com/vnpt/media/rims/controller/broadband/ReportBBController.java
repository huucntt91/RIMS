/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.ChiTietCardBO;
import com.vnpt.media.rims.bean.EthernetBcBO;
import com.vnpt.media.rims.bean.ManETaiNguyenMangCiscoBO;
import com.vnpt.media.rims.bean.ManETaiNguyenMangHuaweiBO;
import com.vnpt.media.rims.bean.ManETaiNguyenMangJuniperBO;
import com.vnpt.media.rims.bean.ModuleQuangBcBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.ManeFacade;
import com.vnpt.media.rims.facade.ReportBBFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/reportBB")
public class ReportBBController {
    private static Logger logger = LogManager.getLogger(ReportBBController.class);
    private static final String MANE_CHI_TIET_CARD_TP = "broadband/report/mane_chi_tiet_card";
    private static final String ACCESS_CHI_TIET_CARD = "broadband/report/access_chi_tiet_card";
    private static final String VN2_CHI_TIET_CARD = "broadband/report/vn2_chi_tiet_card";
    private static final String VN2_MODULE_QUANG_THEOTB = "broadband/report/vn2_module_quang_theotb";
    private static final String VN2_MODULE_QUANG_THEOTP = "broadband/report/vn2_module_quang_theotp";
    private static final String VN2_ETHERNET_THEOTP = "broadband/report/vn2_ethernet_theotp";
    private static final String VN2_ETHERNET_THEOTB = "broadband/report/vn2_ethernet_theotb";
    private static final String MANE_TAINGUYENMANG = "broadband/report/mane_tainguyenmang";
    private static final String VN2_TAINGUYENMANG = "broadband/report/vn2_tainguyenmang";
    //list khu vuc
    @ModelAttribute("khuvucList")
    public List findAllKhuVuc(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllKhuVuc(String.join(",", tinhManager));
    }

    //
    //list tinh
    @RequestMapping(value = "/getTinhTp", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getTinhTp(@RequestParam(value = "khuVucId", required = false) String khuVucId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<TinhBO> list = null;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            list = facade.findTinhByKv(khuVucId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
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
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    /*
    cac bao cao ve MANE
     */
    //bao cao chi tiet card theo tinh/tp
    @RequestMapping(value = "/preChiTietCardTp", method = RequestMethod.GET)
    public String preChiTietCardTp(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "typeId", required = false) String typeId
    )  {
        typeId = "1,2,3";
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = ReportBBFacade.countChiTietCardTp(tinhTpId, typeId);
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
            return ("redirect:/reportBB/preChiTietCardTp?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportBB/preChiTietCardTp?page=" + pageInt);
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
        List<ChiTietCardBO> list = ReportBBFacade.findChiTietCardTp(startRow, endRow, tinhTpId, typeId);
        mm.addAttribute("list", list);
        mm.put("typeId", typeId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("startRow", startRow);
        return MANE_CHI_TIET_CARD_TP;
    }

    @RequestMapping(value = "/reportChiTietCardTp", method = RequestMethod.GET)
    public void reportChiTietCardTp(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {
            typeId = "1,2,3";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<ChiTietCardBO> datas = ReportBBFacade.reportChiTietCardTp(tinhTpId, typeId);
            
            File fileResult = writeChiTietCardTp(dataDirectory + "/ChiTietCardTP.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private File writeChiTietCardTp(String fileTemplate, List<ChiTietCardBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("ChiTietCardTP.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 2;
                Cell cell = null;
                Row row = null;
                for (ChiTietCardBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getTenTinh() == null ? "" : it.getTenTinh());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getCode() == null ? "" : it.getCode());

                    cell = row.createCell(2);
                    cell.setCellValue(it.getIp() == null ? "" : it.getIp());

                    cell = row.createCell(3);
                    cell.setCellValue(it.getTvendorName() == null ? "" : it.getTvendorName());

                    cell = row.createCell(4);
                    cell.setCellValue(it.getDongTBiName() == null ? "" : it.getDongTBiName());

                    cell = row.createCell(5);
                    cell.setCellValue(it.getFrame() == 0 ? "" : String.valueOf(it.getFrame()));

                    cell = row.createCell(6);
                    cell.setCellValue(it.getSlot() == 0 ? "" : String.valueOf(it.getSlot()));

                    cell = row.createCell(7);
                    cell.setCellValue(it.getCardName() == null ? "" : it.getCardName());

                    cell = row.createCell(8);
                    cell.setCellValue(it.getSerialNumber() == null ? "" : it.getSerialNumber());

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
                ex.printStackTrace();
            }

        }

        return result;
    }

    /*
    Cac bao cao ve Access
     */
    //bao cao chi tiet card theo tinh/tp
    @RequestMapping(value = "/preChiTietCardAccess", method = RequestMethod.GET)
    public String preChiTietCardAcess(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "typeId", required = false) String typeId
    )  {
        typeId = "4,5,6,13,14";
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = ReportBBFacade.countChiTietCardTp(tinhTpId, typeId);
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
            return ("redirect:/reportBB/preChiTietCardAccess?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportBB/preChiTietCardAccess?page=" + pageInt);
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
        List<ChiTietCardBO> list = ReportBBFacade.findChiTietCardTp(startRow, endRow, tinhTpId, typeId);
        mm.addAttribute("list", list);
        mm.put("typeId", typeId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("startRow", startRow);
        return ACCESS_CHI_TIET_CARD;
    }

    @RequestMapping(value = "/reportChiTietCardAccess", method = RequestMethod.GET)
    public void reportChiTietCardAccess(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {
            typeId = "4,5,6,13,14";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<ChiTietCardBO> datas = ReportBBFacade.reportChiTietCardTp(tinhTpId, typeId);
            
            File fileResult = writeChiTietCardTp(dataDirectory + "/ChiTietCardTP.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /*
    cac bao cao ve VN2
     */
    @RequestMapping(value = "/preChiTietCardVn2", method = RequestMethod.GET)
    public String preChiTietCardVn2(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "typeId", required = false) String typeId
    )  {
        typeId = "7,8,9,10,11";
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = ReportBBFacade.countChiTietCardTp(tinhTpId, typeId);
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
            return ("redirect:/reportBB/preChiTietCardVn2?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportBB/preChiTietCardVn2?page=" + pageInt);
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
        List<ChiTietCardBO> list = ReportBBFacade.findChiTietCardTp(startRow, endRow, tinhTpId, typeId);
        mm.addAttribute("list", list);
        mm.put("typeId", typeId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("startRow", startRow);
        return VN2_CHI_TIET_CARD;
    }

    @RequestMapping(value = "/reportChiTietCardVn2", method = RequestMethod.GET)
    public void reportChiTietCardVn2(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {
            typeId = "7,8,9,10,11";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<ChiTietCardBO> datas = ReportBBFacade.reportChiTietCardTp(tinhTpId, typeId);
            
            File fileResult = writeChiTietCardTp(dataDirectory + "/ChiTietCardTP.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    // bao cao ve module quang vn2
    @RequestMapping(value = "/preModuleQuangTheoTbVn2", method = RequestMethod.GET)
    public String preModuleQuangTheoTbVn2(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    )  {
        typeId = "7,8,9,10,11";
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
            return ("redirect:/reportBB/preModuleQuangTheoTbVn2?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportBB/preModuleQuangTheoTbVn2?page=" + pageInt);
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
        return VN2_MODULE_QUANG_THEOTB;
    }

    @RequestMapping(value = "/reportModuleQuangTheoTbVn2", method = RequestMethod.GET)
    public void reportModuleQuangTheoTbVn2(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String typeId = "7,8,9,10,11";
            String tinhTpId = request.getParameter("tinhTpId");

            List<ModuleQuangBcBO> datas = ManeFacade.report(tinhTpId, typeId);
            
            File fileResult = writeModuleQuangTheoTbVn2(dataDirectory + "/Vn2ThongKeModuleQuangTheoTB.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private File writeModuleQuangTheoTbVn2(String fileTemplate, List<ModuleQuangBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("Vn2ThongKeModuleQuangTheoTB.xlsx");
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

                    cell = row.createCell(30);
                    cell.setCellValue(it.getOther() == 0 ? "" : String.valueOf(it.getOther()));

                    cell = row.createCell(31);
                    cell.setCellValue(it.getNotUseOther() == 0 ? "" : String.valueOf(it.getNotUseOther()));

                    cell = row.createCell(32);
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
                ex.printStackTrace();
            }

        }

        return result;
    }

    @RequestMapping(value = "/preModuleQuangTheoTpVn2", method = RequestMethod.GET)
    public String preModuleQuangTheoTpVn2(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId
    )  {
        typeId = "7,8,9,10,11";
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
            return ("redirect:/reportBB/preModuleQuangTheoTpVn2?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportBB/preModuleQuangTheoTpVn2?page=" + pageInt);
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
        return VN2_MODULE_QUANG_THEOTP;
    }

    @RequestMapping(value = "/reportModuleQuangTheoTpVn2", method = RequestMethod.GET)
    public void reportModuleQuangTheoTpVn2(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            String typeId = "7,8,9,10,11";
            String tinhTpId = request.getParameter("tinhTpId");

            List<ModuleQuangBcBO> datas = ManeFacade.reportTp(typeId, tinhTpId);
            File fileResult = writeModuleQuangTheoTPVn2(dataDirectory + "/Vn2ThongKeModuleQuangTheoTP.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private File writeModuleQuangTheoTPVn2(String fileTemplate, List<ModuleQuangBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("Vn2ThongKeModuleQuangTheoTP.xlsx");
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

                    cell = row.createCell(29);
                    cell.setCellValue(it.getOther() == 0 ? "" : String.valueOf(it.getOther()));

                    cell = row.createCell(30);
                    cell.setCellValue(it.getNotUseOther() == 0 ? "" : String.valueOf(it.getNotUseOther()));

                    cell = row.createCell(31);
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
                ex.printStackTrace();
            }

        }

        return result;
    }

//
    @RequestMapping(value = "/preEthernetTbVn2", method = RequestMethod.GET)
    public String preEthernetTbVn2(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId)  {
        typeId = "7,8,9,10,11";
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = ReportBBFacade.countEthernetTbVn2(tinhTpId, typeId);
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
            return ("redirect:/reportBB/preEthernetTbVn2?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportBB/preEthernetTbVn2?page=" + pageInt);
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
        List<EthernetBcBO> list = ReportBBFacade.findEthernetTbVn2(startRow, endRow, tinhTpId, typeId);
        mm.addAttribute("list", list);
        mm.put("typeId", typeId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("startRow", startRow);
        return VN2_ETHERNET_THEOTB;
    }

    @RequestMapping(value = "/ethernetTbVn2", method = RequestMethod.GET)
    public void ethernetTbVn2(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<EthernetBcBO> datas = ReportBBFacade.ethernetTbVn2(tinhTpId, typeId);
            
            File fileResult = writeEthernetTBVn2(dataDirectory + "/Vn2ThongKeEthernetTB.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private File writeEthernetTBVn2(String fileTemplate, List<EthernetBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("Vn2ThongKeEthernetTB.xlsx");
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

                    cell = row.createCell(15);
                    cell.setCellValue(it.getPort100G() == 0 ? "" : String.valueOf(it.getPort100G()));

                    cell = row.createCell(16);
                    cell.setCellValue(it.getUse100G() == 0 ? "" : String.valueOf(it.getUse100G()));

                    cell = row.createCell(17);
                    cell.setCellValue(it.getNotUse10G() == 0 ? "" : String.valueOf(it.getNotUse10G()));

                    cell = row.createCell(18);
                    cell.setCellValue(it.getCfp100G() == 0 ? "" : String.valueOf(it.getCfp100G()));

                    cell = row.createCell(19);
                    cell.setCellValue(it.getUseCfp100G() == 0 ? "" : String.valueOf(it.getUseCfp100G()));

                    cell = row.createCell(20);
                    cell.setCellValue(it.getNotUseCfp100G() == 0 ? "" : String.valueOf(it.getNotUseCfp100G()));

                    cell = row.createCell(21);
                    cell.setCellValue(it.getStm16() == 0 ? "" : String.valueOf(it.getStm16()));

                    cell = row.createCell(22);
                    cell.setCellValue(it.getUseStm16() == 0 ? "" : String.valueOf(it.getUseStm16()));

                    cell = row.createCell(23);
                    cell.setCellValue(it.getNotUseStm16() == 0 ? "" : String.valueOf(it.getNotUseStm16()));

                    cell = row.createCell(24);
                    cell.setCellValue(it.getSfpOc48() == 0 ? "" : String.valueOf(it.getSfpOc48()));

                    cell = row.createCell(25);
                    cell.setCellValue(it.getUseSfpOc48() == 0 ? "" : String.valueOf(it.getUseSfpOc48()));

                    cell = row.createCell(26);
                    cell.setCellValue(it.getNotUseSfpOc48() == 0 ? "" : String.valueOf(it.getNotUseSfpOc48()));

                    cell = row.createCell(27);
                    cell.setCellValue(it.getStm64() == 0 ? "" : String.valueOf(it.getStm64()));

                    cell = row.createCell(28);
                    cell.setCellValue(it.getUseStm64() == 0 ? "" : String.valueOf(it.getUseStm64()));

                    cell = row.createCell(29);
                    cell.setCellValue(it.getNotUseStm64() == 0 ? "" : String.valueOf(it.getNotUseStm64()));

                    cell = row.createCell(30);
                    cell.setCellValue(it.getSfpOc192() == 0 ? "" : String.valueOf(it.getSfpOc192()));

                    cell = row.createCell(31);
                    cell.setCellValue(it.getUseSfpOc192() == 0 ? "" : String.valueOf(it.getUseSfpOc192()));

                    cell = row.createCell(32);
                    cell.setCellValue(it.getNotUseSfpOc192() == 0 ? "" : String.valueOf(it.getNotUseSfpOc192()));
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
                ex.printStackTrace();
            }

        }

        return result;
    }

    @RequestMapping(value = "/preEthernetTpVn2", method = RequestMethod.GET)
    public String preEthernetTpVn2(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId)  {
        typeId = "7,8,9,10,11";
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = ReportBBFacade.countEthernetTpVn2(tinhTpId, typeId);
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
            return ("redirect:/reportBB/preEthernetTpVn2?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportBB/preEthernetTpVn2?page=" + pageInt);
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
        List<EthernetBcBO> list = ReportBBFacade.findEthernetTpVn2(startRow, endRow, tinhTpId, typeId);
        mm.addAttribute("list", list);
        mm.put("typeId", typeId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("startRow", startRow);
        return VN2_ETHERNET_THEOTP;
    }

    @RequestMapping(value = "/ethernetTpVn2", method = RequestMethod.GET)
    public void ethernetTpVn2(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {
            typeId = "7,8,9,10,11";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<EthernetBcBO> datas = ReportBBFacade.ethernetTpVn2(tinhTpId, typeId);
            
            File fileResult = writeEthernetTPVn2(dataDirectory + "/Vn2ThongKeEthernetTP.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private File writeEthernetTPVn2(String fileTemplate, List<EthernetBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("Vn2ThongKeEthernetTP.xlsx");
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
                    //
                    cell = row.createCell(13);
                    cell.setCellValue(it.getPort100G() == 0 ? "" : String.valueOf(it.getPort100G()));

                    cell = row.createCell(14);
                    cell.setCellValue(it.getUse100G() == 0 ? "" : String.valueOf(it.getUse100G()));

                    cell = row.createCell(15);
                    cell.setCellValue(it.getNotUse10G() == 0 ? "" : String.valueOf(it.getNotUse10G()));

                    cell = row.createCell(16);
                    cell.setCellValue(it.getCfp100G() == 0 ? "" : String.valueOf(it.getCfp100G()));

                    cell = row.createCell(17);
                    cell.setCellValue(it.getUseCfp100G() == 0 ? "" : String.valueOf(it.getUseCfp100G()));

                    cell = row.createCell(18);
                    cell.setCellValue(it.getNotUseCfp100G() == 0 ? "" : String.valueOf(it.getNotUseCfp100G()));

                    cell = row.createCell(19);
                    cell.setCellValue(it.getStm16() == 0 ? "" : String.valueOf(it.getStm16()));

                    cell = row.createCell(20);
                    cell.setCellValue(it.getUseStm16() == 0 ? "" : String.valueOf(it.getUseStm16()));

                    cell = row.createCell(21);
                    cell.setCellValue(it.getNotUseStm16() == 0 ? "" : String.valueOf(it.getNotUseStm16()));

                    cell = row.createCell(22);
                    cell.setCellValue(it.getSfpOc48() == 0 ? "" : String.valueOf(it.getSfpOc48()));

                    cell = row.createCell(23);
                    cell.setCellValue(it.getUseSfpOc48() == 0 ? "" : String.valueOf(it.getUseSfpOc48()));

                    cell = row.createCell(24);
                    cell.setCellValue(it.getNotUseSfpOc48() == 0 ? "" : String.valueOf(it.getNotUseSfpOc48()));

                    cell = row.createCell(25);
                    cell.setCellValue(it.getStm64() == 0 ? "" : String.valueOf(it.getStm64()));

                    cell = row.createCell(26);
                    cell.setCellValue(it.getUseStm64() == 0 ? "" : String.valueOf(it.getUseStm64()));

                    cell = row.createCell(27);
                    cell.setCellValue(it.getNotUseStm64() == 0 ? "" : String.valueOf(it.getNotUseStm64()));

                    cell = row.createCell(28);
                    cell.setCellValue(it.getSfpOc192() == 0 ? "" : String.valueOf(it.getSfpOc192()));

                    cell = row.createCell(29);
                    cell.setCellValue(it.getUseSfpOc192() == 0 ? "" : String.valueOf(it.getUseSfpOc192()));

                    cell = row.createCell(30);
                    cell.setCellValue(it.getNotUseSfpOc192() == 0 ? "" : String.valueOf(it.getNotUseSfpOc192()));
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
                ex.printStackTrace();
            }

        }

        return result;
    }

    //thong ke tai nguyen mang mane
    @RequestMapping(value = "/preTaiNguyenMangManE", method = RequestMethod.GET)
    public String preTaiNguyenMangManE(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuVucId", required = false) String khuVucId)  {
        if (typeId == null) {
            typeId = "1";
        }
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = ReportBBFacade.countTaiNguyenMangMane(typeId, khuVucId, tinhTpId);
        
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
            return ("redirect:/reportBB/preTaiNguyenMangManE?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportBB/preTaiNguyenMangManE?page=" + pageInt);
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
        List<Object> list = ReportBBFacade.findTaiNguyenMangMane(startRow, endRow, typeId, khuVucId, tinhTpId);
        mm.addAttribute("list", list);
        mm.put("typeId", typeId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("khuVucId", khuVucId);
        mm.put("startRow", startRow);
        return MANE_TAINGUYENMANG;
    }
    
    //thong ke tai nguyen mang VN2
    @RequestMapping(value = "/vn2/tainguyenmang", method = RequestMethod.GET)
    public String taiNguyenMang(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuVucId", required = false) String khuVucId)  {
        if(typeId == null) typeId = "1";
        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);
        int totalRows = 0;
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
            return ("redirect:/reportBB/vn2/tainguyenmang?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/reportBB/vn2/tainguyenmang?page=" + pageInt);
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
        List<EthernetBcBO> list = null;
        mm.addAttribute("list", list);
        mm.put("typeId", typeId);
        mm.put("tinhTpId", tinhTpId);
        mm.put("khuVucId", khuVucId);
        mm.put("startRow", startRow);
        return VN2_TAINGUYENMANG;
    }


    @RequestMapping(value = "/taiNguyenMangManE", method = RequestMethod.GET)
    public void taiNguyenMangManE(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "khuVucId", required = false) String khuVucId) {
        try {
            
            
            
            if(tinhTpId.equalsIgnoreCase("null")) tinhTpId = null;
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<Object> huawei = ReportBBFacade.taiNguyenMangMane("1", khuVucId, tinhTpId);
            List<Object> cisco = ReportBBFacade.taiNguyenMangMane("2", khuVucId, tinhTpId);
            List<Object> juniper = ReportBBFacade.taiNguyenMangMane("3", khuVucId, tinhTpId);
            
            File fileResult = writeTaiNguyenMangManE(dataDirectory + "/TaiNguyenMangManE.xlsx", cisco, huawei, juniper);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private File writeTaiNguyenMangManE(String fileTemplate, List<Object> cisco,
            List<Object> huawei, List<Object> juniper) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("TaiNguyenMangManE.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet0 = workbook.getSheet("Huawei");
            if (huawei != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                int i = 1;
                for (Object object : huawei) {
                    ManETaiNguyenMangHuaweiBO it = (ManETaiNguyenMangHuaweiBO) object;
                    row = sheet0.createRow(rowIndex++);
                    cell = row.createCell(0);
                    cell.setCellValue(i);
                    cell = row.createCell(1);
                    cell.setCellValue(it.getKhuVuc());
                    cell = row.createCell(2);
                    cell.setCellValue(it.getTenTinh());
                    cell = row.createCell(3);
                    cell.setCellValue(it.getCode());
                    cell = row.createCell(4);
                    cell.setCellValue(it.getVendor());
                    cell = row.createCell(5);
                    cell.setCellValue(it.getTypeName());
                    i++;
                }
            }
            //
            Sheet sheet1 = workbook.getSheet("Cisco");
            if (cisco != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                int i = 1;
                for (Object object : cisco) {
                    ManETaiNguyenMangCiscoBO it = (ManETaiNguyenMangCiscoBO) object;
                    row = sheet1.createRow(rowIndex++);
                    cell = row.createCell(0);
                    cell.setCellValue(i);
                    cell = row.createCell(1);
                    cell.setCellValue(it.getKhuVuc());
                    cell = row.createCell(2);
                    cell.setCellValue(it.getTenTinh());
                    cell = row.createCell(3);
                    cell.setCellValue(it.getCode());
                    cell = row.createCell(4);
                    cell.setCellValue(it.getVendor());
                    cell = row.createCell(5);
                    cell.setCellValue(it.getTypeName());
                    i++;
                }
            }

            Sheet sheet2 = workbook.getSheet("Juniper");
            if (juniper != null) {
                int rowIndex = 3;
                Cell cell = null;
                Row row = null;
                int i = 1;
                for (Object object : juniper) {
                    ManETaiNguyenMangJuniperBO it = (ManETaiNguyenMangJuniperBO) object;
                    row = sheet1.createRow(rowIndex++);
                    cell = row.createCell(0);
                    cell.setCellValue(i);
                    cell = row.createCell(1);
                    cell.setCellValue(it.getKhuVuc());
                    cell = row.createCell(2);
                    cell.setCellValue(it.getTenTinh());
                    cell = row.createCell(3);
                    cell.setCellValue(it.getCode());
                    cell = row.createCell(4);
                    cell.setCellValue(it.getVendor());
                    cell = row.createCell(5);
                    cell.setCellValue(it.getTypeName());
                    i++;
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
                ex.printStackTrace();
            }

        }

        return result;
    }
}
