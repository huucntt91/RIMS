<%@page import="com.vnpt.media.rims.common.Constants"%>
<%@page import="com.vnpt.media.rims.common.Message"%>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>

        <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.js"></script>
    </head>
    <body>
        <section class="content-header">
            <h1>Duyệt và cấp mã cell<small></small></h1>
            <ol class="breadcrumb">

            </ol>
        </section>
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">

                        <div class="box-body table-responsive">
                            <div id="tablePagingId">

                                <table id="example1" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã đối tượng</th>
                                            <th>Mã đối tượng cha</th>
                                            <th>Tỉnh/TP</th>
                                            <th>Ngày hoạt động</th>
                                            <th>Hoàn cảnh ra đời</th>                                                                                        
                                            <th>Tên trên hệ thống</th>
                                            <th>LAC</th>
                                            <th>CI</th>
                                            <th>Frequency Band</th>

                                            <th>Loại cell</th>
                                            <th>No of carrier</th>
                                            <th>Trạng thái</th>
                                            <th>Xử lý</th>
                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="temp" items="${list_cell}" varStatus="status">                                        
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${temp.cellInfo.code}</td>
                                                <td>${temp.btsCode}</td>                                                
                                                <td>${temp.donViName}</td>
                                                <td>${temp.cellInfo.ngayHoatDong}</td>
                                                <td>${temp.cellInfo.hoanCanhRaDoi}</td>

                                                <td>${temp.omcCellInfo.tenTrenHeThong}</td>
                                                <td>${temp.omcCellInfo.lac}</td>
                                                <td>${temp.omcCellInfo.ci}</td>
                                                <td>${temp.omcCellInfo.bangTanName}</td>                                                
                                                <td>${temp.loaiTram.tenLoaiTram}</td>
                                                <td>${temp.cellInfo.noOfCarrier}</td>

                                                <td>
                                                    <c:choose>

                                                        <c:when test="${temp.cellInfo.status==NE_REG_ON}">                                                            
                                                            Đăng ký on air111
                                                            <br />
                                                        </c:when>    
                                                        <c:when test="${temp.cellInfo.status==NE_APPROVE_ON}">
                                                            On air
                                                            <br />
                                                        </c:when> 
                                                        <c:when test="${temp.cellInfo.status==NE_UNAPPROVE_ON}">
                                                            Hủy duyệt On air
                                                            <br />
                                                        </c:when>    
                                                        <c:when test="${temp.cellInfo.status==NE_REG_OFF}">
                                                            Đăng ký off air
                                                            <br />
                                                        </c:when>    
                                                        <c:when test="${temp.cellInfo.status==NE_APPROVE_OFF}">
                                                            Duyệt off air
                                                            <br />
                                                        </c:when> 
                                                        <c:when test="${temp.cellInfo.status==NE_UNAPPROVE_OFF}">
                                                            Hủy duyệt off air
                                                            <br />
                                                        </c:when> 

                                                        <c:otherwise>
                                                            Inactive
                                                            <br />
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td>
                                                  
                                                    <c:choose>                                                        
                                                        <c:when test="${temp.cellInfo.status==NE_REG_ON}">                                                            
                                                            <a style="cursor: pointer"  onclick="approve(${temp.nodeId}, '${temp.maNodeCha}',${temp.nodeChaId}, '${temp.code}');"
                                                               title="Duyệt đăng ký và cấp mã cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_check.png">
                                                            </a>                                                        
                                                            &nbsp;
                                                            <a style="cursor: pointer" onclick="unApprove(${temp.nodeId}, '${temp.maNodeCha}',${temp.nodeChaId}, '${temp.code}');"
                                                               title="Hủy duyệt đăng ký cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_delete.png">
                                                            </a>                                                    
                                                            &nbsp;
                                                        </c:when>

                                                        <c:when test="${temp.cellInfo.status==NE_REG_OFF}">
                                                            <a style="cursor: pointer" onclick="approveOff(${temp.nodeId}, '${temp.maNodeCha}',${temp.nodeChaId}, '${temp.code}');"
                                                               title="Duyệt y/c off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                            &nbsp;
                                                            <a style="cursor: pointer" onclick="unApproveOff(${temp.nodeId}, '${temp.maNodeCha}',${temp.nodeChaId}, '${temp.code}');"
                                                               title="Hủy y/c off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_delete.png">
                                                            </a>                                                    
                                                            &nbsp;
                                                        </c:when>                                                                                                                
                                                    </c:choose>                                                    
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
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>                        
                        <!--<h4 class="modal-title">Nhập lý do</span></h4>-->
                        <h4 class="modal-title" id="myModalLabel"></h4>
                    </div>
                    <div class="modal-body">
                        <%--<form:form method="POST" action="approve" commandName="approveForm">--%>
                        <div class="box-body table-responsive">
                            <!--<iframe width="100%" height="150" frameborder="0"></iframe>-->
                            <form:form  method="POST"  action="${pageContext.request.contextPath}/cell/approve" commandName="approveForm">
                                <div class="form-group" style="padding: 0 5px">
                                    <form:textarea rows="4" type="text" class="form-control" 
                                                   path="comment"
                                                   placeholder="Nhập lý do"/>
                                    <form:hidden path="status" id="status"></form:hidden>
                                    <form:hidden path="nodeId" id="nodeId"></form:hidden>
                                    <form:hidden path="parentCode" id="parentCode"></form:hidden>
                                    <form:hidden path="nodeChaId" id="nodeChaId"></form:hidden>
                                    </div>

                                    <!-- /.box-body -->
                                    <div class="box-footer">
                                        <button id="btnUpdateComment" type="summit" class="btn btn-primary">Đồng ý</button>
                                    </div>
                            </form:form>
                        </div>

                        <%--</form:form>--%>
                    </div>

                </div>
            </div>                 
        </div>


        <script type="text/javascript">
            function approve(nodeId, parentCode, nodeChaId, code) {
                var status =<%=Constants.NE_APPROVE_ON%>;
                $("#myModalLabel").html('Duyệt và cấp mã cell ' + code);
                $(".modal-body #status").val(status);
                $(".modal-body #nodeId").val(nodeId);
                $(".modal-body #parentCode").val(parentCode);
                $(".modal-body #nodeChaId").val(nodeChaId);
                $('#myModal').modal('show');
            }

            function unApprove(nodeId, parentCode, nodeChaId, code) {
                var status =<%=Constants.NE_UNAPPROVE_ON%>;
                $("#myModalLabel").html('Hủy duyệt đăng ký cell ' + code);
                $(".modal-body #status").val(status);
                $(".modal-body #nodeId").val(nodeId);
                $(".modal-body #parentCode").val(parentCode);
                $(".modal-body #nodeChaId").val(nodeChaId);
                $('#myModal').modal('show');

            }

            function approveOff(nodeId, parentCode, nodeChaId, code) {
                var status =<%=Constants.NE_APPROVE_OFF%>;
                $("#myModalLabel").html('Duyệt đăng ký Off cell ' + code);
                $(".modal-body #status").val(status);
                $(".modal-body #nodeId").val(nodeId);
                $(".modal-body #parentCode").val(parentCode);
                $(".modal-body #nodeChaId").val(nodeChaId);
                $('#myModal').modal('show');
            }

            function unApproveOff(nodeId, parentCode, nodeChaId, code) {
                var status =<%=Constants.NE_UNAPPROVE_OFF%>;
                $("#myModalLabel").html('Hủy duyệt đăng ký Off cell ' + code);
                $(".modal-body #status").val(status);
                $(".modal-body #nodeId").val(nodeId);
                $(".modal-body #parentCode").val(parentCode);
                $(".modal-body #nodeChaId").val(nodeChaId);
                $('#myModal').modal('show');
            }

            $(document).ready(function() {

                $('#myModal iframe').on('load', function(e) {
                    var iframe1 = $('#myModal iframe').contents();
                    iframe1.find('#btnUpdateComment').on('click', function() {
                        $('#myModal').modal('hide');
//                         window.opener.location.reload();

                    });
                });
            });

        </script>
        <script>
            $(function() {
                $("#example1").DataTable({
                    "language": {
                        "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                    }
                });
            });
        </script>
    </body>

</html>
