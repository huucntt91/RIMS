
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
        Quản lý cấu hình cảnh báo
    </h1>
    <ol class="breadcrumb">

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
                                <select name="neTypeId" id="neTypeId" class="form-control"> 
                                    <option value="">--- Chọn NE TYPE ---</option>
                                    <c:forEach var="neBO" items="${neList}">
                                        <option  
                                            value="${neBO.id}"  <c:choose>
                                                <c:when test="${neBO.id == neTypeId}">
                                                    selected    
                                                </c:when>    
                                            </c:choose>

                                            >${neBO.name}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <select name="thietBiId" id="thietBiId" class="form-control"> 
                                    <option value="">--- Chọn Vender ---</option>
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
                                <select name="status" id="status" class="form-control"> 
                                    <option  value="">--- Chọn trạng thái ---</option>
                                    <option ${status}   <c:if test='${status==NE_REG_ON}'> selected </c:if> value="${NE_REG_ON}">Đăng ký on air</option>
                                    <option <c:if test='${status==NE_REG_OFF}'> selected </c:if> value="${NE_REG_OFF}">Đăng ký off air</option>
                                    <option <c:if test='${status==NE_APPROVE_ON}'> selected </c:if> value="${NE_APPROVE_ON}">On air</option>
                                    <option <c:if test='${status==NE_APPROVE_OFF}'> selected </c:if> value="${NE_APPROVE_OFF}">Off air</option>
                                    <option <c:if test='${status==NE_UNAPPROVE_ON}'> selected </c:if> value="${NE_UNAPPROVE_ON}">Hủy on air</option>
                                    <option <c:if test='${status==NE_UNAPPROVE_OFF}'> selected </c:if> value="${NE_UNAPPROVE_OFF}">Hủy off air</option>
                                    </select>  
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon">Tỉnh/TP</label>


                                        <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen(0);"> 

                                        <c:forEach var="tinhBO" items="${tinhList}">
                                            <option  
                                                value="${tinhBO.tinhTpId}" 
                                                <c:if test='${fn:contains(tinhTpId,tinhBO.tinhTpId)}' >  selected="selected" </c:if>

                                                    >${tinhBO.tenTinhTp}</option>
                                        </c:forEach>

                                    </select>  
                                </div>


                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon">Quận/Huyện</label>
                                    <select multiple="multiple"  name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa(0);"> 
                                        <c:forEach var="huyenBO" items="${huyenList}">
                                            <option  
                                                <c:if test='${fn:contains(quanHuyenId,huyenBO.quanHuyenId)}' >  selected="selected" </c:if>
                                                value="${huyenBO.quanHuyenId}"  


                                                >${huyenBO.tenQuanHuyen}</option>
                                        </c:forEach>
                                    </select>  
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4"> 
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon">Phường/Xã</label>
                                    <select name="phuongXaId" id="phuongXaId" class="form-control" > 
                                        <c:forEach var="phuongxaBO" items="${phuongXaList}">
                                            <option  
                                                <c:if test='${fn:contains(phuongXaId,phuongxaBO.phuongXaId)}' >  selected="selected" </c:if>
                                                value="${phuongxaBO.phuongXaId}"  


                                                >${phuongxaBO.tenPhuongXa}</option>
                                        </c:forEach>
                                    </select>  
                                </div>
                            </div>
                        </div>

                        <div class="clearfix" ></div>


                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon">Khu vực</label>
                                    <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control"
                                            > 
                                        <!--<option value="">--- Chọn Tỉnh/Thành Phố ---</option>-->
                                        <c:forEach var="tinhBO" items="${khuvucList}">
                                            <option  <c:if test='${fn:contains(khuvucId,tinhBO.id)}' >  selected="selected" </c:if>
                                                value="${tinhBO.id}"  
                                                >${tinhBO.name}</option>
                                        </c:forEach>


                                    </select>                                  
                                </div>


                            </div>
                        </div>
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

        <div class="col-xs-12" >

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách Nodes</h3>
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

                                <th>Trạng thái</th>
                                <th>Cảnh báo</th>
                                <!--                                <th>Node Links</th>
                                                                <th style="width: 82px;">Chức năng</th>-->
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="item" items="${list}" varStatus="status">                                        
                                <tr>

                                    <td> 
                                        <input type="hidden" class="node_id" value="${item.id}"/>
                                        <input type="hidden" class="type_id" value="${item.neTypeId}"/>
                                        ${startRow + (status.index)}
                                    </td>
                                    <td>${item.code}</td>
                                    <td>${item.tenNeType}</td>
                                    <td>${item.codeBuilding}</td>

                                    <td><c:choose>
                                            <c:when test="${item.status==NE_REG_ON}">                                                            
                                                Đăng ký on air
                                                <br />
                                            </c:when>    
                                            <c:when test="${item.status==NE_APPROVE_ON}">
                                                On air
                                                <br />
                                            </c:when> 
                                            <c:when test="${item.status==NE_UNAPPROVE_ON}">
                                                Hủy duyệt On air
                                                <br />
                                            </c:when>    
                                            <c:when test="${item.status==NE_REG_OFF}">
                                                Đăng ký off air
                                                <br />
                                            </c:when>    
                                            <c:when test="${item.status==NE_APPROVE_OFF}">
                                                Duyệt off air
                                                <br />
                                            </c:when> 
                                            <c:when test="${item.status==NE_UNAPPROVE_OFF}">
                                                Hủy duyệt off air
                                                <br />
                                            </c:when> 

                                            <c:otherwise>
                                                Inactive
                                                <br />
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td> 
                                        <a  href="#" onclick="getNodeLink(${item.id}, '${item.code}')" 
                                            data-toggle="modal" data-target="#myModal">Chi tiết</a> 
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
                <h4 class="modal-title">Danh sách người nhận cảnh báo Node <span style="font-weight: bold" id="mynodes"></span></h4>
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

