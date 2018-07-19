<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="box-body" id="mydiv" >                                    
    <form:form method="post" action="${pageContext.request.contextPath}/cells/updateCell3g" commandName="cellNewForm">
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
        <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>

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
                <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"AREA")}'> disableGroup="disable" </c:if>>        
                    <div class="form-group">
                        <div class="input-group">                                
                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn kiểu Cell</label>
                        <form:select  path="neTypeId" id="neTypeid" class="form-control"  >
                            <form:option value="6">CELL3G</form:option>                                            
                        </form:select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" for="exampleInputEmail1">Mã NodeB</label>
                        <input type="text" disabled class="form-control"
                               value=${cellNewForm.maNodeCha} />                                     
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


                        <form:select name="bang_tan_id" path="bangTanId" class="form-control"  >
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
                            <form:options  items="${tramList}"  itemValue="loaiTramId"  itemLabel="tenLoaiTram"/>                                               
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


        <!--
        <div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"STATUS_INFO")}'>  style="display: none" </c:if>>
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
        </div>
        -->

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
                                <c:if test='${fn:contains(nhomcellId,tinhBO.id)}' >   <option 
                                        value="${tinhBO.id}"  
                                        >${tinhBO.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>                                  
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">CELL_TYPE</label>
                        <form:input  class="form-control" type="number"
                                     path="cellType" name="cellNewForm.cellType"/>
                    </div>                                                                                                
                </div>
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
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">ANTENNA_MODEL</label>
                        <form:input  class="form-control" 
                                     path="antennaModel" name="cellNewForm.antennaModel"/>
                    </div>                                                                                                
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">ANTENNA_PATTERN</label>
                        <form:input  class="form-control" 
                                     path="antennaPattern" name="cellNewForm.antennaPattern"/>
                    </div>                                                                                                
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">NO_OF_CARRIER</label>
                        <form:input  class="form-control"  type="number"
                                     path="noOfCarrier" name="cellNewForm.noOfCarrier"/>
                    </div>                                                                                                
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">BOSTER_TMA</label>
                        <form:input  class="form-control" 
                                     path="bosterTma" name="cellNewForm.bosterTma"/>
                    </div>                                                                                                
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">SPECIAL_COVERAGE</label>
                        <form:input  class="form-control" 
                                     path="specialCoverage" name="cellNewForm.specialCoverage"/>
                    </div>                                                                                                
                </div>

                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">DL_PSC</label>
                        <form:input  class="form-control" 
                                     path="dlPsc" name="cellNewForm.dlPsc"/>
                    </div>                                                                                                
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">CPICH_POWER</label>
                        <form:input  class="form-control" 
                                     path="cpichPower" name="cellNewForm.cpichPower"/>
                    </div>                                                                                                
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">TOTAL_POWER</label>
                        <form:input  class="form-control" 
                                     path="totalPower" name="cellNewForm.totalPower"/>
                    </div>                                                                                                
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">MAX_POWER</label>
                        <form:input  class="form-control" 
                                     path="maxPower" name="cellNewForm.maxPower"/>
                    </div>                                                                                                
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">TEN_NG_QLY</label>
                        <form:input  class="form-control" 
                                     path="tenNgQLy" name="cellNewForm.tenNgQLy"/>
                    </div>                                                                                                
                </div>
                <div class="form-group">
                    <div class="input-group">  
                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">SDT_NG_QLY</label>
                        <form:input  class="form-control" 
                                     path="SDTQLy" name="cellNewForm.SDTQLy"/>
                    </div>                                                                                                
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