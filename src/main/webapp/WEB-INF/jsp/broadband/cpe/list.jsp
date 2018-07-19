
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
        Quản lý CPE
    </h1>
    <ol class="breadcrumb">                
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/cpe/preAdd/12'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm CPE</span> 
        </button>
    </ol>
    <!--    <ol class="breadcrumb">
            <button class="btn btn-info btn-sm"  onclick="location.href = '%=request.getContextPath()%>/access/preAdd'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm access</span> 
            </button>
        </ol>-->
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
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Mã Node</label>
                                    <input name="code" value="${code}"
                                           type="text" class="form-control" id="exampleInputEmail1"
                                           placeholder="Mã TNODE">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Tên Node</label>
                                    <input name="name" value="${name}"
                                           type="text" class="form-control" id="exampleInputEmail1"
                                           placeholder="Tên TNODE">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix" ></div>
                    <!-- /.box-body -->
                    <div class="box-footer" align="center" >
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-12">
            <c:if test="${typeId==12}">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Danh sách thiết bị CPE</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>CODE</th>   
                                    <th>Name</th> 
                                    <th>TNode cha</th> 
                                    <th>Dòng thiết bị</th> 
                                    <th>Trạng thái</th> 
                                    <th>Tổng slot</th> 
                                    <th>Building</th>
                                    <th>IP</th>                                    
                                    <th>NOTE</th>
                                    <th>address</th>                                    
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="item" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + (status.index)}</td>
                                        <td>${item.code}</td>
                                        <td>${item.name}</td>
                                        <td>${item.chaName}</td>
                                        <td>${item.dongTBiName}</td>
                                        <td>${item.status}</td>
                                        <td>${item.totalSlot}</td>
                                        <td>${item.buildingName}</td>
                                        <td>${item.ip}</td>                                                                       
                                        <td>${item.note}</td> 
                                        <td>${item.address}</td> 

                                        <td>

                                            <a href="<%=request.getContextPath()%>/cpe/preUpdate/${item.id}/${item.typeId}"
                                               title="Sửa">
                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>  
                                            <a href="<%=request.getContextPath()%>/mane/preTnodeNode/${item.id}" title="Liên kết với Radio">
                                                <img src="<%=request.getContextPath()%>/image/icon/link-open-flat.png">
                                            </a> 

                                            <a href="<%=request.getContextPath()%>/cpe/delete/${item.id}/${item.typeId}"
                                               title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                            </a>

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
            </c:if>

            <!-- /.box -->
        </div>
    </div>
</section>

<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>


<script>
//    function getListHuyen(tinh)
//    {
//        var id = $("#tinhTpId").val();
//        if (tinh != 0)
//            id = tinh;
//        $.get("${pageContext.request.contextPath}/dv/getHuyen/" + id, function(data) {
//            var html = '<option value="" >--- Quận / Huyện ---</option>';
//            if (data.length > 0) {
//                data.forEach(function(entry) {
//                    var htmlx = '<option value="' + entry.quanHuyenId + '">' + entry.tenQuanHuyen + '</option>';
//                    html += htmlx;
//
//                });
//
//            }
//            ;
//            $('#quanHuyenId').html(html);
//            if (tinh != 0)
//                $('#quanHuyenId').val(${quanHuyenId});
//        });
//    }
//
//    function getListPhuongXa(huyen)
//    {
//
//        var id = $("#quanHuyenId").val();
//        if (huyen != 0)
//            id = huyen;
//        $.get("${pageContext.request.contextPath}/dv/getPhuong/" + id, function(data) {
//            var html = '<option value="">--- Phường / Xã ---</option>';
//            if (data.length > 0) {
//                data.forEach(function(entry) {
//                    var htmlx = '<option value="' + entry.phuongXaId + '">' + entry.tenPhuongXa + '</option>';
//                    html += htmlx;
//
//                });
//
//            }
//            ;
//            $('#phuongXaId').html(html);
//            if (huyen != 0)
//                $('#phuongXaId').val(${phuongXaId});
//        });
//    }
    function getListHuyen(tinh)
    {
        var id = $("#tinhTpId").val();
        if (id == null) {
            return;
        }
        $.get("${pageContext.request.contextPath}/dv/getHuyenByListTinh/" + id, function (data) {
//            var html = '<option value="" >--- Quận / Huyện ---</option>';
            if (data.length > 0) {
                $('#quanHuyenId')
                        .find('option')
                        .remove()
                        .end()
//                        .append('<option value="whatever">text</option>')
//                        .val('whatever')
                        ;
                data.forEach(function (entry) {
                    $('#quanHuyenId').append('<option value="' + entry.quanHuyenId + '">' + entry.tenQuanHuyen + '</option>');
                });
                $('#quanHuyenId').multiselect('rebuild');
            }
            ;
        });
    }

    function getListPhuongXa(huyen)
    {
        var id = $("#quanHuyenId").val();
        if (id == null) {
            return;
        }
        $.get("${pageContext.request.contextPath}/dv/getPhuongByListHuyen/" + id, function (data) {
//            var html = '<option value="" >--- Quận / Huyện ---</option>';
            if (data.length > 0) {
                $('#phuongXaId')
                        .find('option')
                        .remove()
                        .end()
//                        .append('<option value="whatever">text</option>')
//                        .val('whatever')
                        ;
                data.forEach(function (entry) {
                    $('#phuongXaId').append('<option value="' + entry.phuongXaId + '">' + entry.tenPhuongXa + '</option>');
                });
                $('#phuongXaId').multiselect('rebuild');
            }
            ;
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
//        var tinhId = $("#tinhTpId").val();
//        if (tinhId != '')
//        {
//            getListHuyen(${tinhTpId});
//            getListPhuongXa(${quanHuyenId});
//        }

        $('#tinhTpId').multiselect(({
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

        $('#quanHuyenId').multiselect(({
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
//                var brands = $('#kpiLst option:selected');
//                var textTmp = '';
//                $(brands).each(function (index, brand) {
//                    textTmp += $(this).val() + ",";
//                });
//                $('#txtEvents').val(textTmp);
            }
        }));
    });
</script>

