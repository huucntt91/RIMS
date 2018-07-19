<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>
        Quản lý Logical Link
    </h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/logicalLink/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm Logical Link</span> 
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
                                    <input name="logicalLinkName" value="${logicalLinkName}"
                                           type="text" class="form-control" id="exampleInputEmail1"
                                           placeholder="Tên logical link">
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
                    <h3 class="box-title">Danh sách Logical Link</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div id="tablePagingId">

                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên Logical Link</th>         
                                    <th>Tên Link</th>
                                    <th>Tên Path</th>
                                    <th>Băng thông</th>
                                    <th>Ghi chú</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="temp" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${status.index+1}</td>
                                        <td>${temp.logicalLinkName}</td>
                                        <td>${temp.tlinkName}</td>
                                        <td>${temp.tpathName}</td>
                                        <td>${temp.bandwidth}</td>
                                        <td>${temp.note}</td>
                                        <td>
                                            <a href="<%=request.getContextPath()%>/logicalLink/view/${temp.logicalLinkId}"
                                               title="Sửa">
                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>  
                                            <a href="<%=request.getContextPath()%>/logicalLink/delete/${temp.logicalLinkId}"
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



