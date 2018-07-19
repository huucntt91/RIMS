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
            <h1>Xóa node</h1>
            <ol class="breadcrumb">                
                <div class="btn-group">
                    <a href="${pageContext.request.contextPath}/cellExcelImport/files/Template_DELETE_NODE"><button type="button" class="btn btn-danger">
                        <i class="fa fa-download"></i> 
                        Download Template
                        </button> </a>
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

                        <form:form method="POST" action="${pageContext.request.contextPath}/excelDeleteNode/checkDestroy" commandName="importNodeForm"
                                   enctype="multipart/form-data">
                            <div class="box-body" >
                                <div class="form-group">
                                        <div class="col-xs-4">                                    
                                            <div class="input-group">   
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

            <c:if test="${tableDeleteForm.models.size()>0}">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">

                            <div class="box-body table-responsive">
                                <div id="tablePagingId">

                                    <%--modelAttribute="tableForm">--%>
                                    <form:form method="post" action="${pageContext.request.contextPath}/excelDeleteNode/updateDestroy" 
                                               modelAttribute="tableDeleteForm">
                                        <input type="hidden" name="type" value="${type}"/>
                                        <table id="example1" class="table table-bordered table-hover">
                                            <thead>
                                                <tr>

                                                    <th><input type="checkbox" id="checkall"></th>
                                                    <th>Kết quả</th>                                                    
                                                    <th>Mã Node</th>
                                                    <th>Kiểu Node</th>
                                                    <th>Lý do</th>

                                                </tr>
                                            </thead>

                                            <tbody>                                       
                                                <c:forEach var="temp" items="${tableDeleteForm.models}" varStatus="status">                                        
                                                    <tr>
                                                        <td><form:checkbox path="models[${status.index}].check" class="checkitem"/></td>
                                                        <td><form:label path="models[${status.index}].checkDB" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.checkDB}</form:label>
                                                            <form:hidden path="models[${status.index}].checkDB" />
                                                        </td>

                                                        <td><form:label path="models[${status.index}].code" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.code}</form:label>
                                                            <form:hidden path="models[${status.index}].code" />
                                                        </td>

                                                        <td><form:label path="models[${status.index}].loaiNE" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.loaiNE}</form:label>
                                                            <form:hidden path="models[${status.index}].loaiNE" />
                                                        </td>
                                                        <td><form:label path="models[${status.index}].lyDo" 
                                                                    cssStyle="border:0;font-weight:normal" readonly="true" >${temp.lyDo}</form:label>
                                                            <form:hidden path="models[${status.index}].lyDo" />
                                                        </td>                                                                                                              
                                                    </tr>
                                                </c:forEach>                                       							
                                            </tbody>                                    
                                        </table>

                                        <br/>                                    
                                        <button type="submit" class="btn btn-primary" >Save</button>
                                        <button name="excel" type="submit" class="btn btn-primary" >Excel</button>

                                    </form:form>
                                </div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>
                </div>
            </c:if>            
        </section> 

        <script  type="text/javascript">
            $(function() {
                $('.iCheck-helper').click(function() {
                    var parent = $(this).parent().get(0);
                    //var checkboxId = parent.getElementsByTagName('input')[0].id;
                    $(parent).find('input').click();
                });


                $('#checkall').click(function() {
                    var isChecked = $("#checkall").prop("checked");
                    if (!isChecked) {
                        $('.checkitem').iCheck('check');
                    } else {
                        $('.checkitem').iCheck('uncheck');
                    }

                });
                $('#example1').DataTable({
                    "language": {
                        "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                    }
                });
            });

        </script>
    </body>

</html>

