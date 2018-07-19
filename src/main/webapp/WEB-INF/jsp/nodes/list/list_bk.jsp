
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>

<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>-->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>

<!--<script src="js/jquery.multi-select.js"></script>-->
<section class="content-header">
    <h1>
        Quản lý Nodes
    </h1>
    <ol class="breadcrumb">
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'excelUpdateNode/init/1')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/excelUpdateNode/init/1'" >
                <span>  <img width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Cập nhật cell với Excel</span>
            </button>
        </c:if>
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'excelUpdateNode/init/2')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/excelUpdateNode/init/2'" >
                <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Cập nhật BTS/NODEB/ENODEB với Excel</span> 
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
                                <select name="neTypeId" id="neTypeId" class="form-control"> >
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
                        <div class="col-md-4">
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
                                <select name="status" id="status" class="form-control"> >
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

                        <div class="col-md-8">
                            <div class="form-group">
                                <input name="code" value="${code}"
                                       type="text" class="form-control" id="exampleInputEmail1"
                                       placeholder="Mã Node hoặc địa chỉ">
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon">Tỉnh TP</label>
                                    <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control"
                                            onchange="getListHuyen(0);"> >
                                        <!--<option value="">--- Chọn Tỉnh/Thành Phố ---</option>-->
                                        <c:forEach var="tinhBO" items="${tinhList}">
                                            <option 
                                                <c:if test='${fn:contains(tinhTpId,tinhBO.tinhTpId)}' >  selected="selected" </c:if>
                                                value="${tinhBO.tinhTpId}"  

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
                                    <select  multiple="multiple" name="quanHuyenId" id="quanHuyenId" class="form-control" 
                                             onchange="getListPhuongXa(0);"> >
                                        <!--<option value="">---Chọn Quận / Huyện ---</option>-->
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
                                    <select  multiple="multiple" name="phuongXaId" id="phuongXaId" class="form-control" > 
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

        <div class="col-md-8" >

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
                                <!--                                <th>Vender</th> 
                                                                <th>Đơn vị</th>-->
                                <th>Trạng thái</th>
                                <th>Node Links</th>
                                <th style="width: 100px;">Chức năng</th>
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
<!--                                    <td>${item.tenThietBi}</td>
                                    <td>${item.donViName}</td> -->
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
                                                Off air
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
                                        </c:choose></td>
                                    <td> <a href="#" onclick="getNodeLink(${item.id}, '${item.code}')" data-toggle="modal" data-target="#myModal">Chi tiết</a> </td>
                                    <td>
                                        <!--public static int NE_REG_ON = 112;
                                            public static int NE_APPROVE_ON = 211;
                                            public static int NE_UNAPPROVE_ON = 121;
                                            public static int NE_REG_OFF = 212;
                                            public static int NE_APPROVE_OFF = 111;
                                            public static int NE_UNAPPROVE_OFF = 221;-->
                                        &nbsp;
                                        <c:if test='${item.neTypeId == 5 || item.neTypeId == 6 || item.neTypeId == 7}'>   
                                            <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/history/init')}">
                                                <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/init?type=CELL&code=${item.id}"
                                                   title="Lịch sử cell" >
                                                    <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                                </a>
                                            </c:if>
                                            <c:if test="${item.status==NE_REG_ON}">

                                            </c:if>
                                            <c:if test="${item.status==NE_APPROVE_ON}">
                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                    <a href="<%=request.getContextPath()%>/cell/preUpdate/${item.id}/${item.neTypeId}"
                                                       title="Cập nhật cell" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                    </a>    
                                                </c:if>
                                                &nbsp;
                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                    <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}');"
                                                       title="Đăng ký Off cell" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                    </a>
                                                </c:if>
                                            </c:if>                                                 
                                            <c:if test="${item.status==NE_UNAPPROVE_ON}">
                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preRegUpdate')}">
                                                    <%--<c:if test="${fn:containsIgnoreCase((UserBO)(sessionScope.user).id, item.userInsert)}">--%>
                                                    <c:if test="${sessionScope.user.id==item.userInsert}">                                                        
                                                        <a href="<%=request.getContextPath()%>/cell/preRegUpdate/${item.id}/${item.neTypeId}"
                                                           title="Cập nhật thông tin đăng ký cell" >
                                                            <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                        </a>   
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${item.status==NE_REG_OFF}">

                                            </c:if>
                                            <c:if test="${item.status==NE_APPROVE_OFF}">

                                            </c:if>
                                            <c:if test="${item.status==NE_UNAPPROVE_OFF}">
                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                    <a href="<%=request.getContextPath()%>/cell/preUpdate/${item.id}/${item.neTypeId}"
                                                       title="Cập nhật cell" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                    </a>    
                                                </c:if>
                                                &nbsp;
                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                    <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}');"
                                                       title="Đăng ký Off cell" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                    </a>
                                                </c:if>
                                            </c:if>

                                        </c:if>

                                        <c:if test='${item.neTypeId == 2 || item.neTypeId == 3 || item.neTypeId == 8}'>   
                                            <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/history/init')}">
                                                <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/init?type=BTS&code=${item.id}"
                                                   title="Lịch sử BTS/NodeB/eNodeB" >
                                                    <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                                </a>
                                            </c:if>

                                            <c:if test="${item.status==NE_REG_ON}">

                                            </c:if>
                                            <c:if test="${item.status==NE_APPROVE_ON}">
                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/preUpdate')}">
                                                    <a href="<%=request.getContextPath()%>/nodes/preUpdate/${item.id}/${item.neTypeId}"
                                                       title="Cập nhật" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                    </a>    
                                                </c:if>
                                                &nbsp;
                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/regOffAir')}">
                                                    <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}');"
                                                       title="Đăng ký Off" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                    </a>
                                                </c:if>
                                            </c:if>                                                 
                                            <c:if test="${item.status==NE_UNAPPROVE_ON || item.status==NE_REG_ON }">

                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/preRegUpdate')}">
                                                    <%--<c:if test="${fn:containsIgnoreCase((UserBO)(sessionScope.user).id, item.userInsert)}">--%>
                                                    <c:if test="${sessionScope.user.id==item.userInsert}">                                                        
                                                        <a href="<%=request.getContextPath()%>/nodes/preRegUpdate/${item.id}/${item.neTypeId}"
                                                           title="Cập nhật thông tin đăng ký" >
                                                            <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                        </a>   
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${item.status==NE_REG_OFF}">

                                            </c:if>
                                            <c:if test="${item.status==NE_APPROVE_OFF}">

                                            </c:if>
                                            <c:if test="${item.status==NE_UNAPPROVE_OFF}">
                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/preUpdate')}">
                                                    <a href="<%=request.getContextPath()%>/nodes/preUpdate/${item.id}/${item.neTypeId}"
                                                       title="Cập nhật" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                    </a>    
                                                </c:if>
                                                &nbsp;
                                                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/regOffAir')}">
                                                    <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}');"
                                                       title="Đăng ký Off" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                    </a>
                                                </c:if>
                                            </c:if>

                                        </c:if>   

