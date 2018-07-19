<%-- 
    Document   : list
    Created on : May 4, 2017, 9:07:21 AM
    Author     : Cyano
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/datatables/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.buttons.min.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/datatables/buttons.flash.min.js"></script>-->
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jszip.min.js"></script>-->
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/datatables/pdfmake.min.js"></script>-->
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/datatables/vfs_fonts.js"></script>-->
<section class="content-header">
    <h1>
        Danh sách liên kết OPC/DPC
    </h1>
    <!--    <ol class="breadcrumb">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/hlr/preAdd'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm HLR</span> 
            </button>
        </ol>-->
</section>

<section class="content">   
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"> <spring:message code="admin.common.search" /></h3>
                </div>
                <form:form method="GET" id="frm_search">
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Mã Node</label>
                                        <input type="text" class="form-control"  id="node1" name="node1" placeholder="Mã Node"/>
                                    </div>
                                </div>
                            </div>

                            <!--                            <div class="col-md-4">
                                                            <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                                                        </div>-->
                        </div>
                    </div>
                </form:form>

            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách liên kết OPC/DPC</h3>

                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <ol class="breadcrumb">
                        <form method="post" action="${pageContext.request.contextPath}/nelink/export">
                            <button type="submit" name="export" class="btn btn-danger">Excel</button>                        
                        </form>
                    </ol>
                    <div id="tablePagingId">

                        <table id="example1" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã Node1</th>                                            
                                    <th>OPC1</th>
                                    <th>Hệ số mã OPC1</th>
                                    <th>Mã Node2</th>
                                    <th>OPC2</th>
                                    <th>Hệ số mã OPC2</th>
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
<%@include file="../../include/footer.jsp"%>

<script>
    $(document).ready(function () {
        loadTable();
    });
    function loadTable() {
        var example1 = $("#example1").DataTable({
            "pageLength": 10,
            "serverSide": true,
            ajax: '${pageContext.request.contextPath}/nelink/list',
            "columns": [
                {"name": "stt", "orderable": false, "searchable": false},
                {"name": "node_code1"},
                {"name": "opc1", "orderable": false, "searchable": false},
                {"name": "numeral1", "orderable": false, "searchable": false},
                {"name": "node_code2"},
                {"name": "opc2", "orderable": false, "searchable": false},
                {"name": "numeral2", "orderable": false, "searchable": false}
            ],
            "zeroRecords": "Không có dữ liệu được tìm thấy",
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
        $('#node1').on('keyup', function () {
            example1.columns(4).search(this.value).draw();
        });
    }
    ;
</script>
