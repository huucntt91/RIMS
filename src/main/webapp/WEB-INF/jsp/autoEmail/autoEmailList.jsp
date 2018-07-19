
<%@page import="com.vnpt.media.rims.common.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        
                <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        
    </head>
    <body>
        <section class="content-header">
            <h1>Quản lý cấu hình email tự động<small></small></h1>
            <ol class="breadcrumb">
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/autoEmail/preAddReport'" >
                        <span><i class="fa fa-fw fa-plus"></i>Thêm report</span> 
                    </button>     
            </ol>
            
                    
                     
        </section>
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                        </div>
                        <form:form method="GET">
                              
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tên báo cáo</label>
                                    <input name="reportMailName" value="${reportMailName}"
                                           type="text" class="form-control" id="reportMailName"
                                           placeholder="Tên báo cáo">
                                </div>                                                                                     
                            </div>               
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>



            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Danh sách báo cáo email</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <div id="tablePagingId">
                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên chủ đề</th>
                                            <th>Nội dung email</th>
                                            <th>Danh sách email</th>
                                            <th>Thời gian gửi</th>
                                            <th>Xử lý</th>

                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="value" items="${list_emailReport}" varStatus="status">                                        
                                            <tr>
                                                <td>${startRow + (status.index)}</td>
                                                <td>${value.reportMailName}</td>
                                                <td>${value.emailDetail}</td>
                                                <td>${value.listMail}</td>
                                                <td>${value.timeSend}</td>
                                                <td>
                                                    <a href="<%=request.getContextPath()%>/autoEmail/view/${value.idReportMail}"
                                                       title="Sửa">
                                                        <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                    </a>  
                                                    <a href="<%=request.getContextPath()%>/autoEmail/delete/${value.idReportMail}"
                                                       title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                        <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                    </a>
                                                    <a href="<%=request.getContextPath()%>/autoEmail/addSqlToReport/${value.idReportMail}"
                                                       title="Thông tin báo cáo" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/sql.png">
                                                    </a> 
                                                </td>
                                           
                                            </tr>
                                        </c:forEach>                                       							
                                    </tbody>                                    
                                </table>
                            </div>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <!-- ADD PAGE INFO -->
                            <%@include file="../include/page_info.jsp"%>
                        </div>
                    </div>
                    <!-- /.box -->
<!--                    <div class="box-footer">
                     ADD PAGE INFO 
                        <%--<%@include file="../../include/page_info.jsp"%>--%>
                    </div>-->
                </div>
            </div>
        </section>

              
        <script type="text/javascript">

//                function hdsd(media) {
//                window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
//            }                                                    
        </script>
        <%@include file="../include/footer.jsp"%>            

       
  