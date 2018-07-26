<%@page import="java.util.List"%>
<%@page import="com.vnpt.media.rims.common.utils.Convert"%>
<%@page import="com.vnpt.media.rims.common.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>

        <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.js"></script>
    </head>
    <body>
        <div class="content-header">
            <h1>Cập nhật trạm quy hoạch</h1>
        </div>
        <section class="content"> 
            <c:if test='${fn:containsIgnoreCase(sessionScope.function,"EXCEL_UPDATE_QH_PTM")}'>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Cập nhật thông tin Quy hoạch của PTM</h3>
                            </div>
                            <form:form method="post" action="${pageContext.request.contextPath}/excel_update_project_station/updateQhPtm" commandName="groupContactForm"
                                       enctype="multipart/form-data">

                                <div class="box-body" >
                                    <div class="form-group">

                                        <div class="col-xs-6">                                    
                                            <div class="input-group">                                
                                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn file import</label>
                                                <form:input class="form-control"  id="file3"
                                                            type="file"  path="file" 
                                                            value="${groupContactForm.name}"/>                                                                            
                                            </div>
                                        </div>

                                        <div class="col-xs-6">                                    
                                            <button type="button" onclick='window.location.href = "<%=request.getContextPath()%>/excel_update_project_station/templateQhPtm"' class="btn btn-facebook"><spring:message code="btn.download.template"/></button>
                                        </div>
                                    </div>
                                </div>
                                </br>
                                <div class="box-footer">
                                    <button type="submit" id="upload3" class="btn btn-primary"><spring:message code="btn.update"/></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test='${fn:containsIgnoreCase(sessionScope.function,"EXCEL_UPDATE_QH_TINH")}'>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Cập nhật thông tin Quy hoạch của Tỉnh</h3>
                            </div>
                            <form:form method="post" action="${pageContext.request.contextPath}/excel_update_project_station/updateCshtNguonDc" commandName="groupContactForm"
                                       enctype="multipart/form-data">

                                <div class="box-body" >
                                    <div class="form-group">

                                        <div class="col-xs-6">                                    
                                            <div class="input-group">                                
                                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn file import</label>
                                                <form:input class="form-control" id="file"
                                                            type="file"  path="file" 
                                                            value="${groupContactForm.name}"/>                                                                            
                                            </div>
                                        </div>

                                        <div class="col-xs-6">                                    
                                            <button type="button" onclick='window.location.href = "<%=request.getContextPath()%>/excel_update_project_station/templateQhTinh"' class="btn btn-facebook"><spring:message code="btn.download.template"/></button>
                                        </div>
                                    </div>
                                </div>
                                </br>
                                <div class="box-footer">
                                    <button type="submit" id="upload" class="btn btn-primary"><spring:message code="btn.update"/></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test='${fn:containsIgnoreCase(sessionScope.function,"EXCEL_UPDATE_QH_QLDA")}'>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Cập nhật thông tin Quy hoạch của QLDA</h3>
                            </div>
                            <form:form method="post" action="${pageContext.request.contextPath}/excel_update_project_station/updateQhQlda" commandName="groupContactForm"
                                       enctype="multipart/form-data">

                                <div class="box-body" >
                                    <div class="form-group">

                                        <div class="col-xs-6">                                    
                                            <div class="input-group">                                
                                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn file import</label>
                                                <form:input class="form-control"  id="file1"
                                                            type="file"  path="file" 
                                                            value="${groupContactForm.name}"/>                                                                            
                                            </div>
                                        </div>

                                        <div class="col-xs-6">                                    
                                            <button type="button" onclick='window.location.href = "<%=request.getContextPath()%>/excel_update_project_station/templateQhQlda"' class="btn btn-facebook"><spring:message code="btn.download.template"/></button>
                                        </div>
                                    </div>
                                </div>
                                </br>
                                <div class="box-footer">
                                    <button type="submit" id="upload1" class="btn btn-primary"><spring:message code="btn.update"/></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test='${fn:containsIgnoreCase(sessionScope.function,"EXCEL_UPDATE_QH_NETX")}'>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Cập nhật thông tin Quy hoạch của NETx</h3>
                            </div>
                            <form:form method="post" action="${pageContext.request.contextPath}/excel_update_project_station/updateQhNetx" commandName="groupContactForm"
                                       enctype="multipart/form-data">

                                <div class="box-body" >
                                    <div class="form-group">

                                        <div class="col-xs-6">                                    
                                            <div class="input-group">                                
                                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn file import</label>
                                                <form:input class="form-control" id="file2"
                                                            type="file"  path="file" 
                                                            value="${groupContactForm.name}"/>                                                                            
                                            </div>
                                        </div>

                                        <div class="col-xs-6">                                    
                                            <button type="button" onclick='window.location.href = "<%=request.getContextPath()%>/excel_update_project_station/templateQhNetx"' class="btn btn-facebook"><spring:message code="btn.download.template"/></button>
                                        </div>
                                    </div>
                                </div>
                                </br>
                                <div class="box-footer">
                                    <button type="submit" id="upload2" class="btn btn-primary"><spring:message code="btn.update"/></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </c:if>

            
        </section>    
        <script>

            $(document).ready(function () {
                $('#upload').bind("click", function ()
                {
                    var imgVal = $('#file').val();
                    if (imgVal == '')
                    {
                        alert("Bạn phải chọn file trước khi cập nhật dữ liệu");
                        return false;
                    }
                    return true;
                });
                $('#upload1').bind("click", function ()
                {
                    var imgVal = $('#file1').val();
                    if (imgVal == '')
                    {
                        alert("Bạn phải chọn file trước khi cập nhật dữ liệu");
                        return false;
                    }
                    return true;
                });
                $('#upload2').bind("click", function ()
                {
                    var imgVal = $('#file2').val();
                    if (imgVal == '')
                    {
                        alert("Bạn phải chọn file trước khi cập nhật dữ liệu");
                        return false;
                    }
                    return true;
                });
                $('#upload3').bind("click", function ()
                {
                    var imgVal = $('#file3').val();
                    if (imgVal == '')
                    {
                        alert("Bạn phải chọn file trước khi cập nhật dữ liệu");
                        return false;
                    }
                    return true;
                });
            });


        </script>
    </body>


</html>