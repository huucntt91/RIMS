<%-- 
    Document   : add, edit thong cam kết của trạm quy hoach
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
                    <h3 class="box-title">Quản lý thông tin cam kết của trạm</h3>
                </div>
                <div class="box-body"> 
                    <form method="post" action="${pageContext.request.contextPath}/project_station/updateCamKet" commandName="tramQhBo">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin chung</h3>
                            </div>
                            <div class="panel-body">   
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Mã trạm quy hoạch</label>
                                            <input type="text" class="form-control"  id="maQh" name="maQh" placeholder="Nhập mã trạm quy hoạch" value="${tramQuyHoach.maQh}" disabled="true"/>
                                            <input type="hidden" class="form-control" name="tramQhId" id="tramQhId">
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
        $('#tramQhId').val(${tramQuyHoach.tramQhId});
        $('#THOI_DIEM_DAP_UNG_DU_KIEN').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });

        $('#THOI_DIEM_DAP_UNG_THUC_TE').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });

    });

</script>