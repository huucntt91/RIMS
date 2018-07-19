package com.vnpt.media.rims.controller.dataAudit;

import com.vnpt.media.rims.controller.googleMap.*;
import com.google.gson.Gson;
import com.vnpt.media.rims.bean.GoogleMapBO;
import com.vnpt.media.rims.bean.MonitoringJobAuditBO;
import com.vnpt.media.rims.controller.managerAdmin.*;
import com.vnpt.media.rims.common.Constants;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DataAuditFacade;
import com.vnpt.media.rims.facade.GoogleMapFacade;
import com.vnpt.media.rims.formbean.MonitoringJobAuditForm;
import java.util.Date;
import javax.validation.Valid;


/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/monitoringJobAudit")
public class MonitoringJobAudit extends BaseController {
    
    private static Logger logger = LogManager.getLogger(MonitoringJobAudit.class);
    private static int maxDisplayPages = 8;
    private static String pagerStyle = "pagelinks";
    private static final String[] strPrefix = {"&#272;&#7847;u", "Tr&#432;&#7899;c", "Sau", "Cu&#7889;i", "Trang", "T&#7899;i trang "};
    private static final String separator = ",";
    private static final String LIST = "dataAudit/monitoringJob/monitorinJobList";
    @Autowired
    private MessageSource messageSource;
    
    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[])request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(@Valid @ModelAttribute(value = "monitoringJobForm") MonitoringJobAuditForm monitoringJobForm,
            Locale locale, ModelMap mm, HttpServletRequest request) {
        logger.info("Action init map");
        
        List<MonitoringJobAuditBO> list = null;
        try{
            DataAuditFacade facade = new DataAuditFacade();
            if (monitoringJobForm.getNE_NAME()== null) {
                monitoringJobForm.setNE_NAME("BTS");
            }
            if (monitoringJobForm.getEndTime() == null) {
                monitoringJobForm.setEndTime(new Date());
            }
            if(monitoringJobForm.getStartTime() != null && monitoringJobForm.getEndTime()!= null){
                list = facade.findMonitoringJobAudit(monitoringJobForm.getNE_NAME(), monitoringJobForm.getStartTime(), monitoringJobForm.getEndTime());
            }
            
            mm.put("modelSearch", monitoringJobForm);
            mm.put("list", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return LIST;
    }    



    
    
}
