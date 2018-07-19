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
                    <h3 class="box-title">Quản lý Loại Trạm</h3>
                </div>
                <form:form method="post" action="update" commandName="model">

                    <div class="box-body">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Tên Loại Trạm</label>                                    
                            <form:hidden path="loaiTramId" title="${model.loaiTramId}"></form:hidden>   
                            <input required type="text" class="form-control" name="tenLoaiTram" placeholder="Tên Loại Trạm" value="${model.tenLoaiTram}" />
                        </div> 
                        <div class="form-group">
                        <label for="exampleInputEmail1">Loại NE</label>
                        <select name="neTypeId" id="tinhTpId" class="form-control" required >
                            <option value="">---Chọn Loại NE---</option>
                            <c:forEach var="item" items="${neList}">
                                <option  
                                    value="${item.id}" <c:if test="${item.id == model.neTypeId}">    selected    </c:if>
                                    >${item.name}</option>
                            </c:forEach>
                        </select>  

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