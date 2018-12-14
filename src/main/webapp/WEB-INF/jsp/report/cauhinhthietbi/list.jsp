
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<section class="content-header">
    <h1>
        <i class="fa fa-file-word-o"></i> Báo cáo cấu hình thiết bị
    </h1>            
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-body">


                    <form:form method="POST" id="form_changeObject" action="changeObject">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Chọn đối tượng: </label>

                            <select name="type" class="form-control" onchange="fnChangeObject()">
                                <option  <c:if test='${type == "-1"}' > selected </c:if>  value="-1">--- Chọn đối tượng ---</option>
                                <option <c:if test='${type == "5"}' > selected </c:if>  value="5">--- Cell 2G ---</option>
                                <option <c:if test='${type == "6"}' > selected </c:if>  value="6">--- Cell 3G ---</option>
                                <option <c:if test='${type == "8"}' > selected </c:if>  value="8">--- Cell 4G ---</option>
                                <option <c:if test='${type == "2"}' > selected </c:if>  value="2">--- BTS ---</option>
                                <option <c:if test='${type == "3"}' > selected </c:if>  value="3">--- NodeB ---</option>                            
                                </select>  
                            </div>            
                    </form:form>

                    <c:if test='${type!=null && type!="-1"}'>
                        <!--<div class="form-group">-->    
                        <form:form method="GET" id="form_change_column" action="${pageContext.request.contextPath}/report/configCell/addFilter" 
                                   commandName="filterForm">
                            <!--                    <div class="box ">
                                                    <div class="box-body">-->
                            <div class="form-group">
                                <div class="row">
                                    <input type="hidden" value="${type}" name="type"/>

                                    <div class="col-md-3">
                                        <form:select path="column" id="idColumn" class="form-control" 
                                                     onchange="getComboA(this)">>
                                            <%--onchange="getComboA(this)">--%>
                                            <form:option value="-1">-- Chọn thuộc tính --</form:option>
                                            <form:options items="${filterReportList}" itemValue="columnId"  itemLabel="description"/>
                                        </form:select>
                                        <form:hidden path="description" id="description"/>

                                    </div>
                                    <div class="col-md-3">
                                        <form:select  path="operator" class="form-control"  >
                                            <form:option value="Contains">--- Contains ---</form:option>
                                            <form:option value="Not contains">--- Not contains ---</form:option>
                                            <form:option value="startWith">--- startWith ---</form:option>
                                            <form:option value="endWith">--- endWith ---</form:option>
                                            <form:option value="Exact">--- Exact ---</form:option>
                                            <form:option value="NULL">--- NULL ---</form:option>
                                            <form:option value="NOT NULL">--- NOT NULL ---</form:option>                                
                                        </form:select>                                
                                    </div>
                                    <div class="col-md-3" id="valueDiv">
                                        <c:if test="${filterForm.dataType==0}">
                                            <form:input class="form-control" placeholder="Giá trị" type="number"
                                                        id="text" path="value_" />  
                                        </c:if>
                                        <c:if test="${filterForm.dataType==1}">
                                            <form:input class="form-control" placeholder="Giá trị"
                                                        id="text" path="value_" />  
                                        </c:if>
                                        <c:if test="${filterForm.dataType==2}">
                                            <form:input class="form-control" placeholder="Giá trị"
                                                        id="ngay" path="value_" />  
                                        </c:if>

                                        <form:hidden path="dataType"/>
                                    </div>

                                    <!--                        </div>
                                                        </div>   -->

                                    <!--<div class="box-footer">-->
                                    <div class="col-md-3">
                                        <button id="btnAdd"  type="submit" onclick="return setDesc(${filterForm.dataType})" class="btn btn-primary">Add Filter</button>
                                    </div>

                                </div>

                            </div>
                            <c:if test="${filterForm.listFilterForm!=null}">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="box">
                                            <div class="box-header">
                                                <h3 class="box-title">Điều kiện lọc</h3>
                                            </div>
                                            <div class="box-body table-responsive">
                                                <table id="example1" class="table table-bordered table-hover"
                                                       style=" overflow-x:scroll !important;;overflow-y:hidden !important;">
                                                    <thead>
                                                        <tr>
                                                            <!--<th>#</th>-->
                                                            <th>Thuộc tính</th>
                                                            <th>Điều kiện</th>
                                                            <th>Giá trị</th>
                                                            <th>Xử lý</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>       
                                                        <c:forEach var="item" items="${filterForm.listFilterForm}" varStatus="status">
                                                            <tr>
                                                                <form:input cssStyle="display:none" readonly="true" path="listFilterForm[${status.index}].id" name="listFilterForm" />
                                                                <form:input cssStyle="display:none" readonly="true" path="listFilterForm[${status.index}].column" name="listFilterForm"  />
                                                                <td><form:input readonly="true" path="listFilterForm[${status.index}].description" name="listFilterForm"  /></td>
                                                                <td><form:input readonly="true" path="listFilterForm[${status.index}].operator" name="listFilterForm" /></td>
                                                                <td><form:input readonly="true" path="listFilterForm[${status.index}].value_" name="listFilterForm" /></td>
                                                                <td> 
                                                                    <button type="submit" onclick="return removeId('${filterForm.listFilterForm[status.index].id}')" name="removeFilter" class="btn btn-danger">Remove</button>
                                                                </td>
                                                            </tr>        
                                                        </c:forEach>           
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <!--</div>-->

                            <div class="form-group">
                                <button type="submit" name="search" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                                <button type="submit" name="export" class="btn btn-primary"> <i class="fa fa-sign-in"></i> Export</button>
                            </div>
                            <input type="hidden" name="id" id="idRemove"/>
                        </form:form>
                    </c:if>
                </div>
            </div>    
        </div>    
    </div>
    <c:if test="${list!=null}">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Kết quả tìm kiếm</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <c:if test='${type=="5"}'>
                            <div id="tablePagingId">
                                <table id="example1" class="table table-bordered table-hover"
                                       style=" overflow-x:scroll !important;;overflow-y:hidden !important;">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tỉnh/TP</th>
                                            <th>Quận/Huyện</th>
                                            <th>Phường/Xã</th>
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
                                            <th>SPECIAL_COVERAGE</th>                           
                                            <th>Tên trên hệ thống</th>
                                            <th>LAC</th>
                                            <th>CI</th>
                                            <th>FREQUENCY_BAND</th>
                                            <th>TRANG_THAI_HD_ID</th>
                                            <th>TRANG_THAI_QL_ID</th>
                                            <th>TEN_THIET_BI</th>
                                            <th>TEN_LOAI_TRAM</th>                                    
                                        </tr>
                                    </thead>
                                    <tbody>                                       
                                        <c:forEach var="item" items="${list}" varStatus="status">                                        
                                            <tr>
                                                <td>${startRow + (status.index)}</td>
                                                <td>${item.tinh}</td>
                                                <td>${item.quan}</td>
                                                <td>${item.xa}</td>
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
                                                <td>${item.specialCoverage}</td>                                                
                                                <td>${item.tenTrenHeThong}</td>
                                                <td>${item.lac}</td>
                                                <td>${item.ci}</td>
                                                <td>${item.frequencyBand}</td>
                                                <td>${item.trangThaiHdId}</td>
                                                <td>${item.trangThaiQlId}</td>
                                                <td>${item.tenThietBi}</td>
                                                <td>${item.tenLoaiTram}</td>                                                                              
                                            </tr>
                                        </c:forEach>                                       							
                                    </tbody>                                    
                                </table>   
                            </div>
                        </c:if>
                        <c:if test='${type=="6"}'>
                            <div id="tablePagingId">
                                <table id="example1" class="table table-bordered table-hover"
                                       style=" overflow-x:scroll !important;;overflow-y:hidden !important;">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tỉnh/TP</th>
                                            <th>Quận/Huyện</th>
                                            <th>Phường/Xã</th>
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
                                            <th>FREQUENCY_BAND</th>
                                            <th>TRANG_THAI_HD_ID</th>
                                            <th>TRANG_THAI_QL_ID</th>
                                            <th>TEN_THIET_BI</th>
                                            <th>TEN_LOAI_TRAM</th>                                    
                                        </tr>
                                    </thead>
                                    <tbody>                                       
                                        <c:forEach var="item" items="${list}" varStatus="status">                                        
                                            <tr>
                                                <td>${startRow + (status.index)}</td>
                                                <td>${item.tinh}</td>
                                                <td>${item.quan}</td>
                                                <td>${item.xa}</td>
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
                                                <td>${item.frequencyBand}</td>
                                                <td>${item.trangThaiHdId}</td>
                                                <td>${item.trangThaiQlId}</td>
                                                <td>${item.tenThietBi}</td>
                                                <td>${item.tenLoaiTram}</td>                                                                                
                                            </tr>
                                        </c:forEach>                                       							
                                    </tbody>                                    
                                </table>   
                            </div>
                        </c:if>

                        <c:if test='${type=="2" || type=="3"}'>
                            <div id="tablePagingId">
                                <table id="example1" class="table table-bordered table-hover"
                                       style=" overflow-x:scroll !important;;overflow-y:hidden !important;">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tỉnh/TP</th>
                                            <th>Quận/Huyện</th>
                                            <th>Phường/Xã</th>
                                            <th>Dịa chỉ</th>
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
                                            <th>Tên BSC/RNC quản lý</th>					
                                            <th>MSC/MSS</th>
                                            <th>SGSN</th>
                                            <th>Mã trạm</th>
                                            <!--<th>DC-HSDPA 42M</th>-->
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
                                            <th>Thời gian bảo dưỡng</th>
                                            <th>Đơn vị thực hiện</th>
                                            <th>Mã kiểm định</th>
                                            <th>Ngày hiệu lực</th>
                                            <th>Ngày hết hiệu lực</th>
                                            <th>Tên người quản lý</th>	
                                            <th>Số đt quản lý</th>	
                                            <th>Chung cơ sở hạ tầng</th>
                                            <th>Loại trạm CSHT</th>
                                            <th>Độ cao cột Anten</th>
                                            <th>Độ cao nhà đặt cột Anten</th>
                                            <th>Loại cột Anten</th>
                                            <th>Ngày hoạt động của tủ nguồn</th>
                                            <th>Chủng loại tủ nguồn</th>
                                            <th>Số module tủ nguồn</th>
                                            <th>Ngày hoạt động máy nổ</th>
                                            <th>Chủng loại máy nổ</th>
                                            <th>Công suất máy nổ (KVA)</th>
                                            <th>Máy nổ cố định di động</th>
                                            <th>Ngày hoạt động của ACCU</th>
                                            <th>Chủng loại ACCU</th>
                                            <th>Dung lượng ACCU (AH)</th>
                                            <th>TG hoạt động sau khi mất điện lưới ACCU (giờ)</th>
                                            <th>Ngày bảo dưỡng ACCU</th>
                                            <th>Chủng loại Truyền dẫn</th>
                                            <th>Dung lượng truyền dẫn</th>
                                            <th>Điện trở của hệ thống tiếp đất (Om)</th>																						
                                        </tr>
                                    </thead>
                                    <tbody>                                       
                                        <c:forEach var="item" items="${list}" varStatus="status">                                        
                                            <tr>
                                                <td>${startRow + (status.index)}</td>
                                                <td>${item.tinh}</td>
                                                <td>${item.quan}</td>
                                                <td>${item.xa}</td>
                                                <td>${item.diachi}</td>
                                                <td>${item.ngayHoatDong}</td>
                                                <td>${item.hoanCanhRaDoi}</td>
                                                <td>${item.tenBts}</td>
                                                <td>${item.ngayDangKy}</td>
                                                <td>${item.ngayKiemDuyet}</td>
                                                <td>${item.ngayCapPhep}</td>
                                                <td>${item.trangThaiHdId}</td>
                                                <td>${item.trangThaiQlId}</td>

                                                <td>${item.tenTrenHeThong}</td>
                                                <td>${item.tenBscRnc}</td>
                                                <td>${item.tenBscRncQl}</td>
                                                 <td>${item.mscMss}</td>
                                                 <td>${item.sgsn}</td>
                                                <td>${item.maNode}</td>

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
                                                <td>${item.ngayBaoDuong}</td>
                                                <td>${item.donViThucHien}</td>

                                                <td>${item.maKiemDinh}</td>
                                                <td>${item.ngayHieuLuc}</td>
                                                <td>${item.ngayHetHieuLuc}</td>
                                                <td>${item.tenNguoiQL}</td>
                                                <td>${item.soDTNgQL}</td>
                                                <td>${item.chungCSHT}</td>
                                                <td>${item.loaiTramCSHT}</td>
                                                <td>${item.docaoAnTen}</td>
                                                <td>${item.doCaoNhaDatAnten}</td>

                                                <td>${item.loaiAnTenId}</td>
                                                <td>${item.ngayHDTuNguon}</td>
                                                <td>${item.loaiTuNguonId}</td>
                                                <td>${item.soModuleTuNguon}</td>
                                                <td>${item.ngayHDMayNo}</td>
                                                <td>${item.loaiMayNoId}</td>
                                                <td>${item.congSuatMayNo}</td>
                                                <td>${item.trangThaiDatMayNo}</td>

                                                <td>${item.ngayHDAccu}</td>
                                                <td>${item.loaiAcQuyId}</td>

                                                <td>${item.dungLuongAcc}</td>
                                                <td>${item.thoiGianHdSauMatDien}</td>
                                                <td>${item.ngayBaoDuongAccu}</td>
                                                <td>${item.loaiTruyenDanId}</td>
                                                <td>${item.duongLuongTruyenDan}</td>
                                                <td>${item.dienTroTiepDia}</td>                                                                                                
                                            </tr>
                                        </c:forEach>                                       							
                                    </tbody>                                    
                                </table>   
                            </div>
                        </c:if>


                        <!--nodeB-->
                        <c:if test='${type=="333"}'>
                            <div id="tablePagingId">
                                <table id="example1" class="table table-bordered table-hover"
                                       style=" overflow-x:scroll !important;;overflow-y:hidden !important;">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tỉnh/TP</th>
                                            <th>Quận/Huyện</th>
                                            <th>Phường/Xã</th>
                                            <th>Dịa chỉ</th>
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
                                            <th>Tên BSC/RNC quản lý</th>				
                                            
                                            <!--<th>SGSN</th>-->
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
                                            <th>Thời gian bảo dưỡng</th>
                                            <th>Đơn vị thực hiện</th>
                                            <th>Mã kiểm định</th>
                                            <th>Ngày hiệu lực</th>
                                            <th>Ngày hết hiệu lực</th>
                                            <th>Tên người quản lý</th>	
                                            <th>Số đt quản lý</th>	
                                            <th>Chung cơ sở hạ tầng</th>
                                            <th>Loại trạm CSHT</th>
                                            <th>Độ cao cột Anten</th>
                                            <th>Độ cao nhà đặt cột Anten</th>
                                            <th>Loại cột Anten</th>
                                            <th>Ngày hoạt động của tủ nguồn</th>
                                            <th>Chủng loại tủ nguồn</th>
                                            <th>Số module tủ nguồn</th>
                                            <th>Ngày hoạt động máy nổ</th>
                                            <th>Chủng loại máy nổ</th>
                                            <th>Công suất máy nổ (KVA)</th>
                                            <th>Máy nổ cố định di động</th>
                                            <th>Ngày hoạt động của ACCU</th>
                                            <th>Chủng loại ACCU</th>
                                            <th>Dung lượng ACCU (AH)</th>
                                            <th>TG hoạt động sau khi mất điện lưới ACCU (giờ)</th>
                                            <th>Ngày bảo dưỡng ACCU</th>
                                            <th>Chủng loại Truyền dẫn</th>
                                            <th>Dung lượng truyền dẫn</th>
                                            <th>Điện trở của hệ thống tiếp đất (Om)</th>																						
                                        </tr>
                                    </thead>
                                    <tbody>                                       
                                        <c:forEach var="item" items="${list}" varStatus="status">                                        
                                            <tr>
                                                <td>${startRow + (status.index)}</td>
                                                <td>${item.tinh}</td>
                                                <td>${item.quan}</td>
                                                <td>${item.xa}</td>
                                                <td>${item.diachi}</td>
                                                <td>${item.ngayHoatDong}</td>
                                                <td>${item.hoanCanhRaDoi}</td>
                                                <td>${item.tenBts}</td>
                                                <td>${item.ngayDangKy}</td>
                                                <td>${item.ngayKiemDuyet}</td>
                                                <td>${item.ngayCapPhep}</td>
                                                <td>${item.trangThaiHd}</td>
                                                <td>${item.trangThaiQuanLy}</td>

                                                <td>${item.tenTrenHeThong}</td>
                                                <td>${item.tenBscRnc}</td>
                                                <td>${item.tenBscRncQuanLy}</td>
                                                
                                                <td>${item.maNode}</td>

                                                <td>${item.dcHsdpa42M}</td>
                                                <td>${item.filterUser}</td>
                                                <td>${item.frequencyBand}</td>
                                                <td>${item.latitude}</td>
                                                <td>${item.longitude}</td>
                                                <td>${item.cosite2G3G}</td>

                                                <td>${item.maCosite2G3G}</td>
                                                <td>${item.thietBi}</td>
                                                <td>${item.loaiTram}</td>
                                                <td>${item.cauHinh}</td>
                                                <td>${item.cauHinhBangTan}</td>
                                                <td>${item.thoiGianBaoDuong}</td>
                                                <td>${item.donViThucHien}</td>

                                                <td>${item.maKiemDinh}</td>
                                                <td>${item.ngayHieuLuc}</td>
                                                <td>${item.ngayHetHieuLuc}</td>
                                                <td>${item.tenNguoiQuanLy}</td>
                                                <td>${item.soDtQuanLy}</td>
                                                <td>${item.chungCSHT}</td>
                                                <td>${item.loaiTramCSHT}</td>
                                                <td>${item.doCaoCotAnten}</td>
                                                <td>${item.doCaoNhaDatCotAnten}</td>

                                                <td>${item.loaiCotAnten}</td>
                                                <td>${item.ngayHoatDongCuaTuNguon}</td>
                                                <td>${item.chungLoaiTuNguon}</td>
                                                <td>${item.soModuleTuNguon}</td>
                                                
                                                <td>${item.ngayHoatDongCuaMayNo}</td>
                                                <td>${item.chungLoaiMayNo}</td>
                                                <td>${item.congSuatMayNo}</td>
                                                <td>${item.mayNoCoDinhDiDong}</td>
                                                <td>${item.ngayHoatDongCuaAccu}</td>

                                                <td>${item.chungLoaiAccu}</td>
                                                <td>${item.dungLuongAccu}</td>


                                                <td>${item.thoiGianHoatDongSauKhiMatDienLuoi}</td>
                                                <td>${item.ngayBaoDuongAccu}</td>
                                                <td>${item.chungLoaiTruyenDan}</td>
                                                <td>${item.dungLuongTruyenDan}</td>
                                                <td>${item.dienTroCuaHeThongTiepDat}</td>                                                                                                
                                            </tr>
                                        </c:forEach>                                       							
                                    </tbody>                                    
                                </table>   
                            </div>
                        </c:if>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <!-- ADD PAGE INFO -->
                        <%@include file="../../include/page_info.jsp"%>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>        
    </c:if>
