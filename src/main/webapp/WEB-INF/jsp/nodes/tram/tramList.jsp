<%@page import="com.vnpt.media.rims.common.Constants"%>
<%@page import="com.vnpt.media.rims.common.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="../../include/header.jsp"%>
<section class="content">

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h1 class="box-title"><i class="fa fa-check-square"></i> Danh sách BTS/NodeB/eNodeB cần duyệt</h1>
                </div>
                <div class="box-body table-responsive">
                    <div id="tablePagingId">

                        <table id="example1" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th><input type="checkbox" id="checkall"></th>
                                    <th>STT</th>
                                    <th>Mã Node</th>
                                    <th>Mã BSC/RNC</th>
                                    <th>Mã trạm dự án</th>
                                    <th>Mã CSHT</th>
                                    <th>Loại NE</th>
                                    <th>Tỉnh/TP</th>
                                    <th>Ngày hoạt động</th>
                                    <th>Hoàn cảnh ra đời</th>                                            
                                    <th>Tên người quản lý</th>
                                    <th>Tên cho quản lý</th>
                                    <th>Tên trên hệ thống</th>
                                    <th>Thiết bị</th>
                                    <th>Loại trạm</th>
                                    <th>Cấu hình</th>
                                    <th>Trạng thái</th>
                                    <th>Lý do</th>
                                  
                                </tr>
                            </thead>

                            <tbody>                                       
                                <c:forEach var="item" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td><input type="checkbox" value="${item.id}" class="checkitem"/></td>
                                        <td>${status.index+1}</td>
                                        <td>${item.code}</td>
                                        <td>${item.maNodeCha}</td>
                                        <td>${item.codeTramDA}</td>
                                        <td>${item.codeBuilding}</td>
                                        <td>${item.tenNeType}</td>
                                        <td>${item.donViName}</td>
                                        <td>${item.ngayHoatDong}</td>
                                        <td>${item.hoanCanhRaDoi}</td>
                                        <td>${item.tenNgQLy}</td>
                                        <td>${item.name}</td>
                                        <td>${item.tenTrenHeThong}</td>
                                        <td>${item.tenThietBi}</td>
                                        <td>${item.tenLoaiTram}</td>
                                        <td>${item.cauHinh}</td>

                                        <td>
                                            <c:choose>

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
                                        <td>${item.note}</td>
                                       
                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>
                    </div>
                    
                    <button id="btnDuyetAll" class="btn btn-primary">Duyệt</button>
                    <button id="btnCancelAll" class="btn btn-danger">Từ chối</button>
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
                <h4 class="modal-title">Nhập lý do</span></h4>
            </div>
            <div class="modal-body">

                <div class="box-body table-responsive">
                    <div class="form-group">

                        <textarea rows="4"
                                  type="text" class="form-control" 
                                  id="comment"   
                                  placeholder="Lý do"></textarea>

                        <input type="hidden" id="status" value="" />
                        <input type="hidden" id="nodeId" value="" />
                        <input type="hidden" id="parentCode" value="" />
                        <input type="hidden" id="codeProvince" value="" />
                        <input type="hidden" id="type" value="" />
                    </div>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button id="btnUpdateComment" onclick="fnComment()" class="btn btn-primary">Đồng ý</button>
                </div>
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
                    <button id="btnUpdateAllComment" onclick="fnAllComment()" class="btn btn-primary">Đồng ý</button>
                </div>
            </div>

        </div>


    </div>

</div>
<%@include file="../../include/footer.jsp"%>


<script type="text/javascript">
    function approve(nodeId, code, codeProvince, type) {
        $('#status').val(<%=Constants.NE_APPROVE_ON%>)
        $('#nodeId').val(nodeId);
        $('#parentCode').val(code);
        $('#codeProvince').val(codeProvince);
        if (type == '2') {
            type = '2G'
        } else if (type == '3') {
            type = '3G'
        } else if (type == '8') {
            type = '4G'
        }
        $('#type').val(type);
        $('.modal-title').text('Nhập lý do bạn đồng ý duyệt');

    }

    function unApprove(nodeId) {
        $('#status').val(<%=Constants.NE_UNAPPROVE_ON%>);
        $('#nodeId').val(nodeId);
        $('.modal-title').text('Nhập lý do bạn từ chối duyệt');
    }

    function approveOff(nodeId, code) {
        $('#status').val(<%=Constants.NE_APPROVE_OFF%>);
        $('#nodeId').val(nodeId);
        $('#parentCode').val(code);
        $('.modal-title').text('Nhập lý do bạn đồng ý duyệt');
    }

    function unApproveOff(nodeId) {
        $('#status').val(<%=Constants.NE_UNAPPROVE_OFF%>);
        $('#nodeId').val(nodeId);

        $('.modal-title').text('Nhập lý do bạn từ chối duyệt');
    }



    function fnComment()
    {
        $('#btnUpdateComment').attr('disabled','disabled');
        $.post('${pageContext.request.contextPath}/nodes/approve',
                {
                    status: $('#status').val(),
                    nodeId: $('#nodeId').val(),
                    comment: $('#comment').val(),
                    parentCode: $('#parentCode').val(),
                    codeProvince: $('#codeProvince').val(),
                    type: $('#type').val()
                },
                function (data) {

                    if (data == "1") {
                        //$('#myModal').modal('hide');
                        window.location.href = '${pageContext.request.contextPath}/nodes/approveOn';
                    } else
                    {
                        alert("Có lỗi xảy ra bạn hãy thử lại");
                           $('#btnUpdateComment').removeAttr('disabled');
                    }
                }
        );

    }

    $(function () {
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
            var ids = "";
            var rows = table.rows({ 'search': 'applied' }).nodes();
            $('input[type="checkbox"]', rows).each(function (i) {
                if ($(this).prop("checked")) {
                    flag = true;
                    ids += $(this).val() + ',';
                }
            });
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
    function checkSumit(status) {
        var flag = false;
        var ids = "";
        $(".checkitem").each(function (i) {
            if ($(this).prop("checked")) {
                flag = true;
                ids += $(this).val() + ',';
            }
        });
        if (!flag) {
            alert('Bạn cần click chọn bản ghi cần duyệt');
            return;
        }
        $('#statusAll').val(status);
        $('#ids').val(ids.substring(0,ids.length-1));
        $('#myModalAll').modal('show');
    }
    
    function fnAllComment()
    {
        $('#btnUpdateAllComment').attr('disabled','disabled');
        $.post('${pageContext.request.contextPath}/nodes/approveall',
                {
                    ids: $('#ids').val(),
                    status: $('#statusAll').val(),
                    comment: $('#commentAll').val()
                },
                function (data) {

                    if (data == "1") {
                        //$('#myModal').modal('hide');
                        window.location.href = '${pageContext.request.contextPath}/nodes/approveOn';
                    } else
                    {
                        alert("Có lỗi xảy ra bạn hãy thử lại");
                        $('#btnUpdateComment').removeAttr('btnUpdateAllComment');
                    }
                }
        );

    }
</script>
