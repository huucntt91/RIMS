package com.vnpt.media.rims.controller.managerAdmin;

import java.util.ArrayList;
import java.util.HashMap;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.PagingUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import java.util.Iterator;
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
@RequestMapping(value = "/hdsd")
public class HdsdController extends BaseController {

    private static Logger logger = LogManager.getLogger(HdsdController.class);
  
    @Autowired
    private MessageSource messageSource;

   

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, HttpServletRequest request,
            @RequestParam(value = "media", required = false) String media) {
        logger.info("media=" + media);
        mm.put("media", media);
        return "managerAdmin/hdsd/hdsd";
    }
}
