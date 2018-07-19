/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.vnpt.media.rims.controller.managerAdmin;
//
//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//
//import org.springframework.web.HttpSessionRequiredException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author VNP
 */
//@ControllerAdvice

//public class GlobalExceptionHandler {
//
//    private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

//	@ExceptionHandler(SQLException.class)
//	public String handleSQLException(HttpServletRequest request, Exception ex){
//		logger.info("SQLException Occured:: URL="+request.getRequestURL());
//		return "database_error";
//	}
//	
//    @ExceptionHandler(HttpSessionRequiredException.class)
//    public ModelAndView handleSessionTimeout(Exception ex) {
//        ModelAndView model = new ModelAndView("login");		
//        return model;
//
//    }
//	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleAllException(Exception ex) {
//        ModelAndView model = new ModelAndView("error");
//		model.addObject("exception", ex);
//		ex.printStackTrace();
//
//        logger.error("IOException handler executed");
//        //returning 404 error code
//        return model;
//
//    }
//}
