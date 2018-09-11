<%@page import="com.vnpt.media.rims.common.Message"%>
<%@page import="com.vnpt.media.rims.common.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bản đồ RIMS</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/map.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/ol.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <style>
            #map {
                width: 100%;
                overflow: hidden;
            }
            #borough {
                background-color: #fff;
                border: 1px solid #000;
                font-family: Arial, sans-serif;
                font-size: 12px;
                padding: 10px 10px 8px;
                width: 400px;
                text-align: center;
                position: absolute;
                z-index: 9999;
                margin-left: auto;
                margin-right: auto;
                left: 0;
                right: 0;
                display: none;
            }
            #borough h4{
                text-align: center;
                margin-top:0;
            }
            #borough span{
                color: blue;
            }
            ol, ul {
                list-style-image: none;
                list-style-position: outside;
                list-style-type: none;
            }
            .content {
                padding-bottom: 0px;
            }
            div.fill {
                width: 100%;
                height: 100%;
            }
            .ol-popup {
                position: absolute;
                background-color: white;
                -webkit-filter: drop-shadow(0 1px 4px rgba(0,0,0,0.2));
                filter: drop-shadow(0 1px 4px rgba(0,0,0,0.2));
                padding: 15px;
                border-radius: 10px;
                border: 1px solid #cccccc;
                bottom: 12px;
                left: -50px;
                width: 500px;
            }

            .ol-popup:after, .ol-popup:before {
                top: 100%;
                border: solid transparent;
                content: " ";
                height: 0;
                width: 0;
                position: absolute;
                pointer-events: none;
            }

            .ol-popup:after {
                border-top-color: white;
                border-width: 10px;
                left: 48px;
                margin-left: -10px;
            }

            .ol-popup:before {
                border-top-color: #cccccc;
                border-width: 11px;
                left: 48px;
                margin-left: -11px;
            }

            .ol-popup-closer {
                text-decoration: none;
                position: absolute;
                top: 2px;
                right: 8px;
                width:10px;
                z-index: 99999;
            }
            .modal-dialog {
                width: 900px;
            }

            #noteLink {
                background-color: #fff;
                border: 1px solid #000;
                font-family: Arial, sans-serif;
                font-size: 12px;
                padding: 10px 10px 8px;
                width: 220px;
                text-align: center;
                position: absolute;
                z-index: 9999;
                margin-left: auto;
                margin-right: auto;
                left: 0;
                right: 0;
                display: none;

            }
            #divNeLink{
                background-color: #fff;
                border: 1px solid #000;
                font-family: Arial, sans-serif;
                font-size: 12px;
                width: 270px;
                height:  400px;
                text-align: left;
                position: absolute;
                z-index: 9999;
                left: 0;
                top: 100px;
                display: none;
                overflow: scroll;
            }
            #iframeDetail{
                background-color: #fff;
                border: 1px solid #000;
                font-family: Arial, sans-serif;
                font-size: 12px;
                width: 90%;
                height:  150px;
                text-align: center;

                z-index: 999;
                margin-left: auto;
                margin-right: auto;
                left: 0;
                right: 0;
                bottom:0;
                overflow-y: scroll;
                position: absolute;
                display: none;
            }
            .table-condensed{
                font-size: 10px !important;
            }
        </style>
    </style>

    <!-- jQuery 2.0.2 -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.9.2.js" type="text/javascript"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
    <!--call ajax-->
    <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>


    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-select.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/js/ol_v4.6.5.js" type="text/javascript"></script>
   
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDn_nXdDrNiUlGFpAr2uwL3hsDGYaLiU7M"></script>

    <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/spectrum.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/resources/js/spectrum.js" type="text/javascript"></script>
    

</head>
<body>
    <section class="content">
   
        <div class="row">
            <div class="col-xs-12">
                <ul class="MapToolsBar btns">
                    <li style="margin-right: 38px;"> <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_MAPS_RIMS.mp4');" >Hướng dẫn</button></li>
                    <li class="MT-dokhoangcach"><a title="Thước đo"><span class="mtmeasure1"></span></a>
                        <ul class="dokhoangcach" style="display: none;">
                            <li>
                                <input type="radio" name="type" value="none" id="noneToggle" onclick="openRuler(this);" checked="checked">
                                <label for="noneToggle">Làm mới</label>
                            </li>
                            <li>
                                <input type="radio" name="type" value="line" id="lineToggle" onclick="openRuler(this);">
                                <label for="lineToggle">Tính khoảng cách</label>
                            </li>
                            <li>
                                <input type="radio" name="type" value="polygon" id="polygonToggle" onclick="openRuler(this);">
                                <label for="polygonToggle">Tính diện tích</label>
                            </li>
                        </ul>
                    </li>
                    <li class="MT-search"><a data-toggle="modal" data-target="#myModal" title="Tìm kiếm"><span class="icon-search"></span>
                        </a></li>
                    <li class="MT-layers"><a title="Các lớp trên bản đồ"><span></span></a>
                        <ul class="MT-layers-list">
                            <!--                            <li style="font-weight: bold">Lớp nền</li>
                                                        <li style="font-weight: bold">
                                                            <input type="radio" checked name="baseLayer"
                                                                   id="google_base"  value="1">
                                                            <label>Google Map</label> </li>
                                                        <li style="font-weight: bold">
                                                            <input type="radio"  name="baseLayer"
                                                                   id="here_base" value="2">
                                                            <label>Here Map</label> </li>-->
                            <li style="font-weight: bold">Lớp bản đồ</li>
                            <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/bsc_rnc')}">
                                <li>

                                    <input type="checkbox"  name="neType"
                                           id="bsc_rnc" value="1">
                                    <label>BSC_RNC</label> 
                                    <div class="colorLayerBSC"></div>

                                </li>
                            </c:if>
                                 <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/bts')}">
                            <li >
                                <input type="checkbox"  name="neType"
                                       id="bts" value="2">
                                <label>BTS</label>
                                <div class="colorLayerBTS"></div>
                            </li>
                                 </c:if>
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/nodeb')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="nodeb" value="3">
                                <label>NODEB</label>
                                <div class="colorLayerNodeB"></div>
                            </li>
                             </c:if>
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/enodeb')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="enodeb" value="8">
                                <label>ENODEB</label>
                                <div class="colorLayereNodeB"></div>
                            </li>
                             </c:if>
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/cell2g')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="cell2g" value="5">
                                <label>CELL 2G</label>
                                <div class="colorLayereCell2g"></div>
                            </li>
                             </c:if>
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/cell3g')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="cell3g" value="6">
                                <label>CELL 3G</label>
                                <div class="colorLayereCell3g"></div>
                            </li>
                             </c:if>
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/cell4g')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="cell4g" value="7">
                                <label>CELL 4G</label>
                                <div class="colorLayereCell4g"></div>
                            </li>
                             </c:if>
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/nodeqh')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="nodeQH" value="9">
                                <label>NODE QH</label>
                                <div class="colorLayereQH"></div>
                            </li>
                             </c:if>
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/nodeda')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="nodeDA" value="10">
                                <label>NODE DA</label>
                                <div class="colorLayereDA"></div>
                            </li>
                             </c:if>
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/csht')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="building" value="4">
                                <label>CSHT</label>
                                <div class="colorLayereBuilding"></div>
                            </li> 
                             </c:if>
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="chk_province" value="12">
                                <label>PROVINCE</label>
                                <div class="colorLayereProvince"></div>
                            </li> 
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="chk_district" value="13">
                                <label>DISTRICT</label>
                                <div class="colorLayereDistrict"></div>
                            </li> 
                            <!--trunglk_start-->
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/cs_core')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="chk_cs_core" value="20">
                                <label>CS CORE</label>
                                <div class="colorLayerCsCore"></div>
                            </li>
                             </c:if>
                             <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'maps/ps_core')}">
                            <li>
                                <input type="checkbox"  name="neType"
                                       id="chk_ps_core" value="25">
                                <label>PS CORE</label>
                                <div class="colorLayerPsCore"></div>
                            </li> 
                             </c:if>
                            <!--trunglk_end-->
                        </ul>
                    </li>


                    <!--                    <li class="MT-toanmanhinh" onclick="fullMapDesktop()" ><a title="Toàn màn hình"><span></span>
                                            </a></li>-->
                </ul>
                <div id="iframeDetail" class="panel with-nav-tabs">
                    <a href="#" id="detail-closer"  class="ol-popup-closer"><img draggable="false" class="emoji" alt="✖" src="https://s0.wp.com/wp-content/mu-plugins/wpcom-smileys/twemoji/2/svg/2716.svg"></a>
                    <div class="panel-heading" style="padding: 5px 15px">
                        <ul class="nav nav-tabs" id="tabs" style="font-size: 10px">
                        </ul>
                    </div>
                    <div class="panel-body" style="padding: 0px 15px">
                        <div id="divDetail" class="tab-content">

                        </div>
                    </div>
                </div>
                <div id="popup" class="ol-popup">
                    <a href="#" id="popup-closer" class="ol-popup-closer"><img draggable="false" class="emoji" alt="✖" src="https://s0.wp.com/wp-content/mu-plugins/wpcom-smileys/twemoji/2/svg/2716.svg"></a>
                    <div id="popup-content">
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Code</th>
                                    <th>Object</th> 
                                    <th>Địa chỉ</th>  
                                    <th>Link cha con</th>
                                    <th>Link liên kết</th>
                                    <th>Sửa</th>
                                </tr>
                            </thead>
                            <tbody id="table-content">  
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="borough">
                    <a href="#" id="borough-closer" class="ol-popup-closer"><img draggable="false" class="emoji" alt="✖" src="https://s0.wp.com/wp-content/mu-plugins/wpcom-smileys/twemoji/2/svg/2716.svg"></a>
                    <h4 id="nameProvince"></h4>
                    <div class="divTotalFill" id="divcountBTS"><strong>BTS:</strong> <span id="countBTS">0</span></div> 
                    <div class="divTotalFill" id="divcountCell2G"><strong>Cell2G:</strong> <span id="countCell2G">0</span></div> 
                    <div class="divTotalFill" id="divcountNodeB"><strong>NodeB:</strong> <span id="countNodeB">0</span></div> 
                    <div class="divTotalFill" id="divcountCell3G"><strong>Cell3G:</strong> <span id="countCell3G">0</span></div> 
                    <div class="divTotalFill" id="divcountNodeB"><strong>eNodeB:</strong> <span id="counteNodeB">0</span></div> 
                    <div class="divTotalFill" id="divcountCell4G"><strong>Cell4G:</strong> <span id="countCell4G">0</span></div> 
                    <div id="whereFilter"></div>
                </div>
                <div id="noteLink">
                    <a href="#" class="notelink-closer ol-popup-closer" onclick ="hideLink()"><img draggable="false" class="emoji" alt="✖" src="https://s0.wp.com/wp-content/mu-plugins/wpcom-smileys/twemoji/2/svg/2716.svg"></a>
                    <label style="width:60px;">Link đến: </label><div  style="margin: 7px 20px 0 0; width: 100px; height: 4px; background-color: #DF01D7; float:right"></div>
                    <label style="width:50px">Link đi: </label><div style="margin: 7px 20px 0 0; width: 100px; height: 4px; background-color: #0000FF; float:right"></div>
                    <label style="width:50px">Uplink: </label><div style="margin: 7px 20px 0 0; width: 100px; height: 4px; background-color: #FF4000; float:right"></div>
                    <label style="width:50px">Downlink: </label><div style="margin: 7px 20px 0 0; width: 100px; height: 4px; background-color: #000000; float:right"></div>
                </div>
                <div id="divNeLink">
                    <a href="#" class="notelink-closer ol-popup-closer" onclick ="hideLink()"><img draggable="false" class="emoji" alt="✖" src="https://s0.wp.com/wp-content/mu-plugins/wpcom-smileys/twemoji/2/svg/2716.svg"></a>
                    <div id="table1NeLink">
                        <h4 style="text-align: center; color: blue" >Link đến <span class="codeNeLink"></span></h4>

                        <table  class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã Node</th>
                                    <th>Truyền dẫn</th>
                                    <th>Topo BroadBand</th> 
                                </tr>

                            </thead>
                            <tbody id="list1NeLink">                                                                   

                            </tbody>                                    
                        </table>
                    </div>
                    <div id="table2NeLink">
                        <h4 style="text-align: center; color: blue">Link đi <span class="codeNeLink"></span></h4>
                        <table  class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã Node</th>
                                    <th>Truyền dẫn</th> 
                                    <th>Topo BroadBand</th> 
                                </tr>

                            </thead>
                            <tbody id="list2NeLink">                                                                   

                            </tbody>                                    
                        </table>
                    </div>
                </div>
                <div id="map" class="map">
                    <div id="gmap" class="fill"></div>
                    <!-- <div style="margin-left: -200px;" id="olmap" class="fill"></div>-->
                    <div id="olmap" class="fill"></div>
                    <div class="maximize_divNav">
                        <div class="maximizeNav"></div>
                    </div>
                    <div class="conTrolPnlRight" style="display: none;">
                        <div class="titleRight"><p>MY LAYERS</p></div>

                        <div class="form_main content" >
