<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    </head>
    <body>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Cập nhật trạm dự án</h3>
                        </div>
                        <form:form method="post" action="update" commandName="tramDA">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>       
                              
                                <%--<div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"DEPLOY_STATUS_NETX")}'> style="display: none" </c:if> >--%>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin triển khai dự án - NetX</h3>
                                    </div>
                                    <%--<div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"DEPLOY_STATUS_NETX")}'> disableGroup="disable" </c:if>>--%>
                                    <div class="panel-body"> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày phê duyệt kết quả khảo sát của VNPT Tỉnh/TP</label> 
                                                <input type="hidden" name="id" value="${tramDA.id}" id="id"  />
                                                <input type="hidden" name="trangThaiTram" value="${trangThaiTram}" id="trangThaiTram"  />
                                                <input type="hidden" name="duAnId" value="${tramDA.duAnId}" id="duAnId"  />
                                                <input type="hidden" name="tramQHId" value="${tramDA.tramQHId}" id="tramQHId"  />
                                                <input  class="form-control"  id="ngayPheDuyetKhaoSat" name="ngayPheDuyetKhaoSat" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayPheDuyetKhaoSat}" />" />
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày tiếp nhận truyền dẫn từ VNPT Tỉnh/ TP</label> 
                                                <input  class="form-control"  id="ngayTiepNhanTruyenDan" name="ngayTiepNhanTruyenDan" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayTiepNhanTruyenDan}" />" /> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
              
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>           
        </section>


        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
        
        <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        

         <script>  
            $(document).ready(function() {
                $('#ngayPheDuyetKhaoSat').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayTiepNhanTruyenDan').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
            });
        </script>
    </body>
</html>
