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
                    <h3 class="box-title">Quản lý Network Space</h3>
                </div>
                <form:form method="post" action="update" commandName="item">

                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên network space</label>                               
                                <form:hidden path="networkSpaceId" title="${item.networkSpaceId}"></form:hidden>
                                <input required type="text" class="form-control" name="networkSpaceName" placeholder="Tên" value="${item.networkSpaceName}" />
                            </div>
                        </div>   
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ghi chú</label>                                    
                                <input required value="${item.note}" type="text" class="form-control" name="note" placeholder="Ghi chú"  />
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

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
