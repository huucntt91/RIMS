<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>
        Quản lý Danh mục máy chủ
    </h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/serverDir/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm Máy chủ</span> 
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
                    <h3 class="box-title">Danh sách Máy chủ</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div id="tablePagingId">
<!--                        <button type="button" id="export" class="btn btn-primary" 
                            onclick="location.href = '<%=request.getContextPath()%>/jasper/exportMsc?name='
                                            + $('#name').val()">Export excel</button>-->
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên</th>    
                                    <th>Ghi chú</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="temp" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + (status.index)}</td>
                                        <td>${temp.serverName}</td>
                                        <td>${temp.note}</td>
                                        
                                        <td>
                                            <a href="<%=request.getContextPath()%>/serverDir/view/${temp.id}"
                                               title="Sửa">
                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>  
                                            <a href="<%=request.getContextPath()%>/serverDir/delete/${temp.id}"
                                               title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                <img src="<%=request.getContextPath()%>/image/icon/delete.png">
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

<%@include file="../../include/footer.jsp"%>



