<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RIMS</title>
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
                            
                                <%--<div class="panel panel-primary" <c:if test='${!fn:contains(classAtrrView,"DEPLOY_STATUS")}'> style="display: none"  </c:if>>--%>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin triển khai dự án - QLHT</h3>
                                    </div>
                                    <%--<div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"DEPLOY_STATUS")}'> disableGroup="disable" </c:if>>--%>  
                                    <div class="panel-body">      
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch xuất thiết bị</label> 
                                                <input type="hidden" name="id" value="${tramDA.id}" id="id"  />
                                                <input type="hidden" name="trangThaiTram" value="${trangThaiTram}" id="trangThaiTram"  />
                                                <input type="hidden" name="duAnId" value="${tramDA.duAnId}" id="duAnId"  />
                                                <input type="hidden" name="tramQHId" value="${tramDA.tramQHId}" id="tramQHId"  />
                                                <input  class="form-control"  id="keHoachXuatThietBi" name="keHoachXuatThietBi" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.keHoachXuatThietBi}" />" />
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày xuất anten thực tế</label> 
                                                <input  class="form-control"  id="ngayXuatAntenThucTe" name="ngayXuatAntenThucTe" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayXuatAntenThucTe}" />" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày xuất thiết bị thực tế</label> 
                                                <input  class="form-control"  id="ngayXuatThietBiThucTe" name="ngayXuatThietBiThucTe" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayXuatThietBiThucTe}" />" />
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày tiếp nhận thiết bị</label> 
                                                <input  class="form-control"  id="ngayTiepNhanTb" name="ngayTiepNhanTb" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayTiepNhanTb}" />" /> 
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch thiết bị đến site</label> 
                                                <input  class="form-control"  id="keHoachTbDenSite" name="keHoachTbDenSite" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.keHoachTbDenSite}" />" /> 
                                            </div>
                                        </div> 

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch Hòa mạng</label> 
                                                <input  class="form-control"  id="keHoachHoaMang" name="keHoachHoaMang" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.keHoachHoaMang}" />" />  
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày hòa mạng thực tế</label> 
                                                <input  class="form-control"  id="ngayHoaMangThucTe" name="ngayHoaMangThucTe" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayHoaMangThucTe}" />" /> 
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch phát sóng chính thức</label> 
                                                <input  class="form-control"  id="keHoachPhatSongCt" name="keHoachPhatSongCt" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.keHoachPhatSongCt}" />" /> 
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày phát sóng chính thức</label> 
                                                <input  class="form-control"  id="ngayPhatSongCt" name="ngayPhatSongCt" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayPhatSongCt}" />" /> 
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Kế hoạch nghiệm thu</label> 
                                                <input  class="form-control"  id="keHoachNghiemThu" name="keHoachNghiemThu" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.keHoachNghiemThu}" />" /> 
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ngày nghiệm thu</label> 
                                                <input  class="form-control"  id="ngayNghiemThu" name="ngayNghiemThu" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                        value="${tramDA.ngayNghiemThu}" />" />
                                            </div>
                                           
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đầu mối (VNPT Net)</label> 
                                                <input
                                                    type="text" class="form-control" id="exampleInputEmail1" name="dauMoiVnptNet"
                                                    placeholder="Đầu mối (VNPT Net)" value="${tramDA.dauMoiVnptNet}"/> 
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Đơn vị vận chuyển</label> 
                                                <input
                                                    type="text" class="form-control" id="exampleInputEmail1" name="donViVanChuyen"
                                                    placeholder="Đơn vị vận chuyển" value="${tramDA.donViVanChuyen}"/>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Ghi chú</label> 
                                                <input
                                                    type="text" class="form-control" id="exampleInputEmail1" name="ghiChu"
                                                    placeholder="Ghi chú" value="${tramDA.ghiChu}"/> 
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
            $(document).ready(function() {                
                 $('#keHoachXuatThietBi').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayXuatThietBiThucTe').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayTiepNhanTb').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#keHoachTbDenSite').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });

                 $('#keHoachHoaMang').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayHoaMangThucTe').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#keHoachPhatSongCt').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#ngayPhatSongCt').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                 $('#keHoachNghiemThu').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 });
                $('#ngayNghiemThu').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 }); 
                 $('#ngayXuatAntenThucTe').datepicker({
                    format: 'dd/mm/yyyy',
                    todayHighlight: true,
                    autoclose: true
                 }); 
                 
            });
        </script>
    </body>
</html>
