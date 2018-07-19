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
                    <h3 class="box-title">Tổng hợp module quang theo Tỉnh/TP (VN2)</h3>
                </div>
                <form method="get">
                    <div class="box-body">
<!--                        <div class="col-md-4">
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

                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/reportBB/reportModuleQuangTheoTpVn2?tinhTpId=' + $('#tinhTpId').val()"> <spring:message code="admin.common.export.excel" /></button>
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
                                    <th rowspan="3">STT</th>
                                    <th rowspan="3">Tỉnh</th>
                                    <th colspan="7">Module 1Gb</th>
                                    <th colspan="7">Module 10Gb</th>
                                    <th colspan="7">Module STM16</th>
                                    <th colspan="7">Module STM64</th>
                                    <th colspan="7">Module CFP 100G</th>
                                    <th colspan="3">Khác</th>
                                    
                                </tr>
                                <tr>
                                    <th colspan="2">10km</th>
                                    <th colspan="2">40km</th>
                                    <th colspan="2">80km</th>
                                    <th rowspan="2">Tổng</th>
                                    <th colspan="2">10km</th>
                                    <th colspan="2">40km</th>
                                    <th colspan="2">80km</th>
                                    <th rowspan="2">Tổng</th>
                                    <th colspan="2">10km</th>
                                    <th colspan="2">40km</th>
                                    <th colspan="2">80km</th>
                                    <th rowspan="2">Tổng</th>
                                    <th colspan="2">10km</th>
                                    <th colspan="2">40km</th>
                                    <th colspan="2">80km</th>
                                    <th rowspan="2">Tổng</th>
                                    <th colspan="2">10km</th>
                                    <th colspan="2">40km</th>
                                    <th colspan="2">80km</th>
                                    <th rowspan="2">Tổng</th>
                                    <th rowspan="2">Lắp đặt</th>
                                    <th rowspan="2">Chưa dùng</th>
                                    <th rowspan="2">Tổng</th>
                                </tr>
                                <tr>
                                   <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                    <th>Lắp đặt</th>
                                    <th>Chưa dùng</th>
                                </tr>
                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="it" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + status.index}</td>
                                        <td>${it.tenTinhTP}</td>
                                        <td>${it.baseEx == 0 ? "" : it.baseEx  }</td>
                                        <td>${it.notUseBaseEx == 0 ? "" : it.notUseBaseEx}</td>
                                        <td>${it.baseLx == 0 ? "" :it.baseLx}</td>
                                        <td>${it.notUseBaseLx == 0 ? "" : it.notUseBaseLx}</td>
                                        <td>${it.baseZx == 0 ? "" :it.baseZx}</td>
                                        <td>${it.notUseBaseZx == 0 ? "" : it.notUseBaseZx}</td>
                                        <td>${it.sfp1G == 0 ? "" :it.sfp1G}</td>
                                        <td>${it.baseEr == 0 ? "" : it.baseEr}</td>
                                        <td>${it.notUseBaseEr == 0 ? "" : it.notUseBaseEr}</td>
                                        <td>${it.baseLr == 0 ? "" : it.baseLr}</td>
                                        <td>${it.notUseBaseLr == 0 ? "" : it.notUseBaseLr}</td>
                                        <td>${it.baseZr == 0 ? "" : it.baseZr}</td>
                                        <td>${it.notUseBaseZr == 0 ? "" : it.notUseBaseZr}</td>
                                        <td>${it.sfp10G == 0 ? "" : it.sfp10G}</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>

                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>

                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        
                                        <td>${it.other == 0 ? "" : it.other}</td>
                                        <td>${it.notUseOther == 0 ? "" : it.notUseOther}</td>
                                        <td>${it.otherTotal == 0 ? "" : it.otherTotal}</td>                                        

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