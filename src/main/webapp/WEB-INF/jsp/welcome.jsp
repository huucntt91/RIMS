
<%@page import="com.vnpt.media.rims.bean.UserBO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
        <!-- jQuery 2.0.2 -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script> 
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>

        <!--highcharts -->
        <script src="${pageContext.request.contextPath}/resources/js/highcharts/highcharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/highcharts/exporting.js" type="text/javascript"></script>
        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <section class="content">                        
            Xin chào : <%=request.getRemoteUser()%>
        </section>
        <!--biểu đồ thể hiện số lượng file các omc download được -->
        <div id="chart1" ></div>
        <!--biểu đồ thể hiện số parse được được -->
        <div id="chart2"  ></div>

        <!--biểu đồ thể hiện số parse được được -->
        <div id="chart3"  ></div>

        <!-- biểu đồ thể hiện data_audit -->
        <div id="chart4"  ></div>



        <script language="JavaScript">
            $(document).ready(function () {
            buildChart1();
            buildChart2();
            buildChart3();
            buildChart4();
            });
            function buildChart1() {
            Highcharts.chart('chart1', {
            chart: {
            type: 'column'
            },
                    title: {
                    text: 'Lượng file cấu hình download được'
                    },
                    xAxis: {

                    categories: [
            <c:forEach var="t" items="${dates}" >
                    '${t}',
            </c:forEach>
                    ],
                            crosshair: true
                    },
                    yAxis: {
                    min: 0,
                            title: {
                            text: 'số file'
                            }

                    },
                    tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                            '<td style="padding:0"><b>{point.y:.1f} file</b></td></tr>',
                            footerFormat: '</table>',
                            shared: true,
                            useHTML: true
                    },
                    plotOptions: {
                    column: {
                    pointPadding: 0.2,
                            borderWidth: 0
                    }
                    },
                    series: [
            <c:forEach var="chartData" items="${chartDatas}" >
                    { name: '${chartData.key}',
                            data: ${chartData.data}
                    },
            </c:forEach>
                    ]
            });
            };
            function buildChart2() {
            Highcharts.chart('chart2', {

            title: {
            text: 'Số lượng BTS parsing được từ file cấu hình'
            },
                    xAxis: {

                    categories: [
            <c:forEach var="t" items="${dates}" >
                    '${t}',
            </c:forEach>
                    ],
                            crosshair: true
                    },
                    yAxis: {
                    title: {
                    text: 'số lượng'
                    }
                    },
                    legend: {
                    layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'middle'
                    },
                    series: [
            <c:forEach var="chartData0" items="${chartDatas0}" >
                    { name: '${chartData0.key}',
                            data: ${chartData0.data}
                    },
            </c:forEach>
                    ]

            });
            };
            function buildChart3() {
            Highcharts.chart('chart3', {

            title: {
            text: 'Số lượng NODEB parsing được từ file cấu hình'
            },
                    xAxis: {

                    categories: [
            <c:forEach var="t" items="${dates}" >
                    '${t}',
            </c:forEach>
                    ],
                            crosshair: true
                    },
                    yAxis: {
                    title: {
                    text: 'số lượng'
                    }
                    },
                    legend: {
                    layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'middle'
                    },
                    series: [
            <c:forEach var="chartData3" items="${chartDatas3}" >
                    { name: '${chartData3.key}',
                            data: ${chartData3.data}
                    },
            </c:forEach>
                    ]

            });
            };
            function buildChart4(){
            Highcharts.chart('chart4', {
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
            <c:forEach var="item" items="${chartDatas4}" >
                    '<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.INSERT_DATE}" />',
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
                            data: [<c:forEach var="item" items="${chartDatas4}" >
                ${item.RI_Y_OMC_N},
            </c:forEach>]
                    }, {
                    name: 'Thay đổi thông tin',
                            data: [<c:forEach var="item" items="${chartDatas4}" >
                ${item.RI_Y_OMC_Y},
            </c:forEach>]
                    }, {
                    name: 'Không có RIMS - Có OMC',
                            data: [<c:forEach var="item" items="${chartDatas4}" >
                ${item.RI_N_OMC_Y},
            </c:forEach>]
                    }]

            });
            };
        </script>
    </body>
</html>
