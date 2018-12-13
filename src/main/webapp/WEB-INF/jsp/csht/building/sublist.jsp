
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<style>
    .truncate {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;

    }
    .tablePagingId table {
        table-layout: fixed;
        word-wrap: break-word;
        min-width: 1450px !important;
    }
    span.indenter{
        width: 20px;
        height: 20px;
    }
    span.indenter a{
        display: none !important;
    }


</style>
<style>
    .modal-dialog {
        width: 900px;
    }
</style>
<section class="content-header">
    <h1>
       <i class="fa fa-map-marker" aria-hidden="true"></i>  Quản lý vị trí <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_CSHT_RIMS.mp4');" >Hướng dẫn</button>
    </h1>
    <ol class="breadcrumb">
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/building/preAdd')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/building/preAdd'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm vị trí</span> 
            </button>
        </c:if>
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'building/exceladdcsht')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/building/exceladdcsht'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm CSHT(Excel)</span> 
            </button>
        </c:if>
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'building/excelupdatecsht')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/building/excelupdatecsht'" >
                <span><i class="fa fa-fw fa-plus"></i>Sửa CHST(Excel)</span> 
            </button>
        </c:if>

    </ol>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                </div>

                <form:form method="GET" id="frm_search">
                    <div class="box-body">
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon" style="min-width: 150px">Khu vực</label>
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

                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon" style="min-width: 150px">Tỉnh TP</label>

                                    <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen();"  > 
                                    </select>
                                    <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                                </div>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon" style="min-width: 150px">Quận/Huyện</label>
                                    <select multiple="multiple" name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa();"> 
                                    </select>
                                    <input type="hidden" value="${quanHuyenId}" id="quanHuyenIds"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4"> 
                            <div class="input-group">
                                <label class=" input-group-addon" style="min-width: 150px">Phường/Xã</label>
                                <select multiple="multiple" name="phuongXaId" id="phuongXaId" class="form-control"> 
                                </select>
                                <input type="hidden" value="${phuongXaId}" id="phuongXaIds"/>    
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="input-group">
                                <label class=" input-group-addon" style="min-width: 150px">Code, Tên CSHT</label>
                                <input name="code" value="${code}"
                                       type="text" class="form-control" id="exampleInputEmail1"
                                       placeholder="Mã hoặc tên CSHT">
                            </div>
                        </div>
                        <div class="clearfix"></div>

                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i>  <spring:message code="admin.common.search" /></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-12 col-md-12 col-lg-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách vị trí</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive"  >
                    <div class="tablePagingId" style=" overflow-x:scroll !important;;overflow-y:hidden !important;;">
                        <button type="button" id="export" class="btn btn-primary" 
                                onclick="exportExcel()">Export excel</button>
                        <table id="treeadvanced" class="table table-bordered">
                            <thead>
                                <tr>
                                    <th width="170">Type</th>
                                    <th width="140" >Mã</th>
                                    <th min-width="140">Tên CSHT</th>  
                                    <th width="140" class="text-center" style="text-align: center;">Mã quy hoạch<br> vị trí</th>
                                    <th width="220">Địa chỉ</th> 
                                    <th>LATITUDE</th>
                                    <th>LONGITUDE</th> 
                                    <th>Tỉnh/TP</th> 
                                    <th>Quận/Huyện</th> 
                                    <th>Phường/Xã</th>
                                    <th>Thiết bị</th>
                                    <th>Nhóm CSHT</th>
                                    <th>Vị trí Links</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="item" items="${list}" varStatus="status">                             
                                    <tr data-tt-id='${item.buildingBO.id}'>

                                        <td class="truncate" ><span class='buildingIcon'>CSHT</span></td>

                                        <td>${item.buildingBO.code}  <a data-toggle="modal" data-target="#mapModal" onclick="viewMap(${item.buildingBO.id})" href="#"
                                                                        title="View Map">
                                                <img src="<%=request.getContextPath()%>/resources/img/equipment/maps-icon.png">
                                            </a>  </td>
                                        <td>${item.buildingBO.name}</td>
                                        <td>${item.buildingBO.planningCode}</td>
                                        <td>${item.buildingBO.address}</td>

                                        <td>${item.buildingBO.lat}</td>
                                        <td>${item.buildingBO.lon}</td>
                                        <td>${item.buildingBO.tinhName}</td>
                                        <td>${item.buildingBO.quanName}</td>
                                        <td>${item.buildingBO.phuongName}</td>


                                        <td></td>
                                        <td>${item.buildingBO.nhomCSHT}</td>
                                        <td> <a href="#" onclick="getBuildingLink(${item.buildingBO.id}, '${item.buildingBO.code}')" data-toggle="modal" data-target="#myModal">Chi tiết</a> </td>
                                        <td>
                                            <a style="cursor: pointer" href="<%=request.getContextPath()%>/object/findLogObject?object=BUILDING&objectCode=${item.buildingBO.code}"
                                               title="Lịch sử CSHT" >
                                                <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                            </a>
                                            &nbsp;
                                            <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/building/view/{id}')}">
                                                <a href="<%=request.getContextPath()%>/building/view/${item.buildingBO.id}"
                                                   title="Sửa">
                                                    <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                </a>
                                                &nbsp;
                                            </c:if>

                                            <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/building/delete/{id}')}">
                                                <a href="<%=request.getContextPath()%>/building/delete/${item.buildingBO.id}"
                                                   title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                    <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                </a>
                                            </c:if>
                                            &nbsp;
                                            <a  onclick="showDetail(${item.buildingBO.id})"
                                               title="Chi tiết">
                                                <img src="<%=request.getContextPath()%>/image/icon/info_box_blue.png">
                                            </a>

                                        </td>
                                    </tr>

                                    <c:forEach var="itemPT" items="${item.phuTroBO}">
                                        <tr data-tt-id='${itemPT.id}' data-tt-parent-id='${item.buildingBO.id}'>
                                            <td class="truncate" ><span class='phutroIcon'>Phụ trợ</span></td>
                                            <td><a href="#" onclick="getPhuTro(${itemPT.id}, '${itemPT.id}')" data-toggle="modal" data-target="#detailModal">${itemPT.id}</a></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </c:forEach>    
                                    <c:forEach var="itemTram" items="${item.tram}">
                                        <tr data-tt-id='${itemTram.id}' data-tt-parent-id='${item.buildingBO.id}'>
                                            <td class="truncate" ><span class='btsIcon'>${itemTram.tenNeType}</span></td>
                                            <td class="truncate"><a href="#" onclick="getNode(${itemTram.id},${itemTram.neTypeId}, '${itemTram.code}')" data-toggle="modal" data-target="#detailModal">${itemTram.code}</a></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>    
                                            <td>${itemTram.tenThietBi}</td>                            
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <c:forEach var="itemCell" items="${item.cell}">
                                            <c:if test="${itemCell.nodeChaId == itemTram.id}" >
                                                <tr data-tt-id='${itemCell.id}' data-tt-parent-id='${itemTram.id}'>
                                                    <td class="truncate" ><span class='rackIcon'>${itemCell.tenNeType}</span></td>
                                                    <td class="truncate"><a href="#" onclick="getNode(${itemCell.id},${itemCell.neTypeId}, '${itemCell.code}')" data-toggle="modal" data-target="#detailModal">${itemCell.code}</a></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td>${itemCell.tenThietBi}</td>                            
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </c:if> 
                                        </c:forEach> 
                                    </c:forEach>

                                </c:forEach>       
                            </tbody>                                    
                        </table>   
                    </div>
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

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách Building Links đến Building: <span style="font-weight: bold" id="mybuilding"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Mã Building</th> 
                            </tr>
                        </thead>
                        <tbody id="buildlinklist">   
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>
<div id="mapModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Vị trí bản đồ</span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <iframe width="100%" height="450" frameborder="0"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>

