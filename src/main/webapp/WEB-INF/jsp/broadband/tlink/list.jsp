<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>Quản lý TLink<small></small></h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/tlink/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm tlink</span> 
        </button>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <form:form method="GET" id="frm_search">
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon">Tên</label>
                            <input name="name" value="${name}"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                </div>        
            </form:form>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                 <div class="box-header">
                    <h3 class="box-title">Danh sách TLink</h3>
                </div>
                <div class="box-body table-responsive">
                    <div id="tablePagingId">
                        <table id="example1" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Xử lý</th>
                                    <th>TNode 1</th>
                                    <th>TNode 2</th>
                                    <th>Port 1</th>
                                    <th>Port 2</th>
                                    <th>Ip 1</th>                                                                                        
                                    <th>Ip 2</th>
                                    <th>MUC_THU1</th>
                                    <th>MUC_THU2</th>
                                    <th>DISTANCE_M</th>
                                    <th>EXTRA_DATA1</th>
                                    <th>EXTRA_DATA2</th>
                                    <th>TLINK_NAME</th>
                                    <th>Note</th>
                                    
                                </tr>
                            </thead>                            
                            <tbody>                                       
                                <c:forEach var="temp" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${status.index+1}</td>
                                         <td>
                                            
                                                <a href="<%=request.getContextPath()%>/tlink/preUpdate/${temp.id}"
                                                   title="Sửa">
                                                    <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                </a>  
                                            
                                                <a href="<%=request.getContextPath()%>/tlink/delete/${temp.id}"
                                                   title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                    <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                </a>
                                            
                                            &nbsp;
                                        </td>
                                        <td>${temp.nodeName1}</td>
                                        <td>${temp.nodeName2}</td>
                                        <td>${temp.port1}</td>
                                        <td>${temp.port2}</td>
                                        <td>${temp.ip1}</td>
                                        <td>${temp.ip2}</td>
                                        <td>${temp.mucThu1}</td>
                                        <td>${temp.mucThu2}</td>
                                        <td>${temp.distanceM}</td>
                                        <td>${temp.extraData1}</td>
                                        <td>${temp.extraData2}</td>
                                        <td>${temp.tLinkName}</td>
                                        <td>${temp.note}</td>
                                       
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
<script>
    $(function() {
        $("#example1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
    });
</script>

