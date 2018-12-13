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
                        <label class=" input-group-addon" style="min-width: 150px">Khu vực</label>
                        <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control" onchange="getTinhTp();"> 
                        </select> 
                        <input type="hidden" value="${khuvucIds}" id="khuvucIds"/>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" style="min-width: 150px">Tỉnh TP</label>

                        <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen();"  > 
                        </select>
                        <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                    </div>

                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" style="min-width: 150px">Quận/Huyện</label>
                        <select multiple="multiple" name="quanHuyenId" id="quanHuyenId" class="form-control" > 
                        </select>
                        <input type="hidden" value="${quanHuyenId}" id="quanHuyenIds"/>
                    </div>
                </div>
            </div>
            <!--<div class="col-md-6"> 
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon">Phường/Xã</label>
                        <select multiple="multiple" name="phuongXaId" id="phuongXaId" class="form-control"> 
                        </select>
                        <input type="hidden" value="${phuongXaId}" id="phuongXaIds"/>    
                    </div>
                </div>
            </div> -->
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" style="min-width: 150px">Chọn đối tượng</label>
                        <select name="neTypeId" id="neTypeId" class="form-control" onchange="generateTable();">
                            <option <c:if test='${ne_type_id == "5"}' > selected </c:if>  value="5">--- Cell 2G ---</option>
                            <option <c:if test='${ne_type_id == "6"}' > selected </c:if>  value="6">--- Cell 3G ---</option>
                            <option <c:if test='${ne_type_id == "7"}' > selected </c:if>  value="7">--- Cell 4G ---</option>
                            </select> 
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
    <ol class="breadcrumb">


    </ol>

    <div id="tabs" class="tab-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Báo cáo config cell</h3>
                    </div>
                    <!-- /.box-header -->
                    <div id="table" class="box-body table-responsive">
                        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/configCell/exportExcel?khuvucId=' + $('#khuvucId').val()
                                        + '&tinhTpId=' + $('#tinhTpId').val() + '&quanHuyenId=' + $('#quanHuyenId').val() + '&phuongXaId=' + $('#phuongXaId').val()
                                        + '&neTypeId=' + $('#neTypeId').val()" >
                            <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Xuất Excel</span> 
                        </button>
                        <div id="tablePaging5" style="overflow-y: scroll;">
                            <table id="table5" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>NeTypeId</th>
                                        <th>Mã Node</th>
                                        <th>Đơn vị quản lý</th>
                                        <th>Thiết bị</th>
                                        <th>Mã CSHT</th>
                                        <th>Khu vực</th>
                                        <th>Tỉnh/TP ID</th>
                                        <th>Tỉnh/TP</th>
                                        <th>Quận/Huyện Id</th>
                                        <th>Quận/Huyện</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>
                                        <th>Loại trạm</th>
                                        <th>Mã BTS</th>
                                        <th>Mã trạm dự án</th>
                                        <th>BSC</th>
                                        <th>Tên cell (Tên cho quản lý)</th>
                                        <th>Tên trên hệ thống</th>
                                        <th>Lac</th>
                                        <th>Ci</th>
                                        <th>Băng tần</th>
                                        <th>Bcch</th>
                                        <th>bsic</th>
                                        <th>Tch</th>
                                        <th>TrxConfig</th>
                                        <th>Cell Type</th>
                                        <th>Azimuth</th>
                                        <th>MechanicalTilt</th>
                                        <th>ElectricalTilt</th>
                                        <th>TotalTilt</th>
                                        <th>AntennaType</th>
                                        <th>AntennaHigh</th>
                                        <th>Chung Anten</th>
                                        <th>BosterTma</th>
                                        <th>SpecialCoverage</th>
                                        <th>AntennaGain</th>
                                        <th>Trạng thái</th>
                                        <th>Note</th>

                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div id="tablePaging6" style="overflow-y: scroll;">
                            <table id="table6" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>NeTypeId</th>
                                        <th>Mã Node</th>
                                        <th>Đơn vị quản lý</th>
                                        <th>Thiết bị</th>
                                        <th>Mã CSHT</th>
                                        <th>Khu vực</th>
                                        <th>Tỉnh/TP ID</th>
                                        <th>Tỉnh/TP</th>
                                        <th>Quận/Huyện Id</th>
                                        <th>Quận/Huyện</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>
                                        <th>Loại trạm</th>
                                        <th>Mã NODEB</th>
                                        <th>Mã trạm dự án</th>
                                        <th>RNC</th>
                                        <th>Tên cell (Tên cho quản lý)</th>
                                        <th>Tên trên hệ thống</th>
                                        <th>Lac</th>
                                        <th>Ci</th>
                                        <th>RAC</th>
                                        <th>Tên băng tần</th>
                                        <th>DL_UARFCN</th>
                                        <th>dlPsc</th>
                                        <th>cpichPower</th>
                                        <th>totalPower</th>
                                        <th>maxPower</th>
                                        <th>OAM IP</th>
                                        <th>Service IP</th>
                                        <th>Cell Type</th>
                                        <th>Azimuth</th>
                                        <th>MechanicalTilt</th>
                                        <th>ElectricalTilt</th>
                                        <th>TotalTilt</th>
                                        <th>AntennaType</th>
                                        <th>AntennaHigh</th>
                                        <th>Chung Anten</th>
                                        <th>noOfCarrier</th>
                                        <th>BosterTma</th>
                                        <th>SpecialCoverage</th>
                                        <th>Lý do</th>
                                        <th>AntennaGain</th>
                                        <th>Trạng thái</th>
                                        <th>Note</th>

                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div id="tablePaging7" style="overflow-y: scroll;">
                            <table id="table7" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>NeTypeId</th>
                                        <th>Mã Node</th>
                                        <th>Đơn vị quản lý</th>
                                        <th>Thiết bị</th>
                                        <th>Mã CSHT</th>
                                        <th>Khu vực</th>
                                        <th>Tỉnh/TP ID</th>
                                        <th>Tỉnh/TP</th>
                                        <th>Quận/Huyện Id</th>
                                        <th>Quận/Huyện</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>
                                        <th>Loại trạm</th>
                                        <th>Mã ENODEB</th>
                                        <th>ENODEB ID</th>
                                        <th>Mã trạm dự án</th>
                                        <th>Tên cell (Tên cho quản lý)</th>
                                        <th>Tên trên hệ thống</th>
                                        <th>Ci</th>
                                        <th>Tên băng tần</th>
                                        <th>Bandwidth</th>
                                        <th>UARFCN</th>
                                        <th>Pci</th>
                                        <th>tac</th>
                                        <th>lcrid</th>
                                        <th>OAM IP</th>
                                        <th>Service IP</th>
                                        <th>Cell Type</th>
                                        <th>Hoàn cảnh ra đời</th>
                                        <th>Azimuth</th>
                                        <th>MechanicalTilt</th>
                                        <th>ElectricalTilt</th>
                                        <th>TotalTilt</th>
                                        <th>AntennaType</th>
                                        <th>AntennaHigh</th>
                                        <th>Chung Anten</th>
                                        <th>noOfCarrier</th>
                                        <th>BosterTma</th>
                                        <th>SpecialCoverage</th>
                                        <th>Lý do</th>
                                        <th>AntennaGain</th>
                                        <th>Trạng thái</th>
                                        <th>Note</th>
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
                                getArea();
