
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>RIMS</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- bootstrap 3.0.2 -->
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css">

        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
        <!-- -->

        <!-- jQuery 2.0.2 -->
       <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

        <!-- DataTables -->
        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
        <!-- SlimScroll -->
        <script src="${pageContext.request.contextPath}/resources/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/fastclick/fastclick.js"></script>
        <!-- multiselect -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/highcharts/highcharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/highcharts/exporting.js" type="text/javascript"></script>

        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

        <link href="${pageContext.request.contextPath}/resources/css/datatables/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.buttons.min.js"></script>
    </head>
    <body class="skin-blue">
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <section class="content">   
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Danh sách liên kết OPC/DPC</h3>

                                <button class="btn btn-info btn-sm" style="float: right; margin-top: 10px"  onclick="location.href = '<%=request.getContextPath()%>/nelink/preAdd/${nodeId}/${neTypeId}/${node_code1}'" >
                                    <span><i class="fa fa-fw fa-plus"></i>Thêm OPC DPC ${nodeId}</span> 
                                </button>

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
        </div>
    </body>


</html>

<script>

    $(document).ready(function () {

        loadTable('${node_code1}');
    });
    function loadTable(node_code) {
        var example1 = $("#example1").DataTable({
            "pageLength": 10,
            "serverSide": true,
            ajax: '${pageContext.request.contextPath}/nelink/list?node_code=' + node_code,
            "columns": [
                {"name": "stt", "orderable": false, "searchable": false},
                {"name": "node_code1"},
                {"name": "opc1", "orderable": false, "searchable": false},
                {"name": "numeral1", "orderable": false, "searchable": false},
                {"name": "node_code2"},
                {"name": "opc2", "orderable": false, "searchable": false},
                {"name": "numeral2", "orderable": false, "searchable": false},
                {"name": "function", "orderable": false, "searchable": false},
                {"name": "ne_link_id", "visible": false}
            ],
            "createdRow": function (row, data, index) {
                var td_7_content = '<a title="Xóa" href="<%=request.getContextPath()%>/nelink/delete/' + data[8] + '?node_code=' + node_code + '" onclick="return confirm(\'Bạn có muốn thực hiện xoá không?\');">\n\
                        <img src="<%=request.getContextPath()%>/image/icon/delete.png"> </a>';
                $('td', row).eq(7).html(td_7_content);
            },
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

