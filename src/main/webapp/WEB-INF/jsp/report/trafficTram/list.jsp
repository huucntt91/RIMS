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
                    <h3 class="box-title"><i class="fa fa-file-word-o"></i> Báo cáo traffic mức trạm</h3>
                </div>
                <form method="get">
                    <div class="box-body">
                        <div class="col-md-4">
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon" style="min-width: 150px">Khu vực</label>
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
                                    <label class=" input-group-addon" style="min-width: 150px">Tỉnh TP</label>
                                    <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" > 
                                    </select>
                                    <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                                </div>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">                                    
                                    <label class=" input-group-addon" style="min-width: 150px">Loại công nghệ</label>
                                    <select  name="techType" id="techType" class="form-control">                                        
                                        <option value="2" <c:if test = '${fn:contains(techType,2)}'> selected="selected" </c:if> >2G</option>
                                        <option value="3" <c:if test = '${fn:contains(techType,3)}'> selected="selected" </c:if> >3G</option>  
                                        <option value="4" <c:if test = '${fn:contains(techType,4)}'> selected="selected" </c:if> >4G</option>
                                    </select>                                  
                                 </div>
                             </div>
                         </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">                                    
                                    <label class=" input-group-addon" style="min-width: 150px">Loại thời gian</label>
                                    <select  name="timeType" id="timeType" class="form-control" onchange="show1();">                                        
                                        <option value="1" <c:if test = '${fn:contains(timeType,1)}'> selected="selected" </c:if> >Ngày</option>
                                        <option value="2" <c:if test = '${fn:contains(timeType,2)}'> selected="selected" </c:if> >Tuần</option>  
                                        <option value="3" <c:if test = '${fn:contains(timeType,3)}'> selected="selected" </c:if> >Tháng</option>
                                    </select>                                  
                                 </div>
                             </div>
                         </div>              
                        <div class="col-md-4">
                            <div class="form-group" style="">
                                <div id="divFromDate" style="display: none">
                                    <div class="input-group">       
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">From Date</label>
                                        <input  type="text" class="form-control date_form" value="${fromDate}" id="fromDate" name="fromDate"  />  
                                    </div>
                                    <script>
                                        $(document).ready(function() {
                                            $('#fromDate').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true,
                                            });
                                        });
                                    </script>
                                </div>                              
                            </div>
                        </div>
   
                        <div class="col-md-4">
                            <div class="form-group" style="">
                                <div id="divToDate" style="display: none">
                                    <div class="input-group">       
                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">To Date</label>
                                        <input  type="text" class="form-control date_form" value="${toDate}" id="toDate" name="toDate"  />  
                                    </div>
                                    <script>
                                        $(document).ready(function() {
                                            $('#toDate').datepicker({
                                                format: 'dd/mm/yyyy',
                                                todayHighlight: true,
                                                autoclose: true,
                                            });
                                        });
                                    </script>
                                </div>                               
                            </div>
                        </div>
                        <div class="clearfix" ></div>  
                        <div class="col-md-4">
                                    <div class="form-group" style="">
                                        <div id="divFromYear" style="display: none">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="min-width: 150px">From Year</label>
                                                <select  name="fromYear" id="fromYear" class="form-control">
                                                            <!--onchange="getTinhTp();" >--> 
                                                    <c:forEach var="fYear" begin = "2007" end = "2027">
                                                        <option  value="${fYear}"  <c:if test = '${fn:contains(fromYear,fYear)}'> selected="selected" </c:if> >${fYear}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                </div>
                        </div>          
                        <div id="divFromWeek" style="display: none">
                        <div class="col-md-4">
                                    <div class="form-group" style="">
                                        
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="min-width: 150px">From Week</label>
                                                <select  name="fromWeek" id="fromWeek" class="form-control">
                                                            <!--onchange="getTinhTp();" >--> 
                                                    <c:forEach var="fWeek" begin = "1" end = "53">
                                                        <option  value="${fWeek}"  <c:if test = '${fn:contains(fromWeek,fWeek)}'> selected="selected" </c:if> >${fWeek}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                </div>
                        </div>
                        <div id="divFromMonth" style="display: none">
                            <div class="col-md-4">
                                    <div class="form-group" style="">
                                        
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="min-width: 150px">From Month</label>
                                                <select  name="fromMonth" id="fromMonth" class="form-control">
                                                            <!--onchange="getTinhTp();" >--> 
                                                    <c:forEach var="fMonth" begin = "1" end = "12">
                                                        <option  value="${fMonth}"  <c:if test = '${fn:contains(fromMonth,fMonth)}'> selected="selected" </c:if> >${fMonth}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                </div>
                        </div> 
                        <div class="clearfix" ></div>            
                        <div class="col-md-4">
                                    <div class="form-group" style="">
                                        <div id="divToYear" style="display: none">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="min-width: 150px">To Year</label>
                                                <select  name="toYear" id="toYear" class="form-control">
                                                            <!--onchange="getTinhTp();" >--> 
                                                    <c:forEach var="tYear" begin = "2007" end = "2027">
                                                        <option  value="${tYear}"  <c:if test = '${fn:contains(toYear,tYear)}'> selected="selected" </c:if> >${tYear}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                </div>
                        </div>            
                        <div id="divToWeek" style="display: none">
                            <div class="col-md-4">
                                    <div class="form-group" style="">
                                        
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="min-width: 150px">To Week</label>
                                                <select  name="toWeek" id="toWeek" class="form-control">
                                                            <!--onchange="getTinhTp();" >--> 
                                                    <c:forEach var="tWeek" begin = "1" end = "53">
                                                        <option  value="${tWeek}"  <c:if test = '${fn:contains(toWeek,tWeek)}'> selected="selected" </c:if> >${tWeek}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                </div>
                        </div> 
                        <div id="divToMonth" style="display: none">
                            <div class="col-md-4">
                                    <div class="form-group" style="">
                                        
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="min-width: 150px">From Month</label>
                                                <select  name="toMonth" id="toMonth" class="form-control">
                                                            <!--onchange="getTinhTp();" >--> 
                                                    <c:forEach var="tMonth" begin = "1" end = "12">
                                                        <option  value="${tMonth}"  <c:if test = '${fn:contains(toMonth,tMonth)}'> selected="selected" </c:if> >${tMonth}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                </div>
                        </div> 
                                 
                    </div>
                    <div class="clearfix" ></div>
                    <div id="divDate" style="display: none">
                        <div class="col-md-4">
                                <div class="form-group">          
                                    <div class="input-group">
                                        <!--<label class=" input-group-addon" >Date: ${hiddenTime}</label>-->   
                                        <input type="hidden" value="${hiddenTime}" id="hiddenTime"/>
                                        <input type="hidden" value="${fromDateWeek}" id="fromDateWeek"/>
                                        <input type="hidden" value="${toDateWeek}" id="toDateWeek"/>
                                        <input type="hidden" value="${fromDateMonth}" id="fromDateMonth"/>
                                        <input type="hidden" value="${toDateMonth}" id="toDateMonth"/>
                                    </div> 
                                </div>
                            </div>
                    </div>
                    
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/reportTrafficTram/reportTrafficTram?techType='+ $('#techType').val()+ '&timeType=' + $('#timeType').val()
                                                                                                                                                                            + '&fromDate=' + $('#fromDate').val()+ '&toDate=' + $('#toDate').val()
                                                                                                                                                                            + '&khuvucId=' + $('#khuvucId').val()+ '&tinhTpId=' + $('#tinhTpId').val()
                                                                                                                                                                            + '&fromWeek=' + $('#fromWeek').val()+ '&toWeek=' + $('#toWeek').val()
                                                                                                                                                                            + '&fromMonth=' + $('#fromMonth').val()+ '&toMonth=' + $('#toMonth').val()
                                                                                                                                                                            + '&fromYear=' + $('#fromYear').val()+ '&toYear=' + $('#toYear').val()"> <spring:message code="admin.common.export.excel" /></button>
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
                                    
                                    <c:if test="${techType==2}">
                                        <th>STT</th>
                                        <th>Hãng</th>
                                        <th>Mã tỉnh</th>
                                        <th>Tên tỉnh</th>
                                        <th>Thời gian</th> 
                                        <th>BTS</th> 
