
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
        Quản lý IP layer3
    </h1>
    <ol class="breadcrumb">
<!--        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/mane/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm ManE</span> 
        </button>-->
    </ol>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                </div>

                <form method="GET" id="frm_search">
                    <div class="box-body"> 
                        <div class="col-md-4">
                            <div class="form-group">          
                                <div class="input-group">
                                    <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                                    <select name="tinhTpId"  id="tinhTpId" class="form-control" required="true" onchange="getTnodes();"> 
                                        <option  value="" >--- Chọn Tỉnh/TP ---</option>
                                        <c:forEach var="tinhBO" items="${tinhThanhPhoLst}">
                                            <option  value="${tinhBO.tinhTpId}" <c:if test = '${tinhBO.tinhTpId  == tinhTpId}'> selected="selected" </c:if> >${tinhBO.tenTinhTp}</option>
                                        </c:forEach>
                                    </select>
                                </div> 
                            </div>
                        </div>   
                        <div class="col-md-4">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">                                    
                                    <label class=" input-group-addon">Loại thiết bị</label>
                                    <select name="typeId" id="typeId" class="form-control" required="true" onchange="getTnodes();">
                                        <option value="" >--- Chọn loại ---</option>
                                        <option value="1" <c:if test = '${typeId  == 1}'> selected="selected" </c:if> >AGG</option>
                                        <option value="2" <c:if test = '${typeId  == 2}'> selected="selected" </c:if> >UPE</option>
                                        <option value="3" <c:if test = '${typeId  == 3}'> selected="selected" </c:if> >NPE</option> 
                                        <option value="4" <c:if test = '${typeId  == 4}'> selected="selected" </c:if> >DSLAM</option> 
                                        <option value="5" <c:if test = '${typeId  == 5}'> selected="selected" </c:if> >SWITCH</option> 
                                        <option value="6" <c:if test = '${typeId  == 6}'> selected="selected" </c:if> >GPON</option> 
                                        <option value="7" <c:if test = '${typeId  == 7}'> selected="selected" </c:if> >P ROUTER</option> 
                                        <option value="8" <c:if test = '${typeId  == 8}'> selected="selected" </c:if> >PE</option> 
                                        <option value="9" <c:if test = '${typeId  == 9}'> selected="selected" </c:if> >ASBR</option> 
                                        <option value="10" <c:if test = '${typeId  == 10}'> selected="selected" </c:if> >NIX</option> 
                                        <option value="11" <c:if test = '${typeId  == 11}'> selected="selected" </c:if> >BRAS</option> 
                                        <option value="13" <c:if test = '${typeId  == 13}'> selected="selected" </c:if> >MxU</option> 
                                        </select>                                  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <div class="input-group">                                    
                                        <label class=" input-group-addon">Node</label>
                                        <select  name="tnodes" id="tnodes" class="form-control" required="true">

                                    </select>                                  
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix" ></div>
                    <!-- /.box-body -->
                    <div class="box-footer" align="center" >
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                    <button type="button" id="export" class="btn btn-primary" 
                            onclick="location.href = '<%=request.getContextPath()%>/jasper/exportIplayer3?tnode_id='+ $('#tnodes').val()">Xuất excel</button>
                    </div>

                </form>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách Ip layer3</h3>

                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
<!--                    <button type="button" id="export" class="btn btn-primary" 
                            onclick="location.href = '<%=request.getContextPath()%>/jasper/exportBroadBand?code='
                                            + $('#code').val() + '&name=' + $('#name').val() + '&typeId=' + $('#typeId').val()
                                            + '&khuvucId=' + $('#khuvucId').val() + '&tinhTpId=' + $('#tinhTpId').val()
                                            + '&quanHuyenId=' + $('#quanHuyenId').val()
                                            + '&phuongXaId=' + $('#phuongXaId').val()">Export excel</button>-->
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên Giao diện</th>   
                                <th>IP</th> 
                                <th>Subnet</th> 
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="item" items="${list}" varStatus="status">                                        
                                <tr>
                                    <td>${startRow + (status.index)}</td>
                                    <td>${item.ipLayer3Name}</td>
                                    <td>${item.ip}</td> 
                                    <td>${item.subnet}</td> 
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



    $(document).ready(function () {
        getTnodes();
    });

    function getTnodes() {
        var tnode_id = '${tnodes}';
        var typeId = $("#typeId").val();
        var tinhTpId = $("#tinhTpId").val();
        if (tinhTpId == null || typeId == null)
            return;
        $.get("${pageContext.request.contextPath}/interface/findTnodes?typeId=" + typeId + "&tinhTpId=" + tinhTpId, function (data) {
            var html = '<option value="" >--- Chọn node ---</option> ';
            if (data.length > 0) {
                data.forEach(function (data) {
                    var htmlx = '<option value="' + data.id + '" ';
                    if(data.id == tnode_id ) htmlx += 'selected="selected"';
                    htmlx += '>' + data.code + '</option>';
                    html += htmlx;
                });
            }
            $('#tnodes').html(html);
//            $('#tnodes').multiselect('rebuild');
        });
    }

</script>

