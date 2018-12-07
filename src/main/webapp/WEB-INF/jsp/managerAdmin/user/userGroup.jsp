<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
        <section class="content-header">
            <h1>Thêm nhóm cho người dùng</h1>
            <ol class="breadcrumb">

            </ol>
        </section>                                       
        <section class="content">                        
            <div  style="padding-bottom: 16px;">
                <div style="width: 50%; float: left;">
                    <button id="addRows" class="btn btn-primary" style="width: 100px; float: right;  margin-right: 5px;">Add &raquo;</button>
                </div>
                <div style="width: 50%; float: left;">
                    <button id="removeRows" class="btn btn-danger" style="width: 100px; float: left; margin-left: 5px;">&laquo; Remove</button>
                </div>
            </div>
            <div style="margin-top: 24px;">                                                        

                <div class="box box-primary" style="width: 50%; float: left;">
                    <div class="box-body">
                        <h4> <b>Danh sách nhóm quyền:</b></h4>
                        <hr/>

                        <table id="table-source" class="display">
                            <thead>
                                <tr>
                                    <th>
                                        <button type="button" class="btn btn-default btn-sm" id="selectPage1">
                                            <span class="glyphicon glyphicon-ok"></span>
                                        </button>
                                    </th>
                                    <th>Tên Nhóm</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="groupBO1" items="${list_group_not_user}" varStatus="statusgroupBO">                                        
                                    <tr>
                                        <td>${groupBO1.id}</td>
                                        <td>${groupBO1.name}</td>
                                    </tr> 
                                </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- /.box-body -->
                </div><!-- /.col -->                
                <div class="box box-primary" style="width: 50%; float: left;">
                    <div class="box-body">
                        <h4> <b>Nội dung đã chọn:</b></h4>
                        <hr/>
                        <table id="table-dest" class="display">
                            <thead>
                                <tr>
                                    <th>
                                        <button type="button" class="btn btn-default btn-sm" id="selectPage2">
                                            <span class="glyphicon glyphicon-ok"></span>
                                        </button>
                                        <input type="hidden" id="action" value="${pageContext.request.contextPath}/user/updateUserGroup"/>
                                        <input type="hidden" id="userId" value="${userId}"/>
                                        <input type="hidden" class="form-control" id="contentList"/>
                                    </th>
                                    <th>Tên Nhóm</th>
                                    <th width="40px">Xử lý</th>
                                </tr>
                            </thead>
                            <tbody>

                                <%--<form:hidden path="userId" title="${userId}"/>--%>
                                <c:forEach var="groupBO" items="${list_group_by_user}" varStatus="status">                                        
                                    <tr>
                                        <td>${groupBO.id}</td>
                                        <!--                                                <td>{groupBO.name}</td>                                    -->
                                        <td>
                                            ${groupBO.name}</td>
                                        <td align="center">
                                            <a href="${pageContext.request.contextPath}/permission/userAttr?uid=${userName}" title="Phân quyền tài khoản theo thuộc tính">
                                                <img src="/RIMS/image/icon/document-add-icon.png">
                                            </a>
                                        </td>
                                    </tr>    
                                </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- /.box-body -->

                    <div>
                        <div style="width: 100%; float: right;"> <p id="err_message"> </p></div>
                        <button type="button" id="getAllCheckboxGroupUser" style="width: 20%; float: right;" class="btn btn-primary">Xác nhận</button>
                    </div>
                    <!--id="getAllCheckboxGroupUser"-->
                </div><!-- /.col -->


                <div class="box-footer">
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
        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.6/css/jquery.dataTables.css"/>-->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"/>

        <script src="https://cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>

        <script src="${pageContext.request.contextPath}/resources/js/process-form.js" type="text/javascript"></script>

        <!--        <script type="javascript">            
                    $("tr").click(function(){
                    $(this).addClass("selected").siblings().removeClass("selected");
                    });​
                </script>-->
    </body>
</html>