/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.bean.Cell4GConfig;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.formbean.Cell3G;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.BtsNodeB;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Cyano
 */
public class DataReport {
private static Logger logger = LogManager.getLogger(DataReport.class);
    public File writeBcll(List<BtsNodeB> btsNodeBs,
            List<Cell2G> cell2Gs, List<Cell3G> cell3Gs, List<Cell4GConfig> cell4Gs) {
        SXSSFWorkbook streamWorkbook = null;
        FileOutputStream fos = null;
        File fileResult = new File(StringUtils.getFolderTemp() + File.separator + "BCLL.xlsx");
        try {
//            FileInputStream fin = new FileInputStream(fileTemplate);
//            Workbook workbook = null;
//            workbook = new XSSFWorkbook(fin);

            streamWorkbook = new SXSSFWorkbook();
            Sheet sheet = streamWorkbook.createSheet("2G Config");
            write2Gconfig(cell2Gs, sheet);
            sheet = streamWorkbook.createSheet("3G Config");
            write3Gconfig(cell3Gs, sheet);
            sheet = streamWorkbook.createSheet("Onair");
            writeBtsNodeB(btsNodeBs, sheet);
            sheet = streamWorkbook.createSheet("4G Config");
            write4GConfig(cell4Gs, sheet);
            fos = new FileOutputStream(fileResult);
            streamWorkbook.write(fos);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                if (streamWorkbook != null) {
                    streamWorkbook.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return fileResult;
    }
    
    public void writeBcllForJob(File fileResult,List<BtsNodeB> btsNodeBs,
            List<Cell2G> cell2Gs, List<Cell3G> cell3Gs, List<Cell4GConfig> cell4Gs) {
        SXSSFWorkbook streamWorkbook = null;
        FileOutputStream fos = null;
        try {
//            FileInputStream fin = new FileInputStream(fileTemplate);
//            Workbook workbook = null;
//            workbook = new XSSFWorkbook(fin);

            streamWorkbook = new SXSSFWorkbook();
            Sheet sheet = streamWorkbook.createSheet("2G Config");
            write2Gconfig(cell2Gs, sheet);
            sheet = streamWorkbook.createSheet("3G Config");
            write3Gconfig(cell3Gs, sheet);
            sheet = streamWorkbook.createSheet("Onair");
            writeBtsNodeB(btsNodeBs, sheet);
            sheet = streamWorkbook.createSheet("4G Config");
            write4GConfig(cell4Gs, sheet);
            fos = new FileOutputStream(fileResult);
            streamWorkbook.write(fos);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                if (streamWorkbook != null) {
                    streamWorkbook.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    /*
    ghi du lieu vao sheet 2G-config
     */
    private void write2Gconfig(List<Cell2G> list, Sheet sheet) {
        try {
            if (list == null) {
                return;
            }
            Iterator<Cell2G> iterator = list.iterator();

            int rowIndex = 1;
            Cell cell = null;
            Row row = null;
            /*
            tao header 
             */
            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("Vendor");
            cell = row.createCell(1);
            cell.setCellValue("Type1");
            cell = row.createCell(2);
            cell.setCellValue("Type2");
            cell = row.createCell(3);
            cell.setCellValue("MBSC");
            cell = row.createCell(4);
            cell.setCellValue("BTS Name");
            cell = row.createCell(5);
            cell.setCellValue("Cell name");
            cell = row.createCell(6);
            cell.setCellValue("Freq Band");
            cell = row.createCell(7);
            cell.setCellValue("LAC");
            cell = row.createCell(8);
            cell.setCellValue("CI");
            cell = row.createCell(9);
            cell.setCellValue("BSIC");
            cell = row.createCell(10);
            cell.setCellValue("BCCH");
            cell = row.createCell(11);
            cell.setCellValue("Frequency");
            cell = row.createCell(12);
            cell.setCellValue("Config");
            while (iterator.hasNext()) {

                Cell2G cell2G = iterator.next();
                row = sheet.createRow(rowIndex++);

                //vendor
                if (cell2G.getVendor() != null) {
                    cell = row.createCell(0);
                    cell.setCellValue(cell2G.getVendor());
                }
                //type1
                if (cell2G.getType1() != null) {
                    cell = row.createCell(1);
                    cell.setCellValue(cell2G.getType1());
                }
                //type2
                if (cell2G.getType2() != null) {
                    cell = row.createCell(2);
                    cell.setCellValue(cell2G.getType2());
                }
                //mbsc
                if (cell2G.getmBsc() != null) {
                    cell = row.createCell(3);
                    cell.setCellValue(cell2G.getmBsc());
                }
                //btsName
                if (cell2G.getBtsName() != null) {
                    cell = row.createCell(4);
                    cell.setCellValue(cell2G.getBtsName());
                }
                //cellName
                if (cell2G.getCellName() != null) {
                    cell = row.createCell(5);
                    cell.setCellValue(cell2G.getCellName());
                }
                //freqBand
                if (cell2G.getFreqBand() != null) {
                    cell = row.createCell(6);
                    cell.setCellValue(cell2G.getFreqBand());
                }
                //lac
                if (cell2G.getLac() != null) {
                    cell = row.createCell(7);
                    cell.setCellValue(cell2G.getLac());
                }
                //ci
                if (cell2G.getCi() != null) {
                    cell = row.createCell(8);
                    cell.setCellValue(cell2G.getCi());
                }
                //bsic
                if (cell2G.getBsic() != null) {
                    cell = row.createCell(9);
                    cell.setCellValue(cell2G.getBsic());
                }
                //bcch

                if (cell2G.getBcch() != null) {
                    cell = row.createCell(10);
                    cell.setCellValue(cell2G.getBcch());
                }
                //frequency
                if (cell2G.getFrequency() != null) {
                    cell = row.createCell(11);
                    cell.setCellValue(cell2G.getFrequency());
                }
                //config
                if (cell2G.getConfig() != null) {
                    cell = row.createCell(12);
                    cell.setCellValue(cell2G.getConfig());
                }

            }
        } catch (Exception e) {

        }
    }

    /*
    ghi du lieu vao sheet 3G-config
     */
    private void write3Gconfig(List<Cell3G> list, Sheet sheet) {
        try {
            if (list == null) {
                return;
            }
            Iterator<Cell3G> iterator = list.iterator();
            Cell cell = null;
            Row row = null;
            int rowIndex = 1;
            //header
            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("Vendor");
            cell = row.createCell(1);
            cell.setCellValue("Type1");
            cell = row.createCell(2);
            cell.setCellValue("Type2");
            cell = row.createCell(3);
            cell.setCellValue("MBSC");
            cell = row.createCell(4);
            cell.setCellValue("NodeB Name");
            cell = row.createCell(5);
            cell.setCellValue("Cell Type");
            cell = row.createCell(6);
            cell.setCellValue("Cell Name");
            cell = row.createCell(7);
            cell.setCellValue("LAC");
            cell = row.createCell(8);
            cell.setCellValue("CI");
            cell = row.createCell(9);
            cell.setCellValue("DL Primary Scrambling Code");
            cell = row.createCell(10);
            cell.setCellValue("Freq");

            while (iterator.hasNext()) {
                Cell3G cell3G = iterator.next();
                row = sheet.createRow(rowIndex++);
                //vendor
                if (cell3G.getVendor() != null) {
                    cell = row.createCell(0);
                    cell.setCellValue(cell3G.getVendor());
                }
                //type1
                if (cell3G.getType1() != null) {
                    cell = row.createCell(1);
                    cell.setCellValue(cell3G.getType1());
                }
                //type2
                if (cell3G.getType2() != null) {
                    cell = row.createCell(2);
                    cell.setCellValue(cell3G.getType2());
                }
                //mbsc
                if (cell3G.getmBsc() != null) {
                    cell = row.createCell(3);
                    cell.setCellValue(cell3G.getmBsc());
                }
                //nodeBName
                if (cell3G.getNodeBname() != null) {
                    cell = row.createCell(4);
                    cell.setCellValue(cell3G.getNodeBname());
                }
                //cellType
                if (cell3G.getCellType() != null) {
                    cell = row.createCell(5);
                    cell.setCellValue(cell3G.getCellType());
                }
                //cellName
                if (cell3G.getCellName() != null) {
                    cell = row.createCell(6);
                    cell.setCellValue(cell3G.getCellName());
                }
                //Lac
                if (cell3G.getLac() != null) {
                    cell = row.createCell(7);
                    cell.setCellValue(cell3G.getLac());
                }
                //ci
                if (cell3G.getCi() != null) {
                    cell = row.createCell(8);
                    cell.setCellValue(cell3G.getCi());
                }
                //dl primary scrambling code
                if (cell3G.getDlpsc() != null) {
                    cell = row.createCell(9);
                    cell.setCellValue(cell3G.getDlpsc());
                }
                //freq
                if (cell3G.getFreq() != null) {
                    cell = row.createCell(10);
                    cell.setCellValue(cell3G.getFreq());
                }

            }
        } catch (Exception e) {

        }
    }

    /*
    ghi du lieu vao sheet Onair
     */
    private void writeBtsNodeB(List<BtsNodeB> btsNodeBs, Sheet sheet) {
        try {
            if (btsNodeBs == null) {
                return;
            }
            Iterator<BtsNodeB> iterator = btsNodeBs.iterator();

            int rowIndex = 1;
            Row row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("Year");
            cell = row.createCell(1);
            cell.setCellValue("Week");
            cell = row.createCell(2);
            cell.setCellValue("Vendor");
            cell = row.createCell(3);
            cell.setCellValue("Type1");
            cell = row.createCell(4);
            cell.setCellValue("Type2");
            cell = row.createCell(5);
            cell.setCellValue("BSC");
            cell = row.createCell(6);
            cell.setCellValue("BTS");
            cell = row.createCell(7);
            cell.setCellValue("Site Type");
            cell = row.createCell(8);
            cell.setCellValue("PCode");
            cell = row.createCell(9);
            cell.setCellValue("PName");
            cell = row.createCell(10);
            cell.setCellValue("HSDPA");
            while (iterator.hasNext()) {
                BtsNodeB btsNodeB = iterator.next();
                row = sheet.createRow(rowIndex++);
                //year

                cell = row.createCell(0);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                cell.setCellValue(year);

                //week
                cell = row.createCell(1);
                int weekOfyear = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
                cell.setCellValue(weekOfyear);

                //vendor
                if (btsNodeB.getVendor() != null) {
                    cell = row.createCell(2);
                    cell.setCellValue(btsNodeB.getVendor());
                }
                //type1
                if (btsNodeB.getType1() != null) {
                    cell = row.createCell(3);
                    cell.setCellValue(btsNodeB.getType1());
                }
                //type2
                if (btsNodeB.getType2() != null) {
                    cell = row.createCell(4);
                    cell.setCellValue(btsNodeB.getType2());
                }
                //bsc
                if (btsNodeB.getBscRnc() != null) {
                    cell = row.createCell(5);
                    cell.setCellValue(btsNodeB.getBscRnc());
                }
                //nodeBName
                if (btsNodeB.getBtsNodeB() != null) {
                    cell = row.createCell(6);
                    cell.setCellValue(btsNodeB.getBtsNodeB());
                }
                //site type
                if (btsNodeB.getSiteType() != null) {
                    cell = row.createCell(7);
                    cell.setCellValue(btsNodeB.getSiteType());
                }
                //pc code
                if (btsNodeB.getPcCode() != null) {
                    cell = row.createCell(8);
                    cell.setCellValue(btsNodeB.getPcCode());
                }
                //vnp
                if (btsNodeB.getVnp() != null) {
                    cell = row.createCell(9);
                    cell.setCellValue(btsNodeB.getVnp());
                }
                //hsdpa
                if (btsNodeB.getHsdpa() != null) {
                    cell = row.createCell(10);
                    cell.setCellValue(btsNodeB.getHsdpa());
                }

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void write4GConfig(List<Cell4GConfig> list, Sheet sheet) {
        try {
            if (list == null) {
                return;
            }
            Iterator<Cell4GConfig> iterator = list.iterator();

            int rowIndex = 1;
            Row row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("Vendor");
            cell = row.createCell(1);
            cell.setCellValue("EnodeB");
            cell = row.createCell(2);
            cell.setCellValue("Cell Name");
            cell = row.createCell(3);
            cell.setCellValue("tac");
            cell = row.createCell(4);
            cell.setCellValue("phyCellId");
            cell = row.createCell(5);
            cell.setCellValue("lcrId");

            while (iterator.hasNext()) {
                Cell4GConfig cell4g = iterator.next();
                row = sheet.createRow(rowIndex++);

                cell = row.createCell(0);
                cell.setCellValue(cell4g.getVendor() == null ? "" : cell4g.getVendor());

                cell = row.createCell(1);
                cell.setCellValue(cell4g.getEnodeb_name() == null ? "" : cell4g.getEnodeb_name());

                cell = row.createCell(2);
                cell.setCellValue(cell4g.getCell_name() == null ? "" : cell4g.getCell_name());

                cell = row.createCell(3);
                cell.setCellValue(cell4g.getTac() == null ? "" : cell4g.getTac());

                cell = row.createCell(4);
                cell.setCellValue(cell4g.getPhyCellId() == null ? "" : cell4g.getPhyCellId());

                cell = row.createCell(5);
                cell.setCellValue(cell4g.getLcrId() == null ? "" : cell4g.getLcrId());

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
