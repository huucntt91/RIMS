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
                            <h3 class="box-title">Quản lý Thiết Bị</h3>
                        </div>
                        <form:form method="post" action="update" commandName="model">
                 
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tên Thiết Bị</label>                                    
                                    <form:hidden path="thietBiId" title="${model.thietBiId}"></form:hidden>   
                                        <input required type="text" class="form-control" name="tenThietBi" placeholder="Tên Thiết Bị" value="${model.tenThietBi}" />
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