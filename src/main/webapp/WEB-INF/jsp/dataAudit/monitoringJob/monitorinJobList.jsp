
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
        <script src="${pageContext.request.contextPath}/resources/js/highcharts/highcharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/highcharts/exporting.js" type="text/javascript"></script>

        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>


    </head>
    <body class="skin-blue">
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <section class="content">            
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">

                            <form:form method="GET" >
                                <div class="box-body">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" >Loại NE</label>
                                                    <select name="NE_NAME" class="form-control">
                                                        <option value="BTS" <c:if test="${modelSearch.NE_NAME == 'BTS'}" >  selected="selected"</c:if>>BTS</option>
                                                        <option value="NODEB" <c:if test="${modelSearch.NE_NAME == 'NODEB'}" >  selected="selected"</c:if>>NODEB</option>
                                                        <option value="ENODEB" <c:if test="${modelSearch.NE_NAME == 'ENODEB'}" >  selected="selected"</c:if>>ENODEB</option>
                                                        <option value="CELL2G" <c:if test="${modelSearch.NE_NAME == 'CELL2G'}" >  selected="selected"</c:if>>CELL2G</option>
                                                        <option value="CELL3G" <c:if test="${modelSearch.NE_NAME == 'CELL3G'}" >  selected="selected"</c:if>>CELL3G</option>
                                                        <option value="CELL4G" <c:if test="${modelSearch.NE_NAME == 'CELL4G'}" >  selected="selected"</c:if>>CELL4G</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="input-group" >
                                                <label class=" input-group-addon" >Thời gian bắt đầu</label>
                                                <input type="text" class="form-control date_form"  name="startTime" id="startTime" 
                                                       value="<fmt:formatDate pattern="dd/MM/yyyy" value="${modelSearch.startTime}" />" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="col-md-12">
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
                                <h3 class="box-title">Biểu đồ</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive">
                                <div class="panel-group" id="accordion">
                                    <%--<c:forEach var="kpi" items="${kpiLst}" varStatus="status">--%>
                                    <div class="panel panel-default">

                                        <div class="panel-body">
                                            <div id="container" style="width: 100%;height:500px; margin: 0 auto">

                                            </div>
                                        </div>

                                        <script>
                                            Highcharts.chart('container', {
                                            chart: {
                                            zoomType: 'x'
                                            },
                                                    title: {
                                                    text: 'Giám sát tiến trình Data Audit'
                                                    },
                                                    subtitle: {
                                                    text: document.ontouchstart === undefined ?
                                                            'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
                                                    },
                                                    xAxis: {
                                                    categories: [
                                            <c:forEach var="item" items="${list}" >
                                                    '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.INSERT_DATE}" />',


                                            </c:forEach>]
                                                    },
                                                    yAxis: {
                                                    title: {
                                                    text: 'Giá trị'
                                                    }
                                                    },
                                                    legend: {
                                                    layout: 'vertical',
                                                            align: 'right',
                                                            verticalAlign: 'middle'
                                                    },
                                                    series: [{
                                                    name: 'Có RIMS - Không có OMC',
                                                            data: [<c:forEach var="item" items="${list}" >
                                                                ${item.RI_Y_OMC_N},
                                                            </c:forEach>]
                                                    }, {
                                                    name: 'Thay đổi thông tin',
                                                            data: [<c:forEach var="item" items="${list}" >
                                                                ${item.RI_Y_OMC_Y},
                                                            </c:forEach>]
                                                    }, {
                                                    name: 'Không có RIMS - Có OMC',
                                                            data: [<c:forEach var="item" items="${list}" >
                                                                ${item.RI_N_OMC_Y},
                                                            </c:forEach>]
                                                    }]

                                            });
                                        </script>
                                    </div>    
                                    <%--</c:forEach>--%>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>

            </section>
        </div>
        <script>
            $(document).ready(function () {

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
            });
        </script>                            
    </body>

</html>


