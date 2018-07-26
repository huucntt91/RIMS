<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RIMS</title>
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

                               
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin quản lý triển khai dự án</h3>
                                    </div>
                                    <div class="panel-body"  disableGroup="disable" >        
                                        <div class="form-group">
                                            <div class="input-group">  
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Dự án</label>
                                                <input type="hidden" name="id" value="${tramDA.id}" id="id" required />
                                                <input type="hidden" name="trangThaiTram" value="${trangThaiTram}" id="trangThaiTram" required />
                                                <input required type="text"  class="form-control" id="maDA" value="${tramDA.maDuAn}" placeholder="Mã dự án" disabled  /> 
                                                <input type="hidden" name="duAnId" value="${tramDA.duAnId}" id="duAnId" required />

                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">  
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Trạm quy hoạch</label>
                                                <input type="text"  class="form-control" id="maQH" value="${tramDA.maQuyHoach}" placeholder="Trạm quy hoạch"  disabled /> 
                                                <input type="hidden" name="tramQHId" value="${tramDA.tramQHId}" id="tramQHId" required />

                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Mã hợp đồng</label>    
                                                <input
                                                    type="text" class="form-control" id="exampleInputEmail1" name="maSoHopDong"
                                                    placeholder="Mã hợp đồng" value="${tramDA.maSoHopDong}" disabled/>
                                            </div>
                                        </div> 

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Tỉnh</label>  
                                                <select name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen(0);"> 
                                                    <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                                                    <c:forEach var="tinhBO" items="${tinhList}">
                                                        <option  
                                                            value="${tinhBO.tinhTpId}"  <c:choose>
                                                                <c:when test="${tinhBO.tinhTpId == tramDA.tinhTpId}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${tinhBO.tenTinhTp}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Quận/Huyện</label>  
                                                <select name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa(0);"> 
                                                    <option value="">---Chọn Quận / Huyện ---</option>
                                                </select> 
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Địa chỉ lắp đặt</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="address"
                                                    placeholder="Địa chỉ lắp đặt" value="${tramDA.address}"/>   
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Mã trạm dự án</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="maTramDuAn"
                                                    placeholder="Mã trạm dự án" value="${tramDA.maTramDuAn}"/> 
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Tên trạm dự án</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="tenTramDuAn"
                                                    placeholder="Tên trạm dự án" value="${tramDA.tenTramDuAn}"/>  
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Mã trạm BTS</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="maTramBTS"
                                                    placeholder="Mã trạm BTS" value="${tramDA.maTramBTS}"/> 
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Mã trạm NodeB</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="maTramNodeB"
                                                    placeholder="Mã trạm NodeB" value="${tramDA.maTramNodeB}"/>  
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Mã trạm quy hoạch</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="maTramQuyHoach"
                                                    placeholder="Mã trạm quy hoạch" value="${tramDA.maTramQuyHoach}"/>    
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Hiện trạng trạm</label>    
                                                <select name="hienTrangTram" id="hienTrangTram" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmHienTrangTram" items="${dmHienTrangTramList}">
                                                        <option  
                                                            value="${dmHienTrangTram.id}"  <c:choose>
                                                                <c:when test="${dmHienTrangTram.id == tramDA.hienTrangTram}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmHienTrangTram.name}</option>
                                                    </c:forEach>
                                                </select>   
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Longitude</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="longitude"
                                                    placeholder="Longitude" value="${tramDA.longitude}"/>  
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Latitude</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="latitude"
                                                    placeholder="Latitude" value="${tramDA.latitude}"/> 
                                            </div>
                                        </div> 
                                            
                                    </div>
                                </div>
                            
                            
                             
                                <div class="panel panel-primary" >
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin cam kết thiết bị</h3>
                                    </div>
                                    <div class="panel-body" disableGroup="disable">  
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">VNPT Net phê duyệt triển khai</label> 
                                                <select name="vnptNetPheDuyet" id="vnptNetPheDuyet" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.vnptNetPheDuyet}">
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
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Cấu hình thiết bị</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="cauHinhThietBi"
                                                    placeholder="Cấu hình thiết bị" value="${tramDA.cauHinhThietBi}"/>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Nguồn thiết bị</label> 
                                                <select name="nguonThietBi" id="nguonThietBi" class="form-control" > 
                                                    <option value="">--- Chọn nguồn thiết bị ---</option>
                                                    <c:forEach var="nguonThietBiBO" items="${dmNguonThietBiList}">
                                                        <option  
                                                            value="${nguonThietBiBO.id}"  <c:choose>
                                                                <c:when test="${nguonThietBiBO.id == tramDA.nguonThietBi}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${nguonThietBiBO.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Loại công nghệ</label> 
                                                <select name="loaiCongNghe" id="loaiCongNghe" class="form-control" > 
                                                    <option value="">--- Chọn Loại công nghệ ---</option>
                                                    <c:forEach var="loaiCongNgheBO" items="${dmLoaiCongNgheList}">
                                                        <option  
                                                            value="${loaiCongNgheBO.id}"  <c:choose>
                                                                <c:when test="${loaiCongNgheBO.id == tramDA.loaiCongNghe}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${loaiCongNgheBO.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Chủng loại thiết bị</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="chungLoaiThietBi"
                                                    placeholder="Chủng loại thiết bị" value="${tramDA.chungLoaiThietBi}"/>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Chủng loại Anten</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="chungLoaiAnten"
                                                    placeholder="Chủng loại Anten" value="${tramDA.chungLoaiAnten}"/>
                                            </div>
                                        </div> 
                                    </div>
                                </div>
                            
                            
                              
                                <div class="panel panel-primary" >
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin Cam kết hạ tầng</h3>
                                    </div>
                                    <div class="panel-body"  disableGroup="disable">  
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Long( khảo sát )</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="latitude"
                                                    placeholder="Latitude" value="${tramDA.latitude}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Lat ( Khảo sát )</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="latitude"
                                                    placeholder="Latitude" value="${tramDA.latitude}"/> 
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
                                                    type="text" class="form-control" id="exampleInputEmail1" path="dungLuongTuNguon"
                                                    placeholder="Dung lượng tủ nguồn (A)" value="${tramDA.dungLuongTuNguon}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Số Module nguồn</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="soModuleTuNguon"
                                                    placeholder="Số Module nguồn" value="${tramDA.soModuleTuNguon}"/> 
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
                                                    type="text" class="form-control" id="exampleInputEmail1" path="dungLuongAccu"
                                                    placeholder="Dung lượng ACCU (Ah)" value="${tramDA.dungLuongAccu}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Số lượng tổ ACCU</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="soLuongToAccu"
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
                                                <form:input  class="form-control"  id="ngayHoanThanhKs" path="ngayHoanThanhKs" value="${tramDA.ngayHoanThanhKs}" />
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày gửi số liệu lên Sever</label>    
                                                <form:input  class="form-control"  id="ngayGuiSoLieu" path="ngayGuiSoLieu" value="${tramDA.ngayGuiSoLieu}" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đầu mối VNPT Tỉnh/TP nhận thiết bị</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="dauMoiNhanThietBi"
                                                    placeholder="Đầu mối VNPT Tỉnh/TP nhận thiết bị" value="${tramDA.dauMoiNhanThietBi}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đầu mối QL CSHT nhà trạm</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="dauMoiQLCSHT"
                                                    placeholder="Đầu mối QL CSHT nhà trạm" value="${tramDA.dauMoiQLCSHT}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đơn vị lắp đặt</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="donViLapDat"
                                                    placeholder="Đơn vị lắp đặt" value="${tramDA.donViLapDat}"/> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            
                            
                              
                                <div class="panel panel-primary" >
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin triển khai dự án - NetX</h3>
                                    </div>
                                    <div class="panel-body"  disableGroup="disable"> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày phê duyệt kết quả khảo sát của VNPT Tỉnh/TP</label> 
                                                <form:input  class="form-control"  id="ngayPheDuyetKhaoSat" path="ngayPheDuyetKhaoSat" value="${tramDA.ngayPheDuyetKhaoSat}" />
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày tiếp nhận truyền dẫn từ VNPT Tỉnh/ TP</label> 
                                                <form:input  class="form-control"  id="ngayTiepNhanTruyenDan" path="ngayTiepNhanTruyenDan" value="${tramDA.ngayTiepNhanTruyenDan}" /> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                               
                            
                               
                                <div class="panel panel-primary" >
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin triển khai dự án - QLHT</h3>
                                    </div>
                                    <div class="panel-body" disableGroup="disable">  
                                         
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch xuất thiết bị</label> 
                                                <form:input  class="form-control"  id="keHoachXuatThietBi" path="keHoachXuatThietBi" value="${tramDA.keHoachXuatThietBi}" />
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày xuất thiết bị thực tế</label> 
                                                <form:input  class="form-control"  id="ngayXuatThietBiThucTe" path="ngayXuatThietBiThucTe" value="${tramDA.ngayXuatThietBiThucTe}" />
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày tiếp nhận thiết bị</label> 
                                                <form:input  class="form-control"  id="ngayTiepNhanTb" path="ngayTiepNhanTb" value="${tramDA.ngayTiepNhanTb}" /> 
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch thiết bị đến site</label> 
                                                <form:input  class="form-control"  id="keHoachTbDenSite" path="keHoachTbDenSite" value="${tramDA.keHoachTbDenSite}" /> 
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch lắp đặt</label> 
                                                <form:input  class="form-control"  id="keHoachLapDat" path="keHoachLapDat" value="${tramDA.keHoachLapDat}" /> 
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày bắt đầu lắp đặt thiết bị</label> 
                                                <form:input  class="form-control"  id="ngayBatDauLapDat" path="ngayBatDauLapDat" value="${tramDA.ngayBatDauLapDat}" />
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày hoàn thành lắp đặt thiết bị</label> 
                                                <form:input  class="form-control"  id="ngayHTLapDatTb" path="ngayHTLapDatTb" value="${tramDA.ngayHTLapDatTb}" />  
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch Hòa mạng</label> 
                                                <form:input  class="form-control"  id="keHoachHoaMang" path="keHoachHoaMang" value="${tramDA.keHoachHoaMang}" />  
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày hòa mạng thực tế</label> 
                                                <form:input  class="form-control"  id="ngayHoaMangThucTe" path="ngayHoaMangThucTe" value="${tramDA.ngayHoaMangThucTe}" /> 
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch phát sóng chính thức</label> 
                                                <form:input  class="form-control"  id="keHoachPhatSongCt" path="keHoachPhatSongCt" value="${tramDA.keHoachPhatSongCt}" /> 
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày phát sóng chính thức</label> 
                                                <form:input  class="form-control"  id="ngayPhatSongCt" path="ngayPhatSongCt" value="${tramDA.ngayPhatSongCt}" /> 
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch nghiệm thu</label> 
                                                <form:input  class="form-control"  id="keHoachNghiemThu" path="keHoachNghiemThu" value="${tramDA.keHoachNghiemThu}" /> 
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày nghiệm thu</label> 
                                                <form:input  class="form-control"  id="ngayNghiemThu" path="ngayNghiemThu" value="${tramDA.ngayNghiemThu}" />
                                            </div>
                                           
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đầu mối (VNPT Net)</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="dauMoiVnptNet"
                                                    placeholder="Đầu mối (VNPT Net)" value="${tramDA.dauMoiVnptNet}"/> 
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đơn vị vận chuyển</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="donViVanChuyen"
                                                    placeholder="Đơn vị vận chuyển" value="${tramDA.donViVanChuyen}"/>
                                            </div>
                                        </div> 
                                            <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ghi chú</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="ghiChu"
                                                    placeholder="Ghi chú" value="${tramDA.ghiChu}"/> 
                                            </div>
                                        </div> 
                                    </div>
                                </div>
                            
                            
                            <!-- /.box-body -->
                            <!--<div class="box-footer">-->
                                <!--<button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>-->
                            <!--</div>-->
                        </form:form>
                    </div>
                </div>
            </div>           
        </section>
        
        <!-- Modal -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Tìm Kiếm</span></h4>
                    </div>
                    <div class="modal-body">
                        <div class="box-body table-responsive">
                            <iframe width="100%" height="450" frameborder="0"></iframe>
                        </div>
                        <div class="modal-footer">

                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>                 
        </div>
        
        <div class="modal fade" id="myModalTramQuyHoach" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Tìm Kiếm</span></h4>
                    </div>
                    <div class="modal-body">
                        <div class="box-body table-responsive">
                            <iframe width="100%" height="450" frameborder="0"></iframe>
                        </div>
                        <div class="modal-footer">

                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>                 
        </div>

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
                        $('#quanHuyenId').val(${tramDA.quanHuyenId});
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
                        $('#phuongXaId').val(${phuongXaId});
                });
            }

            $(document).ready(function () {
            
                //$('.navbar-btn').click();
                var tinhId = $("#tinhTpId").val();
                if (tinhId != '')
                {
                    getListHuyen(${tramDA.tinhTpId});
                    getListPhuongXa(${quanHuyenId});
                }
                $('#example1 tbody').on('click', 'tr', function () {
                    if ($(this).hasClass('selected')) {
                        //$(this).removeClass('selected');
                    } else {
                        $('#example1 tbody').find('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                    var node_id = $(this).find('.node_id').val();
                    var type_id = $(this).find('.type_id').val();
                    viewDetail(node_id, type_id);

                });
                
                $('#btn_tramDA').click(function () {
                    $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/stationPlans/popupDuAn'});
                });
                $('#btn_node').click(function () {
                    $("#myModalTramQuyHoach iframe").prop({'src': '${pageContext.request.contextPath}/stationPlans/popupTramKH'});
                });
                
                $('#myModal iframe').on('load', function (e) {
                    var iframe1 = $('#myModal iframe').contents();
                    iframe1.find('#example1 tbody').on('click', 'tr', function () {
                        //alert($(this).text());
                        $('#maDA').val($(this).find('input.du_an_code').val());
                        $('#duAnId').val($(this).find('input.du_an_id').val());
                    });
                    iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                        //alert($(this).text());
                        $('#maDA').val($(this).find('input.du_an_code').val());
                        $('#duAnId').val($(this).find('input.du_an_id').val());
                        $('#myModal').modal('hide');
                    });
                });
                
                $('#myModalTramQuyHoach iframe').on('load', function (e) {
                    var iframe1 = $('#myModalTramQuyHoach iframe').contents();
                    iframe1.find('#example1 tbody').on('click', 'tr', function () {
                        //alert($(this).text());
                        $('#maQH').val($(this).find('input.tram_qh_code').val());
                        $('#tramQHId').val($(this).find('input.tram_qh_id').val());
                    });
                    iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                        //alert($(this).text());
                        $('#maQH').val($(this).find('input.tram_qh_code').val());
                        $('#tramQHId').val($(this).find('input.tram_qh_id').val());
                        $('#myModalTramQuyHoach').modal('hide');
                    });
                });
                
                $('.panel-body').each(function() {
                    if ($(this).attr('disableGroup') != undefined) {
                        $(this).find('input').attr('disabled', 'disabled');
                        $(this).find('select').attr('disabled', 'disabled');
                    }
                 });
                
            });
            
            
        </script>
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
                $('#ngayPheDuyetKhaoSat').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayTiepNhanTruyenDan').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#keHoachXuatThietBi').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayXuatThietBiThucTe').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayTiepNhanTb').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#keHoachTbDenSite').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#keHoachLapDat').datepicker({
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
                 $('#keHoachHoaMang').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayHoaMangThucTe').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#keHoachPhatSongCt').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayPhatSongCt').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#keHoachNghiemThu').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                $('#ngayNghiemThu').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
            });
        </script>
    </body>
</html>
