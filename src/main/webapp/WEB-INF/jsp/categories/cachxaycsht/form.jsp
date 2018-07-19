<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="../../include/header.jsp"%>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Quản lý Cách Xây CSHT</h3>
                </div>
                <form:form method="post" action="update" commandName="model">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Tên Cách Xây CSHT</label>                                    
                            <form:hidden path="cach_xay_csht_id" title="${model.cach_xay_csht_id}"></form:hidden>   
                            <input required type="text" class="form-control" name="ten_cach_xay_csht"
                                   placeholder="Tên Cách Xây CSHT" value="${model.ten_cach_xay_csht}" />
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

<%@include file="../../include/footer.jsp"%>