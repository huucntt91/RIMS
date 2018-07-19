<%-- 
    Document   : bcll
    Created on : Nov 15, 2016, 11:01:34 AM
    Author     : Cyano
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- multiselect -->
<!--<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>-->
<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <ul class="nav nav-pills" id="tabs">
        <li class="active"><a data-toggle="tab" href="#home">Quy hoạch</a></li>
        <li><a data-toggle="tab" href="#menu1">Quy hoạch tỉnh</a></li>
        <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_QH_RIMS.mp4');" >Hướng dẫn</button>
    </ul>


</section>
<section class="content">
    <div class="tab-content">
        <input type="hidden" name="tab_id" id="tab_id" value="${tab}" >
        <div id="home" class="tab-pane fade in active">
            <ol class="breadcrumb">
                <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/project/preAddQh/'" >
                    <span><i class="fa fa-fw fa-plus"></i>Thêm quy hoạch</span> 
                </button>
            </ol>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Danh sách quy hoạch</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <div id="tablePagingId">
                                <button type="button" id="export" class="btn btn-primary" 
                                    onclick="location.href = '<%=request.getContextPath()%>/jasper/exportQuyHoach'">Export excel</button>
                                <table id="table1" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã quy hoạch</th>
                                            <th>Tên quy hoạch</th>
                                            <th>Quy hoạch cha</th>
                                            <th>Chức năng</th>
                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="project" items="${projects}" varStatus="status">                                        
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${project.projectCode}</td>
                                                <td>${project.projectName}</td>
                                                <td>${project.parentName}</td>                                        
                                                <td>
                                                    <a href="<%=request.getContextPath()%>/project/viewQuyHoach/${project.qhId}"
                                                       title="Sửa">
                                                        <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                    </a>
                                                    &nbsp;
                                                    <a href="<%=request.getContextPath()%>/project/deleteProject/${project.qhId}"
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
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </div>
        <div id="menu1" class="tab-pane fade">
            <ol class="breadcrumb">
                <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/project/preAddQhTinh/'" >
                    <span><i class="fa fa-fw fa-plus"></i>Thêm quy hoạch tỉnh</span> 
                </button>
            </ol>
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Danh sách quy hoạch tỉnh</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <div id="tablePagingId">
                                <button type="button" id="export" class="btn btn-primary" 
                                    onclick="location.href = '<%=request.getContextPath()%>/jasper/exportQuyHoachTinh'">Export excel</button>
                                <table id="table2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th rowspan="2">STT</th>
                                            <th rowspan="2">Mã Quy hoạch</th>
                                            <th rowspan="2">Tên quy hoạch</th>
                                            <th rowspan="2">Mã quy hoạch tỉnh</th>
                                            <th rowspan="2">Tỉnh</th>
                                            <th colspan="3">2G</th>
                                            <th colspan="3">3G 2100MHz</th>
                                            <th rowspan="2">Tổng xây mới CSHT (cả 2G&3G)</th>
                                            <th rowspan="2">Tổng thiết bị 3G 900MHz</th>
                                            <th rowspan="2">LTE</th>
                                            <th rowspan="2">Chức năng</th>

                                        </tr>
                                        <tr>
                                            <th>Số lượng chỉ 2G</th>
                                            <th>Số lượng 2G & 3G</th>                                            
                                            <th>Tổng thiết bị</th>
                                            <th >Lắp trên CSHT có sẵn</th>
                                            <th >Lắp trên CSHT mới</th>
                                            <th >Tổng thiết bị</th>
                                        </tr>

                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="projectInfor" items="${projectInfors}" varStatus="status">                                        
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${projectInfor.projectCode}</td>
                                                <td>${projectInfor.projectName}</td>
                                                <td>${projectInfor.maQhTinh}</td>
                                                <td>${projectInfor.province}</td>
                                                <td>${projectInfor.soLuongChi2G}</td>
                                                <td>${projectInfor.soLuong2G3G}</td>
                                                <td>${projectInfor.tongThietBi2G3G}</td>
                                                <td>${projectInfor.cshtNew}</td>
                                                <td>${projectInfor.chstActive}</td>
                                                <td>${projectInfor.tongThieBi}</td>
                                                <td>${projectInfor.tongCshtXaymoi}</td>
                                                <td>${projectInfor.tongThietBi3G}</td>
                                                <td>${projectInfor.lte}</td>
                                                <td>

                                                    <a href="<%=request.getContextPath()%>/project/viewQuyHoachTinh/${projectInfor.qhInforId}"
                                                       title="Sửa">
                                                        <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                    </a>
                                                    &nbsp;
                                                    <a href="<%=request.getContextPath()%>/project/deleteProjectInfor/${projectInfor.qhInforId}"
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
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </div>

    </div>








</section>

<%@include file="../../include/footer.jsp"%> 
<script>
    $(document).ready(function () {
//        var tab = $('#tab_id').val();
        var tab = '${tab}';
        if (tab == '1')
            $('#tabs a[href="#menu1"]').tab('show');
        else
            $('#tabs a[href="#home"]').tab('show');
        $('#qhId').val($('#quyHoachId').val());
        $('#tinhTpId').val($('#province').val());
        $('#parentId').val($('#projectParent').val());
        $("#table1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
        $("#table2").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });



    });

    //post ajax
//     $("#btnAdd").click(function () {
//
//            $.post("${pageContext.request.contextPath}/project/update",
//                    {
//                        projectCode: $('#projectCode').val(),
//                        projectName: $('#projectName').val()
//                    },
//                    function (data, status) {
//                        alert("Data: " + data + "\nStatus: " + status);
//                    });
//        });

    //xu ly su kien khi select option
    $('#projectParent').on('change', function (e) {
        var valueSelected = this.value;
        $('#parentId').val(valueSelected);
    });

    $('#quyHoachId').on('change', function (e) {
        var valueSelected = this.value;
        $('#qhId').val(valueSelected);
    });
    $('#province').on('change', function (e) {
        var valueSelected = this.value;
        $('#tinhTpId').val(valueSelected);
    });
    
    function hdsd(media) {
                window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
            }
            
</script>
