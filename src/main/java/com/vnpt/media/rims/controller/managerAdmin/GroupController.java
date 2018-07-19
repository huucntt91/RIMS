/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.managerAdmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.bean.HierarchicalFunction;
import com.vnpt.media.rims.bean.MenuBO;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.security.SetSessionServlet;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.formbean.GrantMenuForm;
import com.vnpt.media.rims.formbean.GroupBeanBO;
import com.vnpt.media.rims.formbean.GroupMenuForm;
import com.vnpt.media.rims.formbean.GroupMenuItem;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/group")
public class GroupController {

    private static Logger logger = LogManager.getLogger(GroupController.class);

    private static final String GROUP_LIST = "managerAdmin/group/groupList";
    private static final String ATT = "list_group";
    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("phancapList")
    public List findAllCp() {
        ManagerAdminFacade facade = new ManagerAdminFacade();
        return facade.findAllPhancap("", "");
    }

    @ModelAttribute("networkTypeList")
    public List findAllNetworkType() {
        ManagerAdminFacade facade = new ManagerAdminFacade();
        return facade.findAllNetworkType("", "");
    }

    @ModelAttribute("familyTypeList")
    public List findAllFamilyType() {
        ManagerAdminFacade facade = new ManagerAdminFacade();
        return facade.findAllFamilyType("", "");
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initGroup(ModelMap mm, HttpServletRequest request) {
        logger.info("Action init user");
        ManagerAdminFacade facade = new ManagerAdminFacade();
        List<GroupBO> lst = facade.findAllGroup("");
        mm.put(ATT, lst);
        return GROUP_LIST;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("name") String name, ModelMap mm, HttpServletRequest request) {
        name = ((name == null || name.equals("")) ? "null" : name);
        ManagerAdminFacade facade = new ManagerAdminFacade();
        logger.info("Action search user");
        List<GroupBO> lst = facade.findAllGroup(name);
        mm.put(ATT, lst);
        mm.put("name", name);
        return GROUP_LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        GroupBO groupBO = new GroupBO();
        mm.addAttribute("gbo", groupBO);
        return "managerAdmin/group/editGroup";
    }

    @RequestMapping(value = "/view/{groupid}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "groupid") String groupId, ModelMap mm,
            Locale locale, RedirectAttributes attr) throws Exception {
        logger.debug("view User Action");
        try {
            if (StringUtils.hasText(groupId)) {
                ManagerAdminFacade facade = new ManagerAdminFacade();
                GroupBO groupBO = facade.findByGroupId(groupId);
                mm.put("group", groupBO);
                mm.addAttribute("gbo", groupBO);
            } else {

            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/group/init";
        }

        return "managerAdmin/group/editGroup";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "gbo") GroupBO gbo, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        try {

            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
                        break;
                    } else {
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                    }
                }
                mm.addAttribute("gbo", gbo);
                return "redirect:/group/preAdd";
            }
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            adminFacade.addGroup(gbo);
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("UK_GROUP_NAME") != -1) {
                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_GROUP_NAME", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/group/preAdd";
                }
            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/group/preAdd";
        }
        GroupBO groupBO = new GroupBO();
        mm.addAttribute("gbo", groupBO);
        String msg = messageSource.getMessage("admin.common.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));

        // set lai quyen  session
        SetSessionServlet setSession = new SetSessionServlet();
        setSession.initSession(request, response);
        return "redirect:/group/init";

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "gbo") GroupBO gbo,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        mm.put("ubo", gbo);
        logger.info("update Group Action");
        try {
            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
                        break;
                    } else {
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                    }
                }
                mm.addAttribute("gbo", gbo);
                return "redirect:/group/view/" + gbo.getId();
