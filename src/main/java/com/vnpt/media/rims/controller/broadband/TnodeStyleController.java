package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.TnodeStyleBO;
import com.vnpt.media.rims.bean.TnodeTypeBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.DateTimeUtils;
import com.vnpt.media.rims.facade.TNodeStyleFacade;
import com.vnpt.media.rims.facade.TNodeTypeFacade;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import static org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect.type;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/tnodestyle")
public class TnodeStyleController {
    
    private static Logger logger = LogManager.getLogger(TnodeStyleController.class);
    private static final String FORM = "broadband/tnodestyle/form";
    private static final String LIST = "broadband/tnodestyle/list";
    private static final String REDIRECT = "redirect:/tnodestyle/init";
    
    @ModelAttribute("tnodeTypeList")
    public List findDongTbi() {
        List<TnodeTypeBO> list = null;
        try {
            list = TNodeTypeFacade.getInstance().findAll(null, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }
    
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap mm, HttpServletRequest request) {
        List<TnodeStyleBO> list = new ArrayList<TnodeStyleBO>();
        mm.addAttribute("tnodeStyle", list);
        TNodeStyleFacade facade = new TNodeStyleFacade();
        List<TnodeStyleBO> styleDsLam = facade.findAll(null, null);
        mm.addAttribute("tnodeStype", styleDsLam);
        mm.addAttribute("urlImage", Constants.FOLDER_SAVE_IMG);
        return LIST;
    }
    
    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String preAdd(ModelMap mm)  {
        mm.addAttribute("tnodeStyleBO", new TnodeStyleBO());
        mm.addAttribute("urlImage", Constants.FOLDER_SAVE_IMG);
        return FORM;
    }
    
    @RequestMapping(value = "/preUpdate/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable(value = "id") String id, ModelMap mm)  {
        TNodeStyleFacade facade = new TNodeStyleFacade();
        TnodeStyleBO tlinkBO = facade.findAll(id, null).get(0);
        mm.addAttribute("tnodeStyleBO", tlinkBO);
        mm.addAttribute("urlImage", Constants.FOLDER_SAVE_IMG);
        return FORM;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap mm, @Valid @ModelAttribute(value = "tnodeStyleBO") TnodeStyleBO form,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "note", required = false) String note,
            HttpServletRequest request, BindingResult bindingResult, RedirectAttributes attr
    )  {
        
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        
        form.setId((id == null || id.equals("")) ? null : Long.valueOf(id));
        form.setStatus(status);
        form.setNote(note);
        Locale locale = LocaleContextHolder.getLocale();
        if (form.getFile() != null) {
            String fileName = saveFile(form.getFile(), request);
            form.setUrl(fileName);
        }
        if (id == null || id.trim().equals("")) {
            if (TNodeStyleFacade.getInstance().addTnodeStyle(form, user.getId())) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_ADD_SUCCESS));
            } else {
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_ADD_ERROR));
            }
            
        } else {
            if (TNodeStyleFacade.getInstance().updateTnodeStyle(form, user.getId())) {
                attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
            } else {
                attr.addFlashAttribute("info", new Message(Message.TYPE_DANGER, Message.HEAD_DANGER, Message.MESSAGE_UPDATE_ERROR));
            }
        }
        attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        return REDIRECT;
    }
    
    public String saveFile(MultipartFile file, HttpServletRequest request) {
        try {
            byte[] bytes = file.getBytes();
            
            List<Integer> width = new ArrayList<Integer>();
            List<Integer> height = new ArrayList<Integer>();

//                ConfigInfoCache config_ = ConfigInfoCache.instance();
            File dir = new File(request.getServletContext().getRealPath("/") + Constants.FOLDER_SAVE_IMG);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            
//            dir.getAbsolutePath() + File.separator + file.getName()
//            File serverFile = new File("E:\\Setup\\Apache Tomcat 8.0.3\\webapps\\FOLDER_SAVE_IMG\\" + file.getName() + ".png");
//            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getName() + "." + file.getOriginalFilename().split("\\.")[1]);
            String sysdate = DateTimeUtils.getSysdate("yyyyMMddHHmmss");
            String fileName = sysdate + "_" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
            
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return fileName;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }
}
