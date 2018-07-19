<%-- 
    Document   : edit thong tin chung cua tram quy hoạch
    Created on : Dec 21, 2016, 2:36:54 PM
    Author     : Cyano
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../include/header.jsp"%>
<section class="content">  
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Quản lý thông tin chung của trạm</h3>
                </div>
                <div class="box-body">                    
                    <form method="post" action="${pageContext.request.contextPath}/project_station/add" commandName="tramQhBo">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin chung</h3>
                            </div>
                            <div class="panel-body">  
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Dự án quy hoạch</label>
                                            <select name="qhTinhId"  id="qhTinhId" class="form-control" >
                                                <option value="">---Chọn dự án quy hoạch ---</option>
                                                <c:forEach var="qhTinh" items="${qhTinhLst}">
                                                    <option value="${qhTinh.qhInforId}" 
                                                            <c:choose> <c:when test="${qhTinh.qhInforId == tramQuyHoach.qhTinhId}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose> 
                                                            >Quy hoạch: ${qhTinh.projectName} ; Tỉnh: ${qhTinh.province}  </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Mã trạm quy hoạch</label>
                                            <input type="text" class="form-control"  id="maQh" name="maQh" placeholder="Mã trạm quy hoạch" value="${tramQuyHoach.maQh}" required="true" />
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tên trạm quy hoạch</label>
                                            <input type="text" class="form-control"  id="tenQh" name="tenQh" placeholder="Tên trạm quy hoạch" value="${tramQuyHoach.tenQh}" required="true"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" >Nhập mã building</label>
                                            <input type="text"  class="form-control" id="buildingCode" name="buildingCode" placeholder="Mã building"  value="${tramQuyHoach.buildingCode}" /> 
                                            <input type="hidden" name="buildingId" value="" id="buildingId" />
                                            <input type="hidden" name="tramQhId" id="tramQhId">
                                            <input type="hidden" name="tinhTpId" id="tinhTpId" value="${tramQuyHoach.tinhTpId}"/>
                                            <input type="hidden" name="quanHuyenId" id="quanHuyenId" value="${tramQuyHoach.quanHuyenId}"/>
                                            <input type="hidden" name="phuongXaId" id="phuongXaId" value="${tramQuyHoach.phuongXaId}"/>
                                            <input type="hidden" name="DIA_CHI" id="DIA_CHI" maxlength="200"/>
                                            <span class="input-group-btn">
                                                <button  class="btn btn-success"  data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm building</button>
                                            </span>
                                        </div>
                                    </div>    
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Năm khởi tạo</label>
                                            <input type="text" class="form-control date_form"  id="namKhoiTao" name="namKhoiTao" placeholder="Năm khởi tạo" 
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.namKhoiTao}" />"  required="true"/>
                                        </div>
                                    </div>
                                </div>

                                <!-- long -->    
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Longitude</label>
                                            <input type="number" class="form-control"   id="longitude" step="0.000001" name="longitude" placeholder="Longitude" value="${tramQuyHoach.longitude}" required="true" />
                                        </div>
                                    </div>
                                </div>

                                <!-- lat -->    
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Latitude</label>
                                            <input type="number" class="form-control"  id="latitude" step="0.000001" name="latitude" placeholder="Latitude"  value="${tramQuyHoach.latitude}" required="true"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" > 
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Loại công nghệ</label>
                                            <select name="loaiCongNgheId" id="loaiCongNgheId" class="form-control" onchange="getBangTan();" required="true" >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="loaiCongNghe" items="${loaiCongNgheLst}">
                                                    <option value="${loaiCongNghe.id_loai_cong_nghe}" <c:choose> 
                                                                <c:when test="${loaiCongNghe.id_loai_cong_nghe == tramQuyHoach.loaiCongNgheId}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose> 
                                                            >${loaiCongNghe.ten_loai_cong_nghe}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>    
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" >     
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Băng tần</label>
                                            <select name="bangTanId" id="bangTanId" class="form-control" required="true">
                                                <option value="">-Băng Tần-</option>
                                            </select>  
                                        </div>    
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group" >       
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Chương trình PT CSHT</label>
                                            <select name="loaiPtCshtId" id="loaiPtCshtId" class="form-control" required="true" > 
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="loaiPtCsht" items="${loaiPtCshtLst}">
                                                    <option value="${loaiPtCsht.loai_pt_csht_id}" <c:choose> <c:when test="${loaiPtCsht.loai_pt_csht_id == tramQuyHoach.loaiPtCshtId}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${loaiPtCsht.ten_loai_pt_csht}</option>
                                                </c:forEach>
                                            </select> 
                                        </div>    
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" >     
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Trạng thái CSHT</label>
                                            <select name="trangThaiCshtId" id="trangThaiCshtId" class="form-control" required="true"  >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="trangThaiCsht" items="${trangThaiCshtLst}">
                                                    <option value="${trangThaiCsht.trang_thai_csht_id}" <c:choose> <c:when test="${trangThaiCsht.trang_thai_csht_id == tramQuyHoach.trangThaiCshtId}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${trangThaiCsht.ten_trang_thai_csht}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>    
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" >
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Đơn vị phê duyệt</label>
                                            <select name="donViPheDuyet" id="donViPheDuyet" class="form-control" required="true"  >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="donViPheDuyet" items="${donViLst}">
                                                    <option value="${donViPheDuyet.don_vi_phe_duyet_id}" <c:choose> <c:when test="${donViPheDuyet.don_vi_phe_duyet_id == tramQuyHoach.donViPheDuyet}" >
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${donViPheDuyet.ten_don_vi_phe_duyet}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>  
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Số hiệu văn bản</label>
                                            <input type="text" class="form-control"  name="soHieuVanBan" id="soHieuVanBan" placeholder="Số hiệu văn bản" value="${tramQuyHoach.soHieuVanBan}"  required="true"/>
                                        </div>
                                    </div>    
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group" >
                                            <label class=" input-group-addon" >Ngày phê duyệt</label>
                                            <input type="text" class="form-control date_form"  name="ngayPheDuyet" id="ngayPheDuyet" placeholder="Ngày phê duyệt" required="true"
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.ngayPheDuyet}" />" />
                                        </div>
                                    </div>    
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày điều chỉnh</label>
                                            <input type="text" class="form-control date_form"  id="ngayDieuChinhGanNhat" name="ngayDieuChinhGanNhat" placeholder="Ngày điều chỉnh gần nhất"
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.ngayDieuChinhGanNhat}" />"/>
                                        </div>
                                    </div>    
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" >  
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Đơn vị điều chỉnh</label>
                                            <select name="donViDieuChinhId" id="donViDieuChinhId" class="form-control"  >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="donViPheDuyet" items="${donViLst}">
                                                    <option value="${donViPheDuyet.don_vi_phe_duyet_id}" <c:choose> <c:when test="${donViPheDuyet.don_vi_phe_duyet_id == tramQuyHoach.donViDieuChinhId}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${donViPheDuyet.ten_don_vi_phe_duyet}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>    
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" class="form-control">
                                        <div class="input-group">       
                                            <label class=" input-group-addon" style="min-width:150px;" >Ngày phát sóng</label>
                                            <input  type="text" class="form-control date_form" id="ngayPhatSong" name="ngayPhatSong" placeholder="Ngày phát sóng"
                                                    value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.ngayPhatSong}" />"/>                    
                                        </div>
                                    </div> 
                                </div>
                            </div>
                        </div>
                        <div class="box-footer">

                            <button type="submit"  class="btn btn-success"  >${btnName}</button>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<div id="myBuilding" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tìm Kiếm</h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <iframe id ="iframeBuilding" width="100%" height="450" frameborder="0"></iframe>
                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
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
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<script>
                                                $(document).ready(function () {
                                                    //
                                                    $('#tramQhId').val(${tramQuyHoach.tramQhId});
                                                    $('#buildingId').val(${tramQuyHoach.buildingId});
                                                    $('#tinhTpId').val(${tramQuyHoach.tinhTpId});
                                                    $('#quanHuyenId').val(${tramQuyHoach.quanHuyenId});
                                                    $('#phuongXaId').val(${tramQuyHoach.phuongXaId});
                                                    $('#DIA_CHI').val('${tramQuyHoach.DIA_CHI}');
                                                    getBangTan();
                                                    $('#btn_building').click(function () {
                                                        $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
                                                    });

                                                    $('#myBuilding iframe').on('load', function (e) {
                                                        var iframe1 = $('#myBuilding iframe').contents();
                                                        iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                                            //alert($(this).text());
                                                            $('#buildingCode').val($(this).find('input.txtcode').val());
                                                            $('#buildingId').val($(this).find('input.txtid').val());
                                                            $('#longitude').val($(this).find('input.txtlon').val());
                                                            $('#latitude').val($(this).find('input.txtlat').val());
                                                            $('#tinhTpId').val($(this).find('input.txttinhTpId').val());
                                                            $('#quanHuyenId').val($(this).find('input.txtquanHuyenId').val());
                                                            $('#phuongXaId').val($(this).find('input.txtphuongXaId').val());
                                                            $('#DIA_CHI').val($(this).find('input.txtaddress').val());
                                                        });
                                                        iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                                            //alert($(this).text());
                                                            $('#buildingCode').val($(this).find('input.txtcode').val());
                                                            $('#buildingId').val($(this).find('input.txtid').val());
                                                            $('#longitude').val($(this).find('input.txtlon').val());
                                                            $('#latitude').val($(this).find('input.txtlat').val());
                                                            $('#tinhTpId').val($(this).find('input.txttinhTpId').val());
                                                            $('#quanHuyenId').val($(this).find('input.txtquanHuyenId').val());
                                                            $('#phuongXaId').val($(this).find('input.txtphuongXaId').val());
                                                            $('#DIA_CHI').val($(this).find('input.txtaddress').val());
                                                            $('#myBuilding').modal('hide');
                                                        });
                                                    });



                                                    //
                                                    $('#ngayPhatSong').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#ngayPheDuyet').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#ngayDieuChinhGanNhat').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#namKhoiTao').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });

                                                });

                                                function getBangTan()
                                                {
                                                    var id = $("#loaiCongNgheId").val();

                                                    $.get("${pageContext.request.contextPath}/project_station/getBangTan/" + id, function (data) {
                                                        var html = '<option value="" >-Băng tần-</option>';
                                                        if (data.length > 0) {
                                                            data.forEach(function (entry) {
                                                                var htmlx = '<option value="' + entry.bang_tan_id + '">' + entry.ten_bang_tan + '</option>';
                                                                html += htmlx;

                                                            });
                                                        }
                                                        ;
                                                        $('#bangTanId').html(html);
                                                        $('#bangTanId').val(${tramQuyHoach.bangTanId});
                                                    });
                                                }
                                                function approve(action, tramQhId, TRANG_THAI_TRAM, NOTE) {
//                                        duyệt tram quy hoach
                                                    if (action === 'approve') {
                                                        $("#duyetModalTitle").html('Duyệt trạm quy hoạch');
                                                        $("#duyetLabel").html('Lý do duyệt');
                                                        $(".modal-body #TRANG_THAI_TRAM").val('APPROVE');
                                                    } else if (action === 'reject') {
                                                        $("#duyetModalTitle").html('Từ chối trạm quy hoạch');
                                                        $("#duyetLabel").html('Lý do từ chối');
                                                        $(".modal-body #TRANG_THAI_TRAM").val('REJECT');
//                                           hủy duyệt trạm quy hoạch     
                                                    } else if (action === 'disapprove') {
                                                        $("#duyetModalTitle").html('Hủy duyệt trạm quy hoạch');
                                                        $("#duyetLabel").html('Lý do hủy');
                                                        $(".modal-body #TRANG_THAI_TRAM").val('');
                                                    }
                                                    $(".modal-body #tramQhId").val(tramQhId);
                                                    $(".modal-body #NOTE").val(NOTE);
                                                    $('#duyetDialog').modal('show');
                                                }
</script>