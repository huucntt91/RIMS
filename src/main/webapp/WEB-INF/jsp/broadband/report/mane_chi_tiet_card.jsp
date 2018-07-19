<%-- 
    Document   : bcll
    Created on : Nov 15, 2016, 11:01:34 AM
    Author     : Cyano
--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@include file="../../include/header.jsp"%>
<!-- multiselect -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Báo cáo chi tiết card theo Tỉnh/TP (Mane)</h3>
                </div>
                <form method="get">
                    <div class="box-body"> 
                        <div class="panel-body">
<!--                            <div class="col-md-4">
                                <div class="form-group">          
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Loại</label>                                    
                                        <select  name="typeId" id="typeId" class="form-control" required="true"> 
                                            <option  value="" > ---Chọn loại ---</option>
                                            <option  value="1,2,3" <c:if test = '${typeId  == "1,2,3"}'> selected="selected" </c:if> >Man-E</option>
                                            <option  value="7,8,9,10,11" <c:if test = '${typeId  == "7,8,9,10,11"}'> selected="selected" </c:if> >VN2</option>
                                            <option  value="4,5,6,13,14" <c:if test = '${typeId  == "4,5,6,13,14"}'> selected="selected" </c:if> >Access</option>
                                            </select>
                                        </div> 
                                    </div>
                                </div> -->
                                <div class="col-md-4">
                                    <div class="form-group">          
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                                            <select multiple="multiple"  name="tinhTpId" id="tinhTpId" class="form-control" required="true"> 
                                            <c:forEach var="tinhBO" items="${tinhThanhPhoLst}">
                                                <option  value="${tinhBO.tinhTpId}" <c:if test = '${fn:contains(tinhTpId,tinhBO.tinhTpId)}'> selected="selected" </c:if> >${tinhBO.tenTinhTp}</option>
                                            </c:forEach>
                                        </select>
                                    </div> 
                                </div>
                            </div>  
                        </div>
                    </div>
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/reportBB/reportChiTietCardTp?typeId=' + $('#typeId').val()
                                        + '&tinhTpId=' + $('#tinhTpId').val()"> <spring:message code="admin.common.export.excel" /></button>
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
                                    <th >STT</th>
                                    <th >Tên tỉnh</th>
                                    <th >Tên thiết bị</th>
                                    <th >Địa chỉ IP</th>
                                    <th >Loại</th>
                                    <th >Dòng</th>
                                    <th >Frame</th>
                                    <th >Slot</th>
                                    <th >Tên card</th>
                                    <th >Số serial</th>
                                </tr>

                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="it" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + status.index}</td>
                                        <td>${it.tenTinh}</td>
                                        <td>${it.code}</td>
                                        <td>${it.ip}</td>
                                        <td>${it.tvendorName}</td>
                                        <td>${it.dongTBiName}</td>
                                        <td>${it.frame}</td>
                                        <td>${it.slot}</td>
                                        <td>${it.cardName}</td>
                                        <td>${it.serialNumber}</td>
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

<%@include file="../../include/footer.jsp"%>

<script>
    $(document).ready(function () {
        $('#tinhTpId').multiselect(({
            maxHeight: 200,
            buttonWidth: '300px',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
    });
</script>