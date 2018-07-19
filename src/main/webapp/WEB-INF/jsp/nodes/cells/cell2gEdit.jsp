<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="../../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Cập nhật thông tin cell 2G</h3>
                </div>
                <div class="box-body" id="mydiv" >                                    
                    <form:form method="post" action="${pageContext.request.contextPath}/cells/updateCell2g" commandName="cellNewForm">
                        <form:hidden path="id" value="${cellNewForm.nodeId}"/>
                        <form:hidden path="neTypeId" value="${cellNewForm.neTypeId}"/>
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">Mã cell</label>
                                <form:input readonly="true" class="form-control" placeholder="Mã cell"
                                            path="code"
                                            value="${cellNewForm.code}"/>  
                            </div>
                        </div>  
                        <div class="form-group">
                            <div class="input-group">

                                <form:hidden path="status"
                                             value="${cellNewForm.status}"/>  
                            </div>
                        </div>  

                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"AREA")}'>    style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin khu vực</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"AREA")}'> disableGroup="disable" </c:if>>        
                                    <div class="form-group">
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn kiểu Cell</label>
                                        <form:select  path="neTypeId" id="neTypeid" class="form-control"  >
                                            <form:option value="5">--- Cell 2G ---</form:option>                                            
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon" for="exampleInputEmail1">Mã BTS</label>
                                        <input type="text" disabled class="form-control"
                                               value=${cellNewForm.maNodeCha} />                                     
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập mã CSHT</label>
                                        <input required type="text" value="${cellNewForm.codeBuilding}"  class="form-control" id="codeBuilding" placeholder="Mã CSHT" disabled  /> 
                                        <input type="hidden" name="buildingId" value="${cellNewForm.buildingId}" id="buildingId" />
                                        <span class="input-group-btn">
                                            <button  class="btn btn-danger" data-toggle="modal"  id="btn_clear_building" onclick="clearBuilding()" >Xóa</button>
                                            <button  class="btn btn-success" data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm CSHT</button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"BIRTH_INFO")}'>    style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin khai sinh</h3>
                                </div>
                                <!--disableGroup="disable"-->
                                <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"BIRTH_INFO")}'> disableGroup="disable" </c:if>>        
                                    <div class="form-group">
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên cho quản lý</label>
                                        <form:input  type="text" class="form-control" path="tenCell" placeholder="Tên cho quản lý" 
                                                     value="${cellNewForm.tenCell}"/>
                                    </div>                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                                                                    
                                        <label class=" input-group-addon" style="min-width:150px;">Hoàn cảnh ra đời</label>
                                        <form:input  type="text" class="form-control" placeholder="Hoàn cảnh ra đời " 
                                                     path="hoanCanhRaDoi" />
                                    </div>                                                                                                
                                </div>
                                <div class="form-group" class="form-control">                                              
                                    <div class="input-group">                                                                                    
                                        <label class=" input-group-addon" style="min-width:150px;">Ngày hoạt động</label>
                                        <form:input  class="form-control date_form" id="ngayHoatDong" 
                                                     path="ngayHoatDong" />                    
                                    </div>                                                                                                
                                    <script>
                                        $(document).ready(function () {
                                            $('#ngayHoatDong').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true
                                            });
                                        });
                                    </script>
                                </div> 

                            </div>
                        </div>


                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"OMC_INFO")}'>   style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin trên OMC</h3>
                                </div>
                                <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"OMC_INFO")}'> disableGroup="disable" </c:if>>        
                                    <div class="form-group">
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Tên trên hệ thống</label>
                                        <form:input  class="form-control" placeholder="Tên trên hệ thống" name="temTrenHeThong" 
                                                     path="tenTrenHeThong"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">LAC</label>
                                        <form:input  class="form-control" placeholder="LAC" name="lac" type="number"
                                                     path="lac"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">CI</label>
                                        <form:input class="form-control" placeholder="CI" name="ci" type="number"
                                                    path="ci"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Frequency Band</label>
                                        <select name="bangTanId" id="bangTanId" class="form-control" required="true" >
                                            <option value="">-Chọn-</option>
                                            <c:forEach var="it" items="${bangTanList2G}">
                                                <option value="${it.bang_tan_id}" <c:choose> 
                                                            <c:when test="${it.bang_tan_id == bangTanId}">
                                                                selected    
                                                            </c:when>    
                                                        </c:choose> 
                                                        >${it.ten_bang_tan}</option>
                                            </c:forEach>
                                        </select>  
                                    </div>                                                                                                
                                </div>
                            </div>
                        </div>


                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"CONFIG_INFO")}' >  style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin cấu hình</h3>
                                </div>
                                <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"CONFIG_INFO")}'> disableGroup="disable" </c:if>>
                                    <div class="form-group">
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Chọn thiết bị</label>
                                        <form:select name="thietBiId" path="thietBiId" id="thietBiId" class="form-control"  >
                                            <form:option value="">--- Chọn thiết bị ---</form:option>
                                            <form:options  items="${thietBiList}"  itemValue="thietBiId"  itemLabel="tenThietBi"/>                                                
                                        </form:select>  
                                    </div>
                                </div>
                                <div class="form-group" class="form-control">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                                        <form:select path="loaiTramId" class="form-control"  > 
                                            <form:option value="">--- Chọn loại trạm ---</form:option>
                                            <form:options  items="${tramList2G}"  itemValue="loaiTramId"  itemLabel="tenLoaiTram"/>                                               
                                        </form:select>     
                                    </div>
                                </div>                                    
                            </div>
                        </div>


                        <!--////////////////////////-->

                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"QUANTITY_INFO")}'>    style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin Chất lượng</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"QUANTITY_INFO")}'> disableGroup="disable" </c:if>   >                                            
                                    <div class="form-group">
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Ngày phê duyệt</label>
                                        <form:input  class="form-control"  id="ngayKiemDuyet"
                                                     path="ngayKiemDuyet"/>
                                    </div>
                                    <script>
                                        $(document).ready(function () {
                                            $('#ngayKiemDuyet').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true
                                            });
                                        });
                                    </script>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Lý do</label>
                                        <form:input  class="form-control" 
                                                     path="note" name="cellNewForm.note"/>
                                    </div>
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
        </div>-->


                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"RF_INFO")}'>    style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin RF</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"RF_INFO")}'> disableGroup="disable" </c:if> >        

                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Azimuth</label>
                                        <form:input  class="form-control" type="number"
                                                     path="azimuth" name="cellNewForm.azimuth"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Mechanical Tilt</label>
                                        <form:input  class="form-control" type="number"
                                                     path="mechanicalTilt" name="cellNewForm.mechanicalTilt"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Total tilt</label>
                                        <form:input  class="form-control" type="number"
                                                     path="totalTilt" name="cellNewForm.totalTilt"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Antenna high</label>
                                        <form:input  class="form-control" type="number"
                                                     path="antennaHigh" name="cellNewForm.antennaHigh"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">   
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Antenna type</label>


                                        <select name="antennaType" id="antennaType" class="form-control selectpicker" data-live-search="true" >
                                            <option value="">--- Chọn loại Anten ---</option>
                                            <c:forEach var="temp" items="${loaiAnTen}">
                                                <option  
                                                    <c:if test='${cellNewForm.antennaType == temp.id}' >  selected="selected" </c:if>
                                                    value="${temp.id}"
                                                    >${temp.name}</option>
                                            </c:forEach>

                                        </select>    
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Antenna gain</label>
                                        <form:input  class="form-control" 
                                                     path="antennaGain" name="cellNewForm.antennaGain"/>
                                    </div>                                                                                                
                                </div>

                            </div>
                        </div>



                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"RF_INFO")}'>    style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin khác</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"RF_INFO")}'> disableGroup="disable" </c:if> >        
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon">Nhóm cell</label>
                                            <select multiple="multiple" name="nhomcellId" id="nhomcellId" class="form-control"
                                                    >                                                 
                                            <c:forEach var="tinhBO" items="${nhomcellList}">
                                                <option  <c:if test='${fn:contains(nhomcellId,tinhBO.id)}' >  selected="selected" </c:if>
                                                                                                              value="${tinhBO.id}"  
                                                                                                              >${tinhBO.name}</option>
                                            </c:forEach>
                                        </select>                                  
                                    </div>
                                </div>
                                <!--                                <div class="form-group">
                                                                    <div class="input-group">  
                                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Cell Type</label>
                                <form:input  class="form-control"  type="number"
                                             path="cellType" name="cellNewForm.cellType"/>
                            </div>                                                                                                
                        </div>-->
                                <!--                                <div class="form-group">
                                                                    <div class="input-group">  
                                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">VNP code</label>
                                <form:input  class="form-control" 
                                             path="vnpCode" name="cellNewForm.vnpCode"/>
                            </div>                                                                                                
                        </div>-->

                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Ngày đăng ký</label>
                                        <form:input  class="form-control"  id="ngayDangKy"
                                                     path="ngayDangKy"/>
                                    </div>
                                    <script>
                                        $(document).ready(function () {
                                            $('#ngayDangKy').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true
                                            });
                                        });
                                    </script>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Ngày cấp phép</label>
                                        <form:input  class="form-control"  id="ngayCapPhep"
                                                     path="ngayCapPhep"/>
                                    </div>
                                    <script>
                                        $(document).ready(function () {
                                            $('#ngayCapPhep').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true
                                            });
                                        });
                                    </script>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">ELECTRICAL_TILT</label>
                                        <form:input  class="form-control"  type="number"
                                                     path="electricalTilt" name="cellNewForm.electricalTilt"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Antenna Model</label>
                                        <form:input  class="form-control" 
                                                     path="antennaModel" name="cellNewForm.antennaModel"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Antenna Pattern</label>
                                        <form:input  class="form-control" 
                                                     path="antennaPattern" name="cellNewForm.antennaPattern"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">bosterTma</label>
                                        <form:input  class="form-control" 
                                                     path="bosterTma" name="cellNewForm.bosterTma"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">specialCoverage</label>
                                        <form:input  class="form-control" 
                                                     path="specialCoverage" name="cellNewForm.specialCoverage"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">bcch</label>
                                        <form:input  class="form-control"  type="number" 
                                                     path="bcch" name="cellNewForm.bcch"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">bsic</label>
                                        <form:input  class="form-control" 
                                                     path="bsic" name="cellNewForm.bsic"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">tch</label>
                                        <form:input  class="form-control" 
                                                     path="tch" name="cellNewForm.tch"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">trxConfig</label>
                                        <form:input  class="form-control" 
                                                     path="trxConfig" name="cellNewForm.trxConfig"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Tên người quản lý</label>
                                        <form:input  class="form-control" 
                                                     path="tenNgQLy" name="cellNewForm.tenNgQLy"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">SDT quản lý</label>
                                        <form:input  class="form-control" 
                                                     path="SDTQLy" name="cellNewForm.SDTQLy"/>
                                    </div>                                                                                                
                                </div>
                            </div>
                        </div>

                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>           
