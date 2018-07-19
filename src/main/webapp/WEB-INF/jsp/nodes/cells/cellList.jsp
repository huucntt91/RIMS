<%@page import="com.vnpt.media.rims.common.Constants"%>
<%@page import="com.vnpt.media.rims.common.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>Duyệt và cấp mã cell<small></small></h1>
    <ol class="breadcrumb">
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <form:form method="GET" id="frm_search">
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon">Chọn loại cell</label>
                            <select name="neTypeId" id="neTypeId" class="form-control"> >
                                <!--<option value="">--- Chọn NE TYPE ---</option>-->                                        
                                <option <c:if test='${neTypeId==5}'> selected </c:if> value="5">Cell 2G</option>
                                <option <c:if test='${neTypeId==6}'> selected </c:if> value="6">Cell 3G</option>
                                <option <c:if test='${neTypeId==7}'> selected </c:if> value="7">Cell 4G</option>                                        
                                </select>  
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                </div>        
            </form:form>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-body table-responsive">
                    <div id="tablePagingId">
                        <table id="example1" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th><input type="checkbox" id="checkall"></th>
                                    <th>STT</th>
                                    <th>Mã đối tượng</th>
                                    <th>Mã đối tượng cha</th>
                                    <th>Tỉnh/TP</th>
                                    <th>Ngày hoạt động</th>
                                    <th>Hoàn cảnh ra đời</th>                                                                                        
                                    <th>Tên trên cho quản lý</th>
                                    <th>Tên trên hệ thống</th>
                                    <th>LAC</th>
                                    <th>CI</th>
                                    <th>Frequency Band</th>
                                    <th>Loại cell</th>
                                    <th>Trạng thái</th>
                                    <th>Lý do</th>
<!--                                    <th>Xử lý</th>-->
                                </tr>
                            </thead>
                            <!--<div align="right" style="margin-right: 50px;">${paging}</div>-->
                            <tbody>                                       
                                <c:forEach var="temp" items="${list_cell}" varStatus="status">                                        
                                    <tr>
                                        <td><input type="checkbox" value="${temp.id}" class="checkitem" data-item="${temp.maNodeCha}"/></td>
                                        <td>${status.index+1}</td>
                                        <td>${temp.code}</td>
                                        <td>${temp.maNodeCha}</td>                                                
                                        <td>${temp.donViName}</td>
                                        <td>${temp.ngayHoatDong}</td>
                                        <td>${temp.hoanCanhRaDoi}</td>
                                        <td>${temp.tenCell}</td>
                                        <td>${temp.tenTrenHeThong}</td>
                                        <td>${temp.lac}</td>
                                        <td>${temp.ci}</td>
                                        <td>${temp.tenBangTan}</td>                                                
                                        <td>${temp.tenLoaiTram}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${temp.status==NE_REG_ON}">                                                            
                                                    Đăng ký on air
                                                    <br />
                                                </c:when>    
                                                <c:when test="${temp.status==NE_APPROVE_ON}">
                                                    On air
                                                    <br />
                                                </c:when> 
                                                <c:when test="${temp.status==NE_UNAPPROVE_ON}">
                                                    Hủy duyệt On air
                                                    <br />
                                                </c:when>    
                                                <c:when test="${temp.status==NE_REG_OFF}">
                                                    Đăng ký off air
                                                    <br />
                                                </c:when>    
                                                <c:when test="${temp.status==NE_APPROVE_OFF}">
                                                    Duyệt off air
                                                    <br />
                                                </c:when> 
                                                <c:when test="${temp.status==NE_UNAPPROVE_OFF}">
                                                    Hủy duyệt off air
                                                    <br />
                                                </c:when> 

                                                <c:otherwise>
                                                    Inactive
                                                    <br />
                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                        <td>${temp.note}</td>
