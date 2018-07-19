/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.InterfaceBO;
import com.vnpt.media.rims.bean.InterfaceBcBO;
import com.vnpt.media.rims.bean.TNodeBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.Page;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.InterfaceFacade;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import com.vnpt.media.rims.facade.TnodeFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/interface")
public class InterfaceController {

    private static final String REDIRECT = "redirect:/interface/init";
    private static Logger logger = LogManager.getLogger(InterfaceController.class);
    private static final String LIST = "broadband/interface/list";
    private static final String MANE_INTERFACE_TB = "broadband/report/mane_interface_theotb";
    private static final String MANE_INTERFACE_TP = "broadband/report/mane_interface_theotp";
    private static final String VN2_INTERFACE_TB = "broadband/report/vn2_interface_theotb";
    private static final String VN2_INTERFACE_TP = "broadband/report/vn2_interface_theotp";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tnodes", required = false) String tnodes,
            ModelMap mm, HttpServletRequest request) {
        try {
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = InterfaceFacade.countSearch(tnodes);
            Page objPage = new Page();

            int numPerPage = Constants.NUMBER_FOR_PAGING;
            int totalPages = 0;

            if (totalRows % numPerPage == 0) {
                totalPages = (int) totalRows / numPerPage;
            } else {
                totalPages = (int) totalRows / numPerPage + 1;
            }
            if (totalRows == 0) {
                totalPages = 0;
            }

            if (pageInt < 1) {
                pageInt = 1;
                return ("redirect:/interface/init?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/interface/init?page=" + pageInt);
            }
            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<InterfaceBO> list = InterfaceFacade.findAll(String.valueOf(startRow), String.valueOf(endRow), tnodes);
            mm.addAttribute("list", list);
            mm.put("tinhTpId", tinhTpId);
            mm.put("typeId", typeId);
            mm.put("tnodes", tnodes);
            mm.put("startRow", startRow);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return LIST;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String tnodeId = request.getParameter("tnodeId");
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<InterfaceBcBO> datas = null;
            File fileResult = writeInterfaceTp(dataDirectory + "/InterfaceTheoTp.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/findTnodes", method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    public @ResponseBody
    String findTnodes(HttpServletRequest request, @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        List<TNodeBO> list = null;
        try {
            if (tinhTpId != null && !tinhTpId.isEmpty()) {
                TnodeFacade facade = new TnodeFacade();
                list = facade.findTnode(typeId, tinhTpId);
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(list);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @ModelAttribute("tinhThanhPhoLst")
    public List findTinhThanhPho(HttpServletRequest request) {
        List<TinhBO> list = null;
        try {
            ManagerAdminFacade facade = new ManagerAdminFacade();
            list = facade.findAllTinh("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    // cac bao cao ve interface cua thiet bi mane
    @RequestMapping(value = "/preInterfaceTbMane", method = RequestMethod.GET)
    public String preInterfaceTbMane(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "typeId", required = false) String typeId) {
        try {
            typeId = "1,2,3";
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = InterfaceFacade.countInterfaceTb(tinhTpId, typeId);
            Page objPage = new Page();

            int numPerPage = Constants.NUMBER_FOR_PAGING;
            int totalPages = 0;

            if (totalRows % numPerPage == 0) {
                totalPages = (int) totalRows / numPerPage;
            } else {
                totalPages = (int) totalRows / numPerPage + 1;
            }
            if (totalRows == 0) {
                totalPages = 0;
            }

            if (pageInt < 1) {
                pageInt = 1;
                return ("redirect:/interface/preInterfaceTbMane?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/interface/preInterfaceTbMane?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<InterfaceBcBO> list = InterfaceFacade.searchInterfaceTb(startRow, endRow, tinhTpId, typeId);
            mm.addAttribute("list", list);
            mm.put("tinhTpId", tinhTpId);
            mm.put("startRow", startRow);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return MANE_INTERFACE_TB;
    }

    @RequestMapping(value = "/interfaceTbMane", method = RequestMethod.GET)
    public void interfaceTbMane(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String tinhTpId = request.getParameter("tinhTpId");
            String typeId = "1,2,3";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<InterfaceBcBO> datas = InterfaceFacade.interfaceTb(tinhTpId, typeId);
            File fileResult = writeInterfaceTb(dataDirectory + "/InterfaceTheoTb.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private File writeInterfaceTb(String fileTemplate, List<InterfaceBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("InterfaceTheoTb.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 1;
                Cell cell = null;
                Row row = null;
                for (InterfaceBcBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getCode() == null ? "" : it.getCode());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getIp() == null ? "" : it.getIp());

                    cell = row.createCell(2);
                    cell.setCellValue(it.getLoopBack() == 0 ? "" : String.valueOf(it.getLoopBack()));

                    cell = row.createCell(3);
                    cell.setCellValue(it.getEthernet() == 0 ? "" : String.valueOf(it.getEthernet()));

                    cell = row.createCell(4);
                    cell.setCellValue(it.getAdLag() == 0 ? "" : String.valueOf(it.getAdLag()));

                    cell = row.createCell(5);
                    cell.setCellValue(it.getServiceInstance() == 0 ? "" : String.valueOf(it.getServiceInstance()));

                    cell = row.createCell(6);
                    cell.setCellValue(it.getSubInterface() == 0 ? "" : String.valueOf(it.getSubInterface()));

                    cell = row.createCell(7);
                    cell.setCellValue(it.getPropVirtual() == 0 ? "" : String.valueOf(it.getPropVirtual()));

                    cell = row.createCell(8);
                    cell.setCellValue(it.getL3IpVlan() == 0 ? "" : String.valueOf(it.getL3IpVlan()));

                    cell = row.createCell(9);
                    cell.setCellValue(it.getMpls() == 0 ? "" : String.valueOf(it.getMpls()));

                    cell = row.createCell(10);
                    cell.setCellValue(it.getTunnel() == 0 ? "" : String.valueOf(it.getTunnel()));

                    cell = row.createCell(11);
                    cell.setCellValue(it.getMplsTunnel() == 0 ? "" : String.valueOf(it.getMplsTunnel()));

                    cell = row.createCell(12);
                    cell.setCellValue(it.getOther() == 0 ? "" : String.valueOf(it.getOther()));
                }
            }

            fos = new FileOutputStream(result);
            workbook.write(fos);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        return result;
    }

    @RequestMapping(value = "/preInterfaceTpMane", method = RequestMethod.GET)
    public String preInterfaceTpMane(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "typeId", required = false) String typeId) {
        try {
            typeId = "1,2,3";
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = InterfaceFacade.countInterfaceTp(tinhTpId, typeId);
            Page objPage = new Page();

            int numPerPage = Constants.NUMBER_FOR_PAGING;
            int totalPages = 0;

            if (totalRows % numPerPage == 0) {
                totalPages = (int) totalRows / numPerPage;
            } else {
                totalPages = (int) totalRows / numPerPage + 1;
            }
            if (totalRows == 0) {
                totalPages = 0;
            }

            if (pageInt < 1) {
                pageInt = 1;
                return ("redirect:/interface/preInterfaceTpMane?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/interface/preInterfaceTpMane?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<InterfaceBcBO> list = InterfaceFacade.searchInterfaceTp(startRow, endRow, tinhTpId, typeId);
            mm.addAttribute("list", list);
            mm.put("tinhTpId", tinhTpId);
            mm.put("startRow", startRow);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return MANE_INTERFACE_TP;
    }

    @RequestMapping(value = "/interfaceTpMane", method = RequestMethod.GET)
    public void interfaceTpMane(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String tinhTpId = request.getParameter("tinhTpId");
            String typeId = request.getParameter("typeId");
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<InterfaceBcBO> datas = InterfaceFacade.interfaceTp(tinhTpId, typeId);
            File fileResult = writeInterfaceTp(dataDirectory + "/InterfaceTheoTp.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private File writeInterfaceTp(String fileTemplate, List<InterfaceBcBO> datas) {
        File result = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            result = new File("InterfaceTheoTp.xlsx");
            FileInputStream fin = new FileInputStream(fileTemplate);
            workbook = new XSSFWorkbook(fin);
            Sheet sheet = workbook.getSheetAt(0);
            if (datas != null) {
                int rowIndex = 1;
                Cell cell = null;
                Row row = null;
                for (InterfaceBcBO it : datas) {
                    row = sheet.createRow(rowIndex++);

                    cell = row.createCell(0);
                    cell.setCellValue(it.getMaTinh() == null ? "" : it.getMaTinh());

                    cell = row.createCell(1);
                    cell.setCellValue(it.getTenTinhTp() == null ? "" : it.getTenTinhTp());

                    cell = row.createCell(2);
                    cell.setCellValue(it.getLoopBack() == 0 ? "" : String.valueOf(it.getLoopBack()));

                    cell = row.createCell(3);
                    cell.setCellValue(it.getEthernet() == 0 ? "" : String.valueOf(it.getEthernet()));

                    cell = row.createCell(4);
                    cell.setCellValue(it.getAdLag() == 0 ? "" : String.valueOf(it.getAdLag()));

                    cell = row.createCell(5);
                    cell.setCellValue(it.getServiceInstance() == 0 ? "" : String.valueOf(it.getServiceInstance()));

                    cell = row.createCell(6);
                    cell.setCellValue(it.getSubInterface() == 0 ? "" : String.valueOf(it.getSubInterface()));

                    cell = row.createCell(7);
                    cell.setCellValue(it.getPropVirtual() == 0 ? "" : String.valueOf(it.getPropVirtual()));

                    cell = row.createCell(8);
                    cell.setCellValue(it.getL3IpVlan() == 0 ? "" : String.valueOf(it.getL3IpVlan()));

                    cell = row.createCell(9);
                    cell.setCellValue(it.getMpls() == 0 ? "" : String.valueOf(it.getMpls()));

                    cell = row.createCell(10);
                    cell.setCellValue(it.getTunnel() == 0 ? "" : String.valueOf(it.getTunnel()));

                    cell = row.createCell(11);
                    cell.setCellValue(it.getMplsTunnel() == 0 ? "" : String.valueOf(it.getMplsTunnel()));

                    cell = row.createCell(12);
                    cell.setCellValue(it.getOther() == 0 ? "" : String.valueOf(it.getOther()));
                }
            }

            fos = new FileOutputStream(result);
            workbook.write(fos);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        return result;
    }
    // cac bao cao ve vn2 cua interface    

    @RequestMapping(value = "/preInterfaceTbVn2", method = RequestMethod.GET)
    public String preInterfaceTbVn2(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "typeId", required = false) String typeId) {
        try {
            typeId = "7,8,9,10,11";
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = InterfaceFacade.countInterfaceTb(tinhTpId, typeId);
            Page objPage = new Page();

            int numPerPage = Constants.NUMBER_FOR_PAGING;
            int totalPages = 0;

            if (totalRows % numPerPage == 0) {
                totalPages = (int) totalRows / numPerPage;
            } else {
                totalPages = (int) totalRows / numPerPage + 1;
            }
            if (totalRows == 0) {
                totalPages = 0;
            }

            if (pageInt < 1) {
                pageInt = 1;
                return ("redirect:/interface/preInterfaceTbVn2?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/interface/preInterfaceTbVn2?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<InterfaceBcBO> list = InterfaceFacade.searchInterfaceTb(startRow, endRow, tinhTpId, typeId);
            mm.addAttribute("list", list);
            mm.put("tinhTpId", tinhTpId);
            mm.put("startRow", startRow);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return VN2_INTERFACE_TB;
    }

    @RequestMapping(value = "/preInterfaceTpVn2", method = RequestMethod.GET)
    public String preInterfaceTpVn2(ModelMap mm, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "typeId", required = false) String typeId) {
        try {
            typeId = "7,8,9,10,11";
            page = page == null ? "1" : page;
            Integer pageInt = Integer.parseInt(page);
            int totalRows = InterfaceFacade.countInterfaceTp(tinhTpId, typeId);
            Page objPage = new Page();

            int numPerPage = Constants.NUMBER_FOR_PAGING;
            int totalPages = 0;

            if (totalRows % numPerPage == 0) {
                totalPages = (int) totalRows / numPerPage;
            } else {
                totalPages = (int) totalRows / numPerPage + 1;
            }
            if (totalRows == 0) {
                totalPages = 0;
            }

            if (pageInt < 1) {
                pageInt = 1;
                return ("redirect:/interface/preInterfaceTpVn2?page=" + pageInt);
            } else if (pageInt > totalPages && totalPages > 0) {
                pageInt = totalPages;
                return ("redirect:/interface/preInterfaceTpVn2?page=" + pageInt);
            }

            objPage.setTotalPages(totalPages);
            objPage.setTotalRows(totalRows);
            objPage.setDestPage(pageInt);
            objPage.setDirection(1);
            mm.addAttribute("pageInfo", objPage);

            int startRow = 0, endRow = 0;
            if (pageInt > 1) {
                startRow = ((pageInt - 1) * (numPerPage) + 1);
                endRow = (pageInt * (numPerPage));
            } else if (pageInt == 1) {
                startRow = 1;
                endRow = Constants.NUMBER_FOR_PAGING;
            }
            List<InterfaceBcBO> list = InterfaceFacade.searchInterfaceTp(startRow, endRow, tinhTpId, typeId);
            mm.addAttribute("list", list);
            mm.put("tinhTpId", tinhTpId);
            mm.put("startRow", startRow);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return VN2_INTERFACE_TP;
    }

    @RequestMapping(value = "/interfaceTbVn2", method = RequestMethod.GET)
    public void interfaceTbVn2(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String tinhTpId = request.getParameter("tinhTpId");
            String typeId = "7,8,9,10,11";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<InterfaceBcBO> datas = InterfaceFacade.interfaceTb(tinhTpId, typeId);
            File fileResult = writeInterfaceTb(dataDirectory + "/InterfaceTheoTb.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/interfaceTpVn2", method = RequestMethod.GET)
    public void interfaceTpVn2(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String tinhTpId = request.getParameter("tinhTpId");
            String typeId = request.getParameter("typeId");
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            List<InterfaceBcBO> datas = InterfaceFacade.interfaceTp(tinhTpId, typeId);
            File fileResult = writeInterfaceTp(dataDirectory + "/InterfaceTheoTp.xlsx", datas);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileResult.getName());
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
