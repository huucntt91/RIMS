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
        <title>Cổng ứng dụng VNPT OSS</title>
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
        
        <link href="${pageContext.request.contextPath}/resources/css/rims.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <div class="container" >
            <div class="row" >
                <div>

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#homeApp" aria-controls="home" role="tab" data-toggle="tab"><span class="title portalTitle"><i class="fa fa-building-o"></i> Ứng dụng điều hành VNPT</span></a></li>
    <li role="presentation"><a href="#documentApp" aria-controls="profile" role="tab" data-toggle="tab"><span  class="title portalTitle">Tài liệu hướng dẫn</span></a></li>
  </ul>
  <br>
  <!-- Tab panes -->
  <div class="tab-content">
      <div role="tabpanel" class="tab-pane active" id="homeApp">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 appLogo">
                                 <a target="_blank" href="https://oss.vnpt.vn/home" class="btn btn-sq-lg btn-primary logoIcon">
                                    <i class="fa fa-desktop fa-3x"></i><br/>
                                    <h4>Hệ thống RIMS</h4>
                        </a>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 appLogo">
                                 <a target="_blank" href="http://10.156.8.42" class="btn btn-sq-lg logoIcon btn-success">
                            <i class="fa  fa-desktop fa-3x"></i><br/>
                            <h4>Hệ thống PM NEW</h4>
                        </a>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 appLogo">
                                  <a target="_blank" href="http://10.156.8.41:8889/pm4g/login" class="btn btn-sq-lg logoIcon btn-info">
                            <i class="fa  fa-desktop fa-3x"></i><br/>
                            <h4>Hệ thống PM 4G</h4>
                        </a>
                        </div>
                         <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 appLogo">
                                  <a target="_blank" href="http://clm.vinaphone.vn/" class="btn btn-sq-lg logoIcon btn-warning">
                            <i class="fa  fa-desktop fa-3x"></i><br/>
                            <h4>Hệ thống PM 2G, 3G OLD</h4>
                        </a>
                        </div>
                          <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 appLogo">
                                  <a target="_blank" href="http://dhtt2.vinaphone.vn:8888/fm/login" class="btn btn-sq-lg logoIcon btn-danger">
                            <i class="fa  fa-rss fa-3x"></i><br/>
                            <h4>Hệ thống FM new</h4>
                        </a>
                        </div>
                         <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 appLogo">
                                  <a href="http://dhtt.vinaphone.vn/" class="btn btn-sq-lg logoIcon btn-danger">
                            <i class="fa  fa-rss fa-3x"></i><br/>
                            <h4>Hệ thống FM old</h4>
                        </a>
                        </div>
                          <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 appLogo">
                                  <a target="_blank" href="https://csm.vnpt.vn/" class="btn btn-sq-lg btn-danger logoIcon">
                            <i class="fa  fa-desktop fa-3x"></i><br/>
                            <h4>Hệ thống CSM</h4>
                        </a>
                        </div>
                          <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 appLogo">
                                 
                        <a target="_blank" href="http://mnp.vnpt.vn/" class="btn btn-sq-lg btn-danger logoIcon">
                            <i class="fa  fa-desktop fa-3x"></i><br/>
                            <h4>Hệ thống MNP</h4>
                        </a>
                        </div>
                        
                      
                    </div>
                </div>
               
      </div>
      <div role="tabpanel" class="tab-pane" id="documentApp">
          <h5 class="userGuileTitle col-md-12">I.Hệ thống RIMS</h5>
          <ul class="col-md-12">
              <li class="col-md-12 userGuileItem " ><a target="_blank" href="/resources/userguides/HDSD_RIMS.pdf">1. Tài liệu hướng dẫn sử dụng RIMS</a></li>
              <li class="col-md-12 userGuileItem " ><a target="_blank" href="/resources/userguides/VNPT-OSS-RIMS_Trainning.pdf">2. Slide đào tạo hướng dẫn sử dụng</a></li>
          </ul>
            <h5 class="userGuileTitle col-md-12">II.Hệ thống PM</h5>
          <ul class="col-md-12">
              <li class="col-md-12 userGuileItem " ><a target="_blank"  href="/resources/userguides/HDSD_VNP-3GTOOL2_PM_v1.0.pdf">1. Tài liệu hướng dẫn sử dụng PM</a></li>
          </ul>
            <h5 class="userGuileTitle col-md-12">III.Hệ thống FM</h5>
          <ul class="col-md-12">
              <li class="col-md-12 userGuileItem " ><a target="_blank"  href="/resources/userguides/HDSD_VNP-3GTOOL2_FM_v1.0.pdf">1. Tài liệu hướng dẫn sử dụng FM</a></li>
          </ul>
      </div>
    
  </div>

</div>
              
            </div>
        </div>
    </body>
</html>
