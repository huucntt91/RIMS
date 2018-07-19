
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ADD HEADER PAGE -->
<html>
    <head>
    <head>
        <title>RIMS</title>
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

    </head>
</head>
<body class="skin-blue">
    <div class="wrapper row-offcanvas row-offcanvas-left">
        <section class="content">            
            <jsp:include page="../../alert.jsp"/>
            <div class="row">
                <div class="col-xs-12">
                    <%--<c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/removeNodeLink/{id}/{id1}')}">--%>

                        <div class="box">
                            <form:form method="POST">
                                <div class="box-header">
                                    <h3 class="box-title">Thêm người nhận cảnh báo</h3>
                                </div>
                                <div class="box-body">
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" for="exampleInputEmail1">Nhập mã user</label>
                                            <input type="text" value=""  class="form-control" id="code" placeholder="Nhập mã user"  disabled /> 
                                            <input type="hidden" name="userId"  value=""  id="userId"  />
                                            <input type="hidden" name="nodeId"  value="${nodeId}" />
                                            <span class="input-group-btn">
                                                <button  class="btn btn-success" data-toggle="modal" data-target="#myModalBTSCode" id="btn_node">Tìm user</button>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary">Thêm mới</button>
                                </div>
                            </form:form>
                        </div>
                    <%--</c:if>--%>
                </div>
            </div>


            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Danh sách người nhận cảnh báo</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã Node</th>
                                        <th>UserName</th> 
                                        <th>FullName</th> 
                                        <th>Email</th> 
                                        <th>SĐT</th> 
                                        <th></th>                
                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>
                                            <td> 
                                                ${status.index + 1}
                                            </td>
                                            <td>${item.maNode}</td> 
                                            <td>${item.userName}</td> 
                                            <td>${item.fullName}</td> 
                                            <td>${item.email}</td> 
                                            <td>${item.msisdn}</td> 
                                            <td> 
                                                <%--<c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/removeNodeLink/{id}/{id1}')}">--%>
                                                    <div class="form-group">
                                                        <button type="button" onclick="removeText(${item.dataAuditConfigId},${nodeId})" class="btn btn-danger">Remove</button>    

                                                    </div>
                                                <%--</c:if>--%>
                                            </td>        
                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                        <!-- /.box-body -->

                    </div>

                </div>


            </div>
        </section>
    </div><!-- ./wrapper -->
    <div class="modal fade" id="myModalBTSCode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Tìm Kiếm</span></h4>
                </div>
                <div class="modal-body">
                    <div class="box-body table-responsive">
                        <iframe width="100%" height="450" frameborder="0"></iframe>
                    </div>
                    <div class="modal-footer">

                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>                 
    </div>


    <!-- jQuery 2.0.2 -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- DataTables -->
    <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="${pageContext.request.contextPath}/resources/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>



    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


    <!--call ajax-->
    <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
</body>

</html>


<script>
                                                            function removeText(id, nodeId) {
                                                                if (confirm('Bạn có muốn thực hiện xoá không ?') == true)
                                                                    window.location.href = '${pageContext.request.contextPath}/dataAudit/removeUserNode/' + id + '/' + nodeId;
                                                                //$(that).parents('.groupFilter').remove()();
                                                            }
                                                            
                                                            $(document).ready(function () {
                                                                $('#btn_node').click(function () {
                                                                    $("#myModalBTSCode iframe").prop({'src': '${pageContext.request.contextPath}/dataAudit/popup'});
                                                                });
                                                                $('#myModalBTSCode iframe').on('load', function (e) {
                                                                    var iframe1 = $('#myModalBTSCode iframe').contents();
//                                                                    iframe1.find('#neTypeId').removeAttr('disabled');
                                                                    iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                                                        //alert($(this).text());
                                                                        $('#code').val($(this).find('input.user_name').val());
                                                                        $('#userId').val($(this).find('input.user_id').val());
                                                                    });
                                                                    iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                                                        //alert($(this).text());
                                                                        $('#code').val($(this).find('input.user_name').val());
                                                                        $('#userId').val($(this).find('input.user_id').val());
                                                                        $('#myModalBTSCode').modal('hide');
                                                                    });
                                                                });

                                                            });
</script>

