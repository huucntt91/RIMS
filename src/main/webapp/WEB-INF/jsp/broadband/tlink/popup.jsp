<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="../../include/header.jsp"%>

<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <form:form method="GET" id="frm_search">
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon">Tên</label>
                            <input name="name" value="${name}"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                </div>        
            </form:form>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                 <div class="box-header">
                    <h3 class="box-title">Chọn 1 trong các giá trị bên dưới</h3>
                </div>
                <div class="box-body table-responsive">
                    <div id="tablePagingId">
                        <table id="example1" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>TLINK_NAME</th>
                                    <th>Note</th>
                                    
                                </tr>
                            </thead>                            
                            <tbody>                                       
                                <c:forEach var="temp" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <input type="hidden" class="txtLinkName" value="${temp.tLinkName}" />
                                        <input type="hidden" class="txtLinkId" value="${temp.id}" />
                                        <td>${status.index+1}</td>
                                        <td>${temp.tLinkName}</td>
                                        <td>${temp.note}</td>
                                       
                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>
                    </div>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <!-- ADD PAGE INFO -->
                    <%@include file="../../include/page_info.jsp"%>
                </div>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>
<%@include file="../../include/footer.jsp"%>
<script>
    $(function() {
        $("#example1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
    });
</script>

