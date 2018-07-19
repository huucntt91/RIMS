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


<%@include file="../../include/header.jsp"%>
<!-- multiselect -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Thống kê chủng loại thiết bị L2SW</h3>
                </div>
                <form method="get">
                    <div class="box-body"> 
                        <div class="panel-body">
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
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/access/reportThL2sw?tinhTpId='+ $('#tinhTpId').val()"> <spring:message code="admin.common.export.excel" /></button>
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
                                    <th>Đơn vị</th>
                                    <th>Tổng</th>
                                    <th>2226_SFP</th>
                                    <th>AT_FS750</th>
                                    <th>BATM_T5C</th>
                                    <th>C2960</th>
                                    <th>C3560</th>
                                    <th>CE500</th>
                                    <th>ES_2226C</th>
                                    <th>ISCOM2126</th>
                                    <th>LINKSYS</th>
                                    <th>LS62XX</th>
                                    <th>ME340X</th>
                                    <th>MES3500_24F</th>
                                    <th>O62XX</th>
                                    <th>O6424</th>
                                    <th>O6450</th>
                                    <th>O64XX</th>
                                    <th>RAISECOM2828</th>
                                    <th>RT2126</th>
                                    <th>S3328</th>
                                    <th>S4100</th>
                                    <th>S53XX</th>
                                    <th>SF300</th>
                                    <th>TERRABIT</th>
                                    <th>V6328</th>
                                    <th>VFT22XX</th>
                                    <th>ZTE3928</th>
                                    
                                </tr>
                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="it" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + status.index}</td>
                                        <td>${it.ten_tinh_tp}</td>
                                        <td>${it.tong_tb}</td>
                                        <td>${it.getS2226_SFP()}</td>
                                        <td>${it.getAT_FS750()}</td>
                                        <td>${it.getBATM_T5C()}</td>
                                        <td>${it.getC2960()}</td>
                                        <td>${it.getC3560()}</td>
                                        <td>${it.getCE500()}</td>
                                        <td>${it.getES_2226C()}</td>
                                        <td>${it.getISCOM2126()}</td>
                                        <td>${it.getLINKSYS()}</td>
                                        <td>${it.getLS62XX()}</td>
                                        <td>${it.getME340X()}</td>
                                        <td>${it.getMES3500_24F()}</td>
                                        <td>${it.getO62XX()}</td>
                                        <td>${it.getO6424()}</td>
                                        <td>${it.getO6450()}</td>
                                        <td>${it.getO64XX()}</td>
                                        <td>${it.getRAISECOM2828()}</td>
                                        <td>${it.getRT2126()}</td>
                                        <td>${it.getS3328()}</td>
                                        <td>${it.getS4100()}</td>
                                        <td>${it.getS53XX()}</td>
                                        <td>${it.getSF300()}</td>
                                        <td>${it.getTERRABIT()}</td>
                                        <td>${it.getV6328()}</td>
                                        <td>${it.getVFT22XX()}</td>
                                        <td>${it.getZTE3928()   }</td>
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