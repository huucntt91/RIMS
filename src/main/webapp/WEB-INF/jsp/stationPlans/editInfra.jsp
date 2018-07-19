<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Cập nhật trạm dự án</h3>
                        </div>
                        <form:form method="post" action="update" commandName="tramDA">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <%--<div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"DEVICE_INFRA")}'>   style="display: none" </c:if>>--%>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin Cam kết hạ tầng</h3>
                                    </div>
                                    <%--<div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"DEVICE_INFRA")}'> disableGroup="disable" </c:if>>--%> 
                                    <div class="panel-body" >  
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Long( khảo sát )</label>    
                                                <input type="hidden" name="id" value="${tramDA.id}" id="id"  />
                                                <input type="hidden" name="trangThaiTram" value="${trangThaiTram}" id="trangThaiTram"  />
                                                <input type="hidden" name="duAnId" value="${tramDA.duAnId}" id="duAnId"  />
                                                <input type="hidden" name="tramQHId" value="${tramDA.tramQHId}" id="tramQHId"  />
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="longitudeKhaoSat"
                                                    placeholder="Latitude khảo sát" value="${tramDA.longitudeKhaoSat}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Lat ( Khảo sát )</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="latitudeKhaoSat"
                                                    placeholder="Latitude khảo sát" value="${tramDA.latitudeKhaoSat}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Nhà trạm</label>    
                                                <select name="nhaTram" id="nhaTram" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.nhaTram}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Cột anten</label>    
                                                <select name="cotAnten" id="cotAnten" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.cotAnten}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Cầu cáp ngoài</label>    
                                                <select name="cauCapNgoai" id="cauCapNgoai" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.cauCapNgoai}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Tủ nguồn</label>    
                                                <select name="tuNguon" id="tuNguon" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.tuNguon}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Dung lượng tủ nguồn (A)</label>    
                                                <form:input
                                                    type="number" class="form-control" id="dungLuongTuNguon" path="dungLuongTuNguon"
                                                    placeholder="Dung lượng tủ nguồn (A)" value="${tramDA.dungLuongTuNguon}"/> 
<!--                                                <input
                                                    type="number" class="form-control" id="exampleInputEmail1" path="dungLuongTuNguon"
                                                    placeholder="Dung lượng tủ nguồn (A)" value="${tramDA.dungLuongTuNguon}"/> -->
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Số Module nguồn</label>    
                                                <form:input
                                                    type="number" class="form-control" id="soModuleTuNguon" path="soModuleTuNguon"
                                                    placeholder="Số Module nguồn" value="${tramDA.soModuleTuNguon}"/> 
<!--                                                <input
                                                    type="number" class="form-control" id="exampleInputEmail1" path="soModuleTuNguon"
                                                    placeholder="Số Module nguồn" value="${tramDA.soModuleTuNguon}"/> -->
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Chủng loại ACCU</label>    
                                                <select name="chungLoaiAccu" id="chungLoaiAccu" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmDlACCU" items="${dmDungLuongACCUList}">
                                                        <option  
                                                            value="${dmDlACCU.id}"  <c:choose>
                                                                <c:when test="${dmDlACCU.id == tramDA.chungLoaiAccu}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmDlACCU.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Dung lượng ACCU (Ah)</label>    
                                                <form:input
                                                    type="number" class="form-control" id="dungLuongAccu" path="dungLuongAccu"
                                                    placeholder="Dung lượng ACCU (Ah)" value="${tramDA.dungLuongAccu}"/>
