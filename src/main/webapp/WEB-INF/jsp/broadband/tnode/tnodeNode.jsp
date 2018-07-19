<%-- 
    Document   : form
    Created on : Aug 8, 2017, 10:48:07 AM
    Author     : Cyano
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../../include/header.jsp"%>

<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <!--<h3 class="box-title">Quản lý Mane</h3>-->
                </div>

                <div class="box-body" id="mydiv" >                        
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Thông tin node</h3>
                        </div>
                        <div class="panel-body">

                            <div class="col-md-4">
                                <div class="form-group">
                                    <div class="input-group">                                    
                                        <label class=" input-group-addon">Loại thiết bị</label>
                                        <select  name="typeId" id="typeId" class="form-control" onchange="getNodes();"> 
                                            <option value="11" >BSC/RNC</option>
                                            <option value="2" >BTS</option>
                                            <option value="3" >NODEB</option>
                                            <option value="8" >ENODEB</option>
                                            <option value="12" >MSC</option>
                                            <option value="13" >MSS</option>
                                            <option value="14" >MGW</option>
                                            <option value="22" >SGSN</option>
                                            <option value="23" >GGSN</option>
                                        </select>
                                        <input type="hidden"  id="tnode_id" value="${tnode_id}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">          
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                                        <select  name="tinhTpId" id="tinhTpId" class="form-control" onchange="getNodes();"> 
                                            <option  value="" > --- Chọn Tỉnh/TP ---</option>
                                            <c:forEach var="tinhBO" items="${tinhThanhPhoLst}">
                                                <option  value="${tinhBO.tinhTpId}" >${tinhBO.tenTinhTp}</option>
                                            </c:forEach>
                                        </select>
                                    </div> 
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">          
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Chọn node</label>                                    
                                        <select multiple="multiple" name="nodes" id="nodes" class="form-control"> 
                                        </select>
                                    </div> 
                                </div>
                            </div>
                                    <div class="col-md-4">
                                <div class="form-group">          
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Port In</label>                                    
                                          <input type="text" class="form-control" placeholder="Nhập port in" 
                                                   name="portIn" id="portIn" value="">
                                    </div> 
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">          
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Port Out</label>                                    
                                        <input type="text" class="form-control" placeholder="Nhập port out" 
                                                   name="portOut" id="portOut" value="">
                                    </div> 
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <div class="input-group">  
                                        <label class=" input-group-addon" for="exampleInputEmail1">Loại truyền dẫn</label>
                                        <select name="truyenDanId" id="truyenDanId" class="form-control" required >
                                            <option value="">--- Chọn loại truyền dẫn---</option>
                                            <c:forEach var="temp" items="${loaitruyendanList}">
                                                <option  
                                                    <c:if test='${model.loaiTruyenDanId == temp.id}' >  selected="selected" </c:if>
                                                    value="${temp.id}"
                                                    >${temp.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>        
                            </div>
                        </div>
                    </div>
                </div>    
                <!-- /.box-body -->
                <div class="clearfix" ></div>
                <div class="box-footer" align="center">
                    <button type="button"  class="btn btn-success" onclick="addTnodeNode();" >Thêm liên kết</button>
                    <button type="button"  class="btn btn-success" id="searchBtn" onclick="this.disabled = true;search();">Tìm kiếm</button>
                </div>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách thiết bị</h3>

                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive" id="divTable">

                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <!-- ADD PAGE INFO -->
                </div>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>






<script>
                        $(document).ready(function () {
                            $('#nodes').multiselect(({
                                maxHeight: 200,
                                enableFiltering: true,
                                includeSelectAllOption: true,
                                onChange: function (element, checked) {
                                }
                            }));

                            getNodes();
                            search();
                        });
                        function getNodes() {
                            var typeId = $("#typeId").val();
                            var tinhTpId = $("#tinhTpId").val();
                            if (tinhTpId == null)
                                return;
                            $.get("${pageContext.request.contextPath}/mane/findNodes?typeId=" + typeId + "&tinhTpId=" + tinhTpId, function (data) {
                                var html = '';
                                if (data.length > 0) {
                                    data.forEach(function (data) {
                                        var htmlx = '<option value="' + data.id + '" ';
                                        htmlx += '>' + data.code + '</option>';
                                        html += htmlx;
                                    });
                                }
                                $('#nodes').html(html);
                                $('#nodes').multiselect('rebuild');
                            });
                        }

                        function search() {
                            var typeId = $("#typeId").val();
                            var tinhTpId = $("#tinhTpId").val();
                            var tnodeId = $("#tnode_id").val();
                            $("#divTable").empty();
                            $.get("${pageContext.request.contextPath}/mane/searchTnodeNode?tnodeId=" + tnodeId + "&typeId=" + typeId + "&tinhTpId=" + tinhTpId, function (data) {
                                var table = $('<table id="example1"></table>').addClass('table table-bordered table-hover');
                                //tạo head cho bảng    
                                var thread = $('<thead></thead>');
                                var row = $('<tr></tr>');
                                row.append($('<th>STT</th>')).append($('<th>Mạng broadband</th>'))
                                        .append($('<th>Mạng radio</th>')).append($('<th>Port In</th>')).append($('<th>Port Out</th>')).append($('<th>Truyền dẫn</th>')).append($('<th>Chức năng</th>'));
                                thread.append(row);
                                table.append(thread);
                                //tạo thẻ div
                                var divPage = $('<div align="right" style="margin-right: 50px;"></div>');
                                table.append(divPage);
                                if (data.length > 0) {
                                    var count = 1;
                                    data.forEach(function (data) {
                                        var a = '<a title="Xóa" onclick="deleteTnodeNode(' + data.id + ')">\n\
                                            <img src="<%=request.getContextPath()%>/image/icon/delete.png"> </a>';
                                        var tr = $('<tr>').append($('<td>' + count + ' </td>')).append($('<td>' + data.tnode_code + ' </td>')).append($('<td>' + data.ma_node + ' </td>')).append($('<td>' + data.portIn + ' </td>')).append($('<td>' + data.portOut + ' </td>')).append($('<td>' + data.tenTruyenDan + ' </td>')).
                                                append($('<td>').html(a));
//                                        $("#example1").find('tbody').append(tr);
                                        //toa body cho table
                                        var tbody = $('<tbody></tbody>');
                                        table.append(tbody);
                                        tbody.append(tr);
                                        count++;
                                    });
                                }
                                $('#divTable').append(table);
                                $('#searchBtn').attr("disabled", false);
                                $("#example1").DataTable({
                                    "language": {
                                        "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
                                    }
                                });
                            });


                        }

                        function addTnodeNode() {
                            var nodes = $("#nodes").val();
                            var tnodeId = $("#tnode_id").val();
                            if (nodes == null || nodes == '' || tnodeId == null) {
                                alert('Bạn chưa chọn node');
                                return;
                            }
                            var portIn = $("#portIn").val();
                            var portOut = $("#portOut").val();
                            var truyenDanId = $("#truyenDanId").val();
                           
                            $.get("${pageContext.request.contextPath}/mane/addTnodeNode?tnodeId=" + tnodeId + "&nodes=" + nodes + "&portIn=" + portIn + "&portOut=" + portOut + "&truyenDanId=" + truyenDanId , function (data) {
                                if (data == 1) {
                                    alert('Thêm mới thành công');
                                    search();
                                } else
                                    alert('Thêm mới không thành công');
                            });

                        }

                        function deleteTnodeNode(id) {
                            if (id == '')
                                return;
                            if (confirm("Bạn có muốn xóa bản ghi không?")) {
                                $.get("${pageContext.request.contextPath}/mane/deleteTnodeNode?id=" + id, function (data) {
                                    if (data == 1)
                                    {
                                        alert('Xóa thành công');
                                        search();
                                    } else
                                        alert('Xóa không thành công');
                                });
                            }
                        }

</script>

