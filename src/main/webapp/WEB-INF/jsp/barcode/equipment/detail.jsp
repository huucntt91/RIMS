
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
        THÔNG TIN CHI TIẾT TÀI SẢN MÁY MÓC THIẾT BỊ
    </h1>
    <ol class="breadcrumb">                

    </ol>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">

                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Thông tin dự án</h3>
                    </div>

                    <div class="panel-body">    
                        <table class="table-info" border="0" style="width: 100%; float: left">
                            <tbody><tr class="row">
                                    <td style="width: 20%">
                                        Mã dự án
                                    </td>
                                    <td class="value" style="width: 30%">
                                        ${model.getMaDuAn()}
                                    </td>
                                    <td style="width: 20%">
                                        Tên dự án
                                    </td>
                                    <td class="value">
                                        ${model.getTenDuAn()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Loại dự án
                                    </td>
                                    <td class="value">
                                        ${model.getLoaiDuAn()}
                                    </td>
                                    <td>
                                        Đơn vị
                                    </td>
                                    <td class="value">
                                        ${model.getDonVi()}
                                    </td>
                                </tr>
                            </tbody></table>
                    </div>
                </div>

                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Thông tin cơ bản</h3>
                    </div>

                    <div class="panel-body"> 
                        <table class="table-info" border="0" style="width: 100%; float: left">
                            <tbody><tr class="row">
                                    <td >
                                        Tên tài sản
                                    </td>
                                    <td class="value" colspan="3">
                                        ${model.getTenTaiSan()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td style="width: 20%" >
                                        Số thẻ tài sản
                                    </td>
                                    <td class="value" style="width: 30%">
                                        ${model.getTheTaiSan()}    
                                    </td>
                                    <td style="width: 20%" >
                                        Part-number
                                    </td>
                                    <td class="value">
                                        ${model.getPartNumber()}      
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Mã vạch
                                    </td>
                                    <td class="value">
                                        ${model.getMaVach()}
                                    </td>
                                    <td >
                                        Serial
                                    </td>
                                    <td class="value">
                                        ${model.getSerialNumber()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Khối lượng
                                    </td>
                                    <td class="value">
                                        ${model.getKhoiLuong()}
                                    </td>
                                    <td >
                                        Hãng sản xuất
                                    </td>
                                    <td class="value">
                                        ${model.getHangSx()}
                                    </td>
                                </tr>
                            </tbody></table>
                    </div>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"> Giá trị</h3>
                    </div>
                    <div class="panel-body">                  
                        <table class="table-info" border="0" style="width: 100%; float: left">
                            <tbody><tr class="row">
                                    <td >
                                        Thời gian sử dụng
                                    </td>
                                    <td class="value">
                                        ${model.getThoiGianSuDung()}
                                        (Năm)
                                    </td>
                                    <td>
                                        Ngày tăng
                                    </td>
                                    <td class="value">
                                        ${model.getNgayTang()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td  style="width: 20%">
                                        Giá trị
                                    </td>
                                    <td class="value" style="width: 30%">
                                        ${model.getGiaTri()} VNĐ
                                        (VNĐ)
                                    </td>
                                    <td  style="width: 20%">
                                        Nguyên tệ
                                    </td>
                                    <td class="value">
                                        ${model.getNguyenTe()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Có khấu hao
                                    </td>
                                    <td class="value" colspan="3">
                                         ${model.getCoKhauHao()}
                                    </td>
                                </tr>
                                <tr style="${model.getLyDoKhongKhauHao() == null ? 'display:none;' : ''}" class="row">
                                    <td >
                                        Lý do không khấu hao
                                    </td>
                                    <td colspan="3" class="value">
                                        <div id="divLyDo" class="divLyDo" style="font-size: 12px; cursor: default;">
                                            ${model.getLyDoKhongKhauHao()}
                                        </div>
                                    </td>
                                </tr>
                                <tr class="row" style="">
                                    <td >
                                        Phương pháp khấu hao
                                    </td>
                                    <td class="value">
                                        ${model.getPhuongPhapKhauHao()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td colspan="4">
                                        <table class="table-info" border="0" style="width: 100%; float: left">
                                            <tbody><tr class="row">
                                                    <th >
                                                        Tên nguồn vốn
                                                    </th>
                                                    <th >
                                                        Nguyên giá
                                                    </th>
                                                    <th >
                                                        Nguyên tệ
                                                    </th>

                                                    <th  style="">
                                                        Khấu hao lũy kế
                                                    </th>
                                                    <th  style="">
                                                        Giá trị còn lại
                                                    </th>
                                                </tr>
                                                 <c:forEach var="item" items="${model.getThongTinNguonVon()}" varStatus="status">                                        
                                                     <tr class="row">
                                                         <td>
                                                             ${item.getTenNguonVon()}
                                                         </td>
                                                         <td>
                                                             ${item.getNguyenGia()}
                                                         </td>
                                                         <td>
                                                             ${item.getNguyenTe()}
                                                         </td>
                                                         <td>
                                                             ${item.getKhauHaoLuyKe()}
                                                         </td>
                                                         <td>
                                                             ${item.getGiaTriConLai()}
                                                         </td>
                                                     </tr>
                                                 </c:forEach>

                                            </tbody></table>
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Tổng giá trị
                                    </td>
                                    <td class="value">
                                           ${model.getTongGiaTri()} VNĐ 
                                    </td>
                                    <td >
                                        Tổng giá trị còn lại
                                    </td>
                                    <td class="value">
                                        ${model.getTongGiaTriConLai()} VNĐ
                                    </td>
                                </tr>
                            </tbody></table>
                    </div>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Thông tin quản lý</h3>
                    </div>
                    <div class="panel-body"> 
                        <table class="table-info" border="0" style="width: 100%; float: left">
                            <tbody><tr class="row">
                                    <td  style="width: 20%">
                                        Hiện trạng sử dụng
                                    </td>
                                    <td class="value" style="width: 30%">
                                        ${model.getHienTrangSuDungTaiSan()}
                                    </td>
                                    <td  style="width: 20%">
                                        Mục đích sử dụng
                                    </td>
                                    <td class="value">
                                        ${model.getMucDichSuDung()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Lĩnh vực sử dụng
                                    </td>
                                    <td class="value">
                                        ${model.getLinhVucSuDung()}
                                    </td>
                                    <td >
                                        Đơn vị quản lý
                                    </td>
                                    <td class="value">
                                        ${model.getDonViQuanLy()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Đơn vị sử dụng
                                    </td>
                                    <td class="value">
                                        ${model.getDonViSuDung()}
                                    </td>
                                    <td >
                                        Bộ phận sử dụng
                                    </td>
                                    <td class="value">
                                        ${model.getBoPhanSuDung()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Chi phí vận chuyển
                                    </td>
                                    <td class="value">
                                        ${model.getChiPhiVanChuyen()} (VNĐ)
                                    </td>
                                    <td >
                                        Chi phí chạy thử
                                    </td>
                                    <td class="value">
                                       ${model.getChiPhiChayThu()} (VNĐ)
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Vị trí lắp đặt
                                    </td>
                                    <td class="value" colspan="3">
                                        ${model.getViTriLapDat()}
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td >
                                        Trạng thái 
                                    </td>
                                    <td class="value" colspan="3">
                                        ${model.getTrangThaiBd()}
                                    </td>
                                </tr>
                            </tbody></table>
                    </div>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Đính kèm</h3>
                    </div>
                    <div class="panel-body"> 

                        <table class="table-info" border="0" style="width: 100%; float: left">
                            <tbody><tr class="row">
                                    <td  style="width: 20%">
                                        File đính kèm
                                    </td>
                                    <td align="right" colspan="3">
                                        <ul id="listFileAttachRemove">

                                        </ul>
                                    </td>
                                </tr>
                            </tbody></table>
                    </div>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Phụ kiện kèm theo</h3>
                    </div>
                    <div class="panel-body"> 

                        <table class="table-info" border="0" style="width: 100%; float: left">
                            <tbody><tr style="">
                                    <td colspan="4">
                                        <!-- Danh sách phụ kiện -->
                                        <div id="list-container">


                                            <title>

                                            </title>
                                            <style type="text/css">
                                                a
                                                {
                                                    text-decoration: none;
                                                }
                                            </style>



                                            <table style="width: 100%;">
                                                <tbody><tr>
                                                        <td>

                                                            <p class="no-record">Chưa có tài sản đính kèm nào thuộc tài sản</p>
                                                        </td>
                                                    </tr>
                                                </tbody></table>


                                        </div>
                                    </td>
                                </tr>
                            </tbody></table>
                    </div>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Danh sách bộ phận cấu thành</h3>
                    </div>
                    <div class="panel-body"> 

                        <table class="table-info" border="0" style="width: 100%; float: left">
                            <tbody><tr style="">
                                    <td colspan="4">
                                        <div id="gridview-container"><div class="img-loading" style="font:12px Arial; width:100%; margin:0 auto; padding:10px 0px;"><img src="/images/ajax-loader.gif"><span>Đang tải dữ liệu...</span></div></div>
                                    </td>
                                </tr>
                            </tbody></table>
                    </div>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Lịch sử tài sản</h3>
                    </div>
                    <div class="panel-body"> 
                        <div id="gridcontainer" class="grid-container">
                            <table class="gridView" cellspacing="1" width="100%">
                                <tbody><tr class="md-header">
                                        <th style="width: 150px;">
                                            Loại biến động
                                        </th>
                                        <th style="width: 100px;">
                                            Ngày biến động
                                        </th>
                                        <th>
                                            Thời gian sử dụng còn lại
                                        </th>
                                        <th>
                                            Nguyên giá
                                        </th>
                                        <th>
                                            Giá trị còn lại
                                        </th>
                                        <th>
                                            Đơn vị quản lý
                                        </th>
                                    </tr>
                                    <c:forEach var="item" items="${model.getLichSuTaiSan()}" varStatus="status">
                                    <tr>
                                        
                                        <td>
                                            <a href="javascript:;" onclick="return ViewHistoryTaiSan('B700044DA1F947DEAEA62094DC84C2F2');">
                                                ${item.getLoaiBienDong()}</a>
                                        </td>
                                        <td>
                                           ${item.getNgayBienDong()}
                                        </td>
                                        <td>
                                             ${item.getThoiGianSuDungConLai()}
                                        </td>
                                        <td>
                                           ${item.getNguyenGia()}
                                        </td>
                                        <td>
                                               ${item.getGiaTriConLai()}
                                        </td>
                                        <td>
                                            ${item.getDonViQuanLy()}
                                        </td>
                                    </tr>
                                    </c:forEach>
                             

                                </tbody></table> </div>  
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>

