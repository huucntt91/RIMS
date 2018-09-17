/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.job;

import com.vnpt.media.rims.bean.Cell4GConfig;
import com.vnpt.media.rims.facade.ReportFacade;
import com.vnpt.media.rims.formbean.BtsNodeB;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import java.io.File;
import java.util.List;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

/**
 *
 * @author Cyano
 */
public class DataReportJob implements Job {

    private static Logger logger = LogManager.getLogger(DataReportJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        List<BtsNodeB> btsLst = null;
//        List<BtsNodeB> nodebLst = null;
//        List<Cell2G> cell2Gs = null;
//        List<Cell3G> cell3Gs = null;
//        List<Cell4GConfig> cell4Gs = null;
//        try {
//            List<JobExecutionContext> jobs = context.getScheduler().getCurrentlyExecutingJobs();
//            for (JobExecutionContext job : jobs) {
//                if (job.getTrigger().equals(context.getTrigger()) && !job.getJobInstance().equals(this)) {
//                    logger.info("Another job instance running, leaving : " + context.getJobDetail().getKey().getName());
//                    return;
//                }
//            }
//            ServletContext servletContext = (ServletContext) context.getMergedJobDataMap().get("servletContext");
//            String dataDirectory = servletContext.getRealPath("/WEB-INF/excel-templates/");
//            File tempFile = new File(dataDirectory + File.separator + "Template_BCLL.xlsx");
//            if (tempFile.exists()) {
//                //logger.info("running job....");
//                String folderPath = servletContext.getRealPath("/bcll");
//                File folder = new File(folderPath);
//                if (!folder.exists()) {
//                    folder.mkdirs();
//                }
//                File fileResult = new File(folderPath + File.separator + "BCLL.xlsx");
//                ReportFacade reportFacade = new ReportFacade();
//                DataReport dataReport = new DataReport();
//                btsLst = reportFacade.findBts("");
//                nodebLst = reportFacade.findNodeB("");
//                if (nodebLst != null) {
//                    btsLst.addAll(nodebLst);
//                }
//                cell2Gs = reportFacade.findCell2G("");
//                cell3Gs = reportFacade.findCell3G("");
//                cell4Gs = reportFacade.findCell4G("");
//                dataReport.writeBcllForJob( fileResult, btsLst, cell2Gs, cell3Gs,cell4Gs);
//
//            }
//            //logger.info("end job....");
//        } catch (SchedulerException ex) {
//            ex.printStackTrace();
//        }
    }

}
