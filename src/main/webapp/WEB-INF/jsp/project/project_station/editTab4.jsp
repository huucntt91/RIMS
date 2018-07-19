<%-- 
    Document   : editTab4
    Created on : Dec 27, 2016, 2:36:51 PM
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
                    <h3 class="box-title">Quản lý Antena của trạm</h3>
                </div>
                <div class="box-body">                    
                    <form method="post" action="${pageContext.request.contextPath}/project_station/updateAntena" commandName="antena">

                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin chung</h3>
                            </div>
                            <div class="panel-body">  
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Mã trạm quy hoạch</label>
                                            <input type="text" class="form-control"  id="maQh" name="maQh" value="${tramQuyHoach.maQh}" disabled="true"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tên trạm quy hoạch</label>
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

                        <div class="box-footer">
                                <button type="submit"  class="btn btn-success"  >${btnName}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<div id="myBuilding" class="modal fade" role="dialog">
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
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<script>
    $(document).ready(function () {
        $('#tramQhId').val(${tramQuyHoach.tramQhId});
        $('#NGAY_DAP_UNG_ANTENA_DU_KIEN').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });
        $('#NGAY_DAP_UNG_ANTENA_THUC_TE').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });

    });


</script>
