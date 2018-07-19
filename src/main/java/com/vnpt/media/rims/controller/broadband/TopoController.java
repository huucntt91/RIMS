package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.TLinkBO;
import com.vnpt.media.rims.bean.TNodeBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.formbean.ToPoForm;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.TlinkFacade;
import com.vnpt.media.rims.facade.TopoManEFacade;
import com.vnpt.media.rims.bean.TnodeStyleBO;
import com.vnpt.media.rims.facade.ManeFacade;
import com.vnpt.media.rims.facade.GoogleMapFacade;
import com.vnpt.media.rims.facade.TNodeStyleFacade;
import com.vnpt.media.rims.facade.TnodeFacade;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.formbean.SearchTopoForm;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/broadband/topo")
public class TopoController {

    private static final String TOPO_MANE = "broadband/topo/mane";
    private static final String TOPO_SEARCH = "broadband/topo/searchtopo";
    private static final String PRINT_CANVAS = "broadband/topo/printcanvas";
    private static final String TOPO_RADIO = "broadband/topo/vpntopo";

    @RequestMapping(value = "/mane", method = RequestMethod.GET)
    public String topo(Locale locale, ModelMap mm, HttpServletRequest request, @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String userId = String.valueOf(user.getId());
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        List<TinhBO> tinhList = facade.findAllTinh(String.join(",", tinhManager));
        boolean issetTopo = false;
        if (tinhTpId != null && !tinhTpId.equals("")) {

            List<TNodeBO> nodeList = TopoManEFacade.listTopoByTinh(tinhTpId);
            if (nodeList != null && nodeList.size() > 0) {
                mm.put("nodeList", nodeList);
                List<TLinkBO> tlinkList = TopoManEFacade.listLinkTopoByTinh(tinhTpId);
                mm.put("tlinkList", tlinkList);

                String[] contentTopo = TopoManEFacade.getTopoManEContent(tinhTpId);
                if (contentTopo != null && contentTopo[0] != null) {
                    mm.put("content", contentTopo);
                }
                TNodeStyleFacade tstyleFace = new TNodeStyleFacade();
                List<TnodeStyleBO> styleList = tstyleFace.findAll(null, null);
                mm.put("styleList", styleList);
                issetTopo = true;
            }
        }

        mm.put("tinhList", tinhList);
        mm.put("tinhTpId", tinhTpId);
        mm.put("issetTopo", issetTopo);
        return TOPO_MANE;
    }

    @RequestMapping(value = "/searchtopo", method = RequestMethod.GET)
    public String searchTopo(Locale locale, ModelMap mm, HttpServletRequest request, @RequestParam(value = "tnode1", required = false) String tnode1, @RequestParam(value = "tnode2", required = false) String tnode2) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String userId = String.valueOf(user.getId());
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        List<TinhBO> tinhList = facade.findAllTinh(String.join(",", tinhManager));
        boolean issetTopo = false;
        if (tnode1 != null && !tnode2.equals("")) {

            SearchTopoForm topo = TopoManEFacade.searchTopo(tnode1, tnode2);
            if (topo.getListTNode() != null && topo.getListTNode().size() > 0) {
                mm.put("nodeList", topo.getListTNode());
                mm.put("tlinkList", topo.getListTLink());

                TNodeStyleFacade tstyleFace = new TNodeStyleFacade();
                List<TnodeStyleBO> styleList = tstyleFace.findAll(null, null);
                mm.put("styleList", styleList);
                issetTopo = true;
            }
        }

