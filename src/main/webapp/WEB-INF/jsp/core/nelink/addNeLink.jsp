
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ADD HEADER PAGE -->
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
    </head>
    <body class="skin-blue">
        <div class="wrapper row-offcanvas row-offcanvas-left">

            <section class="content-header">
                <h1>Quản lý OPC DPC</h1>
            </section>                                       
            <section class="content">                        
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Thêm DPC cho Node</h3>
                            </div>
                            <form:form method="POST" id="frm_search" action="${pageContext.request.contextPath}/nelink/insertNeLink">
                                <input type="hidden" class="nodeCode" name="nodeId" value="${nodeId}"/>
                                <input type="hidden" name="neTypeId" value="${neTypeId}"/>
                                <input type="hidden" name="node_code1" value="${node_code1}"/>

                                <div class="box-body">                                
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <div class="input-group">                                    
                                                <label class=" input-group-addon">DPC</label>
                                                <select multiple="multiple" name="node2Id" id="node2Id" class="form-control"> >
                                                    <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                                                    <c:forEach var="nodeBO" items="${nodeList}">
                                                        <option 
                                                            <c:if test='${fn:contains(nodeId,nodeBO.id)}' >  selected="selected" </c:if>
                                                            value="${nodeBO.id}"  
                                                            >${nodeBO.code}</option>
                                                    </c:forEach>

                                                </select>                                  
                                            </div>

                                        </div>
                                    </div>

                                    <div class="col-md-4"> 
                                        <div class="form-group">

                                            <select name="loaiTruyenDanId" id="loaiTruyenDanId" class="form-control"> >
                                                <option value="">--- Chọn loại truyền dẫn ---</option>
                                                <c:forEach var="tbBO" items="${loaitruyendanList}">
                                                    <option  
                                                        value="${tbBO.id}"  <c:choose>
                                                            <c:when test="${tbBO.id == truyendanId}">
                                                                selected    
                                                            </c:when>    
                                                        </c:choose>

                                                        >${tbBO.name}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="submit" class="btn btn-primary">Thêm</button>
                                    </div>
                                    <div class="clearfix" ></div>
                                </div>                    
                            </form:form>
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

        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
    </body>
</html>
<script>
                        $(document).ready(function () {
                            var nodeCode = $(this).find('.nodeCode').val();
                            var neLink = '${pageContext.request.contextPath}/nelink/popup?node_code=' + nodeCode;
                            $('#node2Id').multiselect(({
                                maxHeight: 200,
                                enableFiltering: true,
                                includeSelectAllOption: true,
                                onChange: function (element, checked) {
                                }
                            }));
                        });
</script>