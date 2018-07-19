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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../../include/header.jsp"%>
<!-- multiselect -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<!--datepicker -->
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Báo cáo Event</h3>
                </div>
                <form method="get">
                    <div class="box-body">

                        <div class="col-md-4">
                            <div class="form-group" >
                                <div class="input-group">
                                    <label class=" input-group-addon">Đối tượng</label>
                                    <select  name="objectTypeId" id="objectTypeId" class="form-control" required="true" onchange="actionObject();" > 
                                        <option value="">--- Chọn đối tượng ---</option>
                                        <c:forEach var="item" items="${objects}">
                                            <option  value="${item.objectTypeId}"  
                                                     <c:if test = '${objectTypeId == item.objectTypeId}'> selected="selected" </c:if> >${item.objectTypeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div id="khuVucDiv" class="col-md-4" style="display:none">
                            <div class="form-group" >
                                <div class="input-group">
                                    <label class=" input-group-addon">Khu vực</label>
                                    <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control"> 
                                        <!--<option value="">--- Chọn Tỉnh/Thành Phố ---</option>-->
                                        <c:forEach var="item" items="${khuvucList}">
                                            <option  value="${item.id}"  <c:if test = '${fn:contains(khuvucId,item.id)}'> selected="selected" </c:if> >${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div id="tinhTpDiv" class="col-md-4" style="display:none">
                            <div class="form-group">          
                                <div class="input-group">
                                    <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                                    <select multiple="multiple"  name="tinhTpId" id="tinhTpId" class="form-control"> 
                                        <c:forEach var="tinhBO" items="${tinhThanhPhoLst}">
                                            <option  value="${tinhBO.maTinhTp}" <c:if test = '${fn:contains(tinhTpId,tinhBO.maTinhTp)}'> selected="selected" </c:if> >${tinhBO.tenTinhTp}</option>
                                        </c:forEach>
                                    </select>
                                </div> 
                            </div>
                        </div> 
                        <div class="col-md-4">
                            <div class="form-group" >          
                                <div class="input-group">
                                    <label class=" input-group-addon" >Event</label>                                    
                                    <select multiple="multiple"  name="eventDefineId" id="eventDefineId" class="form-control"> 
                                    </select>
                                </div> 
                            </div>
                        </div>
                        <div class="clearfix" ></div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon" >Thời gian bắt đầu</label>
                                    <input type="text" class="form-control date_form"  id="startTime" name="startTime" placeholder="Thời gian bắt đầu" 
                                           value="<fmt:formatDate pattern="dd/MM/yyyy" value="${startTime}" />"  required="true"/>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon" >Thời gian kết thúc</label>
                                    <input type="text" class="form-control date_form"  id="endTime" name="endTime" placeholder="Thời gian bắt đầu" 
                                           value="<fmt:formatDate pattern="dd/MM/yyyy" value="${endTime}" />"  required="true"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/event/eventReport?tinhTpId=' + $('#tinhTpId').val()
                                + '&khuvucId=' + $('#khuvucId').val() + '&eventDefineId=' + $('#eventDefineId').val() + '&objectTypeId=' + $('#objectTypeId').val() + '&startTime=' + $('#startTime').val()+ '&endTime=' + $('#endTime').val()"> <spring:message code="admin.common.export.excel" /></button>
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
                                    <th >Đối tượng</th>
                                    <th >Event name</th>
                                    <th >Mô tả event</th>
                                    <th >Giá trị</th>
                                    <th >Thời gian bắt đầu</th>
                                    <th >Thời gian kết thúc</th>
                                    <th >Trạng thái</th>
                                    <th >Handling Status</th>
                                </tr>
                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="it" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + status.index}</td>
                                        <td>${it.objectCode}</td>
                                        <td>${it.eventName}</td>
                                        <td>${it.eventDescription}</td>
                                        <td>${it.eventValue}</td>
                                        <td>${it.startTime}</td>
                                        <td>${it.endTime}</td>
                                        <td>${it.status}</td>
                                        <td>${it.handlingStatus}</td>
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

        $('#khuvucId').multiselect(({
            maxHeight: 200,
            buttonWidth: '300px',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
        $('#tinhTpId').multiselect(({
            maxHeight: 200,
            buttonWidth: '300px',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
        $('#eventDefineId').multiselect(({
            maxHeight: 200,
            buttonWidth: '300px',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));

        $('#endTime').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });
        $('#startTime').datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true
        });
        actionObject();
    });

    //lay ra danh sach tinhtp theo khu vuc

    function actionObject() {
        var objectTypeId = $("#objectTypeId").val();
        if (objectTypeId == 1) {
            $("#tinhTpDiv").hide();
            $("#khuVucDiv").show();
        } else if (objectTypeId == 2) {
            $("#tinhTpDiv").show();
            $("#khuVucDiv").hide();
        } else {
            $("#tinhTpDiv").hide();
            $("#khuVucDiv").hide();
        }
        //load du lieu event
        var eventDefineId = '${eventDefineId}';
        $.get("${pageContext.request.contextPath}/event/getEvent?objecTypeId=" + objectTypeId, function (data) {
            var html = '';
            if (data.length > 0) {
                data.forEach(function (data) {
                    var htmlx = '<option value="' + data.eventDefineId + '" ';
                    if (eventDefineId.indexOf(data.eventDefineId) > -1) {
                        htmlx += ' selected="selected" ';
                    }
                    htmlx += '>' + data.eventDefineName + '</option>';
                    html += htmlx;
                });
            }
            $('#eventDefineId').html(html);
            $('#eventDefineId').multiselect('rebuild');
        });

    }

</script>