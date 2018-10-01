/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller;

import com.vnpt.media.rims.bean.ChartData;
import com.vnpt.media.rims.bean.ChartBO;
import com.vnpt.media.rims.bean.MonitoringJobAuditBO;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.ChartFacade;
import com.vnpt.media.rims.facade.DataAuditFacade;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrator
 */
@Controller
public class LoginController {

    private static Logger logger = LogManager.getLogger(LoginController.class);

    @RequestMapping(value = {"/", "/portal"}, method = RequestMethod.GET)
    public String portal(ModelMap model, HttpServletRequest request) {
        model.addAttribute("user", getPrincipal(request));
        //load du lieu cho omc - chart
       
        return "portal";
    }
    @RequestMapping(value = { "/home"}, method = RequestMethod.GET)
    public String homePage(ModelMap model, HttpServletRequest request) {
        model.addAttribute("user", getPrincipal(request));
        //load du lieu cho omc - chart
        List<ChartBO> list = null;
        List<String> dates = null;
        String vendors[] = {"Alcatel", "Alu", "Ericsson", "Huawei", "Motorola", "Nokia", "Zte"};
        List<ChartData> chartDatas = new ArrayList<>();
        Date currentDate = new Date();
        Date startDate = new Date(currentDate.getTime() - 5 * 24 * 3600 * 1000);
        dates = new ArrayList<>();
//        dates.add(dateToString(new Date(currentDate.getTime() - 5 * 24 * 3600 * 1000), "dd/MM/yyyy"));
        dates.add(dateToString(new Date(currentDate.getTime() - 4 * 24 * 3600 * 1000), "dd/MM/yyyy"));
        dates.add(dateToString(new Date(currentDate.getTime() - 3 * 24 * 3600 * 1000), "dd/MM/yyyy"));
        dates.add(dateToString(new Date(currentDate.getTime() - 2 * 24 * 3600 * 1000), "dd/MM/yyyy"));
        dates.add(dateToString(new Date(currentDate.getTime() - 1 * 24 * 3600 * 1000), "dd/MM/yyyy"));
        dates.add(dateToString(currentDate, "dd/MM/yyyy"));
        try {
            list = ChartFacade.fn_get_download_file("", "", dateToString(startDate, "dd/MM/yyyy HH:mm:ss"), dateToString(currentDate, "dd/MM/yyyy HH:mm:ss"));
            if (list != null) {
                for (String vendor : vendors) {
                    ChartData chart = new ChartData();
                    chart.setKey(vendor);
                    List<Integer> data = new ArrayList<>();
                    for (String date : dates) {
                        int count = 0;

                        for (ChartBO downloadFileBO : list) {
                            String temp = dateToString(downloadFileBO.getDownload_time(), "dd/MM/yyyy");
                            if (temp.equalsIgnoreCase(date) && downloadFileBO.getVendor().equalsIgnoreCase(vendor)) {
                                count = downloadFileBO.getFile_num();
                            }
                        }
                        data.add(count);
                    }
                    chart.setData(data);
                    chartDatas.add(chart);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        model.put("dates", dates);
        model.put("chartDatas", chartDatas);
        //load d? li?u cho bieu do parsing
        List<ChartBO> parsings = null;
        List<ChartData> chartDatas0 = new ArrayList<>();
        try {
            parsings = ChartFacade.fn_get_parsing("bts", "", dateToString(startDate, "dd/MM/yyyy HH:mm:ss"), dateToString(currentDate, "dd/MM/yyyy HH:mm:ss"));
            if (parsings != null) {
                for (String vendor : vendors) {
                    ChartData chart = new ChartData();
                    chart.setKey(vendor);
                    List<Integer> data = new ArrayList<>();
                    for (String date : dates) {
                        int count = 0;

                        for (ChartBO chartBO : parsings) {
                            String temp = dateToString(chartBO.getDownload_time(), "dd/MM/yyyy");
                            if (temp.equalsIgnoreCase(date) && chartBO.getVendor().equalsIgnoreCase(vendor)) {
                                count = chartBO.getFile_num();
                            }
                        }
                        data.add(count);
                    }
                    chart.setData(data);
                    chartDatas0.add(chart);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        model.put("chartDatas0", chartDatas0);
        //loai du lieu bieu do parsing cell3g
        parsings = null;
        List<ChartData> chartDatas3 = new ArrayList<>();
        try {
            parsings = ChartFacade.fn_get_parsing("nodeb", "", dateToString(startDate, "dd/MM/yyyy HH:mm:ss"), dateToString(currentDate, "dd/MM/yyyy HH:mm:ss"));
            if (parsings != null) {
                for (String vendor : vendors) {
                    ChartData chart = new ChartData();
                    chart.setKey(vendor);
                    List<Integer> data = new ArrayList<>();
                    for (String date : dates) {
                        int count = 0;

                        for (ChartBO chartBO : parsings) {
                            String temp = dateToString(chartBO.getDownload_time(), "dd/MM/yyyy");
                            if (temp.equalsIgnoreCase(date) && chartBO.getVendor().equalsIgnoreCase(vendor)) {
                                count = chartBO.getFile_num();
                            }
                        }
                        data.add(count);
                    }
                    chart.setData(data);
                    chartDatas3.add(chart);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        model.put("chartDatas3", chartDatas3);
        //loai du lieu bieu do parsing cell4g
        parsings = null;
        List<ChartData> chartDatas4 = new ArrayList<>();
        try {
            parsings = ChartFacade.fn_get_parsing("enodeb", "", dateToString(startDate, "dd/MM/yyyy HH:mm:ss"), dateToString(currentDate, "dd/MM/yyyy HH:mm:ss"));
            if (parsings != null) {
                for (String vendor : vendors) {
                    ChartData chart = new ChartData();
                    chart.setKey(vendor);
                    List<Integer> data = new ArrayList<>();
                    for (String date : dates) {
                        int count = 0;

                        for (ChartBO chartBO : parsings) {
                            String temp = dateToString(chartBO.getDownload_time(), "dd/MM/yyyy");
                            if (temp.equalsIgnoreCase(date) && chartBO.getVendor().equalsIgnoreCase(vendor)) {
                                count = chartBO.getFile_num();
                            }
                        }
                        data.add(count);
                    }
                    chart.setData(data);
                    chartDatas4.add(chart);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        model.put("chartDatas4", chartDatas4);
        return "welcome";
    }
    @RequestMapping(value = {"/noreg"}, method = RequestMethod.GET)
    public String noReg(ModelMap model, HttpServletRequest request) {
        return "noreg";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model, HttpServletRequest request) {
        model.addAttribute("user", getPrincipal(request));
        return "admin";
    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String dbaPage(ModelMap model, HttpServletRequest request) {
        model.addAttribute("user", getPrincipal(request));
        return "dba";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model, HttpServletRequest request) {
        model.addAttribute("user", getPrincipal(request));
        
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String host = url.substring(0, url.indexOf(uri));
        return "redirect:https://id.vnpt.com.vn/cas/logout?service=" + host + request.getContextPath();
        //return "redirect:/login?logout";
    }

    private String getPrincipal(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    private static String dateToString(Date date, String pattern) {
        try {
            if (date != null) {
                SimpleDateFormat sp = null;
                if (pattern == null || pattern.isEmpty()) {
                    sp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
                } else {
                    sp = new SimpleDateFormat(pattern);
                }
                return sp.format(date);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }
}
