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
                    <h3 class="box-title">Quản lý Logical link</h3>
                </div>
                <form:form method="post" action="update" commandName="item">

                    <div class="box-body">
                        
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên logical link</label>                               
                                <form:hidden path="logicalLinkId" title="${item.logicalLinkId}"></form:hidden>
                                <input required type="text" class="form-control" name="logicalLinkName" placeholder="Tên" value="${item.logicalLinkName}" />
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
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Băng thông</label>                                    
                                <input required value="${item.bandwidth}" type="text" class="form-control" name="bandwidth" placeholder="Băng thông"  />
                            </div>
                        </div>    
                            
                        
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập tên path</label>
                                <input required="true" type="text"  value="${item.tpathName}"  class="form-control" id="tpathName" placeholder="Tên path" disabled  /> 
                                <input type="hidden" name="tpathId" value="${item.tpathId}" id="tpathId" required />
                                <span class="input-group-btn">
                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myPath" id="btn_path" >Tìm path</button>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập tên link</label>
                                <input required="true" type="text"  value="${item.tlinkName}"  class="form-control" id="tlinkName" placeholder="Tên link" disabled  /> 
                                <input type="hidden" name="tlinkId" value="${item.tlinkId}" id="tlinkId" required />
                                <span class="input-group-btn">
                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myLink" id="btn_link" >Tìm link</button>
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
<div id="myPath" class="modal fade" role="dialog">
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
<div id="myLink" class="modal fade" role="dialog">
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
                                    $('#btn_path').click(function() {
                                        $("#myPath iframe").prop({'src': '${pageContext.request.contextPath}/tPath/popup'});
                                    });

                                    $('#myPath iframe').on('load', function(e) {
                                        var iframe1 = $('#myPath iframe').contents();
                                        iframe1.find('#example1 tbody').on('click', 'tr', function() {
                                            //alert($(this).text());
                                            $('#tpathName').val($(this).find('input.txtcode').val());
                                            $('#tpathId').val($(this).find('input.txtid').val());
                                        });
                                        iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                                            //alert($(this).text());
                                            $('#tpathName').val($(this).find('input.txtcode').val());
                                            $('#tpathId').val($(this).find('input.txtid').val());
                                            $('#myPath').modal('hide');
                                        });
                                    });
                                    
                                    $('#btn_link').click(function() {
                                        $("#myLink iframe").prop({'src': '${pageContext.request.contextPath}/tlink/popup'});
                                    });

                                    $('#myLink iframe').on('load', function(e) {
                                        var iframe1 = $('#myLink iframe').contents();
                                        iframe1.find('#example1 tbody').on('click', 'tr', function() {
                                            //alert($(this).text());
                                            $('#tlinkName').val($(this).find('input.txtLinkName').val());
                                            $('#tlinkId').val($(this).find('input.txtLinkId').val());
                                        });
                                        iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                                            //alert($(this).text());
                                            $('#tlinkName').val($(this).find('input.txtLinkName').val());
                                            $('#tlinkId').val($(this).find('input.txtLinkId').val());
                                            $('#myLink').modal('hide');
                                        });
                                    });

                                });
</script>