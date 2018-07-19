
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>

<section class="content-header">
    <h1>
        Quản lý Building
    </h1>
    <ol class="breadcrumb">

        <button class="btn btn-info btn-sm"  data-toggle="modal" data-target="#myBuilding" id="btn_building" >
            <span><i class="fa fa-fw fa-plus"></i>PM</span> 
        </button>

        <button class="btn btn-info btn-sm"  data-toggle="modal" data-target="#myFm" id="fm_btn">
            <span><i class="fa fa-fw fa-plus"></i>FM</span> 
        </button>

    </ol>
</section>
<section class="content">            

    <div class="box">
        <div class="box-header">
            <h3 class="box-title">Tìm kiếm trạm quy hoạch</h3>
        </div>
        <div class="box-body">
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Mã trạm quy hoạch</label>
                        <input type="text" class="form-control"  id="MA_QUY_HOACH1" name="MA_QUY_HOACH" placeholder="Tên trạm dự án"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group">
                        <label class=" input-group-addon" >Tên trạm quy hoạch</label>
                        <input type="text" class="form-control"  id="TEN_QUY_HOACH1" name="TEN_QUY_HOACH" placeholder="Tên trạm quy hoạch"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group" >          
                    <div class="input-group">
                        <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                        <select name="TINH_TP_ID" id="TINH_TP_ID1" class="form-control"> 
                            <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                            <c:forEach var="tinhBO" items="${tinhThanhPhoLst}">
                                <option  
                                    value="${tinhBO.tinhTpId}" >${tinhBO.tenTinhTp}</option>
                            </c:forEach>
                        </select>
                    </div> 
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group" > 
                    <div class="input-group">
                        <label class=" input-group-addon" >Loại công nghệ</label>
                        <select name="ID_LOAI_CONG_NGHE" id="ID_LOAI_CONG_NGHE1" class="form-control" >
                            <option value="0">-Chọn loại công nghệ-</option>
                            <c:forEach var="loaiCongNghe" items="${loaiCongNgheLst}">
                                <option value="${loaiCongNghe.id_loai_cong_nghe}" >${loaiCongNghe.ten_loai_cong_nghe}</option>
                            </c:forEach>
                        </select>  
                    </div>    
                </div>
            </div>

            <div class="clearfix"></div>
        </div>
        <!-- /.box-body -->
        <div class="box-footer">
            <button type="button" class="btn btn-primary" id="btn_search_cam_ket">Tìm kiếm</button>
        </div>
    </div> 
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách trạm quy hoạch</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div id="tablePagingId">

                        <table id="table1" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã Quy hoạch</th>
                                    <th>Tên quy hoạch</th>
                                    <th>Đơn vị chịu trách nhiệm</th>
                                    <th>Nguồn thiết bị</th>
                                    <th>Thời điểm đáp ứng thiết bị dự kiến</th>
                                    <th>Công nghệ đáp ứng</th>
                                    <th>Chủng loại thiết bị</th>
                                    <th>Thời gian đáp ứng thiết bị thực tế</th>
                                    <th>Khó khăn, vướng mắc</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>

</section>

<div id="myBuilding" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tìm Kiếm</span></h4>
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

<div id="myFm" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tìm Kiếm</span></h4>
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
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>


<script>


    $(document).ready(function () {
        $('#btn_building').click(function () {
            $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/pm_fm/popup?vnpCode=3GTQG0219&nodeType=NODEB'});
        });
        $('#fm_btn').click(function () {
            $("#myFm iframe").prop({'src': '${pageContext.request.contextPath}/pm_fm/popup_fm?vnpCode=3GHNI1306&nodeType=NODEB'});
        });

        $("#table1").DataTable({
            paging: true,
            searching: false,
            destroy: true,
            processing: true,
            serverSide: true,
            ajax: '${pageContext.request.contextPath}/project_station/initCamKetThietBi',
            "columns": [
                {"name": "stt", "orderable": false},
                {"name": "MA_QUY_HOACH" },
                {"name": "TEN_QUY_HOACH"},
                {"name": "dvi_TRACH_NHIEM_CCTB" ,"orderable": false},
                {"name": "NGUON_THIET_BI" ,"orderable": false},
                {"name": "THOI_DIEM_DAP_UNG_DU_KIEN"},
                {"name": "TEN_CONG_NGHE_DAP_UNG"},
                {"name": "CHUNG_LOAI_THIET_BI"},
                {"name": "THOI_DIEM_DAP_UNG_THUC_TE"},
                {"name": "THOI_DIEM_DAP_UNG_THUC_TE"},
                {"name": "CHUC_NANG"}
            ],
            "createdRow": function (row, data, index) {
                // xử lý cột trạng thái trong row thêm link xử lý
                var td_1_id = "td-10-" + index;
                $('td', row).eq(10).attr("id", td_1_id);
                // xử lý cột lựa chọn nội dung
                $('td', row).eq(10).html('<a href="<%=request.getContextPath()%>/project_station/viewCamKet/'+data[11]+'" title="Cập nhật"><img src="<%=request.getContextPath()%>/image/icon/edit_icon.png"> </a>');
            },
            "zeroRecords": "Không có dữ liệu được tìm thấy",
            "paginate": {
                "first": "Đầu tiên",
                "last": "Cuối cùng",
                "next": "Sau",
                "previous": "Trước"
            },
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
    });


</script>

