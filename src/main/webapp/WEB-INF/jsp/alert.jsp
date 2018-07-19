<%-- 
    Document   : alert
    Created on : Sep 19, 2016, 10:24:19 AM
    Author     : VNP
--%>

<%@page import="com.vnpt.media.rims.common.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <% Message info = (Message) request.getAttribute("info");%>
        <%if (info != null) {%>
        <section class="content-header ">
            <c:choose>
                <c:when test="${info.type=='alert-success'}">
                    <div class="box-body" id="alert_id_common" >
                        <div class="alert alert-success alert-dismissable">
                            <i class="fa fa-check"></i>
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">&times;</button>
                            <b>${info.head}!</b> ${info.content}
                        </div>
                    </div>
                    <br />
                </c:when>    
                <c:when test="${info.type=='alert-warning'}">
                    <div class="box-body" id="alert_id_common" >
                        <div class="alert alert-warning alert-dismissable">
                            <i class="fa fa-warning"></i>
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">&times;</button>
                            <b>${info.head}!</b> ${info.content}
                        </div>
                    </div>
					
					
					
                    <br />
                </c:when>    
                <c:when test="${info.type=='alert-danger'}">
                    <div class="box-body" id="alert_id_common" >
                        <div class="alert alert-danger alert-dismissable">
                            <i class="fa fa-ban"></i>
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">&times;</button>
                            <b>${info.head}!</b> ${info.content}
                        </div>
                    </div>
                    <br />
                </c:when>    
                <c:otherwise>
                     <div class="box-body" id="alert_id_common" >
                        <div class="alert alert-info alert-dismissable">
                            <i class="fa fa-info"></i>
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">&times;</button>
                            <b>${info.head}!</b> ${info.content}
                        </div>
                    </div>
                    <br />
                </c:otherwise>
            </c:choose>
        </section>
        <%}%>   
