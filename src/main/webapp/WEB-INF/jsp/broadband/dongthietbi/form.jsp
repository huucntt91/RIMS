<%-- 
    Document   : form
    Created on : Aug 8, 2017, 10:48:07 AM
    Author     : Cyano
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../../include/header.jsp"%>

<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <!--<h3 class="box-title">Quản lý Mane</h3>-->
                </div>
                <form method="post" action="${pageContext.request.contextPath}/dongthietbi/update" commandName="dongthietbi">
                    <div class="box-body" id="mydiv" >                        
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin dòng thiết bị</h3>
                            </div>
                            <div class="panel-body">  
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input type="hidden" name="dongTbiId" value="${dongthietbi.dongTbiId}" id="dongTbiId" required="true"/>
                                            <label class=" input-group-addon" >Tên</label>
                                            <input type="text" class="form-control" placeholder="Nhập tên" maxlength="100"
                                                   name="tenDongTbi" value="${dongthietbi.tenDongTbi}" required="true">
                                        </div>
                                    </div>                                                
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">                                    
                                            <label class=" input-group-addon">Hãng cung cấp</label>
                                            <select  name="tvendorId" id="tvendorId" class="form-control" required="true">
                                                <option value="">--- Chọn dòng hãng cung cấp ---</option>
                                                <c:forEach var="it" items="${vendor}">
                                                    <option value="${it.id}" <c:choose> 
                                                                <c:when test="${it.id == dongthietbi.tvendorId}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose> 
                                                            >${it.name}</option>
                                                </c:forEach>
                                            </select>                                  
                                        </div>
                                    </div>
                                </div>



                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ghi chú</label>
                                            <input  class="form-control" placeholder="Nhập ghi chú" 
                                                    name="note" value="${dongthietbi.note}">
                                        </div>
                                    </div>                                                
                                </div>
                            </div>
                        </div>
                    </div>    
                    <!-- /.box-body -->
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button type="submit"  class="btn btn-success">${btnName}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>           
</section>

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<div id="myBuilding" class="modal fade" role="dialog">
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


<script>
    $(document).ready(function() {

        $('#btn_building').click(function() {
            $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
        });
        $('#myBuilding iframe').on('load', function(e) {
            var iframe1 = $('#myBuilding iframe').contents();
            iframe1.find('#example1 tbody').on('click', 'tr', function() {
                //alert($(this).text());
                $('#ma_building').val($(this).find('input.txtcode').val());
                $('#BUILDING_ID').val($(this).find('input.txtid').val());

            });
            iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                //alert($(this).text());
                $('#ma_building').val($(this).find('input.txtcode').val());
                $('#BUILDING_ID').val($(this).find('input.txtid').val());
                $('#myBuilding').modal('hide');
            });
        });
    });

</script>