<!--                            <h4>Object Layer</h4>
                            <table id="myLayer" class="table table-bordered table-hover">
                                <thead>
                                <th width="50">Active</th>
                                <th>Layer name</th>
                                <th width="52">Color</th>
                                <th width="50"></th>
                                <th width="50"></th>
                                <thead>

                                <tbody id="listLayer">
                                    <c:if test="${lstLayer !=  null}">
                                        <c:forEach var="item" items="${lstLayer}">

                                            <tr>
                                                <td style="text-align: center"><input type="checkbox" name="chkLayer" class="chkMyLayer" checked value="myLayer_${item.layerId}"></td>
                                                <td>${item.layerName}</td>
                                                <td><div class="colorLayer" data-id="${item.layerId}" style="background-color: ${item.styleColor}"></div></td>
                                                <td style="text-align: center">
                                                    <a href="#" data-toggle="modal" onclick="updateFormStyle(${item.layerId}, '${item.layerName}',${item.styleSize}, '${item.styleColor}', '${item.styleBorderColor}',${item.styleOpacity})"  data-target="#styleLayers" class="linkChangeStyleLayer"></a>
                                                </td>
                                                <td style="text-align: center"><a href="#" data-id="${item.layerId}" class="linkDeleteLayer"></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>-->
                            <h4>Event Layer</h4>
                            <table id="myEvent" class="table table-bordered table-hover">
                                <thead>
                                <th width="50">Active</th>
                                <th>Event name</th>
                                <th width="52">Color</th>
                                <th width="50"></th>
                                <th width="50"></th>
                                <thead>

                                <tbody id="listEvent">
                                    <c:if test="${myListLayer !=  null}">
                                        <c:forEach var="item" items="${myListLayer}">
                                            <tr>
                                                <td style="text-align: center"><input type="checkbox" name="chkLayer" class="chkMyLayer" checked value="myLayer_${item.layerId}"></td>
                                                <td>${item.layerName}</td>
                                                <td><div class="colorLayer" data-id="${item.layerId}" style="background-color: ${item.styleColor}"></div></td>
                                                <td style="text-align: center">
                                                    <a href="#" data-toggle="modal" onclick="updateFormStyle(${item.layerId})"  data-target="#styleLayers" class="linkChangeStyleLayer"></a>
                                                </td>
                                                <td style="text-align: center"><a href="#" data-id="${item.layerId}" class="linkDeleteLayer"></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
                            <div class="modal-footer" style="text-align: center">
<!--                                <button type="button" style="margin-right: 100px;" data-toggle="modal" data-target="#addLayer" class="btn btn-primary">Add Layer</button>-->
                                <button type="button" data-toggle="modal" data-target="#addEvent" class="btn btn-primary">Add Event</button>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- /.box-body -->
                <!--form popup-->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-tiCỎtle" id="myModalLabel"></h4>
                            </div>
                            <div class="modal-body" id="boxSearch">
                                
                                <div class="locationFilter">
                                    <div class="col-md-4">                            
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Latitude</label>
                                            <input type="number" id="locationLat" name="locationLat" placeholder="Latitude"  class="form-control">
                                        </div>
                                    </div>
                                    <div class="col-md-4">                            
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Longitude</label>
                                            <input type="number" id="locationLong" name="locationLong" placeholder="Longitude"  class="form-control">
                                        </div>
                                    </div>
                                    <div class="col-md-4">                            
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Bán kính(m)</label>
                                            <input type="number" id="radius" name="radius"  placeholder="Longitude" value="1000"  class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <!--tinhList-->
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Chọn tỉnh quản lý</label>
                                    <select name="tinhTpId" id="tinhTpId" class="form-control selectpicker" data-live-search="true"  onchange="getListHuyen(0);"> 
                                        <option value="${tinhTpId}">--- Tỉnh/Thành Phố ---</option>
                                        <c:forEach var="tinhBO" items="${tinhList}">
                                            <option  data-tokens="${tinhBO.tenTinhTp}"
                                                     value="${tinhBO.tinhTpId}"  
                                                     >${tinhBO.tenTinhTp}</option>
                                        </c:forEach>

                                    </select>  
                                </div> 

                                <div class="form-group">
                                    <label  for="exampleInputEmail1">Chọn huyện</label>
                                    <select name="quanHuyenId" id="quanHuyenId" class="form-control selectpicker" data-live-search="true" onchange="getListPhuongXa(0);"> 
                                        <option value="">--- Quận / Huyện ---</option>
                                    </select>  


                                </div>

                                <div class="form-group">
                                    <label  for="exampleInputEmail1">Chọn phường/xã</label>
                                    <select name="phuongXaId" id="phuongXaId" class="form-control selectpicker" data-live-search="true" > 
                                        <option value="">--- Phường / Xã ---</option>
                                    </select>      

                                </div>

                                <div class="form-group">
                                    <button onclick="return afterText()" class="btn btn-primary">Add Filter (+)</button>
                                </div>


                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                                <button type="button" id="sm" onclick="searchMap()" class="btn btn-primary">Tìm kiếm</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade ModelMap" id="ModelMap" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Thông tin chi tiết</h4>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" id="nodeId" class="form-control pull-right"/>
                                <input type="hidden" id="neTypeId" class="form-control pull-right"/>



                                <div id="detailDiv">

                                </div>

                                <!--
                                                                <a onclick="javascript:drawLinkNode(1)">Vẽ link tới node cha</a>
                                                                <br>
                                                                <a onclick="javascript:drawLinkNode(2)">Vẽ link tới node con</a>-->
                            </div>

                        </div>
                    </div>
                </div>

                <div class="modal fade ModelMap" id="editDialog" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Thông tin đối tượng</h4>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" id="nodeId" class="form-control pull-right"/>
                                <input type="hidden" id="neTypeId" class="form-control pull-right"/>



                                <div id="editDiv">

                                </div>
                            </div>

                        </div>
                    </div>
                </div>



            </div>
        </div>


        <!--form popup search-->
    </section>
    <div id="addFiller" style="display: none">
        <div class="groupFilter">
            <div class="col-md-2">                            
                <div class="form-group">
                    <select name="objectFill"  class="form-control objectFill"  onchange="changeObjectFill(this)">
                        <option value="-1">Chọn Object</option>
                        <option value="2">BTS</option>
                        <option value="3">NodeB</option>
                        <option value="8">eNodeB</option>
                        <option value="5">Cell2G</option>
                        <option value="6">Cell3G</option>
                        <option value="7">Cell4G</option>
                    </select>
                </div>
            </div>
            <div class="col-md-3">                            
                <div class="form-group">
                    <select name="column"  class="form-control column"  onchange="changeAtrColum(this)">
                        <option value="-1">Chọn thuộc tính</option>
                    </select>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <select name="filterType" class="form-control filterType"> 
                        <option value="Contains">Contains</option>
                        <option value="Not contains">Not contains</option>
                        <option value="startWith">startWith</option>
                        <option value="endWith">endWith</option>
                        <option value="NULL">NULL</option>
                        <option value="NOT NULL">NOT NULL</option>
                    </select>  
                </div>
            </div>                        
            <div class="col-md-2">
                <div class="form-group">
                    <input  name="value" class="form-control value_" placeholder="Giá trị" required="true"                        
                            type="text" value=" "/>                

                </div>
            </div>  
            <div class="col-md-2">
                <div class="form-group">
                    <button type="button" onclick="removeText(this)" class="btn btn-danger">Remove (-)</button>    
                </div>
            </div>
            <div class="clearfix" ></div>
        </div>
    </div>
    <div style="display: none">
        <select id="numberOption"> 
            <option value=">">></option>
            <option value=">=">>=</option>
            <option value="<"><</option>
            <option value="<="><=</option>
            <option value="NULL">NULL</option>
            <option value="NOT NULL">NOT NULL</option>
        </select> 
        <select id="varcharOption"> 
            <option value="Contains">Contains</option>
            <option value="Not contains">Not contains</option>
            <option value="startWith">startWith</option>
            <option value="endWith">endWith</option>
            <option value="NULL">NULL</option>
            <option value="NOT NULL">NOT NULL</option>
        </select> 
    </div>
    <div class="modal fade" id="styleLayers" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Change <span id="nameMyLayer"></span></h4>
                </div>
                <div class="modal-body">
                    <table class="borderless">
                        <tr>
                            <td  style="width: 150px">Opacity:</td>
                            <td>
                                <input type="hidden" class="txtEventId"/>
                                <input type="hidden" class="txtLayerId"/>
                                <input type="hidden" class="txtOpacity"/>
                                <div id="info_opacity">Opacity 50%</div>
                                <div id="slider" style="width:250px"></div></td>
                        </tr>
                        <tr>
                            <td >Color:</td>
                            <td><input type='text' class="pickColor"/>
                                <input type="hidden" value="#f00f00"  class="txtColor"/></td>
                        </tr>
                        <tr>
                            <td>Border color:</td>
                            <td><input type='text' class="pickBorderColor"/>
                                <input type="hidden" value="#f00f00" class="txtBorderColor"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Size:</td>
                            <td><input type="number" value="5" class="txtSize form-control" /></td>
                        </tr>
                        <tr>
                            <td>Start date:</td>
                            <td>   <input type="text" class="form-control date_form"  name="startTime" id="startTime" required="true"
                                          value="" /></td>
                        </tr>
                        <tr>
                            <td>End date:</td>
                            <td><input type="text" class="form-control date_form"  name="endTime" id="endTime"
                                       value="" /></td>
                        </tr>
                    </table>

                </div>
                <div class="modal-footer">
                    <button id="btUpdateStyle" onclick="updateStyle()" type="button" class="btn btn-success" data-dismiss="modal">Update</button> 
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="addLayer" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Add Object</span></h4>
                </div>
                <div class="modal-body" id="boxAddLayer">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Tên Layer</label>
                        <input  name="nameLayer" id="nameLayer" class="form-control" placeholder="Tên Layer" required="true"                        
                                type="text" value=" "/>  
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Chọn Object</label>
                        <select name="objectLayer" id="objectLayer" class="form-control"> 
                            <option value="-1">--- Object ---</option>
                            <option  value="2">BTS</option>
                            <option  value="3">NODEB</option>
                            <option  value="8">ENODEB</option>
                            <option  value="5">CELL2G</option>
                            <option  value="6">CELL3G</option>
                            <option  value="7">CELL4G</option>
                        </select>  
                    </div> 

                    <div class="form-group">
                        <button onclick="return afterLayerText()" id="addFormLayer" disabled class="btn btn-primary">Add Filter (+)</button>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="btAddLayer" onclick="SubmitAddLayer()" type="button" class="btn btn-success" >Add</button> 
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="addEvent" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Add Event</span></h4>
                </div>
                <div class="modal-body" id="boxAddLayer">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Chọn Event</label>
                        <select name="eventLayer" id="eventLayer" class="form-control"> 
                            <option value="-1">--- Object ---</option>
                            <c:forEach var="item" items="${listEvent}" varStatus="status">
                                <option  value="${item.eventId}">${item.eventName}</option>
                            </c:forEach>
                        </select>  
                    </div> 
                </div>
                <div class="modal-footer">
                    <button id="btAddEvent" onclick="SubmitAddEvent()" type="button" class="btn btn-success" >Add</button> 
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>


    <script type="text/javascript">
        var isRuler = false;
        var mapHereId = 'uiZgbRzrpg5YN25bffGI';
        var mapHereCode = 'xQaoYM7OaRQImDYcSQfGAQ';
        var tinhId = '${tinhTpId}';
        var quanHuyenId = ${quanHuyenId};
        var phuongXaId = ${phuongXaId};
        var url = "https://oss.vnpt.vn/geoserver/RIMS/wms"
                var map;
        var gmap;
        var bts_layer;
        var nodeb_layer;
        var enodeb_layer;
        var bsc_rnc_layer;
        var cell2g_layer;
        var cell3g_layer;
        var cell4g_layer;
        var nodeQH_layer;
        var nodeDA_layer;
        var building_layer;
        var province_layer;
        var district_layer;
        var base_mapHere;
