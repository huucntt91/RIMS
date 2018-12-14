<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>
        <i class="fa fa-server" aria-hidden="true"></i> Quản lý BSC/RNC
    </h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/bscRnc/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm BSC/RNC</span> 
        </button>
    </ol>
</section>
<section class="content">   

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách BSC/RNC</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div id="tablePagingId">

                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên</th>                                            
                                    <th>Ngày hoạt động</th>
                                    <th>Hoàn cảnh ra đời</th>
                                    <th>Ngày đăng ký</th>
                                    <th>Ngày kiểm duyệt</th>
                                    <th>Ngày Cấp phép</th>
                                    <th>TYPE</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="temp" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + (status.index)}</td>
                                        <td>${temp.name}</td>
                                        <td>${temp.ngayHoatDong}</td>
                                        <td>${temp.hoanCanhRaDoi}</td>
                                        <td>${temp.ngayDangKy}</td>
                                        <td>${temp.ngayKiemDuyet}</td>
                                        <td>${temp.ngayCapPhep}</td>
                                        <td>${temp.typeBSCRNC}</td>

                                        <td>
                                            <a href="<%=request.getContextPath()%>/bscRnc/view/${temp.nodeId}"
                                               title="Sửa">
                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>  
                                            <a href="<%=request.getContextPath()%>/bscRnc/delete/${temp.nodeId}"
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


