/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.managerAdmin;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.StringUtils;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VNP
 */
public class BaseController {

    public static Logger logger = LogManager.getLogger(BaseController.class);

    protected String getBaseURL(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
        return baseURL;
    }

    protected void genMessageSystemError(RedirectAttributes attr, MessageSource messageSource, Locale locale) {
        String duplicateUsername = messageSource.getMessage("admin.validate.duplicate.username", null, locale);
        attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_SYSTEM_DEFAULT_ERROR));
    }

    protected String buildUrl(String urlWeb, String prefix, String param) {

        String tempParamWeb = "";

        if (StringUtils.hasText(param)) {
            tempParamWeb = tempParamWeb + prefix + "=" + param;
        } else {
            tempParamWeb = tempParamWeb + prefix + "=";
        }

        urlWeb = urlWeb + tempParamWeb;

        return urlWeb;
    }

    protected void errorMessage(String message, RedirectAttributes attr, MessageSource messageSource, Locale locale) {
        if (message.indexOf("NODE_UK1") != -1) { // 
            String msg = messageSource.getMessage("node.manode.NODE_UK1", null, locale);
            attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, msg));
            return;
        }
        attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_SYSTEM_DEFAULT_ERROR));
    }
}