<!--                                        <td>
                                            <c:choose>                                                        
                                                <c:when test="${temp.status==NE_REG_ON}">                                                            
                                                    <a style="cursor: pointer"  onclick="approve(${temp.id}, '${temp.maNodeCha}','${temp.nodeChaId}', '${temp.code}');"
                                                       title="Duyệt đăng ký và cấp mã cell" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_check.png">
                                                    </a>                                                        
                                                    &nbsp;
                                                    <a style="cursor: pointer" onclick="unApprove(${temp.id}, '${temp.maNodeCha}','${temp.nodeChaId}', '${temp.code}');"
                                                       title="Hủy duyệt đăng ký cell" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_delete.png">
                                                    </a>                                                    
                                                    &nbsp;
                                                </c:when>

                                                <c:when test="${temp.status==NE_REG_OFF}">
                                                    <a style="cursor: pointer" onclick="approveOff(${temp.id}, '${temp.maNodeCha}','${temp.nodeChaId}', '${temp.code}');"
                                                       title="Duyệt y/c off cell" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                    </a>
                                                    &nbsp;
                                                    <a style="cursor: pointer" onclick="unApproveOff(${temp.id}, '${temp.maNodeCha}','${temp.nodeChaId}', '${temp.code}');"
                                                       title="Hủy y/c off cell" >
                                                        <img src="<%=request.getContextPath()%>/image/icon/icon_delete.png">
                                                    </a>                                                    
                                                    &nbsp;
                                                </c:when>                                                                                                                
                                            </c:choose>                                                    
                                        </td>-->
                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>
                    </div>
                            
                    <button id="btnDuyetAll" class="btn btn-primary">Duyệt tất cả</button>
                    <button id="btnCancelAll" class="btn btn-danger">Từ chối tất cả</button>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>                        
                <!--<h4 class="modal-title">Nhập lý do</span></h4>-->
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <%--<form:form method="POST" action="approve" commandName="approveForm">--%>
                <div class="box-body table-responsive">
                    <!--<iframe width="100%" height="150" frameborder="0"></iframe>-->
                    <form:form  method="POST"  action="${pageContext.request.contextPath}/cell/approve" commandName="approveForm">
                        <div class="form-group" style="padding: 0 5px">
                            <form:textarea rows="4" type="text" class="form-control" 
                                           path="comment"
                                           placeholder="Nhập lý do"/>
                            <form:hidden path="status" id="status"></form:hidden>
                            <form:hidden path="nodeId" id="nodeId"></form:hidden>
                            <form:hidden path="parentCode" id="parentCode"></form:hidden>
                            <form:hidden path="nodeChaId" id="nodeChaId"></form:hidden>
                            <input type="hidden" value="${neTypeId}" id="neTypeId" name="type" />
                            </div>

                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button  type="summit" id="btn1Comment" class="btn btn-primary">Đồng ý</button>
                            </div>
                    </form:form>
                </div>

                <%--</form:form>--%>
            </div>

        </div>
    </div>                 
</div>

<div class="modal fade" id="myModalAll" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Nhập lý do</span></h4>
            </div>
            <div class="modal-body">

                <div class="box-body table-responsive">
                    <div class="form-group">

                        <textarea rows="4"
                                  type="text" class="form-control" 
                                  id="commentAll"   
                                  placeholder="Lý do"></textarea>

                        <input type="hidden" id="ids" value="" />
                        <input type="hidden" id="statusAll" value="" />
                    </div>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button id="btnUpdateComment" onclick="fnAllComment()" class="btn btn-primary">Đồng ý</button>
                </div>
            </div>

        </div>


    </div>

