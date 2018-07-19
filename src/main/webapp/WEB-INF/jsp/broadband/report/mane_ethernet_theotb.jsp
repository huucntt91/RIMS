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
                    <h3 class="box-title">Tổng hợp Ethernet theo thiết bị (MANE)</h3>
                </div>
                <form method="get" >
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
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/mane/ethernetTb?typeId=' + $('#typeId').val()
                                        + '&tinhTpId=' + $('#tinhTpId').val()"> <spring:message code="admin.common.export.excel" /></button>
                    </div>
                </form>
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
                                        <th rowspan="2">STT</th>
                                        <th rowspan="2">Tên tỉnh</th>
                                        <th rowspan="2">Tên thiết bị</th>
                                        <th rowspan="2">Ip</th>
                                        <th colspan="3">Cổng 1G</th>
                                        <th colspan="3">Module SFP 1G</th>
                                        <th colspan="3">Cổng 10G</th>
                                        <th colspan="3">Module XFP/SFPP 10G</th>

                                    </tr>
                                    <tr>
                                        <th >Lắp đặt(4)</th>
                                        <th >Sử dụng(5)</th>
                                        <th >Chưa dùng
                                            (6)=(4)-(5)</th>
                                        <th >Lắp đặt(7)</th>
                                        <th >Sử dụng(8)</th>
                                        <th >Chưa dùng(9)=(7)-(8)</th>
                                        <th >Lắp đặt(10)</th>
                                        <th >Sử dụng(11)</th>
                                        <th >Chưa dùng(12)=(10)-(11)</th>
                                        <th >Lắp đặt(13)</th>
                                        <th >Sử dụng(14)</th>
                                        <th >Chưa dùng(15)=(13)-(14)</th>
                                    </tr>
                                </thead>
                                <div align="right" style="margin-right: 50px;">${paging}</div>
                                <tbody>                                       
                                    <c:forEach var="it" items="${list}" varStatus="status">                                        
                                        <tr>
                                            <td>${startRow + status.index}</td>
                                            <td>${it.tenTinhTp}</td>
                                            <td>${it.code}</td>
                                            <td>${it.ip}</td>
                                            <td>${it.port_1g == 0 ? "" : it.port_1g  }</td>
                                            <td>${it.use1G == 0 ? "" : it.use1G  }</td>
                                            <td>${it.notUse1G == 0 ? "" : it.notUse1G  }</td>                                      
                                            <td>${it.sfp1g == 0 ? "" : it.sfp1g  }</td>
                                            <td>${it.useSfp1G == 0 ? "" : it.useSfp1G  }</td>  
                                            <td>${it.notUseSfp1G == 0 ? "" : it.notUseSfp1G  }</td> 
                                            <td>${it.port_10g == 0 ? "" : it.port_10g  }</td>
                                            <td>${it.use10G == 0 ? "" : it.use10G  }</td>
                                            <td>${it.notUse10G == 0 ? "" : it.notUse10G  }</td>  
                                            <td>${it.sfp10g == 0 ? "" : it.sfp10g  }</td>
                                            <td>${it.useSfp10G == 0 ? "" : it.useSfp10G  }</td>  
                                            <td>${it.notUseSfp10G == 0 ? "" : it.notUseSfp10G  }</td> 

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