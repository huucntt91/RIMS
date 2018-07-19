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
            <h3 class="box-title">Tìm kiếm trạm quy hoạch</h3> 
            <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_TRAM_QH_RIMS.mp4');" >Hướng dẫn</button>
        </div>
        <div class="box-body">
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Mã trạm quy hoạch</label>
                        <input type="text" class="form-control"  id="MA_QUY_HOACH" name="MA_QUY_HOACH" placeholder="Mã trạm quy hoạch"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Tên trạm quy hoạch</label>
                        <input type="text" class="form-control"  id="TEN_QUY_HOACH" name="TEN_QUY_HOACH" placeholder="Tên trạm quy hoạch"/>
                    </div>
                </div>
            </div>

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
                        <label class=" input-group-addon">Tỉnh/Tp</label>
                        <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control"  > 
                        </select>
                        <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group" > 
                    <div class="input-group">
                        <label class=" input-group-addon" >Loại công nghệ</label>
                        <select name="ID_LOAI_CONG_NGHE" id="ID_LOAI_CONG_NGHE" class="form-control" >
                            <option value="">-Chọn loại công nghệ-</option>
                            <c:forEach var="loaiCongNghe" items="${loaiCongNgheLst}">
                                <option value="${loaiCongNghe.id_loai_cong_nghe}" >${loaiCongNghe.ten_loai_cong_nghe}</option>
                            </c:forEach>
                        </select>  
                    </div>    
                </div>
            </div>

            <div class="col-md-6">
                        <div class="form-group" > 
                            <div class="input-group">
                                <label class=" input-group-addon" >Trạng thái</label>
                                <select name="SEARCH_STATUS" id="SEARCH_STATUS" class="form-control" >
                                    <option value="">-Trạng thái-</option>
                                    <option value="1">-Chưa duyệt-</option>
                                    <option value="60">-Đã duyệt-</option>
                                </select>  
                            </div>    
                        </div>
            </div>


            <div class="clearfix"></div>
            <div class="box-footer" align="center" >
                <button type="button" id="btnSearch" onclick="this.disabled = true;searchQh();" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
            </div>
        </div>
        <!-- /.box-body -->

    </div>                

    <ol class="breadcrumb">
        <c:if test='${fn:containsIgnoreCase(sessionScope.function,"addqh")}'>
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/project_station/preAddThongTinChung'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm trạm quy hoạch</span> 
            </button>
        </c:if>
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/excel_project_station/init')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/excel_project_station/init'" >

                <span>  <img width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Thêm trạm QH với Excel</span> 
            </button>
        </c:if>
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/excel_update_project_station/init')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/excel_update_project_station/init'" >
                <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Sửa trạm QH với Excel</span> 
            </button>
        </c:if>
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'viewqh')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/project_station/exportExcel?maQh=' + $('#MA_QUY_HOACH').val()
                            + '&tenQh=' + $('#TEN_QUY_HOACH').val() + '&khuVucIds=' + $('#khuvucId').val() + '&tinhTpIds=' + $('#tinhTpId').val()
                            + '&loaiCongNghe=' + $('#ID_LOAI_CONG_NGHE').val()" >
                <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Xuất Excel</span> 
            </button>
        </c:if>
    </ol>

    <div id="tabs" class="tab-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Danh sách trạm quy hoạch</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <div id="tablePagingId">

                            <table id="table0" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã Quy hoạch</th>
                                        <th>Tên quy hoạch</th>
                                        <th>Năm khởi tạo</th>
                                        <th>Longitude</th>
                                        <th>Latitude</th>
                                        <th>Loại CN</th>
                                        <th>Băng tần</th>
                                        <th>Chương trình PT CSHT</th>
                                        <th>Trạng thái</th>
                                        <th>Thông tin chung</th>
                                        <th>Cam kết thiết bị</th>
                                        <th>CSHT</th>
                                        <th>Nguồn DC</th>
                                        <th>Antena</th>
                                        <th>Chức năng</th>
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

<div id="duyetDialog" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" id="duyetModalTitle"></h4>
            </div>
            <div class="modal-body">
                <form method="post" action="${pageContext.request.contextPath}/project_station/approve" commandName="tramQuyHoach">
                    <div class="box-body table-responsive">

                        <div class="form-group" >
                            <div class="input-group">
                                <label class=" input-group-addon" id ="duyetLabel"></label>
                            </div>
                        </div>
                        <div class="form-group" >
                            <textarea rows="4" type="text" class="form-control" name="NOTE" id="NOTE"  ></textarea>
                            <input type="hidden"  class="form-control" name="tramQhId" id="tramQhId">
                            <input type="hidden"  class="form-control" name="TRANG_THAI_TRAM" id="TRANG_THAI_TRAM">
                        </div>

                    </div>
                    <div class="modal-footer">          
                        <button type="submit" class="btn btn-default">Đồng ý</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                    </div>
                </form>
            </div>

        </div>
    </div>                 
</div>

<div id="viewTramQh" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Thông tin chi tiết</h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <div id="divDetail"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>



