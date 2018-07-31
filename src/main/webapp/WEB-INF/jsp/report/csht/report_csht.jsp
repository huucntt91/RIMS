<%-- 
    Document   : bcll
    Created on : Nov 15, 2016, 11:01:34 AM
    Author     : Cyano
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../include/header.jsp"%>

<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>


<div class="container">
    <div class="box">
        <div class="box-header">
            <h3 class="box-title">Tìm kiếm</h3> 
        </div>
        <div class="box-body">
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon">Khu vực</label>
                        <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control" onchange="getTinhTp();"> 
                            <c:forEach var="tinhBO" items="${khuvucList}">
                                <option  <c:if test='${fn:contains(khuvucId,tinhBO.id)}' >  selected="selected" </c:if>
                                                                                            value="${tinhBO.id}"  
                                                                                            >${tinhBO.name}</option>
                            </c:forEach>
                        </select>                                  
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon">Tỉnh TP</label>

                        <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen();"  > 
                        </select>
                        <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                    </div>

                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon">Quận/Huyện</label>
                        <select multiple="multiple" name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa();"> 
                        </select>
                        <input type="hidden" value="${quanHuyenId}" id="quanHuyenIds"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6"> 
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon">Phường/Xã</label>
                        <select multiple="multiple" name="phuongXaId" id="phuongXaId" class="form-control"> 
                        </select>
                        <input type="hidden" value="${phuongXaId}" id="phuongXaIds"/>    
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Mã CSHT</label>
                        <input type="text" class="form-control"  id="buildingCode" name="buildingCode" />
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Tên CSHT</label>
                        <input type="text" class="form-control"  id="buildingName" name="buildingName" />
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
                        <h3 class="box-title">Danh sách CSHT và trạm gắn vào</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <div id="tablePagingId">

                            <table id="table0" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã CSHT</th>
                                        <th>Tên CSHT</th>
                                        <th>Mã trạm</th>
                                        <th>Tên quản lý</th>
                                        <th>Tên trên hệ thống</th>
                                        <th>Loại</th>
                                        <th>Khu vực</th>
                                        <th>Tỉnh tp id</th>
                                        <th>Tỉnh</th>
                                        <th>Quận/huyện id</th>
                                        <th>Quận/huyện</th>
                                        <th>Phường xã id</th>
                                        <th>Phường xã</th>
                                        <th>Longitude</th>
                                        <th>Latitude</th>
                                        <th>Mã kiểm định</th>
                                        <th>Ngày hiệu lực</th>
                                        <th>Ngày hết hiệu lực</th>
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
</div>

