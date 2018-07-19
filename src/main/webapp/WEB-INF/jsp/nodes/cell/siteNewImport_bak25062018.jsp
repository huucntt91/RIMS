<%@page import="com.vnpt.media.rims.common.utils.Convert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            <h1>
                <c:if test="${type==2}">Khai báo Bts/NodeB/EnodeB</c:if>
                </h1>
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
                        <c:if test="${type==2}">
                            <li><a href="${pageContext.request.contextPath}/cellExcelImport/files/Template_DK_SITE">Biểu mẫu đk BTS/NODEB/ENODEB</a></li>
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

                        <form:form method="POST" action="${pageContext.request.contextPath}/cellExcelImport/preCheck" commandName="importNodeForm"
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
                                <button type="submit" class="btn btn-primary">Kiểm tra dữ liệu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <c:if     test="${type==2}">
                <c:if test="${tableFormBts.models.size()>0}">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">

                                <div class="box-body table-responsive">
                                    <%--modelAttribute="tableForm">--%>
                                    <form:form method="post" action="${pageContext.request.contextPath}/cellExcelImport/updateBts" 
                                               modelAttribute="tableFormBts">
                                        <input type="hidden" name="type" value="${type}"/>
                                        <table id="example1" class="table table-bordered table-hover">
                                            <thead>
                                                <tr>

                                                    <th><input type="checkbox" id="checkall"></th>
                                                    <th>Kết quả</th>
                                                    <th>Mã Đăng Ký</th>
                                                    <th>Loại NE</th>
                                                    <th>Mã BSC/RNC</th>
                                                    <th>Mã trạm dự án</th>                                                    
                                                    <th>Tên đv quản lý</th>                                                    
                                                    <th>Mã CSHT</th>                                                    
                                                    <th>Ngày hoạt động</th>
                                                    <th>Hoàn cảnh ra đời</th>
                                                    <th>Tên người quản lý</th>
                                                    <th>Tên cho quản lý</th>
                                                    <th>Tên trên hệ thống</th>
                                                    <th>Tên thiết bị</th>                                                    
                                                    <th>Loại trạm</th>
                                                    <th>Cấu hình</th>
                                                    <th>eNodeB ID</th>
                                                </tr>
                                            </thead>

                                            <tbody>                                       
                                                <c:forEach var="temp" items="${tableFormBts.models}" varStatus="status">                                        
                                                    <tr <c:if test="${temp.checkDB != 'OK'}" > class="error" </c:if>  >
                                                        <td><form:checkbox path="models[${status.index}].check" class="checkitem"/></td>
                                                        <td><form:label path="models[${status.index}].checkDB" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.checkDB}</form:label>
                                                            <form:hidden path="models[${status.index}].checkDB" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].maDK" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.maDK}</form:label>
                                                            <form:hidden path="models[${status.index}].maDK" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].neTypeName" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.neTypeName}</form:label>
                                                            <form:hidden path="models[${status.index}].neTypeName" />
                                                        </td>


                                                        <td><form:label path="models[${status.index}].maBscRnc" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true">${temp.maBscRnc}</form:label>
                                                            <form:hidden path="models[${status.index}].maBscRnc" />

                                                        </td>
                                                        <td><form:label path="models[${status.index}].maTramDuAn" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.maTramDuAn}
                                                            </form:label>
                                                            <form:hidden path="models[${status.index}].maTramDuAn" />

                                                        </td>                                                        

                                                        <td><form:label path="models[${status.index}].tenDonViQuanLy" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenDonViQuanLy}</form:label>
                                                            <form:hidden path="models[${status.index}].tenDonViQuanLy" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].maBuilding" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.maBuilding}</form:label>
                                                            <form:hidden path="models[${status.index}].maBuilding" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].ngayHoatDong" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.ngayHoatDong}</form:label>
                                                            <form:hidden path="models[${status.index}].ngayHoatDong" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].hoanCanhRaDoi" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.hoanCanhRaDoi}</form:label>
                                                            <form:hidden path="models[${status.index}].hoanCanhRaDoi" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].tenNguoiQuanLy" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenNguoiQuanLy}</form:label>
                                                            <form:hidden path="models[${status.index}].tenNguoiQuanLy" />
                                                        </td>
                                                         <td><form:label path="models[${status.index}].tenChoQuanLy" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenChoQuanLy}</form:label>
                                                            <form:hidden path="models[${status.index}].tenChoQuanLy" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].tenTrenHeThong" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenTrenHeThong}</form:label>
                                                            <form:hidden path="models[${status.index}].tenTrenHeThong" />
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
                                                        <td><form:label path="models[${status.index}].enodebId" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.enodebId}</form:label>
                                                            <form:hidden path="models[${status.index}].enodebId" />
                                                        </td>
                                                    </tr>
                                                </c:forEach>                                       							
                                            </tbody>                                    
                                        </table>

                                        <br/>                                    
                                        <button type="submit" class="btn btn-primary" onclick="return checkSumit();" >Khai báo</button>
                                        <!--                                        <button name="excel" type="submit" class="btn btn-primary" >Xuất Excel</button>-->

                                    </form:form>

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
//                $('#example1').DataTable({
//                    "language": {
//                        "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
//                    }
//                });


            });
            function checkSumit() {
                var flag = false;
                $(".checkitem").each(function (i) {
                    if ($(this).prop("checked")) {
                        flag = true;
                    }
                });
                if (!flag) {
                    alert('Bạn cần click chọn bản ghi cần khai báo');
                }

                return flag;
            }
        </script>
    </body>

</html>

