
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
                                                <label class=" input-group-addon"  style="min-width: 150px;">Chọn kpi</label>
                                                <select id="kpiLst" multiple="multiple" name="kpis"  class="form-control">
                                                    <c:forEach var="kpi" items="${kpiLst}">
                                                        <option
                                                            <c:if test='${fn:contains(modelSearch.kpis,kpi.kpiMappingId)}' >  selected="selected" </c:if>
                                                            value="${kpi.kpiMappingId}"> ${kpi.name} </option>
                                                    </c:forEach>
                                                </select>
                                                <!--<input type="hidden" id="txtKpi" name="kpis" value="${modelSearch.kpis}" />-->
                                                <input type="hidden" name="vnpCode" value="${modelSearch.vnpCode}" id="vnpCode"  />
                                                <input type="hidden" name="nodeType" value="${modelSearch.nodeType}" id="nodeType" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon"   style="min-width: 150px;">Tấn suất</label>
                                                <select name="frequency" class="form-control">
                                                    <option value="HOURLY" <c:if test="${modelSearch.frequency == 'HOURLY'}" >  selected="selected"</c:if>>HOURLY</option>
                                                    <option value="DAILY" <c:if test="${modelSearch.frequency == 'DAILY'}" >  selected="selected"</c:if>>DAILY</option>
                                                    <option value="WEEKLY" <c:if test="${modelSearch.frequency == 'WEEKLY'}" >  selected="selected"</c:if>>WEEKLY</option>
                                                    <option value="MONTHLY" <c:if test="${modelSearch.frequency == 'MONTHLY'}" >  selected="selected"</c:if>>MONTHLY</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="input-group" >
                                                    <label class=" input-group-addon"   style="min-width: 150px;">Thời gian bắt đầu</label>
                                                    <input type="text" class="form-control date_form"  name="startTime" id="startTime" required="true"
                                                           value="<fmt:formatDate pattern="dd/MM/yyyy" value="${modelSearch.startTime}" />" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <div class="input-group" >
                                                <label class=" input-group-addon"   style="min-width: 150px;">Thời gian kết thúc</label>
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
                                <h3 class="box-title">Biểu đồ KPI</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive">
                                <div class="panel-group" id="accordion">
                                    <c:forEach var="kpi" items="${kpiLst}" varStatus="status">
                                        <c:if test='${fn:contains(modelSearch.kpis,kpi.kpiMappingId)}' >
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a data-toggle="collapse"  data-parent="#accordion" href="#collapse${status.index}">
                                                            ${kpi.name} </a>
                                                    </h4>
                                                </div>
                                                            <div id="collapse${status.index}" class="panel-collapse collapse <c:if test='${status.index == 0}' >in</c:if> ">
                                                    <div class="panel-body">
                                                        <div id="container${status.index}" style="width: 700px;height:400; margin: 0 auto">

                                                        </div>
                                                    </div>
                                                </div>
                                                <script>
                                                    Highcharts.chart('container${status.index}', {
                                                        chart: {
                                                            zoomType: 'x'
                                                        },
                                                        title: {
                                                            text: '${kpi.name}'
                                                        },
                                                        subtitle: {
                                                            text: document.ontouchstart === undefined ?
                                                                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
                                                        },
                                                        xAxis: {
                                                            type: 'datetime'
                                                        },
                                                        yAxis: {
                                                            title: {
                                                                text: 'Giá trị'
                                                            }
                                                        },
                                                        legend: {
                                                            enabled: false
                                                        },
                                                        plotOptions: {
                                                            area: {
                                                                fillColor: {
                                                                    linearGradient: {
                                                                        x1: 0,
                                                                        y1: 0,
                                                                        x2: 0,
                                                                        y2: 1
                                                                    },
                                                                    stops: [
                                                                        [0, Highcharts.getOptions().colors[0]],
                                                                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                                                                    ]
                                                                },
                                                                marker: {
                                                                    radius: 2
                                                                },
                                                                lineWidth: 1,
                                                                states: {
                                                                    hover: {
                                                                        lineWidth: 1
                                                                    }
                                                                },
                                                                threshold: null
                                                            }
                                                        },
                                                        series: [{
                                                                type: 'area',
                                                                name: '',
                                                                data: [<c:forEach var="item" items="${list}" >
                                                                <c:forEach var="pm" items="${item.list}">
                                                                    <c:if test='${pm.code == kpi.code}' >
                                                                       [Date.UTC(<fmt:formatDate pattern="yyyy,"  
                                                                    value="${item.createTime}" /><fmt:formatDate pattern="M" value="${item.createTime}" /> - 1<fmt:formatDate pattern=",dd,HH,mm,ss"  
                                                                    value="${item.createTime}" />),${pm.value}],
                                                                    </c:if>
                                                                </c:forEach>
                                                         </c:forEach>]
                                                                
                                                            }]
                                                    });

                                                </script>
                                            </div>    
                                        </c:if>
                                    </c:forEach>

                                </div>
                                <!--                                <div id="highcharts" style="display: none">
                                                                    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto">
                                
                                                                    </div>
                                                                </div>-->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Danh sách KPI</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive">

                                <div id="tablePagingId">
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
                                                    <td>${item.nodeType}</td>
                                                    <td><fmt:formatDate pattern="dd/MM/yyyy:HH" 
                                                                    value="${item.createTime}" /></td>  
                                                        <c:forEach var="pm" items="${item.list}" varStatus="status">
                                                        <td>${pm.value}</td> 
                                                    </c:forEach>  
                                                </tr>
                                            </c:forEach>                                       							
                                        </tbody> 
                                    </table>   
                                </div>
                            </div>
                            <!-- /.box-body -->

                        </div>

                    </div>
                </div>
            </section>
        </div>
    </body>


</html>

<script>
    function loadChart(index) {
        $('#collapse' + index + ' .panel-body').html($('#highcharts').html());
    }

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
//                $('#txtKpi').val($('select#kpiLst').val());
//                var  x = $('select#kpiLst').val();
//                console.log(x);
            }

        }));
    });
</script>

