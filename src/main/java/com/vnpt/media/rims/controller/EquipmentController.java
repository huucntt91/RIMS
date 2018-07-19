/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller;

import com.vnpt.media.rims.bean.CardBO;
import com.vnpt.media.rims.bean.DataViewBO;
import com.vnpt.media.rims.bean.PortBO;
import com.vnpt.media.rims.bean.RackBO;
import com.vnpt.media.rims.bean.SlotBO;
import com.vnpt.media.rims.bean.SubRackBO;
import com.vnpt.media.rims.bean.TreeTableDataView;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.facade.EquipmentFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/equipment")
public class EquipmentController {

    private static Logger logger = LogManager.getLogger(EquipmentController.class);

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Locale locale, ModelMap mm, 
              @RequestParam(value = "nodeId", required = false) String nodeId,HttpServletRequest request) {
        ManagerAdminFacade facade = new ManagerAdminFacade();
        List<UserBO> lst = facade.findAllUsers(null, 0, 10);
        mm.put("list_user", lst);
        
        EquipmentFacade equipmentFacade = new EquipmentFacade();
        List<DataViewBO> list = equipmentFacade.getDataView(Long.valueOf(nodeId));
        List<TreeTableDataView> listTreeTable = new ArrayList<TreeTableDataView>();
        Map<Long, Long> mapRack = new HashMap<Long, Long>();
        Map<Long, Long> mapSubRack = new HashMap<Long, Long>();
        Map<Long, Long> mapSlot = new HashMap<Long, Long>();
        Map<Long, Long> mapCard = new HashMap<Long, Long>();
        Map<Long, Long> mapPort = new HashMap<Long, Long>();
        String neType = "";
        String neName = "";
        String neFdn = "";
         
        for (int i = 0; i < list.size(); i++) {
            
            DataViewBO temp = list.get(i);
            if (temp.getRackId() != null && temp.getRackId() != -1l) {
                if (mapRack.get(temp.getRackId()) == null) {
                    neFdn = temp.getRackNeFdn();
                    neType = temp.getRackNeType();
                    neName = temp.getRackNeName();
                    listTreeTable.add(new TreeTableDataView.Builder(temp.getRackId(), -1l, temp.getRackNeName(), "rackIcon", "Rack").neType(temp.getRackNeType())
                            .neName(temp.getRackNeName()).neFdn(temp.getRackNeFdn()).inventoryUnitType(temp.getRackInventoryUnitType())
                            .bomCode(temp.getRackBomCode()).manufacturerData(temp.getRackManufacturerData())
                            .serialNumber(temp.getRackSerialNumber()).vendorName(temp.getRackVendorName()).no(temp.getRackNo())
                            .name(temp.getRackName())
                            .status(temp.getRackStatus())
                            .build());
                    mapRack.put(temp.getRackId(), temp.getRackId());
                }
            }
            if (temp.getSubrackId() != null && temp.getSubrackId() != -1l) {
                if (mapSubRack.get(temp.getSubrackId()) == null) {
                    listTreeTable.add(new TreeTableDataView.Builder(temp.getSubrackId(), temp.getRackId(), temp.getSubrackNeName(), "subrackIcon", "subrack").neType(temp.getSubrackNeType())
                            .neName(temp.getSubrackNeName()).neFdn(temp.getSubrackNeFdn()).inventoryUnitType(temp.getSubrackInventoryUnitType())
                            .bomCode(temp.getSubrackBomCode()).manufacturerData(temp.getSubrackManufacturerData())
                            .serialNumber(temp.getSubrackSerialNumber()).vendorName(temp.getSubrackVendorName()).no(temp.getSubrackNo())
                            .name(temp.getRackName())
                            .status(temp.getRackStatus())
                            .build());
                    mapSubRack.put(temp.getSubrackId(), temp.getSubrackId());
                }
            }
            if (temp.getSlotId() != null && temp.getSlotId() != -1l) {
                if (mapSlot.get(temp.getSlotId()) == null) {
                    listTreeTable.add(new TreeTableDataView.Builder(temp.getSlotId(), temp.getSubrackId(),
                            "", "slotIcon", "Slot")
                            //                            .neType(temp.getSlotNeType())
                            //                            .neName(temp.getSlotNeName()).neFdn(temp.getSlotNeFdn()).inventoryUnitType(temp.getSlotInventoryUnitType())
                            //                            .bomCode(temp.getSlotBomCode()).manufacturerData(temp.getSlotManufacturerData())
                            //                            .serialNumber(temp.getSlotSerialNumber()).vendorName(temp.getSlotVendorName())
                            .no(temp.getSlotNo())
                            .name(temp.getRackName())
                            .status(temp.getRackStatus())
                            .build());
                    mapSlot.put(temp.getSlotId(), temp.getSlotId());
                }
            }
            if (temp.getCardId() != null && temp.getCardId() != -1l) {
                if (mapCard.get(temp.getCardId()) == null) {
                    listTreeTable.add(new TreeTableDataView.Builder(temp.getCardId(), temp.getSlotId(), temp.getCardNeName(), "cardIcon", "Card").neType(temp.getCardNeType())
                            .neName(temp.getCardNeName()).neFdn(temp.getCardNeFdn()).inventoryUnitType(temp.getCardInventoryUnitType())
                            .bomCode(temp.getCardBomCode()).manufacturerData(temp.getCardManufacturerData())
                            .serialNumber(temp.getCardSerialNumber()).vendorName(temp.getCardVendorName()).no(temp.getCardNo())
                            .name(temp.getRackName())
                            .status(temp.getRackStatus())
                            .build());
                    mapCard.put(temp.getCardId(), temp.getCardId());
                }
            }
            if (temp.getPortId() != null && temp.getPortId() != -1l) {
                if (mapPort.get(temp.getPortId()) == null) {
//                    listTreeTable.add(new TreeTableDataView(temp.getPortId(), temp.getCardId(), temp.getPortNo(),"portIcon","PORT"));
//                    mapPort.put(temp.getPortId(), temp.getPortId());
                    listTreeTable.add(new TreeTableDataView.Builder(temp.getPortId(), temp.getCardId(),
                            "", "portIcon", "Port")
                            //                            .neType(temp.getPortNeType())
                            //                            .neName(temp.getPortNeName()).neFdn(temp.getPortNeFdn()).inventoryUnitType(temp.getPortInventoryUnitType())
                            //                            .bomCode(temp.getPortBomCode()).manufacturerData(temp.getPortManufacturerData())
                            //                            .serialNumber(temp.getPortSerialNumber()).vendorName(temp.getPortVendorName())
                            .no(temp.getPortNo())
                            .name(temp.getRackName())
                            .status(temp.getRackStatus())
                            .build());
                    mapPort.put(temp.getPortId(), temp.getPortId());
                }
            }
        }
        mm.put("list_treetable", listTreeTable);
        mm.put("neFdn", neFdn);
        mm.put("neType", neType);
        mm.put("neName", neName);
        return "equipment/dataViewEq";
    }
}
