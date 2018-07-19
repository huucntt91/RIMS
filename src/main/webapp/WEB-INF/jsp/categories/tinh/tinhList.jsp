<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>
        Quản lý Tỉnh/Thành Phố
    </h1>
    <ol class="breadcrumb">

        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/tinh/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm Tỉnh/TP</span> 
        </button>

    </ol>
</section>
<section class="content">            

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách Tỉnh/TP</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên Tỉnh/TP</th>                                            
                                <th>Mã Tỉnh/TP</th>
                                <th>Khu Vực</th>

                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="tinhBO" items="${tinhList}" varStatus="status">                                        
                                <tr>
                                    <td>${status.index+1}</td>
                                    <td>${tinhBO.tenTinhTp}</td>
                                    <td>${tinhBO.maTinhTp}</td>
                                    <td>${tinhBO.khuVuc}</td>          
                                    <td>
                                        <a href="<%=request.getContextPath()%>/tinh/view/${tinhBO.tinhTpId}"
                                           title="Sửa">
                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                        </a>  
                                        <a href="<%=request.getContextPath()%>/tinh/delete/${tinhBO.tinhTpId}"
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
</script>
