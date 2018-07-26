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

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <section class="content-header">
            <h1>
                <spring:message code="admin.cp.title" />
            </h1>
            <ol class="breadcrumb">
                <button class="btn btn-info btn-sm"  onclick="location.href='<%=request.getContextPath()%>/cp/preAdd'" >
                    <span><i class="fa fa-fw fa-plus"></i><spring:message code="admin.cp.addCP" /></span> 
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
                                    <label for="exampleInputEmail1">Tên CP</label>
                                    <input name="name" value="${name}"
                                        type="text" class="form-control" id="exampleInputEmail1"
                                        placeholder="Tên CP">
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
                            <h3 class="box-title">Danh sách CP</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <div id="tablePagingId">

                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên CP</th>                                            
                                            <th>Địa chỉ</th>
                                            <th>Mô tả</th>
                                            <th>ĐT</th>
                                            <th>Email</th>
                                            <th>Ngày tạo</th>
                                            <th>Ngày cập nhật</th>
                                            <th>Trạng thái</th>
                                            <th>Xử lý</th>
                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="cpBO" items="${list_cp}" varStatus="status">                                        
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${cpBO.name}</td>
                                                <td>${cpBO.address}</td>
                                                <td>${cpBO.description}</td>
                                                <td>${cpBO.msisdn}</td>
                                                <td>${cpBO.email}</td>
                                                <td>${cpBO.createDate}</td>
                                                <td>${cpBO.updateDate}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${cpBO.status=='1'}">
                                                            Đang hoạt động
                                                            <br />
                                                        </c:when>    
                                                            <c:when test="${cpBO.status=='2'}">
                                                            Đang bị khóa
                                                            <br />
                                                        </c:when>    
                                                            <c:when test="${cpBO.status=='3'}">
                                                            Thanh lý hợp đồng
                                                            <br />
                                                        </c:when>    
                                                        <c:otherwise>
                                                            Không xác định
                                                            <br />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <a href="<%=request.getContextPath()%>/cp/view/${cpBO.id}"
                                                       title="Sửa">
                                                        <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                    </a>   
                                                    &nbsp;
                                                    <a href="<%=request.getContextPath()%>/brandname/searchListOfCp/${cpBO.id}"
                                                       title="Quản lý Brand name của CP">
                                                        <img src="<%=request.getContextPath()%>/image/icon/document-add-icon.png">
                                                    </a>  
                                                    <a href="<%=request.getContextPath()%>/package/preAddPackageToCP/${cpBO.id}"
                                                       title="Quản lý gói cước của CP">
                                                        <img src="<%=request.getContextPath()%>/image/icon/document-add-icon.png">
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

    </body>
</html>