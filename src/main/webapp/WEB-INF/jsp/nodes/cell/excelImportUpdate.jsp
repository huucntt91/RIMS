<%@page import="com.vnpt.media.rims.common.utils.Convert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>

        <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/plugins/selected/bootstrap-select.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/selected/bootstrap-select.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js"></script>


    </head>
    <body>
        <div class="content-header">
            <h1><c:if test="${type==1}">Cập nhật thông tin cell</c:if>
                <c:if test="${type==2}">Cập nhật thông tin bts/nodeb/enodeb</c:if>
                </h1>
                <h2 style="display: none; color: blue;" id="mes_">Bạn check kết quả file download đi kèm</h2>
                <ol class="breadcrumb">                
                    <div class="btn-group">
                        <button type="button" class="btn btn-danger">
                            <i class="fa fa-download"></i> 
                            Download Template
                        </button>
                        <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <c:if test="${type==1}">
                            <li><a href="${pageContext.request.contextPath}/cellExcelImport/files/Template_CAPNHAT_CELL">Biểu mẫu cập nhật cell</a></li>
                            </c:if>
                            <c:if test="${type==2}">
                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'UPDATE_BTS_EXCEL')}">
                                    <li><a href="${pageContext.request.contextPath}/resources/excel/Template_CAPNHAT_SITE.xlsx">Biểu mẫu cập nhật BTS/NODEB/ENODEB</a></li>
                                </c:if>
                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'UPDATE_CSHT_BTS_TINH_EXCEL')}">
                                    <li><a href="${pageContext.request.contextPath}/resources/excel/Template_CAPNHAT_SITE_CSHT.xlsx">Biểu mẫu map CSHT vào BTS/NODEB/ENODEB</a></li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </ol>
        </div>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title"></h3>
                        </div>

                        <form:form method="POST" action="${pageContext.request.contextPath}/excelUpdateNode/preCheck" commandName="importNodeForm"
                                   enctype="multipart/form-data">
                            <div class="box-body" >
                                <div class="form-group">
                                        <div class="col-xs-4">                                    
                                            <div class="input-group">                                
                                                    <input type="hidden" value="${type}" name="type"/>
                                                <!--                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn file import</label>-->
                                                <form:input type="file" path="file"  id="BSbtndanger" value="${groupContactForm.name}"/>

                                                <script>
                                                    $('#BSbtndanger').filestyle({
                                                        buttonName: 'btn-info',
                                                        buttonText: ' Chọn File import'
                                                    });
                                                </script>

                                            </div>
                                        </div>                                   

                                    <div class="col-xs-8" >

                                    </div>
                                </div>
                            </div>
                            </br>
                            <div class="box-footer">
                                <button type="submit" id="upload" class="btn btn-primary">Cập nhật dữ liệu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <c:if     test="${type==2}">
                <c:if test="${tableFormUpdateBts.models.size()>0}">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">

                                <div class="box-body table-responsive">
                                    <div id="tablePagingId">

                                        <%--modelAttribute="tableForm">--%>
                                        <form:form method="post" action="${pageContext.request.contextPath}/excelUpdateNode/updateBts" 
                                                   modelAttribute="tableFormUpdateBts">
                                            <input type="hidden" name="type" value="${type}"/>
                                            <table id="example1" class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th><input type="checkbox" id="checkall"></th>
                                                        <th>Kết quả</th>            
                                                        <th>Loại NE</th>
                                                        <th>Mã Bts/NodeB/EnodeB</th>                                                        
                                                        <th>Mã BSC/RNC</th>                                                        
                                                        <th>Mã trạm dự án</th>          
                                                        <th>Mã CSHT</th>
                                                        <th>Tên người quản lý</th>
                                                        <th>SDT người quản lý</th>                                                        
                                                        <th>Tên cho quản lý</th>
                                                        <th>Hoàn cảnh ra đời</th>                                                    
                                                        <th>Ngày hoạt động</th>
                                                        <th>Tên trên hệ thống</th>
                                                        <th>Tên BSC/RNC</th>
                                                        <th>MSC/MSS</th>
                                                        <th>SGSN</th>
                                                        <th>DCHSDPA42M</th>
                                                        <th>Filter User</th>
                                                        <th>Loại công nghệ</th>
                                                        <th>Frequency band</th>          
                                                        <th>Thiết bị</th>
                                                        <th>Loại trạm</th>
                                                        <th>Cấu hình</th>
                                                    </tr>
                                                </thead>

                                                <tbody>                                       
                                                    <c:forEach var="temp" items="${tableFormUpdateBts.models}" varStatus="status">                                        
                                                        <tr <c:if test="${temp.checkDB != 'OK'}" > class="error" </c:if>>
                                                            <td><form:checkbox path="models[${status.index}].check" class="checkitem"/></td>
                                                            <td><form:label path="models[${status.index}].checkDB" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.checkDB}</form:label>
                                                                <form:hidden path="models[${status.index}].checkDB" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].loaiNE" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.loaiNE}</form:label>
                                                                <form:hidden path="models[${status.index}].loaiNE" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].maBts" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.maBts}</form:label>
                                                                <form:hidden path="models[${status.index}].maBts" />
                                                            </td>

                                                            <td><form:label path="models[${status.index}].bscCode" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.bscCode}</form:label>
                                                                <form:hidden path="models[${status.index}].bscCode" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].maTramDuAn" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.maTramDuAn}</form:label>
                                                                <form:hidden path="models[${status.index}].maTramDuAn" />
                                                            </td>

                                                            <td><form:label path="models[${status.index}].maBuilding" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.maBuilding}
                                                                </form:label>
                                                                <form:hidden path="models[${status.index}].maBuilding" />

                                                            </td>                                                        

                                                            <td><form:label path="models[${status.index}].tenNguoiQuanLy" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenNguoiQuanLy}</form:label>
                                                                <form:hidden path="models[${status.index}].tenNguoiQuanLy" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].sdtNguoiQuanLy" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.sdtNguoiQuanLy}</form:label>
                                                                <form:hidden path="models[${status.index}].sdtNguoiQuanLy" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].tenChoQuanLy" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenChoQuanLy}</form:label>
                                                                <form:hidden path="models[${status.index}].tenChoQuanLy" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].hoanCanhRaDoi" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.hoanCanhRaDoi}</form:label>
                                                                <form:hidden path="models[${status.index}].hoanCanhRaDoi" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].ngayHoatDong" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.ngayHoatDong}</form:label>
                                                                <form:hidden path="models[${status.index}].ngayHoatDong" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].tenTrenHeThong" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenTrenHeThong}</form:label>
                                                                <form:hidden path="models[${status.index}].tenTrenHeThong" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].tenBscRnc" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenBscRnc}</form:label>
                                                                <form:hidden path="models[${status.index}].tenBscRnc" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].mscMss" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.mscMss}</form:label>
                                                                <form:hidden path="models[${status.index}].mscMss" />
                                                            </td>

                                                            <td><form:label path="models[${status.index}].sgsn" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.sgsn}</form:label>
                                                                <form:hidden path="models[${status.index}].sgsn" />
                                                            </td>

                                                            <td><form:label path="models[${status.index}].dcHsdpa42M" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.dcHsdpa42M}</form:label>
                                                                <form:hidden path="models[${status.index}].dcHsdpa42M" />
                                                            </td>

                                                            <td><form:label path="models[${status.index}].filterUser" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.filterUser}</form:label>
                                                                <form:hidden path="models[${status.index}].filterUser" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].loaiCongNghe" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.loaiCongNghe}</form:label>
                                                                <form:hidden path="models[${status.index}].loaiCongNghe" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].frequencyBand" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.frequencyBand}</form:label>
                                                                <form:hidden path="models[${status.index}].frequencyBand" />
                                                            </td>

                                                            <td><form:label path="models[${status.index}].thietBi" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.thietBi}</form:label>
                                                                <form:hidden path="models[${status.index}].thietBi" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].loaiTram" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.loaiTram}</form:label>
                                                                <form:hidden path="models[${status.index}].loaiTram" />
                                                            </td>
                                                            <td><form:label path="models[${status.index}].cauHinh" 
                                                                        cssStyle="border:0;font-weight:normal" readonly="true" >${temp.cauHinh}</form:label>
                                                                <form:hidden path="models[${status.index}].cauHinh" />
                                                            </td>                                                                                                                  
                                                        </tr>
                                                    </c:forEach>                                       							
                                                </tbody>                                    
                                            </table>

                                            <br/>                                    
                                            <button type="submit" class="btn btn-primary" >Save</button>
                                            <!--                                            <button name="excel" type="submit" class="btn btn-primary" >Excel</button>-->

                                        </form:form>
                                    </div>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                    </div>
                </c:if>
            </c:if>       
        </section> 

        <script  type="text/javascript">
            $(function () {
                $('.iCheck-helper').click(function () {
                    var parent = $(this).parent().get(0);
                    //var checkboxId = parent.getElementsByTagName('input')[0].id;
                    $(parent).find('input').click();
                });


                $('#checkall').click(function () {
                    var isChecked = $("#checkall").prop("checked");
                    if (!isChecked) {
                        $('.checkitem').iCheck('check');
                    } else {
                        $('.checkitem').iCheck('uncheck');
                    }



                    //if ($(this).is(':checked'))
                    //$('.checkitem').prop('checked', true);
                    //else
                    //    $('.checkitem').prop('checked', true);
                });
                $('#upload').bind("click", function ()
                {
                    var imgVal = $('#BSbtndanger').val();
                    if (imgVal == '')
                    {
                        alert("Bạn phải chọn file trước khi cập nhật dữ liệu");
                        return false;

                    }
                    $('#mes_').show();
                    return true;

                });
            });


        </script>
    </body>

</html>

