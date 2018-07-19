<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>

<section class="content">   


    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách Port</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div id="tablePagingId">

                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Slot/Port</th>     
                                    <th>Mô tả</th>
                                    <th>MTU</th>
                                    <th>Transceiver</th>
                                    <th>Công suất phát</th>
                                    <th>Công suất thu</th>
                                    <th>Ngưỡng thu min</th>
                                    <th>Ngưỡng thu max</th>
                                    <th>Dự phòng</th>
                                    <th>Serial</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="temp" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + (status.index)}</td>
                                        <td>${temp.portNo}</td>
                                        <td>${temp.note}</td>
                                        <td>${temp.mtu}</td>
                                        <td>${temp.transceiver}</td>
                                        <td>${temp.congsuatphat}</td>
                                        <td>${temp.congsuatthu}</td>
                                        <td>${temp.nguongthumin}</td>
                                        <td>${temp.nguongthumax}</td>
                                        <td></td>
                                        <td>${temp.serialNo}</td>
                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>   
                    </div>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <!-- ADD PAGE INFO -->
                    <%@include file="../../include/page_info.jsp"%>
                </div>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>


