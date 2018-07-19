<%@page import="com.vnpt.media.rims.common.utils.Convert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RIMS | Đăng ký CSHT mới</title>
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/plugins/selected/bootstrap-select.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/selected/bootstrap-select.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js"></script>


    </head>
    <body>
        <div class="content-header">
            <h1>Đăng ký CSHT mới

            </h1>
            <ol class="breadcrumb">                
                <div class="btn-group">
                    <button type="button" class="btn btn-danger">
                        <i class="fa fa-download"></i> 
                        Download Template
                    </button>
                    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu">

                        <li><a href="${pageContext.request.contextPath}/cellExcelImport/files/Template_DK_CELL_2G">Biểu mẫu đk cell 2G</a></li>
                        <li><a href="${pageContext.request.contextPath}/cellExcelImport/files/Template_DK_CELL_3G">Biểu mẫu đk cell 3G</a></li>
                        <li><a href="${pageContext.request.contextPath}/cellExcelImport/files/Template_DK_CELL_4G">Biểu mẫu đk cell 4G</a></li>                        
                    </ul>
                </div>
            </ol>
        </div>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title"></h3>
                        </div>
                        <form:form method="POST" action="${pageContext.request.contextPath}/cellsExcel/insert" commandName="cellNewExcelBO"
                                   enctype="multipart/form-data">
                            <div class="box-body" >
                                <div class="form-group">
                                    <div class="col-xs-4">
                                        <form:select  path="neTypeId" class="form-control" required="true"  >
                                            <form:option value="5">--- Cell 2G ---</form:option>
                                            <form:option value="6">--- Cell 3G ---</form:option>
                                            <form:option value="7">--- Cell 4G ---</form:option>
                                        </form:select>  
                                    </div>
                                    <div class="col-xs-4">                                    
                                        <div class="input-group">                                
                                            <form:input type="file" path="file"  id="BSbtndanger" value="${groupContactForm.name}"/>
                                            <script>
                                                $('#BSbtndanger').filestyle({
                                                    buttonName: 'btn-info',
                                                    buttonText: ' Chọn File import'
                                                });
                                            </script>
                                        </div>
                                    </div>                                   
                                    <div class="col-xs-4" >
                                    </div>
                                </div>
                            </div>
                            </br>
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">Cập nhật</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>                        
        </section> 
    </body>

</html>

