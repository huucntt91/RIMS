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
        <![endif]-->
    </head>
    <body>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Thông tin cá nhân</h3> <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_THONGTINCANHAN_RIMS.mp4');" >Hướng dẫn</button>
                        </div>
                        <form:form method="post" action="updateUserInfo" commandName="ubo">
                            <!--id, username, password, fullname, email, msisdn, status, is_admin,cp_id, create_date-->
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Họ và tên</label>                                    
                                    <form:hidden path="id" title="${user.id}"></form:hidden>
                                    <form:input
                                        type="text" class="form-control" id="exampleInputEmail1" path="fullname"
                                        placeholder="Họ và tên" value="${user.fullname}"/>                                    
                                </div>                                    
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email</label>                                    
                                    <form:input
                                        type="text" class="form-control" id="exampleInputEmail1" path="email"
                                        placeholder="Email" value="${user.email}"/>                                    
                                </div>                                    
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Số điện thoại</label>                                                                        
                                    <form:input
                                        type="text" class="form-control" id="exampleInputEmail1" path="msisdn"
                                        placeholder="Số điện thoại" value="${user.msisdn}"/>                                    
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
        <script>
            function hdsd(media) {
                window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
            }
        </script>
    </body>
    
</html>
