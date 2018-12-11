/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller;

import com.google.gson.Gson;
import com.vnpt.media.rims.bean.BaoDuongNetExcel;
import com.vnpt.media.rims.bean.LogConfigBO;
import com.vnpt.media.rims.bean.ObjectLog;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.ContentDataTableItem;
import com.vnpt.media.rims.common.Function;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.facade.CellsFacade;
import com.vnpt.media.rims.facade.LogFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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

    private static final Logger logger = LogManager.getLogger(LogController.class);

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

    @RequestMapping(value = "/findLogObject", method = RequestMethod.GET)
    public String findLogObject(
            ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "objectCode", required = false) String objectCode,
            @RequestParam(value = "object", required = false) String object,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate) {
        mm.put("objectCode", objectCode);
        mm.put("object", object);
        mm.put("userName", userName);
        mm.put("action", action);
        mm.put("fromDate", fromDate);
        mm.put("toDate", toDate);
        return "log/object/object_log";
    }

    @RequestMapping(value = "/searchLogObject", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String searchLogObject(HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            logger.info("user: {}, ip: {},mem: {}, search", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            String[] tinhManagerArr = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhManagers = String.join(",", tinhManagerArr);
            logger.debug("tinhManagers: {}", tinhManagers);
            int draw = -1;
            String prs_start_record = "";
            String prs_length_page = "";
            String prs_global_search = "";
            String prs_list_column_name = "";
            String prs_list_column_search = "";
            String prs_column_to_sort = "";
            String param_sort_column = "";
            String prs_sort_direction = "";
            String pList = "";
            ArrayList<String> ar_name = new ArrayList<>();
            ArrayList<String> ar_search_value = new ArrayList<>();

            String columnPermisson = "";
            // duyệt tất cả các tham số truyền vào request
            for (Enumeration items = request.getParameterNames(); items.hasMoreElements();) {
                String param_name = (String) items.nextElement();
                String param_value = request.getParameter(param_name);
                if (param_name.equals("draw")) {
                    draw = Integer.parseInt(param_value);
                } else if (param_name.startsWith("order") && param_name.contains("column")) {
                    param_sort_column = param_value;
                    prs_column_to_sort = param_value;
                } else if (param_name.startsWith("order") && param_name.contains("dir")) {
                    prs_sort_direction = param_value;
                } else if (param_name.equals("start")) {
                    prs_start_record = String.valueOf(Integer.parseInt(param_value) + 1);
                } else if (param_name.equals("length")) {
                    prs_length_page = param_value;
                } else if (param_name.equals("search[value]")) {
                    prs_global_search = param_value;
                } else if (param_name.startsWith("columns") && param_name.contains("name")) {
                    ar_name.add(param_value);
                } else if (param_name.startsWith("columns") && param_name.contains("search") && param_name.contains("value")) {
                    ar_search_value.add(param_value);
                }
                pList += param_name + "=" + param_value + ",";
            }
            //bổ sung phân quyền vào điều kiện tìm kiếm
            Integer index = ar_name.indexOf("tinhtp_id");
            if (index != null && !ar_search_value.isEmpty() && index >= 0) {
                String temp = ar_search_value.get(index);
                if (temp == null || temp.isEmpty()) {
                    ar_search_value.set(index, tinhManagers);
                }
            }
            //            
            int total_column = ar_name.size();
            for (int i = 0; i < total_column; i++) {
                String name = ar_name.get(i);
                String value = ar_search_value.get(i);
                // update tên cột sorting
                if (!param_sort_column.equals("0")) {
                    if (Integer.parseInt(param_sort_column) == i) {
                        prs_column_to_sort = ar_name.get(i);
                    }
                }
                // check xem cột đó có giá trị cần search ko
                if (!value.isEmpty()) {
                    prs_list_column_name = prs_list_column_name + ";" + name;
                    prs_list_column_search = prs_list_column_search + ";" + value;
                }
            }
            String[] recordsTotal = new String[1];
            String[] recordsFiltered = new String[1];
            ArrayList<ObjectLog> list = null;
            try {
                logger.debug("prs_start_record :{}, prs_length_page:{}, prs_global_search:{}, prs_list_column_name:{}, prs_list_column_search:{}, prs_column_to_sort:{}, prs_sort_direction:{} ",
                        prs_start_record, prs_length_page, prs_global_search, prs_list_column_name, prs_list_column_search, prs_column_to_sort, prs_sort_direction);
                list = LogFacade.searchObjectLog(prs_start_record, prs_length_page,
                        prs_global_search, prs_list_column_name, prs_list_column_search, prs_column_to_sort,
                        prs_sort_direction, recordsTotal, recordsFiltered);
                logger.info("user: {}, ip: {},mem: {}", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            } catch (DAOException ex) {
                logger.error(ex.getMessage(), ex);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            int count = 0;
            // chuyển list  thành list String
            List<List<String>> data = new ArrayList();
            if (list != null) {
                for (ObjectLog item : list) {
                    ArrayList<String> ls = new ArrayList();
                    // thứ tự phải khớp với thứ tự của các cột trong table của trang jsp
                    count++;
                    ls.add(count + "");
                    ls.add(item.getObjectName());
                    ls.add(item.getAttributeName() == null ? "" : item.getAttributeName());
                    ls.add(item.getOldValue() == null ? "" : item.getOldValue());
                    ls.add(item.getNewValue() == null ? "" : item.getNewValue());
                    ls.add(item.getUserName() == null ? "" : item.getUserName());
                    ls.add(item.getActionDate() == null ? "" : item.getActionDate());
                    ls.add(item.getActionName() == null ? "" : item.getActionName());
                    ls.add(item.getObjectCode() == null ? "" : item.getObjectCode());
                    ls.add(item.getObjectType() == null ? "" : item.getObjectType());
                    data.add(ls);
                }
            }
            // chuyển thành object json
            ContentDataTableItem responseObj = new ContentDataTableItem(draw, recordsTotal[0], recordsFiltered[0], data);
            Gson gson = new Gson();
            logger.info("user: {}, ip: {},mem: {} end", user.getUsername(), request.getRemoteAddr(), Function.getInfoMemory());
            return gson.toJson(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }

        return null;

    }
}