//                                                          
                                $('#tinhTpId').multiselect(({
                                    maxHeight: 300,
                                    buttonWidth: '100%',
                                    enableFiltering: true,
                                    includeSelectAllOption: true,
                                    onChange: function (element, checked) {
                                    }
                                }));

                                $('#quanHuyenId').multiselect(({
                                    maxHeight: 300,
                                    buttonWidth: '100%',
                                    enableFiltering: true,
                                    includeSelectAllOption: true,
                                    onChange: function (element, checked) {
                                    }
                                }));

                                $('#phuongXaId').multiselect(({
                                    maxHeight: 300,
                                    buttonWidth: '100%',
                                    enableFiltering: true,
                                    includeSelectAllOption: true,
                                    onChange: function (element, checked) {
                                    }
                                }));
                                $('#khuvucId').multiselect(({
                                    maxHeight: 300,
                                    buttonWidth: '100%',
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
                                generateTable();

                            });

                            function  search() {
                                $('#table').show();

                                var temp = "";
                                $('#khuvucId :selected').each(function (i, selected) {
                                    if (temp == "") {
                                        temp = temp + $(selected).val();
                                    } else {
                                        temp = temp + "," + $(selected).val();
                                    }
                                });
                                var temp1 = "";
                                $('#tinhTpId :selected').each(function (i, selected) {
                                    if (temp1 == "") {
                                        temp1 = temp1 + $(selected).val();
                                    } else {
                                        temp1 = temp1 + "," + $(selected).val();
                                    }
                                });
                                var temp2 = "";
                                $('#quanHuyenId :selected').each(function (i, selected) {
                                    if (temp2 == "") {
                                        temp2 = temp2 + $(selected).val();
                                    } else {
                                        temp2 = temp2 + "," + $(selected).val();
                                    }
                                });

                                var temp3 = "";
                                $('#phuongXaId :selected').each(function (i, selected) {
                                    if (temp3 == "") {
                                        temp3 = temp3 + $(selected).val();
                                    } else {
                                        temp3 = temp3 + "," + $(selected).val();
                                    }
                                });
                                if ($("#neTypeId").val() == 5) {
                                    $('#tablePaging5').show();
                                    $('#tablePaging6').hide();
                                    $('#tablePaging7').hide();
                                    var table5 = $('#table5').DataTable();
                                    table5.columns(1).search("5");
                                    table5.columns(6).search(temp);
                                    table5.columns(7).search(temp1);
                                    table5.columns(9).search(temp2);
                                    //vẽ bảng
                                    table5.draw();

                                } else if ($("#neTypeId").val() == 6) {
                                    $('#tablePaging5').hide();
                                    $('#tablePaging6').show();
                                    $('#tablePaging7').hide();
                                    var table6 = $('#table6').DataTable();
//                                table0.columns(1).search($('#buildingCode').val());
//                                table0.columns(2).search($('#buildingName').val());
                                    table6.columns(1).search("6");
                                    table6.columns(6).search(temp);
                                    table6.columns(7).search(temp1);
                                    table6.columns(9).search(temp2);
                                    //vẽ bảng
                                    table6.draw();

                                } else if ($("#neTypeId").val() == 7) {
                                    $('#tablePaging5').hide();
                                    $('#tablePaging6').hide();
                                    $('#tablePaging7').show();
                                    var table7 = $('#table7').DataTable();
//                                table0.columns(1).search($('#buildingCode').val());
//                                table0.columns(2).search($('#buildingName').val());
                                    table7.columns(1).search("7");
                                    table7.columns(6).search(temp);
                                    table7.columns(7).search(temp1);
                                    table7.columns(9).search(temp2);
                                    //vẽ bảng
                                    table7.draw();
                                }

                                $('#btnSearch').attr("disabled", false);
                            }
                            function generateTable() {
                                $('#table').hide();
                                $('#tablePaging5').hide();
                                $('#tablePaging6').hide();
                                $('#tablePaging7').hide();
                                if ($("#neTypeId").val() == 5) {
                                    $('#table6').DataTable().clear().destroy();
                                    $('#table7').DataTable().clear().destroy();
                                    $("#table5").DataTable({
                                        "deferLoading": 0,
                                        destroy: true,
                                        searchDelay: 1000,
                                        "pageLength": 10,
                                        "serverSide": true,
                                        ajax: {
                                            "url": '${pageContext.request.contextPath}/configCell/search',
                                            "type": "POST"
                                        },
                                        //dinh nghia cac cloumn giong voi cloumn trả về trong database
                                        "columns": [
                                            {"name": "stt", "orderable": false, "searchable": false},
                                            {"name": "ne_type_id", "orderable": false, "visible": false, "searchable": false},
                                            {"name": "ma_node"},
                                            {"name": "ten_don_vi"},
                                            {"name": "ten_thiet_bi"},
                                            {"name": "ma_building"},
                                            {"name": "khu_vuc", "orderable": false, "visible": false},
                                            {"name": "tinhtp_id", "orderable": false, "visible": false},
                                            {"name": "ten_tinh_tp"},
                                            {"name": "quanhuyen_id", "orderable": false, "visible": false},
                                            {"name": "ten_quan_huyen"},
                                            {"name": "longitude", "searchable": false},
                                            {"name": "latitude", "searchable": false},
                                            {"name": "ten_loai_tram", "searchable": false},
                                            {"name": "ma_node_cha"},
                                            {"name": "ma_tram_da"},
                                            {"name": "bsc_rnc_name","searchable": false},
                                            {"name": "ten_cell"},
                                            {"name": "ten_tren_he_thong"},
                                            {"name": "lac","searchable": false},
                                            {"name": "ci","searchable": false},
                                            {"name": "ten_bang_tan", "searchable": false},
                                            {"name": "bcch", "searchable": false},
                                            {"name": "bsic", "searchable": false},
                                            {"name": "tch", "searchable": false},
                                            {"name": "trx_config", "searchable": false},
                                            {"name": "cell_type", "searchable": false},
                                            {"name": "azimuth", "searchable": false},
                                            {"name": "mechanical_tilt", "searchable": false},
                                            {"name": "electrical_tilt", "searchable": false},
                                            {"name": "total_tilt", "searchable": false},
                                            {"name": "ten_loai_anten", "searchable": false},
                                            {"name": "antenna_high", "searchable": false},
                                            {"name": "chung_anten", "searchable": false},
                                            {"name": "boster_tma", "searchable": false},
                                            {"name": "special_coverage", "searchable": false},
                                            {"name": "antenna_gain", "searchable": false},
                                            {"name": "status", "searchable": false},
                                            {"name": "note", "searchable": false}
                                        ],

                                        "zeroRecords": "Không có dữ liệu được tìm thấy",
                                        "language": {
                                            "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                                        }
                                    });
                                } else if ($("#neTypeId").val() == 6) {
                                    console.log("gen cell3g config");
                                    $('#table5').DataTable().clear().destroy();
                                    $('#table7').DataTable().clear().destroy();
                                    $("#table6").DataTable({
                                        "deferLoading": 0,
                                        destroy: true,
                                        searchDelay: 1000,
                                        "pageLength": 10,
                                        dom: 'Bfrtip',
                                        "serverSide": true,
                                        ajax: {
                                            "url": '${pageContext.request.contextPath}/configCell/search',
                                            "type": "POST"
                                        },
                                        //dinh nghia cac cloumn giong voi cloumn trả về trong database
                                        "columns": [
                                            {"name": "stt", "orderable": false, "searchable": false},
                                            {"name": "ne_type_id", "orderable": false, "visible": false},
                                            {"name": "ma_node"},
                                            {"name": "ten_don_vi"},
                                            {"name": "ten_thiet_bi"},
                                            {"name": "ma_building"},
                                            {"name": "khu_vuc", "orderable": false, "visible": false},
                                            {"name": "tinhtp_id", "orderable": false, "visible": false},
                                            {"name": "ten_tinh_tp"},
                                            {"name": "quanhuyen_id", "orderable": false, "visible": false},
                                            {"name": "ten_quan_huyen"},
                                            {"name": "longitude", "searchable": false},
                                            {"name": "latitude", "searchable": false},
                                            {"name": "ten_loai_tram","searchable": false},
                                            {"name": "ma_node_cha"},
                                            {"name": "ma_tram_da"},
                                            {"name": "bsc_rnc_name","searchable": false},
                                            {"name": "ten_cell"},
                                            {"name": "ten_tren_he_thong"},
                                            {"name": "lac","searchable": false},
                                            {"name": "ci","searchable": false},
                                            {"name": "rac", "searchable": false},
                                            {"name": "ten_bang_tan","searchable": false},
                                            {"name": "dl_uarfcn", "searchable": false},
                                            {"name": "dl_psc", "searchable": false},
                                            {"name": "cpich_power", "searchable": false},
                                            {"name": "total_power", "searchable": false},
                                            {"name": "max_power", "searchable": false},
                                            {"name": "oam_ip", "searchable": false},
                                            {"name": "service_ip", "searchable": false},
                                            {"name": "cell_type", "searchable": false},
                                            {"name": "azimuth", "searchable": false},
                                            {"name": "mechanical_tilt", "searchable": false},
                                            {"name": "electrical_tilt", "searchable": false},
                                            {"name": "total_tilt", "searchable": false},
                                            {"name": "ten_loai_anten", "searchable": false},
                                            {"name": "antenna_high", "searchable": false},
                                            {"name": "chung_atena", "searchable": false},
                                            {"name": "no_of_carrier", "searchable": false},
                                            {"name": "ly_do", "searchable": false},
                                            {"name": "boster_tma", "searchable": false},
                                            {"name": "special_coverage", "searchable": false},
                                            {"name": "antenna_gain", "searchable": false},
                                            {"name": "status", "searchable": false},
                                            {"name": "note", "searchable": false}
                                        ],

                                        "zeroRecords": "Không có dữ liệu được tìm thấy",
                                        "language": {
                                            "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                                        }
                                    });

                                } else if ($("#neTypeId").val() == 7) {
                                    console.log("gen cell4g config");
                                    $('#table5').DataTable().clear().destroy();
                                    $('#table6').DataTable().clear().destroy();
                                    $("#table7").DataTable({
                                        "deferLoading": 0,
                                        destroy: true,
                                        searchDelay: 1000,
                                        "pageLength": 10,
                                        "serverSide": true,
                                        ajax: {
                                            "url": '${pageContext.request.contextPath}/configCell/search',
                                            "type": "POST"
                                        },
                                        //dinh nghia cac cloumn giong voi cloumn trả về trong database
                                        "columns": [
                                            {"name": "stt", "orderable": false, "searchable": false},
                                            {"name": "ne_type_id", "orderable": false, "visible": false},
                                            {"name": "ma_node"},
                                            {"name": "ten_don_vi"},
                                            {"name": "ten_thiet_bi"},
                                            {"name": "ma_building"},
                                            {"name": "khu_vuc", "orderable": false, "visible": false},
                                            {"name": "tinhtp_id", "orderable": false, "visible": false},
                                            {"name": "ten_tinh_tp"},
                                            {"name": "quanhuyen_id", "orderable": false, "visible": false},
                                            {"name": "ten_quan_huyen"},
                                            {"name": "longitude","searchable": false},
                                            {"name": "latitude","searchable": false},
                                            {"name": "ten_loai_tram","searchable": false},
                                            {"name": "ma_node_cha"},
                                            {"name": "enodeb_id","searchable": false},
                                            {"name": "ma_tram_da"},
                                            {"name": "ten_cell"},
                                            {"name": "ten_tren_he_thong"},
                                            {"name": "ci","searchable": false},
                                            {"name": "ten_bang_tan", "searchable": false},
                                            {"name": "bandwidth", "searchable": false},
                                            {"name": "uarfcn", "searchable": false},
                                            {"name": "pci", "searchable": false},
                                            {"name": "tac", "searchable": false},
                                            {"name": "lcrid", "searchable": false},
                                            {"name": "oam_ip", "searchable": false},
                                            {"name": "service_ip", "searchable": false},
                                            {"name": "cell_type", "searchable": false},
                                            {"name": "hoan_canh_ra_doi", "searchable": false},
                                            {"name": "azimuth", "searchable": false},
                                            {"name": "mechanical_tilt", "searchable": false},
                                            {"name": "electrical_tilt", "searchable": false},
                                            {"name": "total_tilt", "searchable": false},
                                            {"name": "ten_loai_anten", "searchable": false},
                                            {"name": "antenna_high", "searchable": false},
                                            {"name": "chung_atena", "searchable": false},
                                            {"name": "no_of_carrier", "searchable": false},
                                            {"name": "ly_do", "searchable": false},
                                            {"name": "boster_tma", "searchable": false},
                                            {"name": "special_coverage", "searchable": false},
                                            {"name": "antenna_gain", "searchable": false},
                                            {"name": "status", "searchable": false},
                                            {"name": "note", "searchable": false}

                                        ],

                                        "zeroRecords": "Không có dữ liệu được tìm thấy",
                                        "language": {
                                            "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                                        }
                                    });
                                }

                            }
                            function getArea() {
                                var khuVucIds = $("#khuvucIds").val();
                                $.get("${pageContext.request.contextPath}/mane/getArea", function (data) {
                                    var html = '';
                                    if (data.length > 0) {
                                        data.forEach(function (data) {
                                            var htmlx = '<option value="' + data.id + '" ';
                                            if (khuVucIds.indexOf(data.id) > -1) {
                                                htmlx += ' selected="selected" ';
                                            }
                                            htmlx += '>' + data.name + '</option>';
                                            html += htmlx;
                                        });
                                    }
                                    $('#khuvucId').html(html);
                                    $('#khuvucId').multiselect('rebuild');
                                });
                            }

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

</script>