</section>


<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>

<div id="addFiller" style="display: none">
    <div class="groupFilter">
        <div class="col-md-4">                            
            <div class="form-group">
                <select name="column" class="form-control" >
                    <option value="-1">--- Chọn thuộc tính ---</option>
                    <c:forEach var="item" items="${filterReportList}">
                        <option value="${item.columnId }" >${item.columnName}</option>
                    </c:forEach>
                </select>  
            </div>
        </div>
        <div class="col-md-2">
            <div class="form-group">
                <select name="filterType"  class="form-control"> 
                    <option value="Contains">Contains</option>
                    <option value="Not contains">Not contains</option>
                    <option value="startWith">startWith</option>
                    <option value="endWith">endWith</option>
                    <option value="Exact">Exact</option>
                    <option value="NULL">NULL</option>
                    <option value="NOT NULL">NOT NULL</option>
                </select>  
            </div>
        </div>                        
        <div class="col-md-4">
            <div class="form-group">
                <input  name="value_" class="form-control" placeholder="Giá trị" required="true"                        
                        type="text" value=" "/>                
                <input  name="dataType" class="form-control"
                        type="hidden" value="0"/>                
            </div>
        </div>  
        <div class="col-md-2">
            <div class="form-group">
                <button type="button" onclick="removeText(this)" class="btn btn-danger">Remove (-)</button>    
            </div>
        </div>
        <div class="clearfix" ></div>
    </div>
