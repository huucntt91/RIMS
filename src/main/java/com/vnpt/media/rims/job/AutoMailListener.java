/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.job;

import com.vnpt.media.rims.bean.EmailReportBO;
import com.vnpt.media.rims.facade.AutoMailFacade;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Cyano
 */
public class AutoMailListener implements Job {

    private static Logger logger = LogManager.getLogger(AutoMailListener.class);
    Scheduler scheduler = null;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {

            String jobKey = jec.getJobDetail().getKey().getName();
            List<JobExecutionContext> jobs = jec.getScheduler().getCurrentlyExecutingJobs();
            for (JobExecutionContext job : jobs) {
                if (job.getTrigger().equals(jec.getTrigger()) && !job.getJobInstance().equals(this)) {
                    logger.info("AutoMailListener job instance running, leaving : " + jobKey);
                    return;
                }
            }
            logger.info("AutoMailListener running : " + jobKey);
            if (scheduler == null) {
                scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.start();
            }
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            List<EmailReportBO> emailReports = AutoMailFacade.getEmailReport("");
            if (emailReports != null) {
                for (EmailReportBO e : emailReports) {
                    addJob(scheduler, e.getIdReportMail(), e.getReportMailName(), e.getListMail(),
                            e.getTimeSend(), e.getStatus() == null ? "0" : e.getStatus(), e.getEmailDetail());
                }

//                //xoa nhung job ko ton tai
//                for (String groupName : scheduler.getJobGroupNames()) {
//                    Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName));
//                    if (jobKeys != null) {
//                        for (JobKey jKey : jobKeys) {
//                            String jobName = jKey.getName();
//                            String jobGroup = jKey.getGroup();
//
//                            JobDetail jobDetail = scheduler.getJobDetail(jKey);
//
//                            JobDataMap datamap = jobDetail.getJobDataMap();
//
//                            if (datamap != null) {
//                                String status = jobDetail.getJobDataMap().getString("status");
//                                String id = jobDetail.getJobDataMap().getString("idReportMail");
////                                neu status = 0 thi delete job
//                                if (status.equalsIgnoreCase("0")) {
//                                    scheduler.deleteJob(jKey);
//                                    logger.info("Deleted job: {}, in group: {}", jobName, jobGroup);
//                                }
//
//                            }
//
//                        }
//                    }
//
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void addJob(Scheduler scheduler, String idReportMail, String reportMailName, String listMail, String timeSend, String status, String emailDetail) {
        try {

            if (scheduler == null) {
                scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.start();
            }
            if (reportMailName == null || reportMailName.isEmpty() || listMail == null || listMail.isEmpty() || timeSend == null
                    || timeSend.isEmpty()) {
                return;
            }
            if (timeSend == null || timeSend.isEmpty()) {
                return;
            }
            //thiet lay so job cau hinh cho 1 server
            JobKey jobKey = new JobKey("job_" + idReportMail, "SendMailGroup");
            if (!scheduler.checkExists(jobKey) && status.equals("1")) {
                JobDetail jobDetail = JobBuilder.newJob(AutoMailJob.class)
                        .withIdentity(jobKey).build();
                jobDetail.getJobDataMap().put("idReportMail", idReportMail);
                jobDetail.getJobDataMap().put("reportMailName", reportMailName);
                jobDetail.getJobDataMap().put("listMail", listMail);
                jobDetail.getJobDataMap().put("timeSend", timeSend);
                jobDetail.getJobDataMap().put("status", status);
                jobDetail.getJobDataMap().put("emailDetail", emailDetail);
                Trigger trigger = newTrigger()
                        .withIdentity("trigger_" + idReportMail, "SendMailGroup")
                        .startNow()
                        .withSchedule(CronScheduleBuilder.cronSchedule(timeSend))
                        .build();

                scheduler.scheduleJob(jobDetail, trigger);
                logger.info("Add job: {} to group: {}", idReportMail, "SendMailGroup");
            } else if (scheduler.checkExists(jobKey) && status.equals("1")) {
                //kiem tra lại xem cac thong tin co bi thay doi ko
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                if (!reportMailName.equals(jobDetail.getJobDataMap().getString("reportMailName"))
                        || !listMail.equals(jobDetail.getJobDataMap().getString("listMail"))
                        || !timeSend.equals(jobDetail.getJobDataMap().getString("timeSend"))
                        || !emailDetail.equals(jobDetail.getJobDataMap().getString("emailDetail"))) {
                    scheduler.deleteJob(jobKey);
                    jobDetail = JobBuilder.newJob(AutoMailJob.class)
                            .withIdentity(jobKey).build();
                    jobDetail.getJobDataMap().put("idReportMail", idReportMail);
                    jobDetail.getJobDataMap().put("reportMailName", reportMailName);
                    jobDetail.getJobDataMap().put("listMail", listMail);
                    jobDetail.getJobDataMap().put("timeSend", timeSend);
                    jobDetail.getJobDataMap().put("status", status);
                    jobDetail.getJobDataMap().put("emailDetail", emailDetail);
                    Trigger trigger = newTrigger()
                            .withIdentity("trigger_" + idReportMail, "SendMailGroup")
                            .startNow()
                            .withSchedule(CronScheduleBuilder.cronSchedule(timeSend))
                            .build();
                    scheduler.scheduleJob(jobDetail, trigger);
                    logger.info("Update job: {} in group: {}", idReportMail, "SendMailGroup");
                }
            } else if (scheduler.checkExists(jobKey) && status.equals("0")) {
                scheduler.deleteJob(jobKey);
                logger.info("Delete job: {} in group: {}", idReportMail, "SendMailGroup");
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