<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<script>
                    $(document).ready(function () {

                        // Highlight selected row
                        $(".indenter").each(function () {
                            $(this).css('background-image', $(this).find('a').css('background-image'));
                        });

//                                                          
                        $('#tinhTpId').multiselect(({
                            maxHeight: 300,
                            buttonWidth: '300px',
                            enableFiltering: true,
                            includeSelectAllOption: true,
                            onChange: function (element, checked) {
                            }
                        }));

                        $('#quanHuyenId').multiselect(({
                            maxHeight: 300,
                            buttonWidth: '300px',
                            enableFiltering: true,
                            includeSelectAllOption: true,
                            onChange: function (element, checked) {
                            }
                        }));

                        $('#phuongXaId').multiselect(({
                            maxHeight: 300,
                            buttonWidth: '300px',
                            enableFiltering: true,
                            includeSelectAllOption: true,
                            onChange: function (element, checked) {
                            }
                        }));
                        $('#khuvucId').multiselect(({
                            maxHeight: 300,
                            buttonWidth: '300px',
                            enableFiltering: true,
                            includeSelectAllOption: true,
                            onChange: function (element, checked) {
                            }
                        }));

                        if ($('#khuvucId').val() != null && $('#khuvucId').val() != '') {
                            getTinhTp();
                        }
                        if ($('#tinhTpIds').val() != null && $('#tinhTpIds').val() != '') {
                            getListHuyen();
                        }

                        if ($('#quanHuyenIds').val() != null && $("#quanHuyenId").val() != '') {
                            getListPhuongXa();
                        }
                        var table0 = $("#table0").DataTable({
                            //disable global search
                            //"searching": false,
                            searchDelay: 1000,
                            "pageLength": 10,
                            dom: 'Bfrtip',
                            buttons: [
                                'copy', 'csv', 'excel', 'pdf', 'print'],
                            "serverSide": true,
                            ajax: '${pageContext.request.contextPath}/reportCSHT/search',
                            //dinh nghia cac cloumn giong voi cloumn trả về trong database
                            "columns": [
                                {"name": "stt", "orderable": false, "searchable": false},
                                {"name": "ma_building"},
                                {"name": "building_name"},
                                {"name": "ma_node"},
                                {"name": "management_name"},
                                {"name": "ten_tren_he_thong"},
                                {"name": "ne_type"},
                                {"name": "khu_vuc", "orderable": false, "visible": false},
                                {"name": "tinhtp_id", "orderable": false, "visible": false},
                                {"name": "ten_tinh_tp"},
                                {"name": "quanhuyen_id", "orderable": false, "visible": false},
                                {"name": "ten_quan_huyen"},
                                {"name": "phuongxa_id", "orderable": false, "visible": false},
                                {"name": "ten_phuong_xa"},
                                {"name": "longitude"},
                                {"name": "latitude"},
                                {"name": "accreditation_code"},
                                {"name": "accre_start_date"},
                                {"name": "accre_end_date"}

                            ],

                            "zeroRecords": "Không có dữ liệu được tìm thấy",
                            "language": {
                                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                            }
                        });
                        search();
                    });
                    function getTinhTp() {
                        var id = $("#khuvucId").val();
                        var tinhTpIds = $("#tinhTpIds").val();
                        $.get("${pageContext.request.contextPath}/mane/getTinhTp?khuVucId=" + id, function (data) {
                            var html = '';
                            if (data.length > 0) {
                                data.forEach(function (data) {
                                    var htmlx = '<option value="' + data.tinhTpId + '" ';
                                    if (tinhTpIds.indexOf(data.tinhTpId) > -1) {
                                        htmlx += ' selected="selected" ';
                                    }
                                    htmlx += '>' + data.tenTinhTp + '</option>';
                                    html += htmlx;
                                });
                            }
                            $('#tinhTpId').html(html);
                            $('#tinhTpId').multiselect('rebuild');
                        });
                    }
                    function getListHuyen(tinh)
                    {
                        var id = $("#tinhTpId").val();
                        if (id === null) {
                            id = $("#tinhTpIds").val();
                        }
                        var quanHuyenIds = $("#quanHuyenIds").val();
                        $.get("${pageContext.request.contextPath}/mane/getQuanHuyen?tinhTpId=" + id, function (data) {
                            var html = '';
                            if (data.length > 0) {
                                data.forEach(function (data) {
                                    var htmlx = '<option value="' + data.quanHuyenId + '" ';
                                    if (quanHuyenIds.indexOf(data.quanHuyenId) > -1) {
                                        htmlx += ' selected="selected" ';
                                    }
                                    htmlx += '>' + data.tenQuanHuyen + '</option>';
                                    html += htmlx;
                                });
                            }
                            $('#quanHuyenId').html(html);
                            $('#quanHuyenId').multiselect('rebuild');
                        });
                    }

                    function getListPhuongXa()
                    {
                        var id = $("#quanHuyenId").val();
                        if (id === null) {
                            id = $("#quanHuyenIds").val();
                        }
                        if (id == null || id == '') {
                            return;
                        }
                        var phuongXaIds = $("#phuongXaIds").val();
                        $.get("${pageContext.request.contextPath}/mane/getPhuongXa?quanHuyenId=" + id, function (data) {
                            var html = '';
                            if (data.length > 0) {
                                data.forEach(function (data) {
                                    var htmlx = '<option value="' + data.phuongXaId + '" ';
                                    if (phuongXaIds.indexOf(data.phuongXaId) > -1) {
                                        htmlx += ' selected="selected" ';
                                    }
                                    htmlx += '>' + data.tenPhuongXa + '</option>';
                                    html += htmlx;
                                });
                            }
                            $('#phuongXaId').html(html);
                            $('#phuongXaId').multiselect('rebuild');
                        });
                    }
                    function  search() {
                        var table0 = $('#table0').DataTable();
                        table0.columns(1).search($('#buildingCode').val());
                        table0.columns(2).search($('#buildingName').val());

                        var temp1 = "";
                        $('#khuvucId :selected').each(function (i, selected) {
                            if (temp1 == "") {
                                temp1 = temp1 + $(selected).val();
                            } else {
                                temp1 = temp1 + "," + $(selected).val();
                            }
                        });
                        table0.columns(7).search(temp1);
                        var temp = "";
                        $('#tinhTpId :selected').each(function (i, selected) {
                            if (temp == "") {
                                temp = temp + $(selected).val();
                            } else {
                                temp = temp + "," + $(selected).val();
                            }
                        });
                        table0.columns(8).search(temp);

                        temp = "";
                        $('#quanHuyenId :selected').each(function (i, selected) {
                            if (temp == "") {
                                temp = temp + $(selected).val();
                            } else {
                                temp = temp + "," + $(selected).val();
                            }
                        });
                        table0.columns(10).search(temp);
                        //
                        temp = "";
                        $('#phuongXaId :selected').each(function (i, selected) {
                            if (temp == "") {
                                temp = temp + $(selected).val();
                            } else {
                                temp = temp + "," + $(selected).val();
                            }
                        });
                        table0.columns(12).search(temp);
                        //vẽ bảng
                        table0.draw();
                        $('#btnSearch').attr("disabled", false);
                    }
</script>