        mm.put("tnode1", tnode1);
        mm.put("tnode2", tnode2);
        mm.put("issetTopo", issetTopo);
        return TOPO_SEARCH;
    }

    @RequestMapping(value = "/radiotopo", method = RequestMethod.GET)
    public String abc(Locale locale, ModelMap mm, HttpServletRequest request, @RequestParam(value = "node1", required = false) String node1, @RequestParam(value = "node2", required = false) String node2) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String userId = String.valueOf(user.getId());

        boolean issetTopo = false;
        String tnode1 = String.valueOf(TopoManEFacade.findTNodeIdByRadio(node1));
        String tnode2 = String.valueOf(TopoManEFacade.findTNodeIdByRadio(node2));
        if (!tnode1.equals("-1") && !tnode2.equals("-1")) {

            SearchTopoForm topo = TopoManEFacade.searchTopo(tnode1, tnode2);
            if (topo.getListTNode() != null && topo.getListTNode().size() > 0) {
                mm.put("nodeList", topo.getListTNode());
                mm.put("tlinkList", topo.getListTLink());

                TNodeStyleFacade tstyleFace = new TNodeStyleFacade();
                List<TnodeStyleBO> styleList = tstyleFace.findAll(null, null);
                mm.put("styleList", styleList);
                issetTopo = true;
            }
        }
        NodeBO nodeBO1 = GoogleMapFacade.getNodeById(node1);
        NodeBO nodeBO2 = GoogleMapFacade.getNodeById(node2);
        TnodeFacade facade=new TnodeFacade();
        TNodeBO tnodeBO1 = facade.findAll(tnode1,"","").get(0);
        TNodeBO tnodeBO2 = facade.findAll(tnode2,"","").get(0);
        mm.put("tnode1", tnodeBO1);
        mm.put("tnode2", tnodeBO2);
        mm.put("node1", nodeBO1);
        mm.put("node2", nodeBO2);
            
        mm.put("issetTopo", issetTopo);
        return TOPO_RADIO;
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String printCanvas(Locale locale, ModelMap mm, HttpServletRequest request, @RequestParam(value = "tinhTpId", required = false) String tinhTpId, @RequestParam(value = "neType", required = false) String neType) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String userId = String.valueOf(user.getId());
        CategoriesFacade facade = new CategoriesFacade();
        String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
        List<TinhBO> tinhList = facade.findAllTinh(String.join(",", tinhManager));
        boolean issetTopo = false;
        if (tinhTpId != null && !tinhTpId.equals("")) {

            List<TNodeBO> nodeList = TopoManEFacade.listTopoByTinh(tinhTpId);
            if (nodeList != null && nodeList.size() > 0) {
                mm.put("nodeList", nodeList);
                List<TLinkBO> tlinkList = TopoManEFacade.listLinkTopoByTinh(tinhTpId);
                mm.put("tlinkList", tlinkList);

                String[] contentTopo = TopoManEFacade.getTopoManEContent(tinhTpId);
                if (contentTopo != null && contentTopo[0] != null) {
                    mm.put("content", contentTopo);
                }
                TNodeStyleFacade tstyleFace = new TNodeStyleFacade();
                List<TnodeStyleBO> styleList = tstyleFace.findAll(null, null);
                mm.put("styleList", styleList);
                issetTopo = true;
            }
        }
        ManagerAdminFacade facadeTinh = new ManagerAdminFacade();
        List<TinhBO> tinhBO = facadeTinh.findAllTinh(tinhTpId);
        mm.put("tinhBO", tinhBO.get(0));
        mm.put("tinhTpId", tinhTpId);

        mm.put("neType", neType);
        mm.put("issetTopo", issetTopo);
        return PRINT_CANVAS;
    }

    @RequestMapping(value = "/addToPoManE", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String addToPoManE(@ModelAttribute(value = "model") ToPoForm model, HttpServletRequest request) {
        try {
//            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
//            String userId = String.valueOf(user.getId());
            model.setTypeId("1");

            int result = TopoManEFacade.fn_modify(model);
            return String.valueOf(result);
        } catch (Exception e) {
            return "-1";
        }
    }

    @RequestMapping(value = "/addEdges", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String addEdges(@ModelAttribute(value = "tlinkBO") TLinkBO model, HttpServletRequest request) {
        try {
            
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TlinkFacade facade = new TlinkFacade();
            int result = facade.insert(model, user.getId());
            return String.valueOf(result);
        } catch (Exception e) {
            return "-1";
        }
    }

    @RequestMapping(value = "/deleteEdges", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String deleteEdges(@ModelAttribute(value = "tlinkBO") TLinkBO model, HttpServletRequest request) {
        try {
            
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TlinkFacade facade = new TlinkFacade();
            int result = facade.delete(model, user.getId());
            return String.valueOf(result);
        } catch (Exception e) {
            return "-1";
        }
    }

    @RequestMapping(value = "/getEdges/{id}", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getEdges(@PathVariable(value = "id") String id, ModelMap mm,
            HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        TlinkFacade facade = new TlinkFacade();
        return mapper.writeValueAsString(facade.findAll(id, null, 0, 10).get(0));
    }

    @RequestMapping(value = "/addTnodePopup", method = RequestMethod.GET)
    public String addTnodePopup(ModelMap mm, HttpServletRequest request) {
        
        return "broadband/topo/popup";
    }

    @RequestMapping(value = "/deleteTnode/{id}", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String deleteTnode(@PathVariable(value = "id") String id, HttpServletRequest request) {
        try {
            
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            ManeFacade.delete(id, String.valueOf(user.getId()));
            return String.valueOf(1);
        } catch (Exception e) {
            return "-1";
        }
    }
    
    @RequestMapping(value = "/vpntopo", method = RequestMethod.GET)
    public String vpntopo(Locale locale, ModelMap mm, HttpServletRequest request, @RequestParam(value = "node1", required = false) String node1, @RequestParam(value = "node2", required = false) String node2) {
        UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
        String userId = String.valueOf(user.getId());

        boolean issetTopo = false;
        String tnode1 = String.valueOf(TopoManEFacade.findTNodeIdByRadio(node1));
        String tnode2 = String.valueOf(TopoManEFacade.findTNodeIdByRadio(node2));
        if (!tnode1.equals("-1") && !tnode2.equals("-1")) {

            SearchTopoForm topo = TopoManEFacade.searchTopo(tnode1, tnode2);
            if (topo.getListTNode() != null && topo.getListTNode().size() > 0) {
                mm.put("nodeList", topo.getListTNode());
                mm.put("tlinkList", topo.getListTLink());

                TNodeStyleFacade tstyleFace = new TNodeStyleFacade();
                List<TnodeStyleBO> styleList = tstyleFace.findAll(null, null);
                mm.put("styleList", styleList);
                issetTopo = true;
            }
        }
        NodeBO nodeBO1 = GoogleMapFacade.getNodeById(node1);
        NodeBO nodeBO2 = GoogleMapFacade.getNodeById(node2);
        TnodeFacade facade=new TnodeFacade();
        TNodeBO tnodeBO1 = facade.findAll(tnode1,"","").get(0);
        TNodeBO tnodeBO2 = facade.findAll(tnode2,"","").get(0);
        mm.put("tnode1", tnodeBO1);
        mm.put("tnode2", tnodeBO2);
        mm.put("node1", nodeBO1);
        mm.put("node2", nodeBO2);
            
        mm.put("issetTopo", issetTopo);
        return TOPO_RADIO;
    }


}
