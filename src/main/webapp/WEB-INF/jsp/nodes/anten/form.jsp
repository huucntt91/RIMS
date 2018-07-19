<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Quản lý Info Anten</h3>
                </div>
                <form:form method="post" action="update" commandName="model">

                    <div class="box-body" id="mydiv" >                        
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Mã node</label>
                                <input  type="text" class="form-control " required
                                        value="${model.code}"  id="code" name="code"/>
                                <form:hidden path="id" title="${item.id}"></form:hidden>
                                </div>
                            </div>
                            <div class="form-group" class="form-control">
                                <div class="input-group">  
                                    <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên Anten</label>
                                    <input  type="text" class="form-control " required
                                            value="${model.name}"  id="name" name="name"/>                    
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">                                
                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Đơn vị</label>
                                <form:select name="donViId" path="donViId" class="form-control"  required="true">
                                    <form:option value="">--- Đơn vị ---</form:option>
                                    <form:options  items="${dvList}"  itemValue="donViId"  itemLabel="tenDonVi"/>                                                
                                </form:select>  
                            </div>                                                                                                
                        </div>

                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập mã building</label>
                                <input required="true" type="text"  value="${model.codeBuilding}"  class="form-control" id="codeBuilding" placeholder="Mã building" disabled  /> 
                                <input required="true" type="hidden" name="buildingId" value="${model.buildingId}" id="buildingId"  />
                                <span class="input-group-btn">
                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm building</button>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">                                    
                                <label class=" input-group-addon" style="min-width:150px;">Gắn tới Cell</label>
                                <select multiple="multiple" name="node2Id" id="node2Id" class="form-control" required="true">
                                    <c:forEach var="nodeBO" items="${listCell}">
                                        <option 
                                            <c:if test='${fn:contains(node2Id,nodeBO.id)}' >  selected="selected" </c:if>
                                            value="${nodeBO.id}"  
                                            >${nodeBO.code}</option>
                                    </c:forEach>
                                </select>                                  
                            </div>

                        </div>         


                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" style="min-width:150px;">Loại Ănten</label>
                                <select name="loaiAntenId" id="loaiAntenId" class="form-control" >
                                    <option value="">--- Chọn loại Ăn ten---</option>
                                    <c:forEach var="temp" items="${loaiantenList}">
                                        <option  
                                            <c:if test='${model.loaiAntenId == temp.id}' >  selected="selected" </c:if>
                                            value="${temp.id}"

                                            >${temp.name}</option>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" style="min-width:150px;">Băng tần</label>
                                <select name="bangTanId" id="bangTanId" class="form-control" >
                                    <option value="">--- Chọn loại băng tần---</option>
                                    <c:forEach var="temp" items="${bangtanList}">
                                        <option  
                                            <c:if test='${model.bangTanId == temp.bang_tan_id}' >  selected="selected" </c:if>
                                            value="${temp.bang_tan_id}"

                                            >${temp.ten_bang_tan}</option>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <label class="input-group-addon" style="min-width:150px;">Cấu hình Port</label>
                                <select name="portId" id="portId" class="form-control"  >
                                    <option value="">--- Chọn loại Port---</option>
                                    <c:forEach var="temp" items="${portList}">
                                        <option  
                                            <c:if test='${model.portId == temp.CAU_HINH_PORT_ID}' >  selected="selected" </c:if>
                                            value="${temp.CAU_HINH_PORT_ID}"

                                            >${temp.CAU_HINH_PORT}</option>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>        

                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Hãng sản xuất</label>
                                <input  type="text" class="form-control " 
                                        value="${model.hangSX}"  id="name" name="hangSX"/>                    
                            </div>
                        </div>
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Trọng lượng</label>
                                <input  type="number" class="form-control " 
                                        value="${model.trongLuong}"  id="trongLuong" name="trongLuong"/>                    
                            </div>
                        </div>

                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Kích cỡ</label>
                                <input  type="text" class="form-control " 
                                        value="${model.kichCo}"  id="trongLuong" name="kichCo"/>                    
                            </div>
                        </div>
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Độ cao lắp đặt</label>
                                <input  type="number" class="form-control " 
                                        value="${model.doCao}"  id="doCao" name="doCao"/>                    
                            </div>
                        </div>
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Cấu hính gain</label>
                                <input  type="text" class="form-control " 
                                        value="${model.gain}"  id="gain" name="gain"/>                    
                            </div>
                        </div>
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Cấu hình beam width</label>
                                <input  type="text" class="form-control " 
                                        value="${model.beamWidth}"  id="trongLuong" name="beamWidth"/>                    
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

<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<%@include file="../../include/footer.jsp"%>


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


<script>
    $(document).ready(function () {
        getListCellBuilding($('#buildingId').val());
        $('#btn_building').click(function () {
            $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
        });
        $('#node2Id').multiselect(({
            maxHeight: 200,
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
        $('#myBuilding iframe').on('load', function (e) {
            var iframe1 = $('#myBuilding iframe').contents();
            iframe1.find('#example1 tbody').on('click', 'tr', function () {
                //alert($(this).text());
                $('#codeBuilding').val($(this).find('input.txtcode').val());
                $('#buildingId').val($(this).find('input.txtid').val());
                getListCellBuilding($('#buildingId').val());
            });
            iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                //alert($(this).text());
                $('#codeBuilding').val($(this).find('input.txtcode').val());
                $('#buildingId').val($(this).find('input.txtid').val());
                $('#myBuilding').modal('hide');
                getListCellBuilding($('#buildingId').val());
            });
        });
    });

    function getListCellBuilding(buildingId)
    {
        $.get("${pageContext.request.contextPath}/anteninfo/getCell/" + buildingId, function (data) {
            var html = '';
            if (data.length > 0) {
                data.forEach(function (entry) {
                    var htmlx = '<option value="' + entry.id + '">' + entry.code + '</option>';
                    html += htmlx;
                });
            }
            $('#node2Id').html(html);
            $('#node2Id').multiselect('rebuild');
        });
    }

</script>
