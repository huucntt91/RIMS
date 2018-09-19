/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.job;

import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.AutoMailFacade;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import com.vnpt.media.rims.formbean.Cell4G;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Cyano
 */
@DisallowConcurrentExecution
public class AutoMailJob implements Job {

    private static Logger logger = LogManager.getLogger(AutoMailJob.class);
    private static final String HOST = ResourceBundle.getBundle("config", Locale.getDefault()).getString("mail_host");
    private static final String PORT = ResourceBundle.getBundle("config", Locale.getDefault()).getString("mail_port");
    private static final String USERNAME = ResourceBundle.getBundle("config", Locale.getDefault()).getString("mail_user");
    private static final String PASSWORD = ResourceBundle.getBundle("config", Locale.getDefault()).getString("mail_pass");

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            List<JobExecutionContext> jobs = jec.getScheduler().getCurrentlyExecutingJobs();
            for (JobExecutionContext job : jobs) {
                if (job.getTrigger().equals(jec.getTrigger()) && !job.getJobInstance().equals(this)) {
                    logger.info("Another job instance running, leaving : " + jec.getJobDetail().getKey().getName());
                    return;
                }
            }
            logger.info("auto mail job: {}", new Date().toString());
            ServletContext servletContext = (ServletContext) jec.getMergedJobDataMap().get("servletContext");
            String toAddress = StringUtils.getString("to_address_lac_ci");
            String subject = StringUtils.getString("subject_lac_ci");
            String message = "";
            ArrayList<String> attachFiles = new ArrayList<>();
            String folderTemp = StringUtils.getFolderTemp();
            String dataDirectory = servletContext.getRealPath("/WEB-INF/excel-templates/");
            //gui mail nhung cell 2G trung LAC-CI tren inventory
            String fileName2G = "Cell2G_Duplicate_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
            List cell2G = AutoMailFacade.findCell2G();
            message += "- Số lượng Cell2G trùng LAC-CI: ".concat(cell2G == null ? "" : String.valueOf(cell2G.size())).concat(" \t\n");
            writeDuplicateLacCi(fileName2G, new File(dataDirectory + File.separator + "CELL2G_Duplicate_LacCi.xlsx"), folderTemp, cell2G);
            attachFiles.add(folderTemp + File.separator + fileName2G);
            //gui mail nhung cell 3G trung LAC-CI tren inventory
            String fileName3G = "Cell3G_Duplicate_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
            List cell3G = AutoMailFacade.findCell3G();
            message += "- Số lượng Cell3G trùng LAC-CI: ".concat(cell3G == null ? "" : String.valueOf(cell3G.size())).concat(" \t\n");
            writeDuplicateLacCi(fileName3G, new File(dataDirectory + File.separator + "CELL3G_Duplicate_LacCi.xlsx"), folderTemp, cell3G);
            attachFiles.add(folderTemp + File.separator + fileName3G);
            //gui mail canh bao nhung cell co tren SMRS va khong co tren Inventory
            String fileNameSmrsInventory = "SMRS_INVENTORY_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
            List smrsInventory = AutoMailFacade.findSmrsInventory();
            message += "- Số lượng Cell có trên SMRS và không có trên Inventory: ".concat(smrsInventory == null ? "" : String.valueOf(smrsInventory.size())).concat(" \t\n");
            writeCellSmrsInventory(fileNameSmrsInventory, new File(dataDirectory + File.separator + "SMRS_INVENTORY.xlsx"), folderTemp, smrsInventory);
            attachFiles.add(folderTemp + File.separator + fileNameSmrsInventory);
            //gui mail canh bao nhung cell tren inventory khong tim thay tren RIMS
            String fileNameInventoryRims2G = "Cell2G_INVENTORY_RIMS_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
            List inventoryRims2G = AutoMailFacade.inventoryRims("5");
            message += "- Số lượng Cell 2G có trên Inventory và không có trên Rims: ".concat(inventoryRims2G == null ? "" : String.valueOf(inventoryRims2G.size())).concat(" \t\n");
            writeInventoryRims(fileNameInventoryRims2G, new File(dataDirectory + File.separator + "Cell2G_Inventory_Rims.xlsx"), folderTemp, inventoryRims2G);
            attachFiles.add(folderTemp + File.separator + fileNameInventoryRims2G);
            //
            String fileNameInventoryRims3G = "Cell3G_INVENTORY_RIMS_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
            List inventoryRims3G = AutoMailFacade.inventoryRims("6");
            message += "- Số lượng Cell 3G có trên Inventory và không có trên Rims: ".concat(inventoryRims3G == null ? "" : String.valueOf(inventoryRims3G.size())).concat(" \t\n");
            writeInventoryRims(fileNameInventoryRims3G, new File(dataDirectory + File.separator + "Cell3G_Inventory_Rims.xlsx"), folderTemp, inventoryRims3G);
            attachFiles.add(folderTemp + File.separator + fileNameInventoryRims3G);
            //
            String fileNameInventoryRims4G = "Cell4G_INVENTORY_RIMS_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".xlsx";
            List inventoryRims4G = AutoMailFacade.inventoryRims("7");
            message += "- Số lượng Cell 4G có trên Inventory và không có trên Rims: ".concat(inventoryRims4G == null ? "" : String.valueOf(inventoryRims4G.size())).concat(" \t\n");
            writeInventoryRims(fileNameInventoryRims4G, new File(dataDirectory + File.separator + "Cell4G_Inventory_Rims.xlsx"), folderTemp, inventoryRims4G);
            attachFiles.add(folderTemp + File.separator + fileNameInventoryRims4G);
            //send mail
            sendEmailWithAttachments(HOST, PORT, USERNAME, PASSWORD, toAddress, subject, message, attachFiles);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void writeDuplicateLacCi(String fileName, File fileTemplate, String folderTemp, List<?> temp) {
        try {
            FileInputStream fin = new FileInputStream(fileTemplate);
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");

            Cell cell = null;
            Row row = null;
            int rowIndex = 1;
            if (fileName.contains("2G")) {
                Iterator<Cell2G> iterator = (Iterator<Cell2G>) temp.iterator();
                while (iterator.hasNext()) {
                    Cell2G item = iterator.next();
                    row = sheet.createRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    cell = row.createCell(0);
                    cell.setCellValue(item.getCellName());
                    cell = row.createCell(1);
                    cell.setCellValue(item.getLac());
                    cell = row.createCell(2);
                    cell.setCellValue(item.getCi());
                    cell = row.createCell(3);
                    cell.setCellValue(item.getFreqBand());
                    cell = row.createCell(4);
                    cell.setCellValue(item.getBcch());
                    cell = row.createCell(5);
                    cell.setCellValue(item.getBsic());
                    cell = row.createCell(6);
                    cell.setCellValue(item.getTch());
                    cell = row.createCell(7);
                    cell.setCellValue(item.getTrxConfig());
                    cell = row.createCell(8);
                    cell.setCellValue(item.getmBsc());
                    cell = row.createCell(9);
                    cell.setCellValue(item.getBtsName());
                    cell = row.createCell(10);
                    cell.setCellValue(item.getVendor());
                    cell = row.createCell(11);
                    cell.setCellValue(item.getFileName());
                    cell = row.createCell(12);
                    cell.setCellValue(item.getCheckDate());
                    cell = row.createCell(13);
                    cell.setCellValue(item.getCellCode());
                }
            } else if (fileName.contains("3G")) {
                Iterator<Cell3G> iterator = (Iterator<Cell3G>) temp.iterator();
                while (iterator.hasNext()) {
                    Cell3G item = iterator.next();
                    row = sheet.createRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    cell = row.createCell(0);
                    cell.setCellValue(item.getCellName());
                    cell = row.createCell(1);
                    cell.setCellValue(item.getLac());
                    cell = row.createCell(2);
                    cell.setCellValue(item.getCi());
                    cell = row.createCell(3);
                    cell.setCellValue(item.getFreqBand());
                    cell = row.createCell(4);
                    cell.setCellValue(item.getDlpsc());
                    cell = row.createCell(5);
                    cell.setCellValue(item.getFreq());
                    cell = row.createCell(6);
                    cell.setCellValue(item.getCpichPower());
                    cell = row.createCell(7);
                    cell.setCellValue(item.getTotalPower());
                    cell = row.createCell(8);
                    cell.setCellValue(item.getMaxPower());
                    cell = row.createCell(9);
                    cell.setCellValue(item.getmBsc());
                    cell = row.createCell(10);
                    cell.setCellValue(item.getNodeBname());
                    cell = row.createCell(11);
                    cell.setCellValue(item.getVendor());
                    cell = row.createCell(12);
                    cell.setCellValue(item.getFileName());
                    cell = row.createCell(13);
                    cell.setCellValue(item.getCheckDate());
                    cell = row.createCell(14);
                    cell.setCellValue(item.getCellCode());
                    cell = row.createCell(15);
                    cell.setCellValue(item.getRac());
                    cell = row.createCell(16);
                    cell.setCellValue(item.getDlUarfcn());
                    cell = row.createCell(17);
                    cell.setCellValue(item.getDcSupport());
                    cell = row.createCell(18);
                    cell.setCellValue(item.getOamIp());
                    cell = row.createCell(19);
                    cell.setCellValue(item.getServiceIp());
                }
            }
            fin.close();
            File file = new File(folderTemp + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /*
    ghi danh sach nhung cell tren SMRS và không có trên Inventory
     */
    private void writeCellSmrsInventory(String fileName, File fileTemplate, String folderTemp, List<?> temp) {
        try {
            FileInputStream fin = new FileInputStream(fileTemplate);
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");

            Cell cell = null;
            Row row = null;
            int rowIndex = 2;
            Iterator<Cell2G> iterator = (Iterator<Cell2G>) temp.iterator();
            while (iterator.hasNext()) {
                Cell2G item = iterator.next();
                row = sheet.createRow(rowIndex++);
                CellStyle style = sheet.getWorkbook().createCellStyle();
                row.setRowStyle(style);
                cell = row.createCell(0);
                cell.setCellValue(rowIndex - 3);
                cell = row.createCell(1);
                cell.setCellValue(item.getLacStr());
                cell = row.createCell(2);
                cell.setCellValue(item.getCiStr());
                cell = row.createCell(3);
                cell.setCellValue(item.getCell_id_ocs());
            }
            fin.close();
            File file = new File(folderTemp + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /*
    ghi danh sach nhung cell tren inventory khong tim thay tren rims
     */
    private void writeInventoryRims(String fileName, File fileTemplate, String folderTemp, List<?> temp) {
        try {
            FileInputStream fin = new FileInputStream(fileTemplate);
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");

            Cell cell = null;
            Row row = null;
            int rowIndex = 2;
            if (fileName.contains("2G")) {
                Iterator<Cell2G> iterator = (Iterator<Cell2G>) temp.iterator();
                while (iterator.hasNext()) {
                    Cell2G item = iterator.next();
                    row = sheet.createRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    cell = row.createCell(0);
                    cell.setCellValue(item.getCellName());
                    cell = row.createCell(1);
                    cell.setCellValue(item.getLac());
                    cell = row.createCell(2);
                    cell.setCellValue(item.getCi());
                    cell = row.createCell(3);
                    cell.setCellValue(item.getFreqBand());
                    cell = row.createCell(4);
                    cell.setCellValue(item.getBcch());
                    cell = row.createCell(5);
                    cell.setCellValue(item.getBsic());
                    cell = row.createCell(6);
                    cell.setCellValue(item.getTch());
                    cell = row.createCell(7);
                    cell.setCellValue(item.getTrxConfig());
                    cell = row.createCell(8);
                    cell.setCellValue(item.getmBsc());
                    cell = row.createCell(9);
                    cell.setCellValue(item.getBtsName());
                    cell = row.createCell(10);
                    cell.setCellValue(item.getVendor());
                    cell = row.createCell(11);
                    cell.setCellValue(item.getFileName());
                    cell = row.createCell(12);
                    cell.setCellValue(item.getCheckDate());
                    cell = row.createCell(13);
                    cell.setCellValue(item.getCellCode());
                }
            } else if (fileName.contains("3G")) {
                Iterator<Cell3G> iterator = (Iterator<Cell3G>) temp.iterator();
                while (iterator.hasNext()) {
                    Cell3G item = iterator.next();
                    row = sheet.createRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    cell = row.createCell(0);
                    cell.setCellValue(item.getCellName());
                    cell = row.createCell(1);
                    cell.setCellValue(item.getLac());
                    cell = row.createCell(2);
                    cell.setCellValue(item.getCi());
                    cell = row.createCell(3);
                    cell.setCellValue(item.getFreqBand());
                    cell = row.createCell(4);
                    cell.setCellValue(item.getDlpsc());
                    cell = row.createCell(5);
                    cell.setCellValue(item.getFreq());
                    cell = row.createCell(6);
                    cell.setCellValue(item.getCpichPower());
                    cell = row.createCell(7);
                    cell.setCellValue(item.getTotalPower());
                    cell = row.createCell(8);
                    cell.setCellValue(item.getMaxPower());
                    cell = row.createCell(9);
                    cell.setCellValue(item.getmBsc());
                    cell = row.createCell(10);
                    cell.setCellValue(item.getNodeBname());
                    cell = row.createCell(11);
                    cell.setCellValue(item.getVendor());
                    cell = row.createCell(12);
                    cell.setCellValue(item.getFileName());
                    cell = row.createCell(13);
                    cell.setCellValue(item.getCheckDate());
                    cell = row.createCell(14);
                    cell.setCellValue(item.getCellCode());
                    cell = row.createCell(15);
                    cell.setCellValue(item.getRac());
                    cell = row.createCell(16);
                    cell.setCellValue(item.getDlUarfcn());
                    cell = row.createCell(17);
                    cell.setCellValue(item.getDcSupport());
                    cell = row.createCell(18);
                    cell.setCellValue(item.getOamIp());
                    cell = row.createCell(19);
                    cell.setCellValue(item.getServiceIp());
                }
            } else if (fileName.contains("4G")) {
                Iterator<Cell4G> iterator = (Iterator<Cell4G>) temp.iterator();
                while (iterator.hasNext()) {
                    Cell4G item = iterator.next();
                    row = sheet.createRow(rowIndex++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    cell = row.createCell(0);
                    cell.setCellValue(item.getCell_name());
                    cell = row.createCell(1);
                    cell.setCellValue(item.getCi());
                    cell = row.createCell(2);
                    cell.setCellValue(item.getFrequency_band());
                    cell = row.createCell(3);
                    cell.setCellValue(item.getPci());
                    cell = row.createCell(4);
                    cell.setCellValue(item.getTac());
                    cell = row.createCell(5);
                    cell.setCellValue(item.getLcrid());
                    cell = row.createCell(6);
                    cell.setCellValue(item.getEnodeb_name());
                    cell = row.createCell(7);
                    cell.setCellValue(item.getVendor());
                    cell = row.createCell(8);
                    cell.setCellValue(item.getFilename());
                    cell = row.createCell(9);
                    cell.setCellValue(item.getEnodeb_id());
                    cell = row.createCell(10);
                    cell.setCellValue(item.getCheck_date());
                    cell = row.createCell(11);
                    cell.setCellValue(item.getCell_code());
                    cell = row.createCell(12);
                    cell.setCellValue(item.getBandwith());
                    cell = row.createCell(13);
                    cell.setCellValue(item.getUarcn());
                    cell = row.createCell(14);
                    cell.setCellValue(item.getOam_ip());
                    cell = row.createCell(15);
                    cell.setCellValue(item.getService_ip());
                }
            }
            fin.close();
            File file = new File(folderTemp + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void sendEmailWithAttachments(String host, String port,
            String userName, String password, String toAddress,
            String subject, String message, ArrayList<String> attachFiles)
            throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.timeout", 5000);
        props.put("mail.smtp.connectiontimeout", 5000);
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName + "@vnpt.vn", password);
            }
        });
        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
        msg.setFrom(new InternetAddress(userName + "@vnpt.vn", userName));
        msg.setReplyTo(InternetAddress.parse(userName + "@vnpt.vn", false));
        msg.setSubject(subject, "UTF-8");
        msg.setText(message, "UTF-8");
        msg.setSentDate(new Date());
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress, false));
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/plain; charset=UTF-8");
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // adds attachments
        if (attachFiles != null && attachFiles.size() > 0) {
            for (String filePath : attachFiles) {
                File file = new File(filePath);
                if (file.exists()) {
                    MimeBodyPart attachPart = new MimeBodyPart();
                    attachPart.attachFile(filePath);
                    multipart.addBodyPart(attachPart);
                }
            }
        }
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
        // sends the e-mail
        Transport.send(msg);
    }
}