</section>


<!--<div id="myModal1" class="modal fade" role="dialog">-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
<div class="modal fade" id="myModalBTSCode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
<%@include file="../../include/footer.jsp"%>
<!-- jQuery 2.0.2 -->
<!--<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>-->
<!-- Bootstrap -->
<!--<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>-->
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<!--call ajax-->
<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>

<script src="${pageContext.request.contextPath}/resources/js/bootstrap-select.min.js" type="text/javascript"></script>
<script type="text/javascript">
                                        $(document).ready(function () {
                                            $('#nhomcellId').multiselect(({
                                                maxHeight: 200,
                                                enableFiltering: true,
                                                includeSelectAllOption: true,
                                                onChange: function (element, checked) {
                                                }
                                            }));
                                            $('#btn_building').click(function () {
                                                $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
                                            });

                                            $('#myBuilding iframe').on('load', function (e) {
                                                var iframe1 = $('#myBuilding iframe').contents();
                                                iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                                    //alert($(this).text());
                                                    $('#codeBuilding').val($(this).find('input.txtcode').val());
                                                    $('#buildingId').val($(this).find('input.txtid').val());
                                                    $('#lat').val($(this).find('input.txtlat').val());
                                                    $('#lon').val($(this).find('input.txtlon').val());
                                                });
                                                iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                                    //alert($(this).text());
                                                    $('#codeBuilding').val($(this).find('input.txtcode').val());
                                                    $('#buildingId').val($(this).find('input.txtid').val());
                                                    $('#lat').val($(this).find('input.txtlat').val());
                                                    $('#lon').val($(this).find('input.txtlon').val());
                                                    $('#myBuilding').modal('hide');
                                                });
                                            });
                                            //$('#ifram-value').val($('#myModal iframe').find('tr.selected').text());
                                            //BTS CODE
                                            $('#btn_bts').click(function () {
                                                $("#myModalBTSCode iframe").prop({'src': '${pageContext.request.contextPath}/nodes/popup?neTypeId=' + $('#cellTypeId').val()});
                                            });
                                            $('#myModalBTSCode iframe').on('load', function (e) {
                                                var iframe1 = $('#myModalBTSCode iframe').contents();
                                                iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                                    //alert($(this).text());
                                                    $('#btsCode').val($(this).find('input.node_id').val());
                                                    $('#btsCodeId').val($(this).find('input.code_id').val());
                                                });
                                                iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                                    //alert($(this).text());
                                                    $('#btsCode').val($(this).find('input.node_id').val());
                                                    $('#btsCodeId').val($(this).find('input.code_id').val());
                                                    $('#myModalBTSCode').modal('hide');
                                                });
                                            });

                                            $('.panel-body').each(function () {
                                                if ($(this).attr('disableGroup') != undefined) {
                                                    $(this).find('input').attr('readonly', 'readonly');
                                                    $(this).find('select').attr('disabled', 'disabled');
                                                }
                                            });
                                        });
                                        function clearBuilding() {
                                            if ($('#codeBuilding').val() != null && $('#codeBuilding').val() != '') {
                                                if (confirm('Bạn có muốn xóa liên kết với CSHT hiện tại không ?')) {
                                                    $('#buildingId').val('');
                                                    $('#codeBuilding').val('');
                                                }
                                            }
                                        }
</script>
