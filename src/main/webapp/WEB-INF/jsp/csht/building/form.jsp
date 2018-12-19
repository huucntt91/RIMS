<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="pm" uri="/WEB-INF/tld/permissionTagLib" %>
<%@include file="../../include/header.jsp"%>

<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Quản lý vị trí</h3>
                </div>
                <form:form method="post" action="update" commandName="model">

                    <div class="box-body" id="mydiv" >
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin khu vực</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tên CSHT</label>                                    
                                    <form:hidden path="id" title="${model.id}"></form:hidden>   
                                    <input required type="text" class="form-control" name="code" placeholder="Tên CSHT" value="${model.name}" />
                                </div>
                                <div class="form-group">
                                    <label for="planningCode">Mã quy hoạch vị trí</label>                                    
                                    <input required type="text" id="planningCode" class="form-control" name="planningCode" placeholder="Mã quy hoạch vị trí" value="${model.planningCode}" />
                                </div>   
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Địa chỉ</label>                                    
                                    <input required type="text" class="form-control" name="address" placeholder="Địa chỉ" value="${model.address}" />
                                </div>   
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Latitude</label>                                    

                                    <input required type="number" step="any" class="form-control" name="lat" placeholder="Latitude" value="${model.lat}" />
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Longitude</label>                                    

                                    <input required type="number" step="any" class="form-control" name="lon" placeholder="Longitude" value="${model.lon}" />
                                </div> 

<!--                                <div class="form-group">
                                    <label for="exampleInputEmail1">Đơn vị </label>
                                    <select name="donViId" id="donViId" class="form-control" required> >
                                        <option value="">--- Chọn Đơn Vị ---</option>
                                        <c:forEach var="dvBO" items="${dvList}">
                                            <option  
                                                value="${dvBO.donViId}"  <c:choose>
                                                    <c:when test="${dvBO.donViId == model.donViId}">
                                                        selected    
                                                    </c:when>    
                                                </c:choose>

                                                >${dvBO.tenDonVi}</option>
                                        </c:forEach>

                                    </select>  
                                </div> -->


                                <div class="form-group">
                                    <label for="exampleInputEmail1">Chọn tỉnh </label>
                                    <select name="tinhTpId" id="tinhTpId" class="form-control" required onchange="getListHuyen(0);"> >
                                        <option value="">--- Tỉnh/Thành Phố ---</option>
                                        <c:forEach var="tinhBO" items="${tinhList}">
                                            <option  
                                                value="${tinhBO.tinhTpId}"  <c:choose>
                                                    <c:when test="${tinhBO.tinhTpId == model.tinhTpId}">
                                                        selected    
                                                    </c:when>    
                                                </c:choose>

                                                >${tinhBO.tenTinhTp}</option>
                                        </c:forEach>

                                    </select>  
                                </div> 
                                <div class="form-group">
                                    <label  for="exampleInputEmail1">Chọn huyện</label>
                                    <select name="quanHuyenId" id="quanHuyenId" class="form-control" required onchange="getListPhuongXa(0);"> 
                                        <option value="">--- Quận / Huyện ---</option>
                                    </select>
                                </div> 
                                <div class="form-group" class="form-control">
                                    <label for="exampleInputEmail1">Chọn phường xã</label>
                                    <select name="phuongXaId" id="phuongXaId" class="form-control" > 
                                        <option value="">--- Phường / Xã ---</option>
                                    </select>                              
                                </div>
                                 
<!--                                <div class="form-group" class="form-control">
                                    <label for="exampleInputEmail1">Nhóm CSHT</label>
                                    <select name="nhomCSHT" id="nhomCSHT" class="form-control" > 
                                        <option value="" >--- Nhóm CSHT ---</option>
                                        <option value="Nhóm 1" <c:if test="${fn:containsIgnoreCase(model.nhomCSHT, '1')}"> selected </c:if> >Nhóm 1</option>
                                        <option value="Nhóm 2" <c:if test="${fn:containsIgnoreCase(model.nhomCSHT, '2')}"> selected </c:if> >Nhóm 2</option>
                                        <option value="Nhóm 3" <c:if test="${fn:containsIgnoreCase(model.nhomCSHT, '3')}"> selected </c:if> >Nhóm 3</option>
                                        <option value="Nhóm 4" <c:if test="${fn:containsIgnoreCase(model.nhomCSHT, '4')}"> selected </c:if> >Nhóm 4</option>
                                        <option value="Nhóm 5" <c:if test="${fn:containsIgnoreCase(model.nhomCSHT, '5')}"> selected </c:if> >Nhóm 5</option>
                                    </select>                              
                                </div>-->


                               <div class="form-group" class="form-control"
                                <c:if test="${not pm:checkUserAttr('INFRAS_ACTIVE_DATE', 'BUILDING','VIEW')}"> style="display: none" </c:if>>
                                    <label for="exampleInputEmail1">Ngày hoạt động CSHT</label>
                                    <input value="${model.ngayHdCsht}"  type="text" class="form-control date_form "
                                        id="ngayHdCsht" name="ngayHdCsht"
                                        <c:if test="${not pm:checkUserAttr('INFRAS_ACTIVE_DATE', 'BUILDING','UPDATE')}"> disabled </c:if>
                                    />
                                    <script>
                                        $(document).ready(function () {
                                            $('#ngayHdCsht').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true
                                            });
                                        });
                                    </script>            
                                </div>     
                            </div>
                        </div>

                    </div>    

                    <!-- /.box-body -->
                    <c:if test="${isView!=1}">
                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                        </div>
                    </c:if>
                </form:form>
            </div>
        </div>
    </div>           
</section>

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<script>
                                        function getListHuyen(tinh)
                                        {
                                            var id = $("#tinhTpId").val();
                                            if (tinh != 0)
                                                id = tinh;
                                            $.get("${pageContext.request.contextPath}/dv/getHuyen/" + id, function (data) {
                                                var html = '<option value="" >--- Quận / Huyện ---</option>';
                                                if (data.length > 0) {
                                                    data.forEach(function (entry) {
                                                        var htmlx = '<option value="' + entry.quanHuyenId + '">' + entry.tenQuanHuyen + '</option>';
                                                        html += htmlx;

                                                    });

                                                }
                                                ;
                                                $('#quanHuyenId').html(html);
                                                if (tinh != 0)
                                                    $('#quanHuyenId').val(${model.quanHuyenId});
                                            });
                                        }

                                        function getListPhuongXa(huyen)
                                        {

                                            var id = $("#quanHuyenId").val();
                                            if (huyen != 0)
                                                id = huyen;
                                            $.get("${pageContext.request.contextPath}/dv/getPhuong/" + id, function (data) {
                                                var html = '<option value="">--- Phường / Xã ---</option>';
                                                if (data.length > 0) {
                                                    data.forEach(function (entry) {
                                                        var htmlx = '<option value="' + entry.phuongXaId + '">' + entry.tenPhuongXa + '</option>';
                                                        html += htmlx;

                                                    });

                                                }
                                                ;
                                                $('#phuongXaId').html(html);
                                                if (huyen != 0)
                                                    $('#phuongXaId').val(${model.phuongXaId});
                                            });
                                        }

                                        $(document).ready(function () {
                                        <c:if test="${isView==1}">
                                            $('#mydiv').find('input, textarea, button, select').attr('disabled', 'disabled');
                                        </c:if>
                                            var tinhId = $("#tinhTpId").val();
                                            if (tinhId != '')
                                            {
                                                getListHuyen(${model.tinhTpId});
                                                getListPhuongXa(${model.quanHuyenId});
                                            }
                                        });
</script>
