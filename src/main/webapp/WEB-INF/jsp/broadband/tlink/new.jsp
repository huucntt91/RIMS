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
                <div class="box-header">
                    <h3 class="box-title">Cập nhật thông tin Tlink</h3>
                </div>
                <div class="box-body" id="mydiv" >                                    
                    <form:form method="post" action="${pageContext.request.contextPath}/tlink/update" commandName="tlinkForm">
                        <form:hidden path="id" value="${tlinkForm.id}"/>
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">TNode 1</label>
                                <input type="hidden" name="nodeId1" value="${tlinkForm.nodeId1}" id="nodeId1" required />
                                <form:input readonly="readonly" disabled="disabled" required="required"  path="nodeName1"
                                            class="form-control" id="nodeName1"
                                       value="${tlinkForm.nodeName1}" />   
                                <span class="input-group-btn">
                                    <button type="button"  class="btn btn-success btn_tnode1" data-toggle="modal" data-target="#myModalTnode1" id="btn_bts"  >Tìm Tnode1</button>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">TNode 2</label>

                                <input type="hidden" name="nodeId2" value="${tlinkForm.nodeId2}" id="nodeId2" required />
                                <input required type="text"  class="form-control" id="nodeName2"
                                       value=${tlinkForm.nodeName2} />      
                                <span class="input-group-btn">
                                    <button type="button"  class="btn btn-success btn_tnode2" data-toggle="modal" data-target="#myModalTnode2" id="btn_bts"  >Tìm Tnode2</button>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">                                
                                <label class=" input-group-addon" style="font-weight: bold; " for="exampleInputEmail1">Port 1</label>
                                <form:input   class="form-control" path="port1"
                                       value="${tlinkForm.port1}" />   
                            </div>                                                                                                
                        </div>
                        <div class="form-group">
                            <div class="input-group">                                
                                <label class=" input-group-addon" style="font-weight: bold; " for="exampleInputEmail1">Port 2</label>
                                <form:input   class="form-control"  path="port2"
                                       value="${tlinkForm.port2}" />   
                            </div>                                                                                                
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">IP 1</label>
                                <form:input  class="form-control" placeholder="IP 1"
                                             path="ip1"
                                             value="${tlinkForm.ip1}"/>  
                            </div>
                        </div>    
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">IP 2</label>
                                <form:input  class="form-control" placeholder="IP 2"
                                             path="ip2"
                                             value="${tlinkForm.ip2}"/>  
                            </div>
                        </div>                                      
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">Mức thu 1</label>
                                <form:input class="form-control" placeholder="Mức thu 1"
                                            path="mucThu1"
                                            value="${tlinkForm.mucThu1}"/>  
                            </div>
                        </div>                                      
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">Mức thu 2</label>
                                <form:input class="form-control" placeholder="Mức thu"
                                            path="mucThu2"
                                            value="${tlinkForm.mucThu2}"/>  
                            </div>
                        </div>                                      
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">DISTANCE_M</label>
                                <form:input class="form-control" placeholder="DISTANCE_M"
                                            path="distanceM"
                                            value="${tlinkForm.distanceM}"/>  
                            </div>
                        </div>                                      
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">extraData1</label>
                                <form:input class="form-control" placeholder="extraData1"
                                            path="extraData1"
                                            value="${tlinkForm.extraData1}"/>  
                            </div>
                        </div>                                      
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">extraData2</label>
                                <form:input class="form-control" placeholder="extraData2"
                                            path="extraData2"
                                            value="${tlinkForm.extraData2}"/>  
                            </div>
                        </div>                                      
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">tLinkName</label>
                                <form:input class="form-control" placeholder="tLinkName"
                                            path="tLinkName"
                                            value="${tlinkForm.tLinkName}"/>  
                            </div>
                        </div>                                      
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">note</label>
                                <form:input class="form-control" placeholder="note"
                                            path="note"
                                            value="${tlinkForm.note}"/>  
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
                $('#nodeId1').val($(this).find('input.txtid').val());
            });
            iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                //alert($(this).text());
                $('#nodeId1').val($(this).find('input.txtid').val());
                $('#nodeName1').val($(this).find('input.txtname').val());
                $('#myModalTnode1').modal('hide');
            });
        });
        $('.btn_tnode2').click(function() {
            $("#myModalTnode2 iframe").prop({'src': '${pageContext.request.contextPath}/tnode/popup'});
        });
        $('#myModalTnode2 iframe').on('load', function(e) {
            var iframe1 = $('#myModalTnode2 iframe').contents();
            iframe1.find('#example1 tbody').on('click', 'tr', function() {
                $('#nodeId2').val($(this).find('input.txtid').val());
            });
            iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                //alert($(this).text());
                $('#nodeId2').val($(this).find('input.txtid').val());
                $('#nodeName2').val($(this).find('input.txtname').val());
                $('#myModalTnode2').modal('hide');
            });
        });
    });
</script>
