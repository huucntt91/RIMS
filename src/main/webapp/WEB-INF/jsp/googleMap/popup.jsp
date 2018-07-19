
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- ADD HEADER PAGE -->
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
        <style>
            #map {

                width: 100%;
                height: 365px;
                overflow: hidden;
            }
            #infoWindow{
                display: none;
            }
        </style>
    </head>
    <body class="skin-blue">
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <section class="content">            

                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                                                       <!-- /.box-header -->
                            <div class="box-body ">
                                <div id="map"></div>
                            </div>
                            <!-- /.box-body -->

                        </div>
                        <!-- /.box -->
                    </div>
                </div>
            </section>
        </div><!-- ./wrapper -->
        <div id="infoWindow">
            <table id="example1" class="table table-bordered table-striped">
                <tbody id="table-content" >
                    <tr>
                        <th>Mã Building</td>
                        <td>${model.code}</td>
                    </tr>
                    <tr>
                        <th>Địa chỉ</th>
                        <td>${model.address}</td>
                    </tr>
                    <tr>
                        <th>Đơn vị</th>      
                        <td>${model.donViName}</td>
                    </tr>
                </tbody>
              
            </table>
        </div>
        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDn_nXdDrNiUlGFpAr2uwL3hsDGYaLiU7M"></script>

    </body>
</html>



<script>
    function initMap() {
        var myLatLng = {lat: ${model.lat}, lng: ${model.lon}};

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 9,
            center: myLatLng
        });
        var infowindow = new google.maps.InfoWindow({
            content: $('#infoWindow').html()
        });
        var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            icon: '${pageContext.request.contextPath}/resources/img/equipment/building-icon.png',
            title: 'Hello World!'
        });
        marker.setMap(map);
        marker.addListener('click', function () {
            infowindow.open(map, marker);
        });
    }
    $(document).ready(function () {
        initMap();
    });
</script>

