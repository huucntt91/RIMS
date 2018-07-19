
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<section class="content-header">
    <h1>
        Báo cáo nhanh Config
    </h1>            
</section>
<section class="content">   
    <c:if test="${list!=null}">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">                    
                    <div class="box-body">                        
                        <form:form method="GET" action="export">
                            <button type="submit" name="export" class="btn btn-danger">Export</button>                        
                        </form:form>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">                        
                        <div id="tablePagingId">
                            <table id="example1" class="table table-bordered table-hover"
                                   style=" overflow-x:scroll !important;;overflow-y:hidden !important;">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tỉnh/TP</th>
                                        <th>Số BTS</th>
                                        <th>Số BTS cấu hình 2/2/2</th>
                                        <th>Số BTS cấu hình 3/3/3</th>
                                        <th>Số BTS cấu hình 4/4/4</th>
                                        <th>Số BTS có cấu hình khác</th>
                                        <th>Số NodeB</th>
                                        <th>Số NodeB cấu hình 1/1/1 (U900 Only)</th>
                                        <th>Số NodeB cấu hình 1/1/1 (U2100 Only)</th>
                                        <th>Số NodeB cấu hình 2/2/2 (U2100 Only)</th>
                                        <th>Số NodeB cấu hình 3/3/3 (U2100 Only)</th>
                                        <th>Số NodeB cấu hình 1/1/1 & 1/1/1 (U900&U2100)</th>
                                        <th>Số NodeB cấu hình 1/1/1 & 2/2/2 (U900&U2100)</th>
                                        <th>Số NodeB cấu hình 1/1/1 & 3/3/3 (U900&U2100)</th>
                                        <th>Số NodeB có cấu hình khác</th>                                                                      
                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>
                                            <td>${startRow + (status.index + 1)}</td>
                                            <td>${item.tenTinh}</td>
                                            <td>${item.soBts}</td>
                                            <td>${item.soBts222}</td>
                                            <td>${item.soBts333}</td>
                                            <td>${item.soBts444}</td>
                                            <td>${item.soBtsKhac}</td>
                                            <td>${item.soNodeB}</td>
                                            <td>${item.soNodeB111U900}</td>
                                            <td>${item.soNodeB111U2100}</td>
                                            <td>${item.soNodeB222}</td>
                                            <td>${item.soNodeB333}</td>
                                            <td>${item.soNodeB111111}</td>
                                            <td>${item.soNodeB111222}</td>
                                            <td>${item.soNodeB111333}</td>
                                            <td>${item.soNodeBKhac}</td>                                                                                                                       
                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>

                    </div>
                   
                </div>
                <!-- /.box -->
            </div>
        </div>        
    </c:if>
</section>
<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>

