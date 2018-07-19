<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-black">
    <head>
        <meta charset="UTF-8">
        <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
        <title>RIMS | Đăng nhập </title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="bg-black">        
        <div class="form-box" id="login-box">
            <c:url var="loginUrl2" value="/login" />
            <c:if test="${param.error != null}">
                <div class="alert alert-info alert-dismissable">
                    <i class="fa fa-info"></i>
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">&times;</button>
                    Thông tin đăng nhập không đúng.
                </div>
            </c:if>
            <div class="header">RIMS</div>
            
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="body bg-gray">
                    <div class="form-group">
                        <input type="text" id="ssoId" name="ssoId" class="form-control" placeholder="Tài khoản"/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Mật khẩu"/>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                </div>                
                <div class="footer">                                                               
                    <button type="submit" class="btn bg-olive btn-block">Đăng nhập</button>  
                </div>                
            </form>
        </div>


        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>         
        <script>
             $( "#ssoId" ).focus();
        </script>
    </body>
</html>