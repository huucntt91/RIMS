/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.api;

import com.vnpt.media.rims.facade.ApiFacade;
import com.vnpt.media.rims.facade.TinhDSFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController {

    private static Logger logger = LogManager.getLogger(ApiController.class);

    /*
    lay danh sach tỉnh
     */
    @RequestMapping(value = "/findProvince", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String findProvince(HttpServletRequest request,
            @RequestParam(value = "provinceId", required = false) String provinceId
    ) {
        List list = null;
        try {
            list = TinhDSFacade.findAllTinh(provinceId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /*
    lay danh sach quan/huyen
     */
    @RequestMapping(value = "/findDistrict", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String findDistrict(HttpServletRequest request,
            @RequestParam(value = "provinceId", required = false) String provinceId
    ) {
        List list = null;
        try {
            list = TinhDSFacade.findAllHuyen("", provinceId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /*
    lay danh sach phuong xa
     */
    @RequestMapping(value = "/findWard", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String findWard(HttpServletRequest request,
            @RequestParam(value = "districtId", required = false) String districtId
    ) {
        List list = null;
        try {
            list = TinhDSFacade.findAllPhuongXa("", districtId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

     /*
    tu lac-ci tra ve json
     */
    @RequestMapping(value = "/findLocation", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String findLocation(HttpServletRequest request,
            @RequestParam(value = "lac", required = false) String lac,
            @RequestParam(value = "ci", required = false) String ci) {
        List list = null;
        try {
            list = ApiFacade.findLocation(lac, ci);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
