
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>

<section class="content-header">
    <h1>
        Lịch sự PS Core
    </h1>

</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                </div>

                <form:form method="GET" id="frm_search">
                    <div class="box-body">
                        <div class="col-md-3">
                            <div class="form-group">
                                <div class="input-group" >
                                    <label class=" input-group-addon" >Thời gian bắt đầu</label>
                                    <input type="text" class="form-control date_form"  name="startTime" id="startTime" required="true"
                                           value="${startTime}"/>
                                </div>
                            </div>    
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <div class="input-group" >
                                    <label class=" input-group-addon" >Thời gian kết thúc</label>
                                    <input type="text" class="form-control date_form"  name="endTime" id="endTime"
                                           value="${endTime}" />
                                </div>
                            </div>    
                        </div>



                        <div class="col-md-3">
                            <div class="form-group">
                                <select name="userId" id="userId" class="form-control selectpicker" data-live-search="true"> 

                                    <option  value="">--- Chọn Người SD ---</option>
                                    <c:forEach var="item" items="${userList}">
                                        <option  data-tokens="${item.username}"
                                                 value="${item.id}"  <c:choose>
                                                     <c:when test="${item.id == userId}">
                                                         selected    
                                                     </c:when>    
                                                 </c:choose>

                                                 >${item.username}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <select name="action" id="status" class="form-control"> >
                                    <option  value="">--- Chọn tác động ---</option>
                                    <option  <c:if test='${action == "Thêm mới"}'> selected </c:if> value="Thêm mới">Thêm mới</option>
                                    <option <c:if test='${action=="Cập nhật"}'> selected </c:if> value="Cập nhật">Cập nhật</option>
                                    <option  <c:if test='${action == "Xóa"}'> selected </c:if> value="Thêm mới">Xóa</option>
                                    </select>  
                                </div>
                            </div>

                            <!--                            <div class="col-md-4">
                                                            <div class="form-group">
                            -->                                                                <input name="code" value="${code}"  type="hidden"  />
                        <input name="type" value="${type}"  type="hidden"  />
                        <!--
                                            </div>-->
                        <!--                    </div>-->
                        <div class="clearfix"></div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <div class="row">

        <div class="col-md-12" >

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Lịch sử </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div class="tablePagingId">
                        <table id="tableBts"  class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>UserName</th>
                                    <th>Hành động</th>
                                    <th>Thời gian</th>
                                    <th>IP Client</th>
                                    <th>Loại NE</th>          

                                    <th>Mã node</th>
                                    <th>Đơn vị</th>
                                    <th>Mã Building</th>
                                    <th>Tên người quản lý</th>	
                                    <th>Số đt quản lý</th>
                                    <th>Tên</th>
                                    <th>Ngày hoạt động</th>
                                    <th>NE Status</th>
                                    <th>IP NE</th>
                                    <th>OPC</th>
                                    <th>Soft Version</th>
                                    <th>FlatForm</th>
                                    <th>Địa chỉ</th>
                                    <th>Tên card</th>
                                    <th>Seria number</th>
                                    <th>SL card</th>
                                    <th>Card version</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="item" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + 1 + (status.index)}</td>

                                        <td>${item.userName}</td>
                                        <td>${item.action}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.createTime}" /></td>
                                        <td>${item.ipClient}</td>
                                        <td>${item.tenNeType}</td>

                                        <td>${item.code}</td>
                                        <td>${item.donViName}</td>
                                        <td>${item.codeBuilding}</td>
                                        <td>${item.tenNgQLy}</td>
                                        <td>${item.SDTQLy}</td>
                                        <td>${item.name}</td>

                                        <td>${item.ngayHoatDong}</td>
                                        <td>${item.neStatus}</td>
                                        <td>${item.ipNe}</td>
                                        <td>${item.opc}</td>
                                        <td>${item.softVersion}</td>
                                        <td>${item.hwFlatForm}</td>
                                        <td>${item.address}</td>
                                        <td>${item.tenCard}</td>
                                        <td>${item.seria}</td>
                                        <td>${item.cardSL}</td>
                                        <td>${item.cardVersion}</td>

                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>   
                    </div>
                </div>

            </div>

        </div>

    </div>

</section>


<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>

<script src="${pageContext.request.contextPath}/resources/js/bootstrap-select.min.js" type="text/javascript"></script>

<script>
    $(document).ready(function () {
        //$('.navbar-btn').click();
        $('#startTime').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });

        $('#endTime').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true,

        });

        $(".table").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });


    });

</script>

