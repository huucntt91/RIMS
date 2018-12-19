/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.controller;

import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Cyano
 */
@Component
public class ServiceInterceptor implements HandlerInterceptor {

    private static Logger logger = LogManager.getLogger(ServiceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        try {
            logger.debug("Pre Handle method is Calling");
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            UserBO user = (UserBO) request.getSession().getAttribute(Constants.USER_KEY);
            if(user != null){
             user = adminFacade.findByUserName(user.getUsername());
            String url = request.getRequestURL().toString();
            logger.debug("user: {}, url: {}", user.getUsername(), url);
            if (url.endsWith("/noreg") || url.endsWith("/oss.vnpt.vn")
                    || url.endsWith("RIMS/") || url.contains("decorators")
                    || url.endsWith("logout") || url.contains("api")) {
                return true;
            } else {
                if ( user.getStatus() != 1) {
                    response.sendRedirect(request.getContextPath() + "/noreg");
                    return false;
                }
            }
            }
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception excptn) throws Exception {
    }

}
