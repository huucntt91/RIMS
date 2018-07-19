<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="box-body">
    <form:form method="post" action="update" commandName="item">
        <div class="form-group" class="form-control">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Mã node</label>
                <input  type="text" class="form-control " required
                        value="${item.code}"  id="code" name="code"/>                    
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <label class=" input-group-addon"  style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Nhập mã Node cha</label>

                <form:hidden name="nodeChaId"  id="nodeChaId" path="nodeChaId" />                                                    
                <span class="input-group-btn">
                    <button type="button"  class="btn btn-success" data-toggle="modal" data-target="#myModalBTSCode" id="btn_bts"  >Tìm Node cha</button>
                </span>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">                                
                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Đơn vị</label>
                <form:select name="donViId" path="donViId" class="form-control" >
                    <form:option value="">--- Đơn vị ---</form:option>
                    <%--<form:options  items="${dvList}"  itemValue="donViId"  itemLabel="tenDonVi"/>--%> 
                    <c:forEach var="dvBO" items="${dvList}">
                        <option  
                            value="${dvBO.donViId}"  <c:choose>
                                <c:when test="${dvBO.donViId == item.donViId}">
                                    selected    
                                </c:when>    
                            </c:choose>

                            >${dvBO.tenDonVi}</option>
                    </c:forEach>
                </form:select>  
            </div>                                                                                                
        </div>

        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn Vendor</label>
                <select name="thietBiId" id="thietBiId" class="form-control" > 
                    <option value="">--- Chọn Vendor ---</option>
                    <c:forEach var="tbBO" items="${thietBiList}">
                        <option  
                            value="${tbBO.thietBiId}"  <c:choose>
                                <c:when test="${tbBO.thietBiId == item.thietBiId}">
                                    selected    
                                </c:when>    
                            </c:choose>

                            >${tbBO.tenThietBi}</option>
                    </c:forEach>
                </select>  
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập mã building</label>
                <input  type="text"  value="${item.codeBuilding}"  class="form-control" id="codeBuilding" placeholder="Mã building" disabled  /> 
                <input type="hidden" name="buildingId" value="${item.buildingId}" id="buildingId"  />
                <span class="input-group-btn">
                    <button  class="btn btn-success" data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm building</button>
                </span>
            </div>
        </div>

        <div class="form-group" class="form-control">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                <select name="loaiTramId" id="neTypeId" class="form-control"  > 
                    <option value="">--- Chọn loại trạm ---</option>
                    <c:forEach var="item" items="${tramList}">
                        <c:choose>
                            <c:when test="${item.neTypeId == neTypeId}">  
                                <option  
                                    <c:choose>
                                        <c:when test="${item.loaiTramId == model.loaiTramId}">
                                            selected    
                                        </c:when>    
                                    </c:choose>  
                                    value="${item.loaiTramId}">${item.tenLoaiTram}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên người quản lý</label>                                    
                <input  value="${item.tenNgQLy}" type="text" class="form-control" name="tenNgQLy" placeholder="Tên người quản lý"  />
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">SDT người quản lý</label>                                    
                <input  value="${item.SDTQLy}" type="text" class="form-control" name="SDTQLy" placeholder="SDT người quản lý"  />
            </div>
        </div>

        <!--bsc_rnc_info-->
        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên</label>                               
                <form:hidden path="nodeId" title="${item.nodeId}"></form:hidden>
                <input  type="text" class="form-control" name="name" placeholder="Tên" value="${item.name}" />
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Hoàn cảnh ra đời</label>

                <input  type="text" class="form-control" name="hoanCanhRaDoi" placeholder="Hoàn cảnh ra đời" value="${item.hoanCanhRaDoi}" />
            </div>   
        </div>    
        <div class="form-group" class="form-control">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày hoạt động</label>
                <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="yyyy-mm-dd" 
                                value="${item.ngayHoatDong}" />" id="ngayHoatDong" name="ngayHoatDong"  />                    
            </div>
            <script>
                $(document).ready(function () {
                    $('#ngayHoatDong').datepicker({
                        format: 'yyyy-mm-dd',
                        todayHighlight: true,
                        autoclose: true,
                    });
                });
            </script>
        </div> 
        <div class="form-group" class="form-control">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày đăng ký</label>
                <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="yyyy-mm-dd" 
                                value="${item.ngayDangKy}" />" id="ngayDangKy" name="ngayDangKy"  />                    
            </div>
            <script>
                $(document).ready(function () {
                    $('#ngayDangKy').datepicker({
                        format: 'yyyy-mm-dd',
                        todayHighlight: true,
                        autoclose: true,
                    });
                });
            </script>
        </div>  
        <div class="form-group" class="form-control">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày kiểm duyệt</label>
                <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="yyyy-mm-dd" 
                                value="${item.ngayKiemDuyet}" />" id="ngayKiemDuyet" name="ngayKiemDuyet"  />                    
            </div>
            <script>
                $(document).ready(function () {
                    $('#ngayKiemDuyet').datepicker({
                        format: 'yyyy-mm-dd',
                        todayHighlight: true,
                        autoclose: true,
                    });
                });
            </script>
        </div>
        <div class="form-group" class="form-control">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày cấp phép</label>
                <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="yyyy-mm-dd" 
                                value="${item.ngayCapPhep}" />" id="ngayCapPhep" name="ngayCapPhep"  />                    
            </div>
            <script>
                $(document).ready(function () {
                    $('#ngayCapPhep').datepicker({
                        format: 'yyyy-mm-dd',
                        todayHighlight: true,
                        autoclose: true,
                    });
                });
            </script>
        </div>
        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Type</label>                             
                <input  type="type" class="form-control" name="typeBSCRNC" placeholder="TYPE" value="${item.typeBSCRNC}" />
            </div>   
        </div>    
        <!--omc_bsc_rnc_info-->
        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên trên hệ thống</label>                                    
                <input  value="${item.tenTrenHT}" type="text" class="form-control" name="tenTrenHT" placeholder="Tên trên hệ thống"  />
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">MSC_MSS</label>                                    
                <input  value="${item.mscMss}" type="text" class="form-control" name="mscMss" placeholder="MSC_MSS"  />
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">SGSN</label>                                    
                <input  value="${item.sgsn}" type="text" class="form-control" name="sgsn" placeholder="SGSN"  />
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">  
                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">OPC</label>                                    
                <input  value="${item.opc}" type="text" class="form-control" name="opc" placeholder="OPC"  />
            </div>
        </div>
        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Numeral System</label>                                    
                                <input  value="${item.numeralSystem}" type="text" class="form-control" name="numeralSystem" placeholder="Numeral System"  />
                            </div>
                        </div>     

    </form:form>
</div>
