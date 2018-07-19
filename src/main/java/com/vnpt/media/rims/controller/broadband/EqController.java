/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.facade.TEqFacade;
import com.vnpt.media.rims.facade.TnodeFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/eq")
public class EqController {

    private static Logger logger = LogManager.getLogger(EqController.class);

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String sublist(@RequestParam(value = "tnodeId", required = false) String tnodeId,
            ModelMap mm, HttpServletRequest request) {
        try {
            logger.info("Action init tnode");

            TEqFacade facade = new TEqFacade();

            List<TEq1BO> list = facade.findAllEq1(null, null, tnodeId, 0, 100);

            List<SubEqBO> subList = new ArrayList<SubEqBO>();
            for (TEq1BO item : list) {
                SubEqBO sub = new SubEqBO();
                List<TEq2BO> listEq2 = facade.findAllEq2(null, null, String.valueOf(item.getId()), 0, 100);
                item.settEq2BOList(listEq2);
//            List<TEq3BO> listEq3=new ArrayList<TEq3BO>();
                for (int i = 0; i < listEq2.size(); i++) {
                    List<TEq3BO> listEq3 = facade.findAllEq3(null, null, listEq2.get(i).getId() + "", 0, 100);
                    listEq2.get(i).settEq3BOList(listEq3);
                    item.settEq2BOList(listEq2);
                    for (int j = 0; j < listEq3.size(); j++) {
                        List<TPortBO> listPort = facade.findAllPort(null, null, listEq3.get(j).getId() + "", 0, 100);
                        listEq3.get(j).settPortBOList(listPort);
                        listEq2.get(i).settEq3BOList(listEq3);
                        item.settEq2BOList(listEq2);
                        for (int k = 0; k < listPort.size(); k++) {
                            List<TModuleQuangBO> listModuleQuang = facade.findAllModuleQuang(null, null, listPort.get(k).getId() + "", 0, 100);
                            listPort.get(k).settModuleQuangBOList(listModuleQuang);
                            listEq3.get(j).settPortBOList(listPort);
                            listEq2.get(i).settEq3BOList(listEq3);
                            item.settEq2BOList(listEq2);
                        }
                    }
                }
                sub.settEq1BO(item);
                subList.add(sub);
            }
            request.getSession().setAttribute("listEq", subList);
            mm.put("list", subList);

//        TnodeFacade tnodefacade = new TnodeFacade();
//        if (tnodeId != null) {
//            List<TNodeBO> temp = tnodefacade.findAll(tnodeId, null, null);
//            if (temp != null && temp.size() > 0) {
            //        mm.put("tnodeName", temp.get(0).getName());
            mm.put("tnodeId", tnodeId);
//            }
//        }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/sublist";
    }

    @RequestMapping(value = "/preAddEq1", method = RequestMethod.GET)
    public String preAddEq1(@RequestParam(value = "tnodeId", required = false) long tnodeId,
            @RequestParam(value = "tnodeName", required = false) String tnodeName, ModelMap mm) {
        try {
            TEq1BO teq1BO = new TEq1BO();
            teq1BO.setTnodeId(tnodeId);
            teq1BO.setTnodeName(tnodeName);
            mm.addAttribute("tEq1Form", teq1BO);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newEq1";
    }

    @RequestMapping(value = "/preAddEq2", method = RequestMethod.GET)
    public String preAddEq2(@RequestParam(value = "eq1Id", required = false) long eq1Id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            @RequestParam(value = "eq1Name", required = false) String eq1Name, ModelMap mm) {
        try {
            TEq2BO teq2BO = new TEq2BO();
            teq2BO.setEq1Id(eq1Id);

            teq2BO.setEq1Name(eq1Name);
            mm.addAttribute("tEq2Form", teq2BO);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newEq2";
    }

    @RequestMapping(value = "/preAddEq3", method = RequestMethod.GET)
    public String preAddEq3(@RequestParam(value = "eq2Id", required = false) long eq2Id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            @RequestParam(value = "eq2Name", required = false) String eq2Name, ModelMap mm) {
        try {
            TEq3BO teq3BO = new TEq3BO();
            teq3BO.setEq2Id(eq2Id);

            teq3BO.setEq2Name(eq2Name);
            mm.addAttribute("tEq3Form", teq3BO);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newEq3";
    }

    @RequestMapping(value = "/preAddTport", method = RequestMethod.GET)
    public String preAddTport(@RequestParam(value = "eq3Id", required = false) long eq3Id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            @RequestParam(value = "eq3Name", required = false) String eq3Name, ModelMap mm) {
        try {
            TPortBO tPortBO = new TPortBO();
            tPortBO.setEq3Id(eq3Id);

            tPortBO.setEq3Name(eq3Name);
            mm.addAttribute("tPortForm", tPortBO);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newTPort";
    }

    @RequestMapping(value = "/preAddTquang", method = RequestMethod.GET)
    public String preAddTquang(@RequestParam(value = "tportId", required = false) long portId,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            @RequestParam(value = "tportName", required = false) String portName, ModelMap mm) {
        try {
            TModuleQuangBO tQuang = new TModuleQuangBO();
            tQuang.setPortId(portId);

            tQuang.setPortName(portName);
            mm.addAttribute("tQuangForm", tQuang);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newTQuang";
    }

    @RequestMapping(value = "/preUpdateEq1/{id}", method = RequestMethod.GET)
    public String preUpdateEq1(@PathVariable(value = "id") String id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            ModelMap mm) {
        try {
            TEqFacade facade = new TEqFacade();
            TEq1BO tEq1BO = facade.findAllEq1(id, null, null, 0, 10).get(0);
            mm.addAttribute("tEq1Form", tEq1BO);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newEq1";
    }

    @RequestMapping(value = "/preUpdateEq2/{id}", method = RequestMethod.GET)
    public String preUpdateEq2(@PathVariable(value = "id") String id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            ModelMap mm) {
        try {
            TEqFacade facade = new TEqFacade();
            TEq2BO tEq2BO = facade.findAllEq2(id, null, null, 0, 10).get(0);
            mm.addAttribute("tEq2Form", tEq2BO);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newEq2";
    }

    @RequestMapping(value = "/preUpdateEq3/{id}", method = RequestMethod.GET)
    public String preUpdateEq3(@PathVariable(value = "id") String id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            ModelMap mm) {
        try {
            TEqFacade facade = new TEqFacade();

            TEq3BO tEq3BO = facade.findAllEq3(id, null, null, 0, 10).get(0);
            mm.addAttribute("tEq3Form", tEq3BO);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newEq3";
    }

    @RequestMapping(value = "/preUpdateTport/{id}", method = RequestMethod.GET)
    public String preUpdateTport(@PathVariable(value = "id") String id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            ModelMap mm) {
        try {
            TEqFacade facade = new TEqFacade();
            TPortBO tPortBO = facade.findAllPort(id, null, null, 0, 10).get(0);
            mm.addAttribute("tPortForm", tPortBO);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newTPort";
    }

    @RequestMapping(value = "/preUpdateTquang/{id}", method = RequestMethod.GET)
    public String preUpdateTquang(@PathVariable(value = "id") String id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            ModelMap mm) {
        try {
            TEqFacade facade = new TEqFacade();

            TModuleQuangBO tQuangBO = facade.findAllModuleQuang(id, null, null, 0, 10).get(0);
            mm.addAttribute("tQuangForm", tQuangBO);
            mm.addAttribute("tnodeId", tnodeId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "broadband/eq/newTQuang";
    }

    @RequestMapping(value = "/updateEq1", method = RequestMethod.POST)
    public String updateEq1(ModelMap mm, @ModelAttribute(value = "tEq1BO") TEq1BO tEq1BO,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("tEq1Form", tEq1BO);

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null ) {
            facade.updateEq1(tEq1BO, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tEq1BO.getTnodeId();
    }

    @RequestMapping(value = "/updateEq2", method = RequestMethod.POST)
    public String updateEq2(ModelMap mm, @ModelAttribute(value = "tEq2BO") TEq2BO tEq2BO,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("tEq2Form", tEq2BO);

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null || tEq1BO.getId().equalsIgnoreCase("")) {
            facade.updateEq2(tEq2BO, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tnodeId;
    }

    @RequestMapping(value = "/updateEq3", method = RequestMethod.POST)
    public String updateEq3(ModelMap mm, @ModelAttribute(value = "tEq3BO") TEq3BO tEq3BO,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("tEq3Form", tEq3BO);

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null || tEq1BO.getId().equalsIgnoreCase("")) {
            facade.updateEq3(tEq3BO, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tnodeId;
    }

    @RequestMapping(value = "/updateTport", method = RequestMethod.POST)
    public String updateTPort(ModelMap mm, @ModelAttribute(value = "tPortBO") TPortBO tPortBO,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("tPortForm", tPortBO);

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null || tEq1BO.getId().equalsIgnoreCase("")) {
            facade.updateTPort(tPortBO, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tnodeId;
    }

    @RequestMapping(value = "/updateTquang", method = RequestMethod.POST)
    public String updateTQuang(ModelMap mm, @ModelAttribute(value = "tQuangBO") TModuleQuangBO tQuangBO,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            BindingResult bindingResult, RedirectAttributes attr, HttpServletRequest request) {
        try {
            mm.addAttribute("tQuangForm", tQuangBO);

            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null || tEq1BO.getId().equalsIgnoreCase("")) {
            facade.updateTQuang(tQuangBO, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tnodeId;
    }

    @RequestMapping(value = "/deleteEq1/{id}", method = RequestMethod.GET)
    public String deleteEq1(ModelMap mm, @PathVariable(value = "id") String id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            RedirectAttributes attr, HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null ) {
            facade.deleteEq1(id, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tnodeId;
    }

    @RequestMapping(value = "/deleteEq2/{id}", method = RequestMethod.GET)
    public String deleteEq2(ModelMap mm, @PathVariable(value = "id") String id,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            RedirectAttributes attr, HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null ) {
            facade.deleteEq2(id, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tnodeId;
    }

    @RequestMapping(value = "/deleteEq3/{id}", method = RequestMethod.GET)
    public String deleteEq(@PathVariable(value = "id") String id, ModelMap mm,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            RedirectAttributes attr, HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null ) {
            facade.deleteEq3(id, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tnodeId;
    }

    @RequestMapping(value = "/deleteTport", method = RequestMethod.GET)
    public String deleteTport(@RequestParam(value = "id", required = false) String id, ModelMap mm,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            RedirectAttributes attr, HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null ) {
            facade.deleteTport(id, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//        return "broadband/eq/sublist";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tnodeId;
    }

    @RequestMapping(value = "/deleteTquang", method = RequestMethod.GET)
    public String deleteTquang(@RequestParam(value = "id", required = false) String id, ModelMap mm,
            @RequestParam(value = "tnodeId", required = false) long tnodeId,
            RedirectAttributes attr, HttpServletRequest request) {
        try {
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            TEqFacade facade = new TEqFacade();
//        if (tEq1BO.getId() == null ) {
            facade.deleteTquang(id, user.getId());
//        } else {
//            facade.update(tEq1BO, user.getId());
//        }
            attr.addFlashAttribute("info", new Message(Message.TYPE_SUCCESS, Message.HEAD_SUCCESS, Message.MESSAGE_UPDATE_SUCCESS));
//        return "broadband/eq/sublist";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/eq/init?tnodeId=" + tnodeId;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            TEqFacade facade = new TEqFacade();
            String jasperFileName = "report1.jrxml";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", "Lạc Tân");
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, "test");
        } catch (JRException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private void xls(HttpServletResponse resp,
            JasperPrint xlsPrint, String fileName) throws JRException, IOException {
        JRXlsExporter xlsExporter = new JRXlsExporter();
        xlsExporter.setExporterInput(new SimpleExporterInput(xlsPrint));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(resp.getOutputStream()));//new SimpleOutputStreamExporterOutput(outXlsName));
        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
        xlsReportConfiguration.setOnePagePerSheet(false);
        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        xlsReportConfiguration.setDetectCellType(false);
        xlsReportConfiguration.setWhitePageBackground(false);
        xlsExporter.setConfiguration(xlsReportConfiguration);
        resp.setContentType("application/force-download");
        resp.setHeader("Content-Transfer-Encoding", "binary");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls\"");
        xlsExporter.exportReport();
    }

    private void xlsx(HttpServletResponse resp,
            JasperPrint xlsxPrint, String fileName) throws JRException, IOException {
        JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        xlsxExporter.setExporterInput(new SimpleExporterInput(xlsxPrint));
        xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(resp.getOutputStream()));//new SimpleOutputStreamExporterOutput(outXlsName));
        SimpleXlsxReportConfiguration xlsxReportConfiguration = new SimpleXlsxReportConfiguration();
        xlsxReportConfiguration.setOnePagePerSheet(false);
        xlsxReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        xlsxReportConfiguration.setDetectCellType(false);
        xlsxReportConfiguration.setWhitePageBackground(false);
        xlsxExporter.setConfiguration(xlsxReportConfiguration);
        resp.setContentType("application/force-download");
        resp.setHeader("Content-Transfer-Encoding", "binary");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"");
        xlsxExporter.exportReport();
    }
//    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
//    public void exportExcel(HttpServletRequest request,
//            HttpServletResponse response) {
//        List<SubEqBO> list = (List<SubEqBO>) request.getSession().getAttribute("listEq");
//
//        try {
//            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
//            String fileName = "eq.xlsx";
//            File fileTemplate = new File(dataDirectory + File.separator + fileName);
//
//            File fileResult = writeExcel(fileTemplate, fileName, list);
//            if (fileResult != null && fileResult.exists()) {
//                response.setContentType("application/excel");
//                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
//                try {
//                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
//                    response.getOutputStream().flush();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        logger.info("writed excel ...............");
//    }

    private File writeExcel(File fileTemplate, String fileName, List<SubEqBO> datas) {
        FileInputStream fin = null;
        File result = null;
        Workbook workbook = null;
        FileOutputStream fos = null;
        try {

            fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            int rowIndexEq1 = 1;
            int sttEq1 = 0;
            for (SubEqBO item : datas) {
//                    write eq1
//                for (int i = 0; i < item.gettEq1BO(); i++) {

                try {
                    Cell cell = null;
                    Row row = null;

                    //header
//                        for (SubEqBO item : datas) {
                    Sheet sheet = workbook.getSheetAt(0);//createSheet("2G");
                    sttEq1 += 1;
                    row = sheet.createRow(rowIndexEq1++);
                    CellStyle style = sheet.getWorkbook().createCellStyle();
                    row.setRowStyle(style);
                    cell = row.createCell(0);
                    cell.setCellValue(sttEq1);
                    cell = row.createCell(1);
                    cell.setCellValue(item.gettEq1BO().getName());

                    cell = row.createCell(2);
                    cell.setCellValue(item.gettEq1BO().getNumber());

                    cell = row.createCell(3);
                    cell.setCellValue(item.gettEq1BO().getVendor());

                    cell = row.createCell(4);
                    cell.setCellValue(item.gettEq1BO().getNote());

                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }

            }

            int rowIndexEq2 = 1;
            int sttEq2 = 0;
            for (SubEqBO item : datas) {
                for (int i = 0; i < item.gettEq1BO().gettEq2BOList().size(); i++) {
                    try {
                        Cell cell = null;
                        Row row = null;

                        //header
//                        for (SubEqBO item : datas) {
                        Sheet sheet = workbook.getSheetAt(1);//createSheet("2G");
                        sttEq2 += 1;
                        row = sheet.createRow(rowIndexEq2++);
                        CellStyle style = sheet.getWorkbook().createCellStyle();
                        row.setRowStyle(style);
                        cell = row.createCell(0);
                        cell.setCellValue(sttEq2);
                        cell = row.createCell(1);
                        cell.setCellValue(item.gettEq1BO().getName());
                        cell = row.createCell(2);
                        cell.setCellValue(item.gettEq1BO().gettEq2BOList().get(i).getName());

                        cell = row.createCell(3);
                        cell.setCellValue(item.gettEq1BO().gettEq2BOList().get(i).getNumber());

                        cell = row.createCell(4);
                        cell.setCellValue(item.gettEq1BO().gettEq2BOList().get(i).getVendor());
//
                        cell = row.createCell(5);
                        cell.setCellValue(item.gettEq1BO().gettEq2BOList().get(i).getNote());
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }

            int rowIndexEq3 = 1;
            int sttEq3 = 0;
            for (SubEqBO item : datas) {

                for (TEq2BO eq2 : item.gettEq1BO().gettEq2BOList()) {

                    for (TEq3BO eq3 : eq2.gettEq3BOList()) {

                        try {
                            Cell cell = null;
                            Row row = null;

                            //header
//                        for (SubEqBO item : datas) {
                            Sheet sheet = workbook.getSheetAt(2);//createSheet("2G");
                            sttEq3 += 1;
                            row = sheet.createRow(rowIndexEq3++);
                            CellStyle style = sheet.getWorkbook().createCellStyle();
                            row.setRowStyle(style);
                            cell = row.createCell(0);
                            cell.setCellValue(sttEq3);
                            cell = row.createCell(1);
                            cell.setCellValue(item.gettEq1BO().getName());

                            cell = row.createCell(2);
                            cell.setCellValue(eq2.getName());

                            cell = row.createCell(3);
                            cell.setCellValue(eq3.getName());
                            cell = row.createCell(4);
                            cell.setCellValue(eq3.getNumber());
                            cell = row.createCell(5);
                            cell.setCellValue(eq3.getVendor());
                            cell = row.createCell(6);
                            cell.setCellValue(eq3.getNote());
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
            }

            int rowIndexEq4 = 1;
            int sttEq4 = 0;
            for (SubEqBO item : datas) {
                for (TEq2BO eq2 : item.gettEq1BO().gettEq2BOList()) {
                    for (TEq3BO eq3 : eq2.gettEq3BOList()) {
                        for (TPortBO port : eq3.gettPortBOList()) {
                            try {
                                Cell cell = null;
                                Row row = null;

                                //header
//                        for (SubEqBO item : datas) {
                                Sheet sheet = workbook.getSheetAt(3);//createSheet("2G");
                                sttEq4 += 1;
                                row = sheet.createRow(rowIndexEq4++);
                                CellStyle style = sheet.getWorkbook().createCellStyle();
                                row.setRowStyle(style);
                                cell = row.createCell(0);
                                cell.setCellValue(sttEq4);
                                cell = row.createCell(1);
                                cell.setCellValue(item.gettEq1BO().getName());

                                cell = row.createCell(2);
                                cell.setCellValue(eq2.getName());

                                cell = row.createCell(3);
                                cell.setCellValue(eq3.getName());
                                cell = row.createCell(4);
                                cell.setCellValue(port.getPortNo());
                                cell = row.createCell(5);
                                cell.setCellValue(port.getNote());
                                cell = row.createCell(6);
                                cell.setCellValue(port.getMtu());

                                cell = row.createCell(7);
                                cell.setCellValue(port.getTransceiver());
                                cell = row.createCell(8);
                                cell.setCellValue(port.getCongsuatphat());
                                cell = row.createCell(9);
                                cell.setCellValue(port.getCongsuatthu());
                                cell = row.createCell(10);
                                cell.setCellValue(port.getNguongthumin());
                                cell = row.createCell(11);
                                cell.setCellValue(port.getNguongthumax());
                                cell = row.createCell(12);
                                cell.setCellValue(port.getSerialNo());
                                cell = row.createCell(13);
                                cell.setCellValue(port.getTportName());
                            } catch (Exception e) {
                                logger.error(e.getMessage(), e);
                            }
                        }
                    }
                }
            }
            int rowIndexEq5 = 1;
            int sttEq5 = 0;
            for (SubEqBO item : datas) {
                for (TEq2BO eq2 : item.gettEq1BO().gettEq2BOList()) {
                    for (TEq3BO eq3 : eq2.gettEq3BOList()) {
                        for (TPortBO port : eq3.gettPortBOList()) {
                            for (TModuleQuangBO quang : port.gettModuleQuangBOList()) {
                                try {
                                    Cell cell = null;
                                    Row row = null;

                                    //header
//                        for (SubEqBO item : datas) {
                                    Sheet sheet = workbook.getSheetAt(4);//createSheet("2G");
                                    sttEq5 += 1;
                                    row = sheet.createRow(rowIndexEq5++);
                                    CellStyle style = sheet.getWorkbook().createCellStyle();
                                    row.setRowStyle(style);
                                    cell = row.createCell(0);
                                    cell.setCellValue(sttEq5);
                                    cell = row.createCell(1);
                                    cell.setCellValue(item.gettEq1BO().getName());

                                    cell = row.createCell(2);
                                    cell.setCellValue(eq2.getName());

                                    cell = row.createCell(3);
                                    cell.setCellValue(eq3.getName());
                                    cell = row.createCell(4);
                                    cell.setCellValue(port.getTportName());
                                    cell = row.createCell(5);
                                    cell.setCellValue(quang.getTransceiverType());
                                    cell = row.createCell(6);
                                    cell.setCellValue(quang.getSerialNo());

                                    cell = row.createCell(7);
                                    cell.setCellValue(quang.getTxPower());
                                    cell = row.createCell(8);
                                    cell.setCellValue(quang.getRxPower());
                                    cell = row.createCell(9);
                                    cell.setCellValue(quang.getName());
                                    cell = row.createCell(10);
                                    cell.setCellValue(quang.getNote());
                                } catch (Exception e) {
                                    logger.error(e.getMessage(), e);
                                }
                            }
                        }
                    }
                }
            }
            result = new File(fileName);
            fos = new FileOutputStream(result);
            workbook.write(fos);
            workbook.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fin.close();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;

    }

}
