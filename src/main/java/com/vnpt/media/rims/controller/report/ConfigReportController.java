/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.bean.BcTongHopBO;
import com.vnpt.media.rims.bean.BtsReportBO;
import com.vnpt.media.rims.bean.Cell2GReportBO;
import com.vnpt.media.rims.bean.Detail2gBO;
import com.vnpt.media.rims.bean.Detail3gBO;
import com.vnpt.media.rims.bean.NodeBReportBO;
import com.vnpt.media.rims.bean.User;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.controller.nodes.NodesController;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.LogMenuFacade;
import com.vnpt.media.rims.facade.ReportFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author VNP
 */
@Controller

@RequestMapping(value = "/report/configReport")
public class ConfigReportController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(ConfigReportController.class);
//    @ModelAttribute("filterReportList")
//    public List findFilterReport() {
//        ReportFacade facade = new ReportFacade();
//        return facade.findFilterReport();
//    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, HttpServletRequest request) {
        // log user request menu
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LogMenuFacade.logMenu(String.valueOf(user.getId()), request.getRemoteAddr(), "Báo cáo nhanh Config");
        // end log
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);

        ReportFacade facade = new ReportFacade();

        List<BcTongHopBO> list = new ArrayList<>();
        try {
            LOGGER.info("user: {}, ip: {}, call findConfigReport ", user.getUsername(), request.getRemoteAddr());
            list = facade.findConfigReport();
            LOGGER.info("user: {}, ip: {}, done findConfigReport: {}", user.getUsername(), request.getRemoteAddr(), list.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        List<BcTongHopBO> temp = new ArrayList<BcTongHopBO>();
        if (tinhManager != null && tinhManager.length > 0) {
            for (int i = 0; i < list.size(); i++) {

                for (String item : tinhManager) {
                    if (item.equals(list.get(i).getMaTinh())) {
                        temp.add(list.get(i));
                    }
                }
            }

        } else {
            temp = list;
        }

        mm.put("list", temp);

        return "report/cauhinhnhanh/list";
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        ReportFacade facade = new ReportFacade();
        
        List<BcTongHopBO> temp = new ArrayList<>();
        try {
            LOGGER.info("user: {}, ip: {}, call findConfigReport ", user.getUsername(), request.getRemoteAddr());
            temp = facade.findConfigReport();
            LOGGER.info("user: {}, ip: {}, done findConfigReport: {}", user.getUsername(), request.getRemoteAddr(), temp.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        List<Detail2gBO> detail2G = new ArrayList<>();
        try {
            LOGGER.info("user: {}, ip: {}, call findDetail2G ", user.getUsername(), request.getRemoteAddr());
            detail2G = facade.findDetail2G();
            LOGGER.info("user: {}, ip: {}, done findDetail2G: {}", user.getUsername(), request.getRemoteAddr(), detail2G.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        List<Detail3gBO> detail3G = new ArrayList<>();
        try {
            LOGGER.info("user: {}, ip: {}, call findDetail3G ", user.getUsername(), request.getRemoteAddr());
            detail3G = facade.findDetail3G();
            LOGGER.info("user: {}, ip: {}, done findDetail3G: {}", user.getUsername(), request.getRemoteAddr(), detail3G.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
        String fileName = "Template_BCCH_NHANH.xlsx";
        
        LOGGER.debug("user: {}, ip: {}, call bao cao excel cau hinh nhanh {}",user.getUsername(),request.getRemoteAddr(), Function.getInfoMemory());
        File fileTemplate = new File(dataDirectory + File.separator + fileName);
        
        File fileResult = writeBc(fileTemplate, temp, detail2G, detail3G);
        if (fileResult.exists()) {
            response.setContentType("application/excel");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                response.getOutputStream().flush();
                temp.clear();
                detail2G.clear();
                detail3G.clear();
                LOGGER.debug("user: {}, ip: {}, end bao cao excel cau hinh nhanh {}",user.getUsername(),request.getRemoteAddr(), Function.getInfoMemory());
            } catch (IOException ex) {
            }
        }
    }

    public File writeBc(File fileTemplate, List<BcTongHopBO> temp, List<Detail2gBO> detail2G, List<Detail3gBO> detail3G) {
        try {

            FileInputStream fin = new FileInputStream(fileTemplate);
            Workbook workbook = null;
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
            Sheet sheet1 = workbook.getSheetAt(1);//createSheet("2G");
            Sheet sheet2 = workbook.getSheetAt(2);//createSheet("2G");
            writeBCTongHop(temp, sheet);
            writeBC2G(detail2G, sheet1);
            writeBC3G(detail3G, sheet2);

            fin.close();
            File file = new File("bao_cao_cau_hinh_nhanh.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;

        } catch (IOException e) {

        }
        return null;
    }

    private void writeBCTongHop(List<BcTongHopBO> temp, Sheet sheet) {
        try {
            Iterator<BcTongHopBO> iterator = temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            int stt = 0;
            //header
            while (iterator.hasNext()) {
                stt += 1;
                BcTongHopBO tonghop = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                cell = row.createCell(0);
                cell.setCellValue(stt);
                cell = row.createCell(1);
                cell.setCellValue(tonghop.getTenTinh());

                cell = row.createCell(2);
                cell.setCellValue(tonghop.getSoBts());

                cell = row.createCell(3);
                cell.setCellValue(tonghop.getSoBts222());

                cell = row.createCell(4);
                cell.setCellValue(tonghop.getSoBts333());

                cell = row.createCell(5);
                cell.setCellValue(tonghop.getSoBts444());
                cell = row.createCell(6);
                cell.setCellValue(tonghop.getSoBtsKhac());
                cell = row.createCell(7);
                cell.setCellValue(tonghop.getSoNodeB());
                cell = row.createCell(8);
                cell.setCellValue(tonghop.getSoNodeB111U900());
                cell = row.createCell(9);
                cell.setCellValue(tonghop.getSoNodeB111U2100());
                cell = row.createCell(10);
                cell.setCellValue(tonghop.getSoNodeB222());
                cell = row.createCell(11);
                cell.setCellValue(tonghop.getSoNodeB333());
                cell = row.createCell(12);
                cell.setCellValue(tonghop.getSoNodeB111111());
                cell = row.createCell(13);
                cell.setCellValue(tonghop.getSoNodeB111222());
                cell = row.createCell(14);
                cell.setCellValue(tonghop.getSoNodeB111333());
                cell = row.createCell(15);
                cell.setCellValue(tonghop.getSoNodeBKhac());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void writeBC2G(List<Detail2gBO> temp, Sheet sheet) {
        try {
            Iterator<Detail2gBO> iterator = temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            //header
            int stt = 0;
            while (iterator.hasNext()) {
                stt += 1;
                Detail2gBO tonghop = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                //vendor

                cell = row.createCell(0);
                cell.setCellValue(tonghop.getTinh());

                cell = row.createCell(1);
                cell.setCellValue(tonghop.getTen());

                cell = row.createCell(2);
                cell.setCellValue(tonghop.getCauhinh());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void writeBC3G(List<Detail3gBO> temp, Sheet sheet) {
        try {
            Iterator<Detail3gBO> iterator = temp.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 3;
            int stt = 0;
            //header
            while (iterator.hasNext()) {
                stt += 1;
                Detail3gBO tonghop = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);

                cell = row.createCell(0);
                cell.setCellValue(tonghop.getTinh());

                cell = row.createCell(1);
                cell.setCellValue(tonghop.getTen());

                cell = row.createCell(2);
                cell.setCellValue(tonghop.getCauhinh());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