//trunglk_start
        var csCore_layer;
        var psCore_layer;
        var linkChaCon_layer;
        var linkNodeCha_layer;
        var linkDi_layer;
        var linkDen_layer;
//trunglk_end

        var zoomLevel1 = 7;
        var zoomLevel2 = 8;
        var zoomLevel3 = 12;
        var zoomLevel4 = 14;
        var zoomLevel5 = 16;
        var neTypeBSC = 1;
        var neTypeBTS = 2;
        var neTypeNodeB = 3;
        var neTypeCell2G = 5;
        var neTypeCell3G = 6;
        var neTypeCell4G = 7;
        var neTypeEnodeB = 8;
        var neTypeNodeQh = 9;
        var neTypeNodeDA = 10;
        var neTypeBuilding = 4;
        var neTypeProvince = 12;
        var neTypeDistrict = 13;
//trunglk_start
        var neTypeCsCore = 20;
        var neTypePsCore = 25;
        var neTypeLinkChaCon = 21;
        var neTypeLinkDi = 22;
        var neTypeLinkDen = 23;
//trunglk_end        
        var mapZoom;
        var geocoder = new google.maps.Geocoder();
        var container = document.getElementById('popup');
        var closer = document.getElementById('popup-closer');
        var olMapDiv = document.getElementById('olmap');
        var view;
        var overlay = new ol.Overlay(/** @type {olx.OverlayOptions} */({
        element: container,
                autoPan: true,
                autoPanAnimation: {
                duration: 250
                }
        }));
        var arrLayer = {};
        function init() {
        // xet true để bật công cụ (bản đồ, vệ tinh) lên
        gmap = new google.maps.Map(document.getElementById('gmap'), {
        center: {lat: 16.672373, lng: 106.842041},
                zoom: 6,
                disableDefaultUI: false,
                keyboardShortcuts: false,
                draggable: false,
                disableDoubleClickZoom: true,
                scrollwheel: false,
                streetViewControl: false,
                mapTypeControl: false
            
        });
       
        bts_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:btsView',
                },
        })
        });
        nodeb_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:nodebView'
                }
        })
        });
        enodeb_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:enodeBView'
                }
        })
        });
        cell2g_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:cell2G'
                }
        })
        });
        cell3g_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:cell3G'
                }
        })
        });
        cell4g_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:cell4G'
                }
        })
        });
        nodeQH_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:nodeQH_view'
                }
        })
        });
        nodeDA_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:nodeDA_view'
                }
        })
        });
        building_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:building_View'
                }
        })
        });
        bsc_rnc_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:bsc_rnc_view'
                }
        })
        });
        province_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:province_polygon'
                }
        })
        });
        district_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:district_polygon'
                }
        })
        });
//trunglk_start
        csCore_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:CS_Core'
                }
        })
        });
        psCore_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:PS_CORE'
                }
        })
        });
        linkChaCon_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:link_cha_con_layer'
                }
        })
        });
        linkNodeCha_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:link_node_cha_layer'
                }
        })
        });
        linkDi_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:ne_link_di_layer'
                }
        })
        });
        linkDen_layer = new ol.layer.Tile({
        source: new ol.source.TileWMS({
        url: url,
                params: {
                'LAYERS': 'RIMS:ne_link_den_layer'
                }
        })
        });
        linkChaCon_layer.setVisible(false);
        linkNodeCha_layer.setVisible(false);
        linkDi_layer.setVisible(false);
        linkDen_layer.setVisible(false);
//trunglk_end


        var base_map = new ol.layer.Tile({// 
        source: new ol.source.OSM(),
        });
        base_mapHere = new ol.layer.Tile({// 
        preload: Infinity,
                source: new ol.source.XYZ({
                url: 'https://{1-4}.base.maps.cit.api.here.com' +
                        '/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/256/png' +
                        '?app_id=' + mapHereId + '&app_code=' + mapHereCode,
                })
        });
        var layers = [
                base_mapHere,
                province_layer,
                district_layer,
                bts_layer,
                nodeb_layer,
                enodeb_layer,
                cell2g_layer,
                cell3g_layer,
                cell4g_layer,
                building_layer,
                nodeDA_layer,
                nodeQH_layer,
                bsc_rnc_layer,
                csCore_layer,
                psCore_layer,
                linkChaCon_layer,
                linkNodeCha_layer,
                linkDi_layer,
                linkDen_layer

        ];
        view = new ol.View({
        center: ol.proj.fromLonLat([108.012, 18.012]),
                zoom: 6
        });
        // dong bo luon he truc toa do
