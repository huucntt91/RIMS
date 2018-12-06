<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pm" uri="/WEB-INF/tld/permissionTagLib" %>
<%@include file="../../include/header.jsp"%>

<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Cập nhập thông tin BTS/NodeB/eNodeB</h3>
                </div> 
                <form:form method="post" action="${pageContext.request.contextPath}/nodes/update" commandName="model">


                    <div class="box-body" id="mydiv" >

                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Mã BTS/NodeB/eNodeB</label>
                                <input name="code" type="text" readonly="readonly" class="form-control" placeholder=""  
                                       value="${model.code}"/>  
                                <input type="hidden" name="id" value="${model.id}" />
                                <input type="hidden" name="status" value="${model.status}" />

                            </div>
                        </div>  
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" style="min-width:163px;" for="exampleInputEmail1">Loại Ne</label>
                                <input type="text" disabled class="form-control" placeholder=""  
                                       value="${model.tenNeType}"/>  
                                <input type="hidden" name="neTypeId" id="neTypeId" value="${model.neTypeId}" />
                            </div>
                        </div>
                        <c:if test ="${model.neTypeId != 8}"> 
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon" style="min-width:163px;" for="exampleInputEmail1">Mã BSC/RNC</label>
                                    <input type="text" disabled class="form-control" placeholder=""  
                                           value="${model.maNodeCha}"/>  

                                </div>
                            </div>
                        </c:if>
                        <c:if test ="${model.neTypeId == 8}"> 
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon" style="min-width:163px;" for="exampleInputEmail1">ENODEB ID</label>
                                    <input type="text" disabled class="form-control" placeholder=""  
                                           value="${model.enodebId}"/>  
                                </div>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" style="min-width:163px;" for="exampleInputEmail1">Mã trạm dự án</label>
                                <input type="text" disabled class="form-control" placeholder=""  
                                       value="${model.codeTramDA}"/>  

                            </div>
                        </div>                


                        <div class="panel panel-primary"  <c:if test='${!fn:contains(classAtrrView,"AREA")}'>  style="display: none" </c:if> >
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin khu vực</h3>
                                </div>

                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"AREA")}'> disableGroup="disable" </c:if>>
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Đơn vị quản lý </label>
                                            <select name="donViId" id="donViId" class="form-control" required> >
                                                <option value="">--- Chọn Đơn Vị ---</option>
                                            <c:forEach var="dvBO" items="${dvList}">

                                                <option  
                                                    value="${dvBO.donViId}" 
                                                    <c:choose>
                                                        <c:when test="${dvBO.donViId == model.donViId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose>
                                                    >${dvBO.tenDonVi}</option>
                                            </c:forEach>

                                        </select>  
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập mã CSHT</label>
                                        <input  type="text" value="${model.codeBuilding}"  class="form-control" id="codeBuilding" placeholder="Mã CSHT" disabled  /> 
                                        <input type="hidden" name="buildingId" value="${model.buildingId}" id="buildingId" />
                                        <span class="input-group-btn">
                                            <button  class="btn btn-danger" data-toggle="modal"  id="btn_clear_building" onclick="clearBuilding()" >Xóa</button>
                                            <button  class="btn btn-success" data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm CSHT</button>
                                        </span>
                                    </div>
                                    <div class="" style="color:#FFE495; font-size: 16px" id="errBuilding"></div>   
                                </div>

                            </div>

                        </div>



                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"MANAGEMENT_INFO")}'>  style="display: none" </c:if> >
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin quản lý</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"MANAGEMENT_INFO")}'> disableGroup="disable" </c:if>>                        
                                    <div class="form-group" class="form-control"
                                        <c:if test="${not pm:checkUserAttr('MANAGER_NAME', 'MANAGEMENT_INFO','VIEW')}"> style="display: none" </c:if>
                                    >
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;"  for="exampleInputEmail1">Tên người quản lý</label>
                                            <input type="text" value="${model.tenNgQLy}" class="form-control" name="tenNgQLy"  placeholder="Tên người quản lý"
                                                <c:if test="${not pm:checkUserAttr('MANAGER_NAME', 'MANAGEMENT_INFO','UPDATE')}"> disabled </c:if>
                                            />
                                    </div>
                                </div> 
                                <div class="form-group" class="form-control">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">SĐT quản lý</label>
                                        <input type="text" value="${model.SDTQLy}" class="form-control" name="SDTQLy" placeholder="SĐT quản lý"  />                    
                                    </div>
                                </div>  

                            </div>
                        </div>





                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"BIRTH_INFO")}'>  style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin khai sinh</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"BIRTH_INFO")}'> disableGroup="disable" </c:if>>        
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên cho quản lý</label>                                    
                                            <input required value="${model.name}" type="text" class="form-control" name="name" placeholder="Tên cho quản lý"  />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Hoàn cảnh ra đời </label>
                                        <input  type="text" value="${model.hoanCanhRaDoi}" class="form-control" placeholder="Hoàn cảnh ra đời " name="hoanCanhRaDoi" />
                                    </div>
                                </div>
                                <div class="form-group" class="form-control">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày hoạt động</label>
                                        <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${model.ngayHoatDong}" />" id="ngayHoatDong" name="ngayHoatDong"  />                    
                                    </div>
                                    <script>
                                        $(document).ready(function () {
                                            $('#ngayHoatDong').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true,

                                            });
                                        });
                                    </script>
                                </div> 

                                <div class="form-group" class="form-control">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày đăng ký</label>
                                        <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${model.ngayDangKy}" />" id="ngayDangKy" name="ngayDangKy"  />                    
                                    </div>
                                    <script>
                                        $(document).ready(function () {
                                            $('#ngayDangKy').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true,

                                            });
                                        });
                                    </script>
                                </div> 

                                <div class="form-group" class="form-control">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày kiểm duyệt</label>
                                        <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${model.ngayKiemDuyet}" />" id="ngayKiemDuyet" name="ngayKiemDuyet"  />                    
                                    </div>
                                    <script>
                                        $(document).ready(function () {
                                            $('#ngayKiemDuyet').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true,

                                            });
                                        });
                                    </script>
                                </div> 

                                <div class="form-group" class="form-control">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày cấp phép</label>
                                        <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${model.ngayCapPhep}" />" id="ngayCapPhep" name="ngayCapPhep"  />                    
                                    </div>
                                    <script>
                                        $(document).ready(function () {
                                            $('#ngayCapPhep').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true,

                                            });
                                        });
                                    </script>
                                </div>     

                            </div>
                        </div>



