
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>

<section class="content">            

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h2 class="box-title"><i class="fa fa-check-square"></i> Danh sách CSHT cần duyệt</h2>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive"  >
                    <div class="tablePagingId" style=" overflow-x:scroll !important;;overflow-y:hidden !important;;">
                        <table id="example1" class="table table-bordered">
                            <thead>
                                <tr>
                                    <th><input type="checkbox" id="checkall"></th>
                                    <th>STT</th>
                                    <th>Mã</th>
                                    <th>Tên CSHT</th>  
                                    <th>Địa chỉ</th> 
                                    <th>Latitude</th>
                                    <th>Longitude</th> 
                                    <th>Tỉnh/TP</th> 
                                    <th>Quận/Huyện</th> 
                                    <th>Phường/Xã</th>
                                    <th>Nhóm CSHT</th>
                               
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="item" items="${list}" varStatus="status">                             
                                    <tr data-tt-id='${item.id}'>
                                        <td><input type="checkbox" value="${item.id}" class="checkitem"/></td>
                                        <td>${status.index+1}</td>
                                        <td>${item.code}</td>
                                        <td>${item.name}</td>
                                        <td>${item.address}</td>

                                        <td>${item.lat}</td>
                                        <td>${item.lon}</td>
                                        <td>${item.tinhName}</td>
                                        <td>${item.quanName}</td>
                                        <td>${item.phuongName}</td>
                                        <td>${item.nhomCSHT}</td>
                                        
                                    </tr>
                                </c:forEach>       
                            </tbody>                                    
                        </table>   
                    </div>
                    <button id="btnDuyetAll" class="btn btn-primary">Duyệt tất cả</button>
                    <button id="btnCancelAll" class="btn btn-danger">Từ chối tất cả</button>
                </div>

            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<!-- Modal -->
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
                                  id="comment"   
                                  placeholder="Lý do"></textarea>
                        <input type="hidden" id="ids" value="" />
                        <input type="hidden" id="statusAll" value="" />
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


<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript">
    function fnComment()
    {
        $('#btnUpdateComment').attr('disabled', 'disabled');
        $.post('${pageContext.request.contextPath}/building/approveall',
                {
                    ids: $('#ids').val(),
                    status: $('#statusAll').val(),
                    comment: $('#comment').val()
                    
                },
                function (data) {
                    if (data == "1") {
                        //$('#myModal').modal('hide');
                        window.location.href = '${pageContext.request.contextPath}/building/approveOn';
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
            var rows = table.rows({'search': 'applied'}).nodes();
            if (!isChecked) {
                $('input[type="checkbox"]', rows).iCheck('check');
            } else {
                $('input[type="checkbox"]', rows).iCheck('uncheck');
            }
        });

        $('#btnDuyetAll').click(function () {
            var flag = false;
            var ids = "";
            var rows = table.rows({'search': 'applied'}).nodes();
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
            $('#ids').val(ids.substring(0, ids.length - 1));
            $('#myModalAll').modal('show');
        });

        $('#btnCancelAll').click(function () {
            var flag = false;
            var ids = "";
            var rows = table.rows({'search': 'applied'}).nodes();
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
            $('#ids').val(ids.substring(0, ids.length - 1));
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
        $('#ids').val(ids.substring(0, ids.length - 1));
        $('#myModalAll').modal('show');
    }

</script>

