<%@page import="com.vnpt.media.rims.common.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                            <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                        </div>
                        <form:form method="GET" action="search">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Họ tên</label>
                                    <input name="fullname" value="${name}"
                                           type="text" class="form-control" id="exampleInputEmail1"
                                           placeholder="Họ và tên">
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
                            <h3 class="box-title">Danh sách tài khoản</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <div id="tablePagingId">

                                <table id="example1" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tài khoản</th>
                                            <th>Họ và tên</th>
                                            <th>Email</th>                                            
                                            <th>Số điện thoại</th>
                                            <th>Đơn vị</th>
                                            <th>Trạng thái</th>
                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="userBO" items="${list_user}" varStatus="status">                                        
                                            <tr>
                                                <td>
                                                    <input type="hidden" class="user_id" value="${userBO.id}"/>
                                                    <input type="hidden" class="user_name" value="${userBO.username}"/>
                                                    ${status.index+1}
                                                </td>
                                                <td>${userBO.username}</td>
                                                <td>${userBO.fullname}</td>
                                                <td>${userBO.email}</td>
                                                <td>${userBO.msisdn}</td>
                                                <td>${userBO.tenDonVi}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${userBO.status=='1'}">
                                                            Active
                                                            <br />
                                                        </c:when>    
                                                        <c:otherwise>
                                                            Inactive
                                                            <br />
                                                        </c:otherwise>
                                                    </c:choose>
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

    </body>
</html>

<script>

    $(document).ready(function () {

        $('#example1 tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $('#example1 tbody').find('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
    });
</script>