/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.job;

import com.vnpt.media.rims.bean.EmailReportBO;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.common.utils.ZipUtils;
import com.vnpt.media.rims.facade.AutoMailFacade;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
            String jobKey = jec.getJobDetail().getKey().getName();
            List<JobExecutionContext> jobs = jec.getScheduler().getCurrentlyExecutingJobs();
            for (JobExecutionContext job : jobs) {
                if (job.getTrigger().equals(jec.getTrigger()) && !job.getJobInstance().equals(this)) {
                    logger.info("Another job instance running, leaving : " + jobKey);
                    return;
                }
            }
            logger.info("AutoMailJob running : " + jobKey);
            String idReportMail = jec.getJobDetail().getJobDataMap().getString("idReportMail");
            String reportMailName = jec.getJobDetail().getJobDataMap().getString("reportMailName");
            String listMail = jec.getJobDetail().getJobDataMap().getString("listMail");
            String emailDetail = jec.getJobDetail().getJobDataMap().getString("emailDetail");

            ArrayList<String> attachFiles = new ArrayList<String>();
//           lay danh sach cau lenh sql bao cao de chay
            List<EmailReportBO> sqlLst = AutoMailFacade.getEmailReportDetail(idReportMail);
            if (sqlLst != null) {
                for (EmailReportBO lst : sqlLst) {
                    String fileName = AutoMailFacade.findData(lst.sqlValue, lst.sqlName);
                    attachFiles.add(fileName);
                }
            }
            String zipFile = StringUtils.getFolderTemp() + File.separator + "data_" + DateTimeUtils.convertDateString(new Date(), "ddMMyyy_HHmmss") + ".zip";
            ZipUtils.zipMultipleFiles(attachFiles, zipFile);
            ArrayList<String> zipFiles = new ArrayList<String>();
            zipFiles.add(zipFile);

            sendEmailWithAttachments(HOST, PORT, USERNAME, PASSWORD, listMail, reportMailName, emailDetail, zipFiles);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
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

    public static boolean sendEmailReport(ArrayList<String> listEmail, String subject, String message, ArrayList<String> attachFiles) {
        try {
            ArrayList<String> files = new ArrayList<String>();
            for (String email : listEmail) {
                logger.info("user: {}, subject: {}, files: {}, start send mail to user...", email, subject, files);

//                sendEmailWithAttachments(HOST,PORT,USER,PASS,email,subject,message,files);
//                Thread.sleep(100);
                logger.info("user: {}, subject: {}, files: {}, end send mail to user...", email, subject, files);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
