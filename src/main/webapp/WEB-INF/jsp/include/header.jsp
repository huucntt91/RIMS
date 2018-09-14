<%-- 
    Document   : header
    Created on : Aug 31, 2016, 5:46:18 PM
    Author     : Administrator
--%>
<%@page import="com.vnpt.media.rims.common.Message"%>

<%@ page import="com.vnpt.media.rims.bean.UserBO" %>
<%@ page import="com.vnpt.media.rims.common.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>RIMS</title>
        <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- bootstrap 3.0.2 -->
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <link href="${pageContext.request.contextPath}/resources/vnpt/css/vnpt.css" rel="stylesheet" type="text/css"/>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css">
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <!-- jQuery 2.0.2 -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.min.js"></script> 

        <!-- jquery ui -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-106468556-7"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            gtag('config', 'UA-106468556-7');
            gtag('set', {'user_id': '<%=request.getRemoteUser()%>}'); 
        </script>
    </head>

    <body>
        <div class="page-wrap">
            <div class="page_top" id="header">
                <div class="container header_div">
                    <div class="header_info hidden-sm hidden-xs">
                        <span id="lblName">Hệ thống quản lý tài nguyên mạng VNPT<br>Resource Inventory Management System - RIMS</span>
                        <br />
                        <span id="lblPhone"></span>
                    </div>
                </div>
            </div>

            <div id="mainContainer">
                <!-- main navbar -->
                <div class="navbar navbar-inverse navbar-static-top navbar-sm" role="navigation" id="navbar-main">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="https://oss.vnpt.vn">
                                <span id="lblVersion"><i class="fa fa-home"></i></span>
                            </a>
                        </div>
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav">
                                <li><a href="${pageContext.request.contextPath}/home">RIMS</a></li>    
                                <!-- User Account: style can be found in dropdown.less -->
                                <%
                                    String fullname = (String) request.getSession().getAttribute("fullname");
                                    if (fullname == null || fullname.equals("null") || fullname.equals("")) {
                                        response.sendRedirect(request.getContextPath() + "/SetSessionServlet");
                                    }

                                    String data = (String) request.getSession().getAttribute(Constants.MENU_KEY);
                                %>
                                <%=data%>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Hỗ trợ <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="https://oss.vnpt.vn/media/HDSD_RIMS.pdf">Hướng dẫn sử dụng</a>
                                        </li>
                                        <li>
                                            <a href="https://oss.vnpt.vn/media/VNPT-OSS-RIMS_Trainning.pdf">Slide đào tạo hướng dẫn sử dụng</a>
                                        </li>
                                    </ul> 
                                </li>


                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="divider-vertical"></li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <span id="NavMenu_lblUsername">VNPT ID: <%=request.getSession().getAttribute("fullname")%></span>
                                        <b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a id="NavMenu_aLogout" href="<c:url value="/logout" />"><i class="icon-signout"></i> Thoát</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>


                        </div>
                    </div>
                </div>                      
                <!-- content wrapper -->
                <div class="container" id="wrapper">
                    <div id="content" style="min-height: 400px">
                        <jsp:include page="../../alert.jsp"/>


