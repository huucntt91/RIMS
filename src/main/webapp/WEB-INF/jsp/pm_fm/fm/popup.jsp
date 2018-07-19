
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>RIMS</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- bootstrap 3.0.2 -->
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css">

        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
        <!-- -->

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="skin-blue">
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <section class="content">            
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <form:form method="GET" >
                                <div class="box-body">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" >Chọn event</label>
                                                <select id="kpiLst" multiple="multiple" name="events" class="form-control">
                                                    <c:forEach var="event" items="${eventLst}">
                                                        <option
                                                            <c:if test='${fn:contains(modelSearch.events,event.event_define_id)}' >  selected="selected" </c:if>
                                                            value="${event.event_define_id}"> ${event.event_define_name} </option>
                                                    </c:forEach>
                                                </select>
                                                <input type="hidden" name="vnpCode" value="${modelSearch.vnpCode}" id="vnpCode"  />
                                                <input type="hidden" name="nodeType" value="${modelSearch.nodeType}" id="nodeType" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <div class="input-group" >
                                                <label class=" input-group-addon" >Thời gian bắt đầu</label>
                                                <input type="text" class="form-control date_form"  name="startTime" id="startTime" required="true"
                                                       value="<fmt:formatDate pattern="dd/MM/yyyy" value="${modelSearch.startTime}" />" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <div class="input-group" >
                                                <label class=" input-group-addon" >Thời gian kết thúc</label>
                                                <input type="text" class="form-control date_form"  name="endTime" id="endTime"
                                                       value="<fmt:formatDate pattern="dd/MM/yyyy" value="${modelSearch.endTime}" />" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="clearfix" ></div>
                                </div>
                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Kết quả</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive">
                                <table id="table1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <c:forEach var="columnName" items="${columnNames}" varStatus="status">
                                                <th>${columnName}</th>
                                                </c:forEach>    
                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="item" items="${list}" varStatus="status">                                        
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${item.vnpCode}</td>
                                                <td>${item.createTime}</td> 
                                                <td>${item.event_define_name}</td>
                                                <td>${item.event_description}</td>
                                            </tr>
                                        </c:forEach>                                       							
                                    </tbody> 
                                </table>   
                            </div>
                            <!-- /.box-body -->

                        </div>

                    </div>
                </div>
            </section>
        </div>
    </body>

    <!-- jQuery 2.0.2 -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- DataTables -->
    <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="${pageContext.request.contextPath}/resources/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/plugins/fastclick/fastclick.js"></script>
    <!-- multiselect -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
    <!-- AdminLTE App -->


    <!--call ajax-->
    <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>

</html>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<script>
    $(document).ready(function () {
        $("#table1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
        $('#startTime').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });

        $('#endTime').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });

        $('#kpiLst').multiselect(({
            maxHeight: 200,
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
//                var brands = $('#kpiLst option:selected');
//                var textTmp = '';
//                $(brands).each(function (index, brand) {
//                    textTmp += $(this).val() + ",";
//                });
//                $('#txtEvents').val(textTmp);
            }
        }));


    });
</script>

