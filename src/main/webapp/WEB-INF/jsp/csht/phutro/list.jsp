
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
        <i class="fa fa-plug"></i> Quản lý Phụ trợ
    </h1>
    <ol class="breadcrumb">
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/building/view/{id}')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/phutro/preAdd'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm Phụ trợ</span> 
            </button>
        </c:if>

    </ol>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <form:form method="GET" id="frm_search" class="frmSearchPhuTro">
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
                            <div class="input-group">
                                <label class=" input-group-addon">Tỉnh TP</label>

                                <select style="width:100%;" multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen();"  > 
                                </select>
                                <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon">Quận/Huyện</label>
                                <select multiple="multiple" name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa();"> 
                                </select>
                                <input type="hidden" value="${quanHuyenId}" id="quanHuyenIds"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4"> 
                        <div class="input-group">
                            <label class=" input-group-addon">Phường/Xã</label>
                            <select multiple="multiple" name="phuongXaId" id="phuongXaId" class="form-control"> 
                            </select>
                            <input type="hidden" value="${phuongXaId}" id="phuongXaIds"/>    
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="form-group" style="padding: 0 15px">
                            <input name="code" value="${code}"
                                   type="text" class="form-control" id="exampleInputEmail1"
                                   placeholder="Mã hoặc tên CSHT">
                        </div>
                        </div>
                        
                    </div>
                    <div class="clearfix"></div>

                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> <spring:message code="admin.common.search" /></button>
                </div>
            </form:form>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách Phụ trợ</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <!--<button type="button" id="export" class="btn btn-primary" 
                            onclick="location.href = '<%=request.getContextPath()%>/jasper/exportPhutro?name='
                                            + $('#name').val() 
                                            + '&khuvucId=' + $('#khuvucId').val() + '&tinhTpId=' + $('#tinhTpId').val()">Export excel</button> -->
                    <button type="button" id="export" class="btn btn-primary" 
                            onclick="exportExcel()"><i class="fa "></i> Export excel</button>
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Mã CSHT</th>
                                <th>Tên CSHT</th>  
                                <th>Tỉnh/TP</th> 
                                <th>Truyền dẫn</th> 
                                <th>Anten</th>
                                <!--                                <th>Chung CSHT</th>
                                                                <th>Độ cao Anten</th>
                                                                <th>Độ cao nhà đặt</th>-->
                                <th>Tủ nguồn</th> 
                                <!--                                <th>Ngày HD tủ nguồn</th>
                                                                <th>Số module tủ nguồn </th>-->
                                <th>Máy nổ</th>  
                                <!--                                <th>Ngày HD máy nổ</th>
                                                                <th>Công suất máy nổ</th>
                                                                <th>Trạng thái đặt máy nổ</th>-->
                                <th>AcQuy</th>
                                <!--                                <th>Ngay HD accu</th>
                                                                <th>Dung lượng accu</th>-->

                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="item" items="${list}" varStatus="status">                                        
                                <tr>
                                    <td>${startRow + (status.index)}</td>

                                    <td>${item.code}</td>
                                    <td>${item.cshtName}</td>
                                    <td>${item.tenTinhTP}</td>
                                    <td>${item.truyenDan}</td>
                                    <td>${item.anTen}</td>
                                    <td>${item.tuNguon}</td>
                                    <td>${item.mayNo}</td>
                                    <td>${item.acQuy}</td>

                                    <td>
                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/phutro/view/{id}')}">
                                            <a href="<%=request.getContextPath()%>/phutro/view/${item.id}"
                                               title="Sửa">
                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>  
                                        </c:if>
                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/phutro/delete/{id}')}">
                                            <a href="<%=request.getContextPath()%>/phutro/delete/${item.id}"
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


<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>


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
    $(document).ready(function () {
        if ($("#quanHuyenId").val() != '')
            getListPhuongXa(${quanHuyenId});
                                         
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
</script>

