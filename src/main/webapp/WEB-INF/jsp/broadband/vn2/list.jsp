
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
        Quản lý VN2
    </h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/vn2/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm mới</span> 
        </button>
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


                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Mã Node</label>
                                    <input name="code" value="${code}"
                                           type="text" class="form-control" id="code"
                                           placeholder="Mã TNODE">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Tên Node</label>
                                    <input name="name" value="${name}"
                                           type="text" class="form-control" id="name"
                                           placeholder="Tên TNODE">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">                                    
                                    <label class=" input-group-addon">Loại thiết bị</label>
                                    <select multiple="multiple"  name="typeId" id="typeId" class="form-control" value="${typeId}">
                                        <option value="7" <c:if test = '${fn:contains(typeId,7)}'> selected="selected" </c:if> >P ROUTER</option>
                                        <option value="8" <c:if test = '${fn:contains(typeId,8)}'> selected="selected" </c:if> >PE</option>
                                        <option value="9" <c:if test = '${fn:contains(typeId,9)}'> selected="selected" </c:if> >ASBR</option>                                                    
                                        <option value="10" <c:if test = '${fn:contains(typeId,10)}'> selected="selected" </c:if> >NIX</option> 
                                        <option value="11" <c:if test = '${fn:contains(typeId,11)}'> selected="selected" </c:if> >BRAS</option> 
                                        </select>                                  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group" style="padding: 0 15px">
                                    <div class="input-group">
                                        <label class=" input-group-addon">Khu vực</label>
                                        <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control"
                                                onchange="getTinhTp();"
                                                > 
                                            <!--<option value="">--- Chọn Tỉnh/Thành Phố ---</option>-->
                                        <c:forEach var="item" items="${khuvucList}">
                                            <option  value="${item.id}"  <c:if test = '${fn:contains(khuvucId,item.id)}'> selected="selected" </c:if> >${item.name}</option>
                                        </c:forEach>
                                    </select>

                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">          
                                <div class="input-group">
                                    <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                                    <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen();"  > 
                                    </select>
                                    <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                                </div> 
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px" >          
                                <div class="input-group">
                                    <label class=" input-group-addon" >Quận/Huyện</label>                                    
                                    <select multiple="multiple" name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa();"> 
                                    </select>
                                    <input type="hidden" value="${quanHuyenId}" id="quanHuyenIds"/>
                                </div> 
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">
                                    <label class=" input-group-addon" >Phường/Xã</label>
                                    <select multiple="multiple" name="phuongXaId" id="phuongXaId" class="form-control"> 
                                    </select>
                                    <input type="hidden" value="${phuongXaId}" id="phuongXaIds"/>
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
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách thiết bị VN2</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <button type="button" id="export" class="btn btn-primary" 
                            onclick="exportExcel();" ><spring:message code="admin.common.export.excel" /></button>
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>CODE</th>   
                                <th>Name</th> 
                                <th>Tỉnh/TP</th> 
                                <th>Loại thiết bị</th>
                                <th>Dòng thiết bị</th> 
                                <th>Tổng slot</th> 
                                <th>Building</th>
                                <th>IP</th>
                                <th>NOTE</th>
                                <th>Equipment</th>
                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="item" items="${list}" varStatus="status">                                        
                                <tr>
                                    <td>${startRow + (status.index)}</td>
                                    <td>${item.code}</td>
                                    <td>${item.name}</td>
                                    <td>${item.tenTinh}</td>
                                    <td>${item.typeName}</td>
                                    <td>${item.dongTBiName}</td>
                                    <td>${item.totalSlot}</td>
                                    <td>${item.buildingCode}</td>
                                    <td>${item.ip}</td>                                  
                                    <td>${item.note}</td> 
                                    <td><a href="#" onclick="openEQ(${item.id})">Chi tiết</a></td> 
                                    <td>

                                        <a href="<%=request.getContextPath()%>/vn2/preUpdate/${item.id}"
                                           title="Sửa">
                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                        </a>  
                                        <a href="<%=request.getContextPath()%>/mane/preTnodeNode/${item.id}" title="Liên kết với Radio">
                                            <img src="<%=request.getContextPath()%>/image/icon/link-open-flat.png">
                                        </a>  

                                        <a href="<%=request.getContextPath()%>/vn2/delete/${item.id}"
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
            <!-- /.box -->
        </div>
    </div>
</section>

<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/fileSaver.js" type="text/javascript"></script>

<script>
    function exportExcel(){
        var url = window.location.href;
        var param = '';
        if(url.indexOf('?') > -1){
            param = url.substring(url.indexOf('?'), url.length);
        }
        var urlGet = '<%=request.getContextPath()%>/broadband/export/vn2'  + param;
        $.get(urlGet, function (data) {
            fnExcelReport(data,"Danh_sach_VN2");
        });
    }
    
    function getListHuyen()
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

    function getListPhuongXa()
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
        
        $('#typeId').multiselect(({
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
    function openEQ(id)
    {
        window.open('${pageContext.request.contextPath}/eq/init?tnodeId=' + id, '_blank', 'width=700,height=500');
    }
</script>

