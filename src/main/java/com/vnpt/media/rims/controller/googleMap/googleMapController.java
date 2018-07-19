package com.vnpt.media.rims.controller.googleMap;

import com.google.gson.Gson;
import com.vnpt.media.rims.bean.GoogleMapBO;
import com.vnpt.media.rims.controller.managerAdmin.*;
import com.vnpt.media.rims.common.Constants;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.GoogleMapFacade;
import com.vnpt.media.rims.facade.ReportFacade;
import com.vnpt.media.rims.formbean.FilterForm;
import java.io.IOException;
import java.util.AbstractList;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/map")
public class googleMapController extends BaseController {

    private static Logger logger = LogManager.getLogger(googleMapController.class);
    private static int maxDisplayPages = 8;
    private static String pagerStyle = "pagelinks";
    private static final String[] strPrefix = {"&#272;&#7847;u", "Tr&#432;&#7899;c", "Sau", "Cu&#7889;i", "Trang", "T&#7899;i trang "};
    private static final String separator = ",";
    private static final String MAP_LIST = "googleMap/map";
    private static final String MAP_FUSION = "googleMap/mapFusion";
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, HttpServletRequest request) {
        logger.info("Action init map");

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId;
        tinhTpId = (((tinhManager == null) || (tinhManager.length == 0)) ? "0" : String.join(",", tinhManager));
        GoogleMapFacade facade = new GoogleMapFacade();
        mm.put("tinhTpId", tinhTpId);
        mm.put("quanHuyenId", 0L);
        mm.put("phuongXaId", 0L);
        return MAP_LIST;
    }

    @RequestMapping(value = "/init1", method = RequestMethod.GET)
    public String init1(Locale locale, ModelMap mm, HttpServletRequest request) {
        logger.info("Action init map");

        return MAP_FUSION;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String search1(@RequestParam("maNode") String maNode,
//                        @RequestParam(value = "neType", required = false) String[] neType, 
//                        @RequestParam("tinhTpId")  Long tinhTpId, 
//                        @RequestParam("quanHuyenId")  Long quanHuyenId,
//                        @RequestParam("phuongXaId")  Long phuongXaId, 
//                        ModelMap mm,
//                        HttpServletRequest request) {

    public String search1(
            @RequestParam(value = "neType", required = false) String[] neType,
            @RequestParam("tinhTpId") Long tinhTpId,
            ModelMap mm,
            HttpServletRequest request) {
//        maNode = ((maNode == null || maNode.equals("")|| maNode.equals("")) ? "" : maNode);
//        classType = (String[]) ((classType == null ) ? "" : classType);

        GoogleMapFacade facade = new GoogleMapFacade();
        logger.info("Action search node");
        //List<GoogleMapBO> lst = facade.findAll(maNode,neType,tinhTpId,quanHuyenId,phuongXaId);

        //Gson gson = new Gson();
        //String jsonList = gson.toJson(lst);
        //mm.put("list_map", jsonList);
        tinhTpId = (Long) ((tinhTpId == null) ? 0L : tinhTpId);
        Long quanHuyenId = 0L;// (Long)  ((quanHuyenId == null ) ? 0L : quanHuyenId);
        Long phuongXaId = 0L; // (Long)  ((phuongXaId == null ) ? 0L : phuongXaId);

        mm.put("tinhTpId", tinhTpId);
        mm.put("quanHuyenId", quanHuyenId);
        mm.put("phuongXaId", phuongXaId);
        return MAP_LIST;
    }

    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
    }

    @RequestMapping(value = "/countNodes", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getCountNodes(@RequestParam(value = "tinhId") String tinhId, @RequestParam(value = "huyenId") String huyenId,@RequestParam(value = "where", required = false) String where, ModelMap mm,
            HttpServletRequest request) throws IOException {
        GoogleMapFacade facade = new GoogleMapFacade();

        String whereLocation = " ";
        whereLocation += " and building.tinhtp_id = " + tinhId;
        if (huyenId != null && huyenId != "") {
            whereLocation += " and building.quanhuyen_id = " + huyenId;
        }

        int totalBts = facade.getTotalNodes("2", where);
        int totalNodeB = facade.getTotalNodes("3", where);
        int totaleNodeB = facade.getTotalNodes("8", where);
        int total2G = facade.getTotalNodes("5", where);
        int total3G = facade.getTotalNodes("6", where);
        int total4G = facade.getTotalNodes("7", where);
        //Object to JSON in String
        return "{\"bts\":" + totalBts + ",\"nodeb\":" + totalNodeB + ",\"enodeb\":" + totaleNodeB + ",\"cell2g\":" + total2G + ",\"cell3g\":" + total3G + ",\"cell4g\":" + total4G + "}";
    }

}
