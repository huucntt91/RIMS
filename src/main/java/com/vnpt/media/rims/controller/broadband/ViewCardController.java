/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.facade.NetworkSpaceFacade;
import com.vnpt.media.rims.facade.TEqFacade;
import com.vnpt.media.rims.facade.ViewCardFacade;
import java.util.ArrayList;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/viewCard")
public class ViewCardController {

    private static Logger logger = LogManager.getLogger(ViewCardController.class);

    private static final String LIST = "broadband/viewCard/list";
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/view/{tNodeId}", method = RequestMethod.GET)
    public String search(ModelMap mm,
            @PathVariable(value = "tNodeId") String tNodeId,
            HttpServletRequest request) {
        logger.info("Action init tPortView");

        ViewCardFacade facade = new ViewCardFacade();
        List<ViewCardBO> lstHangSlot = facade.findHangSlot(tNodeId);
     
        if (lstHangSlot != null && lstHangSlot.size() > 0) {
            if(lstHangSlot.get(0).getTotal_eq3() != null && lstHangSlot.get(0).getTotal_eq3() != "")
            {
                List<ViewCardBO> lstCard = facade.findCardbyDevice(tNodeId);
                mm.put("lstCard", lstCard);
                mm.put("row", lstHangSlot.size());
                mm.put("colum", Integer.parseInt(lstHangSlot.get(0).getTotal_eq3()));
                mm.put("tnodeName", lstCard.get(0).getTnode_name());
            }
        }
        mm.put("tPortViewName", tNodeId);

        return LIST;
    }
}
