<!DOCTYPE html>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | 500 Error</title>
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
        <!-- Content Header (Page header) -->
        <section class="content-header">


        </section>

        <!-- Main content -->
        <section class="content">

            <div class="error-page">

                <!--<div class="error-content">-->
                <h3>
                    <i class="fa fa-warning text-yellow"></i> Oops! Something went
                    wrong.
                </h3>
                <p>                    
                    <a href='${pageContext.request.contextPath}'>return to login page</a>
                </p>
                <p>
                    <b>${exception.message}</b><br/><br/>
                    <strong>Exception Stack Trace</strong><br>
                    <c:forEach items="${exception.stackTrace}" var="ste">
                        ${ste}
                    </c:forEach>

                </p>				
                <!--</div>-->
            </div>
            <!-- /.error-page -->

        </section>
        <!-- /.content -->


        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/demo.js" type="text/javascript"></script>
    </body>
</html>