<!--                                                <input
                                                    type="number" class="form-control" id="exampleInputEmail1" name="dungLuongAccu"
                                                    placeholder="Dung lượng ACCU (Ah)" value="${tramDA.dungLuongAccu}"/> -->
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Số lượng tổ ACCU</label>    
                                                <form:input
                                                    type="number" class="form-control" id="exampleInputEmail1" path="soLuongToAccu"
                                                    placeholder="Số lượng tổ ACCU" value="${tramDA.soLuongToAccu}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Truyền dẫn</label>    
                                                <select name="truyenDan" id="truyenDan" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.truyenDan}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Điều hòa</label>    
                                                <select name="dieuHoa" id="dieuHoa" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.dieuHoa}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Điện AC</label>    
                                                <select name="dienAc" id="dienAc" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.dienAc}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Điện AC nội trạm</label>    
                                                <select name="dienAcNoiTram" id="dienAcNoiTram" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.dienAcNoiTram}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đủ điều kiện lắp eNodeB</label>    
                                                <select name="duDkLapEnodeb" id="duDkLapEnodeb" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.duDkLapEnodeb}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày đủ điều kiện lắp đặt thiết bị</label>    
                                                <input  class="form-control"  id="ngayDuDkLapDatThietBi" name="ngayDuDkLapDatThietBi" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayDuDkLapDatThietBi}" />" />
                                            </div>
                                        </div>    
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Cấp mới tủ nguồn DC</label>    
                                                <select name="capMoiTuNguonDc" id="capMoiTuNguonDc" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.capMoiTuNguonDc}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Cấp mới ACCU</label>    
                                                <select name="capMoiAccu" id="capMoiAccu" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.capMoiAccu}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Swap (lắp mới) anten</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="swapAnten"
                                                    placeholder="Swap (lắp mới) anten" value="${tramDA.swapAnten}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày hoàn thành khảo sát</label>    
                                                <input  class="form-control"  id="ngayHoanThanhKs" name="ngayHoanThanhKs" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayHoanThanhKs}" />" />
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày gửi số liệu lên Sever</label>    
                                                <input  class="form-control"  id="ngayGuiSoLieu" name="ngayGuiSoLieu" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayGuiSoLieu}" />" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đầu mối VNPT Tỉnh/TP nhận thiết bị</label>    
                                                <form:input
                                                    type="text" class="form-control" id="dauMoiNhanThietBi" path="dauMoiNhanThietBi"
                                                    placeholder="Đầu mối VNPT Tỉnh/TP nhận thiết bị" value="${tramDA.dauMoiNhanThietBi}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đầu mối QL CSHT nhà trạm</label>    
                                                <form:input
                                                    type="text" class="form-control" id="dauMoiQLCSHT" path="dauMoiQLCSHT"
                                                    placeholder="Đầu mối QL CSHT nhà trạm" value="${tramDA.dauMoiQLCSHT}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đơn vị lắp đặt</label>    
                                                <form:input
                                                    type="text" class="form-control" id="donViLapDat" path="donViLapDat"
                                                    placeholder="Đơn vị lắp đặt" value="${tramDA.donViLapDat}"/> 
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch lắp đặt</label> 
                                                <input  class="form-control"  id="keHoachLapDat" name="keHoachLapDat" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.keHoachLapDat}" />" /> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày bắt đầu lắp đặt thiết bị</label> 
                                                <input  class="form-control"  id="ngayBatDauLapDat" name="ngayBatDauLapDat" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayBatDauLapDat}" />" />
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày hoàn thành lắp đặt thiết bị</label> 
                                                <input  class="form-control"  id="ngayHTLapDatTb" name="ngayHTLapDatTb" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayHTLapDatTb}" />" />  
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày hoàn thành lắp đặt anten</label>    
                                                <input  class="form-control"  id="ngayHTLapDatAnten" name="ngayHTLapDatAnten" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayHTLapDatAnten}" />" />
                                            </div>
                                        </div>    
                                    </div>
                                </div>                            
                            
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>           
        </section>

        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
        
        <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        
         <script>  
            $(document).ready(function() {
                $('#ngayHoanThanhKs').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayGuiSoLieu').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });    
                 $('#ngayDuDkLapDatThietBi').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayBatDauLapDat').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayHTLapDatTb').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayHTLapDatAnten').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#keHoachLapDat').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
            });
        </script>
    </body>
</html>
