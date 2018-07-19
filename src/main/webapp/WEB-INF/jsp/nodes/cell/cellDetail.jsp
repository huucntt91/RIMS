<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<div class="box-body" id="mydiv"  >                                    
    <form:form method="post" action="${pageContext.request.contextPath}/cell/update" commandName="cellNewForm">
        <form:hidden path="cellInfo.id" value="${cellNewForm.cellInfo.nodeId}"/>
        <form:hidden path="cellInfo.neTypeId" value="${cellNewForm.cellInfo.neTypeId}"/>
        <div class="form-group">
            <label  for="exampleInputEmail1">Mã Cell</label>
            <form:input readonly="true" class="form-control" placeholder="Mã trạm"
                        path="cellInfo.code" id="code"
                        value="${cellNewForm.cellInfo.code}"/>  

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

        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"AREA")}'>    style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin khu vực</h3>
                </div>
                <div class="panel-body" >        
                    <div class="form-group">

                        <label>Chọn kiểu Cell</label>
                    <form:select  path="loaiNE.id" id="neTypeid" class="form-control"  >
                        <form:option value="5">CELL2G</form:option>
                        <form:option value="6">CELL3G</form:option>
                        <form:option value="7">CELL4G</form:option>
                    </form:select>

                </div>

                <div class="form-group">

                    <label >Mã BTS/NodeB/ENodeB</label>
                    <input type="text" disabled class="form-control"
                           value=${cellNewForm.btsCode} />                                     

                </div>
            </div>
        </div>


        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"BIRTH_INFO")}'>    style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin khai sinh</h3>
                </div>
                <!--disableGroup="disable"-->
                <div class="panel-body">        
                    <div class="form-group">

                        <label>Tên chỗ quản lý</label>
                    <form:input  type="text" class="form-control" path="cellInfo.tenNgQLy" placeholder="Tên người quản lý" 
                                 value="${cellNewForm.cellInfo.tenNgQLy}"/>

                </div>
                <div class="form-group">                    
                    <label>Hoàn cảnh ra đời</label>
                    <form:input  type="text" class="form-control" placeholder="Hoàn cảnh ra đời " 
                                 path="cellInfo.hoanCanhRaDoi" />                    
                </div>
                <div class="form-group" class="form-control">                                                                  
                    <label>Ngày hoạt động</label>
                    <form:input  class="form-control date_form" id="ngayHoatDong" 
                                 path="cellInfo.ngayHoatDong" />                                                                                                                                    

                </div> 

            </div>
        </div>


        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"OMC_INFO")}'>   style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin trên OMC</h3>
                </div>
                <div class="panel-body">        
                    <div class="form-group">                        
                        <label>Tên trên hệ thống</label>
                    <form:input  class="form-control" placeholder="Tên trên hệ thống" name="temTrenHeThong" 
                                 path="omcCellInfo.tenTrenHeThong"/>                    
                </div>
                <div class="form-group">                    
                    <label>LAC</label>
                    <form:input  class="form-control" placeholder="LAC" name="lac" type="number"
                                 path="omcCellInfo.lac"/>
                </div>
                <div class="form-group">                    
                    <label>CI</label>
                    <form:input class="form-control" placeholder="CI" name="ci" type="number"
                                path="omcCellInfo.ci"/>
                </div>
                <div class="form-group">                    
                    <label>Frequency Band</label>
                                      
                    <form:select name="bang_tan_id" path="omcCellInfo.bangTanId" class="form-control"  >
                                            <form:option value="">--- Băng tần ---</form:option>
                                            <form:options  items="${bangTanList}"  itemValue="bang_tan_id"  itemLabel="ten_bang_tan"/>                                                
                                        </form:select>  
                </div>
            </div>
        </div>


        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"CONFIG_INFO")}' >  style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin cấu hình</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">                        
                        <label>Chọn thiết bị</label>
                    <form:select name="thietBiId" path="thietBi.thietBiId" id="thietBiId" class="form-control"  >
                        <form:option value="">--- Chọn thiết bị ---</form:option>
                        <form:options  items="${thietBiList}"  itemValue="thietBiId"  itemLabel="tenThietBi"/>                                                
                    </form:select>  
                </div>                
                <div class="form-group" class="form-control">                    
                    <label>Loại Trạm</label>
                    <form:select path="loaiTram.loaiTramId" class="form-control"  > 
                        <form:option value="">--- Chọn loại trạm ---</form:option>
                        <form:options  items="${tramList}"  itemValue="loaiTramId"  itemLabel="tenLoaiTram"/>                                               
                    </form:select>                         
                </div>                                    
                <div class="form-group">                         
                    <label>No of carrier</label>
                    <form:input  class="form-control" placeholder="No of carrier" 
                                 type="number"  path="cellInfo.noOfCarrier"/>
                </div>
            </div>
        </div>


        <!--////////////////////////-->

        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"QUANTITY_INFO")}'>    style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin Chất lượng</h3>
                </div>
                <div class="panel-body">        
                    <div class="form-group">                                                                         
                    <form:radiobutton path="cellInfo.blackListFlag" value="1"/>Loại khỏi blacklist 
                    <form:radiobutton path="cellInfo.blackListFlag" value="0"/>Đưa vào blacklist 
                </div>
                <div class="form-group">                    
                    <label>Ngày phê duyệt</label>
                    <form:input  class="form-control"  id="ngayKiemDuyet"
                                 path="cellInfo.ngayKiemDuyet"/>                    

                </div>
                <div class="form-group">                     
                    <label>Lý do</label>
                    <form:input  class="form-control" 
                                 path="cellInfo.lyDo" name="cellNewForm.cellInfo.lyDo"/>                    
                </div>                                    
            </div>
        </div>



        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"STATUS_INFO")}'>  style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin trạng thái</h3>
                </div>
                <div class="panel-body"  >        
                    <div class="form-group">                                                                         
                        <label>Trạng thái hoạt động</label>
                    <form:select path="cellInfo.trangThaiHDId" class="form-control"  > 
                        <form:option value="">--- Chọn trạng thái hoạt động ---</form:option>
                        <form:options  items="${trangThaiHDList}"  itemValue="id"  itemLabel="name"/>                                                                
                    </form:select>     
                </div>
                <div class="form-group">                         
                    <label>Trạng thái xử lý</label>
                    <form:select path="cellInfo.trangThaiQLId" class="form-control"  > 
                        <form:option value="">--- Chọn trạng thái quản lý ---</form:option>
                        <form:options  items="${trangThaiQLList}"  itemValue="id"  itemLabel="name"/>                                                                
                    </form:select>  
                </div>                                    
            </div>
        </div>


        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"RF_INFO")}'>    style="display: none" </c:if>>
                <div class="panel-heading">
                    <h3 class="panel-title">Thông tin RF</h3>
                </div>
                <div class="panel-body" >        
                    <div class="form-group">                              
                        <label>Latitude</label>
                    <form:input  class="form-control" 
                                 path="cellInfo.lat" name="cellNewForm.cellInfo.lat"/>                    
                </div>
                <div class="form-group">

                    <label>Longitude</label>
                    <form:input  class="form-control" 
                                 path="cellInfo.lon" name="cellNewForm.cellInfo.lon"/>                    
                </div>
                <div class="form-group">
                    <label>Azimuth</label>
                    <form:input  class="form-control" type="number"
                                 path="cellInfo.azimuth" name="cellNewForm.cellInfo.azimuth"/>                    
                </div>
                <div class="form-group">                   
                    <label>Mechanical Tilt</label>
                    <form:input  class="form-control" type="number"
                                 path="cellInfo.mechanitalTilt" name="cellNewForm.cellInfo.mechanitalTilt"/>                    
                </div>
                <div class="form-group">
                    <label>Total tilt</label>
                    <form:input  class="form-control" type="number"
                                 path="cellInfo.totalTilt" name="cellNewForm.cellInfo.totalTilt"/>

                </div>
                <div class="form-group">

                    <label>Antenna high</label>
                    <form:input  class="form-control" type="number"
                                 path="cellInfo.antennaHigh" name="cellNewForm.cellInfo.antennaHigh"/>

                </div>
                <div class="form-group">

                    <label>Antenna type</label>
                    <form:input  class="form-control" type="number"
                                 path="cellInfo.antennaType" name="cellNewForm.cellInfo.antennaType"/>

                </div>
                <div class="form-group">                    
                    <label>Antenna gain</label>
                    <form:input readonly="true" class="form-control" type="number"
                                path="cellInfo.antennaGain" name="cellNewForm.cellInfo.antennaGain"/>                    
                </div>

            </div>
        </div>       
    </form:form>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $('#btPM').click(function () {
            var tenNeType = $('#neTypeid :selected').text();
            var vnpCode = $('#code').val();
            window.open('${pageContext.request.contextPath}/pm_fm/popup?vnpCode=' + vnpCode + '&nodeType=' + tenNeType,'_blank','width=700,height=500');
        });
        $('#btFM').click(function () {
            var tenNeType = $('#neTypeid :selected').text();
            var vnpCode = $('#code').val();
            window.open('${pageContext.request.contextPath}/pm_fm/popup_fm?vnpCode=' + vnpCode + '&nodeType=' + tenNeType,'_blank','width=700,height=500');
        });
        $('.panel-body').each(function () {
            $(this).find('input').attr('disabled', 'disabled');
            $(this).find('select').attr('disabled', 'disabled');
        });

    });
</script>