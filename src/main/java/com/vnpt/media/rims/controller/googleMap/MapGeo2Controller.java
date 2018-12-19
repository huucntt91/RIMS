package com.vnpt.media.rims.controller.googleMap;

import com.google.gson.Gson;
import com.vnpt.media.rims.bean.BSCRNCInfoBO;
import com.vnpt.media.rims.bean.BTSInfoBO;
import com.vnpt.media.rims.bean.BuildingBO;
import com.vnpt.media.rims.bean.ConfLayerBO;
import com.vnpt.media.rims.bean.HlrInfoBO;
import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.OmcCell2gInfoBO;
import com.vnpt.media.rims.bean.OmcCell3gInfoBO;
import com.vnpt.media.rims.bean.OmcCell4gInfoBO;
import com.vnpt.media.rims.bean.ProjectStationBO;
import com.vnpt.media.rims.bean.SgsnInfoBO;
import com.vnpt.media.rims.bean.TramDuAnBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.GoogleMapFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.FilterMapForm;
import com.vnpt.media.rims.formbean.DetailMapForm;
import com.vnpt.media.rims.formbean.NeLinkForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/mapGeo2")
public class MapGeo2Controller {

    private static final String MAP_LIST = "googleMap/mapGeo2";
    private static final String NODE_LIST = "googleMap/getNodes";
    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("tinhList")
    public List findAllTinh(HttpServletRequest request) {
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        return facade.findAllTinh(String.join(",", tinhManager));
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, HttpServletRequest request) {
//        trunglk_start
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String userId = String.valueOf(user.getId());
//        trunglk_end

        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        String tinhTpId;
        tinhTpId = (((tinhManager == null) || (tinhManager.length == 0)) ? "0" : String.join(",", tinhManager));
        GoogleMapFacade facade = new GoogleMapFacade();
//        trunglk_start
        ArrayList<ConfLayerBO> list = facade.fc_find_layer("", userId);

        mm.addAttribute("lstLayer", list);
        mm.addAttribute("myListLayer", facade.fc_find_event("", userId));
        mm.addAttribute("listEvent", facade.fc_list_event());
//        trunglk_end
        //mm.put("filterReportList", facade.findFilterMap());
        mm.put("tinhTpId", tinhTpId);
        mm.put("quanHuyenId", 0L);
        mm.put("phuongXaId", 0L);
        return MAP_LIST;
    }

    @RequestMapping(value = "/popup/{id}", method = RequestMethod.GET)
    public String popup(ModelMap mm, HttpServletRequest request, @PathVariable(value = "id") String id) {
        CategoriesFacade facade = new CategoriesFacade();
        List<BuildingBO> list = facade.findAllBuildingBO("", "", id, "", null, "", "", "");
        if (list.size() > 0) {
            mm.addAttribute("model", list.get(0));
        }
        return "googleMap/popup";
    }

//    trunglk_start
    @RequestMapping(value = "/getlistLayer/{layerId}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getlistLayer(@PathVariable(value = "layerId") String layerId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String userId = String.valueOf(user.getId());
        Map referenceData = new HashMap();
        GoogleMapFacade facade = new GoogleMapFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        List<ConfLayerBO> model = facade.fc_find_event(layerId, userId);
        if (model != null && model.size() > 0) {
            return mapper.writeValueAsString(model.get(0));
        } else {
            return "{}";
        }
    }

    @RequestMapping(value = "/getlistEvent/{eventId}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getlistEvent(ModelMap mm,
            HttpServletRequest request) throws IOException {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String userId = String.valueOf(user.getId());
        Map referenceData = new HashMap();
        GoogleMapFacade facade = new GoogleMapFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(facade.fc_list_event());
    }

