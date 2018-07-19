
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- ADD HEADER PAGE -->
<html>
    <head>
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

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
</head>
<body class="skin-blue">
    <div class="wrapper row-offcanvas row-offcanvas-left">
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <form:form method="GET" id="frm_search">
                            <div class="box-body">
                                <div class="col-md-6">
                                    <div class="form-group" style="padding: 0 15px">
                                        <input name="name" value="${name}"
                                               type="text" class="form-control" id="exampleInputEmail1"
                                               placeholder="Tên Tnode">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group" style="padding: 0 15px">
                                        <input name="code" value="${code}"
                                               type="text" class="form-control" id="exampleInputEmail1"
                                               placeholder="Mã Tnode">
                                    </div>
                                </div>
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
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Chọn 1 trong các TNode bên dưới</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã TNode</th> 
                                        <th>Tên TNode</th> 
                                        <th>Tnode cha</th>   
                                        <th>Dòng thiết bị</th> 
                                        <th>Trạng thái</th>
                                        <th>số slot</th>
                                        <th>Building</th>
                                        <th>Kiểu Tnode</th>
                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>  
                                    <input type="hidden" class="txtname" value="${item.name}" />
                                    <input type="hidden" class="txtid" value="${item.id}" />
                                    <td>${status.index+1}</td>
                                    <td>${item.code}</td>  
                                    <td>${item.name}</td>  
                                    <td>${item.chaName}</td>  
                                    <td>${item.dongTBiName}</td>  
                                    <td>${item.status}</td>  
                                    <td>${item.totalSlot}</td>  
                                    <td>${item.buildingName}</td>  
                                    <td>${item.typeName}</td>  
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


            </div>
        </section>
    </div><!-- ./wrapper -->

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


    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


    <!--call ajax-->
    <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
</body>
</html>


<script>
    $(document).ready(function() {
        $('.navbar-btn').click();
        var tinhId = $("#tinhTpId").val();
        if (tinhId != '')
        {
            getListHuyen(${tinhTpId});
            getListPhuongXa(${quanHuyenId});
        }
        $('#example1 tbody').on('click', 'tr', function() {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $('#example1 tbody').find('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });

        $('#frm_search').submit(function() {
            $("#neTypeId").prop('disabled', false);
            //Rest of code
        })
    });
</script>

