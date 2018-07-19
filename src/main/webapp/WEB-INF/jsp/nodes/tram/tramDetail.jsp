<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="box-body" id="mydiv" >
    <form:form method="post" action="addtram" commandName="model">
        <div class="form-group">

            <label for="exampleInputEmail1">Mã BTS/NodeB/eNodeB</label>
            <input type="text" disabled class="form-control" placeholder=""  
                   value="${model.code}"/>  

        </div>  

        <div class="form-group">
            <label for="exampleInputEmail1">Loại Ne</label>
            <input disabled type="text"  class="form-control" placeholder=""  
                   value="${model.tenNeType}"/>  

        </div>

        <div class="form-group">   
            <label for="exampleInputEmail1">Mã BSC/RNC</label>
            <input disabled type="text" class="form-control" placeholder=""  
                   value="${model.maNodeCha}"/>  
        </div>   
        <div class="form-group">      
            <label for="exampleInputEmail1">Mã trạm dự án</label>
            <input disabled type="text" class="form-control" placeholder=""  
                   value="${model.codeTramDA}"/>  
        </div>                
        <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>
            <style>
                .modal-dialog {
                    width: 900px;
                }
            </style>
            <div class="panel panel-primary"  >
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin FM/PM</h3>
                </div>

                <div class="panel-body" >
                    <button id="btFM" data-toggle="modal" data-target="#myFM" class="btn btn-primary" >Chi tiết FM</button>
                    <button id="btPM" style="float: right" data-toggle="modal" data-target="#myPM" class="btn btn-primary" >Chi tiết PM</button>

                </div>

            </div>

        </c:if>
        <div class="panel panel-primary"  <c:if test='${!fn:contains(classAtrrView,"AREA")}'>  style="display: none" </c:if> >
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin khu vực</h3>
                </div>

                <div class="panel-body" >
                    <div class="form-group">

                        <label for="exampleInputEmail1">Đơn vị quản lý </label>
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
                <div class="form-group">

                    <label  for="exampleInputEmail1">Nhập mã CSHT</label>
                    <input required type="text"  class="form-control" value="${model.codeBuilding}" id="codeBuilding" placeholder="Mã CSHT" disabled /> 
                    <input type="hidden" name="buildingId" value="" value="${model.buildingId}" id="buildingId" required />


                </div>

            </div>

        </div>



        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"MANAGEMENT_INFO")}'>  style="display: none" </c:if> >
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin quản lý</h3>
                </div>
                <div class="panel-body" >                        
                    <div class="form-group" class="form-control">

                        <label for="exampleInputEmail1">Tên người quản lý</label>
                        <input required type="text" value="${model.tenNgQLy}" class="form-control" name="tenNgQLy"  placeholder="Tên người quản lý"  />                    

                </div> 
                <div class="form-group" class="form-control">

                    <label  for="exampleInputEmail1">SĐT quản lý</label>
                    <input required type="text" value="${model.SDTQLy}" class="form-control" name="SDTQLy" placeholder="SĐT quản lý"  />                    

                </div>  

            </div>
        </div>





        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"BIRTH_INFO")}'>  style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin khai sinh</h3>
                </div>
                <div class="panel-body">        
                    <div class="form-group">

                        <label  for="exampleInputEmail1">Tên cho quản lý</label>                                    
                        <input required value="${model.name}" type="text" class="form-control" name="name" placeholder="Tên cho quản lý"  />

                </div>
                <div class="form-group">

                    <label  for="exampleInputEmail1">Hoàn cảnh ra đời </label>
                    <input  type="text" value="${model.hoanCanhRaDoi}" class="form-control" placeholder="Hoàn cảnh ra đời " name="hoanCanhRaDoi" />

                </div>
                <div class="form-group" class="form-control">

                    <label  for="exampleInputEmail1">Ngày hoạt động</label>
                    <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                    value="${model.ngayHoatDong}" />" id="ngayHoatDong" name="ngayHoatDong"  />                    


                </div> 

            </div>
        </div>



       <!--  <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"STATUS_INFO")}'>  style="display: none" </c:if> >
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin trạng thái</h3>
                </div>
                <div class="panel-body" >        
                    <div class="form-group">

                        <label  for="exampleInputEmail1">Trạng thái hoạt động</label>
        <form:select path="trangThaiHDId" class="form-control"  > 
            <form:option value="">--- Chọn trạng thái hoạt động ---</form:option>
            <form:options  items="${trangThaiHDList}"  itemValue="id"  itemLabel="name"/>                                                                
        </form:select>     

    </div>
    <div class="form-group">

        <label  for="exampleInputEmail1">Trạng thái xử lý</label>
        <form:select path="trangThaiQLId" class="form-control"  > 
            <form:option value="">--- Chọn trạng thái quản lý ---</form:option>
            <form:options  items="${trangThaiQLList}"  itemValue="id"  itemLabel="name"/>                                                                
        </form:select>     

    </div>                                    