    @RequestMapping(value = "/getdataEvent", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getdataEvent(@RequestParam(value = "eventId") String eventId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            ModelMap mm,
            HttpServletRequest request) throws IOException {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String userId = String.valueOf(user.getId());
        Map referenceData = new HashMap();
        GoogleMapFacade facade = new GoogleMapFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(facade.fc_data_layer(eventId, startDate, endDate));
    }

    @RequestMapping(value = "/addMyLayer", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String addMyLayer(@ModelAttribute(value = "model") ConfLayerBO model, HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String userId = String.valueOf(user.getId());
            int result = GoogleMapFacade.fn_modify("add", model.layerId, model.layerName, model.styleOpacity, model.styleColor, model.styleBorderColor, model.styleSize, model.styleWhere, userId, model.objectId, model.eventId, model.eventName, model.startDate, model.endDate);
            return String.valueOf(result);
        } catch (Exception e) {
            return "-1";
        }
    }

    @RequestMapping(value = "/updateMyLayer", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String update(ModelMap mm, @ModelAttribute(value = "model") ConfLayerBO model,
            HttpServletRequest request) throws Exception {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            String userId = String.valueOf(user.getId());
            int result = GoogleMapFacade.fn_modify("edit", model.layerId, model.layerName, model.styleOpacity, model.styleColor, model.styleBorderColor, model.styleSize, model.styleWhere, userId, model.objectId, model.eventId, model.eventName, model.startDate, model.endDate);
            return String.valueOf(result);
        } catch (Exception e) {
            return "-1";
        }
    }

    @RequestMapping(value = "/getNode1Link/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getNode1Link(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        NodesFacade facade = new NodesFacade();
        List<NeLinkForm> list = facade.findNode1Link(id);
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(list);
    }

    @RequestMapping(value = "/getNode2Link/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getNode2Link(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        NodesFacade facade = new NodesFacade();
        List<NeLinkForm> list = facade.findNode2Link(id);
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(list);
    }

    @RequestMapping(value = "/delMyLayer/{Id}", method = RequestMethod.GET,produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String delMyLayer(ModelMap mm, @PathVariable(value = "Id") String Id) throws Exception {
        try {
            int result = GoogleMapFacade.fn_modify("del", Id, "", "", "", "", "", "", "", "", "", "", "", "");
            return String.valueOf(result);
        } catch (Exception e) {
            return "-1";
        }
    }

    @RequestMapping(value = "/countNodes",
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getCountNodes(@ModelAttribute(value = "model") FilterMapForm model, ModelMap mm,
            HttpServletRequest request) throws IOException {
        GoogleMapFacade facade = new GoogleMapFacade();

        String whereLocation = " ";
        if (model.getTinhId() != null && model.getTinhId() != ""  && !model.getTinhId().equals("0")) {
            whereLocation += " and building.tinhtp_id = " + model.getTinhId();
            if (model.getHuyenId() != null && model.getHuyenId() != "") {
                whereLocation += " and building.quanhuyen_id = " + model.getHuyenId();
                if (model.getXaId() != null && model.getXaId() != "") {
                    whereLocation += " and building.phuongxa_id = " + model.getXaId();
                }
            }
        }

        String where = whereLocation + model.getWhere();
        int totalBts=0;
        try
        {
          totalBts = facade.getTotalNodes("2", where);
        }
        catch(Exception ex1)
        {
            ex1.getStackTrace();
        }
      
        int totalNodeB= 0;
        try
        {
            totalNodeB = facade.getTotalNodes("3", where);
        }
        catch(Exception ex1)
        {
            ex1.getStackTrace();
        }
        int totaleNodeB = 0;
        try
        {
            totaleNodeB = facade.getTotalNodes("8", where);
        }
        catch(Exception ex1)
        {
            ex1.getStackTrace();
        }
        int total2G = 0;
        try
        {
            total2G = facade.getTotalNodes("5", where);
        }
        catch(Exception ex1)
        {
            ex1.getStackTrace();
        }
        int total3G = 0;
        try
        {
        total3G = facade.getTotalNodes("6", where);
        }
        catch(Exception ex1)
        {
            ex1.getStackTrace();
        }
        int total4G =0;
         try
        {
        total4G = facade.getTotalNodes("7", where);
        }
        catch(Exception ex1)
        {
            ex1.getStackTrace();
        }
        //Object to JSON in String
        return "{\"bts\":" + totalBts + ",\"nodeb\":" + totalNodeB + ",\"enodeb\":" + totaleNodeB + ",\"cell2g\":" + total2G + ",\"cell3g\":" + total3G + ",\"cell4g\":" + total4G + "}";
    }
    
    // API tra ve danh sach cac node
     @RequestMapping(value = "/Nodes", method = RequestMethod.GET)
    // public @ResponseBody
      public  String getNodes(@ModelAttribute(value = "model") FilterMapForm model, ModelMap mm,
            HttpServletRequest request) 
      {
        try
        {
            GoogleMapFacade facade = new GoogleMapFacade();
            //
            String whereLocation = " ";
            if (model.getTinhId() != null && model.getTinhId() != "" && !model.getTinhId().equals("0")) {
                whereLocation += " and building.tinhtp_id = " + model.getTinhId();
                if (model.getHuyenId() != null && model.getHuyenId() != "") {
                    whereLocation += " and building.quanhuyen_id = " + model.getHuyenId();
                    if (model.getXaId() != null && model.getXaId() != "") {
                        whereLocation += " and building.phuongxa_id = " + model.getXaId();
                    }
                }
            }
            //
            String objectType=model.getObjectType();
            String where = whereLocation + model.getWhere();
            if(objectType.equals("-1")) objectType="2";
            List<NodeBO> resultSearch=facade.getNodes(objectType, where);
            mm.put("objectType",objectType);
            mm.put("list", resultSearch);
            mm.put("resultCount",resultSearch.size());
            return NODE_LIST;
        }
        catch(Exception ex)
        {
            ex.getStackTrace();
            
        }
        return "Error get data";
    }
//    trunglk_end

    @RequestMapping(value = "/fillAttrObject/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String fillAttrObject(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        GoogleMapFacade facade = new GoogleMapFacade();
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        return mapper.writeValueAsString(facade.findFilterMap(id));
    }
    @RequestMapping(value = "/GetProviceByLonLat/{lon}/{lat}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    int GetProviceByLonLat(@PathVariable(value = "lon") String lon, @PathVariable(value = "lat") String lat,HttpServletRequest request) throws IOException 
    {
        GoogleMapFacade facade = new GoogleMapFacade();
        return facade.GetProvinceByLonLat(lon,lat);
    }
    @RequestMapping(value = "/GetDistrictByLonLat/{lon}/{lat}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String GetDistrictByLonLat(@PathVariable(value = "lon") String lon, @PathVariable(value = "lat") String lat,HttpServletRequest request) throws IOException 
    {
        GoogleMapFacade facade = new GoogleMapFacade();
        ObjectMapper mapper = new ObjectMapper();
        //return mapper.writeValueAsString(facade.findFilterMap(id));
        return "";
    }
    
    @RequestMapping(value = "/getchaid/{id}", method = RequestMethod.GET,
           produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getchaid(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        return gson.toJson(GoogleMapFacade.getNodeChaByNodeId(id));
    }
}
