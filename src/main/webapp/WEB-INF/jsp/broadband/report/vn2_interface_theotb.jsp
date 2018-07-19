<%-- 
    Document   : bcll
    Created on : Nov 15, 2016, 11:01:34 AM
    Author     : Cyano
--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>
<!-- multiselect -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Tổng hợp interface theo thiết bị (VN2)</h3>
                </div>
                <form method="get">
                    <div class="box-body"> 
                        <div class="panel-body">
                            <!--                            <div class="col-md-6">
                                                            <div class="form-group">          
                                                                <div class="input-group">
                                                                    <label class=" input-group-addon" >Loại</label>                                    
                                                                    <select  name="typeId" id="typeId" class="form-control" required="true"> 
                                                                        <option  value="" > ---Chọn loại ---</option>
                                                                        <option  value="1" >Man-E</option>
                                                                        <option  value="2" >VN2</option>
                                                                    </select>
                                                                </div> 
                                                            </div>
                                                        </div> -->
                            <div class="col-md-4">
                                <div class="form-group">          
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                                        <select  name="tinhTpId" id="tinhTpId" class="form-control" required="true"> 
                                            <option  value="" > --- Chọn Tỉnh/TP ---</option>
                                            <c:forEach var="tinhBO" items="${tinhThanhPhoLst}">
                                                <option  value="${tinhBO.tinhTpId}" <c:if test = '${tinhBO.tinhTpId  == tinhTpId}'> selected="selected" </c:if> >${tinhBO.tenTinhTp}</option>
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
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/interface/interfaceTbVn2?tinhTpId=' + $('#tinhTpId').val()"> 
                            <spring:message code="admin.common.export.excel" /></button>
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
                                    <th >Thiết bị</th>
                                    <th >Ip</th>
                                    <th >LoopBack</th>
                                    <th >Ethernet</th>
                                    <th >AdLag</th>
                                    <th >ServiceInstance</th>
                                    <th >SubInterface</th>
                                    <th >PropVirtual</th>
                                    <th >L3IpVlan</th>
                                    <th >Mpls</th>
                                    <th >Tunnel</th>
                                    <th >MplsTunnel</th>
                                    <th >Khác</th>
                                </tr>

                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="it" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + status.index}</td>
                                        <td>${it.code}</td>
                                        <td>${it.ip}</td>  
                                        <td>${it.loopBack == 0 ? "" : it.loopBack}</td> 
                                        <td>${it.ethernet == 0 ? "" : it.ethernet}</td> 
                                        <td>${it.adLag == 0 ? "" : it.adLag}</td> 
                                        <td>${it.serviceInstance == 0 ? "" : it.serviceInstance}</td> 
                                        <td>${it.subInterface == 0 ? "" : it.subInterface}</td> 
                                        <td>${it.propVirtual == 0 ? "" : it.propVirtual}</td> 
                                        <td>${it.l3IpVlan == 0 ? "" : it.l3IpVlan}</td> 
                                        <td>${it.mpls == 0 ? "" : it.mpls}</td> 
                                        <td>${it.tunnel == 0 ? "" : it.tunnel}</td> 
                                        <td>${it.mplsTunnel == 0 ? "" : it.mplsTunnel}</td> 
                                        <td>${it.other == 0 ? "" : it.other}</td> 
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

</script>