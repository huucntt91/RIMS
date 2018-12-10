<%-- 
    Document   : edit thong tin chung cua tram quy hoạch
    Created on : Dec 21, 2016, 2:36:54 PM
    Author     : Cyano
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="box-body">                    


    <!-- CSHT -->

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Thông tin chung cơ sở hạ tầng</h3>
        </div>
        <div class="panel-body">    
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Mã CSHT</label>
                        <input type="text" class="form-control"  value="${m.code}" disabled="true"/>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Tên CSHT</label>
                        <input type="text" class="form-control"  value="${m.name}" disabled="true" />
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Mã quy hoạch vị trí</label>
                        <input type="text" class="form-control" value="${m.planningCode}" disabled="true" />
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group" >   
                    <div class="input-group">
                        <label class=" input-group-addon" >Đơn vị</label>
                        <select class="form-control"  >
                            <option value="">${m.donViName}</option>
                        </select>  
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group" > 
                    <div class="input-group">
                        <label class=" input-group-addon" >Tỉnh</label>
                        <select  class="form-control"  >
                            <option value="${m.tinhTpId}" selected    >${m.tinhName}</option>
                        </select>  
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group" >          
                    <div class="input-group">
                        <label class=" input-group-addon" >Quận/Huyện</label>                                    
                        <select  class="form-control"  disabled="true"> 
                            <option value="${m.quanHuyenId}">${m.quanName}</option>
                        </select>
                    </div> 
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group" >
                    <div class="input-group">
                        <label class=" input-group-addon" >Phường/Xã</label>
                        <select  class="form-control" > 
                            <option value="${m.phuongXaId}">${m.phuongName}</option>
                        </select>
                    </div>    
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Địa chỉ</label>
                        <input type="text" class="form-control"   value="${m.address}" disabled="true" />
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Longitude</label>
                        <input type="text" class="form-control"  value="${m.lon}" disabled="true" />
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Latitude</label>
                        <input type="text" class="form-control"  value="${m.lat}" disabled="true" />
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Thông tin kiểm định</h3>
        </div>
        <div class="panel-body">
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Ngày hoạt động CSHT</label>
                        <input type="text" class="form-control"   value="${m.ngayHdCsht}" disabled="true" />
                    </div>
                </div>
            </div>
            
             <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Mã kiểm định</label>
                        <input type="text" class="form-control"    value="${m.ACCREDITATION_CODE}" disabled="true" />
                    </div>
                </div>
            </div>
            
                    <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >ngày kiểm định hiệu lực</label>
                        <input type="text" class="form-control"    value="${m.ACCRE_START_DATE}" disabled="true" />
                    </div>
                </div>
            </div>
                    
                    <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >ngày kiểm định hết hiệu lực</label>
                        <input type="text" class="form-control"    value="${m.ACCRE_END_DATE}" disabled="true" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
 
    });

    


</script>