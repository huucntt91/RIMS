/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.broadband;

import com.vnpt.media.rims.bean.DongTbiBO;
import com.vnpt.media.rims.bean.ExportExcelL2Switch;
import com.vnpt.media.rims.bean.ExportExcelLKNAccessTheoTb;
import com.vnpt.media.rims.bean.ExportExcelLKNAccessTheoTinh;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.TnodeTypeBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.controller.googleMap.MapDetailController;
import com.vnpt.media.rims.facade.CategoriesFacade;
import com.vnpt.media.rims.facade.DongThietBiFacade;
import com.vnpt.media.rims.facade.JasperFacade;
import com.vnpt.media.rims.facade.TNodeTypeFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author VNP
 */
@Controller
@RequestMapping(value = "/jasper")
public class JasperController {

    private static Logger logger = LogManager.getLogger(JasperController.class);

    @ModelAttribute("tnodeTypeList")
    public List findTNodeType() {
        List<TnodeTypeBO> list = null;
        try {
            list = TNodeTypeFacade.getInstance().findAll(null, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String action = request.getParameter("action");

            if (action.equals("chungloaitb")) {
//            bc chung loai thiet bi
                String jasperFileName = "report1.jrxml";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("name", "Lạc Tân");
                JasperPrint jp = facade.export(jasperFileName, params);
                xls(response, jp, "test");
                return;
            }
            String jasperFileName = "report1.jrxml";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", "Lạc Tân");
            JasperPrint jp = facade.export(jasperFileName, params);
            xls(response, jp, "test");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportBroadBand", method = RequestMethod.GET)
    public void exportBroadBand(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String provinceId,
            @RequestParam(value = "quanHuyenId", required = false) String districtId,
            @RequestParam(value = "phuongXaId", required = false) String wardsId,
            @RequestParam(value = "listTnodeTypeId", required = false) String listTnodeTypeId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            JasperFacade facade = new JasperFacade();
            String exportFileName = "export_mane";
            String jasperFileName = "export_mane.jrxml";
            if (typeId.equalsIgnoreCase("1")) {
                jasperFileName = "export_agg.jrxml";
                exportFileName = "export_agg";
            } else if (typeId.equalsIgnoreCase("2")) {
                jasperFileName = "export_upe.jrxml";
                exportFileName = "export_upe";
            }
            if (typeId.equalsIgnoreCase("3")) {
                jasperFileName = "export_npe.jrxml";
                exportFileName = "export_npe";
            }
            if (typeId.equalsIgnoreCase("4")) {
                jasperFileName = "export_dslam.jrxml";
                exportFileName = "export_dslam";
            }
            if (typeId.equalsIgnoreCase("5")) {
                jasperFileName = "export_switch.jrxml";
                exportFileName = "export_switch";
            }
            if (typeId.equalsIgnoreCase("6")) {
                jasperFileName = "export_gpon.jrxml";
                exportFileName = "export_gpon";
            }
            if (typeId.equalsIgnoreCase("7")) {
                jasperFileName = "export_prouter.jrxml";
                exportFileName = "export_prouter";
            }
            if (typeId.equalsIgnoreCase("8")) {
                jasperFileName = "export_prouter.jrxml";
                exportFileName = "export_pe";
            }
            if (typeId.equalsIgnoreCase("9")) {
                jasperFileName = "export_prouter.jrxml";
                exportFileName = "export_asbr";
            }
            if (typeId.equalsIgnoreCase("10")) {
                jasperFileName = "export_prouter.jrxml";
                exportFileName = "export_nix";
            }
            if (typeId.equalsIgnoreCase("11")) {
                jasperFileName = "export_prouter.jrxml";
                exportFileName = "export_bras";
            }
            if (typeId.equalsIgnoreCase("12")) {
                jasperFileName = "export_chitietcard.jrxml";
                exportFileName = "export_chitietcard";
                params.put("p_province_id", provinceId.replace("null", ""));
                params.put("p_list_tnode_type_id", listTnodeTypeId);
            }
            if (khuvucId != null) {
                khuvucId = khuvucId.replace("null", "");
            }
            if (provinceId != null) {
                provinceId = provinceId.replace("null", "");
            }
            if (districtId != null) {
                districtId = districtId.replace("null", "");
            }
            if (wardsId != null) {
                wardsId = wardsId.replace("null", "");
            }

            params.put("p_tnode_type_id", typeId);
            params.put("p_tnode_code", code);
            params.put("p_tnode_name", name);
            params.put("p_khu_vuc_id", khuvucId);
            params.put("p_province_id", provinceId);
            params.put("p_district_id", districtId);
            params.put("p_wards_id", wardsId);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportIplayer3", method = RequestMethod.GET)
    public void exportIplayer3(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tnode_id", required = false) String tnode_id) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "exportIplayer3";
            String jasperFileName = "export_iplayer3.jrxml";
            if (tnode_id != null) {
                tnode_id = tnode_id.replace("null", "");
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("P_TNODE_ID", tnode_id.trim());
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportVlan", method = RequestMethod.GET)
    public void exportVlan(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tnode_id", required = false) String tnode_id) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "exportVlan";
            String jasperFileName = "export_vlan.jrxml";
            if (tnode_id != null) {
                tnode_id = tnode_id.replace("null", "");
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("P_TNODE_ID", tnode_id.trim());
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportEvc", method = RequestMethod.GET)
    public void exportEvc(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tnode_id", required = false) String tnode_id) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "exportEvc";
            String jasperFileName = "export_evc.jrxml";
            if (tnode_id != null) {
                tnode_id = tnode_id.replace("null", "");
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("P_TNODE_ID", tnode_id.trim());
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportInterface", method = RequestMethod.GET)
    public void exportInterface(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "tnode_id", required = false) String tnode_id) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "exportInterface";
            String jasperFileName = "export_interface.jrxml";
            if (tnode_id != null) {
                tnode_id = tnode_id.replace("null", "");
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("P_TNODE_ID", tnode_id.trim());
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

//    trunglk_start
    @RequestMapping(value = "/exportHlr", method = RequestMethod.GET)
    public void exportHlr(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "HLR";
            String jasperFileName = "HLR.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportHss", method = RequestMethod.GET)
    public void exportHss(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "HSS";
            String jasperFileName = "HSS.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportIms", method = RequestMethod.GET)
    public void exportIms(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "IMS";
            String jasperFileName = "IMS.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportMgw", method = RequestMethod.GET)
    public void exportMgw(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "MGW";
            String jasperFileName = "MGW.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportMsc", method = RequestMethod.GET)
    public void exportMsc(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "MSC";
            String jasperFileName = "MSC.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportMss", method = RequestMethod.GET)
    public void exportMss(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "MSS";
            String jasperFileName = "MSS.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportNgn", method = RequestMethod.GET)
    public void exportNgn(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "NGN";
            String jasperFileName = "NGN.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportStp", method = RequestMethod.GET)
    public void exportStp(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "STP";
            String jasperFileName = "STP.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportTss", method = RequestMethod.GET)
    public void exportTss(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "TSS";
            String jasperFileName = "TSS.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
//    ps_core_start

    @RequestMapping(value = "/exportSgsn", method = RequestMethod.GET)
    public void exportSgsn(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "SGSN";
            String jasperFileName = "SGSN.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportGgsn", method = RequestMethod.GET)
    public void exportGgsn(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "GGSN";
            String jasperFileName = "GGSN.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportMme", method = RequestMethod.GET)
    public void exportMme(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "MME";
            String jasperFileName = "MME.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportPgw", method = RequestMethod.GET)
    public void exportPgw(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "PGW";
            String jasperFileName = "PGW.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportSmsc", method = RequestMethod.GET)
    public void exportSmsc(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "SMSC";
            String jasperFileName = "SMSC.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportSmpp", method = RequestMethod.GET)
    public void exportSmpp(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "SMPP";
            String jasperFileName = "SMPP.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportDsr", method = RequestMethod.GET)
    public void exportDsr(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "DSR";
            String jasperFileName = "DSR.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportMca", method = RequestMethod.GET)
    public void exportMca(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "MCA";
            String jasperFileName = "MCA.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportCrbt", method = RequestMethod.GET)
    public void exportCrbt(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "CRBT";
            String jasperFileName = "CRBT.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportUssd", method = RequestMethod.GET)
    public void exportUssd(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "USSD";
            String jasperFileName = "USSD.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportDns", method = RequestMethod.GET)
    public void exportDns(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "DNS";
            String jasperFileName = "DNS.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportRc", method = RequestMethod.GET)
    public void exportRc(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "RC";
            String jasperFileName = "RC.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportSdp", method = RequestMethod.GET)
    public void exportSdp(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "SDP";
            String jasperFileName = "SDP.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportFda", method = RequestMethod.GET)
    public void exportFda(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "FDA";
            String jasperFileName = "FDA.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportSapc", method = RequestMethod.GET)
    public void exportSapc(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "SAPC";
            String jasperFileName = "SAPC.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
//    ps_core_end

    @RequestMapping(value = "/exportTramDuAn", method = RequestMethod.GET)
    public void exportTramDuAn(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "maTramDuAn", required = false) String maTramDuAn,
            @RequestParam(value = "tenTramDuAn", required = false) String tenTramDuAn) {
        try {
            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            String tinhTpId = String.join(",", tinhManager);
            JasperFacade facade = new JasperFacade();
            String exportFileName = "TramKeHoach";
            String jasperFileName = "TramKeHoach.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("prs_tinhTp_Id", tinhTpId);
            params.put("prs_ma_tram_da", maTramDuAn);
            params.put("prs_ten_tram_da", tenTramDuAn);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportBuilding", method = RequestMethod.GET)
    public void exportBuilding(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId,
            @RequestParam(value = "phuongXaId", required = false) String phuongXaId) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "BUILDING";
            String jasperFileName = "BUILDING.jrxml";

            if (khuvucId != null) {
                khuvucId = khuvucId.replace("null", "");
            }
            if (tinhTpId != null) {
                tinhTpId = tinhTpId.replace("null", "");
            }
            if (quanHuyenId != null) {
                quanHuyenId = quanHuyenId.replace("null", "");
            }
            if (phuongXaId != null) {
                phuongXaId = phuongXaId.replace("null", "");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("prs_name", name);
            params.put("pri_khuvuc_id", khuvucId);
            params.put("pri_tinh_id", tinhTpId);
            params.put("pri_quan_id", quanHuyenId);
            params.put("pri_phuong_id", phuongXaId);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportPhutro", method = RequestMethod.GET)
    public void exportPhutro(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "PHUTRO";
            String jasperFileName = "PHUTRO.jrxml";

            if (khuvucId != null) {
                khuvucId = khuvucId.replace("null", "");
            }
            if (tinhTpId != null) {
                tinhTpId = tinhTpId.replace("null", "");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("prs_name", name);
            params.put("pri_khuvuc_id", khuvucId);
            params.put("pri_tinh_id", tinhTpId);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportQuyHoach", method = RequestMethod.GET)
    public void exportQuyHoach(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "QUYHOACH";
            String jasperFileName = "QUYHOACH.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportQuyHoachTinh", method = RequestMethod.GET)
    public void exportQuyHoachTinh(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "QUYHOACHTINH";
            String jasperFileName = "QUYHOACHTINH.jrxml";

            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("name", name);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportNodes", method = RequestMethod.GET)
    public void exportNodes(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "thietBiId", required = false) String thietBiId,
            @RequestParam(value = "neTypeId", required = false) String neTypeId,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId,
            @RequestParam(value = "quanHuyenId", required = false) String quanHuyenId,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "phuongXaId", required = false) String phuongXaId) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "BTS";
            String jasperFileName = "BTS.jrxml";
            if (neTypeId != null && !neTypeId.equals("")) {
                if (neTypeId.equalsIgnoreCase("2")) {
                    jasperFileName = "BTS.jrxml";
                    exportFileName = "BTS";
                } else if (neTypeId.equalsIgnoreCase("3")) {
                    jasperFileName = "NODEB.jrxml";
                    exportFileName = "NODEB";
                }
                if (neTypeId.equalsIgnoreCase("8")) {
                    jasperFileName = "ENODEB.jrxml";
                    exportFileName = "ENODEB";
                }
                if (neTypeId.equalsIgnoreCase("5")) {
                    jasperFileName = "CELL_2G.jrxml";
                    exportFileName = "CELL_2G";
                }
                if (neTypeId.equalsIgnoreCase("6")) {
                    jasperFileName = "CELL_3G.jrxml";
                    exportFileName = "CELL_3G";
                }
                if (neTypeId.equalsIgnoreCase("7")) {
                    jasperFileName = "CELL_4G.jrxml";
                    exportFileName = "CELL_4G";
                }
                if (neTypeId.equalsIgnoreCase("11")) {
                    jasperFileName = "BSC_RNC.jrxml";
                    exportFileName = "BSC_RNC";
                }
            } else {
                neTypeId = "2";
            }

            if (khuvucId != null) {
                khuvucId = khuvucId.replace("null", "");
            }

            String[] tinhManager = (String[]) request.getSession().getAttribute(Constants.PROVINCE_KEY);
            tinhTpId = tinhTpId == null ? String.join(",", tinhManager) : tinhTpId;
            if (quanHuyenId != null) {
                quanHuyenId = quanHuyenId.replace("null", "");
            }
            if (phuongXaId != null) {
                phuongXaId = phuongXaId.replace("null", "");
            }
            if (thietBiId != null) {
                thietBiId = thietBiId.replace("null", "");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("pri_vender_id", thietBiId);
            params.put("prs_name", code);
            params.put("pri_ne_id", neTypeId);
            params.put("pri_list_khuvuc_id", khuvucId);
            params.put("pri_list_tinh_id", tinhTpId);
            params.put("pri_list_quan_id", quanHuyenId);
            params.put("pri_list_phuong_id", phuongXaId);
            params.put("prn_status_list", status);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/exportAnten", method = RequestMethod.GET)
    public void exportAnten(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "khuvucId", required = false) String khuvucId,
            @RequestParam(value = "tinhTpId", required = false) String tinhTpId) {
        try {
            JasperFacade facade = new JasperFacade();
            String exportFileName = "ANTEN";
            String jasperFileName = "ANTEN.jrxml";

            if (khuvucId != null) {
                khuvucId = khuvucId.replace("null", "");
            }
            if (tinhTpId != null) {
                tinhTpId = tinhTpId.replace("null", "");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("prs_name", code);
            params.put("prs_khuvuc_id", khuvucId);
            params.put("prs_tinhtp_id", tinhTpId);
            JasperPrint jp = facade.export(jasperFileName, params);
            xlsx(response, jp, exportFileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
//    trunglk_end

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
            JasperPrint xlsPrint, String fileName) throws JRException, IOException {
        JRXlsxExporter xlsxExporter = new JRXlsxExporter();

        xlsxExporter.setExporterInput(new SimpleExporterInput(xlsPrint));
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

    @RequestMapping(value = "/init/l2switch", method = RequestMethod.GET)
    public String initFormL2Switch(@RequestParam(value = "tnodeTypeId", required = false) Long tnodeTypeId,
            @RequestParam(value = "excel", required = false) String excel,
            HttpServletRequest request,
            HttpServletResponse response) {
        JasperFacade facade = new JasperFacade();
        String fileName = "DanhsachTBL2Switch.xlsx";
        if (excel != null && excel.equalsIgnoreCase("excel")) {
            List<TnodeTypeBO> list = TNodeTypeFacade.getInstance().findAll(tnodeTypeId.toString(), null);
            if (list != null && list.size() > 0) {
                fileName = "DanhsachTB_" + list.get(0).getName() + ".xlsx";
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileTemplate = new File(dataDirectory + File.separator + fileName);

            List<DongTbiBO> dongthietbiList
                    = DongThietBiFacade.getInstance().findAll(null, null, null, null, null);
            List<ExportExcelL2Switch> temp = facade.exportExcelL2Switch(tnodeTypeId);
            File fileResult = writeExcelL2switch(fileTemplate, dongthietbiList, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
        }
        return "broadband/report/list";
    }

    @RequestMapping(value = "/init/chungloaitb", method = RequestMethod.GET)
    public String initChungloaiTB(@RequestParam(value = "tnodeTypeId", required = false) Long tnodeTypeId,
            @RequestParam(value = "excel", required = false) String excel,
            HttpServletRequest request,
            HttpServletResponse response) {
        JasperFacade facade = new JasperFacade();

        String fileName = "DanhsachTBL2Switch.xlsx";
        if (excel != null && excel.equalsIgnoreCase("excel")) {
            List<TnodeTypeBO> list = TNodeTypeFacade.getInstance().findAll(tnodeTypeId.toString(), null);
            if (list != null && list.size() > 0) {
                fileName = "DanhsachTB_" + list.get(0).getName() + ".xlsx";
            }

            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileTemplate = new File(dataDirectory + File.separator + fileName);

            List<DongTbiBO> dongthietbiList
                    = DongThietBiFacade.getInstance().findAll(null, null, null, null, null);
            List<ExportExcelL2Switch> temp = facade.exportExcelL2Switch(tnodeTypeId);
            File fileResult = writeExcelL2switch(fileTemplate, dongthietbiList, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
        }
        return "broadband/report/list_chungloaithietbi";
    }

    private File writeExcelL2switch(File fileTemplate, List<DongTbiBO> dongthietbiList, List<ExportExcelL2Switch> temp) {
        try {
            Workbook workbook = null;
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("DATA");//createSheet("2G");
            int rowIndex = 0;
            Font font = sheet.getWorkbook().createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBold(true);
            font.setItalic(false);
            Row row = sheet.createRow(rowIndex++);
            CellStyle style2 = sheet.getWorkbook().createCellStyle();
            row.setRowStyle(style2);
            int colNum = 1;
            for (DongTbiBO field : dongthietbiList) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue((String) field.getTenDongTbi());
            }
            for (int i = 0; i < temp.size(); i++) {
                row = sheet.createRow(rowIndex++);
                colNum = 0;
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTenTinh());
                for (DongTbiBO field : dongthietbiList) {
                    cell = row.createCell(colNum++);
                    cell.setCellValue(temp.get(i).getData().get(field.getTenDongTbi()) == null ? "0" : temp.get(i).getData().get(field.getTenDongTbi()).toString());
                }
            }
            File file = new File("fdafd.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
//
//    @RequestMapping(value = "/exportExcelL2Switch", method = RequestMethod.GET)
//    public void exportExcelL2Switch(@RequestParam(value = "tnodeTypeId", required = false) Long tnodeTypeId,
//            HttpServletRequest request,
//            HttpServletResponse response)  {
//        JasperFacade facade = new JasperFacade();
////        String jasperFileName = "report1.jrxml";
////        Map<String, Object> params = new HashMap<String, Object>();
////        params.put("name", "Lạc Tân");
////        JasperPrint jp = facade.export(jasperFileName, params);
////        xls(response, jp, "test");
//        
//        List<ExportExcelL2Switch> temp = facade.exportExcelL2Switch(tnodeTypeId);
//
//        try {
//            String fileName = "C:\\Documents and Settings\\abhijit\\JasperSimpleReport.jasper";
//            String outFileNamePDF = "c:\\JasperSimpleReport.pdf";
//            HashMap hm = new HashMap();
//            
//            hm.put("", "Hello Report");
//            //JasperReport manager=JasperManager.compileReport(fileName);
////            JasperFillManager.fillReportToFile(fileName, outFileNamePDF, hm);
////            JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
//            //parameter used for the destined file.
////            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
////                    outFileNamePDF);
////            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            //export to .pdf
////            exporter.exportReport();\
//
////            JRDataSource ds = new JRBeanCollectionDataSource(temp);
//            InputStream resourceAsStream = getClass().getResourceAsStream("/l2switch.jrxml");
//
//            Map<String, Object> params = new HashMap<String, Object>();
//            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(temp);
//            for (int i = 0; i < temp.size(); i++) {
//                
//
//            }
//            params.put("ItemDataSource", itemsJRBean);
//            JasperReport jr = JasperCompileManager.compileReport(resourceAsStream);
//            JasperPrint jasperPrint
//                    = JasperFillManager.fillReport(jr, params, new JREmptyDataSource());
//            
//            
//            xls(response, jasperPrint, "test");
//        } catch (JRException e) {
//            logger.error(e.getMessage(), e);
//        }
//    }

    @RequestMapping(value = "/init/mane/chitietcard", method = RequestMethod.GET)
    public String initChiTietCard(@RequestParam(value = "tinhTpId", required = false) Long tinhTpId,
            @RequestParam(value = "excel", required = false) String excel, ModelMap mm,
            HttpServletRequest request,
            HttpServletResponse response) {
        JasperFacade facade = new JasperFacade();
        String fileName = "DanhsachTBL2Switch.xlsx";
        CategoriesFacade cate = new CategoriesFacade();
        List<TinhBO> tinhTPList = cate.findAllTinh(null);
        mm.put("tinhTPList", tinhTPList);
        mm.put("tinhTpId", tinhTpId);
        return "broadband/report/list_chitietcard";
    }

    @RequestMapping(value = "/init/ketnoiaccesstheotinh", method = RequestMethod.GET)
    public String initKetNoiAccessTheoTinh(@RequestParam(value = "tinhTPId", required = false) Long tinhTPId,
            @RequestParam(value = "excel", required = false) String excel, ModelMap mm,
            HttpServletRequest request,
            HttpServletResponse response) {
        JasperFacade facade = new JasperFacade();
        String fileName = "DanhsachTBL2Switch.xlsx";
        CategoriesFacade cate = new CategoriesFacade();
        List<TinhBO> tinhTPList = cate.findAllTinh(null);
        mm.put("tinhTPList", tinhTPList);
        mm.put("tinhTPId", tinhTPId);
        if (excel != null && excel.equalsIgnoreCase("excel")) {
            fileName = "KetNoiAccessTheoTinh_" + tinhTPId + ".xlsx";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            Map<String, List<ExportExcelLKNAccessTheoTinh>> temp = facade.exportExcelKetNoiAccessTheoTinh(tinhTPId);
            File fileResult = writeExcelLKetNoiAccessTheoTinh(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
        }
        return "broadband/report/list_ketnoiaccesstheotinh";
    }

    @RequestMapping(value = "/init/vn2/ketnoiaccesstheotinh", method = RequestMethod.GET)
    public String initKetNoiAccessTheoTinhVn2(@RequestParam(value = "tinhTPId", required = false) Long tinhTPId,
            @RequestParam(value = "excel", required = false) String excel, ModelMap mm,
            HttpServletRequest request,
            HttpServletResponse response) {
        JasperFacade facade = new JasperFacade();
        String fileName = "DanhsachTBL2Switch.xlsx";
        CategoriesFacade cate = new CategoriesFacade();
        List<TinhBO> tinhTPList = cate.findAllTinh(null);
        mm.put("tinhTPList", tinhTPList);
        mm.put("tinhTPId", tinhTPId);
        if (excel != null && excel.equalsIgnoreCase("excel")) {
            fileName = "VN2KetNoiAccessTheoTinh_" + tinhTPId + ".xlsx";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            Map<String, List<ExportExcelLKNAccessTheoTinh>> temp = facade.vn2ExportExcelKetNoiAccessTheoTinh(tinhTPId);
            File fileResult = vn2WriteExcelLKetNoiAccessTheoTinh(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
        }
        return "broadband/report/list_ketnoiaccesstheotinh_vn2";
    }

    @RequestMapping(value = "/init/ketnoiaccesstheotb", method = RequestMethod.GET)
    public String initKetNoiAccessTheoTb(
            @RequestParam(value = "excel", required = false) String excel, ModelMap mm,
            HttpServletRequest request,
            HttpServletResponse response) {
        JasperFacade facade = new JasperFacade();
        String fileName = "DanhsachTBL2Switch.xlsx";
        CategoriesFacade cate = new CategoriesFacade();

        if (excel != null && excel.equalsIgnoreCase("excel")) {
            fileName = "KetNoiAccessTheoTb.xlsx";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            List<ExportExcelLKNAccessTheoTb> temp = facade.exportExcelKetNoiAccessTheoTb();
            File fileResult = writeExcelLKetNoiAccessTheoTb(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
        }
        return "broadband/report/list_ketnoiaccesstheotb";
    }

    @RequestMapping(value = "/init/vn2/ketnoiaccesstheotb", method = RequestMethod.GET)
    public String initKetNoiAccessTheoTbVn2(
            @RequestParam(value = "excel", required = false) String excel, ModelMap mm,
            HttpServletRequest request,
            HttpServletResponse response) {
        JasperFacade facade = new JasperFacade();
        String fileName = "DanhsachTBL2Switch.xlsx";
        CategoriesFacade cate = new CategoriesFacade();

        if (excel != null && excel.equalsIgnoreCase("excel")) {
            fileName = "VN2KetNoiAccessTheoTb.xlsx";
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/excel-templates/");
            File fileTemplate = new File(dataDirectory + File.separator + fileName);
            List<ExportExcelLKNAccessTheoTb> temp = facade.vn2ExportExcelKetNoiAccessTheoTb();
            File fileResult = vn2WriteExcelLKetNoiAccessTheoTb(fileTemplate, temp);
            if (fileResult.exists()) {
                response.setContentType("application/excel");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    FileCopyUtils.copy(new BufferedInputStream(new FileInputStream(fileResult)), response.getOutputStream());
                    response.getOutputStream().flush();

                } catch (IOException ex) {
                }
            }
        }
        return "broadband/report/list_ketnoiaccesstheotb_vn2";
    }

    private File writeExcelLKetNoiAccessTheoTinh(File fileTemplate, Map<String, List<ExportExcelLKNAccessTheoTinh>> temp) {
        try {
            Workbook workbook = null;
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("DATA");//createSheet("2G");
            int rowIndex = 0;
            Font font = sheet.getWorkbook().createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBold(true);
            font.setItalic(false);
            Row row = sheet.createRow(rowIndex++);
            CellStyle style2 = sheet.getWorkbook().createCellStyle();
            row.setRowStyle(style2);
            int colNum = 0;
//            for (DongTbiBO field : dongthietbiList) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue("Thiết bị");
            cell = row.createCell(colNum++);
            cell.setCellValue("DSLam");
            cell = row.createCell(colNum++);
            cell.setCellValue("L2SW");
            cell = row.createCell(colNum++);
            cell.setCellValue("GPON");
            cell = row.createCell(colNum++);
            cell.setCellValue("NODEB");
            cell = row.createCell(colNum++);
            cell.setCellValue("Tổng cộng");
//            }
            CellStyle style = workbook.createCellStyle();//Create style
            Font font1 = workbook.createFont();//Create font
            font1.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            style.setFont(font1);//set it to bold
            int tempTotal1 = 0;
            int tempTotal2 = 0;
            int tempTotal3 = 0;
            int tempTotal4 = 0;
            int tempTotal5 = 0;
            for (String key : temp.keySet()) {
                List<ExportExcelLKNAccessTheoTinh> listItem = temp.get(key);
                row = sheet.createRow(rowIndex++);
                colNum = 0;
                cell = row.createCell(colNum++);
                cell.setCellValue("RING:" + key);
                cell.setCellStyle(style);//Set the style
                int total1 = 0;
                int total2 = 0;
                int total3 = 0;
                int total4 = 0;
                int total5 = 0;
                for (int i = 0; i < listItem.size(); i++) {
                    row = sheet.createRow(rowIndex++);
                    colNum = 0;
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTnodeCode());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal1());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal2());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal3());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal4());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal1()
                            + listItem.get(i).getTotal2() + listItem.get(i).getTotal3()
                            + listItem.get(i).getTotal4());
                    total1 += listItem.get(i).getTotal1();
                    total2 += listItem.get(i).getTotal2();
                    total3 += listItem.get(i).getTotal3();
                    total4 += listItem.get(i).getTotal4();
                    total5 += (listItem.get(i).getTotal1()
                            + listItem.get(i).getTotal2() + listItem.get(i).getTotal3()
                            + listItem.get(i).getTotal4());
                }

                row = sheet.createRow(rowIndex++);
                colNum = 1;
                cell = row.createCell(colNum++);
                cell.setCellValue(total1);
                cell.setCellStyle(style);//Set the style
                cell = row.createCell(colNum++);
                cell.setCellValue(total2);
                cell.setCellStyle(style);//Set the style
                cell = row.createCell(colNum++);
                cell.setCellValue(total3);
                cell.setCellStyle(style);//Set the style
                cell = row.createCell(colNum++);
                cell.setCellValue(total4);
                cell.setCellStyle(style);//Set the style
                cell = row.createCell(colNum++);
                cell.setCellValue(total5);
                cell.setCellStyle(style);//Set the style
                tempTotal1 += total1;
                tempTotal2 += total2;
                tempTotal3 += total3;
                tempTotal4 += total4;
                tempTotal5 += total5;
            }
            row = sheet.createRow(rowIndex++);
            colNum = 1;
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal1);
            cell.setCellStyle(style);//Set the style
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal2);
            cell.setCellStyle(style);//Set the style
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal3);
            cell.setCellStyle(style);//Set the style
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal4);
            cell.setCellStyle(style);//Set the style
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal5);
            cell.setCellStyle(style);//Set the style
            File file = new File("fdafd.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private File writeExcelLKetNoiAccessTheoTb(File fileTemplate, List<ExportExcelLKNAccessTheoTb> temp) {
        try {
            Workbook workbook = null;
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("DATA");//createSheet("2G");
            int rowIndex = 0;
            Font font = sheet.getWorkbook().createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBold(true);
            font.setItalic(false);
            Row row = sheet.createRow(rowIndex++);
            CellStyle style2 = sheet.getWorkbook().createCellStyle();
            row.setRowStyle(style2);
            int colNum = 0;
//            for (DongTbiBO field : dongthietbiList) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue("Mã Tỉnh/TP");
            cell = row.createCell(colNum++);
            cell.setCellValue("Tỉnh/TP");
            cell = row.createCell(colNum++);
            cell.setCellValue("Tổng");
            cell = row.createCell(colNum++);
            cell.setCellValue("DSLAM");
            cell = row.createCell(colNum++);
            cell.setCellValue("L2SW");
            cell = row.createCell(colNum++);
            cell.setCellValue("GPON");
            cell = row.createCell(colNum++);
            cell.setCellValue("NODEB");
//            }
            CellStyle style = workbook.createCellStyle();//Create style
            Font font1 = workbook.createFont();//Create font
            font1.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            style.setFont(font1);//set it to bold            

            for (int i = 0; i < temp.size(); i++) {
                row = sheet.createRow(rowIndex++);
                colNum = 0;
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getMaTinhTP());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTenTinhTP());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal1()
                        + temp.get(i).getTotal2() + temp.get(i).getTotal3()
                        + temp.get(i).getTotal4());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal1());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal2());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal3());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal4());

            }

            File file = new File("fdafd.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private File vn2WriteExcelLKetNoiAccessTheoTinh(File fileTemplate, Map<String, List<ExportExcelLKNAccessTheoTinh>> temp) {
        try {
            Workbook workbook = null;
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("DATA");//createSheet("2G");
            int rowIndex = 0;
            Font font = sheet.getWorkbook().createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBold(true);
            font.setItalic(false);
            Row row = sheet.createRow(rowIndex++);
            CellStyle style2 = sheet.getWorkbook().createCellStyle();
            row.setRowStyle(style2);
            int colNum = 0;
//            for (DongTbiBO field : dongthietbiList) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue("Thiết bị");
            cell = row.createCell(colNum++);
            cell.setCellValue("AGG");
            cell = row.createCell(colNum++);
            cell.setCellValue("UPE");
            cell = row.createCell(colNum++);
            cell.setCellValue("NPE");
            cell = row.createCell(colNum++);
            cell.setCellValue("NODEB");
            cell = row.createCell(colNum++);
            cell.setCellValue("Tổng cộng");
//            }
            CellStyle style = workbook.createCellStyle();//Create style
            Font font1 = workbook.createFont();//Create font
            font1.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            style.setFont(font1);//set it to bold
            int tempTotal1 = 0;
            int tempTotal2 = 0;
            int tempTotal3 = 0;
            int tempTotal4 = 0;
            int tempTotal5 = 0;
            for (String key : temp.keySet()) {
                List<ExportExcelLKNAccessTheoTinh> listItem = temp.get(key);
                row = sheet.createRow(rowIndex++);
                colNum = 0;
                cell = row.createCell(colNum++);
                cell.setCellValue("RING:" + key);
                cell.setCellStyle(style);//Set the style
                int total1 = 0;
                int total2 = 0;
                int total3 = 0;
                int total4 = 0;
                int total5 = 0;
                for (int i = 0; i < listItem.size(); i++) {
                    row = sheet.createRow(rowIndex++);
                    colNum = 0;
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTnodeCode());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal1());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal2());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal3());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal4());
                    cell = row.createCell(colNum++);
                    cell.setCellValue(listItem.get(i).getTotal1()
                            + listItem.get(i).getTotal2() + listItem.get(i).getTotal3()
                            + listItem.get(i).getTotal4());
                    total1 += listItem.get(i).getTotal1();
                    total2 += listItem.get(i).getTotal2();
                    total3 += listItem.get(i).getTotal3();
                    total4 += listItem.get(i).getTotal4();
                    total5 += (listItem.get(i).getTotal1()
                            + listItem.get(i).getTotal2() + listItem.get(i).getTotal3()
                            + listItem.get(i).getTotal4());
                }

                row = sheet.createRow(rowIndex++);
                colNum = 1;
                cell = row.createCell(colNum++);
                cell.setCellValue(total1);
                cell.setCellStyle(style);//Set the style
                cell = row.createCell(colNum++);
                cell.setCellValue(total2);
                cell.setCellStyle(style);//Set the style
                cell = row.createCell(colNum++);
                cell.setCellValue(total3);
                cell.setCellStyle(style);//Set the style
                cell = row.createCell(colNum++);
                cell.setCellValue(total4);
                cell.setCellStyle(style);//Set the style
                cell = row.createCell(colNum++);
                cell.setCellValue(total5);
                cell.setCellStyle(style);//Set the style
                tempTotal1 += total1;
                tempTotal2 += total2;
                tempTotal3 += total3;
                tempTotal4 += total4;
                tempTotal5 += total5;
            }
            row = sheet.createRow(rowIndex++);
            colNum = 1;
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal1);
            cell.setCellStyle(style);//Set the style
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal2);
            cell.setCellStyle(style);//Set the style
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal3);
            cell.setCellStyle(style);//Set the style
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal4);
            cell.setCellStyle(style);//Set the style
            cell = row.createCell(colNum++);
            cell.setCellValue(tempTotal5);
            cell.setCellStyle(style);//Set the style
            File file = new File("fdafd.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private File vn2WriteExcelLKetNoiAccessTheoTb(File fileTemplate, List<ExportExcelLKNAccessTheoTb> temp) {
        try {
            Workbook workbook = null;
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("DATA");//createSheet("2G");
            int rowIndex = 0;
            Font font = sheet.getWorkbook().createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBold(true);
            font.setItalic(false);
            Row row = sheet.createRow(rowIndex++);
            CellStyle style2 = sheet.getWorkbook().createCellStyle();
            row.setRowStyle(style2);
            int colNum = 0;
//            for (DongTbiBO field : dongthietbiList) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue("Mã Tỉnh/TP");
            cell = row.createCell(colNum++);
            cell.setCellValue("Tỉnh/TP");
            cell = row.createCell(colNum++);
            cell.setCellValue("Tổng");
            cell = row.createCell(colNum++);
            cell.setCellValue("AGG");
            cell = row.createCell(colNum++);
            cell.setCellValue("UPE");
            cell = row.createCell(colNum++);
            cell.setCellValue("NPE");
            cell = row.createCell(colNum++);
            cell.setCellValue("NODEB");
//            }
            CellStyle style = workbook.createCellStyle();//Create style
            Font font1 = workbook.createFont();//Create font
            font1.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            style.setFont(font1);//set it to bold            

            for (int i = 0; i < temp.size(); i++) {
                row = sheet.createRow(rowIndex++);
                colNum = 0;
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getMaTinhTP());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTenTinhTP());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal1()
                        + temp.get(i).getTotal2() + temp.get(i).getTotal3()
                        + temp.get(i).getTotal4());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal1());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal2());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal3());
                cell = row.createCell(colNum++);
                cell.setCellValue(temp.get(i).getTotal4());

            }

            File file = new File("fdafd.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            return file;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
