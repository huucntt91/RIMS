
<%@page import="com.vnpt.media.rims.bean.UserBO"%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
    <head>
        <title>RIMS</title>
        <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- bootstrap 3.0.2 -->
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css">
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <!-- jQuery 2.0.2 -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.min.js"></script> 
        <!-- jquery ui -->

    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="${pageContext.request.contextPath}/" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                RIMS
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">                                                                
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>                                
                                <span>                                
                                    <%=request.getRemoteUser()%>
                                    <i class="caret"></i>
                                </span>
                            </a>
                            <ul class="dropdown-menu">   
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
    <!--                                    <a href="${pageContext.request.contextPath}/user/viewChangepass" class="btn btn-default btn-flat">??i m?t kh?u</a>-->
                                    </div>
                                    <div class="pull-right">
                                        <a href="<c:url value="/logout" />" class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">                                    
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">

                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <style>
                    .content{
                        margin-top: 50px;
                        text-align: center;
                        font-size: 20px;
                        color: blue;
                    }
                </style>


                <section class="content">                        
                    Xin chào : <%=request.getRemoteUser()%>! Bạn chưa được tạo tài khoản trên hệ thống. Vui lòng liên hệ tới Admin để có thể sử dụng! 
                </section>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

        <!-- DataTables -->
        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.js"></script>
        <!-- SlimScroll -->
        <script src="${pageContext.request.contextPath}/resources/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/fastclick/fastclick.js"></script>


        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>

    </body>
</html>










