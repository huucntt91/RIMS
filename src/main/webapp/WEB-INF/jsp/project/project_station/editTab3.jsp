<%-- 
    Document   : editTab3
    Created on : Dec 26, 2016, 11:08:29 AM
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
                    <h3 class="box-title">Quản lý nguồn DC của trạm</h3>
                </div>
                <div class="box-body"> 
                    <form method="post" action="${pageContext.request.contextPath}/project_station/updateTuNguon" commandName="tramQuyHoach">

                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin chung</h3>
                            </div>
                            <div class="panel-body">    
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Mã trạm quy hoạch</label>
                                            <input type="hidden" name="tramQhId" value="${tramQuyHoach.tramQhId}" id="tramQhId" required />
                                            <input type="text" class="form-control"  id="maQh" name="maQh" placeholder="nhập mã trạm quy hoạch" value="${tramQuyHoach.maQh}" disabled="true"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tên trạm quy hoạch</label>
                                            <input type="text" class="form-control"  id="tenQh" name="tenQh" placeholder="Tên trạm quy hoạch" value="${tramQuyHoach.tenQh}" disabled="true" />
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
                                            <label class=" input-group-addon" >Nguồn thiết bị tủ nguồn</label>                                    
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
                                            <select name="LOAI_TU_NGUON_ID" id="LOAI_TU_NGUON_ID" class="form-control"  >
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
                                            <input type="number" class="form-control"   name="DUNG_LUONG_ACCU" id="DUNG_LUONG_ACCU" value="${tramQuyHoach.DUNG_LUONG_ACCU}"  />
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
                                <div class="col-md-6">
                                    <div class="form-group" >   
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tình trạng nguồn điện</label>
                                            <select name="tinh_trang_nguon_dien" id="tinh_trang_nguon_dien" class="form-control"   >
                                                <option value="">--Chọn--</option>
                                                <option value="1" <c:if test="${tramQuyHoach.tinh_trang_nguon_dien == '1'}"> selected="true" </c:if> >OK </option>               
                                                <option value="0" <c:if test="${tramQuyHoach.tinh_trang_nguon_dien == '0'}"> selected="true" </c:if>>NOK </option>
                                                </select>  
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
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<script>
    $(document).ready(function () {
        $('#NGAY_DAP_UNG_NGUON_DC_TT').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });
        $('#NGAY_DAP_UNG_NGUON_DC_DU_KIEN').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });
    });



</script>