//                        return "redirect:/group/view/" + StringUtils.encodePassword(gbo.getId()+"","MD5");                         
            }
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            adminFacade.updateGroup(gbo);
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("UK_GROUP_NAME") != -1) {
                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_GROUP_NAME", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/group/view/" + gbo.getId();
                }
            }
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/group/view/" + gbo.getId();
        }
        String msg = messageSource.getMessage("admin.common.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));

        // set lai quyen  session
        SetSessionServlet setSession = new SetSessionServlet();
        setSession.initSession(request, response);
        return "redirect:/group/init";
    }

    @RequestMapping(value = "/delete/{groupId}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "groupId") String groupId,
            Locale locale, RedirectAttributes attr) throws Exception {
        logger.debug("Delete Group Action");
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            GroupBO groupBO = adminFacade.findByGroupId(groupId);
            adminFacade.deleteGroup(groupId, groupBO);
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("FK_GROUP_ID") != -1) {
                    String msg = messageSource.getMessage("admin.excute.sql.violated.FK_GROUP_ID", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/group/init";
                }
            }
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            logger.error("Exception :", e);
            return "redirect:/group/init";
        }
        String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
        return "redirect:/group/init";
    }

    ///m//////
    @RequestMapping(value = "/menu/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request) {
        logger.info("Init group menu page");
        GroupMenuForm groupMenuForm = new GroupMenuForm();
        List<GroupMenuItem> listGroupMenuItem = new ArrayList<GroupMenuItem>();
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();

//        List<MenuBO> menuBOList = adminFacade.getChildrenMenuByUserId(null, null, null, null);
        List<HierarchicalFunction> menuBOList = adminFacade.getHierarchicalFunction();
        List<GroupBO> groupBOList = adminFacade.getGroup("");
        Map<String, Long> groupMenuBOListMap = adminFacade.putGroupMenu();

        for (int i = 0; i < menuBOList.size(); i++) {
            GroupMenuItem menuItem = new GroupMenuItem();

            menuItem.setMenuId(menuBOList.get(i).getId());
            menuItem.setMenuName(menuBOList.get(i).getName());
            menuItem.setLevel(menuBOList.get(i).getLevel());
            List<GroupBeanBO> listGroupBeanBO = new ArrayList<GroupBeanBO>();
            GroupBeanBO tempGroup;
            for (int j = 0; j < groupBOList.size(); j++) {
                tempGroup = new GroupBeanBO();
                tempGroup.setId(groupBOList.get(j).getId());
                tempGroup.setName(groupBOList.get(j).getName());
                tempGroup.setCheck(groupMenuBOListMap.get(menuBOList.get(i).getId() + "-" + groupBOList.get(j).getId()) == null ? false : true);
                listGroupBeanBO.add(tempGroup);
            }
            menuItem.setGroupBeanBO(listGroupBeanBO);
            if (i == 0) {
                GroupMenuItem header = new GroupMenuItem();
                header.setStt("#");
                header.setMenuName("Tên Menu");
                header.setGroupBeanBO(listGroupBeanBO);
                listGroupMenuItem.add(header);
            }
            listGroupMenuItem.add(menuItem);
        }

        groupMenuForm.setGroupMenuItem(listGroupMenuItem);
        mm.put("groupMenuForm", groupMenuForm);
        return "managerAdmin/group/groupMenu";
    }

    @RequestMapping(value = "/menu/init/{groupId}", method = RequestMethod.GET)
    public String initGroupMenu(ModelMap mm, @PathVariable(value = "groupId") String groupId, HttpServletRequest request) {
        logger.info("Init group menu page");
        GroupMenuForm groupMenuForm = new GroupMenuForm();
        List<GroupMenuItem> listGroupMenuItem = new ArrayList<GroupMenuItem>();
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();

//        List<MenuBO> menuBOList = adminFacade.getChildrenMenuByUserId(null, null, null, null);
        List<HierarchicalFunction> menuBOList = adminFacade.getHierarchicalFunction();
        List<GroupBO> groupBOList = adminFacade.getGroup(groupId);
        Map<String, Long> groupMenuBOListMap = adminFacade.putGroupMenu();

        for (int i = 0; i < menuBOList.size(); i++) {
            GroupMenuItem menuItem = new GroupMenuItem();

            menuItem.setMenuId(menuBOList.get(i).getId());
            menuItem.setMenuName(menuBOList.get(i).getName());
            menuItem.setLevel(menuBOList.get(i).getLevel());
            List<GroupBeanBO> listGroupBeanBO = new ArrayList<GroupBeanBO>();
            GroupBeanBO tempGroup;
            for (int j = 0; j < groupBOList.size(); j++) {
                tempGroup = new GroupBeanBO();
                tempGroup.setId(groupBOList.get(j).getId());
                tempGroup.setName(groupBOList.get(j).getName());
                tempGroup.setCheck(groupMenuBOListMap.get(menuBOList.get(i).getId() + "-" + groupBOList.get(j).getId()) == null ? false : true);
                listGroupBeanBO.add(tempGroup);
            }
            menuItem.setGroupBeanBO(listGroupBeanBO);
            if (i == 0) {
                GroupMenuItem header = new GroupMenuItem();
                header.setStt("#");
                header.setMenuName("Tên Menu");
                header.setGroupBeanBO(listGroupBeanBO);
                listGroupMenuItem.add(header);
            }
            listGroupMenuItem.add(menuItem);
        }

        groupMenuForm.setGroupMenuItem(listGroupMenuItem);
        mm.put("groupMenuForm", groupMenuForm);
        mm.put("groupId", groupId);
        return "managerAdmin/group/groupMenu";
    }

