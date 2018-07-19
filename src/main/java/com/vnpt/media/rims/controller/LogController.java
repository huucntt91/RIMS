/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller;

import com.vnpt.media.rims.bean.LogConfigBO;
import com.vnpt.media.rims.facade.LogFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/object")
public class LogController {

    private static final Logger logger = LogManager.getLogger(LoginLogController.class);

    private static final String LIST = "log/object/list";
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(@RequestParam(value = "group", required = false) String groupName,
            @RequestParam(value = "object", required = false) String objectName,
            ModelMap mm, HttpServletRequest request) {
        return LIST;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String search(@RequestParam(value = "group") String groupName,
            @RequestParam(value = "object", required = false) String objectName,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<LogConfigBO> list;
        Map<String, String> map = new HashMap();
        try {

            list = LogFacade.findConfig(groupName, objectName);
            if (list != null && !list.isEmpty()) {
                for (LogConfigBO item : list) {
                    String user_id = item.getUser_id();
                    String actionStr = item.getAction();
                    String fromDateStr = item.getFrom_date();
                    String toDateStr = item.getTo_date();
                    if (user_id != null && !user_id.isEmpty()) {
                        user_id = user_id.replace("p_user_name", userName);
                        item.setUser_id(user_id);
                    }
                    if (fromDate != null && !fromDate.isEmpty() && fromDateStr != null && !fromDateStr.isEmpty()) {
                        fromDateStr = fromDateStr.replace("p_from_date", fromDate);
                        item.setFrom_date(fromDateStr);
                    } else {
                        item.setFrom_date(null);
                    }
                    if (toDate != null && !toDate.isEmpty() && toDateStr != null && !toDateStr.isEmpty()) {
                        toDateStr = toDateStr.replace("p_to_date", toDate);
                        item.setTo_date(toDateStr);
                    } else {
                        item.setTo_date(null);
                    }

                    if (action != null && !action.isEmpty() && actionStr != null && !actionStr.isEmpty()) {
                        actionStr = actionStr.replace("p_action", action);
                        item.setAction(actionStr);
                    } else {
                        item.setAction(null);
                    }

                    List<Map<String, String>> data;
                    data = LogFacade.findData(item);
                    item.setData(data);
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }

    //lay danh sach object the group name
    @RequestMapping(value = "/getObject/{group}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getObject(@PathVariable(value = "group") String groupName, ModelMap mm,
            HttpServletRequest request) throws IOException {
        List<LogConfigBO> list;
        try {
            list = LogFacade.findConfig(groupName, null);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @ModelAttribute("groups")
    public List findGroup() {
        List<LogConfigBO> list = null;
        try {
            list = LogFacade.findGroup();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

}
