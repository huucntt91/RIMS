/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author john
 */
@Controller
public class DefaultController {

    private static final Logger logger = LogManager.getLogger(DefaultController.class);

    @RequestMapping(value = "/blank", method = RequestMethod.GET)
    public String index(ModelMap map) {
        map.put("msg", "Hello Spring 4 Web MVC!");
        return "blank";
    }
    
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String map(ModelMap map) {
        map.put("msg", "Hello Spring 4 Web MVC!");
        return "map";
    }
    
    @RequestMapping(value = "/map2", method = RequestMethod.GET)
    public String map2(ModelMap map) {
        map.put("msg", "Hello Spring 4 Web MVC!");
        return "map2";
    }

    @RequestMapping(value = "/pages/{type}/{page}", method = RequestMethod.GET)
    public String pagesOfTypes(Locale locale, Model model, @PathVariable(value = "type") String type, @PathVariable(value = "page") String page) {
        logger.info("Welcome page! The client locale is {}." + locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "pages/" + type + "/" + page;
    }

    @RequestMapping(value = "/pages/{page}", method = RequestMethod.GET)
    public String pages(Locale locale, Model model, @PathVariable(value = "page") String page) {
        logger.info("Welcome page! The client locale is {}." + locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "pages/" + page;
    }
}