</div>
</div>    
        -->

        <div class="panel panel-primary"  <c:if test='${!fn:contains(classAtrrView,"OMC_INFO")}'>  style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin trên OMC</h3>
                </div>
                <div class="panel-body" >        
                    <div class="form-group">

                        <label for="exampleInputEmail1">Tên trên hệ thống</label>                                    

                        <input  type="text" value="${model.tenTrenHeThong}"  class="form-control" placeholder="Tên trên hệ thống" name="tenTrenHeThong" />

                </div>
                <div class="form-group">

                    <label for="exampleInputEmail1">Tên BSC/RNC</label>
                    <input value="${model.tenBSCRNC}"   type="text" class="form-control" placeholder="Tên BSC/RNC" name="tenBSCRNC" />

                </div>

                <div class="form-group">

                    <label for="exampleInputEmail1">Tên BSC/RNC quản lý</label>
                    <input value="${model.tenBSCRNCQL}"   type="text" class="form-control" placeholder="Tên BSC/RNC quản lý" name="tenBSCRNCQL" />

                </div>    
                <c:if test='${model.neTypeId == 8}'>
                    <div class="form-group">

                        <label   for="exampleInputEmail1">MSC/MSS</label>
                        <input   type="text" value="${model.mSCMSS}" class="form-control" placeholder="MSC/MSS" name="mSCMSS" />

                    </div>
                    <div class="form-group">

                        <label  for="exampleInputEmail1">SGSN</label>
                        <input  type="text" class="form-control" value="${model.sGSN}" placeholder="SGSN" name="sGSN" />

                    </div>
                </c:if>    
                <c:if test='${model.neTypeId != 2}'>
                    <div class="form-group">

                        <label for="exampleInputEmail1">DC-HSDPA 42M</label>
                        <input  type="text" class="form-control" value="${model.dCHSPDA42M}" placeholder="DC-HSDPA 42M" name="dCHSPDA42M" />

                    </div>
                </c:if> 

                <div class="form-group">

                    <label for="exampleInputEmail1">Filter User</label>
                    <input  type="text" class="form-control" value="${model.filterUser}" name="filterUser" placeholder="Filter User" />

                </div>

                <div class="form-group">

                    <label  for="exampleInputEmail1">Frequency Band</label>
                    <input  type="text" class="form-control" value="${model.frequencyBand}"  name="frequencyBand" />

                </div>
            </div>
        </div>


        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"POS_INFO")}'>  style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin toạ độ</h3>
                </div>
                <div class="panel-body" >
                    <div class="form-group">

                        <label  for="exampleInputEmail1">Latitude</label>                                    

                        <input required type="number" step="any" class="form-control" value="${model.lat}" name="lat" placeholder="Latitude" value="" />

                </div>
                <div class="form-group">

                    <label  for="exampleInputEmail1">Longitude</label>                                    

                    <input required type="number" step="any" class="form-control" value="${model.lon}" name="lon" placeholder="Longitude" value="" />

                </div> 
            </div>

        </div>

<!--
        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"CONNECT_INFO")}'>  style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin liên kết</h3>
                </div>
                <div class="panel-body" >
                    <div class="form-group" class="form-control">

                        <label  for="exampleInputEmail1">Cosite 2G - 3G Type</label>
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
                <div class="form-group" class="form-control">

                    <label for="exampleInputEmail1">Mã Cosite 2G 3G</label>
                    <input type="text" class="form-control" value="${model.maCosite}" name="maCosite" placeholder="Mã Cosite 2G 3G"  />                    

                </div>  
            </div>
        </div>

-->
        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"CONFIG_INFO")}'>  style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin cấu hình</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">

                        <label  for="exampleInputEmail1">Chọn thiết bị </label>
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
                <div class="form-group" class="form-control">

                    <label for="exampleInputEmail1">Loại Trạm</label>
                    <select name="loaiTramId" id="neTypeId" class="form-control" required > 
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
                <div class="form-group">

                    <label  for="exampleInputEmail1">Cấu hình </label>
                    <input required type="text" class="form-control" value="${model.cauHinh}"  name="cauHinh" placeholder="Cấu hình"  />

                </div>     
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

    </form:form>
</div>
<!-- /.box-body -->




<script type="text/javascript">
    $(document).ready(function () {

        $('.panel-body').each(function () {
            $(this).find('input').attr('disabled', 'disabled');
            $(this).find('select').attr('disabled', 'disabled');
        });
        $('#btPM').click(function () {
            window.open('${pageContext.request.contextPath}/pm_fm/popup?vnpCode=${model.code}&nodeType=${model.tenNeType}', '_blank', 'width=700,height=500');
                    });


                    $('#btFM').click(function () {

                        window.open('${pageContext.request.contextPath}/pm_fm/popup_fm?vnpCode=${model.code}&nodeType=${model.tenNeType}', '_blank', 'width=700,height=500');
                                });
                            });
</script>