package com.vnpt.media.rims.controller.security;

import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionHandleFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(SessionHandleFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp = (HttpServletResponse) response;
        HttpSession session = rq.getSession();
        String url = rq.getRequestURI();
        Object USER_KEY = session.getAttribute(Constants.USER_KEY);
        if (url.toLowerCase().contains("resources/")) {
            chain.doFilter(request, response);
        } else if (USER_KEY == null && !url.endsWith("/SetSessionServlet") && !url.endsWith("/noreg") && !url.endsWith("/logout")) {
            if (USER_KEY != null) {
                UserBO user = (UserBO) USER_KEY;
                LOGGER.debug(user.listParam());
            }
            LOGGER.debug("url=" + url + ",USER_KEY=" + USER_KEY);
            LOGGER.debug("USER_KEY null case, redirect /SetSessionServlet");
            rp.sendRedirect(rq.getContextPath() + "/SetSessionServlet");
        } else {
            if (USER_KEY != null) {
                UserBO user = (UserBO) USER_KEY;
                LOGGER.debug(user.listParam());
            }
            LOGGER.debug("url=" + url + ",USER_KEY=" + USER_KEY);
            chain.doFilter(request, response);
        }

    }
}
