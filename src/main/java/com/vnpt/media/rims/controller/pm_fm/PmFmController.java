/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.pm_fm;

import com.vnpt.media.rims.bean.EventBO;
import com.vnpt.media.rims.bean.KpiBO;
import com.vnpt.media.rims.bean.PmFmBO;
import com.vnpt.media.rims.controller.managerAdmin.BaseController;
import com.vnpt.media.rims.facade.PmFmFacade;
import com.vnpt.media.rims.formbean.PmFmForm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/pm_fm")
public class PmFmController extends BaseController {

    private static Logger logger = LogManager.getLogger(PmFmController.class);
    @Autowired
    private MessageSource messageSource;
    private static final String POPUP_PM = "pm_fm/pm/popup";
    private static final String POPUP_FM = "pm_fm/fm/popup";

    String PAGE_LST = "pm_fm/pm/index";

    @ModelAttribute("kpiLst")
    public List findKpis() {
        List<KpiBO> list = null;
        try {
            PmFmFacade face = new PmFmFacade();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request) {
        try {
//            List<KpiBO> list = null;
//            PmFmFacade face = new PmFmFacade();
//            list = face.getPm(new Date(), new Date());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return PAGE_LST;
    }

    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String popup(@RequestParam(value = "page", required = false) String page,
            @Valid @ModelAttribute(value = "pmFmForm") PmFmForm pmFmForm,
            ModelMap mm, HttpServletRequest request, HttpServletResponse response) {
        logger.info("popup PM FM");
        List<String> columnNames = new ArrayList();
        List<KpiBO> kpis = null;
        List<PmFmBO> list = null;
        try {
            PmFmFacade face = new PmFmFacade();
            //lay danh sach kpi
            kpis = face.getKpiBOs(null, pmFmForm.getNodeType());
            //tao cot dong theo danh sach kpi
            columnNames.add("STT");
            columnNames.add("VNP_CODE");
            columnNames.add("Loại đối tượng");
            columnNames.add("Thời gian");
            String temp = pmFmForm.getKpis();
            String temp2 = "";
            if (temp != null) {
                if (temp.endsWith(",")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                String[] columns = temp.split(",");
                if (columns != null) {
                    for (String column : columns) {
                        if (column != null && !column.trim().equalsIgnoreCase("")) {
                            if (kpis != null) {
                                for (KpiBO kpi : kpis) {
                                    if (kpi.getKpiMappingId().toString().equalsIgnoreCase(column.trim())) {
                                        columnNames.add(kpi.getName().trim());
                                        temp2 += kpi.getCode().trim() + ",";
                                    }
                                }
                            }
                        }
                    }
                    if (temp2.endsWith(",")) {
                        temp2 = temp2.substring(0, temp2.length() - 1);
                    }
                }
            }
//            page = page == null ? "1" : page;

            if (pmFmForm.getFrequency() == null) {
                pmFmForm.setFrequency("DAILY");
            }
            if (pmFmForm.getEndTime() == null) {
                pmFmForm.setEndTime(new Date());
            }
//            Integer pageInt = Integer.parseInt(page);
//             
//            face.countKPI(pmFmForm.getVnpCode(), pmFmForm.getNodeType(), pmFmForm.getFrequency(), pmFmForm.getStartTime(), pmFmForm.getEndTime(), pmFmForm.getKpis());
//            Page objPage = new Page();
//
//            int numPerPage = Constants.NUMBER_FOR_PAGING;
//            int totalPages = 0;
//
//            if (totalRows % numPerPage == 0) {
//                totalPages = (int) totalRows / numPerPage;
//            } else {
//                totalPages = (int) totalRows / numPerPage + 1;
//            }
//            if (totalRows == 0) {
//                totalPages = 0;
//            }
//            if (pageInt < 1) {
//                pageInt = 1;
//                return ("redirect:/pm_fm/popup?page=" + pageInt);
//            } else if (pageInt > totalPages && totalPages > 0) {
//                pageInt = totalPages;
//                return ("redirect:/pm_fm/popup?page=" + pageInt);
//            }
//            objPage.setTotalPages(totalPages);
//            objPage.setTotalRows(totalRows);
//            objPage.setDestPage(pageInt);
//            objPage.setDirection(1);
//            //objPage.setSubject("Quản lý Building");
//            mm.addAttribute("pageInfo", objPage);
//
//            int startRow = 0, endRow = 0;
//            if (pageInt > 1) {
//                startRow = ((pageInt - 1) * (numPerPage) + 1);
//                endRow = (pageInt * (numPerPage));
//            } else if (pageInt == 1) {
//                startRow = 1;
//                endRow = Constants.NUMBER_FOR_PAGING;
//            }

            if (temp2 != null && !temp2.equalsIgnoreCase("") && pmFmForm.getVnpCode() != null && pmFmForm.getNodeType() != null) {
                list = face.getPm(pmFmForm.getVnpCode(), pmFmForm.getNodeType(), pmFmForm.getFrequency(), pmFmForm.getStartTime(), pmFmForm.getEndTime(), temp2);
            }
        
            
            mm.put("modelSearch", pmFmForm);
            mm.put("kpiLst", kpis);
            mm.put("columnNames", columnNames);
            mm.put("list", list);
//            mm.put("startRow", startRow);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return POPUP_PM;
    }

    @RequestMapping(value = "/popup_fm", method = RequestMethod.GET)
    public String popup_fm(@RequestParam(value = "page", required = false) String page,
            @Valid @ModelAttribute(value = "pmFmForm") PmFmForm pmFmForm,
            ModelMap mm, HttpServletRequest request, HttpServletResponse response) {
        logger.info("popup PM FM");
        List<String> columnNames = new ArrayList();
        List<EventBO> eventLst = null;
        List<PmFmBO> list = null;
        try {
            PmFmFacade face = new PmFmFacade();
            //lay danh sach kpi
            eventLst = face.get_event_lst(pmFmForm.getNodeType());
            //tao cot dong theo danh sach kpi
            columnNames.add("STT");
            columnNames.add("VNP_CODE");
            columnNames.add("Thời gian");
            columnNames.add("Event");
            columnNames.add("Mô tả");
            if (pmFmForm.getEndTime() == null) {
                pmFmForm.setEndTime(new Date());
            }
//            Integer pageInt = Integer.parseInt(page);
//             
//            face.countKPI(pmFmForm.getVnpCode(), pmFmForm.getNodeType(), pmFmForm.getFrequency(), pmFmForm.getStartTime(), pmFmForm.getEndTime(), pmFmForm.getKpis());
//            Page objPage = new Page();
//
//            int numPerPage = Constants.NUMBER_FOR_PAGING;
//            int totalPages = 0;
//
//            if (totalRows % numPerPage == 0) {
//                totalPages = (int) totalRows / numPerPage;
//            } else {
//                totalPages = (int) totalRows / numPerPage + 1;
//            }
//            if (totalRows == 0) {
//                totalPages = 0;
//            }
//            if (pageInt < 1) {
//                pageInt = 1;
//                return ("redirect:/pm_fm/popup?page=" + pageInt);
//            } else if (pageInt > totalPages && totalPages > 0) {
//                pageInt = totalPages;
//                return ("redirect:/pm_fm/popup?page=" + pageInt);
//            }
//            objPage.setTotalPages(totalPages);
//            objPage.setTotalRows(totalRows);
//            objPage.setDestPage(pageInt);
//            objPage.setDirection(1);
//            //objPage.setSubject("Quản lý Building");
//            mm.addAttribute("pageInfo", objPage);
//
//            int startRow = 0, endRow = 0;
//            if (pageInt > 1) {
//                startRow = ((pageInt - 1) * (numPerPage) + 1);
//                endRow = (pageInt * (numPerPage));
//            } else if (pageInt == 1) {
//                startRow = 1;
//                endRow = Constants.NUMBER_FOR_PAGING;
//            }

            if(pmFmForm.getEvents()!=null && !pmFmForm.getEvents().isEmpty() && pmFmForm.getVnpCode()!=null && !pmFmForm.getVnpCode().isEmpty())
            list = face.getFm(pmFmForm.getVnpCode(),pmFmForm.getStartTime(), pmFmForm.getEndTime(),pmFmForm.getEvents());
            mm.put("modelSearch", pmFmForm);
            mm.put("eventLst", eventLst);
            mm.put("columnNames", columnNames);
            mm.put("list", list);
//            mm.put("startRow", startRow);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return POPUP_FM;
    }

}
