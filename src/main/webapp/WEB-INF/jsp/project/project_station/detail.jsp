<%-- 
    Document   : edit thong tin chung cua tram quy hoạch
    Created on : Dec 21, 2016, 2:36:54 PM
    Author     : Cyano
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="box-body">                    
    <c:if test='${fn:contains(classAtrrView,"THONG_TIN_CHUNG")}'>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Thông tin chung</h3>
            </div>
            <div class="panel-body">  
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Dự án quy hoạch</label>
                            <select name="qhTinhId"  id="qhTinhId" class="form-control" required="true">
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
                            <input type="text" class="form-control"  id="maQh" name="maQh" placeholder="nhập mã trạm quy hoạch" value="${tramQuyHoach.maQh}" required="true" />
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
                            <input required="true" type="text"  class="form-control" id="buildingCode" name="buildingCode" placeholder="Mã building" disabled value="${tramQuyHoach.buildingCode}" /> 
                            <input type="hidden" name="buildingId" value="" id="buildingId" required="true"/>
                            <input type="hidden" name="tramQhId" id="tramQhId">
                            <input type="hidden" name="tinhTpId" id="tinhTpId" value="${tramQuyHoach.tinhTpId}"/>
                            <input type="hidden" name="quanHuyenId" id="quanHuyenId" value="${tramQuyHoach.quanHuyenId}"/>
                            <input type="hidden" name="phuongXaId" id="phuongXaId" value="${tramQuyHoach.phuongXaId}"/>
                            <input type="hidden" name="DIA_CHI" id="DIA_CHI" maxlength="200"/>
                        </div>
                    </div>    
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Năm khởi tạo</label>
                            <input type="text" class="form-control date_form"  id="namKhoiTao" name="namKhoiTao" placeholder="Năm khởi tạo" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${tramQuyHoach.namKhoiTao}" />"  required="true"/>
                        </div>
                    </div>
                </div>

                <!-- long -->    
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Longitude</label>
                            <input type="number" class="form-control" readonly="true"  id="longitude" step="0.000001" name="longitude" placeholder="Longitude" value="${tramQuyHoach.longitude}" required="true" />
                        </div>
                    </div>
                </div>

                <!-- lat -->    
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Latitude</label>
                            <input type="number" class="form-control" readonly="true" id="latitude" step="0.000001" name="latitude" placeholder="Latitude"  value="${tramQuyHoach.latitude}" required="true"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Loại công nghệ</label>
                            <select name="loaiCongNgheId" id="loaiCongNgheId" class="form-control" required="true" >
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
    </c:if>
    <!-- end thong tin chung -->
    <c:if test='${fn:contains(classAtrrView,"CAM_KET_THIET_BI")}'>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Cam kết tiết bị</h3>
            </div>
            <div class="panel-body">   
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Mã trạm dự án</label>
                            <input type="text" class="form-control"  id="maQh" name="maQh" placeholder="Nhập mã trạm dự án" value="${tramQuyHoach.maQh}" disabled="true"/>
                            <input type="hidden" class="form-control" name="tramQhId" id="tramQhId">
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Tên trạm dự án</label>
                            <input type="text" class="form-control"  id="tenQh" name="tenQh" placeholder="Tên trạm dự án" value="${tramQuyHoach.tenQh}" disabled="true" />
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" >    
                        <div class="input-group">
                            <label class=" input-group-addon" >Đơn vị chịu trách nhiệm</label>
                            <select name="DVI_TRACH_NHIEM_CCTB_ID" id="DVI_TRACH_NHIEM_CCTB_ID" class="form-control"  required="true">
                                <option value="">-Chọn-</option>
                                <c:forEach var="donViChiuTrachNhiem" items="${donViLst}">
                                    <option value="${donViChiuTrachNhiem.don_vi_phe_duyet_id}" <c:choose> <c:when test="${donViChiuTrachNhiem.don_vi_phe_duyet_id == tramQuyHoach.DVI_TRACH_NHIEM_CCTB_ID}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${donViChiuTrachNhiem.ten_don_vi_phe_duyet}</option>
                                </c:forEach>
                            </select>  
                        </div> 
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" >   
                        <div class="input-group">
                            <label class=" input-group-addon" >Nguồn thiết bị</label>
                            <select name="NGUON_THIET_BI_ID" id="NGUON_THIET_BI_ID" class="form-control" required="true" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="nguonThietBi" items="${nguonThietBiLst}">
                                    <option value="${nguonThietBi.nguon_thiet_bi_id}" <c:choose> <c:when test="${nguonThietBi.nguon_thiet_bi_id == tramQuyHoach.NGUON_THIET_BI_ID}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${nguonThietBi.ten_nguon_thiet_bi}</option>
                                </c:forEach>
                            </select>  
                        </div> 
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Thời điểm đáp ứng dự kiến</label>
                            <input type="text" class="form-control date_form" required="true"  name="THOI_DIEM_DAP_UNG_DU_KIEN" id="THOI_DIEM_DAP_UNG_DU_KIEN" placeholder="Thời điểm đáp ứng dự kiến" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.THOI_DIEM_DAP_UNG_DU_KIEN}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group" >        
                        <div class="input-group">
                            <label class=" input-group-addon" >Công nghệ đáp ứng</label>
                            <select name="CONG_NGHE_DAP_UNG" id="CONG_NGHE_DAP_UNG" class="form-control" required="true"  >
                                <option value="">Chọn</option>
                                <c:forEach var="congNgheDapUng" items="${loaiCongNgheLst}">
                                    <option value="${congNgheDapUng.id_loai_cong_nghe}" <c:choose> <c:when test="${congNgheDapUng.id_loai_cong_nghe == tramQuyHoach.CONG_NGHE_DAP_UNG}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${congNgheDapUng.ten_loai_cong_nghe}</option>
                                </c:forEach>
                            </select>  
                        </div>    
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group" >
                            <label class=" input-group-addon" >Chủng loại thiết bị</label>
                            <input type="text" class="form-control"  name="CHUNG_LOAI_THIET_BI" required="true"  placeholder="Chủng loại thiết bị" value="${tramQuyHoach.CHUNG_LOAI_THIET_BI}" />
                        </div>
                    </div>    
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Thời gian đáp ứng thiết bị thực tế</label>
                            <input type="text" class="form-control date_form"  id="THOI_DIEM_DAP_UNG_THUC_TE" name="THOI_DIEM_DAP_UNG_THUC_TE" placeholder="Thời gian đáp ứng thực tế"
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.THOI_DIEM_DAP_UNG_THUC_TE}" />"  />
                        </div>
                    </div>    
                </div>
                <div class="col-md-6">
                    <div class="form-group" class="form-control">
                        <div class="input-group">       
                            <label class=" input-group-addon" style="min-width:150px;" >Khó khăn vướng mắc (nếu có)</label>
                            <input  type="text" class="form-control" id="KHO_KHAN_VUONG_MAC" name="KHO_KHAN_VUONG_MAC" placeholder="Khó khăn vướng mắc" value="${tramQuyHoach.KHO_KHAN_VUONG_MAC}" />                    
                        </div>
                    </div> 
                </div>
            </div>
        </div>   
    </c:if>
    <!-- CSHT -->
    <c:if test='${fn:contains(classAtrrView,"CO_SO_HA_TANG")}'>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Thông tin chung cơ sở hạ tầng</h3>
            </div>
            <div class="panel-body">    
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Mã trạm dự án</label>
                            <input type="text" class="form-control"  id="maQh" name="maQh" placeholder="nhập mã trạm dự án" value="${tramQuyHoach.maQh}" disabled="true"/>
                            <input type="hidden" class="form-control" name="tramQhId" id="tramQhId">
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Tên trạm dự án</label>
                            <input type="text" class="form-control"  id="tenQh" name="tenQh" placeholder="Tên trạm dự án" value="${tramQuyHoach.tenQh}" disabled="true" />
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Đơn vị chịu trách nhiệm</label>
                            <select name="DVI_TRACH_NHIEM_CSHT_ID" id="DVI_TRACH_NHIEM_CSHT_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <c:forEach var="donViChiuTrachNhiem" items="${donViLst}">
                                    <option value="${donViChiuTrachNhiem.don_vi_phe_duyet_id}" <c:choose> <c:when test="${donViChiuTrachNhiem.don_vi_phe_duyet_id == tramQuyHoach.DVI_TRACH_NHIEM_CSHT_ID}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${donViChiuTrachNhiem.ten_don_vi_phe_duyet}</option>
                                </c:forEach>
                            </select>  
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" >          
                        <div class="input-group">
                            <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                            <select name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen(0);" disabled="true" > 
                                <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                                <c:forEach var="tinhBO" items="${tinhThanhPhoLst}">
                                    <option  
                                        value="${tinhBO.tinhTpId}"  <c:choose>
                                            <c:when test="${tinhBO.tinhTpId == tramQuyHoach.tinhTpId}">
                                                selected    
                                            </c:when>    
                                        </c:choose>

                                        >${tinhBO.tenTinhTp}</option>
                                </c:forEach>
                            </select>
                        </div> 
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" >          
                        <div class="input-group">
                            <label class=" input-group-addon" >Quận/Huyện</label>                                    
                            <select name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa(0);" disabled="true"> 
                                <option value="">---Chọn Quận / Huyện ---</option>
                            </select>
                        </div> 
                    </div>
                </div>

                <!--                            <div class="col-md-6">
                                                <div class="form-group" >
                                                    <div class="input-group">
                                                        <label class=" input-group-addon" >Phường/Xã</label>
                                                        <select name="phuongXaId" id="phuongXaId" class="form-control" onchange="getListPhuongXa(0);"> 
                                                            <option value="">---Chọn Phường/Xã ---</option>
                                                        </select>
                                                    </div>    
                                                </div>
                                            </div>-->
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Địa chỉ</label>
                            <input type="text" class="form-control"   name="DIA_CHI" id="DIA_CHI" value="${tramQuyHoach.DIA_CHI}" disabled="true" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Tên trạm</label>
                            <input type="text" class="form-control"   name="TEN_TRAM" id="TEN_TRAM" value="${tramQuyHoach.TEN_TRAM}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group" >   
                        <div class="input-group">
                            <label class=" input-group-addon" >Cách thức xây dựng</label>
                            <select name="CACH_XAY_CSHT_ID" id="CACH_XAY_CSHT_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <c:forEach var="cachThucXayDung" items="${cachThucXayDungLst}">
                                    <option value="${cachThucXayDung.cach_xay_csht_id}" <c:choose> <c:when test="${cachThucXayDung.cach_xay_csht_id == tramQuyHoach.CACH_XAY_CSHT_ID}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${cachThucXayDung.ten_cach_xay_csht}</option>
                                </c:forEach>
                            </select>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Thông tin đất</h3>
            </div>
            <div class="panel-body">
                <div class="col-md-6">
                    <div class="form-group" >
                        <div class="input-group">
                            <label class=" input-group-addon" >Loại đất</label>
                            <select name="LOAI_DAT_ID" id="LOAI_DAT_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <c:forEach var="loaiDat" items="${loaiDatLst}">
                                    <option value="${loaiDat.loai_dat_id}" <c:choose> <c:when test="${loaiDat.loai_dat_id == tramQuyHoach.LOAI_DAT_ID}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${loaiDat.ten_loai_dat}</option>
                                </c:forEach>
                            </select>  
                        </div> 
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày cấp/thuê đất</label>
                            <input type="text" class="form-control date_form"   name="NGAY_CAP_DAT" id="NGAY_CAP_DAT" placeholder="Ngày cấp/thuê đất" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_CAP_DAT}" />"  />
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Thông tin nhà trạm</h3>
            </div>
            <div class="panel-body">
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày xin phép xây dựng nhà trạm</label>
                            <input type="text" class="form-control date_form"   name="NGAY_XIN_PHEP_XD_NHA_TRAM" id="NGAY_XIN_PHEP_XD_NHA_TRAM" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_XIN_PHEP_XD_NHA_TRAM}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày hoàn thành thủ tục xây </label>
                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_THU_TUC" id="NGAY_HOAN_THANH_THU_TUC" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_THU_TUC}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày khởi công xây dựng </label>
                            <input type="text" class="form-control date_form"   name="NGAY_KHOI_CONG_XD_NHA_TRAM" id="NGAY_KHOI_CONG_XD_NHA_TRAM"  
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_KHOI_CONG_XD_NHA_TRAM}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày hoàn thành xây </label>
                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_XD_NHA_TRAM" id="NGAY_HOAN_THANH_XD_NHA_TRAM"  
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_XD_NHA_TRAM}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group" >   
                        <div class="input-group">
                            <label class=" input-group-addon" >Loại nhà trạm</label>
                            <select name="LOAI_NHA_TRAM_ID" id="LOAI_NHA_TRAM_ID" class="form-control"   >
                                <option value="">-Chọn-</option>
                                <c:forEach var="loaiNhaTram" items="${loaiNhaTramLst}">
                                    <option value="${loaiNhaTram.loai_nha_tram_id}" <c:choose> 
                                                <c:when test="${loaiNhaTram.loai_nha_tram_id == tramQuyHoach.LOAI_NHA_TRAM_ID}">
                                                    selected    
                                                </c:when>    
                                            </c:choose> >${loaiNhaTram.ten_loai_nha_tram}</option>
                                </c:forEach>
                            </select>  
                        </div>     
                    </div>
                </div>

            </div>
        </div>

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Cột Antena</h3>
            </div>
            <div class="panel-body">
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày xin phép độ cao cột</label>
                            <input type="text" class="form-control date_form"   name="NGAY_XIN_PHEP_DO_CAO_COT" id="NGAY_XIN_PHEP_DO_CAO_COT"  
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_XIN_PHEP_DO_CAO_COT}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày cấp phép độ cao cột</label>
                            <input type="text" class="form-control date_form"   name="NGAY_CAP_PHEP_DO_CAO_COT" id="NGAY_CAP_PHEP_DO_CAO_COT"  
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_CAP_PHEP_DO_CAO_COT}" />"  />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày hoàn thành thủ tục xây dựng cột</label>
                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_THU_TUC_XD_COT" id="NGAY_HOAN_THANH_THU_TUC_XD_COT" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_THU_TUC_XD_COT}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày khởi công dựng cột</label>
                            <input type="text" class="form-control date_form"   name="NGAY_KHOI_CONG_XD_COT" id="NGAY_KHOI_CONG_XD_COT"  
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_KHOI_CONG_XD_COT}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày hoàn thành cột</label>
                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_XD_COT" id="NGAY_HOAN_THANH_XD_COT" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_XD_COT}" />"  />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Loại cột</label>
                            <select name="LOAI_COT_ANTEN_ID" id="tramQuyHoach" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <c:forEach var="loaiCot" items="${loaiCotLst}">
                                    <option value="${loaiCot.loai_cot_anten_id}" <c:choose> <c:when test="${loaiCot.loai_cot_anten_id == tramQuyHoach.LOAI_COT_ANTEN_ID}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${loaiCot.loai_cot_anten}</option>
                                </c:forEach>
                            </select>  
                        </div>
                    </div>     
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Độ cao cột</label>
                            <input type="number" class="form-control"   name="DO_CAO_COT" id="DO_CAO_COT"  value="${tramQuyHoach.DO_CAO_COT}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Độ cao chân cột so với mực nước biển</label>
                            <input type="number" class="form-control"   name="DO_CAO_VI_TRI_XAY_COT_ANTTEN" id="DO_CAO_VI_TRI_XAY_COT_ANTTEN"  value="${tramQuyHoach.DO_CAO_VI_TRI_XAY_COT_ANTTEN}" />
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Truyền dẫn</h3>
            </div>
            <div class="panel-body">
                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Phương thức truyền dẫn</label>
                            <select name="LOAI_TRUYEN_DAN_ID" id="LOAI_TRUYEN_DAN_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <c:forEach var="loaiTruyenDan" items="${loaiTruyenDanLst}">
                                    <option value="${loaiTruyenDan.id}" <c:choose> <c:when test="${loaiTruyenDan.id == tramQuyHoach.LOAI_TRUYEN_DAN_ID}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${loaiTruyenDan.name}</option>
                                </c:forEach>
                            </select>  
                        </div>
                    </div>     
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Giao diện truyền dẫn E1</label>
                            <input type="number" class="form-control"   name="GIAODIEN_TD_E1" id="GIAODIEN_TD_E1"  value="${tramQuyHoach.GIAODIEN_TD_E1}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Giao diện truyền dẫn FE</label>
                            <select name="GIAODIEN_TD_FE_ID" id="GIAODIEN_TD_FE_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <c:forEach var="gdTruyenDan" items="${gdTruyenDanLst}">
                                    <option value="${gdTruyenDan.giaodien_td_id}" <c:choose> <c:when test="${gdTruyenDan.giaodien_td_id == tramQuyHoach.GIAODIEN_TD_FE_ID}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${gdTruyenDan.ten_giaodien_td}</option>
                                </c:forEach>
                            </select>  
                        </div>
                    </div>     
                </div>
                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Giao diện truyền dẫn GE</label>
                            <select name="GIAODIEN_TD_GE_ID" id="GIAODIEN_TD_GE_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <c:forEach var="gdTruyenDan" items="${gdTruyenDanLst}">
                                    <option value="${gdTruyenDan.giaodien_td_id}" <c:choose> <c:when test="${gdTruyenDan.giaodien_td_id == tramQuyHoach.GIAODIEN_TD_GE_ID}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${gdTruyenDan.ten_giaodien_td}</option>
                                </c:forEach>
                            </select>  
                        </div>
                    </div>     
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Giao diện truyền dẫn STM1</label>
                            <input type="number" class="form-control"   name="GIAODIEN_TD_STM1" id="GIAODIEN_TD_STM1"  value="${tramQuyHoach.GIAODIEN_TD_STM1}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày khởi công truyền dẫn</label>
                            <input type="text" class="form-control date_form"   name="NGAY_KHOI_CONG_TD" id="NGAY_KHOI_CONG_TD" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_KHOI_CONG_TD}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày hoàn thành truyền dẫn</label>
                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_TD" id="NGAY_HOAN_THANH_TD" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_TD}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày đáp ứng điện AC</label>
                            <input type="text" class="form-control date_form"   name="NGAY_DAP_UNG_DIEN_AC" id="NGAY_DAP_UNG_DIEN_AC" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_DAP_UNG_DIEN_AC}" />"  />
                        </div>
                    </div>
                </div>
            </div>             
        </div>

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Phụ trợ</h3>
            </div>
            <div class="panel-body">
                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Hệ thống điện trong nhà trạm</label>
                            <select name="HE_THONG_DIEN_NHA_TRAM_ID" id="HE_THONG_DIEN_NHA_TRAM_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <option  value="0">Chưa có</option>
                                <option value="1">Đã có</option>
                            </select>  
                        </div>
                    </div> 
                </div>
                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Hệ thống điều hòa</label>
                            <select name="HE_THONG_DIEU_HOA_ID" id="HE_THONG_DIEU_HOA_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <option  value="0">Chưa có</option>
                                <option value="1">Đã có</option>
                            </select>  
                        </div>
                    </div>  
                </div>
                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Hệ thống tiếp đất</label>
                            <select name="HE_THONG_TIEP_DAT_ID" id="HE_THONG_TIEP_DAT_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <option  value="0">Chưa có</option>
                                <option value="1">Đã có</option>
                            </select>  
                        </div>
                    </div>  
                </div>
                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Máy nổ</label>
                            <select name="HE_THONG_MAY_NO_ID" id="HE_THONG_MAY_NO_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <option  value="0">Chưa có</option>
                                <option value="1">Đã có</option>
                            </select>  
                        </div>
                    </div>  
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày hoàn thành phụ trợ</label>
                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_PHU_TRO" id="NGAY_HOAN_THANH_PHU_TRO" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_PHU_TRO}" />"  />
                        </div>
                    </div>
                </div>
            </div>     
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Văn bản thông báo hoàn thành CSHT</h3>
            </div>
            <div class="panel-body">
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Đối tượng thông báo</label>
                            <input type="text" class="form-control"   name="DOI_TUONG_THONG_BAO" id="DOI_TUONG_THONG_BAO"  value="${tramQuyHoach.DOI_TUONG_THONG_BAO}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Số hiệu thông báo</label>
                            <input type="text" class="form-control"   name="SO_HIEU_THONG_BAO" id="SO_HIEU_THONG_BAO"  value="${tramQuyHoach.SO_HIEU_THONG_BAO}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày thông báo hoàn thành CSHT</label>
                            <input type="text" class="form-control date_form"   name="NGAY_THONG_BAO_HT_CSHT" id="NGAY_THONG_BAO_HT_CSHT"  
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_THONG_BAO_HT_CSHT}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Khó khăn vướng mắc</label>
                            <input type="text" class="form-control"   name="KHO_KHAN_VUONG_MAC_CSHT" id="KHO_KHAN_VUONG_MAC_CSHT"  value="${tramQuyHoach.KHO_KHAN_VUONG_MAC_CSHT}" />
                        </div>
                    </div>
                </div>
            </div>             
        </div>
    </c:if>
    <!-- end csht -->
    <!-- nguon dc cua tram -->
    <c:if test='${fn:contains(classAtrrView,"NGUON_ANTENA")}'>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Nguồn DC của trạm</h3>
            </div>
            <div class="panel-body">    
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Mã trạm dự án</label>
                            <input type="hidden" name="tramQhId" value="${tramQuyHoach.tramQhId}" id="tramQhId" required />
                            <input type="text" class="form-control"  id="maQh" name="maQh" placeholder="nhập mã trạm dự án" value="${tramQuyHoach.maQh}" disabled="true"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Tên trạm dự án</label>
                            <input type="text" class="form-control"  id="tenQh" name="tenQh" placeholder="Tên trạm dự án" value="${tramQuyHoach.tenQh}" disabled="true" />
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Đơn vị chịu trách nhiệm</label>
                            <select name="DVI_TRACH_NHIEM_NGUON_DC_ID" id="DVI_TRACH_NHIEM_NGUON_DC_ID" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <c:forEach var="donViChiuTrachNhiem" items="${donViLst}">
                                    <option value="${donViChiuTrachNhiem.don_vi_phe_duyet_id}" 
                                            <c:choose> 
                                                <c:when test="${donViChiuTrachNhiem.don_vi_phe_duyet_id == tramQuyHoach.DVI_TRACH_NHIEM_NGUON_DC_ID}">
                                                    selected    
                                                </c:when>    
                                            </c:choose> >
                                        ${donViChiuTrachNhiem.ten_don_vi_phe_duyet}
                                    </option>
                                </c:forEach>
                            </select>  
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" >          
                        <div class="input-group">
                            <label class=" input-group-addon" >Tủ nguồn</label>                                    
                            <select name="NGUON_THIET_BI_TU_NGUON_ID" id="NGUON_THIET_BI_TU_NGUON_ID" class="form-control"  >
                                <option value="">-Chọn-</option> 
                                <c:forEach var="nguonTB" items="${nguonThietBiLst}">
                                    <option value="${nguonTB.nguon_thiet_bi_id}" 
                                            <c:choose> 
                                                <c:when test="${nguonTB.nguon_thiet_bi_id == tramQuyHoach.NGUON_THIET_BI_TU_NGUON_ID}">
                                                    selected    
                                                </c:when>    
                                            </c:choose> >
                                        ${nguonTB.ten_nguon_thiet_bi}
                                    </option>
                                </c:forEach>
                            </select>  
                        </div> 
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" >          
                        <div class="input-group">
                            <label class=" input-group-addon" >Loại tủ nguồn</label>                                    
                            <select name="LOAI_TU_NGUON_ID" id="LOAI_TU_NGUON_ID" class="form-control"   >
                                <option value="">-Chọn-</option>
                                <c:forEach var="loaiTuNguon" items="${loaiTuNguon}">
                                    <option value="${loaiTuNguon.id}" 
                                            <c:choose> 
                                                <c:when test="${loaiTuNguon.id == tramQuyHoach.LOAI_TU_NGUON_ID}">
                                                    selected    
                                                </c:when>    
                                            </c:choose> >
                                        ${loaiTuNguon.name}
                                    </option>
                                </c:forEach>
                            </select>  
                        </div> 
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Dung lượng tủ nguồn (A)</label>
                            <input type="number" class="form-control"   name="DUNG_LUONG_TU_NGUON" id="DUNG_LUONG_TU_NGUON" value="${tramQuyHoach.DUNG_LUONG_TU_NGUON}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Số lượng racktifier</label>
                            <input type="number" class="form-control"   name="SO_RACTIFIER" id="SO_RACTIFIER" value="${tramQuyHoach.SO_RACTIFIER}" />
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Dung lượng acquy (AH)</label>
                            <input type="number" class="form-control"   name="DUNG_LUONG_ACCU" id="DUNG_LUONG_ACCU" value="${tramQuyHoach.DUNG_LUONG_ACCU}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Số lượng tổ acquy</label>
                            <input type="number" class="form-control"  name="SO_LUONG_ACCU" id="SO_LUONG_ACCU" value="${tramQuyHoach.SO_LUONG_ACCU}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Điện áp acquy (V)</label>
                            <input type="number" class="form-control"    name="DIEN_AP_ACCU" id="DIEN_AP_ACCU" value="${tramQuyHoach.DIEN_AP_ACCU}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày đáp ứng nguồn DC dự kiến</label>
                            <input type="text" class="form-control date_form"   name="NGAY_DAP_UNG_NGUON_DC_DU_KIEN" id="NGAY_DAP_UNG_NGUON_DC_DU_KIEN" placeholder="Thời điểm đáp ứng dự kiến" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_DAP_UNG_NGUON_DC_DU_KIEN}" />"  />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Ngày đáp ứng nguồn DC thực tế</label>
                            <input type="text" class="form-control date_form"   name="NGAY_DAP_UNG_NGUON_DC_TT" id="NGAY_DAP_UNG_NGUON_DC_TT" placeholder="Thời điểm đáp ứng thực tế" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_DAP_UNG_NGUON_DC_TT}" />"  />
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- end dc cua tram -->
        <!-- angten --->
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Thông tin chung Anten của trạm</h3>
            </div>
            <div class="panel-body">  
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Mã trạm dự án</label>
                            <input type="text" class="form-control"  id="maQh" name="maQh" value="${tramQuyHoach.maQh}" disabled="true"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Tên trạm dự án</label>
                            <input type="text" class="form-control"  id="tenQh" name="tenQh"  value="${tramQuyHoach.tenQh}" disabled="true" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group" >
                        <div class="input-group">
                            <label class=" input-group-addon" >Đơn vị chịu trách nhiệm</label>
                            <select name="DVI_TRACH_NHIEM_ANTEN" id="DVI_TRACH_NHIEM_ANTEN" class="form-control"  >
                                <option value="">-Chọn-</option>
                                <c:forEach var="donViPheDuyet" items="${donViLst}">
                                    <option value="${donViPheDuyet.don_vi_phe_duyet_id}" <c:choose> <c:when test="${donViPheDuyet.don_vi_phe_duyet_id == tramQuyHoach.DVI_TRACH_NHIEM_ANTEN}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${donViPheDuyet.ten_don_vi_phe_duyet}</option>
                                </c:forEach>
                                <input type="hidden"  class="form-control" name="tramQhId" id="tramQhId">
                            </select>  
                        </div>  
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group" >
                            <label class=" input-group-addon" >Ngày đáp ứng Anten dự kiến</label>
                            <input type="text" class="form-control date_form"  name="NGAY_DAP_UNG_ANTENA_DU_KIEN" id="NGAY_DAP_UNG_ANTENA_DU_KIEN" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_DAP_UNG_ANTENA_DU_KIEN}" />" />
                        </div>
                    </div>    
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group" >
                            <label class=" input-group-addon" >Ngày đáp ứng Anten thực tế</label>
                            <input type="text" class="form-control date_form"  name="NGAY_DAP_UNG_ANTENA_THUC_TE" id="NGAY_DAP_UNG_ANTENA_THUC_TE" 
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_DAP_UNG_ANTENA_THUC_TE}" />" />
                        </div>
                    </div>    
                </div>                                        
            </div>
        </div>

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Antena1</h3>
            </div>
            <div class="panel-body">  
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Loại antena</label>
                            <select name="LOAI_ANTEN_ID1"  id="LOAI_ANTEN_ID1" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="loaiAntena" items="${loaiAntenaLst}">
                                    <option value="${loaiAntena.id}" <c:choose> <c:when test="${loaiAntena.id == tramQuyHoach.LOAI_ANTEN_ID1}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${loaiAntena.name}</option>
                                </c:forEach>
                            </select>

                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Tên antena</label>
                            <input type="text" class="form-control"  id="TEN_ANTENA1" name="TEN_ANTENA1" value="${tramQuyHoach.TEN_ANTENA1}" />
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Hãng sản xuất</label>
                            <input type="text" class="form-control"  id="HANG_SX_ANTENA1" name="HANG_SX_ANTENA1"  value="${tramQuyHoach.HANG_SX_ANTENA1}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Số lượng Antena</label>
                            <input type="number" class="form-control"  id="SO_LUONG_ANTENA1" name="SO_LUONG_ANTENA1"  value="${tramQuyHoach.SO_LUONG_ANTENA1}" />
                        </div>
                    </div>
                </div>   

                <div class="col-md-6">
                    <div class="form-group" >     
                        <div class="input-group">
                            <label class=" input-group-addon" >Băng tần</label>
                            <select name="BANG_TANG_ANTENA_ID1" id="BANG_TANG_ANTENA_ID1" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="bangTan" items="${bangTanLst}">
                                    <option value="${bangTan.bang_tan_id}" <c:choose> <c:when test="${bangTan.bang_tan_id == tramQuyHoach.BANG_TANG_ANTENA_ID1}" >
                                            selected    
                                            </c:when>    
                                        </c:choose> >${bangTan.ten_bang_tan}</option>
                                </c:forEach>
                            </select>  
                        </div>    
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Cấu hình Port</label>
                            <select name="CAU_HINH_PORT_ID1"  id="CAU_HINH_PORT_ID1" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="cauHinhPort" items="${cauHinhPortLst}">
                                    <option value="${cauHinhPort.CAU_HINH_PORT_ID}" <c:choose> <c:when test="${cauHinhPort.CAU_HINH_PORT_ID == tramQuyHoach.CAU_HINH_PORT_ID1}" >
                                            selected    
                                            </c:when>    
                                        </c:choose> >${cauHinhPort.CAU_HINH_PORT}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Cấu hình Gain</label>
                            <input type="text" class="form-control"  id="CAU_HINH_GAIN1" name="CAU_HINH_GAIN1"  value="${tramQuyHoach.CAU_HINH_GAIN1}" />
                        </div>
                    </div>
                </div>        
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Cấu hình beam-width</label>
                            <input type="text" class="form-control"  id="CAU_HINH_BEAM_WIDTH1" name="CAU_HINH_BEAM_WIDTH1"  value="${tramQuyHoach.CAU_HINH_BEAM_WIDTH1}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Trọng lương (kg)</label>
                            <input type="number" class="form-control"  id="TRONG_LUONG1" name="TRONG_LUONG1"  value="${tramQuyHoach.TRONG_LUONG1}" />
                        </div>
                    </div>
                </div>        
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Kích cỡ (cao)</label>
                            <input type="number" class="form-control"  id="KICH_CO_CAO1" name="KICH_CO_CAO1"  value="${tramQuyHoach.KICH_CO_CAO1}" />
                        </div>
                    </div>
                </div>  
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Kích cỡ (rộng)</label>
                            <input type="number" class="form-control"  id="KICH_CO_RONG1" name="KICH_CO_RONG1"  value="${tramQuyHoach.KICH_CO_RONG1}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Kích cỡ (sâu)</label>
                            <input type="number" class="form-control"  id="KICH_CO_SAU1" name="KICH_CO_SAU1"  value="${tramQuyHoach.KICH_CO_SAU1}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Độ cao lắp đặt Antena</label>
                            <input type="number" class="form-control"  id="DO_CAO_ANTENA_SO_VOI_MAT_DAT1" name="DO_CAO_ANTENA_SO_VOI_MAT_DAT1"  value="${tramQuyHoach.DO_CAO_ANTENA_SO_VOI_MAT_DAT1}" />
                        </div>
                    </div>
                </div>         
            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Antena2</h3>
            </div>
            <div class="panel-body">  
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Loại antena</label>
                            <select name="LOAI_ANTEN_ID2"  id="LOAI_ANTEN_ID2" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="loaiAntena" items="${loaiAntenaLst}">
                                    <option value="${loaiAntena.id}" <c:choose> <c:when test="${loaiAntena.id == tramQuyHoach.LOAI_ANTEN_ID2}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${loaiAntena.name}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden"  class="form-control" name="tramQhId" id="tramQhId">
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Tên antena</label>
                            <input type="text" class="form-control"  id="TEN_ANTENA2" name="TEN_ANTENA2" value="${tramQuyHoach.TEN_ANTENA2}" />
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Hãng sản xuất</label>
                            <input type="text" class="form-control"  id="HANG_SX_ANTENA2" name="HANG_SX_ANTENA2"  value="${tramQuyHoach.HANG_SX_ANTENA2}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Số lượng Antena</label>
                            <input type="number" class="form-control"  id="SO_LUONG_ANTENA2" name="SO_LUONG_ANTENA2"  value="${tramQuyHoach.SO_LUONG_ANTENA2}" />
                        </div>
                    </div>
                </div>   

                <div class="col-md-6">
                    <div class="form-group" >     
                        <div class="input-group">
                            <label class=" input-group-addon" >Băng tần</label>
                            <select name="BANG_TANG_ANTENA_ID2" id="BANG_TANG_ANTENA_ID2" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="bangTan" items="${bangTanLst}">
                                    <option value="${bangTan.bang_tan_id}" <c:choose> <c:when test="${bangTan.bang_tan_id == tramQuyHoach.BANG_TANG_ANTENA_ID2}" >
                                            selected    
                                            </c:when>    
                                        </c:choose> >${bangTan.ten_bang_tan}</option>
                                </c:forEach>
                            </select>  
                        </div>    
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Cấu hình Port</label>
                            <select name="CAU_HINH_PORT_ID2"  id="CAU_HINH_PORT_ID2" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="cauHinhPort" items="${cauHinhPortLst}">
                                    <option value="${cauHinhPort.CAU_HINH_PORT_ID}" <c:choose> <c:when test="${cauHinhPort.CAU_HINH_PORT_ID == tramQuyHoach.CAU_HINH_PORT_ID2}" >
                                            selected    
                                            </c:when>    
                                        </c:choose> >${cauHinhPort.CAU_HINH_PORT}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Cấu hình Gain</label>
                            <input type="text" class="form-control"  id="CAU_HINH_GAIN1" name="CAU_HINH_GAIN2"  value="${tramQuyHoach.CAU_HINH_GAIN2}" />
                        </div>
                    </div>
                </div>        
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Cấu hình beam-width</label>
                            <input type="text" class="form-control"  id="CAU_HINH_BEAM_WIDTH2" name="CAU_HINH_BEAM_WIDTH2"  value="${tramQuyHoach.CAU_HINH_BEAM_WIDTH2}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Trọng lương (kg)</label>
                            <input type="number" class="form-control"  id="TRONG_LUONG1" name="TRONG_LUONG2"  value="${tramQuyHoach.TRONG_LUONG2}" />
                        </div>
                    </div>
                </div>        
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Kích cỡ (cao)</label>
                            <input type="number" class="form-control"  id="KICH_CO_CAO1" name="KICH_CO_CAO2"  value="${tramQuyHoach.KICH_CO_CAO2}" />
                        </div>
                    </div>
                </div>  
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Kích cỡ (rộng)</label>
                            <input type="number" class="form-control"  id="KICH_CO_RONG1" name="KICH_CO_RONG2"  value="${tramQuyHoach.KICH_CO_RONG2}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Kích cỡ (sâu)</label>
                            <input type="number" class="form-control"  id="KICH_CO_SAU1" name="KICH_CO_SAU2"  value="${tramQuyHoach.KICH_CO_SAU2}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Độ cao lắp đặt Antena</label>
                            <input type="number" class="form-control"  id="DO_CAO_ANTENA_SO_VOI_MAT_DAT2" name="DO_CAO_ANTENA_SO_VOI_MAT_DAT2"  value="${tramQuyHoach.DO_CAO_ANTENA_SO_VOI_MAT_DAT2}" />
                        </div>
                    </div>
                </div>         
            </div>
        </div>

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Antena3</h3>
            </div>
            <div class="panel-body">  
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Loại antena</label>
                            <select name="LOAI_ANTEN_ID3"  id="LOAI_ANTEN_ID3" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="loaiAntena" items="${loaiAntenaLst}">
                                    <option value="${loaiAntena.id}" <c:choose> <c:when test="${loaiAntena.id == tramQuyHoach.LOAI_ANTEN_ID3}">
                                            selected    
                                            </c:when>    
                                        </c:choose> >${loaiAntena.name}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden"  class="form-control" name="tramQhId" id="tramQhId">
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Tên antena</label>
                            <input type="text" class="form-control"  id="TEN_ANTENA1" name="TEN_ANTENA3" value="${tramQuyHoach.TEN_ANTENA3}" />
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Hãng sản xuất</label>
                            <input type="text" class="form-control"  id="HANG_SX_ANTENA1" name="HANG_SX_ANTENA3"  value="${tramQuyHoach.HANG_SX_ANTENA3}" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Số lượng </label>
                            <input type="number" class="form-control"  id="SO_LUONG_ANTENA1" name="SO_LUONG_ANTENA3"  value="${tramQuyHoach.SO_LUONG_ANTENA3}" />
                        </div>
                    </div>
                </div>   

                <div class="col-md-6">
                    <div class="form-group" >     
                        <div class="input-group">
                            <label class=" input-group-addon" >Băng tần</label>
                            <select name="BANG_TANG_ANTENA_ID3" id="BANG_TANG_ANTENA_ID3" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="bangTan" items="${bangTanLst}">
                                    <option value="${bangTan.bang_tan_id}" <c:choose> <c:when test="${bangTan.bang_tan_id == tramQuyHoach.BANG_TANG_ANTENA_ID3}" >
                                            selected    
                                            </c:when>    
                                        </c:choose> >${bangTan.ten_bang_tan}</option>
                                </c:forEach>
                            </select>  
                        </div>    
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Cấu hình Port</label>
                            <select name="CAU_HINH_PORT_ID3"  id="CAU_HINH_PORT_ID3" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="cauHinhPort" items="${cauHinhPortLst}">
                                    <option value="${cauHinhPort.CAU_HINH_PORT_ID}" <c:choose> <c:when test="${cauHinhPort.CAU_HINH_PORT_ID == tramQuyHoach.CAU_HINH_PORT_ID3}" >
                                            selected    
                                            </c:when>    
                                        </c:choose> >${cauHinhPort.CAU_HINH_PORT}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Cấu hình Gain</label>
                            <input type="text" class="form-control"  id="CAU_HINH_GAIN3" name="CAU_HINH_GAIN3"  value="${tramQuyHoach.CAU_HINH_GAIN3}" />
                        </div>
                    </div>
                </div>        
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Cấu hình beam-width</label>
                            <input type="text" class="form-control"  id="CAU_HINH_BEAM_WIDTH3" name="CAU_HINH_BEAM_WIDTH3"  value="${tramQuyHoach.CAU_HINH_BEAM_WIDTH3}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Trọng lương (kg)</label>
                            <input type="number" class="form-control"  id="TRONG_LUONG3" name="TRONG_LUONG3"  value="${tramQuyHoach.TRONG_LUONG3}" />
                        </div>
                    </div>
                </div>        
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Kích cỡ (cao)</label>
                            <input type="number" class="form-control"  id="KICH_CO_CAO3" name="KICH_CO_CAO3"  value="${tramQuyHoach.KICH_CO_CAO3}" />
                        </div>
                    </div>
                </div>  
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Kích cỡ (rộng)</label>
                            <input type="number" class="form-control"  id="KICH_CO_RONG3" name="KICH_CO_RONG3"  value="${tramQuyHoach.KICH_CO_RONG3}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Kích cỡ (sâu)</label>
                            <input type="number" class="form-control"  id="KICH_CO_SAU3" name="KICH_CO_SAU3"  value="${tramQuyHoach.KICH_CO_SAU3}" />
                        </div>
                    </div>
                </div> 
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Độ cao lắp đặt Antena</label>
                            <input type="number" class="form-control"  id="DO_CAO_ANTENA_SO_VOI_MAT_DAT3" name="DO_CAO_ANTENA_SO_VOI_MAT_DAT3"  value="${tramQuyHoach.DO_CAO_ANTENA_SO_VOI_MAT_DAT3}" />
                        </div>
                    </div>
                </div>         
            </div>
        </div>   
    </c:if>
</div>

<script>
    $(document).ready(function () {
        //
        $('#tramQhId').val(${tramQuyHoach.tramQhId});
        $('#buildingId').val(${tramQuyHoach.buildingId});
        $('#tinhTpId').val(${tramQuyHoach.tinhTpId});
        $('#quanHuyenId').val(${tramQuyHoach.quanHuyenId});
        $('#phuongXaId').val(${tramQuyHoach.phuongXaId});
        $('#DIA_CHI').val('${tramQuyHoach.DIA_CHI}');

        var loaiCongNgheId = $('#loaiCongNgheId').val();
        if (loaiCongNgheId != '') {
            getBangTan(${tramQuyHoach.loaiCongNgheId});

        }
    });

    function getBangTan(loaiCongNgheId)
    {
        var id = $("#loaiCongNgheId").val();
        if (loaiCongNgheId != 0) {
            id = loaiCongNgheId;
        }
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


</script>