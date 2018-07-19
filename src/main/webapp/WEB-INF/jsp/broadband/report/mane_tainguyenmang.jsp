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
                    <h3 class="box-title">Bảng thống kê tài nguyên mạng (MANE)</h3>
                </div>
                <form method="get">
                    <div class="box-body"> 
                        <div class="panel-body">
                            <div class="col-md-4">
                                <div class="form-group">          
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Dòng thiết bị</label>                                    
                                        <select  name="typeId" id="typeId" class="form-control" required="true"> 
                                            <option  value="" > ---Chọn loại ---</option>
                                            <option  value="1" <c:if test = '${typeId  == "1"}'> selected="selected" </c:if> >Huawei</option>
                                            <option  value="2" <c:if test = '${typeId  == "2"}'> selected="selected" </c:if> >Cisco</option>
                                            <option  value="3" <c:if test = '${typeId  == "3"}'> selected="selected" </c:if> >JUNIPER</option>
                                            </select>
                                        </div> 
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group" style="padding: 0 15px">
                                        <div class="input-group">
                                            <label class=" input-group-addon">Khu vực</label>
                                            <select multiple="multiple" name="khuVucId" id="khuVucId" class="form-control"
                                                    onchange="getTinhTp();" > 
                                            <c:forEach var="item" items="${khuvucList}">
                                                <option  value="${item.id}"  <c:if test = '${fn:contains(khuVucId,item.id)}'> selected="selected" </c:if> >${item.name}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group" style="padding: 0 15px">          
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                                        <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control"  > 
                                        </select>
                                        <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                                    </div> 
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                        <button type="button" class="btn btn-primary" onclick="location.href = '<%=request.getContextPath()%>/reportBB/taiNguyenMangManE?tinhTpId=' + $('#tinhTpId').val() + '&khuVucId=' + $('#khuVucId').val()">
                            <spring:message code="admin.common.export.excel" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:if test = '${typeId  == 1}'>
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Danh sách</h3>
                    </div>
                    <div class="box-body table-responsive">
                        <div id="tablePagingId">
                            <table id="example1" class="table table-bordered table-hover">
                                <thead class="text-center">
                                    <tr>
                                        <th rowspan="3" class="text-center">STT</th>
                                        <th rowspan="3" class="text-center">Khu vực</th>
                                        <th rowspan="3" class="text-center">Tên tỉnh</th>
                                        <th rowspan="3" class="text-center">Tên thiết bị</th>
                                        <th rowspan="3" class="text-center">Hãng cung cấp thiết bị</th>
                                        <th rowspan="3" class="text-center">Loại thiết bị</th>
                                        <th colspan="3" rowspan="2" class="text-center">Số lượng port trang bị</th>
                                        <th colspan="5" class="text-center">Số lượng port sử dụng</th>
                                        <th colspan="10" rowspan="2" class="text-center">Huawei</th>
                                    </tr>
                                    <tr>
                                        <th colspan="2" class="text-center">Uplink SD</th>
                                        <th colspan="2" class="text-center">Downlink SD </th>
                                        <th rowspan="2" class="text-center">Tổng link sử dụng Gb/s </th>
                                    </tr>
                                    <tr>
                                        <th class="text-center">1GE</th>
                                        <th class="text-center">10GE</th>
                                        <th class="text-center">Tổng link sử dụng Gb/s </th>
                                        <th class="text-center">1GE</th>
                                        <th class="text-center">10GE</th>
                                        <th class="text-center">1GE</th>
                                        <th class="text-center">10GE</th>
                                        <th class="text-center" >LPUF-21</th>
                                        <th class="text-center">LPUF-20</th>
                                        <th class="text-center">1x10GE</th>
                                        <th class="text-center">2x10GE</th>
                                        <th class="text-center">12x10GE</th>
                                        <th class="text-center">12xGE-B</th>
                                        <th class="text-center">LPUF-120</th>
                                        <th class="text-center">LPUI-40</th>
                                        <th class="text-center">6x10GE</th>
                                        <th class="text-center">24x1GE</th>
                                    </tr>

                                </thead>
                                <div align="right" style="margin-right: 50px;">${paging}</div>
                                <tbody>                                       
                                    <c:forEach var="it" items="${list}" varStatus="status">                                        
                                        <tr>
                                            <td>${startRow + status.index}</td>
                                            <td>${it.khuVuc}</td>
                                            <td>${it.tenTinh}</td>
                                            <td>${it.code}</td>
                                            <td>${it.vendor}</td>
                                            <td>${it.typeName}</td>
                                            <td>${it.port1GE}</td>
                                            <td>${it.port10GE}</td>
                                            <td>${it.totalLink}</td>
                                            <td>${it.upLinkPort1GE}</td>
                                            <td>${it.upLinkPort10GE}</td>
                                            <td>${it.downLinkPort1GE}</td>
                                            <td>${it.downLinkPort10GE}</td>
                                            <td>${it.totalUseLink}</td>
                                            <td>${it.cLPUF_21}</td>
                                            <td>${it.cLPUF_20}</td>
                                            <td>${it.c1x10GE}</td>
                                            <td>${it.c2x10GE}</td>
                                            <td>${it.c12xGE}</td>
                                            <td>${it.c12xGE_B}</td>
                                            <td>${it.cLPUF_120}</td>
                                            <td>${it.cLPUI_40}</td>
                                            <td>${it.c6x10GE}</td>
                                            <td>${it.c24x1GE}</td>

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
    </c:if>

    <c:if test = '${typeId  == 2}'>
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
                                        <th rowspan="3" >STT</th>
                                        <th rowspan="3">Khu vực</th>
                                        <th rowspan="3">Tên tỉnh</th>
                                        <th rowspan="3">Tên thiết bị</th>
                                        <th rowspan="3">Hãng cung cấp thiết bị</th>
                                        <th rowspan="3">Loại thiết bị</th>
                                        <th colspan="3" rowspan="2" class="text-center">Số lượng port trang bị</th>
                                        <th colspan="5" class="text-center">Số lượng port sử dụng</th>
                                        <th colspan="8" class="text-center">Cisco</th>
                                    </tr>
                                    <tr>
                                        <th colspan="2">Uplink SD</th>
                                        <th colspan="2">Downlink SD </th>
                                        <th rowspan="2">Tổng link sử dụng Gb/s </th>
                                        <th colspan="3">760x </th>
                                        <th colspan="5">ASR</th>

                                    </tr>
                                    <tr>
                                        <th>1GE</th>
                                        <th>10GE</th>
                                        <th>Tổng link sử dụng Gb/s </th>
                                        <th>1GE</th>
                                        <th>10GE</th>
                                        <th>1GE</th>
                                        <th>10GE</th>

                                        <th>2x10GE</th>
                                        <th>4x10GE</th>
                                        <th>20xGE</th>
                                        <th>Card MOD80-TR</th>
                                        <th>Card MOD80-SE</th>
                                        <th>4x10GE</th>
                                        <th>20xGE</th>
                                        <th>24x10GE-TR </th>
                                    </tr>

                                </thead>
                                <div align="right" style="margin-right: 50px;">${paging}</div>
                                <tbody>                                       
                                    <c:forEach var="it" items="${list}" varStatus="status">                                        
                                        <tr>
                                            <td>${startRow + status.index}</td>
                                            <td>${it.khuVuc}</td>
                                            <td>${it.tenTinh}</td>
                                            <td>${it.code}</td>
                                            <td>${it.vendor}</td>
                                            <td>${it.typeName}</td>
                                            <td>${it.port1GE}</td>
                                            <td>${it.port10GE}</td>
                                            <td>${it.totalLink}</td>
                                            <td>${it.upLinkPort1GE}</td>
                                            <td>${it.upLinkPort10GE}</td>
                                            <td>${it.downLinkPort1GE}</td>
                                            <td>${it.downLinkPort10GE}</td>
                                            <td>${it.totalUseLink}</td>
                                            <td>${it.c760x2x10GE}</td>
                                            <td>${it.c760x4x10GE}</td>
                                            <td>${it.c760x20xGE}</td>
                                            <td>${it.cASRCard_MOD80_TR}</td>
                                            <td>${it.cASRCard_MOD80_SE}</td>
                                            <td>${it.cASR4x10GE}</td>
                                            <td>${it.cASR20xGE}</td>
                                            <td>${it.cASR24x10GE_TR}</td>
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
    </c:if>

    <c:if test = '${typeId  == 3}'>
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
                                        <th rowspan="3" >STT</th>
                                        <th rowspan="3">Khu vực</th>
                                        <th rowspan="3">Tên tỉnh</th>
                                        <th rowspan="3">Tên thiết bị</th>
                                        <th rowspan="3" class="text-center">Hãng cung cấp thiết bị</th>
                                        <th rowspan="3" class="text-center">Loại thiết bị</th>
                                        <th colspan="3" rowspan="2" class="text-center">Số lượng port trang bị</th>
                                        <th colspan="5" class="text-center">Số lượng port sử dụng</th>
                                        <th colspan="5" class="text-center">JUNIPER</th>
                                    </tr>
                                    <tr>
                                        <th colspan="2">Uplink SD</th>
                                        <th colspan="2">Downlink SD </th>
                                        <th rowspan="2">Tổng link sử dụng Gb/s </th>
                                        <th colspan="3">MX960</th>
                                        <th colspan="2">MX2010</th>
                                    </tr>
                                    <tr>
                                        <th>1GE</th>
                                        <th>10GE</th>
                                        <th>Tổng link sử dụng Gb/s </th>
                                        <th>1GE</th>
                                        <th>10GE</th>
                                        <th>1GE</th>
                                        <th>10GE</th>

                                        <th>MPCE 2</th>
                                        <th>MIC 3D 4x 10GE</th>
                                        <th>MIC 3D 20x 1GE</th>
                                        <th>MPC6</th>
                                        <th>MPC2 </th>
                                    </tr>

                                </thead>
                                <div align="right" style="margin-right: 50px;">${paging}</div>
                                <tbody>                                       
                                    <c:forEach var="it" items="${list}" varStatus="status">                                        
                                        <tr>
                                            <td>${startRow + status.index}</td>
                                            <td>${it.khuVuc}</td>
                                            <td>${it.tenTinh}</td>
                                            <td>${it.code}</td>
                                            <td>${it.vendor}</td>
                                            <td>${it.typeName}</td>
                                            <td>${it.port1GE}</td>
                                            <td>${it.port10GE}</td>
                                            <td>${it.totalLink}</td>
                                            <td>${it.upLinkPort1GE}</td>
                                            <td>${it.upLinkPort10GE}</td>
                                            <td>${it.downLinkPort1GE}</td>
                                            <td>${it.downLinkPort10GE}</td>
                                            <td>${it.totalUseLink}</td>
                                            <td>${it.MX960MPCE2}</td>
                                            <td>${it.MX960MIC3D4x10GE}</td>
                                            <td>${it.MX960MIC3D20x1GE}</td>
                                            <td>${it.MX2010MPC6}</td>
                                            <td>${it.MX2010MPC2}</td>
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
    </c:if>
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
        $('#khuVucId').multiselect(({
            maxHeight: 200,
            buttonWidth: '300px',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
        var id = '${khuVucId}';
        if (id != null && id != '') {
            getTinhTp();
        }
    });

    function getTinhTp() {
        var id = $("#khuVucId").val();
        var tinhTpIds = $("#tinhTpIds").val();
        $.get("${pageContext.request.contextPath}/reportBB/getTinhTp?khuVucId=" + id, function (data) {
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