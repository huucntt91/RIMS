<%@page import="com.vnpt.media.rims.common.Message"%>
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
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <section class="content-header">
            <h1><spring:message code="welcome.userList"/><small></small>
            <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_QUANLYTAIKHOAN_RIMS.mp4');" >Hướng dẫn</button>
            </h1>
            <ol class="breadcrumb">
                <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/user/preAdd'" >
                    <span><i class="fa fa-fw fa-plus"></i>Thêm tài khoản</span> 
                </button>
            </ol>
        </section>
        <section class="content">


            <script type="text/javascript">
                resetPassword = function (userId) {
                    if (confirm("Bạn có muốn thực hiện reset password không ?")) {
                        $.ajax({
                            type: 'GET',
                            url: "<%=request.getContextPath()%>/user/resetpassword/" + userId,
                            dataType: "xml",
                            beforeSend: function () {
                            },
                            complete: function () {
                            },
                            success: function (data) {
                                var code = "";
                                $(data).find("responseCode").each(function () {
                                    code = code + $(this).text() + "";
                                });
                                if (code == '01') {
                                    alert("Có lỗi trong quá trình xử lý. Vui lòng thử lại");
                                } else if (code == '00') {
                                    alert("Reset mật khẩu thành công");
                                }
                            }
                            ,
                            error: function (xhr, textStatus, error) {
                                alert('error_ajax_xml?');
                            }
                        });
                    } else {
                        return false;
                    }
                }
                
    function hdsd(media) {
        window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
    }

            </script>

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

                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Ngày tạo</th>
                                            <th>Tài khoản</th>
                                            <th>Họ và tên</th>
                                            <th>Email</th>                                            
                                            <th>Số điện thoại</th>
                                            <th>Đơn vị</th>
                                            <th>Trạng thái</th>
                                            <th>Xử lý</th>
                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="userBO" items="${list_user}" varStatus="status">                                        
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${userBO.createDate}</td>
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
                                                <td>

                                                    &nbsp;
                                                    <a href="<%=request.getContextPath()%>/user/active/${userBO.id}"
                                                       title="Active/Inactive"
                                                       onclick="return confirm('Bạn có muốn thực hiện active/inactive không ?')">
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_check.png"></a>
                                                    &nbsp;
                                                    <a href="<%=request.getContextPath()%>/user/view/${userBO.id}"
                                                       title="Sửa">
                                                        <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                    </a>
                                                    &nbsp;
                                                    <a href="<%=request.getContextPath()%>/user/delete/${userBO.id}"
                                                       title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                        <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                    </a>
                                                    &nbsp;
                                                    <a href="<%=request.getContextPath()%>/user/addGroupToUser/${userBO.id}"
                                                       title="Thông tin nhóm quyền" >
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
