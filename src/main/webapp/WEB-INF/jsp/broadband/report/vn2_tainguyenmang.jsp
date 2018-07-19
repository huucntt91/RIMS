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
                    <h3 class="box-title">Bảng thống kê tài nguyên mạng (VN2)</h3>
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
                                            <option  value="1" <c:if test = '${typeId  == "1"}'> selected="selected" </c:if>>P router</option>
                                            <option  value="2" <c:if test = '${typeId  == "2"}'> selected="selected" </c:if>>PE,ASBR,NIX</option>

                                            </select>
                                        </div> 
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon">Khu vực</label>
                                            <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control"
                                                    onchange="getTinhTp();"
                                                    > 
                                                <!--<option value="">--- Chọn Tỉnh/Thành Phố ---</option>-->
                                            <c:forEach var="item" items="${khuvucList}">
                                                <option  value="${item.id}"  <c:if test = '${fn:contains(khuvucId,item.id)}'> selected="selected" </c:if> >${item.name}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">          
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
                      <button type="button" id="export" class="btn btn-primary" 
                            onclick="exportExcel();"><spring:message code="admin.common.export.excel" /></button>
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
                                <thead>
                                    <tr>
                                        <th rowspan="3" >STT</th>
                                        <th rowspan="3">Khu vực</th>
                                        <th rowspan="3">Tên tỉnh</th>
                                        <th rowspan="3">Tên thiết bị</th>
                                        <th rowspan="3">Hãng cung cấp thiết bị</th>
                                        <th rowspan="3">Loại thiết bị</th>
                                        <th colspan="3" rowspan="2">Số lượng port trang bị</th>
                                        <th colspan="5">Số lượng port sử dụng</th>
                                        <th colspan="9" style="text-align: center" rowspan="2">Các loai card trang bị P</th>
                                    </tr>
                                    <tr>
                                        <th colspan="2">Uplink SD</th>
                                        <th colspan="2">Downlink SD </th>
                                        <th rowspan="2">Tổng link sử dụng Gb/s </th>
                                    </tr>
                                    <tr>
                                        <th>1GE</th>
                                        <th>10GE</th>
                                        <th>Tổng link sử dụng Gb/s </th>
                                        <th>1GE</th>
                                        <th>10GE</th>
                                        <th>1GE</th>
                                        <th>10GE</th>
                                        <th>FPC Type 5-3D</th>
                                        <th>FPC Type 4-ES</th>
                                        <th>FPC Type 3-ES</th>
                                        <th>PIC 1x100GE</th>
                                        <th>PIC 24x10GE</th>
                                        <th>PIC 10x10GE</th>
                                        <th>PIC 4x10GE</th>
                                        <th>PIC 4xSTM64(OC192)</th>
                                        <th>PIC 4xSTM16</th>
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
                                        <th colspan="3" rowspan="2">Số lượng port trang bị</th>
                                        <th colspan="5">Số lượng port sử dụng</th>
                                        <th colspan="9" rowspan="2" style="text-align: center" >Các loai card trang bị PE 7750</th>
                                        <th colspan="6" rowspan="2" style="text-align: center" >Các loại card trang bị PE MX960</th>
                                    </tr>
                                    <tr>
                                        <th colspan="2">Uplink SD</th>
                                        <th colspan="2">Downlink SD </th>
                                        <th rowspan="2">Tổng link sử dụng Gb/s </th>

                                    </tr>
                                    <tr>
                                        <th>1GE</th>
                                        <th>10GE</th>
                                        <th>Tổng link sử dụng Gb/s </th>
                                        <th>1GE</th>
                                        <th>10GE</th>
                                        <th>1GE</th>
                                        <th>10GE</th>

                                        <th>IOM3</th>
                                        <th>IOM2</th>
                                        <th>m2-10gb</th>
                                        <th>m1-10gb</th>
                                        <th>m1-oc192</th>
                                        <th>m4-oc48</th>
                                        <th>m2-oc48</th>
                                        <th>m10-1gb</th>
                                        <th>m5-1gb</th>
                                        <th>MPC4E 3D 32XGE</th>
                                        <th>MPCE Type 2</th>
                                        <th>MPC4E 3D 2CGE+8XGE</th>
                                        <th>MIC4x 10GE</th>
                                        <th>MIC 2x 10GE</th>
                                        <th>MIC 20x 1GE</th>
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
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
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
<script src="${pageContext.request.contextPath}/resources/js/fileSaver.js" type="text/javascript"></script>
<script>
    function exportExcel(){
        var data = $('#example1').html();
        fnExcelReport(data,"Tai_nguyen_mang_VN2");
    }
    $(document).ready(function () {
        $('#tinhTpId').multiselect(({
            maxHeight: 200,
            buttonWidth: '300px',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
        $('#khuvucId').multiselect(({
            maxHeight: 200,
            buttonWidth: '300px',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
    });

    function getTinhTp() {
        var id = $("#khuvucId").val();
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