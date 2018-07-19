package com.vnpt.media.rims.controller.googleMap;

import com.vnpt.media.rims.bean.BSCRNCInfoBO;
import com.vnpt.media.rims.bean.BTSInfoBO;
import com.vnpt.media.rims.bean.BuildingBO;
import com.vnpt.media.rims.bean.HlrInfoBO;
import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.OmcCell2gInfoBO;
import com.vnpt.media.rims.bean.OmcCell3gInfoBO;
import com.vnpt.media.rims.bean.OmcCell4gInfoBO;
import com.vnpt.media.rims.bean.ProjectStationBO;
import com.vnpt.media.rims.bean.SgsnInfoBO;
import com.vnpt.media.rims.bean.TramDuAnBO;
import com.vnpt.media.rims.controller.report.EventController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.vnpt.media.rims.facade.GoogleMapFacade;
import com.vnpt.media.rims.facade.NodesFacade;
import com.vnpt.media.rims.formbean.DetailMapForm;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/mapDetail")
public class MapDetailController {
private static Logger logger = LogManager.getLogger(MapDetailController.class);
    @RequestMapping(value = "/detailGeo", method = RequestMethod.GET)
    public String detailGeo(@ModelAttribute(value = "model") DetailMapForm model,
            ModelMap mm, HttpServletRequest request) {

        NodesFacade face = new NodesFacade();
        String listIdBTS = "";
        String listIdNODEB = "";
        String listIdENODEB = "";
        String listIdCELL2G = "";
        String listIdCELL3G = "";
        String listIdCELL4G = "";
        String listIdBSCRNC = "";
        String listIdBUILDING = "";
        String listIdQH = "";
        String listIdDA = "";
        String listIdCS = "";
        String listIdPS = "";

        for (int i = 0; i < model.getNeTypeId().length; i++) {
            if (model.getNeTypeId()[i].equals("2")) {
                listIdBTS += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("3")) {
                listIdNODEB += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("8")) {
                listIdENODEB += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("5")) {
                listIdCELL2G += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("6")) {
                listIdCELL3G += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("7")) {
                listIdCELL4G += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("11")) {
                listIdBSCRNC += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("4")) {
                listIdBUILDING += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("9")) {
                listIdQH += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("10")) {
                listIdDA += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("20")) {
                listIdCS += model.getNodeId()[i] + ",";
            }
            if (model.getNeTypeId()[i].equals("25")) {
                listIdPS += model.getNodeId()[i] + ",";
            }
        }
      
        if(!listIdBTS.equals("")){
            List<?> listBTS = face.findAllDetailMap(listIdBTS, "2");
            mm.put("listBTS", (List<BTSInfoBO>) listBTS);
        }
        if(!listIdNODEB.equals("")){
            List<?> listNODEB = face.findAllDetailMap(listIdNODEB, "3");
            mm.put("listNODEB", (List<BTSInfoBO>) listNODEB);
        }
        if(!listIdENODEB.equals("")){
            List<?> listENODEB = face.findAllDetailMap(listIdENODEB, "8");
            mm.put("listENODEB", (List<BTSInfoBO>) listENODEB);
        }
        if(!listIdCELL2G.equals("")){
            List<?> listCELL2G = face.findAllDetailMap(listIdCELL2G, "5");
            mm.put("listCELL2G", (List<OmcCell2gInfoBO>) listCELL2G);
        }
        if(!listIdCELL3G.equals("")){
            List<?> listCELL3G = face.findAllDetailMap(listIdCELL3G, "6");
            mm.put("listCELL3G", (List<OmcCell3gInfoBO>) listCELL3G);
        }
        if(!listIdCELL4G.equals("")){
            List<?> listCELL4G = face.findAllDetailMap(listIdCELL4G, "7");
            mm.put("listCELL4G", (List<OmcCell4gInfoBO>) listCELL4G);
        }
        if(!listIdBSCRNC.equals("")){
            List<?> listBSCRNC = face.findAllDetailMap(listIdBSCRNC, "11");
            mm.put("listBSCRNC", (List<BSCRNCInfoBO>) listBSCRNC);
        }
        if(!listIdBUILDING.equals("")){
            List<?> listBUILDING = face.findAllDetailMap(listIdBUILDING, "4");
            mm.put("listBUILDING", (List<BuildingBO>) listBUILDING);
        }
        if(!listIdQH.equals("")){
            List<?> listQH = face.findAllDetailMap(listIdQH, "9");
            mm.put("listQH", (List<ProjectStationBO>) listQH);
        }
        if(!listIdDA.equals("")){
            List<?> listDA = face.findAllDetailMap(listIdDA, "10");
            mm.put("listDA", (List<TramDuAnBO>) listDA);
        }
        if(!listIdCS.equals("")){
            List<?> listCS= face.findAllDetailMap(listIdCS, "20");
            mm.put("listCS", (List<HlrInfoBO>) listCS);
        }
        if(!listIdPS.equals("")){
            List<?> listPS = face.findAllDetailMap(listIdPS, "25");
            mm.put("listPS", (List<SgsnInfoBO>) listPS);
        }
        return "googleMap/detailLayer";
    }
    
    @RequestMapping(value = "/getNode", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getNode(@RequestParam(value = "nodeId", required = false) String nodeId, ModelMap mm,
            HttpServletRequest request) throws IOException {
        
        try {
            GoogleMapFacade facade = new GoogleMapFacade();
            NodeBO node = GoogleMapFacade.getNodeById(nodeId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(node);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
