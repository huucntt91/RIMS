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
import com.vnpt.media.rims.facade.LoaiPtCshtFacade;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/loaiphutrocsht")
public class DMLoaiPtCshtController {

    private static final Logger logger = LogManager.getLogger(DMLoaiPtCshtController.class);
    private static final String LIST = "categories/loaiphutrocsht/list";
    private static final String FORM = "categories/loaiphutrocsht/form";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request) {
        ArrayList<DMLoaiPtCshtBO> list = LoaiPtCshtFacade.fc_find_all("");
        mm.put("list", list);
        return LIST;
    }

    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm) throws Exception {
        DMLoaiPtCshtBO model = new DMLoaiPtCshtBO();
        mm.addAttribute("model", model);
        return FORM;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") String id,
            Locale locale, RedirectAttributes attr, ModelMap mm) throws Exception {
        try {
            if (StringUtils.hasText(id)) {
                ArrayList<DMLoaiPtCshtBO> list = LoaiPtCshtFacade.fc_find_all(id);
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
    public String add(ModelMap mm, @Valid @ModelAttribute(value = "model") DMLoaiPtCshtBO model,
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
                return "redirect:/loaiphutrocsht/preAdd";
            }
            if (LoaiPtCshtFacade.fn_modify("add", model.loai_pt_csht_id, model.ten_loai_pt_csht) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/loaiphutrocsht/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/loaiphutrocsht/preAdd";
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/loaiphutrocsht/preAdd";
        }
    }

    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "model") DMLoaiPtCshtBO model,
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
                return "redirect:/loaiphutrocsht/view/" + model.loai_pt_csht_id;
            }
            if (LoaiPtCshtFacade.fn_modify("edit", model.loai_pt_csht_id, model.ten_loai_pt_csht) > 0) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
                return "redirect:/loaiphutrocsht/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/loaiphutrocsht/view/" + model.loai_pt_csht_id;
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            mm.addAttribute("model", model);
            return "redirect:/loaiphutrocsht/view/" + model.loai_pt_csht_id;
        }
    }

    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(ModelMap mm, @PathVariable(value = "Id") String Id,
            Locale locale, RedirectAttributes attr) throws Exception {
        try {
            if (LoaiPtCshtFacade.fn_modify("del", Id, "") > -1) {
                String msg = messageSource.getMessage("admin.common.delete.success", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, msg));
                return "redirect:/loaiphutrocsht/init";
            } else {
                String msg = messageSource.getMessage("admin.common.error", null, locale);
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
                return "redirect:/loaiphutrocsht/init";
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            String msg = messageSource.getMessage("admin.common.error", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            logger.error("Exception :", e);
            return "redirect:/loaiphutrocsht/init";
        }
    }

}
