<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="../../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                   <button type="button" id="export" class="btn btn-bitbucket" 
                            onclick="window.history.back();">Quay lại</button>
                <div class="box-header">
                    <h3 class="box-title">Cập nhật thông tin Eq2</h3>
                </div>
                <div class="box-body" id="mydiv" >                                    
                    <form:form method="post" action="${pageContext.request.contextPath}/eq/updateEq2" commandName="tEq2Form">
                        <form:hidden path="id" value="${tEq2Form.id}"/>
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">TEq1</label>
                                <input type="hidden" name="id" value="${tEq2Form.id}" id="id" required />
                                <input type="hidden" name="tnodeId" value="${tnodeId}" id="tnodeId" required />
                                <input type="hidden" name="eq1Id" value="${tEq2Form.eq1Id}" id="eq1Id" required />
                                <form:input readonly="readonly" disabled="disabled" required="required"  path="eq1Name"
                                            class="form-control" id="eq1Name"
                                       value="${tEq2Form.eq1Name}" />   
                                <span class="input-group-btn">
                                    <button type="button"  class="btn btn-success btn_tnode1" data-toggle="modal" data-target="#myModalTnode1" id="btn_bts"  >Tìm Tnode1</button>
                                </span>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="input-group">                                
                                <label class=" input-group-addon" style="font-weight: bold; " for="exampleInputEmail1">NUMBER</label>
                                <form:input   class="form-control" path="number"
                                       value="${tEq1Form.number}" />   
                            </div>                                                                                                
                        </div>
                        <div class="form-group">
                            <div class="input-group">                                
                                <label class=" input-group-addon" style="font-weight: bold; " for="exampleInputEmail1">name</label>
                                <form:input   class="form-control"  path="name"
                                       value="${tEq1Form.name}" />   
                            </div>                                                                                                
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">vendor</label>
                                <form:input  class="form-control" placeholder="vendor"
                                             path="vendor"
                                             value="${tEq1Form.vendor}"/>  
                            </div>
                        </div>    
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">note</label>
                                <form:input  class="form-control" placeholder="note"
                                             path="note"
                                             value="${tEq1Form.note}"/>  
                            </div>
                        </div>                                      
                                                          
                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>           
</section>

<div class="modal fade" id="myModalTnode1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>                 
</div>
<div class="modal fade" id="myModalTnode2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>                 
</div>
<!-- jQuery 2.0.2 -->
<%@include file="../../include/footer.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
<!--call ajax-->
<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {        
        $('.btn_tnode1').click(function() {
            $("#myModalTnode1 iframe").prop({'src': '${pageContext.request.contextPath}/tnode/popup'});
        });
        $('#myModalTnode1 iframe').on('load', function(e) {
            var iframe1 = $('#myModalTnode1 iframe').contents();
            iframe1.find('#example1 tbody').on('click', 'tr', function() {
                $('#eq1Id').val($(this).find('input.txtid').val());
            });
            iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                //alert($(this).text());
                $('#eq1Id').val($(this).find('input.txtid').val());
                $('#eq1Name').val($(this).find('input.txtname').val());
                $('#myModalTnode1').modal('hide');
            });
        });
        
    });
</script>
