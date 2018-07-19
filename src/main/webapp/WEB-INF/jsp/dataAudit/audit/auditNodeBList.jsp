<%@page import="com.vnpt.media.rims.common.Constants"%>
<%@page import="com.vnpt.media.rims.common.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="../../include/header.jsp"%>
<section class="content">

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                </div>
                <form:form method="GET" action="${pageContext.request.contextPath}/dataAuditSummary/detail/nodeb_info/-1">
                    <div class="box-body">
                        <div class="form-group">

                            <select name="typeSearch" class="form-control" >
                                <option  <c:if test='${typeSearch == "-1"}' > selected </c:if>  value="-1">--- Chọn loại sai lệch ---</option>
                                <option  <c:if test='${typeSearch == "1"}' > selected </c:if>  value="1">--- Thừa RIMS ---</option>
                                <option <c:if test='${typeSearch == "2"}' > selected </c:if>  value="2">--- Sai khác dữ liệu ---</option>
                                <option <c:if test='${typeSearch == "3"}' > selected </c:if>  value="3">--- Thiếu RIMS ---</option>                                        
                                </select> 
                            </div>                                                                                     
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Thông tin chi tiết</h3>
                </div>
                <!-- /.box-header -->

                <div class="box-body table-responsive">
                    <div id="tablePagingId">

                        <table id="example1" class="table table-bordered ">
                            <thead>
                                <tr>
                                    <th rowspan="2">STT</th>
                                    <th rowspan="2">Loại</th>
                                    <th colspan="7">Inventory</th>
                                    <th colspan="7">RIMS</th>
                                    <th rowspan="2">Compare time</th>
                                    <th rowspan="2">Xử lý</th>
                                </tr>
                                <tr>

                                    <!--<th>Mã NodeB</th>-->
                                    <th>Tên BSC RNC MNG</th>
                                    
                                    <th>Tên BSC RNC</th>
                                    <th>Tên NodeB</th>
                                    <!--<th>MSC MSS</th>-->
                                    <!--<th>SGSN</th>-->
                                    <th>DC HSDPA 42M</th>
                                    <th>FILTER USER</th>
                                    <th>FREQUENCY BAND</th>
                                    <th>Vendor</th>
                                    <!--<th>Mã NodeB</th>-->
                                    <th>Tên NodeB</th>
                                    <th>Tên BSC RNC</th>
                                    <th>Tên BSC RNC MNG</th>
                                    <!--<th>MSC MSS</th>-->
                                    <!--<th>SGSN</th>-->
                                    <th>DC HSDPA 42M</th>
                                    <th>FILTER USER</th>
                                    <th>FREQUENCY BAND</th>
                                    <th>Vendor</th>
                                </tr>
                            </thead>
                            <div align="right" style="margin-right: 50px;">${paging}</div>
                            <tbody>                                       
                                <c:forEach var="userBO" items="${list_audit_detail}" varStatus="status">                                        
                                    <tr 
                                        <c:if test="${userBO.type==1}">style="color: white;background: rgba(54, 67, 244, 0.76);"</c:if>
                                        <c:if test="${userBO.type==2}">style="color: white;background: #607D8B;"</c:if>
                                        <c:if test="${userBO.type==3}">style="color: white;background: rgba(244, 67, 54, 0.76);"</c:if>
                                            >
                                            <td>
                                            ${startRow + (status.index)}
                                        </td>
                                        <td>
                                            <c:if test="${userBO.type==1}">Thừa RIMS</c:if>
                                            <c:if test="${userBO.type==2}">Thay đổi</c:if>
                                            <c:if test="${userBO.type==3}">Thiếu RIMS</c:if>
                                            </td>
                                            <!--<td>${userBO.iNodebCode}</td>-->
                                        
                                        <td>${userBO.iBscRncMngName}</td>
                                        <td>${userBO.iBscRncName}</td>
                                        <td>${userBO.iNodebName}</td>
                                        <!--<td>${userBO.iMscMss}</td>-->
                                        <!--<td>${userBO.iSgsn}</td>-->
                                        <td>${userBO.iDcHsdpa42M}</td>
                                        <td>${userBO.iFilterUser}</td>
                                        <td>${userBO.iFrequencyBand}</td>
                                        <td>${userBO.iVendor}</td>
                                                <!--<td>${userBO.rNodebCode}</td>-->
                                        <td>${userBO.rNodeBName}</td>
                                        <td>${userBO.rBscRncName}</td>
                                        <td>${userBO.rBscRncMngName}</td>
                                        <!--<td>${userBO.rMscMss}</td>-->
<!--                                                <td>${userBO.rSgsn}</td>-->
                                        <td>${userBO.rDcHsdpa42M}</td>
                                        <td>${userBO.rFilterUser}</td>
                                        <td>${userBO.rFrequencyBand}</td>
                                        <td>${userBO.rVendor}</td>
                                        <td>${userBO.compareTime}</td>

                                        <td>
                                            <a href="<%=request.getContextPath()%>/dataAuditSummary/update/nodeb_info/${userBO.type}/${userBO.id}/1"
                                               title="Cập nhật trạng thái" onclick="return confirm('Đã cập nhật thông tin bản ghi ?')">
                                                <img src="<%=request.getContextPath()%>/image/icon/approval_request.gif">
                                            </a> 
                                            <c:if test="${userBO.type==3}">
                                                <a href="#" onclick="getRimOMC('${name}', '${userBO.id}')" data-toggle="modal" data-target="#myModal"
                                                   title="swap">
                                                    <img src="<%=request.getContextPath()%>/image/icon/ic_swap.png">
                                                </a>
                                            </c:if>
                                        </td>                                                
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
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
    <%@include file="popup.jsp"%>
</section>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tìm Kiếm</span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <iframe width="100%" height="450" frameborder="0"></iframe>
                </div>
                <div class="modal-footer">
                    <form:form method="post" action="${pageContext.request.contextPath}/dataAuditSummary/swap" >
                        <input type="hidden" name="auditId" id="auditId"/>
                        <input type="hidden" name="rNodeId" id="rNodeId" value="1"/>
                        <input type="hidden" name="name"  value="${name}"/>
                        <input type="hidden" name="type"  value="${type}"/>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-default" >Đồng ý</button>
                    </form:form>
                </div>
            </div>

        </div>
    </div>                 
</div>
<%@include file="../../include/footer.jsp"%>
<script>
 $(document).ready(function() {
        $('#myModal iframe').on('load', function(e) {
            var iframe1 = $('#myModal iframe').contents();
            iframe1.find('#example1 tbody').on('click', 'tr', function() {
                $('#rNodeId').val($(this).find('input.node_id').val());
            });
        });
    });
    function getRimOMC(name, id)
    {
        $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/nodes/popup?neTypeId=3'});
        $('#auditId').val(id);

    }

    function regOff(nodeId, idAudit) {
        var status =<%=Constants.NE_REG_OFF%>;
        var type = document.getElementById('type').value;
        $("#myModalLabel").html('Đăng ký off nodeb');
        $(".modal-body #status").val(status);
        $(".modal-body #nodeId").val(nodeId);
        $(".modal-body #idAudit").val(idAudit);
        $(".modal-body #name").val('nodeb_info');
        $(".modal-body #type").val(type);
    }
</script>