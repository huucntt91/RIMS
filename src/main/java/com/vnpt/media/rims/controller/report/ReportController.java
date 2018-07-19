/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.report;

import com.vnpt.media.rims.bean.Cell4GConfig;
import com.vnpt.media.rims.bean.ThietBiBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.LogMenuFacade;
import com.vnpt.media.rims.facade.ReportFacade;
import com.vnpt.media.rims.formbean.BtsNodeB;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/bcll")
public class ReportController {

    private static Logger logger = LogManager.getLogger(ReportController.class);
    private static String reportPage = "report/bcll/index";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request) {
        // log user request menu
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        LogMenuFacade.logMenu(String.valueOf(user.getId()), request.getRemoteAddr(), "Báo cáo lưu lượng tuần");
        // end log

        logger.info("Action init BCLL");
//        CategoriesFacade facade = new CategoriesFacade();
//        List<LoaiTramBO> list = facade.findAllLoaiTram("");
//        mm.put("list", list);
        return reportPage;
    }

    @ModelAttribute("vendors")
    public List findTechType() {
        List<ThietBiBO> vendors = null;
        try {
            CategoriesFacade facade = new CategoriesFacade();
            vendors = facade.findAllThietBi("");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return vendors;
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void downloadFile(HttpServletRequest request,
            HttpServletResponse response) {
        logger.info("---download file---");
        try {
            String vendor_id = "";
            String[] vendor_ids = request.getParameterValues("vendor_id");
            if (vendor_ids != null) {
                vendor_id = String.join(",", Arrays.asList(vendor_ids));
            }
            String fileName = "BCLL.xlsx";
            String dataDirectory = request.getServletContext().getRealPath("/bcll");
            File fileResult = new File(dataDirectory + File.separator + fileName);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
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
        logger.info("done ...............");
    }

    @RequestMapping(value = "/exportFile", method = RequestMethod.POST)
    public void exportFile(HttpServletRequest request,
            HttpServletResponse response) {
        List<BtsNodeB> btsLst = null;
        List<BtsNodeB> nodebLst = null;
        List<Cell2G> cell2Gs = null;
        List<Cell3G> cell3Gs = null;
        List<Cell4GConfig> cell4Gs = null;
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String vendor_id = "";
            String[] vendor_ids = request.getParameterValues("vendor_id");
            if (vendor_ids != null) {
                vendor_id = String.join(",", Arrays.asList(vendor_ids));
            }
            logger.info("user: {}, ip:{}, bcll tuan vendor :{}", user.getUsername(), request.getRemoteAddr(), vendor_id);
            ReportFacade reportFacade = new ReportFacade();
            btsLst = reportFacade.findBts(vendor_id);
            nodebLst = reportFacade.findNodeB(vendor_id);
            if (nodebLst != null) {
                btsLst.addAll(nodebLst);
            }
            cell2Gs = reportFacade.findCell2G(vendor_id);
            cell3Gs = reportFacade.findCell3G(vendor_id);
            cell4Gs = reportFacade.findCell4G(vendor_id);
            logger.info("user: {}, ip:{}, bcll tuan btsLst:{}, cell2Gs:{}, cell3Gs:{}, cell4Gs:{}", user.getUsername(), request.getRemoteAddr(),
                    btsLst.size(), cell2Gs.size(), cell3Gs.size(), cell4Gs.size());
            String fileName = "Template_BCLL.xlsx";
//            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
//            Path file = Paths.get(dataDirectory, fileName);
//            File f = new File(dataDirectory + File.separator + fileName);
            DataReport dataReport = new DataReport();
            logger.info("user: {}, ip:{}, bcll ghi file", user.getUsername(), request.getRemoteAddr());
            File fileResult = dataReport.writeBcll(btsLst, cell2Gs, cell3Gs, cell4Gs);

            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            logger.info("user: {}, ip:{}, bcll tra file excel", user.getUsername(), request.getRemoteAddr(), fileResult.getName());
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        } finally {
            btsLst = null;
            nodebLst = null;
            cell2Gs = null;
            cell3Gs = null;
            cell4Gs = null;
        }
    }

}
