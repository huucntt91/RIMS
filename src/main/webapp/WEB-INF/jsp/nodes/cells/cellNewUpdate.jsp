<%@page import="com.vnpt.media.rims.common.utils.Convert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="../../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>

<section class="content-header">

</section>
<div class="content-header">
    <h1>Cập nhật khai báo cell<c:if test="${neTypeId==5}"> 2G</c:if><c:if test="${neTypeId==6}"> 3G</c:if><c:if test="${neTypeId==7}"> 4G</c:if></h1>    
    </div>
    <section class="content">            
        <div class="tab-content">        
        <c:if test="${neTypeId==5}"> 
            <div id="menu0" class="tab-pane fade in active">            
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-body" id="mydiv" >
                                <form:form method="post" action="${pageContext.request.contextPath}/cells/updateAddCell2g" commandName="cellNewForm">
                                    <input type="hidden" value="${action}" name="action" />   
                                    <input type="hidden" value="5" id="neTypeId" name="neTypeId" />   
                                    <form:hidden name="nodeId"   path="nodeId"  />
                                    <c:if test='${fn:contains(classAtrrView,"AREA")}'>                                               
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" for="exampleInputEmail1">Nhập mã BTS</label>
                                                <form:input required="required" class="form-control" path="maNodeCha" id="cell2GCodeId"  ></form:input>
                                                <form:hidden name="nodeChaId"  id="cell2GChaId" path="nodeChaId" />                                                    
                                                <span class="input-group-btn">
                                                    <button type="button"  class="btn btn-success btn_bts" data-toggle="modal" data-target="#myModalBTSCode" id="btn_bts"  >Tìm BTS</button>
                                                </span>
                                            </div>
                                        </div>

                                    </c:if>
                                    <c:if test='${fn:contains(classAtrrView,"BIRTH_INFO")}'>   
                                        <div class="panel panel-primary">                                    
                                            <div class="panel-heading">                                        
                                                <h3 class="panel-title">
                                                    Thông tin khai sinh</h3>
                                            </div>
                                            <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"BIRTH_INFO")}'> disableGroup="disable" </c:if> >                
                                                    <div class="form-group">
                                                        <div class="input-group">                                
                                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên cho quản lý</label>
                                                        <form:input  type="text" class="form-control" path="tenCell" placeholder="Tên cho quản lý" 
                                                                     value="${cell2gRegForm.tenCell}"/>
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
                                                        $(document).ready(function() {
                                                            $('#ngayHoatDong').datepicker({
                                                                format: 'mm/dd/yyyy',
                                                                todayHighlight: true,
                                                                autoclose: true
                                                            });
                                                        });
                                                    </script>
                                                </div> 

                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test='${fn:contains(classAtrrView,"OMC_INFO")}'>   
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Thông tin trên OMC</h3>
                                            </div>
                                            <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"OMC_INFO")}'> disableGroup="disable" </c:if> >         
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
                                                        <form:input required="true" class="form-control" placeholder="LAC" name="lac" type="number"
                                                                    path="lac"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">CI</label>
                                                        <form:input required="true" class="form-control" placeholder="CI" name="ci" type="number"
                                                                    path="ci"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Frequency Band</label>

                                                        <form:select name="bang_tan_id" path="bangTanId" class="form-control" required="true"  >
                                                            <form:option value="1">--- Băng tần ---</form:option>
                                                            <form:options  items="${bangTanList2G}"  itemValue="bang_tan_id"  itemLabel="ten_bang_tan"/>                                                
                                                        </form:select>  
                                                    </div>                                                                                                
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test='${fn:contains(classAtrrView,"CONFIG_INFO")}'>   
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Thông tin cấu hình</h3>
                                            </div>
                                            <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"CONFIG_INFO")}'> disableGroup="disable" </c:if> >         
                                                    <div class="form-group">
                                                        <div class="input-group">                                
                                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Chọn thiết bị</label>
                                                        <form:select name="thietBiId" path="thietBiId" id="thietBiId" class="form-control" required="true"   >
                                                            <form:option value="">--- Chọn thiết bị ---</form:option>
                                                            <form:options  items="${thietBiList}"  itemValue="thietBiId"  itemLabel="tenThietBi"/>
                                                        </form:select>  
                                                    </div>
                                                </div>
                                                <div class="form-group" class="form-control">
                                                    <div class="input-group">                                
                                                        <label  class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                                                        <form:select required="true" path="loaiTramId" class="form-control"  > 
                                                            <form:option value="">--- Chọn loại trạm ---</form:option>
                                                            <form:options  items="${tramList2G}"  itemValue="loaiTramId"  itemLabel="tenLoaiTram"/>
                                                        </form:select>     
                                                    </div>
                                                </div>                                                    
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>       
                </div>
            </div>
        </c:if>
        <c:if test="${neTypeId==6}"> 

            <div id="menu1" class="tab-pane fade  ">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-body" id="mydiv" >
                                <form:form method="post" action="${pageContext.request.contextPath}/cells/updateAddCell3g" commandName="cellNewForm">
                                    <input type="hidden" value="${action}" name="action" />   
                                    <input type="hidden" value="6" id="neTypeId" name="neTypeId" />   
                                    <form:hidden name="nodeId"   path="nodeId" />
                                    <c:if test='${fn:contains(classAtrrView,"AREA")}'>                                               
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" for="exampleInputEmail1">Nhập mã NodeB</label>
                                                <form:input required="required"  class="form-control" path="btsCode" id="cell3GCodeId"  ></form:input>
                                                <form:hidden name="nodeChaId"  id="cell3GChaId" path="nodeChaId" />                                                    
                                                <span class="input-group-btn">
                                                    <button type="button"  class="btn btn-success btn_bts" data-toggle="modal" data-target="#myModalBTSCode" id="btn_bts"  >Tìm NodeB</button>
                                                </span>
                                            </div>
                                        </div>

                                    </c:if>
                                    <c:if test='${fn:contains(classAtrrView,"BIRTH_INFO")}'>   
                                        <div class="panel panel-primary">                                    
                                            <div class="panel-heading">                                        
                                                <h3 class="panel-title">
                                                    Thông tin khai sinh</h3>
                                            </div>
                                            <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"BIRTH_INFO")}'> disableGroup="disable" </c:if> >                
                                                    <div class="form-group">
                                                        <div class="input-group">                                
                                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên cho quản lý</label>
                                                        <form:input  type="text" class="form-control" path="tenCell" placeholder="Tên cho quản lý" 
                                                                     value="${cell2gRegForm.tenCell}"/>
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
                                                        <form:input  class="form-control date_form" id="ngayHoatDong3G" 
                                                                     path="ngayHoatDong" />                    
                                                    </div>                                                                                                
                                                    <script>
                                                        $(document).ready(function() {
                                                            $('#ngayHoatDong3G').datepicker({
                                                                format: 'mm/dd/yyyy',
                                                                todayHighlight: true,
                                                                autoclose: true
                                                            });
                                                        });
                                                    </script>
                                                </div> 

                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test='${fn:contains(classAtrrView,"OMC_INFO")}'>   
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Thông tin trên OMC</h3>
                                            </div>
                                            <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"OMC_INFO")}'> disableGroup="disable" </c:if> >         
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
                                                        <form:input required="true" class="form-control" placeholder="LAC" name="lac" type="number"
                                                                    path="lac"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">CI</label>
                                                        <form:input required="true" class="form-control" placeholder="CI" name="ci" type="number"
                                                                    path="ci"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Frequency Band</label>

                                                        <form:select name="bang_tan_id" path="bangTanId" class="form-control" required="true"  >
                                                            <form:option value="">--- Băng tần ---</form:option>
                                                            <form:options  items="${bangTanList3G}"  itemValue="bang_tan_id"  itemLabel="ten_bang_tan"/>                                                
                                                        </form:select>  
                                                    </div>                                                                                                
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test='${fn:contains(classAtrrView,"CONFIG_INFO")}'>   
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Thông tin cấu hình</h3>
                                            </div>
                                            <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"CONFIG_INFO")}'> disableGroup="disable" </c:if> >         
                                                    <div class="form-group">
                                                        <div class="input-group">                                
                                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Chọn thiết bị</label>
                                                        <form:select name="thietBiId" path="thietBiId" id="thietBiId" class="form-control" required="true"   >
                                                            <form:option value="">--- Chọn thiết bị ---</form:option>
                                                            <form:options  items="${thietBiList}"  itemValue="thietBiId"  itemLabel="tenThietBi"/>
                                                        </form:select>  
                                                    </div>
                                                </div>
                                                <div class="form-group" class="form-control">
                                                    <div class="input-group">                                
                                                        <label  class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                                                        <form:select required="true" path="loaiTramId" class="form-control"  > 
                                                            <form:option value="">--- Chọn loại trạm ---</form:option>
                                                            <form:options  items="${tramList3G}"  itemValue="loaiTramId"  itemLabel="tenLoaiTram"/>
                                                        </form:select>     
                                                    </div>
                                                </div>  
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">No of carrier</label>
                                                        <form:input  class="form-control" placeholder="No of carrier" 
                                                                     type="number"  path="noOfCarrier"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>       
                </div>
            </div>
        </c:if>
        <c:if test="${neTypeId==7}"> 
            <div id="menu2" class="tab-pane fade  ">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-body" id="mydiv" >
                                <form:form method="post" action="${pageContext.request.contextPath}/cells/updateAddCell4g" commandName="cellNewForm">
                                    <input type="hidden" value="${action}" name="action" />   
                                    <input type="hidden" value="7" id="neTypeId" name="neTypeId" />   
                                    <form:hidden name="nodeId"   path="nodeId" />
                                    <c:if test='${fn:contains(classAtrrView,"AREA")}'>                                               
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" for="exampleInputEmail1">Nhập mã EnodeB</label>
                                                <form:input required="required"  class="form-control" path="btsCode" id="cell4GCodeId"  ></form:input>
                                                <form:hidden name="nodeChaId"  id="cell4GChaId" path="nodeChaId" />                                                    
                                                <span class="input-group-btn">
                                                    <button type="button"  class="btn btn-success btn_bts" data-toggle="modal" data-target="#myModalBTSCode" id="btn_bts"  >Tìm ENodeB</button>
                                                </span>
                                            </div>
                                        </div>

                                    </c:if>
                                    <c:if test='${fn:contains(classAtrrView,"BIRTH_INFO")}'>   
                                        <div class="panel panel-primary">                                    
                                            <div class="panel-heading">                                        
                                                <h3 class="panel-title">
                                                    Thông tin khai sinh</h3>
                                            </div>
                                            <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"BIRTH_INFO")}'> disableGroup="disable" </c:if> >                
                                                    <div class="form-group">
                                                        <div class="input-group">                                
                                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên cho quản lý</label>
                                                        <form:input  type="text" class="form-control" path="tenCell" placeholder="Tên cho quản lý" 
                                                                     value="${cell2gRegForm.tenCell}"/>
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
                                                        <form:input  class="form-control date_form" id="ngayHoatDong4G" 
                                                                     path="ngayHoatDong" />                    
                                                    </div>                                                                                                
                                                    <script>
                                                        $(document).ready(function() {
                                                            $('#ngayHoatDong4G').datepicker({
                                                                format: 'mm/dd/yyyy',
                                                                todayHighlight: true,
                                                                autoclose: true
                                                            });
                                                        });
                                                    </script>
                                                </div> 

                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test='${fn:contains(classAtrrView,"OMC_INFO")}'>   
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Thông tin trên OMC</h3>
                                            </div>
                                            <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"OMC_INFO")}'> disableGroup="disable" </c:if> >         
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
                                                        <form:input required="true" class="form-control" placeholder="LAC" name="lac" type="number"
                                                                    path="lac"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">CI</label>
                                                        <form:input required="true" class="form-control" placeholder="CI" name="ci" type="number"
                                                                    path="ci"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Frequency Band</label>

                                                        <form:select name="bang_tan_id" path="bangTanId" class="form-control" required="true"  >
                                                            <form:option value="">--- Băng tần ---</form:option>
                                                            <form:options  items="${bangTanList4G}"  itemValue="bang_tan_id"  itemLabel="ten_bang_tan"/>                                                
                                                        </form:select>  
                                                    </div>                                                                                                
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test='${fn:contains(classAtrrView,"CONFIG_INFO")}'>   
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Thông tin cấu hình</h3>
                                            </div>
                                            <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"CONFIG_INFO")}'> disableGroup="disable" </c:if> >         
                                                    <div class="form-group">
                                                        <div class="input-group">                                
                                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Chọn thiết bị</label>
                                                        <form:select name="thietBiId" path="thietBiId" id="thietBiId" class="form-control" required="true"   >
                                                            <form:option value="">--- Chọn thiết bị ---</form:option>
                                                            <form:options  items="${thietBiList}"  itemValue="thietBiId"  itemLabel="tenThietBi"/>
                                                        </form:select>  
                                                    </div>
                                                </div>
                                                <div class="form-group" class="form-control">
                                                    <div class="input-group">                                
                                                        <label  class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                                                        <form:select required="true" path="loaiTramId" class="form-control"  > 
                                                            <form:option value="">--- Chọn loại trạm ---</form:option>
                                                            <form:options  items="${tramList4G}"  itemValue="loaiTramId"  itemLabel="tenLoaiTram"/>
                                                        </form:select>     
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">No of carrier</label>
                                                        <form:input  class="form-control" placeholder="No of carrier" 
                                                                     type="number"  path="noOfCarrier"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>       
                </div>
            </div>
        </c:if>

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

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
                                                        $(document).ready(function() {

                                                            $('#btn_building').click(function() {
                                                                $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
                                                            });
                                                            $('#myModal iframe').on('load', function(e) {
                                                                var iframe1 = $('#myModal iframe').contents();

                                                                iframe1.find('#example1 tbody').on('click', 'tr', function() {
                                                                    //alert($(this).text());
                                                                    $('#codeBuilding').val($(this).find('input.txtcode').val());
                                                                    $('#buildingId').val($(this).find('input.txtid').val());

                                                                });
                                                                iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                                                                    //alert($(this).text());
                                                                    $('#codeBuilding').val($(this).find('input.txtcode').val());
                                                                    $('#buildingId').val($(this).find('input.txtid').val());
                                                                    $('#myModal').modal('hide');
                                                                });
                                                            });
                                                            //$('#ifram-value').val($('#myModal iframe').find('tr.selected').text());
                                                            //BTS CODE
                                                            $('.btn_bts').click(function() {
                                                                var neType = 2;
                                                                $('#tabs li').each(function(index) {
                                                                    if ($(this).attr('class') == 'active') {
                                                                        neType = $(this).attr('data-id');
                                                                    }
                                                                });

                                                                $("#myModalBTSCode iframe").prop({'src': '${pageContext.request.contextPath}/nodes/popup?neTypeId=' + neType});


                                                            });
                                                            $('#myModalBTSCode iframe').on('load', function(e) {
                                                                var iframe1 = $('#myModalBTSCode iframe').contents();
                                                                iframe1.find('#example1 tbody').on('click', 'tr', function() {
                                                                    //alert($(this).text());
                                                                    $('#nodeChaId').val($(this).find('input.node_id').val());
                                                                    $('#btsCodeId').val($(this).find('input.code_id').val());
                                                                });
                                                                iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                                                                    //alert($(this).text());
                                                                    //var active = $( ".selector" ).tabs( "option", "active" );
//                                                                var ref_this = $("ul#tabs li a.active");
//                                                     alert(ref_this);
                                                                    var neType = 2;
                                                                    $('#tabs li').each(function(index) {
                                                                        if ($(this).attr('class') == 'active') {
                                                                            neType = $(this).attr('data-id');
                                                                        }
                                                                    });
//                                                                var ref_this = $("ul.tabs li a").find(".active");
                                                                    if (neType == 2) {
                                                                        $('#cell2GChaId').val($(this).find('input.node_id').val());
                                                                        $('#cell2GCodeId').val($(this).find('input.code_id').val());
                                                                    }
                                                                    if (neType == 3) {
                                                                        $('#cell3GChaId').val($(this).find('input.node_id').val());
                                                                        $('#cell3GCodeId').val($(this).find('input.code_id').val());
                                                                    }
                                                                    if (neType == 8) {
                                                                        $('#cell4GChaId').val($(this).find('input.node_id').val());
                                                                        $('#cell4GCodeId').val($(this).find('input.code_id').val());
                                                                    }
                                                                    $('#myModalBTSCode').modal('hide');
                                                                });
                                                            });
                                                            $('.panel-body').each(function() {
                                                                if ($(this).attr('disableGroup') !== undefined) {
                                                                    $(this).find('input').attr('disabled', 'disabled');
                                                                    $(this).find('select').attr('disabled', 'disabled');
                                                                }
                                                            });
                                                        });
                                                        $('#neTypeId').change(function() {
//                                                        alert('The option with value ' + $(this).val() + ' and text ' + $(this).text() + ' was selected.');
                                                            $('#btsCodeId').val('');
                                                            $('#nodeChaId').val('');
                                                            window.location = '${pageContext.request.contextPath}/cell/init?neTypeId=' + $('#neTypeId').val();
                                                        });

                                                        $(document).ready(function() {
//        var tab = $('#tab_id').val();
                                                            var tab = '${tab_id}';
                                                            if (tab == '0') {
                                                                $('#tabs a[href="#menu0"]').tab('show');
                                                            }
                                                            else if (tab == '1') {
                                                                $('#tabs a[href="#menu1"]').tab('show');
                                                            }
                                                            else {
                                                                $('#tabs a[href="#menu2"]').tab('show');
                                                            }
                                                            $('#qhId').val($('#quyHoachId').val());
                                                            $('#tinhTpId').val($('#province').val());
                                                            $('#parentId').val($('#projectParent').val());
//                                                        $("#table1").DataTable({
//                                                            "language": {
//                                                                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
//                                                            }
//                                                        });
//                                                        $("#table2").DataTable({
//                                                            "language": {
//                                                                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
//                                                            }
//                                                        });



                                                        });
</script>
