<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="../../include/header.jsp"%>
<div class="content-header">
    <h1>Khai báo Trạm BTS/NodeB/eNodeB</h1>
    <ol class="breadcrumb">
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'cellExcelImport/init/2')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/cellExcelImport/init/2'" >
                <span>  <img width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Thêm BTS/NODEB/ENODEB với Excel</span>
            </button>
        </c:if>
        <!--<c:if test="${fn:containsIgnoreCase(sessionScope.function, 'excelDeleteNode/init/2')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/excelDeleteNode/init/2'" >
                <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Xóa BTS/NODEB/ENODEB với Excel</span> 
            </button>
        </c:if>-->
    </ol>
</div>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <!--                <div class="box-header">
                                    <h3 class="box-title">Khai báo Trạm BTS/NodeB/eNodeB </h3>
                                </div> -->
                <form:form method="post" action="${pageContext.request.contextPath}/nodes/postaddtram" commandName="model">

                    <div class="box-body" id="mydiv" >

                        <div class="form-group" class="form-control">
                            <div class="input-group">           
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn loại NE</label>
                                <select name="neTypeId" id="neTypeId" class="form-control" required onchange="changeType()" > 
                                    <option value="">--- Chọn loại NE ---</option>
                                    <c:forEach var="item" items="${neList}">
                                        <c:if test="${item.id == 2 || item.id == 3 || item.id == 8}" >
                                            <option   <c:if test="${item.id == model.neTypeId}">  selected  </c:if>
                                                                                                  value="${item.id}"  >${item.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>

                                <input type="hidden" value="${model.id}" name="id" />
                            </div>
                        </div>

                        <div class="form-group" id="bsc_group" >
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập mã BSC/RNC</label>
                                <input type="text" value="${model.tenNodeCha}"  class="form-control" id="code" placeholder="Mã BSC/RNC"  disabled /> 
                                <input type="hidden" name="nodeChaId"  value="${model.nodeChaId}"  id="nodeChaId"  />
                                <span class="input-group-btn">
                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myModalBTSCode" id="btn_node">Tìm BSC/RNC</button>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập mã trạm dự án</label>
                                <input required type="text" value="${model.codeTramDA}"  class="form-control" id="codeTramDA" placeholder="Mã trạm dự án" disabled  /> 
                                <input type="hidden" name="tramDAId" value="${model.tramDAId}" id="tramDAId" />
                                <span class="input-group-btn">
                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myModal" id="btn_tramDA" >Tìm trạm dự án</button>
                                </span>
                            </div>
                        </div>

                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin khu vực</h3>
                            </div>

                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Đơn vị quản lý </label>
                                        <select name="donViId" id="donViId" class="form-control" required> >
                                            <option value="">--- Chọn Đơn Vị ---</option>
                                            <c:forEach var="dvBO" items="${dvList}">

                                                <option 
                                                    <c:if test="${dvBO.donViId == model.donViId}">  selected  </c:if>
                                                    value="${dvBO.donViId}"

                                                    >${dvBO.tenDonVi}</option>
                                            </c:forEach>

                                        </select>  
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập mã CSHT</label>
                                        <input required type="text" value="${model.codeBuilding}"  class="form-control" id="codeBuilding" placeholder="Mã CSHT" disabled  /> 
                                        <input type="hidden" name="buildingId" value="${model.buildingId}" id="buildingId" />
                                        <span class="input-group-btn">
                                            <button  class="btn btn-success" data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm CSHT</button>
                                        </span>
                                    </div>
                                </div>

                            </div>

                        </div>

                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin khai sinh</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group" class="form-control">
                                    <div class="input-group">       
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày hoạt động</label>
                                        <input required type="text" class="form-control date_form" id="datecreate" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${model.ngayHoatDong}" />" name="ngayHoatDong" placeholder="Ngày hoạt động"  />                    
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">      
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Hoàn cảnh ra đời </label>
                                        <input required type="text" class="form-control" value="${model.hoanCanhRaDoi}" name="hoanCanhRaDoi" placeholder="Hoàn cảnh ra đời"  />
                                    </div>
                                </div> 
                                <div class="form-group" class="form-control">
                                    <div class="input-group">      
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên người quản lý</label>
                                        <input type="text" class="form-control" value="${model.tenNgQLy}" name="tenNgQLy" placeholder="Tên người quản lý"  />                    
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">      
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên cho quản lý</label>                                    

                                        <input required type="text" class="form-control" value="${model.name}" name="name" placeholder="Tên cho quản lý"  />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin cấu hình</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="input-group">      
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên trên hệ thống</label>                                    
                                        <input type="text" required="true" class="form-control" value="${model.tenTrenHeThong}" name="tenTrenHeThong" placeholder="Tên trên hệ thống"  />
                                    </div>
                                </div>
                                <div class="form-group">

                                    <div class="input-group">      
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn thiết bị </label>
                                        <select name="thietBiId" id="thietBiId" class="form-control" required >
                                            <option value="">--- Chọn thiết bị ---</option>
                                            <c:forEach var="item" items="${thietBiList}">
                                                <option  
                                                    <c:if test="${item.thietBiId == model.thietBiId}">  selected  </c:if>
                                                    value="${item.thietBiId}">${item.tenThietBi}</option>
                                            </c:forEach>

                                        </select>  
                                    </div>
                                </div> 

                                <div class="form-group" class="form-control">
                                    <div class="input-group">      
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                                        <select name="loaiTramId" id="loaiTramId" class="form-control" required > 
                                            <option value="">--- Chọn loại trạm ---</option>
                                            <c:forEach var="item" items="${tramList}">
                                                <c:choose>
                                                    <c:when test="${item.neTypeId == 2}">  
                                                        <option  
                                                            <c:if test="${item.loaiTramId == model.loaiTramId}">  selected  </c:if>
                                                            value="${item.loaiTramId}">${item.tenLoaiTram}</option>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">      
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Cấu hình </label>
<!--                                        <input required type="text" class="form-control" value="${model.cauHinh}" name="cauHinh" placeholder="Cấu hình"  />-->
                                        <select name="cauHinhPortId" id="cauHinhPortId" class="form-control" required> >
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
                                <div class="form-group" id="enodebIdGroup" style="display: none">
                                    <div class="input-group">      
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">eNodeB ID</label>                                    
                                        <input type="text" class="form-control" value="${model.enodebId}" name="enodebId" placeholder="eNodeB ID"  />
                                    </div>
                                </div>        
                            </div>
                        </div>


                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary">Khai báo</button>
                    </div>

                </form:form>
            </div>
        </div>
    </div>           
</section>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
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

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<script>
                                    $(document).ready(function () {
                                        changeType();
                                        $('#datecreate').datepicker({
                                            format: 'dd/mm/yyyy',
                                            todayHighlight: true,
                                            autoclose: true
                                        });
                                        $('#btn_tramDA').click(function () {
                                            $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/nodes/popupTramDuAn'});
                                        });
                                        $('#btn_node').click(function () {
                                            $("#myModalBTSCode iframe").prop({'src': '${pageContext.request.contextPath}/nodes/popup?neTypeId=11'});
                                        });

                                        $('#btn_building').click(function () {
                                            $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
                                        });

                                        $('#myModal iframe').on('load', function (e) {
                                            var iframe1 = $('#myModal iframe').contents();
                                            iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                                //alert($(this).text());
                                                $('#codeTramDA').val($(this).find('input.tram_code').val());
                                                $('#tramDAId').val($(this).find('input.tram_id').val());
                                            });
                                            iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                                //alert($(this).text());
                                                $('#codeTramDA').val($(this).find('input.tram_code').val());
                                                $('#tramDAId').val($(this).find('input.tram_id').val());
                                                $('#myModal').modal('hide');
                                            });
                                        });

                                        $('#myModalBTSCode iframe').on('load', function (e) {
                                            var iframe1 = $('#myModalBTSCode iframe').contents();
                                            iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                                //alert($(this).text());
                                                $('#code').val($(this).find('input.tram_code').val());
                                                $('#nodeChaId').val($(this).find('input.node_id').val());
                                            });
                                            iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                                //alert($(this).text());
                                                $('#code').val($(this).find('input.code_id').val());
                                                $('#nodeChaId').val($(this).find('input.node_id').val());
                                                $('#myModalBTSCode').modal('hide');
                                            });
                                        });

                                        $('#myBuilding iframe').on('load', function (e) {
                                            var iframe1 = $('#myBuilding iframe').contents();
                                            iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                                //alert($(this).text());
                                                $('#codeBuilding').val($(this).find('input.txtcode').val());
                                                $('#buildingId').val($(this).find('input.txtid').val());
                                            });
                                            iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                                //alert($(this).text());
                                                $('#codeBuilding').val($(this).find('input.txtcode').val());
                                                $('#buildingId').val($(this).find('input.txtid').val());
                                                $('#myBuilding').modal('hide');
                                            });
                                        });


                                        //$('#ifram-value').val($('#myModal iframe').find('tr.selected').text());

                                    });

                                    function changeType() {
                                        if($('#neTypeId').val() == 8){
                                            $('#bsc_group').hide();
                                            $('#enodebIdGroup').show();
                                        } else {
                                            $('#bsc_group').show();
                                            $('#enodebIdGroup').hide();
                                        }
                                    }

</script>

