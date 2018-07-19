<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>
        Quản lý MGW
    </h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/mgw/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm MGW</span> 
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
                <form:form method="GET" id="frm_search">
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <input name="name" value="${name}"
                                           type="text" class="form-control" id="name"
                                           placeholder="Tên">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                            </div>
                        </div>
                    </div>
                </form:form>

            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách MGW</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div id="tablePagingId">
                        <button type="button" id="export" class="btn btn-primary" 
                            onclick="location.href = '<%=request.getContextPath()%>/jasper/exportMgw?name='
                                            + $('#name').val()">Export excel</button>
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên</th>         
                                    <th>Mã node</th>
                                    <th>OPC1</th>
                                    <th>OPC2</th>
                                    <th>Hệ số mã OPC1</th>
                                    <th>Hệ số mã OPC2</th>
                                    <th>Ngày hoạt động</th>
                                    <th>Hoàn cảnh ra đời</th>
                                    <th>Ngày đăng ký</th>
                                    <th>TYPE</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="temp" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + (status.index)}</td>
                                        <td>${temp.name}</td>
                                        <td>${temp.code}</td>
                                        <td>${temp.opc}</td>
                                        <td>${temp.opc1}</td>
                                        <td>${temp.numberalSystem}</td>
                                        <td>${temp.numberalSystem1}</td>
                                        <td>${temp.ngayHoatDong}</td>
                                        <td>${temp.hoanCanhRaDoi}</td>
                                        <td>${temp.ngayDangKy}</td>
                                        <td>${temp.type}</td>

                                        <td>
                                            <a href="<%=request.getContextPath()%>/mgw/view/${temp.nodeId}"
                                               title="Sửa">
                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>  
                                            <a href="<%=request.getContextPath()%>/mgw/delete/${temp.nodeId}"
                                               title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                            </a>
                                            &nbsp;
                                             <button type="button"  class="btn btn-link" style="border: 0px; padding:0px;"  data-toggle="modal" data-target="#myModal" onclick="nelink('${temp.code}',${temp.nodeId},${temp.neTypeId})"id="btn_bts"  >
                                                <img src="<%=request.getContextPath()%>/image/icon/link-open-flat.png">
                                            </button>
                                            <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/cs?type=MGW&code=${temp.nodeId}"
                                               title="Lịch sử MGW" >
                                                <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                            </a>      
                                        </td>
                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>   
                    </div>
                </div>
                <!-- /.box-body -->
                 <div class="box-footer">
                    <!-- ADD PAGE INFO -->
                    <%@include file="../../include/page_info.jsp"%>
                </div>
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
<%@include file="../../include/footer.jsp"%>


<script type="text/javascript">

    nelink = function (code, nodeId, neTypeId) {
        $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/nelink/popup?node_code=' + code + '&nodeId=' + nodeId + '&neTypeId=' + neTypeId});

    };
</script>
