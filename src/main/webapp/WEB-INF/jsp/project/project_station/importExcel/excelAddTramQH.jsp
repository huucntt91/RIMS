<%@page import="java.util.List"%>
<%@page import="com.vnpt.media.rims.common.utils.Convert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
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
            <h1>Khai báo trạm quy hoạch</h1>
             <h2 style="display: none; color: blue;" id="mes_">Cập nhật thành công. Bạn check kết quả file download đi kèm</h2>
        </div>
        <section class="content">            


            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Upload file excel trạm quy hoạch</h3>
                          
                        </div>
                        <form:form method="post" action="${pageContext.request.contextPath}/excel_project_station/checkFile" commandName="groupContactForm"
                                   enctype="multipart/form-data">

                            <div class="box-body" >
                                <div class="form-group">

                                    <div class="col-xs-6">                                    
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn file import</label>
                                            <form:input class="form-control" 
                                                        type="file"  path="file" 
                                                        value="${groupContactForm.name}"/>                                                                            
                                        </div>
                                    </div>

                                    <div class="col-xs-6">                                    
                                        <button type="button" onclick="btDownloadTemplate();" class="btn btn-facebook">Download biểu mẫu</button>
                                    </div>
                                </div>
                            </div>
                            </br>
                            <div class="box-footer">
                                <button type="submit" id="upload" class="btn btn-primary">Cập nhật dữ liệu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
     
        </section>    
        <script>
            function btDownloadTemplate() {
                window.location.href = "${pageContext.request.contextPath}/resources/excel/MaudangkymamQH.xlsx";
            }

            $(document).ready(function () {
                $('#upload').bind("click", function ()
                {
                    var imgVal = $('#file').val();
                    if (imgVal == '')
                    {
                        alert("Bạn phải chọn file trước khi cập nhật dữ liệu");
                        return false;

                    }
                    $('#mes_').show();
                    return true;

                });
            });


        </script>
    </body>


</html>