//        tú béo định bỏ chỗ dưới này chứ gì? anh thử bỏ rồi nó còn lệch hơn
        var center = ol.proj.transform(view.getCenter(), 'EPSG:3857', 'EPSG:4326');
        gmap.setCenter(new google.maps.LatLng(center[1], center[0]));
        view.on('change:center', function () {
            var center = ol.proj.transform(view.getCenter(), 'EPSG:3857', 'EPSG:4326');
            gmap.setCenter(new google.maps.LatLng(center[1], center[0]));
        });
        view.on('change:resolution', function () {
            gmap.setZoom(view.getZoom());
        });
        ;
        map = new ol.Map({
        target: olMapDiv,
                layers: layers,
                view: view,
                overlays: [overlay], //them khai bao overlays
                interactions: ol.interaction.defaults({
                altShiftDragRotate: false,
                        dragPan: false,
                        rotate: false
                }).extend([new ol.interaction.DragPan({kinetic: null})]),
        });
        olMapDiv.parentNode.removeChild(olMapDiv);
        gmap.controls[google.maps.ControlPosition.TOP_LEFT].push(olMapDiv);
        
        loadMyLayer();
        displayLayer();
        map.on("moveend", function () {
        displayLayer();
        });
        map.on('singleclick', function (evt) {
        $("#table-content").html('');
        $('#tabs').html('');
        $('#divDetail').html('');
        var view = map.getView();
        var viewResolution = view.getResolution();
        var neTypes = document.getElementsByName('neType');
        for (var i = 0, neType; neType = neTypes[i]; i++) {
        if (neType.checked) {
        var neTypeName = neType.value.replace(/'/g, '\\\'');
        var source;
        var nameType = '';
        if (neTypeName == neTypeBTS.toString())
        {
        source = bts_layer.getSource();
        nameType = 'BTS';
        } else if (neTypeName == neTypeNodeB.toString())
        {
        source = nodeb_layer.getSource();
        nameType = 'NodeB';
        } else if (neTypeName == neTypeEnodeB.toString())
        {
        source = enodeb_layer.getSource();
        nameType = 'EnodeB';
        } else if (neTypeName == neTypeCell2G.toString())
        {
        source = cell2g_layer.getSource();
        nameType = 'CELL2G';
        } else if (neTypeName == neTypeCell3G.toString())
        {
        source = cell3g_layer.getSource();
        nameType = 'CELL3G';
        } else if (neTypeName == neTypeCell4G.toString())
        {
        source = cell4g_layer.getSource();
        nameType = 'CELL4G';
        } else if (neTypeName == neTypeNodeDA.toString())
        {
        source = nodeDA_layer.getSource();
        nameType = 'Trạm Dự Án';
        } else if (neTypeName == neTypeNodeQh.toString())
        {
        source = nodeQH_layer.getSource();
        nameType = 'Trạm Quy Hoạch';
        } else if (neTypeName == neTypeBuilding.toString())
        {
        source = building_layer.getSource();
        nameType = 'Building';
        } else if (neTypeName == neTypeBSC.toString())
        {
        source = bsc_rnc_layer.getSource();
        nameType = 'BSC_RNC';
        }
//trunglk_start
        else if (neTypeName == neTypeCsCore.toString())
        {
        source = csCore_layer.getSource();
        nameType = 'CS_CORE';
        } else if (neTypeName == neTypePsCore.toString())
        {
        source = psCore_layer.getSource();
        nameType = 'PS_CORE';
        }
//trunglk_end

        var url = source.getGetFeatureInfoUrl(
                evt.coordinate, viewResolution, view.getProjection(),
        {'INFO_FORMAT': 'application/json', 'FEATURE_COUNT': 50});
        if (url) {
        getListNodeClick(url, nameType, evt);
        }
        }
        }
        // sort tab

        });
        
        //end init 
        }

        function filterLayerRIMS(layer, where) {

        }

        function getListNodeClick(url, nameType, evt) {

        $.ajax({
        type: "POST",
                url: url,
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                success: function (n) {
                //alert(n.features.length);    
                if (n.features.length > 0)
                {
                //$("#table-content").html('');
                var html = '';
                var count = $("#table-content tr").size();
                var postdata = '';
                for (var i = 0; i < n.features.length; i++) {
                count++;
                //                if (count > 10)
                //                        break;
                var feature = n.features[i];
                var featureAttr = feature.properties;
                postdata += 'nodeId=' + featureAttr['NODE_ID'] + '&';
                postdata += 'neTypeId=' + featureAttr['NE_TYPE_ID'] + '&';
                var neTypeLocation = featureAttr['NE_TYPE_ID'];
//                html += '<tr><td>' + count + '</td>';
//                html += '<td><a href="#" onclick="approveDialogMap(' + featureAttr['NODE_ID'] + ',' + featureAttr['NE_TYPE_ID'] + ')" >' + featureAttr['MA_NODE'] + ' </a></td>';
//                html += '<td>' + nameType + '</td>';
//                html += '<td>' + featureAttr['DIA_CHI'] + '</td>';
//                html += '<td>';
//                if (neTypeLocation == 2 || neTypeLocation == 3 || neTypeLocation == 5 || neTypeLocation == 6 || neTypeLocation == 7 || neTypeLocation == 8)
//                        html += '<a href="#" onclick="setDisplayLink(1,' + featureAttr['NODE_ID'] + ',\'' + featureAttr['MA_NODE'] + '\')" > Chi tiết </a>';
//                html += '</td>';
//                html += '<td>';
//                if (neTypeLocation !== 4 && neTypeLocation !== 5 && neTypeLocation !== 6 && neTypeLocation !== 7 && neTypeLocation !== 8)
//                        html += '<a href="#" onclick="setDisplayLink(2,' + featureAttr['NODE_ID'] + ',\'' + featureAttr['MA_NODE'] + '\')" > Chi tiết </a>';
//                html += '</td>';
//                html += '</tr>';
                }

                postdata = postdata.substring(0, postdata.length - 1); ;
                $.ajax({
                type: "GET",
                        url: "${pageContext.request.contextPath}/mapDetail/detailGeo",
                        data: postdata,
                        success: function(data) {
//                            console.log(data);
                        var isset = false;
                        var neNew = $(data).find('#tabs li').attr('data-id');
                        $("#tabs li").each(function (i) {
                        if ($(this).attr('data-id') == neNew){
                        isset = true;
                        }
                        });
                        if (!isset){
                        if ($('#tabs').text() == ''){
                        $('#tabs').append($(data).find('#tabs').html().replace('class=""', 'class="active"'));
                        $('#divDetail').append($(data).find('#divContent').html().replace('tab-pane fade', 'tab-pane fade active in'));
                        } else{
                        $('#tabs').append($(data).find('#tabs').html());
                        $('#divDetail').append($(data).find('#divContent').html());
                        }
                        $("#tabs").html($("#tabs").children('li').sort(function(a, b){
                        return ($(b).data('position')) < ($(a).data('position')) ? 1 : - 1;
                        }));
                        }
                        }
                });
                $('#iframeDetail').show();
                // table node old
//                    $("#table-content").append(html);
//                        overlay.setPosition(evt.coordinate);
                }
                else{
                $('#iframeDetail').hide();
//                        overlay.setPosition(undefined);
//                        closer.blur();
                }

                }
        });
        //return arrayNode;
        }

        function hideLink(){
        linkChaCon_layer.setVisible(false);
        linkNodeCha_layer.setVisible(false);
        linkDi_layer.setVisible(false);
        linkDen_layer.setVisible(false);
        }

        function displayLayer() {
        var view = map.getView();
        mapZoom = view.getZoom();
        bts_layer.setVisible(false);
        nodeb_layer.setVisible(false);
        enodeb_layer.setVisible(false);
        cell2g_layer.setVisible(false);
        cell3g_layer.setVisible(false);
        cell4g_layer.setVisible(false);
        nodeDA_layer.setVisible(false);
        nodeQH_layer.setVisible(false);
        building_layer.setVisible(false);
        bsc_rnc_layer.setVisible(false);
        province_layer.setVisible(false);
        district_layer.setVisible(false);
//trunglk_start
        csCore_layer.setVisible(false);
        psCore_layer.setVisible(false);
//trunglk_end

        if ($("#here_base").is(":checked")) {
        base_mapHere.setVisible(true);
        } else {
        base_mapHere.setVisible(false);
        }

        var neTypes = document.getElementsByName('neType');
        var filter = [];
        for (var i = 0, neType; neType = neTypes[i]; i++) {
        if (neType.checked) {
        var neTypeName = neType.value.replace(/'/g, '\\\'');
        if (neTypeName === neTypeProvince.toString()) {
        setDisplayLayer(neTypeName);
        }
        //filter.push("'" + neTypeName + "'");
//        if (mapZoom <= zoomLevel1) {
//        if (neTypeName === neTypeBSC.toString() || neTypeName === neTypeCsCore.toString() || neTypeName === neTypePsCore.toString()) {
//        // show bsc
//        setDisplayLayer(neTypeName);
//        }
//        } else  if ((mapZoom >= zoomLevel1) && (mapZoom < zoomLevel5)) {
        if (mapZoom <= zoomLevel5) {
        if (neTypeName === neTypeBSC.toString() || neTypeName === neTypeCsCore.toString() || neTypeName === neTypePsCore.toString()
                || neTypeName === neTypeBTS.toString()
                || neTypeName === neTypeNodeB.toString() || neTypeName === neTypeEnodeB.toString()
                || neTypeName === neTypeNodeQh.toString() || neTypeName === neTypeNodeDA.toString() || neTypeName === neTypeBuilding.toString()) {
        // show all - cell
        setDisplayLayer(neTypeName);
        }

        if (mapZoom >= zoomLevel3) {
        if (neTypeName === neTypeDistrict.toString())
        {
        // show district
        setDisplayLayer(neTypeName);
        }

        }
        }
        if (mapZoom >= zoomLevel4) {
        if (neTypeName === neTypeBSC.toString() || neTypeName === neTypeCsCore.toString() || neTypeName === neTypePsCore.toString()
                || neTypeName === neTypeBTS.toString()
                || neTypeName === neTypeNodeB.toString() || neTypeName === neTypeEnodeB.toString()
                || neTypeName === neTypeCell2G.toString() || neTypeName === neTypeCell3G.toString()
                || neTypeName === neTypeCell4G.toString() || neTypeName === neTypeNodeQh.toString() || neTypeName === neTypeNodeDA.toString() || neTypeName === neTypeBuilding.toString()) {
        setDisplayLayer(neTypeName);
        }
        }
        }
        }


        // load mylayer
//            var neTypes = document.getElementsByName('chkLayer');
//            var filter = [];
//            for (var i = 0, neType; neType = neTypes[i]; i++) {
//                if (neType.checked) {
//                    var neTypeName = neType.value.replace(/'/g, '\\\'');
//                    if (neTypeName === neTypeProvince.toString()) {
//                        setDisplayLayer(neTypeName);
//                    }
//                }
//            }

        $(".chkMyLayer").click(function () {

        var mLayers = map.getLayers();
        var nameLayer = $(this).attr('value');
        if (!$(this).prop('checked')) {
        mLayers.forEach(function(layer, i) {

        if (layer.get('name') === nameLayer)
        {
        console.log(layer.get('name'));
        layer.setVisible(true);
        }
        });
        } else {
        mLayers.forEach(function(layer, i) {
        if (layer.get('name') === nameLayer)
        {
        console.log(layer.get('name'));
        layer.setVisible(false);
        }
        });
        }
        });
        }
//trunglk_start
        function setDisplayLink(type, nodeId, code){
        $('#iframeDetail').hide();
        var where = ' AND 1 = 1 ';
        var whereNodeCha = where;
        var whereNodeCon = where;
        var wheredi = where;
        var whereden = where;
//            alert('type :'+ type);
//            alert('nodeId :'+ nodeId);
        if (type === 1){
        where += " AND  NODE_CHA_ID = " + nodeId;
        linkChaCon_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        linkChaCon_layer.setVisible(true);
        whereNodeCha += " AND NODE_ID = " + nodeId;
        linkNodeCha_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + whereNodeCha});
        linkNodeCha_layer.setVisible(true);
        } else if (type === 2){
        wheredi = " AND NODE1_ID = " + nodeId;
        linkDi_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + wheredi});
        linkDi_layer.setVisible(true);
        whereden = " AND NODE2_ID = " + nodeId;
        linkDen_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + whereden});
        linkDen_layer.setVisible(true);
        }

        $('#noteLink').show();
        $('.codeNeLink').text(code);
        if (type == 2){ // link lien ket
        $.get("${pageContext.request.contextPath}/mapGeo/getNode1Link/" + nodeId, function (data) {
        var html = "";
        if (data.length > 0) {
        $('#table1NeLink').show();
        var i = 0;
        data.forEach(function (entry) {
        i++;
        html += "<tr>"
                html += "<td>" + i + "</td>";
        html += "<td><a href='#' onclick='callNode(" + entry.lat + "," + entry.lon + ");' > " + entry.code + "</a></td>";
        html += "<td>" + entry.tenLoaiTruyenDan + "</td>";
        html += "<td><a href='#' onclick='viewTopo(" + nodeId + "," + entry.nodeId + ");' >View Topo</a></td>";
        html += "</tr>";
        });
        $('#list1NeLink').html(html);
        $('#divNeLink').show();
        }
        else {
        $('#table1NeLink').hide();
        }
        });
        $.get("${pageContext.request.contextPath}/mapGeo/getNode2Link/" + nodeId, function (data) {
        var html = "";
        if (data.length > 0) {
        var i = 0;
        $('#table2NeLink').show();
        data.forEach(function (entry) {
        i++;
        html += "<tr>"
                html += "<td>" + i + "</td>";
        html += "<td><a href='#' onclick='callNode(" + entry.lat + "," + entry.lon + ");' > " + entry.code + "</a></td>";
        html += "<td>" + entry.tenLoaiTruyenDan + "</td>";
        html += "<td><a href='#' onclick='viewTopo(" + nodeId + "," + entry.id2 + ");' >View Topo</a></td>";
        html += "</tr>";
        });
        $('#list2NeLink').html(html);
        $('#divNeLink').show();
        }
        else{
        $('#table2NeLink').hide();
        }
        });
        }
        if (type == 1) // Link cha con
        {
        $.get("${pageContext.request.contextPath}/mapGeo/getchaid/" + nodeId, function (data) {
        var html = "";
        if (data.length > 0) {
        var i = 0;
        $('#table2NeLink').show();
        data.forEach(function (entry) {
        i++;
        html += "<tr>"
                html += "<td>" + i + "</td>";
        html += "<td><a href='#' onclick='callNode(" + entry.lat + "," + entry.lon + ");' > " + entry.code + "</a></td>";
        html += "<td></td>";
        html += "<td><a href='#' onclick='viewTopo(" + nodeId + "," + entry.id + ");' >View Topo</a></td>";
        html += "</tr>";
        });
        $('#list2NeLink').html(html);
        $('#divNeLink').show();
        }
        else{
        $('#table2NeLink').hide();
        }
        });
        }
        overlay.setPosition(undefined);
        closer.blur();
        }
//trunglk_end

        function setDisplayLayer(neTypeId)
        {
        var where = ' AND 1 = 1 ';
        var whereProvince = where;
        var whereDistrict = where;
        if ($('#tinhTpId').val() !== '')
                tinhId = $('#tinhTpId').val();
        if ($('#quanHuyenId').val() !== '')
                quanHuyenId = $('#quanHuyenId').val();
        if (tinhId !== '0' && $('#tinhTpId').val() !== '') {
            where += " AND building.TINHTP_ID IN (" + tinhId + ') ';
            whereProvince = where;
        }
        if (quanHuyenId !== 0 && $('#quanHuyenId').val() !== '') {
        where += " AND building.QUANHUYEN_ID IN ( " + quanHuyenId + " ) ";
        whereDistrict = where;
        }
        if ($('#phuongXaId').val() !== '') {
        phuongXaId = $('#phuongXaId').val();
        where = where + " AND building.PHUONGXA_ID IN( " + phuongXaId + " ) ";
        }
       
        // check addfilter
        var listObjectFill = "";
        $("#boxSearch .groupFilter").each(function (i) {
        listObjectFill += $(this).find('.objectFill').val() + ",";
        where = where + convertQueryFilter(neTypeId, $(this).find('.objectFill').val(), $(this).find('.column').val(), $(this).find('.filterType').val(), $(this).find('.value_').val());
        });
        if (listObjectFill.length > 0)
        {
        if (listObjectFill.indexOf(neTypeId + ",") == - 1)
                where = " AND 1 = 2 ";
        }
        where = EscapeCommasSemiColons(where);
        //console.log(where);
        if (neTypeId === neTypeBSC.toString()) {
        bsc_rnc_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        bsc_rnc_layer.setVisible(true);
        } else if (neTypeId === neTypeBTS.toString()) {
        //alert(where);
        bts_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        bts_layer.setVisible(true);
        } else if (neTypeId === neTypeEnodeB.toString()) {
        enodeb_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        enodeb_layer.setVisible(true);
        } else if (neTypeId === neTypeNodeB.toString()) {
        nodeb_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        nodeb_layer.setVisible(true);
        } else if (neTypeId === neTypeCell2G.toString()) {
        cell2g_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        cell2g_layer.setVisible(true);
        } else if (neTypeId === neTypeCell3G.toString()) {
        cell3g_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        cell3g_layer.setVisible(true);
        } else if (neTypeId === neTypeCell4G.toString()) {
        cell4g_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        cell4g_layer.setVisible(true);
        } else if (neTypeId === neTypeBuilding.toString()) {
        building_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        building_layer.setVisible(true);
        } else if (neTypeId === neTypeNodeDA.toString()) {
        nodeDA_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        nodeDA_layer.setVisible(true);
        } else if (neTypeId === neTypeNodeQh.toString()) {
        nodeQH_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        nodeQH_layer.setVisible(true);
        } else if (neTypeId === neTypeProvince.toString()) {
        province_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + whereProvince});
        province_layer.setVisible(true);
        } else if (neTypeId === neTypeDistrict.toString()) {
        district_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + whereDistrict});
        district_layer.setVisible(true);
        }
