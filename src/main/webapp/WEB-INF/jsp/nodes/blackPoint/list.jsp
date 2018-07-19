
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content-header">
    <h1>
        Quản lý điểm đen
    </h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/excel_black_point/init'" >
            <span><i class="fa fa-fw fa-plus"></i>Import điểm đen</span> 
        </button>
    </ol>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách điểm đen</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <button type="button" id="export" class="btn btn-primary" 
                            onclick="exportExcel()">Export excel</button>
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tỉnh/TP</th>
                                <th>Quận/Huyện</th> 
                                <th>Tên</th>
                                <th>Mã</th>
                                <th>Latitude</th> 
                                <th>Longitude</th>
                                <th>Địa chỉ</th>
                                <th>Mô tả</th>
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="item" items="${list}" varStatus="status">                                        
                                <tr>
                                    <td>${startRow + (status.index + 1)}</td>
                                    <td>${item.tenTinhTp}</td>
                                    <td>${item.tenQuanHuyen}</td>
                                    <td>${item.name}</td>
                                    <td>${item.code}</td>
                                    <td>${item.lat}</td>
                                    <td>${item.lon}</td>
                                    <td>${item.address}</td>
                                    <td>${item.des}</td>
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
<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/fileSaver.js" type="text/javascript"></script>

<script>
   
    $(function () {
        $("#example1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
    });
     function exportExcel(){
        var url = window.location.href;
        var param = '';
        if(url.indexOf('?') > -1){
            param = url.substring(url.indexOf('?'), url.length);
        }
        var urlGet = '<%=request.getContextPath()%>/broadband/export/blackpoint'  + param;
        $.get(urlGet, function (data) {
            fnExcelReport(data,"Danh_sach_diem_den");
        });
    }
</script>