<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="../../include/header.jsp"%>

<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Cập nhật thông tin cell 2G/cell 3G/cell 4G</h3>
                </div>
                <div class="box-body" id="mydiv" >                                    
                    <form:form method="post" action="${pageContext.request.contextPath}/cell/update" commandName="cellNewForm">
                        <form:hidden path="cellInfo.id" value="${cellNewForm.cellInfo.nodeId}"/>
                        <form:hidden path="cellInfo.neTypeId" value="${cellNewForm.cellInfo.neTypeId}"/>
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">Mã trạm</label>
                                <form:input readonly="true" class="form-control" placeholder="Mã trạm"
                                            path="cellInfo.code"
                                       value="${cellNewForm.cellInfo.code}"/>  
                            </div>
                        </div>  
                        <div class="form-group">
                            <div class="input-group">

                                <form:hidden path="cellInfo.status"
                                             value="${cellNewForm.cellInfo.status}"/>  
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
                                        <form:select  path="loaiNE.id" id="neTypeid" class="form-control"  >
                                            <form:option value="5">--- Cell 2G ---</form:option>
                                            <form:option value="6">--- Cell 3G ---</form:option>
                                            <form:option value="7">--- Cell 4G ---</form:option>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon" for="exampleInputEmail1">Mã BTS/NodeB/ENodeB</label>
                                        <input type="text" disabled class="form-control"
                                               value=${cellNewForm.btsCode} />                                     
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
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên chỗ quản lý</label>
                                        <form:input  type="text" class="form-control" path="cellInfo.tenNgQLy" placeholder="Tên người quản lý" 
                                                     value="${cellNewForm.cellInfo.tenNgQLy}"/>
                                    </div>                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                                                                    
                                        <label class=" input-group-addon" style="min-width:150px;">Hoàn cảnh ra đời</label>
                                        <form:input  type="text" class="form-control" placeholder="Hoàn cảnh ra đời " 
                                                     path="cellInfo.hoanCanhRaDoi" />
                                    </div>                                                                                                
                                </div>
                                <div class="form-group" class="form-control">                                              
                                    <div class="input-group">                                                                                    
                                        <label class=" input-group-addon" style="min-width:150px;">Ngày hoạt động</label>
                                        <form:input  class="form-control date_form" id="ngayHoatDong" 
                                                     path="cellInfo.ngayHoatDong" />                    
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


                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"OMC_INFO")}'>   style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin trên OMC</h3>
                                </div>
                                <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"OMC_INFO")}'> disableGroup="disable" </c:if>>        
                                    <div class="form-group">
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Tên trên hệ thống</label>
                                        <form:input  class="form-control" placeholder="Tên trên hệ thống" name="temTrenHeThong" 
                                                     path="omcCellInfo.tenTrenHeThong"/>
                                    </div>                                                                                                
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">LAC</label>
                                        <form:input  class="form-control" placeholder="LAC" name="lac" type="number"
                                                     path="omcCellInfo.lac"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">CI</label>
                                        <form:input class="form-control" placeholder="CI" name="ci" type="number"
                                                    path="omcCellInfo.ci"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Frequency Band</label>
                                        
                                                          
                    <form:select name="bang_tan_id" path="omcCellInfo.bangTanId" class="form-control"  >
                                            <form:option value="">--- Băng tần ---</form:option>
                                            <form:options  items="${bangTanList}"  itemValue="bang_tan_id"  itemLabel="ten_bang_tan"/>                                                
                                        </form:select>  
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
                                        <form:select name="thietBiId" path="thietBi.thietBiId" id="thietBiId" class="form-control"  >
                                            <form:option value="">--- Chọn thiết bị ---</form:option>
                                            <form:options  items="${thietBiList}"  itemValue="thietBiId"  itemLabel="tenThietBi"/>                                                
                                        </form:select>  
                                    </div>
                                </div>
                                <div class="form-group" class="form-control">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                                        <form:select path="loaiTram.loaiTramId" class="form-control"  > 
                                            <form:option value="">--- Chọn loại trạm ---</form:option>
                                            <form:options  items="${tramList}"  itemValue="loaiTramId"  itemLabel="tenLoaiTram"/>                                               
                                        </form:select>     
                                    </div>
                                </div>                                    
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">No of carrier</label>
                                        <form:input  class="form-control" placeholder="No of carrier" 
                                                     type="number"  path="cellInfo.noOfCarrier"/>
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
                                        <form:radiobutton path="cellInfo.blackListFlag" value="1"/>Loại khỏi blacklist 
                                        <form:radiobutton path="cellInfo.blackListFlag" value="0"/>Đưa vào blacklist 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Ngày phê duyệt</label>
                                        <form:input  class="form-control"  id="ngayKiemDuyet"
                                                     path="cellInfo.ngayKiemDuyet"/>
                                    </div>
                                    <script>
                                        $(document).ready(function() {
                                            $('#ngayKiemDuyet').datepicker({
                                                format: 'mm/dd/yyyy',
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
                                                     path="cellInfo.lyDo" name="cellNewForm.cellInfo.lyDo"/>
                                    </div>
                                </div>                                    
                            </div>
                        </div>



                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"STATUS_INFO")}'>  style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin trạng thái</h3>
                                </div>
                                <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"STATUS_INFO")}'> disableGroup="disable" </c:if> >        
                                    <div class="form-group">
                                        <div class="input-group">                                                                            
                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Trạng thái hoạt động</label>
                                        <form:select path="cellInfo.trangThaiHDId" class="form-control"  > 
                                            <form:option value="">--- Chọn trạng thái hoạt động ---</form:option>
                                            <form:options  items="${trangThaiHDList}"  itemValue="id"  itemLabel="name"/>                                                                
                                        </form:select>     
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Trạng thái xử lý</label>
                                        <form:select path="cellInfo.trangThaiQLId" class="form-control"  > 
                                            <form:option value="">--- Chọn trạng thái quản lý ---</form:option>
                                            <form:options  items="${trangThaiQLList}"  itemValue="id"  itemLabel="name"/>                                                                
                                        </form:select>     
                                    </div>
                                </div>                                    
                            </div>
                        </div>


                        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"RF_INFO")}'>    style="display: none" </c:if>>
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin RF</h3>
                                </div>
                                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"RF_INFO")}'> disableGroup="disable" </c:if> >        
                                    <div class="form-group">
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Latitude</label>
                                        <form:input  class="form-control" 
                                                     path="cellInfo.lat" name="cellNewForm.cellInfo.lat"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">                                
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Longitude</label>
                                        <form:input  class="form-control" 
                                                     path="cellInfo.lon" name="cellNewForm.cellInfo.lon"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Azimuth</label>
                                        <form:input  class="form-control" type="number"
                                                     path="cellInfo.azimuth" name="cellNewForm.cellInfo.azimuth"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Mechanical Tilt</label>
                                        <form:input  class="form-control" type="number"
                                                     path="cellInfo.mechanitalTilt" name="cellNewForm.cellInfo.mechanitalTilt"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Total tilt</label>
                                        <form:input  class="form-control" type="number"
                                                     path="cellInfo.totalTilt" name="cellNewForm.cellInfo.totalTilt"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Antenna high</label>
                                        <form:input  class="form-control" type="number"
                                                     path="cellInfo.antennaHigh" name="cellNewForm.cellInfo.antennaHigh"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">   
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Antenna type</label>
                                        <form:input  class="form-control" type="number"
                                                     path="cellInfo.antennaType" name="cellNewForm.cellInfo.antennaType"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Antenna gain</label>
                                        <form:input  class="form-control" 
                                                     path="cellInfo.antennaGain" name="cellNewForm.cellInfo.antennaGain"/>
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
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<!--call ajax-->
<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<script type="text/javascript">
                                        $(document).ready(function() {

                                            $('#btn_building').click(function() {
                                                $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
                                            });
                                            $('#myModal iframe').on('load', function(e) {
                                                var iframe1 = $('#myModal iframe').contents();
                                                iframe1.find('#tinhTpId').prop('disabled', true);
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
                                            $('#btn_bts').click(function() {
                                                $("#myModalBTSCode iframe").prop({'src': '${pageContext.request.contextPath}/nodes/popup?neTypeId=' + $('#cellTypeId').val()});
                                            });
                                            $('#myModalBTSCode iframe').on('load', function(e) {
                                                var iframe1 = $('#myModalBTSCode iframe').contents();
                                                iframe1.find('#example1 tbody').on('click', 'tr', function() {
                                                    //alert($(this).text());
                                                    $('#btsCode').val($(this).find('input.node_id').val());
                                                    $('#btsCodeId').val($(this).find('input.code_id').val());
                                                });
                                                iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                                                    //alert($(this).text());
                                                    $('#btsCode').val($(this).find('input.node_id').val());
                                                    $('#btsCodeId').val($(this).find('input.code_id').val());
                                                    $('#myModalBTSCode').modal('hide');
                                                });
                                            });

                                            $('.panel-body').each(function() {
                                                if ($(this).attr('disableGroup') != undefined) {
                                                    $(this).find('input').attr('readonly', 'readonly');
                                                    $(this).find('select').attr('disabled', 'disabled');
                                                }
                                            });
                                        });
</script>