//trunglk_start
        else if (neTypeId === neTypeCsCore.toString()) {
        csCore_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        csCore_layer.setVisible(true);
        } else if (neTypeId === neTypePsCore.toString()) {
        psCore_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where});
        psCore_layer.setVisible(true);
        }
//trunglk_end
        }


        var flagFullMap = false;
        function fullMapDesktop() {
        $('.navbar-btn').click();
        if (!flagFullMap) {
        $('.navbar').hide();
        flagFullMap = true;
        $("#map").
                css("width", '100%').
                css("height", $(window).height() - 50);
        google.maps.event.trigger(gmap, 'resize');
        } else
        {
        $('.navbar').show();
        flagFullMap = false;
        $("#map").css("width", '100%').
                css("height", $(window).height() - 70);
        google.maps.event.trigger(gmap, 'resize');
        }
        map.getView().setCenter(ol.proj.transform([gmap.getCenter().lng(), gmap.getCenter().lat()], 'EPSG:4326', 'EPSG:3857'));
        }

        var vectorLayer = ol.source.Vector({});
        function searchMap() {
            var loca_lat = $('#locationLat').val();
            var loca_long = $('#locationLong').val();
            
            map.removeLayer(vectorLayer);
            if( loca_lat != '' && loca_long != '') {
                map.getView().setCenter(ol.proj.transform([Number(loca_long), Number(loca_lat)], 'EPSG:4326', 'EPSG:3857'));               
                map.getView().setZoom(16);
                var banKinh =  Number($('#radius').val());
                var circle = new ol.geom.Circle(
                    ol.proj.transform([Number(loca_long), Number(loca_lat)], 'EPSG:4326', 'EPSG:3857'),
                    banKinh
                );
                
                // Features
                var circleFeature = new ol.Feature(circle);

                // Source and vector layer
                var vectorSource = new ol.source.Vector({
                    projection: 'EPSG:4326',
                    features: [circleFeature]
                });

                var style = new ol.style.Style({
                    fill: new ol.style.Fill({
                        color: 'rgba(255, 100, 50, 0.3)'
                    }),
                    stroke: new ol.style.Stroke({
                        width: 2,
                        color: 'rgba(255, 100, 50, 0.8)'
                    }),
                    image: new ol.style.Circle({
                        fill: new ol.style.Fill({
                            color: 'rgba(55, 200, 150, 0.5)'
                        }),
                        stroke: new ol.style.Stroke({
                            width: 1,
                            color: 'rgba(55, 200, 150, 0.8)'
                        }),
                        radius: 7
                    }),
                });

                vectorLayer = new ol.layer.Vector({
                    source: vectorSource,
                    style: style
                });

                map.addLayer(vectorLayer);
                
                $('#myModal').modal('hide');
                return;
            }
        
        var checkValue = false;
        $("#boxSearch .groupFilter").each(function (i) {
        if ($(this).find('.value_').val().trim() == '') {
        checkValue = true;
        }
        });
        if (checkValue)
        {
        alert('Bạn phải nhập dữ liệu cho filter');
        $('#myModal').modal('show');
        } else
        {
        if ($("#tinhTpId").val() == '')
        {
        //alert('Bạn phải chọn tỉnh/thành phố');
        //$('#myModal').modal('show');
        province_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:'});
        district_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:'});
        $('#myModal').modal('hide');
        } else
        {
        $('#myModal').modal('hide');
        var address = $("#tinhTpId option:selected").text().trim();
        if ($("#quanHuyenId").val() !== '')
        {
        address = $("#quanHuyenId option:selected").text().trim() + " " + $("#tinhTpId option:selected").text().trim();
        }
        if ($("#phuongXaId").val() !== '')
        {
        address = $("#phuongXaId option:selected").text().trim() + " " + $("#quanHuyenId option:selected").text().trim() + " " + $("#tinhTpId option:selected").text().trim();
        }



        // dua den vi tri can search      
        geocoder.geocode({
        address: address.trim()
        }, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
        if($("#tinhTpId").val().indexOf(',') == -1){
            alert($("#tinhTpId").val().indexOf(','));
//            alert(1)
            map.getView().setCenter(ol.proj.transform([results[0].geometry.location.lng(), results[0].geometry.location.lat()], 'EPSG:4326', 'EPSG:3857'));
            map.getView().setZoom(15);
//            alert(2)
        }
        //map.setCenter(results[0].geometry.location);
        if ($("#phuongXaId").val() !== '')
        {
        
        district_layer.setVisible(false);
        province_layer.setVisible(false);
        //$('#chk_district').prop('checked', true);
        $('#chk_district').iCheck('uncheck');
        $('#chk_province').iCheck('uncheck');
        } else {
        if ($("#quanHuyenId").val() == '' && $("#tinhTpId").val().indexOf(',') == -1)
        {
            map.getView().setZoom(9);
        var whereProvince = "  AND TINHTP_ID IN (" + $("#tinhTpId").val() + ') ';
        whereProvince = EscapeCommasSemiColons(whereProvince);
        province_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + whereProvince});
        district_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + whereProvince});
        province_layer.setVisible(true);
        //$('#chk_province').prop('checked', true);
        $('#chk_province').iCheck('check');
        }
        //map.setZoom(9); // 9 la tinh
        else if ($("#quanHuyenId").val() != '' && $("#tinhTpId").val().indexOf(',') == -1)
        {
        map.getView().setZoom(12);
        var where_district = " AND QUANHUYEN_ID IN ( " + $("#quanHuyenId").val() + " ) ";
        district_layer.getSource().updateParams({"VIEWPARAMS": 'WHERE_:' + where_district});
        district_layer.setVisible(true);
        province_layer.setVisible(false);
        //$('#chk_district').prop('checked', true);
        $('#chk_district').iCheck('check');
        $('#chk_province').iCheck('uncheck');
        }
        }
        //map.setZoom(12); // 12 la quan huyen
        }

        });
        // Hien thi geo tinh day va so luong thong so chua co geo cua huyen nen thay huyen thi stop
        if ($("#quanHuyenId").val() == '')
        {
        //var where = 'TINHTP_ID =' + $("#tinhTpId").val();
        //filterGeoMap(layerGeoProvince, tableIdTinhTp, map, where);
        }
        // count node
        //$('#borough').show();
        $('#nameProvince').text(address);
        }
            if($("#tinhTpId").val().indexOf(',') == -1){
                getCountNode();
            }
//        enable ket qua tim kiem khi dung filter
        $('#bts').iCheck('check');
        bts_layer.setVisible(true);
        $('#nodeb').iCheck('check');
        nodeb_layer.setVisible(true);
        $('#enodeb').iCheck('check');
        enodeb_layer.setVisible(true);
        $('#cell2g').iCheck('check');
        cell2g_layer.setVisible(true);
        $('#cell3g').iCheck('check');
        cell3g_layer.setVisible(true);
        $('#cell4g').iCheck('check');
        cell4g_layer.setVisible(true);
