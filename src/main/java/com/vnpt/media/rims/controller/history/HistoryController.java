/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.history;

import com.vnpt.media.rims.bean.*;
import java.util.List;
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
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.*;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/history")
public class HistoryController {
    
    private static final Logger LOGGER = LogManager.getLogger(HistoryController.class);
    private static final String LIST = "history/list/list";
    
    @Autowired
    private MessageSource messageSource;
    
    @ModelAttribute("userList")
    public List findAllUser() {
        ManagerAdminFacade admin = new ManagerAdminFacade();
        return admin.findAllUsers("", 0, 100000);
    }
    
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(@RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "type", required = false) String type, @RequestParam(value = "userId", required = false) String userId, @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime, @RequestParam(value = "action", required = false) String action,
            ModelMap mm, HttpServletRequest request) {
        
        code = code == null ? "" : code;
        type = type == null ? "" : type;
        userId = userId == null ? "" : userId;
        action = action == null ? "" : action;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -30); //minus number would decrement the days
        Date dateF = cal.getTime();
        endTime = endTime == null ? dateFormat.format(date) : endTime;
        startTime = startTime == null ? dateFormat.format(dateF) : startTime;
//        endTime = endTime == null ? "" : endTime;
//        startTime = startTime == null ? "" : startTime;
        try {
            HistoryFacade facade = new HistoryFacade();
            if (type.equals("BTS")) {
                //
                List<BtsReportBO> listBts = facade.btsHistory(startTime, endTime, action, userId, type, code);
                
                if (listBts != null && listBts.size() > 0) {
                    mm.put("listBts", listBts);
                    if (listBts.get(0).getBuildingId() != null && !listBts.get(0).getBuildingId().equals("")) {
                        String buildingId = listBts.get(0).getBuildingId();
                        List<CshtHistoryBO> listCsht = facade.cshtHistory(startTime, endTime, action, userId, buildingId);
                        if (listCsht != null && listCsht.size() > 0) {
                            mm.put("listCsht", listCsht);
                        }
                    }
                    String tramDAId = listBts.get(0).getTramDaId();
                    if (tramDAId != null && !tramDAId.equals("")) {
                        List<TramDAHistoryBO> listTramDA = facade.tramDAHistory(startTime, endTime, action, userId, tramDAId);
                        if (listTramDA != null && listTramDA.size() > 0) {
                            mm.put("listTramDA", listTramDA);
                        }
                        List<TramQHHistoryBO> listTramQH = facade.tramQHHistory(startTime, endTime, action, userId, tramDAId);
                        if (listTramQH != null && listTramQH.size() > 0) {
                            mm.put("listTramQH", listTramQH);
                        }
                    }
                    
                }
                //
            }
            
            if (type.equals("CELL")) {
                List<Cell2GReportBO> listCell = facade.cell2GReport(startTime, endTime, action, userId, code);
                if (listCell != null && listCell.size() > 0) {
                    mm.put("listCell", listCell);
                }
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        mm.put("type", type);
        
        mm.put("userId", userId);
        mm.put("action", action);
        mm.put("startTime", startTime);
        mm.put("endTime", endTime);
        mm.put("code", code);
        return LIST;
        
    }
    
    @RequestMapping(value = "/ps", method = RequestMethod.GET)
    public String hisPs(@RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "type", required = false) String type, @RequestParam(value = "userId", required = false) String userId, @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime, @RequestParam(value = "action", required = false) String action,
            ModelMap mm, HttpServletRequest request) {
        
        code = code == null ? "" : code;
        type = type == null ? "" : type;
        userId = userId == null ? "" : userId;
        action = action == null ? "" : action;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -180); //minus number would decrement the days
        Date dateF = cal.getTime();
        endTime = endTime == null ? dateFormat.format(date) : endTime;
        startTime = startTime == null ? dateFormat.format(dateF) : startTime;
        HistoryFacade facade = new HistoryFacade();
        List<PgwInfoBO> list = facade.psHistory(startTime, endTime, action, userId, code);
        mm.put("list", list);
        mm.put("type", type);
        
        mm.put("userId", userId);
        mm.put("action", action);
        mm.put("startTime", startTime);
        mm.put("endTime", endTime);
        mm.put("code", code);
        return "history/core_ps/list";
        
    }
    
    @RequestMapping(value = "/cs", method = RequestMethod.GET)
    public String hisCs(@RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "type", required = false) String type, @RequestParam(value = "userId", required = false) String userId, @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime, @RequestParam(value = "action", required = false) String action,
            ModelMap mm, HttpServletRequest request) {
        
        code = code == null ? "" : code;
        type = type == null ? "" : type;
        userId = userId == null ? "" : userId;
        action = action == null ? "" : action;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -180); //minus number would decrement the days
        Date dateF = cal.getTime();
        endTime = endTime == null ? dateFormat.format(date) : endTime;
        startTime = startTime == null ? dateFormat.format(dateF) : startTime;
        HistoryFacade facade = new HistoryFacade();
        List<CsCoreBO> list = facade.csHistory(startTime, endTime, action, userId, code);
        mm.put("list", list);
        mm.put("type", type);
        
        mm.put("userId", userId);
        mm.put("action", action);
        mm.put("startTime", startTime);
        mm.put("endTime", endTime);
        mm.put("code", code);
        return "history/core_cs/list";
        
    }
    
    @ModelAttribute("thietBiList")
    public List findAllThietBi() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllThietBi("");
    }
    
    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
    }
    
    @ModelAttribute("neList")
    public List findAllNE() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllLoaiNe("");
    }
    
    @ModelAttribute("tramList")
    public List findAllTram() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllLoaiTram("");
    }
    
    @ModelAttribute("dvList")
    public List findAllDonVi(HttpServletRequest request) {
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhs = String.join(",", tinhManager);
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllDonVi("", "", tinhs);
    }
    
    @ModelAttribute("loaitruyendanList")
    public List findAllLoaiTruyenDan() {
        CategoriesFacade facade = new CategoriesFacade();
        return facade.findAllTruyenDan("");
    }
    
}
