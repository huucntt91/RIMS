package com.vnpt.media.rims.controller.autoEmail;

import com.vnpt.media.rims.bean.AutoEmailBO;
import com.vnpt.media.rims.controller.managerAdmin.*;
import com.vnpt.media.rims.common.Constants;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.AutoEmailFacade;
import com.vnpt.media.rims.facade.CategoriesFacade;
import org.springframework.validation.FieldError;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/sqlReport")
public class sqlReportController extends BaseController {
    
//    private static Logger logger = Logger.getLogger(sqlReportController.class);
    private static int maxDisplayPages = 8;
    private static String pagerStyle = "pagelinks";
    private static final String[] strPrefix = {"&#272;&#7847;u", "Tr&#432;&#7899;c", "Sau", "Cu&#7889;i", "Trang", "T&#7899;i trang "};
    private static final String separator = ",";
    private static final String LIST = "autoEmail/sqlList";
    private static final String ADD_SQL = "autoEmail/addSql";

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
                        @RequestParam(value = "sqlName", required = false) String sqlName,
                        Locale locale, ModelMap mm, HttpServletRequest request) {

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId = String.join(",", tinhManager);

        page = page == null ? "1" : page;
        Integer pageInt = Integer.parseInt(page);

        AutoEmailBO sqlRp = new AutoEmailBO();
        AutoEmailFacade facade = new AutoEmailFacade();

        int totalRows = facade.countSqlReport(sqlName);

        Page objPage = new Page();

        int numPerPage = Constants.NUMBER_FOR_PAGING;
        int totalPages = 0;

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
            return ("redirect:/sqlReport/init?page=" + pageInt);
        } else if (pageInt > totalPages && totalPages > 0) {
            pageInt = totalPages;
            return ("redirect:/sqlReport/init?page=" + pageInt);
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

        List<AutoEmailBO> lst = facade.fc_find_sql(startRow, endRow, null, sqlName);
        mm.put("startRow", startRow);
        mm.put("list_sqlRp", lst);
        mm.put("tramDABO", sqlRp);
        return LIST;
    }
    
    @RequestMapping(value = "/preAddSql", method = RequestMethod.GET)
    public String preAddSql(ModelMap mm) throws Exception {
        AutoEmailBO autoEmailBO = new AutoEmailBO();
        mm.addAttribute("sqlRp", autoEmailBO);
        return ADD_SQL;
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        try {
            if (StringUtils.hasText(id)) {
                List<AutoEmailBO> list = AutoEmailFacade.fc_find_sql(1, 10,id, null);
                mm.addAttribute("sqlRp", list.get(0));
            }
        } catch (Exception e) {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return ADD_SQL;
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
                return "redirect:/sqlReport/preAddSql";
            }
            if (AutoEmailFacade.fn_addSql(model.sqlName, model.sqlValue, model.description) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/sqlReport/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/sqlReport/preAddSql";
            }
        } catch (Exception e) {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/sqlReport/preAddSql";
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
                return "redirect:/sqlReport/view/" + model.idSql;
            }
            if (AutoEmailFacade.fn_updateSql(model.idSql, model.sqlName, model.sqlValue, model.description) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/sqlReport/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/sqlReport/view/" + model.idSql;
            }
        } catch (Exception e) {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/sqlReport/view/" + model.idSql;
        }
    }
    
    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "Id") String Id,
            Locale locale, RedirectAttributes attr) throws Exception {
        try {
            if (AutoEmailFacade.fn_deleteSql(Id) > -1) {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/sqlReport/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/sqlReport/init";
            }
        } catch (Exception e) {
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return "redirect:/sqlReport/init";
        }
    }


}
