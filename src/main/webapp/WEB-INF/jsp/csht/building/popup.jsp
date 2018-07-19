
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon">Khu vực</label>
                                            <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control" onchange="getTinhTp();"> 
                                                <c:forEach var="tinhBO" items="${khuvucList}">
                                                    <option  <c:if test='${fn:contains(khuvucId,tinhBO.id)}' >  selected="selected" </c:if>
                                                                                                                value="${tinhBO.id}"  
                                                                                                                >${tinhBO.name}</option>
                                                </c:forEach>
                                            </select>                                  
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon">Tỉnh TP</label>

                                            <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen();"  > 
                                            </select>
                                            <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                                        </div>

                                    </div>
                                </div>
                                <div class="col-xs-6">     
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon">Quận/Huyện</label>
                                            <select multiple="multiple" name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa();"> 
                                            </select>
                                            <input type="hidden" value="${quanHuyenId}" id="quanHuyenIds"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6"> 
                                    <div class="input-group">
                                        <label class=" input-group-addon">Phường/Xã</label>
                                        <select multiple="multiple" name="phuongXaId" id="phuongXaId" class="form-control"> 
                                        </select>
                                        <input type="hidden" value="${phuongXaId}" id="phuongXaIds"/>    
                                    </div>
                                </div>
                                <div class="col-xs-12">
                                    <div class="form-group" style="padding: 0 15px">
                                        <input name="code" value="${code}"
                                               type="text" class="form-control" id="exampleInputEmail1"
                                               placeholder="Mã hoặc tên CSHT">
                                    </div>
                                </div>
                                <div class="clearfix"></div>

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
                            <h3 class="box-title">Chọn 1 trong các Building bên dưới</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã CSHT</th> 
                                        <th>Tên CSHT</th> 
                                        <th>Địa chỉ</th> 
                                        <th>LATITUDE</th>
                                        <th>LONGITUDE</th> 
                                        <th>Tỉnh/TP</th> 
                                        <th>Quận/Huyện</th> 
                                        <th>Phường/Xã</th>         
                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>
                                    <input type="hidden" class="txtcode" value="${item.code}" />
                                    <input type="hidden" class="txtid" value="${item.id}" />
                                    <input type="hidden" class="txtlat" value="${item.lat}" />
                                    <input type="hidden" class="txtlon" value="${item.lon}" />
                                    <input type="hidden" class="txttinhTpId" value="${item.tinhTpId}" />
                                    <input type="hidden" class="txtquanHuyenId" value="${item.quanHuyenId}" />
                                    <input type="hidden" class="txtphuongXaId" value="${item.phuongXaId}" />
                                    <input type="hidden" class="txtaddress" value="${item.address}" />
                                    <td>${startRow + (status.index)}</td>

                                    <td>${item.code}</td>
                                    <td>${item.name}</td>
                                    <td>${item.address}</td>
                                    <td>${item.lat}</td>
                                    <td>${item.lon}</td>
                                    <td>${item.tinhName}</td>
                                    <td>${item.quanName}</td>
                                    <td>${item.phuongName}</td>

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
                    <!-- /.box -->
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
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
</body>
</html>