//        enable ket qua tim kiem khi dung filter end            
        }
        }

        function afterText() {
        $('#boxSearch').append($('#addFiller').html());
        return false;
        }


        function afterLayerText() {
        $('#boxAddLayer').append($('#addFiller').html());
        }
        function removeText(that) {
        $(that).parents('.groupFilter').remove();
        }

        function changeObjectFill(that){
        $.get("${pageContext.request.contextPath}/mapGeo/fillAttrObject/" + $(that).val(), function (data) {
        var html = '<option value="">Chọn thuộc tính</option>';
        if (data.length > 0) {
        data.forEach(function (entry) {
        var htmlx = '<option  value="' + entry.columnId + '">' + entry.columnName + '</option>';
        html += htmlx;
        });
        $(that).parents('.groupFilter').find('.column').html(html);
        };
        });
        }
        function changeAtrColum(that)
        {
        var colum = $(that).val();
        var i = colum.split(":")[0];
        if (i == 0) {
        $(that).parents('.groupFilter').find('.filterType').html($('#varcharOption').html());
        } else if (i == 1) {
        $(that).parents('.groupFilter').find('.filterType').html($('#numberOption').html());
        }
        }
        function convertQueryFilter(neTypeId, type, colum, logic, value)
        {
        value = value.trim();
        var str = " ";
        if (type == neTypeId)
        {
        // logic
        str += " AND " + colum;
        if (logic == "Contains")
                str += " like '%" + value + "%'";
        else if (logic == "startWith")
                str += " like '" + value + "%'";
        else if (logic == "endWith")
                str += " like '%" + value + "'";
        else if (logic == "NULL")
                str += " IS NULL ";
        else if (logic == "NOT NULL")
                str += " IS NOT NULL ";
        // toan tu
        else if (logic == "<")
                str += " < " + value;
        else if (logic == "<=")
                str += " <= " + value;
        else if (logic == ">")
                str += " > " + value;
        else if (logic == ">=")
                str += " >= " + value;
        }
        return str;
        }

        function convertQueryTotalFilter(colum, logic, value)
        {
        value = value.trim();
        //alert(type + neTypeId);
        var str = "";
        // logic
        str = " AND " + colum;
        if (logic == "Contains")
                str += " like '%" + value + "%'";
        else if (logic == "startWith")
                str += " like '" + value + "%'";
        else if (logic == "endWith")
                str += " like '%" + value + "'";
        else if (logic == "NULL")
                str += " IS NULL ";
        else if (logic == "NOT NULL")
                str += " IS NOT NULL ";
        // toan tu
        else if (logic == "<")
                str += " < " + value;
        else if (logic == "<=")
                str += " <= " + value;
        else if (logic == ">")
                str += " > " + value;
        else if (logic == ">=")
                str += " >= " + value;
        return str;
        }
        $(document).ready(function () {
        if ($('#tinhTpId').val() != ''){
        getListHuyen($('#tinhTpId').val());
        }

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
        $('.iCheck-helper').click(function () {
        var parent = $(this).parent().get(0);
        $(parent).find('input').click();
        displayLayer();
        });
        $('#map').css("height", $(window).height() - 70).css("width", '100%');
        $('.notelink-closer').click(function () {
        $('#noteLink').hide(1000);
        $('#divNeLink').hide(1000);
        });
        $('#borough-closer').click(function () {
        $('#borough').hide(1000);
        });
        $('#detail-closer').click(function () {
        $('#iframeDetail').hide(1000);
        });
        var flagListLayer = false;
        $('.maximize_divNav').click(function () {
        if (!flagListLayer)
        {
        $('.conTrolPnlRight').show(1000);
        $('.maximizeNav').addClass('minimizeNav');
        flagListLayer = true;
        } else
        {
        $('.conTrolPnlRight').hide(1000);
        $('.maximizeNav').removeClass('minimizeNav');
        flagListLayer = false;
        }
        });
        $("#slider").slider({
        range: "min",
                value: 50,
                min: 1,
                max: 100,
                step: 0.01,
                slide: function (event, ui) {
                $("#info_opacity").html("Opacity " + Math.round(ui.value) + "%");
                $('.txtOpacity').val(Math.round(ui.value));
                //$("#dragItem").draggable("option", "opacity", ui.value);
                }
        });
        $(".pickColor").spectrum({
        color: "#f00",
                change: function (color) {
                //$("#basic-log").text("change called: " + color.toHexString());
                $('.txtColor').val(color.toHexString());
                }
        });
        $(".pickBorderColor").spectrum({
        color: "#f00",
                change: function (color) {
                //$("#basic-log").text("change called: " + color.toHexString());
                $('.txtBorderColor').val(color.toHexString());
                }
        });
        $("#objectLayer").change(function() {
        if ($(this).val() != "-1"){
        $('#addFormLayer').removeAttr('disabled');
        }
        else
                $('#addFormLayer').attr("disabled", "disabled");
        });
        $(".linkDeleteLayer").click(function () {
        if (confirm("Bạn có chắc chắn muốn xóa layer này trên bản đồ!") == true) {
        var layerId = $(this).attr('data-id');
        $.get("${pageContext.request.contextPath}/mapGeo/delMyLayer/" + layerId, function(data) {
        if (parseInt(data) < 0){
        alert('Thêm mới layer thất bại! Mời bạn thử lại');
        } else {
        var mLayers = map.getLayers();
        mLayers.forEach(function(layer, i) {
        if (layer.get('name') === 'myLayer_' + layerId){
        map.removeLayer(layer)
                alert('Đã xóa thành công');
        }
        });
        map.render();
        }
        });
        $(this).parents('tr').remove();
        }
        });
        init();
        
        $("label").click(function () {
            if($(this).attr("for") != null){
                $('#' + $(this).attr("for")).click();
            }
        });
        
        
        });
        closer.onclick = function () {
        overlay.setPosition(undefined);
        closer.blur();
        return false;
        };
        function getCountNode()
        {
        $('#whereFilter').text('');
        var whereSearch = ' ';
        var htmlFilter = '';
        $("#boxSearch .groupFilter").each(function (i) {
        whereSearch = whereSearch + convertQueryTotalFilter($(this).find('.column').val().trim(), $(this).find('.filterType').val(), $(this).find('.value_').val());
        htmlFilter += $(this).find('.column option:selected').text() + "  " + $(this).find('.filterType').val() + " " + $(this).find('.value_').val() + " | "
                //alert(whereSearch);
        });
        if (whereSearch !== ' '){
        //$('#whereFilter').text(htmlFilter);
//        $('#divcountBTS').hide();
//        $('#divcountNodeB').hide();
//        $('#divcountCell2G').hide();
//        $('#divcountCell3G').hide();
//        $('#divcountCell4G').hide();
//        $('#divcounteNodeB').hide();
//        $("#boxSearch .groupFilter").each(function (i) {
//        var typeId = $(this).find('.objectFill').val();
//        if (typeId == 2){
//        $('#divcountBTS').show();
//        } else if (typeId == 3){
//        $('#divcountNodeB').show();
//        } else if (typeId == 5){
//        $('#divcountCell2G').show();
//        } else if (typeId == 6){
//        $('#divcountCell3G').show();
//        } else if (typeId == 7){
//        $('#divcountCell4G').show();
//        } else if (typeId == 8){
//        $('#divcounteNodeB').show();
//        }
//        });
        }
        $.post("${pageContext.request.contextPath}/mapGeo/countNodes", {tinhId:$("#tinhTpId").val(), huyenId: $("#quanHuyenId").val(), xaId: $("#phuongXaId").val(), where: whereSearch}, function (data) {
        $('#countBTS').text(data.bts);
        $('#countNodeB').text(data.nodeb);
        $('#counteNodeB').text(data.enodeb);
        $('#countCell2G').text(data.cell2g);
        $('#countCell3G').text(data.cell3g);
        $('#countCell4G').text(data.cell4g);
        $('#borough').show();
        });
        }
        function getListHuyen(tinh)
        {
        var id = $("#tinhTpId").val();
        if (tinh != 0)
                id = tinh;
        $.get("${pageContext.request.contextPath}/mane/getQuanHuyen?tinhTpId=" + id, function (data) {
        var html = '<option value="" >--- Quận / Huyện ---</option>';
        if (data.length > 0) {
        data.forEach(function (entry) {
        var htmlx = '<option data-tokens="' + entry.tenQuanHuyen + '" value="' + entry.quanHuyenId + '">' + entry.tenQuanHuyen + '</option>';
        html += htmlx;
        });
        }
        ;
        $('#quanHuyenId').html(html);
        $('#quanHuyenId').selectpicker('refresh');
        if (tinh != 0)
                $('#quanHuyenId').val(${dvbo.quanHuyenId});
        });
        }

        function getListPhuongXa(huyen)
        {

        var id = $("#quanHuyenId").val();
        if (huyen != 0)
                id = huyen;
        $.get("${pageContext.request.contextPath}/dv/getPhuong/" + id, function (data) {
        var html = '<option value="">--- Phường / Xã ---</option>';
        if (data.length > 0) {
        data.forEach(function (entry) {
        var htmlx = '<option value="' + entry.phuongXaId + '">' + entry.tenPhuongXa + '</option>';
        html += htmlx;
        });
        }
        ;
        $('#phuongXaId').html(html);
        $('#phuongXaId').selectpicker('refresh');
        if (huyen != 0)
                $('#phuongXaId').val(${dvbo.phuongXaId});
        });
        }

        function approveDialogMap(nodeId, neTypeId) {
        $('#nodeId').val(nodeId);
        $('#neTypeId').val(neTypeId);
        $('#ModelMap').modal('show');
        viewDetail(nodeId, neTypeId);
        }
        //huunv
        function displayEdit(type, id){
        var linkDetail = '';
        console.log(id);
        console.log(type);
        if (type == 20 || type == 25){
        $.get("${pageContext.request.contextPath}/mapDetail/getNode?nodeId=" + id, function (data) {

        if (data.neTypeId == 12){
        console.log(data);
        linkDetail = '${pageContext.request.contextPath}/msc/view/' + id;
        }
        else if (data.neTypeId == 13){
        linkDetail = '${pageContext.request.contextPath}/mss/view/' + id;
        }
        else if (data.neTypeId == 14){
        linkDetail = '${pageContext.request.contextPath}/mgw/view/' + id;
        }
        else if (data.neTypeId == 15){
        linkDetail = '${pageContext.request.contextPath}/stp/view/' + id;
        }
        else if (data.neTypeId == 16){
        linkDetail = '${pageContext.request.contextPath}/tss/view/' + id;
        }
        else if (data.neTypeId == 17){
        linkDetail = '${pageContext.request.contextPath}/hss/view/' + id;
        }
        else if (data.neTypeId == 18){
        linkDetail = '${pageContext.request.contextPath}/hlr/view/' + id;
        }
        else if (data.neTypeId == 20){
        linkDetail = '${pageContext.request.contextPath}/ngn/view/' + id;
        }
        else if (data.neTypeId == 22){
        linkDetail = '${pageContext.request.contextPath}/sgsn/view/' + id;
        }
        else if (data.neTypeId == 23){
        linkDetail = '${pageContext.request.contextPath}/ggsn/view/' + id;
        }
        else if (data.neTypeId == 24){
        linkDetail = '${pageContext.request.contextPath}/mme/view/' + id;
        }
        else if (data.neTypeId == 25){
        linkDetail = '${pageContext.request.contextPath}/pgw/view/' + id;
        }
        else if (data.neTypeId == 26){
        linkDetail = '${pageContext.request.contextPath}/smsc/view/' + id;
        }
        else if (data.neTypeId == 27){
        linkDetail = '${pageContext.request.contextPath}/smpp/view/' + id;
        }
        else if (data.neTypeId == 28){
        linkDetail = '${pageContext.request.contextPath}/dsr/view/' + id;
        }
        else if (data.neTypeId == 29){
        linkDetail = '${pageContext.request.contextPath}/mca/view/' + id;
        }
        else if (data.neTypeId == 30){
        linkDetail = '${pageContext.request.contextPath}/crbt/view/' + id;
        }
        else if (data.neTypeId == 31){
        linkDetail = '${pageContext.request.contextPath}/ussd/view/' + id;
        }
        else if (data.neTypeId == 32){
        linkDetail = '${pageContext.request.contextPath}/dns/view/' + id;
        }
        else if (data.neTypeId == 33){
        linkDetail = '${pageContext.request.contextPath}/rc/view/' + id;
        }
        else if (data.neTypeId == 34){
        linkDetail = '${pageContext.request.contextPath}/sdp/view/' + id;
        }
        else if (data.neTypeId == 35){
        linkDetail = '${pageContext.request.contextPath}/fda/view/' + id;
        }
        PopupCenter(linkDetail, '_blank', 800, 600);
        });
        }
        else{
        if (type == 11){
        linkDetail = '${pageContext.request.contextPath}/bscRnc/view/' + id;
        }
        else if (type == 2 || type == 3 || type == 8 ){
            linkDetail = '${pageContext.request.contextPath}/nodes/preUpdate/' + id + '/' + type;
        }
        else if (type == 5 || type == 6 || type == 7 ){
            linkDetail = '${pageContext.request.contextPath}/cells/preUpdate/' + id + '/' + type;
        }
        else if (type == 0){
        linkDetail = '${pageContext.request.contextPath}/building/view/' + id;
        }
        else if (type == 10){
        linkDetail = '${pageContext.request.contextPath}/stationPlans/preUpdate/' + id + '/1';
        }
        else if (type == 9){
        linkDetail = '${pageContext.request.contextPath}/project_station/viewThongTinChung/' + id;
        }
        //cs core

        PopupCenter(linkDetail, '_blank', 800, 600);
        }
        }
        function PopupCenter(url, title, w, h) {
        // Fixes dual-screen position                         Most browsers      Firefox
        var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
        var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;
        var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
        var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;
        var left = ((width / 2) - (w / 2)) + dualScreenLeft;
        var top = ((height / 2) - (h / 2)) + dualScreenTop;
        var newWindow = window.open(url, title, 'resizable=0,scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
        // Puts focus on the newWindow
//        if (window.focus) {
//        newWindow.focus();
//        }
        }
        //huunv


        function updateFormStyle(id)
        {
        $.get("${pageContext.request.contextPath}/mapGeo/getlistLayer/" + id, function (data) {
        $('.txtEventId').val(data.eventId);
        $('.txtLayerId').val(id);
        $('.txtOpacity').val(data.styleOpacity);
        $("#slider").slider('value', data.styleOpacity);
        $('.txtSize').val(data.styleSize);
        $(".pickColor").spectrum({
        color: data.styleColor,
                change: function (color) {
                $('.txtColor').val(color.toHexString());
                }
        });
        $('.txtColor').val(data.styleColor);
        $('.txtBorderColor').val(data.styleBorderColor);
        $(".pickBorderColor").spectrum({
        color: data.styleBorderColor,
                change: function (color) {
                $('.txtBorderColor').val(color.toHexString());
                }
        });
        $('#info_opacity').text('Opacity ' + data.styleOpacity + '%')
                $('#nameMyLayer').text(data.layerName);
        $('#startTime').val(data.startDate);
        $('#endTime').val(data.endDate);
        });
        }

        //update style 
        function updateStyle() {
        var size = $('.txtSize').val();
        var opacity = $('.txtOpacity').val();
        var color = $('.txtColor').val();
        var borderColor = $('.txtBorderColor').val();
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();
        var idLayer = $('.txtLayerId').val();
        var eventId = $('.txtEventId').val();
        $.post("${pageContext.request.contextPath}/mapGeo/updateMyLayer", {layerId:idLayer, styleOpacity: opacity, styleColor:color, styleBorderColor: borderColor, styleSize:size, startDate: startTime, endDate: endTime}, function(data) {
        if (parseInt(data) < 1){
        alert('Cập nhập thất bại! Mời bạn thử lại');
        }
        else{
        alert('Cập nhập thành công');
        $(".colorLayer").each(function (i) {
        var layerId = $(this).attr('data-id');
        if (layerId == idLayer){
        $(this).css("background-color", color);
        }
        });
        var mLayers = map.getLayers();
        mLayers.forEach(function(layer, i) {
        if (layer.get('name') === 'myLayer_' + data){
        map.removeLayer(layer);
        var updatelayer = new ol.layer.Vector({
        name:'myLayer_' + parseInt(data),
                source: new ol.source.Vector(),
                style: new ol.style.Style({
                image: new ol.style.Circle({
                radius: size,
                        stroke: new ol.style.Stroke({
                        color: borderColor,
                                width: 2
                        }),
                        fill: new ol.style.Fill({
                        color: color,
                        })
                })
                })
        });
        updatelayer.setOpacity(opacity / 100);
        $.ajax({
        type: "GET",
                url: '${pageContext.request.contextPath}/mapGeo/getdataEvent?eventId=' + eventId + "&startDate=" + startTime + "&endDate=" + endTime,
                dataType: 'json',
                success:function(data){
                // we need to transform the geometries into the view's projection
                var transform = ol.proj.getTransform('EPSG:4326', 'EPSG:3857');
                // loop over the items in the response
                if (data.length > 0) {
                data.forEach(function(item) {
                // create a new feature with the item as the properties
                var feature = new ol.Feature(item);
                // add a url property for later ease of access
                //feature.set('url', item.media.m);
                // create an appropriate geometry and add it to the feature
                var coordinate = transform([parseFloat(item.longitude), parseFloat(item.latitude)]);
                var geometry = new ol.geom.Point(coordinate);
                feature.setGeometry(geometry);
                // add the feature to the source
                updatelayer.getSource().addFeature(feature);
                });
                }
                }
        });
        map.addLayer(updatelayer);
        }
        });
        }
        });
        }
        function removeMyLayer(layerId) {
        }
        function addMyLayer() {
        }
        function addMyEvent() {
        }

        function loadMyLayer(){
        <c:forEach var="item" items="${lstLayer}" varStatus="status">
        var myLayer_${item.layerId} = new ol.layer.Tile({
        name:'myLayer_${item.layerId}',
                source: new ol.source.TileWMS({
                url: url,
                        params: {
                        VIEWPARAMS: 'WHERE_:' + '${item.styleWhere}',
            <c:choose>
                <c:when test="${item.objectId == 2}">
                        LAYERS: 'RIMS:btsView'
                </c:when>
                <c:when test="${item.objectId == 3}">
                        LAYERS: 'RIMS:nodebView'
                </c:when>
                <c:when test="${item.objectId == 8}">
                        LAYERS: 'RIMS:enodeBView'
                </c:when>
                <c:when test="${item.objectId == 5}">
                        LAYERS: 'RIMS:cell2G'
                </c:when>
                <c:when test="${item.objectId == 6}">
                        LAYERS: 'RIMS:cell3G'
                </c:when>
                <c:when test="${item.objectId == 7}">
                        LAYERS: 'RIMS:cell4G'
                </c:when>
            </c:choose>

                        }
                })
        });
        myLayer_${item.layerId}.setOpacity(${item.styleOpacity} / 100);
        arrLayer['myLayer_${item.layerId}'] = myLayer_${item.layerId};
        map.addLayer(myLayer_${item.layerId});
        //console.log(map.getLayers().getLength());
        //map.render();
        </c:forEach>
        // load list event
        <c:forEach var="item" items="${myListLayer}" varStatus="status">
        var myLayer_${item.layerId} = new ol.layer.Vector({
        name:'myLayer_${item.layerId}',
                source: new ol.source.Vector(),
                style: new ol.style.Style({
                image: new ol.style.Circle({
                radius: ${item.styleSize},
                        stroke: new ol.style.Stroke({
                        color: "${item.styleBorderColor}",
                                width: 2
                        }),
                        fill: new ol.style.Fill({
                        color: "${item.styleColor}",
                        })
                })
                })
        });
        $.ajax({
        type: "GET",
                url: '${pageContext.request.contextPath}/mapGeo/getdataEvent?eventId=${item.eventId}',
                            dataType: 'json',
                            success:function(data){
                            // we need to transform the geometries into the view's projection
                            var transform = ol.proj.getTransform('EPSG:4326', 'EPSG:3857');
                            // loop over the items in the response
                            if (data.length > 0) {
                            data.forEach(function(item) {
                            // create a new feature with the item as the properties
                            var feature = new ol.Feature(item);
                            // add a url property for later ease of access
                            //feature.set('url', item.media.m);
                            // create an appropriate geometry and add it to the feature
                            var coordinate = transform([parseFloat(item.longitude), parseFloat(item.latitude)]);
                            var geometry = new ol.geom.Point(coordinate);
                            feature.setGeometry(geometry);
                            // add the feature to the source

                            myLayer_${item.layerId}.getSource().addFeature(feature);
                            });
                            }
                            }
                    });
                    myLayer_${item.layerId}.setOpacity(${item.styleOpacity} / 100);
                    arrLayer['myLayer_${item.layerId}'] = myLayer_${item.layerId};
                    map.addLayer(myLayer_${item.layerId});
        </c:forEach>
                    }


                    function loadMyLayer_Style()
                    {
                    var myLayer = new ol.layer.Tile({
                    source: new ol.source.TileWMS({
                    url: url,
                            params: {
                            LAYERS: 'RIMS:btsView',
                                    //'SLD': 'http://localhost:8080/RIMS/resources/sldFile/mystyle.xml' 
                            },
                    })
                    });
                    var _sld = '<?xml version="1.0" encoding="ISO-8859-1"?>';
                    _sld += '<StyledLayerDescriptor version="1.0.0"';
                    _sld += 'xsi:schemaLocation="http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd"';
                    _sld += 'xmlns="http://www.opengis.net/sld" xmlns:ogc="http://www.opengis.net/ogc"';
                    _sld += 'xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">';
                    _sld += '<NamedLayer><Name></Name><UserStyle><Title>dark orange square point style</Title>';
                    _sld += '<FeatureTypeStyle><Rule><PointSymbolizer> <Graphic><Mark><WellKnownName>circle</WellKnownName><Fill><CssParameter name="fill">#FF0000</CssParameter></Fill></Mark> <Size>10</Size></Graphic> </PointSymbolizer></Rule></FeatureTypeStyle></UserStyle></NamedLayer></StyledLayerDescriptor>';
                    myLayer.getSource().updateParams({STYLES: undefined, SLD_BODY:_sld});
                    //myLayer.mergeNewParams({

                    //"env": 'color:' + color + ';stroker_color:' + stroker_color + ';fill_opacity:' + fill_opacity / 100 + ';size:' + (parseInt(size) + 7)
                    //});
                    //myLayer.refresh();
                    map.addLayer(myLayer);
                    }


                    function SubmitAddLayer()
                    {
                    var nameLayer = $('#nameLayer').val();
                    var objectId = $('#objectLayer').val();
                    var opacity = 50;
                    var color = getRandomColor();
                    var borderColor = getRandomColor();
                    var size = 6;
                    var where = '';
                    var layerSource = '';
                    if (objectId == 2){
                    layerSource = 'RIMS:btsView';
                    color = '#3CB371';
                    } else if (objectId == 3){
                    layerSource = 'RIMS:nodebView';
                    color = '#FF0000';
                    } else if (objectId == 8){
                    layerSource = 'RIMS:enodeBView';
                    color = '#CDAD00';
                    } else if (objectId == 5){
                    layerSource = 'RIMS:cell2G';
                    color = '#3CB371';
                    } else if (objectId == 6){
                    layerSource = 'RIMS:cell3G';
                    color = '#FF0000';
                    } else if (objectId == 7){
                    layerSource = 'RIMS:cell4G';
                    color = '#CDAD00';
                    }

                    $("#boxAddLayer .groupFilter").each(function (i) {
                    where = where + convertQueryFilter(objectId, $(this).find('.objectFill').val(), $(this).find('.column').val(), $(this).find('.filterType').val(), $(this).find('.value_').val());
                    console.log(where);
                    });
                    $.post("${pageContext.request.contextPath}/mapGeo/addMyLayer", {layerId: - 1, layerName: nameLayer, styleOpacity: opacity, styleColor:color, styleBorderColor: borderColor, styleSize:size, styleWhere:where, objectId: objectId }, function(data) {
                    if (parseInt(data) < 1){
                    alert('Thêm mới layer thất bại! Mời bạn thử lại');
                    }
                    else{
                    alert('Thêm mới layer thành công');
                    var newLayer = new ol.layer.Tile({
                    name:'myLayer_' + parseInt(data),
                            source: new ol.source.TileWMS({
                            url: url,
                                    params: {
                                    VIEWPARAMS: 'WHERE_:' + where,
                                            LAYERS: layerSource
                                    }
                            })
                    });
                    newLayer.setOpacity(opacity / 100);
                    arrLayer['myLayer_' + parseInt(data)] = newLayer;
                    map.addLayer(newLayer);
                    console.log(map.getLayers().getLength());
                    var htmlRowLayer = '<tr><td style="text-align: center"><input type="checkbox" name="chkLayer" class="chkMyLayer" checked value="myLayer_' + parseInt(data) + '"/></td> ';
                    htmlRowLayer += '<td>' + nameLayer + '</td><td><div class="colorLayer" data-id="' + data + '" style="background-color: ' + color + '"></div></td>';
                    htmlRowLayer += '<td style="text-align: center">';
                    //htmlRowLayer += '<a href="#" data-toggle="modal" onclick="updateFormStyle('+parseInt(data)+',\''+ nameLayer + '\','+ size +',\''+color+'\', \''+ borderColor +'\','+ opacity +')"  data-target="#styleLayers" class="linkChangeStyleLayer"></a>';
                    htmlRowLayer += '</td>';
                    htmlRowLayer += '<td style="text-align: center"><a href="#" data-id="' + parseInt(data) + '" class="linkDeleteLayer"></td></tr>';
                    $('#listLayer').append(htmlRowLayer);
                    $("input[type='checkbox'], input[type='radio']").iCheck({
                    checkboxClass: 'icheckbox_minimal',
                            radioClass: 'iradio_minimal'
                    });
                    $('.iCheck-helper').click(function () {
                    var parent = $(this).parent().get(0);
                    $(parent).find('input').click();
                    displayLayer();
                    });
                    $(".linkDeleteLayer").click(function () {
                    if (confirm("Bạn có chắc chắn muốn xóa layer này trên bản đồ!") == true) {
                    var layerId = $(this).attr('data-id');
                    $.get("${pageContext.request.contextPath}/mapGeo/delMyLayer/" + layerId, function(data) {
                    if (parseInt(data) < 0){
                    alert('Thêm mới layer thất bại! Mời bạn thử lại');
                    } else {
                    var mLayers = map.getLayers();
                    mLayers.forEach(function(layer, i) {
                    if (layer.get('name') === 'myLayer_' + layerId){
                    map.removeLayer(layer)
                            alert('Đã xóa thành công');
                    }
                    });
                    map.render();
                    }
                    });
                    $(this).parents('tr').remove();
                    }
                    });
                    $('#addLayer').modal('hide');
                    }
                    });
                    }

                    function SubmitAddEvent()
                    {
                    var nameLayer = $('#eventLayer option:selected').text();
                    var eventId = $('#eventLayer').val();
                    var opacity = 50;
                    var color = getRandomColor();
                    var borderColor = getRandomColor();
                    var size = 6;
                    var where = '';
                    $.post("${pageContext.request.contextPath}/mapGeo/addMyLayer", {layerId: - 1, eventName:nameLayer, styleOpacity: opacity, styleColor:color, styleBorderColor: borderColor, styleSize:size, eventId: eventId }, function(data) {
                    if (parseInt(data) < 1){
                    alert('Thêm mới event thất bại! Mời bạn thử lại');
                    }
                    else{
                    alert('Thêm mới event thành công');
                    var newLayer = new ol.layer.Vector({
                    name:'myLayer_' + parseInt(data),
                            source: new ol.source.Vector(),
                            style: new ol.style.Style({
                            image: new ol.style.Circle({
                            radius: 6,
                                    stroke: new ol.style.Stroke({
                                    color: borderColor,
                                            width: 2
                                    }),
                                    fill: new ol.style.Fill({
                                    color: color
                                    })
                            })
                            })
                    });
                    $.ajax({
                    type: "GET",
                            url: '${pageContext.request.contextPath}/mapGeo/getdataEvent?eventId=' + eventId,
                            dataType: 'json',
                            success:function(data){
                            // we need to transform the geometries into the view's projection
                            var transform = ol.proj.getTransform('EPSG:4326', 'EPSG:3857');
                            // loop over the items in the response
                            if (data.length > 0) {
                            data.forEach(function(item) {
                            // create a new feature with the item as the properties
                            var feature = new ol.Feature(item);
                            // add a url property for later ease of access
                            //feature.set('url', item.media.m);
                            // create an appropriate geometry and add it to the feature
                            var coordinate = transform([parseFloat(item.longitude), parseFloat(item.latitude)]);
                            var geometry = new ol.geom.Point(coordinate);
                            feature.setGeometry(geometry);
                            // add the feature to the source

                            newLayer.getSource().addFeature(feature);
                            });
                            }
                            }
                    });
                    newLayer.setOpacity(opacity / 100);
                    arrLayer['myLayer_' + parseInt(data)] = newLayer;
                    map.addLayer(newLayer);
                    console.log(map.getLayers().getLength());
                    var htmlRowLayer = '<tr><td style="text-align: center"><input type="checkbox" name="chkLayer" class="chkMyLayer" checked value="myLayer_' + parseInt(data) + '"/></td> ';
                    htmlRowLayer += '<td>' + nameLayer + '</td><td><div class="colorLayer" data-id="' + data + '" style="background-color: ' + color + '"></div></td>';
                    htmlRowLayer += '<td style="text-align: center"><a href="#" data-toggle="modal" onclick="updateFormStyle(' + parseInt(data) + ')"  data-target="#styleLayers" class="linkChangeStyleLayer"></a></td>';
                    htmlRowLayer += '<td style="text-align: center"><a href="#" data-id="' + parseInt(data) + '" class="linkDeleteLayer"></td></tr>';
                    $('#listEvent').append(htmlRowLayer);
                    $("input[type='checkbox'], input[type='radio']").iCheck({
                    checkboxClass: 'icheckbox_minimal',
                            radioClass: 'iradio_minimal'
                    });
                    $('.iCheck-helper').click(function () {
                    var parent = $(this).parent().get(0);
                    $(parent).find('input').click();
                    displayLayer();
                    });
                    $(".linkDeleteLayer").click(function () {
                    if (confirm("Bạn có chắc chắn muốn xóa layer này trên bản đồ!") == true) {
                    var layerId = $(this).attr('data-id');
                    $.get("${pageContext.request.contextPath}/mapGeo/delMyLayer/" + layerId, function(data) {
                    if (parseInt(data) < 0){
                    alert('Thêm mới layer thất bại! Mời bạn thử lại');
                    } else {
                    var mLayers = map.getLayers();
                    mLayers.forEach(function(layer, i) {
                    if (layer.get('name') === 'myLayer_' + layerId){
                    map.removeLayer(layer);
                    alert('Đã xóa thành công');
                    }
                    });
                    map.render();
                    }
                    });
                    $(this).parents('tr').remove();
                    }
                    });
                    $('#addEvent').modal('hide');
                    }
                    });
                    }

                    function getRandomColor() {
                    var letters = '0123456789ABCDEF';
                    var color = '#';
                    for (var i = 0; i < 6; i++) {
                    color += letters[Math.floor(Math.random() * 16)];
                    }
                    return color;
                    }

                    function viewDetail(id, type) {
                    var linkDetail = '${pageContext.request.contextPath}/nodes/view/' + type + '/' + id;
                    if (type == 5 || type == 6 || type == 7) {
                    linkDetail = '${pageContext.request.contextPath}/cells/detail/' + id + '/' + type;
                    } else if (type == 2 || type == 3 || type == 8) {
                    linkDetail = '${pageContext.request.contextPath}/nodes/detail/' + id + '/' + type;
                    } else if (type == 4) {
                    linkDetail = '${pageContext.request.contextPath}/building/detail/' + id;
                    } else if (type == 10) {
                    linkDetail = '${pageContext.request.contextPath}/stationPlans/preUpdate/' + id + "/0";
                    } else if (type == 9) {
                    linkDetail = '${pageContext.request.contextPath}/project_station/detail/' + id;
                    } else if (type == 11){
                    linkDetail = '${pageContext.request.contextPath}/bscRnc/detail/' + id;
                    } else if (type == 13){
                    linkDetail = '${pageContext.request.contextPath}/mss/popup/' + id;
                    } else if (type == 14){
                    linkDetail = '${pageContext.request.contextPath}/mgw/popup/' + id;
                    } else if (type == 15){
                    linkDetail = '${pageContext.request.contextPath}/stp/popup/' + id;
                    } else if (type == 16){
                    linkDetail = '${pageContext.request.contextPath}/tss/popup/' + id;
                    } else if (type == 17){
                    linkDetail = '${pageContext.request.contextPath}/hss/popup/' + id;
                    } else if (type == 18){
                    linkDetail = '${pageContext.request.contextPath}/hlr/popup/' + id;
                    } else if (type == 19){
                    linkDetail = '${pageContext.request.contextPath}/ims/popup/' + id;
                    } else if (type == 20){
                    linkDetail = '${pageContext.request.contextPath}/ngn/popup/' + id;
                    } else if (type == 22 || type == 23 || type == 24 || type == 25 || type == 26 || type == 27 || type == 28 || type == 29 || type == 30 || type == 31 || type == 32 || type == 33){
                    linkDetail = '${pageContext.request.contextPath}/mme/detail/' + id + "/" + type;
                    }

                    $.get(linkDetail, function (data) {
                    if (type == 10) {
                    var html = $(data).find('#tramDA').html();
                    $('#detailDiv').html(html);
                    } else
                            $('#detailDiv').html(data);
                    $('#detailDiv').find('input, textarea, select').attr('disabled', 'disabled');
                    });
                    }

                    function callNode(lat, lon)
                    {
                    map.getView().setCenter(ol.proj.transform([lon, lat], 'EPSG:4326', 'EPSG:3857'));
                    }
                    function viewTopo(node1, node2){
                    var urlTopo = '${pageContext.request.contextPath}/broadband/topo/vpntopo?node1=' + node1 + '&node2=' + node2;
                    PopupCenter(urlTopo, '_blank', 800, 600);
                    }
                    
                    //Function to add replaceAll to Strings
String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.replace(new RegExp(search, 'g'), replacement);
};

