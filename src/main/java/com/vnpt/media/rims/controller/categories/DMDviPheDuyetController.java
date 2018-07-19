package com.vnpt.media.rims.controller.categories;

import java.util.Locale;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.DviPheDuyetFacade;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/dvipheduyet")
public class DMDviPheDuyetController {

    private static final Logger logger = LogManager.getLogger(DMDviPheDuyetController.class);
    private static final String LIST = "categories/dvipheduyet/list";
    private static final String FORM = "categories/dvipheduyet/form";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request) {
        ArrayList<DMDviPheDuyetBO> list = DviPheDuyetFacade.fc_find_all("");
        mm.put("list", list);
        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        DMDviPheDuyetBO model = new DMDviPheDuyetBO();
        mm.addAttribute("model", model);
        return FORM;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        try {
            if (StringUtils.hasText(id)) {
                ArrayList<DMDviPheDuyetBO> list = DviPheDuyetFacade.fc_find_all(id);
                mm.addAttribute("model", list.get(0));
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
        }
        return FORM;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "model") DMDviPheDuyetBO model,
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
                return "redirect:/dvipheduyet/preAdd";
            }
            if (DviPheDuyetFacade.fn_modify("add", model.don_vi_phe_duyet_id, model.ten_don_vi_phe_duyet) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/dvipheduyet/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/dvipheduyet/preAdd";
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/dvipheduyet/preAdd";
        }
    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "model") DMDviPheDuyetBO model,
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
                return "redirect:/dvipheduyet/view/" + model.don_vi_phe_duyet_id;
            }
            if (DviPheDuyetFacade.fn_modify("edit", model.don_vi_phe_duyet_id, model.ten_don_vi_phe_duyet) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/dvipheduyet/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/dvipheduyet/view/" + model.don_vi_phe_duyet_id;
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/dvipheduyet/view/" + model.don_vi_phe_duyet_id;
        }
    }

    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "Id") String Id,
            Locale locale, RedirectAttributes attr) throws Exception {
        try {
            if (DviPheDuyetFacade.fn_modify("del", Id, "") > -1) {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/dvipheduyet/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/dvipheduyet/init";
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            logger.error("Exception :", e);
            return "redirect:/dvipheduyet/init";
        }
    }

}
