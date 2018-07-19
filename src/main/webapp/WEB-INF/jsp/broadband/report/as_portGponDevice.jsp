<%-- 
    Document   : bcll
    Created on : Nov 15, 2016, 11:01:34 AM
    Author     : Cyano
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@include file="../../include/header.jsp"%>
<!-- multiselect -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Thống kê hiệu suất sử dụng cổng GPON theo thiết bị</h3>
                </div>
                <form method="get">
                    <div class="box-body"> 
                        <div class="panel-body">
                                <div class="col-md-4">
                                    <div class="form-group">          
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                                            <select multiple="multiple"  name="tinhTpId" id="tinhTpId" class="form-control" required="true"> 
                                            <c:forEach var="tinhBO" items="${tinhThanhPhoLst}">
                                                <option  value="${tinhBO.tinhTpId}" <c:if test = '${fn:contains(tinhTpId,tinhBO.tinhTpId)}'> selected="selected" </c:if> >${tinhBO.tenTinhTp}</option>
                                            </c:forEach>
                                        </select>
                                    </div> 
                                </div>
                            </div>  
                        </div>
                    </div>
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/access/reportPortGponDevice?tinhTpId='+ $('#tinhTpId').val()"> <spring:message code="admin.common.export.excel" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách</h3>
                </div>
                <div class="box-body table-responsive">
                    <div id="tablePagingId">
                        <table id="example1" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên tỉnh</th>
                                    <th>Đơn vị</th>
                                    <th>Số OLT đã lắp đặt</th>
                                    <th>Số card pon đã lắp đặt</th>
                                    <th>Số card pon mở rộng tối đa</th>
                                    <th>Số cổng pon tối đa</th>
                                    <th>Số pon đã lắp đặt</th>
                                    <th>Số pon đã sử dụng</th>
                                    <th>Số ONT có thể lắp đặt</th>
                                    <th>Số ONT đang hoạt động</th>
                                    <th>Hiệu suất sử dụng</th>
                                    <th>Địa chỉ</th>
                                    
                                </tr>
                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="it" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + status.index}</td>
                                        <td>${it.ten_tinh_tp}</td>
                                        <td>${it.tenDonvi}</td>
                                        <td>${it.so_olt_lap_dat}</td>
                                        <td>${it.so_card_pon_lap_dat}</td>
                                        <td>${it.so_card_pon_mp_rong_td}</td>
                                        <td>${it.so_cong_pon_toi_da}</td>
                                        <td>${it.so_pon_da_lap_dat}</td>
                                        <td>${it.so_pon_da_su_dung}</td>
                                        <td>${it.so_ont_co_the_lap_dat}</td>
                                        <td>${it.so_ont_dang_hoat_dong}</td>
                                        <td>${it.hieu_suat_su_dung}</td>
                                        <td>${it.diachi}</td>
                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>
                    </div>
                </div>
                <div class="box-footer">
                    <!-- ADD PAGE INFO -->
                    <%@include file="../../include/page_info.jsp"%>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>

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
    });
</script>