</div>

<script>

    function fnChangeObject() {
        $('form#form_changeObject').submit();
    }
    function afterText()
    {
        $('#boxSearch').append($('#addFiller').html());
        return false;
    }

    function removeText(that) {

        $(that).parents('.groupFilter').remove()();
    }

    removeId = function(id) {
        document.getElementById('idRemove').value = id;
    };
    getComboA = function(sel) {
        if (sel.value == '-1') {
            $('#btnAdd').hide();
        } else {
            $('#btnAdd').show();
        }
//        $('form#form_change_column').submit('changeColumn');
        $('form#form_change_column').append($('<input>').attr('type', 'hidden').attr('name', 'changeColumn').val('endActivity')).submit();

//        var id = $("#idColumn").val();
//
//        $.get("${pageContext.request.contextPath}/report/configCell/changeColumn/" + id, function(data) {
//            var html = '<option value="" >--- Quận / Huyện ---</option>';
//            if (data.length > 0) {
//                data.forEach(function(entry) {
//                    var htmlx = '<option value="' + entry.quanHuyenId + '">' + entry.tenQuanHuyen + '</option>';
//                    html += htmlx;
//
//                });
//
//            }
//            ;
//            $('#quanHuyenId').html(html);
//        });

    }
    $(document).ready(function() {
        if (document.getElementById('idColumn') != null) {
            if (document.getElementById('idColumn').value == '-1') {
                $('#btnAdd').hide();
            }
        }
        ;
        $('#ngay').datepicker({
            format: 'mm/dd/yyyy',
            todayHighlight: true,
            autoclose: true
        });
    });
    setDesc = function(dataType) {
        var el = document.getElementById('idColumn');
        var text = el.options[el.selectedIndex].innerHTML;
        document.getElementById('description').value = text;
        if (dataType == 2 && document.getElementById('ngay').value == '') {
            alert('Vui lòng nhập ngày theo đúng định dạng');
            return false;
        }
    }

</script>

