<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                            <h3 class="box-title">Cập nhật trạm dự án</h3>
                        </div>
                        <form:form method="post" action="update" commandName="tramDA">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <%--<div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"COMMIT_DEVICE")}'>  style="display: none"  </c:if>>--%>
                                <div class="panel panel-primary" >
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin cam kết thiết bị</h3>
                                    </div>
                                    <%--<div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"COMMIT_DEVICE")}'> disableGroup="disable" </c:if>>--%>  
                                    <div class="panel-body">      
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">VNPT Net phê duyệt triển khai</label> 
                                                <input type="hidden" name="id" value="${tramDA.id}" id="id"  />
                                                <input type="hidden" name="trangThaiTram" value="${trangThaiTram}" id="trangThaiTram"  />
                                                <input type="hidden" name="duAnId" value="${tramDA.duAnId}" id="duAnId"  />
                                                <input type="hidden" name="tramQHId" value="${tramDA.tramQHId}" id="tramQHId"  />
                                                <select name="vnptNetPheDuyet" id="vnptNetPheDuyet" class="form-control" > 
                                                    <option value="">--- Chọn---</option>
                                                    <c:forEach var="dmOkNok" items="${dmOkNokList}">
                                                        <option  
                                                            value="${dmOkNok.id}"  <c:choose>
                                                                <c:when test="${dmOkNok.id == tramDA.vnptNetPheDuyet}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${dmOkNok.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Cấu hình thiết bị</label> 
                                                <%--<form:input--%>
                                                    <%--type="text" class="form-control" id="exampleInputEmail1" path="cauHinhThietBi"--%>
                                                    <%--placeholder="Cấu hình thiết bị" value="${tramDA.cauHinhThietBi}"/>--%>
                                                <select name="cauHinhThietBi" id="cauHinhThietBi" class="form-control" > 
                                                    <option value="">--- Chọn cấu hình thiết bị ---</option>
                                                    <c:forEach var="portBO" items="${portList}">
                                                        <option  
                                                            value="${portBO.CAU_HINH_PORT_ID}"  <c:choose>
                                                                <c:when test="${portBO.CAU_HINH_PORT_ID == tramDA.cauHinhThietBi}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${portBO.CAU_HINH_PORT}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Nguồn thiết bị</label> 
                                                <select name="nguonThietBi" id="nguonThietBi" class="form-control" > 
                                                    <option value="">--- Chọn nguồn thiết bị ---</option>
                                                    <c:forEach var="nguonThietBiBO" items="${dmNguonThietBiList}">
                                                        <option  
                                                            value="${nguonThietBiBO.id}"  <c:choose>
                                                                <c:when test="${nguonThietBiBO.id == tramDA.nguonThietBi}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${nguonThietBiBO.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Loại công nghệ</label> 
                                                <select name="loaiCongNghe" id="loaiCongNghe" class="form-control" > 
                                                    <option value="">--- Chọn Loại công nghệ ---</option>
                                                    <c:forEach var="loaiCongNgheBO" items="${dmLoaiCongNgheList}">
                                                        <option  
                                                            value="${loaiCongNgheBO.id}"  <c:choose>
                                                                <c:when test="${loaiCongNgheBO.id == tramDA.loaiCongNghe}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${loaiCongNgheBO.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Chủng loại thiết bị</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="chungLoaiThietBi"
                                                    placeholder="Chủng loại thiết bị" value="${tramDA.chungLoaiThietBi}"/>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Chủng loại Anten</label> 
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="chungLoaiAnten"
                                                    placeholder="Chủng loại Anten" value="${tramDA.chungLoaiAnten}"/>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Thời điểm cung cấp thiết bị</label> 
                                                <input  class="form-control"  id="ngayCungCapTb" name="ngayCungCapTb" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayCungCapTb}" />" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Thời điểm swap thiết bị</label> 
                                                <input  class="form-control"  id="ngaySwapTb" name="ngaySwapTb" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngaySwapTb}" />" />
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
                            <iframe width="100%" height="450" frameborder="0"></iframe>
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
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
        
        <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

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
                        $('#quanHuyenId').val(${tramDA.quanHuyenId});
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
                    getListHuyen(${tramDA.tinhTpId});
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
                
                $('.panel-body').each(function() {
                    if ($(this).attr('disableGroup') != undefined) {
                        $(this).find('input').attr('readonly', 'readonly');
                        $(this).find('select').attr('readonly', 'readonly');
                    }
                 });
                 
                $('#ngayCungCapTb').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 }); 
                 $('#ngaySwapTb').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });    
                
            });
            
            
        </script>

    </body>
</html>
