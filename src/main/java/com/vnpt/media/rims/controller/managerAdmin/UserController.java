package com.vnpt.media.rims.controller.managerAdmin;

import java.util.ArrayList;
import java.util.HashMap;

import com.vnpt.media.rims.bean.TinhTpGiapRanhBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.PagingUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.controller.security.SetSessionServlet;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.formbean.GroupUserForm;
import com.vnpt.media.rims.formbean.Groupbean;
import com.vnpt.media.rims.formbean.IdGroupbean;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private static Logger logger = LogManager.getLogger(UserController.class);
    private static int maxDisplayPages = 8;
    private static String pagerStyle = "pagelinks";
    private static final String[] strPrefix = {"&#272;&#7847;u", "Tr&#432;&#7899;c", "Sau", "Cu&#7889;i", "Trang", "T&#7899;i trang "};
    private static final String separator = ",";
    private static final String USER_LIST = "managerAdmin/user/userList";
    @Autowired
    private MessageSource messageSource;

   

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, HttpServletRequest request) {
        logger.info("Action init user");

        ManagerAdminFacade facade = new ManagerAdminFacade();
        int startRow = 0, endRow = 0, totalRecordPerPageList = Constants.NUMBER_FOR_PAGING, currentPage = 1;
        int totalRecords = facade.getTotalUser(null);
        // tao paging
        try {
            String urlWeb = getBaseURL(request) + "user/paging?fullname=";
            String pagingUrl = PagingUtils.printPaging(urlWeb, totalRecords, Constants.NUMBER_FOR_PAGING, currentPage);
            mm.put("paging", pagingUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (currentPage > 1) {
            startRow = ((currentPage - 1) * (totalRecordPerPageList) + 1);
            endRow = (currentPage * (totalRecordPerPageList));
        } else if (currentPage == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<UserBO> lst = facade.findAllUsers(null, startRow, endRow);
        String tinhTpId = "";
        mm.put("list_user", lst);
        mm.put("tinhTpId", tinhTpId);
        return USER_LIST;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("fullname") String fullname, ModelMap mm, HttpServletRequest request) {
        
        fullname = ((fullname == null || fullname.equals("")) ? "" : fullname);
        ManagerAdminFacade facade = new ManagerAdminFacade();
        logger.info("Action search user");
        int startRow = 0, endRow = 0, totalRecordPerPageList = Constants.NUMBER_FOR_PAGING, currentPage = 1;
        int totalRecords = facade.getTotalUser(fullname);
        if (currentPage > 1) {
            startRow = ((currentPage - 1) * (totalRecordPerPageList) + 1);
            endRow = (currentPage * (totalRecordPerPageList));
        } else if (currentPage == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }
        List<UserBO> lst = facade.findAllUsers(fullname, startRow, endRow);

        mm.put("list_user", lst);
        mm.put("name", fullname);
        String paging;
        try {
            String urlWeb = getBaseURL(request) + "user/paging?fullname=" + fullname;
            paging = PagingUtils.printPaging(urlWeb, totalRecords, Constants.NUMBER_FOR_PAGING, currentPage);
            mm.put("paging", paging);
        } catch (Exception ex) {
            ex.printStackTrace();
//            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return USER_LIST;
    }

    @RequestMapping(value = "/paging", method = RequestMethod.GET,
            produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String paging(@RequestParam("currentPage") int currentPage,
            @RequestParam(value = "fullname") String fullname, ModelMap mm,
            HttpServletRequest request) {
        
        StringBuilder htmlTable = new StringBuilder();
        try {
            htmlTable.append("<table id=\"example2\" class=\"table table-bordered table-hover\" width=\"100%\">");
            htmlTable.append("<thead><tr>");
//             <th>STT</th>
//                                            <th>Ngày tạo</th>
//                                            <th>Tài khoản</th>
//                                            <th>Họ và tên</th>
//                                            <th>Email</th>                                            
//                                            <th>Số điện thoại</th>
//                                            <th>Đơn vị</th>
//                                            <th>Trạng thái</th>
//                                            <th>Xử lý</th>
            htmlTable.append("<th>").append("STT").append("</th>");
            htmlTable.append("<th>").append("Ngày tạo").append("</th>");
            htmlTable.append("<th>").append("Tài khoản").append("</th>");
            htmlTable.append("<th>").append("Họ và tên").append("</th>");
            htmlTable.append("<th>").append("Email").append("</th>");
            htmlTable.append("<th>").append("Số điện thoại").append("</th>");
            htmlTable.append("<th>").append("Đơn vị").append("</th>");
            htmlTable.append("<th>").append("Trạng thái").append("</th>");
            htmlTable.append("<th>").append("Xử lý").append("</th>");
            htmlTable.append("</tr></thead>");
            int startRow = 0, endRow = 0, totalRecordPerPageList = Constants.NUMBER_FOR_PAGING;
            ManagerAdminFacade facade = new ManagerAdminFacade();
            int totalRecords = facade.getTotalUser((fullname == null || fullname.equalsIgnoreCase("null")) ? "" : fullname);
            String urlWeb = getBaseURL(request) + "/user/paging?fullname=";
            urlWeb += fullname == null ? "" : fullname;

            String pagingUrl = PagingUtils.printPaging(urlWeb, totalRecords, Constants.NUMBER_FOR_PAGING, currentPage);
            mm.put("paging", pagingUrl);
            if (currentPage > 1) {
                startRow = ((currentPage - 1) * (totalRecordPerPageList) + 1);
                endRow = (currentPage * (totalRecordPerPageList));
            } else if (currentPage == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            if (totalRecords > 0) {

                htmlTable.append("<div align=\"right\">").append(pagingUrl).append("</div>");

                List<UserBO> transactionsReport = facade.findAllUsers((fullname == null || fullname.equalsIgnoreCase("null")) ? "" : fullname, startRow, endRow);
                
                if (!StringUtils.isEmpty(transactionsReport)) {
                    Iterator iterator = transactionsReport.iterator();
                    int count = (currentPage - 1) * Constants.NUMBER_FOR_PAGING + 1;
                    String action = null;
                    htmlTable.append("<tbody>");
                    int stt = 0;
                    while (iterator.hasNext()) {
                        stt += 1;
                        UserBO userBO = (UserBO) iterator.next();
                        if (count % 2 == 0) {
                            htmlTable.append("<tr class='event'><td>").append(count).append("</td>");
                        } else {
                            htmlTable.append("<tr class='odd'><td>").append(count).append("</td>");
                        }
//                        htmlTable.append("<td align=\"center\">").append(stt).append("</td>");
                        htmlTable.append("<td align=\"center\">").append(userBO.getCreateDate()).append("</td>");
                        htmlTable.append("<td align=\"center\">").append(userBO.getUsername()).append("</td>");
                        htmlTable.append("<td align=\"center\">").append(userBO.getFullname() == null ? "" : userBO.getFullname()).append("</td>");
                        htmlTable.append("<td align=\"center\">").append(userBO.getEmail() == null ? "" : userBO.getEmail()).append("</td>");
                        htmlTable.append("<td align=\"center\">").append(userBO.getMsisdn() == null ? "" : userBO.getMsisdn()).append("</td>");
                        htmlTable.append("<td align=\"center\">").append(userBO.getTenDonVi()).append("</td>");
                        htmlTable.append("<td align=\"center\">").append(userBO.getStatus() == 1 ? "Active" : "Inactive").append("</td>");
                        action = "";


                        action = action + "<a href=\"" + request.getContextPath() + "/user/active/" + userBO.getId() + "\""
                                + "onclick=\"return confirm('Bạn có muốn thực hiện active/inactive không ?')\""
                                + "title=\"Active/Inactive\"\n"
                                + " ><img src=\"" + request.getContextPath() + "/image/icon/icon_check.png\" /></a> ";

                        action = action + "<a href=\"" + request.getContextPath() + "/user/view/" + userBO.getId() + "\""
                                + "title=\"Sửa\"\n"
                                + " ><img src=\"" + request.getContextPath() + "/image/icon/edit_icon.png\" /></a> ";

                        action = action + "<a href=\"" + request.getContextPath() + "/user/delete/" + userBO.getId() + "\""
                                + "onclick=\"return confirm('Bạn có muốn thực hiện xoá không ?')\""
                                + "title=\"Xoá\"\n"
                                + " ><img src=\"" + request.getContextPath() + "/image/icon/delete.png\" /></a> ";
 
                        action = action + "<a href=\"" + request.getContextPath() + "/user/addGroupToUser/" + userBO.getId() + "\""
                                + "title=\"Phân quyền tài khoản\"\n"
                                + " ><img src=\"" + request.getContextPath() + "/image/icon/document-add-icon.png\" /></a> ";
                        htmlTable.append("<td align=\"center\">").append(action).append("</td>");

                        htmlTable.append("</tr>");

                        count = count + 1;
                    }
                    htmlTable.append("</tbody>");
                    htmlTable.append("</table>");
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(UserController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        return htmlTable.toString();
    }

    @ModelAttribute("cpList")
    public List findAllCp(HttpServletRequest request) {
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhs =  String.join(",", tinhManager);
        Map referenceData = new HashMap();
        ManagerAdminFacade facade = new ManagerAdminFacade();
        return facade.findAllCp("", "",tinhs);
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        UserBO userBO = new UserBO();
        mm.addAttribute("user", userBO);
        return "managerAdmin/user/editUser";
    }

    @RequestMapping(value = "/view/{userid}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "userid") String userId, ModelMap mm, RedirectAttributes attr, Locale locale) throws Exception {
        logger.debug("view User Action");
        try {
            if (StringUtils.hasText(userId)) {
                ManagerAdminFacade facade = new ManagerAdminFacade();
                UserBO userBO = facade.findByUserId(userId);
                mm.put("user", userBO);
                mm.addAttribute("ubo", userBO);

                List<TinhTpGiapRanhBO> tinhGiapRanhs = facade.findTinhTpGiapRanh(userBO.getId(), Constants.DOI_TUONG_USER);
                if(tinhGiapRanhs == null)
                    tinhGiapRanhs = new ArrayList<>();
                mm.put("tinhTpId", String.join(",", tinhGiapRanhs.stream().map(x->x.getMaTinhTp()).collect(Collectors.toList())));
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            genMessageSystemError(attr, messageSource, locale);
        }

        return "managerAdmin/user/editUser";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "ubo") UserBO ubo,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            
            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        if (!fieldError.getField().equals("newpassword")) {
                            
                            attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
                            mm.addAttribute("ubo", ubo);
                            return "redirect:/user/preAdd";
                        }
                    } else {
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                        return "redirect:/user/preAdd";
                    }
                }
            }
            if (ubo.getUsername().contains(" ")) {
                
                String noWhitespaceAllowed = messageSource.getMessage("admin.validate.noWhitespaceAllowed.username", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
                return "redirect:/user/preAdd";
            }
//            if (ubo.getPassword().contains(" ")) {
//                
//                String noWhitespaceAllowed = messageSource.getMessage("admin.validate.noWhitespaceAllowed.password", null, locale);
//                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, noWhitespaceAllowed));
//                return "redirect:/user/preAdd";
//            }

            
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            UserBO temp = adminFacade.findByUserName(ubo.getUsername().toLowerCase().replace("@vnpt.vn", ""));
            if (temp != null && temp.getUsername() != null) {
                String duplicateUsername = messageSource.getMessage("admin.validate.duplicate.username", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, duplicateUsername));
                UserBO userBO = new UserBO();
                mm.addAttribute("ubo", userBO);
                return "redirect:/user/preAdd";
            }
            adminFacade.addUser(ubo);

            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {

            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("UK_CMS_USER_USERNAME") != -1) {
                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_CMS_USER_USERNAME", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/user/preAdd";
                }
            }
            logger.error("Exception :", e);
            genMessageSystemError(attr, messageSource, locale);
            return "redirect:/user/preAdd";
        }
        UserBO userBO = new UserBO();
        mm.addAttribute("ubo", userBO);
        String msg = messageSource.getMessage("admin.common.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
        return "redirect:/user/init";

    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "ubo") UserBO ubo,  @RequestParam(value = "tinhTpId", required = false) String tinhTpId, BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request,HttpServletResponse response) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        mm.put("ubo", ubo);
        logger.info("update User Action");
        try {
            ubo.setNewpassword(ubo.getPassword());
            if (bindingResult.hasErrors()) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        if (!fieldError.getField().equals("newpassword") && !fieldError.getField().equals("password")) {
                            attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
                            mm.addAttribute("ubo", ubo);
                            return "redirect:/user/view/" + ubo.getId();
                        }
                    } else {
                        attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, Message.MESSAGE_INPUT_DEFAULT));
                        mm.addAttribute("ubo", ubo);
                        return "redirect:/user/view/" + ubo.getId();
                    }
                }

            }
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            
            ubo.setEmail(ubo.getUsername() + "@vnpt.vn");
            adminFacade.updateUser(ubo);

            //huannv
            //cập nhật quyền xem tỉnh giáp ranh
            List<String> sqlsDelete = new ArrayList<String>();
            List<String> sqlsInsert = new ArrayList<String>();
            List<String> sqls = new ArrayList<String>();
            //sqls.add(tempDelete);

            if(tinhTpId == null) tinhTpId = "";
            String[] listTinhtp = tinhTpId.split(",");
            String tempDelete = "delete from tinh_tp_giap_ranh ua where ma_doi_tuong = " + ubo.getId() + " and loai_doi_tuong='user'";
            sqlsDelete.add(tempDelete);

            for (int i = 0; i < listTinhtp.length; i++) {
                String temp = " insert into tinh_tp_giap_ranh(id, ma_doi_tuong, loai_doi_tuong, ma_tinh_tp, ma_quyen) values (SEQ_TINH_TP_GIAP_RANH_ID.nextval, " + ubo.getId() + ","
                        + "'"+ Constants.DOI_TUONG_USER +"'" + ","
                        + "'" + listTinhtp[i] + "', 'VIEW') ";
                sqlsInsert.add(temp);
            }
            sqls.addAll(sqlsDelete);
            sqls.addAll(sqlsInsert);

            if (adminFacade.updateTinhTpGiapRanh(sqls.stream().distinct().collect(Collectors.toList())).equals("1")) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));

                // set lai quyen  session
                SetSessionServlet setSession = new SetSessionServlet();
                setSession.initSession(request, response);
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/user/view/" + ubo.getId();
            }

        } catch (Exception e) {

            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("UK_CMS_USER_USERNAME") != -1) {
                    String msg = messageSource.getMessage("admin.excute.sql.violated.UK_CMS_USER_USERNAME", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/user/view/" + ubo.getId();
                }
            }
            logger.error("Exception :", e);
            genMessageSystemError(attr, messageSource, locale);
            return "redirect:/user/view/" + ubo.getId();
        }
        String msg = messageSource.getMessage("admin.common.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));

        return "redirect:/user/init";
    }

    @RequestMapping(value = "/resetpassword/{userId}", method = RequestMethod.GET,
            produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String resetPassword(ModelMap mm, @PathVariable(value = "userId") String userId, RedirectAttributes attr, Locale locale) throws Exception {
        logger.debug("Reset password user action");
        StringBuilder xmlAjax = new StringBuilder();
        xmlAjax.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        long t1 = System.currentTimeMillis();
        try {
            UserBO userBO = new ManagerAdminFacade().findByUserId(userId.trim());
            new ManagerAdminFacade().resetPassword(userBO);
            xmlAjax.append("<responseCode>00</responseCode>");
        } catch (Exception e) {
            logger.error("Exception : ", e);
            xmlAjax.append("<responseCode>01</responseCode>");

            return xmlAjax.toString();
        }
        return xmlAjax.toString();
    }

//    @RequestMapping(value = "/viewChangepass", method = RequestMethod.GET)
//    public String viewChangePass(ModelMap mm) throws Exception {
//        logger.debug("viewChangePass Action");
//        try {
//            Authentication a = SecurityContextHolder.getContext().getAuthentication();
//            UserBO temp = (UserBO) a.getPrincipal();
//            ManagerAdminFacade facade = new ManagerAdminFacade();
//            UserBO userBO = facade.findByUserName(temp.getUsername());
//            userBO.setPassword("");
//            mm.addAttribute("ubo", userBO);
//        } catch (Exception e) {
//            logger.error("Exception :", e);
//        }
//        return "managerAdmin/user/changePass";
//    }

    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    public String updateChangePass(ModelMap mm, @Valid @ModelAttribute(value = "ubo") UserBO ubo,
            BindingResult bindingResult, Locale locale, RedirectAttributes attr) throws Exception {
        logger.debug("updateChangePass Action");
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        UserBO temp = adminFacade.findByUserId(ubo.getId() + "");
        try {
            if (bindingResult.hasErrors()) {
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        if (fieldError.getField().equals("newpassword") || fieldError.getField().equals("password")) {
                            attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
                            return "redirect:/user/viewChangepass";
                        }
                    }
                }
            }

            if (!StringUtils.encodePassword(ubo.getPassword(), "MD5").equals(temp.getPassword())) {
                String message = messageSource.getMessage("admin.validate.oldpassword", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, message));
                return "redirect:/user/viewChangepass";
            }
            temp.setPassword(ubo.getNewpassword());
            adminFacade.updateUser(temp);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error("Exception :", e);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_SYSTEM_DEFAULT_ERROR));
        }
        return "redirect:/user/viewChangepass";
    }

    @RequestMapping(value = "/viewUserInfo", method = RequestMethod.GET)
    public String viewUserInfo(ModelMap mm, HttpServletRequest request) throws Exception {
        logger.debug("viewUserInfo Action");
        try {
            UserBO temp = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManagerAdminFacade facade = new ManagerAdminFacade();
            UserBO userBO = facade.findByUserName(temp.getUsername());
            mm.addAttribute("ubo", userBO);
        } catch (Exception e) {
            logger.error("Exception :", e);

        }

        return "managerAdmin/user/userInfo";
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(ModelMap mm, @Valid @ModelAttribute(value = "ubo") UserBO ubo,
            BindingResult bindingResult, Locale locale, RedirectAttributes attr) throws Exception {
        
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        UserBO temp = adminFacade.findByUserId(ubo.getId() + "");
        temp.setFullname(ubo.getFullname());
        temp.setMsisdn(ubo.getMsisdn());
        temp.setEmail(ubo.getEmail());
        temp.setPassword("");
        mm.put("ubo", temp);
        logger.info("update User Action");
        try {
            if (bindingResult.hasErrors()) {
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        if (fieldError.getField().equals("fullname") || fieldError.getField().equals("email")
                                || fieldError.getField().equals("msisdn")) {
                            attr.addFlashAttribute("info", new Message(Message.TYPE_WARNING, Message.HEAD_WARNING, fieldError.getDefaultMessage()));
                            return "redirect:/user/viewUserInfo";
                        }
                    }
                }
            }
            adminFacade.updateUser(temp);
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error("Exception :", e);
            genMessageSystemError(attr, messageSource, locale);

        }
        return "redirect:/user/viewUserInfo";
    }

    @RequestMapping(value = "/active/{userId}", method = RequestMethod.GET)
    public String active(ModelMap mm, @PathVariable(value = "userId") String userId,
            Locale locale, RedirectAttributes attr) throws Exception {
        logger.debug("Active User Action");
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            UserBO userBO = adminFacade.findByUserId(userId);
            if (userBO.getStatus() == 1) {
                userBO.setStatus(0);
            } else if (userBO.getStatus() == 0) {
                userBO.setStatus(1);
            }
            adminFacade.activeUser(userBO);
        } catch (Exception e) {
            logger.error("Exception :", e);
            genMessageSystemError(attr, messageSource, locale);
            return "redirect:/user/init";
        }
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        return "redirect:/user/init";
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "userId") String userId,
            Locale locale, RedirectAttributes attr) throws Exception {
        
        logger.debug("Delete User Action");
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            UserBO userBO = adminFacade.findByUserId(userId);
            adminFacade.deleteUser(userId, userBO);
        } catch (Exception e) {
            String message = StringUtils.captureStackTrace(e);
            if (StringUtils.hasText(message)) {
                if (message.indexOf("FK_USER_ID") != -1) {
                    String msg = messageSource.getMessage("admin.excute.sql.violated.FK_USER_ID", null, locale);
                    attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                    return "redirect:/user/init";
                }
            }
            logger.error("Exception :", e);
            genMessageSystemError(attr, messageSource, locale);
            return "redirect:/user/init";
        }
        String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
        return "redirect:/user/init";
    }

    @RequestMapping(value = "/addGroupToUser/{userId}", method = RequestMethod.GET)
    public String preAddGroupToUser(ModelMap mm, @PathVariable(value = "userId") String userId) throws Exception {
        mm.addAttribute("Groupbean", new Groupbean());
        mm.addAttribute("IdGroupbean", new IdGroupbean());
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        mm.put("list_group_by_user", adminFacade.getGroupByUserId(userId));
        mm.put("list_group_not_user", adminFacade.getGroupByNotUserId(userId));
        GroupUserForm groupUserForm = new GroupUserForm();
        mm.addAttribute("userId", userId);
        UserBO user = adminFacade.findByUserId(userId);
        mm.addAttribute("userName", user.getUsername());
        mm.addAttribute("groupUserForm", groupUserForm);
        return "managerAdmin/user/userGroup";
    }

