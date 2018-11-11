
<%@page import="com.vnpt.media.rims.common.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@include file="../../include/header.jsp"%>
     
<!--         AdminLTE App 
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
         Bootstrap 
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        call ajax
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>-->

        <!-- multiselect -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
        
        

        <section class="content-header">
            <h1>Quản lý dự án nguồn</h1>
            <ol class="breadcrumb">
                <%--<c:if test="${fn:containsIgnoreCase(sessionScope.function, '/stationPlansExcelImport/init')}">--%>
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/duAnNguon/insertExels'" >

                        <span>  <img width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Thêm DA nguồn Excel</span> 
                    </button>
                <%--</c:if>--%>
                <%--<c:if test="${fn:containsIgnoreCase(sessionScope.function, '/stationPlansExcelImport/edit')}">--%>
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/duAnNguon/editExels'" >
                        <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Sửa DA nguồn Excel</span> 
                    </button>
                <%--</c:if>--%>
            </ol>
        </section>
        
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                        </div>
                        <form method="GET" action="search">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon">Khu vực</label>
                                            <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control" onchange="getTinhTp();"> 
                                                <c:forEach var="tinhBO" items="${khuvucList}">
                                                    <option  <c:if test='${fn:contains(khuvucId,tinhBO.id)}' >  selected="selected" </c:if>
                                                                                                                value="${tinhBO.id}"  
                                                                                                                >${tinhBO.name}</option>
                                                </c:forEach>
                                            </select>                                  
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon">Tỉnh TP</label>
                                        <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" > 
                                        </select>
                                        <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                                    </div>

                                </div>
                            </div>  
                                         
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>



            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Danh sách dự án nguồn</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <div id="tablePagingId">
                                <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/duAnNguon/exportExcel?khuvucId=' + $('#khuvucId').val() + '&tinhTpId=' + $('#tinhTpId').val()" >
                                    <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Xuất Excel</span> 
                                </button>
                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Khu vực</th>
                                            <th>Tỉnh</th>
                                            <th>Tên quy hoạch</th>
                                            <th>Mã quy hoạch</th>
                                            <th>Tên CSHT</th>                                            
                                            <th>Mã CSHT</th>
                                            <th>Địa chỉ</th>                                       
                                            <th>Longitude</th>
                                            <th>latitude</th>
                                            <th>Trạng thái CSHT</th>
                                            <th>Ngày hoàn thành CSHT thực tế</th>
                                            <th>Ngày dự kiến hoàn thành CSHT</th>
                                            <th>Xử lý</th>

                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="list" items="${lstDANguon}" varStatus="status">                                        
                                            <tr>
                                                <td>${startRow + (status.index)}</td>
                                                <td>${list.area}</td>
                                                <td>${list.ten_tinh_tp}</td>
                                                <td>${list.ten_quy_hoach}</td>
                                                <td>${list.ma_quy_hoach}</td>
                                                <td>${list.building_name}</td>
                                                <td>${list.ma_building}</td>
                                                <td>${list.dia_chi}</td>
                                                <td>${list.longitude}</td>
                                                <td>${list.latitude}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${list.status=='1'}">
                                                            CSHT có sẵn
                                                            <br />
                                                        </c:when>    
                                                        <c:when test="${list.status=='2'}">
                                                            CSHT mới
                                                            <br />
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td>${list.ngay_hoat_dong_csht}</td>
                                                <td>${list.ngay_hoan_thanh_csht}</td>                                                

                                                <td>
                                                    <a href="<%=request.getContextPath()%>/duAnNguon/delete/${list.du_an_nguon_id}"
                                                       title="Xóa">
                                                       <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                    </a>
                                                </td>    

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
<!--                    <div class="box-footer">
                     ADD PAGE INFO 
                        <%--<%@include file="../../include/page_info.jsp"%>--%>
                    </div>-->
                </div>
            </div>
        </section>         
                    
        <%@include file="../../include/footer.jsp"%>      
        
        <script type="text/javascript">


                $(document).ready(function () {

                    $('#khuvucId').multiselect(({
                        maxHeight: 200,
                        enableFiltering: true,
                        includeSelectAllOption: true,
                        onChange: function (element, checked) {
                        }
                    }));

                    $('#tinhTpId').multiselect(({
                        maxHeight: 200,
                        enableFiltering: true,
                        includeSelectAllOption: true,
                        onChange: function (element, checked) {
                        }
                    }));
                    getTinhTp();
                });
                
                //lay ra danh sach tinhtp theo khu vuc
                function getTinhTp() {
                    var id = $("#khuvucId").val();
                    var tinhTpIds = $("#tinhTpIds").val();
                    $.get("${pageContext.request.contextPath}/mane/getTinhTp?khuVucId=" + id, function (data) {
                        var html = '';
                        if (data.length > 0) {
                            data.forEach(function (data) {
                                var htmlx = '<option value="' + data.tinhTpId + '" ';
                                if (tinhTpIds.indexOf(data.tinhTpId) > -1) {
                                    htmlx += ' selected="selected" ';
                                }
                                htmlx += '>' + data.tenTinhTp + '</option>';
                                html += htmlx;
                            });
                        }
                        $('#tinhTpId').html(html);
                        $('#tinhTpId').multiselect('rebuild');
                    });
                }
        </script>
           

       
  