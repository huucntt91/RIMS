<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="box-body" id="mydiv" >
    <ul class="nav nav-tabs" role="tablist" id="myTab">
        <li class="active"><a href="#common" role="tab" data-toggle="tab">Thông tin chung</a></li>
        <li><a href="#info" role="tab" data-toggle="tab">Chi tiết </a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="common">
            <div class="form-group">
                <label for="exampleInputEmail1">Ne Type</label>
                <input readonly="true" class="form-control" type="text" name="neTypeId" value="${model.tenNeType}" />

                <div class="form-group">
                    <label for="exampleInputEmail1">Đơn vị </label>
                    <input readonly="true" class="form-control" type="text" name="donViId" value="${model.donViName}" />
                </div> 
                <div class="form-group">
                    <label for="exampleInputEmail1">Địa chỉ </label>
                    <input readonly="true" class="form-control" type="text" name="address" value="${model.address}" />
                </div> 

                <div class="form-group">
                    <label for="exampleInputEmail1">Vender </label>
                    <input readonly="true" class="form-control" type="text" name="thietBiId" value="${model.tenThietBi}" />
                </div> 

                <div class="form-group">

                    <label  for="exampleInputEmail1">Mã Building</label>

                    <input type="text" readonly="true" class="form-control" value="${model.codeBuilding}" /> 


                </div>

                <div class="form-group" class="form-control">
                    <label for="exampleInputEmail1">Loại Trạm</label>
                    <input  type="text" readonly="true" class="form-control"  value="${model.tenLoaiTram}" />                           
                </div>
                <div class="form-group" class="form-control">
                    <label for="exampleInputEmail1">Node cha</label>
                    <input  type="text" readonly="true" class="form-control"  value="${model.tenNodeCha}" />                          
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Latitude</label>                                    

                    <input readonly="true" type="number" step="any" class="form-control" name="lat"  value="${model.lat}" />
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Longitude</label>                                    

                    <input readonly="true" type="number" step="any" class="form-control" name="lon"  value="${model.lon}" />
                </div> 

            </div>
        </div>
        <div class="tab-pane" id="info">

            <div class="form-group">
                <label for="exampleInputEmail1">Tên Cell</label>                                    

                <input readonly="true" type="text" class="form-control" name="name" placeholder="${model.name}"  />
            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Tên người quản lý</label>
                <input readonly="true" type="text" class="form-control" name="tenNgQLy" value="${model.tenNgQLy}"  />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">SĐT quản lý</label>
                <input readonly="true" type="text" class="form-control" name="SDTQLy" value="${model.SDTQLy}"   />                    
            </div>  

            <div class="form-group">
                <label for="exampleInputEmail1">Hoàn cảnh ra đời </label>
                <input readonly="true" type="text" class="form-control" name="hoanCanhRaDoi" value="${model.hoanCanhRaDoi}" />
            </div> 

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày cấp phép</label>
                <input  readonly="true" type="text" class="form-control date_form" id="datecreate" name="ngayCapPhep" value="${model.ngayCapPhep}"  />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày đăng ký</label>
                <input readonly="true" type="text" class="form-control date_form" id="datecreate" name="ngayCapPhep" value="${model.ngayDangKy}"  />                    
            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày kiểm duyệt</label>
                <input readonly="true" type="text" class="form-control date_form" id="datecreate" name="ngayKiemDuyet" value="${model.ngayKiemDuyet}"  />                    
            </div> 
            <div class="form-group">
                <label for="exampleInputEmail1">Azimuth </label>
                <input readonly="true"  type="number" step="any" class="form-control" name="azimuth"  value="${model.azimuth}" />

            </div>      

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Mechanical Tilt</label>
                <input readonly="true"  type="number" step="any" class="form-control" name="mechanicalTilt"  value="${model.mechanitalTilt}" />

            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Total Tilt</label>
                <input readonly="true" type="number" step="any" class="form-control" name="totalTilt" value="${model.totalTilt}"  />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Antenna Type</label>
                <input readonly="true" type="number" step="any" class="form-control "  name="antennaType" value="${model.antennaType}"   />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Antenna High</label>
                <input readonly="true" type="text" class="form-control date_form"  name="antennaHigh" value="${model.antennaHigh}" />                    
            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Antenna Gain</label>
                <input readonly="true" type="number" step="any" class="form-control " name="antennaHigh" value="${model.antennaHigh}"  />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">No Of Carrier</label>
                <input readonly="true" type="number" step="any" class="form-control " id="datecreate" name="noOfCarrier" value="${model.noOfCarrier}"  />                    
            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Boster Tma</label>
                <input readonly="true" type="text" class="form-control " id="datecreate" name="bosterTma" value="${model.bosterTma}"  />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Cpich Power</label>
                <input readonly="true" type="text" class="form-control " id="datecreate" name="cpichPower" value="${model.cpichPower}"   />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Total Power</label>
                <input readonly="true" type="text" class="form-control " id="datecreate" name="totalPower" value="${model.totalPower}"  />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Special Coverage</label>
                <input readonly="true" type="text" class="form-control"  name="specialCoverage" value="${model.specialCoverage}" />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Black List Flag</label>
                <input readonly="true" type="text" class="form-control"  name="blackListFlag" value="${model.blackListFlag}" />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ly Do</label>
                <input readonly="true" type="text" class="form-control"  name="lyDo" value="${model.lyDo}"  />                    
            </div> 
        </div>
    </div>    
</div>
<!-- /.box-body -->



