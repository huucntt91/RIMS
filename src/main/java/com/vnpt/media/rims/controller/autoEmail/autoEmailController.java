package com.vnpt.media.rims.controller.autoEmail;

import com.vnpt.media.rims.bean.AutoEmailBO;
import com.vnpt.media.rims.controller.managerAdmin.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.controller.security.SetSessionServlet;
import com.vnpt.media.rims.facade.AutoEmailFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/autoEmail")
public class autoEmailController extends BaseController {

    private static final int maxDisplayPages = 8;
    private static String pagerStyle = "pagelinks";
    private static final String[] strPrefix = {"&#272;&#7847;u", "Tr&#432;&#7899;c", "Sau", "Cu&#7889;i", "Trang", "T&#7899;i trang "};
    private static final String separator = ",";
    private static final String LIST = "autoEmail/autoEmailList";
    private static final String ADD_REPORT = "autoEmail/addReport";
    private static final String ADD_SQL_REPORT = "autoEmail/sqlToReport";

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "reportMailName", required = false) String reportMailName,
            Locale locale, ModelMap mm, HttpServletRequest request) {

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);

        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        AutoEmailBO lstE = new AutoEmailBO();

        int totalRows = AutoEmailFacade.countEmailReport(reportMailName);

        Page objPage = new Page();

        int numPerPage = Constants.NUMBER_FOR_PAGING;
        int totalPages;

        if (totalRows % numPerPage == 0) {
            totalPages = (int) totalRows / numPerPage;
        } else {
            totalPages = (int) totalRows / numPerPage + 1;
        }
        if (totalRows == 0) {
            totalPages = 0;
        }

        if (pageInt < 1) {
            pageInt = 1;
            return ("redirect:/autoEmail/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/autoEmail/init?page=" + pageInt);
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageInt);
        objPage.setDirection(1);
        objPage.setSubject("Quản lý gửi mail tự động");
        mm.addAttribute("pageInfo", objPage);

        int startRow = 0, endRow = 0;
        if (pageInt > 1) {
            startRow = ((pageInt - 1) * (numPerPage) + 1);
            endRow = (pageInt * (numPerPage));
        } else if (pageInt == 1) {
            startRow = 1;
            endRow = Constants.NUMBER_FOR_PAGING;
        }

        List<AutoEmailBO> lst = AutoEmailFacade.fc_find_email_report(startRow, endRow, null, reportMailName);
        mm.put("startRow", startRow);
        mm.put("list_emailReport", lst);
        mm.put("tramDABO", lstE);
        return LIST;
    }
    
    @RequestMapping(value = "/preAddReport", method = RequestMethod.GET)
    public String preAddReport(ModelMap mm) throws Exception {
        AutoEmailBO autoEmailBO = new AutoEmailBO();
        mm.addAttribute("lstE", autoEmailBO);
        return ADD_REPORT;
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        try {
            if (StringUtils.hasText(id)) {
                List<AutoEmailBO> list = AutoEmailFacade.fc_find_email_report(1, 10,id, null);
                mm.addAttribute("lstE", list.get(0));
            }
        } catch (Exception e) {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return ADD_REPORT;
    }

@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "model") AutoEmailBO model,
            BindingResult bindingResult, RedirectAttributes attr) throws Exception {
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
                mm.addAttribute("model", model);
                return "redirect:/autoEmail/preAddReport";
            }
            if (AutoEmailFacade.fn_addReport(model.reportMailName, model.listMail, model.timeSend, model.emailDetail) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/autoEmail/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/autoEmail/preAddReport";
            }
        } catch (Exception e) {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/autoEmail/preAddReport";
        }
    }
    
@RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "model") AutoEmailBO model,
            BindingResult bindingResult, RedirectAttributes attr) throws Exception {
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
                mm.addAttribute("model", model);
                return "redirect:/autoEmail/view/" + model.idReportMail;
            }
            if (AutoEmailFacade.fn_updateReport(model.idReportMail, model.reportMailName, model.listMail, model.timeSend, model.emailDetail) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/autoEmail/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/autoEmail/view/" + model.idReportMail;
            }
        } catch (Exception e) {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/autoEmail/view/" + model.idReportMail;
        }
    }
    
    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "Id") String Id,
            Locale locale, RedirectAttributes attr) throws Exception {
        try {
            if (AutoEmailFacade.fn_deleteReport(Id) > -1) {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/autoEmail/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/autoEmail/init";
            }
        } catch (Exception e) {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/autoEmail/init";
        }
    }
    
    @RequestMapping(value = "/addSqlToReport/{reportId}", method = RequestMethod.GET)
    public String preAddGroupToUser(ModelMap mm, @PathVariable(value = "reportId") String reportId) throws Exception {
//        mm.addAttribute("Groupbean", new Groupbean());
//        mm.addAttribute("IdGroupbean", new IdGroupbean());
        AutoEmailFacade emailFacade = new AutoEmailFacade();
        mm.put("list_sql_to_report", emailFacade.fc_find_sql_to_report(reportId));
        mm.put("list_sql_no_report", emailFacade.fc_find_sql_no_report(reportId));
//        GroupUserForm groupUserForm = new GroupUserForm();
        mm.addAttribute("userId", reportId);
//        mm.addAttribute("groupUserForm", groupUserForm);
        return ADD_SQL_REPORT;
    }
    
    @RequestMapping(value = "/updateSqlToReport", method = RequestMethod.GET,
            produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String updateUserGroup(HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        String reportId = request.getParameter("userId");
        String contentList = request.getParameter("contentList");
        
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        List<String> sqls = new ArrayList<>();
        if (StringUtils.hasText(reportId)) {
            sqls.add("delete from email_link where id_report = " + reportId);
            for (String string : contentList.split("-")) {
                if (!string.equals("")) {
                    
                    String temp = "insert into email_link(id, id_report, id_sql) values (email_link_seq.nextval, "
                            + reportId + "," + string + ")";
                    
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
