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
                    <h3 class="box-title">Quản lý Băng Tần</h3>
                </div>
                <form:form method="post" action="update" commandName="model">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Tên Băng Tần</label>                                    
                            <form:hidden path="bang_tan_id" title="${model.bang_tan_id}"></form:hidden>   
                            <input required type="text" class="form-control" name="ten_bang_tan"
                                   placeholder="Tên Băng Tần" value="${model.ten_bang_tan}" />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Loại công nghệ</label>
                            <select name="id_loai_cong_nghe" id="id_loai_cong_nghe" class="form-control" required> >
                                <option value="">--- Chọn Loại công nghệ ---</option>
                                <c:forEach var="itemBO" items="${arList}">
                                    <option
                                        value="${itemBO.id_loai_cong_nghe}"
                                        <c:choose>
                                            <c:when test="${itemBO.id_loai_cong_nghe == model.id_loai_cong_nghe}">
                                                selected
                                            </c:when>    
                                        </c:choose>
                                        >${itemBO.ten_loai_cong_nghe}</option>
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