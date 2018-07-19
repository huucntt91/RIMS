/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.config;

import com.vnpt.media.rims.common.Constants;
import javax.servlet.ServletContext;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRegistration.Dynamic;  
  
import org.springframework.web.WebApplicationInitializer;  
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;  
import org.springframework.web.servlet.DispatcherServlet;  
  
public class WebInitializer implements WebApplicationInitializer{
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {        
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();  
        ctx.register(Config.class);  
        ctx.setServletContext(servletContext);    
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));  
        servlet.addMapping("/");  
//        servlet.addMapping("/*");  
        
        servlet.setLoadOnStartup(1);
        servletContext.setAttribute("NE_REG_ON", Constants.NE_REG_ON);
        servletContext.setAttribute("NE_APPROVE_ON", Constants.NE_APPROVE_ON);
        servletContext.setAttribute("NE_UNAPPROVE_ON", Constants.NE_UNAPPROVE_ON);
        servletContext.setAttribute("NE_REG_OFF", Constants.NE_REG_OFF);
        servletContext.setAttribute("NE_APPROVE_OFF", Constants.NE_APPROVE_OFF);
        servletContext.setAttribute("NE_UNAPPROVE_OFF", Constants.NE_UNAPPROVE_OFF);
        servletContext.setAttribute("API_RIMS", Constants.API_RIMS);
//        // ============= Set UTF-8 ==============
//        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
//        encodingFilter.setInitParameter("encoding", "UTF-8");
//        encodingFilter.setInitParameter("forceEncoding", "true");
//        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
//        // ============= End set UTF-8 ==============
    }
    
}
