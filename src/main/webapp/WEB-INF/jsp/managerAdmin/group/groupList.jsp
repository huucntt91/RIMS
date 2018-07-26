<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <section class="content-header">
            <h1>Quản lý nhóm quyền<small></small>
           <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_QUANLYNHOMQUYEN_RIMS.mp4');" >Hướng dẫn</button>
        </h1>
            <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/group/preAdd')}">
                <ol class="breadcrumb">
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/group/preAdd'" >
                        <span><i class="fa fa-fw fa-plus"></i>Thêm nhóm quyền</span> 
                    </button>
                </ol>
            </c:if>
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
                                    <label for="exampleInputEmail1">Tên nhóm</label>
                                    <input name="name" value="${name}"
                                           type="text" class="form-control" id="exampleInputEmail1"
                                           placeholder="Tên nhóm">
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
                            <h3 class="box-title">Danh sách nhóm quyền</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <div id="tablePagingId">

                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên nhóm</th>                                            
                                            <th>Xử lý</th>
                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="groupBO" items="${list_group}" varStatus="status">                                        
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${groupBO.name}</td>

                                                <td>
                                                    <%--<c:if test="${sessionScope.menu != null}">--%>
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/group/view/{id}')}">
                                                        <a href="<%=request.getContextPath()%>/group/view/${groupBO.id}"
                                                           title="Sửa">
                                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/group/delete/{id}')}">
                                                        &nbsp;
                                                        <a href="<%=request.getContextPath()%>/group/delete/${groupBO.id}"
                                                           title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                            <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                        </a>
                                                    </c:if>
<!--                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/group/delete/{id}')}">
                                                        &nbsp;
                                                        <a href="<%=request.getContextPath()%>/group/clone/${groupBO.id}" class="icon-copy"
                                                           title="Sao chép" onclick="return confirm('Bạn có muốn thực hiện sao chép nhóm quyền không ?')">
                                                            <img src="<%=request.getContextPath()%>/image/icon/copy.png">
                                                        </a>
                                                    </c:if>      -->
                                                    &nbsp;

                                                    <a href="<%=request.getContextPath()%>/group/preUpdateGroupMenu/${groupBO.id}"
                                                       title="Phân quyền tài khoản" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/document-add-icon.png">
                                                    </a> 
                                                       &nbsp;
                                                    
                                                    <a href="<%=request.getContextPath()%>/group/classgroup/${groupBO.id}"
                                                       title="Phân quyền thuộc tính" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/target.png">
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
        <script>
            function hdsd(media) {
                window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
            }
        </script>
    </body>
</html>