<!--                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"STATUS_INFO")}'>  style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin trạng thái</h3>
                                </div>
                                <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"STATUS_INFO")}'> disableGroup="disable" </c:if> >        
                                    <div class="form-group">
                                        <div class="input-group">                                                                            
                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Trạng thái hoạt động</label>
                        <form:select path="trangThaiHDId" class="form-control"  > 
                            <form:option value="">--- Chọn trạng thái hoạt động ---</form:option>
                            <form:options  items="${trangThaiHDList}"  itemValue="id"  itemLabel="name"/>                                                                
                        </form:select>     
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">                                
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Trạng thái xử lý</label>
                        <form:select path="trangThaiQLId" class="form-control"  > 
                            <form:option value="">--- Chọn trạng thái quản lý ---</form:option>
                            <form:options  items="${trangThaiQLList}"  itemValue="id"  itemLabel="name"/>                                                                
                        </form:select>     
                    </div>
                </div>                                    
            </div>
        </div>    -->


                        <div class="panel panel-primary"  <c:if test='${!fn:contains(classAtrrView,"OMC_INFO")}'>  style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin trên OMC</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"OMC_INFO")}'> disableGroup="disable" </c:if>>        
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên trên hệ thống</label>                                    

                                            <input  type="text" value="${model.tenTrenHeThong}"  class="form-control" placeholder="Tên trên hệ thống" name="tenTrenHeThong" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên BSC/RNC</label>
                                        <input value="${model.tenBSCRNC}"   type="text" class="form-control" placeholder="Tên BSC/RNC" name="tenBSCRNC" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên BSC/RNC quản lý</label>
                                        <input value="${model.tenBSCRNCQL}"   type="text" class="form-control" placeholder="Tên BSC/RNC quản lý" name="tenBSCRNCQL" />
                                    </div>
                                </div>    
                                <c:if test='${model.neTypeId == 8}'>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" style="min-width:150px;"  for="exampleInputEmail1">MSC/MSS</label>
                                            <input   type="text" value="${model.mSCMSS}" class="form-control" placeholder="MSC/MSS" name="mSCMSS" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">SGSN</label>
                                            <input  type="text" class="form-control" value="${model.sGSN}" placeholder="SGSN" name="sGSN" />
                                        </div>
                                    </div>
                                </c:if>    
                                <c:if test='${model.neTypeId != 2}'>
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">DC-HSDPA 42M</label>
                                            <input  type="text" class="form-control" value="${model.dCHSPDA42M}" placeholder="DC-HSDPA 42M" name="dCHSPDA42M" />
                                        </div>
                                    </div>
                                </c:if> 

                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Filter User</label>
                                        <input  type="text" class="form-control" value="${model.filterUser}" name="filterUser" placeholder="Filter User" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Frequency Band</label>
    <!--                                        <input  type="text" class="form-control" value="${model.frequencyBand}" placeholder="Frequency Band" name="frequencyBand" />-->
                                        <select name="bangTanId" id="bangTanId" class="form-control" > >
                                            <option value="">--- Chọn Frequency Band ---</option>
                                            <c:forEach var="item" items="${bangTanList}">

                                                <option  
                                                    value="${item.bang_tan_id}"                          
                                                    <c:if test="${item.bang_tan_id == model.bangTanId}">
                                                        selected    
                                                    </c:if>    
                                                    >${item.ten_bang_tan}</option>
                                            </c:forEach>

                                        </select>  
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"POS_INFO")}'>  style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin toạ độ</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"POS_INFO")}'> disableGroup="disable" </c:if>>
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Latitude</label>                                    

                                            <input required type="number" step="any" class="form-control" readonly="readonly" value="${model.lat}" name="lat" id="lat" placeholder="Latitude" value="" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Longitude</label>                                    

                                        <input required type="number" step="any" class="form-control" readonly="readonly" value="${model.lon}" name="lon" id="lon" placeholder="Longitude" value="" />
                                    </div>
                                </div> 
                            </div>

                        </div>


                        <%--                       <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"CONNECT_INFO")}'>  style="display: none" </c:if>>
                                                        <div class="panel-heading">
                                                            <h3 class="panel-title">Thông tin liên kết</h3>
                                                        </div>
                                                        <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"CONNECT_INFO")}'> disableGroup="disable" </c:if>>
                                                            <div class="form-group" class="form-control">
                                                                <div class="input-group">  
                                                                    <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Cosite 2G - 3G Type</label>
                                                                    <select name="cosite2G3GType" id="cosite2G3GType" class="form-control" required> >
                                                                        <option  <c:if test="${model.cosite2G3GType == 0}">
                                                                            selected    
                                                                        </c:if>    
                                                                        value="0">No</option>
                                                                    <option <c:if test="${model.cosite2G3GType == 1}">
                                                                            selected    
                                                                        </c:if> value="1">Yes</option>
                                                                </select>
                                                            </div>
                                                        </div> 
                                                        <div class="form-group" class="form-control">
                                                            <div class="input-group">  
                                                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Mã Cosite 2G 3G</label>
                                                                <input type="text" class="form-control" value="${model.maCosite}" name="maCosite" placeholder="Mã Cosite 2G 3G"  />                    
                                                            </div>
                                                        </div>  
                                                    </div>
                                                </div>--%>


                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"CONFIG_INFO")}'>  style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin cấu hình</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"CONFIG_INFO")}'> disableGroup="disable" </c:if>>
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn thiết bị </label>
                                            <select name="thietBiId" id="thietBiId" class="form-control" required >
                                                <option value="">--- Chọn thiết bị ---</option>
                                            <c:forEach var="item" items="${thietBiList}">
                                                <option   <c:choose>
                                                        <c:when test="${item.thietBiId == model.thietBiId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose>  
                                                    value="${item.thietBiId}">${item.tenThietBi}</option>
                                            </c:forEach>

                                        </select>  
                                    </div>
                                </div>
                                <div class="form-group" class="form-control">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                                        <select name="loaiTramId" class="form-control" > 
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
                                <c:if test='${model.neTypeId != 8}'>
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Cấu hình </label>
                                            <select name="cauHinhPortId" id="cauHinhPortId" class="form-control"  >
                                                <option value="">--- Chọn Cấu Hình ---</option>
                                                <c:forEach var="item" items="${cauHinhList}">

                                                    <option 
                                                        <c:if test="${item.CAU_HINH_PORT_ID == model.cauHinhPortId}">  selected  </c:if>
                                                        value="${item.CAU_HINH_PORT_ID}"

                                                        >${item.CAU_HINH_PORT}</option>
                                                </c:forEach>

                                            </select> 
                                        </div>
                                    </div> 
                                </c:if> 
                            </div>
                        </div>

                        <%-- <c:if test='${fn:contains(classAtrrView,"MAINTENANCE_INFO")}'>    
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin bảo dưỡng</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"MAINTENANCE_INFO")}'> disableGroup="disable" </c:if>>
                                    <div class="form-group" class="form-control">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Thời gian bảo dưỡng</label>                    
                                            <input  type="text" class="form-control date_form" value="${model.ngayBaoDuong}"  id="ngayBaoDuong" name="ngayBaoDuong"  />                    
                                        </div>
                                        <script>
                                            $(document).ready(function () {
                                                $('#ngayBaoDuong').datepicker({
                                                    format: 'mm/dd/yyyy',
                                                    todayHighlight: true,
                                                    autoclose: true
                                                });
                                            });
                                        </script>
                                    </div>
                                    <div class="form-group" class="form-control">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Đơn vị thực hiện</label>
                                            <input type="text" class="form-control" name="donViThucHien" value="${model.donViThucHien}"  placeholder="Đơn vị thực hiện"  />                    
                                        </div>
                                    </div>  
                                </div> 

                            </div>
                        </c:if> --%>


                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                        <button type="submit" onclick="return checkValid();" class="btn btn-primary">Cập nhật</button>
                    </div>

                </form:form>
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

                    <button type="button"  class="btn btn-default" data-dismiss="modal">Close</button>
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

                                $('#btn_building').click(function () {
                                    $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
                                    $('#errBuilding').text('');
                                });

                                $('#myBuilding iframe').on('load', function (e) {
                                    var iframe1 = $('#myBuilding iframe').contents();
                                    iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                        //alert($(this).text());
                                        $('#codeBuilding').val($(this).find('input.txtcode').val());
                                        $('#buildingId').val($(this).find('input.txtid').val());
                                        $('#lat').val($(this).find('input.txtlat').val());
                                        $('#lon').val($(this).find('input.txtlon').val());
                                        findNodeByBuilding($(this).find('input.txtid').val(), $('#neTypeId').val());
                                    });
                                    iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                        //alert($(this).text());
                                        $('#codeBuilding').val($(this).find('input.txtcode').val());
                                        $('#buildingId').val($(this).find('input.txtid').val());
                                        $('#lat').val($(this).find('input.txtlat').val());
                                        $('#lon').val($(this).find('input.txtlon').val());
                                        $('#myBuilding').modal('hide');
                                        findNodeByBuilding($(this).find('input.txtid').val(), $('#neTypeId').val());
                                    });
                                });

                                $('.panel-body').each(function () {
                                    if ($(this).attr('disableGroup') != undefined) {
                                        $(this).find('input').attr('readonly', 'readonly');
                                        $(this).find('select').attr('readonly', 'readonly');
                                    }
                                });

                            });

                            function findNodeByBuilding(id, type)
                            {
                                $.get("${pageContext.request.contextPath}/nodes/getSiteBuilding/" + id + "/" + type, function (data) {
                                    if (data.length > 0) {
                                        var htmlx = 'Đã tồn tại trạm:  ';
                                        data.forEach(function (data) {
                                            htmlx += " " + data.code;
                                        });
                                        htmlx += " gắn tới CSHT đã chọn!";
                                        $('#errBuilding').text(htmlx);
                                    }
                                });
                            }
                            function checkValid()
                            {
                                var error = $('#errBuilding').text();
                                if (error == '') {
                                    return true;
                                } else {
                                    return confirm(error + " Bạn chắc chắn cập nhật?");
                                }

                            }
                            function clearBuilding() {
                                if ($('#codeBuilding').val() != null && $('#codeBuilding').val() != '') {
                                    if (confirm('Bạn có muốn xóa liên kết với CSHT hiện tại không ?')) {
                                        $('#buildingId').val('');
                                        $('#codeBuilding').val('');
                                    }
                                }
                            }

</script>