<script>
                                                function getTinhTp() {
                                                    var id = $("#khuvucId").val();
                                                    var tinhTpIds = $("#tinhTpIds").val();
                                                    $.get("${pageContext.request.contextPath}/mane/getTinhTp?khuVucId=" + id, function (data) {
                                                        var html = '';
                                                        if (data.length > 0) {
                                                            data.forEach(function (data) {
                                                                var htmlx = '<option value="' + data.tinhTpId + '" ';
                                                                if (tinhTpIds.indexOf(data.tinhTpId) > -1) {
                                                                    htmlx += ' selected="selected" ';
                                                                }
                                                                htmlx += '>' + data.tenTinhTp + '</option>';
                                                                html += htmlx;
                                                            });
                                                        }
                                                        $('#tinhTpId').html(html);
                                                        $('#tinhTpId').multiselect('rebuild');
                                                    });
                                                }
                                                function getListHuyen(tinh)
                                                {
                                                    var id = $("#tinhTpId").val();
                                                    if (id === null) {
                                                        id = $("#tinhTpIds").val();
                                                    }
                                                    var quanHuyenIds = $("#quanHuyenIds").val();
                                                    $.get("${pageContext.request.contextPath}/mane/getQuanHuyen?tinhTpId=" + id, function (data) {
                                                        var html = '';
                                                        if (data.length > 0) {
                                                            data.forEach(function (data) {
                                                                var htmlx = '<option value="' + data.quanHuyenId + '" ';
                                                                if (quanHuyenIds.indexOf(data.quanHuyenId) > -1) {
                                                                    htmlx += ' selected="selected" ';
                                                                }
                                                                htmlx += '>' + data.tenQuanHuyen + '</option>';
                                                                html += htmlx;
                                                            });
                                                        }
                                                        $('#quanHuyenId').html(html);
                                                        $('#quanHuyenId').multiselect('rebuild');
                                                    });
                                                }

                                                function getListPhuongXa(huyen)
                                                {
                                                    var id = $("#quanHuyenId").val();
                                                    if (id === null) {
                                                        id = $("#quanHuyenIds").val();
                                                    }
                                                    if (id == null || id == '') {
                                                        return;
                                                    }
                                                    var phuongXaIds = $("#phuongXaIds").val();
                                                    $.get("${pageContext.request.contextPath}/mane/getPhuongXa?quanHuyenId=" + id, function (data) {
                                                        var html = '';
                                                        if (data.length > 0) {
                                                            data.forEach(function (data) {
                                                                var htmlx = '<option value="' + data.phuongXaId + '" ';
                                                                if (phuongXaIds.indexOf(data.phuongXaId) > -1) {
                                                                    htmlx += ' selected="selected" ';
                                                                }
                                                                htmlx += '>' + data.tenPhuongXa + '</option>';
                                                                html += htmlx;
                                                            });
                                                        }
                                                        $('#phuongXaId').html(html);
                                                        $('#phuongXaId').multiselect('rebuild');
                                                    });
                                                }


                                                function getBuildingLink(id, code)
                                                {
                                                    $('#mybuilding').text(code);
                                                    $.get("${pageContext.request.contextPath}/building/getBuildingLink/" + id, function (data) {
                                                        var html = '';

                                                        if (data.length > 0) {
                                                            var i = 0;
                                                            data.forEach(function (entry) {
                                                                i++
                                                                var htmlx = '<tr><td>' + entry.id + '</td><td><a href="${pageContext.request.contextPath}/building/detail/' + entry.id + '" > ' + entry.code + '</a></td><tr>';
                                                                html += htmlx;

                                                            });

                                                        }
                                                        ;
                                                        $('#buildlinklist').html(html);

                                                    });
                                                }

                                                $(document).ready(function () {

                                                    $('#example1 tbody').on('click', 'tr', function () {
                                                        if ($(this).hasClass('selected')) {
                                                            $(this).removeClass('selected');
                                                        } else {
                                                            $('#example1 tbody').find('tr.selected').removeClass('selected');
                                                            $(this).addClass('selected');
                                                        }
                                                    });

                                                    $('#tinhTpId').multiselect(({
                                                        maxHeight: 200,
                                                        enableFiltering: true,
                                                        includeSelectAllOption: true,
                                                        onChange: function (element, checked) {
                                                        }
                                                    }));

                                                    $('#quanHuyenId').multiselect(({
                                                        maxHeight: 200,
                                                        enableFiltering: true,
                                                        includeSelectAllOption: true,
                                                        onChange: function (element, checked) {
                                                        }
                                                    }));

                                                    $('#phuongXaId').multiselect(({
                                                        maxHeight: 200,
                                                        enableFiltering: true,
                                                        includeSelectAllOption: true,
                                                        onChange: function (element, checked) {
                                                        }
                                                    }));
                                                    $('#khuvucId').multiselect(({
                                                        maxHeight: 200,
                                                        enableFiltering: true,
                                                        includeSelectAllOption: true,
                                                        onChange: function (element, checked) {
                                                        }
                                                    }));
                                                    getTinhTp();
                                                    if ($('#tinhTpIds').val() != null) {
                                                        getListHuyen();
                                                    }

                                                    if ($('#quanHuyenIds').val() != null) {
                                                        getListPhuongXa();
                                                    }
                                                });
</script>

