/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.excelBuilder;

import com.vnpt.media.rims.bean.Cell2GReportBO;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author VNP
 */
public class Cell2GBuilder extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<Cell2GReportBO> listCell2GReportBO = (List<Cell2GReportBO>) model.get("listCell2GReportBO");

        // create a new Excel sheet
        POIFSFileSystem fs = new POIFSFileSystem(
                new FileInputStream("/WEB-INF/excel-templates/danh_sach_khach_hang-template-v1.xls"));
        workbook = new HSSFWorkbook(fs, true);
        
//        POIFSFileSystem fs = new POIFSFileSystem(
//                    new FileInputStream("danh_sach_khach_hang-template-v1.xls"));
//            HSSFWorkbook wb = new HSSFWorkbook(fs, true);
            HSSFSheet sheet = workbook.getSheet("Sheet1");
            
            
                    sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
//        style.setFillForegroundColor(HSSFColor.BLUE.index);    
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFPalette palette = hwb.getCustomPalette();
        HSSFColor myColor = palette.findSimilarColor(66, 206, 199);
        style.setFillForegroundColor(myColor.getIndex());

        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);

        // create header row
        HSSFRow header = sheet.createRow(0);
        header.setRowStyle(style);

        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                0, //last row  (0-based)
                0, //first column (0-based)
                3 //last column  (0-based)
        ));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 9));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 17));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 18, 19));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 20, 27));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 28, 34));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 35, 36));
        
        header.createCell(0).setCellValue("Khu vực");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Thông tin khai sinh");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Thông tin trên OMC");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Thông tin trạng thái");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Thông tin RF");
        header.getCell(4).setCellStyle(style);

        header.createCell(5).setCellValue("Thông tin cấu hình");
        header.getCell(5).setCellStyle(style);

        header.createCell(6).setCellValue("Thông tin chất lượng");
        header.getCell(6).setCellStyle(style);
        
        HSSFRow header1 = sheet.createRow(1);
        header1.createCell(0).setCellValue("Khu vực");
        // create data rows
        int rowCount = 2;

        for (Cell2GReportBO temp : listCell2GReportBO) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(temp.getNgayHoatDong());
//            aRow.createCell(1).setCellValue(temp.getAuthor());
//            aRow.createCell(2).setCellValue(temp.getIsbn());
//            aRow.createCell(3).setCellValue(temp.getPublishedDate());
//            aRow.createCell(4).setCellValue(temp.getPrice());
        }
//        workbook.
    }

//    @Override
//    protected void buildExcelDocument(Map<String, Object> model,
//            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        // get data model which is passed by the Spring container
//        List<Cell2GReportBO> listCell2GReportBO = (List<Cell2GReportBO>) model.get("listCell2GReportBO");
//
//        // create a new Excel sheet
//        HSSFSheet sheet = workbook.createSheet("Java Books");
//        sheet.setDefaultColumnWidth(30);
//
//        // create style for header cells
//        CellStyle style = workbook.createCellStyle();
//        Font font = workbook.createFont();
//        font.setFontName("Arial");
////        style.setFillForegroundColor(HSSFColor.BLUE.index);    
//        HSSFWorkbook hwb = new HSSFWorkbook();
//        HSSFPalette palette = hwb.getCustomPalette();
//        HSSFColor myColor = palette.findSimilarColor(66, 206, 199);
//        style.setFillForegroundColor(myColor.getIndex());
//
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        font.setColor(HSSFColor.BLACK.index);
//        style.setFont(font);
//
//        // create header row
//        HSSFRow header = sheet.createRow(0);
//        header.setRowStyle(style);
//
//        sheet.addMergedRegion(new CellRangeAddress(
//                0, //first row (0-based)
//                0, //last row  (0-based)
//                0, //first column (0-based)
//                3 //last column  (0-based)
//        ));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 9));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 17));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 18, 19));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 20, 27));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 28, 34));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 35, 36));
//        
//        header.createCell(0).setCellValue("Khu vực");
//        header.getCell(0).setCellStyle(style);
//
//        header.createCell(1).setCellValue("Thông tin khai sinh");
//        header.getCell(1).setCellStyle(style);
//
//        header.createCell(2).setCellValue("Thông tin trên OMC");
//        header.getCell(2).setCellStyle(style);
//
//        header.createCell(3).setCellValue("Thông tin trạng thái");
//        header.getCell(3).setCellStyle(style);
//
//        header.createCell(4).setCellValue("Thông tin RF");
//        header.getCell(4).setCellStyle(style);
//
//        header.createCell(5).setCellValue("Thông tin cấu hình");
//        header.getCell(5).setCellStyle(style);
//
//        header.createCell(6).setCellValue("Thông tin chất lượng");
//        header.getCell(6).setCellStyle(style);
//        
//        HSSFRow header1 = sheet.createRow(1);
//        header1.createCell(0).setCellValue("Khu vực");
//        // create data rows
//        int rowCount = 2;
//
//        for (Cell2GReportBO temp : listCell2GReportBO) {
//            HSSFRow aRow = sheet.createRow(rowCount++);
//            aRow.createCell(0).setCellValue(temp.getNgayHoatDong());
////            aRow.createCell(1).setCellValue(temp.getAuthor());
////            aRow.createCell(2).setCellValue(temp.getIsbn());
////            aRow.createCell(3).setCellValue(temp.getPublishedDate());
////            aRow.createCell(4).setCellValue(temp.getPrice());
//        }
//    }
}
