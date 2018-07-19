<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>
        Quản lý Đơn Vị <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_QUANLYDONVI_RIMS.mp4');" >Hướng dẫn</button>
    </h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/dv/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm Đơn Vị</span> 
        </button>
    </ol>
</section>
<section class="content">            

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách Đơn Vị</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên Đơn Vị</th>                                            
                                <th>Địa chỉ</th>
                                <th>Tỉnh</th>
                                <th>Huyện</th>
                                <th>Phường Xã</th>
                                <th>Đơn Vị Cha</th>
                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="dvBO" items="${list_cp}" varStatus="status">                                        
                                <tr>
                                    <td>${status.index+1}</td>
                                    <td>${dvBO.tenDonVi}</td>
                                    <td>${dvBO.diaChiDonVi}</td>
                                    <td>${dvBO.tenTinhTp}</td>
                                    <td>${dvBO.tenQuanHuyen}</td>
                                    <td>${dvBO.tenPhuongXa}</td>

                                    <td>${dvBO.tenParent}</td>

                                    <td>
                                        <a href="<%=request.getContextPath()%>/dv/view/${dvBO.donViId}"
                                           title="Sửa">
                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                        </a>  
                                        <a href="<%=request.getContextPath()%>/dv/delete/${dvBO.donViId}"
                                           title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                            <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                        </a>
                                        &nbsp;

                                    </td>
                                </tr>
                            </c:forEach>                                       							
                        </tbody>                                    
                    </table>   
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>

<script>
    $(function () {
        $("#example1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
    });
    
    function hdsd(media) {
        window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
    }

</script>