<div id="detailModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Chi tiết  <span style="font-weight: bold" id="mynodes"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <div id="detailDiv"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>

<div id="detailBuilding" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Thông tin chi tiết</h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <div id="divDetail"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>
<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/jquery.treetable.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.treetable.theme.custom.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.treetable.js"></script>
<script>
                                                        function exportExcel() {
                                                            var url = window.location.href;
                                                            var param = '';
                                                            if (url.indexOf('?') > -1) {
                                                                param = url.substring(url.indexOf('?'), url.length);
                                                            }
                                                            var urlGet = '<%=request.getContextPath()%>/building/export' + param;
                                                            location.href = urlGet;
                                                        }

//lay ra danh sach tinhtp theo khu vuc
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
                                                                        var htmlx = '<tr><td>' + i + '</td><td><a href="${pageContext.request.contextPath}/building/detail/' + entry.id + '" > ' + entry.code + '</a></td><tr>';
                                                                        html += htmlx;

                                                                    });

                                                                }
                                                                ;
                                                                $('#buildlinklist').html(html);

                                                            });
                                                        }

                                                        function getNode(id, type, code)
                                                        {
                                                            $('#mynodes').text(code);
                                                            var linkDetail = '${pageContext.request.contextPath}/nodes/view/' + type + '/' + id;

                                                            if (type == 5 || type == 6 || type == 7) {
                                                                linkDetail = '${pageContext.request.contextPath}/cells/detail/' + id + '/' + type;

                                                            } else if (type == 2 || type == 3 || type == 8) {
                                                                linkDetail = '${pageContext.request.contextPath}/nodes/detail/' + id + '/' + type

                                                            }
                                                            $.get(linkDetail, function (data) {
                                                                $('#detailDiv').html(data);
                                                            });
                                                        }
                                                        function viewMap(id) {
                                                            $("#mapModal iframe").prop({'src': '${pageContext.request.contextPath}/mapGeo/popup/' + id});
                                                        }

                                                        function getPhuTro(id, code)
                                                        {
                                                            $('#mynodes').text('phụ trợ ' + code);
                                                            var linkDetail = '${pageContext.request.contextPath}/phutro/detail/' + id;

                                                            $.get(linkDetail, function (data) {
                                                                $('#detailDiv').html(data);
                                                            });
                                                        }

                                                        $(document).ready(function () {
//                                                            var tinhId = $("#tinhTpId").val();
//                                                            if (tinhId != '')
//                                                            {
//                                                                getListHuyen(${tinhTpId});
                                                            if ($("#quanHuyenId").val() != '')
                                                                getListPhuongXa(${quanHuyenId});
//                                                            }

                                                            $('.truncate').tooltip();




                                                            $("#treeadvanced").treetable({expandable: true});

                                                            // Highlight selected row
                                                            $(".indenter").each(function () {
                                                                $(this).css('background-image', $(this).find('a').css('background-image'));
                                                            });

                                                            $("#treeadvanced tbody").on("mousedown", "tr", function () {
                                                                $(".selected").not(this).removeClass("selected");
                                                                $(this).toggleClass("selected");
                                                                $(this).find('.indenter a').click();
                                                                $(this).find('.indenter').css('background-image', $(this).find('.indenter a').css('background-image'));
                                                            });
//                                                          
                                                            $('#tinhTpId').multiselect(({
                                                                maxHeight: 200,
                                                                buttonWidth: '100%',
                                                                enableFiltering: true,
                                                                includeSelectAllOption: true,
                                                                onChange: function (element, checked) {
                                                                }
                                                            }));

                                                            $('#quanHuyenId').multiselect(({
                                                                maxHeight: 200,
                                                                buttonWidth: '100%',
                                                                enableFiltering: true,
                                                                includeSelectAllOption: true,
                                                                onChange: function (element, checked) {
                                                                }
                                                            }));

                                                            $('#phuongXaId').multiselect(({
                                                                maxHeight: 200,
                                                                buttonWidth: '100%',
                                                                enableFiltering: true,
                                                                includeSelectAllOption: true,
                                                                onChange: function (element, checked) {
                                                                }
                                                            }));
                                                            $('#khuvucId').multiselect(({
                                                                maxHeight: 200,
                                                                buttonWidth: '100%',
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

                                                        function hdsd(media) {
                                                            window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
                                                        }
                                                        function showDetail(buildingId) {
                                                            var linkDetail = '${pageContext.request.contextPath}/building/detail/' + buildingId;
                                                            $.get(linkDetail, function (data) {
                                                                $('#divDetail').html(data);
                                                                $('#detailBuilding').modal('show');
                                                            });
                                                            $('#divDetail').find('input, textarea, button, select').attr('disabled', 'disabled');
                                                        }
</script>

