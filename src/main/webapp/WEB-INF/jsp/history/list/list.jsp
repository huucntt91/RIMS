
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>

<section class="content-header">
    <h1>
        <i class="fa fa-history"></i> Lịch sự tác động vòng đời NE
    </h1>
    <ol class="breadcrumb">

    </ol>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                </div>

                <form:form method="GET" id="frm_search">
                    <div class="box-body">
                        <div class="col-md-3">
                            <div class="form-group">
                                <div class="input-group" >
                                    <label class=" input-group-addon" >Thời gian bắt đầu</label>
                                    <input type="text" class="form-control date_form"  name="startTime" id="startTime" required="true"
                                           value="${startTime}"/>
                                </div>
                            </div>    
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <div class="input-group" >
                                    <label class=" input-group-addon" >Thời gian kết thúc</label>
                                    <input type="text" class="form-control date_form"  name="endTime" id="endTime"
                                           value="${endTime}" />
                                </div>
                            </div>    
                        </div>



                        <div class="col-md-3">
                            <div class="form-group">
                                <select name="userId" id="userId" class="form-control selectpicker" data-live-search="true"> 

                                    <option  value="">--- Chọn Người SD ---</option>
                                    <c:forEach var="item" items="${userList}">
                                        <option  data-tokens="${item.username}"
                                                 value="${item.id}"  <c:choose>
                                                     <c:when test="${item.id == userId}">
                                                         selected    
                                                     </c:when>    
                                                 </c:choose>

                                                 >${item.username}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <select name="action" id="status" class="form-control"> >
                                    <option  value="">--- Chọn tác động ---</option>
                                    <option  <c:if test='${action == "Thêm mới"}'> selected </c:if> value="Thêm mới">Thêm mới</option>
                                    <option <c:if test='${action== "Kiểm duyệt"}'> selected </c:if> value="Kiểm duyệt">Kiểm duyệt</option>
                                    <option <c:if test='${action=="Đăng ký hủy"}'> selected </c:if> value="Đăng ký hủy">Đăng ký hủy</option>
                                    <option <c:if test='${action=="Cập nhật"}'> selected </c:if> value="Cập nhật">Cập nhật</option>

                                    </select>  
                                </div>
                            </div>

                            <!--                            <div class="col-md-4">
                                                            <div class="form-group">
                            -->                                                                <input name="code" value="${code}"  type="hidden"  />
                        <input name="type" value="${type}"  type="hidden"  />
                        <!--
                                            </div>-->
                        <!--                    </div>-->
                        <div class="clearfix"></div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <c:if test="${listTramQH != null}" >
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Lịch sử trạm quy hoạch</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <div id="tablePagingId">
                            <table id="table0" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>UserName</th>
                                        <th>Hành động</th>      
                                        <th>Ghi chú</th>
                                        <th>Thời gian</th>
                                        <th>IP Client</th>
                                        <th>Mã Quy hoạch</th>
                                        <th>Tên quy hoạch</th>
                                        <th>Năm khởi tạo</th>
                                        <th>Longitude</th>
                                        <th>Latitude</th>
                                        <th>Loại CN</th>
                                        <th>Băng tần</th>
                                        <th>Chương trình PT CSHT</th>
                                        <th>Trạng thái</th>

                                        <!-- cam ket thiet bi -->
                                        <th>Đơn vị chịu trách nhiệm</th>
                                        <th>Nguồn thiết bị</th>
                                        <th>Thời điểm đáp ứng thiết bị dự kiến</th>
                                        <th>Công nghệ đáp ứng</th>
                                        <th>Chủng loại thiết bị</th>
                                        <th>Thời gian đáp ứng thiết bị thực tế</th>
                                        <th>Khó khăn, vướng mắc</th>

                                        <!-- CSHT -->
                                        <th>Tỉnh/Thành phố</th>
                                        <th>Quận/Huyện</th>
                                        <th>Địa chỉ</th>
                                        <th>Tên trạm</th>
                                        <th>Cách thức xây dựng CSHT</th>

                                        <!-- Nguon DC -->
                                        <th>Đơn vị chịu trách nhiệm</th>
                                        <th>Tủ nguồn</th>
                                        <th>Loại tủ nguồn</th>
                                        <th>Dung lượng tủ nguồn (A)</th>
                                        <th>Số lượng Racktifier</th>
                                        <th>Dung lượng acquy (AH)</th>
                                        <th>Số lượng tổ Acquy</th>
                                        <th>Điện áp Acquy</th>
                                        <th>Ngày đáp ứng nguồn DC dự kiến</th>
                                        <th>Ngày đáp ứng nguồn DC thực tế</th>

                                        <!-- Nguon ANTEN -->
                                        <th>Tên Antena1</th>
                                        <th>Hãng sản xuất1</th>
                                        <th>Số lượng1</th>
                                        <th>Tên Antena2</th>
                                        <th>Hãng sản xuất2</th>
                                        <th>Số lượng2</th>
                                        <th>Tên Antena3</th>
                                        <th>Hãng sản xuất3</th>
                                        <th>Số lượng3</th>


                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${listTramQH}" varStatus="status">                                        
                                        <tr>
                                            <td>${startRow + 1 +(status.index)}</td>
                                            <td>${item.userName}</td>
                                            <td>${item.action}</td>
                                            <td>${item.note}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.createTime}" /></td>
                                            <td>${item.ipClient}</td>


                                            <td>${item.tram.maQh}</td>
                                            <td>${item.tram.tenQh}</td>
                                            <td>${item.tram.namKhoiTao}</td>
                                            <td>${item.tram.longitude}</td>

                                            <td>${item.tram.latitude}</td>

                                            <td>${item.tram.loaiCongNghe}</td>
                                            <td>${item.tram.bangTan}</td>
                                            <td>${item.tram.ptCsht}</td>
                                            <td>${item.tram.trangThaiCsht}</td>


                                            <td>${item.tram.DVI_TRACH_NHIEM_CCTB}</td>
                                            <td>${item.tram.NGUON_THIET_BI}</td>
                                            <td>${item.tram.THOI_DIEM_DAP_UNG_DU_KIEN}</td>
                                            <td>${item.tram.TEN_CONG_NGHE_DAP_UNG}</td>
                                            <td>${item.tram.CHUNG_LOAI_THIET_BI}</td>
                                            <td>${item.tram.THOI_DIEM_DAP_UNG_THUC_TE}</td>
                                            <td>${item.tram.KHO_KHAN_VUONG_MAC}</td>


                                            <td>${item.tram.tinhTp}</td>
                                            <td>${item.tram.quanHuyen}</td>
                                            <td>${item.tram.DIA_CHI}</td>
                                            <td>${item.tram.TEN_TRAM}</td>
                                            <td>${item.tram.CACH_XAY_CSHT} </td>


                                            <td>${item.tram.DVI_TRACH_NHIEM_TU_NGUON} </td>
                                            <td>${item.tram.TEN_LOAI_TU_NGUON} </td>
                                            <td>${item.tram.DUNG_LUONG_TU_NGUON} </td>
                                            <td>${item.tram.SO_RACTIFIER} </td>
                                            <td>${item.tram.DUNG_LUONG_ACCU} </td>
                                            <td>${item.tram.SO_LUONG_ACCU} </td>
                                            <td>${item.tram.DIEN_AP_ACCU} </td>
                                            <td>${item.tram.NGAY_DAP_UNG_NGUON_DC_DU_KIEN} </td>
                                            <td>${item.tram.NGAY_DAP_UNG_NGUON_DC_TT} </td>


                                            <td>${item.tram.TEN_ANTENA1} </td>
                                            <td>${item.tram.HANG_SX_ANTENA1} </td>
                                            <td>${item.tram.SO_LUONG_ANTENA1} </td>
                                            <td>${item.tram.TEN_ANTENA2} </td>
                                            <td>${item.tram.DUNG_LUONG_ACCU} </td>
                                            <td>${item.tram.HANG_SX_ANTENA2} </td>
                                            <td>${item.tram.SO_LUONG_ANTENA2} </td>
                                            <td>${item.tram.TEN_ANTENA3} </td>
                                            <td>${item.tram.HANG_SX_ANTENA3} </td>
                                            <td>${item.tram.SO_LUONG_ANTENA3} </td>

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
    </c:if>
    <c:if test="${listTramDA != null}" >
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Lịch sử trạm kế hoạch</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <div class="tablePagingId">

                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>UserName</th>
                                        <th>Hành động</th>
                                        <th>Ghi chú</th>
                                        <th>Thời gian</th>
                                        <th>IP Client</th>
                                        <th>Mã DA</th>
                                        <th>Tên DA</th>
                                        <th>Mã quy hoạch</th>
                                        <th>Mã HĐ</th>
                                        <th>Tỉnh</th>                                            
                                        <th>Huyện</th>
                                        <th>Địa chỉ lắp đặt</th>
                                        <th>Mã trạm DA</th>
                                        <th>Tên Trạm DA</th>
                                        <th>Mã trạm BTS</th>
                                        <th>Mã trạm NodeB</th>
                                        <th>Mã trạm QH</th>
                                        <th>Hiện trạng</th>
                                        <th>Longitude</th>
                                        <th>Latitude</th>
                                        <th>Trạng thái trạm</th>

                                        <th>VNPT phê duyệt</th>
                                        <th>Cấu hình</th>
                                        <th>Nguồn thiết bị</th>
                                        <th>Công nghệ</th>
                                        <th>Chủng loại thiết bị</th>
                                        <th>Chủng loại Anten</th>

                                        <th>Long(khảo sát)</th>
                                        <th>Lat(khảo sát)</th>
                                        <th>Nhà trạm</th>
                                        <th>Cột anten</th>
                                        <th>Cầu cáp ngoài</th>
                                        <th>Tủ nguồn</th>

                                        <th>Dung lượng tủ nguồn (A)</th>
                                        <th>Số Module nguồn</th>
                                        <th>Chủng loại ACCU</th>
                                        <th>Dung lượng ACCU (Ah</th>
                                        <th>Số lượng tổ ACCU</th>
                                        <th>Truyền dẫn</th>

                                        <th>Điều hòa</th>
                                        <th>Điện AC</th>
                                        <th>Điện AC nội trạm</th>
                                        <th>Đủ điều kiện lắp eNodeB</th>
                                        <th>Cấp mới tủ nguồn DC</th>
                                        <th>Cấp mới ACCU</th

                                        <th>Swap (lắp mới) anten</th>
                                        <th>Ngày hoàn thành khảo sát</th>
                                        <th>Ngày gửi số liệu lên Sever</th>
                                        <th>Đầu mối VNPT Tỉnh/TP nhận thiết bị</th>
                                        <th>Đầu mối QL CSHT nhà trạm</th>
                                        <th>Đơn vị lắp đặt</th>


                                        <th>Ngày duyệt kết quả khảo sát</th>
                                        <th>Ngày tiếp nhận truyền dẫn</th>

                                        <th>Kế hoạch xuất thiết bị</th>
                                        <th>Ngày xuất thiết bị thực tế</th>
                                        <th>Ngày tiếp nhận thiết bị</th>
                                        <th>Kế hoạch thiết bị đến site</th>
                                        <th>Kế hoạch lắp đặt</th>
                                        <th>Ngày bắt đầu lắp đặt thiết bị</th>
                                        <th>Ngày hoàn thành lắp đặt thiết bị</th>
                                        <th>Kế hoạch Hòa mạng</th>

                                        <th>Ngày hòa mạng thực tế</th>
                                        <th>Kế hoạch phát sóng chính thức</th>
                                        <th>Ngày phát sóng chính thức</th>
                                        <th>Kế hoạch nghiệm thu</th>
                                        <th>Ngày nghiệm thu</th>
                                        <th>Đầu mối (VNPT Net)</th>
                                        <th>Đơn vị vận chuyển</th>
                                        <th>Ghi chú</th>  
                                    </tr>
                                </thead>

                                <tbody>                                       
                                    <c:forEach var="item" items="${listTramDA}" varStatus="status">                                        
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${item.userName}</td>
                                            <td>${item.action}</td>
                                            <td>${item.note}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.createTime}" /></td>
                                            <td>${item.ipClient}</td>
                                            <td>${item.tram.maDuAn}</td>
                                            <td>${item.tram.tenDuAn}</td>
                                            <td>${item.tram.maQuyHoach}</td>
                                            <td>${item.tram.maSoHopDong}</td>
                                            <td>${item.tram.tenTinhTp}</td>
                                            <td>${item.tram.tenQuanHuyen}</td>
                                            <td>${item.tram.address}</td>
                                            <td>${item.tram.maTramDuAn}</td>
                                            <td>${item.tram.tenTramDuAn}</td>
                                            <td>${item.tram.maTramBTS}</td>
                                            <td>${item.tram.maTramNodeB}</td>
                                            <td>${item.tram.maTramQuyHoach}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.tram.hienTrangTram=='1'}">
                                                        Đang hoạt động
                                                        <br />
                                                    </c:when>    
                                                    <c:otherwise>
                                                        Trạm quy hoạch
                                                        <br />
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${item.tram.longitude}</td>
                                            <td>${item.tram.latitude}</td>
                                            <td>${item.tram.tenTrangThaiTram}</td>

                                            <!-- cam ket tb -->
                                            <td>${item.tram.vnptNetPheDuyet}</td>
                                            <td>${item.tram.cauHinhThietBi}</td>
                                            <td>${item.tram.strNguonThietBi}</td>
                                            <td>${item.tram.strLoaiCongNghe}</td>
                                            <td>${item.tram.chungLoaiThietBi}</td>
                                            <td>${item.tram.chungLoaiAnten}</td>

                                            <!-- Cam kết hạ tầng -->
                                            <td>${item.tram.longitudeKhaoSat}</td>
                                            <td>${item.tram.longitudeKhaoSat}</td>
                                            <td>${item.tram.nhaTram}</td>
                                            <td>${item.tram.cotAnten}</td>
                                            <td>${item.tram.cauCapNgoai}</td>
                                            <td>${item.tram.strTuNguon}</td>

                                            <td>${item.tram.dungLuongTuNguon}</td>
                                            <td>${item.tram.chungLoaiAccu}</td>
                                            <td>${item.tram.dungLuongAccu}</td>
                                            <td>${item.tram.soLuongToAccu}</td>
                                            <td>${item.tram.truyenDan}</td>

                                            <td>${item.tram.dieuHoa}</td>

                                            <td>${item.tram.dienAc}</td>
                                            <td>${item.tram.dienAcNoiTram}</td>
                                            <td>${item.tram.duDkLapEnodeb}</td>
                                            <td>${item.tram.capMoiTuNguonDc}</td>
                                            <td>${item.tram.capMoiAccu}</td>
                                            <td>${item.tram.swapAnten}</td>

                                            <td>${item.tram.ngayHoanThanhKs}</td>
                                            <td>${item.tram.ngayGuiSoLieu}</td>
                                            <td>${item.tram.dauMoiNhanThietBi}</td>
                                            <td>${item.tram.dauMoiQLCSHT}</td>
                                            <td>${item.tram.donViLapDat}</td>
                                            <!-- Thông tin triển khai dự án - NetX -->
                                            <td>${item.tram.ngayPheDuyetKhaoSat}</td>
                                            <td>${item.tram.ngayTiepNhanTruyenDan}</td>
                                            <!-- Thông tin triển khai dự án - QLHT -->
                                               <td>${item.tram.keHoachXuatThietBi}</td>
                                            <td>${item.tram.ngayXuatThietBiThucTe}</td>
                                            <td>${item.tram.ngayTiepNhanTb}</td>
                                            <td>${item.tram.keHoachTbDenSite}</td>
                                            <td>${item.tram.keHoachLapDat}</td>
                                            <td>${item.tram.ngayBatDauLapDat}</td>

                                            <td>${item.tram.ngayHTLapDatTb}</td>
                                            <td>${item.tram.keHoachHoaMang}</td>
                                            <td>${item.tram.ngayHoaMangThucTe}</td>
                                            <td>${item.tram.keHoachPhatSongCt}</td>
                                            <td>${item.tram.ngayPhatSongCt}</td>  
                                            
                                              <td>${item.tram.keHoachNghiemThu}</td>
                                            <td>${item.tram.ngayNghiemThu}</td>
                                            <td>${item.tram.dauMoiVnptNet}</td>
                                            <td>${item.tram.donViVanChuyen}</td>
                                            <td>${item.tram.ghiChu}</td>   

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
    </c:if>
    <c:if test="${listCsht != null}" >
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Lịch sử CSHT</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">  
                        <div class="tablePagingId">
                            <table  class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>UserName</th>
                                        <th>Hành động</th>
                                        <th>Ghi chú</th>
                                        <th>Thời gian</th>
                                        <th>IP Client</th>
                                        <th>Tỉnh/TP</th>
                                        <th>Quận/Huyện</th>
                                        <!--                                <th>Phường/Xã</th>-->
                                        <th width="200">Dịa chỉ</th>
                                        <th>Mã Building</th>

                                        <th>Truyền dẫn</th> 
                                        <th>Chung CSHT</th>
                                        <th>Anten</th>

                                        <th>Độ cao Anten</th>
                                        <th>Độ cao nhà đặt</th>
                                        <th>Tủ nguồn</th> 
                                        <th>Ngày HD tủ nguồn</th>
                                        <th>Số module tủ nguồn </th>
                                        <th>Máy nổ</th>  
                                        <th>Ngày HD máy nổ</th>
                                        <th>Công suất máy nổ</th>
                                        <th>Trạng thái đặt máy nổ</th>
                                        <th>AcQuy</th>
                                        <th>Ngay HD accu</th>
                                        <th>Dung lượng accu</th>
                                        <th>Thời gian HD sau mất điện</th>
                                        <th>Ngày bảo dưỡng Accu</th>
                                        <th>Điện trở tiếp đất</th>

                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${listCsht}" varStatus="status">                                        
                                        <tr>
                                            <td>${startRow + 1 +(status.index)}</td>
                                            <td>${item.userName}</td>
                                            <td>${item.action}</td>
                                               <td>${item.note}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.createTime}" /></td>
                                            <td>${item.ipClient}</td>
                                            <td>${item.buildingBO.tinhName}</td>
                                            <td>${item.buildingBO.quanName}</td>
                                            <td>${item.buildingBO.address}</td>

                                            <td>${item.buildingBO.code}</td>

                                            <td>${item.phuTroBO.truyenDan}</td>
                                            <td>${item.phuTroBO.chungCsht}</td>
                                            <td>${item.phuTroBO.anTen}</td>
                                            <td>${item.phuTroBO.doCaoAnTen}</td>
                                            <td>${item.phuTroBO.doCaoNhaDatAnTen}</td>
                                            <td>${item.phuTroBO.tuNguon}</td>
                                            <td>${item.phuTroBO.ngayHDTuNguon}</td>
                                            <td>${item.phuTroBO.soModuleTuNguon}</td>

                                            <td>${item.phuTroBO.mayNo}</td>
                                            <td>${item.phuTroBO.ngayHDMayNo}</td>
                                            <td>${item.phuTroBO.congSuatMayNo}</td>
                                            <td>${item.phuTroBO.trangThaiMayNo}</td>

                                            <td>${item.phuTroBO.acQuy}</td>
                                            <td>${item.phuTroBO.ngayHDAccu}</td>
                                            <td>${item.phuTroBO.dungLuongAccu}</td>
                                            <td>${item.phuTroBO.thoigianHDSauMatDien} </td>
                                            <td>${item.phuTroBO.ngayBaoDuongAccu} </td>

                                            <td>${item.phuTroBO.dienTroTiepDia} </td>

                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                    </div>

                </div>
                <!-- /.box -->
            </div>
        </div>            
    </c:if>
    <c:if test="${listCell != null}" >       
        <div class="row">
            <div class="col-md-12" >

                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Lịch sử Cell</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <div class="tablePagingId">
                            <table id="tableCell"  class="table table-bordered table-hover"
                                   style=" overflow-x:scroll !important;;overflow-y:hidden !important;">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>UserName</th>
                                        <th>Hành động</th>
                                        <th>Ghi chú</th>
                                        <th>Thời gian</th>
                                        <th>IP Client</th>
                                        <th>Tỉnh/TP</th>
                                        <!--                                    <th>Quận/Huyện</th>
                                                                            <th>Phường/Xã</th>-->
                                        <th>Dịa chỉ</th>
                                        <th>Ngày hoạt động</th>
                                        <th>Hoàn cảnh ra đời</th>
                                        <th>Tên cho quản lý</th>
                                        <th>Ngày kiểm duyệt</th>
                                        <th>Ngày cấp phép</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>
                                        <th>Azimuth</th>
                                        <th>Mechanical tilt</th>
                                        <th>Total tilt</th>
                                        <th>ANTENNA_HIGH</th>
                                        <th>ANTENNA_GAIN</th>
                                        <th>ANTENNA_TYPE</th>
                                        <th>NO_OF_CARRIER</th>
                                        <th>CPICH_POWER</th>
                                        <th>TOTAL_POWER</th>
                                        <th>BOSTER_TMA</th>
                                        <th>SPECIAL_COVERAGE</th>
                                        <th>BLACK_LIST_FLAG</th>
                                        <th>LY_DO</th>                                    
                                        <th>Tên trên hệ thống</th>
                                        <th>LAC</th>
                                        <th>CI</th>
                                        <th>TEN_BSC_RNC</th>
                                        <th>Code</th>
                                        <th>DC_HSDPA_42M</th>
                                        <th>FREQUENCY_BAND</th>
                                        <th>Trạng thái HĐ</th>
                                        <th>Trạng Thái QL</th>
                                        <th>Thiết bị</th>
                                        <th>Loại trạm</th>                                    
                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${listCell}" varStatus="status">                                        
                                        <tr>
                                            <td>${startRow + 1 +(status.index)}</td>
                                            <td>${item.userName}</td>
                                            <td>${item.action}</td>
                                               <td>${item.note}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.createTime}" /></td>
                                            <td>${item.ipClient}</td>
                                            <td>${item.tinh}</td>
    <!--                                        <td>${item.quan}</td>
                                            <td>${item.xa}</td>-->
                                            <td>${item.diachi}</td>
                                            <td>${item.ngayHoatDong}</td>
                                            <td>${item.hoanCanhRaDoi}</td>
                                            <td>${item.tenCell}</td>
                                            <td>${item.ngayKiemDuyet}</td>
                                            <td>${item.ngayCapPhep}</td>
                                            <td>${item.latitude}</td>
                                            <td>${item.longitude}</td>
                                            <td>${item.azimuth}</td>
                                            <td>${item.mechanical}</td>
                                            <td>${item.totalTilt}</td>
                                            <td>${item.antennaHigh}</td>
                                            <td>${item.antennaGain}</td>
                                            <td>${item.antennaType}</td>
                                            <td>${item.noOfCarrier}</td>
                                            <td>${item.cpichPower}</td>
                                            <td>${item.totalPower}</td>
                                            <td>${item.bosterTma}</td>
                                            <td>${item.specialCoverage}</td>
                                            <td>${item.blackListFlag}</td>
                                            <td>${item.lyDo}</td>
                                            <td>${item.tenTrenHeThong}</td>
                                            <td>${item.lac}</td>
                                            <td>${item.ci}</td>
                                            <td>${item.tenBscRnc}</td>
                                            <td>${item.code}</td>
                                            <td>${item.dcHsdpa42m}</td>
                                            <td>${item.frequencyBand}</td>
                                            <td>${item.trangThaiHd}</td>
                                            <td>${item.trangThaiQl}</td>
                                            <td>${item.thietBiId}</td>
                                            <td>${item.tenLoaiTram}</td>                                                                                
                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                    </div>

                </div>

            </div>

        </div>
    </c:if>
    <c:if test="${listBts != null}" >  
        <div class="row">

            <div class="col-md-12" >

                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Lịch sử BTS/NodeB/eNodeB</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <div class="tablePagingId">
                            <table id="tableBts"  class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>UserName</th>
                                        <th>Hành động</th>
                                        <th>Ghi chú</th>
                                        <th>Thời gian</th>
                                        <th>IP Client</th>
                                        <th>Tỉnh/TP</th>
                                        <th>Địa chỉ</th>
                                        <th>Ngày hoạt động</th>
                                        <th>Hoàn cảnh ra đời</th>
                                        <th>Tên cho quản lý</th>                                            
                                        <th>Ngày đăng ký</th>
                                        <th>Ngày kiểm duyệt</th>
                                        <th>Ngày cấp phép</th>
                                        <th>Trạng thái hoạt động</th>
                                        <th>Trạng thái quản lý</th>
                                        <th>Tên trên hệ thống</th>
                                        <th>Tên BSC/RNC</th>
                                        <th>Tên BSC/RNC quản lý</th>					<th>MSC/MSS</th>
                                        <th>SGSN</th>
                                        <th>Mã trạm</th>
                                        <th>DC-HSDPA 42M</th>
                                        <th>Filter User</th>
                                        <th>Frequency Band</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>
                                        <th>Co-site 2G-3G</th>
                                        <th>Mã Co-site 2G-3G</th>
                                        <th>Thiết bị</th>
                                        <th>Loại trạm</th>
                                        <th>Cấu hình</th>
                                        <th>Cấu hình số TRX</th>

                                        <th>Tên người quản lý</th>	
                                        <th>Số đt quản lý</th>	


                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${listBts}" varStatus="status">                                        
                                        <tr>
                                            <td>${startRow + 1 + (status.index)}</td>

                                            <td>${item.userName}</td>
                                            <td>${item.action}</td>
                                               <td>${item.note}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.createTime}" /></td>
                                            <td>${item.ipClient}</td>
                                            <td>${item.tinh}</td>

                                            <td>${item.diachi}</td>
                                            <td>${item.ngayHoatDong}</td>
                                            <td>${item.hoanCanhRaDoi}</td>
                                            <td>${item.tenBts}</td>
                                            <td>${item.ngayDangKy}</td>
                                            <td>${item.ngayKiemDuyet}</td>
                                            <td>${item.ngayCapPhep}</td>
                                            <td>${item.trangThaiHd}</td>
                                            <td>${item.trangThaiQl}</td>

                                            <td>${item.tenTrenHeThong}</td>
                                            <td>${item.tenBscRnc}</td>
                                            <td>${item.tenBscRncQl}</td>
                                            <td>${item.mscMss}</td>
                                            <td>${item.sgsn}</td>
                                            <td>${item.maNode}</td>

                                            <td>${item.dcHsdpa42M}</td>
                                            <td>${item.filterUser}</td>
                                            <td>${item.frequencyBand}</td>
                                            <td>${item.latitude}</td>
                                            <td>${item.longitude}</td>
                                            <td>${item.cosite2G3GType}</td>

                                            <td>${item.maCosite2G3GType}</td>
                                            <td>${item.tenThietBi}</td>
                                            <td>${item.tenLoaiTram}</td>
                                            <td>${item.cauhinh}</td>
                                            <td>${item.cauhinhSoTRX}</td>

                                            <td>${item.tenNguoiQL}</td>
                                            <td>${item.soDTNgQL}</td>

                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                    </div>

                </div>

            </div>

        </div>
    </c:if>
</section>


<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>

<script src="${pageContext.request.contextPath}/resources/js/bootstrap-select.min.js" type="text/javascript"></script>

<script>
    $(document).ready(function () {
        //$('.navbar-btn').click();
        $('#startTime').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });

        $('#endTime').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true,

        });

        $(".table").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });


    });

</script>

