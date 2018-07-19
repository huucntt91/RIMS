/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller.barcode;

import com.google.gson.Gson;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.vnpt.media.rims.bean.ObjectTaiSan;

/**
 *
 * @author Cyano
 */
@Controller
@RequestMapping(value = "/barcode")
public class BarCodeController {

    private static final Logger logger = LogManager.getLogger(BarCodeController.class);
    private static final String FORM_DETAIL = "barcode/equipment/detail";
    private static final String URL_BARCODE = ResourceBundle.getBundle("config", Locale.getDefault()).getString("URL_BARCODE");

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String getDetail(
            @RequestParam(value = "partnumber", defaultValue = "") String partnumber,
            @RequestParam(value = "SerialNumber", defaultValue = "") String SerialNumber,
            @RequestParam(value = "MaVach", defaultValue = "") String MaVach,
            ModelMap mm, HttpServletRequest request) {
        try {
            String url = URL_BARCODE + "?partnumber=" + partnumber + "&SerialNumber=" + SerialNumber + "&MaVach=" + MaVach;
            String result = GET_BARCODE(url);
            Gson gson = new Gson();
            ObjectTaiSan[] taiSan = gson.fromJson(result, ObjectTaiSan[].class);
            if (taiSan != null && taiSan.length > 0) {
                mm.put("model", taiSan[0]);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return FORM_DETAIL;
    }

    private String GET_BARCODE(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "4f84d975-3c21-2613-3f3c-9cf5cd26bab7")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