<!--                                        <th>Tên quản lý</th> 
                                        <th>Tên CSHT</th> -->
                                        <th>Nhóm trạm</th>
                                        <th>BSC</th>
                                        <th>Traffic 2G CS [Erl]</th> 
                                        <th>Traffic 2G PS [MB]</th> 
                                    </c:if>
                                    <c:if test="${techType==3}">
                                        <th>STT</th>
                                        <th>Hãng</th>
                                        <th>Mã tỉnh</th>
                                        <th>Tên tỉnh</th>
                                        <th>Thời gian</th> 
                                        <th>NODEB</th> 
<!--                                        <th>Tên quản lý</th> 
                                        <th>Tên CSHT</th> -->
                                        <th>Nhóm trạm</th>
                                        <th>RNC</th> 
                                        <th>CS_Total Traffic [Erl]</th>
                                        <th>PS_Total Traffic [GB]</th>
                                    </c:if>
                                    <c:if test="${techType==4}">
                                        <th>STT</th>
                                        <th>Hãng</th>
                                        <th>Mã tỉnh</th>
                                        <th>Tên tỉnh</th>
                                        <th>Thời gian</th> 
                                        <th>ENODEB</th> 
<!--                                        <th>Tên quản lý</th> 
                                        <th>Tên CSHT</th> -->
                                        <th>4G PS_Total Traffic [GB]</th>
                                    </c:if>
                                </tr>
                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="it" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${startRow + status.index}</td>
                                        <td>${it.vendor}</td>
                                        <td>${it.provinceCode}</td>
                                        <td>${it.provinceName}</td>
                                        <td>${it.date}</td>  
                                        <td>${it.tenNode}</td>
