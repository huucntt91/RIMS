<%@page import="com.vnpt.media.rims.common.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
        <section class="content-header">
            <h1>Quản lý dự án<small></small></h1>
            <ol class="breadcrumb">
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/duantinh/preAdd'" >
                        <span><i class="fa fa-fw fa-plus"></i>Thêm Dự Án</span> 
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
                        <form:form method="GET" action="search">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Mã dự án</label>
                                    <input name="maDuAn" value="${maDuAn}"
                                           type="text" class="form-control" id="exampleInputEmail1"
                                           placeholder="Mã dự án">
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
                            <h3 class="box-title">Danh sách dự án</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <div id="tablePagingId">

                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã DA</th>
                                            <th>Tên DA</th>
                                            <th>Tỉnh</th>
                                            <th>Mã quy hoạch</th>
                                            <th>Sửa</th>
                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="duAnTinhBO" items="${listDuAnTinh}" varStatus="status">                                        
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${duAnTinhBO.maDuAn}</td>
                                                <td>${duAnTinhBO.tenDuAn}</td>
                                                <td>${duAnTinhBO.tenTinh}</td>
                                                <td>${duAnTinhBO.maQuyHoach}</td>
                                                
                                                <td>
                                                        <a href="<%=request.getContextPath()%>/duantinh/preUpdate/${duAnTinhBO.duAnId}"
                                                            title="Cập nhật thông tin dự án">
                                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                        </a>
                                                  
                                                   
                                                        <a href="<%=request.getContextPath()%>/duantinh/delete/${duAnTinhBO.duAnId}"
                                                           title="Xóa">
                                                            <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                        </a>

                                                </td>
                                            </tr>
                                        </c:forEach>                                       							
                                    </tbody>                                    
                                </table>
                            </div>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>                                 

        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        
        <script type="text/javascript">


        </script>
    </body>
</html>