//    @RequestMapping(value = "/addGroupToUser/searchGroup", method = RequestMethod.POST)
//    public String searchGroup(@ModelAttribute("Groupbean") Groupbean user
//    ) throws Exception {
//        return "redirect:/user/addGroupToUser/3";
//    }
    @RequestMapping(value = "/updateUserGroup", method = RequestMethod.GET,
            produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String updateUserGroup(HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        String userId = request.getParameter("userId");
        String contentList = request.getParameter("contentList");
        
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> sqls = new ArrayList<String>();
        if (StringUtils.hasText(userId)) {
            sqls.add("delete from cms_user_group where user_id = " + userId);
            for (int i = 0; i < contentList.split("-").length; i++) {
                String string = contentList.split("-")[i];
                if (!string.equals("")) {
                    
                    String temp = "insert into cms_user_group(id, user_id,group_id) values (SQ_CMS_USER_GROUP_ID.nextval, "
                            + userId + "," + string + ")";
                    
                    sqls.add(temp);
                }
            }
        }
        StringBuilder xmlAjax = new StringBuilder();
        xmlAjax.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        try {
            String ret = adminFacade.updateGroupMenu(sqls);
            if (ret.equals("1")) {
                // reload lai session
                SetSessionServlet setSession = new SetSessionServlet();
                setSession.initSession(request, response);
                xmlAjax.append("<responseCode>00</responseCode>");
            } else {
                xmlAjax.append("<responseCode>01</responseCode>");
            }
        } catch (Exception e) {
            xmlAjax.append("<responseCode>01</responseCode>");
        }
//        
        return xmlAjax.toString();
    }

}
