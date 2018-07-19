<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>          
        <![endif]-->
    </head>
    <body>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Quản lý CP</h3>
                        </div>
                        <form:form method="post" action="update" commandName="cpbo">
                            <!--id, username, password, fullname, email, msisdn, status, is_admin,cp_id, create_date-->
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tên CP</label>                                    
                                    <form:hidden path="id" title="${cp.id}"></form:hidden>
                                    <form:input
                                        type="text" class="form-control" id="exampleInputEmail1" path="name"
                                        placeholder="Tên CP" value="${cp.name}"/>                                    
                                </div>   
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Địa chỉ</label>                                                                        
                                    <form:input class="form-control" path="address"
                                                placeholder="Địa chỉ" value="${cp.address}"/>                                    
                                </div>   
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Mô tả</label>                                                                        
                                    <form:input class="form-control" path="description"
                                                placeholder="Mô tả" value="${cp.description}"/>                                    
                                </div>   
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Số điện thoại</label>                                                                        
                                    <form:input class="form-control" id="exampleInputEmail1" path="msisdn"
                                                placeholder="Số điện thoại" value="${cp.msisdn}"/>                                    
                                </div>   
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email</label>                                                                        
                                    <form:input class="form-control" id="exampleInputEmail1" path="email"
                                                placeholder="Email" value="${cp.email}"/>                                    
                                </div>   
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Trạng thái</label>
                                    <form:select path="status">
                                        <form:option value="1" label="--- Đang hoạt động ---"/>
                                        <form:option value="2" label="--- Đang bị Khóa ---"/>
                                        <form:option value="3" label="--- Thanh lý hợp đồng ---"/>                                        
                                    </form:select>
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


        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
    </body>
</html>