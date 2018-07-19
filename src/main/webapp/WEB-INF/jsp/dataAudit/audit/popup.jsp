
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="modal fade" id="myModalOff" tabindex="-1" role="dialog" aria-labelledby="myModalLabelOff" aria-hidden="true">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" id="myModalLabel"></h4>

            </div>

            <div class="modal-body">

                <%--<form:form method="POST" action="approve" commandName="approveForm">--%>
                <div class="box-body table-responsive">
                    <!--<iframe width="100%" height="150" frameborder="0"></iframe>-->
                    <form:form  method="POST" action="${pageContext.request.contextPath}/dataAuditSummary/approve" commandName="approveForm">
                        <div class="form-group" style="padding: 0 5px">
                            <form:textarea rows="4" type="text" class="form-control" 
                                           path="comment"
                                           placeholder="Lý do"/>
                            
                            <form:hidden path="status" id="status"></form:hidden>
                            <form:hidden path="nodeId" id="nodeId"></form:hidden>
                            <form:hidden path="parentCode" id="parentCode"></form:hidden>
                            <form:hidden path="nodeChaId" id="nodeChaId"></form:hidden>
                            <input type="hidden" name="idAudit" id="idAudit"/>
                            <input type="hidden" name="name" id="name"/>
                            <input type="hidden" name="type" id="type"/>
                            </div>

                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button id="btnUpdateComment" type="summit" class="btn btn-primary">Đồng ý</button>
                            </div>
                    </form:form>
                </div>

                <%--</form:form>--%>
            </div>

        </div>
    </div>                 
</div>
