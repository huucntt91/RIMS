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
                    <h3 class="box-title">Quản lý cell bất thường</h3>
                </div>
                <form method="get">
                    <div class="box-body"> 
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">From Date</label>
                                    <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="yyyy-mm-dd" 
                                                value="${item.fromDate}" />" id="fromDate" name="fromDate"  />  
                                </div>
                                <script>
                                    $(document).ready(function() {
                                        $('#fromDate').datepicker({
                                            format: 'yyyy-mm-dd',
                                            todayHighlight: true,
                                            autoclose: true,
                                        });
                                    });
                                </script>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">To Date</label>
                                    <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="yyyy-mm-dd" 
                                                value="${item.toDate}" />" id="toDate" name="toDate"  />  
                                </div>
                                <script>
                                    $(document).ready(function() {
                                        $('#toDate').datepicker({
                                            format: 'yyyy-mm-dd',
                                            todayHighlight: true,
                                            autoclose: true,
                                        });
                                    });
                                </script>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Vendor</label>
                                    <input name="vendor" value="${vendor}"
                                           type="text" class="form-control" id="vendor"
                                           placeholder="Vendor">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">VNP Code</label>
                                    <input name="vnpCode" value="${vnpCode}"
                                           type="text" class="form-control" id="vnpCode"
                                           placeholder="VNP Code">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Code</label>
                                    <input name="code" value="${code}"
                                           type="text" class="form-control" id="code"
                                           placeholder="Code">
                                </div>
                            </div>
                        </div>                     
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Province Code</label>
                                    <input name="provinceCode" value="${provinceCode}"
                                           type="text" class="form-control" id="provinceCode"
                                           placeholder="Province Code">
                                </div>
                            </div>
                        </div> 
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">LAC</label>
                                    <input name="lac" value="${lac}"
                                           type="text" class="form-control" id="lac"
                                           placeholder="LAC">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">CI</label>
                                    <input name="ci" value="${ci}"
                                           type="text" class="form-control" id="ci"
                                           placeholder="CI">
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Name</label>
                                    <input name="name" value="${name}"
                                           type="text" class="form-control" id="name"
                                           placeholder="Name">
                                </div>
                            </div>
                        </div>                     
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Province Name</label>
                                    <input name="provinceName" value="${provinceName}"
                                           type="text" class="form-control" id="provinceName"
                                           placeholder="Province Name">
                                </div>
                            </div>
                        </div> 
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">BSC RNC Name</label>
                                    <input name="bscRncName" value="${bscRncName}"
                                           type="text" class="form-control" id="bscRncName"
                                           placeholder="BSC RNC Name">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Bts NodeB Name</label>
                                    <input name="btsNodeBName" value="${btsNodeBName}"
                                           type="text" class="form-control" id="btsNodeBName"
                                           placeholder="Bts NodeB Name">
                                </div>
                            </div>
                        </div>
                                           
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">OMC Name</label>
                                    <input name="omcName" value="${omcName}"
                                           type="text" class="form-control" id="omcName"
                                           placeholder="OMC Name">
                                </div>
                            </div>
                        </div>                   
                        <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">                                    
                                    <label class=" input-group-addon">Tech Type</label>
                                    <select  name="techType" id="techType" class="form-control">
                                        <option value="" <c:if test = '${fn:contains(techType,"")}'> selected="selected" </c:if> >Tất cả</option>
                                        <option value="2" <c:if test = '${fn:contains(techType,2)}'> selected="selected" </c:if> >2G</option>
                                        <option value="3" <c:if test = '${fn:contains(techType,3)}'> selected="selected" </c:if> >3G</option>                                                    
                                    </select>                                  
                                 </div>
                             </div>
                         </div>  
                         <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">                                    
                                    <label class=" input-group-addon">Abnormal System</label>
                                    <select multiple="multiple"  name="abnormalSystem" id="abnormalSystem" class="form-control">
                                        <option value="" <c:if test = '${fn:contains(abnormalSystem,"")}'> selected="selected" </c:if> >System</option>
                                        <option value="1" <c:if test = '${fn:contains(abnormalSystem,1)}'> selected="selected" </c:if> >RIMS</option>
                                        <option value="2" <c:if test = '${fn:contains(abnormalSystem,2)}'> selected="selected" </c:if> >PM</option>                                                    
                                    </select>                                  
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-3">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">                                    
                                    <label class=" input-group-addon">Cause</label>
                                    <select multiple="multiple"  name="cause" id="cause" class="form-control">
                                        <option value="" <c:if test = '${fn:contains(cause,"")}'> selected="selected" </c:if> >Cause</option>
                                        <option value="1" <c:if test = '${fn:contains(cause,1)}'> selected="selected" </c:if> >2G</option>
                                        <option value="2" <c:if test = '${fn:contains(cause,2)}'> selected="selected" </c:if> >3G</option>                                                    
                                    </select>                                  
                                 </div>
                             </div>
                         </div>           
                    </div>
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/access/reportPortDSLAMDevice?tinhTpId='+ $('#tinhTpId').val()"> <spring:message code="admin.common.export.excel" /></button>
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
                                    <th>VNP Code</th>
                                    <th>Code</th>
                                    <th>Name</th>
                                    <th>Bts_NodeB_Name</th>
                                    <th>BSC_RNC_Name</th>
                                    <th>OMC_Name</th>
                                    <th>Province Name</th>
                                    <th>Province Code</th>
                                    <th>Lac</th>
                                    <th>Ci</th>
                                    <th>Vendor</th>
                                    <th>Tech Type</th>
                                    <th>Abnormal System</th>
                                    <th>Start Date</th>
                                    <th>Expression Type</th>
                                    <th>Supplier PM</th>
                                    <th>Process Date</th>
                                    <th>NE ID</th>                                                                        
                                </tr>
                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="it" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + status.index}</td>
                                        <td>${it.vnpCode}</td>
                                        <td>${it.code}</td>
                                        <td>${it.name}</td>
                                        <td>${it.btsNodeBName}</td>
                                        <td>${it.bscRncName}</td>
                                        <td>${it.omcName}</td>
                                        <td>${it.provinceName}</td>
                                        <td>${it.provinceCode}</td>
                                        <td>${it.lac}</td>
                                        <td>${it.ci}</td>
                                        <td>${it.vendor}</td>
                                        <td>${it.techType}</td>
                                        <td>${it.abnormalSystem}</td>
                                        <td>${it.startDate}</td>
                                        <td>${it.expressionType}</td>
                                        <td>${it.supplierPm}</td>
                                        <td>${it.processDate}</td>
                                        <td>${it.neId}</td>
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

