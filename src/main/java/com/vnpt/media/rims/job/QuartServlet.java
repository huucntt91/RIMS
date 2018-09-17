/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.job;

import com.vnpt.media.rims.common.utils.StringUtils;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
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
public class QuartServlet implements Servlet {

    private static Logger logger = LogManager.getLogger(QuartServlet.class);
    Scheduler scheduler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            //Create & start the scheduler.

            //pass the servlet context to the job
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("servletContext", config.getServletContext());
//            // define the job and tie it to our job's class
//            JobDetail job = JobBuilder.newJob(DataReportJob.class)
//                    .withIdentity("dummyJobName", "group1").usingJobData(jobDataMap).build();
//            // Trigger the job to run now, and then repeat every 3 seconds
//            Trigger trigger = TriggerBuilder
//                    .newTrigger()
//                    .withIdentity("dummyTriggerName", "group1")
//                    .withSchedule(
//                            CronScheduleBuilder.cronSchedule("0 0/15 * * * ?"))
//                    .build();
//            scheduler = new StdSchedulerFactory().getScheduler();
//            scheduler.start();
//            scheduler.scheduleJob(job, trigger);
            addJob(scheduler, "AutoMailJob", "Mail", StringUtils.getString("schedule_lac_ci"), jobDataMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void addJob(Scheduler scheduler, String jobName, String jobGroup, String schedule, JobDataMap jobDataMap) throws SchedulerException {
        try {
            if (scheduler == null) {
                scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.start();
            }
            if (schedule == null || schedule.isEmpty()) {
                schedule = "0 0/15 * * * ?";
            }
            JobKey jobKey = new JobKey(jobName, jobGroup);
            if (scheduler.checkExists(jobKey)) {
                scheduler.deleteJob(jobKey);
            }
            JobDetail jobDetail = JobBuilder.newJob(AutoMailJob.class)
                    .withIdentity(jobKey).usingJobData(jobDataMap).build();
            Trigger trigger = newTrigger()
                    .withIdentity("trigger_" + jobName, jobGroup)
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(schedule))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("Build job {} to group {}", jobName, jobGroup);
        } catch (SchedulerException e) {
            throw e;
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getServletInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        try {
            if (scheduler != null) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