<!--                                        <a href="<%=request.getContextPath()%>/nodes/off/${item.id}"
title="Off" onclick="return confirm('Bạn có off không ?')">
<img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
</a>-->
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

        <div class="col-md-4">
            <div class="box">
                <div class="box-header"><h3 class="box-title">Thông tin chi tiết</h3></div>
                <!--<div class="box-body table-responsive">-->
                <div id="detailDiv">

                </div>

                <!--</div>-->
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModalOff" tabindex="-1" role="dialog" aria-labelledby="myModalLabelOff" aria-hidden="true">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="myModalLabel"></h4>

                </div>

                <div class="modal-body">

                    <%--<form:form method="POST" action="approve" commandName="approveForm">--%>
                    <div class="box-body table-responsive">
                        <!--<iframe width="100%" height="150" frameborder="0"></iframe>-->
                        <form:form  method="POST" action="${pageContext.request.contextPath}/cell/approve" commandName="approveForm">
                            <div class="form-group" style="padding: 0 5px">
                                <form:textarea rows="4" type="text" class="form-control" 
                                               path="comment"
                                               placeholder="Lý do"/>
                                <form:hidden path="status" id="status"></form:hidden>
                                <form:hidden path="nodeId" id="nodeId"></form:hidden>
                                <form:hidden path="parentCode" id="parentCode"></form:hidden>
                                <form:hidden path="nodeChaId" id="nodeChaId"></form:hidden>
                                </div>

                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button id="btnUpdateComment" type="summit" class="btn btn-primary">Đồng ý</button>
                                </div>
                        </form:form>
                    </div>

                    <%--</form:form>--%>
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
                <h4 class="modal-title">Danh sách Node Links đến Node <span style="font-weight: bold" id="mynodes"></span></h4>
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
        $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/nodes/getNodeLink/' + id});
    }

    $(document).ready(function() {


        //$('.navbar-btn').click();
//        var tinhId = $("#tinhTpId").val();
//        if (tinhId != null)
//        {
//            getListHuyen(${tinhTpId});
//            getListPhuongXa(${quanHuyenId});
//        }
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

        } else if (type == 2 || type == 3 || type == 8) {
            linkDetail = '${pageContext.request.contextPath}/nodes/detail/' + id + '/' + type

        }
        $.get(linkDetail, function(data) {
            $('#detailDiv').html(data);
        });
    }
</script>
<script>
    $(document).ready(function() {
// $("#tinhTpId").multiselect({
//        selectedValue:'1016'
//    });
//        var valArr = [1016, 1034];
//       var i = 0, size = valArr.length;
//        for (i; i < size; i++) {
//            $("#tinhTpId").multiselect("widget").find(":checkbox[value='" + valArr[i] + "']").attr("selected", "selected");
//            $("#tinhTpId option[value='" + valArr[i] + "']").attr("selected", 1);
//            $("#tinhTpId").multiselect("refresh");
//        }
//$('#tinhTpId').multiSelect('select', valArr);
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


    });
</script>

