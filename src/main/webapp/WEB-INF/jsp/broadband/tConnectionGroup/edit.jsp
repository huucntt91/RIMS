<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../include/header.jsp"%>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Quản lý Connection Group</h3>
                </div>
                <form:form method="post" action="update" commandName="item">

                    <div class="box-body">
                        
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên connection group</label>                               
                                <form:hidden path="tConnectionGroupId" title="${item.tConnectionGroupId}"></form:hidden>
                                <input required type="text" class="form-control" name="groupName" placeholder="Tên" value="${item.groupName}" />
                            </div>
                        </div>   
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ghi chú</label>                                    
                                <input required value="${item.note}" type="text" class="form-control" name="note" placeholder="Ghi chú"  />
                            </div>
                        </div>
                            
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên network space</label>
                                <input required="true" type="text"  value="${item.networkSpaceName}"  class="form-control" id="networkSpaceName" placeholder="Mã network space" disabled  /> 
                                <input type="hidden" name="networkSpaceId" value="${item.networkSpaceId}" id="networkSpaceId" required />
                                <span class="input-group-btn">
                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myNetworkSpace" id="btn_networkSpace" >Tìm network space</button>
                                </span>
                            </div>
                        </div>                        

                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>           
</section>
<div id="myNetworkSpace" class="modal fade" role="dialog">
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
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
                                $(document).ready(function() {
                                    $('#btn_networkSpace').click(function() {
                                        $("#myNetworkSpace iframe").prop({'src': '${pageContext.request.contextPath}/networkSpace/popup'});
                                    });

                                    $('#myNetworkSpace iframe').on('load', function(e) {
                                        var iframe1 = $('#myNetworkSpace iframe').contents();
                                        iframe1.find('#example1 tbody').on('click', 'tr', function() {
                                            //alert($(this).text());
                                            $('#networkSpaceName').val($(this).find('input.txtcode').val());
                                            $('#networkSpaceId').val($(this).find('input.txtid').val());
                                        });
                                        iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                                            //alert($(this).text());
                                            $('#networkSpaceName').val($(this).find('input.txtcode').val());
                                            $('#networkSpaceId').val($(this).find('input.txtid').val());
                                            $('#myNetworkSpace').modal('hide');
                                        });
                                    });

                                });
</script>