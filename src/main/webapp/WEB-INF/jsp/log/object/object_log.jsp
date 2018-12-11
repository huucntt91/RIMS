<%-- 
    Document   : list
    Created on : Aug 16, 2017, 10:30:01 AM
    Author     : Cyano
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content-header">
    <h1>
        Lịch sử tác động đối tượng <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_LICHSUTACDONG_RIMS.mp4');" >Hướng dẫn</button>
    </h1>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                </div>

                <form method="GET" action="'<%=request.getContextPath()%>/object/search'"  id="frm_search">
                    <div class="box-body"> 

                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Đối tượng</label>
                                    <select  name="object" id="object" class="form-control">
                                        <option value="">--Chọn đối tượng--</option>
                                        <option value="BUILDING" <c:if test='${object == "BUILDING"}'> selected </c:if> >CSHT</option>
                                        <option value="BTS" <c:if test='${object == "BTS"}'> selected </c:if> >BTS</option>
                                        <option value="NODEB" <c:if test='${object == "NODEB"}'> selected </c:if> >NODEB</option>
                                        <option value="ENODEB" <c:if test='${object == "ENODEB"}'> selected </c:if> >ENODEB</option>
                                        <option value="CELL2G" <c:if test='${object == "CELL2G"}'> selected </c:if> >CELL2G</option>
                                        <option value="CELL3G" <c:if test='${object == "CELL3G"}'> selected </c:if> >CELL3G</option>
                                        <option value="CELL4G" <c:if test='${object == "CELL4G"}'> selected </c:if> >CELL4G</option>
                                    </select> 
                                    <input type="hidden" id="objectCode" name="objectCode" value="${objectCode}" />
                                </div>
                            </div>
                        </div>  
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Tên đăng nhập</label>
                                    <input name="userName" value="${userName}"
                                           type="text" class="form-control" id="userName"
                                           placeholder="username">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Hành động</label>
                                    <select  name="action" id="action" class="form-control" value="${action}">
                                        <option value="">--Chọn hành động--</option>
                                        <option value="INSERT">Thêm</option>
                                        <option value="UPDATE">Sửa</option>
                                        <option value="DELETE">Xóa</option>
                                        <option value="OTHER">Khác</option> 
                                    </select> 
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Từ ngày</label>
                                    <input type="text" class="form-control date_form"  id="fromDate" name="fromDate"
                                           value="<fmt:formatDate pattern="dd/mm/yyyy" value="${tramQuyHoach.namKhoiTao}" />" />
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Đến ngày</label>
                                    <input type="text" class="form-control date_form"  id="toDate" name="toDate"
                                           value="<fmt:formatDate pattern="dd/mm/yyyy" value="${tramQuyHoach.namKhoiTao}" />" />
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="clearfix" ></div>
                    <!-- /.box-body -->
                    <div class="box-footer" align="center" >
                        <button type="button"  id="btnSearch" class="btn btn-primary" onclick="this.disabled = true;search();" ><spring:message code="admin.common.search" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách lịch sử tác động vào đối tượng</h3>
                </div>
                <!-- /.box-header -->
                <div id="tables" class="box-body table-responsive">
                    <div id="tablePagingId" style="overflow-y: scroll;">
                        <table id="table0" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên bảng</th>
                                    <th>Thuộc tính</th>
                                    <th>Giá trị cũ</th>
                                    <th>Giá trị mới</th>
                                    <th>User tác động</th>
                                    <th>Thời gian tác động</th>
                                    <th>Hành động</th>
                                    <th>Mã đối tượng bị tác động</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<!-- ADD Footer PAGE -->
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<%@include file="../../include/footer.jsp"%>


<script>

                            $(document).ready(function () {
                                $('#fromDate').datepicker({
                                    format: 'dd/mm/yyyy',
                                    todayHighlight: true,
                                    autoclose: true
                                });
                                $('#toDate').datepicker({
                                    format: 'dd/mm/yyyy',
                                    todayHighlight: true,
                                    autoclose: true
                                });
                                var table0 = $("#table0").DataTable({
                                    searchDelay: 1000,
                                    "pageLength": 10,
                                    "serverSide": true,
                                    ajax: {
                                        "url": '${pageContext.request.contextPath}/object/searchLogObject',
                                        "type": "GET"
                                    },
                                    //dinh nghia cac cloumn giong voi cloumn trả về trong database
                                    "columns": [
                                        {"name": "stt", "orderable": false, "searchable": false},
                                        {"name": "object_name"},
                                        {"name": "attribute_name"},
                                        {"name": "old_value"},
                                        {"name": "new_value"},
                                        {"name": "username"},
                                        {"name": "action_date"},
                                        {"name": "action_name"},
                                        {"name": "object_code"},
                                        {"name": "object_type", "visible": false}

                                    ],

                                    "zeroRecords": "Không có dữ liệu được tìm thấy",
                                    "language": {
                                        "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                                    }
                                });
                                 search();
                            });
                            function hdsd(media) {
                                window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
                            }
                            function  search() {
                                var table0 = $('#table0').DataTable();
                                table0.columns(5).search($('#userName').val());
                                table0.columns(6).search($('#fromDate').val()+'-'+$('#toDate').val());
                                table0.columns(7).search($('#action').val());
                                table0.columns(8).search($('#objectCode').val());
                                table0.columns(9).search($('#object').val());
                                
                                //vẽ bảng
                                table0.draw();
                                $('#btnSearch').attr("disabled", false);
                            }
</script>


