<%@page import="com.vnpt.media.rims.common.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/resources/css/jquery.treetable.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.treetable.theme.custom.css">
        <script src="${pageContext.request.contextPath}/resources/js/jquery.treetable.js"></script>


        <script src="${pageContext.request.contextPath}/resources/js/jquery.ui.core.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.ui.widget.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.ui.mouse.js"></script>

        <script src="${pageContext.request.contextPath}/resources/js/jquery.ui.draggable.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.ui.droppable.js"></script>        
        <script src="${pageContext.request.contextPath}/resources/js/jquery.isloading.js" type="text/javascript"></script>
        <style>
            .truncate {
                /*// width: 250px;*/
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }

        </style>



    </head>
    <body>
        <section class="content-header">
            <h1>Quản lý thiết bị</h1>

        </section>
        <section class="content">


            <script type="text/javascript">
//                viewDetail = function(id, type) {
//                    $.ajax({
//                        type: 'GET',
//                        url: "<%=request.getContextPath()%>/equipment/viewDetail/" + type + "/" + id,
//                        dataType: "html",
//                        beforeSend: function() {
////                            $('#treeadvanced').isLoading({ text: "Loading...", position: "overlay" });
////                            $.isLoading({text: "Loading"});
//                            $("#treeadvanced").isLoading({
//                                text: "Loading",
//                                position: "overlay",
//                            });
//                        },
//                        complete: function() {
//                            $("#treeadvanced").isLoading("hide");
//                        },
//                        success: function(data) {
////                                alert(data);
////                                var code = "";
////                                $(data).find("responseCode").each(function() {
////                                    code = code + $(this).text() + "";
////                                });
////                                if (code == '01') {
////                                    alert("Có lỗi trong quá trình xử lý. Vui lòng thử lại");
////                                } else if (code == '00') {
////                                    alert("Reset mật khẩu thành công");
////                                }
//                            $('#detailDiv').html(data);
//                        }
//                        ,
//                        error: function(xhr, textStatus, error) {
//                            alert('error_ajax_xml?');
//                        }
//                    });
//
//                }
            </script>


            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Danh sách thiết bị</h3>
                        </div>
                        <div class="box-body">

                            <div class="input-group">                                
                                <label class=" input-group-addon" style="font-weight: bold; min-width:100px;">Ne fdn</label>                                                                
                                <label class="form-control" >${neFdn}</label>                                                                
                            </div>
                            <br/>
                            <div class="input-group">
                                <label class="input-group-addon" style="font-weight: bold; min-width:100px;">Ne type</label>
                                <label class="form-control" >${neType}</label>
                            </div>
                            <br/>
                            <div class="input-group">
                                <label class="input-group-addon" style="font-weight: bold; min-width:100px;">Ne name</label>
                                <label class="form-control" >${neName}</label>
                            </div>



                        </div>
                        <div class="box-body table-responsive " style=" overflow-x:scroll !important;;overflow-y:hidden !important;;">

                            <table id="treeadvanced" style="min-width: 100%;" 
                                   class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Type</th>                                          
                                        <th style="display:none;"></th>
                                        <th style="display:none;"></th>
                                        <th>Number</th>  
                                        <th>Serial number</th>                                             
                                        <th>Inventory Unit Type</th>                                        
                                        <th>Vendor</th>
                                        <th>Bomcode</th>                                        
                                        <th>Manufacturer data</th>
                                        <th>Name</th>
                                    </tr>
                                </thead>

                                <tbody>                                       
                                    <c:forEach var="temp" items="${list_treetable}" varStatus="status">     
                                        <c:choose>
                                            <c:when test="${temp.parentId==-1}">
                                                <tr data-tt-id='${temp.id}'>
                                                    <td class="truncate" ><span class='${temp.icon}'>${temp.type}</span></td>
                                                    <td  style="display:none;">${temp.id}</td>
                                                    <td  style="display:none;">${temp.type}</td>
                                                    <td  >${temp.no}</td>
                                                    <td >${temp.serialNumber}</td>                                                        
                                                    <td >${temp.inventoryUnitType}</td>
                                                    <td>${temp.vendorName}</td>
                                                    <td>${temp.bomCode}</td>
                                                    <td>${temp.manufacturerData}</td>
                                                    <td>${temp.name}</td>
                                                </tr>
                                            </c:when>   
                                            <c:when test="${temp.parentId!=-1}">
                                                <tr data-tt-id='${temp.id}' data-tt-parent-id='${temp.parentId}'>
                                                    <td class="truncate" ><span class='${temp.icon}'>${temp.type}</span></td>
                                                    <td  style="display:none;">${temp.id}</td>
                                                    <td  style="display:none;">${temp.type}</td>
                                                    <td  >${temp.no}</td>
                                                    <td >${temp.serialNumber}</td>                                                        
                                                    <td >${temp.inventoryUnitType}</td>
                                                    <td>${temp.vendorName}</td>
                                                    <td>${temp.bomCode}</td>
                                                    <td>${temp.manufacturerData}</td>
                                                    <td>${temp.name}</td>
                                                </tr>
                                            </c:when>   
                                        </c:choose>

                                    </c:forEach>
                                </tbody>
                            </table>                                

                        </div>
                    </div>
                </div>
<!--                <div class="col-md-4">
                    <div class="box">
                        <div class="box-body table-responsive " >
                            <div class="box-header"><h3 class="box-title">Thông tin chi tiết</h3></div>
                            <div class="box-body table-responsive " >

                                <div id="detailDiv" >
                                    &nbsp;
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->

            </div>
        </section>

        <!-- jQuery 2.0.2 -->
        <script>
            $('.truncate').tooltip();
            $("#example-basic").treetable({expandable: true});

            $("#example-basic-static").treetable();

            $("#example-basic-expandable").treetable({expandable: true});

            $("#treeadvanced").treetable({expandable: true});
            $("#treeadvanced").treetable("expandAll")
            // Highlight selected row
            $("#treeadvanced tbody").on("mousedown", "tr", function() {
                $(".selected").not(this).removeClass("selected");
                $(this).toggleClass("selected");

//                var id = this.getElementsByTagName("td")[1].innerText;
//                var type = this.getElementsByTagName("td")[2].innerText;
//                viewDetail(id, type);
            });

            // Drag & Drop Example Code
            $("#treeadvanced .file, #treeadvanced .folder").draggable({
                helper: "clone",
                opacity: .75,
                refreshPositions: true, // Performance?
                revert: "invalid",
                revertDuration: 300,
                scroll: true
            });

            $("#treeadvanced .folder").each(function() {
                $(this).parents("#treeadvanced tr").droppable({
                    accept: ".file, .folder",
                    drop: function(e, ui) {
                        var droppedEl = ui.draggable.parents("tr");
                        $("#treeadvanced").treetable("move", droppedEl.data("ttId"), $(this).data("ttId"));
                    },
                    hoverClass: "accept",
                    over: function(e, ui) {
                        var droppedEl = ui.draggable.parents("tr");
                        if (this != droppedEl[0] && !$(this).is(".expanded")) {
                            $("#treeadvanced").treetable("expandNode", $(this).data("ttId"));
                        }
                    }
                });
            });

            $("form#reveal").submit(function() {
                var nodeId = $("#revealNodeId").val()

                try {
                    $("#treeadvanced").treetable("reveal", nodeId);
                }
                catch (error) {
                    alert(error.message);
                }

                return false;
            });
        </script>
    </body>
</html>