<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>


<script>
    function regOff(nodeId, code) {

        var status =<%=Constants.NE_REG_OFF%>;


        $("#myModalLabel").html('Đăng ký off node ' + code);
        $(".modal-body #status").val(status);
        $(".modal-body #nodeId").val(nodeId);

    }
    function getListHuyen(tinh)
    {
        var id = $("#tinhTpId").val();
        if (id == null) {
            return;
        }
        $.get("${pageContext.request.contextPath}/dv/getHuyenByListTinh/" + id, function(data) {
//            var html = '<option value="" >--- Quận / Huyện ---</option>';
            if (data.length > 0) {
                $('#quanHuyenId')
                        .find('option')
                        .remove()
                        .end()
//                        .append('<option value="whatever">text</option>')
//                        .val('whatever')
                        ;
                data.forEach(function(entry) {
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
        $.get("${pageContext.request.contextPath}/dv/getPhuongByListHuyen/" + id, function(data) {
//            var html = '<option value="" >--- Quận / Huyện ---</option>';
            if (data.length > 0) {
                $('#phuongXaId')
                        .find('option')
                        .remove()
                        .end()
//                        .append('<option value="whatever">text</option>')
//                        .val('whatever')
                        ;
                data.forEach(function(entry) {
                    $('#phuongXaId').append('<option value="' + entry.phuongXaId + '">' + entry.tenPhuongXa + '</option>');
                });
                $('#phuongXaId').multiselect('rebuild');
            }
            ;
        });
    }

    function getNodeLink(id, code)
    {
        $('#mynodes').text(code);
        $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/dataAudit/getLstConfig/' + id});
    }

    $(document).ready(function() {
        //$('.navbar-btn').click();
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
            onChange: function(element, checked) {
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
            onChange: function(element, checked) {
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
            onChange: function(element, checked) {
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
            onChange: function(element, checked) {
//                var brands = $('#kpiLst option:selected');
//                var textTmp = '';
//                $(brands).each(function (index, brand) {
//                    textTmp += $(this).val() + ",";
//                });
//                $('#txtEvents').val(textTmp);
            }
        }));
        $('#example1 tbody').on('click', 'tr', function() {
            if ($(this).hasClass('selected')) {
                //$(this).removeClass('selected');
            } else {
                $('#example1 tbody').find('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
            var node_id = $(this).find('.node_id').val();
            var type_id = $(this).find('.type_id').val();
            viewDetail(node_id, type_id);

        });
    });
    function viewDetail(id, type) {
        var linkDetail = '${pageContext.request.contextPath}/nodes/view/' + type + '/' + id;

        if (type == 5 || type == 6 || type == 7) {
            linkDetail = '${pageContext.request.contextPath}/cell/detail/' + id + '/' + type;

        }
        else if (type == 2 || type == 3 || type == 8) {
            linkDetail = '${pageContext.request.contextPath}/dataAudit/detail/' + id + '/' + type

        }
        $.get(linkDetail, function(data) {
            $('#detailDiv').html(data);
        });
    }
</script>

