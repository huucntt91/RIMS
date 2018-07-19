package com.vnpt.media.rims.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DecoratorController {

    private static final Logger logger = LogManager.getLogger(DecoratorController.class);

    @RequestMapping(value = "/decorators/{decorator}", method = RequestMethod.GET)
    public String pages(HttpServletRequest request,Locale locale, Model model, @PathVariable(value = "decorator") String decorator) {
        //logger.info("Welcome page! The client locale is0." + locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("user", getPrincipal(request));
        return "decorators/" + decorator;
    }

    private String getPrincipal(HttpServletRequest request) {
        return request.getRemoteUser();
    }
}
