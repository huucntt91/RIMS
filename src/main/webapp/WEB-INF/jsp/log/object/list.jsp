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
        <i class="fa fa-history"></i> Lịch sử tác động đối tượng <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_LICHSUTACDONG_RIMS.mp4');" >Hướng dẫn</button>
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
                                        <option value="0">CSHT</option>
                                        <option value="1">Phụ trợ</option>
                                        <option value="2">BTS</option>
                                        <option value="3">NODEB</option>
                                        <option value="8">ENODEB</option>
                                        <option value="5">CELL2G</option>
                                        <option value="6">CELL3G</option>
                                        <option value="7">CELL4G</option>
                                    </select> 
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
                                        <option value="insert">Thêm</option>
                                        <option value="update">Sửa</option>
                                        <option value="delete">Xóa</option>
                                        <option value="other">Khác</option> 
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
                        <button type="button"  id="btnSearch" class="btn btn-primary" onclick="this.disabled=true;search();" ><spring:message code="admin.common.search" /></button>
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
                            });
                            function search() {
                                $('#tables').empty();
                                var group = $("#group").val();
                                if (group == null || group == '') {
                                    alert("Bạn chưa chọn nhóm");
                                    $('#btnSearch').attr("disabled",false);
                                    return;
                                }
                                var object = $("#object").val();
                                var username = $("#userName").val();
                                var action = $("#action").val();
                                var fromDate = $("#fromDate").val();
                                var toDate = $("#toDate").val();

                                $.get("${pageContext.request.contextPath}/object/search?group=" + group + "&object=" + object
                                        + "&userName=" + username + "&action=" + action + "&fromDate=" + fromDate + "&toDate=" + toDate,
                                        function (data) {
                                            if (data.length > 0) {
                                                var ul = $('<ul></ul>').addClass('nav nav-pills');
                                                var divTabContent = $('<div></div>').addClass('tab-content');
                                                var count = 0;
                                                data.forEach(function (entry) {
                                                    //tao tab
                                                    var li = $('<li><a data-toggle="tab" href="#div_' + entry.id + '">' + entry.object_name + '</a></li>');

                                                    // tao content cho cac tab
                                                    var div = $('<div id="div_' + entry.id + '" class="tab-pane fade"></div>').addClass('tablePagingId');
                                                    if (count === 0) {
                                                        li.addClass('active');
                                                        div.addClass('in active');
                                                    }
                                                    ul.append(li);

                                                    //tao table
                                                    var table = $('<table></table>').addClass('table table-bordered table-hover');
                                                    table.attr('id', 'table_' + entry.id);
                                                    //tạo head cho bảng    
                                                    var thread = $('<thead></thead>');
                                                    var row = $('<tr></tr>');
                                                    var column_title = entry.column_title.split(',');
                                                    var column_name = entry.column_name.split(',');
                                                    if (column_title !== null && column_name !== null) {
                                                        var thStt = $('<th>STT</th>');
                                                        row.append(thStt);
                                                        for (var i = 0; i < column_title.length; i++) {
                                                            var th = $('<th>' + column_title[i].trim() + '</th>');
                                                            row.append(th);
                                                        }

                                                        thread.append(row);
                                                        table.append(thread);
                                                        //tạo thẻ div
                                                        var divPage = $('<div align="right" style="margin-right: 50px;"></div>');
                                                        table.append(divPage);
                                                        //toa body cho table
                                                        var tbody = $('<tbody></tbody>');
                                                        table.append(tbody);
                                                        var dataTable = entry.data;
                                                        if (dataTable !== null) {
                                                            for (var i = 0; i < dataTable.length; i++) {
                                                                var object = dataTable[i];
                                                                var tr = $('<tr></tr>');
                                                                var td = $('<td>' + (i + 1) + '</td>');
                                                                tr.append(td);
                                                                for (var j = 0; j < column_name.length; j++) {
                                                                    td = $('<td>' + object[column_name[j].toLowerCase().trim()] + '</td>');
                                                                    tr.append(td);
                                                                }
                                                                tbody.append(tr);
                                                            }
                                                        }
                                                        div.append(table);
                                                        divTabContent.append(div);
                                                        count++;
                                                    }

                                                });
                                                $('#tables').append(ul);
                                                $('#tables').append(divTabContent);

                                                //phan trang cho table
                                                data.forEach(function (entry) {
                                                    $('#table_' + entry.id).DataTable({
                                                        "language": {
                                                            "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                                                        }
                                                    });
                                                });
                                            }
                                            $('#btnSearch').attr("disabled",false);
                                        });
                            }
    
    function hdsd(media) {
        window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
    }                        
</script>


