
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content-header">
    <h1>
        Quản lý Building
    </h1>
    <ol class="breadcrumb">
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/building/preAdd')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/building/preAdd'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm Building</span> 
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
                       
                        <div class="col-md-4">
                            <div class="form-group">


                                <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen();"  > 
                                </select>
                                <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <select multiple="multiple" name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa();"> 
                                </select>
                                <input type="hidden" value="${quanHuyenId}" id="quanHuyenIds"/>
                            </div>
                        </div>
                        <div class="col-md-4"> 
                            <select multiple="multiple" name="phuongXaId" id="phuongXaId" class="form-control"> 
                            </select>
                            <input type="hidden" value="${phuongXaId}" id="phuongXaIds"/>            
                        </div>
                        <div class="col-md-8">
                            <div class="form-group" style="padding: 0 15px">
                                <input name="code" value="${code}"
                                       type="text" class="form-control" id="exampleInputEmail1"
                                       placeholder="Mã Building hoặc địa chỉ">
                            </div>
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
                    <h3 class="box-title">Danh sách Building</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Mã Building</th>   
                                <th>Địa chỉ</th> 
                                <th>LATITUDE</th>
                                <th>LONGITUDE</th> 
                                <th>Tỉnh/TP</th> 
                                <th>Quận/Huyện</th> 
                                <th>Phường/Xã</th>
                                <th>Building Links</th>
                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="item" items="${list}" varStatus="status">                                        
                                <tr>
                                    <td>${startRow + (status.index)}</td>

                                    <td>${item.code}</td>
                                    <td>${item.address}</td>
                                    <td>${item.lat}</td>
                                    <td>${item.lon}</td>
                                    <td>${item.tinhName}</td>
                                    <td>${item.quanName}</td>
                                    <td>${item.phuongName}</td>
                                    <td> <a href="#" onclick="getBuildingLink(${item.id}, '${item.code}')" data-toggle="modal" data-target="#myModal">Chi tiết</a> </td>
                                    <td>
                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/building/view/{id}')}">
                                            <a href="<%=request.getContextPath()%>/building/view/${item.id}"
                                               title="Sửa">
                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>  
                                        </c:if>
                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/building/delete/{id}')}">
                                            <a href="<%=request.getContextPath()%>/building/delete/${item.id}"
                                               title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                            </a>
                                        </c:if>
                                        &nbsp;

                                    </td>
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

<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>


<script>
    
    
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

    $(document).ready(function () {
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
//                var brands = $('#kpiLst option:selected');
//                var textTmp = '';
//                $(brands).each(function (index, brand) {
//                    textTmp += $(this).val() + ",";
//                });
//                $('#txtEvents').val(textTmp);
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

