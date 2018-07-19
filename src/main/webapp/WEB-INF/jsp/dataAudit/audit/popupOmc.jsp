
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- ADD HEADER PAGE -->
<html>
    <head>
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

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
</head>
<body class="skin-blue">
    <div class="wrapper row-offcanvas row-offcanvas-left">
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <form:form method="GET" id="frm_search">
                            <div class="box-body">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <select disabled name="neTypeId" id="neTypeId" class="form-control"> >
                                            <option value="">--- Chọn NE TYPE ---</option>
                                            <c:forEach var="neBO" items="${neList}">
                                                <c:if test='${neBO.id != 9 && neBO.id != 10}'>
                                                <option  
                                                    value="${neBO.id}"  <c:choose>
                                                        <c:when test="${neBO.id == neTypeId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose>

                                                    >${neBO.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>  
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <select name="thietBiId" id="thietBiId" class="form-control"> >
                                            <option value="">--- Chọn Vendor ---</option>
                                            <c:forEach var="tbBO" items="${thietBiList}">
                                                <option  
                                                    value="${tbBO.thietBiId}"  <c:choose>
                                                        <c:when test="${tbBO.thietBiId == thietBiId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose>

                                                    >${tbBO.tenThietBi}</option>
                                            </c:forEach>
                                        </select>  
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">


                                        <select name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen(0);"> >
                                            <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                                            <c:forEach var="tinhBO" items="${tinhList}">
                                                <option  
                                                    value="${tinhBO.tinhTpId}"  <c:choose>
                                                        <c:when test="${tinhBO.tinhTpId == tinhTpId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose>

                                                    >${tinhBO.tenTinhTp}</option>
                                            </c:forEach>

                                        </select>  



                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa(0);"> 
                                            <option value="">---Chọn Quận / Huyện ---</option>

                                        </select>  
                                    </div>
                                </div>
                                <div class="col-md-4"> 
                                    <select name="phuongXaId" id="phuongXaId" class="form-control" > 
                                        <option value="">--- Phường / Xã ---</option>
                                    </select>              
                                </div>

                                <div class="clearfix" ></div>



                                <div class="form-group" style="padding: 0 15px">
                                    <input name="code" value="${code}"
                                           type="text" class="form-control" id="exampleInputEmail1"
                                           placeholder="Mã Node hoặc địa chỉ">
                                </div>
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
                            <h3 class="box-title">Chọn 1 trong các NE bên dưới</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã Node</th> 
                                        <th>Ne Type</th> 
                                        <th>Mã Building</th>   
                                        <th>Vender</th> 
                                        <th>Đơn vị</th>
                                        <th>Địa chỉ</th>

                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>

                                            <td> 
                                                <input type="hidden" class="node_id" value="${item.id}"/>
                                                <input type="hidden" class="type_id" value="${item.neTypeId}"/>
                                                <input type="hidden" class="code_id" value="${item.code}"/>
                                                ${startRow + (status.index)}
                                            </td>
                                            <td>${item.code}</td>
                                            <td>${item.tenNeType}</td>
                                            <td>${item.codeBuilding}</td>
                                            <td>${item.tenThietBi}</td>
                                            <td>${item.donViName}</td> 
                                            <td>${item.address}</td>  

                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <!-- ADD PAGE INFO -->
                            <%@include file="../../include/page_info.jsp"%>
                        </div>
                    </div>

                </div>


            </div>
        </section>
    </div><!-- ./wrapper -->

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


    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


    <!--call ajax-->
    <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
</body>
</html>


<script>
                                            function getListHuyen(tinh)
                                            {
                                                var id = $("#tinhTpId").val();
                                                if (tinh != 0)
                                                    id = tinh;
                                                $.get("${pageContext.request.contextPath}/dv/getHuyen/" + id, function (data) {
                                                    var html = '<option value="" >--- Quận / Huyện ---</option>';
                                                    if (data.length > 0) {
                                                        data.forEach(function (entry) {
                                                            var htmlx = '<option value="' + entry.quanHuyenId + '">' + entry.tenQuanHuyen + '</option>';
                                                            html += htmlx;

                                                        });

                                                    }
                                                    ;
                                                    $('#quanHuyenId').html(html);
                                                    if (tinh != 0)
                                                        $('#quanHuyenId').val(${quanHuyenId});
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
                                                    if (huyen != 0)
                                                        $('#phuongXaId').val(${phuongXaId});
                                                });
                                            }



                                            $(document).ready(function () {

                                                $('.navbar-btn').click();
                                                var tinhId = $("#tinhTpId").val();
                                                if (tinhId != '')
                                                {
                                                    getListHuyen(${tinhTpId});
                                                    getListPhuongXa(${quanHuyenId});
                                                }
                                                $('#example1 tbody').on('click', 'tr', function () {
                                                    if ($(this).hasClass('selected')) {
                                                        $(this).removeClass('selected');
                                                    } else {
                                                        $('#example1 tbody').find('tr.selected').removeClass('selected');
                                                        $(this).addClass('selected');
                                                    }
                                                });

                                                $('#frm_search').submit(function () {
                                                    $("#neTypeId").prop('disabled', false);
                                                    //Rest of code
                                                })
                                            });
</script>

