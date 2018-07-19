/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 *
 * @author Administrator
 */
@Configuration
@ComponentScan("com.vnpt.media.rims.controller")
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/image/");
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName("my-locale-cookie");
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /**
     * Registering our validator with spring mvc for our i18n support
     */
    @Override
    public Validator getValidator() {
        try {
            return validator();
        } catch (Exception e) {
            throw new BeanInitializationException(
                    "exception when registering validator in "
                    + this.getClass().getName(), e);
        }
    }

    @Bean
    public LocalValidatorFactoryBean validator() throws Exception {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

//cau hinh upload file
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

        //Set the maximum allowed size (in bytes) for each individual file.
        resolver.setMaxUploadSizePerFile(5242880);//5MB
        //You may also set other available properties.

        return resolver;
    }

    //cau hinh mapping khi loi
    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
            createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r
                = new SimpleMappingExceptionResolver();

//        Properties mappings = new Properties();
//        mappings.setProperty("DatabaseException", "databaseError");
//        mappings.setProperty("InvalidCreditCardException", "creditCardError");
//        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
//        r.setExceptionAttribute("ex");     // Default is "exception"
//        r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }

    @Bean
    public ResourceBundleViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
        resolver.setBasename("views");
        return resolver;
    }

}
