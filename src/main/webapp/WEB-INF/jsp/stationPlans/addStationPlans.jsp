<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Thêm trạm dự án</h3>
                        </div>
                        <form:form method="post" action="add" commandName="tramDA">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <!--id, username, password, fullname, email, msisdn, status, is_admin,cp_id, create_date-->
                            <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin quản lý triển khai dự án</h3>
                                    </div>
                                    <div class="panel-body">        
                                        <div class="form-group">
                                            <div class="input-group">  
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Dự án</label>
                                                <input type="hidden" name="id" value="${tramDA.id}" id="id" required />
                                                <input required type="text"  class="form-control" id="maDA" value="${tramDA.maDuAn}" placeholder="Mã dự án" disabled  /> 
                                                <input type="hidden" name="duAnId" value="${tramDA.duAnId}" id="duAnId" required />
                                                <span class="input-group-btn">
                                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myModal" id="btn_tramDA" >Tìm dự án</button>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">  
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Trạm quy hoạch</label>
                                                <input type="text"  class="form-control" id="maQH" value="${tramDA.maQuyHoach}" placeholder="Trạm quy hoạch"  disabled /> 
                                                <input type="hidden" name="tramQHId" value="${tramDA.tramQHId}" id="tramQHId" required />
                                                <span class="input-group-btn">
                                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myModalTramQuyHoach" id="btn_node">Tìm trạm quy hoạch</button>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Mã hợp đồng</label>    
                                                <form:input
                                                    type="text" class="form-control" id="maSoHopDong" path="maSoHopDong"
                                                    placeholder="Mã hợp đồng" value="${tramDA.maSoHopDong}"/>
                                            </div>
                                        </div> 

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Tỉnh</label>  
                                                <select name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen(0);"> 
                                                    <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                                                    <c:forEach var="tinhBO" items="${tinhList}">
                                                        <option  
                                                            value="${tinhBO.tinhTpId}"  <c:choose>
                                                                <c:when test="${tinhBO.tinhTpId == tramDA.tinhTpId}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${tinhBO.tenTinhTp}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Quận/Huyện</label>  
                                                <select name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa(0);"> 
                                                    <option value="">---Chọn Quận / Huyện ---</option>
                                                </select> 
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Địa chỉ lắp đặt</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="address"
                                                    placeholder="Địa chỉ lắp đặt" value="${tramDA.address}"/>   
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Mã trạm dự án</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="maTramDuAn"
                                                    placeholder="Mã trạm dự án" value="${tramDA.maTramDuAn}"/> 
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Tên trạm dự án</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="tenTramDuAn"
                                                    placeholder="Tên trạm dự án" value="${tramDA.tenTramDuAn}"/>  
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Mã trạm BTS</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="maTramBTS"
                                                    placeholder="Mã trạm BTS" value="${tramDA.maTramBTS}"/> 
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Mã trạm NodeB</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="maTramNodeB"
                                                    placeholder="Mã trạm NodeB" value="${tramDA.maTramNodeB}"/>  
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Mã trạm quy hoạch</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="maTramQuyHoach"
                                                    placeholder="Mã trạm quy hoạch" value="${tramDA.maTramQuyHoach}"/>    
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Hiện trạng trạm</label>    
                                                <select name="hienTrangTram" id="hienTrangTram" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmHienTrangTram" items="${dmHienTrangTramList}">
                                                        <option  
                                                            value="${dmHienTrangTram.id}"  <c:choose>
                                                                <c:when test="${dmHienTrangTram.id == tramDA.hienTrangTram}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmHienTrangTram.name}</option>
                                                    </c:forEach>
                                                </select>   
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Longitude</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="longitude"
                                                    placeholder="Longitude" value="${tramDA.longitude}"/>  
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Latitude</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="latitude"
                                                    placeholder="Latitude" value="${tramDA.latitude}"/> 
                                            </div>
                                        </div> 
                                            
                                    </div>
                                </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
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
                            <iframe id="duAnFrame" width="100%" height="450" frameborder="0"></iframe>
                        </div>
                        <div class="modal-footer">

                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>                 
        </div>
        
        <div class="modal fade" id="myModalTramQuyHoach" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Tìm Kiếm</span></h4>
                    </div>
                    <div class="modal-body">
                        <div class="box-body table-responsive">
                            <iframe id="tramQhFrame" width="100%" height="450" frameborder="0"></iframe>
                        </div>
                        <div class="modal-footer">

                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>                 
        </div>

        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        
        <script>
            function getListHuyen(tinh)
            {
                var id = $("#tinhTpId").val();
                if (tinh != 0)
                    id = tinh;
                $.get("${pageContext.request.contextPath}/dv/getHuyen/" + id, function (data) {
                    var html = '<option value="" >--- Quận / Huyện ---</option>';
                    if (data.length > 0) {
                        data.forEach(function (entry) {
                            var htmlx = '<option value="' + entry.quanHuyenId + '">' + entry.tenQuanHuyen + '</option>';
                            html += htmlx;

                        });

                    }
                    ;
                    $('#quanHuyenId').html(html);
                    if (tinh != 0)
                        $('#quanHuyenId').val(${quanHuyenId});
                });
            }

            function getListPhuongXa(huyen)
            {

                var id = $("#quanHuyenId").val();
                if (huyen != 0)
                    id = huyen;
                $.get("${pageContext.request.contextPath}/dv/getPhuong/" + id, function (data) {
                    var html = '<option value="">--- Phường / Xã ---</option>';
                    if (data.length > 0) {
                        data.forEach(function (entry) {
                            var htmlx = '<option value="' + entry.phuongXaId + '">' + entry.tenPhuongXa + '</option>';
                            html += htmlx;
                        });
                    }
                    ;
                    $('#phuongXaId').html(html);
                    if (huyen != 0)
                        $('#phuongXaId').val(${phuongXaId});
                });
            }

            $(document).ready(function () {
                //$('.navbar-btn').click();
                var tinhId = $("#tinhTpId").val();
                if (tinhId != '')
                {
                    getListHuyen(${tinhTpId});
                    getListPhuongXa(${quanHuyenId});
                }
                $('#example1 tbody').on('click', 'tr', function () {
                    if ($(this).hasClass('selected')) {
                        //$(this).removeClass('selected');
                    } else {
                        $('#example1 tbody').find('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                    var node_id = $(this).find('.node_id').val();
                    var type_id = $(this).find('.type_id').val();
                    viewDetail(node_id, type_id);

                });
                
                $('#btn_tramDA').click(function () {
                    $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/stationPlans/popupDuAn'});
                });
                $('#btn_node').click(function () {
                    $("#myModalTramQuyHoach iframe").prop({'src': '${pageContext.request.contextPath}/stationPlans/popupTramKH'});
                });
                
                $('#myModal iframe').on('load', function (e) {
                    var iframe1 = $('#myModal iframe').contents();
                    iframe1.find('#example1 tbody').on('click', 'tr', function () {
                        //alert($(this).text());
                        $('#maDA').val($(this).find('input.du_an_code').val());
                        $('#duAnId').val($(this).find('input.du_an_id').val());
                    });
                    iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                        //alert($(this).text());
                        $('#maDA').val($(this).find('input.du_an_code').val());
                        $('#duAnId').val($(this).find('input.du_an_id').val());
                        $('#myModal').modal('hide');
                    });
                });
                
                $('#myModalTramQuyHoach iframe').on('load', function (e) {
                    var iframe1 = $('#myModalTramQuyHoach iframe').contents();
                    iframe1.find('#example1 tbody').on('click', 'tr', function () {
                        //alert($(this).text());
                        $('#maQH').val($(this).find('input.tram_qh_code').val());
                        $('#tramQHId').val($(this).find('input.tram_qh_id').val());
                    });
                    iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                        //alert($(this).text());
                        $('#maQH').val($(this).find('input.tram_qh_code').val());
                        $('#tramQHId').val($(this).find('input.tram_qh_id').val());
                        $('#myModalTramQuyHoach').modal('hide');
                    });
                });
                
            });
        </script>
    </body>
</html>

