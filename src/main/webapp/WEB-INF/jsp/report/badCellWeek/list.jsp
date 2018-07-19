<%-- 
    Document   : bcll
    Created on : Nov 15, 2016, 11:01:34 AM
    Author     : trunglk
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
                    <h3 class="box-title">Báo cáo bad cell theo tuần</h3>
                </div>
                <form method="get">
                    <div class="box-body">
                        <div class="col-md-4">
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon">Khu vực</label>
                                            <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control" onchange="getTinhTp();"
                                                    > 
                                            <c:forEach var="tinhBO" items="${khuvucList}">
                                                <option  <c:if test='${fn:contains(khuvucId,tinhBO.id)}' >  selected="selected" </c:if>
                                                                                                            value="${tinhBO.id}"  
                                                                                                            >${tinhBO.name}</option>
                                            </c:forEach>


                                        </select>                                  
                                    </div>
                                </div>
                        </div>
                        
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon">Tỉnh TP</label>
                                    <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen();"  > 
                                    </select>
                                    <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                                </div>

                            </div>
                        </div>
                        <div class="clearfix" ></div>         
                        <div class="col-md-4">
                                    <div class="form-group" style="">
                                        
                                            <div class="input-group">
                                                <label class=" input-group-addon">Tuần</label>
                                                <select  name="fromWeek" id="fromWeek" class="form-control">
                                                            <!--onchange="getTinhTp();" >--> 
                                                    <c:forEach var="fWeek" begin = "1" end = "53">
                                                        <option  value="${fWeek}"  <c:if test = '${fn:contains(fromWeek,fWeek)}'> selected="selected" </c:if> >${fWeek}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>                                       
                        </div>
                        
                        <div class="col-md-4">
                                    <div class="form-group" style="">
                                            <div class="input-group">
                                                <label class=" input-group-addon">From Year</label>
                                                <select  name="fromYear" id="fromYear" class="form-control">
                                                            <!--onchange="getTinhTp();" >--> 
                                                    <c:forEach var="fYear" begin = "2007" end = "2027">
                                                        <option  value="${fYear}"  <c:if test = '${fn:contains(fromYear,fYear)}'> selected="selected" </c:if> >${fYear}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        
                                </div>
                        </div>

                    <div id="divDate" style="display: none">
                        <div class="col-md-4">
                                <div class="form-group">          
                                    <div class="input-group">
                                        <input type="hidden" value="${fromDateWeek}" id="fromDateWeek"/>
                                    </div> 
                                </div>
                            </div>
                    </div>
                    
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <!--<button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>-->
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/reportBadCellWeek/reportBadCellWeek?tinhTpId=' + $('#tinhTpId').val()
                                                                                                                                                                            + '&khuvucId=' + $('#khuvucId').val()
                                                                                                                                                                            + '&fromWeek=' + $('#fromWeek').val()
                                                                                                                                                                            + '&fromYear=' + $('#fromYear').val()"> <spring:message code="admin.common.export.excel" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</section>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<script>
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