//    @RequestMapping(value = "/menu/updateGroupMenu", method = RequestMethod.POST)
//    public String updateMenuGroup(@ModelAttribute("groupMenuForm") GroupMenuForm groupMenuForm, RedirectAttributes attr) throws Exception {
//        List<String> sqls = new ArrayList<String>();
//        if (groupMenuForm.getGroupMenuItem() != null) {
//            String tempDelete = "delete from group_menu";
//            sqls.add(tempDelete);
//            for (int i = 1; i < groupMenuForm.getGroupMenuItem().size(); i++) {
//                if (groupMenuForm.getGroupMenuItem().get(i).getGroupBeanBO() != null) {
//                    for (int j = 0; j < groupMenuForm.getGroupMenuItem().get(i).getGroupBeanBO().size(); j++) {
//                        if (groupMenuForm.getGroupMenuItem().get(i).getGroupBeanBO().get(j).getCheck()) {
//                            String temp = "insert into group_menu(id, menu_id,group_id) values (SQ_CMS_GROUP_MENU_ID.nextval, " + groupMenuForm.getGroupMenuItem().get(i).getMenuId() + ","
//                                    + groupMenuForm.getGroupMenuItem().get(i).getGroupBeanBO().get(j).getId() + ")";
//                            sqls.add(temp);
//                        }
//                    }
//                }
//            }
//            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//            adminFacade.updateGroupMenu(sqls);
//            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//        }
//        return "redirect:/group/menu/init";
//    }
    @RequestMapping(value = "/menu/init/updateGroupMenu/{groupId}", method = RequestMethod.POST)
    public String updateMenuGroup(@ModelAttribute("groupMenuForm") GroupMenuForm groupMenuForm,
            @PathVariable(value = "groupId") String groupId, RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> sqls = new ArrayList<String>();
        
        if (groupMenuForm.getGroupMenuItem() != null) {
            String tempDelete = "delete from group_menu";
            sqls.add(tempDelete);
            for (int i = 1; i < groupMenuForm.getGroupMenuItem().size(); i++) {
                if (groupMenuForm.getGroupMenuItem().get(i).getGroupBeanBO() != null) {
                    for (int j = 0; j < groupMenuForm.getGroupMenuItem().get(i).getGroupBeanBO().size(); j++) {
                        if (groupMenuForm.getGroupMenuItem().get(i).getGroupBeanBO().get(j).getCheck()) {
                            String temp = "insert into group_menu(id, menu_id,group_id) values (SQ_CMS_GROUP_MENU_ID.nextval, " + groupMenuForm.getGroupMenuItem().get(i).getMenuId() + ","
                                    + groupMenuForm.getGroupMenuItem().get(i).getGroupBeanBO().get(j).getId() + ")";
                            sqls.add(temp);
                        }
                    }
                }
            }
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            adminFacade.updateGroupMenu(sqls);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));

            // set lai quyen  session
            SetSessionServlet setSession = new SetSessionServlet();
            setSession.initSession(request, response);
        }

        return "redirect:/group/menu/init/" + groupId;
    }

    public String CreateChildMenu(MenuBO mn, String groupId) throws IOException {
        try {
            StringBuffer sb = new StringBuffer();
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<MenuBO> menuBOList = adminFacade.getAllChildrenMenuById(mn.getId() + "", groupId);

            String dataSelected = "data-jstree='{ \"selected\" : true}'";
            //String dataSelected = "";
            boolean isSelected = true;

            for (int i = 0; i < menuBOList.size(); i++) {
                if (!menuBOList.get(i).isSelected()) {
                    isSelected = false;
                    break;
                }

            }
            if (menuBOList.size() > 0) {
                sb.append("<ul>");
                for (int i = 0; i < menuBOList.size(); i++) {
                    String contentChild = CreateChildMenu(menuBOList.get(i), groupId);
                    
                    if (!contentChild.equals("")) {
                        sb.append("<li id='" + menuBOList.get(i).getId() + "' "  + ">" + menuBOList.get(i).getName());
                        sb.append(contentChild);
                        sb.append("</li>");

                    } else {
//                sb.append("<li id='" + menuBOList.get(i).getId() + "'>" + menuBOList.get(i).getName());
                        sb.append("<li id='" + menuBOList.get(i).getId() + "' " + (menuBOList.get(i).isSelected() ? dataSelected : "") + ">" + menuBOList.get(i).getName());
                        //sb.append(contentChild);
                        sb.append("</li>");
                    }
                }

                sb.append("</ul>");
            }
            return sb.toString();

        } catch (Exception e) {
            logger.error("Exception :", e);
        }
        return "";

    }

    // tu update
    @RequestMapping(value = "/postUpdateGroupMenu", method = RequestMethod.POST)
    public String postUpdateGroupMenu(@ModelAttribute("grantMenuForm") GrantMenuForm grantMenuForm,
            ModelMap mm, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr, Locale locale) throws IOException {
        List<String> sqls = new ArrayList<String>();
        String tempDelete = "delete from group_menu where group_id = " + grantMenuForm.getGroupid();
        sqls.add(tempDelete);
        String[] listMenu = grantMenuForm.getListmenu().split(",");
        for (int i = 0; i < listMenu.length; i++) {
            if(listMenu[i].length() > 0){
                String temp = " insert into group_menu(id, menu_id,group_id) values (SQ_CMS_GROUP_MENU_ID.nextval, " + listMenu[i] + ","
                        + grantMenuForm.getGroupid() + ") ";
                sqls.add(temp);
            }
        }

        ManagerAdminFacade adminFacade = new ManagerAdminFacade();

        if (adminFacade.updateGroupMenu(sqls).equals("1")) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } else {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }

        // set lai quyen  session
        SetSessionServlet setSession = new SetSessionServlet();
        setSession.initSession(request, response);
        return "redirect:/group/preUpdateGroupMenu/" + grantMenuForm.getGroupid();
    }

    @RequestMapping(value = "/preUpdateGroupMenu/{groupId}", method = RequestMethod.GET)
    public String preUpdateGroupMenu(RedirectAttributes attr, ModelMap mm,
            @PathVariable(value = "groupId") String groupId, HttpServletRequest request) throws Exception {
        StringBuffer sb = new StringBuffer();
        String dataSelected = "data-jstree='{ \"selected\" : true}'";

        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<GroupBO> groupBO = adminFacade.getGroup(groupId);

        List<MenuBO> menuBOList = adminFacade.getAllChildrenMenuById("0", groupId);
        
        sb.append("<ul>");
        boolean isSelected = false;
//        for (int i = 0; i < menuBOList.size(); i++) {
//            if (!menuBOList.get(i).isSelected()) {
//                isSelected = false;
//                break;
//            }
//
//        }

        for (int i = 0; i < menuBOList.size(); i++) {
            //loop a Map
            //String selected = "";
//            for (Map.Entry<String, Long> entry : groupMenuBOListMap.entrySet()) {
//                
//                if(entry.getKey().equals(groupId) && entry.getValue().equals(menuBOList.get(i).getId()))
//                {
//                    selected = dataSelected;
//                    break;
//                }
//                    //
//            }
            
            sb.append("<li id='" + menuBOList.get(i).getId() + "' " + (isSelected ? dataSelected : "") + ">");
            sb.append(menuBOList.get(i).getName());
            sb.append(CreateChildMenu(menuBOList.get(i), groupId));
            sb.append("</li>");
        }
        sb.append("</ul>");
        
//        fc_get_all_children_menu
//        String test = "  <ul>\n"
//                + "                    <li id='1'>Root one</li>\n"
//                + "                    <li id='2'     data-jstree='{ \"selected\" : true}' >Root two\n"
//                + "                        <ul>\n"
//                + "                            <li id='21'>Child node 1</li>\n"
//                + "                            <li id='22'>\n"
//                + "                                Child node 2\n"
//                + "                                <ul>\n"
//                + "                                    <li id='221'> child 2.1</li>\n"
//                + "                                    <li id='222'> child 2.2</li>\n"
//                + "                                </ul>\n"
//                + "                            </li>\n"
//                + "                        </ul>\n"
//                + "                    </li>\n"
//                + "                </ul>";
        mm.addAttribute("data", sb.toString());
        mm.addAttribute("groupId", groupId);
        mm.addAttribute("groupName", groupBO.get(0).getName());
        return "managerAdmin/group/groupMenuT";

    }

    @RequestMapping(value = "/updateGroupMenu/{groupId}", method = RequestMethod.GET)
    public @ResponseBody
    String updateGroupMenu(RedirectAttributes attr, ModelMap mm, @PathVariable(value = "groupId") String groupId) throws Exception {
        String test = "  <ul>\n"
                + "                    <li id='1'>Root one</li>\n"
                + "                    <li id='2' checked>Root two\n"
                + "                        <ul>\n"
                + "                            <li id='21'>Child node 1</li>\n"
                + "                            <li id='22'>\n"
                + "                                Child node 2\n"
                + "                                <ul>\n"
                + "                                    <li id='221'> child 2.1</li>\n"
                + "                                    <li id='222'> child 2.2</li>\n"
                + "                                </ul>\n"
                + "                            </li>\n"
                + "                        </ul>\n"
                + "                    </li>\n"
                + "                    <li id='3'>Root three</li>\n"
                + "                </ul>";
        return test;
    }

    @RequestMapping(value = "/classgroup/{groupId}", method = RequestMethod.GET)
    public String classGroup(RedirectAttributes attr, ModelMap mm,
            @PathVariable(value = "groupId") String groupId, HttpServletRequest request) throws Exception {
        StringBuffer sb = new StringBuffer();
        String dataSelected = "data-jstree='{ \"selected\" : true}'";

        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<GroupBO> groupBO = adminFacade.getGroup(groupId);
        List<ObjectListBO> objectList = adminFacade.findAllObjectList("");

        sb.append("<ul>");
        boolean isSelected = false;
//        for (int i = 0; i < menuBOList.size(); i++) {
//            if (!menuBOList.get(i).isSelected()) {
//                isSelected = false;
//                break;
//            }
//
//        }
        List<AttClassGroupBO> classGroup = adminFacade.findAttClassGroupByGroupId(groupId);
        for (int i = 0; i < objectList.size(); i++) {

            sb.append("<li id='OBJECT_" + objectList.get(i).getId() + "' >");
            sb.append(objectList.get(i).getName());
            List<AttClassListBO> classList = adminFacade.findAttrClassListByObjectId(String.valueOf(objectList.get(i).getId()));
            if (classList.size() > 0) {
                sb.append("<ul>");

                for (int j = 0; j < classList.size(); j++) {
                    sb.append("<li id='CLASS_" + classList.get(j).getId() + "' >");
                    sb.append(classList.get(j).getName());
                    sb.append("<ul>");
                    String idChild = objectList.get(i).getId() + "-" + classList.get(j).getId() + "-";
                    sb.append("<li " + (checkGroupClass(classGroup, classList.get(j).getId(), groupId, "S") ? dataSelected : "") + " class='groupClass' id='" + idChild + "S' >Select</li>");
//                    sb.append("<li " + (checkGroupClass(classGroup, classList.get(j).getId(), groupId, "U") ? dataSelected : "") + "  class='groupClass' id='" + idChild + "I' >Insert</li>");
                    sb.append("<li " + (checkGroupClass(classGroup, classList.get(j).getId(), groupId, "U") ? dataSelected : "") + " class='groupClass' id='" + idChild + "U' >Update</li>");
//                    sb.append("<li " + (checkGroupClass(classGroup, classList.get(j).getId(), groupId, "D") ? dataSelected : "") + " class='groupClass' id='" + idChild + "D' >Delete</li>");
                    sb.append("</ul>");
                    sb.append("</li>");
                }
                sb.append("</ul>");
            }

            sb.append("</li>");
        }
        sb.append("</ul>");
        //

        mm.addAttribute("data", sb.toString());
        mm.addAttribute("groupId", groupId);
        mm.addAttribute("groupName", groupBO.get(0).getName());
        return "managerAdmin/group/groupClass";
    }

    @RequestMapping(value = "/updateClassGroup", method = RequestMethod.POST)
    public String updateClassGroup(@ModelAttribute("grantMenuForm") GrantMenuForm grantMenuForm,
            ModelMap mm, HttpServletRequest request,HttpServletResponse response, RedirectAttributes attr, Locale locale) throws IOException {
      
        List<String> sqls = new ArrayList<String>();
        String tempDelete = "delete from attr_class_group where cms_group_id = " + grantMenuForm.getGroupid();
        sqls.add(tempDelete);
        
        String[] listMenu = grantMenuForm.getListmenu().split(",");
        for (int i = 0; i < listMenu.length; i++) {
            if(listMenu[i].length() > 0){
                String[] item = listMenu[i].split("-");
                String temp = " insert into attr_class_group(cms_group_id,attr_class_id,permission) values ( " + grantMenuForm.getGroupid() + ","
                        + item[1] + ","
                        + "'" + item[2] + "' ) ";
                sqls.add(temp);
            }
        }
    
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();

        if (adminFacade.updateGroupMenu(sqls).equals("1")) {
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));

            // set lai quyen  session
            SetSessionServlet setSession = new SetSessionServlet();
            setSession.initSession(request, response);
        } else {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
   
        return "redirect:/group/classgroup/" + grantMenuForm.getGroupid();
    }

    private boolean checkGroupClass(List<AttClassGroupBO> classGroup, Long classId, String groupId, String action) {
        for (AttClassGroupBO item : classGroup) {
            if (String.valueOf(item.getGroupId()).equals(groupId) && item.getAttrClassId().equals(classId) && item.getPermission().equals(action)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/clone/{groupId}", method = RequestMethod.GET)
    public String clone(ModelMap mm, @PathVariable(value = "groupId") String groupId,
            Locale locale, RedirectAttributes attr) throws Exception {
        logger.debug("Delete Group Action");
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
//            GroupBO groupBO = adminFacade.findByGroupId(groupId);
            adminFacade.clone(groupId);
        } catch (Exception e) {
//            String message = StringUtils.captureStackTrace(e);
//            if (StringUtils.hasText(message)) {
//                if (message.indexOf("FK_GROUP_ID") != -1) {
//                    String msg = messageSource.getMessage("admin.excute.sql.violated.FK_GROUP_ID", null, locale);
//                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//                    return "redirect:/group/init";
//                }
//            }
//            String msg = messageSource.getMessage("admin.common.error", null, locale);
//            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
//            logger.error("Exception :", e);
            return "redirect:/group/init";
        }
        String msg = messageSource.getMessage("admin.common.clone.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
        return "redirect:/group/init";
    }

}
