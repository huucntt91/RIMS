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
                    <h3 class="box-title">Quản lý Máy chủ</h3>
                </div>
                <form:form method="post" action="update" commandName="item">

                    <div class="box-body">
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên</label>
                                <form:hidden path="id" title="${item.id}"></form:hidden>
                                <input  type="text" class="form-control " 
                                        value="${item.serverName}"  id="serverName" name="serverName"/>                    
                            </div>
                        </div>
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ghi chú</label>
                                <input  type="text" class="form-control " 
                                        value="${item.note}"  id="note" name="note"/>                    
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