<!--                                        <td>${it.tenQuanLy}</td>
                                        <td>${it.tenCsht}</td>-->
                                        <c:if test="${techType==2 || techType==3}">
                                        <td>${it.nhomTram}</td>    
                                        <td>${it.bscRncName}</td>
                                        <td>${it.traffic2g3gCs}</td>
                                        </c:if>
                                        <td>${it.traffic2g3g4gPs}</td> 
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
        $('#khuvucId').multiselect(({
            maxHeight: 200,
            buttonWidth: '100%',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
        
        $('#tinhTpId').multiselect(({
            maxHeight: 200,
            buttonWidth: '100%',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
        getTinhTp();
    });
    if($('#timeType').val() == 1){
            $('#divFromDate').show();
            $('#divToDate').show();
            $('#divFromYear').hide();
            $('#divFromWeek').hide();
            $('#divToYear').hide();
            $('#divToWeek').hide();
            $('#divFromMonth').hide();
            $('#divToMonth').hide();
        }else if ($('#timeType').val() == 2){
            $('#divFromDate').hide();
            $('#divToDate').hide();
            $('#divFromYear').show();
            $('#divFromWeek').show();
            $('#divToYear').show();
            $('#divToWeek').show();
            $('#divFromMonth').hide();
            $('#divToMonth').hide();
            $('#divDate').show();
        }else{
            $('#divFromDate').hide();
            $('#divToDate').hide();
            $('#divFromYear').show();
            $('#divFromWeek').hide();
            $('#divToYear').show();
            $('#divToWeek').hide();
            $('#divFromMonth').show();
            $('#divToMonth').show();
            $('#divDate').show();
        }
//    $('#divFromDate').show();
//    $('#divToDate').show();
    
    function show1(){
        //alert($('#timeType').val());
        if($('#timeType').val() == 1){
            $('#divFromDate').show();
            $('#divToDate').show();
            $('#divFromYear').hide();
            $('#divFromWeek').hide();
            $('#divToYear').hide();
            $('#divToWeek').hide();
            $('#divFromMonth').hide();
            $('#divToMonth').hide();
        }else if ($('#timeType').val() == 2){
            $('#divFromDate').hide();
            $('#divToDate').hide();
            $('#divFromYear').show();
            $('#divFromWeek').show();
            $('#divToYear').show();
            $('#divToWeek').show();
            $('#divFromMonth').hide();
            $('#divToMonth').hide();
            $('#divDate').show();
        }else{
            $('#divFromDate').hide();
            $('#divToDate').hide();
            $('#divFromYear').show();
            $('#divFromWeek').hide();
            $('#divToYear').show();
            $('#divToWeek').hide();
            $('#divFromMonth').show();
            $('#divToMonth').show();
            $('#divDate').show();
        }
        
    }
    
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