function EscapeCommasSemiColons(input){
    var output=input.replaceAll(",", "\\,"); //replace all the commas
    output=output.replaceAll(";", "\\;"); //replace all the SemiColons
    return output;
}

 function hdsd(media) {
    window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
 }
 
 // ve thuoc do do dai
 function openRuler(item){
        //createMeasureTooltip();
        //createHelpTooltip();

        
        map.removeInteraction(draw);
        map.removeOverlay(measureTooltip);
        map.removeOverlay(helpTooltip);
        map.render();
        if (item.value == 'none') {
            map.removeLayer(layer_circle)
            map.render();      
        }
        else{
            
            var layer_circle = new ol.layer.Vector({
                source: new ol.source.Vector(),
                style: new ol.style.Style({
                  fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                  }),
                  stroke: new ol.style.Stroke({
                    color: '#ffcc33',
                    width: 2
                  }),
                  image: new ol.style.Circle({
                    radius: 7,
                    fill: new ol.style.Fill({
                      color: '#ffcc33'
                    })
                  })
                })
              });
            
            map.addLayer(layer_circle);
            createMeasureTooltip();
            createHelpTooltip();
            var type = (item.value == 'polygon' ? 'Polygon' : 'LineString');
            draw = new ol.interaction.Draw({
              source: new ol.source.Vector(),
              type: type,
              style: new ol.style.Style({
                fill: new ol.style.Fill({
                  color: 'rgba(255, 255, 255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                  color: 'rgba(0, 0, 0, 0.5)',
                  lineDash: [10, 10],
                  width: 2
                }),
                image: new ol.style.Circle({
                  radius: 5,
                  stroke: new ol.style.Stroke({
                    color: 'rgba(0, 0, 0, 0.7)'
                  }),
                  fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                  })
                })
              })
            });
            map.addInteraction(draw);

           
            map.on('pointermove', pointerMoveHandler);

            map.getViewport().addEventListener('mouseout', function() {
              helpTooltipElement.classList.add('hidden');
            });
            var listener;
            draw.on('drawstart',
                function(evt) {
                  // set sketch
                  sketch = evt.feature;

                  /** @type {ol.Coordinate|undefined} */
                  var tooltipCoord = evt.coordinate;

                  listener = sketch.getGeometry().on('change', function(evt) {
                    var geom = evt.target;
                    var output;
                    if (geom instanceof ol.geom.Polygon) {
                      output = formatArea(geom);
                      tooltipCoord = geom.getInteriorPoint().getCoordinates();
                    } else if (geom instanceof ol.geom.LineString) {
                      output = formatLength(geom);
                      tooltipCoord = geom.getLastCoordinate();
                    }
                    measureTooltipElement.innerHTML = output;
                    measureTooltip.setPosition(tooltipCoord);
                  });
                }, this);

            draw.on('drawend',
                function() {
                  measureTooltipElement.className = 'tooltip tooltip-static';
                  measureTooltip.setOffset([0, -7]);
                  // unset sketch
                  sketch = null;
                  // unset tooltip so that a new one can be created
                  measureTooltipElement = null;
                  createMeasureTooltip();
                  ol.Observable.unByKey(listener);
                }, this);

        }
 }
    </script>
    <script src="${pageContext.request.contextPath}/resources/js/measure.js" type="text/javascript"></script>
</body>
</html>