<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<%@include file="index_js.jsp"%>
<script>
                $(document).ready(function () {
                    $('#tinhTpId').multiselect(({
                        maxHeight: 200,
                        buttonWidth: '300px',
                        enableFiltering: true,
                        includeSelectAllOption: true,
                        onChange: function (element, checked) {
                        }
                    }));
                    $('#khuvucId').multiselect(({
                        maxHeight: 200,
                        buttonWidth: '300px',
                        enableFiltering: true,
                        includeSelectAllOption: true,
                        onChange: function (element, checked) {
                        }
                    }));
                    activaTab(<%= request.getAttribute("tab")%>);
                    var table0 = $("#table0").DataTable({
                        "pageLength": 10,
                        "serverSide": true,
                        ajax: '${pageContext.request.contextPath}/project_station/initThongTinChung',
                        "columns": [
                            {"name": "STT", "orderable": false},
                            {"name": "MA_QUY_HOACH"},
                            {"name": "TEN_QUY_HOACH"},
                            {"name": "NAM_KHOI_TAO"},
                            {"name": "LONGITUDE"},
                            {"name": "LATITUDE"},
                            {"name": "ten_loai_cong_nghe", "orderable": false},
                            {"name": "ten_bang_tan", "orderable": false},
                            {"name": "ten_loai_pt_csht", "orderable": false},
                            {"name": "trang_thai", "orderable": false},
                            {"name": "thong_tin_chung", "orderable": false},
                            {"name": "cam_ket_thiet_bi", "orderable": false},
                            {"name": "csht", "orderable": false},
                            {"name": "nguon_dc", "orderable": false},
                            {"name": "antena", "orderable": false},
                            {"name": "duyet_huy_duyet", "orderable": false},
                            {"name": "TRAM_QH_ID", "visible": false},
                            {"name": "TINHTP_ID", "visible": false},
                            {"name": "ID_LOAI_CONG_NGHE", "visible": false},
                            {"name": "TRANG_THAI_TRAM", "visible": false},
                            {"name": "KHU_VUC", "visible": false}
                        ],
                        "createdRow": function (row, data, index) {
                            // xử lý cột trạng thái trong row thêm link xử lý

                            var td_10_id = "td-10-" + index;
                            $('td', row).eq(10).attr("id", td_10_id);
                            // xử lý cột lựa chọn nội dung
    <c:if test='${fn:contains(classAtrrEdit,"THONG_TIN_CHUNG")}'>
                            $('td', row).eq(10).html('<a href="<%=request.getContextPath()%>/project_station/viewThongTinChung/' + data[17] + '" title="Cập nhật thông tin">\n\
                                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png"> \n\
                                                            </a>');
    </c:if>
                            // xử lý cột trạng thái trong row thêm link xử lý
    <c:if test='${fn:contains(classAtrrEdit,"CAM_KET_THIET_BI")}'>
                            var td_11_id = "td-11-" + index;
                            $('td', row).eq(11).attr("id", td_11_id);
                            // xử lý cột lựa chọn nội dung
                            $('td', row).eq(11).html('<a href="<%=request.getContextPath()%>/project_station/viewCamKet/' + data[17] + '" title="Cập nhật thông tin">\n\
                                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png"> \n\
                                                            </a>');
    </c:if>
    <c:if test='${fn:contains(classAtrrEdit,"CO_SO_HA_TANG")}'>
                            var td_12_id = "td-12-" + index;
                            $('td', row).eq(12).attr("id", td_12_id);
                            // xử lý cột lựa chọn nội dung
                            $('td', row).eq(12).html('<a href="<%=request.getContextPath()%>/project_station/viewCoSoHaTang/' + data[17] + '" title="Cập nhật thông tin">\n\
                                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png"> \n\
                                                            </a>');
    </c:if>
    <c:if test='${fn:contains(classAtrrEdit,"NGUON_DC")}'>
                            var td_13_id = "td-13-" + index;
                            $('td', row).eq(13).attr("id", td_13_id);
                            // xử lý cột lựa chọn nội dung
                            $('td', row).eq(13).html('<a href="<%=request.getContextPath()%>/project_station/viewNguonDC/' + data[17] + '" title="Cập nhật thông tin">\n\
                                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png"> \n\
                                                            </a>');
                            var td_14_id = "td-14-" + index;
                            $('td', row).eq(14).attr("id", td_14_id);
    </c:if>
    <c:if test='${fn:contains(classAtrrEdit,"ANTENA")}'>
                            // xử lý cột lựa chọn nội dung

                            $('td', row).eq(14).html('<a href="<%=request.getContextPath()%>/project_station/viewAntena/' + data[17] + '" title="Cập nhật thông tin">\n\
                                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png"> \n\
                                                            </a>');
    </c:if>
                            var status = data[20];
                            var td_15_content = '';
    <c:if test='${fn:containsIgnoreCase(sessionScope.function,"viewqh")}'>
                            td_15_content += ' <a title="Chi tiết" onclick="showDetail(' + data[17] + ');"> <img src="<%=request.getContextPath()%>/image/icon/view.png"> </a> ';
    </c:if>
                            if (status === '60') {
    <c:if test='${fn:containsIgnoreCase(sessionScope.function,"approveqh")}'>
                                td_15_content += '<a title="Hủy duyệt" onclick="disapprove( ' + data[17] + ');"> <img src="<%=request.getContextPath()%>/image/icon/icon_delete.png"> </a>';
    </c:if>

                                $('td', row).eq(15).html(td_15_content);
                            } else {

                                var td_15_id = "td-15-" + index;
                                $('td', row).eq(15).attr("id", td_15_id);

    <c:if test='${fn:containsIgnoreCase(sessionScope.function,"approveqh")}'>
                                // xử lý cột lựa chọn nội dung
                                td_15_content += '<a title="Duyệt" onclick="approve(' + data[17] + ');"> <img src="<%=request.getContextPath()%>/image/icon/icon_check.png"> </a>';
    </c:if>
    <c:if test='${fn:containsIgnoreCase(sessionScope.function,"deleteqh")}'>
                                td_15_content += '<a title="Xóa" href="<%=request.getContextPath()%>/project_station/deleteTramQh/' + data[17] + '" onclick="return confirm(\'Bạn có muốn thực hiện xoá không?\');">\n\
                        <img src="<%=request.getContextPath()%>/image/icon/delete.png"> </a>';
    </c:if>
                                $('td', row).eq(15).html(td_15_content);
                            }

                        },
                        "zeroRecords": "Không có dữ liệu được tìm thấy",
                        "language": {
                            "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                        }
                    });
//                    $('#MA_QUY_HOACH').on('keyup', function () {
//                        table0.columns(1).search(this.value).draw();
//                    });
//                    $('#TEN_QUY_HOACH').on('keyup', function () {
//                        table0.columns(2).search(this.value).draw();
//                    });
//                    $('#tinhTpId').on('change', function () {
//                        var temp = "";
//                        $('#tinhTpId :selected').each(function (i, selected) {
//                            if (temp == "") {
//                                temp = temp + $(selected).val();
//                            } else {
//                                temp = temp + "," + $(selected).val();
//                            }
//                        });
//                        table0.columns(17).search(temp).draw();
//                    });
//                    $('#ID_LOAI_CONG_NGHE').on('change', function () {
//                        table0.columns(18).search(this.value).draw();
//                    });
//                    $('#SEARCH_STATUS').on('change', function () {
//                        table0.columns(19).search(this.value).draw();
//                    });
//                    $('#khuvucId').on('change', function () {
//                        var temp = "";
//                        $('#khuvucId :selected').each(function (i, selected) {
//                            if (temp == "") {
//                                temp = temp + $(selected).val();
//                            } else {
//                                temp = temp + "," + $(selected).val();
//                            }
//                        });
//                        table0.columns(20).search(temp).draw();
//                    });
                    searchQh();
                });
                function approve(tramQhId) {
// duyệt tram quy hoach
                    $("#duyetModalTitle").html('Duyệt trạm quy hoạch');
                    $("#duyetLabel").html('Lý do duyệt');
                    $(".modal-body #TRANG_THAI_TRAM").val('60');
                    $(".modal-body #tramQhId").val(tramQhId);
                    $(".modal-body #NOTE").val('');
                    $('#duyetDialog').modal('show');
                }


                function disapprove(tramQhId) {
// hủy duyệt trạm quy hoạch     
                    $("#duyetModalTitle").html('Hủy duyệt trạm quy hoạch');
                    $("#duyetLabel").html('Lý do hủy');
                    $(".modal-body #TRANG_THAI_TRAM").val('0');
                    $(".modal-body #tramQhId").val(tramQhId);
                    $(".modal-body #NOTE").val('');
                    $('#duyetDialog').modal('show');
                }
                function showDetail(tramQhId) {
                    var linkDetail = '${pageContext.request.contextPath}/project_station/detail/' + tramQhId;
                    $.get(linkDetail, function (data) {
                        $('#divDetail').html(data);
                        $('#viewTramQh').modal('show');
                    });
                    $('#divDetail').find('input, textarea, button, select').attr('disabled', 'disabled');
                }
                function hdsd(media) {
                    window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
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
                function  searchQh() {
                    var table0 = $('#table0').DataTable();
                    table0.columns(1).search($('#MA_QUY_HOACH').val());
                    table0.columns(2).search($('#TEN_QUY_HOACH').val());
                    var temp = "";
                    $('#tinhTpId :selected').each(function (i, selected) {
                        if (temp == "") {
                            temp = temp + $(selected).val();
                        } else {
                            temp = temp + "," + $(selected).val();
                        }
                    });
                    table0.columns(17).search(temp);
                    table0.columns(18).search($('#ID_LOAI_CONG_NGHE').val());
                    table0.columns(19).search($('#SEARCH_STATUS').val());

                    var temp1 = "";
                    $('#khuvucId :selected').each(function (i, selected) {
                        if (temp1 == "") {
                            temp1 = temp1 + $(selected).val();
                        } else {
                            temp1 = temp1 + "," + $(selected).val();
                        }
                    });
                    table0.columns(20).search(temp1);
                    table0.draw();
                    $('#btnSearch').attr("disabled", false);
                }
</script>

