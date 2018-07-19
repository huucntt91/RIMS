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
            <h1>Khai báo cell 2G/cell 3G/cell 4G

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

                        <li><a href="${pageContext.request.contextPath}/cellExcelImport/files/Template_DK_CELL_2G">Biểu mẫu đk cell 2G</a></li>
                        <li><a href="${pageContext.request.contextPath}/cellExcelImport/files/Template_DK_CELL_3G">Biểu mẫu đk cell 3G</a></li>
                        <li><a href="${pageContext.request.contextPath}/cellExcelImport/files/Template_DK_CELL_4G">Biểu mẫu đk cell 4G</a></li>                        
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
                        <form:form method="POST" action="${pageContext.request.contextPath}/cellsExcel/importCellNew" commandName="cellNewExcelBO"
                                   enctype="multipart/form-data">
                            <div class="box-body" >
                                <div class="form-group">
                                    <div class="col-xs-4">
                                        <select  name="type" class="form-control" required="true"  >
                                            <option value="5" <c:if test="${type == '5'}"> selected </c:if>>Cell 2G</option>
                                            <option value="6" <c:if test="${type == '6'}"> selected </c:if>>Cell 3G</option>
                                            <option value="7" <c:if test="${type == '7'}"> selected </c:if>>Cell 4G</option>
                                            </select>  
                                        </div>
                                        <div class="col-xs-4">                                    
                                            <div class="input-group">     
                                                <label class=" input-group-addon" style="min-width:150px;" for="BSbtndanger">Chọn file import</label>
                                            <form:input class="form-control" type="file" path="file"  id="BSbtndanger" value="${groupContactForm.name}"/>
                                        </div>
                                    </div>                                   

                                </div>
                            </div>
                            </br>
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">Khai báo</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <c:if test="${type == '5'}">
                <c:if test="${tableFormCell2G.models.size()>0}">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">

                                <div class="box-body table-responsive">
                                    <%--modelAttribute="tableForm">--%>
                                    <form:form method="post" action="${pageContext.request.contextPath}/cellsExcel/updateCell2g" 
                                               modelAttribute="tableFormCell2G">
                                        <input type="hidden" name="type" value="${type}"/>
                                        <table id="example1" class="table table-bordered table-hover">
                                            <thead>
                                                <tr>

                                                    <th><input type="checkbox" id="checkall"></th>
                                                    <th>Kết quả</th>                                                    
                                                    <th>Mã BTS</th>
                                                    <th>Ngày hoạt động</th>  
                                                    <th>Hoàn cảnh ra đời</th>
                                                    <th>Tên Cell cho quản lý</th>
                                                    <th>Tên trên hệ thống</th>                                                    
                                                    <th>LAC</th>                                                    
                                                    <th>CI</th>
                                                    <th>Thiết bị</th>
                                                    <th>Loại trạm</th>
                                                    <th>NoOfCarrier</th>                                                    
                                                </tr>
                                            </thead>

                                            <tbody>                                       
                                                <c:forEach var="temp" items="${tableFormCell2G.models}" varStatus="status">                                        
                                                    <tr <c:if test="${temp.checkDB != 'OK'}" > class="error" </c:if>  >
                                                        <td><form:checkbox path="models[${status.index}].check" class="checkitem"/></td>
                                                        <td><form:label path="models[${status.index}].checkDB" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.checkDB}</form:label>
                                                            <form:hidden path="models[${status.index}].checkDB" />
                                                        </td>

                                                        <td><form:label path="models[${status.index}].maNodeCha" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.maNodeCha}</form:label>
                                                            <form:hidden path="models[${status.index}].maNodeCha" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].ngayHoatDong" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.ngayHoatDong}</form:label>
                                                            <form:hidden path="models[${status.index}].ngayHoatDong" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].hoanCanhRaDoi" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.hoanCanhRaDoi}
                                                            </form:label>
                                                            <form:hidden path="models[${status.index}].hoanCanhRaDoi" />

                                                        </td>        
                                                        <td><form:label path="models[${status.index}].ten_cell" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true">${temp.ten_cell}</form:label>
                                                            <form:hidden path="models[${status.index}].ten_cell" />

                                                        </td>



                                                        <td><form:label path="models[${status.index}].tenTrenHeThong" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenTrenHeThong}</form:label>
                                                            <form:hidden path="models[${status.index}].tenTrenHeThong" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].lac" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.lac}</form:label>
                                                            <form:hidden path="models[${status.index}].lac" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].ci" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.ci}</form:label>
                                                            <form:hidden path="models[${status.index}].ci" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].tenThietBi" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenThietBi}</form:label>
                                                            <form:hidden path="models[${status.index}].tenThietBi" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].tenTram" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenTram}</form:label>
                                                            <form:hidden path="models[${status.index}].tenTram" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].noOfCarrier" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.noOfCarrier}</form:label>
                                                            <form:hidden path="models[${status.index}].noOfCarrier" />
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
            <c:if test="${type == '6'}">
                <c:if test="${tableFormCell3G.models.size()>0}">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">

                                <div class="box-body table-responsive">
                                    <%--modelAttribute="tableForm">--%>
                                    <form:form method="post" action="${pageContext.request.contextPath}/cellsExcel/updateCell3g" 
                                               modelAttribute="tableFormCell3G">
                                        <input type="hidden" name="type" value="${type}"/>
                                        <table id="example1" class="table table-bordered table-hover">
                                            <thead>
                                                <tr>

                                                    <th><input type="checkbox" id="checkall"></th>
                                                    <th>Kết quả</th>                                                    
                                                    <th>Mã NODEB</th>
                                                    <th>Ngày hoạt động</th>  
                                                    <th>Hoàn cảnh ra đời</th>
                                                    <th>Tên Cell cho quản lý</th>
                                                    <th>Tên trên hệ thống</th>                                                    
                                                    <th>LAC</th>                                                    
                                                    <th>CI</th>
                                                    <th>Thiết bị</th>
                                                    <th>Loại trạm</th>
                                                    <th>NoOfCarrier</th>                                                    
                                                </tr>
                                            </thead>

                                            <tbody>                                       
                                                <c:forEach var="temp" items="${tableFormCell3G.models}" varStatus="status">                                        
                                                    <tr <c:if test="${temp.checkDB != 'OK'}" > class="error" </c:if>  >
                                                        <td><form:checkbox path="models[${status.index}].check" class="checkitem"/></td>
                                                        <td><form:label path="models[${status.index}].checkDB" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.checkDB}</form:label>
                                                            <form:hidden path="models[${status.index}].checkDB" />
                                                        </td>

                                                        <td><form:label path="models[${status.index}].maNodeCha" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.maNodeCha}</form:label>
                                                            <form:hidden path="models[${status.index}].maNodeCha" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].ngayHoatDong" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.ngayHoatDong}</form:label>
                                                            <form:hidden path="models[${status.index}].ngayHoatDong" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].hoanCanhRaDoi" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.hoanCanhRaDoi}
                                                            </form:label>
                                                            <form:hidden path="models[${status.index}].hoanCanhRaDoi" />

                                                        </td>        
                                                        <td><form:label path="models[${status.index}].ten_cell" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true">${temp.ten_cell}</form:label>
                                                            <form:hidden path="models[${status.index}].ten_cell" />

                                                        </td>



                                                        <td><form:label path="models[${status.index}].tenTrenHeThong" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenTrenHeThong}</form:label>
                                                            <form:hidden path="models[${status.index}].tenTrenHeThong" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].lac" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.lac}</form:label>
                                                            <form:hidden path="models[${status.index}].lac" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].ci" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.ci}</form:label>
                                                            <form:hidden path="models[${status.index}].ci" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].tenThietBi" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenThietBi}</form:label>
                                                            <form:hidden path="models[${status.index}].tenThietBi" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].tenTram" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenTram}</form:label>
                                                            <form:hidden path="models[${status.index}].tenTram" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].noOfCarrier" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.noOfCarrier}</form:label>
                                                            <form:hidden path="models[${status.index}].noOfCarrier" />
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
            <c:if test="${type == '7'}">
                <c:if test="${tableFormCell4G.models.size()>0}">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">

                                <div class="box-body table-responsive">
                                    <%--modelAttribute="tableForm">--%>
                                    <form:form method="post" action="${pageContext.request.contextPath}/cellsExcel/updateCell4g" 
                                               modelAttribute="tableFormCell4G">
                                        <input type="hidden" name="type" value="${type}"/>
                                        <table id="example1" class="table table-bordered table-hover">
                                            <thead>
                                                <tr>

                                                    <th><input type="checkbox" id="checkall"></th>
                                                    <th>Kết quả</th>                                                    
                                                    <th>EnodeB ID</th>
                                                    <th>Tên Cell cho quản lý</th>
                                                    <th>Hoàn cảnh ra đời</th>
                                                    <th>Ngày hoạt động</th>                                                    
                                                    <th>Tên trên hệ thống</th>                                                    
                                                    <th>TAC</th>                                                    
                                                    <th>PCI</th>
                                                    <th>LCI</th>
                                                    <th>Frequency band</th>
                                                    <th>Thiết bị</th>
                                                    <th>Loại trạm</th>
                                                    <th>NoOfCarrier</th>                                                    
                                                </tr>
                                            </thead>

                                            <tbody>                                       
                                                <c:forEach var="temp" items="${tableFormCell4G.models}" varStatus="status">                                        
                                                    <tr <c:if test="${temp.checkDB != 'OK'}" > class="error" </c:if>  >
                                                        <td><form:checkbox path="models[${status.index}].check" class="checkitem"/></td>
                                                        <td><form:label path="models[${status.index}].checkDB" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.checkDB}</form:label>
                                                            <form:hidden path="models[${status.index}].checkDB" />
                                                        </td>

                                                        <td><form:label path="models[${status.index}].enodebId" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.enodebId}</form:label>
                                                            <form:hidden path="models[${status.index}].enodebId" />
                                                        </td>


                                                        <td><form:label path="models[${status.index}].ten_cell" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true">${temp.ten_cell}</form:label>
                                                            <form:hidden path="models[${status.index}].ten_cell" />

                                                        </td>
                                                        <td><form:label path="models[${status.index}].hoanCanhRaDoi" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.hoanCanhRaDoi}
                                                            </form:label>
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
                                                        <td><form:label path="models[${status.index}].tag" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tag}</form:label>
                                                            <form:hidden path="models[${status.index}].tag" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].lac" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.lac}</form:label>
                                                            <form:hidden path="models[${status.index}].lac" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].ci" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.ci}</form:label>
                                                            <form:hidden path="models[${status.index}].ci" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].frequenctyBand" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.frequenctyBand}</form:label>
                                                            <form:hidden path="models[${status.index}].frequenctyBand" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].tenThietBi" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenThietBi}</form:label>
                                                            <form:hidden path="models[${status.index}].tenThietBi" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].tenTram" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.tenTram}</form:label>
                                                            <form:hidden path="models[${status.index}].tenTram" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].noOfCarrier" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.noOfCarrier}</form:label>
                                                            <form:hidden path="models[${status.index}].noOfCarrier" />
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

