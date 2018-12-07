/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.managerAdmin;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.PagingUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.security.SetSessionServlet;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.formbean.GrantMenuForm;
import com.vnpt.media.rims.formbean.GroupBeanBO;
import com.vnpt.media.rims.formbean.GroupMenuForm;
import com.vnpt.media.rims.formbean.GroupMenuItem;
import jdk.management.resource.internal.inst.UnixAsynchronousServerSocketChannelImplRMHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/permission")
public class PermissionController {

    private static Logger logger = LogManager.getLogger(PermissionController.class);

    private static final String USER_ATTR_LIST = "managerAdmin/userAttr/userAttrList";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/userAttr", method = RequestMethod.GET)
    public String userAttr(@RequestParam("uid") String uid, ModelMap mm, HttpServletRequest request) {

        ManagerAdminFacade facade = new ManagerAdminFacade();
        logger.info("Action userAttr");

        //huan.nguyen bo sung kiem tra quyen theo thuoc tinh
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        UserBO userB0 = facade.findByUserName(uid);
        mm.put("name", userB0.getUsername());
        mm.put("uid", userB0.getId());

        List<UserAttrBO> listUserAtts = adminFacade.findUserAttrByUserId(String.valueOf(userB0.getId()));

        //List<GroupBO> groupList = adminFacade.getGroupByUserId(String.valueOf(userB0.getId()));

        StringBuffer sb = new StringBuffer();
        String dataSelected = "data-jstree='{ \"selected\" : true}'";
        List<ObjectListBO> objectList = adminFacade.findAllObjectList("");

        sb.append("<ul>");
        boolean isSelected = false;

        for (int i = 0; i < objectList.size(); i++) {

            sb.append("<li id='OBJECT_" + objectList.get(i).getId() + "' >");
            sb.append(objectList.get(i).getName());
            List<AttClassListBO> classList = adminFacade.findAttrClassListByObjectId(String.valueOf(objectList.get(i).getId()));
            if (classList.size() > 0) {
                sb.append("<ul>");
                for (int j = 0; j < classList.size(); j++) {
                    List<AttributeBO> attrList =  adminFacade.findAttrByClassId(classList.get(j).getId());

                    sb.append("<li id='CLASS_" + classList.get(j).getId() + "' title='" + classList.get(j).getCode()  + "'>");
                    sb.append(classList.get(j).getName());

                    if(attrList.size() > 0){
                        sb.append("<ul>");
                        //duyệt danh sách thuộc tính theo ATTR_CLASS
                        for (int k = 0; k < attrList.size(); k++) {
                            sb.append("<li id='ATTR_" + attrList.get(k).getId() + "' title='"+attrList.get(k).getAttrCode() +"'>");
                            sb.append(attrList.get(k).getAttrName());

                                    //Thêm thuộc tính quyền VIEW, UPDATE. Kiểm tra trạng thái và hiển thị
                                    sb.append("<ul>");
                                        String idChild = classList.get(j).getId() + "-" + attrList.get(k).getId() + "-";
                                        sb.append("<li " + (checkAttr(listUserAtts, attrList.get(k).getId(), classList.get(j).getId(), "VIEW") ? dataSelected : "") + " class='groupClass' id='" + idChild + "VIEW' >Xem</li>");
                                        sb.append("<li " + (checkAttr(listUserAtts, attrList.get(k).getId(), classList.get(j).getId(), "UPDATE") ? dataSelected : "") + " class='groupClass' id='" + idChild + "UPDATE' >Cập nhật</li>");
                                    sb.append("</ul>");

                            sb.append("</li>");
                        }

                        sb.append("</ul>");
                    }

                    sb.append("</li>");
                }
                sb.append("</ul>");
            }

            sb.append("</li>");
        }
        sb.append("</ul>");
        //

        mm.addAttribute("data", sb.toString());
        return USER_ATTR_LIST;
    }


    @RequestMapping(value = "/updateUserAttr", method = RequestMethod.POST)
    public String updateUserAttr(@ModelAttribute("grantMenuForm") GrantMenuForm grantMenuForm, @ModelAttribute("uid") Integer uid,
                                   ModelMap mm, HttpServletRequest request,HttpServletResponse response, RedirectAttributes attr, Locale locale) throws IOException {

        List<String> sqlsDelete = new ArrayList<String>();
        List<String> sqlsInsert = new ArrayList<String>();
        List<String> sqls = new ArrayList<String>();
        //sqls.add(tempDelete);

        String[] listMenu = grantMenuForm.getListmenu().split(",");
        for (int i = 0; i < listMenu.length; i++) {
            if(listMenu[i].length() > 0){
                String[] item = listMenu[i].split("-");
                String tempDelete = "delete from CMS_USER_ATTR ua where ua.user_id = " + uid + " and ua.attr_id in (select attr_id from ATTR_LIST al where al.attr_class_id = " + item[0] + ")";
                sqlsDelete.add(tempDelete);

                String temp = " insert into CMS_USER_ATTR(id, user_id, attr_id, action) values (SEQ_CMS_USER_ATTR_ID.nextval, " + uid + ","
                        + item[1] + ","
                        + "'" + item[2] + "' ) ";
                sqlsInsert.add(temp);
            }
        }
        sqls.addAll(sqlsDelete);
        sqls.addAll(sqlsInsert);
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();

        if (adminFacade.updateUserAttr(sqls.stream().distinct().collect(Collectors.toList())).equals("1")) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));

            // set lai quyen  session
            SetSessionServlet setSession = new SetSessionServlet();
            setSession.initSession(request, response);
        } else {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        return "redirect:" + request.getHeader("referer");
    }


    private boolean checkAttr(List<UserAttrBO> userAttrList, Long attrId, Long attrClassId, String action) {
        if(userAttrList.stream().anyMatch(x->x.getAttr().getId().equals(attrId) && x.getAttClass().getId() == attrClassId && x.getAction().equals(action)))
        {
            return  true;
        }

        if(userAttrList.stream().anyMatch(x->x.getAttr().getId().equals(attrId) && x.getAttClass().getId() == attrClassId && x.getAction().equals("NOT" + action)))
        {
            return  false;
        }
//        UserAttrBO userAttr = userAttrList.stream().filter(x->x.getAttr().getId().equals(attrId) && x.getAttClass().getId() == attrClassId).findFirst().orElse(null);
//        if(userAttr == null)
//        {
//            return  true;
//        }
//        if(userAttr.getAction().equals("NOT" + action))
//            return  false;
        return true;
    }
}