</div>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
                                                        function approve(nodeId, parentCode, nodeChaId, code) {
                                                            if(parentCode.indexOf('DK_') > 0){
                                                                alert('Bạn phải duyệt mã Trạm xong mới được duyệt Cell');
                                                                return;
                                                            }
                                                            var status =<%=Constants.NE_APPROVE_ON%>;                                                            
                                                            $("#myModalLabel").html('Duyệt và cấp mã cell ' + code);
                                                            $(".modal-body #status").val(status);
                                                            $(".modal-body #nodeId").val(nodeId);
                                                            $(".modal-body #parentCode").val(parentCode);
                                                            $(".modal-body #nodeChaId").val(nodeChaId);
                                                            $('#myModal').modal('show');
                                                        }

                                                        function unApprove(nodeId, parentCode, nodeChaId, code) {
                                                            var status =<%=Constants.NE_UNAPPROVE_ON%>;
                                                            $("#myModalLabel").html('Hủy duyệt đăng ký cell ' + code);
                                                            $(".modal-body #status").val(status);
                                                            $(".modal-body #nodeId").val(nodeId);
                                                            $(".modal-body #parentCode").val(parentCode);
                                                            $(".modal-body #nodeChaId").val(nodeChaId);
                                                            $('#myModal').modal('show');

                                                        }

                                                        function approveOff(nodeId, parentCode, nodeChaId, code) {
                                                            var status =<%=Constants.NE_APPROVE_OFF%>;
                                                            $("#myModalLabel").html('Duyệt đăng ký Off cell ' + code);
                                                            $(".modal-body #status").val(status);
                                                            $(".modal-body #nodeId").val(nodeId);
                                                            $(".modal-body #parentCode").val(parentCode);
                                                            $(".modal-body #nodeChaId").val(nodeChaId);
                                                            $('#myModal').modal('show');
                                                        }

                                                        function unApproveOff(nodeId, parentCode, nodeChaId, code) {
                                                            var status =<%=Constants.NE_UNAPPROVE_OFF%>;
                                                            $("#myModalLabel").html('Hủy duyệt đăng ký Off cell ' + code);
                                                            $(".modal-body #status").val(status);
                                                            $(".modal-body #nodeId").val(nodeId);
                                                            $(".modal-body #parentCode").val(parentCode);
                                                            $(".modal-body #nodeChaId").val(nodeChaId);
                                                            $('#myModal').modal('show');
                                                        }

                                                        $(document).ready(function() {

                                                            $('#myModal iframe').on('load', function(e) {
                                                                var iframe1 = $('#myModal iframe').contents();
                                                                iframe1.find('#btnUpdateComment').on('click', function() {
                                                                    $('#myModal').modal('hide');
//                         window.opener.location.reload();

                                                                });
                                                            });
                                                        });

</script>
<script>
    $(function() {
        var table = $("#example1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
           
        $('.iCheck-helper').click(function () {
            var parent = $(this).parent().get(0);
            //var checkboxId = parent.getElementsByTagName('input')[0].id;
            $(parent).find('input').click();
        });


        $('#checkall').click(function () {
            var isChecked = $("#checkall").prop("checked");
            var rows = table.rows({ 'search': 'applied' }).nodes();
            if (!isChecked) {
                $('input[type="checkbox"]', rows).iCheck('check');
            } else {
                $('input[type="checkbox"]', rows).iCheck('uncheck');
            }
        });
        $('#btnDuyetAll').click(function () {
            var flag = false;
            var flagDk = false;
            var ids = "";
            var rows = table.rows({ 'search': 'applied' }).nodes();
            $('input[type="checkbox"]', rows).each(function (i) {
                if ($(this).prop("checked")) {
                    if($(this).attr('data-item').indexOf('DK_') > -1)
                    {
                        flagDk = true;
                    }
                    flag = true;
                    ids += $(this).val() + ',';
                }
            });
            if(flagDk){
                alert('Bạn phải duyệt mã Trạm xong mới được duyệt Cell');
                return;
            }
            if (!flag) {
                alert('Bạn cần click chọn bản ghi cần duyệt');
                return;
            }
            $('#statusAll').val(1); // dong y
            $('#ids').val(ids.substring(0,ids.length-1));
            $('#myModalAll').modal('show');
        });
        
        $('#btnCancelAll').click(function () {
            var flag = false;
            var ids = "";
            var rows = table.rows({ 'search': 'applied' }).nodes();
            $('input[type="checkbox"]', rows).each(function (i) {
                if ($(this).prop("checked")) {
                    flag = true;
                    ids += $(this).val() + ',';
                }
            });
            if (!flag) {
                alert('Bạn cần click chọn bản ghi cần hủy duyệt');
                return;
            }
            $('#statusAll').val(0);
            $('#ids').val(ids.substring(0,ids.length-1));
            $('#myModalAll').modal('show');
        });
    });
   
    
    function fnAllComment()
    {
        $('#btnUpdateComment').attr('disabled','disabled');
        $.post('${pageContext.request.contextPath}/nodes/approveall',
                {
                    ids: $('#ids').val(),
                    status: $('#statusAll').val(),
                    comment: $('#commentAll').val()
                },
                function (data) {

                    if (data == "1") {
                        //$('#myModal').modal('hide');
                        window.location.href = '${pageContext.request.contextPath}/cells/approveOn/init?neTypeId=' + $('#neTypeId').val();
                    } else
                    {
                        alert("Có lỗi xảy ra bạn hãy thử lại");
                        $('#btnUpdateComment').removeAttr('disabled');
                    }
                    
                }
        );

    }
</script>

