
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../include/header.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js"></script>
<div class="content-header">
    <h1>Thông tin bảo dưỡng</h1>
</div>
<section class="content">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title"><a data-toggle="collapse" href="#tuNguon1Div">+ Cập nhật thông tin bảo dưỡng</a></h3>
        </div>
        <div class="panel-body panel-collapse collapse" id="tuNguon1Div">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title"></h3>
                        </div>
                        <form:form method="POST" action="${pageContext.request.contextPath}/nodes/baoduong/update" commandName="cellNewExcelBO"
                                   enctype="multipart/form-data">
                            <div class="box-body" >
                                <div class="form-group">

                                    <div class="col-xs-6">                                    
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn file import</label>
                                            <form:input class="form-control"  id="file3"
                                                        type="file"  path="file"  value="${groupContactForm.name}"/>

                                        </div>
                                    </div>                                   
                                    <div class="col-xs-6">                                    
                                        <button type="button" onclick='window.location.href = "${pageContext.request.contextPath}/resources/excel/Template_NET_BaoDuong.xlsx"' class="btn btn-facebook"><spring:message code="btn.download.template"/></button>
                                    </div>
                                </div>
                            </div>
                            </br>
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">Cập nhật dữ liệu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>                        
    </div> 

    <div class="box">
        <div class="box-header">
            <h3 class="box-title">Tìm kiếm</h3> 
        </div>
        <div class="box-body">

            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Mã trạm</label>
                        <input type="text" class="form-control"  id="nodeCode" name="nodeCode" value="${node_code}" />
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Loại trạm</label>
                        <input type="text" class="form-control"  id="neTypeId" name="neTypeId" />
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="box-footer" align="center" >
                <button type="button" id="btnSearch" onclick="this.disabled = true; search();" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
            </div>
        </div>
        <!-- /.box-body -->
    </div>

    <div id="tabs" class="tab-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Danh sách bảo dưỡng của trạm</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/reportCSHT/exportExcel?khuvucId=' + $('#khuvucId').val()
                                        + '&tinhTpId=' + $('#tinhTpId').val() + '&quanHuyenId=' + $('#quanHuyenId').val() + '&phuongXaId=' + $('#phuongXaId').val() + '&buildingCode=' + $('#buildingCode').val()
                                        + '&buildingName=' + $('#buildingName').val()" >
                            <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Xuất Excel</span> 
                        </button>
                        <div id="tablePagingId" style="overflow-y: scroll;">
                            <table id="table0" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã Trạm</th>
                                        <th>Loại</th>
                                        <th>Ngày bảo dưỡng</th>
                                        <th>Đơn vị thực hiện</th>
                                        <th>Ghi chú</th>
                                        <th>ne_type_id</th>
                                        <th>baoduong_id</th>
                                        <th>node_id</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
    </div>

</section>

<%@include file="../../include/footer.jsp"%>
<script>
    $(document).ready(function () {

        // Highlight selected row
        $(".indenter").each(function () {
            $(this).css('background-image', $(this).find('a').css('background-image'));
        });

        var table0 = $("#table0").DataTable({
            searchDelay: 1000,
            "pageLength": 10,
            "serverSide": true,
            ajax: {
                "url": '${pageContext.request.contextPath}/nodes/searchBaoDuong',
                "type": "GET"
            },
            //dinh nghia cac cloumn giong voi cloumn trả về trong database
            "columns": [
                {"name": "stt", "orderable": false, "searchable": false},
                {"name": "ma_node"},
                {"name": "ne_type"},
                {"name": "ngay_bao_duong"},
                {"name": "don_vi_thuc_hien"},
                {"name": "ghi_chu"},
                {"name": "ne_type_id", "visible": false},
                {"name": "baoduong_id", "visible": false},
                {"name": "node_id", "visible": false}
            ],

            "zeroRecords": "Không có dữ liệu được tìm thấy",
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
        search();
    });
    function  search() {
        var table0 = $('#table0').DataTable();
        table0.columns(1).search($('#nodeCode').val());
        table0.columns(2).search($('#neTypeId').val());
        //vẽ bảng
        table0.draw();
        $('#btnSearch').attr("disabled", false);
    }

</script>