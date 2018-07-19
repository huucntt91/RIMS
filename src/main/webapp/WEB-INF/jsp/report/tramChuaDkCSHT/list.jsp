<%-- 
    Document   : bcll
    Created on : Nov 15, 2016, 11:01:34 AM
    Author     : Cyano
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@include file="../../include/header.jsp"%>
<!-- multiselect -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Báo cáo trạm chưa đăng ký cơ sở hạ tầng</h3>
                </div>
                <form method="get">
                    <div class="box-body"> 
                        <div class="col-md-4">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">                                    
                                    <label class=" input-group-addon">Loại công nghệ</label>
                                    <select  name="techType" id="techType" class="form-control">
                                        <option value="2" <c:if test = '${fn:contains(techType,2)}'> selected="selected" </c:if> >2G</option>
                                        <option value="3" <c:if test = '${fn:contains(techType,3)}'> selected="selected" </c:if> >3G</option> 
                                        <option value="8" <c:if test = '${fn:contains(techType,8)}'> selected="selected" </c:if> >4G</option>
                                    </select>                                  
                                 </div>
                             </div>
                         </div>
                        <div class="col-md-4">
                                    <div class="form-group" style="padding: 0 15px">
                                        <div class="input-group">
                                            <label class=" input-group-addon">Khu vực</label>
                                            <select multiple="multiple" name="khuVucId" id="khuVucId" class="form-control"> 
                                            <c:forEach var="item" items="${khuvucList}">
                                                <option  value="${item.id}"  <c:if test = '${fn:contains(khuVucId,item.id)}'> selected="selected" </c:if> >${item.name}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                        </div>                 
                          
                                 
                    </div>
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/reportTramChuaDkCSHT/reportTramChuaDkCsht?techType='+ $('#techType').val() +'&khuVucId='+ $('#khuVucId').val() "> <spring:message code="admin.common.export.excel" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách</h3>
                </div>
                <div class="box-body table-responsive">
                    <div id="tablePagingId">
                        <table id="example1" class="table table-bordered table-hover">
                            <thead>
                                <tr>		
                                    <th>STT</th>
                                    <th>Mã trạm</th>
                                    <th>Tên trạm</th>
                                    <th>Loại trạm</th>
                                    <th>Tên tỉnh</th>                                                                                               
                                </tr>
                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="it" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + status.index}</td>
                                        <td>${it.maNode}</td>
                                        <td>${it.tenNode}</td>
                                        <td>${it.loaiTram}</td>
                                        <td>${it.provinceName}</td>                                        
                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>
                    </div>
                </div>
                <div class="box-footer">
                    <!-- ADD PAGE INFO -->
                    <%@include file="../../include/page_info.jsp"%>
                </div>
            </div>
        </div>
    </div>
</section>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<script>
    $(document).ready(function () {
        $('#khuVucId').multiselect(({
            maxHeight: 200,
            buttonWidth: '300px',
            enableFiltering: true,
            includeSelectAllOption: true
        }));
    });

</script>
