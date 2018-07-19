package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.TNodeBO;
import com.vnpt.media.rims.facade.TnodeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/tnode")
public class TnodeController {
    
    private static Logger logger = LogManager.getLogger(TnodeController.class);
    private static final String POPUP = "broadband/tnode/popup";
        private static final String POPUP2 = "broadband/tnode/popup2";

  
    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String popup(@RequestParam(value = "id", required = false) String id,            
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            ModelMap mm, HttpServletRequest request) {
        TnodeFacade facade=new TnodeFacade();
        List<TNodeBO> list=facade.findAll(id,code,name);
        
        mm.put("list", list);        
        
        return POPUP;
    }   
   
}
