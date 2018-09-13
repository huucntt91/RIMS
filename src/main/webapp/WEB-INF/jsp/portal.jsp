
<%@page import="com.vnpt.media.rims.bean.UserBO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RIMS</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
        <!-- jQuery 2.0.2 -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script> 
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


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
        <div class="container" >
            <div class="row" >
                <div class="col-lg-4">
                    <p>
                        <a href="https://oss.vnpt.vn/home" class="btn btn-sq-lg btn-primary">
                            <i class="fa fa-user fa-5x"></i><br/>
                            RIMS
                        </a>
                        <a href="https://dhtt2.vinaphone.vn:8889/pm1/" class="btn btn-sq-lg btn-success">
                            <i class="fa fa-user fa-5x"></i><br/>
                            PM 2G,3G NEW
                        </a>
                        <a href="http://10.156.8.41:8889/pm4g/login" class="btn btn-sq-lg btn-info">
                            <i class="fa fa-user fa-5x"></i><br/>
                            PM 4G
                        </a>
                        <a href="http://clm.vinaphone.vn/" class="btn btn-sq-lg btn-warning">
                            <i class="fa fa-user fa-5x"></i><br/>
                            PM 2G,3G OLD
                        </a>
                        <a href="http://10.156.8.42/kpi/dashboard/" class="btn btn-sq-lg btn-danger">
                            <i class="fa fa-user fa-5x"></i><br/>
                            PM TOOL NEW
                        </a>
                        <a href="http://dhtt2.vinaphone.vn:8888/fm/login" class="btn btn-sq-lg btn-danger">
                            <i class="fa fa-user fa-5x"></i><br/>
                            FM NEW
                        </a>
                        <a href="http://dhtt.vinaphone.vn/" class="btn btn-sq-lg btn-danger">
                            <i class="fa fa-user fa-5x"></i><br/>
                            FM OLD
                        </a>
                        <a href="https://csm.vnpt.vn/" class="btn btn-sq-lg btn-danger">
                            <i class="fa fa-user fa-5x"></i><br/>
                            CMS
                        </a>
                        <a href="http://mnp.vnpt.vn/" class="btn btn-sq-lg btn-danger">
                            <i class="fa fa-user fa-5x"></i><br/>
                            NMP
                        </a>
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>
